package com.game.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 替换后写入文件是覆盖,执行前一定记的手动备份
 * 
 * 每次执行VERSION+1000
 * 
 * 
 */
public class FindChineseUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CopyrightUtil.class);

    public static final char CHAR_1 = '"';
    public static final char CHAR_2 = '\'';
    public static final char CHAR_3 = '=';
    public static final char CHAR_4 = ' ';
    public static final char CHAR_5 = '>';
    public static final char CHAR_6 = '[';
    public static final char CHAR_7 = ']';
    public static final char CHAR_8 = '\\';
    public static final char CHAR_9 = '\0';
    public static final int VERSION = 0;
    public static int index = 1;

    public static final String HTML_PATH = "D:\\Work\\\\changan-os\\server\\aoshu_bi\\src\\main\\resources\\templates";
    public static final String MESSAGE_FILE = "D:\\Work\\changan-os\\server\\aoshu_bi\\src\\main\\resources\\messages_zh_CN.properties";
    public static String ROOT;
    static
    {
        String path = HTML_PATH.replace("\\\\", "\\").replace("////", "//");
        String[] paths = path.split("\\\\|/");
        ROOT = paths[paths.length - 1];
    }

    public static final List FILE_TYPE = Arrays.asList("html", "htm", "shtml");

    private static final List<String> OUT_PUT = new ArrayList<>();

    public static void main(String[] args)
    {
        parseHTML();
        // testParseJavaScript();
    }

    public static void testParseJavaScript()
    {
        File file = new File(
                "D:\\Work\\changan-os\\server\\aoshu_bi\\src\\main\\resources\\templates\\febs\\views\\layout.html");
        parseJavaScript(file);
        System.out.println(OUT_PUT);
    }

    public static void parseHTML()
    {
        parse(HTML_PATH);
        saveMessageFile(MESSAGE_FILE);
        System.out.println(OUT_PUT);
    }

    public static void parse(String path, String... exceptPath)
    {
        File file = new File(path);
        File[] children = file.listFiles();
        assert children != null;
        for (File child : children)
        {
            if (child.isDirectory())
            {
                boolean isExcept = false;
                for (String except : exceptPath)
                {
                    if (!child.getPath().contains(except))
                    {
                        isExcept = true;
                        break;
                    }
                }
                if (isExcept)
                {
                    continue;
                }
                parse(child.getPath(), exceptPath);
            }
            else
            {
                String fileName = child.getName();
                String[] fileArr = fileName.split("\\.");
                String suffix = fileArr[fileArr.length - 1].toLowerCase();
                if (FILE_TYPE.contains(suffix))
                {
                    String attName = getFilePrefix(child.getPath()) + "_";
                    jsoupParse(child, attName);
                    parseJavaScript(child);
                }
            }
        }
    }

    public static void jsoupParse(File file, String attName)
    {
        index = 1;
        Document doc = null;
        try
        {
            doc = Jsoup.parse(file, StandardCharsets.UTF_8.name());
        }
        catch (IOException e)
        {
            LOGGER.error("Doc parse error: ", e);
        }
        assert doc != null;
        for (Element element : doc.getAllElements())
        {
            List<TextNode> textNodeList = element.textNodes();
            for (TextNode textNode : textNodeList)
            {
                String text = textNode.text();
                if (containsCN(textNode.text()))
                {
                    String filed = attName + (VERSION + index++);
                    // 方法1
                    textNode.text("[[#{" + filed + "}]]");

                    // 方法2 会去掉原本文本样式
                   /* textNode.text("");
                    if (element.nodeName().equals("title"))
                    {
                        element.attr("th:text", "#{" + filed + "}");
                    }
                    else
                    {
                        Node node = new Element("span");
                        node.attr("th:text", "#{" + filed + "}");
                        element.appendChild(node);
                    }*/

                    // element.attr("th:text", "#{" + filed + "}");
                    OUT_PUT.add(filed + "=" + text);
                }
            }
            for (Attribute attributes : element.attributes())
            {
                String attValue = attributes.getValue();
                if (containsCN(attValue))
                {
                    String filed = attName + (VERSION + index++);
                    element.removeAttr(attributes.getKey());
                    element.attr("th:" + attributes.getKey(), "#{" + filed + "}");
                    OUT_PUT.add(filed + "=" + attValue);
                    break;
                }
            }
        }

        saveHtmlFile(file, doc.html());
    }

    public static String getFilePrefix(String str)
    {
        StringBuilder attrPrefix = new StringBuilder();
        str = str.replace("\\\\", "\\").replace("////", "//");
        String[] path = str.split("[\\\\/]");
        boolean start = false;
        for (int i = 0; i < path.length - 1; i++)
        {
            if (start)
            {
                attrPrefix.append(path[i]);
                attrPrefix.append('.');
            }
            if (!start && path[i].equals(ROOT))
            {
                start = true;
            }
        }
        String[] fileSplit = path[path.length - 1].split("\\.");
        attrPrefix.append(fileSplit[0]);
        return attrPrefix.toString();
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
            LOGGER.error("Save message file error: ", e);
        }
        finally
        {
            IOUtil.closeIO(fos);
        }
    }

    public static void saveHtmlFile(File file, String content)
    {
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(file);
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

    public static void parseJavaScript(File file)
    {
        StringBuilder sb = new StringBuilder();
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileWriter fileWriter = null;
        try
        {
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String line;
            boolean parseJs = false;
            List<String> jsVar = new ArrayList<>();
            while ((line = br.readLine()) != null)
            {
                if (line.contains("script") && line.contains("text/javascript"))
                {
                    parseJs = true;
                }
                if (parseJs && line.contains("/script"))
                {
                    parseJs = false;
                }

                if (parseJs)
                {
                    line = replaceJSCN(line, 0, file.getPath(), jsVar);
                    // line = replaceJSCNAfter(line);
                }

                sb.append(line);
                OutPutUtil.newLine(sb);
            }

            // 提取出来的中文以变量存放
            if (!jsVar.isEmpty())
            {
                StringBuilder newScript = new StringBuilder();
                newScript.append("  <script th-inline=\"javascript\" type=\"text/javascript\">");
                OutPutUtil.newLine(newScript);
                jsVar.forEach(k -> {
                    String varName = k.replaceAll("\\.", "_");
                    newScript.append("var " + varName + "='[[#{" + k + "}]]'");
                    OutPutUtil.newLine(newScript);
                });
                newScript.append("</script>");
                OutPutUtil.newLine(newScript);
                newScript.append(sb);
                sb = newScript;
            }
        }
        catch (IOException e)
        {
            LOGGER.error("Update copyright error: {}, message: {}", file.getName(), e);
        }
        finally
        {
            IOUtil.closeIO(br, isr, fis, fileWriter);
        }

        saveHtmlFile(file, sb.toString());
    }

    public static String replaceJSCN(String str, int startIndex, String filePath, List<String> jsVar)
    {
        int endIndex = -1;
        char[] strChar = str.toCharArray();
        boolean flag = false;
        for (int i = startIndex; i < str.length(); i++)
        {
            String temp = String.valueOf(str.charAt(i));
            if (temp.matches("[\\u4e00-\\u9fa5]") || temp.matches(
                    "\\*[\u3002\uff1b\uff0c\uff1a\u201c\u201d\uff08\uff09\u3001\uff1f\u300a\u300b\uff01]"))
            {
                startIndex = i;
                flag = true;
                break;
            }
        }

        if (!flag)
        {
            return str;
        }
        // 往前往前找 去到 ' " [
        boolean cond1 = false;
        boolean cond2 = false;
        flag = false;
        char condChar = CHAR_1;
        for (int k = startIndex - 1; k > 0; k--)
        {
            if ((strChar[k] == CHAR_1 || strChar[k] == CHAR_2) && (k - 1 > 0 && strChar[k - 1] != CHAR_8))
            {
                startIndex = k + 1;
                condChar = strChar[k];
                strChar[k] = CHAR_4;
                cond1 = true;
                break;
            }
        }
        // 往前往后找 去到 ' " [
        for (int k = startIndex; k < strChar.length; k++)
        {
            if ((strChar[k] == condChar) && (k - 1 > 0 && strChar[k - 1] != CHAR_8))
            {
                endIndex = k;
                strChar[k] = CHAR_4;
                cond2 = true;
                break;
            }
        }
        if (cond1 && cond2)
        {
            flag = true;
            String attName = getFilePrefix(filePath) + ".js_";
            String filed = attName + (VERSION + index++);
            String replaceStr = str.substring(startIndex, endIndex);
            String varName = filed.replaceAll("\\.", "_");
            str = String.valueOf(strChar).replace(replaceStr, varName);
            jsVar.add(filed);
            OUT_PUT.add(filed + "=" + replaceStr);
        }
        if (flag)
        {
            str = replaceJSCN(str, startIndex, filePath, jsVar);
        }
        return str;
    }

    public static void findChinese(String str)
    {
        String a = "<div src=\"中国sdI记得\" class=\"layui-logo\"><span><b>奥术</b> BI系统</span></div>";
        a = a.trim();
        Whitelist whitelist = new Whitelist();
        whitelist.addTags("span", "b");
        String b = Jsoup.clean(a, whitelist);
        // System.out.println(b);
        // findChinese(a);

        String filedName = "login";
        int startIndex = 0;
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
                String attName = getAttrName(str, startIndex);
                if (StringUtil.isNotNullAndNotEmpty(attName))
                {
                    newLine = newLine.replace(attName, "th:" + attName);
                    newLine = newLine.replace(sb.toString(), "#{login_" + (VERSION + index++) + "}");
                }

                sb.setLength(0);
                startIndex = 0;
            }
        }
        System.out.println(newLine);
    }

    public static String getAttrName(String str, int startIndex)
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
