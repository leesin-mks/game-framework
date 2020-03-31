/*
 * IMessageHandler
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
public interface IMessageHandler
{
    /**
     * 封包处理
     * 
     * @param conn
     *            连接
     * @param packet
     *            封包
     */
    void process(IClientConnection conn, Object packet);
}
