/*
 * AbstractTask
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game;

import com.game.command.AbstractUserCmd;
import com.google.protobuf.ByteString;

/**
 * @author jacken
 *
 */
public abstract class AbstractTask implements Runnable
{
    /**
     * 指令
     */
    protected AbstractUserCmd cmd;

    /**
     * 通用消息
     */
    protected ByteString message;

    public AbstractTask(AbstractUserCmd cmd, ByteString message)
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
