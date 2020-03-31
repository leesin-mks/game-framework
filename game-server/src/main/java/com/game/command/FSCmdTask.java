/*
 * FSCmdTask
 *
 * 2017年6月24日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
 */
package com.game.command;

import com.game.cs.CSServerConn;
import com.game.net.IServerConnector;
import com.game.util.StackMessagePrint;

/**
 * @author jacken
 *
 */
public class FSCmdTask extends AbstractServerTask
{

    protected IServerConnector server;

    /**
     * @param cmd
     * @param message
     */
    public FSCmdTask(AbstractServerCmd cmd, byte[] message, IServerConnector server)
    {
        super(cmd, message);
        this.server = server;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.niuniu.command.AbstractServerTask#execute()
     */
    @Override
    protected void execute()
    {
        try
        {
            this.cmd.execute((CSServerConn) server, this.message);
        }
        catch (Throwable e)
        {
            LOGGER.error(StackMessagePrint.printErrorTrace(e));
        }
        finally
        {
            ((CSServerConn) server).finishOneCommandTask();
        }
    }

}
