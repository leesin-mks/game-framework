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

package com.game.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Provide for a Uptime class that is compatible with Android, GAE, and the new Java 8 compact profiles
 */
public class Uptime
{
    public static final int NO_IMPL = -1;

    public  interface Impl
    {
         long getUptime();
    }

    public static class DefaultImpl implements Impl
    {
        public Object mxBean;
        public Method uptimeMethod;

        public DefaultImpl()
        {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            try
            {
                Class<?> mgmtFactory = Class.forName("java.lang.management.ManagementFactory",true,cl);
                Class<?> runtimeClass = Class.forName("java.lang.management.RuntimeMXBean",true,cl);
                Class<?>[] noParams = new Class<?>[0];
                Method mxBeanMethod = mgmtFactory.getMethod("getRuntimeMXBean",noParams);

                mxBean = mxBeanMethod.invoke(mgmtFactory);
                if (mxBean == null)
                {
                    throw new UnsupportedOperationException("getRuntimeMXBean() method returned null");
                }
                uptimeMethod = runtimeClass.getMethod("getUptime",noParams);
            }
            catch (ClassNotFoundException |
                   NoClassDefFoundError |
                   NoSuchMethodException |
                   SecurityException |
                   IllegalAccessException |
                   IllegalArgumentException |
                   InvocationTargetException e)
            {
                throw new UnsupportedOperationException("Implementation not available in this environment",e);
            }
        }

        @Override
        public long getUptime()
        {
            try
            {
                return (long)uptimeMethod.invoke(mxBean);
            }
            catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
            {
                return NO_IMPL;
            }
        }
    }

    private static final Uptime INSTANCE = new Uptime();

    public static Uptime getInstance()
    {
        return INSTANCE;
    }

    private Impl impl;

    private Uptime()
    {
        try
        {
            impl = new DefaultImpl();
        }
        catch (UnsupportedOperationException e)
        {
            System.err.printf("Defaulting Uptime to NO_IMPL due to (%s) %s%n",e.getClass().getName(),e.getMessage());
            impl = null;
        }
    }

    public Impl getImpl()
    {
        return impl;
    }

    public void setImpl(Impl impl)
    {
        this.impl = impl;
    }

    public static long getUptime()
    {
        Uptime u = getInstance();
        if (u == null || u.impl == null)
        {
            return NO_IMPL;
        }
        return u.impl.getUptime();
    }
}
