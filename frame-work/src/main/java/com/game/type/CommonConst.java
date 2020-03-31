/*
 * CommonConst
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
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
