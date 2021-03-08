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


import com.game.net.CommonMessageHandler;
import com.game.net.IClientConnection;
import com.game.type.CommonConst;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author jacken
 *
 */
public class ClientNettyHandler extends ChannelInboundHandlerAdapter
{
    private String cmdCMPTName;

    /**
     * 
     */
    public ClientNettyHandler(String cmdCMPTName)
    {
        this.cmdCMPTName = cmdCMPTName;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception
    {
        // TODO Auto-generated method stub
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        IClientConnection conn =new NettyClientConnection(new CommonMessageHandler(cmdCMPTName), ctx.channel(), 65534);
        ctx.channel().attr(CommonConst.CLIENT_CON).set(conn);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        // super.channelRead(ctx, msg);
        IClientConnection conn = ctx.channel().attr(CommonConst.CLIENT_CON).get();
        conn.getPacketHandler().process(conn, msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception
    {
        // TODO Auto-generated method stub
        super.channelInactive(ctx);
        IClientConnection conn = ctx.channel().attr(CommonConst.CLIENT_CON).get();
        if (!conn.isServerClosed())
            conn.onDisconnect();
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
        // TODO Auto-generated method stub
        // super.exceptionCaught(ctx, cause);
    }
}
