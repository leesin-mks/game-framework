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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.config.GlobalConfigManager;

/**
 * @author leesin
 *
 */
public class LanguageComponent implements IComponent
{
    /** 组件名称 */
    public static final String NAME = "LanguageComponent";

    private static final Logger LOGGER = LoggerFactory.getLogger(LanguageComponent.class);

    private static Map<String, String> cache = new ConcurrentHashMap<String, String>();

    /*
     * (non-Javadoc)
     * 
     * @see com.game.component.IComponent#getName()
     */
    @Override
    public String getName()
    {
        return NAME;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.game.component.IComponent#initialize()
     */
    @Override
    public boolean initialize()
    {
        // do nothing.
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.game.component.IComponent#start()
     */
    @Override
    public boolean start()
    {
        try
        {
            // String path = this.getClass().getResource("/").getPath();
            // LOGGER.error("------Path:" + path);

            FileInputStream fis = new FileInputStream(
                    GlobalConfigManager.getInstance().getServerConfig().getLanguagePath());
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            String lineContent;
            cache = new ConcurrentHashMap<String, String>();
            while ((lineContent = bufferedReader.readLine()) != null)
            {
                int index = lineContent.indexOf(":");
                if (index > -1)
                {
                    String key = lineContent.substring(0, index);
                    String value = lineContent.substring(index + 1);
                    cache.put(key, value);
                }
            }
            bufferedReader.close();
            isr.close();
            fis.close();

            LOGGER.info("Load {} language items.", cache.size());
            return true;
        }
        catch (FileNotFoundException e)
        {
            LOGGER.error("start language Fileerror!", e);
            return false;
        }
        catch (IOException e)
        {
            LOGGER.error("start language IOerror!", e);
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.game.component.IComponent#stop()
     */
    @Override
    public void stop()
    {
        cache.clear();
        cache = null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.game.component.IComponent#reload()
     */
    @Override
    public boolean reload()
    {
        this.stop();
        return this.start();
    }

    /**
     * 从语言包中获取对应的值。
     * 
     * @param key
     * @param paras
     * @return
     */
    public static String getResource(String key, Object... paras)
    {
        try
        {
            String msg = cache.get(key);
            if (msg == null)
            {
                LOGGER.error("Language package does not contain key: {}", key);
                return "";
            }
            return String.format(msg, paras);
        }
        catch (Exception e)
        {
            LOGGER.error(String.format("error key : %s", key), e);
            return "";
        }
    }

    public static String getContent(String content, Object... params)
    {
        String msg = content;
        if (null != params && params.length > 0)
        {
            for (int i = 0, max = params.length; i < max; i++)
            {
                Object param = params[i];
                if (null != param)
                {
                    msg = msg.replaceAll("\\{" + i + "\\}", param.toString());
                }
            }
        }
        return msg;
    }

    /**
     * 从语言包中获取对应的值
     * 
     * @param key
     * @return
     */
    public static String getResource(String key)
    {
        try
        {
            String msg = cache.get(key);
            if (msg == null)
            {
                LOGGER.error("Language package does not contain key: " + key,
                        new NullPointerException());
                return key;
            }

            return msg;
        }
        catch (Exception e)
        {
            LOGGER.error(e.toString());
            return key;
        }
    }
}
