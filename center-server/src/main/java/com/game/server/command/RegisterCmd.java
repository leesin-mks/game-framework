/*
 * LoginCmd
 *
 * 2017年6月24日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
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
        try
        {
            RegisterMsg msg = RegisterMsg.parseFrom(packet);
            ServerClient server = new ServerClient();
            server.setServerID(msg.getServerID());
            server.setClientConnection(conn);
            conn.setHolder(server);
            LOGGER.info("服务器登录成功, id: {}", msg.getServerID());
            IServerComponent sc = (IServerComponent) ComponentManager.getInstance().getComponent(
                    IServerComponent.NAME);
            sc.addServerClient(server);
        }
        catch (InvalidProtocolBufferException e)
        {
            LOGGER.error("Parse packet error: ", e);
        }
    }

}
