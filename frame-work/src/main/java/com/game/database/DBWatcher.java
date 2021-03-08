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

package com.game.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.util.TimeUtil;

/**
 * @author jacken
 *
 */
public class DBWatcher
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DBWatcher.class);
    private long first;
    private long second;
    private long end;

    public DBWatcher()
    {
        first = TimeUtil.getSysCurTimeMillis();
    }

    /**
     * 记录获取数据库连接点
     */
    public void watchGetConnector()
    {
        second = TimeUtil.getSysCurTimeMillis();
    }

    /**
     * 记录数据脚本执行点
     */
    public void watchSqlExec()
    {
        end = TimeUtil.getSysCurTimeMillis();
    }

    /**
     * 查看执行情况
     * 
     * @param procName
     *            此次执行事务的别名
     */
    public void keyRecords(String procName)
    {
        long spendTime = end - first;
        if (spendTime > 1000)
        {
            LOGGER.error(String.format(
                    "执行语句%s花耗时间总时间 超过:%sms,获取连接：%sms,执行sql:%sms", procName,
                    spendTime, second - first, end - second));
        }
    }
}
