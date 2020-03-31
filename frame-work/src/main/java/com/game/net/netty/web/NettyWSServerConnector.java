/*
 * NettyWSServerConnector
 *
 * 2017年6月28日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
 */
package com.game.net.netty.web;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.net.AbstractServerConnector;
import com.game.net.IServerPacketHandler;
import com.game.net.netty.NettyServerConnector;
import com.game.type.CommonConst;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketClientCompressionHandler;

/**
 * @author jacken
 *
 */
public class NettyWSServerConnector extends AbstractServerConnector
{
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServerConnector.class);
    private Channel channel;

    /** 处理器 */
    private ChannelInboundHandlerAdapter handler = null;

    private static EventLoopGroup group = new NioEventLoopGroup();

    private static Bootstrap bootstrap = new Bootstrap();

    private static boolean setGroup = false;

    private static synchronized void init()
    {
        if (!setGroup)
        {
            bootstrap.group(group).channel(NioSocketChannel.class);
            setGroup = true;
        }
    }

    // NioSocketChannel
    /**
     * @param address
     * @param port
     * @param packetHandler
     */
    public NettyWSServerConnector(String address, int port, IServerPacketHandler packetHandler)
    {
        super(address, port, packetHandler);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.IServerConnector#connect()
     */
    @Override
    public boolean connect()
    {
        try
        {
            NettyWSServerConnector.init();
            String URL = System.getProperty("url", String.format("ws://%s:%s/websocket", getAddress(), getPort()));
            URI uri = new URI(URL);
            handler = new NettyWSServerConnectorHandler(
                    WebSocketClientHandshakerFactory.newHandshaker(
                            uri, WebSocketVersion.V13, null, true, new DefaultHttpHeaders()));

            ChannelFuture cf = connect(uri, handler);

            ((NettyWSServerConnectorHandler) handler).handshakeFuture().sync();
            channel = cf.channel();
            channel.attr(CommonConst.SERVER_CON).set(this);
            setChannelReadBytes();
            return true;
        }
        catch (Exception e)
        {
            LOGGER.error("Connector Error", e);
            return false;
        }
    }

    private static synchronized ChannelFuture connect(URI uri, ChannelInboundHandlerAdapter handler)
    {
        bootstrap.handler(new ChannelInitializer<SocketChannel>()
        {
            @Override
            protected void initChannel(SocketChannel ch)
            {
                {
                    ChannelPipeline p = ch.pipeline();
                    p.addLast(
                            new HttpClientCodec(),
                            new HttpObjectAggregator(8192),
                            WebSocketClientCompressionHandler.INSTANCE,
                            handler);
                }
            }
        });
        try
        {
            ChannelFuture cf = bootstrap.connect(uri.getHost(), uri.getPort()).sync();
            return cf;
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.IServerConnector#disconnect()
     */
    @Override
    public void disconnect()
    {
        if (channel != null)
        {
            channel.close();
            channel = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.IServerConnector#isConnected()
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
     * @see com.bdsk.net.IServerConnector#send(com.bdsk.net.CommonMessage)
     */
    public void send(Object msg)
    {
        if (isConnected())
        {
            channel.writeAndFlush(msg);
        }
    }

    @Override
    public void setChannelReadBytes()
    {
        channel.attr(CommonConst.READ_BYTES).set(readBytes);
    }

}
