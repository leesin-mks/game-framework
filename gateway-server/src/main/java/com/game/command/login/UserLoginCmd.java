package com.game.command.login;

import com.game.GateWayServer;
import com.game.bean.StateCode;
import com.game.command.AbstractUserCmd;
import com.game.command.ICode;
import com.game.component.ComponentManager;
import com.game.component.IRedisComponent;
import com.game.component.LanguageComponent;
import com.game.component.inf.IPlayerComponent;
import com.game.net.IClientConnection;
import com.game.object.ProxyPlayer;
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
@ICode(code = ProtocolIn.USER_LOGIN_VALUE, desc = "登陆")
public class UserLoginCmd extends AbstractUserCmd
{

    private static IPlayerComponent playerComponent;

    private static IRedisComponent redisComponent;

    @Override
    public void execute(ProxyPlayer player, ByteString packet)
    {

    }

    @Override
    public void executeConnect(IClientConnection client, ByteString packet)
    {
        LoginMsgCS msg;
        try
        {
            msg = LoginMsgCS.parseFrom(packet);
        }
        catch (InvalidProtocolBufferException e)
        {
            LOGGER.error(StackMessagePrint.printErrorTrace(e));
            return;
        }

        LOGGER.info("UserLogin info: {}", msg);

        String password = msg.getPassword();
        int userID = msg.getUserID();
        String message = "Login.Null";
        int status = 0;
        try
        {
            IRedisComponent rc = getRedisComponent();
            IPlayerComponent pc = getPlayerComponent();
            String key = rc.get(RedisConst.USER_SESSION_KEY + userID);

            String serverID = String.valueOf(GateWayServer.getInstance().getBean().getId());
            String cacheServerID = rc.setNX(RedisConst.USER_SERVER_KEY + userID, serverID);
            if (key != null && key.equals(password) && !serverID.equals(cacheServerID))
            {
                ProxyPlayer proxyPlayer = pc.getPlayerByUserID(userID);
                if (proxyPlayer == null)
                {
                    proxyPlayer = new ProxyPlayer(userID, client);
                    if (proxyPlayer.login())
                    {
                        return;
                    }
                    else
                    {
                        status = StateCode.LOAD_DATA_FAIL;
                        message = "Login.Fail";
                    }
                }
                else
                {
                    proxyPlayer.reconnection(client);
                    return;
                }
            }

            CommonMsgPB.Builder commonMessage = CommonMsgPB.newBuilder();
            commonMessage.setCode(ProtocolOut.USER_LOGIN_OUT_VALUE);

            LoginMsgSC.Builder builder = LoginMsgSC.newBuilder();
            builder.setStatus(status);
            builder.setMessage(message);

            commonMessage.setBody(builder.build().toByteString());
            client.send(commonMessage.build().toByteArray());
            client.closeConnection(true);
            LOGGER.info("UserLogin Fail: {} , message: {}", userID, LanguageComponent.getResource(message));
        }
        catch (Exception e)
        {
            LOGGER.error("Login catch error: {}, message: {}", userID, StackMessagePrint.printErrorTrace(e));
        }
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
