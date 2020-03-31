/*
 * DBParameter
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.database;

/**
 * @author jacken
 *
 */
public class DBParameter
{
    private String direction;
    private int dbtype;
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
        this.dbtype = Types;
        this.info = info;
    }

    public DBParameter(int Types, Object info)
    {
        this.dbtype = Types;
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
        this.dbtype = Types;
    }

    public String getDirection()
    {
        return direction;
    }

    public int getDbtype()
    {
        return dbtype;
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
