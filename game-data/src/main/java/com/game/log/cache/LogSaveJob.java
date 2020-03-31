/*
 * LogSaveJob
 *
 * 2016年7月22日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.log.cache;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.game.component.ComponentManager;

/**
 * @author Jacken
 *
 */
public class LogSaveJob implements Job
{

    /*
     * (non-Javadoc)
     * 
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException
    {
        ILogCache lc = (ILogCache) ComponentManager.getInstance().getComponent(ILogCache.NAME);
        lc.writeLog();
    }
}
