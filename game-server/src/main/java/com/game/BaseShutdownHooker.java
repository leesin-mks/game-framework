/*
 * BaseShutdownHooker
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game;

/**
 * @author leesin
 *
 */
public class BaseShutdownHooker extends Thread
{
    private GameServer centerServer;

    public BaseShutdownHooker(GameServer server)
    {
        this.centerServer = server;
    }

    /**
     * 退出回调，停止服务器
     */
    public void run()
    {
        if (centerServer != null)
        {
            centerServer.callBackStop();
        }
    }
}
