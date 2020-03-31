/*
 * SelfDrivenAction
 *
 * 2016年2月22日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.util.StackMessagePrint;

/**
 * @author jacken
 *         抽象的自驱动action类，所有antion需继承此类
 */
public abstract class SelfDrivenAction implements Runnable
{
    protected static final Logger logger = LoggerFactory.getLogger(SelfDrivenAction.class.getName());

    private SelfDrivenTaskQueue<SelfDrivenAction> actionQueue;

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
    public void setActionQueue(SelfDrivenTaskQueue<SelfDrivenAction> actionQueue)
    {
        this.actionQueue = actionQueue;
    }

    public abstract void execute();
}
