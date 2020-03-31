/**
 * Date: 2014-7-31
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.game.log;

import java.sql.Connection;
import java.util.List;

/**
 * 
 * @author leesin
 * @param <T>
 * 
 */
public interface ILog<T>
{
    public boolean add(List<T> logs, Connection conn);
}
