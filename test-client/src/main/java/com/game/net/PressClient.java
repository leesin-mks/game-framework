package com.game.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.client.PressNettyWSServerConnector;
import com.game.component.ComponentManager;
import com.game.component.inf.IPlayerComponent;
import com.game.object.LoginResultBean;
import com.game.object.PressPlayer;
import com.game.pb.CommonMsgProto.CommonMsgPB;
import com.game.pb.PlayerMsgProto.LoginMsgCS;
import com.game.pb.command.ProtocolInProto.ProtocolIn;

public class PressClient extends PressNettyWSServerConnector implements Runnable
{
    private LoginResultBean loginResult;

    private static final Logger LOGGER = LoggerFactory.getLogger(PressClient.class);

    public PressClient(String ip, int port, LoginResultBean loginResult, IServerPacketHandler packetHandler)
    {
        super(ip, port, packetHandler);
        PressPlayer player = new PressPlayer(loginResult.getUserID(), this);
        setPlayer(player);
        IPlayerComponent pc = (IPlayerComponent) ComponentManager.getInstance().getComponent(IPlayerComponent.NAME);
        pc.addPlayer(player);
        this.loginResult = loginResult;
    }

    @Override
    public void run()
    {
        if (!connect())
        {
            LOGGER.error("Socket connect fail...");
        }
        else
        {

            // GameServer登录
            CommonMsgPB.Builder builder = CommonMsgPB.newBuilder();
            builder.setCode(ProtocolIn.USER_LOGIN_VALUE);

            LoginMsgCS.Builder loginBuilder = LoginMsgCS.newBuilder();
            loginBuilder.setChannel(1);
            loginBuilder.setUserID(loginResult.getUserID());
            loginBuilder.setPassword(loginResult.getKey());
            builder.setBody(loginBuilder.build().toByteString());

            LOGGER.error("userID: " + 1 + " send login to GameServer");
            sendTCP(builder);
        }
    }

}
