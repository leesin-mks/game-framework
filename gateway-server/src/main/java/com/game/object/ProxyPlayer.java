package com.game.object;

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
import com.game.module.MessageModule;
import com.game.module.inf.IMessageModule;
import com.game.module.inf.IModule;
import com.game.net.IClientConnection;
import com.game.net.IConnectionHolder;
import com.game.pb.CommonMsgProto.CommonMsgPB;
import com.game.type.ModuleType;
import com.game.type.RedisConst;
import com.game.util.StackMessagePrint;

/**
 * @date 2020年04月09日 18:49
 * @auth zm
 */
public class ProxyPlayer implements IConnectionHolder, ISequenceTask
{
    private final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());

    /** 命令队列 */
    private SelfDrivenTaskQueue<UserCmdTask> cmdQueue = new SelfDrivenTaskQueue<>(
            ComponentManager.getInstance().getUserCmdThreadPool());

    /** 玩家连接引用 */
    private IClientConnection clientConn = null;

    /** 模块缓存 */
    private Map<ModuleType, IModule> moduleMap;

    private IMessageModule messageModule;

    private int userID;

    public ProxyPlayer(int userID, IClientConnection clientConn)
    {
        this.userID = userID;
        this.clientConn = clientConn;
        clientConn.setHolder(this);
        moduleMap = new LinkedHashMap<>();
        moduleMap.put(ModuleType.MESSAGE, new MessageModule(this));
    }

    @Override
    public void onDisconnect()
    {
        LOGGER.info("Proxy player disconnect: {}");
    }

    @Override
    public IClientConnection getClientConnection()
    {
        return clientConn;
    }

    @Override
    public void setClientConnection(IClientConnection conn)
    {
        this.clientConn = conn;
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

    public boolean login()
    {
        return true;
    }

    public void reconnection(IClientConnection clientConn)
    {
        this.clientConn.setIsServerClosed(true);
        this.clientConn.closeConnection(true);
        this.clientConn = clientConn;
        clientConn.setHolder(this);
    }

    public void sendPacket(CommonMsgPB.Builder commonMessage)
    {
        messageModule.sendTCP(commonMessage.build().toByteArray());
    }

    public void sendPacket(byte[] message)
    {
        messageModule.sendTCP(message);
    }

    public IModule getModule(ModuleType type)
    {
        return moduleMap.get(type);
    }

    public void disconnect(boolean isShutDown)
    {
        disconnect(isShutDown, false, 0);
    }

    public void disconnect(boolean isShutDown, boolean sendLoginOut, int type)
    {
        if (clientConn != null)
        {
            if (sendLoginOut)
            {
                messageModule.sendLoginOutMessage(type);
            }
            clientConn.setIsServerClosed(isShutDown);
            clientConn.closeConnection(true);
        }

        if (!isShutDown)
        {
            LOGGER.error("玩家失去连接, userID : {}", userID);
        }
        else
        {
            offline();
        }
    }

    protected void offline()
    {
        try
        {
            IPlayerComponent playerComponent = (IPlayerComponent) ComponentManager.getInstance().getComponent(
                    IPlayerComponent.NAME);
            ProxyPlayer player = playerComponent.getPlayerByUserID(userID);
            if (player == this)
            {
                playerComponent.remove(userID);
                IRedisComponent rc = (IRedisComponent) ComponentManager.getInstance().getComponent(
                        IRedisComponent.NAME);
                String token = rc.get(RedisConst.USER_SESSION_KEY + userID);
                if (token != null)
                {
                    rc.setex(RedisConst.USER_SESSION_KEY + userID, 600, token);
                }
                rc.del(RedisConst.USER_SERVER_KEY + userID);
            }
            else
            {
                StackMessagePrint.printStackTrace();
                LOGGER.warn("Other player already when user offline: {}", userID);
            }
        }
        catch (Exception e)
        {
            LOGGER.error("Catch error when user quit: {}", userID, e);
        }
        finally
        {
            LOGGER.info("Player offline id: {}", userID);
        }
    }

}
