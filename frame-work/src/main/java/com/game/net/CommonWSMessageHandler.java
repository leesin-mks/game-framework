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

package com.game.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.pb.CommonMsgProto.CommonMsgPB;
import com.game.command.AbstractCommandComponent;
import com.game.command.ICommand;
import com.game.component.ComponentManager;
import com.game.pb.command.ProtocolInProto.ProtocolIn;

/**
 * @author leesin
 *
 */
public class CommonWSMessageHandler implements IMessageHandler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonWSMessageHandler.class);

    /*
     * (non-Javadoc)
     * 
     * @see little.seven.net.IMessageHandler#process(little.seven.net.IClientConnection, java.lang.Object)
     */
    @Override
    public void process(IClientConnection conn, Object packet)
    {
        CommonMsgPB pb = ((CommonMsgPB) packet);
        short code = (short) pb.getCode();
        // 从组件管理器中间调用
        AbstractCommandComponent<?> cm = (AbstractCommandComponent<?>) ComponentManager.getInstance().getComponent(
                AbstractCommandComponent.NAME);
        if (cm == null)
        {
            LOGGER.warn("Command module not found");
            return;
        }

        ICommand cmd = cm.getCommand(code);
        if (cmd == null)
        {
            cmd = cm.getCommand((short) ProtocolIn.FORWARD_MESSAGE_VALUE);
        }

        try
        {
            cmd.execute(conn, pb.getBody(), code);
        }
        catch (Exception e)
        {
            LOGGER.error("", e);
        }
    }

}
