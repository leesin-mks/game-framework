/*
 * ExecuteUpdate
 *
 * 2017年10月16日
 *
 * All rights reserved. This material is confidential and proprietary to BDSK
 */
package com.game.hotswap;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.Base64;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

/**
 * @author dsfish
 *
 */
public class ExecuteUpdate
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteUpdate.class);

    public boolean executeUpdate(String message)
    {
        try
        {
            File agentFile = new File("./reload/agent.jar");
            FileUtils.writeByteArrayToFile(agentFile, Base64.getDecoder().decode(message), false);
            // 为了防止修改的类没有加载到JVM。先用classloader加载一下。
            JarFile agentJar = new JarFile(agentFile);
            Manifest manifest = agentJar.getManifest();
            Attributes attributes = manifest.getMainAttributes();
            String fileParams = attributes.getValue("File-Params");
            ClassLoader classLoader = this.getClass().getClassLoader();
            for (String jarEntryName : fileParams.split(","))
            {
                String className = jarEntryName.replaceAll("\\.class", "").replaceAll("/", ".");
                classLoader.getResource(className);
                // 检测类是否存在。
                if (classLoader.getResource(className) != null)
                {
                    classLoader.loadClass(className);
                }
            }
            agentJar.close();
            LOGGER.debug("save agent.jar success!");
            if (doAgentReload(agentFile))
                return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean doAgentReload(File agent)
    {
        // 替换agent.jar
        VirtualMachineDescriptor gameVMDescriptor = null;
        String serverPid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        for (VirtualMachineDescriptor vmDescriptor : VirtualMachine.list())
        {
            String pid = vmDescriptor.id();
            if (serverPid.equals(pid))
            {
                gameVMDescriptor = vmDescriptor;
                break;
            }
        }
        try
        {
            if (gameVMDescriptor != null)
            {
                if (agent.exists())
                {
                    VirtualMachine virtualMachine = VirtualMachine.attach(gameVMDescriptor);
                    virtualMachine.loadAgent(agent.getCanonicalPath(), agent.getCanonicalPath());
                    virtualMachine.detach();
                }
                else
                {
                    throw new IllegalStateException(String.format("AgentFile[%s]不存在！", agent.getCanonicalPath()));
                }
            }
            else
            {
                throw new IllegalStateException(String.format("没有找到匹配进程!"));
            }
        }
        catch (Throwable e)
        {
            e.printStackTrace();
            return false;
        }
        LOGGER.debug(String.format("Agent Reload execute success!"));
        return true;
    }

}
