/*
 * CreateLoginRevBean
 *
 * 2016年2月24日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.object;

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
