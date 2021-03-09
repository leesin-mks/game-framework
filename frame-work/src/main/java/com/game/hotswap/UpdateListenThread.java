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

/**
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.game.hotswap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.component.ComponentManager;
import com.game.component.IRedisComponent;

/**
 * @author leesin
 *
 */
public class UpdateListenThread implements Runnable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateListenThread.class);
    private String[] channels;

    public void run()
    {
        while (true)
        {
            try
            {
                IRedisComponent rc = (IRedisComponent) ComponentManager.getInstance().getComponent(
                        IRedisComponent.NAME);
                UpdateListener listener = new UpdateListener();
                if (channels.length > 0)
                {
                    for (String channel : channels)
                    {
                        LOGGER.error("start listener: " + channel);
                    }
                    rc.subScribe(listener, channels);
                }
            }
            catch (Exception e)
            {
                LOGGER.error("UpdateListenThread error: ", e);
            }
        }
    }

    public void setChannel(String[] channels)
    {
        this.channels = channels;
    }

}
