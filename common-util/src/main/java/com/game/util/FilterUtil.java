package com.game.util;

public class FilterUtil
{
    public static String filterEmoji(String source)
    {

        if (source == null)
        {
            return null;
        }
        source = source.replaceAll("[^\\u0000-\\uFFFF]", "");
        return source;
    }
}
