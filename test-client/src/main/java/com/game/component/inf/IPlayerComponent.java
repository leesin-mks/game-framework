package com.game.component.inf;

import com.game.component.IComponent;
import com.game.object.PressPlayer;

/**
 * @date 2020年04月21日 14:29
 * @auth zm
 */
public interface IPlayerComponent extends IComponent {
	public static final String NAME = "PlayerComponent";

	void addPlayer(PressPlayer player);

	void ping();

}
