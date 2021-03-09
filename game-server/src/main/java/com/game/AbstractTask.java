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

package com.game;

import com.game.command.AbstractUserCmd;
import com.google.protobuf.ByteString;

/**
 * @author leesin
 *
 */
public abstract class AbstractTask implements Runnable
{
    /**
     * 指令
     */
    protected AbstractUserCmd cmd;

    /**
     * 通用消息
     */
    protected ByteString message;

    public AbstractTask(AbstractUserCmd cmd, ByteString message)
    {
        this.cmd = cmd;
        this.message = message;
    }

    /**
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run()
    {
        this.execute();
    }

    protected abstract void execute();

}
