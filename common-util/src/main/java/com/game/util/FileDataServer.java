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

/**
 * Date: 2014-2-12
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.game.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 文件存储数据
 * 
 * @author leesin
 */
public class FileDataServer
{
    public static final String LAST_NAME = ".xml";// 文件后缀名
    public static final String ROOT_PATH = "/data/";// 文件根路径
    public static final String PATH = "data/";// 文件路径
    public static final String COPY_PATH = "copy/";// 副本文件路径(用于文件更新时临时使用)
    public static final String LOGPATH = "/log/";// 日志文件路径

    /**
     * 创建xml
     * 
     * @param path
     * @return
     */
    public static String build(String path, String fileName, List<?> objs)
    {
        boolean value = false;
        String message = "Fail!";
        Document result = DocumentHelper.createDocument();
        Element element = result.addElement("Result");
        try
        {
            if (objs != null && objs.size() > 0)
            {

                for (Object obj : objs)
                {
                    if (!createInfoXMLConfig(element, obj))
                    {
                        value = false;
                        message = "Fail!";
                        break;
                    }
                }
            }
            value = true;
            message = "Success!";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        element.addAttribute("value", String.valueOf(value));
        element.addAttribute("message", message);

        return CompressUtil.createCompressFile(path, result.asXML(), fileName,
                false, message, ".xml");
    }

    public static void buildLog(String path, String fileName, List<?> objs)
    {
        boolean value = false;
        String message = "Fail!";
        Document result = DocumentHelper.createDocument();
        Element element = result.addElement("Result");
        try
        {
            if (objs != null && objs.size() > 0)
            {

                for (Object obj : objs)
                {
                    if (!createInfoXMLConfig(element, obj))
                    {
                        value = false;
                        message = "Fail!";
                        break;
                    }
                }
            }
            value = true;
            message = "Success!";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        element.addAttribute("time", TimeUtil.getDateFormat(TimeUtil.getCurrentDate()));
        element.addAttribute("value", String.valueOf(value));
        element.addAttribute("message", message);

        CompressUtil.setCompressXml(path, result.asXML(), fileName, false, message);
    }

    /**
     * 添加单个XML结点
     * 
     * @param element
     * @param info
     */
    public static <T> boolean createInfoXMLConfig(Element element, T info)
    {
        Element item = element.addElement("Item");
        try
        {
            /**
             * 取所有public字段的属性值(目前主要是因为EquipBean和PropBean属于子类，所以只能让父类属
             * 性为public才能通过这种方式取到父类属性相关)
             */
            Field[] filFields = info.getClass().getFields();
            for (Field field : filFields)
            {
                item.addAttribute(field.getName(), field.get(info).toString());
            }

            /**
             * 取所有当前类的属性值包括private字段
             */
            filFields = info.getClass().getDeclaredFields();
            for (Field field : filFields)
            {
                field.setAccessible(true);
                Object value = field.get(info);
                item.addAttribute(field.getName(),
                        value != null ? value.toString() : null);
            }
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 文件夹拷贝
     * 
     * @param dataPath
     *            目标文件夹路径
     * @param copyPath
     *            拷贝到文件夹路径
     */
    public static void copyFolder(String dataPath, String copyPath)
    {
        try
        {
            File file = new File(dataPath);
            String[] files = file.list();
            File temp = null;
            if (null != files)
            {
                for (int i = 0; i < files.length; i++)
                {
                    String fileName = files[i];
                    String prefix = fileName.substring(fileName.lastIndexOf("."));
                    if (LAST_NAME.equals(prefix))
                    {
                        if (dataPath.endsWith(File.separator))
                        {
                            temp = new File(dataPath + fileName);
                        }
                        else
                        {
                            temp = new File(dataPath + File.separator
                                    + fileName);
                        }
                        if (temp.isFile())
                        {
                            FileInputStream input = new FileInputStream(temp);
                            FileOutputStream output = new FileOutputStream(
                                    copyPath + "/"
                                            + (temp.getName()).toString());
                            byte[] b = new byte[1024 * 5];
                            int len;
                            while ((len = input.read(b)) != -1)
                            {
                                output.write(b, 0, len);
                            }
                            output.flush();
                            output.close();
                            input.close();
                        }
                        // 如果是子文件夹
                        if (temp.isDirectory())
                        {
                            copyFolder(dataPath + "/" + fileName, copyPath
                                    + "/" + fileName);
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
