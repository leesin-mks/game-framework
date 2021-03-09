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

/**
 * @author leesin
 *         事件源。同一事件类型，支持多个监听者，并且监听者对象可以相同。
 */
public interface IEventSource
{
    /**
     * 将监听者加入到指定事件类型的监听队列中。
     * 
     * @param eventType
     *            事件类型
     * @param listener
     *            监听者
     */
    void addListener(int eventType, IEventListener listener);

    /**
     * 从指定事件类型的监听队列中移除指定的监听者。
     * 
     * @param eventType
     *            事件类型
     * @param listener
     *            监听者
     */
    void removeListener(int eventType, IEventListener listener);

    /**
     * 通知监听者发生了事件。事件源由事件参数arg指定。
     * 
     * @param arg
     *            事件参数
     */
    void notifyListeners(EventArg arg);

    /**
     * 通知监听者发生了事件。事件源为当前this对象。
     * 
     * @param eventType
     *            事件类型
     */
    void notifyListeners(int eventType);
}
