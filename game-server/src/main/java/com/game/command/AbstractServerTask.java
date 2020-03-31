/*
 * AbstractServerTask
 *
 * 2017年6月24日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
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
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());
    /**
     * 指令
     */
    protected AbstractServerCmd cmd;

    /**
     * 通用消息
     */
    protected byte[] message;

    /**
     * 
     */
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
