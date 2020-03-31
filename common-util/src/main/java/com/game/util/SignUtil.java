package com.game.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SignUtil.class);

    public static String getSign(Map<String, String> map, String key)
    {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, String> entry : map.entrySet())
        {
            if (entry.getValue() != null)
            {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++)
        {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "secretkey=" + key;
        result = HashUtil.md5(result).toLowerCase();
        return result;
    }

    public static String getExchangeSign(Map<String, String> map, String key)
    {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, String> entry : map.entrySet())
        {
            if (entry.getValue() != null)
            {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++)
        {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + key;
        LOGGER.error(result);
        result = HashUtil.md5(result).toLowerCase();
        return result;
    }
}
