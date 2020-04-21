package com.game.command;

import com.game.object.PressPlayer;
import com.game.pb.command.ProtocolOutProto.ProtocolOut;
import com.google.protobuf.ByteString;

@ICode(code = ProtocolOut.PING_VALUE, desc = "ping")
public class PingCmd extends AbstractPressCmd
{

    @Override
    public void execute(PressPlayer player, ByteString packet)
    {
        System.out.println("Player ping: " + player.getUserID());
    }

}
