/*
 * ICommand
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.command;

import com.google.protobuf.ByteString;
import com.game.net.IClientConnection;

/**
 * @author jacken
 *
 */
public interface ICommand
{
    public abstract void execute(IClientConnection client, byte[] packet)
            throws Exception;

    public abstract void execute(IClientConnection client, ByteString packet, short code)
            throws Exception;
}
