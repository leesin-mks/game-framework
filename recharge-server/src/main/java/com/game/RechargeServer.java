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

package com.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.component.ComponentManager;
import com.game.component.LanguageComponent;
import com.game.config.GlobalConfigManager;
import com.game.entity.bean.ServerListBean;
import com.game.timer.TimerComponent;
import com.game.type.ServerStateType;
import com.game.util.Uptime;
import com.game.web.SpringBootComponent;

public class RechargeServer implements IServer
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RechargeServer.class);

    private int status;

    private ServerListBean bean;

    /**
     * 单例加载器
     */
    private static class LazyHolder
    {
        private static final RechargeServer INSTANCE = new RechargeServer();
    }

    /**
     * 获取实例
     *
     * @return INSTANCE
     */
    public static RechargeServer getInstance()
    {
        return LazyHolder.INSTANCE;
    }

    public static void main(String[] args)
    {
        LOGGER.info("RechargeServer is starting...");
        if (args.length <= 0)
        {
            LOGGER.error("Please input the global config path.");
            System.exit(1);
        }
        // 初始化配置管理器。
        if (!GlobalConfigManager.getInstance().init(args[0]))
        {
            LOGGER.error("RechargeServer has started failed because of init config.");
            System.exit(1);
        }
        Runtime.getRuntime().addShutdownHook(new BaseShutdownHooker(RechargeServer.getInstance()));

        if (!RechargeServer.getInstance().loadComponents())
        {
            LOGGER.error("RechargeServer has started failed");
            System.out.print("RechargeServer has started failed");
            System.exit(1);
        }

        IServer.printServerPID();
        IServer.printServerVersion();
        System.out.printf("RechargeServer has started successfully, taken %d millis.%n", Uptime.getUptime());
        LOGGER.info("RechargeServer has started successfully, taken {}} millis.", Uptime.getUptime());
    }

    /**
     * @return load result
     */
    @Override
    public boolean loadComponents()
    {
        try
        {
            ComponentManager componentManager = ComponentManager.getInstance();
            // 初始化组件处理器
            if (!componentManager.init())
            {
                return false;
            }

            if (!componentManager.addComponent(LanguageComponent.class.getName()))
                return false;
            if (!componentManager.addComponent(TimerComponent.class.getName()))
                return false;
            if (!componentManager.addComponent(SpringBootComponent.class.getName()))
                return false;
            // 启动
            if (!componentManager.start())
            {
                return false;
            }
        }
        catch (Throwable e)
        {
            LOGGER.error("", e);
            return false;
        }
        return true;
    }

    @Override
    public int getServerID()
    {
        return bean.getId();
    }

    /**
     * 停止回调
     *
     */
    @Override
    public void callBackStop()
    {
        setStatus(ServerStateType.SHUTTING_DOWN.getValue());
        ComponentManager.getInstance().stop();
    }

    /**
     * @return the status
     */
    @Override
    public int getStatus()
    {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status)
    {
        this.status = status;
        if (this.status == ServerStateType.PREPARE_STOP.getValue())
        {
            LOGGER.error("-------------RechargeServer shutting down！！！-------------");
        }
        if (this.status == ServerStateType.SHUTTING_DOWN.getValue())
        {
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                LOGGER.info("Thread interrupted: ", e);
            }
            LOGGER.error("-------------RechargeServer shutdown！！！-------------");
            System.exit(1);
        }
    }

    public ServerListBean getBean()
    {
        return this.bean;
    }

    public void setBean(ServerListBean bean)
    {
        this.bean = bean;
    }

}
