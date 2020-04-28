package com.game.component.inf;

import com.game.component.IComponent;
import com.game.net.CommonMessage;
import com.google.protobuf.ByteString;

/**
 * @date 2020年03月31日 17:44
 * @auth zm
 */
public interface ICSComponent extends IComponent
{
    public static final String NAME = "CSComponent";

    void ping();

    void sendToAll(CommonMessage message);

    void forwardMessage(int toServer, byte[] packet, short code);

    void forwardMessage(int toServer, ByteString packet, short code);

    void msgToUser(int toServer, ByteString packet);
}
