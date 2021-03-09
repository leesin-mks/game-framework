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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author leesin
 *
 */
public interface IBaseDao<T>
{
    /**
     * 添加
     * 
     * @param t
     * @return
     */
    boolean add(T t);

    /**
     * 修改
     * 
     * @param t
     * @return
     */
    boolean update(T t);

    /**
     * 根据脚本执行更新
     * 
     * @param sql
     *            更新脚本
     * @param paramWrapper
     *            参数
     * @return
     */
    boolean update(String sql, DBParamWrapper paramWrapper);

    /**
     * 删除
     * 
     * @param t
     * @return
     */
    boolean delete(T t);

    /**
     * 添加或修改缓存
     * 
     * @param t
     * @return
     */
    boolean addOrUpdate(T t);

    /**
     * @param ids
     *            对应数据库中主键
     * @return
     * @注意 参数传入的顺序对应脚本中的主键设置顺序
     */
    boolean deleteByKey(Object... ids);

    /**
     * 根据脚本执行查询操作，不带参数
     * 
     * @param sql
     * @return
     */
    T query(String sql);

    /**
     * 根据脚本执行查询操作
     * 
     * @param sql
     *            查询的脚本
     * @param paramWrapper
     *            参数
     * @return 返回查询结果对象
     */
    T query(String sql, DBParamWrapper paramWrapper);

    /**
     * 根据脚本执行查询操作,不带参数
     * 
     * @param sql
     *            查询的脚本
     * @return
     */
    List<T> queryList(String sql);

    /**
     * 根据脚本执行查询操作
     * 
     * @param sql
     *            sql 查询的脚本
     * @param paramWrapper
     *            参数
     * @return 返回查询结果对象集合
     */
    List<T> queryList(String sql, DBParamWrapper paramWrapper);

    /**
     * @param ids
     *            对应数据库中主键
     * @return
     * @注意 参数传入的顺序对应脚本中的主键设置顺序
     */
    T getByKey(Object... ids);

    /**
     * 查询所有
     */
    List<T> listAll();

    /**
     * 批量添加或修改实体
     * 
     * @param t
     * @return
     */
    int[] addOrUpdateBatch(List<T> t);

    /**
     * 批量删除实体
     * 
     * @param t
     * @return
     */
    int[] deleteBatch(List<T> t);

    /**
     * 批量新增
     * 
     * @param list
     * @param conn
     * @throws SQLException
     */
    void addBatch(List<T> list, Connection conn) throws SQLException;

    /**
     * 批量更新
     * 
     * @param list
     * @param conn
     * @throws SQLException
     */
    void updateBatch(List<T> list, Connection conn) throws SQLException;
}
