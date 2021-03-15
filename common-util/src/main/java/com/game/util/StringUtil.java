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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author leesin
 *
 */
public class StringUtil
{

    private static final String PHONE_REG_1 = "^\\+[0-9]+(-)[0-9]{2,11}$";
    private static final String PHONE_REG_2 = "^(1)\\d{10}$";
    private static final Pattern p1 = Pattern.compile(PHONE_REG_1);
    private static final Pattern p2 = Pattern.compile(PHONE_REG_2);

    private static final String ACCOUNT_NAME_REG = "[a-zA-Z0-9_~\\^\\-\\[\\|.@]{6,18}$";
    private static final Pattern p3 = Pattern.compile(ACCOUNT_NAME_REG);
    private static final String ONLY_W_CN = "[a-zA-Z0-9\u4e00-\u9fa5]+$";       // 字母数字中文
    private static final Pattern p4 = Pattern.compile(ONLY_W_CN);

    /**
     * 验证email地址是否合法
     * 
     * @param address
     * @return
     */
    public static boolean isValidEmailAddress(String address)
    {
        if (address == null)
        {
            return false;
        }
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher m = p.matcher(address);
        return m.find();
    }

    /**
     * 判断字符串是否为空。
     * 
     * @param src
     * @return
     */
    public static boolean isNullOrEmpty(String src)
    {
        return src == null || src.isEmpty() || src.trim().length() <= 0;
    }

    /**
     * 判断字符串是否为空。
     * 
     * @param src
     *            source string
     * @return
     */
    public static boolean isNotNullAndNotEmpty(String src)
    {
        return src != null && !src.isEmpty() && src.trim().length() > 0;
    }

    /**
     * 判断字符串是否为空或者只包含空格。
     * 
     * @param src
     * @return
     */
    public static boolean isEmptyOrWhitespaceOnly(String src)
    {
        if (isNullOrEmpty(src))
        {
            return true;
        }

        if (src.trim().length() == 0)
        {
            return true;
        }

        return false;
    }

    /**
     * 获取字符长度
     * 
     * @param str
     * @return
     */
    public static int getStrLength(String str)
    {
        if (str == null)
            return 0;
        int strLength = 0;
        String chinese = "[\u4e00-\u9fa5]";

        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < str.length(); i++)
        {
            // 获取一个字符
            String temp = str.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese))
            {
                // 中文字符长度为2
                strLength += 2;
            }
            else
            {
                // 其他字符长度为1
                strLength += 1;
            }
        }
        return strLength;
    }

    /**
     * String is Number
     * 
     * @param str
     * @return is numeric
     */
    public static boolean isNumeric(String str)
    {
        if (isNullOrEmpty(str))
        {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches())
        {
            return false;
        }
        return true;
    }

    public static String hidePhone(String str)
    {
        str = getPhoneNumber(str);
        if (getStrLength(str) <= 7)
            return str;
        char[] temp = str.toCharArray();
        for (int i = 0; i < temp.length; i++)
        {
            if (i > 2 && i < temp.length - 4)
                temp[i] = '*';
        }
        return String.valueOf(temp);
    }

    /**
     * 验证手机号是否合法
     * 1. +区号-手机号,例子: +86-13200000000
     * 2. 中国11位手机号,例子: 132000000000
     * 
     * @param phone
     * @return
     */
    public static boolean isValidPhoneNumber(String phone)
    {
        if (phone == null || "".equals(phone))
        {
            return false;
        }
        Matcher m1 = p1.matcher(phone);
        Matcher m2 = p2.matcher(phone);
        return m2.find() || m1.find();
    }

    public static boolean isValidBankCard(String str)
    {
        if (isNullOrEmpty(str))
        {
            return false;
        }
        Pattern pattern = Pattern.compile("^\\d{14,20}$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches())
        {
            return false;
        }
        return true;
    }

    /**
     * 字符串不包含 : |
     * 
     * @param str
     * @return
     */
    public static boolean isValidBankInfo(String str)
    {
        if (isNullOrEmpty(str))
        {
            return false;
        }
        Pattern pattern = Pattern.compile("\\||:");
        Matcher isNum = pattern.matcher(str);
        if (isNum.find())
        {
            return false;
        }
        return true;
    }

    /**
     * 去掉手机区号
     * 1. +86-13200000000-> 13200000000
     * 2. 13200000000-> 13200000000
     * 
     * @param phone
     * @return
     */
    public static String getPhoneNumber(String phone)
    {
        if (phone == null || "".equals(phone))
        {
            return phone;
        }
        int index = phone.indexOf("-");
        if (index != -1)
        {
            phone = phone.substring(index + 1, phone.length());
        }
        return phone;
    }

    public static boolean isBlank(final CharSequence cs)
    {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0)
        {
            return true;
        }
        for (int i = 0; i < strLen; i++)
        {
            if (!Character.isWhitespace(cs.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidAccountName(String str)
    {
        if (str == null || "".equals(str))
        {
            return false;
        }
        Matcher m = p3.matcher(str);
        return m.find();
    }

    /**
     * name2到6个汉子之间,身份证符合身份证规则
     * 
     * @param name
     *            姓名
     * @param id
     *            身份证号
     * @return
     */
    public static boolean checkRealNameSystem(String name, String id)
    {
        int nameLen = getStrLength(name);
        if (nameLen >= 4 && nameLen <= 20 && getStrLength(id) == 18)
        {
            final String rexID = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|"
                    + "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
            final String rexName = "^[\u4E00-\u9FA5]+$";
            Pattern pID = Pattern.compile(rexID);
            Matcher mID = pID.matcher(id);
            Pattern pName = Pattern.compile(rexName);
            Matcher mName = pName.matcher(name);
            if (mID.find() && mName.find())
                return true;
        }
        return false;
    }

    /**
     * 只包含字母数字中文
     * 
     * @param str
     * @return
     */
    public static boolean onlyContainsZNW(String str)
    {
        if (str == null || "".equals(str))
        {
            return false;
        }
        Matcher m = p4.matcher(str);
        return m.find();
    }

    public static void main(String[] args)
    {
        String s = "lee1";
        System.out.println(onlyContainsZNW(s));
    }

}
