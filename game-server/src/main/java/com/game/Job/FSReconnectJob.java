/*
 * FSReconnectJob
 *
 * 2017年7月26日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
 */
package com.game.Job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.game.GameServer;
import com.game.cs.CSServerConn;
import com.game.type.ServerStateType;

/**
 * @author jacken
 *
 */
public class FSReconnectJob implements Job
{

    /*
     * (non-Javadoc)
     * 
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException
    {
        if (GameServer.getInstance().getStatus() == ServerStateType.RUNNING.getValue())
        {
            CSServerConn conn = (CSServerConn) arg0.getMergedJobDataMap().get("object");
            conn.reconnect();
        }
    }
}
