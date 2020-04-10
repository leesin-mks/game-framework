/*
 * PlayerModule
 *
 * 2016年2月22日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.module;

import java.lang.reflect.Proxy;
import java.util.concurrent.locks.ReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.game.module.inf.IMessageModule;
import com.game.module.inf.IModule;
import com.game.object.ProxyPlayer;
import com.game.type.ModuleType;
import com.google.gson.Gson;


/**
 * @author jacken
 *
 */
public abstract class PlayerModule implements IModule
{
    protected ProxyPlayer player;

    private ModuleType moduleType;

    protected ReadWriteLock lock;

    protected IMessageModule messageModule;

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());

    protected static final Gson gson = new Gson();

    // protected static IDataSaveComponent dataSaveComponent;

    public PlayerModule(ProxyPlayer player, ModuleType moduleType)
    {
        this.player = player;
        this.moduleType = moduleType;
        this.messageModule = (IMessageModule) player.getModule(ModuleType.MESSAGE);
    }

    /**
     * @return the player
     */
    public ProxyPlayer getPlayer()
    {
        return player;
    }

    /**
     * @return the moduleType
     */
    @Override
    public ModuleType getType()
    {
        return moduleType;
    }
}
