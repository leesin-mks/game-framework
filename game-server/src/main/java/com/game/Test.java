package com.game;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

/**
 * @date 2020年05月06日 12:30
 * @auth zm
 */
public class Test
{
    private static Gson gson = new Gson();

    public static void main(String[] args)
    {
        // testConvert();
        // testMyAtoi();
        // testStrStr();
        testDivide();

    }

    public static void testDivide()
    {
        int dividend = -2147483648;
        int divisor = -1;
        System.out.println(divide(dividend, divisor));
    }

    public static int divide(int dividend, int divisor)
    {
        if (dividend == 0)
        {
            return 0;
        }
        long result = 0;
        long longDividend = dividend;
        long longDivisor = divisor;
        int sign = 0;
        if (longDividend < 0)
        {
            longDividend = -longDividend;
            sign++;
        }
        if (longDivisor < 0)
        {
            longDivisor = -longDivisor;
            sign++;
        }
        if (longDividend < longDivisor)
        {
            return 0;
        }
        if (longDividend == longDivisor)
        {
            return sign == 1 ? -1 : 1;
        }

        int index = 0;
        while (true)
        {
            result += longDivisor;
            if (result > longDividend)
            {
                break;
            }
            if (result == longDividend)
            {
                index++;
                break;
            }
            index++;
        }
        if (sign == 1)
        {
            index = -index;
        }
        if (index == Integer.MIN_VALUE)
        {
            return Integer.MAX_VALUE;
        }
        return index;
    }

    public static void testStrStr()
    {
        String haystack = "mississippi";
        String needle = "issipi";
        System.out.println(strStr(haystack, needle));
    }

    public static int strStr(String haystack, String needle)
    {
        if (haystack == null || needle == null)
        {
            return -1;
        }
        char[] hayArray = haystack.toCharArray();
        char[] needArray = needle.toCharArray();
        int hayLength = hayArray.length;
        int needLength = needArray.length;
        if (needArray.length == 0)
        {
            return 0;
        }
        if (hayArray.length == 0)
        {
            return -1;
        }
        if (hayArray.length < needArray.length)
        {
            return -1;
        }
        if (hayArray.length == 1)
        {
            return needArray.length == 1 && hayArray[0] == needArray[0] ? 0 : -1;
        }

        for (int i = 0; i < hayLength; i++)
        {
            reset:
            {
                if (hayLength - i < needLength)
                {
                    return -1;
                }
                for (int k = 0, n = i; k < needLength; k++)
                {
                    if (hayArray[n++] != needArray[k])
                    {
                        break reset;
                    }
                }
                return i;
            }
        }
        return -1;
    }

    public static void testMyAtoi()
    {
        long integerMax = Integer.MIN_VALUE - 1L;
        String input = "words and 987";
        // System.out.println(c);
        // input = String.valueOf(Long.MAX_VALUE);
        // BigInteger bi = new BigInteger(input.toString());
        /// System.out.println(String.valueOf(Integer.MIN_VALUE).length());
        // System.out.println(String.valueOf(Long.MAX_VALUE));
        // input = String.valueOf(integerMax);
        System.out.println(myAtoi(input));
    }

