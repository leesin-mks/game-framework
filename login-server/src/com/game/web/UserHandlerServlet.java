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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.component.ComponentManager;
import com.game.component.IRedisComponent;
import com.game.component.inf.IDataCenterComponent;
import com.game.type.RedisConst;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class UserHandlerServlet extends HttpServlet
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());

    private static IRedisComponent rc;

    private static IDataCenterComponent dcc;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);

        String ownerID = request.getParameter("ownerID") == null ? "" : request.getParameter("ownerID");
        String sessionKey = request.getParameter("sessionKey") == null ? "" : request.getParameter("sessionKey");
        String result = "-1";
        if (getDataCenterC().isValidRequest(ownerID))
        {
            if (sessionKey.equals(getRedisC().get(RedisConst.USER_SESSION_KEY + ownerID)))
            {
                result = exec(request, response);
            }
            else
            {
                LOGGER.error("Session key not invalid: " + ownerID + ", sessionKey: " + sessionKey + ", session cache: "
                        + getRedisC().get(RedisConst.USER_SESSION_KEY + ownerID));
            }
        }
        else
        {
            result = "-2";
            LOGGER.error("Request frequency, ownerID: " + ownerID);
        }

        response.setHeader("Content-type", "text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }

    protected abstract String exec(HttpServletRequest request, HttpServletResponse response);

    private IRedisComponent getRedisC()
    {
        if (rc == null)
        {
            rc = (IRedisComponent) ComponentManager.getInstance().getComponent(IRedisComponent.NAME);
        }
        return rc;
    }

    private IDataCenterComponent getDataCenterC()
    {
        if (dcc == null)
        {
            dcc = (IDataCenterComponent) ComponentManager.getInstance().getComponent(IDataCenterComponent.NAME);
        }
        return dcc;
    }

}
