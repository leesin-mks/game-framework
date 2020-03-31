/*
 * ICode
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to leesin
 */
package com.game.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 指令注解
 * @author leesin
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ICode
{
    short code();

    String desc();
}
