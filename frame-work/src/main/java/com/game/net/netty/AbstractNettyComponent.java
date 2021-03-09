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

import com.game.component.IComponent;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author leesin
 *
 */
public abstract class AbstractNettyComponent implements IComponent
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractNettyComponent.class);

    /** netty组件名称 */
    public static final String NAME = "netty";

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    /**
     * (non-Javadoc)
     * 
     * @see com.game.component.IComponent#getName()
     */
    @Override
    public String getName()
    {
        return NAME;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.game.component.IComponent#initialize()
     */
    @Override
    public boolean initialize()
    {
        return true;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.game.component.IComponent#start()
     */
    @Override
    public boolean start()
    {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        try
        {
            ServerBootstrap bootStrap = new ServerBootstrap();
            bootStrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class);

            acceptorInit(bootStrap);
            LOGGER.error("port:" + getPort());
            bootStrap.bind(getPort()).sync();
            // ChannelFuture f = bootStrap.bind(getPort()).sync();
            // f.channel().closeFuture().sync();
        }
        catch (Exception e)
        {
            LOGGER.error("Netty error: ", e.toString());
            return false;
        }
        finally
        {
            // bossGroup.shutdownGracefully();
            // workerGroup.shutdownGracefully();
        }
        return true;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.game.component.IComponent#stop()
     */
    @Override
    public void stop()
    {
        if (bossGroup != null)
            bossGroup.shutdownGracefully();
        if (workerGroup != null)
            workerGroup.shutdownGracefully();
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.game.component.IComponent#reload()
     */
    @Override
    public boolean reload()
    {
        return false;
    }

    /**
     * netty初始化配置。
     * 
     * @param bootstrap
     *            server bootstrap
     */
    protected abstract void acceptorInit(ServerBootstrap bootstrap);

    /**
     * 获取监听的端口。
     * 
     * @return port
     */
    protected abstract int getPort();

    protected abstract String getCmdCMPTName();

}
