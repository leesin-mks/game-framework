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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.net.AbstractServerConnector;
import com.game.net.IServerPacketHandler;
import com.game.type.CommonConst;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author leesin
 *
 */
public class NettyServerConnector extends AbstractServerConnector
{
    protected static final Logger LOGGER = LoggerFactory.getLogger(NettyServerConnector.class);

    private Channel channel;

    /** 处理器 */
    private final Class<NettyServerConnectorHandler> handler;

    /** 编码解码工厂 */
    private final Class<NettyCommonCodecFactory> codec;

    private final EventLoopGroup group = new NioEventLoopGroup();

    private final Bootstrap bootstrap = new Bootstrap();

    private boolean setGroup;

    /**
     * @param address
     *            address
     * @param port
     *            port
     * @param packetHandler
     *            packet handler
     * @param nettyCodecFactory
     *            code factory
     */
    public NettyServerConnector(String address, int port, IServerPacketHandler packetHandler,
            Class<NettyCommonCodecFactory> nettyCodecFactory)
    {
        super(address, port, packetHandler);

        handler = NettyServerConnectorHandler.class;
        this.codec = nettyCodecFactory;
    }

    private synchronized void init()
    {
        if (!setGroup)
        {
            bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true);
            setGroup = true;
        }
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
            this.init();

            bootstrap.handler(new ChannelInitializer<SocketChannel>()
            {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception
                {
                    ch.pipeline().addLast("codec", codec.newInstance());
                    ch.pipeline().addLast(handler.newInstance());

                }
            });
            ChannelFuture cf = bootstrap.connect(getAddress(), getPort()).sync();
            // bootstrap.connect().
            // ChannelFuture cf = bootstrap.connect("127.0.0.1", 5501).sync();
            channel = cf.channel();
            channel.attr(CommonConst.SERVER_CON).set(this);
            setChannelReadBytes();
            return true;
        }
        catch (Exception e)
        {
            LOGGER.error("Connector Error: {}, port: {}", getAddress(), getPort(), e);
        }
        return false;
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
            // group.shutdownGracefully();
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
        return channel != null && channel.isActive();
    }

    /**
     * (non-Javadoc)
     * 
     */
    @Override
    public void send(Object msg)
    {
        if (isConnected())
        {
            channel.writeAndFlush(msg);
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.game.net.AbstractServerConnector#setChannelReadBytes()
     */
    @Override
    public void setChannelReadBytes()
    {
        channel.attr(CommonConst.READ_BYTES).set(readBytes);
    }
}
