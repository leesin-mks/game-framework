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
import com.game.cs.CSServerConn;
import com.game.pb.CenterMsgProto.RegisterMsg;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @date 2020年04月10日 11:14
 * @auth zm
 */
@ICode(code = CSProtocol.REGISTER, desc = "服务器注册")
public class RegisterCmd extends AbstractServerCmd
{

    public void execute(CSServerConn client, byte[] packet)
    {
        RegisterMsg msg = null;
        try
        {
            msg = RegisterMsg.parseFrom(packet);
        }
        catch (InvalidProtocolBufferException e)
        {
            LOGGER.error("Parse packet error: ", e);
            return;
        }

        LOGGER.info("Register success: {}", msg.getServerID());
    }
}
