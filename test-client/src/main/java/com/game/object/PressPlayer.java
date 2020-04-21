package com.game.object;

import com.game.net.PressClient;

public class PressPlayer
{

    private int userID;

    private PressClient client;

    public PressPlayer(int userID, PressClient client)
    {
        this.userID = userID;
        this.client = client;
    }

    public PressClient getClient()
    {
        return client;
    }

    public void setClient(PressClient client)
    {
        this.client = client;
    }

	public int getUserID() {
		return userID;
	}
}
