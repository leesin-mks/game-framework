/*
 * NettyCommonMessageFactory
 *
 * 2016年3月7日
 *
 * All rights reserved. This material is confidential and proprietary to 7ROAD
 */
package com.game.net.netty;

import java.nio.ByteBuffer;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.net.CommonMessage;
import com.game.type.CommonConst;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

/**
 * @author jacken
 *
 */
public class NettyCommonCodecFactory extends ByteToMessageCodec<CommonMessage>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyCommonCodecFactory.class);

    /*
     * (non-Javadoc)
     * 
     * @see io.netty.handler.codec.ByteToMessageCodec#decode(io.netty.channel.ChannelHandlerContext,
     * io.netty.buffer.ByteBuf, java.util.List)
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception
    {
        if (in.readableBytes() < CommonMessage.HDR_SIZE)
        {
            return;
        }

        final int positionBK = in.readerIndex();
        short headerFlag = (short) (in.readShort() & 0xffff);
        if (CommonMessage.HEADER != headerFlag)
        {
            LOGGER.debug("Illegal client request,can not match header flag. drop a packet,close connection.");
            // session.close();
            return;
        }

        // 长度
        int length = in.readShort();
        if (length <= 0 || length >= Short.MAX_VALUE)
        {
            // 非法的数据长度
            LOGGER.debug("Message Length Invalid Length = " + length
                    + ", drop this Message.");
            return;
        }

        if (length > in.readableBytes() + 4)
        {
            // 数据还不够读取,等待下一次读取
            in.readerIndex(positionBK); // 复位
            return;
        }
        Channel channel = ctx.channel();
        byte[] data = channel.attr(CommonConst.READ_BYTES).get();
        // byte[] pktBytes = conn.getReadBytes();

        in.readerIndex(positionBK); // 复位
        in.readBytes(data, 0, length);

        CommonMessage packet = CommonMessage.build(data, length);
        if (packet != null)
        {
            // 调试打印IP和包头
            out.add(packet);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see io.netty.handler.codec.ByteToMessageCodec#encode(io.netty.channel.ChannelHandlerContext, java.lang.Object,
     * io.netty.buffer.ByteBuf)
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, CommonMessage in, ByteBuf out) throws Exception
    {
        ByteBuffer buffer = in.toByteBuffer();

        if (buffer == null)
        {
            // 丢弃这个数据包
            LOGGER.error("Drop this packet: {}.", in.headerToStr());
            return;
        }

        out.writeBytes(buffer);
        ctx.flush();
    }

}
