/*
 * ServerClient
 *
 * 2017年6月20日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
 */
package com.game.server;

import com.game.command.AbstractServerTask;
import com.game.command.ISequenceTask;
import com.game.command.SelfDrivenTaskQueue;
import com.game.component.ComponentManager;
import com.game.component.inf.IServerComponent;
import com.game.net.CommonMessage;
import com.game.net.IClientConnection;
import com.game.net.IConnectionHolder;

/**
 * @author jacken
 *
 */
public class ServerClient implements IConnectionHolder, ISequenceTask
{
    /** 命令队列 */
    private SelfDrivenTaskQueue<AbstractServerTask> cmdQueue = new SelfDrivenTaskQueue<AbstractServerTask>(
            ComponentManager.getInstance().getUserCmdThreadPool());

    private IClientConnection connection;

    private int serverID;

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.IConnectionHolder#onDisconnect()
     */
    @Override
    public void onDisconnect()
    {
        IServerComponent sc = (IServerComponent) ComponentManager.getInstance().getComponent(
                IServerComponent.NAME);
        sc.removeServerClient(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.IConnectionHolder#getClientConnection()
     */
    @Override
    public IClientConnection getClientConnection()
    {
        return connection;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.IConnectionHolder#setClientConnection(com.bdsk.net.IClientConnection)
     */
    @Override
    public void setClientConnection(IClientConnection conn)
    {
        this.connection = conn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.command.ISequenceTask#getSequenceTaskName()
     */
    @Override
    public String getSequenceTaskName()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.command.ISequenceTask#addCommandTask(java.lang.Runnable)
     */
    @Override
    public <T extends Runnable> void addCommandTask(T task)
    {
        cmdQueue.add((AbstractServerTask) task);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.command.ISequenceTask#finishOneCommandTask()
     */
    @Override
    public void finishOneCommandTask()
    {
        cmdQueue.complete();
    }

    /**
     * @param message
     */
    public void send(CommonMessage message)
    {
        getClientConnection().send(message);
    }

    public int getServerID()
    {
        return serverID;
    }

    public void setServerID(int serverID)
    {
        this.serverID = serverID;
    }

}
