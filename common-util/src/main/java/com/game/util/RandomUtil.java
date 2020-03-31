package com.game.util;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil
{
    /**
     * 产生不重复数组
     * 
     * @param minValue
     *            起始值，包含
     * @param maxValue
     *            最大值，不包含
     * @param count
     *            数量
     * @return
     */
    public static int[] getRandomUnrepeatArray(int minValue, int maxValue, int count)
    {
        if (minValue >= maxValue)
        {
            return new int[0];
        }
        // 首先创建一个临时数组，数据为传入的数值
        int[] tempArray = new int[maxValue - minValue];
        for (int i = minValue; i < maxValue; i++)
        {
            tempArray[i - minValue] = i;
        }
        if (count >= tempArray.length)
        {
            return tempArray;
        }
        int[] resultRound = new int[count];
        for (int i = 0; i < count; i++)
        {
            int rand = ThreadLocalRandom.current().nextInt(tempArray.length - i);
            int tempVar = tempArray[rand];
            // 将原有数组随机出的下标所代表的值赋值给新数组
            resultRound[i] = tempVar;
            // 将临时数组的最后一个数与随机出来的数进行交换，保证数组的唯一性
            tempArray[rand] = tempArray[tempArray.length - 1 - i];
            tempArray[tempArray.length - 1 - i] = tempVar;
        }
        return resultRound;
    }

    /**
     * 按给定的总概率随机一个index
     * 
     * @param totalRandomAry
     * @param 最大下标
     * @return
     */
    public static int getRandomIndexOfArrayByTotalRate(int[] totalRandomAry, int maxIndex)
    {
        maxIndex = Math.min(totalRandomAry.length - 1, maxIndex);
        int ok = ThreadLocalRandom.current().nextInt(totalRandomAry[maxIndex]);
        for (int j = 0; j <= maxIndex; j++)
        {
            if (ok < totalRandomAry[j])
            {
                return j;
            }
        }
        return -1;
    }

    /**
     * 按给定的总概率随机一个index
     * 
     * @param totalRandomAry
     * @param 最大下标
     * @return
     */
    public static int getRandomIndexOfArrayByTotalRate(List<Integer> totalRandomList, int maxIndex)
    {
        maxIndex = Math.min(totalRandomList.size() - 1, maxIndex);
        int ok = ThreadLocalRandom.current().nextInt(totalRandomList.get(maxIndex));
        for (int j = 0; j <= maxIndex; j++)
        {
            if (ok < totalRandomList.get(j))
            {
                return j;
            }
        }
        return -1;
    }

    public static int randomRange(int min, int max)
    {
        int start = Math.min(min, max);
        int end = Math.max(min, max);
        int pitch = end - start;
        if (pitch == 0)
        {
            return start;
        }
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
