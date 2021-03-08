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

package com.game.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.game.component.IComponent;

/**
 * @author leesin
 *
 */
@SpringBootApplication
@ComponentScan("com.game")
public class SpringBootComponent implements IComponent
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootComponent.class);

    public static final String NAME = "SpringBootComponent";

    @Override
    public String getName()
    {
        return NAME;
    }

    @Override
    public boolean initialize()
    {
        return true;
    }

    @Override
    public boolean start()
    {
        try
        {
            SpringApplication.run(SpringBootComponent.class, new String[0]);
        }
        catch (Exception e)
        {
            LOGGER.error("load Spring boot error: ", e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void stop()
    {
    }

    @Override
    public boolean reload()
    {
        return false;
    }

}
