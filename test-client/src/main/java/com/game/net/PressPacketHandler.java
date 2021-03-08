/*
 * Copyright 2016-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
