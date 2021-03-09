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

package com.game.cs;

import com.game.GameServer;
import com.game.Job.CSReconnectJob;
import com.game.command.CSCmdTask;
import com.game.command.CSProtocol;
import com.game.command.ISequenceTask;
import com.game.command.SelfDrivenTaskQueue;
import com.game.component.ComponentManager;
import com.game.entity.bean.ServerListBean;
import com.game.net.CommonMessage;
import com.game.net.IServerPacketHandler;
import com.game.net.netty.NettyCommonCodecFactory;
import com.game.net.netty.NettyServerConnector;
import com.game.pb.CenterMsgProto.RegisterMsg;
import com.game.timer.ITimerComponent;
import com.game.type.ServerStateType;

/**
 * @author leesin
 */
public class CSServerConn extends NettyServerConnector implements ISequenceTask
{

    private ServerListBean bean;

    private String ipPort;

    /**
     * 命令队列
     */
    private SelfDrivenTaskQueue<CSCmdTask> cmdQueue = new SelfDrivenTaskQueue<CSCmdTask>(
            ComponentManager.getInstance().getUserCmdThreadPool());

    /**
     * @param bean
     * @param packetHandler
     * @param class1
     */
    public CSServerConn(ServerListBean bean, IServerPacketHandler packetHandler,
            Class<NettyCommonCodecFactory> class1)
    {
        super(bean.getInnerIp(), Integer.parseInt(bean.getInnerPort()), packetHandler, class1);
        this.bean = bean;
        ipPort = bean.getIp() + ":" + bean.getPort();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.game.net.netty.NettyServerConnector#disconnect()
     */
    @Override
    public void disconnect()
    {
        disconnect(true);
    }

    public void disconnect(boolean reconnect)
    {
        super.disconnect();
        if (reconnect && !isServerClosed())
        {
            ITimerComponent tc = (ITimerComponent) ComponentManager.getInstance().getComponent(ITimerComponent.NAME);
            tc.addDelayJob(this.getClass().getName() + bean.getId(), CSReconnectJob.class, 5000, -1, 5000, this);
        }
    }

    public void reconnect()
    {
        if (!isConnected() && GameServer.getInstance().getStatus() == ServerStateType.RUNNING.getValue())
        {
            if (!connect())
            {
                LOGGER.error("Reconnect fightserver fail... id:" + bean.getId());
                return;
            }
            else
            {
                sendRegister();
                LOGGER.error("Reconnect fightserver success! id:" + bean.getId());
            }
        }
        ITimerComponent tc = (ITimerComponent) ComponentManager.getInstance().getComponent(ITimerComponent.NAME);
        tc.deleteJob(this.getClass().getName() + bean.getId());
        tc.deleteJob("VideoPingJob");
    }

    public ServerListBean getBean()
    {
        return bean;
    }

    public String getIpPort()
    {
        return ipPort;
    }

    public void setIpPort(String ipPort)
    {
        this.ipPort = ipPort;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.game.command.ISequenceTask#getSequenceTaskName()
     */
    @Override
    public String getSequenceTaskName()
    {
        return "CS server connection";
    }

    /*
     * (non-Javadoc)
     *
     * @see com.game.command.ISequenceTask#addCommandTask(java.lang.Runnable)
     */
    @Override
    public <T extends Runnable> void addCommandTask(T task)
    {
        cmdQueue.add((CSCmdTask) task);
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
     *
     */
    public void sendRegister()
    {
        CommonMessage message = new CommonMessage(CSProtocol.REGISTER);
        RegisterMsg.Builder builder = RegisterMsg.newBuilder();
        builder.setServerID(GameServer.getInstance().getBean().getId());
        message.setBody(builder.build().toByteArray());
        send(message);
    }

    public int getServerID()
    {
        return bean == null ? -1 : bean.getId();
    }

}
