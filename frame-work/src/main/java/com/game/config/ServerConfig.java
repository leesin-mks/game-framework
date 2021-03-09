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
 * @author leesin
 *
 */
public class ServerConfig
{
    /** 服务器ID */
    private int ID;
    private String port;

    /** 语言文件路径 */
    private String languagePath;
    /** GM指令是否开启 */
    private String nameFilePath;

    private String redisIP;

    private int redisPort;

    private String redisPWD;

    private String versionPath;
    
    private String imagePath;

    /**
     * 版本配置路径
     */
    private String editionPath;

    /**
     * 获取服务器ID。
     * 
     * @return the ID
     */
    public int getID()
    {
        return ID;
    }

    /**
     * 设置服务器ID
     * 
     * @param ID
     *            the iD to set
     */
    public void setID(int ID)
    {
        this.ID = ID;
    }

    /**
     * 获取文件路径。
     * 
     * @return the languagePath
     */
    public String getLanguagePath()
    {
        return languagePath;
    }

    /**
     * 设置文件路径。
     * 
     * @param languagePath
     *            the languagePath to set
     */
    public void setLanguagePath(String languagePath)
    {
        this.languagePath = languagePath;
    }

    /**
     * 获取服务器TCP监听端口。
     * 
     * @return the port
     */
    public String getPort()
    {
        return port;
    }

    /**
     * 设置服务器TCP监听端口。(支持多端口, 端口之间逗号分隔)
     * 
     * @param port
     *            the ports to set
     */
    public void setPort(String port)
    {
        this.port = port;
    }

    /**
     * @return the editionPath
     */
    public String getEditionPath()
    {
        return editionPath;
    }

    /**
     * @param editionPath
     *            the editionPath to set
     */
    public void setEditionPath(String editionPath)
    {
        this.editionPath = editionPath;
    }

    public String getRedisIP()
    {
        return redisIP;
    }

    public void setRedisIP(String redisIP)
    {
        this.redisIP = redisIP;
    }

    public int getRedisPort()
    {
        return redisPort;
    }

    public void setRedisPort(int redisPort)
    {
        this.redisPort = redisPort;
    }

    public String getRedisPWD()
    {
        return redisPWD;
    }

    public void setRedisPWD(String redisPWD)
    {
        this.redisPWD = redisPWD;
    }

    /**
     * @return the nameFilePath
     */
    public String getNameFilePath()
    {
        return nameFilePath;
    }

    /**
     * @param nameFilePath
     *            the nameFilePath to set
     */
    public void setNameFilePath(String nameFilePath)
    {
        this.nameFilePath = nameFilePath;
    }

    public String getVersionPath()
    {
        return versionPath;
    }

    public void setVersionPath(String versionPath)
    {
        this.versionPath = versionPath;
    }

    /**
     * @return the imagePath
     */
    public String getImagePath()
    {
        return imagePath;
    }

    /**
     * @param imagePath the imagePath to set
     */
    public void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
    }

}
