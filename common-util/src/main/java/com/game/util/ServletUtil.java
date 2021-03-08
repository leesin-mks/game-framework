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

package com.game.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jacken
 *
 */
public class ServletUtil
{
    /** 在HttpServletRequest获取到的 unknown IP */
    private static final String UNKNOWN = "unknown";

    /**
     * 获取客户端IP
     * 
     * @param request
     * @return
     */
    public static String getRequestIP(HttpServletRequest request)
    {
        String ip = null;

        if (request == null)
        {
            return null;
        }

        ip = request.getHeader("x-real-ip");
        if (!StringUtil.isNullOrEmpty(ip) && !UNKNOWN.equalsIgnoreCase(ip))
        {
            return ip;
        }

        ip = request.getHeader("x-forwarded-for");
        if (!StringUtil.isNullOrEmpty(ip) && !UNKNOWN.equalsIgnoreCase(ip))
        {
            String[] ips = ip.split(",");
            if (ips.length > 1)
            {
                // 多级反向代理处理。取X-Forwarded-For中第一个非unknown的有效IP字符串。
                for (int i = 0; i < ips.length; i++)
                {
                    if (!UNKNOWN.equalsIgnoreCase(ips[i]))
                    {
                        ip = ips[i];
                        break;
                    }
                }
            }

            return ip.trim();
        }

        ip = request.getHeader("Proxy-Client-IP");
        if (StringUtil.isNullOrEmpty(ip) == false
                && UNKNOWN.equalsIgnoreCase(ip) == false)
        {
            return ip;
        }

        ip = request.getHeader("WL-Proxy-Client-IP");
        if (StringUtil.isNullOrEmpty(ip) == false
                && UNKNOWN.equalsIgnoreCase(ip) == false)
        {
            return ip;
        }

        // 必须在最后啊，不然有代理的时候获取到的不是真实的客户端IP。
        ip = request.getRemoteAddr();
        if (StringUtil.isNullOrEmpty(ip) == false && UNKNOWN.equalsIgnoreCase(ip) == false)
        {
            return ip;
        }

        return null;
    }
}
