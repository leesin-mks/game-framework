/*
 * FSCmdComponent
 *
 * 2017年6月16日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
 */
package com.game.component;

import com.game.command.AbstractCommandComponent;
import com.game.command.ICode;

/**
 * @author jacken
 *
 */
public class CSCommandComponent extends AbstractCommandComponent<ICode>
{

    public static String NAME = "CSCommand";

    @Override
    public String getName()
    {
        return NAME;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.command.AbstractCommandComponent#getCommandPacketName()
     */
    @Override
    public String getCommandPacketName()
    {
        return "com.game.cs.command";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.command.AbstractCommandComponent#getAnnotationClass()
     */
    @Override
    public Class<ICode> getAnnotationClass()
    {
        return ICode.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.command.AbstractCommandComponent#getNodeType(java.lang.annotation.Annotation)
     */
    @Override
    public Short getNodeType(ICode annotation)
    {
        return annotation.code();
    }
}
