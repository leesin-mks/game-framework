/*
 * Copyright 2016-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.game.net.netty;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.net.CommonMessage;
import com.game.type.CommonConst;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

/**
 * @author leesin
 *
 */
public class NettyCodecFactory extends ByteToMessageCodec<CommonMessage>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyCodecFactory.class);

    /**
     * AS3客户端授权
     */
    private static final String POLICY = "<?xml version=\"1.0\"?><!DOCTYPE cross-domain-policy SYSTEM \"http://www.adobe.com/xml/dtds/cross-domain-policy.dtd\"><cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"*\" /></cross-domain-policy>\0";
    private static byte[] POLICY_BYTES;

    static
    {
        try
        {
            POLICY_BYTES = POLICY.getBytes(StandardCharsets.UTF_8);
        }
        catch (Exception e)
        {
            POLICY_BYTES = null;
            if (LOGGER.isErrorEnabled())
                LOGGER.error("fail to encode POLICY string");
        }
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception
    {
        if (in.readableBytes() < 4)
        {
            // 剩余不足4字节，不足以解析数据包头，暂不处理
            return;
        }

        int header;
        int packetLength;
        int[] decryptKey = getDecodeContext(ctx);
        int cipherByte1, cipherByte2, cipherByte3, cipherByte4;

        // 此处4字节头部的解码使用直接解码形式，规避频繁的对象创建
        int plainByte1, plainByte2, plainByte3, plainByte4;
        int key;

        // 解密两字节header
        cipherByte1 = in.readByte() & 0xff;
        key = decryptKey[0];
        plainByte1 = (cipherByte1 ^ key) & 0xff;

        cipherByte2 = in.readByte() & 0xff;
        key = ((decryptKey[1] + cipherByte1) ^ 1) & 0xff;
        plainByte2 = ((cipherByte2 - cipherByte1) ^ key) & 0xff;

        header = ((plainByte1 << 8) + plainByte2);

        // 解密两字节length
        cipherByte3 = in.readByte() & 0xff;
        key = ((decryptKey[2] + cipherByte2) ^ 2) & 0xff;
        plainByte3 = ((cipherByte3 - cipherByte2) ^ key) & 0xff;

        cipherByte4 = in.readByte() & 0xff;
        key = ((decryptKey[3] + cipherByte3) ^ 3) & 0xff;
        plainByte4 = ((cipherByte4 - cipherByte3) ^ key) & 0xff;
        packetLength = (plainByte3 << 8) + plainByte4;

        // 预解密长度信息成功，回溯位置
        // in.position(in.position() - 4);
        in.readerIndex(in.readerIndex() - 4);
        if (header != CommonMessage.HEADER)
        {
            if (cipherByte1 == '<' && cipherByte2 == 'p')
            {
                // 客户端请求授权文件，直接返回即可
                if (POLICY_BYTES != null)
                {
                    ctx.write(ByteBuffer.wrap(POLICY_BYTES));
                }

                else
                {
                    ctx.write(ByteBuffer.wrap(POLICY.getBytes(StandardCharsets.UTF_8)));
                }

                return;
            }

            // 非数据包头部，跳过，继续解密
            in.readerIndex(in.readerIndex() + 2);
            LOGGER.info("数据包头不匹配 hearder : " + header);
            return;
        }

        if (packetLength < CommonMessage.HDR_SIZE)
        {
            // 数据包长度错误，断开连接
            LOGGER.error(String.format(
                    "error packet length: packetlength=%d Packet.HDR_SIZE=%d",
                    packetLength, CommonMessage.HDR_SIZE));
            LOGGER.error(String.format("Disconnect the client:%s",
                    ctx.channel().remoteAddress()));
            ctx.close();
        }

        if (in.readableBytes() < packetLength)
        {
            // 数据长度不足，等待下次接收
            return;
        }

        // 读取数据并解密数据
        // byte[] data = new byte[packetLength];
        byte[] data = ctx.channel().attr(CommonConst.READ_BYTES).get();

        in.readBytes(data, 0, packetLength);

        // conn.getReadBytes() =
        decrypt(data, decryptKey, packetLength);
        CommonMessage packet = CommonMessage.build(data, packetLength);
        if (packet != null)
        {
            out.add(packet);

            // 调试打印IP和包头
            String ip = ((InetSocketAddress) ctx.channel().remoteAddress()).getAddress().toString();
            LOGGER.debug("recv: {}, {}", ip, packet.headerToStr());
        }
    }

    // 获取密钥上下文
    private int[] getDecodeContext(ChannelHandlerContext ctx)
    {
        int[] keys = ctx.channel().attr(CommonConst.NETTY_DECRYPTION_KEY).get();
        if (keys == null)
        {
            keys = new int[] { 0xae, 0xbf, 0x56, 0x78, 0xab, 0xcd, 0xef, 0xf1 };
            // LOGGER.info("getContext keys is null, set default keys");
        }
        return keys;
    }

    // 解密整段数据
    private byte[] decrypt(byte[] data, int[] decryptKey, int length) throws Exception
    {
        if (data.length == 0)
            return data;

        if (decryptKey.length < 8)
            throw new Exception("The decryptKey must be 64bits length!");

        // int length = data.length;
        int lastCipherByte;
        int plainText;
        int key;

        // 解密首字节
        lastCipherByte = data[0] & 0xff;
        data[0] ^= decryptKey[0];

        for (int index = 1; index < length; index++)
        {
            // 解密当前字节
            key = ((decryptKey[index & 0x7] + lastCipherByte) ^ index);
            plainText = (((data[index] & 0xff) - lastCipherByte) ^ key) & 0xff;

            // 更新变量值
            lastCipherByte = data[index] & 0xff;
            data[index] = (byte) plainText;
            decryptKey[index & 0x7] = (byte) (key & 0xff);
        }
        return data;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, CommonMessage in, ByteBuf out)
    {
        synchronized (ctx)
        {
            try
            {
                // 若存在不同线程给同一玩家发送数据的情况，因此加密过程需要同步处理
                // CommonMessage msg = (CommonMessage) message;

                int lastCipherByte;
                int[] encryptKey = getEncodeContext(ctx);

                byte[] plainText = in.toByteBuffer().array();

                int length = plainText.length;
                ByteBuffer cipherBuffer = ByteBuffer.allocate(length);

                // 加密首字节
                lastCipherByte = (byte) ((plainText[0] ^ encryptKey[0]) & 0xff);
                cipherBuffer.put((byte) lastCipherByte);

                // 循环加密
                int keyIndex;
                for (int i = 1; i < length; i++)
                {
                    keyIndex = i & 0x7;
                    encryptKey[keyIndex] = ((encryptKey[keyIndex] + lastCipherByte) ^ i) & 0xff;
                    lastCipherByte = (((plainText[i] ^ encryptKey[keyIndex]) & 0xff) + lastCipherByte) & 0xff;
                    cipherBuffer.put((byte) lastCipherByte);
                }

                cipherBuffer.flip();
                out.writeBytes(cipherBuffer.array());
                ctx.flush();

                // 调试打印IP和包头
                String ip = ((InetSocketAddress) ctx.channel().remoteAddress()).getAddress().toString();
                if (LOGGER.isDebugEnabled())
                {
                    LOGGER.debug("send: {}, {}", ip, in.headerToStr());
                }
            }
            catch (Exception ex)
            {
                LOGGER.error("catch error for encoding packet:", ex);
                throw ex;
            }
        }
    }

    // 获取当前加密密钥
    private int[] getEncodeContext(ChannelHandlerContext ctx)
    {
        int[] keys = ctx.channel().attr(CommonConst.NETTY_ENCRYPTION_KEY).get();
        if (keys == null)
        {
            keys = new int[] { 0xae, 0xbf, 0x56, 0x78, 0xab, 0xcd, 0xef, 0xf1 };
            // LOGGER.error("getContext keys is null, set default keys");
        }
        return keys;
    }

    public static String toHexDump(String description, int[] dump, int start,
            int count)
    {
        String hexDump = "";
        if (description != null)
        {
            hexDump += description;
            hexDump += "\n";
        }
        int end = start + count;
        for (int i = start; i < end; i += 16)
        {
            String text = "";
            String hex = "";

            for (int j = 0; j < 16; j++)
            {
                if (j + i < end)
                {
                    int val = dump[j + i];
                    if (val < 0)
                        val = (val + 256) & 0xFF;
                    if (val < 16)
                    {
                        hex += "0" + Integer.toHexString(val) + " ";
                    }
                    else
                    {
                        hex += Integer.toHexString(val) + " ";
                    }

                    if (val >= 32 && val <= 127)
                    {
                        text += (char) val;
                    }
                    else
                    {
                        text += ".";
                    }
                }
                else
                {
                    hex += "   ";
                    text += " ";
                }
            }
            hex += "  ";
            hex += text;
            hex += '\n';
            hexDump += hex;
        }
        return hexDump;
    }

    public static String toHexDump(String description, byte[] dump, int start,
            int count)
    {
        int[] temps = new int[dump.length];
        System.arraycopy(dump, 0, temps, 0, dump.length);
        return toHexDump(description, temps, start, count);
    }
}
