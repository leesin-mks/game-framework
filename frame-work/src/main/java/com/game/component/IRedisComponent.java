/*
 * IRedisComponent
 *
 * 2016年7月4日
 *
 * All rights reserved. This material is confidential and proprietary to ThreeMonths
 */
package com.game.component;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.JedisPubSub;

/**
 * @author Jacken
 *
 */
public interface IRedisComponent extends IComponent
{
    public static String NAME = "RedisComponent";

    public void hdel(String key, String... fields);

    public boolean exists(String key);

    public long getNextID(String key);

    public boolean hexists(String key, String field);

    public String set(String key, byte[] value);

    public byte[] getBin(String key);

    public String set(String key, String value);

    public void setex(String key, int second, String value);

    public String setex(byte[] key, int seconds, byte[] value);

    public String get(String key);

    public byte[] get(byte[] key);

    public String hget(String key, String field);

    public Map<String, String> hgetAll(String key);

    public void hmset(String key, Map<String, String> map);

    public void hmset(String key, Map<String, String> map, int expire);

    public void hset(String key, String field, String value);

    public void hset(String key, String field, String value, int expire);

    public List<String> lrang(String key, int begin, int end);

    public List<String> lall(String key);

    public Long llen(String key);

    public String lindex(String key, int i);

    public void lpush(String key, String value);

    void lpush(byte[] key, byte[] member);

    List<byte[]> lrang(byte[] key, int start, int end);

    public void rpush(String key, String value);

    public String lpop(String string);

    public void ltrim(String key, int count);

    public void del(String key);

    public void del(byte[] key);

    public String setNX(String key, String value);

    public long incr(String key);

    public long incrby(String key, long value);

    public Long publishMessage(String channel, String message);

    public void subScribe(JedisPubSub jedisPubSub, String... channels);

    public Set<String> keys(String pattern);

    public void srem(String key, String value);

    public void sadd(String key, String value);

    public Set<String> smembers(String key);

    /**
     * 分布式锁获取
     * 
     * @param lockName
     * @param waitTimeOut
     * @param lockTimeOut
     * @return
     */
    public String acquireLockWithTimeOut(String lockName, long waitTimeOut, int lockTimeOut);

    /**
     * 分布式锁释放
     * 
     * @param lockName
     * @param lockId
     * @param waiteTimeOut
     * @return
     */
    public boolean releaseLock(String lockName, String lockId, long waiteTimeOut);
}
