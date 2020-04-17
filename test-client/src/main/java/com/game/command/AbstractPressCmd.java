package com.game.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.net.IClientConnection;
import com.game.object.PressPlayer;
import com.google.protobuf.ByteString;

public abstract class AbstractPressCmd implements ICommand
{

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractPressCmd.class.getName());

    public void execute(IClientConnection conn, byte[] packet)
    {

    }

    @Override
    public void execute(IClientConnection client, ByteString packet, short code)
    {

    }

    public abstract void execute(PressPlayer player, ByteString packet);
}
