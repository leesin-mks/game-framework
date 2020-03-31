/*
 * TimerComponent
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.timer;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jacken
 *
 */
public class TimerComponent implements ITimerComponent
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TimerComponent.class);

    private static Scheduler scheduler = null;

    private static SchedulerFactory schedulerFactory;

    @Override
    public String getName()
    {
        return NAME;
    }

    @Override
    public boolean initialize()
    {
        schedulerFactory = new StdSchedulerFactory();
        try
        {
            scheduler = schedulerFactory.getScheduler();
        }
        catch (SchedulerException e)
        {
            LOGGER.error("初始化定时器异常，", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean start()
    {
        return true;
    }

    @Override
    public void stop()
    {
        try
        {
            scheduler.shutdown();
        }
        catch (SchedulerException e)
        {
            LOGGER.error("定时器关闭异常，", e);
        }
    }

    @Override
    public boolean reload()
    {
        return false;
    }

    @Override
    public void addJob(String jobName, Class<? extends Job> job, long millis)
    {
        addDelayJob(jobName, job, millis, -1, millis);
    }

    @Override
    public void addDelayJob(String jobName, Class<? extends Job> job, long delay)
    {
        addDelayJob(jobName, job, delay, 0, 0L);
    }

    @Override
    public void addDelayJob(JobDataMap dataMap, String jobName, Class<? extends Job> job,
            long delay)
    {
        addDelayJob(dataMap, jobName, job, delay, 0, 0L);
    }

    @Override
    public void addDelayJob(JobDataMap dataMap, String jobName, Class<? extends Job> job,
            long delay, int repeat, long millis)
    {
        try
        {
            JobDetail jobDetail = JobBuilder.newJob(job).withIdentity(jobName, "quartzGroup").setJobData(
                    dataMap).build();

            SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, "quartzGroup").startAt(
                    new Date(new Date().getTime() + delay)).withSchedule(
                            SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(millis).withRepeatCount(
                                    repeat)).build();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        }
        catch (SchedulerException e)
        {
            LOGGER.error("添加定时器任务异常，", e);
        }
    }

    @Override
    public void addDelayJob(String jobName, Class<? extends Job> job, long delay, int repeat,
            long millis)
    {
        try
        {
            JobDetail jobDetail = JobBuilder.newJob(job).withIdentity(jobName, "quartzGroup").build();

            SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, "quartzGroup").startAt(
                    new Date(new Date().getTime() + delay)).withSchedule(
                            SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(millis).withRepeatCount(
                                    repeat)).build();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        }
        catch (SchedulerException e)
        {
            LOGGER.error("添加定时器任务异常，", e);
        }
    }

    @Override
    public void addJob(String jobName, Class<? extends Job> job, String time)
    {
        try
        {
            JobDetail jobDetail = JobBuilder.newJob(job).withIdentity(jobName, "quartzGroup").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, "quartzGroup").withSchedule(
                    CronScheduleBuilder.cronSchedule(time)).build();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        }
        catch (SchedulerException e)
        {
            LOGGER.error("添加定时器任务异常，", e);
        }
    }

    @Override
    public void deleteJob(String jobName)
    {
        try
        {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobName));// 停止触发器
            JobKey key = new JobKey(jobName, "quartzGroup");
            scheduler.deleteJob(key);// 删除任务
            LOGGER.error("删除定时任务: " + jobName);
        }
        catch (SchedulerException e)
        {
            LOGGER.error("删除定时器任务异常，", e);
        }
    }

    @Override
    public void addDelayJob(String jobName, Class<? extends Job> job, long delay, int repeat, long millis,
            Object object)
    {
        try
        {
            LOGGER.error("添加定时任务" + jobName);
            JobDetail jobDetail = JobBuilder.newJob(job).withIdentity(jobName, "quartzGroup").build();

            SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, "quartzGroup").startAt(
                    new Date(new Date().getTime() + delay)).withSchedule(
                            SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(millis).withRepeatCount(
                                    repeat)).build();
            jobDetail.getJobDataMap().put("object", object);
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        }
        catch (SchedulerException e)
        {
            LOGGER.error("添加定时器任务异常，", e);
        }
    }

    @Override
    public boolean isJobExist(String jobName)
    {
        try
        {
            return scheduler.checkExists(JobKey.jobKey(jobName, "quartzGroup"));
        }
        catch (SchedulerException e)
        {
            LOGGER.error("检查定时器任务存在异常, ", e);
        }
        return false;
    }

    @Override
    public void pauseJob(String jobName)
    {
        try
        {
            scheduler.pauseJob(JobKey.jobKey(jobName, "quartzGroup"));
        }
        catch (SchedulerException e)
        {
            LOGGER.error("暂停定时器任务异常, ", e);
        }
    }

    @Override
    public void resumeJob(String jobName)
    {
        try
        {
            scheduler.resumeJob(JobKey.jobKey(jobName, "quartzGroup"));
        }
        catch (SchedulerException e)
        {
            LOGGER.error("恢复定时器任务异常, ", e);
        }
    }

    public TriggerState getJobState(String jobName)
    {
        try
        {
            return scheduler.getTriggerState(TriggerKey.triggerKey(jobName, "quartzGroup"));
        }
        catch (SchedulerException e)
        {
            LOGGER.error("获取定时器任务状态异常, ", e);
        }
        return TriggerState.NONE;
    }
}
