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

/**
 * @author jacken
 *
 */
public class DBParameter
{
    private String direction;
    private int dbType;
    private Object info;

    /**
     * 设置存储过程访问参数
     * 
     * @param ParameterDirection
     *            设置参数传入、传出,指定范围:ParameterDirection.Input;ParameterDirection.
     *            Output ; ParameterDirection.InputOutput
     * @param Types
     *            设置参数数据类型，指定范围：Types.INTEGER;Types.VARCHAR;Types.DATE;
     * @param info
     *            设置参数值，类型为输入时，不能为空；
     */
    public DBParameter(String ParameterDirection, int Types, Object info)
    {
        this.direction = ParameterDirection;
        this.dbType = Types;
        this.info = info;
    }

    public DBParameter(int Types, Object info)
    {
        this.dbType = Types;
        this.info = info;
    }

    /**
     * 设置存储过程访问参数（限为输出类型）
     * 
     * @param ParameterDirection
     *            设置参数传入、传出,指定范围:ParameterDirection.Input;ParameterDirection.
     *            Output ;ParameterDirection.InputOutput
     * @param Types
     *            设置参数数据类型，指定范围：Types.INTEGER;Types.VARCHAR;Types.DATE;
     */
    public DBParameter(String ParameterDirection, int Types)
    {
        this.direction = ParameterDirection;
        this.dbType = Types;
    }

    public String getDirection()
    {
        return direction;
    }

    public int getDbType()
    {
        return dbType;
    }

    public Object getResult()
    {
        return info;
    }

    public void setResult(Object result)
    {
        this.info = result;
    }
}
