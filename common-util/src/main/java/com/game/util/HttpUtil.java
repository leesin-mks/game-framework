/*
 * HttpUtil
 *
 * 2016年2月19日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jacken
 *
 */
public class HttpUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class.getName());

    /**
     * 连接超时
     */
    private static int connectTimeOut = 5000;

    /**
     * 读取数据超时
     */
    private static int readTimeOut = 10000;

    /**
     * 请求编码
     */
    private static String requestEncoding = "UTF-8";

    /**
     * @return 连接超时(毫秒)
     * @see com.hengpeng.common.web.HttpRequestProxy#connectTimeOut
     */
    public static int getConnectTimeOut()
    {
        return HttpUtil.connectTimeOut;
    }

    /**
     * @return 读取数据超时(毫秒)
     * @see com.hengpeng.common.web.HttpRequestProxy#readTimeOut
     */
    public static int getReadTimeOut()
    {
        return HttpUtil.readTimeOut;
    }

    /**
     * @return 请求编码
     * @see com.hengpeng.common.web.HttpRequestProxy#requestEncoding
     */
    public static String getRequestEncoding()
    {
        return requestEncoding;
    }

    /**
     * @param connectTimeOut
     *            连接超时(毫秒)
     * @see com.hengpeng.common.web.HttpRequestProxy#connectTimeOut
     */
    public static void setConnectTimeOut(int connectTimeOut)
    {
        HttpUtil.connectTimeOut = connectTimeOut;
    }

    /**
     * @param readTimeOut
     *            读取数据超时(毫秒)
     * @see com.hengpeng.common.web.HttpRequestProxy#readTimeOut
     */
    public static void setReadTimeOut(int readTimeOut)
    {
        HttpUtil.readTimeOut = readTimeOut;
    }

    /**
     * @param requestEncoding
     *            请求编码
     * @see com.hengpeng.common.web.HttpRequestProxy#requestEncoding
     */
    public static void setRequestEncoding(String requestEncoding)
    {
        HttpUtil.requestEncoding = requestEncoding;
    }

    /**
     * <pre>
     * 发送带参数的GET的HTTP请求
     * </pre>
     * 
     * @param reqUrl
     *            HTTP请求URL
     * @param parameters
     *            参数映射表
     * @return HTTP响应的字符串
     */
    public static String doGet(String reqUrl, Map<?, ?> parameters,
            String recvEncoding)
    {
        HttpURLConnection url_con = null;
        String responseContent = null;
        try
        {
            StringBuffer params = new StringBuffer();
            for (Iterator<?> iter = parameters.entrySet().iterator(); iter.hasNext();)
            {
                Entry<?, ?> element = (Entry<?, ?>) iter.next();
                params.append(element.getKey().toString());
                params.append("=");
                params.append(URLEncoder.encode(element.getValue().toString(),
                        HttpUtil.requestEncoding));
                params.append("&");
            }

            if (params.length() > 0)
            {
                params = params.deleteCharAt(params.length() - 1);
            }

            URL url = new URL(reqUrl);
            url_con = (HttpURLConnection) url.openConnection();
            url_con.setRequestMethod("GET");
            System.setProperty("sun.net.client.defaultConnectTimeout",
                    String.valueOf(HttpUtil.connectTimeOut));// （单位：毫秒）jdk1.4换成这个,连接超时
            System.setProperty("sun.net.client.defaultReadTimeout",
                    String.valueOf(HttpUtil.readTimeOut)); // （单位：毫秒）jdk1.4换成这个,读操作超时
            // url_con.setConnectTimeout(5000);//（单位：毫秒）jdk
            // 1.5换成这个,连接超时
            // url_con.setReadTimeout(5000);//（单位：毫秒）jdk 1.5换成这个,读操作超时
            url_con.setDoOutput(true);
            byte[] b = params.toString().getBytes();
            url_con.getOutputStream().write(b, 0, b.length);
            url_con.getOutputStream().flush();
            url_con.getOutputStream().close();

            InputStream in = url_con.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in,
                    recvEncoding));
            String tempLine = rd.readLine();
            StringBuffer temp = new StringBuffer();
            // String crlf = System.getProperty("line.separator");
            while (tempLine != null)
            {
                temp.append(tempLine);
                tempLine = rd.readLine();
            }
            responseContent = temp.toString();
            rd.close();
            in.close();
        }
        catch (IOException e)
        {
            LOGGER.error("当前请求地址错误：", e);
        }
        finally
        {
            if (url_con != null)
            {
                url_con.disconnect();
            }
        }

        return responseContent;
    }

    /**
     * <pre>
     * 发送带参数的GET的HTTP请求
     * </pre>
     * 
     * @param reqUrl
     *            HTTP请求URL
     * @param parameters
     *            参数映射表
     * @return HTTP响应的字符串
     */
    public static String doGet(String reqUrl, Map<?, ?> parameters,
            String recvEncoding, String requestEncodString)
    {
        HttpURLConnection url_con = null;
        String responseContent = null;
        try
        {
            StringBuffer params = new StringBuffer();
            for (Iterator<?> iter = parameters.entrySet().iterator(); iter.hasNext();)
            {
                Entry<?, ?> element = (Entry<?, ?>) iter.next();
                params.append(element.getKey().toString());
                params.append("=");
                params.append(URLEncoder.encode(element.getValue().toString(),
                        requestEncodString));
                params.append("&");
            }

            if (params.length() > 0)
            {
                params = params.deleteCharAt(params.length() - 1);
            }

            URL url = new URL(reqUrl);
            url_con = (HttpURLConnection) url.openConnection();
            url_con.setRequestMethod("GET");
            System.setProperty("sun.net.client.defaultConnectTimeout",
                    String.valueOf(HttpUtil.connectTimeOut));// （单位：毫秒）jdk1.4换成这个,连接超时
            System.setProperty("sun.net.client.defaultReadTimeout",
                    String.valueOf(HttpUtil.readTimeOut)); // （单位：毫秒）jdk1.4换成这个,读操作超时
            // url_con.setConnectTimeout(5000);//（单位：毫秒）jdk
            // 1.5换成这个,连接超时
            // url_con.setReadTimeout(5000);//（单位：毫秒）jdk 1.5换成这个,读操作超时
            url_con.setDoOutput(true);
            byte[] b = params.toString().getBytes();
            url_con.getOutputStream().write(b, 0, b.length);
            url_con.getOutputStream().flush();
            url_con.getOutputStream().close();

            InputStream in = url_con.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in,
                    recvEncoding));
            String tempLine = rd.readLine();
            StringBuffer temp = new StringBuffer();
            // String crlf = System.getProperty("line.separator");
            while (tempLine != null)
            {
                temp.append(tempLine);
                tempLine = rd.readLine();
            }
            responseContent = temp.toString();
            rd.close();
            in.close();
        }
        catch (IOException e)
        {
            // LOGGER.error("当前请求地址错误：", e);
        }
        finally
        {
            if (url_con != null)
            {
                url_con.disconnect();
            }
        }

        return responseContent;
    }

    /**
     * <pre>
     * 发送不带参数的GET的HTTP请求
     * </pre>
     * 
     * @param reqUrl
     *            HTTP请求URL
     * @return HTTP响应的字符串
     */
    public static String doGet(String reqUrl, String recvEncoding)
    {
        HttpURLConnection url_con = null;
        String responseContent = null;
        try
        {
            StringBuffer params = new StringBuffer();
            String queryUrl = reqUrl;
            int paramIndex = reqUrl.indexOf("?");

            if (paramIndex > 0)
            {
                queryUrl = reqUrl.substring(0, paramIndex);
                String parameters = reqUrl.substring(paramIndex + 1,
                        reqUrl.length());
                String[] paramArray = parameters.split("&");
                for (int i = 0; i < paramArray.length; i++)
                {
                    String string = paramArray[i];
                    int index = string.indexOf("=");
                    if (index > 0)
                    {
                        String parameter = string.substring(0, index);
                        String value = string.substring(index + 1,
                                string.length());
                        params.append(parameter);
                        params.append("=");
                        params.append(URLEncoder.encode(value,
                                HttpUtil.requestEncoding));
                        params.append("&");
                    }
                }

                params = params.deleteCharAt(params.length() - 1);
            }

            URL url = new URL(queryUrl);
            url_con = (HttpURLConnection) url.openConnection();
            url_con.setRequestMethod("GET");
            System.setProperty("sun.net.client.defaultConnectTimeout",
                    String.valueOf(HttpUtil.connectTimeOut));// （单位：毫秒）jdk1.4换成这个,连接超时
            System.setProperty("sun.net.client.defaultReadTimeout",
                    String.valueOf(HttpUtil.readTimeOut)); // （单位：毫秒）jdk1.4换成这个,读操作超时
            // url_con.setConnectTimeout(5000);//（单位：毫秒）jdk
            // 1.5换成这个,连接超时
            // url_con.setReadTimeout(5000);//（单位：毫秒）jdk 1.5换成这个,读操作超时
            url_con.setDoOutput(true);
            byte[] b = params.toString().getBytes();
            url_con.getOutputStream().write(b, 0, b.length);
            url_con.getOutputStream().flush();
            url_con.getOutputStream().close();
            InputStream in = url_con.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in,
                    recvEncoding));
            String tempLine = rd.readLine();
            StringBuffer temp = new StringBuffer();
            String crlf = System.getProperty("line.separator");
            while (tempLine != null)
            {
                temp.append(tempLine);
                temp.append(crlf);
                tempLine = rd.readLine();
            }
            responseContent = temp.toString();
            rd.close();
            in.close();
        }
        catch (IOException e)
        {
            LOGGER.error(e.getMessage());
        }
        finally
        {
            if (url_con != null)
            {
                url_con.disconnect();
            }
        }

        return responseContent;
    }

    public static byte[] doGetFileByte(String reqUrl, String recvEncoding)
    {
        HttpURLConnection url_con = null;
        byte[] data = null;
        try
        {
            String queryUrl = reqUrl;
            URL url = new URL(queryUrl);
            url_con = (HttpURLConnection) url.openConnection();
            url_con.setRequestMethod("GET");
            System.setProperty("sun.net.client.defaultConnectTimeout",
                    String.valueOf(HttpUtil.connectTimeOut));// （单位：毫秒）jdk1.4换成这个,连接超时
            System.setProperty("sun.net.client.defaultReadTimeout",
                    String.valueOf(HttpUtil.readTimeOut)); // （单位：毫秒）jdk1.4换成这个,读操作超时
            url_con.connect();
            InputStream in = url_con.getInputStream();
            data = readStream(in);
            in.close();
        }
        catch (IOException e)
        {
            LOGGER.error(e.getMessage());
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
        }
        finally
        {
            if (url_con != null)
            {
                url_con.disconnect();
            }
        }
        return data;
    }

    public static byte[] readStream(InputStream inStream) throws Exception
    {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inStream.read(buffer)) != -1)
        {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }

    /**
     * <pre>
     * 下载文件信息到指定路径
     * </pre>
     * 
     * @param reqUrl
     *            HTTP请求URL
     * @return HTTP响应的字符串
     */
    public static String doGet(String reqUrl, String recvEncoding, String path)
    {
        HttpURLConnection url_con = null;
        String responseContent = null;
        try
        {
            StringBuffer params = new StringBuffer();
            String queryUrl = reqUrl;
            int paramIndex = reqUrl.indexOf("?");

            if (paramIndex > 0)
            {
                queryUrl = reqUrl.substring(0, paramIndex);
                String parameters = reqUrl.substring(paramIndex + 1,
                        reqUrl.length());
                String[] paramArray = parameters.split("&");
                for (int i = 0; i < paramArray.length; i++)
                {
                    String string = paramArray[i];
                    int index = string.indexOf("=");
                    if (index > 0)
                    {
                        String parameter = string.substring(0, index);
                        String value = string.substring(index + 1,
                                string.length());
                        params.append(parameter);
                        params.append("=");
                        params.append(URLEncoder.encode(value,
                                HttpUtil.requestEncoding));
                        params.append("&");
                    }
                }

                params = params.deleteCharAt(params.length() - 1);
            }

            URL url = new URL(queryUrl);
            url_con = (HttpURLConnection) url.openConnection();
            url_con.setRequestMethod("GET");
            System.setProperty("sun.net.client.defaultConnectTimeout",
                    String.valueOf(HttpUtil.connectTimeOut));// （单位：毫秒）jdk1.4换成这个,连接超时
            System.setProperty("sun.net.client.defaultReadTimeout",
                    String.valueOf(HttpUtil.readTimeOut)); // （单位：毫秒）jdk1.4换成这个,读操作超时
            url_con.setDoOutput(true);
            byte[] b = params.toString().getBytes();
            url_con.getOutputStream().write(b, 0, b.length);
            url_con.getOutputStream().flush();
            url_con.getOutputStream().close();

            InputStream in = url_con.getInputStream();
            byte[] result = new byte[1024];
            in.read(result);

            File myFilePath = new File(path);
            myFilePath.delete();
            myFilePath.createNewFile();

            FileOutputStream writer = new FileOutputStream(myFilePath);
            writer.write(result);
            writer.close();
            in.close();
        }
        catch (IOException e)
        {
            LOGGER.error(e.getMessage());
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
        }
        finally
        {
            if (url_con != null)
            {
                url_con.disconnect();
            }
        }

        return responseContent;
    }

    /**
     * <pre>
     * 发送带参数的POST的HTTP请求
     * </pre>
     * 
     * @param reqUrl
     *            HTTP请求URL
     * @param parameters
     *            参数映射表
     * @return HTTP响应的字符串
     */
    public static String doPost(String reqUrl, Map<String, String> parameters,
            String recvEncoding)
    {
        HttpURLConnection url_con = null;
        String responseContent = "0";
        try
        {
            StringBuffer params = new StringBuffer();
            if (parameters != null)
            {
                for (Iterator<?> iter = parameters.entrySet().iterator(); iter.hasNext();)
                {
                    Entry<?, ?> element = (Entry<?, ?>) iter.next();
                    params.append(element.getKey().toString());
                    params.append("=");
                    params.append(URLEncoder.encode(element.getValue().toString(),
                            HttpUtil.requestEncoding));
                    params.append("&");
                }
            }

            if (params.length() > 0)
            {
                params = params.deleteCharAt(params.length() - 1);
            }

            URL url = new URL(reqUrl);
            url_con = (HttpURLConnection) url.openConnection();
            url_con.setRequestMethod("POST");
            System.setProperty("sun.net.client.defaultConnectTimeout",
                    String.valueOf(HttpUtil.connectTimeOut));// （单位：毫秒）jdk1.4换成这个,连接超时
            System.setProperty("sun.net.client.defaultReadTimeout",
                    String.valueOf(HttpUtil.readTimeOut)); // （单位：毫秒）jdk1.4换成这个,读操作超时
            url_con.setDoOutput(true);
            byte[] b = params.toString().getBytes();
            url_con.getOutputStream().write(b, 0, b.length);
            url_con.getOutputStream().flush();
            url_con.getOutputStream().close();

            InputStream in = url_con.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in,
                    recvEncoding));
            String tempLine = rd.readLine();
            StringBuffer tempStr = new StringBuffer();
            while (tempLine != null)
            {
                tempStr.append(tempLine);
                tempLine = rd.readLine();
            }
            responseContent = tempStr.toString();
            rd.close();
            in.close();
        }
        catch (IOException e)
        {
            LOGGER.error("HttpUtil:doPost", e);
        }
        catch (Exception e)
        {
            LOGGER.error("HttpUtil:doPost", e);
        }
        finally
        {
            if (url_con != null)
            {
                url_con.disconnect();
            }
        }
        return responseContent;
    }

    /**
     * 通过HTTP下载文档，保存
     * 
     * @param xmlUrl
     * @param path
     */
    public static void getUrlXmlFile(String xmlUrl, String path)
    {
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        byte[] buf = new byte[1024 * 10];
        int size = 0;
        try
        {
            url = new URL(xmlUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            // 连接指定的资源
            httpUrl.connect();
            // 获取网络输入流
            InputStream i = httpUrl.getInputStream();
            bis = new BufferedInputStream(i);
            // 建立文件
            fos = new FileOutputStream(path);

            // 保存文件
            while ((size = bis.read(buf)) != -1)
            {
                fos.write(buf, 0, size);
            }
            fos.close();
            bis.close();
            httpUrl.disconnect();

        }
        catch (MalformedURLException e)
        {
            LOGGER.error("HttpUtil:getUrlXmlFile", e);
        }
        catch (IOException e)
        {
            LOGGER.error("HttpUtil:getUrlXmlFile", e);
        }
    }

    /**
     * <pre>
     * 发送带参数的GET的HTTPS请求
     * </pre>
     * 
     * @param reqUrl
     *            HTTP请求URL
     * @param parameters
     *            参数映射表
     * @return HTTP响应的字符串
     */
    public static String doGetByHttps(String reqUrl, Map<?, ?> parameters,
            String recvEncoding)
    {
        HttpsURLConnection url_con = null;
        String responseContent = null;
        try
        {
            StringBuffer params = new StringBuffer();
            for (Iterator<?> iter = parameters.entrySet().iterator(); iter.hasNext();)
            {
                Entry<?, ?> element = (Entry<?, ?>) iter.next();
                params.append(element.getKey().toString());
                params.append("=");
                params.append(URLEncoder.encode(element.getValue().toString(),
                        HttpUtil.requestEncoding));
                params.append("&");
            }

            if (params.length() > 0)
            {
                params = params.deleteCharAt(params.length() - 1);
            }

            URL url = new URL(reqUrl);
            url_con = (HttpsURLConnection) url.openConnection();
            url_con.setRequestMethod("GET");
            System.setProperty("sun.net.client.defaultConnectTimeout",
                    String.valueOf(HttpUtil.connectTimeOut));// （单位：毫秒）jdk1.4换成这个,连接超时
            System.setProperty("sun.net.client.defaultReadTimeout",
                    String.valueOf(HttpUtil.readTimeOut)); // （单位：毫秒）jdk1.4换成这个,读操作超时
            // url_con.setConnectTimeout(5000);//（单位：毫秒）jdk
            // 1.5换成这个,连接超时
            // url_con.setReadTimeout(5000);//（单位：毫秒）jdk 1.5换成这个,读操作超时
            url_con.setDoOutput(true);
            byte[] b = params.toString().getBytes();
            url_con.getOutputStream().write(b, 0, b.length);
            url_con.getOutputStream().flush();
            url_con.getOutputStream().close();

            InputStream in = url_con.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in,
                    recvEncoding));
            String tempLine = rd.readLine();
            StringBuffer temp = new StringBuffer();
            // String crlf = System.getProperty("line.separator");
            while (tempLine != null)
            {
                temp.append(tempLine);
                tempLine = rd.readLine();
            }
            responseContent = temp.toString();
            rd.close();
            in.close();
        }
        catch (IOException e)
        {
            // LOGGER.error("当前请求地址错误：", e);
        }
        finally
        {
            if (url_con != null)
            {
                url_con.disconnect();
            }
        }

        return responseContent;
    }

    public static String doGetHttps(String reqUrl, String recvEncoding)
    {
        HttpsURLConnection url_con = null;
        String responseContent = null;
        try
        {
            StringBuffer params = new StringBuffer();
            String queryUrl = reqUrl;
            int paramIndex = reqUrl.indexOf("?");

            if (paramIndex > 0)
            {
                queryUrl = reqUrl.substring(0, paramIndex);
                String parameters = reqUrl.substring(paramIndex + 1,
                        reqUrl.length());
                String[] paramArray = parameters.split("&");
                for (int i = 0; i < paramArray.length; i++)
                {
                    String string = paramArray[i];
                    int index = string.indexOf("=");
                    if (index > 0)
                    {
                        String parameter = string.substring(0, index);
                        String value = string.substring(index + 1,
                                string.length());
                        params.append(parameter);
                        params.append("=");
                        params.append(URLEncoder.encode(value,
                                HttpUtil.requestEncoding));
                        params.append("&");
                    }
                }

                params = params.deleteCharAt(params.length() - 1);
            }

            URL url = new URL(queryUrl);
            url_con = (HttpsURLConnection) url.openConnection();
            url_con.setRequestMethod("GET");
            System.setProperty("sun.net.client.defaultConnectTimeout",
                    String.valueOf(HttpUtil.connectTimeOut));// （单位：毫秒）jdk1.4换成这个,连接超时
            System.setProperty("sun.net.client.defaultReadTimeout",
                    String.valueOf(HttpUtil.readTimeOut)); // （单位：毫秒）jdk1.4换成这个,读操作超时
            // url_con.setConnectTimeout(5000);//（单位：毫秒）jdk
            // 1.5换成这个,连接超时
            // url_con.setReadTimeout(5000);//（单位：毫秒）jdk 1.5换成这个,读操作超时
            url_con.setDoOutput(true);
            byte[] b = params.toString().getBytes();
            url_con.getOutputStream().write(b, 0, b.length);
            url_con.getOutputStream().flush();
            url_con.getOutputStream().close();
            InputStream in = url_con.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in,
                    recvEncoding));
            String tempLine = rd.readLine();
            StringBuffer temp = new StringBuffer();
            String crlf = System.getProperty("line.separator");
            while (tempLine != null)
            {
                temp.append(tempLine);
                temp.append(crlf);
                tempLine = rd.readLine();
            }
            responseContent = temp.toString();
            rd.close();
            in.close();
        }
        catch (IOException e)
        {
            LOGGER.error(e.getMessage());
        }
        finally
        {
            if (url_con != null)
            {
                url_con.disconnect();
            }
        }

        return responseContent;
    }

    /**
     * <pre>
     * 发送带参数的POST的HTTPS请求
     * </pre>
     * 
     * @param reqUrl
     *            HTTP请求URL
     * @param parameters
     *            参数映射表
     * @return HTTP响应的字符串
     */
    public static String doPostByHttps(String reqUrl,
            Map<String, String> parameters, String recvEncoding)
    {
        HttpsURLConnection url_con = null;
        String responseContent = null;
        try
        {
            StringBuffer params = new StringBuffer();
            if (parameters != null)
            {
                for (Iterator<?> iter = parameters.entrySet().iterator(); iter.hasNext();)
                {
                    Entry<?, ?> element = (Entry<?, ?>) iter.next();
                    params.append(element.getKey().toString());
                    params.append("=");
                    params.append(URLEncoder.encode(element.getValue().toString(),
                            HttpUtil.requestEncoding));
                    params.append("&");
                }
            }

            if (params.length() > 0)
            {
                params = params.deleteCharAt(params.length() - 1);
            }

            URL url = new URL(reqUrl);
            url_con = (HttpsURLConnection) url.openConnection();
            url_con.setRequestMethod("POST");
            System.setProperty("sun.net.client.defaultConnectTimeout",
                    String.valueOf(HttpUtil.connectTimeOut));// （单位：毫秒）jdk1.4换成这个,连接超时
            System.setProperty("sun.net.client.defaultReadTimeout",
                    String.valueOf(HttpUtil.readTimeOut)); // （单位：毫秒）jdk1.4换成这个,读操作超时
            // url_con.setConnectTimeout(5000);//（单位：毫秒）jdk
            // 1.5换成这个,连接超时
            // url_con.setReadTimeout(5000);//（单位：毫秒）jdk 1.5换成这个,读操作超时
            url_con.setDoOutput(true);
            byte[] b = params.toString().getBytes();
            url_con.getOutputStream().write(b, 0, b.length);
            url_con.getOutputStream().flush();
            url_con.getOutputStream().close();

            InputStream in = url_con.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in,
                    recvEncoding));
            String tempLine = rd.readLine();
            StringBuffer tempStr = new StringBuffer();
            // String crlf = System.getProperty("line.separator");
            while (tempLine != null)
            {
                tempStr.append(tempLine);
                // tempStr.append(crlf);
                tempLine = rd.readLine();
            }
            responseContent = tempStr.toString();
            rd.close();
            in.close();
        }
        catch (IOException e)
        {
            LOGGER.error("HttpUtil:doPostByHttps", e);
        }
        catch (Exception e)
        {
            LOGGER.error("HttpUtil:doPostByHttps", e);
        }
        finally
        {
            if (url_con != null)
            {
                url_con.disconnect();
            }
        }
        return responseContent;
    }

    public static String doPosts(String reqUrl, Map<String, String> parameters,
            String recvEncoding)
    {
        HttpsURLConnection url_con = null;
        String responseContent = "0";
        try
        {
            //
            TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager()
            {
                public java.security.cert.X509Certificate[] getAcceptedIssuers()
                {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs,
                        String authType)
                {
                }

                public void checkServerTrusted(X509Certificate[] certs,
                        String authType)
                {
                }

            } };
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            //
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HostnameVerifier allHostsValid = new HostnameVerifier()
            {
                public boolean verify(String hostname, SSLSession session)
                {
                    return true;
                }
            };
            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
            // 进行url编码

            //
            String params = sortParam(parameters);

            URL url = new URL(reqUrl);
            url_con = (HttpsURLConnection) url.openConnection();
            url_con.setRequestMethod("POST");
            System.setProperty("sun.net.client.defaultConnectTimeout",
                    String.valueOf(HttpUtil.connectTimeOut));// （单位：毫秒）jdk1.4换成这个,连接超时
            System.setProperty("sun.net.client.defaultReadTimeout",
                    String.valueOf(HttpUtil.readTimeOut)); // （单位：毫秒）jdk1.4换成这个,读操作超时
            url_con.setDoOutput(true);
            byte[] b = params.toString().getBytes();
            url_con.getOutputStream().write(b, 0, b.length);
            url_con.getOutputStream().flush();
            url_con.getOutputStream().close();

            InputStream in = url_con.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in,
                    recvEncoding));
            String tempLine = rd.readLine();
            StringBuffer tempStr = new StringBuffer();

            while (tempLine != null)
            {
                tempStr.append(tempLine);
                tempLine = rd.readLine();
            }

            responseContent = tempStr.toString();
            rd.close();
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (url_con != null)
            {
                url_con.disconnect();
            }
        }
        return responseContent;
    }

    // 对参数进行编码
    public static String sortParam(Map<String, String> parameters)
            throws UnsupportedEncodingException
    {
        StringBuilder listParam = new StringBuilder("");
        List<String> keys = new ArrayList<String>();

        for (String key : parameters.keySet())
        {
            keys.add(key);
        }

        Collections.sort(keys);

        for (String key : keys)
        {
            if (!key.equals("sig"))
            {
                // 出sig以外都需要进行url编码
                String value = URLEncoder.encode(parameters.get(key), "utf-8");
                listParam.append(key).append("=").append(value).append('&');
            }
            else
            {
                listParam.append(key).append("=").append(parameters.get(key)).append('&');
            }
        }
        return listParam.substring(0, listParam.length() - 1);
    }

    /**
     * 转发表单内容
     * 
     * @param reqUrl
     * @param file
     * @return
     */
    public static String uploadFile(String reqUrl, byte[] file)
    {
        HttpURLConnection httpURLConnection = null;
        DataOutputStream dos = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String responseContent = "0";
        String boundary = "*****";

        try
        {
            URL url = new URL(reqUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");     // 设置是否从httpUrlConnection读入，默认情况下是true;
            httpURLConnection.setDoInput(true);   // 设置是否向httpUrlConnection输出
            httpURLConnection.setDoOutput(true);  // Post 请求不能使用缓存
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(HttpUtil.connectTimeOut));   // （单位：毫秒）jdk1.4换成这个,连接超时
            System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(5000));                         // （单位：毫秒）jdk1.4换成这个,读操作超时
            dos = new DataOutputStream(httpURLConnection.getOutputStream());
            dos.write(file, 0, file.length);
            dos.flush();
            dos.close();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                reader = new BufferedReader(inputStreamReader);
                String tempLine = null;
                resultBuffer = new StringBuffer();
                while ((tempLine = reader.readLine()) != null)
                {
                    resultBuffer.append(tempLine);
                    resultBuffer.append("\n");
                }
                responseContent = resultBuffer.toString();
            }

        }
        catch (IOException e)
        {
            LOGGER.error("HttpUtil:doPost", e);
        }
        catch (Exception e)
        {
            LOGGER.error("HttpUtil:doPost", e);
        }
        finally
        {
            if (dos != null)
            {
                try
                {
                    dos.close();
                }
                catch (IOException e)
                {
                    LOGGER.error("Close dos error: ", e);
                }
            }
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    LOGGER.error("Close read error: ", e);
                }
            }
            if (inputStream != null)
            {
                try
                {
                    inputStream.close();
                }
                catch (IOException e)
                {
                    LOGGER.error("Close is error: ", e);
                }
            }
            if (inputStreamReader != null)
            {
                try
                {
                    inputStreamReader.close();
                }
                catch (IOException e)
                {
                    LOGGER.error("Close isr error: ", e);
                }
            }
            if (httpURLConnection != null)
            {
                httpURLConnection.disconnect();
            }
        }
        return responseContent;
    }

    /**
     * 转发表单内容
     * 
     * @param reqUrl
     * @param file
     * @return
     */
    public static byte[] doPostByByte(String reqUrl, byte[] file)
    {
        HttpURLConnection httpURLConnection = null;
        DataOutputStream dos = null;
        byte result[] = null;
        try
        {
            URL url = new URL(reqUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");     // 设置是否从httpUrlConnection读入，默认情况下是true;
            httpURLConnection.setDoInput(true);             // 设置是否向httpUrlConnection输出
            httpURLConnection.setDoOutput(true);            // Post 请求不能使用缓存
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(HttpUtil.connectTimeOut));   // （单位：毫秒）jdk1.4换成这个,连接超时
            System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(5000));                         // （单位：毫秒）jdk1.4换成这个,读操作超时
            dos = new DataOutputStream(httpURLConnection.getOutputStream());
            dos.write(file, 0, file.length);
            dos.flush();
            dos.close();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                result = new byte[httpURLConnection.getContentLength()];
                httpURLConnection.getInputStream().read(result);
            }
        }
        catch (IOException e)
        {
            LOGGER.error("HttpUtil:doPost", e);
        }
        catch (Exception e)
        {
            LOGGER.error("HttpUtil:doPost", e);
        }
        finally
        {
            if (dos != null)
            {
                try
                {
                    dos.close();
                }
                catch (IOException e)
                {
                    LOGGER.error("Close dos error: ", e);
                }
            }
            if (httpURLConnection != null)
            {
                httpURLConnection.disconnect();
            }
        }
        return result;
    }

    public static String specialUrlEncode(String value) throws Exception
    {
        return java.net.URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }

}
