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

import java.util.HashMap;

/**
 * @author leesin
 *
 */
public class DBParamWrapper
{
    private final HashMap<Integer, DBParameter> params;           // in 参数
    private final HashMap<Integer, DBParameter> outParams;        // out 参数 （存储过程有用到）
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
