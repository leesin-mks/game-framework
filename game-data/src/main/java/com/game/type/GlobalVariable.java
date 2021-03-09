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
 * @date 2020年04月16日 17:05
 * @author leesin
 */
public class GlobalVariable
{
	public static final int REQUEST_EXPIRE_TIME = 10 * 60 * 1000;       // 键10分钟过期

	public static final int REQUEST_FREQUENCY_TIME = 1 * 1000;          // N秒内有效次数

	public static final int REQUEST_FREQUENCY = 5;                      // 每秒次数

	public static final int BAD_REQUEST_FREQUENCY = 5;                  // 失败请求次数

	public static final int BLACK_LIST_TIME = 5 * 60 * 1000;            // 黑名单屏蔽时间
}
