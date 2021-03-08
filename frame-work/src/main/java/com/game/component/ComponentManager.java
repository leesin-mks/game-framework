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

package com.game.component;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.util.StringUtil;

/**
 * @author leesin
 *
 */
public class ComponentManager
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ComponentManager.class);

    private final ClassLoader loader;

    /** 组件集合 */
    private final Map<String, IComponent> modules;

    private final List<String> modulesKeys;

    /** 组件线程池 */
    private final ExecutorService userCmdThreadPool = Executors.newFixedThreadPool(4);

    private ComponentManager()
    {
        loader = Thread.currentThread().getContextClassLoader();
        modules = new ConcurrentHashMap<>();
        modulesKeys = new LinkedList<>();
    }

    private static class LazyHolder
    {
        private static final ComponentManager INSTANCE = new ComponentManager();
    }

    /**
     * 获取ComponentManager实例。
     * 
     * @return single instance
     */
    public static ComponentManager getInstance()
    {
        return LazyHolder.INSTANCE;
    }

    /**
     * 添加组件。
     * 
     * @param className
     *            component simple name
     * @return the add component result
     */
    public boolean addComponent(String className)
    {
        try
        {
            Class<?> cls = loader.loadClass(className);
            IComponent module = (IComponent) cls.newInstance();
            if (StringUtil.isNullOrEmpty(module.getName()))
            {
                LOGGER.error("{} getName() is null or empty", module.getClass().getName());
                return false;
            }
            this.modules.put(module.getName(), module);
            modulesKeys.add(module.getName());

            return true;
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException e)
        {
            LOGGER.error("Add component error: ", e);
            return false;
        }
    }

    /**
     * 获取组件实例。
     * 
     * @param componentName
     *            组件名称
     * @return component instance
     */
    public IComponent getComponent(String componentName)
    {
        return modules.get(componentName);
    }

    /**
     * 静态获取组件实例，无需强转。
     * 
     * @param componentName
     *            组件名称
     * @return component instance
     */
    @SuppressWarnings("unchecked")
    public static <T> T component(String componentName)
    {
        return (T) ComponentManager.getInstance().getComponent(componentName);
    }

    /**
     * 获取所有组件。
     * 
     * @return 所有组件
     */
    public Map<String, IComponent> getComponents()
    {
        return modules;
    }

    /**
     * 初始化组件管理器。
     * 
     * @return the init result
     */
    public boolean init()
    {
        return true;
    }

    /**
     * 启动组件管理器。
     * 
     * @return starting result
     */
    public boolean start()
    {
        if (!initModules())
        {
            return false;
        }
        return startModules();
    }

    /**
     * 关闭组件管理器。
     */
    public void stop()
    {
        stopModules();
    }

    /**
     * 重新加载所有组件
     */
    public boolean reLoad()
    {
        Set<Entry<String, IComponent>> entries = modules.entrySet();
        if (!entries.isEmpty())
        {
            for (Entry<String, IComponent> entry : entries)
            {
                IComponent module = entry.getValue();
                if (!this.reloadModule(module))
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 根据组件名重新加载指定组件
     * 
     * @param names
     *            component name
     */
    public boolean reLoad(String... names)
    {
        if (null == names)
            return false;
        for (String name : names)
        {
            if (null == modules.get(name))
                return false;
        }

        for (String name : names)
        {
            IComponent module = modules.get(name);
            if (null != module)
            {
                if (!this.reloadModule(module))
                    return false;
            }
        }
        return true;
    }

    /**
     * 重新加载组件
     * 
     * @param module
     *            component
     *
     */
    private boolean reloadModule(IComponent module)
    {
        try
        {
            if (module.reload())
            {
                LOGGER.error("Component {} reload success.", module.getName());
                return true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            LOGGER.error("Component {} reload error.", module.getName());
        }
        return false;
    }

    /**
     * 初始化所有组件。
     */
    private boolean initModules()
    {
        IComponent module = null;

        for (String moduleName : modulesKeys)
        {
            try
            {
                module = modules.get(moduleName);
                if (!module.initialize())
                {
                    LOGGER.warn(moduleName + "initialize false.");
                    return false;
                }
            }
            catch (Throwable e)
            {
                if (module != null)
                {
                    this.modules.remove(module.getName());
                    module.stop();
                }
                LOGGER.error("Init module error: ", e);
                return false;
            }
        }

        return true;
    }

    /**
     * 启动所有组件。
     * 
     * @return start result
     */
    private boolean startModules()
    {
        IComponent module = null;

        for (String moduleName : modulesKeys)
        {
            try
            {
                module = modules.get(moduleName);
                LOGGER.info("Component {} is starting...", module.getName());
                if (!module.start())
                {
                    LOGGER.warn("Component {} has started failed.", module.getName());
                    return false;
                }
                LOGGER.info("Component {} has started successfully.", module.getName());
            }
            catch (Throwable e)
            {
                if (module != null)
                {
                    this.modules.remove(moduleName);
                    module.stop();
                }
                LOGGER.error("Component start error: ", e);
                return false;
            }
        }
        return true;
    }

    /**
     * 停止并销毁所有组件。
     */
    private void stopModules()
    {
        List<String> stack = new ArrayList<>();
        for (int i = (modulesKeys.size() - 1); i >= 0; i--)
        {
            stack.add(modulesKeys.get(i));
        }

        IComponent module;
        for (String s : stack)
        {
            module = modules.get(s);
            try
            {
                module.stop();
            }
            catch (Exception e)
            {
                LOGGER.error("Module stop error: {}", module.getName(), e);
            }
        }
        modules.clear();
    }

    /**
     * 
     */
    public ExecutorService getUserCmdThreadPool()
    {
        return userCmdThreadPool;
    }
}
