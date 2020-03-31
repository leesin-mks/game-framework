/*
 * AbstractUserCmd
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.server.ServerClient;
import com.game.component.ComponentManager;
import com.game.net.IClientConnection;
import com.google.protobuf.ByteString;

/**
 * @author jacken
 *
 */
public abstract class AbstractServerCmd implements ICommand
{
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());

    /**
     * 用户任务的调度分配， Handler调用
     */
    @Override
    public void execute(IClientConnection client, byte[] packet) throws Exception
    {
        if (client == null)
        {
            LOGGER.error("client null error!");
        }
        ServerClient server = (ServerClient) client.getHolder();

        if (server == null)
        {
            ServerCmdTask task = new ServerCmdTask(this, packet, client);
            ComponentManager.getInstance().getUserCmdThreadPool().submit(task);
        }
        else
        {
            LoginServerCmdTask task = new LoginServerCmdTask(this, packet, server);
            server.addCommandTask(task);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.command.ICommand#execute(com.bdsk.net.IClientConnection, com.google.protobuf.ByteString)
     */
    @Override
    public void execute(IClientConnection client, ByteString packet, short code) throws Exception
    {
        // TODO Auto-generated method stub

    }

    /**
     * server excute全局调用使用
     * 
     * @param
     */
    public void executeConnect(IClientConnection conn, byte[] packet)
    {

    }

    public abstract void exec(ServerClient client, byte[] packet);

}
