package com.game.component;

import java.util.HashMap;
import java.util.Map;

import com.game.component.inf.IPlayerComponent;
import com.game.job.PingJob;
import com.game.object.PressPlayer;
import com.game.pb.CommonMsgProto.CommonMsgPB;
import com.game.pb.command.ProtocolInProto.ProtocolIn;
import com.game.timer.ITimerComponent;
import com.google.protobuf.ByteString;

/**
 * @date 2020年04月21日 14:29
 * @auth zm
 */
public class PlayerComponent implements IPlayerComponent
{

    private Map<Integer, PressPlayer> playerMap;

    @Override
    public String getName()
    {
        return NAME;
    }

    @Override
    public boolean initialize()
    {
        playerMap = new HashMap<>();
        return true;
    }

    @Override
    public boolean start()
    {

        ITimerComponent timerComponent = (ITimerComponent) ComponentManager.getInstance().getComponent(
                ITimerComponent.NAME);
        timerComponent.addJob("PingJob", PingJob.class, "0/10 * * * * ?");
        return true;
    }

    @Override
    public void stop()
    {

    }

    @Override
    public boolean reload()
    {
        return false;
    }

    @Override
    public void addPlayer(PressPlayer player)
    {
        playerMap.put(player.getUserID(), player);
    }

    @Override
    public void ping()
    {
        CommonMsgPB.Builder builder = CommonMsgPB.newBuilder();
        builder.setCode(ProtocolIn.PING_VALUE);
        builder.setBody(ByteString.EMPTY);
        for (PressPlayer player : playerMap.values())
        {
            player.getClient().sendTCP(builder);
        }
    }
}
