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


import com.game.component.IComponent;
import com.game.log.task.AbstractTask;

/**
 * 日志缓存接口
 * 
 * @author saly.bao
 */
public interface ILogCache extends IComponent
{
    /** 组件名称 */
    public static final String NAME = "LogCache";

    /**
     * 向缓存中添加日志
     * 
     * @param log
     */
    public <T> void addLog(T log);

    /**
     * 向DB中更新日志
     */
    public void writeLog();

    public <T> void addLogTask(AbstractTask<T> task);
    
    public <T> void addLogDelayTask(AbstractTask<T> task);
}
