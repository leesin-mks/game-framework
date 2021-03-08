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

import com.game.net.IServerConnector;
import com.game.type.CommonConst;
import com.game.util.StackMessagePrint;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author jacken
 *
 */
public class NettyServerConnectorHandler extends ChannelInboundHandlerAdapter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServerConnectorHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        IServerConnector conn = ctx.channel().attr(CommonConst.SERVER_CON).get();
        conn.getPacketHandler().process(conn, msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception
    {
        // TODO Auto-generated method stub
        super.channelInactive(ctx);
        IServerConnector conn = ctx.channel().attr(CommonConst.SERVER_CON).get();
        conn.disconnect();
    }

    /*
     * (non-Javadoc)
     * 
     * @see io.netty.channel.ChannelInboundHandlerAdapter#exceptionCaught(io.netty.channel.ChannelHandlerContext,
     * java.lang.Throwable)
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        LOGGER.error(StackMessagePrint.printErrorTrace(cause));
    }

}
