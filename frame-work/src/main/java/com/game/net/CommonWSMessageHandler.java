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

import com.game.pb.CommonMsgProto.CommonMsgPB;
import com.game.command.AbstractCommandComponent;
import com.game.command.ICommand;
import com.game.component.ComponentManager;
import com.game.pb.command.ProtocolInProto.ProtocolIn;

/**
 * @author jacken
 *
 */
public class CommonWSMessageHandler implements IMessageHandler
{
    private static Logger LOGGER = LoggerFactory.getLogger(CommonWSMessageHandler.class);

    /*
     * (non-Javadoc)
     * 
     * @see little.seven.net.IMessageHandler#process(little.seven.net.IClientConnection, java.lang.Object)
     */
    @Override
    public void process(IClientConnection conn, Object packet)
    {
        CommonMsgPB pb = ((CommonMsgPB) packet);
        short code = (short) pb.getCode();
        // 从组件管理器中间调用
        AbstractCommandComponent<?> cm = (AbstractCommandComponent<?>) ComponentManager.getInstance().getComponent(
                AbstractCommandComponent.NAME);
        if (cm == null)
        {
            LOGGER.warn("Command module not found");
            return;
        }

        ICommand cmd = cm.getCommand(code);
        if (cmd == null)
        {
            cmd = cm.getCommand((short) ProtocolIn.FORWARD_MESSAGE_VALUE);
        }

        try
        {
            cmd.execute(conn, pb.getBody(), code);
        }
        catch (Exception e)
        {
            LOGGER.error("", e);
        }
    }

}
