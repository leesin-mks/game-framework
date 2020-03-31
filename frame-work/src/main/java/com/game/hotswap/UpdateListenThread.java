/**
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.game.hotswap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.component.ComponentManager;
import com.game.component.IRedisComponent;

/**
 * @author chenzhichao
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
