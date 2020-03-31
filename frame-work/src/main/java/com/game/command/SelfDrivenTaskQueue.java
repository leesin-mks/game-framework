/*
 * SelfDrivenTaskQueue
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.command;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jacken
 *
 */
public class SelfDrivenTaskQueue<Task extends Runnable>
{
    /** 执行Task的线程池 */
    private ExecutorService threadPool = null;

    /**
     * 任务队列。队头元素是正在执行的任务。
     */
    private Queue<Task> taskQueue = null;

    /** 运行锁，用于确保同时最多只能有一个任务在执行。任务队列本身是线程安全的。 */
    private ReentrantLock runningLock = null;

    public SelfDrivenTaskQueue(ExecutorService exeService)
    {
        this.threadPool = exeService;

        // 使用无锁线程安全队列
        this.taskQueue = new ConcurrentLinkedQueue<Task>();

        this.runningLock = new ReentrantLock();
    }

    /**
     * 往任务队列中添加任务。
     * 
     * @param task
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
            if (this.taskQueue.isEmpty() == false)
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
