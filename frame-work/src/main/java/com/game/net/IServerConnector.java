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

package com.game.net;

/**
 * @author jacken
 *
 */
public interface IServerConnector
{
    /**
     * 获取连接地址（IP或域名）
     * 
     * @return 连接地址（IP或域名）
     */
    String getAddress();

    /**
     * 获取连接端口
     * 
     * @return 连接端口
     */
    int getPort();

    /**
     * 获取最大重连次数
     * 
     * @return 最大重连次数
     */
    int getMaxReconnectTimes();

    /**
     * 设置最大重连次数
     * 
     * @param maxReconnectTimes
     *            最大重连次数
     */
    void setMaxReconnectTimes(int maxReconnectTimes);

    /**
     * 获取发生重连的次数
     * 
     * @return 发生重连的次数
     */
    int getReconnectedTimes();

    /**
     * 连接
     * 
     * @return 连接成功则返回true，否则返回false。
     */
    boolean connect();

    /**
     * 断开连接
     */
    void disconnect();

    /**
     * 是否已连接。
     * 
     * @return 已连接则返回true，否则返回false。
     */
    boolean isConnected();

    /**
     * 发送数据包
     * 
     * @param msg
     *            待发送的数据包
     */
    void send(Object msg);

    /**
     * 获取服务器包处理器
     * 
     * @return 服务器包处理器
     */
    IServerPacketHandler getPacketHandler();

    /**
     * 读取缓冲byte[]
     * 
     * @return
     */
    public byte[] getReadBytes();
}
