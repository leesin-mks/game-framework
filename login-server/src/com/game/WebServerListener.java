/*
 * WebServerListener
 *
 * 2016年2月18日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.component.ComponentManager;
import com.game.component.DataCenterComponent;
import com.game.component.LanguageComponent;
import com.game.component.RedisComponent;
import com.game.component.ServerListComponent;
import com.game.config.GlobalConfigManager;
import com.game.database.DBComponent;
import com.game.manager.LoginManager;
import com.game.timer.TimerComponent;

/**
 * @author jacken
 *
 */
public class WebServerListener implements ServletContextListener
{
    private static Logger LOGGER = LoggerFactory.getLogger(WebServerListener.class);

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0)
    {
        LOGGER.error("------关闭成功-------");
        LoginManager.stop();
        ComponentManager.getInstance().stop();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent context)
    {
        String path = context.getServletContext().getRealPath("") + "/WEB-INF/";
        LOGGER.info("Context path: {}", context.getServletContext().getRealPath(""));
        if (GlobalConfigManager.getInstance().init(path + "global.xml"))
        {
            ComponentManager componentManager = ComponentManager.getInstance();
            componentManager.addComponent(LanguageComponent.class.getName());
            componentManager.addComponent(DBComponent.class.getName());
            componentManager.addComponent(RedisComponent.class.getName());
            componentManager.addComponent(TimerComponent.class.getName());
            componentManager.addComponent(ServerListComponent.class.getName());
            componentManager.addComponent(DataCenterComponent.class.getName());

            if (componentManager.start())
            {
                LoginManager.init();
                printServerVersion(context.getServletContext().getRealPath(""));
                printServerPID();
                LOGGER.info("初始化成功");
                return;
            }
        }
        LOGGER.warn("初始化失败");
    }

    private static void printServerVersion(String path)
    {
        String versionPath = GlobalConfigManager.getInstance().getServerConfig().getVersionPath();
        versionPath = versionPath == null ? path + "version.txt" : versionPath;
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
                LOGGER.info("Server version: {}", version);
            }
            catch (IOException e)
            {
                LOGGER.error("Read version file error: ", e);
            }
            finally
            {
                if (br != null)
                {
                    try
                    {
                        br.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                if (isr != null)
                {
                    try
                    {
                        isr.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                if (fis != null)
                {
                    try
                    {
                        fis.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

        }
        else
        {
            System.out.println("Can not find version file");
            LOGGER.error("Can not find version file");
        }
    }

    private static void printServerPID()
    {
        String serverPid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        System.out.println("Server pid: " + serverPid);
        LOGGER.error("Server pid: " + serverPid);
    }

}
