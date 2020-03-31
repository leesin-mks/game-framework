/**
 * 
 */
package com.game.util;

import java.util.Arrays;

/**
 * @author HoJanHoi
 *
 */
public class ArrayUtil
{

    public static void FillTwoDimensionArray(int[][] arr, int value)
    {
        for (int i = 0; i < arr.length; i++)
        {
            Arrays.fill(arr[i], value);
        }
    }

    public static void FillTwoDimensionArray(boolean[][] arr, boolean value)
    {
        for (int i = 0; i < arr.length; i++)
        {
            Arrays.fill(arr[i], value);
        }
    }

    public static void FillTwoDimensionArray(float[][] arr, float value)
    {
        for (int i = 0; i < arr.length; i++)
        {
            Arrays.fill(arr[i], value);
        }
    }

    public static void FillTwoDimensionArray(long[][] arr, long value)
    {
        for (int i = 0; i < arr.length; i++)
        {
            Arrays.fill(arr[i], value);
        }
    }
}
