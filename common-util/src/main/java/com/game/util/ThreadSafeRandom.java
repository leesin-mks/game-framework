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

package com.game.util;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author jacken
 *
 */
public class ThreadSafeRandom
{
    private SecureRandom  random = new SecureRandom();

    public int next()
    {
        synchronized (this)
        {
            return random.nextInt();
        }
    }

    public int next(int maxValue)
    {
        synchronized (this)
        {
            return random.nextInt(maxValue);
        }
    }

    public float nextFloat()
    {
        synchronized (this)
        {
            return random.nextFloat();
        }
    }

    public static void main(String[] args)
    {
        ThreadSafeRandom ts = new ThreadSafeRandom();
        for (int i = 0; i < 100; i++)
        {
            System.out.print(ts.next(1) + ",");
        }
    }

    /**
     * 随机区间值，如 min=1 maxValue=5 随机，其结果值不包括5
     * 
     * @param minValue
     *            开始值
     * @param maxValue
     *            结束值
     * @return
     */
    public int next(int minValue, int maxValue)
    {
        synchronized (this)
        {
            if (minValue < maxValue)
            {
                return random.nextInt(maxValue - minValue) + minValue;
            }
        }
        return minValue;
    }

    public float next(float minValue, float maxValue)
    {
        synchronized (this)
        {
            if (minValue < maxValue)
            {
                return random.nextFloat() * (maxValue - minValue) + minValue;
            }
        }
        return minValue;
    }

    /**
     * 返回是否在随机范围内
     * 
     * @param value
     * @param maxValue
     * @return
     */
    public boolean inRandom(int value, int maxValue)
    {
        synchronized (this)
        {
            int ran = random.nextInt(maxValue);
            return ran <= value;
        }
    }

    public <K, V> Entry<K, V> randomMapEntry(Map<K, V> map)
    {
        if (map == null)
            return null;

        int index = random.nextInt(map.size());
        int i = 0;
        for (Entry<K, V> entry : map.entrySet())
        {
            if (i == index)
            {
                return entry;
            }
            i++;
        }
        return null;
    }

    public <E> E randomListElement(List<E> list)
    {
        if (list == null || list.size() < 1)
            return null;
        return list.get(random.nextInt(list.size()));
    }

    public <E> E randomArrayElement(E[] array)
    {
        if (array == null || array.length < 1)
            return null;
        return array[random.nextInt(array.length)];
    }

    public <E> E randomSetElement(Set<E> set)
    {
        if (set == null)
            return null;
        int index = random.nextInt(set.size());
        int i = 0;
        for (E e : set)
        {
            if (index == i)
                return e;
            i++;
        }
        return null;
    }
}
