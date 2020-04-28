package com.game.Job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import com.game.component.ComponentManager;
import com.game.component.inf.IPlayerComponent;

/**
 * @date 2020年04月21日 14:27
 * @auth zm
 */
public class PlayerJob implements Job
{
    @Override
    public void execute(JobExecutionContext jobExecutionContext)
    {
        IPlayerComponent pc = (IPlayerComponent) ComponentManager.getInstance().getComponent(IPlayerComponent.NAME);
        pc.clearOfflinePlayer();
        pc.ping();
    }
}
