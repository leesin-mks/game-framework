/*
 * RequestConfig
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.game.util.StringUtil;

/**
 * @author jacken
 *
 */
public class RequestConfig
{
    private Set<String> adminIPs = new HashSet<String>();

    Map<String, String[]> ipMaps = new HashMap<String, String[]>();

    /**
     * 设置允许执行Admin权限的操作的IP集合。
     * 
     * @return the adminIPs
     */
    public Set<String> getAdminIPs()
    {
        return adminIPs;
    }

    /**
     * 获取允许执行Admin权限的操作的IP集合。
     * 
     * @param adminIPs
     *            the adminIPs to set
     */
    public void setAdminIPs(Set<String> adminIPs)
    {
        this.adminIPs = adminIPs;
    }

    /**
     * 获取允许执行Admin权限的操作的IP集合。
     * 
     * @param adminIPs
     *            the adminIPs to set
     */
    public void setAdminIPs(String[] adminIPs)
    {
        for (String item : adminIPs)
        {
            getAdminIPs().add(item);
        }
    }

    /**
     * 检查指定的IP是否允许执行Admin权限的操作。
     * 
     * @param accessIP
     * @return
     */
    public boolean checkAdmin(String accessIP)
    {
        if (StringUtil.isNullOrEmpty(accessIP))
        {
            return false;
        }

        if (getAdminIPs().size() <= 0)
            return true;
        String[] ips = accessIP.split("\\.");
        for (String item : getAdminIPs())
        {
            String[] strs = ipMaps.get(item);
            if (strs == null)
            {
                strs = item.split("\\.");
                ipMaps.put(item, strs);
            }
            boolean bl = true;
            for (int i = 0; i < 4; i++)
            {
                if ("*".equals(strs[i]) || ips[i].equals(strs[i]))
                    continue;
                else
                {
                    bl = false;
                    break;
                }
            }
            if (bl)
                return bl;
        }
        return false;
    }
}
