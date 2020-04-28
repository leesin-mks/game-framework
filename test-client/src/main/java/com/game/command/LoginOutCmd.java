package com.game.command;

import com.game.component.ComponentManager;
import com.game.component.inf.IPlayerComponent;
import com.game.object.PressPlayer;
import com.game.pb.PlayerMsgProto.LoginMsgSC;
import com.game.pb.command.ProtocolOutProto.ProtocolOut;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

@ICode(code = ProtocolOut.USER_LOGIN_OUT_VALUE, desc = "退出登录")
public class LoginOutCmd extends AbstractPressCmd
{

    @Override
    public void execute(PressPlayer player, ByteString packet)
    {
        try
        {
            LoginMsgSC msg = LoginMsgSC.parseFrom(packet);
            if (msg.getStatus() == 1)
            {
                IPlayerComponent pc = (IPlayerComponent) ComponentManager.getInstance().getComponent(
                        IPlayerComponent.NAME);
                pc.addPlayer(player);
            }
            System.out.println("Login out: " + msg);
        }
        catch (InvalidProtocolBufferException e)
        {
            e.printStackTrace();
        }

    }

}
