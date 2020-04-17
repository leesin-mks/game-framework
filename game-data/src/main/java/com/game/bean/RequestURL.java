/*
 * RequestURL
 *
 * 2018年10月19日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
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
