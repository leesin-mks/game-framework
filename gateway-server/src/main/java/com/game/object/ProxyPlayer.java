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

package com.game.object;

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
import com.game.entity.bean.PlayerInfo;
import com.game.module.MessageModule;
import com.game.module.inf.IMessageModule;
import com.game.module.inf.IModule;
import com.game.net.IClientConnection;
import com.game.net.IConnectionHolder;
import com.game.pb.CommonMsgProto.CommonMsgPB;
import com.game.type.ModuleType;
import com.game.type.RedisConst;
import com.game.util.StackMessagePrint;
import com.game.util.StringUtil;
import com.google.protobuf.ByteString;

/**
 * @author leesin
 */
public class ProxyPlayer implements IConnectionHolder, ISequenceTask
{
    private final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());

    /** 命令队列 */
    private final SelfDrivenTaskQueue<UserCmdTask> cmdQueue = new SelfDrivenTaskQueue<>(
            ComponentManager.getInstance().getUserCmdThreadPool());

    /** 玩家连接引用 */
    private IClientConnection clientConn;

    /** 模块缓存 */
    private final Map<ModuleType, IModule> moduleMap;

    private IMessageModule messageModule;

    private final PlayerInfo playerInfo;

    private int gameServerID;

    public ProxyPlayer(PlayerInfo playerInfo, IClientConnection clientConn)
    {
        this.playerInfo = playerInfo;
        this.clientConn = clientConn;
        clientConn.setHolder(this);
        moduleMap = new LinkedHashMap<>();
        moduleMap.put(ModuleType.MESSAGE, new MessageModule(this));
    }

    @Override
    public void onDisconnect()
    {
        if (messageModule != null)
        {
            messageModule.sendOnDisconnect();
        }
        LOGGER.info("Proxy player disconnect: {}", playerInfo.getId());
    }

    @Override
    public IClientConnection getClientConnection()
    {
        return clientConn;
    }

    @Override
    public void setClientConnection(IClientConnection conn)
    {
        this.clientConn = conn;
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
        IRedisComponent rc = (IRedisComponent) ComponentManager.getInstance().getComponent(
                IRedisComponent.NAME);
        String serverSessionKey = rc.get(RedisConst.USER_GS_SERVER_KEY + playerInfo.getId());
        if (!StringUtil.isNumeric(serverSessionKey))
        {
            return false;
        }
        gameServerID = Integer.parseInt(serverSessionKey);
        IPlayerComponent pc = (IPlayerComponent) ComponentManager.getInstance().getComponent(IPlayerComponent.NAME);
        pc.add(playerInfo.getId(), this);
        messageModule = (MessageModule) getModule(ModuleType.MESSAGE);
        messageModule.sendLoginGameServer();

        LOGGER.info("Login success: {}", playerInfo.getId());
        return true;
    }

    public void reconnection(IClientConnection clientConn)
    {
        this.clientConn.setIsServerClosed(true);
        this.clientConn.closeConnection(true);
        this.clientConn = clientConn;
        clientConn.setHolder(this);

        messageModule = (MessageModule) getModule(ModuleType.MESSAGE);
        messageModule.sendLoginGameServer();

        LOGGER.info("Reconnect success: {}", playerInfo.getId());
    }

    public void sendPacket(CommonMsgPB.Builder commonMessage)
    {
        messageModule.sendTCP(commonMessage.build().toByteArray());
    }

    public void sendPacket(ByteString message)
    {
        messageModule.sendTCP(message.toByteArray());
    }

    public void sendPacket(byte[] message)
    {
        messageModule.sendTCP(message);
    }

    public IModule getModule(ModuleType type)
    {
        return moduleMap.get(type);
    }

    public void disconnect(boolean isShutDown)
    {
        disconnect(isShutDown, false, 0);
    }

    public void disconnect(boolean isShutDown, boolean sendLoginOut, int type)
    {
        if (clientConn != null)
        {
            if (sendLoginOut)
            {
                messageModule.sendLoginOutMessage(type);
            }
            clientConn.setIsServerClosed(isShutDown);
            clientConn.closeConnection(true);
        }

        if (isShutDown)
        {
            offline();
        }
        else
        {
            LOGGER.error("玩家失去连接, userID : {}", playerInfo.getId());
        }
    }

    protected void offline()
    {
        try
        {
            IPlayerComponent playerComponent = (IPlayerComponent) ComponentManager.getInstance().getComponent(
                    IPlayerComponent.NAME);
            ProxyPlayer player = playerComponent.getPlayerByUserID(playerInfo.getId());
            if (player == this)
            {
                playerComponent.remove(playerInfo.getId());
            }
            else
            {
                StackMessagePrint.printStackTrace();
                LOGGER.warn("Other player already when user offline: {}", playerInfo.getId());
            }
        }
        catch (Exception e)
        {
            LOGGER.error("Catch error when user quit: {}", playerInfo.getId(), e);
        }
        finally
        {
            LOGGER.info("Player offline id: {}", playerInfo.getId());
        }
    }

    public int getGameServerID()
    {
        return gameServerID;
    }
}
