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

package com.game.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.game.bll.ServerBussiness;
import com.game.component.inf.IServerListComponent;
import com.game.entity.bean.ServerListBean;
import com.game.manager.LoginManager;
import com.game.timer.ITimerComponent;
import com.game.type.ServerType;
import com.game.web.job.UpdateServerListJob;

/**
 * @author leesin
 *
 */
public class ServerListComponent implements IServerListComponent
{
    private static final Object locker = new Object();
    private Map<Integer, List<ServerListBean>> servers;     // serverType:servers
    private Map<Integer, ServerListBean> serverIDs;   // serverID:server

    /**
     * (non-Javadoc)
     * 
     * @see com.game.component.IComponent#getName()
     */
    @Override
    public String getName()
    {
        return NAME;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.game.component.IComponent#initialize()
     */
    @Override
    public boolean initialize()
    {
        servers = new HashMap<>();
        serverIDs = new HashMap<>();
        return true;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.game.component.IComponent#start()
     */
    @Override
    public boolean start()
    {
        initServer();
        ((ITimerComponent) ComponentManager.getInstance().getComponent(ITimerComponent.NAME)).addJob(
                UpdateServerListJob.class.getName(), UpdateServerListJob.class, 60 * 1000);
        return true;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.game.component.IComponent#stop()
     */
    @Override
    public void stop()
    {
        servers.clear();
        serverIDs.clear();
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.game.component.IComponent#reload()
     */
    @Override
    public boolean reload()
    {
        stop();
        initialize();
        start();
        return true;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.game.component.inf.IServerListComponent#getServerListByType(com.game.type.ServerType)
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
        for (ServerListBean bean : fsList)
        {
            List<ServerListBean> list = servers.computeIfAbsent(bean.getServerType(), k -> new ArrayList<>());
            list.add(bean);
            serverIDs.put(bean.getId(), bean);
        }
    }

}
