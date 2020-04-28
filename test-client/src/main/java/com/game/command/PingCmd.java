package com.game.command;

import com.game.object.PressPlayer;
import com.game.pb.CommonMsgProto.CommonMsgPB;
import com.game.pb.command.ProtocolInProto.ProtocolIn;
import com.game.pb.command.ProtocolOutProto.ProtocolOut;
import com.google.protobuf.ByteString;

@ICode(code = ProtocolOut.PING_VALUE, desc = "ping")
public class PingCmd extends AbstractPressCmd
{

    @Override
    public void execute(PressPlayer player, ByteString packet)
    {
        CommonMsgPB.Builder builder = CommonMsgPB.newBuilder();
        builder.setCode(ProtocolIn.PING_VALUE);
        builder.setBody(ByteString.EMPTY);
        player.getClient().sendTCP(builder);
        System.out.println("Player ping: " + player.getUserID());
    }

}
