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

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.Trigger.TriggerState;

import com.game.component.IComponent;

/**
 * @author leesin
 *
 */
public interface ITimerComponent extends IComponent
{
    String NAME = "TimerComponent";

    /**
     * 增加定时任务，间隔毫秒单位执行
     * 
     * @param jobName
     *            name
     * @param job
     *            class extends com.quartz.Job
     * @param millis
     *            interval time
     *            重复间隔时间 毫秒
     */
    void addJob(String jobName, Class<? extends Job> job, long millis);

    /**
     * 增加延迟delay任务，延迟毫秒单位执行，只执行一次
     * 
     * @param jobName
     *            name
     * @param job
     *            class extends com.quartz.Job
     * @param delay
     *            延迟执行时间 毫秒
     */
    void addDelayJob(String jobName, Class<? extends Job> job, long delay);

    /**
     * 增加延迟delay任务，延迟毫秒单位执行，只执行一次
     * 
     * @param dataMap
     *            上下文参数
     * @param jobName name
     * @param job class extends com.quartz.Job
     * @param delay
     *            延迟执行时间 毫秒
     */
    void addDelayJob(JobDataMap dataMap, String jobName, Class<? extends Job> job,
            long delay);

    /**
     * 增加延迟delay任务，延迟毫秒单位执行，可指定重复执行次数和间隔时间
     * 
     * @param jobName
     *            name
     * @param job
     *            class extends com.quartz.Job
     * @param delay
     *            延迟执行时间 毫秒
     * @param repeat
     *            重复次数，-1表示一直重复；1表示重复一次，总共执行两次
     * @param millis
     *            重复间隔时间 毫秒
     */
    void addDelayJob(String jobName, Class<? extends Job> job, long delay, int repeat,
            long millis);

    /**
     * 增加延迟delay任务，延迟毫秒单位执行，可指定重复执行次数和间隔时间
     * 
     * @param jobName
     *            name
     * @param job
     *            class extends com.quartz.Job
     * @param delay
     *            延迟执行时间 毫秒
     * @param repeat
     *            重复次数，-1表示一直重复；1表示重复一次，总共执行两次
     * @param millis
     *            重复间隔时间 毫秒
     */
    void addDelayJob(JobDataMap dataMap, String jobName, Class<? extends Job> job,
            long delay, int repeat, long millis);

    /**
     * 增加定时任务，间隔方式用 quartz表达式 定义
     * 
     * @param jobName
     *            name
     * @param job class extends com.quartz.Job
     * @param time
     *            quartz表达式
     */
    void addJob(String jobName, Class<? extends Job> job, String time);

    /**
     * 删除任务
     * 
     * @param jobName
     *            name
     */
    void deleteJob(String jobName);

    /**
     * 增加延迟delay任务，延迟毫秒单位执行，可指定重复执行次数和间隔时间
     * 
     * @param jobName
     *            name
     * @param job
     * @param delay
     *            延迟执行时间 毫秒
     * @param repeat
     *            重复次数，-1表示一直重复；1表示重复一次，总共执行两次
     * @param millis
     *            重复间隔时间 毫秒
     *            object设置参数
     */
    void addDelayJob(String jobName, Class<? extends Job> job, long delay, int repeat,
            long millis, Object object);

    /**
     * 任务是否存在
     * 
     * @param jobName
     *            name
     * 
     * @return job exist
     */
    boolean isJobExist(String jobName);

    /**
     * 暂停任务
     * 
     * @param jobName
     *            name
     */
    void pauseJob(String jobName);

    /**
     * 恢复任务
     * 
     * @param jobName
     *            name
     */
    void resumeJob(String jobName);

    /**
     * 获取任务状态
     * 
     * @param jobName
     *            name
     * @return job status
     */
    TriggerState getJobState(String jobName);
}
