/*
 * StateCode
 *
 * 2017年9月22日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
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
