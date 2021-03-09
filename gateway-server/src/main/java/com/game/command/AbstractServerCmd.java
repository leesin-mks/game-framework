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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.cs.CSServerConn;
import com.game.net.IClientConnection;
import com.google.protobuf.ByteString;

/**
 * @author leesin
 *
 */
public abstract class AbstractServerCmd implements ICommand
{
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());

    /**
     * (non-Javadoc)
     * 
     * @see com.game.command.ICommand#execute(com.game.net.IClientConnection, byte[])
     */
    @Override
    public void execute(IClientConnection client, byte[] packet) throws Exception
    {
        // TODO Auto-generated method stub

    }

    /**
     * (non-Javadoc)
     * 
     * @see com.game.command.ICommand#execute(com.game.net.IClientConnection, com.google.protobuf.ByteString,short)
     */
    @Override
    public void execute(IClientConnection client, ByteString packet, short code) throws Exception
    {

    }

    public void execute(CSServerConn client, byte[] packet)
    {

    }
}
