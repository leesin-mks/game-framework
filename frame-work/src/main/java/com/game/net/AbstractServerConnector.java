/*
 * AbstractServerConnector
 *
 * 2016年2月24日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.net;

/**
 * @author jacken
 *
 */
public abstract class AbstractServerConnector implements IServerConnector
{
    /** 默认的最大重连次数 */
    public static final int DEFAULT_MAX_RECONNECT_TIMES = 30;

    /** 发生重连的次数 */
    protected int reconnectedTimes;

    /** 连接地址（IP或域名） */
    private String address;
    /** 连接端口 */
    private int port;
    /** 最大重连次数 */
    private int maxReconnectTimes;
    /** 服务器包处理器 */
    private IServerPacketHandler packetHandler = null;

    private boolean isServerClosed = false;

    protected byte[] readBytes;

    /**
     * 构造方法
     * 
     * @param address
     *            连接地址（IP或域名）
     * @param port
     *            连接端口
     * @param packetHandler
     *            服务器包处理器
     */
    public AbstractServerConnector(String address, int port,
            IServerPacketHandler packetHandler)
    {
        this.address = address;
        this.port = port;
        this.maxReconnectTimes = DEFAULT_MAX_RECONNECT_TIMES;
        this.reconnectedTimes = 0;
        this.packetHandler = packetHandler;
        this.readBytes = new byte[65534];
    }
    
    public abstract void setChannelReadBytes();

    public byte[] getReadBytes()
    {
        return readBytes;
    }
    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.IServerConnector#getAddress()
     */
    @Override
    public String getAddress()
    {
        return address;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.IServerConnector#getPort()
     */
    @Override
    public int getPort()
    {
        return port;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.IServerConnector#getMaxReconnectTimes()
     */
    @Override
    public int getMaxReconnectTimes()
    {
        return maxReconnectTimes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.IServerConnector#setMaxReconnectTimes(int)
     */
    @Override
    public void setMaxReconnectTimes(int maxReconnectTimes)
    {
        this.maxReconnectTimes = maxReconnectTimes;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.IServerConnector#getReconnectedTimes()
     */
    @Override
    public int getReconnectedTimes()
    {
        return reconnectedTimes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.IServerConnector#getPacketHandler()
     */
    @Override
    public IServerPacketHandler getPacketHandler()
    {
        return packetHandler;
    }

    public boolean isServerClosed()
    {
        return isServerClosed;
    }

    public void setServerClosed(boolean isServerClosed)
    {
        this.isServerClosed = isServerClosed;
    }

}
