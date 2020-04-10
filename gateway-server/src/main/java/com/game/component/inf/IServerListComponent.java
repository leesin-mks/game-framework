package com.game.component.inf;

import java.util.List;

import com.game.component.IComponent;
import com.game.entity.bean.ServerListBean;

/**
 * @date 2020年03月31日 15:29
 * @auth zm
 */

public interface IServerListComponent extends IComponent
{
    public static final String NAME = "ServerListComponent";

    List<ServerListBean> getServerListByType(int type);
}
