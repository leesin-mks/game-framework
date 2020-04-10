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

import com.game.component.ComponentManager;
import com.game.net.IClientConnection;
import com.game.object.ProxyPlayer;
import com.google.protobuf.ByteString;

/**
 * @author jacken
 *
 */
public abstract class AbstractUserCmd implements ICommand
{
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());

    /**
     * 用户任务的调度分配， Handler调用
     */
    @Override
    public void execute(IClientConnection client, ByteString packet, short code) throws Exception
    {
        if (client == null)
        {
            LOGGER.error("client null error!");
            return;
        }
        ProxyPlayer player = (ProxyPlayer) client.getHolder();
        if (player == null)
        {
            // 没有创建ProxyPlayer对象前的客户端请求
            ServerCmdTask task = new ServerCmdTask(this, packet, client);
            ComponentManager.getInstance().getUserCmdThreadPool().submit(task);
        }
        else
        {
            UserCmdTask task = new UserCmdTask(this, packet, player, code);
            player.addCommandTask(task);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.command.ICommand#execute(com.bdsk.net.IClientConnection, byte[])
     */
    @Override
    public void execute(IClientConnection client, byte[] packet) throws Exception
    {
        // TODO Auto-generated method stub

    }

    public abstract void execute(ProxyPlayer player, ByteString packet);

    public void execute(ProxyPlayer player, ByteString packet, short code)
    {
        execute(player, packet);
    }

    /**
     * server excute全局调用使用(还没有玩家的)
     * 
     * @param
     */
    public void executeConnect(IClientConnection conn, ByteString packet)
    {

    }

}
