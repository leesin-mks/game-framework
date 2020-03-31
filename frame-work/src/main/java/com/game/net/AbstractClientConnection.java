/*
 * AbstractClientConnection
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.net;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jacken
 *
 */
public abstract class AbstractClientConnection implements IClientConnection
{
    /** 连接持有者 */
    private IConnectionHolder holder = null;
    /** 客户端封包处理器 */
    private IMessageHandler messageHandler = null;
    /** 扩展属性表 */
    private Map<Object, Object> attributes = null;

    protected byte[] readBytes;

    /**
     * 构造方法
     * 
     * @param messageHandler
     *            客户端封包处理器
     */
    public AbstractClientConnection(IMessageHandler messageHandler, int readBytesLen)
    {
        this.messageHandler = messageHandler;
        this.attributes = new ConcurrentHashMap<Object, Object>();
        readBytes = new byte[readBytesLen];
    }

    public byte[] getReadBytes()
    {
        return readBytes;
    }

    public abstract void setChannelReadBytes();

    /*
     * (non-Javadoc)
     * 
     * @see IClientConnection#getPacketHandler()
     */
    @Override
    public IMessageHandler getPacketHandler()
    {
        return messageHandler;
    }

    /*
     * (non-Javadoc)
     * 
     * @see IClientConnection#getHolder()
     */
    @Override
    public IConnectionHolder getHolder()
    {
        return holder;
    }

    /*
     * (non-Javadoc)
     * 
     * @see IClientConnection#setHolder(IConnectionHolder)
     */
    @Override
    public void setHolder(IConnectionHolder holder)
    {
        this.holder = holder;
        if (this.holder != null)
        {
            this.holder.setClientConnection(this);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see IClientConnection#onDisconnect()
     */
    @Override
    public void onDisconnect()
    {
        if (holder != null)
        {
            holder.onDisconnect();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see IClientConnection#setAttribute(java.lang.Object, java.lang.Object)
     */
    @Override
    public void setAttribute(Object key, Object value)
    {
        attributes.put(key, value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see IClientConnection#getAttribute(java.lang.Object)
     */
    @Override
    public Object getAttribute(Object key)
    {
        return attributes.get(key);
    }
}
