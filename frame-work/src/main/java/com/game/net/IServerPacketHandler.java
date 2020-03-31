/*
 * IServerPacketHandler
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
public interface IServerPacketHandler
{
    /**
     * 封包处理。
     * 
     * @param client
     *            连接器
     * @param packet
     *            封包
     */
    void process(IServerConnector client, Object packet);
}
