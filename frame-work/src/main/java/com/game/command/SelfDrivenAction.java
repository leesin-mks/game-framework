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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.util.StackMessagePrint;

/**
 * @author leesin
 * @since 1.0.0
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
