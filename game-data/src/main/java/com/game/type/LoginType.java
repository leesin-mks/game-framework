/*
 * LoginType
 *
 * 2017年6月15日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
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
