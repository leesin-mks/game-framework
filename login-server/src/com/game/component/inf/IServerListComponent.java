/*
 * IServerListComponent
 *
 * 2017年8月1日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
 */
package com.game.component.inf;

import java.util.List;
import java.util.Map;

import com.game.component.IComponent;
import com.game.entity.bean.ServerListBean;
import com.game.type.ServerType;
import com.game.web.ResultMessage;

/**
 * @author jacken
 *
 */
public interface IServerListComponent extends IComponent
{

    public static final String NAME = "ServerListComponent";

    List<ServerListBean> getServerListByType(ServerType type);

    ServerListBean getServerListByID(int id);

    void updateServerList();
}
