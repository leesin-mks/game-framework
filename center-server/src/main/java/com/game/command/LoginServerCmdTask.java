/*
 * LoginServerCmdTask
 *
 * 2017年6月24日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
 */
package com.game.command;

import com.game.server.ServerClient;
import com.game.util.StackMessagePrint;

/**
 * @author jacken
 *
 */
public class LoginServerCmdTask extends AbstractServerTask
{

    private ServerClient client;

    /**
     * @param cmd
     * @param message
     */
    public LoginServerCmdTask(AbstractServerCmd cmd, byte[] message, ServerClient client)
    {
        super(cmd, message);
        this.client = client;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.niuniu.command.AbstractServerTask#execute()
     */
    @Override
    protected void execute()
    {
        // cmd.exec(client, message);

        try
        {
            this.cmd.exec(this.client, this.message);
        }
        catch (Throwable e)
        {
            LOGGER.error(StackMessagePrint.printErrorTrace(e));
        }
        finally
        {
            client.finishOneCommandTask();
        }
    }

}
