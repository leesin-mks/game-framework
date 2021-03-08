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

package com.game.command;

/**
 * @date 2020年03月04日 19:40
 * @author leesin
 */
public final class CSProtocol
{
    public static final short PING = 0x0001;

    public static final short REGISTER = 0x0002;

    public static final short FORWARD_MESSAGE = 0x0003;

    public static final short MESSAGE_TO_USER = 0x0004;

    public static final short PLAYER_DISCONNECT = 0x0005;

    public static final short PLAYER_ON_DISCONNECT = 0x0006;
}
