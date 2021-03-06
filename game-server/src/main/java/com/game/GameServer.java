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

import java.io.*;
import java.lang.management.ManagementFactory;

import com.game.util.OutPutUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.bll.ServerBussiness;
import com.game.component.*;
import com.game.config.GlobalConfigManager;
import com.game.database.DBComponent;
import com.game.entity.bean.ServerListBean;
import com.game.log.cache.LogCache;
import com.game.pb.CommonMsgProto.CommonMsgPB;
import com.game.pb.command.ProtocolOutProto.ProtocolOut;
import com.game.timer.TimerComponent;
import com.game.type.ServerStateType;
import com.game.util.Uptime;
import com.game.web.WebComponent;

/**
 * @author: Lee Sin
 *
 */
public class GameServer implements IServer
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GameServer.class);

    private int status;

    private ServerListBean bean;

    /**
     * 单例加载器
     */
    private static class LazyHolder
    {
        private static final GameServer INSTANCE = new GameServer();
    }

    /**
     * 获取实例
     *
     * @return INSTANCE
     */
    public static GameServer getInstance()
    {
        return LazyHolder.INSTANCE;
    }

    /**
     * @param args
     *            – command line arguments
     */
    public static void main(String[] args)
    {
        LOGGER.info("GameServer is starting...");
        System.out.println("-->" + OutPutUtil.lineSeparator);
        if (args.length <= 0)
        {
            LOGGER.error("Please input the global config path.");
            System.exit(1);
        }
        // 初始化配置管理器。
        if (!GlobalConfigManager.getInstance().init(args[0]))
        {
            LOGGER.warn("GameServer has started failed because of init config.");
            System.exit(1);
        }
        Runtime.getRuntime().addShutdownHook(new BaseShutdownHooker(GameServer.getInstance()));

        if (!GameServer.getInstance().LoadComponents())
        {
            LOGGER.warn("GameServer has started failed");
            System.out.print("GameServer has started failed");
            System.exit(1);
        }
        printServerVersion();
        printServerPID();
        System.out.printf("GameServer has started successfully, taken %d millis.%n", Uptime.getUptime());
        LOGGER.info("GameServer has started successfully, taken {} millis.", Uptime.getUptime());
        GameServer.getInstance().setStatus(1);
    }

    /**
     * @return load result
     */
    protected boolean LoadComponents()
    {
        try
        {
            ComponentManager componentManager = ComponentManager.getInstance();
            // 初始化组件处理器
            if (!componentManager.init())
            {
                return false;
            }

            if (!componentManager.addComponent(DBComponent.class.getName()))
                return false;
            if (!componentManager.addComponent(RedisComponent.class.getName()))
                return false;
            if (!componentManager.addComponent(LanguageComponent.class.getName()))
                return false;
            if (!componentManager.addComponent(WebComponent.class.getName()))
                return false;
            if (!componentManager.addComponent(TimerComponent.class.getName()))
                return false;
            if (!componentManager.addComponent(LogCache.class.getName()))
                return false;
            if (!componentManager.addComponent(ServerListComponent.class.getName()))
                return false;
            if (!componentManager.addComponent(CommandComponent.class.getName()))
                return false;
            if (!componentManager.addComponent(CSCommandComponent.class.getName()))
                return false;
            if (!componentManager.addComponent(CSComponent.class.getName()))
                return false;
            if (!componentManager.addComponent(PlayerComponent.class.getName()))
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
        if (this.status == ServerStateType.SHUTTING_DOWN.getValue())
        {
            CommonMsgPB.Builder message = CommonMsgPB.newBuilder();
            message.setCode(ProtocolOut.SHUT_DOWN_VALUE);
        }
        if (this.status == ServerStateType.STOP.getValue())
        {
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                LOGGER.info("Thread interrupted: ", e);
            }
            LOGGER.error("-------------GameServer shutdown！！！-------------");
            saveServerState(0);
            System.exit(1);
        }
        saveServerState(status);
    }

    public ServerListBean getBean()
    {
        return this.bean;
    }

    public void setBean(ServerListBean bean)
    {
        this.bean = bean;
    }

    public void saveServerState(int state)
    {
        if (bean != null)
        {
            bean.setServerState(state);
            ServerBussiness.updateServerListBean(bean);
        }
        else
        {
            LOGGER.error("找不到服务器配置");
        }
    }

    private static void printServerVersion()
    {
        String versionPath = GlobalConfigManager.getInstance().getServerConfig().getVersionPath();
        versionPath = versionPath == null ? "./version.txt" : versionPath;
        File file = new File(versionPath);
        if (file.exists())
        {
            FileInputStream fis = null;
            InputStreamReader isr = null;
            BufferedReader br = null;
            try
            {
                fis = new FileInputStream(file);
                isr = new InputStreamReader(fis);
                br = new BufferedReader(isr);
                String version = br.readLine();
                System.out.println("Server version: " + version);
                LOGGER.error("Server version: " + version);
            }
            catch (IOException e)
            {
                LOGGER.error("Read version file error: ", e);
            }
            finally
            {
                if (br != null)
                {
                    try
                    {
                        br.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                if (isr != null)
                {
                    try
                    {
                        isr.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                if (fis != null)
                {
                    try
                    {
                        fis.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

        }
        else
        {
            System.out.println("Can not find version file");
            LOGGER.error("Can not find version file");
        }
    }

    private static void printServerPID()
    {
        String serverPid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        System.out.println("Server pid: " + serverPid);
        LOGGER.error("Server pid: " + serverPid);
    }

    public int getServerID()
    {
        return bean.getId();
    }

}
