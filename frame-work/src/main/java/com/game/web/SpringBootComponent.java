/*
 * WebComponent
 *
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
