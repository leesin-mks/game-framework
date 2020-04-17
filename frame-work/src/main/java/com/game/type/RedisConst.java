/*
 * RedisConst
 *
 * 2017年6月16日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
 */
package com.game.type;

/**
 * @author dsfish
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
