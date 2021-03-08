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

import com.game.command.AbstractCommandComponent;
import com.game.command.AbstractServerCmd;
import com.game.command.AbstractUserCmd;
import com.game.command.CSProtocol;
import com.game.command.ICode;
import com.game.command.ServerCmdTask;
import com.game.command.UserCmdTask;
import com.game.component.ComponentManager;
import com.game.component.inf.IPlayerComponent;
import com.game.cs.CSServerConn;
import com.game.objec.GamePlayer;
import com.game.pb.CenterMsgProto.MsgToUSer;
import com.game.pb.CommonMsgProto.CommonMsgPB;
import com.game.pb.command.ProtocolInProto.ProtocolIn;

/**
 * @date 2020年04月16日 19:26
 * @auth zm
 */
@ICode(code = CSProtocol.MESSAGE_TO_USER, desc = "转发消息")
public class MsgToUserCmd extends AbstractServerCmd
{
    private static AbstractCommandComponent<?> acc;

    private static IPlayerComponent pc;

    @Override
    public void execute(CSServerConn client, byte[] packet)
    {
        try
        {
            MsgToUSer msg = MsgToUSer.parseFrom(packet);
            CommonMsgPB pb = CommonMsgPB.parseFrom(msg.getBody());
            short code = (short) pb.getCode();
            // 从组件管理器中间调用
            AbstractCommandComponent<?> cm = getAbstractCommandComponent();
            if (acc == null)
            {
                LOGGER.warn("Command module not found");
                return;
            }

            AbstractUserCmd cmd = (AbstractUserCmd) cm.getCommand(code);
            if (cmd == null)
            {
                LOGGER.warn("Can not find command: {}", code);
                return;
            }
            if (pb.getCode() == ProtocolIn.USER_LOGIN_VALUE)
            {
                ServerCmdTask task = new ServerCmdTask(cmd, pb.getBody(), client);
                ComponentManager.getInstance().getUserCmdThreadPool().submit(task);
            }
            else
            {
                GamePlayer player = getPlayerComponent().getPlayerByUserID(msg.getUserID());
                if (player == null)
                {
                    LOGGER.warn("Can not find player: {}, code: {}", msg.getUserID(), code);
                    return;
                }
                UserCmdTask task = new UserCmdTask(cmd, pb.getBody(), player);
                player.addCommandTask(task);
            }
        }
        catch (Exception e)
        {
            LOGGER.error("Parse packet error: ", e);
        }
    }

    private static AbstractCommandComponent<?> getAbstractCommandComponent()
    {
        if (acc == null)
        {
            acc = (AbstractCommandComponent<?>) ComponentManager.getInstance().getComponent(
                    AbstractCommandComponent.NAME);
        }
        return acc;
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
