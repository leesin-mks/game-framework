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

        for (int i = 0; i < 1; i++)
        {
            pushPlayer();
        }

    }

    public static void pushPlayer()
    {
        HttpLoginClient pressHttpClient = new HttpLoginClient("1", "23wqe", "qq", "5000");
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
