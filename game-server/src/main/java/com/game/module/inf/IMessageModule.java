/*
 * IMessageModule
 *
 * 2016年2月22日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.module.inf;

import com.game.pb.PlayerMsgProto.PlayerInfoPB;
import com.google.protobuf.ByteString;

/**
 * @author leesin
 *
 */
public interface IMessageModule
{
    void sendTCP(byte[] message);

    void sendTCP(ByteString message);

    void sendLoginOutMessage(boolean shutdown, boolean sendLLoginOut, int type);

    void sendLoginSuccess();

    PlayerInfoPB.Builder getPlayerInfoBuilder();
}
