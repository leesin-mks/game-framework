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

package com.game;

/**
 * @author leesin
 *
 */
public class BaseShutdownHooker extends Thread
{
    private RechargeServer centerServer;

    public BaseShutdownHooker(RechargeServer server)
    {
        this.centerServer = server;
    }

    /**
     * 退出回调，停止服务器
     */
    public void run()
    {
        if (centerServer != null)
        {
            centerServer.callBackStop();
        }
    }
}
