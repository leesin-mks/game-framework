/*
 * DataExecutor
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.database;

import java.sql.PreparedStatement;

/**
 * @author jacken
 *         db执行statement接口
 */
public interface DataExecutor<T>
{
    /**
     * 该方法为泛型方法
     * 
     * @param statement
     *            需要执行的statement
     * @param objects
     *            传入的参数集合
     * @return
     * @throws Exception
     */
    public T execute(PreparedStatement statement, Object... objects)
            throws Exception;

}
