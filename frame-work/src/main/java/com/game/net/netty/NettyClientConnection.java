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

import com.game.net.AbstractClientConnection;
import com.game.net.IMessageHandler;
import com.game.type.CommonConst;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

/**
 * @author leesin
 *
 */
public class NettyClientConnection extends AbstractClientConnection
{

    private Channel channel = null;

    private boolean isServerClosed = false;

    public NettyClientConnection(IMessageHandler mesageHandler, Channel channel, int readByteslen)
    {
        super(mesageHandler, readByteslen);
        this.channel = channel;
        setChannelReadBytes();
    }

    @Override
    public String getClientIP()
    {
        String ip = channel.attr(CommonConst.CLIENT_IP).get();
        if (ip != null)
            return ip;
        InetSocketAddress inSocket = (InetSocketAddress) channel.remoteAddress();
        if (inSocket != null)
            return inSocket.getAddress().getHostAddress();
        return "";
    }

    @Override
    public void send(Object packet)
    {
        if (isConnected())
        {
            channel.writeAndFlush(packet);
        }
    }

    @Override
    public void send(byte[] packet)
    {
        ByteBuf buf = Unpooled.wrappedBuffer(packet);
        send(new BinaryWebSocketFrame(buf));
    }

    @Override
    public void setEncryptionKey(int[] key)
    {
        channel.attr(CommonConst.NETTY_ENCRYPTION_KEY).set(key);
    }

    @Override
    public void setDecryptionKey(int[] key)
    {
        channel.attr(CommonConst.NETTY_DECRYPTION_KEY).set(key);
    }

    @Override
    public void closeConnection(boolean immediately)
    {
        if (isConnected())
        {
            channel.close();
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.game.net.IClientConnection#isServerClosed()
     */
    @Override
    public boolean isServerClosed()
    {
        return isServerClosed;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.game.net.IClientConnection#setIsServerClosed(boolean)
     */
    @Override
    public void setIsServerClosed(boolean isServerClosed)
    {
        this.isServerClosed = isServerClosed;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.game.net.IClientConnection#isConnected()
     */
    @Override
    public boolean isConnected()
    {
        if (channel != null && channel.isActive())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.game.net.AbstractClientConnection#setReadBytes()
     */
    @Override
    public void setChannelReadBytes()
    {
        channel.attr(CommonConst.READ_BYTES).set(readBytes);
    }
}
