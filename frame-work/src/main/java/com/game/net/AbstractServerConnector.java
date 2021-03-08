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
