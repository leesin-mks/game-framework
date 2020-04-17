package com.game.bean;

/**
 * @date 2020年04月10日 18:18
 * @auth zm
 */
public class ServerSession
{
    private int gameServerID;

    private int gateWayServerID;

    public int getGameServerID()
    {
        return gameServerID;
    }

    public void setGameServerID(int gameServerID)
    {
        this.gameServerID = gameServerID;
    }

    public int getGateWayServerID()
    {
        return gateWayServerID;
    }

    public void setGateWayServerID(int gateWayServerID)
    {
        this.gateWayServerID = gateWayServerID;
    }
}
