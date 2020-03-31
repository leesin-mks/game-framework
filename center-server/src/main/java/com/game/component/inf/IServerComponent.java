package com.game.component.inf;

import com.game.component.IComponent;
import com.game.net.CommonMessage;
import com.game.server.ServerClient;

/**
 * @date 2020年03月31日 15:29
 * @auth zm
 */
public interface IServerComponent extends IComponent
{
    public static final String NAME = "CenterServerComponent";

    void addServerClient(ServerClient client);

    void removeServerClient(ServerClient client);

    void sendToAll(CommonMessage message);
}
