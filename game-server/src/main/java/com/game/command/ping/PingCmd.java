package com.game.command.ping;

import com.game.command.AbstractUserCmd;
import com.game.command.ICode;
import com.game.objec.GamePlayer;
import com.game.pb.command.ProtocolInProto.ProtocolIn;
import com.google.protobuf.ByteString;

/**
 * @date 2020年04月09日 18:43
 * @auth zm
 */
@ICode(code = ProtocolIn.PING_VALUE, desc = "ping")
public class PingCmd extends AbstractUserCmd
{

    @Override
    public void execute(GamePlayer player, ByteString packet)
    {
        player.setPing(0);
        if (player.isOnline())
        {
            player.setOnline(true);
        }
        LOGGER.info("Player ping: {}", player.getPlayerInfo().getId());
    }

}
