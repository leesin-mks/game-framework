package com.game.cs.command;

import com.game.command.AbstractCommandComponent;
import com.game.command.AbstractServerCmd;
import com.game.command.AbstractUserCmd;
import com.game.command.CSProtocol;
import com.game.command.ICode;
import com.game.command.ServerCmdTask;
import com.game.command.UserCmdTask;
import com.game.component.ComponentManager;
import com.game.component.inf.IPlayerComponent;
import com.game.cs.CSServerConn;
import com.game.objec.GamePlayer;
import com.game.pb.CenterMsgProto.ForwardMsg;
import com.game.pb.CommonMsgProto.CommonMsgPB;
import com.game.pb.command.ProtocolInProto.ProtocolIn;

/**
 * @date 2020年04月16日 19:26
 * @auth zm
 */
@ICode(code = CSProtocol.FORWARD_MESSAGE, desc = "转发消息")
public class ForwardMsgCmd extends AbstractServerCmd
{
    private static AbstractCommandComponent<?> acc;

    private static IPlayerComponent pc;

    @Override
    public void execute(CSServerConn client, byte[] packet)
    {
        try
        {
            ForwardMsg msg = ForwardMsg.parseFrom(packet);
            CommonMsgPB pb = CommonMsgPB.parseFrom(msg.getPacket());
            short code = (short) pb.getCode();
            // 从组件管理器中间调用
            AbstractCommandComponent<?> cm = getAbstractCommandComponent();
            if (acc == null)
            {
                LOGGER.warn("Command module not found");
                return;
            }

            AbstractUserCmd cmd = (AbstractUserCmd) cm.getCommand(code);
            if (cmd == null)
            {
                LOGGER.warn("Can not find command: {}", code);
                return;
            }
            if (pb.getCode() == ProtocolIn.USER_LOGIN_VALUE)
            {
                ServerCmdTask task = new ServerCmdTask(cmd, pb.getBody(), client);
                ComponentManager.getInstance().getUserCmdThreadPool().submit(task);
            }
            else
            {
                GamePlayer player = getPlayerComponent().getPlayerByUserID(msg.getUserID());
                if (player == null)
                {
                    LOGGER.warn("Can not find player: {}, userID: {}", code, msg.getUserID());
                    return;
                }
                UserCmdTask task = new UserCmdTask(cmd, pb.getBody(), player);
                player.addCommandTask(task);
            }
        }
        catch (Exception e)
        {
            LOGGER.error("Parse packet error: ", e);
        }
    }

    private static AbstractCommandComponent<?> getAbstractCommandComponent()
    {
        if (acc == null)
        {
            acc = (AbstractCommandComponent<?>) ComponentManager.getInstance().getComponent(
                    AbstractCommandComponent.NAME);
        }
        return acc;
    }

    private static IPlayerComponent getPlayerComponent()
    {
        if (pc == null)
        {
            pc = (IPlayerComponent) ComponentManager.getInstance().getComponent(
                    IPlayerComponent.NAME);
        }
        return pc;
    }
}
