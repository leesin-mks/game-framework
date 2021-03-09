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

package com.game.server.command;

import com.game.command.AbstractServerCmd;
import com.game.command.CSProtocol;
import com.game.command.ICode;
import com.game.component.ComponentManager;
import com.game.component.inf.IServerComponent;
import com.game.pb.CenterMsgProto.CSForwardMsg;
import com.game.server.ServerClient;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @date 2020年04月16日 19:26
 * @author leesin
 */
@ICode(code = CSProtocol.FORWARD_MESSAGE, desc = "转发消息")
public class ForwardMsgCmd extends AbstractServerCmd
{
    private static IServerComponent sc;

    @Override
    public void exec(ServerClient client, byte[] packet)
    {
        try
        {
            CSForwardMsg msg = CSForwardMsg.parseFrom(packet);
            getServerComponent().forwardMsg(msg);
        }
        catch (InvalidProtocolBufferException e)
        {
            LOGGER.error("Parse packet error: ", e);
        }
    }

    private static IServerComponent getServerComponent()
    {
        if (sc == null)
        {
            sc = (IServerComponent) ComponentManager.getInstance().getComponent(IServerComponent.NAME);
        }
        return sc;
    }
}
