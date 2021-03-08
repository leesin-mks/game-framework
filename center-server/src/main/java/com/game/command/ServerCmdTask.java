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

import com.game.net.IClientConnection;

/**
 * @author jacken
 *
 */
public class ServerCmdTask extends AbstractServerTask
{
    private IClientConnection connect;

    /**
     * @param cmd
     * @param message
     */
    public ServerCmdTask(AbstractServerCmd cmd, byte[] message, IClientConnection connect)
    {
        super(cmd, message);
        this.connect = connect;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.niuniu.command.AbstractTask#execute()
     */
    @Override
    protected void execute()
    {
        this.cmd.executeConnect(connect, message);
    }

}
