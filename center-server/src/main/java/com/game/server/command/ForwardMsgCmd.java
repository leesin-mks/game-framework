package com.game.server.command;

import com.game.command.AbstractServerCmd;
import com.game.command.CSProtocol;
import com.game.command.ICode;
import com.game.component.ComponentManager;
import com.game.component.inf.IServerComponent;
import com.game.pb.CenterMsgProto.ForwardMsg;
import com.game.server.ServerClient;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @date 2020年04月16日 19:26
 * @auth zm
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
            ForwardMsg msg = ForwardMsg.parseFrom(packet);
            getServerComponent().forwardMsg(msg.getToServerID(), packet);
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
