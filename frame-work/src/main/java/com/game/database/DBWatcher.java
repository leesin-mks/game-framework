/*
 * DBWatcher
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
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
