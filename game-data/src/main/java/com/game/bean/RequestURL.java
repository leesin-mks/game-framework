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

package com.game.bean;

import java.util.Map;

/**
 * @author dsfish
 *
 */
public class RequestURL
{
    private String serverInfo;              // ip:port
    private String path;                    // router
    private Map<String, String> params;     // 参数
    private int times;

    /**
     * @return the serverInfo
     */
    public String getServerInfo()
    {
        return serverInfo;
    }

    /**
     * @param serverInfo
     *            the serverInfo to set
     */
    public void setServerInfo(String serverInfo)
    {
        this.serverInfo = serverInfo;
    }

    /**
     * @return the path
     */
    public String getPath()
    {
        return path;
    }

    /**
     * @param path
     *            the path to set
     */
    public void setPath(String path)
    {
        this.path = path;
    }

    /**
     * @return the params
     */
    public Map<String, String> getParams()
    {
        return params;
    }

    /**
     * @param params
     *            the params to set
     */
    public void setParams(Map<String, String> params)
    {
        this.params = params;
    }

    public int getTimes()
    {
        return times;
    }

    public void setTimes(int times)
    {
        this.times = times;
    }

    @Override
    public String toString()
    {
        return "Server info : " + serverInfo + ", path: " + path + ", params: " + params;
    }
}
