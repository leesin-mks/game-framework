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

package com.game.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.bean.StateCode;
import com.game.component.ComponentManager;
import com.game.component.inf.IDataCenterComponent;
import com.game.entity.bean.PlayerInfo;
import com.game.manager.LoginManager;
import com.game.type.LoginType;
import com.google.gson.Gson;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns = "/Login", description = "用户登录")
public class Login extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private static Logger LOGGER = LoggerFactory.getLogger(Login.class);
    private static final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/html");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        String method = request.getParameter("method") == null ? "" : request.getParameter("method");
        String unionID = request.getParameter("unionID") == null ? "" : request.getParameter("unionID");
        String timestamp = request.getParameter("timestamp") == null ? "" : request.getParameter("timestamp");
        String channel = request.getParameter("channel") == null ? "0" : request.getParameter("channel");// 渠道号，默认0
        String serverID = request.getParameter("serverID") == null ? "" : request.getParameter("serverID");
        LOGGER.info("method: {}, unionID: {}, timestamp: {}, channel: {}", method, unionID, timestamp, channel);

        ResultMessage message = new ResultMessage();
        message.setSite("Login");
        int state = StateCode.SERVER_UPDATE;
        String result = "Login.Null";

        try
        {
            IDataCenterComponent dcc = (IDataCenterComponent) ComponentManager.getInstance().getComponent(
                    IDataCenterComponent.NAME);
            if (!dcc.isValidRequest(unionID))
            {
                LOGGER.info("Invalid request: {}", unionID);
                return;
            }
            // 参数校验
            LoginType type = LoginType.parse(Integer.parseInt(method));
            if (type == null)
            {
                sendResult(response, message);
                return;
            }

            PlayerInfo info = null;
            switch (type)
            {
            case DEVICE:      // 设备号登录
            {
                info = new PlayerInfo();
                info.setId(Integer.parseInt(unionID));
                info.setPlatformType(1);

                state = StateCode.LOGIN_SUCCESS;
                result = "Login.Success";
            }
                break;
            case WEI_XIN:
            {
                state = StateCode.LOGIN_FAIL;
            }
                break;
            default:
                LOGGER.warn("Unknown Type: {}", method);
                break;
            }

            if (state == StateCode.LOGIN_SUCCESS)
            {
                message.setData(LoginManager.playerLogin(info,serverID));
            }
        }
        catch (Exception e)
        {
            LOGGER.error("Login error: {}", unionID, e);
        }
        message.setStatus(state);
        message.setMsg(result);

        sendResult(response, message);
    }

    private void sendResult(HttpServletResponse response, ResultMessage message)
    {
        String output = gson.toJson(message);
        try
        {
            PrintWriter out = response.getWriter();
            out.print(output);
            out.flush();
            out.close();
            LOGGER.info(output);
        }
        catch (IOException e)
        {
            LOGGER.error("Send login message error: ", e);
        }
    }

}
