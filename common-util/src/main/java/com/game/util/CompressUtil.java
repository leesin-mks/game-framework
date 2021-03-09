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
 * All rights reserved. This material is confidential and proprietary to 7ROAD
 */
package com.game.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leesin
 * 
 */
public class CompressUtil
{
    private static final Logger log = LoggerFactory.getLogger(CompressUtil.class.getName());

    /**
     * 压缩数据
     * 
     * @param str
     * @return
     */
    public static byte[] compress(String str)
    {
        byte[] input;
        try
        {
            byte[] output = null;
            input = str.getBytes("UTF-8");

            Deflater compresser = new Deflater();
            compresser.setInput(input);
            compresser.finish();
            ByteArrayOutputStream bos = new ByteArrayOutputStream(input.length);
            byte[] buf = new byte[1024 * 10];
            while (!compresser.finished())
            {
                int i = compresser.deflate(buf);
                bos.write(buf, 0, i);
            }
            output = bos.toByteArray();

            IOUtil.closeIO(bos);
            compresser.end();
            return output;
        }
        catch (UnsupportedEncodingException e)
        {
            log.error("CompressUtil:compress", e);
            return null;
        }
    }

    public static byte[] compress(byte[] src, int start, int len)
    {
        byte[] input = new byte[len];
        System.arraycopy(src, start, input, 0, len);

        byte[] output = null;

        Deflater compresser = new Deflater();
        compresser.setInput(input);
        compresser.finish();
        ByteArrayOutputStream bos = new ByteArrayOutputStream(input.length);
        byte[] buf = new byte[1024 * 10];
        while (!compresser.finished())
        {
            int i = compresser.deflate(buf);
            bos.write(buf, 0, i);
        }
        output = bos.toByteArray();

        try
        {
            bos.close();
        }
        catch (IOException e)
        {
            log.error("CompressUtil:compress", e);
        }
        compresser.end();
        return output;
    }

    /**
     * 创建压缩XML文件
     * 
     * @param path
     *            文件路径
     * @param str
     *            xml字符串
     * @param fileName
     *            文件名称
     * @param isCompress
     *            是否压缩
     * @param message
     *            是否成功消息
     * @return
     */
    public static String createCompressFile(String path, String str,
            String fileName, boolean isCompress, String message,
            String fileSuffix)
    {
        String filePath = path + fileName + fileSuffix;
        try
        {
            File myFilePath = new File(filePath);

            myFilePath.delete();
            myFilePath.createNewFile();

            FileOutputStream writer = new FileOutputStream(myFilePath);
            writer.write(isCompress ? compress(str) : str.getBytes(StandardCharsets.UTF_8));
            writer.close();

            return "Build:" + filePath + "------ " + message;
        }
        catch (IOException e)
        {
            log.error("CreateCompressFile " + filePath + " is fail!", e);
            return "Build:" + filePath + "------ Fail!";
        }
    }

    /**
     * 向XML文件中追加内容
     * 
     * @param path
     *            文件路径
     * @param str
     *            xml字符串
     * @param fileName
     *            文件名称
     * @param isCompress
     *            是否压缩
     * @param message
     *            是否成功消息
     * @return
     */
    public static void setCompressXml(String path, String str, String fileName,
            boolean isCompress, String message)
    {
        String filePath = path + fileName;
        try
        {
            // FileWriter writer = new FileWriter(filePath, true);
            // writer.write(str);
            // writer.close();
            RandomAccessFile file = new RandomAccessFile(filePath, "rw");
            long length = file.length();
            int index = 0;
            if (length > 0)
                index = 38;
            file.seek(length);
            file.writeBytes(str.substring(index));
            file.close();
        }
        catch (IOException e)
        {
            log.error("CreateCompressXml " + filePath + " is fail!", e);
        }
    }

    /**
     * 解压缩数据
     * 
     * @param compressed
     * @return
     */
    public static String decompress(byte[] compressed)
    {
        try
        {
            Inflater deCompress = new Inflater();
            deCompress.setInput(compressed, 0, compressed.length);
            byte[] result = new byte[1024];
            int resultLength;

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (!deCompress.finished())
            {
                resultLength = deCompress.inflate(result);
                byteArrayOutputStream.write(result, 0, resultLength);
            }
            deCompress.end();
            return new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
        }
        catch (DataFormatException e)
        {
            log.error("CompressUtil:decompress: ", e);
        }
        return null;
    }

    public static void main(String[] t)
    {

        // String ss = decompress(compress("<xml>test</xml>"));
        // System.out.println(ss);

        // Document result = DocumentHelper.createDocument();
        // Element element = result.addElement("Result");
        // element.addAttribute("test", "dsssd");
        // element.addAttribute("ids", "12df3");
        // createCompressXml("D:\\", result, "test", false, "success");

        FileInputStream fis;
        try
        {
            fis = new FileInputStream(
                    "E:\\LinuxDDT\\Server\\src\\Workspace\\Request\\WebContent\\DropItemForNewRegister.xml");
            byte[] b1 = new byte[fis.available()];
            fis.read(b1);
            String dString = decompress(b1);
            System.out.println(dString);
        }
        catch (IOException e)
        {
            log.error("CompressUtil:main", e);
        }
    }
}
