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

import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CopyrightUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CopyrightUtil.class);

    public static void updateCopyrightInfo(String path, String copyRightInfo, String... exceptPath)
    {
        File file = new File(path);
        File[] children = file.listFiles();
        for (File child : children)
        {
            if (child.isDirectory())
            {
                boolean isExcept = false;
                for (String except : exceptPath)
                {
                    if (child.getPath().indexOf(except) == -1)
                    {
                        isExcept = true;
                        break;
                    }
                    if (isExcept)
                    {
                        continue;
                    }
                }
                updateCopyrightInfo(child.getPath(), copyRightInfo, exceptPath);
            }
            else
            {
                String fileName = child.getName();
                if (fileName.endsWith(".java"))
                {
                    StringBuilder sb = new StringBuilder();
                    sb.append(copyRightInfo);
                    sb.append(OutPutUtil.lineSeparator);
                    FileInputStream fis = null;
                    InputStreamReader isr = null;
                    BufferedReader br = null;
                    FileWriter fileWriter = null;
                    try
                    {
                        fis = new FileInputStream(child);
                        isr = new InputStreamReader(fis);
                        br = new BufferedReader(isr);
                        String line;
                        boolean start = false;
                        while ((line = br.readLine()) != null)
                        {
                            String temp = line.trim();
                            if (!start)
                            {
                                if (temp.startsWith("/**")
                                        || (!temp.startsWith("/*") && !temp.startsWith("*")
                                                && (temp.indexOf("package") != -1
                                                        || temp.indexOf("import") != -1 || temp.indexOf("class") != -1
                                                        || temp.indexOf("enum") != -1
                                                        || temp.indexOf("interface") != -1)))
                                {
                                    start = true;
                                }

                            }
                            if (start)
                            {
                                sb.append(line);
                                sb.append(OutPutUtil.lineSeparator);
                            }
                        }

                        // 写文件
                        fileWriter = new FileWriter(child);
                        fileWriter.write(sb.toString());
                        fileWriter.close();
                    }
                    catch (IOException e)
                    {
                        LOGGER.error("Update copyright error: {}, message: {}", fileName, e);
                    }
                    finally
                    {
                        IOUtil.closeIO(br, isr, fis, fileWriter);
                    }
                }
            }
        }
    }

    public static String getCopyrightInfo(String path)
    {
        File file = new File(path);
        StringBuilder sb = new StringBuilder();
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try
        {
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
                sb.append(OutPutUtil.lineSeparator);
            }
        }
        catch (IOException e)
        {
            LOGGER.error("Update copyright error: {}, message: {}", path, e);
        }
        finally
        {
            IOUtil.closeIO(br, isr, fis);
        }
        return sb.toString();
    }

    public static void main(String[] args)
    {
        // String temp= "package com.game";
        // System.out.println((temp.indexOf("package1")));
        String content = getCopyrightInfo("./common-lib/copyright/copyright.txt");
        // System.out.println(content);
        updateCopyrightInfo("D:\\Work\\game-lite", content, "protobuf-protocol");
    }
}
