/*
 * Copyright 2016-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.game.timer;

import java.util.Date;

import org.quartz.*;
import org.quartz.Trigger.TriggerState;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leesin
 *
 */
public class TimerComponent implements ITimerComponent
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TimerComponent.class);

    private static Scheduler scheduler;

    @Override
    public String getName()
    {
        return NAME;
    }

    @Override
    public boolean initialize()
    {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try
        {
            scheduler = schedulerFactory.getScheduler();
        }
        catch (SchedulerException e)
        {
            LOGGER.error("Init timer component error: ", e);
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
            LOGGER.error("Timer close error: ", e);
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
            LOGGER.error("Add deal job error: {}, message: {}", jobName, e);
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
            LOGGER.error("Add deal job error: {}, message: {}", jobName, e);
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
            LOGGER.error("Add job error: {}, message: {}", jobName, e);
        }
    }

    @Override
    public void deleteJob(String jobName)
    {
        try
        {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobName));
            JobKey key = new JobKey(jobName, "quartzGroup");
            scheduler.deleteJob(key);
            LOGGER.error("Delete: {}", jobName);
        }
        catch (SchedulerException e)
        {
            LOGGER.error("Delete job error: {}, message: {}", jobName, e);
        }
    }

    @Override
    public void addDelayJob(String jobName, Class<? extends Job> job, long delay, int repeat, long millis,
            Object object)
    {
        try
        {
            LOGGER.info("添加定时任务: {}", jobName);
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
            LOGGER.error("Add deal job error: {}, message: {}", jobName, e);
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
            LOGGER.error("Check job error: {}, message: {}", jobName, e);
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
            LOGGER.error("Pause job error: {}, message: {}", jobName, e);
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
            LOGGER.error("Resume job error: {}, message: {}", jobName, e);
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
            LOGGER.error("Get job status error: {}, message: {}", jobName, e);
        }
        return TriggerState.NONE;
    }
}
