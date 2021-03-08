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

package com.game.util;

import java.util.Arrays;
import java.util.List;

public class NumberUtil
{
    public static boolean isOdd(int n)
    {
        return (n & 0x01) == 0;
    }

    public static int byteToInt(byte b)
    {
        return b & 0xFF;
    }

    /** 整数 n 的第 i 位是否为 1 */
    public static boolean getBit(int n, int i)
    {
        return ((n & (1 << i)) != 0);
    }

    /** 将整数 n 的第 i 位置为 1 */
    public static int setOne(int n, int i)
    {
        return (n | (1 << i));
    }

    /** 将整数n的多位的值置为1 */
    public static int setBitOne(int n, int... i)
    {
        for (int j : i)
            n = setBitOne(n, j);
        return n;
    }

    /** 将整数 n 的第 i 位置为 0 */
    public static int setZero(int n, int i)
    {
        int mask = ~(1 << i);
        return (n & (mask));
    }

    /** 将整数n的多位的值置为0 */
    public static int setBitZero(int n, int... i)
    {
        for (int j : i)
            n = setBitZero(n, j);
        return n;
    }

    public static List<Integer> longToint(long index)
    {
        int low32 = (int) index;
        int higt32 = (int) (index >> 32);
        return Arrays.asList(low32, higt32);
    }

    public static long intToLong(int i, int j)
    {
        long l1 = (j & 0x00000000ffffffffL) << 32;
        long l2 = i & 0x00000000ffffffffL;
        long l = l1 | l2;
        return l;
    }

    public static List<Integer> longTointByBit(long index, int bit)
    {
        // bit<=31
        int low32 = (((int) index) & ((1 << bit) - 1));
        int higt32 = (int) (index >> bit);
        // 在这里左移一位是因为项目中第一位被弃用
        return Arrays.asList(low32, higt32 << 1);
    }

    public static List<Integer> longTointByBit(long index)
    {
        // 取31位，避免与客户端交互出现负数
        return longTointByBit(index, 31);
    }

    public static long intToLong(int i, int j, int bit)
    {
        long l1 = ((long) j) << bit;
        long l2 = (long) i;
        long l = l1 | l2;
        return l;
    }

    public static void main(String[] args)
    {
        long k = (1L << 31) + (1L << 32);
        int g = 31;
        List<Integer> w = longTointByBit(k, g);
        long intToLong = intToLong(w.get(0), w.get(1), g);
        System.out.println(Integer.toBinaryString(w.get(0)) + "   " + Integer.toBinaryString(w.get(1)));
        System.out.println(k + "   " + w + "    " + intToLong + "   " + Long.toBinaryString(k) + "  " + intToLong);
    }

}
