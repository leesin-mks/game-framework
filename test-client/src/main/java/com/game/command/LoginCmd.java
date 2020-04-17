package com.game.command;

import com.game.action.GetPlayerInfoAction;
import com.game.object.PressPlayer;
import com.game.pb.PlayerMsgProto.LoginMsgSC;
import com.game.pb.command.ProtocolOutProto.ProtocolOut;
import com.game.test.PressManager;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

@ICode(code = ProtocolOut.USER_LOGIN_VALUE, desc = "登录返回")
public class LoginCmd extends AbstractPressCmd
{

    @Override
    public void execute(PressPlayer player, ByteString packet)
    {
        try
        {
            LoginMsgSC msg = LoginMsgSC.parseFrom(packet);
            System.out.println("Login result: " + msg);
            PressManager.addAction(new GetPlayerInfoAction(player));
        }
        catch (InvalidProtocolBufferException e)
        {
            e.printStackTrace();
        }

    }

}
