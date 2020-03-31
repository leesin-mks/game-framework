/*
 * IWebHandler
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.web;

/**
 * @author jacken
 *
 */
public interface IWebHandler
{
    /**
     * 通过Request传入的其他数据
     * 
     * @param jsonString
     *            处理的json String
     * @param otherStrings
     * @return
     */
    String execute(String jsonString, String... otherStrings);
}
