/*
 * AbstractCommandComponent
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.command;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.component.IComponent;
import com.game.util.ClassUtil;

/**
 * @author jacken
 *
 */
public abstract class AbstractCommandComponent<T extends Annotation> implements IComponent
{
    private static Logger LOGGER = LoggerFactory.getLogger(AbstractCommandComponent.class);

    public static String NAME = "Command";

    /**
     * 缓存命令对象
     **/
    private final Map<Short, ICommand> cmdCache = new HashMap<Short, ICommand>();

    /**
     * 取得所在包名称
     * 
     * @return
     */
    public abstract String getCommandPacketName();

    /**
     * 取得注释的类型
     * 
     * @return
     */
    public abstract Class<T> getAnnotationClass();

    /**
     * 取得类型
     * 
     * @param annotation
     * @return
     */
    public abstract Short getNodeType(T annotation);

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

    /**
     * 加载命令集合
     * 
     */
    @Override
    public boolean start()
    {
        try
        {
            List<Class<?>> allClasses = ClassUtil.getClasses(getCommandPacketName());
            for (Class<?> clazz : allClasses)
            {
                try
                {
                    T cmd = clazz.getAnnotation(getAnnotationClass());
                    if (cmd != null)
                    {
                        if (cmdCache.get(getNodeType(cmd)) != null)
                        {
                            String name = cmdCache.get(getNodeType(cmd)).getClass().getName();
                            LOGGER.error("cmd code error, code : 0x"
                                    + Integer.toHexString(
                                            (int) getNodeType(cmd)).toUpperCase()
                                    + " exist :" + name
                                    + ", new : " + clazz.getName());
                            return false;
                        }
                        cmdCache.put(getNodeType(cmd), (ICommand) clazz.newInstance());
                        continue;
                    }
                }
                catch (Exception e)
                {
                    LOGGER.error("load command fail, command name : {}", clazz.getName(), e);
                    e.printStackTrace();
                }
            }
            LOGGER.info("cmdCache size : {}", cmdCache.size());
            return true;
        }
        catch (Exception e)
        {
            LOGGER.error("命令管理器解析错误", e);
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.component.IComponent#stop()
     */
    @Override
    public void stop()
    {

    }

    /**
     * 缓存中获取命令
     * 
     * @param code
     * @return
     */
    public ICommand getCommand(short code)
    {
        return cmdCache.get(code);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.component.IComponent#reload()
     */
    @Override
    public boolean reload()
    {
        // TODO Auto-generated method stub
        return false;
    }
}
