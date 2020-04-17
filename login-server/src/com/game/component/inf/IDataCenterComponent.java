package com.game.component.inf;

import com.game.component.IComponent;

public interface IDataCenterComponent extends IComponent
{
    public static final String NAME = "IDataCenterComponent";

    boolean isValidRequest(String key);

    void clearRequestJob();

}
