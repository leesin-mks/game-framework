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
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.Job.PlayerJob;
import com.game.bean.StateCode;
import com.game.component.inf.IPlayerComponent;
import com.game.objec.GamePlayer;
import com.game.pb.CommonMsgProto.CommonMsgPB;
import com.game.pb.CommonMsgProto.CommonMsgPB.Builder;
import com.game.pb.command.ProtocolOutProto.ProtocolOut;
import com.game.timer.ITimerComponent;
import com.google.protobuf.ByteString;

/**
 * @author leesin
 *
 */
public class PlayerComponent implements IPlayerComponent
{
    private Logger LOGGER = LoggerFactory.getLogger(PlayerComponent.class);

    private Map<Integer, GamePlayer> players;

    private static final Object locker = new Object();

    /*
     * (non-Javadoc)
     * 
     * @see little.seven.component.IComponent#getName()
     */
    @Override
    public String getName()
    {
        return NAME;
    }

    /*
     * (non-Javadoc)
     * 
     * @see little.seven.component.IComponent#initialize()
     */
    @Override
    public boolean initialize()
    {
        players = new ConcurrentHashMap<>();
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see little.seven.component.IComponent#start()
     */
    @Override
    public boolean start()
    {
        ITimerComponent timerComponent = (ITimerComponent) ComponentManager.getInstance().getComponent(
                ITimerComponent.NAME);
        timerComponent.addJob("PingJob", PlayerJob.class, "*/10 * * * * ?");

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see little.seven.component.IComponent#stop()
     */
    @Override
    public void stop()
    {
        for (GamePlayer player : players.values())
        {
            player.disconnect(true, true, StateCode.SERVER_CLOSE);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see little.seven.component.IComponent#reload()
     */
    @Override
    public boolean reload()
    {
        return false;
    }

    @Override
    public GamePlayer getPlayerByUserID(int userID)
    {
        synchronized (locker)
        {
            return players.get(userID);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.game.component.inf.IPlayerComponent#remove(int)
     */
    @Override
    public void remove(int userId)
    {
        synchronized (locker)
        {
            players.remove(userId);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.game.component.inf.IPlayerComponent#add(int, com.game.object.inf.GamePlayer)
     */
    @Override
    public boolean add(int userId, GamePlayer player)
    {
        GamePlayer temp = null;
        synchronized (locker)
        {
            GamePlayer oldPlayer = players.get(userId);
            if (oldPlayer != null)
            {
                temp = oldPlayer;
            }
            players.put(userId, player);
        }
        if (temp != null)
        {
            temp.disconnect(true);
            LOGGER.warn("Add player but exist: {}", userId);
        }
        return true;
    }

    @Override
    public List<GamePlayer> getAllPlayers()
    {
        return new ArrayList<>(players.values());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.game.component.inf.IPlayerComponent#sendToAll(com.game.pb.CommonMsgProto.CommonMsgPB.Builder)
     */
    @Override
    public void sendToAll(Builder message)
    {
        for (GamePlayer player : players.values())
        {
            player.sendPacket(message);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.game.component.inf.IPlayerComponent#sendToAll(byte[])
     */
    @Override
    public void sendToAll(byte[] message)
    {
        for (GamePlayer player : players.values())
        {
            player.sendPacket(message);
        }
    }

    public void clearOfflinePlayer()
    {
        synchronized (locker)
        {
            for (GamePlayer player : players.values())
            {
                player.checkOffline();
            }
        }
    }

    @Override
    public void ping()
    {
        CommonMsgPB.Builder msg = CommonMsgPB.newBuilder();
        msg.setCode(ProtocolOut.PING_VALUE);
        msg.setBody(ByteString.EMPTY);

        for (GamePlayer player : players.values())
        {
            if (player.getPing() >= 1)
            {
                player.setOnline(false);
            }
            player.setPing(player.getPing() + 1);
            player.sendPacket(msg);
        }
    }

}
