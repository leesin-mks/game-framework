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
