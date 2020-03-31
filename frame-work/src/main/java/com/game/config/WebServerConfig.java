/*
 * WebServerConfig
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.config;

/**
 * @author jacken
 *
 */
public class WebServerConfig
{
    /**
     * 服务器IP
     */
    private String ip;
    /**
     * servlet的端口
     */
    private int port;

    private String packages;

    /**
     * web资源路径
     */
    private String resourcePath;

    private String url = null;

    /**
     * @return the port
     */
    public int getPort()
    {
        return port;
    }

    /**
     * @param port
     *            the port to set
     */
    public void setPort(int port)
    {
        this.port = port;
    }

    /**
     * 需要加载的包路径，多路径用","分隔
     * 
     * @return the packages
     */
    public String getPackages()
    {
        return packages;
    }

    /**
     * 需要加载的包路径，多路径用","分隔
     * 
     * @param packages
     *            the packages to set
     */
    public void setPackages(String packages)
    {
        this.packages = packages;
    }

    /**
     * @return the resourcePath
     */
    public String getResourcePath()
    {
        return resourcePath;
    }

    /**
     * @param resourcePath
     *            the resourcePath to set
     */
    public void setResourcePath(String resourcePath)
    {
        this.resourcePath = resourcePath;
    }

    /**
     * @return the ip
     */
    public String getIp()
    {
        return ip;
    }

    /**
     * @param ip
     *            the ip to set
     */
    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getUrl()
    {
        if (this.url == null)
        {
            this.url = "http://" + this.ip + ":" + port;
        }
        return this.url;
    }
}
