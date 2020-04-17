package com.game.object;

import com.game.net.PressClient;

public class PressPlayer {

	private PressClient client;

	public PressPlayer(PressClient client) {
		this.client = client;
	}

	public PressClient getClient() {
		return client;
	}

	public void setClient(PressClient client) {
		this.client = client;
	}

}
