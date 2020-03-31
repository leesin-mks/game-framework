/*
 * NettyWSServerConnectorHandler
 *
 * 2017年6月28日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
 */
package com.game.net.netty.web;

import com.game.net.IServerConnector;
import com.game.pb.CommonMsgProto.CommonMsgPB;
import com.game.type.CommonConst;
import com.google.protobuf.InvalidProtocolBufferException;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

/**
 * @author jacken
 *
 */
public class NettyWSServerConnectorHandler extends SimpleChannelInboundHandler<Object>
{
    private final WebSocketClientHandshaker handshaker;
    private ChannelPromise handshakeFuture;

    public NettyWSServerConnectorHandler(WebSocketClientHandshaker handshaker)
    {
        this.handshaker = handshaker;
    }

    public ChannelFuture handshakeFuture()
    {
        return handshakeFuture;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx)
    {
        handshakeFuture = ctx.newPromise();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        Channel ch = ctx.channel();
        if (!handshaker.isHandshakeComplete())
        {
            handshaker.finishHandshake(ch, (FullHttpResponse) msg);
            System.out.println("WebSocket Client connected!");
            handshakeFuture.setSuccess();
            return;
        }

        WebSocketFrame frame = (WebSocketFrame) msg;
        if (frame instanceof BinaryWebSocketFrame)
        {
            // byte[] bytes = new byte[frame.content().readableBytes()];
            // frame.content().readBytes(bytes);
            try
            {
                IServerConnector conn = ctx.channel().attr(CommonConst.SERVER_CON).get();
                int len = frame.content().readableBytes();
                frame.content().readBytes(conn.getReadBytes(), 0, len);
                CommonMsgPB commonMessage = CommonMsgPB.PARSER.parseFrom(conn.getReadBytes(), 0, len);
                conn.getPacketHandler().process(conn, commonMessage);
            }
            catch (InvalidProtocolBufferException e)
            {
                e.printStackTrace();
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelInactive(io.netty.channel.ChannelHandlerContext)
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception
    {
        super.channelInactive(ctx);
        IServerConnector conn = ctx.channel().attr(CommonConst.SERVER_CON).get();
        conn.disconnect();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
    {
        cause.printStackTrace();
        if (!handshakeFuture.isDone())
        {
            handshakeFuture.setFailure(cause);
        }
        ctx.close();
    }

    /*
     * (non-Javadoc)
     * 
     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelActive(io.netty.channel.ChannelHandlerContext)
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        super.channelActive(ctx);
        handshaker.handshake(ctx.channel());
    }

}
