/*
 * ServerListComponent
 *
 * 2017年8月1日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
 */
package com.game.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.bll.ServerBussiness;
import com.game.component.inf.IServerListComponent;
import com.game.entity.bean.ServerListBean;
import com.game.manager.LoginManager;
import com.game.timer.ITimerComponent;
import com.game.type.ServerType;
import com.game.web.job.UpdateServerListJob;
import com.google.gson.Gson;

/**
 * @author jacken
 *
 */
public class ServerListComponent implements IServerListComponent
{
    private static Logger LOGGER = LoggerFactory.getLogger(ServerListComponent.class);
    private static final Gson gson = new Gson();
    private Object locker = new Object();
    private Map<Integer, List<ServerListBean>> servers;     // serverType:servers
    private Map<Integer, ServerListBean> serverIDs;   // serverID:server

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
        servers = new HashMap<>();
        serverIDs = new HashMap<>();
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
        initServer();
        ((ITimerComponent) ComponentManager.getInstance().getComponent(ITimerComponent.NAME)).addJob(
                UpdateServerListJob.class.getName(), UpdateServerListJob.class, 60 * 1000);
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
        servers.clear();
        serverIDs.clear();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.component.IComponent#reload()
     */
    @Override
    public boolean reload()
    {
        stop();
        initialize();
        start();
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.niuniu.component.inf.IServerListComponent#getServerListByType(com.niuniu.type.ServerType)
     */
    @Override
    public List<ServerListBean> getServerListByType(ServerType type)
    {
        return servers.get(type.getValue());
    }

    @Override
    public ServerListBean getServerListByID(int id)
    {
        return serverIDs.get(id);
    }

    @Override
    public void updateServerList()
    {
        synchronized (locker)
        {
            servers.clear();
            initServer();
            LoginManager.init();
        }
    }

    public void initServer()
    {
        List<ServerListBean> fsList = ServerBussiness.getServerList();
        Iterator<ServerListBean> iterator = fsList.iterator();
        while (iterator.hasNext())
        {
            ServerListBean bean = iterator.next();
            List<ServerListBean> list = servers.get(bean.getServerType());
            if (list == null)
            {
                list = new ArrayList<ServerListBean>();
                servers.put(bean.getServerType(), list);
            }
            list.add(bean);
            serverIDs.put(bean.getId(), bean);
        }
    }

}
