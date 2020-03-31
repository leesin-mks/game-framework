/*
 * ISequenceTask
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.command;

/**
 * @author jacken
 *
 */
public interface ISequenceTask
{
    public String getSequenceTaskName();

    <T extends Runnable> void addCommandTask(T task);

    void finishOneCommandTask();
}
