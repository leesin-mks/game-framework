/*
 * NettyClientConnection
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
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
 * @author jacken
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
        InetSocketAddress insocket = (InetSocketAddress) channel.remoteAddress();
        if (insocket != null)
            return insocket.getAddress().getHostAddress();
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

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.IClientConnection#isServerClosed()
     */
    @Override
    public boolean isServerClosed()
    {
        return isServerClosed;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.IClientConnection#setIsServerClosed(boolean)
     */
    @Override
    public void setIsServerClosed(boolean isServerClosed)
    {
        this.isServerClosed = isServerClosed;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.IClientConnection#isConnected()
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

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.AbstractClientConnection#setReadBytes()
     */
    @Override
    public void setChannelReadBytes()
    {
        channel.attr(CommonConst.READ_BYTES).set(readBytes);
    }
}
