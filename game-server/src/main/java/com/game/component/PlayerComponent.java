/*
 * PlayerComponent
 *
 * 2016年2月21日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
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
 * @author Jacken
 *
 */
public class PlayerComponent implements IPlayerComponent
{
    private Logger LOGGER = LoggerFactory.getLogger(PlayerComponent.class);

    private Map<Integer, GamePlayer> players;

    private Object locker = new Object();

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
        timerComponent.addJob("PingJob", PlayerJob.class, "0/10 * * * * ?");

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
        for (GamePlayer player : players.values())
        {
            player.checkOffline();
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
