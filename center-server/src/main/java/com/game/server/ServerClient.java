/*
 * Copyright 2016-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.game.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.CenterServer;
import com.game.command.AbstractServerTask;
import com.game.command.CSProtocol;
import com.game.command.ISequenceTask;
import com.game.command.SelfDrivenTaskQueue;
import com.game.component.ComponentManager;
import com.game.component.inf.IServerComponent;
import com.game.net.CommonMessage;
import com.game.net.IClientConnection;
import com.game.net.IConnectionHolder;
import com.game.pb.CenterMsgProto.RegisterMsg;

import sun.rmi.runtime.Log;

/**
 * @author leesin
 *
 */
public class ServerClient implements IConnectionHolder, ISequenceTask
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerClient.class);

    /** 命令队列 */
    private SelfDrivenTaskQueue<AbstractServerTask> cmdQueue = new SelfDrivenTaskQueue<AbstractServerTask>(
            ComponentManager.getInstance().getUserCmdThreadPool());

    private IClientConnection connection;

    private int serverID;

    /*
     * (non-Javadoc)
     * 
     * @see com.game.net.IConnectionHolder#onDisconnect()
     */
    @Override
    public void onDisconnect()
    {
        IServerComponent sc = (IServerComponent) ComponentManager.getInstance().getComponent(
                IServerComponent.NAME);
        sc.removeServerClient(this);
        LOGGER.info("Client disconnect: {}", serverID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.game.net.IConnectionHolder#getClientConnection()
     */
    @Override
    public IClientConnection getClientConnection()
    {
        return connection;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.game.net.IConnectionHolder#setClientConnection(com.bdsk.net.IClientConnection)
     */
    @Override
    public void setClientConnection(IClientConnection conn)
    {
        this.connection = conn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.game.command.ISequenceTask#getSequenceTaskName()
     */
    @Override
    public String getSequenceTaskName()
    {
        return "ServerClientTask";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.game.command.ISequenceTask#addCommandTask(java.lang.Runnable)
     */
    @Override
    public <T extends Runnable> void addCommandTask(T task)
    {
        cmdQueue.add((AbstractServerTask) task);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.game.command.ISequenceTask#finishOneCommandTask()
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

    public void sendRegisterSuccess()
    {
        CommonMessage message = new CommonMessage(CSProtocol.REGISTER);
        RegisterMsg.Builder builder = RegisterMsg.newBuilder();
        builder.setServerID(CenterServer.getInstance().getBean().getId());
        message.setBody(builder.build().toByteArray());
        send(message);
    }

}
