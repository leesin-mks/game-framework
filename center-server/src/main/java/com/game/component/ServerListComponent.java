package com.game.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.game.CenterServer;
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

    @Override
    public String getName()
    {
        return NAME;
    }

    @Override
    public boolean initialize()
    {
        serverListBeanMap = new HashMap<>();
        return true;
    }

    @Override
    public boolean start()
    {
        List<ServerListBean> serverListBeanList = ServerBussiness.getServerList();
        serverListBeanMap = serverListBeanList.stream().collect(
                Collectors.toMap(ServerListBean::getId, Function.identity()));
        ServerListBean serverConfig = serverListBeanMap.get(
                GlobalConfigManager.getInstance().getServerConfig().getID());
        if (serverConfig == null)
        {
            return false;
        }
        CenterServer.getInstance().setBean(serverConfig);
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
}
