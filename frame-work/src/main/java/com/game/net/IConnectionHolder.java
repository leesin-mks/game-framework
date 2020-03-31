/*
 * IConnectionHolder
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
public interface IConnectionHolder
{
    /**
     * 连接关闭时的回调。
     */
    void onDisconnect();

    /**
     * 获取持有的连接。
     * 
     * @return
     */
    IClientConnection getClientConnection();

    /**
     * 设置持有的连接。
     * 
     * @param conn
     */
    void setClientConnection(IClientConnection conn);
}
