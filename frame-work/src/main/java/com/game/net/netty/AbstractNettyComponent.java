/*
 * AbstractNettyComponent
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.net.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.component.IComponent;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author jacken
 *
 */
public abstract class AbstractNettyComponent implements IComponent
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractNettyComponent.class);

    /** netty组件名称 */
    public static final String NAME = "netty";

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.component.IComponent#getName()
     */
    @Override
    public String getName()
    {
        return NAME;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.component.IComponent#initialize()
     */
    @Override
    public boolean initialize()
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.component.IComponent#start()
     */
    @Override
    public boolean start()
    {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        try
        {
            ServerBootstrap bootStrap = new ServerBootstrap();
            bootStrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class);

            acceptorInit(bootStrap);
            LOGGER.error("port:" + getPort());
            bootStrap.bind(getPort()).sync();
            // ChannelFuture f = bootStrap.bind(getPort()).sync();

            // f.channel().closeFuture().sync();
        }
        catch (Exception e)
        {
            LOGGER.error("netty Error:" + e.toString());
            return false;
        }
        finally
        {
            // bossGroup.shutdownGracefully();
            // workerGroup.shutdownGracefully();
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.component.IComponent#stop()
     */
    @Override
    public void stop()
    {
        if (bossGroup != null)
            bossGroup.shutdownGracefully();
        if (workerGroup != null)
            workerGroup.shutdownGracefully();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.component.IComponent#reload()
     */
    @Override
    public boolean reload()
    {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * netty初始化配置。
     * 
     * @param bootstrap
     */
    protected abstract void acceptorInit(ServerBootstrap bootstrap);

    /**
     * 获取监听的端口。
     * 
     * @return
     */
    protected abstract int getPort();

    protected abstract String getCmdCMPTName();

}
