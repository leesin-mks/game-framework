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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.game.GameServer;
import com.game.bll.ServerBussiness;
import com.game.component.inf.IServerListComponent;
import com.game.config.GlobalConfigManager;
import com.game.entity.bean.ServerListBean;

/**
 * @date 2020年03月31日 15:32
 * @auth zm
 */
public class ServerListComponent implements IServerListComponent
{
    private Map<Integer, ServerListBean> serverListBeanMap;

    private Map<Integer, List<ServerListBean>> serverListTypeMap;

    @Override
    public String getName()
    {
        return NAME;
    }

    @Override
    public boolean initialize()
    {
        serverListBeanMap = new HashMap<>();
        serverListTypeMap = new HashMap<>();
        return true;
    }

    @Override
    public boolean start()
    {
        List<ServerListBean> serverListBeanList = ServerBussiness.getServerList();
        serverListBeanMap = serverListBeanList.stream().collect(
                Collectors.toMap(ServerListBean::getId, Function.identity()));
        serverListTypeMap = serverListBeanList.stream().collect(Collectors.groupingBy(ServerListBean::getServerType));
        ServerListBean serverConfig = serverListBeanMap.get(
                GlobalConfigManager.getInstance().getServerConfig().getID());
        if (serverConfig == null)
        {
            return false;
        }
        GameServer.getInstance().setBean(serverConfig);
        return true;
    }

    @Override
    public void stop()
    {

    }

    @Override
    public boolean reload()
    {
        return false;
    }

    public List<ServerListBean> getServerListByType(int type)
    {
        return serverListTypeMap.get(type);
    }
}
