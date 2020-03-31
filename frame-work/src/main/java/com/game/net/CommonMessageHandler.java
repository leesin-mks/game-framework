/*
 * CommonMessageHandler
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.command.AbstractCommandComponent;
import com.game.command.ICommand;
import com.game.component.ComponentManager;

/**
 * @author jacken
 *
 */
public class CommonMessageHandler implements IMessageHandler
{
    private String cmdCMPTName;
    private static Logger LOGGER = LoggerFactory.getLogger(CommonMessageHandler.class);

    /**
     * 
     */
    public CommonMessageHandler(String cmdCMPTName)
    {
        this.cmdCMPTName = cmdCMPTName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see IMessageHandler#process(IClientConnection, java.lang.Object)
     */
    @Override
    public void process(IClientConnection conn, Object packet)
    {
        CommonMessage message = ((CommonMessage) packet);
        short code = message.getCode();
        // 从组件管理器中间调用
        AbstractCommandComponent<?> cm = (AbstractCommandComponent<?>) ComponentManager.getInstance().getComponent(
                cmdCMPTName);
        if (cm == null)
        {
            LOGGER.error("CommandModule not found");
            return;
        }

        ICommand cmd = cm.getCommand(code);
        if (cmd == null)
        {
            LOGGER.error(" Can not found code = " + code + ",drop this packet.");
            return;
        }

        try
        {
            cmd.execute(conn, message.getBody());
        }
        catch (Exception e)
        {
            LOGGER.error("", e);
        }
    }

}
