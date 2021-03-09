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

import java.io.*;
import java.lang.management.ManagementFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.component.*;
import com.game.config.GlobalConfigManager;
import com.game.database.DBComponent;
import com.game.manager.LoginManager;
import com.game.timer.TimerComponent;

/**
 * @author leesin
 *
 */
public class WebServerListener implements ServletContextListener
{
    private static final Logger LOGGER = LoggerFactory.getLogger(WebServerListener.class);

    /**
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0)
    {
        LOGGER.info("------关闭成功-------");
        LoginManager.stop();
        ComponentManager.getInstance().stop();
    }

    /**
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
