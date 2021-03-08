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

package com.game.command.forward;

import com.game.command.AbstractUserCmd;
import com.game.command.ICode;
import com.game.component.ComponentManager;
import com.game.component.inf.ICSComponent;
import com.game.object.ProxyPlayer;
import com.game.pb.CenterMsgProto.MsgToUSer;
import com.game.pb.CommonMsgProto.CommonMsgPB;
import com.game.pb.command.ProtocolInProto.ProtocolIn;
import com.google.protobuf.ByteString;

/**
 * @date 2020年04月09日 18:43
 * @auth zm
 */
@ICode(code = ProtocolIn.FORWARD_MESSAGE_VALUE, desc = "转发消息")
public class ForwardMsgCmd extends AbstractUserCmd
{

    @Override
    public void execute(ProxyPlayer player, ByteString packet, short code)
    {
        CommonMsgPB.Builder msg = CommonMsgPB.newBuilder();
        msg.setCode(code);
        msg.setBody(packet);

        MsgToUSer.Builder msgToUserBuilder = MsgToUSer.newBuilder();
        msgToUserBuilder.setUserID(player.getPlayerInfo().getId());
        msgToUserBuilder.setBody(msg.build().toByteString());

        ICSComponent csComponent = (ICSComponent) ComponentManager.getInstance().getComponent(ICSComponent.NAME);
        csComponent.msgToUser(player.getGameServerID(), msgToUserBuilder.build().toByteString());
    }
}
