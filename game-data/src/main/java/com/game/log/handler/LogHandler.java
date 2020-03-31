/**
 * Date: 2014-7-31
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.game.log.handler;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * @author saly.bao
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface LogHandler
{
    public String name();

    public String description();
}
