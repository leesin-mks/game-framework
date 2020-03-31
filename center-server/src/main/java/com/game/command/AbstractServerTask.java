/*
 * AbstractTask
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jacken
 *
 */
public abstract class AbstractServerTask implements Runnable
{
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractServerTask.class);
    /**
     * 指令
     */
    protected AbstractServerCmd cmd;

    /**
     * 通用消息
     */
    protected byte[] message;

    public AbstractServerTask(AbstractServerCmd cmd, byte[] message)
    {
        this.cmd = cmd;
        this.message = message;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run()
    {
        this.execute();
    }

    protected abstract void execute();

}
