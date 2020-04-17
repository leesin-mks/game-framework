package com.game.component;

import com.game.command.AbstractCommandComponent;
import com.game.command.ICode;

public class PressCommandComponent extends AbstractCommandComponent<ICode>
{

    @Override
    public String getCommandPacketName()
    {
        return "com.game.command";
    }

    @Override
    public Class<ICode> getAnnotationClass()
    {
        return ICode.class;
    }

    @Override
    public Short getNodeType(ICode annotation)
    {
        return annotation.code();
    }

}
