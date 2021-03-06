/*
 * BaseShutdownHooker
 *
 */
package com.game;

/**
 * @author leesin
 *
 */
public class BaseShutdownHooker extends Thread
{
    private RechargeServer centerServer;

    public BaseShutdownHooker(RechargeServer server)
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
