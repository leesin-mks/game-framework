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

package com.game.entity.bean;

import java.util.Date;

import com.game.database.DataObject;
import com.game.database.DataOption;

public class PlayerInfo extends DataObject
{
    public PlayerInfo(boolean insert)
    {
        super(insert);
    }

    public PlayerInfo()
    {
        super(false);
    }

    /**
     * 玩家唯一标识
     */
    private int id;

    /**
     * 唯一id
     */
    private String unionId;

    /**
     * 设备号
     */
    private String deviceId;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 上次昵称修改时间
     */
    private Date nickChangeDate;

    /**
     * 钻石
     */
    private int diamond;

    /**
     * 金币
     */
    private java.math.BigDecimal coin;

    /**
     * 保险箱
     */
    private int safeDepositBox;

    /**
     * 头像
     */
    private String head;

    /**
     * 账号找回方式
     */
    private String retrieveMethod;

    /**
     * 微信
     */
    private String wechat;

    /**
     * 注册方式
     */
    private int registeMethod;

    /**
     * 注册日期
     */
    private Date registeDate;

    /**
     * 注册ip
     */
    private String createIp;

    /**
     * 地址信息
     */
    private String location;

    /**
     * 激活手机
     */
    private boolean active;

    /**
     * 是否封号(0 未封 1 已封)
     */
    private boolean suspend;

    /**
     * 封号原因
     */
    private String suspendCause;

    /**
     * 来源标识
     */
    private String sign;

    /**
     * 平台类型(0:h5 1:安卓 2：ios )
     */
    private int platformType;

    /**
     * 是否是机器人
     */
    private boolean isRobot;

    /**
     * 代理ID
     */
    private String agentId;

    /**
     * 
     */
    private int totalTimes;

    /**
     * 状态记录
     */
    private int mask;

    /**
     * 性别(1:男,2:女)
     */
    private int sex;

    /**
     * 银行卡(银行名称:卡号:真实姓名)
     */
    private String bankCard;

    /**
     * 我的邀请码
     */
    private int myInviteCode;

    /**
     * 邀请码
     */
    private int inviteCode;

    /**
     * 玩家状态(0:离线,1:大厅,2:游戏内)
     */
    private int state;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 离线时间
     */
    private Date offlineTime;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 总游戏局数
     */
    private int totalGames;

    /**
     * 赢的场次
     */
    private int winGames;

    /**
     * 今日砖石消耗
     */
    private java.math.BigDecimal todayBunk;

    /**
     * 当月砖石消耗
     */
    private java.math.BigDecimal monthBunk;

    /**
     * 成为代理的时间
     */
    private Date joinInviteTime;

    /**
     * 绑定代理的时间
     */
    private Date bindInviteTime;

    /**
     * 所属游戏
     */
    private int gameId;

    /**
     * 每天最大审核额度
     */
    private java.math.BigDecimal exchangeDayCheck;

    /**
     * 转盘总积分
     */
    private java.math.BigDecimal turnTableTotal;

    /**
     * 可用兑换转盘积分
     */
    private java.math.BigDecimal turnTableAvailable;

    /**
     * 当天转盘次数
     */
    private int todayTime;

    /**
     * 重置时间
     */
    private Date updateTime;

    /**
     * 二级密码
     */
    private String secondPassword;

    /**
     * 救济金次数
     */
    private int relieveTimes;

    /**
     * 团队码
     */
    private int teamCode;

    /**
     * 加入团队时间
     */
    private Date joinTeamTime;

    /**
     * 实名认证
     */
    private String realNameSystem;

    /**
     * 签到
     */
    private int signIn;

    /**
     * 玩家等级
     */
    private int userLevel;

    /**
     * 总充值
     */
    private int totalRecharge;

    /**
     * 总解散次数
     */
    private int totalDissolution;

    /**
     * 被举报次数
     */
    private int tipOffTimes;

    /**
     * 用户评级
     */
    private int userEvaluate;

    /**
     * 赛季
     */
    private int competitionSeason;

    /**
     * 过期时间
     */
    private Date hangExpireTime;

    /**
     * 玩家唯一标识
     */
    public int getId()
    {
        return id;
    }

    /**
     * 玩家唯一标识
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
     * 唯一id
     */
    public String getUnionId()
    {
        return unionId;
    }

