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

import com.game.objec.GamePlayer;
import com.game.util.StackMessagePrint;
import com.google.protobuf.ByteString;

/**
 * @author jacken
 *
 */
public class UserCmdTask extends AbstractTask
{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCmdTask.class);

    protected GamePlayer player;

    /**
     * @param cmd
     * @param message
     */
    public UserCmdTask(AbstractUserCmd cmd, ByteString message, GamePlayer player)
    {
        super(cmd, message);
        this.player = player;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.niuniu.command.AbstractTask#execute()
     */
    @Override
    protected void execute()
    {
        try
        {
            this.cmd.execute(this.player, this.message);
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
