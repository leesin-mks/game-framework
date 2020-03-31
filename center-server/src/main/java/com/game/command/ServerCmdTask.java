/*
 * ServerCmdTask
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.command;

import com.game.net.IClientConnection;

/**
 * @author jacken
 *
 */
public class ServerCmdTask extends AbstractServerTask
{
    private IClientConnection connect;

    /**
     * @param cmd
     * @param message
     */
    public ServerCmdTask(AbstractServerCmd cmd, byte[] message, IClientConnection connect)
    {
        super(cmd, message);
        this.connect = connect;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.niuniu.command.AbstractTask#execute()
     */
    @Override
    protected void execute()
    {
        this.cmd.executeConnect(connect, message);
    }

}
