/*
 * BaseHandlerServlet
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.game.config.GlobalConfigManager;
import com.game.util.ServletUtil;
import com.game.util.StackMessagePrint;
import com.game.util.StringUtil;

/**
 * @author jacken
 *
 */
public abstract class BaseHandlerServlet extends HttpServlet implements IWebHandler
{
    private static final long serialVersionUID = -270390193989568460L;

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseHandlerServlet.class);

    private String requestIP = null;                // 请求客户端的IP

    protected static final byte INVALID_IP = 0;     // 非法的客户端IP

    protected static final byte PARAM_ERROR = -1;   // 非法的客户端IP

    protected final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();         // 用于处理实体类的Gson实例

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
    {
        String result = null;
        try
        {
            // IP 检查
            requestIP = ServletUtil.getRequestIP(request);
            if (checkRequestIP(requestIP) == false)
            {
                ResultMessage rd = new ResultMessage();
                rd.setMsg("IP is not valid! " + requestIP);
                rd.setStatus(INVALID_IP);
                result = gson.toJson(rd);
                LOGGER.error("IP is not valid! " + requestIP);
            }
            else
            {
                String jsonString = request.getParameter("params");
                if (StringUtil.isNullOrEmpty(jsonString))
                {
                    ResultMessage rd = new ResultMessage();
                    rd.setMsg("parameter error!");
                    rd.setStatus(PARAM_ERROR);
                    result = gson.toJson(rd);
                }
                else
                    result = execute(jsonString);
            }
        }
        catch (Exception e)
        {
            StackMessagePrint.printError(LOGGER, e.toString(),
                    e.getStackTrace());
        }

        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }

    /**
     * 检查客户端IP是否合法。<br>
     * 子类可通过Override来改变检查范围。
     * 
     * @param ip
     *            发出请求的客户端IP
     * @return
     */
    protected boolean checkRequestIP(String ip)
    {
        return GlobalConfigManager.getInstance().getRequestConfig().checkAdmin(ip);
    }

    /**
     * 获取请求客户端的IP
     * 
     * @return
     */
    protected String getRequstIP()
    {
        return requestIP;
    }

    /**
     * 转换utf-8编码
     * 
     * @param value
     * @return
     */
    public String getUTF8(String value)
    {
        if (value != null)
        {
            try
            {
                if (!checkGBK(value))
                {
                    return new String(value.getBytes("ISO-8859-1"), "UTF-8");
                }
                else
                {
                    return value;
                }
            }
            catch (UnsupportedEncodingException e)
            {
                LOGGER.error("获取字符转换成utf-8出错", e);
            }
        }
        return "";
    }

    /**
     * 检查能否用GBK解码
     * 
     * @param value
     * @return
     */
    public boolean checkGBK(String value)
    {
        return java.nio.charset.Charset.forName("GBK").newEncoder().canEncode(value);
    }
}
