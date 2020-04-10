/*
 * ServerCmdTask
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.command;

import com.game.net.IClientConnection;
import com.google.protobuf.ByteString;

/**
 * @author jacken
 *
 */
public class ServerCmdTask extends AbstractTask
{
    private IClientConnection connect;

    /**
     * @param cmd
     * @param message
     * @param   connect
     */
    public ServerCmdTask(AbstractUserCmd cmd, ByteString message, IClientConnection connect)
    {
        super(cmd, message);
        this.connect = connect;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.game.command.AbstractTask#execute()
     */
    @Override
    protected void execute()
    {
        this.cmd.executeConnect(connect, message);
    }

}
