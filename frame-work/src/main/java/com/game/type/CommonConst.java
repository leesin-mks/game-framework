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

import com.game.net.IClientConnection;
import com.game.net.IServerConnector;

import io.netty.util.AttributeKey;

/**
 * @author jacken
 *
 */
public final class CommonConst
{
    /** 客户端连接 */
    public static final AttributeKey<IClientConnection> CLIENT_CON = AttributeKey.valueOf("CLIENT_CON");
    /** 服务器连接器 */
    public static final AttributeKey<IServerConnector> SERVER_CON = AttributeKey.valueOf("SERVER_CON");
    /** 加密密钥 */
    public static final AttributeKey<int[]> NETTY_ENCRYPTION_KEY = AttributeKey.valueOf("EncryptionKey");
    /** 解密密钥 */
    public static final AttributeKey<int[]> NETTY_DECRYPTION_KEY = AttributeKey.valueOf("DecryptionKey");

    /**
     * 客户端真实IP
     */
    public static final AttributeKey<String> CLIENT_IP = AttributeKey.valueOf("CLIENT_IP");

    public static final AttributeKey<byte[]> READ_BYTES = AttributeKey.valueOf("READ_BYTES");

}
