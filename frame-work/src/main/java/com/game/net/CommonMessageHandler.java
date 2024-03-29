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

import com.game.command.AbstractCommandComponent;
import com.game.command.ICommand;
import com.game.component.ComponentManager;

/**
 * @author leesin
 *
 */
public class CommonMessageHandler implements IMessageHandler
{
    private final String cmdCMPTName;
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonMessageHandler.class);

    /**
     * 
     */
    public CommonMessageHandler(String cmdCMPTName)
    {
        this.cmdCMPTName = cmdCMPTName;
    }

    /**
     * (non-Javadoc)
     * 
     * @see IMessageHandler#process(IClientConnection, java.lang.Object)
     */
    @Override
    public void process(IClientConnection conn, Object packet)
    {
        CommonMessage message = ((CommonMessage) packet);
        short code = message.getCode();
        // 从组件管理器中间调用
        AbstractCommandComponent<?> cm = (AbstractCommandComponent<?>) ComponentManager.getInstance().getComponent(
                cmdCMPTName);
        if (cm == null)
        {
            LOGGER.error("CommandModule not found");
            return;
        }

        ICommand cmd = cm.getCommand(code);
        if (cmd == null)
        {
            LOGGER.error(" Can not found code = " + code + ",drop this packet.");
            return;
        }

        try
        {
            cmd.execute(conn, message.getBody());
        }
        catch (Exception e)
        {
            LOGGER.error("", e);
        }
    }

}
