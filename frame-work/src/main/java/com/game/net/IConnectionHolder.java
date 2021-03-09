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
 * @author leesin
 *
 */
public interface IConnectionHolder
{
    /**
     * 连接关闭时的回调。
     */
    void onDisconnect();

    /**
     * 获取持有的连接。
     * 
     * @return connection
     */
    IClientConnection getClientConnection();

    /**
     * 设置持有的连接。
     * 
     * @param conn
     *            the connection to set
     */
    void setClientConnection(IClientConnection conn);
}
