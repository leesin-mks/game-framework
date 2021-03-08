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
