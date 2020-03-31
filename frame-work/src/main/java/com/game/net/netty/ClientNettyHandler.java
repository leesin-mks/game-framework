/*
 * ClientNettyHandler
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
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
