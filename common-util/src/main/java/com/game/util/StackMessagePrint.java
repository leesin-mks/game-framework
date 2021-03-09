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

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;

/**
 * @author leesin
 *
 */
public class StackMessagePrint
{
    /**
     * 打印错误消息的堆栈
     * 
     * @param error
     * @return
     */
    public static String printErrorTrace(Throwable error)
    {

        StringBuilder result = new StringBuilder();
        if (error != null)
        {
            result.append("***************************************************************************\n");

            StringWriter writer = new StringWriter();
            PrintWriter print = new PrintWriter(writer);
            error.printStackTrace(print);
            result.append(writer.toString());

            result.append("***************************************************************************");
        }
        else
        {
            result.append("no exception!");
        }
        return result.toString();
    }

    /**
     * 打印当前堆栈信息
     * 
     * @param cls
     */
    public static String printStackTrace()
    {
        StackTraceElement[] elements = (new Throwable()).getStackTrace();

        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < elements.length; i++)
        {

            buf.append("    ").append(elements[i].getClassName()).append(".").append(
                    elements[i].getMethodName()).append("(").append(elements[i].getFileName()).append(":").append(
                            elements[i].getLineNumber()).append(")").append("\n");
        }

        return buf.toString();
    }

    /**
     * 获取当前调用线程的调用栈
     * 
     * @return
     */
    public static String captureStackTrace()
    {
        return captureStackTrace(Thread.currentThread(), "");
    }

    /**
     * 获取当前线程的调用栈信息
     * 
     * @param message
     *            输出的头部提示信息
     * @return
     */
    public static String captureStackTrace(Thread thread, String message)
    {
        StringBuilder stringBuilder = new StringBuilder(String.format(message,
                Thread.currentThread().getName()));
        StackTraceElement[] trace = thread.getStackTrace();
        for (int i = 0; i < trace.length; i++)
        {
            stringBuilder.append(" " + trace[i] + "\r\n");
        }

        stringBuilder.append("");

        return stringBuilder.toString();
    }

    /**
     * 打印异常日志
     * 
     * @param logger
     * @param exception
     * @param stacks
     */
    public static void printError(Logger logger, String exception,
            StackTraceElement[] stacks)
    {
        String line = System.getProperty("line.separator");
        String kong = "        at ";
        StringBuffer sb = new StringBuffer();
        sb.append(exception + line);
        for (StackTraceElement stack : stacks)
        {
            sb.append(kong + stack.toString() + line);
        }
        logger.error(sb.toString());
    }
}
