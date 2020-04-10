/*
 * CommandComponent
 *
 * 2016年2月24日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.component;

import com.game.command.AbstractCommandComponent;
import com.game.command.ICode;

/**
 * @author jacken
 *
 */
public class CommandComponent extends AbstractCommandComponent<ICode>
{
    /*
     * (non-Javadoc)
     * 
     * @see little.seven.command.AbstractCommandComponent#getCommandPacketName()
     */
    @Override
    public String getCommandPacketName()
    {
        return "com.game.server.command";
    }

    /*
     * (non-Javadoc)
     * 
     * @see little.seven.command.AbstractCommandComponent#getAnnotationClass()
     */
    @Override
    public Class<ICode> getAnnotationClass()
    {
        return ICode.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see little.seven.command.AbstractCommandComponent#getNodeType(java.lang.annotation.Annotation)
     */
    @Override
    public Short getNodeType(ICode annotation)
    {
        return annotation.code();
    }
}
