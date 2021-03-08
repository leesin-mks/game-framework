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

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.bll.DaoManager;
import com.game.command.SelfDrivenTaskQueue;
import com.game.component.ComponentManager;
import com.game.log.ILog;
import com.game.log.handler.LogHandler;
import com.game.log.task.AbstractTask;
import com.game.timer.ITimerComponent;
import com.game.timer.TimerComponent;
import com.game.util.ClassUtil;
import com.game.util.FileDataServer;
import com.game.util.NamedThreadFactory;

/**
 * 
 * @author saly.bao
 */
public class LogCache implements ILogCache
{
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());
    /** 写DB失败的次数,当次达到就写到文件中 */
    private final int errorNum = 1;

    /** 写DB失败次到达上限的日志记录（要向文件中添加） */
    private Map<List<Object>, Integer> errorLogs;

    private final Logger logger = LoggerFactory.getLogger(LogCache.class);

    private String path;
    private final String fileName = "error.xml";

    /** 日志处理对象 */
    private Map<String, ILog<Object>> logHandler;
    private ReentrantLock lock;

    /**
     * 自驱动的action队列
     */
    private SelfDrivenTaskQueue<Runnable> actionQueue;

    private SelfDrivenTaskQueue<Runnable> actionDelayQueue;

    private ExecutorService executorService;

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.log.cache.ILogCache#addLog(java.lang.Object)
     */
    @Override
    public <T> void addLog(T log)
    {
        CacheManager.getInstance().add(log);
    }

    /**
     * @return the actionDelayQueue
     */
    public SelfDrivenTaskQueue<Runnable> getActionDelayQueue()
    {
        return actionDelayQueue;
    }

    /**
     * @return the actionQueue
     */
    public SelfDrivenTaskQueue<Runnable> getActionQueue()
    {
        return actionQueue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.log.cache.ILogCache#writeLog()
     */
    @Override
    public void writeLog()
    {
        if (lock.isLocked())
            return;
        // long time = System.currentTimeMillis();
        Connection conn = null;
        try
        {
            lock.lock();
            conn = DaoManager.DB_MASTER_HELPER.openConn();
            Map<String, List<Object>> logs = CacheManager.getInstance().getLog();
            if (null != logs)
            {
                Set<Entry<String, List<Object>>> entrys = logs.entrySet();
                for (Entry<String, List<Object>> entry : entrys)
                {
                    List<Object> value = entry.getValue();
                    if (null != value)
                        this.addLog(entry.getKey(), value, conn);
                }
            }
            if (errorLogs.size() > 0)
            {
                List<List<Object>> list = null;
                Set<Entry<List<Object>, Integer>> entrys = errorLogs.entrySet();
                for (Entry<List<Object>, Integer> entry : entrys)
                {
                    if (entry.getValue() >= this.errorNum)
                    {
                        if (null == list)
                            list = new ArrayList<>();
                        list.add(entry.getKey());
                    }
                }
                if (null != list)
                {
                    for (List<Object> obj : list)
                    {
                        errorLogs.remove(obj);
                        FileDataServer.buildLog(this.path, fileName, obj);
                    }
                }
            }
        }
        catch (Exception e)
        {
            LOGGER.error("writeLog error: ", e);
        }
        finally
        {
            DaoManager.DB_MASTER_HELPER.closeConn(conn);
            lock.unlock();
        }
    }

    private void addLog(String key, List<Object> logs, Connection conn)
    {
        ILog<Object> log = logHandler.get(key);
        if (null != log)
        {
            if (null != logs && logs.size() > 0)
            {
                List<Object> logList = new ArrayList<>();
                for (int i = 0, max = logs.size(); i < max; i++)
                {
                    Object obj = logs.get(i);
                    if (obj != null)
                        logList.add(obj);
                    else
                        logger.error("logObject is " + obj + ",index:" + i
                                + ",size:" + max + ",classname:"
                                + log.getClass().getSimpleName());
                }
                if (!log.add(logList, conn))
                    this.setErrorLog(logList);
            }
        }
        else
        {
            logger.error("cachelog error " + key);
        }
    }

    private void setErrorLog(List<Object> value)
    {
        Integer num = errorLogs.get(value);
        num = null != num ? ++num : 1;
        errorLogs.put(value, num);
        if (num < errorNum)
            CacheManager.getInstance().addOld(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.pitaya.component.IComponent#getName()
     */
    @Override
    public String getName()
    {
        return NAME;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.pitaya.component.IComponent#initialize()
     */
    @Override
    public boolean initialize()
    {
        lock = new ReentrantLock();
        errorLogs = new HashMap<>();
        this.path = System.getProperty("user.dir") // + FileDataServer.ROOT_PATH
                + FileDataServer.LOGPATH;
        File dir = new File(path);
        if (!dir.exists() && !dir.isDirectory())
            dir.mkdir();
        File file = new File(path + fileName);
        if (!file.exists())
        {
            try
            {
                file.createNewFile();
            }
            catch (IOException e)
            {
                LOGGER.error("writeLog error:", e);
            }
        }
        return true;
    }

    /**
     * 加载LogHandler的不同接口
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    private Map<String, ILog<Object>> loadLogHandle()
    {
        Map<String, ILog<Object>> handleMap = new HashMap<String, ILog<Object>>();

        // 从相应的包加载LogHandler的不同接口
        List<Class<?>> handlerClass = ClassUtil.getClasses("com.game.log.handle");
        for (Class<?> class1 : handlerClass)
        {
            LogHandler annotation = class1.getAnnotation(LogHandler.class);
            if (annotation != null)
            {
                try
                {
                    handleMap.put(annotation.name(),
                            (ILog<Object>) class1.newInstance());
                }
                catch (InstantiationException e)
                {
                    LOGGER.error("writeLog error:", e);
                }
                catch (IllegalAccessException e)
                {
                    LOGGER.error("writeLog error:", e);
                }
            }
        }

        return handleMap;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.pitaya.component.IComponent#start()
     */
    @Override
    public boolean start()
    {
        logHandler = this.loadLogHandle();
        ((ITimerComponent) ComponentManager.getInstance().getComponent(TimerComponent.NAME)).addJob(
                this.getClass().getName(), LogSaveJob.class, 60 * 1000);
        executorService = Executors.newFixedThreadPool(2, new NamedThreadFactory("log-drive-pool"));
        actionQueue = new SelfDrivenTaskQueue<>(executorService);
        actionDelayQueue = new SelfDrivenTaskQueue<>(executorService);
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.pitaya.component.IComponent#stop()
     */
    @Override
    public void stop()
    {
        this.writeLog();
        if (executorService != null)
        {
            executorService.shutdown();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see little.seven.component.IComponent#reload()
     */
    @Override
    public boolean reload()
    {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.game.log.cache.ILogCache#addLogTask(com.game.log.task.AbstractTask)
     */
    @Override
    public <T> void addLogTask(AbstractTask<T> task)
    {
        task.setActionQueue(getActionQueue());
        getActionQueue().add(task);
    }

    @Override
    public <T> void addLogDelayTask(AbstractTask<T> task)
    {
        task.setActionQueue(getActionDelayQueue());
        getActionDelayQueue().add(task);
    }
}
