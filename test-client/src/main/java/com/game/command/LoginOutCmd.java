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

package com.game.command;

import com.game.component.ComponentManager;
import com.game.component.inf.IPlayerComponent;
import com.game.object.PressPlayer;
import com.game.pb.PlayerMsgProto.LoginMsgSC;
import com.game.pb.command.ProtocolOutProto.ProtocolOut;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

@ICode(code = ProtocolOut.USER_LOGIN_OUT_VALUE, desc = "退出登录")
public class LoginOutCmd extends AbstractPressCmd
{

    @Override
    public void execute(PressPlayer player, ByteString packet)
    {
        try
        {
            LoginMsgSC msg = LoginMsgSC.parseFrom(packet);
            if (msg.getStatus() == 1)
            {
                IPlayerComponent pc = (IPlayerComponent) ComponentManager.getInstance().getComponent(
                        IPlayerComponent.NAME);
                pc.addPlayer(player);
            }
            System.out.println("Login out: " + msg);
        }
        catch (InvalidProtocolBufferException e)
        {
            e.printStackTrace();
        }

    }

}
