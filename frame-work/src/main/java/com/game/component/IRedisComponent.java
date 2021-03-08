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
