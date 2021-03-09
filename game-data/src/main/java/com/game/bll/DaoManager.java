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

package com.game.bll;

import com.game.component.ComponentManager;
import com.game.dao.IServerListBeanDao;
import com.game.dao.impl.ServerListBeanDaoImpl;
import com.game.database.ConnString;
import com.game.database.DBComponent;
import com.game.database.DBHelper;

/**
 * @author leesin
 *
 */
public class DaoManager
{
    /**
     * 游戏主库helper
     */
    public static final DBHelper DB_MASTER_HELPER = ((DBComponent) ComponentManager.getInstance().getComponent(
            "DBComponent")).getDBHelper(ConnString.getMasterDB());
    /**
     * 游戏从库helper
     */
    public static final DBHelper DB_SLAVE_HELPER = ((DBComponent) ComponentManager.getInstance().getComponent(
            "DBComponent")).getDBHelper(ConnString.getSlaveDB());

    private static final IServerListBeanDao serverListDao = new ServerListBeanDaoImpl(DB_MASTER_HELPER);

    public static IServerListBeanDao getServerListDao()
    {
        return serverListDao;
    }

}
