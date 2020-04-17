package com.game.type;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.common.reflect.TypeToken;

/**
 * @date 2020年04月16日 15:33
 * @auth zm
 */
public class JsonTypeToken
{
    public static final Type MAP_STRING_STRING = new TypeToken<Map<String, String>>()
    {
        private static final long serialVersionUID = 1L;
    }.getType();
}
