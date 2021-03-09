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
 * @author leesin
 *
 */
public abstract class AbstractCommandComponent<T extends Annotation> implements IComponent
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCommandComponent.class);

    public static String NAME = "Command";

    /**
     * 缓存命令对象
     **/
    private final Map<Short, ICommand> cmdCache = new HashMap<>();

    /**
     * 取得所在包名称
     * 
     * @return package name
     */
    public abstract String getCommandPacketName();

    /**
     * 取得注释的类型
     * 
     * @return annotation class
     */
    public abstract Class<T> getAnnotationClass();

    /**
     * 取得类型
     * 
     * @param annotation
     *            annotation
     * @return annotation code
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
            assert allClasses != null;
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
            LOGGER.error("Command parse error: ", e);
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.game.component.IComponent#stop()
     */
    @Override
    public void stop()
    {

    }

    /**
     * 缓存中获取命令
     * 
     * @param code
     *            protocol code
     * @return command
     */
    public ICommand getCommand(short code)
    {
        return cmdCache.get(code);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.game.component.IComponent#reload()
     */
    @Override
    public boolean reload()
    {
        return false;
    }
}
