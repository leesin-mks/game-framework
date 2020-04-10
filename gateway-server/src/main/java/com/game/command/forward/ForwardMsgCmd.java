package com.game.command.forward;

import com.game.command.AbstractUserCmd;
import com.game.command.ICode;
import com.game.object.ProxyPlayer;
import com.game.pb.command.ProtocolInProto.ProtocolIn;
import com.google.protobuf.ByteString;

/**
 * @date 2020年04月09日 18:43
 * @auth zm
 */
@ICode(code = ProtocolIn.FORWARD_MESSAGE_VALUE, desc = "转发消息")
public class ForwardMsgCmd extends AbstractUserCmd {

	@Override
	public void execute(ProxyPlayer player, ByteString packet) {

	}
}
