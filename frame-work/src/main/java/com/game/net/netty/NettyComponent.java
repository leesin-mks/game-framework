/*
 * NettyComponent
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.net.netty;

import com.game.command.AbstractCommandComponent;
import com.game.config.GlobalConfigManager;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;

/**
 * @author jacken
 *
 */
public class NettyComponent extends AbstractNettyComponent
{

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.AbstractNettyComponent#acceptorInit(io.netty.bootstrap.ServerBootstrap)
     */
    @Override
    protected void acceptorInit(ServerBootstrap bootstrap)
    {
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>()
        {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception
            {
                ch.pipeline().addLast("codec", new NettyCommonCodecFactory());
                ch.pipeline().addLast(new ClientNettyHandler(getCmdCMPTName()));
            }

        }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.AbstractNettyComponent#getPort()
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
        return AbstractCommandComponent.NAME;
    }

}
