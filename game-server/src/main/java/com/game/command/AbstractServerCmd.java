/*
 * AbstractFSCmd
 *
 * 2017年6月16日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
 */
package com.game.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.cs.CSServerConn;
import com.game.net.IClientConnection;
import com.google.protobuf.ByteString;

/**
 * @author jacken
 *
 */
public abstract class AbstractServerCmd implements ICommand
{
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.command.ICommand#execute(com.bdsk.net.IClientConnection, byte[])
     */
    @Override
    public void execute(IClientConnection client, byte[] packet) throws Exception
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.command.ICommand#execute(com.bdsk.net.IClientConnection, com.google.protobuf.ByteString)
     */
    @Override
    public void execute(IClientConnection client, ByteString packet, short code) throws Exception
    {
        // TODO Auto-generated method stub

    }

    public void execute(CSServerConn client, byte[] packet)
    {

    }
}
