package com.game.objec;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.command.ISequenceTask;
import com.game.command.SelfDrivenTaskQueue;
import com.game.command.UserCmdTask;
import com.game.component.ComponentManager;
import com.game.component.IRedisComponent;
import com.game.component.inf.IPlayerComponent;
import com.game.cs.CSServerConn;
import com.game.database.DataOption;
import com.game.entity.bean.PlayerInfo;
import com.game.module.MessageModule;
import com.game.module.inf.IMessageModule;
import com.game.module.inf.IModule;
import com.game.pb.CommonMsgProto.CommonMsgPB;
import com.game.type.ModuleType;
import com.game.type.RedisConst;
import com.game.util.StringUtil;

/**
 * @date 2020年04月16日 20:05
 * @auth zm
 */
public class GamePlayer implements ISequenceTask
{

    private final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());

    /** 命令队列 */
    private SelfDrivenTaskQueue<UserCmdTask> cmdQueue = new SelfDrivenTaskQueue<>(
            ComponentManager.getInstance().getUserCmdThreadPool());

    /** 模块缓存 */
    private Map<ModuleType, IModule> moduleMap;

    private IMessageModule messageModule;

    private CSServerConn serverConn;

    private PlayerInfo playerInfo;

    private int gateServerID;

    private int ping;

    private boolean online;

    public GamePlayer(PlayerInfo playerInfo, CSServerConn clientConn)
    {
        this.playerInfo = playerInfo;
        moduleMap = new LinkedHashMap<>();
        moduleMap.put(ModuleType.MESSAGE, new MessageModule(this));
    }

    @Override
    public String getSequenceTaskName()
    {
        return "ProxyPlayerTask";
    }

    @Override
    public <T extends Runnable> void addCommandTask(T task)
    {
        cmdQueue.add((UserCmdTask) task);
    }

    @Override
    public void finishOneCommandTask()
    {
        cmdQueue.complete();
    }

    public PlayerInfo getPlayerInfo()
    {
        return playerInfo;
    }

    public boolean login()
    {
        if (playerInfo == null || playerInfo.getId() <= 0)
        {
            LOGGER.error("playerInfo is null when player login", new NullPointerException());
            return false;
        }

        if (!initGateWay())
        {
            return false;
        }

        messageModule = (MessageModule) getModule(ModuleType.MESSAGE);
        try
        {
            /***** 登陆时服务器 加载处理 四个有序步骤 不能乱 *****/
            // 步骤一： 先load数据再加队列，避免定时保存出错(初始化完玩家对象，再放到IPlayerComponent)
            if (!this.initModules())
            {
                return false;
            }
            sendInitDetail();

            // 步骤三： load 各个模块数据
            if (!loadModuleData())
            {
                return false;
            }

            // 步骤四：加载后的业务处理
            if (!this.afterDataLoaded())
            {
                return false;
            }

            IPlayerComponent pc = (IPlayerComponent) ComponentManager.getInstance().getComponent(IPlayerComponent.NAME);
            if (!pc.add(this.playerInfo.getId(), this))
            {
                return false;
            }
            setOnline(true);

            LOGGER.info("Login success: {}", playerInfo.getId());
            return true;
        }
        catch (Exception e)
        {
            LOGGER.error("Player error when login: {}", playerInfo.getId(), e);
        }
        return false;
    }

    public boolean reconnection(CSServerConn clientConn)
    {
        if (!initGateWay())
        {
            return false;
        }
        sendInitDetail();
        afterDataLoaded();
        this.serverConn = clientConn;
        setOnline(true);

        LOGGER.info("Reconnect success: {}", playerInfo.getId());
        return true;
    }

    private boolean initGateWay()
    {
        IRedisComponent rc = (IRedisComponent) ComponentManager.getInstance().getComponent(
                IRedisComponent.NAME);
        String serverSessionKey = rc.get(RedisConst.USER_GW_SERVER_KEY + playerInfo.getId());
        if (!StringUtil.isNumeric(serverSessionKey))
        {
            LOGGER.warn("Can not find gate way server: {}", serverSessionKey);
            return false;
        }
        gateServerID = Integer.parseInt(serverSessionKey);
        return true;
    }

    public void sendPacket(CommonMsgPB.Builder commonMessage)
    {
        sendPacket(commonMessage.build().toByteArray());
    }

    public void sendPacket(byte[] packet)
    {
        messageModule.sendTCP(packet);
    }

    public IModule getModule(ModuleType type)
    {
        return moduleMap.get(type);
    }

    public boolean initModules()
    {
        boolean result = true;
        for (IModule module : moduleMap.values())
        {
            try
            {
                if (!module.init() && !(module instanceof MessageModule))
                {
                    LOGGER.error(module.getType() + " module init error.");
                    result = false;
                }
            }
            catch (Exception e)
            {
                LOGGER.error(module.getType() + " module init error.", e);
                result = false;
            }
        }
        return result;
    }

    public boolean loadModuleData()
    {
        boolean result = true;
        for (IModule module : moduleMap.values())
        {
            try
            {
                if (!module.loadFromDB())
                {
                    LOGGER.error(module.getType() + " module load data from db error.");
                    result = false;
                }
            }
            catch (Exception e)
            {
                LOGGER.error(module.getType() + " module load data from db error:", e);
                result = false;
            }
        }
        return result;
    }

    private boolean afterDataLoaded()
    {
        try
        {
            for (IModule module : moduleMap.values())
            {
                module.afterDataLoaded();
            }
        }
        catch (Exception e)
        {
            LOGGER.error("afterDataLoaded error:", e);
            return false;
        }
        return true;
    }

    public boolean saveModuleData()
    {
        // long tick = System.currentTimeMillis();
        // long startTick = tick;
        for (IModule module : moduleMap.values())
        {
            try
            {
                if (!module.saveIntoDB())
                {
                    LOGGER.error(module.getType() + " module save data from db error.");
                    return false;
                }
                // long tmpTick = System.currentTimeMillis();
                /*
                 * System.out.println(module.getClass().getName() + " cost " + (tmpTick - tick)
                 * + "ms");
                 */
                // tick = tmpTick;
            }
            catch (Exception e)
            {
                LOGGER.error(module.getType() + " module load save from db error:", e);
                return false;
            }
        }
        // long tmpTick = System.currentTimeMillis();
        // System.out.println("save playerInfo cost " + (tmpTick - startTick) + "ms");
        updatePlayerInfos();
        return true;
    }

    private void sendInitDetail()
    {
        IMessageModule module = ((IMessageModule) getModule(ModuleType.MESSAGE));
        try
        {
            module.sendLoginSuccess();
        }
        catch (Exception e)
        {
            LOGGER.error("send error{}", e);
        }
    }

    public boolean updatePlayerInfos()
    {
        if (playerInfo.getOp() != DataOption.NONE)
        {
            if (true)
            {
                playerInfo.setOp(DataOption.NONE);
                return true;
            }
        }
        return true;
    }

    public int getGateServerID()
    {
        return gateServerID;
    }

    public int getPing()
    {
        return ping;
    }

    public void setPing(int ping)
    {
        this.ping = ping;
    }

    public boolean isOnline()
    {
        return online;
    }

    public void setOnline(boolean online)
    {
        this.online = online;
    }
}
