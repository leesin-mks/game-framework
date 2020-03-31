/*
 * GlobalConfigManager
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.util.StringUtil;

/**
 * @author jacken
 *
 */
public class GlobalConfigManager
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalConfigManager.class);

    /** 配置文件根Document */
    private Document documentRoot = null;

    private ServerConfig serverConfig;
    private WebServerConfig webServerConfig;
    private RequestConfig requestConfig;
    private Map<String, String> webResourceConfig = null;

    public static final String PATH_DATABASE = "/config/database";
    public static final String PATH_SERVER = "/config/server";
    public static final String PATH_FIGHT = "/config/fightServer";
    public static final String PATH_REQUEST = "/config/request";
    public static final String PATH_WEB = "/config/web";
    public static final String PATH_CHAT = "config/chat";
    public static final String PATH_WEBRESOURCE = "config/webresource";

    private static final class LazyLoader
    {
        private static final GlobalConfigManager INSTANCE = new GlobalConfigManager();
    }

    /**
     * 获取实例
     * 
     * @return
     */
    public static GlobalConfigManager getInstance()
    {
        return LazyLoader.INSTANCE;
    }

    /**
     * 初始化配置，使用ConfigWrapper前必须先初始化。
     * 
     * @param path
     *            配置文件路径
     * @return
     */
    public boolean init(String path)
    {
        if (StringUtil.isNullOrEmpty(path))
        {
            return false;
        }

        try
        {
            SAXReader reader = new SAXReader();
            documentRoot = reader.read(path);
            LOGGER.info("Init global config successfully.");
            return true;
        }
        catch (DocumentException e)
        {
            LOGGER.error("Init global config error.", e);
            return false;
        }
    }

    /**
     * 获取指定xpath的节点集合
     * 
     * @param xpath
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Element> getElements(String xpath)
    {
        return documentRoot.selectNodes(xpath);
    }

    /**
     * 获取单个节点。如果有多个符合条件的节点，则返回第一个。
     * 
     * @param xpath
     * @return
     */
    public Element getElement(String xpath)
    {
        return (Element) documentRoot.selectSingleNode(xpath);
    }

    public ServerConfig getServerConfig()
    {
        if (serverConfig == null)
        {
            synchronized (this)
            {
                if (serverConfig == null)
                {
                    serverConfig = new ServerConfig();
                    Element tag = getElement(PATH_SERVER);
                    serverConfig.setID(Integer.parseInt(tag.element("id").getTextTrim()));
                    serverConfig.setLanguagePath(tag.element("languagePath").getTextTrim());
                    serverConfig.setNameFilePath(tag.element("nameFilePath").getTextTrim());
                    serverConfig.setPort(tag.elementTextTrim("port"));
                    Element redis = (Element) tag.selectSingleNode("redis");
                    serverConfig.setRedisIP(redis.elementTextTrim("ip"));
                    serverConfig.setRedisPort(Integer.parseInt(redis.elementTextTrim("port")));
                    serverConfig.setRedisPWD(redis.elementTextTrim("password"));
                    serverConfig.setEditionPath(tag.elementTextTrim("editionPath"));
                    serverConfig.setVersionPath(tag.elementTextTrim("versionPath"));
                    serverConfig.setImagePath(tag.elementTextTrim("imgPath"));
                }
            }
        }
        return serverConfig;
    }

    public RequestConfig getRequestConfig()
    {
        if (requestConfig == null)
        {
            synchronized (this)
            {
                if (requestConfig == null)
                {
                    requestConfig = new RequestConfig();
                    Element tag = getElement(PATH_REQUEST);
                    if (tag.elementTextTrim("adminIP").isEmpty() == false)
                    {
                        String[] IPs = tag.elementTextTrim("adminIP").split(
                                "\\|");
                        requestConfig.setAdminIPs(IPs);
                    }
                }
            }
        }
        return requestConfig;
    }

    public WebServerConfig getWebServerConfig()
    {
        if (webServerConfig == null)
        {
            synchronized (this)
            {
                if (webServerConfig == null)
                {
                    webServerConfig = new WebServerConfig();
                    Element tag = getElement(PATH_WEB);
                    webServerConfig.setPort(Integer.parseInt(tag.attributeValue("port")));
                    webServerConfig.setResourcePath(tag.attributeValue("resourcePath"));
                    webServerConfig.setPackages(tag.attributeValue("packages"));
                }
            }
        }
        return webServerConfig;
    }

    public Map<String, String> getWebResourceConfig()
    {
        if (webResourceConfig == null)
        {
            synchronized (this)
            {
                if (webResourceConfig == null)
                {
                    webResourceConfig = new HashMap<String, String>();
                    Element tag = getElement(PATH_WEBRESOURCE);
                    webResourceConfig.put("ip", tag.attributeValue("ip"));
                    webResourceConfig.put("port", tag.attributeValue("port"));
                }
            }
        }
        return webResourceConfig;
    }
}
