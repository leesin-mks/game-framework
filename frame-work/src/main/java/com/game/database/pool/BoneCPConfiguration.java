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

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jacken
 *
 */
public class BoneCPConfiguration
{
    private static final Logger LOGGER = LoggerFactory.getLogger(BoneCPConfiguration.class);
    // 默认值
    public static final int DEFAULT_PARTITION_COUNT = 1;
    public static final int DEFAULT_MAX_CONNECTIONS_PER_PARTITION = 2;
    public static final int DEFAULT_MIN_CONNECTIONS_PER_PARTITION = 0;
    public static final int DEFAULT_ACQUIRE_INCREMENT = 1;
    public static final int DEFAULT_POOL_AVAILABILITY_THRESHOLD = 20;
    public static final long DEFAULT_CONNECTION_TIMEOUT_IN_MS = Long.MAX_VALUE;
    public static final int DEFAULT_RELEASEHELPER_THREADS = 3;
    public static final int DEFAULT_STATEMENT_RELEASEHELPER_THREADS = 3;
    public static final long DEFAULT_MAX_CONNECTION_AGE_IN_SECONDS = 0;
    public static final long DEFAULT_IDLE_MAX_AGE_IN_MINUTES = 60;
    public static final long DEFAULT_IDLE_CONNECTION_TEST_PERIOD_IN_MINUTES = 240;
    public static final int DEFAULT_ACQUIRE_RETRY_ATTEMPTS = 5;
    public static final long DEFAULT_ACQUIRE_RETRY_DELAY_DELAY_INMS = 7000;
    public static final boolean DEFAULT_LAZY_INIT = false;
    public static final boolean DEFAULT_DISABLE_JMX = false;

    public static final boolean CLOSE_CONNECTION_WATCH = false;
    public static final long CLOSE_CONNECTION_WATCH_TIMEOUT_IN_MS = 0;
    public static final boolean LOG_STATEMENTS_ENABLED = false;
    public static final long QUERY_EXECUTE_TIME_LIMIT_IN_MS = 0;
    public static final boolean DISABLE_CONNECTION_TRACKING = false;
    public static final boolean TRANSACTION_RECOVERY_ENABLED = false;

    // BoneCP主要配置参数

    private String jdbcUrl;

    // 设置分区个数。这个参数默认为1，建议3-4
    private int partitionCount = DEFAULT_PARTITION_COUNT;
    // 设置每个分区含有connection最大个数。这个参数默认为2。如果小于2，BoneCP将设置为50。
    private int maxConnectionsPerPartition = DEFAULT_MAX_CONNECTIONS_PER_PARTITION;
    // 设置每个分区含有connection最大小个数。这个参数默认为0。
    private int minConnectionsPerPartition = DEFAULT_MAX_CONNECTIONS_PER_PARTITION;
    // 设置分区中的connection增长数量。这个参数默认为1。
    private int acquireIncrement = DEFAULT_ACQUIRE_INCREMENT;
    // 设置连接池阀值。这个参数默认为20。如果小于0或是大于100，BoneCP将设置为20。
    private int poolAvailabilityThreshold = DEFAULT_POOL_AVAILABILITY_THRESHOLD;
    // 设置获取connection超时的时间。这个参数默认为Long.MAX_VALUE;单位：毫秒。
    private long connectionTimeoutInMs = DEFAULT_CONNECTION_TIMEOUT_IN_MS;

    // BoneCP线程配置参数

    // 设置connection助手线程个数。这个参数默认为3。如果小于0，BoneCP将设置为3。
    private int releaseHelperThreads = DEFAULT_RELEASEHELPER_THREADS;
    // 设置statement助手线程个数。这个参数默认为3。如果小于0，BoneCP将设置为3。
    private int statementReleaseHelperThreads = DEFAULT_STATEMENT_RELEASEHELPER_THREADS;
    // 设置connection的存活时间。这个参数默认为0，单位：毫秒。设置为0该功能失效。
    private long maxConnectionAgeInSeconds = DEFAULT_MAX_CONNECTION_AGE_IN_SECONDS;
    // 设置connection的空闲存活时间。这个参数默认为60，单位：分钟。设置为0该功能失效。
    private long idleMaxAgeInMinutes = DEFAULT_IDLE_MAX_AGE_IN_MINUTES;
    // 设置测试connection的间隔时间。这个参数默认为240，单位：分钟。设置为0该功能失效。
    private long idleConnectionTestPeriodInMinutes = DEFAULT_IDLE_CONNECTION_TEST_PERIOD_IN_MINUTES;

