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

package com.game.component.inf;

import java.util.List;

import com.game.component.IComponent;
import com.game.object.GamePlayer;
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
    String NAME = "PlayerComponent";

    /**
     * 根据玩家id来查找在线玩家
     * 
     * @param userId
     *            玩家的用户ID
     * @return
     */
    GamePlayer getPlayerByUserID(int userId);

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
    boolean add(int userId, GamePlayer player);

    /**
     * @return
     */
    List<GamePlayer> getAllPlayers();

    void sendToAll(Builder message);

    void sendToAll(byte[] message);

    void clearOfflinePlayer();

    void ping();

}
