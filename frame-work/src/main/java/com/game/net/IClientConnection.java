/*
 * IClientConnection
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.net;

/**
 * @author jacken
 *
 */
public interface IClientConnection
{
    /**
     * 获取客户端IP
     * 
     * @return
     */
    String getClientIP();

    /**
     * 获取数据包处理器。
     * 
     * @return
     */
    IMessageHandler getPacketHandler();

    /**
     * 获取连接持有者。
     * 
     * @return
     */
    IConnectionHolder getHolder();

    /**
     * 设置连接持有者。
     * 
     * @param holder
     */
    void setHolder(IConnectionHolder holder);

    /**
     * 发送数据包。
     * 
     * @param packet
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
     * @return
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
     * @return
     */
    public boolean isConnected();

    /**
     * 读取缓冲byte[]
     * 
     * @return
     */
    public byte[] getReadBytes();
}
