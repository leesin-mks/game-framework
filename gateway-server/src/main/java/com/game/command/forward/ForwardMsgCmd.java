package com.game.command.forward;

import com.game.command.AbstractUserCmd;
import com.game.command.ICode;
import com.game.component.ComponentManager;
import com.game.component.inf.ICSComponent;
import com.game.object.ProxyPlayer;
import com.game.pb.CenterMsgProto.MsgToUSer;
import com.game.pb.CommonMsgProto.CommonMsgPB;
import com.game.pb.command.ProtocolInProto.ProtocolIn;
import com.google.protobuf.ByteString;

/**
 * @date 2020年04月09日 18:43
 * @auth zm
 */
@ICode(code = ProtocolIn.FORWARD_MESSAGE_VALUE, desc = "转发消息")
public class ForwardMsgCmd extends AbstractUserCmd
{

    @Override
    public void execute(ProxyPlayer player, ByteString packet, short code)
    {
        CommonMsgPB.Builder msg = CommonMsgPB.newBuilder();
        msg.setCode(code);
        msg.setBody(packet);

        MsgToUSer.Builder msgToUserBuilder = MsgToUSer.newBuilder();
        msgToUserBuilder.setUserID(player.getPlayerInfo().getId());
        msgToUserBuilder.setBody(msg.build().toByteString());

        ICSComponent csComponent = (ICSComponent) ComponentManager.getInstance().getComponent(ICSComponent.NAME);
        csComponent.msgToUser(player.getGameServerID(), msgToUserBuilder.build().toByteString());
    }
}
