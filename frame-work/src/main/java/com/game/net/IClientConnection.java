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
 * @author leesin
 *
 */
public interface IClientConnection
{
    /**
     * 获取客户端IP
     * 
     * @return ip
     */
    String getClientIP();

    /**
     * 获取数据包处理器。
     * 
     * @return message handler
     */
    IMessageHandler getPacketHandler();

    /**
     * 获取连接持有者。
     * 
     * @return connection holder
     */
    IConnectionHolder getHolder();

    /**
     * 设置连接持有者。
     * 
     * @param holder
     *            connection holder
     */
    void setHolder(IConnectionHolder holder);

    /**
     * 发送数据包。
     * 
     * @param packet
     *            message packet
     */
    void send(Object packet);

    void send(byte[] packet);

    /**
     * 连接关闭时的回调。
     */
    void onDisconnect();

    /**
     * 设置加密密钥
     * 
     * @param key
     *            加密密钥
     */
    void setEncryptionKey(int[] key);

    /**
     * 设置解密密钥
     * 
     * @param key
     *            解密密钥
     */
    void setDecryptionKey(int[] key);

    /**
     * 
     * @param immediately
     *            close now
     */
    void closeConnection(boolean immediately);

    /**
     * 设置属性
     * 
     * @param key
     *            属性 key
     * @param value
     *            属性 value
     */
    void setAttribute(Object key, Object value);

    /**
     * 获取属性值
     * 
     * @param key
     *            属性 key
     * @return 属性 value
     */
    Object getAttribute(Object key);

    /**
     * 是否是服务器断开连接
     * 
     * @return server status
     */
    boolean isServerClosed();

    /**
     * 设置是否是服务器断开
     * 
     * @param isServerClosed
     */
    void setIsServerClosed(boolean isServerClosed);

    /**
     * 是否连接
     * 
     * @return is connected
     */
    public boolean isConnected();

    /**
     * 读取缓冲byte[]
     * 
     * @return
     */
    public byte[] getReadBytes();
}
