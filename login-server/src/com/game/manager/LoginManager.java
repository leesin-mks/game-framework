/*
 * LoginManager
 *
 * 2017年6月15日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
 */
package com.game.manager;

import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.bll.ServerBussiness;
import com.game.component.ComponentManager;
import com.game.component.IRedisComponent;
import com.game.component.inf.IServerListComponent;
import com.game.entity.bean.PlayerInfo;
import com.game.entity.bean.ServerListBean;
import com.game.type.RedisConst;
import com.game.type.ServerType;
import com.game.util.ThreadSafeRandom;
import com.game.web.bean.LoginResultBean;
import com.google.gson.Gson;

/**
 * @author jacken
 *
 */
public class LoginManager
{
    private static Logger LOGGER = LoggerFactory.getLogger(LoginManager.class);

    private static final Gson gson = new Gson();

    private static IRedisComponent rc;

    private static ServerListBean server;

    private static String updateTime;

    private static Timer timer;

    private static final int WAIT_TIME_OUT = 1000;

    private static final int LOCK_TIME_OUT = 10;

    private static ThreadSafeRandom random = new ThreadSafeRandom();

    public static String getUpdateTime()
    {
        return updateTime;
    }

    public static IRedisComponent getRedisComponent()
    {
        if (rc == null)
        {
            rc = (IRedisComponent) ComponentManager.getInstance().getComponent(IRedisComponent.NAME);
        }
        return rc;
    }

    public static String playerLogin(PlayerInfo info, String loginServerID)
    {
        LoginResultBean ret = new LoginResultBean();
        try
        {
            if (server == null)
            {
                init();
            }
            if (server != null)
            {
                IRedisComponent redisComponent = getRedisComponent();
                redisComponent.del(RedisConst.USER_GW_SERVER_KEY + info.getId());
                String serverID = redisComponent.setNX(RedisConst.USER_GW_SERVER_KEY + info.getId(),
                        String.valueOf(server.getId()));

                IServerListComponent slc = (IServerListComponent) ComponentManager.getInstance().getComponent(
                        IServerListComponent.NAME);
                ServerListBean slb = slc.getServerListByID(Integer.valueOf(serverID));
                if (slb != null)
                {
                    String key = UUID.randomUUID().toString();
                    getRedisComponent().setex(RedisConst.USER_SESSION_KEY + info.getId(), 60, key);
                    ret.setUserID(info.getId());
                    ret.setKey(key);
                    ret.setIp(slb.getIp());
                    ret.setPort(Integer.parseInt(slb.getPort()));

                    redisComponent.set(RedisConst.USER_GS_SERVER_KEY + info.getId(), loginServerID);
                    redisComponent.set(RedisConst.USER_PASSWORD + info.getId(),
                            redisComponent.get(RedisConst.USER_SESSION_KEY + info.getId()));
                }
                else
                {
                    LOGGER.error("Can not find server info: {}", serverID);
                }
            }
        }
        catch (Exception e)
        {
            LOGGER.error("player login error: {}", info.getId(), e);
        }
        return gson.toJson(ret);
    }

    /**
     * 
     */
    public static void init()
    {
        List<ServerListBean> list = ServerBussiness.getServerList();
        Iterator<ServerListBean> iterator = list.iterator();
        while (iterator.hasNext())
        {
            ServerListBean bean = iterator.next();
            if (bean.getServerType() != ServerType.GATE.getValue() || bean.getServerState() == 0)
            {
                iterator.remove();
            }
        }
        try
        {
            // for (ServerListBean bean : list)
            // {
            // if (bean.getServerState() == 0)
            // continue;
            // if (server == null
            // || bean.getOnlineNum() < server.getOnlineNum()
            // && server.getOnlineNum() > server.getMaxOnline() * 0.8)
            // {
            // server = bean;
            // }
            // }
            server = list.get(random.next(list.size()));
            LOGGER.info("LoginManager server: {}, size: {}", gson.toJson(server), list.size());
        }
        catch (Exception e)
        {
            LOGGER.error("LoginManager start fail!", e);
        }

        updateTime = getRedisComponent().get(RedisConst.SERVER_UPDATE_TIME_KEY);
    }

    public static void stop()
    {
        timer.cancel();
    }

}
