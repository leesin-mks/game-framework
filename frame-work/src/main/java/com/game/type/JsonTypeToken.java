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

package com.game.type;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.game.config.ServerConfig;
import com.google.common.reflect.TypeToken;

/**
 * @date 2020年04月16日 15:33
 * @author leesin
 */
public class JsonTypeToken
{
    public static final Type MAP_STRING_STRING = new TypeToken<Map<String, String>>()
    {
        private static final long serialVersionUID = 1L;
    }.getType();

    public static final Type LIST_INTEGER = new TypeToken<List<Integer>>()
    {
        private static final long serialVersionUID = 1L;
    }.getType();

    public static final Type SERVER = new TypeToken<ServerConfig>()
    {
        private static final long serialVersionUID = 1L;
    }.getType();
}
