/*
 * DataObject
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.database;

/**
 * @author jacken
 *
 */
public class DataObject
{
    private short op = 0;

    private long tick = 0;

    public DataObject(boolean insert)
    {
        if (insert)
        {
            this.op = DataOption.INSERT;
        }
    }

    public final void setOp(short option, long now)
    {
        if ((this.op == DataOption.INSERT) && (option == DataOption.UPDATE))
        {
            return;
        }
        if (option == DataOption.NONE)
        {
            if (tick > now)
            {
                if (this.op == DataOption.INSERT)
                    this.op = DataOption.UPDATE;
                return;
            }
            tick = 0;
        }
        else
        {
            tick = now;
        }
        this.op = option;
    }

    public final void setOp(short option)
    {
        setOp(option, System.currentTimeMillis());
    }

    public final short getOp()
    {
        return this.op;
    }

}
