/*
 * PlayerComponent
 *
 * 2016年2月21日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
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
import com.game.objec.GamePlayer;
import com.game.pb.CommonMsgProto.CommonMsgPB.Builder;
import com.game.type.ModuleType;

/**
 * @author Jacken
 *
 */
public class PlayerComponent implements IPlayerComponent
{
    private Logger LOGGER = LoggerFactory.getLogger(PlayerComponent.class);

    private Map<Integer, GamePlayer> players;

    private Object locker = new Object();

    private com.bdsk.event.IEventListener gameNoticeListener;

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
        for (GamePlayer player : players.values())
        {
          //   player.disconnect(true, true, StateCode.SERVER_CLOSE);
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
            IMessageModule messageModule = (IMessageModule) temp.getModule(ModuleType.MESSAGE);
            messageModule.sendLoginOutMessage(StateCode.REPEAT_LOGIN);
            // temp.disconnect(true);
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

}
