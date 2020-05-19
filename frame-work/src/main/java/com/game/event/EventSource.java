/*
 * EventSource
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jacken
 *         事件源。同一事件类型，支持多个监听者，并且监听者对象可以相同。
 */
public class EventSource implements IEventSource
{
    private static final Logger LOGGER = LoggerFactory.getLogger(EventSource.class);

    /**
     * 监听都列表。同一事件，可能存在多个相同的监听者。
     */
    private Map<Integer, Collection<IEventListener>> listeners = new ConcurrentHashMap<>();

    private ReadWriteLock lock = new ReentrantReadWriteLock();

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

        Collection<IEventListener> lstns = this.listeners.get(eventType);
        if (lstns == null)
        {
            try
            {
                // 加写锁确保没有并发问题。
                this.lock.writeLock().lock();

                lstns = this.listeners.get(eventType);
                if (lstns == null)
                {
                    lstns = new LinkedList<>();
                    lstns.add(listener);
                    this.listeners.put(eventType, lstns);
                }
                else
                {
                    lstns.add(listener);
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
            lstns.add(listener);
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

            Collection<IEventListener> lstns = this.listeners.get(eventType);
            if (lstns != null)
            {
                lstns.remove(listener);
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

            Collection<IEventListener> lstns = this.listeners.get(arg.getEventType());
            if (lstns != null)
            {
                lstns = new ArrayList<>(lstns);
                for (IEventListener item : lstns)
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
