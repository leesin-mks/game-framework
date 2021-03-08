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

package com.game.component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.config.GlobalConfigManager;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.util.SafeEncoder;

/**
 * @author Jacken
 *
 */
public class RedisComponent implements IRedisComponent
{
    protected static Logger LOGGER = LoggerFactory.getLogger(RedisComponent.class);

    /** redis 连接池 */
    private JedisPool jedisPool = null;

    /*
     * (non-Javadoc)
     * 
     * @see com.threemonths.component.IComponent#getName()
     */
    @Override
    public String getName()
    {
        return NAME;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.threemonths.component.IComponent#initialize()
     */
    @Override
    public boolean initialize()
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.threemonths.component.IComponent#start()
     */
    @Override
    public boolean start()
    {
        String ip = GlobalConfigManager.getInstance().getServerConfig().getRedisIP();
        int port = GlobalConfigManager.getInstance().getServerConfig().getRedisPort();
        String password = GlobalConfigManager.getInstance().getServerConfig().getRedisPWD();
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(200);
        config.setTestOnBorrow(true);
        jedisPool = new JedisPool(config, ip, port, 3000, password);
        pingTest();
        return true;
    }

    /*
     * static {
     * // redis 属性配置 start
     * JedisPoolConfig config = new JedisPoolConfig();
     * config.setMaxTotal(5000);
     * config.setMaxIdle(256);
     * config.setMaxWaitMillis(5000L);
     * config.setTestOnBorrow(true);
     * config.setTestOnReturn(true);
     * config.setTestWhileIdle(true);
     * config.setMinEvictableIdleTimeMillis(60000L);
     * config.setTimeBetweenEvictionRunsMillis(3000L);
     * config.setNumTestsPerEvictionRun(-1);
     * // redis 属性配置 end
     * 
     * Set<String> sentinels = new HashSet<String>();
     * sentinels.add("80.27.112.118:26379"); // 此处放置ip及端口为 sentinel
     * // 服务地址，如果有多个sentinel 则逐一add即可
     * jedisPool = new JedisSentinelPool("master1", sentinels, config);
     * }
     */

    /*
     * (non-Javadoc)
     * 
     * @see com.threemonths.component.IComponent#stop()
     */
    @Override
    public void stop()
    {
        if (jedisPool != null)
        {
            jedisPool.destroy();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.threemonths.component.IComponent#reload()
     */
    @Override
    public boolean reload()
    {
        return false;
    }

    /**
     * 测试连接
     * 
     * @return
     */
    public void pingTest()
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.ping();
        }
        catch (Exception e)
        {
            LOGGER.error("Redis连接不上!");
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    /**
     * 删除hash的某些域
     */
    @Override
    public void hdel(String key, String... fields)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.hdel(key, fields);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    /**
     * 判断key是否存在
     */
    @Override
    public boolean exists(String key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    @Override
    public long getNextID(String key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.incr(key);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    /**
     * 判断hash是否存在
     */
    @Override
    public boolean hexists(String key, String field)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.hexists(key, field);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    /**
     * 设置字符串
     */
    @Override
    public String set(String key, byte[] value)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.set(SafeEncoder.encode(key), value);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    /**
     * 获得字符串
     */
    @Override
    public byte[] getBin(String key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.get(SafeEncoder.encode(key));
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    /**
     * 设置字符串
     */
    @Override
    public String set(String key, String value)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    /**
     * 获得字符串
     */
    @Override
    public String get(String key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    @Override
    public byte[] get(byte[] key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    /**
     * 获得hash的值
     */
    @Override
    public String hget(String key, String field)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.hget(key, field);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    /**
     * 获得hash
     */
    @Override
    public Map<String, String> hgetAll(String key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.hgetAll(key);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    /**
     * 把对象存到RediS的hash中。
     */
    @Override
    public void hmset(String key, Map<String, String> map)
    {
        hmset(key, map, -1);
    }

    /**
     * 把对象存到RediS的hash中。
     */
    @Override
    public void hmset(String key, Map<String, String> map, int expire)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.hmset(key, map);
            if (expire != -1)
            {
                jedis.expire(key, expire);
            }
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }

    }

    /**
     * 把对象存到RediS的hash中。
     */
    @Override
    public void hset(String key, String field, String value)
    {
        hset(key, field, value, -1);
    }

    /**
     * 把对象存到RediS的hash中。
     */
    @Override
    public void hset(String key, String field, String value, int expire)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.hset(key, field, value);
            if (expire != -1)
            {
                jedis.expire(key, expire);
            }
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.threemonths.chat.component.inf.IRedisComponent#lrang(java.lang.String, int, int)
     */
    @Override
    public List<String> lrang(String key, int begin, int end)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.lrange(key, begin, end);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    @Override
    public List<String> lall(String key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.lrange(key, 0, -1);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    @Override
    public Long llen(String key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.llen(key);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    @Override
    public String lindex(String key, int i)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.lindex(key, i);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    @Override
    public void lpush(String key, String value)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.lpush(key, value);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    @Override
    public void lpush(byte[] key, byte[] member)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.lpush(key, member);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    @Override
    public List<byte[]> lrang(byte[] key, int start, int end)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.lrange(key, start, end);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    @Override
    public void rpush(String key, String value)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.rpush(key, value);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    @Override
    public String lpop(String key)
    {
        Jedis jedis = null;
        String value = null;
        try
        {
            jedis = jedisPool.getResource();
            value = jedis.lpop(key);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
        return value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.threemonths.chat.component.inf.IRedisComponent#ltrim(java.lang.String, int)
     */
    @Override
    public void ltrim(String key, int count)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.ltrim(key, 0, count);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.threemonths.chat.component.inf.IRedisComponent#delAll(java.lang.String)
     */
    @Override
    public void del(String key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.del(key);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.threemonths.chat.component.inf.IRedisComponent#delAll(java.lang.String)
     */
    @Override
    public void del(byte[] key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.del(key);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.component.IRedisComponent#setNX(java.lang.String, java.lang.String)
     */
    @Override
    public String setNX(String key, String value)
    {
        Jedis jedis = null;
        long result = -1;
        try
        {
            jedis = jedisPool.getResource();
            result = jedis.setnx(key, value);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
        if (result == 1)
            return value;
        else
            return get(key);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.component.IRedisComponent#setex(java.lang.String, int, java.lang.String)
     */
    @Override
    public void setex(String key, int second, String value)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.setex(key, second, value);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    @Override
    public String setex(byte[] key, int seconds, byte[] value)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.setex(key, seconds, value);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.component.IRedisComponent#incr(java.lang.String)
     */
    @Override
    public long incr(String key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.incr(key);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.component.IRedisComponent#incrby(java.lang.String, int)
     */
    @Override
    public long incrby(String key, long value)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.incrBy(key, value);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    @Override
    public Long publishMessage(String channel, String message)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.publish(channel, message);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    @Override
    public void subScribe(JedisPubSub jedisPubSub, String... channels)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.subscribe(jedisPubSub, channels);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    @Override
    public Set<String> keys(String pattern)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.keys(pattern);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    @Override
    public void srem(String key, String value)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.srem(key, value);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    @Override
    public void sadd(String key, String value)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            jedis.sadd(key, value);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    @Override
    public Set<String> smembers(String key)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            return jedis.smembers(key);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }

    /**
     * 获取锁。
     * 该获取锁方法有如下特性：
     * 1.如果获取锁成功，会设置锁的生存时间；
     * 2.虽然大多数情况下redis的锁都有生存时间，
     * 但是为了防止在上锁后、设置锁的生存周期
     * 之前获取锁的方法出现了异常而终止。我们加入如下判断：
     * 如果获取锁失败，会检查已存在锁是否设置有生存时间，
     * 如果没有设置生存时间，那么会给锁设置生存时间。
     *
     * @param lockName
     *            锁名称
     * @param waitTimeOut
     *            等待获取锁的超时时间（毫秒）
     * @param lockTimeOut
     *            锁的生存时间（秒）
     * @return 如果获取锁成功则返回锁键对应值，否则返回null
     */
    @Override
    public String acquireLockWithTimeOut(String lockName, long waitTimeOut, int lockTimeOut)
    {

        Jedis jedis = null;
        try
        {

            String lockKey = "lock:" + lockName;
            String lockId = UUID.randomUUID().toString();
            long end = System.currentTimeMillis() + waitTimeOut;
            int i = 0;
            long sleepTime = waitTimeOut / 5;
            while (System.currentTimeMillis() < end)
            {
                jedis = jedisPool.getResource();
                if (jedis.setnx(lockKey, lockId) == 1)
                {
                    jedis.expire(lockKey, lockTimeOut);
                    LOGGER.error("acquire lock '" + lockName + "',lockId=" + lockId + ",retry " + i);
                    return lockId;
                }
                if (jedis.ttl(lockKey) < 0)
                {
                    jedis.expire(lockKey, lockTimeOut);
                }
                try
                {
                    jedis.close();
                }
                catch (Exception e)
                {
                    LOGGER.error("release redis error: ", e);
                }
                try
                {
                    Thread.sleep(sleepTime);
                    LOGGER.error("Waitting lock release,lockName:" + lockName);
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
                i++;
            }
        }
        finally
        {
            if (jedis != null)
            {
                try
                {
                    jedis.close();
                }
                catch (Exception e)
                {
                    LOGGER.error("release redis error: ", e);
                }
            }
        }
        return null;
    }

    /**
     * 解锁。
     * 解锁时将判断锁键对应值是否是给定的值，防止误解锁。
     *
     * @param lockName
     *            锁名称
     * @param lockId
     *            锁键对应值
     * @param waiteTimeOut
     *            解锁动作的超时时间（毫秒）
     * @return true如果解锁成功，否则返回false
     */
    @Override
    public boolean releaseLock(String lockName, String lockId, long waiteTimeOut)
    {
        Jedis jedis = null;
        try
        {
            jedis = jedisPool.getResource();
            String lockKey = "lock:" + lockName;
            long end = System.currentTimeMillis() + waiteTimeOut;
            int i = 0;
            while (System.currentTimeMillis() < end)
            {
                jedis.watch(lockKey);
                if (lockId.equals(jedis.get(lockKey)))
                {
                    Transaction trans = jedis.multi();
                    trans.del(lockKey);
                    List<Object> exec = trans.exec();
                    if (exec != null)
                    {
                        LOGGER.error("release lock '" + lockName + "',lockId=" + lockId + ",retry " + i);
                        return true;
                    }
                    i++;
                    continue;
                }
                jedis.unwatch();
                break;
            }
        }
        finally
        {
            if (jedis != null)
            {
                try
                {
                    jedis.close();
                }
                catch (Exception e)
                {
                    LOGGER.error("release redis error: ", e);
                }
            }
        }
        return false;
    }
}
