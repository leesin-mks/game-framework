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

package com.game.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;

import org.dom4j.DocumentException;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.component.IComponent;
import com.game.config.GlobalConfigManager;
import com.game.util.ClassUtil;

/**
 * @author jacken
 *
 */
public class WebComponent implements IComponent
{
    private static final Logger LOGGER = LoggerFactory.getLogger(WebComponent.class);

    public static final String NAME = "WebComponent";

    private Server server;      // jetty自带的server

    private ServletContextHandler context;  // 触发的处理器

    private HandlerList handlerList = new HandlerList();    // 处理器列表

    private ResourceHandler resourceHandler;    // 触发的资源处理器

    @Override
    public String getName()
    {
        return NAME;
    }

    @Override
    public boolean initialize()
    {
        return true;
    }

    @Override
    public boolean start()
    {
        server = new Server(GlobalConfigManager.getInstance().getWebServerConfig().getPort());

        try
        {
            context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath("/");
            context.setResourceBase("");

            resourceHandler = new ResourceHandler();
            resourceHandler.setResourceBase(GlobalConfigManager.getInstance().getWebServerConfig().getResourcePath());

            // handList顺序执行, 有报错才会执行下一个, resourceHandler要放在servletHandler前面
            handlerList.addHandler(resourceHandler);
            handlerList.addHandler(context);

            server.setHandler(handlerList);
            server.setHandler(context);
            server.setAttribute("CharacterEncoding", "UTF-8");
            loadServletByWebServerConfig();
            server.start();

        }
        catch (DocumentException e)
        {
            LOGGER.error("load Xml Failed");
            e.printStackTrace();
            return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void stop()
    {
        try
        {
            // server.stop();
        }
        catch (Exception e)
        {
            LOGGER.error("stop webComponent error!", e);
        }
    }

    @Override
    public boolean reload()
    {
        return false;
    }

    public Map<String, Class<?>> resetMapHandle()
    {
        List<Class<?>> activityClass = ClassUtil.getClasses("com.game.web");
        Map<String, Class<?>> HandleMap = new HashMap<String, Class<?>>();
        for (Class<?> class1 : activityClass)
        {
            WebHandleAnnotation annotation = class1.getAnnotation(WebHandleAnnotation.class);
            if (annotation != null)
            {
                HandleMap.put(annotation.cmdName(), class1);
            }
        }
        return HandleMap;
    }

    private boolean loadServletByWebServerConfig()
    {
        Map<String, Class<?>> HandleMap = resetMapHandle();
        for (Map.Entry<String, Class<?>> one : HandleMap.entrySet())
        {
            try
            {
                String path = one.getKey();
                Servlet servlet = (Servlet) one.getValue().newInstance();
                context.addServlet(new ServletHolder(servlet), path);
            }
            catch (InstantiationException e)
            {
                e.printStackTrace();
                continue;
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
                continue;
            }
        }
        return true;
    }
}
