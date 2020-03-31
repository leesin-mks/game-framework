/*
 * BattleServer
 *
 * 2017年6月16日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
 */
package com.game.cs;

import com.game.GameServer;
import com.game.Job.FSReconnectJob;
import com.game.command.CSProtocol;
import com.game.command.FSCmdTask;
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
 * @author jacken
 *
 */
public class CSServerConn extends NettyServerConnector implements ISequenceTask
{

    private ServerListBean bean;

    private String ipPort;

    /** 命令队列 */
    private SelfDrivenTaskQueue<FSCmdTask> cmdQueue = new SelfDrivenTaskQueue<FSCmdTask>(
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
     * @see com.bdsk.net.netty.NettyServerConnector#disconnect()
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
            tc.addDelayJob(this.getClass().getName() + bean.getId(), FSReconnectJob.class, 5000, -1, 5000, this);
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
                sendLogin();
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
        cmdQueue.add((FSCmdTask) task);
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
     * 
     */
    public void sendLogin()
    {
        CommonMessage message = new CommonMessage(CSProtocol.REGISTER);
        RegisterMsg.Builder builder = RegisterMsg.newBuilder();
        builder.setServerID(getServerID());
        message.setBody(builder.build().toByteArray());
        send(message);
    }

    public int getServerID()
    {
        return bean == null ? -1 : bean.getId();
    }
}
