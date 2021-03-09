/*
 * Copyright 2016-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.game.struct;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author leesin
 *
 */
public class KeyDescTreeMap<K extends Comparable<K>, V>extends TreeMap<K, V>
{
    private static final long serialVersionUID = 1840619921095383458L;

    public KeyDescTreeMap()
    {
        super(Comparator.reverseOrder());
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
