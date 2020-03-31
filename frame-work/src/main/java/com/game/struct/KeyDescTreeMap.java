/*
 * KeyDescTreeMap
 *
 * 2017年9月26日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
 */
package com.bdsk.struct;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Kissy
 *
 */
public class KeyDescTreeMap<K extends Comparable<K>, V>extends TreeMap<K, V>
{
    private static final long serialVersionUID = 1840619921095383458L;

    public KeyDescTreeMap()
    {
        super(new Comparator<K>()
        {
            @Override
            public int compare(K o1, K o2)
            {
                return o2.compareTo(o1);
            }
        });
    }

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append("{\n");
        for (Map.Entry<K, V> entry : this.entrySet())
        {
            s.append(String.format("%s = %s\n", entry.getKey(), entry.getValue()));
        }
        s.append("}\n");
        return s.toString();
    }
}
