/*
 * AbstractTask
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.log.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.command.SelfDrivenTaskQueue;
import com.game.util.StackMessagePrint;

/**
 * @author jacken
 * @param <T>
 *
 */
public abstract class AbstractTask<T> implements Runnable
{
    protected static final Logger logger = LoggerFactory.getLogger(AbstractTask.class.getName());

    protected T info;

    private SelfDrivenTaskQueue<Runnable> actionQueue;

    public AbstractTask(T info)
    {
        this.info = info;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run()
    {
        if (actionQueue != null)
        {
            try
            {
                execute();
            }
            catch (Exception e)
            {
                String errorMsg = String.format("action error:%n%s%n",
                        StackMessagePrint.printErrorTrace(e));
                logger.error(errorMsg, e);
            }
            finally
            {
                // 不论是否成功执行，均移除此action
                actionQueue.complete();
            }
        }
    }

    /**
     * @param actionQueue
     *            the actionQueue to set
     */
    public void setActionQueue(SelfDrivenTaskQueue<Runnable> actionQueue)
    {
        this.actionQueue = actionQueue;
    }

    protected abstract void execute();

}
