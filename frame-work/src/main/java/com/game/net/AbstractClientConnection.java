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
