/*
 * MessageModule
 *
 * 2016骞�2鏈�22鏃�
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.module;

import com.game.component.ComponentManager;
import com.game.component.inf.ICSComponent;
import com.game.entity.bean.PlayerInfo;
import com.game.module.inf.IMessageModule;
import com.game.objec.GamePlayer;
import com.game.pb.CommonMsgProto.CommonMsgPB;
import com.game.pb.PlayerMsgProto.LoginMsgSC;
import com.game.pb.PlayerMsgProto.PlayerInfoPB;
import com.game.pb.command.ProtocolOutProto.ProtocolOut;
import com.game.type.ModuleType;
import com.google.protobuf.ByteString;

/**
 * @author jacken
 *
 */
public class MessageModule extends PlayerModule implements IMessageModule
{
    public MessageModule(GamePlayer player)
    {
        super(player, ModuleType.MESSAGE);
    }

    private static ICSComponent csComponent;

    @Override
    public boolean init()
    {
        return true;
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
        ICSComponent csComponent = (ICSComponent) ComponentManager.getInstance().getComponent(ICSComponent.NAME);
        csComponent.forwardMessage(player.getPlayerInfo().getId(), player.getGateServerID(), message);
    }

    @Override
    public void sendTCP(ByteString message)
    {
        ICSComponent csComponent = (ICSComponent) ComponentManager.getInstance().getComponent(ICSComponent.NAME);
        csComponent.forwardMessage(player.getPlayerInfo().getId(), player.getGateServerID(), message);
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
        sendTCP(msg.build().toByteString());
    }

    private ICSComponent getCsComponent()
    {
        if (csComponent == null)
        {
            csComponent = (ICSComponent) ComponentManager.getInstance().getComponent(ICSComponent.NAME);
        }
        return csComponent;
    }

    @Override
    public void sendLoginSuccess()
    {
        LoginMsgSC.Builder builder = LoginMsgSC.newBuilder();
        builder.setStatus(1);
        builder.setPlayerInfos(getPlayerInfoBuilder());
        builder.setMessage("SUCCESS");

        CommonMsgPB.Builder msg = CommonMsgPB.newBuilder();
        msg.setCode(ProtocolOut.USER_LOGIN_OUT_VALUE);
        msg.setBody(builder.build().toByteString());
        sendTCP(msg.build().toByteString());
    }

    @Override
    public PlayerInfoPB.Builder getPlayerInfoBuilder()
    {
        PlayerInfo playerInfo = this.player.getPlayerInfo();

        PlayerInfoPB.Builder playerBuilder = PlayerInfoPB.newBuilder();
        playerBuilder.setUserID(playerInfo.getId());
        return playerBuilder;
    }

}
