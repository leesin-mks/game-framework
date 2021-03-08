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
