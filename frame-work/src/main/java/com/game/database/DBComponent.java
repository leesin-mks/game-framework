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

package com.game.database;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.component.IComponent;
import com.game.config.GlobalConfigManager;
import com.game.database.pool.BoneCPConfiguration;
import com.game.database.pool.BoneCPDBPool;
import com.game.database.pool.IDBPool;

/**
 * @author leesin
 *
 */
public class DBComponent implements IComponent
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DBComponent.class);

    public static final String NAME = "DBComponent";

    /**
     * pools保存所有的连接池的信息 key对应是这个连接池的名字
     *
     */
    private final Map<String, IDBPool> pools = new ConcurrentHashMap<>();
    private final Map<String, DBHelper> dbHelpers = new ConcurrentHashMap<>();

    /**
     * (non-Javadoc)
     * 
     */
    @Override
    public String getName()
    {
        return NAME;
    }

    /**
     * 添加连接池，并启动连接池
     * 
     * @param dbName
     *            database name
     * @param pool
     *            database pool
     */
    public void addDBPool(String dbName, IDBPool pool)
    {
        synchronized (pools)
        {
            IDBPool temp = pools.get(dbName);
            if (temp != null)
            {
                throw new RuntimeException("Already exit the dbPool!");
            }
            else
            {
                pools.put(dbName, pool);
            }
        }

    }

    /**
     * 添加Helper
     * 
     * @param dbName
     *            database name
     * @param dbHelper
     *            database helper
     */
    public void addDBHelper(String dbName, DBHelper dbHelper)
    {
        LOGGER.info("addDBHelper: {}", dbName);
        synchronized (dbHelpers)
        {
            DBHelper temp = dbHelpers.get(dbName);
            if (temp != null)
            {
                throw new RuntimeException("Already exit the dbHelper: " + dbName);
            }
            else
            {
                dbHelpers.put(dbName, dbHelper);
            }
        }
    }

    /**
     * (non-Javadoc)
     * 
     */
    @Override
    public boolean initialize()
    {
        try
        {
            Element element = GlobalConfigManager.getInstance().getElement(GlobalConfigManager.PATH_DATABASE);
            List<?> dbs = element.elements("pool");
            for (Object o : dbs)
            {
                Element db = (Element) o;// 获取DB的节点，可以有多个DB节点
                List<?> dbConfigs = db.elements();// 获取每个DB节点下面的具体数据库的配置文件
                for (Object object : dbConfigs)
                {
                    Element dbConfig = (Element) object;
                    String dbName = dbConfig.attributeValue("name");
                    BoneCPConfiguration configuration = new BoneCPConfiguration(
                            dbConfig);

                    BoneCPDBPool boneCPDBPool = new BoneCPDBPool(configuration);
                    DBHelper dbHelper = new DBHelper(boneCPDBPool);
                    addDBPool(dbName, boneCPDBPool);
                    addDBHelper(dbName, dbHelper);
                }
            }
            return true;
        }
        catch (Exception e)
        {
            LOGGER.error("init data base error!", e);
        }
        return false;
    }

    /**
     * (non-Javadoc)
     * 
     */
    @Override
    public boolean start()
    {
        synchronized (pools)
        {
            Set<Entry<String, IDBPool>> entries = pools.entrySet();
            for (Entry<String, IDBPool> entry : entries)
            {
                if (!entry.getValue().startUp())
                {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * (non-Javadoc)
     * 
     */
    @Override
    public void stop()
    {
        synchronized (pools)
        {
            for (IDBPool pool : pools.values())
            {
                pool.shutDown();
            }
        }
    }

    /**
     * (non-Javadoc)
     * 
     */
    @Override
    public boolean reload()
    {
        synchronized (pools)
        {
            this.stop();
            return this.start();
        }
    }

    public IDBPool getDBPool(String dbName)
    {
        return pools.get(dbName);
    }

    public DBHelper getDBHelper(String dbName)
    {
        return dbHelpers.get(dbName);
    }
}
