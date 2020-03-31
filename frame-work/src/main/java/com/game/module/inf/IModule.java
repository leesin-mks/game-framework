/*
 * IModule
 *
 * 2016年2月22日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.module.inf;

import com.game.type.ModuleType;

/**
 * @author jacken
 *
 */
public interface IModule
{
    /**
     * 初始化
     */
    boolean init();

    /**
     * 读DB
     * 
     * @return
     */
    boolean loadFromDB();

    /**
     * 写DB
     * 
     * @return
     */
    boolean saveIntoDB();

    /**
     * 写缓存
     * 
     * @return
     */
    boolean saveIntoCache();

    /**
     * 当前模块数据已经加载完成
     */
    void afterDataLoaded();

    /**
     * 得到模块类型
     * 
     * @return
     */
    ModuleType getType();
}