    public static int myAtoi(String str)
    {
        if (str == null || str.length() == 0)
        {
            return 0;
        }

        char[] sourceArr = str.toCharArray();
        if (sourceArr.length == 1)
        {
            char val = sourceArr[0];
            return val >= 48 && val <= 57 ? Integer.parseInt(str) : 0;
        }
        char val;
        char[] result = new char[sourceArr.length];
        int index = 0;
        boolean blank;
        for (int i = 0, len = sourceArr.length, times = len - 1; i < times; i++)
        {
            val = sourceArr[i];
            if (!(val == 43 || val == 45 || (val >= 48 && val <= 57)))
            {
                blank = val == 32;
                if (!blank && (i == 0 || (i > 0 && sourceArr[i - 1] == 32)))
                {
                    break;
                }
                continue;
            }
            result[index++] = val;
            for (int k = i + 1; k < len; k++)
            {
                val = sourceArr[k];
                if (val < 48 || val > 57)
                {
                    break;
                }
                result[index++] = val;
            }
            break;
        }
        int len = index;
        if (len == 0)
        {
            return 0;
        }
        else if (len == 1)
        {
            val = result[0];
            return (val == 43 || val == 45) ? 0 : Integer.parseInt(new String(result, 0, index));
        }
        else if (len < 10)
        {
            return Integer.parseInt(new String(result, 0, index));
        }

        BigInteger bi = new BigInteger(new String(result, 0, index));
        if (bi.compareTo(new BigInteger(String.valueOf(Integer.MIN_VALUE))) < 0)
        {
            return Integer.MIN_VALUE;
        }
        else if (bi.compareTo(new BigInteger(String.valueOf(Integer.MAX_VALUE))) > 0)
        {
            return Integer.MAX_VALUE;
        }
        return bi.intValue();
    }

    public static String convert(String s, int numRows)
    {
        if (s == null || s.length() <= numRows || numRows <= 1)
        {
            return s;
        }
        int sLength = s.length();
        int xLength = numRows - 2;
        int middleIndex = xLength;
        char[] sourceArr = s.toCharArray();
        char[] resultArr = new char[sourceArr.length];
        int index = 0;
        for (int i = 0; i < numRows; i++)
        {
            boolean zMiddle = i > 0 && i < numRows - 1;
            int skipIndex = i;
            int zMiddleIndex = 0;
            do
            {
                resultArr[index++] = sourceArr[skipIndex];
                if (zMiddle)
                {
                    zMiddleIndex += numRows + middleIndex;
                    int zIndex = zMiddleIndex - 1;
                    if (zIndex < sLength)
                    {
                        resultArr[index++] = sourceArr[zIndex];
                        zMiddleIndex += xLength - middleIndex;
                    }
                }
                skipIndex += numRows + xLength;
            }
            while (skipIndex < sLength && zMiddleIndex < sLength);
            if (zMiddle)
            {
                middleIndex = middleIndex - 1;
            }
        }
        return String.valueOf(resultArr);
    }

    public static void testConvert()
    {
        int numRows = 2;
        String s = "LE2";
        System.out.println(convert(s, numRows));
    }

    public static void testIsPalindrome()
    {
        int x = 1211;
        System.out.println(isPalindrome(x));
    }

    public static boolean isPalindrome(int x)
    {
        if (x < 0 || x % 10 == 0)
        {
            return false;

        }
        else if (x == 0)
        {
            return true;
        }
        else
        {
            int y = x;
            int n = 0;
            while (true)
            {
                n = y % 10 + n * 10;
                y = y / 10;
                if (y == 0)
                {
                    break;
                }
            }
            return n == x;
        }
    }

    public static boolean isPalindrome1(int x)
    {
        if (x < 0)
        {
            return false;

        }
        else if (x == 0)
        {
            return true;
        }
        else
        {
            List<Integer> list = new ArrayList<>();
            while (true)
            {
                int digit = x % 10;
                list.add(digit);
                x = x / 10;
                if (x == 0)
                {
                    break;
                }
            }
            for (int i = 0, endIndex = list.size() - 1, times = list.size() / 2; i < times; i++)
            {
                if (list.get(i) != list.get(endIndex - i))
                {
                    return false;
                }
            }
            return true;
        }
    }

