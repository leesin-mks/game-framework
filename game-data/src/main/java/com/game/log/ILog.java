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

/**
 * Date: 2014-7-31
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.game.log;

import java.sql.Connection;
import java.util.List;

/**
 * 
 * @author leesin
 * @param <T>
 * 
 */
public interface ILog<T>
{
    public boolean add(List<T> logs, Connection conn);
}
