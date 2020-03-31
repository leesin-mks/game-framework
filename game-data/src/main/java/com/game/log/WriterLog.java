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