    /**
     * 唯一id
     */
    public void setUnionId(String unionId)
    {
        if (unionId != null && !unionId.equals(this.unionId))
        {
            this.unionId = unionId;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 设备号
     */
    public String getDeviceId()
    {
        return deviceId;
    }

    /**
     * 设备号
     */
    public void setDeviceId(String deviceId)
    {
        if (deviceId != null && !deviceId.equals(this.deviceId))
        {
            this.deviceId = deviceId;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 手机号码
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * 手机号码
     */
    public void setPhone(String phone)
    {
        if (phone != null && !phone.equals(this.phone))
        {
            this.phone = phone;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 账户名称
     */
    public String getAccountName()
    {
        return accountName;
    }

    /**
     * 账户名称
     */
    public void setAccountName(String accountName)
    {
        if (accountName != null && !accountName.equals(this.accountName))
        {
            this.accountName = accountName;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 密码
     */
    public String getUserPassword()
    {
        return userPassword;
    }

    /**
     * 密码
     */
    public void setUserPassword(String userPassword)
    {
        if (userPassword != null && !userPassword.equals(this.userPassword))
        {
            this.userPassword = userPassword;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 昵称
     */
    public String getNickName()
    {
        return nickName;
    }

    /**
     * 昵称
     */
    public void setNickName(String nickName)
    {
        if (nickName != null && !nickName.equals(this.nickName))
        {
            this.nickName = nickName;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 上次昵称修改时间
     */
    public Date getNickChangeDate()
    {
        return nickChangeDate;
    }

    /**
     * 上次昵称修改时间
     */
    public void setNickChangeDate(Date nickChangeDate)
    {
        if (nickChangeDate != null && !nickChangeDate.equals(this.nickChangeDate))
        {
            this.nickChangeDate = nickChangeDate;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 钻石
     */
    public int getDiamond()
    {
        return diamond;
    }

    /**
     * 钻石
     */
    public void setDiamond(int diamond)
    {
        if (diamond != this.diamond)
        {
            this.diamond = diamond;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 金币
     */
    public java.math.BigDecimal getCoin()
    {
        return coin;
    }

    /**
     * 金币
     */
    public void setCoin(java.math.BigDecimal coin)
    {
        if (coin != this.coin)
        {
            this.coin = coin;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 保险箱
     */
    public int getSafeDepositBox()
    {
        return safeDepositBox;
    }

    /**
     * 保险箱
     */
    public void setSafeDepositBox(int safeDepositBox)
    {
        if (safeDepositBox != this.safeDepositBox)
        {
            this.safeDepositBox = safeDepositBox;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 头像
     */
    public String getHead()
    {
        return head;
    }

    /**
     * 头像
     */
    public void setHead(String head)
    {
        if (head != null && !head.equals(this.head))
        {
            this.head = head;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 账号找回方式
     */
    public String getRetrieveMethod()
    {
        return retrieveMethod;
    }

    /**
     * 账号找回方式
     */
    public void setRetrieveMethod(String retrieveMethod)
    {
        if (retrieveMethod != null && !retrieveMethod.equals(this.retrieveMethod))
        {
            this.retrieveMethod = retrieveMethod;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 微信
     */
    public String getWechat()
    {
        return wechat;
    }

    /**
     * 微信
     */
    public void setWechat(String wechat)
    {
        if (wechat != null && !wechat.equals(this.wechat))
        {
            this.wechat = wechat;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 注册方式
     */
    public int getRegisteMethod()
    {
        return registeMethod;
    }

    /**
     * 注册方式
     */
    public void setRegisteMethod(int registeMethod)
    {
        if (registeMethod != this.registeMethod)
        {
            this.registeMethod = registeMethod;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 注册日期
     */
    public Date getRegisteDate()
    {
        return registeDate;
    }

    /**
     * 注册日期
     */
    public void setRegisteDate(Date registeDate)
    {
        if (registeDate != null && !registeDate.equals(this.registeDate))
        {
            this.registeDate = registeDate;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 注册ip
     */
    public String getCreateIp()
    {
        return createIp;
    }

    /**
     * 注册ip
     */
    public void setCreateIp(String createIp)
    {
        if (createIp != null && !createIp.equals(this.createIp))
        {
            this.createIp = createIp;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 地址信息
     */
    public String getLocation()
    {
        return location;
    }

    /**
     * 地址信息
     */
    public void setLocation(String location)
    {
        if (location != null && !location.equals(this.location))
        {
            this.location = location;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 激活手机
     */
    public boolean getActive()
    {
        return active;
    }

    /**
     * 激活手机
     */
    public void setActive(boolean active)
    {
        if (active != this.active)
        {
            this.active = active;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 是否封号(0 未封 1 已封)
     */
    public boolean getSuspend()
    {
        return suspend;
    }

    /**
     * 是否封号(0 未封 1 已封)
     */
    public void setSuspend(boolean suspend)
    {
        if (suspend != this.suspend)
        {
            this.suspend = suspend;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 封号原因
     */
    public String getSuspendCause()
    {
        return suspendCause;
    }

    /**
     * 封号原因
     */
    public void setSuspendCause(String suspendCause)
    {
        if (suspendCause != null && !suspendCause.equals(this.suspendCause))
        {
            this.suspendCause = suspendCause;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 来源标识
     */
    public String getSign()
    {
        return sign;
    }

    /**
     * 来源标识
     */
    public void setSign(String sign)
    {
        if (sign != null && !sign.equals(this.sign))
        {
            this.sign = sign;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 平台类型(0:h5 1:安卓 2：ios )
     */
    public int getPlatformType()
    {
        return platformType;
    }

    /**
     * 平台类型(0:h5 1:安卓 2：ios )
     */
    public void setPlatformType(int platformType)
    {
        if (platformType != this.platformType)
        {
            this.platformType = platformType;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 是否是机器人
     */
    public boolean getIsRobot()
    {
        return isRobot;
    }

    /**
     * 是否是机器人
     */
    public void setIsRobot(boolean isRobot)
    {
        if (isRobot != this.isRobot)
        {
            this.isRobot = isRobot;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 代理ID
     */
    public String getAgentId()
    {
        return agentId;
    }

    /**
     * 代理ID
     */
    public void setAgentId(String agentId)
    {
        if (agentId != null && !agentId.equals(this.agentId))
        {
            this.agentId = agentId;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 
     */
    public int getTotalTimes()
    {
        return totalTimes;
    }

    /**
     * 
     */
    public void setTotalTimes(int totalTimes)
    {
        if (totalTimes != this.totalTimes)
        {
            this.totalTimes = totalTimes;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 状态记录
     */
    public int getMask()
    {
        return mask;
    }

    /**
     * 状态记录
     */
    public void setMask(int mask)
    {
        if (mask != this.mask)
        {
            this.mask = mask;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 性别(1:男,2:女)
     */
    public int getSex()
    {
        return sex;
    }

    /**
     * 性别(1:男,2:女)
     */
    public void setSex(int sex)
    {
        if (sex != this.sex)
        {
            this.sex = sex;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 银行卡(银行名称:卡号:真实姓名)
     */
    public String getBankCard()
    {
        return bankCard;
    }

    /**
     * 银行卡(银行名称:卡号:真实姓名)
     */
    public void setBankCard(String bankCard)
    {
        if (bankCard != null && !bankCard.equals(this.bankCard))
        {
            this.bankCard = bankCard;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 我的邀请码
     */
    public int getMyInviteCode()
    {
        return myInviteCode;
    }

    /**
     * 我的邀请码
     */
    public void setMyInviteCode(int myInviteCode)
    {
        if (myInviteCode != this.myInviteCode)
        {
            this.myInviteCode = myInviteCode;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 邀请码
     */
    public int getInviteCode()
    {
        return inviteCode;
    }

    /**
     * 邀请码
     */
    public void setInviteCode(int inviteCode)
    {
        if (inviteCode != this.inviteCode)
        {
            this.inviteCode = inviteCode;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 玩家状态(0:离线,1:大厅,2:游戏内)
     */
    public int getState()
    {
        return state;
    }

    /**
     * 玩家状态(0:离线,1:大厅,2:游戏内)
     */
    public void setState(int state)
    {
        if (state != this.state)
        {
            this.state = state;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 登录时间
     */
    public Date getLoginTime()
    {
        return loginTime;
    }

    /**
     * 登录时间
     */
    public void setLoginTime(Date loginTime)
    {
        if (loginTime != null && !loginTime.equals(this.loginTime))
        {
            this.loginTime = loginTime;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 离线时间
     */
    public Date getOfflineTime()
    {
        return offlineTime;
    }

    /**
     * 离线时间
     */
    public void setOfflineTime(Date offlineTime)
    {
        if (offlineTime != null && !offlineTime.equals(this.offlineTime))
        {
            this.offlineTime = offlineTime;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 个性签名
     */
    public String getSignature()
    {
        return signature;
    }

    /**
     * 个性签名
     */
    public void setSignature(String signature)
    {
        if (signature != null && !signature.equals(this.signature))
        {
            this.signature = signature;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 总游戏局数
     */
    public int getTotalGames()
    {
        return totalGames;
    }

    /**
     * 总游戏局数
     */
    public void setTotalGames(int totalGames)
    {
        if (totalGames != this.totalGames)
        {
            this.totalGames = totalGames;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 赢的场次
     */
    public int getWinGames()
    {
        return winGames;
    }

    /**
     * 赢的场次
     */
    public void setWinGames(int winGames)
    {
        if (winGames != this.winGames)
        {
            this.winGames = winGames;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 今日砖石消耗
     */
    public java.math.BigDecimal getTodayBunk()
    {
        return todayBunk;
    }

    /**
     * 今日砖石消耗
     */
    public void setTodayBunk(java.math.BigDecimal todayBunk)
    {
        if (todayBunk != this.todayBunk)
        {
            this.todayBunk = todayBunk;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 当月砖石消耗
     */
    public java.math.BigDecimal getMonthBunk()
    {
        return monthBunk;
    }

    /**
     * 当月砖石消耗
     */
    public void setMonthBunk(java.math.BigDecimal monthBunk)
    {
        if (monthBunk != this.monthBunk)
        {
            this.monthBunk = monthBunk;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 成为代理的时间
     */
    public Date getJoinInviteTime()
    {
        return joinInviteTime;
    }

    /**
     * 成为代理的时间
     */
    public void setJoinInviteTime(Date joinInviteTime)
    {
        if (joinInviteTime != null && !joinInviteTime.equals(this.joinInviteTime))
        {
            this.joinInviteTime = joinInviteTime;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 绑定代理的时间
     */
    public Date getBindInviteTime()
    {
        return bindInviteTime;
    }

    /**
     * 绑定代理的时间
     */
    public void setBindInviteTime(Date bindInviteTime)
    {
        if (bindInviteTime != null && !bindInviteTime.equals(this.bindInviteTime))
        {
            this.bindInviteTime = bindInviteTime;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 所属游戏
     */
    public int getGameId()
    {
        return gameId;
    }

    /**
     * 所属游戏
     */
    public void setGameId(int gameId)
    {
        if (gameId != this.gameId)
        {
            this.gameId = gameId;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 每天最大审核额度
     */
    public java.math.BigDecimal getExchangeDayCheck()
    {
        return exchangeDayCheck;
    }

    /**
     * 每天最大审核额度
     */
    public void setExchangeDayCheck(java.math.BigDecimal exchangeDayCheck)
    {
        if (exchangeDayCheck != this.exchangeDayCheck)
        {
            this.exchangeDayCheck = exchangeDayCheck;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 转盘总积分
     */
    public java.math.BigDecimal getTurnTableTotal()
    {
        return turnTableTotal;
    }

    /**
     * 转盘总积分
     */
    public void setTurnTableTotal(java.math.BigDecimal turnTableTotal)
    {
        if (turnTableTotal != this.turnTableTotal)
        {
            this.turnTableTotal = turnTableTotal;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 可用兑换转盘积分
     */
    public java.math.BigDecimal getTurnTableAvailable()
    {
        return turnTableAvailable;
    }

    /**
     * 可用兑换转盘积分
     */
    public void setTurnTableAvailable(java.math.BigDecimal turnTableAvailable)
    {
        if (turnTableAvailable != this.turnTableAvailable)
        {
            this.turnTableAvailable = turnTableAvailable;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 当天转盘次数
     */
    public int getTodayTime()
    {
        return todayTime;
    }

    /**
     * 当天转盘次数
     */
    public void setTodayTime(int todayTime)
    {
        if (todayTime != this.todayTime)
        {
            this.todayTime = todayTime;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 重置时间
     */
    public Date getUpdateTime()
    {
        return updateTime;
    }

    /**
     * 重置时间
     */
    public void setUpdateTime(Date updateTime)
    {
        if (updateTime != null && !updateTime.equals(this.updateTime))
        {
            this.updateTime = updateTime;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 二级密码
     */
    public String getSecondPassword()
    {
        return secondPassword;
    }

    /**
     * 二级密码
     */
    public void setSecondPassword(String secondPassword)
    {
        if (secondPassword != null && !secondPassword.equals(this.secondPassword))
        {
            this.secondPassword = secondPassword;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 救济金次数
     */
    public int getRelieveTimes()
    {
        return relieveTimes;
    }

    /**
     * 救济金次数
     */
    public void setRelieveTimes(int relieveTimes)
    {
        if (relieveTimes != this.relieveTimes)
        {
            this.relieveTimes = relieveTimes;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 团队码
     */
    public int getTeamCode()
    {
        return teamCode;
    }

    /**
     * 团队码
     */
    public void setTeamCode(int teamCode)
    {
        if (teamCode != this.teamCode)
        {
            this.teamCode = teamCode;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 加入团队时间
     */
    public Date getJoinTeamTime()
    {
        return joinTeamTime;
    }

    /**
     * 加入团队时间
     */
    public void setJoinTeamTime(Date joinTeamTime)
    {
        if (joinTeamTime != null && !joinTeamTime.equals(this.joinTeamTime))
        {
            this.joinTeamTime = joinTeamTime;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 实名认证
     */
    public String getRealNameSystem()
    {
        return realNameSystem;
    }

    /**
     * 实名认证
     */
    public void setRealNameSystem(String realNameSystem)
    {
        if (realNameSystem != null && !realNameSystem.equals(this.realNameSystem))
        {
            this.realNameSystem = realNameSystem;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 签到
     */
    public int getSignIn()
    {
        return signIn;
    }

    /**
     * 签到
     */
    public void setSignIn(int signIn)
    {
        if (signIn != this.signIn)
        {
            this.signIn = signIn;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 玩家等级
     */
    public int getUserLevel()
    {
        return userLevel;
    }

    /**
     * 玩家等级
     */
    public void setUserLevel(int userLevel)
    {
        if (userLevel != this.userLevel)
        {
            this.userLevel = userLevel;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 总充值
     */
    public int getTotalRecharge()
    {
        return totalRecharge;
    }

    /**
     * 总充值
     */
    public void setTotalRecharge(int totalRecharge)
    {
        if (totalRecharge != this.totalRecharge)
        {
            this.totalRecharge = totalRecharge;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 总解散次数
     */
    public int getTotalDissolution()
    {
        return totalDissolution;
    }

    /**
     * 总解散次数
     */
    public void setTotalDissolution(int totalDissolution)
    {
        if (totalDissolution != this.totalDissolution)
        {
            this.totalDissolution = totalDissolution;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 被举报次数
     */
    public int getTipOffTimes()
    {
        return tipOffTimes;
    }

    /**
     * 被举报次数
     */
    public void setTipOffTimes(int tipOffTimes)
    {
        if (tipOffTimes != this.tipOffTimes)
        {
            this.tipOffTimes = tipOffTimes;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 用户评级
     */
    public int getUserEvaluate()
    {
        return userEvaluate;
    }

    /**
     * 用户评级
     */
    public void setUserEvaluate(int userEvaluate)
    {
        if (userEvaluate != this.userEvaluate)
        {
            this.userEvaluate = userEvaluate;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 赛季
     */
    public int getCompetitionSeason()
    {
        return competitionSeason;
    }

    /**
     * 赛季
     */
    public void setCompetitionSeason(int competitionSeason)
    {
        if (competitionSeason != this.competitionSeason)
        {
            this.competitionSeason = competitionSeason;
            setOp(DataOption.UPDATE);
        }
    }

    /**
     * 过期时间
     */
    public Date getHangExpireTime()
    {
        return hangExpireTime;
    }

    /**
     * 过期时间
     */
    public void setHangExpireTime(Date hangExpireTime)
    {
        if (hangExpireTime != null && !hangExpireTime.equals(this.hangExpireTime))
        {
            this.hangExpireTime = hangExpireTime;
            setOp(DataOption.UPDATE);
        }
    }

}
