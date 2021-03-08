package com.game.util;

import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CopyrightUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CopyrightUtil.class);

    public static void updateCopyrightInfo(String path, String... exceptPath)
    {
        File file = new File(path);
        if (file.isDirectory())
        {
            updateCopyrightInfo(path);
        }
        else
        {
            String fileName = file.getName();
            if (fileName.endsWith(".java"))
            {
                // back up
                FileInputStream fis = null;
                InputStreamReader isr = null;
                BufferedReader br = null;
                try
                {
                    fis = new FileInputStream(file);
                    isr = new InputStreamReader(fis);
                    br = new BufferedReader(isr);
                    String version = br.readLine();
                    LOGGER.info("Server version: " + version);
                }
                catch (IOException e)
                {
                    LOGGER.error("Update copyright error: {}, message: {}", fileName, e);
                }
                finally
                {
                    IOUtil.closeIO(br, isr, fis);
                }
            }
        }
    }

}
