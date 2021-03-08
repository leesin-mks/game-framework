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

package com.game.module.inf;

import com.game.type.ModuleType;

/**
 * @author leesin
 *
 */
public interface IModule
{
    /**
     * 初始化
     */
    boolean init();

    /**
     * 读DB
     * 
     * @return
     */
    boolean loadFromDB();

    /**
     * 写DB
     * 
     * @return
     */
    boolean saveIntoDB();

    /**
     * 写缓存
     * 
     * @return
     */
    boolean saveIntoCache();

    /**
     * 当前模块数据已经加载完成
     */
    void afterDataLoaded();

    /**
     * 得到模块类型
     * 
     * @return
     */
    ModuleType getType();
}
