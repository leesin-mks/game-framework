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

package com.game.command;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author leesin
 * @since 1.0.0
 */
public class SelfDrivenTaskQueue<Task extends Runnable>
{
    /** 执行Task的线程池 */
    private final ExecutorService threadPool;

    /**
     * 任务队列。队头元素是正在执行的任务。
     */
    private final Queue<Task> taskQueue;

    /** 运行锁，用于确保同时最多只能有一个任务在执行。任务队列本身是线程安全的。 */
    private final ReentrantLock runningLock;

    public SelfDrivenTaskQueue(ExecutorService exeService)
    {
        this.threadPool = exeService;

        // 使用无锁线程安全队列
        this.taskQueue = new ConcurrentLinkedQueue<>();

        this.runningLock = new ReentrantLock();
    }

    /**
     * 往任务队列中添加任务。
     * 
     * @param task
     *            task
     */
    public void add(Task task)
    {
        try
        {
            this.runningLock.lock();

            if (this.taskQueue.isEmpty())
            {
                this.taskQueue.add(task);

                // 没有任务在执行，开始执行新添加的。
                this.threadPool.submit(task);
            }
            else
            {
                // 有任务正在执行，将新任务添加到队列中，等待执行。
                this.taskQueue.add(task);
            }
        }
        finally
        {
            this.runningLock.unlock();
        }
    }

    /**
     * 完成一个任务。<br>
     * 任务完成的时候，必须调用本方法来驱动后续的任务，“自驱动”没有你想像中的智能哈~
     */
    public void complete()
    {
        try
        {
            this.runningLock.lock();

            // 移除已经完成的任务。
            this.taskQueue.remove();
            // 完成一个任务后，如果还有任务，则继续执行。
            if (!this.taskQueue.isEmpty())
            {
                this.threadPool.submit(this.taskQueue.peek());
            }
        }
        finally
        {
            this.runningLock.unlock();
        }
    }
}
