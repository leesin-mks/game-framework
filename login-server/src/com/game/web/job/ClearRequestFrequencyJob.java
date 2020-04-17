package com.game.web.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import com.game.component.ComponentManager;
import com.game.component.inf.IDataCenterComponent;

public class ClearRequestFrequencyJob implements Job
{

    @Override
    public void execute(JobExecutionContext arg0)
    {
        IDataCenterComponent dcc = (IDataCenterComponent) ComponentManager.getInstance().getComponent(
                IDataCenterComponent.NAME);
        dcc.clearRequestJob();
    }

}
