package com.game.command.ping;

import com.game.GameServer;
import com.game.bean.StateCode;
import com.game.command.AbstractUserCmd;
import com.game.command.ICode;
import com.game.component.ComponentManager;
import com.game.component.IRedisComponent;
import com.game.component.LanguageComponent;
import com.game.component.inf.IPlayerComponent;
import com.game.cs.CSServerConn;
import com.game.entity.bean.PlayerInfo;
import com.game.objec.GamePlayer;
import com.game.pb.CommonMsgProto.CommonMsgPB;
import com.game.pb.PlayerMsgProto.LoginMsgCS;
import com.game.pb.PlayerMsgProto.LoginMsgSC;
import com.game.pb.command.ProtocolInProto.ProtocolIn;
import com.game.pb.command.ProtocolOutProto.ProtocolOut;
import com.game.type.RedisConst;
import com.game.util.StackMessagePrint;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @date 2020年04月09日 18:43
 * @auth zm
 */
@ICode(code = ProtocolIn.PING_VALUE, desc = "ping")
public class PingCmd extends AbstractUserCmd
{

    private static IPlayerComponent playerComponent;

    private static IRedisComponent redisComponent;

    @Override
    public void execute(GamePlayer player, ByteString packet)
    {
        player.setPing(0);
        LOGGER.info("Player ping: {}", player.getPlayerInfo().getId());
    }

    private void sendLoginResult(CSServerConn client, int userID, int status, String message)
    {
        CommonMsgPB.Builder commonMessage = CommonMsgPB.newBuilder();
        commonMessage.setCode(ProtocolOut.USER_LOGIN_OUT_VALUE);

        LoginMsgSC.Builder builder = LoginMsgSC.newBuilder();
        builder.setStatus(status);
        builder.setMessage(message);

        commonMessage.setBody(builder.build().toByteString());
        client.send(commonMessage.build().toByteArray());
        LOGGER.info("UserLogin Fail: {} , message: {}", userID, LanguageComponent.getResource(message));
    }

    private IPlayerComponent getPlayerComponent()
    {
        if (playerComponent == null)
        {
            playerComponent = (IPlayerComponent) ComponentManager.getInstance().getComponent(IPlayerComponent.NAME);
        }
        return playerComponent;
    }

    private IRedisComponent getRedisComponent()
    {
        if (redisComponent == null)
        {
            redisComponent = (IRedisComponent) ComponentManager.getInstance().getComponent(IRedisComponent.NAME);
        }
        return redisComponent;
    }
}
