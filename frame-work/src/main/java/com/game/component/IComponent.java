/*
 * IComponent
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.component;

/**
 * @author jacken
 *
 */
public interface IComponent
{
    /**
     * 获取组件名称
     *
     * @return
     */
    String getName();

    /**
     * 初始化组件
     *
     * @return
     */
    boolean initialize();

    /**
     * 启动组件
     *
     * @return
     */
    boolean start();

    /**
     * 停止组件
     */
    void stop();

    /**
     * 重新加载组件
     */
    boolean reload();
}
