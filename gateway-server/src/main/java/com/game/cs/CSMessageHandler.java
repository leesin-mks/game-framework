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

package com.game.cs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.command.AbstractServerCmd;
import com.game.command.FSCmdTask;
import com.game.component.CSCommandComponent;
import com.game.component.ComponentManager;
import com.game.net.CommonMessage;
import com.game.net.IServerConnector;
import com.game.net.IServerPacketHandler;

/**
 * @author leesin
 *
 */
public class CSMessageHandler implements IServerPacketHandler
{
    private static Logger LOGGER = LoggerFactory.getLogger(CSMessageHandler.class);

    /**
     * (non-Javadoc)
     * 
     * @see com.game.net.IServerPacketHandler#process(com.game.net.IServerConnector, java.lang.Object)
     */
    @Override
    public void process(IServerConnector client, Object packet)
    {
        CommonMessage message = ((CommonMessage) packet);
        short code = message.getCode();
        // 从组件管理器中间调用
        CSCommandComponent cm = (CSCommandComponent) ComponentManager.getInstance().getComponent(
                CSCommandComponent.NAME);
        if (cm == null)
        {
            LOGGER.error("CommandModule not found");
            return;
        }

        AbstractServerCmd cmd = (AbstractServerCmd) cm.getCommand(code);
        if (cmd == null)
        {
            LOGGER.error(" Can not found code = " + code + ",drop this packet.");
            return;
        }

        try
        {
            ((CSServerConn) client).addCommandTask(new FSCmdTask(cmd, message.getBody(), client));
            // cmd.execute(client, message.getBody());
        }
        catch (Exception e)
        {
            LOGGER.error("", e);
        }

    }

}
