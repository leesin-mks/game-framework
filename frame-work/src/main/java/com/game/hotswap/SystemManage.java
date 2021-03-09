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
 * 
 */
package com.game.hotswap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leesin
 *
 */
public class SystemManage
{

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemManage.class);

    private static UpdateListener listener;

    private static String[] channels;

    public static boolean start(String[] channels)
    {
        SystemManage.channels = channels;
        try
        {
            UpdateListenThread update = new UpdateListenThread();
            update.setChannel(channels);
            Thread thread = new Thread(update, "UpdateListenThread");
            thread.start();
            return true;
        }
        catch (Exception e)
        {
            LOGGER.error("SystemManager start error: " + e.toString());
            return false;
        }
    }

    public static boolean stop()
    {
        try
        {
            if (listener != null)
            {
                if (channels.length > 0)
                {
                    for (String channel : channels)
                    {
                        LOGGER.error("stop Listener: " + channel);
                    }
                    listener.unsubscribe(channels);
                }
            }

            return true;
        }
        catch (Exception e)
        {
            LOGGER.error("SystemManager stop error:" + e.toString());
            return false;
        }
    }

}
