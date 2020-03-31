/*
 * ComponentManager
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.util.StringUtil;

/**
 * @author jacken
 *
 */
public class ComponentManager
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ComponentManager.class);

    private ClassLoader loader = null;

    /** 组件集合 */
    private Map<String, IComponent> modules = null;

    private List<String> modulesKeys = null;

    /** 组件线程池 */
    private ExecutorService userCmdThreadPool = Executors.newFixedThreadPool(4);

    private ComponentManager()
    {
        loader = Thread.currentThread().getContextClassLoader();
        modules = new ConcurrentHashMap<String, IComponent>();
        modulesKeys = new LinkedList<String>();
    }

    private static class LazyHolder
    {
        private static final ComponentManager INSTANCE = new ComponentManager();
    }

    /**
     * 获取ComponentManager实例。
     * 
     * @return
     */
    public static ComponentManager getInstance()
    {
        return LazyHolder.INSTANCE;
    }

    /**
     * 添加组件。
     * 
     * @param className
     *            组件类名
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
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
        catch (ClassNotFoundException e)
        {
            LOGGER.error("", e);
            return false;
        }
        catch (InstantiationException e)
        {
            LOGGER.error("", e);
            return false;
        }
        catch (IllegalAccessException e)
        {
            LOGGER.error("", e);
            return false;
        }
    }

    /**
     * 获取组件实例。
     * 
     * @param componentName
     *            组件名称
     * @return
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
     * @return
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
     * @return
     */
    public boolean init()
    {
        return true;
    }

    /**
     * 启动组件管理器。
     * 
     * @return
     */
    public boolean start()
    {
        if (initModules() == false)
        {
            return false;
        }

        if (startModules() == false)
        {
            return false;
        }

        return true;
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
        Set<Entry<String, IComponent>> entrys = modules.entrySet();
        if (entrys != null && entrys.size() > 0)
        {
            for (Entry<String, IComponent> entry : entrys)
            {
                IComponent module = entry.getValue();
                if (!this.reLoadModule(module))
                    return false;
            }
        }
        return true;
    }

    /**
     * 根据组件名重新加载指定组件
     * 
     * @param names
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
                if (!this.reLoadModule(module))
                    return false;
            }
        }
        return true;
    }

    /**
     * 重新加载组件
     */
    private boolean reLoadModule(IComponent module)
    {
        try
        {
            module.reload();
            LOGGER.error("Component {} reload success.", module.getName());
            return true;
        }
        catch (Exception e)
        {
            LOGGER.error("Component {} reload error.", module.getName());
            e.printStackTrace();
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
                if (module.initialize() == false)
                {
                    LOGGER.error(moduleName + "initialize false.");
                    return false;
                }
            }
            catch (Throwable e)
            {
                if (module != null)
                {
                    this.modules.remove(module.getClass());
                    module.stop();
                }
                LOGGER.error("", e);
                return false;
            }
        }

        return true;
    }

    /**
     * 启动所有组件。
     * 
     * @return
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
                if (module.start() == false)
                {
                    LOGGER.error("Component {} has started failed.",
                            module.getName());
                    return false;
                }
                LOGGER.info("Component {} has started successfully.",
                        module.getName());
            }
            catch (Throwable e)
            {
                if (module != null)
                {
                    this.modules.remove(module.getClass());
                    module.stop();
                }
                LOGGER.error("", e);
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
        List<String> stack = new ArrayList<String>();
        for (int i = (modulesKeys.size() - 1); i >= 0; i--)
        {
            stack.add(modulesKeys.get(i));
        }

        IComponent module = null;
        Iterator<String> iterator = stack.iterator();
        while (iterator.hasNext())
        {
            module = modules.get(iterator.next());
            try
            {
                module.stop();
            }
            catch (Exception e)
            {
                LOGGER.error("module stop error: {}", module.getName(), e);
            }
        }

        modules.clear();
        modules = null;
    }

    /**
     * 
     */
    public ExecutorService getUserCmdThreadPool()
    {
        return userCmdThreadPool;
    }
}
