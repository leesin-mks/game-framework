/*
 * NettyServerConnector
 *
 * 2016年2月24日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.net.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.net.AbstractServerConnector;
import com.game.net.IServerPacketHandler;
import com.game.type.CommonConst;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author jacken
 *
 */
public class NettyServerConnector extends AbstractServerConnector
{
    protected static final Logger LOGGER = LoggerFactory.getLogger(NettyServerConnector.class);

    private Channel channel;

    /** 处理器 */
    private Class<NettyServerConnectorHandler> handler;

    /** 编码解码工厂 */
    private Class<NettyCommonCodecFactory> codec;

    private EventLoopGroup group = new NioEventLoopGroup();

    private Bootstrap bootstrap = new Bootstrap();

    private boolean setGroup;

    // NioSocketChannel
    /**
     * @param address
     * @param port
     * @param packetHandler
     * @param nettyCodecFactory
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

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.IServerConnector#connect()
     */
    @Override
    public boolean connect()
    {
        try
        {
            this.init();

            bootstrap.handler(
                    new ChannelInitializer<SocketChannel>()
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
            LOGGER.error("Connector Error", e);
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.IServerConnector#disconnect()
     */
    @Override
    public void disconnect()
    {
        if (channel != null)
        {
            // channel.close();
            // group.shutdownGracefully();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.IServerConnector#isConnected()
     */
    @Override
    public boolean isConnected()
    {
        if (channel != null && channel.isActive())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.IServerConnector#send(com.bdsk.net.CommonMessage)
     */
    @Override
    public void send(Object msg)
    {
        if (isConnected())
        {
            channel.writeAndFlush(msg);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.AbstractServerConnector#setReadBytes()
     */
    @Override
    public void setChannelReadBytes()
    {
        channel.attr(CommonConst.READ_BYTES).set(readBytes);
    }
}
