/**
 * BaseShutdownHooker
 *
 *  @date 2021-03-06
 *
 * @author leesin
 */
package com.game;


public class BaseShutdownHooker extends Thread
{
    private final IServer server;

    public BaseShutdownHooker(IServer server)
    {
        this.server = server;
    }

    /**
     * 退出回调，停止服务器
     */
    public void run()
    {
        if (server != null)
        {
            server.callBackStop();
        }
    }
}
