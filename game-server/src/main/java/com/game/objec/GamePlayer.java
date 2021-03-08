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

package com.game.objec;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.command.ISequenceTask;
import com.game.command.SelfDrivenTaskQueue;
import com.game.command.UserCmdTask;
import com.game.component.ComponentManager;
import com.game.component.IRedisComponent;
import com.game.component.inf.IPlayerComponent;
import com.game.cs.CSServerConn;
import com.game.database.DataOption;
import com.game.entity.bean.PlayerInfo;
import com.game.module.MessageModule;
import com.game.module.inf.IMessageModule;
import com.game.module.inf.IModule;
import com.game.pb.CommonMsgProto.CommonMsgPB;
import com.game.type.ModuleType;
import com.game.type.RedisConst;
import com.game.util.StackMessagePrint;
import com.game.util.StringUtil;

/**
 * @date 2020年04月16日 20:05
 * @auth zm
 */
public class GamePlayer implements ISequenceTask
{

    private final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());

    /** 命令队列 */
    private SelfDrivenTaskQueue<UserCmdTask> cmdQueue = new SelfDrivenTaskQueue<>(
            ComponentManager.getInstance().getUserCmdThreadPool());

    /** 模块缓存 */
    private Map<ModuleType, IModule> moduleMap;

    private IMessageModule messageModule;

    private CSServerConn serverConn;

    private PlayerInfo playerInfo;

    private int gateServerID;

    private int ping;

    private boolean online;

    protected long offlineTime;

    protected Date loginTime;

    public GamePlayer(PlayerInfo playerInfo, CSServerConn clientConn)
    {
        this.playerInfo = playerInfo;
        moduleMap = new LinkedHashMap<>();
        moduleMap.put(ModuleType.MESSAGE, new MessageModule(this));
    }

    @Override
    public String getSequenceTaskName()
    {
        return "ProxyPlayerTask";
    }

    @Override
    public <T extends Runnable> void addCommandTask(T task)
    {
        cmdQueue.add((UserCmdTask) task);
    }

    @Override
    public void finishOneCommandTask()
    {
        cmdQueue.complete();
    }

    public PlayerInfo getPlayerInfo()
    {
        return playerInfo;
    }

    public boolean login()
    {
        if (playerInfo == null || playerInfo.getId() <= 0)
        {
            LOGGER.error("playerInfo is null when player login", new NullPointerException());
            return false;
        }

        if (!initGateWay())
        {
            return false;
        }

        messageModule = (MessageModule) getModule(ModuleType.MESSAGE);
        try
        {
            /***** 登陆时服务器 加载处理 四个有序步骤 不能乱 *****/
            // 步骤一： 先load数据再加队列，避免定时保存出错(初始化完玩家对象，再放到IPlayerComponent)
            if (!this.initModules())
            {
                return false;
            }
            sendInitDetail();

            // 步骤三： load 各个模块数据
            if (!loadModuleData())
            {
                return false;
            }

            // 步骤四：加载后的业务处理
            if (!this.afterDataLoaded())
            {
                return false;
            }

            IPlayerComponent pc = (IPlayerComponent) ComponentManager.getInstance().getComponent(IPlayerComponent.NAME);
            if (!pc.add(this.playerInfo.getId(), this))
            {
                return false;
            }
            setOnline(true);
            setPing(0);
            loginTime = new Date();

            LOGGER.info("Login success: {}", playerInfo.getId());
            return true;
        }
        catch (Exception e)
        {
            LOGGER.error("Player error when login: {}", playerInfo.getId(), e);
        }
        return false;
    }

    public boolean reconnection(CSServerConn clientConn)
    {
        if (!initGateWay())
        {
            return false;
        }
        sendInitDetail();
        afterDataLoaded();
        this.serverConn = clientConn;
        setOnline(true);
        setPing(0);
        loginTime = new Date();

        LOGGER.info("Reconnect success: {}", playerInfo.getId());
        return true;
    }

    public void disconnect(boolean isShutDown)
    {
        disconnect(isShutDown, false, 0);
    }

    public void disconnect(boolean isShutDown, boolean sendLoginOut, int type)
    {
        // 通知客户端离线
        messageModule.sendLoginOutMessage(isShutDown, sendLoginOut, type);

        if (isShutDown)
        {
            offline();
        }
        else
        {
            setOnline(false);
            LOGGER.error("玩家失去连接, userID : {}", getPlayerInfo().getId());
        }
    }

    protected void offline()
    {
        try
        {
            IPlayerComponent playerComponent = (IPlayerComponent) ComponentManager.getInstance().getComponent(
                    IPlayerComponent.NAME);
            GamePlayer player = playerComponent.getPlayerByUserID(playerInfo.getId());
            if (player == this)
            {
                setOnline(false);

                playerComponent.remove(playerInfo.getId());
                IRedisComponent rc = (IRedisComponent) ComponentManager.getInstance().getComponent(
                        IRedisComponent.NAME);
                String token = rc.get(RedisConst.USER_SESSION_KEY + getPlayerInfo().getId());
                if (token != null)
                {
                    rc.setex(RedisConst.USER_SESSION_KEY + getPlayerInfo().getId(), 600, token);
                }
                rc.del(RedisConst.USER_GW_SERVER_KEY + playerInfo.getId());
            }
            else
            {
                LOGGER.error("Player error when user quit: {}, other player already, stack message: {}",
                        playerInfo.getId(), StackMessagePrint.printStackTrace());
            }
        }
        catch (Exception e)
        {
            LOGGER.error("catch error when user quit:{}", playerInfo.getId(), e);
        }
        finally
        {
            LOGGER.info("Player before offline id: {}", playerInfo.getId());
            Date offlineTime = new Date();
            playerInfo.setState(0);
            playerInfo.setOfflineTime(offlineTime);
            // 保存数据
            saveModuleData();
            LOGGER.info("Player offline id: {}", playerInfo.getId());
        }
    }

    public boolean checkOffline()
    {
        // 1.5 * 60*1000 = 90000
        long offlineTick = System.currentTimeMillis() - offlineTime;
        offlineTick = 100000000;
        if ((!online && (offlineTick > 90000 && loginTime != null) || ping >= 3))
        {
            disconnect(true);
            LOGGER.info("Player checkOffline id: {}", playerInfo.getId());
            return true;
        }
        return false;
    }

    private boolean initGateWay()
    {
        IRedisComponent rc = (IRedisComponent) ComponentManager.getInstance().getComponent(
                IRedisComponent.NAME);
        String serverSessionKey = rc.get(RedisConst.USER_GW_SERVER_KEY + playerInfo.getId());
        if (!StringUtil.isNumeric(serverSessionKey))
        {
            LOGGER.warn("Can not find gate way server: {}", serverSessionKey);
            return false;
        }
        gateServerID = Integer.parseInt(serverSessionKey);
        return true;
    }

    public void sendPacket(CommonMsgPB.Builder commonMessage)
    {
        sendPacket(commonMessage.build().toByteArray());
    }

    public void sendPacket(byte[] packet)
    {
        messageModule.sendTCP(packet);
    }

    public IModule getModule(ModuleType type)
    {
        return moduleMap.get(type);
    }

    public boolean initModules()
    {
        boolean result = true;
        for (IModule module : moduleMap.values())
        {
            try
            {
                if (!module.init() && !(module instanceof MessageModule))
                {
                    LOGGER.warn("Init player module failed: {},  module: {}", playerInfo.getId(), module.getType());
                    result = false;
                }
            }
            catch (Exception e)
            {
                LOGGER.error("Init player module error: {},  module: {}", playerInfo.getId(), module.getType(), e);
                result = false;
            }
        }
        return result;
    }

    public boolean loadModuleData()
    {
        boolean result = true;
        for (IModule module : moduleMap.values())
        {
            try
            {
                if (!module.loadFromDB())
                {
                    LOGGER.error(module.getType() + " module load data from db error.");
                    result = false;
                }
            }
            catch (Exception e)
            {
                LOGGER.error(module.getType() + " module load data from db error:", e);
                result = false;
            }
        }
        return result;
    }

    private boolean afterDataLoaded()
    {
        try
        {
            for (IModule module : moduleMap.values())
            {
                module.afterDataLoaded();
            }
        }
        catch (Exception e)
        {
            LOGGER.error("afterDataLoaded error:", e);
            return false;
        }
        return true;
    }

    public boolean saveModuleData()
    {
        // long tick = System.currentTimeMillis();
        // long startTick = tick;
        for (IModule module : moduleMap.values())
        {
            try
            {
                if (!module.saveIntoDB())
                {
                    LOGGER.error(module.getType() + " module save data from db error.");
                    return false;
                }
                // long tmpTick = System.currentTimeMillis();
                /*
                 * System.out.println(module.getClass().getName() + " cost " + (tmpTick - tick)
                 * + "ms");
                 */
                // tick = tmpTick;
            }
            catch (Exception e)
            {
                LOGGER.error(module.getType() + " module load save from db error:", e);
                return false;
            }
        }
        // long tmpTick = System.currentTimeMillis();
        // System.out.println("save playerInfo cost " + (tmpTick - startTick) + "ms");
        updatePlayerInfos();
        return true;
    }

    private void sendInitDetail()
    {
        IMessageModule module = ((IMessageModule) getModule(ModuleType.MESSAGE));
        try
        {
            module.sendLoginSuccess();
        }
        catch (Exception e)
        {
            LOGGER.error("send error{}", e);
        }
    }

    public boolean updatePlayerInfos()
    {
        if (playerInfo.getOp() != DataOption.NONE)
        {
            if (true)
            {
                playerInfo.setOp(DataOption.NONE);
                return true;
            }
        }
        return true;
    }

    public int getGateServerID()
    {
        return gateServerID;
    }

    public int getPing()
    {
        return ping;
    }

    public void setPing(int ping)
    {
        this.ping = ping;
    }

    public boolean isOnline()
    {
        return online;
    }

    public void setOnline(boolean online)
    {
        this.online = online;
        if (!online)
        {
            offlineTime = System.currentTimeMillis();
        }
    }
}
