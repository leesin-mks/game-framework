/*
 * DBComponent
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
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
 * @author jacken
 *
 */
public class DBComponent implements IComponent
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DBComponent.class);

    public static final String NAME = "DBComponent";

    /*
     * pools保存所有的连接池的信息 key对应是这个连接池的名字
     */
    private Map<String, IDBPool> pools = new ConcurrentHashMap<String, IDBPool>();
    private Map<String, DBHelper> dbHelpers = new ConcurrentHashMap<String, DBHelper>();

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.component.IComponent#getName()
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
     * @param pool
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
     * 添加Hepler
     * 
     * @param dbName
     * @param dbHelper
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

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.component.IComponent#initialize()
     */
    @Override
    public boolean initialize()
    {
        try
        {
            Element element = GlobalConfigManager.getInstance().getElement(GlobalConfigManager.PATH_DATABASE);
            List<?> dbs = element.elements("pool");
            for (int i = 0; i < dbs.size(); i++)
            {
                Element db = (Element) dbs.get(i);// 获取DB的节点，可以有多个DB节点
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

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.component.IComponent#start()
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

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.component.IComponent#stop()
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

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.component.IComponent#reload()
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
