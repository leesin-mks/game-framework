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

import com.game.server.ServerClient;
import com.game.util.StackMessagePrint;

/**
 * @author leesin
 *
 */
public class UserServerCmdTask extends AbstractServerTask
{

    private final ServerClient client;

    /**
     * @param cmd
     *            command
     * @param message
     *            msg
     * @param client
     *            server client
     */
    public UserServerCmdTask(AbstractServerCmd cmd, byte[] message, ServerClient client)
    {
        super(cmd, message);
        this.client = client;
    }

    /**
     * (non-Javadoc)
     * 
     *
     */
    @Override
    protected void execute()
    {
        try
        {
            this.cmd.exec(this.client, this.message);
        }
        catch (Throwable e)
        {
            LOGGER.error("exec task error: {}, info: {}", client.getSequenceTaskName(),
                    StackMessagePrint.printErrorTrace(e));
        }
        finally
        {
            client.finishOneCommandTask();
        }
    }

}
