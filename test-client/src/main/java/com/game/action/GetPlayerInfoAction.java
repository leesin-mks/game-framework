package com.game.action;

import com.game.command.SelfDrivenAction;
import com.game.object.PressPlayer;

public class GetPlayerInfoAction extends SelfDrivenAction
{

    private PressPlayer player;

    public GetPlayerInfoAction(PressPlayer player)
    {
        this.player = player;
    }

    @Override
    public void execute()
    {

    }

}
