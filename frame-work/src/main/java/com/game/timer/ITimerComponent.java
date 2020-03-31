/*
 * ITimerComponent
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.timer;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.Trigger.TriggerState;

import com.game.component.IComponent;

/**
 * @author jacken
 *
 */
public interface ITimerComponent extends IComponent
{
    public static final String NAME = "TimerComponent";

    /**
     * 增加定时任务，间隔毫秒单位执行
     * 
     * @param jobName
     * @param job
     * @param millis
     *            重复间隔时间 毫秒
     */
    public void addJob(String jobName, Class<? extends Job> job, long millis);

    /**
     * 增加延迟delay任务，延迟毫秒单位执行，只执行一次
     * 
     * @param jobName
     * @param job
     * @param delay
     *            延迟执行时间 毫秒
     */
    public void addDelayJob(String jobName, Class<? extends Job> job, long delay);

    /**
     * 增加延迟delay任务，延迟毫秒单位执行，只执行一次
     * 
     * @param dataMap
     *            上下文参数
     * @param jobName
     * @param job
     * @param delay
     *            延迟执行时间 毫秒
     */
    public void addDelayJob(JobDataMap dataMap, String jobName, Class<? extends Job> job,
            long delay);

    /**
     * 增加延迟delay任务，延迟毫秒单位执行，可指定重复执行次数和间隔时间
     * 
     * @param jobName
     * @param job
     * @param delay
     *            延迟执行时间 毫秒
     * @param repeat
     *            重复次数，-1表示一直重复；1表示重复一次，总共执行两次
     * @param millis
     *            重复间隔时间 毫秒
     */
    public void addDelayJob(String jobName, Class<? extends Job> job, long delay, int repeat,
            long millis);

    /**
     * 增加延迟delay任务，延迟毫秒单位执行，可指定重复执行次数和间隔时间
     * 
     * @param jobName
     * @param job
     * @param delay
     *            延迟执行时间 毫秒
     * @param repeat
     *            重复次数，-1表示一直重复；1表示重复一次，总共执行两次
     * @param millis
     *            重复间隔时间 毫秒
     */
    public void addDelayJob(JobDataMap dataMap, String jobName, Class<? extends Job> job,
            long delay, int repeat, long millis);

    /**
     * 增加定时任务，间隔方式用 quartz表达式 定义
     * 
     * @param jobName
     * @param job
     * @param time
     *            quartz表达式
     */
    public void addJob(String jobName, Class<? extends Job> job, String time);

    /**
     * 删除任务
     * 
     * @param jobName
     */
    public void deleteJob(String jobName);

    /**
     * 增加延迟delay任务，延迟毫秒单位执行，可指定重复执行次数和间隔时间
     * 
     * @param jobName
     * @param job
     * @param delay
     *            延迟执行时间 毫秒
     * @param repeat
     *            重复次数，-1表示一直重复；1表示重复一次，总共执行两次
     * @param millis
     *            重复间隔时间 毫秒
     *            object设置参数
     */
    public void addDelayJob(String jobName, Class<? extends Job> job, long delay, int repeat,
            long millis, Object object);

    /**
     * 任务是否存在
     * 
     * @param jobName
     *            任务名
     * @return
     */
    public boolean isJobExist(String jobName);

    /**
     * 暂停任务
     * 
     * @param jobName
     *            任务名
     */
    public void pauseJob(String jobName);

    /**
     * 恢复任务
     * 
     * @param jobName
     *            任务名
     */
    public void resumeJob(String jobName);

    /**
     * 获取任务状态
     * 
     * @param jobName
     *            任务名
     * @return
     */
    public TriggerState getJobState(String jobName);
}
