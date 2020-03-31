/*
 * ServerState
 *
 * 2018年11月12日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
 */
package com.game.type;

/**
 * @author leesin
 *
 */
public enum ServerStateType
{
    STOP(0),          // 停止
    RUNNING(1),       // 运行中
    PREPARE_STOP(2),  // 准备停服
    SHUTTING_DOWN(3);  // 停服中
   

    private final int type;

    private ServerStateType(int type)
    {
        this.type = type;
    }

    public int getValue()
    {
        return type;
    }
}