    // BoneCP可选配置参数
    // 设置重新获取连接的次数。这个参数默认为5。
    private int acquireRetryAttempts = DEFAULT_ACQUIRE_RETRY_ATTEMPTS;
    // 设置重新获取连接的次数间隔时间。这个参数默认为7000，单位：毫秒。如果小于等于0，BoneCP将设置为1000。
    private long acquireRetryDelayDelayInMs = DEFAULT_ACQUIRE_RETRY_DELAY_DELAY_INMS;
    // 设置连接池初始化功能。这个参数默认为false。
    private boolean lazyInit = DEFAULT_LAZY_INIT;
    // 设置是否关闭JMX功能。这个参数默认为false。
    private boolean disableJMX = DEFAULT_DISABLE_JMX;

    // BoneCP调试配置参数
    // 设置是开启connection关闭情况监视器功能。这个参数默认为false。
    private boolean closeConnectionWatch = CLOSE_CONNECTION_WATCH;
    // 设置关闭connection监视器（CloseThreadMonitor）持续多长时间。这个参数默认为0
    private long closeConnectionWatchTimeoutInMs = CLOSE_CONNECTION_WATCH_TIMEOUT_IN_MS;
    // 设置是否开启记录SQL语句功能。这个参数默认是false。
    private boolean logStatementsEnabled = LOG_STATEMENTS_ENABLED;
    // 设置执行SQL的超时时间。这个参数默认为0；单位：毫秒。
    private long queryExecuteTimeLimitInMs = QUERY_EXECUTE_TIME_LIMIT_IN_MS;
    // 设置是否关闭connection跟踪功能。这个参数默认为false。
    private boolean disableConnectionTracking = DISABLE_CONNECTION_TRACKING;
    // 设置事务回放功能。这个参数默认为false。
    private boolean transactionRecoveryEnabled = TRANSACTION_RECOVERY_ENABLED;

    public BoneCPConfiguration(String jdbcUrl)
    {
        setJdbcUrl(jdbcUrl);
    }

