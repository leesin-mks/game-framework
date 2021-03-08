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
 * @author dsfish
 *
 */
public enum LoginType
{
    PHONE_PASSWORD(0),      // 电话号码和密码登录
    DEVICE(1),              // 设备号登陆
    PHONE_CODE(2),          // 电话验证码登录
    RESET_PASSWORD(3),      // 重置密码
    WEI_XIN(4),             // 微信登录
    ACCOUNT_PASSWORD(5),    // 账号密码登录
    CHECK_CODE(6),          // 检验验证码
    AUTO_LOGIN(7),          // 自动登陆
    ;

    private final byte value;

    private LoginType(int value)
    {
        this.value = (byte) value;
    }

    public int getValue()
    {
        return value;
    }

    public static LoginType parse(int type)
    {
        LoginType[] items = LoginType.values();
        for (LoginType item : items)
        {
            if (item.value == type)
            {
                return item;
            }
        }
        return null;
    }

    public static LoginType getLoginType(String str)
    {
        try
        {
            LoginType type = LoginType.valueOf(str);
            return type;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
