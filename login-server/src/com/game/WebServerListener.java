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

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.component.*;
import com.game.config.GlobalConfigManager;
import com.game.database.DBComponent;
import com.game.manager.LoginManager;
import com.game.timer.TimerComponent;
import com.game.util.IOUtil;

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
        LOGGER.info("------Web server listener closed------");
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
                IServer.printServerPID();
                LOGGER.info("Init success");
                return;
            }
        }
        LOGGER.warn("Init failed");
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
                IOUtil.closeIO(br, isr, fis);
            }
        }
        else
        {
            System.out.println("Can not find version file");
            LOGGER.warn("Can not find version file");
        }
    }

}
