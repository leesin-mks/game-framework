/*
 * Copyright 2016-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.game.command.login;

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
import com.game.object.GamePlayer;
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
 * @author leesin
 */
@ICode(code = ProtocolIn.USER_LOGIN_VALUE, desc = "登陆")
public class UserLoginCmd extends AbstractUserCmd
{

    private static IPlayerComponent playerComponent;

    private static IRedisComponent redisComponent;

    /**
     * server excute全局调用使用(还没有玩家的)
     *
     * @param
     */
    public void executeConnect(CSServerConn conn, ByteString packet)
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

            String serverID = String.valueOf(GameServer.getInstance().getBean().getId());
            String cacheServerID = rc.setNX(RedisConst.USER_GS_SERVER_KEY + userID, serverID);
            if (!serverID.equals(cacheServerID))
            {
                status = StateCode.VERIFICATION_FAIL;
                message = "Login.Fail";
                sendLoginResult(conn, userID, status, message);
                return;
            }
            GamePlayer gamePlayer = pc.getPlayerByUserID(userID);
            if (gamePlayer == null)
            {
                PlayerInfo playerInfo = new PlayerInfo();
                playerInfo.setId(userID);
                playerInfo.setPlatformType(msg.getChannel());
                gamePlayer = new GamePlayer(playerInfo, conn);
                if (!gamePlayer.login())
                {
                    status = StateCode.LOAD_DATA_FAIL;
                    message = "Login.Fail";
                    sendLoginResult(conn, userID, status, message);
                }
            }
            else
            {
                gamePlayer.reconnection(conn);
            }
        }
        catch (Exception e)
        {
            LOGGER.error("Login catch error: {}, message: {}", userID, StackMessagePrint.printErrorTrace(e));
        }
    }

    @Override
    public void execute(GamePlayer player, ByteString packet)
    {
        new UnsupportedOperationException();
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
