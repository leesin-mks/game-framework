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

package com.game.net.netty.web;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.net.AbstractServerConnector;
import com.game.net.IServerPacketHandler;
import com.game.net.netty.NettyServerConnector;
import com.game.type.CommonConst;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
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
 * @author leesin
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

    /**
     * (non-Javadoc)
     * 
     * @see com.game.net.IServerConnector#connect()
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

    /**
     * (non-Javadoc)
     * 
     * @see com.game.net.IServerConnector#disconnect()
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

    /**
     * (non-Javadoc)
     * 
     * @see com.game.net.IServerConnector#isConnected()
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
     * @see com.game.net.IServerConnector#send(java.lang.Object)
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
