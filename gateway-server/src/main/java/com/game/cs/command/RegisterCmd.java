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
