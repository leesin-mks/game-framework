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

package com.game.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.game.command.SelfDrivenAction;
import com.game.command.SelfDrivenTaskQueue;
import com.game.component.ComponentManager;
import com.game.component.PlayerComponent;
import com.game.component.PressCommandComponent;
import com.game.net.HttpLoginClient;
import com.game.net.PressClient;
import com.game.net.PressPacketHandler;
import com.game.timer.TimerComponent;

public class PressManager
{

    private static final int THREAD_NUM = 1;

    private static ExecutorService executorService;

    private static SelfDrivenTaskQueue<SelfDrivenAction> actionQueue;

    public static void main(String[] args)
    {
        init();
        loadComponent();

        for (int i = 1; i <= 500; i++)
        {
            pushPlayer(i);
        }
        // pushPlayer(1);

    }

    public static void pushPlayer(int i)
    {
        HttpLoginClient pressHttpClient = new HttpLoginClient("1", String.valueOf(i), "qq", "5000");
        if (pressHttpClient.httpLogin())
        {
            PressClient pressClient = new PressClient(pressHttpClient.getLoginResult().getIp(),
                    pressHttpClient.getLoginResult().getPort(), pressHttpClient.getLoginResult(),
                    new PressPacketHandler());
            Thread t = new Thread(pressClient);
            t.start();
        }
    }

    private static void init()
    {
        executorService = Executors.newFixedThreadPool(THREAD_NUM);
        actionQueue = new SelfDrivenTaskQueue<>(executorService);
    }

    private static void loadComponent()
    {
        ComponentManager componentManager = ComponentManager.getInstance();
        componentManager.addComponent(PressCommandComponent.class.getName());
        componentManager.addComponent(TimerComponent.class.getName());
        componentManager.addComponent(PlayerComponent.class.getName());

        if (!componentManager.start())
        {
            System.out.println("Client start fail...");
            System.exit(0);
        }
    }

    public static void addAction(SelfDrivenAction action)
    {
        action.setActionQueue(actionQueue);
        actionQueue.add(action);
    }

}
