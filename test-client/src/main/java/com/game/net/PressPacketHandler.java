package com.game.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.game.component.ComponentManager;
import com.game.net.IServerConnector;
import com.game.net.IServerPacketHandler;
import com.game.pb.CommonMsgProto.CommonMsgPB;
import com.game.util.StringUtil;
import com.game.client.PressNettyWSServerConnector;
import com.game.command.AbstractPressCmd;
import com.game.component.PressCommandComponent;
public class PressPacketHandler implements IServerPacketHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(PressPacketHandler.class);

	@Override
	public void process(IServerConnector conn, Object packet) {
		CommonMsgPB pb = (CommonMsgPB) packet;
		PressCommandComponent pressCommandComponent = (PressCommandComponent) ComponentManager.getInstance().getComponent(PressCommandComponent.NAME);
		AbstractPressCmd cmd = (AbstractPressCmd) pressCommandComponent.getCommand((short) pb.getCode());
		if (cmd == null) {
			LOGGER.info("Can not find cmd code: {}", pb.getCode());
			return;
		}
		if (StringUtil.isNotNullAndNotEmpty(pb.getErrorMsg())) {
			LOGGER.error("Server error msg: {}", pb.getErrorMsg());
			return;
		}
		try {
			cmd.execute(((PressNettyWSServerConnector) conn).getPlayer(), pb.getBody());
		} catch (Exception e) {
			LOGGER.error("Execute cmd error:", e);
		}

	}

}
