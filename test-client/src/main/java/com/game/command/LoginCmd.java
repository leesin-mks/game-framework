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

import com.game.action.GetPlayerInfoAction;
import com.game.object.PressPlayer;
import com.game.pb.PlayerMsgProto.LoginMsgSC;
import com.game.pb.command.ProtocolOutProto.ProtocolOut;
import com.game.test.PressManager;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

@ICode(code = ProtocolOut.USER_LOGIN_VALUE, desc = "登录返回")
public class LoginCmd extends AbstractPressCmd
{

    @Override
    public void execute(PressPlayer player, ByteString packet)
    {
        try
        {
            LoginMsgSC msg = LoginMsgSC.parseFrom(packet);
            System.out.println("Login result: " + msg);
            PressManager.addAction(new GetPlayerInfoAction(player));
        }
        catch (InvalidProtocolBufferException e)
        {
            e.printStackTrace();
        }

    }

}
