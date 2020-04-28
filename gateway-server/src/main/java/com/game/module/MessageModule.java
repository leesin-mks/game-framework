/*
 * MessageModule
 *
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.module;

import com.game.command.CSProtocol;
import com.game.component.ComponentManager;
import com.game.component.inf.ICSComponent;
import com.game.module.inf.IMessageModule;
import com.game.net.IClientConnection;
import com.game.object.ProxyPlayer;
import com.game.pb.CenterMsgProto.MsgToUSer;
import com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg;
import com.game.pb.CommonMsgProto.CommonMsgPB;
import com.game.pb.PlayerMsgProto.LoginMsgCS;
import com.game.pb.PlayerMsgProto.LoginMsgSC;
import com.game.pb.command.ProtocolInProto.ProtocolIn;
import com.game.pb.command.ProtocolOutProto.ProtocolOut;
import com.game.type.ModuleType;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

/**
 * @author jacken
 *
 */
public class MessageModule extends PlayerModule implements IMessageModule
{
    public MessageModule(ProxyPlayer player)
    {
        super(player, ModuleType.MESSAGE);
    }

    private static ICSComponent csComponent;

    @Override
    public boolean init()
    {
        return this.getConnection() == null ? false : true;
    }

    @Override
    public boolean loadFromDB()
    {
        return true;
    }

    @Override
    public boolean saveIntoDB()
    {
        return true;
    }

    @Override
    public boolean saveIntoCache()
    {
        return true;
    }

    @Override
    public void afterDataLoaded()
    {

    }

    @Override
    public void sendTCP(byte[] message)
    {
        if (getConnection() != null)
        {
            ByteBuf buf = Unpooled.wrappedBuffer(message);
            getConnection().send(new BinaryWebSocketFrame(buf));
        }
    }

    public IClientConnection getConnection()
    {
        return player.getClientConnection();
    }

    @Override
    public void sendLoginOutMessage(int status)
    {
        LoginMsgSC.Builder builder = LoginMsgSC.newBuilder();
        builder.setStatus(status);
        builder.setMessage("");

        CommonMsgPB.Builder msg = CommonMsgPB.newBuilder();
        msg.setCode(ProtocolOut.USER_LOGIN_OUT_VALUE);
        msg.setBody(builder.build().toByteString());
        sendTCP(msg.build().toByteArray());
    }

    @Override
    public void sendLoginGameServer()
    {
        CommonMsgPB.Builder msg = CommonMsgPB.newBuilder();
        msg.setCode(ProtocolIn.USER_LOGIN_VALUE);

        LoginMsgCS.Builder builder = LoginMsgCS.newBuilder();
        builder.setUserID(player.getPlayerInfo().getId());
        builder.setChannel(player.getPlayerInfo().getPlatformType());
        builder.setPassword("");
        msg.setBody(builder.build().toByteString());

        MsgToUSer.Builder msgToUserBuilder = MsgToUSer.newBuilder();
        msgToUserBuilder.setUserID(player.getPlayerInfo().getId());
        msgToUserBuilder.setBody(msg.build().toByteString());

        getCsComponent().msgToUser(player.getGameServerID(), msgToUserBuilder.build().toByteString());
    }

    public void sendOnDisconnect()
    {
        PlayerOnDisconnectMsg.Builder builder = PlayerOnDisconnectMsg.newBuilder();
        builder.setUserID(player.getPlayerInfo().getId());

        getCsComponent().forwardMessage(player.getGameServerID(), builder.build().toByteString(),
                CSProtocol.PLAYER_ON_DISCONNECT);
    }

    private ICSComponent getCsComponent()
    {
        if (csComponent == null)
        {
            csComponent = (ICSComponent) ComponentManager.getInstance().getComponent(ICSComponent.NAME);
        }
        return csComponent;
    }

}