    public BoneCPConfiguration(Element element)
    {
        try
        {
            String url = element.elementText("url");
            this.setJdbcUrl(url);

            if (element.elementText("PartitionCount") != null)
            {
                this.setPartitionCount(Integer.parseInt(element.elementText("PartitionCount")));
            }
            if (element.elementText("MinConnectionsPerPartition") != null)
            {
                this.setMinConnectionsPerPartition(Integer.parseInt(element.elementText("MinConnectionsPerPartition")));
            }
            if (element.elementText("ConnectionsPerPartition") != null)
            {
                this.setMaxConnectionsPerPartition(Integer.parseInt(element.elementText("ConnectionsPerPartition")));
            }
            if (element.elementText("AcquireIncrement") != null)
            {
                this.setAcquireIncrement(Integer.parseInt(element.elementText("AcquireIncrement")));
            }
            if (element.elementText("PoolAvailabilityThreshold") != null)
            {
                this.setPoolAvailabilityThreshold(Integer.parseInt(element.elementText("PoolAvailabilityThreshold")));
            }
            if (element.elementText("ConnectionTimeoutInMs") != null)
            {
                this.setConnectionTimeoutInMs(Long.parseLong(element.elementText("ConnectionTimeoutInMs")));
            }

            //
            if (element.elementText("ReleaseHelperThreads") != null)
            {
                this.setReleaseHelperThreads(Integer.parseInt(element.elementText("ReleaseHelperThreads")));
            }
            if (element.elementText("StatementReleaseHelperThreads") != null)
            {
                this.setStatementReleaseHelperThreads(
                        Integer.parseInt(element.elementText("StatementReleaseHelperThreads")));
            }
            if (element.elementText("MaxConnectionAgeInSeconds") != null)
            {
                this.setMaxConnectionAgeInSeconds(Long.parseLong(element.elementText("MaxConnectionAgeInSeconds")));
            }
            if (element.elementText("IdleMaxAgeInMinutes") != null)
            {
                this.setIdleMaxAgeInMinutes(Long.parseLong(element.elementText("IdleMaxAgeInMinutes")));
            }
            if (element.elementText("IdleConnectionTestPeriodInMinutes") != null)
            {
                this.setIdleConnectionTestPeriodInMinutes(
                        Long.parseLong(element.elementText("IdleConnectionTestPeriodInMinutes")));
            }

            //
            if (element.elementText("AcquireRetryAttempts") != null)
            {
                this.setAcquireRetryAttempts(Integer.parseInt(element.elementText("AcquireRetryAttempts")));
            }
            if (element.elementText("AcquireRetryDelayDelayInMs") != null)
            {
                this.setAcquireRetryDelayDelayInMs(Long.parseLong(element.elementText("AcquireRetryDelayDelayInMs")));
            }
            if (element.elementText("LazyInit") != null)
            {
                this.setLazyInit(Boolean.parseBoolean(element.elementText("LazyInit")));
            }
            if (element.elementText("DisableJMX") != null)
            {
                this.setDisableJMX(Boolean.parseBoolean(element.elementText("DisableJMX")));
            }

            //
            if (element.elementText("CloseConnectionWatch") != null)
            {
                this.setCloseConnectionWatch(Boolean.parseBoolean(element.elementText("CloseConnectionWatch")));
            }
            if (element.elementText("CloseConnectionWatchTimeoutInMs") != null)
            {
                this.setCloseConnectionWatchTimeoutInMs(
                        Long.parseLong(element.elementText("CloseConnectionWatchTimeoutInMs")));
            }
            if (element.elementText("LogStatementsEnabled") != null)
            {
                this.setLogStatementsEnabled(Boolean.parseBoolean(element.elementText("LogStatementsEnabled")));
            }
            if (element.elementText("QueryExecuteTimeLimitInMs") != null)
            {
                this.setQueryExecuteTimeLimitInMs(Long.parseLong(element.elementText("QueryExecuteTimeLimitInMs")));
            }
            if (element.elementText("DisableConnectionTracking") != null)
            {
                this.setDisableConnectionTracking(
                        Boolean.parseBoolean(element.elementText("DisableConnectionTracking")));
            }
            if (element.elementText("TransactionRecoveryEnabled") != null)
            {
                this.setTransactionRecoveryEnabled(
                        Boolean.parseBoolean(element.elementText("TransactionRecoveryEnabled")));
            }
        }
        catch (Exception e)
        {
            LOGGER.error("init database config error!", e);
        }
    }

    public String getJdbcUrl()
    {
        return jdbcUrl;
    }

    public BoneCPConfiguration setJdbcUrl(String jdbcUrl)
    {
        this.jdbcUrl = jdbcUrl;
        return this;
    }

    public int getPartitionCount()
    {
        return partitionCount;
    }

    public BoneCPConfiguration setPartitionCount(int partitionCount)
    {
        this.partitionCount = partitionCount;
        return this;
    }

    public int getMaxConnectionsPerPartition()
    {
        return maxConnectionsPerPartition;
    }

    public BoneCPConfiguration setMaxConnectionsPerPartition(
            int maxConnectionsPerPartition)
    {
        this.maxConnectionsPerPartition = maxConnectionsPerPartition;
        return this;
    }

    public int getMinConnectionsPerPartition()
    {
        return minConnectionsPerPartition;
    }

    public BoneCPConfiguration setMinConnectionsPerPartition(
            int minConnectionsPerPartition)
    {
        this.minConnectionsPerPartition = minConnectionsPerPartition;
        return this;
    }

    public int getAcquireIncrement()
    {
        return acquireIncrement;
    }

    public BoneCPConfiguration setAcquireIncrement(int acquireIncrement)
    {
        this.acquireIncrement = acquireIncrement;
        return this;
    }

    public int getPoolAvailabilityThreshold()
    {
        return poolAvailabilityThreshold;
    }

    public BoneCPConfiguration setPoolAvailabilityThreshold(
            int poolAvailabilityThreshold)
    {
        this.poolAvailabilityThreshold = poolAvailabilityThreshold;
        return this;
    }

    public long getConnectionTimeoutInMs()
    {
        return connectionTimeoutInMs;
    }

    public BoneCPConfiguration setConnectionTimeoutInMs(
            long connectionTimeoutInMs)
    {
        this.connectionTimeoutInMs = connectionTimeoutInMs;
        return this;
    }

    public int getReleaseHelperThreads()
    {
        return releaseHelperThreads;
    }

    public BoneCPConfiguration setReleaseHelperThreads(int releaseHelperThreads)
    {
        this.releaseHelperThreads = releaseHelperThreads;
        return this;
    }

