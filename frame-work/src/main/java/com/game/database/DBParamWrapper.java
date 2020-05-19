/*
 * DBParamWrapper
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.database;

import java.util.HashMap;

/**
 * @author jacken
 *
 */
public class DBParamWrapper
{
    private HashMap<Integer, DBParameter> params;           // in 参数
    private HashMap<Integer, DBParameter> outParams;        // out 参数 （存储过程有用到）
    private int p;

    public DBParamWrapper()
    {
        this.params = new HashMap<>();
        this.outParams = new HashMap<>();
    }

    /**
     * 添加一个参数
     * 
     * @param type
     *            参数的类型
     * @param o
     *            参数的值
     */
    public void put(int type, Object o)
    {
        params.put(++p, new DBParameter(type, o));
    }

    public void putOutParam(int type, Object o)
    {
        outParams.put(++p, new DBParameter(type, o));
    }

    public HashMap<Integer, DBParameter> getParams()
    {
        return params;
    }

    public HashMap<Integer, DBParameter> getOutParams()
    {
        return outParams;
    }

    /**
     * 重新初始化该sql参数包装类
     */
    public void clear()
    {
        params.clear();
        p = 0;
    }
}
