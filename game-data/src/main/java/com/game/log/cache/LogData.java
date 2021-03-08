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

/**
 * 日志缓存对象
 * 
 * @author saly.bao
 * @param <T>
 */
public class LogData<T>
{
    /** 已向数据库更新次数 */
    private int index = 0;

    /** 日志记录信息 */
    private T log;

    /**
     * @return the index
     */
    public int getIndex()
    {
        return index;
    }

    /**
     * @param index
     *            the index to set
     */
    public void setIndex(int index)
    {
        this.index = index;
    }

    /**
     * @return the log
     */
    public T getLog()
    {
        return log;
    }

    /**
     * @param log
     *            the log to set
     */
    public void setLog(T log)
    {
        this.log = log;
    }

}
