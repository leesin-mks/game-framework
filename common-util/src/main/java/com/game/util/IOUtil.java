package com.game.util;

import java.io.Closeable;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtil
{

    private static final Logger LOGGER = LoggerFactory.getLogger(IOUtil.class);

    public static void closeIO(Closeable... sources)
    {
        if (sources != null)
        {
            for (Closeable source : sources)
            {
                try
                {
                    source.close();

                }
                catch (IOException e)
                {
                    LOGGER.error("Close io error: ", e);
                }
            }
        }
    }

}
