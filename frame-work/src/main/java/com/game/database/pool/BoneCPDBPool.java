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

package com.game.database.pool;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

/**
 * @author jacken
 *
 */
public class BoneCPDBPool implements IDBPool
{
    private static final Logger LOGGER = LoggerFactory.getLogger(BoneCPDBPool.class);
    private BoneCP dbPool;
    private BoneCPConfig config;
    private BoneCPConfiguration configuration;

    /**
     * 
     */
    public BoneCPDBPool(BoneCPConfiguration configuration)
    {
        this.configuration = configuration;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.datebase.pool.IDBPool#getConnection()
     */
    @Override
    public Connection getConnection()
    {
        try
        {
            if (dbPool != null)
            {
                return dbPool.getConnection();
            }
        }
        catch (SQLException e)
        {
            LOGGER.error(e.toString());
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.datebase.pool.IDBPool#startup()
     */
    @Override
    public boolean startUp()
    {

        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            InitConfig();
            dbPool = new BoneCP(config);
            return true;
        }
        catch (Exception e)
        {
            LOGGER.error(e.toString());
        }
        return false;
    }

    /**
     * 
     */
    private void InitConfig()
    {
        config = new BoneCPConfig();
        // BoneCP主要配置参数
        config.setJdbcUrl(configuration.getJdbcUrl());
        config.setPartitionCount(4);
        config.setMinConnectionsPerPartition(2);
        config.setMaxConnectionsPerPartition(2);
        config.setAcquireIncrement(configuration.getAcquireIncrement());
        config.setPoolAvailabilityThreshold(configuration.getPoolAvailabilityThreshold());
        config.setConnectionTimeoutInMs(configuration.getCloseConnectionWatchTimeoutInMs());// 严重
        /*
         * // BoneCP线程配置参数
         * config.setReleaseHelperThreads(configuration.getReleaseHelperThreads());
         * config.setStatementReleaseHelperThreads(configuration.getStatementReleaseHelperThreads());
         */
        config.setMaxConnectionAgeInSeconds(configuration.getMaxConnectionAgeInSeconds());// 严重
        config.setIdleMaxAgeInMinutes(configuration.getIdleMaxAgeInMinutes());
        config.setIdleConnectionTestPeriodInMinutes(configuration.getIdleConnectionTestPeriodInMinutes());
        // BoneCP可选配置参数
        config.setAcquireRetryAttempts(configuration.getAcquireRetryAttempts());
        config.setAcquireRetryDelayInMs(configuration.getAcquireRetryDelayDelayInMs());
        config.setLazyInit(configuration.isLazyInit());
        config.setDisableJMX(configuration.isDisableJMX());
        // BoneCP调试配置参数
        config.setCloseConnectionWatch(configuration.isCloseConnectionWatch());
        config.setCloseConnectionWatchTimeoutInMs(configuration.getCloseConnectionWatchTimeoutInMs());
        config.setTransactionRecoveryEnabled(configuration.getTransactionRecoveryEnabled());
        config.setLogStatementsEnabled(configuration.isLogStatementsEnabled());
        config.setQueryExecuteTimeLimitInMs(configuration.getQueryExecuteTimeLimitInMs());
        config.setDisableConnectionTracking(configuration.getDisableConnectionTracking());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.datebase.pool.IDBPool#shutdown()
     */
    @Override
    public void shutDown()
    {
        if (dbPool != null)
        {
            dbPool.close();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.datebase.pool.IDBPool#getState()
     */
    @Override
    public String getState()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.datebase.pool.IDBPool#getCurConns()
     */
    @Override
    public int getCurConns()
    {
        return dbPool.getTotalCreatedConnections();
    }
}
