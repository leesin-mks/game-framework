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

package com.game.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leesin
 *         事件源。同一事件类型，支持多个监听者，并且监听者对象可以相同。
 */
public class EventSource implements IEventSource
{
    private static final Logger LOGGER = LoggerFactory.getLogger(EventSource.class);

    /**
     * 监听都列表。同一事件，可能存在多个相同的监听者。
     */
    private final Map<Integer, LinkedHashSet<IEventListener>> listeners = new ConcurrentHashMap<>();

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * 将监听者加入到指定事件类型的监听队列中。
     * 
     * @param eventType
     *            事件类型
     * @param listener
     *            监听者
     */
    @Override
    public void addListener(int eventType, IEventListener listener)
    {
        if (listener == null)
        {
            LOGGER.error("listener is null", new NullPointerException());
        }

        LinkedHashSet<IEventListener> listeners = this.listeners.get(eventType);
        if (listeners == null)
        {
            try
            {
                // 加写锁确保没有并发问题。
                this.lock.writeLock().lock();

                listeners = this.listeners.get(eventType);
                if (listeners == null)
                {
                    listeners = new LinkedHashSet<>();
                    listeners.add(listener);
                    this.listeners.put(eventType, listeners);
                }
                else
                {
                    listeners.add(listener);
                }
            }
            catch (Exception e)
            {
                LOGGER.error("", e);
            }
            finally
            {
                this.lock.writeLock().unlock();
            }
        }
        else
        {
            listeners.add(listener);
        }
        LOGGER.debug("Added a listener: {}, {}", eventType, listener);
    }

    /**
     * 从指定事件类型的监听队列中移除指定的监听者。
     * 
     * @param eventType
     *            事件类型
     * @param listener
     *            监听者
     */
    @Override
    public void removeListener(int eventType, IEventListener listener)
    {
        try
        {
            this.lock.writeLock().lock();

            Collection<IEventListener> listeners = this.listeners.get(eventType);
            if (listeners != null)
            {
                listeners.remove(listener);
            }
        }
        catch (Exception e)
        {
            LOGGER.error("", e);
        }
        finally
        {
            this.lock.writeLock().unlock();
        }

        LOGGER.debug("Removed a listener: {}, {}", eventType, listener);
    }

    /**
     * 通知监听者发生了事件。事件源由事件参数arg指定。
     * 
     * @param arg
     *            事件参数
     */
    @Override
    public void notifyListeners(EventArg arg)
    {
        try
        {
            this.lock.writeLock().lock();

            Collection<IEventListener> listeners = this.listeners.get(arg.getEventType());
            if (listeners != null)
            {
                listeners = new ArrayList<>(listeners);
                for (IEventListener item : listeners)
                {
                    item.onEvent(arg);
                }
            }
        }
        catch (Exception e)
        {
            LOGGER.error("", e);
        }
        finally
        {
            this.lock.writeLock().unlock();
        }
    }

    /**
     * 通知监听者发生了事件。事件源为当前this对象。
     * 
     * @param eventType
     *            事件类型
     */
    @Override
    public void notifyListeners(int eventType)
    {
        try
        {
            this.lock.writeLock().lock();
            Collection<IEventListener> eventListeners = listeners.get(eventType);
            if (eventListeners != null)
            {
                eventListeners = new ArrayList<>(eventListeners);
                for (IEventListener item : eventListeners)
                {
                    item.onEvent(new EventArg(this, eventType));
                }
            }
        }
        catch (Exception e)
        {
            LOGGER.error("", e);
        }
        finally
        {
            this.lock.writeLock().unlock();
        }
    }
}
