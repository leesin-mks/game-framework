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

package com.game.cs.command;

import com.game.command.AbstractServerCmd;
import com.game.command.CSProtocol;
import com.game.command.ICode;
import com.game.component.ComponentManager;
import com.game.component.inf.IPlayerComponent;
import com.game.cs.CSServerConn;
import com.game.object.ProxyPlayer;
import com.game.pb.CenterMsgProto.PlayerDisconnectMsg;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @date 2020年04月27日 14:46
 * @auth zm
 */
@ICode(code = CSProtocol.PLAYER_DISCONNECT, desc = "玩家断线")
public class PlayerDisconnectCmd extends AbstractServerCmd
{

    private static IPlayerComponent pc;

    @Override
    public void execute(CSServerConn client, byte[] packet)
    {
        PlayerDisconnectMsg msg = null;
        try
        {
            msg = PlayerDisconnectMsg.parseFrom(packet);
        }
        catch (InvalidProtocolBufferException e)
        {
            LOGGER.error("Parse packet error: ", e);
            return;
        }
        ProxyPlayer player = getPlayerComponent().getPlayerByUserID(msg.getUserID());
        if (player == null)
        {
            LOGGER.warn("Player disconnect but not exist: {}", msg.getUserID());
            return;
        }
        player.disconnect(msg.getShutDown(), msg.getSendLoinOut(), msg.getType());
    }

    private static IPlayerComponent getPlayerComponent()
    {
        if (pc == null)
        {
            pc = (IPlayerComponent) ComponentManager.getInstance().getComponent(
                    IPlayerComponent.NAME);
        }
        return pc;
    }

}
