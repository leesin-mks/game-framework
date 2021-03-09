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

/**
 * @author leesin
 *
 */
public final class RedisConst
{
    /**
     * 玩家session
     */
    public static final String USER_SESSION_KEY = "USER_SESSION_KEY";

    /**
     * 玩家自动登录token
     */
    public static final String USER_PASSWORD = "USER_PASSWORD";

    public static final String USER_GS_SERVER_KEY = "USER_GS_SERVER_KEY";   // 游戏服务缓存

    public static final String USER_GW_SERVER_KEY = "USER_GW_SERVER_KEY";   // 网关服务缓存

    /**
     * 服务器更新时间
     */
    public static final String SERVER_UPDATE_TIME_KEY = "SERVER_UPDATE_TIME_KEY";

}
