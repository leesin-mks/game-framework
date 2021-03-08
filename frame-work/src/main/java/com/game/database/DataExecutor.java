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

import java.sql.PreparedStatement;

/**
 * @author leesin
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
     * @return execute result
     * @throws Exception
     *             if execute error
     */
    T execute(PreparedStatement statement, Object... objects)
            throws Exception;

}
