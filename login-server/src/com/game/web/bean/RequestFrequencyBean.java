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
