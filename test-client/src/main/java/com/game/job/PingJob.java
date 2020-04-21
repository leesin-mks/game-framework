package com.game.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.game.component.ComponentManager;
import com.game.component.inf.IPlayerComponent;

/**
 * @date 2020年04月21日 14:27
 * @auth zm
 */
public class PingJob implements Job {
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		IPlayerComponent pc = (IPlayerComponent) ComponentManager.getInstance().getComponent(IPlayerComponent.NAME);
		pc.ping();
	}
}
