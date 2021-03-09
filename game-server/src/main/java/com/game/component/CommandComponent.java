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

package com.game.component;

import com.game.command.AbstractCommandComponent;
import com.game.command.ICode;

/**
 * @author leesin
 *
 */
public class CommandComponent extends AbstractCommandComponent<ICode>
{
    /**
     * (non-Javadoc)
     * 
     * @see com.game.component.CommandComponent#getCommandPacketName()
     */
    @Override
    public String getCommandPacketName()
    {
        return "com.game.command";
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.game.component.CommandComponent#getAnnotationClass()
     */
    @Override
    public Class<ICode> getAnnotationClass()
    {
        return ICode.class;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.game.component.CommandComponent#getNodeType(com.game.command.ICode)
     */
    @Override
    public Short getNodeType(ICode annotation)
    {
        return annotation.code();
    }
}
