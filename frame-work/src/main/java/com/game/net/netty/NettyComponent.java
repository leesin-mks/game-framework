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

import com.game.command.AbstractCommandComponent;
import com.game.config.GlobalConfigManager;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;

/**
 * @author leesin
 *
 */
public class NettyComponent extends AbstractNettyComponent
{

    /**
     * (non-Javadoc)
     * 
     * @see com.game.net.netty.AbstractNettyComponent#acceptorInit(io.netty.bootstrap.ServerBootstrap)
     */
    @Override
    protected void acceptorInit(ServerBootstrap bootstrap)
    {
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>()
        {
            @Override
            protected void initChannel(SocketChannel ch)
            {
                ch.pipeline().addLast("codec", new NettyCommonCodecFactory());
                ch.pipeline().addLast(new ClientNettyHandler(getCmdCMPTName()));
            }

        }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.game.net.netty.AbstractNettyComponent#getPort()
     */
    @Override
    protected int getPort()
    {
        return Integer.parseInt(GlobalConfigManager.getInstance().getServerConfig().getPort());
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.game.net.netty.AbstractNettyComponent#getCmdCMPTName()
     */
    @Override
    protected String getCmdCMPTName()
    {
        return AbstractCommandComponent.NAME;
    }

}
