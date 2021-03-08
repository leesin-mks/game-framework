package com.game;

import java.io.*;
import java.lang.management.ManagementFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.game.config.GlobalConfigManager;
import com.game.util.ClassUtil;
import com.game.util.IOUtil;

public interface IServer
{
    void callBackStop();

    boolean loadComponents();

    int getServerID();

    int getStatus();

    static void printServerPID()
    {
        Class mainClass = ClassUtil.deduceMainApplicationClass();
        String serverPid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        System.out.println("Server pid: " + serverPid);
        if (mainClass != null)
        {
            Log log = LogFactory.getLog(mainClass);
            log.info("Server pid: " + serverPid);
        }
    }

    static void printServerVersion()
    {
        Class mainClass = ClassUtil.deduceMainApplicationClass();
        Log log = LogFactory.getLog(mainClass);
        String versionPath = GlobalConfigManager.getInstance().getServerConfig().getVersionPath();
        versionPath = versionPath == null ? "./version.txt" : versionPath;
        File file = new File(versionPath);
        if (file.exists())
        {
            FileInputStream fis = null;
            InputStreamReader isr = null;
            BufferedReader br = null;
            try
            {
                fis = new FileInputStream(file);
                isr = new InputStreamReader(fis);
                br = new BufferedReader(isr);
                String version = br.readLine();
                System.out.println("Server version: " + version);
                log.info("Server version: " + version);
            }
            catch (IOException e)
            {
                log.error("Read version file error: ", e);
            }
            finally
            {
                IOUtil.closeIO(br, isr, fis);
            }
        }
        else
        {
            System.out.println("Can not find version file");
            log.warn("Can not find version file");
        }
    }

}
