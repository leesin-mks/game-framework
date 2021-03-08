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

package com.game.component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.component.inf.IDataCenterComponent;
import com.game.timer.ITimerComponent;
import com.game.type.GlobalVariable;
import com.game.web.bean.RequestFrequencyBean;
import com.game.web.job.ClearRequestFrequencyJob;

public class DataCenterComponent implements IDataCenterComponent
{
    private static Logger LOGGER = LoggerFactory.getLogger(DataCenterComponent.class);

    private static ITimerComponent timer;

    private Map<String, RequestFrequencyBean> requestFrequency;

    @Override
    public String getName()
    {
        return NAME;
    }

    @Override
    public boolean initialize()
    {
        requestFrequency = new HashMap<>();
        return true;
    }

    @Override
    public boolean start()
    {
        getTimer().addJob("ClearRequestFrequencyJob", ClearRequestFrequencyJob.class, 60 * 1000);
        return true;
    }

    @Override
    public void stop()
    {
        getTimer().deleteJob("TodayRankListJob");
        getTimer().deleteJob("ClearRequestFrequencyJob");
    }

    @Override
    public boolean reload()
    {
        stop();
        initialize();
        start();
        return true;
    }

    private static ITimerComponent getTimer()
    {
        if (timer == null)
        {
            timer = (ITimerComponent) ComponentManager.getInstance().getComponent(ITimerComponent.NAME);
        }
        return timer;
    }

    @Override
    public boolean isValidRequest(String key)
    {
        RequestFrequencyBean rfb = requestFrequency.get(key);
        if (rfb == null)
        {
            rfb = new RequestFrequencyBean(GlobalVariable.REQUEST_EXPIRE_TIME, GlobalVariable.REQUEST_FREQUENCY_TIME);
            requestFrequency.put(key, rfb);
            return true;
        }
        long tick = System.currentTimeMillis();
        if (tick < rfb.getBlackListTime())
        {
            return false;
        }
        if (rfb.getCount() < GlobalVariable.REQUEST_FREQUENCY)
        {
            rfb.addCount(false);
            return true;
        }
        if (tick > rfb.getRequestExpireTime())
        {
            rfb.reset(GlobalVariable.REQUEST_EXPIRE_TIME, GlobalVariable.REQUEST_FREQUENCY_TIME);
            return true;
        }
        rfb.setBlackList(true);
        return false;
    }

    @Override
    public void clearRequestJob()
    {
        Iterator<RequestFrequencyBean> it = requestFrequency.values().iterator();
        RequestFrequencyBean rfb;
        long tick = System.currentTimeMillis();
        while (it.hasNext())
        {
            rfb = it.next();
            if (tick > rfb.getExpireTime())
            {
                it.remove();
            }
        }
    }

}
