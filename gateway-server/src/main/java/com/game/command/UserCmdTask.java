/*
 * UserCmdTask
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.object.ProxyPlayer;
import com.game.util.StackMessagePrint;
import com.google.protobuf.ByteString;

/**
 * @author jacken
 *
 */
public class UserCmdTask extends AbstractTask
{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCmdTask.class);

    protected ProxyPlayer player;

    protected short code;

    /**
     * @param cmd
     * @param message
     */
    public UserCmdTask(AbstractUserCmd cmd, ByteString message, ProxyPlayer player, short code)
    {
        super(cmd, message);
        this.player = player;
        this.code = code;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.game.command.AbstractTask#execute()
     */
    @Override
    protected void execute()
    {
        try
        {
            // this.cmd.execute(this.player, this.message);
            this.cmd.execute(player, message, code);
        }
        catch (Throwable e)
        {
            LOGGER.error(StackMessagePrint.printErrorTrace(e));
        }
        finally
        {
            player.finishOneCommandTask();
        }
    }

}
