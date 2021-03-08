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
import com.game.net.IClientConnection;
import com.game.pb.CenterMsgProto.RegisterMsg;
import com.game.server.ServerClient;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author jacken
 *
 */
@ICode(code = CSProtocol.REGISTER, desc = "服务器注册")
public class RegisterCmd extends AbstractServerCmd
{

    /*
     * (non-Javadoc)
     * 
     * @see com.niuniu.command.AbstractServerCmd#exec(com.niuniu.server.ServerClient, byte[])
     */
    @Override
    public void exec(ServerClient client, byte[] packet)
    {
        // TODO Auto-generated method stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.niuniu.command.AbstractServerCmd#executeConnect(com.bdsk.net.IClientConnection, byte[])
     */
    @Override
    public void executeConnect(IClientConnection conn, byte[] packet)
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

        ServerClient server = new ServerClient();
        server.setServerID(msg.getServerID());
        server.setClientConnection(conn);
        conn.setHolder(server);
        IServerComponent sc = (IServerComponent) ComponentManager.getInstance().getComponent(
                IServerComponent.NAME);
        sc.addServerClient(server);
        server.sendRegisterSuccess();
        LOGGER.info("服务器注册成功, id: {}", msg.getServerID());
    }

}
