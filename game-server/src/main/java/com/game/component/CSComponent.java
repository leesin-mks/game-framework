/*
 * 
 * FSComponent
 *
 * 2017年6月16日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
 */
package com.game.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.command.CSProtocol;
import com.game.component.inf.ICSComponent;
import com.game.component.inf.IServerListComponent;
import com.game.cs.CSMessageHandler;
import com.game.cs.CSServerConn;
import com.game.entity.bean.ServerListBean;
import com.game.net.CommonMessage;
import com.game.net.netty.NettyCommonCodecFactory;
import com.game.type.ServerType;

/**
 * @author jacken
 *
 */
public class CSComponent implements ICSComponent
{

    private static final Logger LOGGER = LoggerFactory.getLogger(CSComponent.class);

    private static IRedisComponent redis;

    private static IServerListComponent slc;

    private Map<Integer, CSServerConn> serverMap;

    private List<CSServerConn> serverList;

    private IRedisComponent getRedis()
    {
        if (redis == null)
        {
            redis = (IRedisComponent) ComponentManager.getInstance().getComponent(IRedisComponent.NAME);
        }
        return redis;
    }

    private IServerListComponent getServerComponent()
    {
        if (slc == null)
        {
            slc = (IServerListComponent) ComponentManager.getInstance().getComponent(IServerListComponent.NAME);
        }
        return slc;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.component.IComponent#getName()
     */
    @Override
    public String getName()
    {
        return NAME;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.component.IComponent#initialize()
     */
    @Override
    public boolean initialize()
    {
        serverMap = new HashMap<Integer, CSServerConn>();
        serverList = new ArrayList<CSServerConn>();
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.component.IComponent#start()
     */
    @Override
    public boolean start()
    {
        try
        {
            IServerListComponent slc = (IServerListComponent) ComponentManager.getInstance().getComponent(
                    IServerListComponent.NAME);
            for (ServerListBean bean : slc.getServerListByType(ServerType.CENTER.getValue()))
            {
                // bean.setIp("192.168.1.127");
                CSServerConn server = new CSServerConn(bean, new CSMessageHandler(), NettyCommonCodecFactory.class);
                if (server.connect())
                {
                    server.sendRegister();
                }
                else
                {
                    server.disconnect();
                }
                serverMap.put(bean.getId(), server);
                serverList.add(server);
            }
        }
        catch (Exception e)
        {
            LOGGER.error("CSComponent start fail!", e);
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.component.IComponent#stop()
     */
    @Override
    public void stop()
    {
        for (CSServerConn server : serverList)
        {
            server.setServerClosed(true);
            server.disconnect(false);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.component.IComponent#reload()
     */
    @Override
    public boolean reload()
    {
        // TODO Auto-generated method stub
        return false;
    }

    public void ping()
    {
        CommonMessage ping = new CommonMessage(CSProtocol.PING);
        sendToAll(ping);
    }

    private CSServerConn findServerByID(int id)
    {
        for (CSServerConn server : serverList)
        {
            if (server.getBean().getId() == id)
                return server;
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.niuniu.component.inf.IFSComponent#sendToAll(com.bdsk.net.
     * CommonMessage)
     */
    @Override
    public void sendToAll(CommonMessage message)
    {
        for (CSServerConn server : serverList)
        {
            server.send(message);
        }
    }

}
