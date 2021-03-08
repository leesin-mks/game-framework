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

import com.game.config.GlobalConfigManager;
import com.game.net.netty.AbstractNettyComponent;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;

/**
 * @author Jacken
 *
 */
public class NettyWSComponent extends AbstractNettyComponent
{
    private static final String WEBSOCKET_PATH = "/websocket";

    /*
     * (non-Javadoc)
     * 
     * @see little.seven.net.netty.AbstractNettyComponent#acceptorInit(io.netty.bootstrap.ServerBootstrap)
     */
    @Override
    protected void acceptorInit(ServerBootstrap bootstrap)
    {
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>()
        {

            @Override
            protected void initChannel(SocketChannel ch)
                    throws Exception
            {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("http-codec", new HttpServerCodec());
                pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
                pipeline.addLast("Compression", new WebSocketServerCompressionHandler());
                pipeline.addLast("Protocol", new WebSocketServerProtocolHandler(WEBSOCKET_PATH, null, true));
                pipeline.addLast("handler", new WebSocketFrameHandler());
            }
        });

    }

    /*
     * (non-Javadoc)
     * 
     * @see little.seven.net.netty.AbstractNettyComponent#getPort()
     */
    @Override
    protected int getPort()
    {
        return Integer.valueOf(GlobalConfigManager.getInstance().getServerConfig().getPort());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.netty.AbstractNettyComponent#getCmdCMPTName()
     */
    @Override
    protected String getCmdCMPTName()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
