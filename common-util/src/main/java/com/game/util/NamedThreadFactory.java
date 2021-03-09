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

package com.game.util;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ThreadFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leesin
 *
 */
public class NamedThreadFactory implements ThreadFactory,
        UncaughtExceptionHandler
{
    private static Logger logger = LoggerFactory.getLogger(NamedThreadFactory.class);

    /**
     * 是否为后台线程
     */
    private boolean daemon;

    /**
     * 线程名
     */
    private String threadName;

    /**
     * 默认构造函数
     * 
     * @param threadName
     *            线程名前缀
     * @param daemon
     *            是否为后台线程
     */
    public NamedThreadFactory(String threadName, boolean daemon)
    {
        this.threadName = threadName;
        this.daemon = daemon;
    }

    public NamedThreadFactory(String threadName)
    {
        this(threadName, false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.concurrent.ThreadFactory#newThread(java.lang.Runnable)
     */
    // @Override
    public Thread newThread(Runnable r)
    {
        Thread t = new Thread(r, this.threadName);
        t.setDaemon(this.daemon);
        t.setUncaughtExceptionHandler(this);
        return t;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Thread.UncaughtExceptionHandler#uncaughtException(java.lang.Thread,
     *      java.lang.Throwable)
     */
    public void uncaughtException(Thread thread, Throwable throwable)
    {
        logger.error("Uncaught Exception in thread " + thread.getName(),
                throwable);
    }
}
