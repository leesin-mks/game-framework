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
 * Date: 2014-3-4
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.game.log;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.game.component.ComponentManager;
import com.game.log.cache.ILogCache;
import com.game.log.task.AbstractTask;

/**
 * 写日志到数据库
 * 
 * @author jacken
 */
public class WriterLog
{
    private static ILogCache logCache;

    private static ILogCache getLogCache()
    {
        if (logCache == null)
        {
            logCache = (ILogCache) ComponentManager.getInstance().getComponent(ILogCache.NAME);
        }
        return logCache;
    }

    public static Date LocalDateTimeToUdate(LocalDateTime localDateTime)
    {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static void addLog(Object log)
    {
        getLogCache().addLog(log);
    }

    public static <T> void addTask(AbstractTask<T> task)
    {
        getLogCache().addLogTask(task);
    }

    public static <T> void addDelayTask(AbstractTask<T> task)
    {
        getLogCache().addLogDelayTask(task);
    }

}
