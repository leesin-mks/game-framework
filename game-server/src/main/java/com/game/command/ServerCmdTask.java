/*
 * ServerCmdTask
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.command;

import com.game.cs.CSServerConn;
import com.google.protobuf.ByteString;

/**
 * @author jacken
 *
 */
public class ServerCmdTask extends AbstractTask
{
    private CSServerConn connect;

    /**
     * @param cmd
     * @param message
     * @param connect
     */
    public ServerCmdTask(AbstractUserCmd cmd, ByteString message, CSServerConn connect)
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
