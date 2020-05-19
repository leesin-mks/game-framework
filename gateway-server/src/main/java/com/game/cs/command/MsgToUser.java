package com.game.cs.command;

import com.game.command.AbstractCommandComponent;
import com.game.command.AbstractServerCmd;
import com.game.command.CSProtocol;
import com.game.command.ICode;
import com.game.component.ComponentManager;
import com.game.component.inf.IPlayerComponent;
import com.game.cs.CSServerConn;
import com.game.object.ProxyPlayer;
import com.game.pb.CenterMsgProto.MsgToUSer;

/**
 * @date 2020年04月16日 19:26
 * @auth zm
 */
@ICode(code = CSProtocol.MESSAGE_TO_USER, desc = "转发玩家消息")
public class MsgToUser extends AbstractServerCmd
{
    private static AbstractCommandComponent<?> acc;

    private static IPlayerComponent pc;

    @Override
    public void execute(CSServerConn client, byte[] packet)
    {
        try
        {
            MsgToUSer msg = MsgToUSer.parseFrom(packet);
            ProxyPlayer player = getPlayerComponent().getPlayerByUserID(msg.getUserID());
            if (player == null)
            {
                LOGGER.warn("Forward msg but can not find player: {}", msg);
                return;
            }
            player.sendPacket(msg.getBody());
        }
        catch (Exception e)
        {
            LOGGER.error("Parse packet error: ", e);
        }
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