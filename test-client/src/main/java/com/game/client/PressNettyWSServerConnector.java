package com.game.client;

import com.game.net.IServerPacketHandler;
import com.game.net.netty.web.NettyWSServerConnector;
import com.game.object.PressPlayer;
import com.game.pb.CommonMsgProto.CommonMsgPB;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

public class PressNettyWSServerConnector extends NettyWSServerConnector
{

    private PressPlayer player;

    public PressNettyWSServerConnector(String address, int port, IServerPacketHandler packetHandler)
    {
        super(address, port, packetHandler);
    }

    public void sendTCP(CommonMsgPB.Builder message)
    {
        ByteBuf buf = Unpooled.wrappedBuffer(message.build().toByteArray());
        send(new BinaryWebSocketFrame(buf));
    }

    public PressPlayer getPlayer()
    {
        return player;
    }

    public void setPlayer(PressPlayer player)
    {
        this.player = player;
    }

}
