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

/**
 * 
 */
package com.game.util;

import java.util.Arrays;

/**
 * @author leesin
 *
 */
public class ArrayUtil
{

    public static void FillTwoDimensionArray(int[][] arr, int value)
    {
        for (int[] ints : arr)
        {
            Arrays.fill(ints, value);
        }
    }

    public static void FillTwoDimensionArray(boolean[][] arr, boolean value)
    {
        for (boolean[] booleans : arr)
        {
            Arrays.fill(booleans, value);
        }
    }

    public static void FillTwoDimensionArray(float[][] arr, float value)
    {
        for (float[] floats : arr)
        {
            Arrays.fill(floats, value);
        }
    }

    public static void fillTwoDimensionArray(long[][] arr, long value)
    {
        for (long[] longs : arr)
        {
            Arrays.fill(longs, value);
        }
    }
}
