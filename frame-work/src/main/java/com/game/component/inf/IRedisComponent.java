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

package com.game.component.inf;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.game.component.IComponent;
import redis.clients.jedis.JedisPubSub;

/**
 * @author leesin
 *
 */
public interface IRedisComponent extends IComponent
{
    String NAME = "RedisComponent";

    void hDel(String key, String... fields);

    boolean exists(String key);

    long getNextID(String key);

    boolean hexists(String key, String field);

    String set(String key, byte[] value);

    byte[] getBin(String key);

    String set(String key, String value);

    void setex(String key, int second, String value);

    String setex(byte[] key, int seconds, byte[] value);

    String get(String key);

    byte[] get(byte[] key);

    String hget(String key, String field);

    Map<String, String> hgetAll(String key);

    void hmset(String key, Map<String, String> map);

    void hmset(String key, Map<String, String> map, int expire);

    void hset(String key, String field, String value);

    void hset(String key, String field, String value, int expire);

    List<String> lrang(String key, int begin, int end);

    List<String> lall(String key);

    Long llen(String key);

    String lindex(String key, int i);

    void lpush(String key, String value);

    void lpush(byte[] key, byte[] member);

    List<byte[]> lrang(byte[] key, int start, int end);

    void rpush(String key, String value);

    String lpop(String string);

    void ltrim(String key, int count);

    void del(String key);

    void del(byte[] key);

    String setNX(String key, String value);

    long incr(String key);

    long incrby(String key, long value);

    Long publishMessage(String channel, String message);

    void subScribe(JedisPubSub jedisPubSub, String... channels);

    Set<String> keys(String pattern);

    void srem(String key, String value);

    void sadd(String key, String value);

    Set<String> smembers(String key);

    /**
     * 分布式锁获取
     * 
     * @param lockName
     * @param waitTimeOut
     * @param lockTimeOut
     * @return
     */
    String acquireLockWithTimeOut(String lockName, long waitTimeOut, int lockTimeOut);

    /**
     * 分布式锁释放
     * 
     * @param lockName
     * @param lockId
     * @param waiteTimeOut
     * @return
     */
    boolean releaseLock(String lockName, String lockId, long waiteTimeOut);
}
