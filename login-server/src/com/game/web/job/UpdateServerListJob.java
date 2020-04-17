/*
 * UpdateServerListJob
 *
 * 2017年8月9日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
 */
package com.game.web.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.component.ComponentManager;
import com.game.component.inf.IServerListComponent;

/**
 * @author jacken
 *
 */
public class UpdateServerListJob implements Job
{
    private final Logger LOGGER = LoggerFactory.getLogger(UpdateServerListJob.class.getName());

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException
    {
        IServerListComponent sl = (IServerListComponent) ComponentManager.getInstance().getComponent(
                IServerListComponent.NAME);
        sl.updateServerList();
        LOGGER.info("更新服务器列表...");
    }
}
