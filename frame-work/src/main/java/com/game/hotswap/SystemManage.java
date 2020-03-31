/**
 * 
 */
package com.game.hotswap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenzhichao
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
