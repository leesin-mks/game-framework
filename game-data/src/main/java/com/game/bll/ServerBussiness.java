/*
 * ServerBussiness
 *
 * 2017年6月16日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
 */
package com.game.bll;

import java.util.List;

import com.game.entity.bean.ServerListBean;

/**
 * @author jacken
 *
 */
public class ServerBussiness
{
    public static List<ServerListBean> getServerList()
    {
        return DaoManager.getServerListDao().listAll();
    }

    public static boolean updateServerListBean(ServerListBean bean)
    {
        return DaoManager.getServerListDao().update(bean);
    }

}
