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

import java.sql.ResultSet;

/**
 * @author leesin
 *         db查询结果集数据读取接口
 */
public interface DataReader<T>
{
    /**
     * 从查询结果集中读取一条记录并返回，本接口为泛型接口，如需读取的数据无明显类型特征（如抽取不规则字段集等），可直接使用Object类型进行读取
     * 
     * @param rs
     *            result set
     * @return read result
     * @throws Exception
     *             if read date error
     */
    T readData(ResultSet rs, Object... objects) throws Exception;
}
