package com.game.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.bean.StateCode;
import com.game.object.LoginResultBean;
import com.game.util.StringUtil;
import com.game.web.ResultMessage;
import com.google.gson.Gson;

public class HttpLoginClient
{

    protected static final Logger LOGGER = LoggerFactory.getLogger(HttpLoginClient.class);

    private static Gson gson = new Gson();

    private static final String HTTP_URL = "http://172.16.1.98:8081/login-server/Login";// http://xyj-t.yinxianggame.com:8080/game-login/login

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

    private String method;

    private String unionID;

    private String serverID;

    private String channel;

    private LoginResultBean loginResult;

    public HttpLoginClient(String method, String unionID, String channel, String serverID)
    {
        this.method = method;
        this.unionID = unionID;
        this.channel = channel;
        this.serverID = serverID;
    }

    public boolean httpLogin()
    {
        Map<String, String> params = new HashMap<>();
        params.put("method", method);
        params.put("unionID", unionID);
        params.put("channel", channel);
        params.put("serverID", serverID);
        String result = doPost(HTTP_URL, params, StandardCharsets.UTF_8.name());
        LOGGER.error("Login result: {}", result);
        if (StringUtil.isNullOrEmpty(result))
        {
            return false;
        }
        ResultMessage loginResult = gson.fromJson(result, ResultMessage.class);
        if (loginResult.getStatus() != StateCode.LOGIN_SUCCESS)
        {
            return false;
        }
        LoginResultBean data = gson.fromJson(loginResult.getData(), LoginResultBean.class);
        this.loginResult = data;
        return true;
    }

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
                            requestEncoding));
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
                    String.valueOf(connectTimeOut));// （单位：毫秒）jdk1.4换成这个,连接超时
            System.setProperty("sun.net.client.defaultReadTimeout",
                    String.valueOf(readTimeOut)); // （单位：毫秒）jdk1.4换成这个,读操作超时
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

    public String getMethod()
    {
        return method;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    public String getUnionID()
    {
        return unionID;
    }

    public void setUnionID(String unionID)
    {
        this.unionID = unionID;
    }

    public LoginResultBean getLoginResult()
    {
        return loginResult;
    }

    public void setLoginResult(LoginResultBean loginResult)
    {
        this.loginResult = loginResult;
    }

    public String getChannel()
    {
        return channel;
    }

    public static void main(String[] args)
    {
        Map<String, String> params = new HashMap<String, String>();
        params.put("openId", "leesink109");
        params.put("version", "1.0.0");
        params.put("channel", "default0");
        String result = doPost(HTTP_URL, params, "utf-8");
        System.out.println("-->" + result);
    }
}
