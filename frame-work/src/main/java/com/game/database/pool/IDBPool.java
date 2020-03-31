/*
 * IDBPool
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.database.pool;

import java.sql.Connection;

/**
 * @author jacken
 *
 */
public interface IDBPool
{
    /**
     * @return 数据连接
     */
    Connection getConnection();

    /**
     * 启动连接池
     * 
     * @return 成功返回true，失败返回false<br>
     *         可能在加载数据源的配置的时候出错
     */
    boolean startUp();

    /**
     * 关闭连接池
     */
    void shutDown();

    /**
     * 获取当前连接池的状态
     * 
     * @格式为 [ total:i, used:j, free:g ]
     * 
     * @return 返回连接池的状态
     */
    String getState();

    /**
     * 
     * @return 返回当前连接池的连接数
     */
    int getCurConns();
}
