package com.game.util;

public class OutPutUtil
{
    public static String lineSeparator;

    static
    {
        lineSeparator = java.security.AccessController.doPrivileged(
                new sun.security.action.GetPropertyAction("line.separator"));
    }

}
