/*
 * ServerConnectorNettyHandler
 *
 * 2016年2月24日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
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
