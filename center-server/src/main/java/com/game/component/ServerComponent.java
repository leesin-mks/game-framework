package com.game.component;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.component.inf.IServerComponent;
import com.game.net.CommonMessage;
import com.game.pb.CenterMsgProto.CSForwardMsg;
import com.game.server.ServerClient;
import com.google.gson.Gson;

/**
 * @date 2020年03月31日 15:38
 * @auth zm
 */
public class ServerComponent implements IServerComponent
{
    private static Logger LOGGER = LoggerFactory.getLogger(ServerComponent.class);

    private Map<Integer, ServerClient> servers;

    private static final Gson gson = new Gson();

    @Override
    public String getName()
    {
        return NAME;
    }

    @Override
    public boolean initialize()
    {
        servers = new HashMap<>();
        return true;
    }

    @Override
    public boolean start()
    {
        return true;
    }

    @Override
    public void stop()
    {
        for (ServerClient server : servers.values())
        {
            server.getClientConnection().closeConnection(true);
        }
    }

    @Override
    public boolean reload()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.niuniu.component.inf.IServerComponent#sendToAll(com.bdsk.net.CommonMessage)
     */
    @Override
    public void sendToAll(CommonMessage message)
    {
        for (ServerClient server : servers.values())
        {
            server.send(message);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.niuniu.component.inf.IServerComponent#addServerClient(com.niuniu.server.ServerClient)
     */
    @Override
    public synchronized void addServerClient(ServerClient client)
    {
        servers.put(client.getServerID(), client);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.niuniu.component.inf.IServerComponent#removeServerClient(com.niuniu.server.ServerClient)
     */
    @Override
    public synchronized void removeServerClient(ServerClient client)
    {
        servers.remove(client.getServerID());
    }

    public void forwardMsg(CSForwardMsg msg)
    {
        ServerClient serverClient = servers.get(msg.getToServerID());
        if (serverClient == null)
        {
            LOGGER.warn("Can find server client: {}", msg.getToServerID());
            return;
        }
        CommonMessage commonMessage = new CommonMessage((short) msg.getCode());
        commonMessage.setBody(msg.getBody().toByteArray());
        serverClient.send(commonMessage);
    }
}
