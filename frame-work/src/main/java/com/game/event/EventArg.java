/*
 * EventArg
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.bdsk.event;

import java.util.EventObject;

/**
 * @author jacken
 *         事件参数
 */
public class EventArg extends EventObject
{
    private static final long serialVersionUID = -3737140928956265655L;

    /** 事件类型 */
    private int eventType;

    /** 自定义参数 */
    private Object data;

    /** 触发器参数(如果事件会或发触发器的话，有可能会设置此参数) */
    private String triParams;

    /**
     * 事件构造器
     * 
     * @param source
     *            事件源
     * @param eventType
     *            事件类型
     */
    public EventArg(Object source, int eventType)
    {
        super(source);
        this.eventType = eventType;
    }

    /**
     * 事件构造器
     * 
     * @param source
     *            事件源
     * @param eventType
     *            事件类型
     * @param data
     *            自定义参数
     */
    public EventArg(Object source, int eventType, Object data)
    {
        super(source);
        this.eventType = eventType;
        this.data = data;
    }

    public EventArg(Object source, int eventType, Object... data)
    {
        super(source);
        this.eventType = eventType;
        this.data = data;
    }

    /**
     * 获取事件类型
     * 
     * @return 事件类型
     */
    public int getEventType()
    {
        return eventType;
    }

    /**
     * 获取自定义参数
     * 
     * @return 自定义参数
     */
    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    /**
     * @return the triParams
     */
    public String getTriParams()
    {
        return triParams;
    }

    /**
     * @param triParams
     *            the triParams to set
     */
    public void setTriParams(String triParams)
    {
        this.triParams = triParams;
    }

    public void setSource(Object source)
    {
        this.source = source;
    }

    /**
     * @param eventType
     *            the eventType to set
     */
    public void setEventType(int eventType)
    {
        this.eventType = eventType;
    }
}
