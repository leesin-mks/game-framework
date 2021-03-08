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
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.bean.StateCode;
import com.game.component.inf.IPlayerComponent;
import com.game.module.inf.IMessageModule;
import com.game.object.ProxyPlayer;
import com.game.pb.CommonMsgProto.CommonMsgPB.Builder;
import com.game.type.ModuleType;

/**
 * @author Jacken
 *
 */
public class PlayerComponent implements IPlayerComponent
{
    private Logger LOGGER = LoggerFactory.getLogger(PlayerComponent.class);

    private Map<Integer, ProxyPlayer> players;

    private Object locker = new Object();

    private Map<Integer, Integer> online;

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
        online = new HashMap<Integer, Integer>();
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
        for (ProxyPlayer player : players.values())
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
    public ProxyPlayer getPlayerByUserID(int userID)
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
            if (players.containsKey(userId))
            {
                players.remove(userId);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.game.component.inf.IPlayerComponent#add(int, com.game.object.inf.GamePlayer)
     */
    @Override
    public boolean add(int userId, ProxyPlayer player)
    {
        ProxyPlayer temp = null;
        synchronized (locker)
        {
            ProxyPlayer oldPlayer = players.get(userId);
            if (oldPlayer != null)
            {
                temp = oldPlayer;
            }
            players.put(userId, player);
        }
        if (temp != null)
        {
            IMessageModule messageModule = (IMessageModule) temp.getModule(ModuleType.MESSAGE);
            messageModule.sendLoginOutMessage(StateCode.REPEAT_LOGIN);
            temp.disconnect(true);
            LOGGER.warn("Add player but exist: {}", userId);
        }
        return true;
    }

    @Override
    public List<ProxyPlayer> getAllPlayers()
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
        for (ProxyPlayer player : players.values())
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
        for (ProxyPlayer player : players.values())
        {
            player.sendPacket(message);
        }
    }

}
