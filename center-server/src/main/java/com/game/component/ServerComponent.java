package com.game.component;

import java.util.HashSet;
import java.util.Set;

import com.game.server.ServerClient;
import com.game.component.inf.IServerComponent;
import com.game.net.CommonMessage;
import com.google.gson.Gson;

/**
 * @date 2020年03月31日 15:38
 * @auth zm
 */
public class ServerComponent implements IServerComponent
{
    private Set<ServerClient> servers;

    private static final Gson gson = new Gson();

    @Override
    public String getName()
    {
        return NAME;
    }

    @Override
    public boolean initialize()
    {
        servers = new HashSet<ServerClient>();
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
        for (ServerClient server : servers)
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
        for (ServerClient server : servers)
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
        servers.add(client);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.niuniu.component.inf.IServerComponent#removeServerClient(com.niuniu.server.ServerClient)
     */
    @Override
    public synchronized void removeServerClient(ServerClient client)
    {
        servers.remove(client);
    }

}
