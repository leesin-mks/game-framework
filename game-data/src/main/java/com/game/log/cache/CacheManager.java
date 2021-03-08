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

/**
 * Date: 2014-3-26
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.game.log.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 缓存管理
 * 
 * @author saly.bao
 */
public class CacheManager
{
    /** 缓存中的日志 */
    private Map<String, List<Object>> cache;
    /** 要向DB中更新的日志 */
    private Map<String, List<Object>> write;
    /** DB更新失败需再次更新日志 */
    private Map<String, List<Object>> old;

    private ReentrantLock lock;

    private CacheManager()
    {
        lock = new ReentrantLock();
        cache = new HashMap<>();
        write = new HashMap<>();
        old = new HashMap<>();
    }

    private static class LazyHolder
    {
        private static final CacheManager INSTANCE = new CacheManager();
    }

    /**
     * 获取CacheManager实例。
     * 
     * @return
     */
    public static CacheManager getInstance()
    {
        return LazyHolder.INSTANCE;
    }

    /**
     * 添加缓存日志
     * 
     * @param log
     */
    public void add(Object log)
    {
        String key = log.getClass().getSimpleName();
        List<Object> logs = null;
        synchronized (key)
        {
            logs = cache.get(key);
            if (logs == null)
            {
                logs = new ArrayList<Object>();
                cache.put(key, logs);
            }
        }
        logs.add(log);
    }

    /**
     * 获取要更新到DB的日志
     * 
     * @return
     */
    public Map<String, List<Object>> getLog()
    {
        try
        {
            lock.lock();
            write.clear();
            // 取出缓存日志添加到DB更新中
            Set<Entry<String, List<Object>>> entrys = cache.entrySet();
            for (Entry<String, List<Object>> entry : entrys)
            {
                String key = entry.getKey();
                List<Object> value = entry.getValue();
                entry.setValue(null);
                if (null != value)
                    write.put(key, value);
            }
            // 取出DB失败日志添加到DB更新中
            entrys = old.entrySet();
            for (Entry<String, List<Object>> entry : entrys)
            {
                String key = entry.getKey();
                List<Object> value = entry.getValue();
                if (null != value)
                {
                    List<Object> wri = write.get(key);
                    if (null == wri)
                    {
                        write.put(key, value);
                    }
                    else
                    {
                        wri.addAll(value);
                    }
                }
            }
            old.clear();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            lock.unlock();
        }
        return write;
    }

    /**
     * 更新DB失败的日志回收，下次重新写
     * 
     * @param log
     */
    public void addOld(Object log)
    {
        String key = log.getClass().getSimpleName();
        List<Object> logs = null;
        synchronized (key)
        {
            logs = old.get(key);
            if (logs == null)
            {
                logs = new ArrayList<Object>();
                old.put(key, logs);
            }
        }
        logs.add(log);
    }
}
