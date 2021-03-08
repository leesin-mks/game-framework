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
