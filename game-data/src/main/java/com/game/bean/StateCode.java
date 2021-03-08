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

package com.game.bean;

/**
 * @author dsfish
 *
 */
public final class StateCode
{
    public static final int SERVER_UPDATE = 0;                   // 服务器更新维护
    public static final int LOGIN_SUCCESS = 1;                   // 登录成功
    public static final int LOGIN_FAIL = 2;                      // 登录失败
    public static final int LOGIN_ACTIVE = 3;                    // 账号未激活
    public static final int LOGIN_SUSPEND = 4;                   // 账号封停
    public static final int ACCOUNT_NULL = 5;                    // 账号不存在
    public static final int ACCOUNT_EXIST = 6;                   // 账号已存在
    public static final int REGISTER_SUCCESS = 7;                // 注册成功
    public static final int REGISTER_FAIL = 7;                   // 注册失败
    public static final int LOAD_DATA_FAIL = 9;                  // 数据加载失败
    public static final int VERIFICATION_FAIL = 10;              // 游戏服务器验证失败
    public static final int GATE_WAY_VERIFICATION_FAIL = 10;     // 网关服务器验证失败
    public static final int REPEAT_LOGIN = 11;                   // 账号在别处登录
    public static final int SERVER_CLOSE = 12;                   // 服务器已关闭
}
