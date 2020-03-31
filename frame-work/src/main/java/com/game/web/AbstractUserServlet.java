/*
 * AbstractUserServlet
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.web;

/**
 * @author jacken
 *
 */
public abstract class AbstractUserServlet extends BaseHandlerServlet
{
    /**
     * 
     */
    private static final long serialVersionUID = -3669019500620243561L;

    /*
     * (non-Javadoc)
     * 
     * @see com.bdsk.web.IWebHandler#execute(java.lang.String, java.lang.String[])
     */
    @Override
    public boolean checkRequestIP(String ip)
    {
        return true;
    }

}
