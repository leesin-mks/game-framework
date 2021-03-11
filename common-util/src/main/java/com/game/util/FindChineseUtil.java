package com.game.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.safety.Whitelist;

public class FindChineseUtil
{

    public static final char CHAR_1 = '"';
    public static final char CHAR_2 = '\'';
    public static final char CHAR_3 = '=';
    public static final char CHAR_4 = ' ';
    public static final char CHAR_5 = '>';
    public static int index = 1;

    private static final List<String> OUT_PUT = new ArrayList<>();

    public static void main(String[] args)
    {
        String fileName = "D:\\Work\\\\changan-os\\server\\aoshu_bi\\src\\main\\resources\\templates\\febs\\views\\login.html";
        String messageFile = "D:\\Work\\changan-os\\server\\aoshu_bi\\src\\main\\resources\\messages_zh_CN.properties";
        String attName = getFilePrefix(fileName) + "_";
        String a = "<div src=\"中国sdI记得\" class=\"layui-logo\"><span><b>奥术</b> BI系统</span></div>";
        a = a.trim();
        Whitelist whitelist = new Whitelist();
        whitelist.addTags("span", "b");
        String b = Jsoup.clean(a, whitelist);
        // System.out.println(b);
        // findChinese(a);
        jsoupParse(fileName, attName);
        saveMessageFile(messageFile);
        System.out.println(OUT_PUT);
    }

    public static void jsoupParse(String fileName, String attName)
    {
        Document doc = null;
        try
        {
            doc = Jsoup.parse(new File(fileName), StandardCharsets.UTF_8.name());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println(doc.text());
        for (Element element : doc.getAllElements())
        {
            List<TextNode> textNodeList = element.textNodes();
            /*
             * if (containsCN(text))
             * {
             * String filed = attName + (index++);
             * element.text("#{" + filed + "}");
             * }
             */
            for (TextNode textNode : textNodeList)
            {
                String text = textNode.text();
                if (containsCN(textNode.text()))
                {
                    textNode.text("");
                    String filed = attName + (index++);
                    System.out.println("Node name: " + element.nodeName());
                    if (element.nodeName().equals("title"))
                    {
                        element.attr("th:text", "#{" + filed + "}");
                    }
                    else
                    {
                        Node node = new Element("span");
                        node.attr("th:text", "#{" + filed + "}");
                        element.appendChild(node);
                    }
                    OUT_PUT.add(filed + "=" + text);
                }
            }
            System.out.println("-->" + textNodeList);
            for (Attribute attributes : element.attributes())
            {
                String attValue = attributes.getValue();
                if (containsCN(attValue))
                {
                    String filed = attName + (index++);
                    element.removeAttr(attributes.getKey());
                    element.attr("th:" + attributes.getKey(), "#{" + filed + "}");
                    OUT_PUT.add(filed + "=" + attValue);
                    break;
                }
            }
        }

        saveHtmlFile(fileName + ".html", doc.html());
        // System.out.println(doc.html());
    }

    public static String getFilePrefix(String str)
    {
        str = str.replace("\\\\", "\\").replace("//", "//");
        String[] path = str.split("\\\\|/");
        String[] fileName = path[path.length - 1].split("\\.");
        return fileName[0];
    }

    public static void saveMessageFile(String fileName)
    {
        StringBuilder sb = new StringBuilder();
        OUT_PUT.forEach(k -> {
            sb.append(k);
            OutPutUtil.newLine(sb);
        });
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(fileName, true);
            fos.write(sb.toString().getBytes());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            IOUtil.closeIO(fos);
        }
    }

    public static void saveHtmlFile(String fileName, String content)
    {
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(fileName);
            fos.write(content.getBytes());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            IOUtil.closeIO(fos);
        }
    }

    public static boolean containsCN(String str)
    {
        for (int i = 0; i < str.length(); i++)
        {
            String temp = String.valueOf(str.charAt(i));
            if (temp.matches("[\\u4e00-\\u9fa5]") || temp.matches(
                    "\\*[\u3002\uff1b\uff0c\uff1a\u201c\u201d\uff08\uff09\u3001\uff1f\u300a\u300b\uff01]"))
            {
                return true;
            }
        }
        return false;
    }

    public static void findChinese(String str)
    {
        String filedName = "login";
        int startIndex = 0;
        int endIndex = 0;
        String newLine = str;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++)
        {
            String temp = String.valueOf(str.charAt(i));
            boolean hasZH = sb.length() > 0;
            if (temp.matches("[\\u4e00-\\u9fa5]") || temp.matches(
                    "\\*[\u3002\uff1b\uff0c\uff1a\u201c\u201d\uff08\uff09\u3001\uff1f\u300a\u300b\uff01]"))
            {
                if (!hasZH)
                {
                    startIndex = i;
                }
                sb.append(temp);
            }
            else if (hasZH)
            {
                endIndex = i;
                String attName = getAttrName(str, startIndex, endIndex);
                if (StringUtil.isNotNullAndNotEmpty(attName))
                {
                    newLine = newLine.replace(attName, "th:" + attName);
                    newLine = newLine.replace(sb.toString(), "#{login_" + (index++) + "}");
                }

                sb.setLength(0);
                startIndex = 0;
            }
        }
        System.out.println(newLine);
    }

    public static String getAttrName(String str, int startIndex, int endIndex)
    {
        StringBuilder attrName = new StringBuilder();
        boolean condition1 = false;
        int index = -1;
        for (int i = startIndex - 1; i > 0; i--)
        {
            char c = str.charAt(i);
            if (c == CHAR_5)
            {
                break;
            }
            if (c == CHAR_1 || c == CHAR_2)
            {
                condition1 = true;
            }
            if (condition1 && c == CHAR_3)
            {
                index = i;
                break;
            }
        }
        if (index != -1)
        {
            for (int i = index - 1; i > 0; i--)
            {
                char c = str.charAt(i);
                if (c == CHAR_4)
                {
                    if (attrName.length() == 0)
                    {
                        continue;
                    }
                    else
                    {
                        break;
                    }
                }
                attrName.append(c);
            }
        }
        attrName.reverse();
        return attrName.toString();
    }
}
