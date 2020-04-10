/*
 * ServerMessageHandler
 *
 * 2017年6月16日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
 */
package com.game.cs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.command.AbstractServerCmd;
import com.game.command.CSCmdTask;
import com.game.component.CSCommandComponent;
import com.game.component.ComponentManager;
import com.game.net.CommonMessage;
import com.game.net.IServerConnector;
import com.game.net.IServerPacketHandler;

/**
 * @author jacken
 *
 */
public class CSMessageHandler implements IServerPacketHandler
{
    private static Logger LOGGER = LoggerFactory.getLogger(CSMessageHandler.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.net.IServerPacketHandler#process(com.bdsk.net.IServerConnector, java.lang.Object)
     */
    @Override
    public void process(IServerConnector client, Object packet)
    {
        CommonMessage message = ((CommonMessage) packet);
        short code = message.getCode();
        // 从组件管理器中间调用
        CSCommandComponent cm = (CSCommandComponent) ComponentManager.getInstance().getComponent(
                CSCommandComponent.NAME);
        if (cm == null)
        {
            LOGGER.error("CommandModule not found");
            return;
        }

        AbstractServerCmd cmd = (AbstractServerCmd) cm.getCommand(code);
        if (cmd == null)
        {
            LOGGER.error(" Can not found code = " + code + ",drop this packet.");
            return;
        }

        try
        {
            ((CSServerConn) client).addCommandTask(new CSCmdTask(cmd, message.getBody(), client));
            // cmd.execute(client, message.getBody());
        }
        catch (Exception e)
        {
            LOGGER.error("", e);
        }

    }

}
