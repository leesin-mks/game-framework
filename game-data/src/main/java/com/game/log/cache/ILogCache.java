/**
 * Date: 2014-3-26
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.game.log.cache;


import com.game.component.IComponent;
import com.game.log.task.AbstractTask;

/**
 * 日志缓存接口
 * 
 * @author saly.bao
 */
public interface ILogCache extends IComponent
{
    /** 组件名称 */
    public static final String NAME = "LogCache";

    /**
     * 向缓存中添加日志
     * 
     * @param log
     */
    public <T> void addLog(T log);

    /**
     * 向DB中更新日志
     */
    public void writeLog();

    public <T> void addLogTask(AbstractTask<T> task);
    
    public <T> void addLogDelayTask(AbstractTask<T> task);
}
