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

package com.game.web.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author jacken
 *
 */
public class LoginResultBean
{
    @SerializedName("userID")
    private int userID;

    @SerializedName("unionID")
    private String unionID;

    @SerializedName("key")
    private String key;

    @SerializedName("ip")
    private String ip;

    @SerializedName("port")
    private int port;

    @SerializedName("webPort")
    private String webPort;

    /**
     * @return the userID
     */
    public int getUserID()
    {
        return userID;
    }

    /**
     * @param userID
     *            the userID to set
     */
    public void setUserID(int userID)
    {
        this.userID = userID;
    }

    /**
     * @return the unionID
     */
    public String getUnionID()
    {
        return unionID;
    }

    /**
     * @param unionID
     *            the unionID to set
     */
    public void setUnionID(String unionID)
    {
        this.unionID = unionID;
    }

    /**
     * @return the key
     */
    public String getKey()
    {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(String key)
    {
        this.key = key;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public String getWebPort()
    {
        return webPort;
    }

    public void setWebPort(String webPort)
    {
        this.webPort = webPort;
    }
}
