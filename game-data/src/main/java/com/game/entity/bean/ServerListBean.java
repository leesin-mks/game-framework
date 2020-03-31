package com.game.entity.bean;

import com.game.database.DataObject;
import com.game.database.DataOption;

public class ServerListBean extends DataObject
{
    public ServerListBean(boolean insert)
    {
        super(insert);
    }

    public ServerListBean()
    {
        super(false);
    }

    /**
     * 
     */
    private int id;

    /**
     * 服务器类型，1 gameserver， 2 fightserver
     */
    private int serverType;

    /**
     * 公网ip
     */
    private String ip;

    /**
     * 服务器之间的通讯ip
     */
    private String innerIp;

    /**
     * 对外端口
     */
    private String port;

    /**
     * 服务器之间的通讯端口
     */
    private String innerPort;

    /**
     * Web端口
     */
    private String webPort;

    /**
     * 对内web端口
     */
    private String innerWebport;

    /**
     * 最小房间号
     */
    private int keyMin;

    /**
     * 最大房间号
     */
    private int keyMax;

    /**
     * 当前服务器状态
     */
    private int serverState;

    /**
     * 在线人数
     */
    private int onlineNum;

    /**
     * 最大在线人数记录
     */
    private int maxOnline;

    /**
     * 备注
     */
    private String remark;

    /**
     * 
     */
    public int getId()
    {
        return id;
    }

    /**
     * 
     */
    public void setId(int id)
    {
        if (id != this.id)
        {
            this.id = id;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 服务器类型，1 gameserver， 2 fightserver
     */
    public int getServerType()
    {
        return serverType;
    }

    /**
     * 服务器类型，1 gameserver， 2 fightserver
     */
    public void setServerType(int serverType)
    {
        if (serverType != this.serverType)
        {
            this.serverType = serverType;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 公网ip
     */
    public String getIp()
    {
        return ip;
    }

    /**
     * 公网ip
     */
    public void setIp(String ip)
    {
        if (ip != null && !ip.equals(this.ip))
        {
            this.ip = ip;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 服务器之间的通讯ip
     */
    public String getInnerIp()
    {
        return innerIp;
    }

    /**
     * 服务器之间的通讯ip
     */
    public void setInnerIp(String innerIp)
    {
        if (innerIp != null && !innerIp.equals(this.innerIp))
        {
            this.innerIp = innerIp;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 对外端口
     */
    public String getPort()
    {
        return port;
    }

    /**
     * 对外端口
     */
    public void setPort(String port)
    {
        if (port != null && !port.equals(this.port))
        {
            this.port = port;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 服务器之间的通讯端口
     */
    public String getInnerPort()
    {
        return innerPort;
    }

    /**
     * 服务器之间的通讯端口
     */
    public void setInnerPort(String innerPort)
    {
        if (innerPort != null && !innerPort.equals(this.innerPort))
        {
            this.innerPort = innerPort;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * Web端口
     */
    public String getWebPort()
    {
        return webPort;
    }

    /**
     * Web端口
     */
    public void setWebPort(String webPort)
    {
        if (webPort != null && !webPort.equals(this.webPort))
        {
            this.webPort = webPort;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 对内web端口
     */
    public String getInnerWebport()
    {
        return innerWebport;
    }

    /**
     * 对内web端口
     */
    public void setInnerWebport(String innerWebport)
    {
        if (innerWebport != null && !innerWebport.equals(this.innerWebport))
        {
            this.innerWebport = innerWebport;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 最小房间号
     */
    public int getKeyMin()
    {
        return keyMin;
    }

    /**
     * 最小房间号
     */
    public void setKeyMin(int keyMin)
    {
        if (keyMin != this.keyMin)
        {
            this.keyMin = keyMin;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 最大房间号
     */
    public int getKeyMax()
    {
        return keyMax;
    }

    /**
     * 最大房间号
     */
    public void setKeyMax(int keyMax)
    {
        if (keyMax != this.keyMax)
        {
            this.keyMax = keyMax;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 当前服务器状态
     */
    public int getServerState()
    {
        return serverState;
    }

    /**
     * 当前服务器状态
     */
    public void setServerState(int serverState)
    {
        if (serverState != this.serverState)
        {
            this.serverState = serverState;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 在线人数
     */
    public int getOnlineNum()
    {
        return onlineNum;
    }

    /**
     * 在线人数
     */
    public void setOnlineNum(int onlineNum)
    {
        if (onlineNum != this.onlineNum)
        {
            this.onlineNum = onlineNum;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 最大在线人数记录
     */
    public int getMaxOnline()
    {
        return maxOnline;
    }

    /**
     * 最大在线人数记录
     */
    public void setMaxOnline(int maxOnline)
    {
        if (maxOnline != this.maxOnline)
        {
            this.maxOnline = maxOnline;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 备注
     */
    public String getRemark()
    {
        return remark;
    }

    /**
     * 备注
     */
    public void setRemark(String remark)
    {
        if (remark != null && !remark.equals(this.remark))
        {
            this.remark = remark;
            setOp(DataOption.UPDATE);
        }
    }

}
