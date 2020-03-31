/*
 * IEventListener
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.bdsk.event;

/**
 * @author jacken
 *         事件监听器接口
 */
public interface IEventListener
{
    /**
     * 事件触发时的回调。
     * 
     * @param arg
     *            事件参数
     */
    void onEvent(EventArg arg);
}
