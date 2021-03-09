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

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.net.CommonWSMessageHandler;
import com.game.net.IClientConnection;
import com.game.net.netty.NettyClientConnection;
import com.game.pb.CommonMsgProto.CommonMsgPB;
import com.game.type.CommonConst;
import com.google.protobuf.InvalidProtocolBufferException;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler.HandshakeComplete;

/**
 * Echoes uppercase content of text frames.
 */
public class WebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame>
{

    private static final Logger logger = LoggerFactory.getLogger(WebSocketFrameHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame)
    {
        if (frame instanceof TextWebSocketFrame)
        {
            String request = ((TextWebSocketFrame) frame).text();
            request += ctx.channel().attr(CommonConst.CLIENT_IP).get();
            logger.info("{} received {}", ctx.channel(), request);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(request.toUpperCase(Locale.US)));
        }
        else
        {
            if ((frame instanceof BinaryWebSocketFrame))
            {
                try
                {
                    IClientConnection conn = ctx.channel().attr(CommonConst.CLIENT_CON).get();
                    int len = frame.content().readableBytes();
                    frame.content().readBytes(conn.getReadBytes(), 0, len);
                    CommonMsgPB commonMessage = CommonMsgPB.PARSER.parseFrom(conn.getReadBytes(), 0, len);
                    conn.getPacketHandler().process(conn, commonMessage);
                    // frame.content().release();
                    // frame.release();
                }
                catch (InvalidProtocolBufferException e)
                {
                    logger.error("Process packet error: ", e);
                }
            }
            else
            {
                logger.warn("can't process the frame！" + frame.toString());
            }
        }
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
        IClientConnection conn = new NettyClientConnection(new CommonWSMessageHandler(), ctx.channel(), 40960);
        ctx.channel().attr(CommonConst.CLIENT_CON).set(conn);
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
        IClientConnection conn = ctx.channel().attr(CommonConst.CLIENT_CON).get();
        if (!conn.isServerClosed())
            conn.onDisconnect();
    }

    /*
     * (non-Javadoc)
     * 
     * @see io.netty.channel.ChannelInboundHandlerAdapter#userEventTriggered(io.netty.channel.ChannelHandlerContext,
     * java.lang.Object)
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception
    {
        if (evt instanceof HandshakeComplete)
        {
            HandshakeComplete handshake = (HandshakeComplete) (evt);
            HttpHeaders headers = handshake.requestHeaders();

            String ip = headers.get("X-Forwarded-For");
            if (ip != null)
                ctx.channel().attr(CommonConst.CLIENT_IP).set(ip.split(",")[0]);
        }
        super.userEventTriggered(ctx, evt);
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
        super.exceptionCaught(ctx, cause);
        IClientConnection conn = ctx.channel().attr(CommonConst.CLIENT_CON).get();
        String ip = conn == null ? "" : conn.getClientIP();
        logger.warn("Connection error: {}", ip, cause);
    }
}
