/*
 * IPlayerComponent
 *
 * 2016年2月21日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.component.inf;

import java.util.List;

import com.game.component.IComponent;
import com.game.object.ProxyPlayer;
import com.game.pb.CommonMsgProto.CommonMsgPB.Builder;

/**
 * @author leesin
 *
 */
public interface IPlayerComponent extends IComponent
{
    /**
     * 组件名
     */
    public static final String NAME = "PlayerComponent";

    /**
     * 根据玩家id来查找在线玩家
     * 
     * @param userId
     *            玩家的用户ID
     * @return
     */
    ProxyPlayer getPlayerByUserID(int userId);

    /**
     * 从服务器中移除一位玩家
     * 
     * @param userID
     */
    void remove(int userID);

    /**
     * 添加在线用户
     * 
     * @param userId
     * @param player
     * @return
     */
    boolean add(int userId, ProxyPlayer player);

    /**
     * @return
     */
    List<ProxyPlayer> getAllPlayers();

    void sendToAll(Builder message);

    void sendToAll(byte[] message);

}
