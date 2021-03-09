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

package com.game.command;

import com.game.cs.CSServerConn;
import com.game.net.IServerConnector;
import com.game.util.StackMessagePrint;

/**
 * @author leesin
 *
 */
public class FSCmdTask extends AbstractServerTask
{

    protected IServerConnector server;

    /**
     * @param cmd
     * @param message
     */
    public FSCmdTask(AbstractServerCmd cmd, byte[] message, IServerConnector server)
    {
        super(cmd, message);
        this.server = server;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.game.command.AbstractServerTask#execute()
     */
    @Override
    protected void execute()
    {
        try
        {
            this.cmd.execute((CSServerConn) server, this.message);
        }
        catch (Throwable e)
        {
            LOGGER.error(StackMessagePrint.printErrorTrace(e));
        }
        finally
        {
            ((CSServerConn) server).finishOneCommandTask();
        }
    }

}
