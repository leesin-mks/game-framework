/*
 * IMessageModule
 *
 * 2016年2月22日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.module.inf;

/**
 * @author leesin
 *
 */
public interface IMessageModule
{
    void sendTCP(byte[] message);

    void sendLoginOutMessage(int status);

}