    public static int lengthOfLongestSubstring(String s)
    {
        Map<String, Integer> statistic = new HashMap<>();
        char[] sArray = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sArray.length; i++)
        {
        }
        return 0;
    }

    public static void testAddTwoNumbers()
    {
        String str1 = "[0]";
        ListNode l1 = ListNode.deserialize(str1);
        System.out.println("l1: " + l1);

        String str2 = "[7,3]";
        ListNode l2 = ListNode.deserialize(str2);
        System.out.println("l2: " + l2);

        System.out.println("Add two number: " + addTwoNumbers2(l1, l2));
    }

    public static int[] twoSum(int[] nums, int target)
    {
        for (int i = 0, len = nums.length; i < len - 1; i++)
        {
            for (int k = i + 1, num0 = nums[i]; k < len; k++)
            {
                if (num0 + nums[k] == target)
                {
                    return new int[] { i, k };
                }
            }
        }
        return new int[] { -1, -1 };
    }

    public static int[] twoSum1(int[] nums, int target)
    {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0, len = nums.length; i < len; i++)
        {
            map.put(nums[i], i);
        }
        for (int i = 0, len = nums.length; i < len; i++)
        {
            int m = target - nums[i];
            Integer index = map.get(m);
            if (index != null && index != i)
            {
                return new int[] { i, index };
            }
        }
        return new int[] { -1, -1 };
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        ListNode result = new ListNode(0);

        ListNode add1 = l1;
        ListNode add2 = l2;
        ListNode resultLast = result;
        int addValue1 = l1.val;
        int addValue2 = l2.val;
        while (true)
        {
            int add = addValue1 + addValue2 + resultLast.val;
            if (add > 9)
            {
                add = add % 10;
                resultLast.addNode(new ListNode(1));
            }
            resultLast.val = add;

            if (add1 != null)
            {
                add1 = add1.next;
            }
            if (add2 != null)
            {
                add2 = add2.next;
            }
            boolean finish = true;
            if (add1 != null)
            {
                addValue1 = add1.val;
                finish = false;
            }
            else
            {
                addValue1 = 0;
            }
            if (add2 != null)
            {
                addValue2 = add2.val;
                finish = false;
            }
            else
            {
                addValue2 = 0;
            }
            if (finish)
            {
                break;
            }

            ListNode next = resultLast.next;
            if (next == null)
            {
                next = new ListNode(0);
                resultLast.addNode(next);
            }
            resultLast = next;
        }
        return result;
    }

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2)
    {
        ListNode result = new ListNode(0);

        ListNode add1 = l1;
        ListNode add2 = l2;
        ListNode resultLast = result;
        int add = add1.val + add2.val;
        while (true)
        {
            if (add > 9)
            {
                add = add % 10;
                resultLast.addNode(new ListNode(1));
            }
            resultLast.val = add;

            if (add1 != null)
            {
                add1 = add1.next;
            }
            if (add2 != null)
            {
                add2 = add2.next;
            }

            boolean finish = true;
            add = 0;
            if (add1 != null)
            {
                finish = false;
                add = add1.val;
            }
            if (add2 != null)
            {
                finish = false;
                add += add2.val;
            }
            if (finish)
            {
                break;
            }

            ListNode next = resultLast.next;
            if (next == null)
            {
                next = new ListNode(0);
                resultLast.addNode(next);
            }
            else
            {
                add += 1;
            }
            resultLast = next;
        }
        return result;
    }

}


class ListNode
{
    int val;
    ListNode next;

    ListNode(int x)
    {
        val = x;
    }

    public void addNode(ListNode node)
    {
        next = node;
    }

    public static ListNode deserialize(String string)
    {

        String inputStr = string.replaceAll("\\[|\\]", "");
        String[] inputArray = inputStr.split(",");
        ListNode result = new ListNode(Integer.parseInt(inputArray[0]));
        ListNode last = result;
        for (int i = 1; i < inputArray.length; i++)
        {
            int sInt = Integer.parseInt(inputArray[i]);
            ListNode next = new ListNode(sInt);
            last.addNode(next);
            last = next;
        }
        return result;
    }

    @Override
    public String toString()
    {
        ListNode iterNext = next;
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        sb.append(val);
        while (true)
        {
            if (iterNext == null)
            {
                break;
            }
            sb.append(',');
            sb.append(iterNext.val);
            iterNext = iterNext.next;
        }
        sb.append(']');
        return sb.toString();
    }
}