    public int getStatementReleaseHelperThreads()
    {
        return statementReleaseHelperThreads;
    }

    public BoneCPConfiguration setStatementReleaseHelperThreads(
            int statementReleaseHelperThreads)
    {
        this.statementReleaseHelperThreads = statementReleaseHelperThreads;
        return this;
    }

    public long getMaxConnectionAgeInSeconds()
    {
        return maxConnectionAgeInSeconds;
    }

    public BoneCPConfiguration setMaxConnectionAgeInSeconds(
            long maxConnectionAgeInSeconds)
    {
        this.maxConnectionAgeInSeconds = maxConnectionAgeInSeconds;
        return this;
    }

    public long getIdleMaxAgeInMinutes()
    {
        return idleMaxAgeInMinutes;
    }

    public BoneCPConfiguration setIdleMaxAgeInMinutes(long idleMaxAge)
    {
        this.idleMaxAgeInMinutes = idleMaxAge;
        return this;
    }

    public long getIdleConnectionTestPeriodInMinutes()
    {
        return idleConnectionTestPeriodInMinutes;
    }

    public BoneCPConfiguration setIdleConnectionTestPeriodInMinutes(
            long idleConnectionTestPeriodInMinutes)
    {
        this.idleConnectionTestPeriodInMinutes = idleConnectionTestPeriodInMinutes;
        return this;
    }

    public int getAcquireRetryAttempts()
    {
        return acquireRetryAttempts;
    }

    public BoneCPConfiguration setAcquireRetryAttempts(int acquireRetryAttempts)
    {
        this.acquireRetryAttempts = acquireRetryAttempts;
        return this;
    }

    public long getAcquireRetryDelayDelayInMs()
    {
        return acquireRetryDelayDelayInMs;
    }

    public BoneCPConfiguration setAcquireRetryDelayDelayInMs(
            long acquireRetryDelayDelayInMs)
    {
        this.acquireRetryDelayDelayInMs = acquireRetryDelayDelayInMs;
        return this;
    }

    public boolean isLazyInit()
    {
        return lazyInit;
    }

    public BoneCPConfiguration setLazyInit(boolean lazyInit)
    {
        this.lazyInit = lazyInit;
        return this;
    }

    public boolean isDisableJMX()
    {
        return disableJMX;
    }

    public BoneCPConfiguration setDisableJMX(boolean disableJMX)
    {
        this.disableJMX = disableJMX;
        return this;
    }

    public boolean isCloseConnectionWatch()
    {
        return closeConnectionWatch;
    }

    public BoneCPConfiguration setCloseConnectionWatch(
            boolean closeConnectionWatch)
    {
        this.closeConnectionWatch = closeConnectionWatch;
        return this;
    }

    public long getCloseConnectionWatchTimeoutInMs()
    {
        return closeConnectionWatchTimeoutInMs;
    }

    public BoneCPConfiguration setCloseConnectionWatchTimeoutInMs(
            long closeConnectionWatchTimeoutInMs)
    {
        this.closeConnectionWatchTimeoutInMs = closeConnectionWatchTimeoutInMs;
        return this;
    }

    public boolean isLogStatementsEnabled()
    {
        return logStatementsEnabled;
    }

    public BoneCPConfiguration setLogStatementsEnabled(
            boolean logStatementsEnabled)
    {
        this.logStatementsEnabled = logStatementsEnabled;
        return this;
    }

    public long getQueryExecuteTimeLimitInMs()
    {
        return queryExecuteTimeLimitInMs;
    }

    public BoneCPConfiguration setQueryExecuteTimeLimitInMs(
            long queryExecuteTimeLimitInMs)
    {
        this.queryExecuteTimeLimitInMs = queryExecuteTimeLimitInMs;
        return this;
    }

    public boolean getDisableConnectionTracking()
    {
        return disableConnectionTracking;
    }

    public BoneCPConfiguration setDisableConnectionTracking(
            boolean disableConnectionTracking)
    {
        this.disableConnectionTracking = disableConnectionTracking;
        return this;
    }

    public boolean getTransactionRecoveryEnabled()
    {
        return transactionRecoveryEnabled;
    }

    public BoneCPConfiguration setTransactionRecoveryEnabled(
            boolean transactionRecoveryEnabled)
    {
        this.transactionRecoveryEnabled = transactionRecoveryEnabled;
        return this;
    }
}
