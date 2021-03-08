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

package com.game.web.bean;

import com.game.type.GlobalVariable;

public class RequestFrequencyBean
{
    private int count;

    private long requestExpireTime;

    private long expireTime;

    private long blackListTime;

    public RequestFrequencyBean(int expireTime, int frequency)
    {
        reset(expireTime, frequency);
        addCount(true);
    }

    public synchronized void reset(int expireTime, int frequency)
    {
        long tick = System.currentTimeMillis();
        this.expireTime = tick + expireTime;
        this.requestExpireTime = tick + frequency;
        addCount(true);
        setBlackList(false);
    }

    public synchronized void addCount(boolean reset)
    {
        count = reset ? 1 : count + 1;
    }

    public synchronized void setBlackList(boolean isBlack)
    {
        long tick = System.currentTimeMillis();
        this.blackListTime = isBlack ? System.currentTimeMillis() + GlobalVariable.BLACK_LIST_TIME : tick;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public long getRequestExpireTime()
    {
        return requestExpireTime;
    }

    public void setRequestExpireTime(long requestExpireTime)
    {
        this.requestExpireTime = requestExpireTime;
    }

    public long getExpireTime()
    {
        return expireTime;
    }

    public void setExpireTime(long expireTime)
    {
        this.expireTime = expireTime;
    }

    public long getBlackListTime()
    {
        return blackListTime;
    }

    public void setBlackListTime(long blackListTime)
    {
        this.blackListTime = blackListTime;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("count: " + count);
        sb.append("\n");
        sb.append("requestExpireTime: " + requestExpireTime);
        sb.append("\n");
        sb.append("requestExpireTime: " + requestExpireTime);
        sb.append("\n");
        return sb.toString();
    }

}
