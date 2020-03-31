/*
 * IBaseDao
 *
 * 2016年2月18日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author jacken
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
    public void addBatch(List<T> list, Connection conn) throws SQLException;

    /**
     * 批量更新
     * 
     * @param list
     * @param conn
     * @throws SQLException
     */
    public void updateBatch(List<T> list, Connection conn) throws SQLException;
}
