package com.game.type;

/**
 * @date 2020年04月16日 17:05
 * @auth zm
 */
public class GlobalVariable
{
	public static final int REQUEST_EXPIRE_TIME = 10 * 60 * 1000;       // 键10分钟过期

	public static final int REQUEST_FREQUENCY_TIME = 1 * 1000;          // N秒内有效次数

	public static final int REQUEST_FREQUENCY = 5;                      // 每秒次数

	public static final int BAD_REQUEST_FREQUENCY = 5;                  // 失败请求次数

	public static final int BLACK_LIST_TIME = 5 * 60 * 1000;            // 黑名单屏蔽时间
}
