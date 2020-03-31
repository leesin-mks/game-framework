/*
 * DaoManager
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.bll;

import com.game.component.ComponentManager;
import com.game.dao.IServerListBeanDao;
import com.game.dao.impl.ServerListBeanDaoImpl;
import com.game.database.ConnString;
import com.game.database.DBComponent;
import com.game.database.DBHelper;

/**
 * @author jacken
 *
 */
public class DaoManager
{
    /**
     * 游戏主库hepler
     */
    public static final DBHelper DB_MASTER_HELPER = ((DBComponent) ComponentManager.getInstance().getComponent(
            "DBComponent")).getDBHelper(ConnString.getMasterDB());
    /**
     * 游戏从库hepler
     */
    public static final DBHelper DB_SLAVE_HELPER = ((DBComponent) ComponentManager.getInstance().getComponent(
            "DBComponent")).getDBHelper(ConnString.getSlaveDB());

    private static final IServerListBeanDao serverListDao = new ServerListBeanDaoImpl(DB_MASTER_HELPER);

    public static IServerListBeanDao getServerListDao()
    {
        return serverListDao;
    }

}
