/*
 * ResultMessage
 *
 * 2016年2月17日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.web;

/**
 * @author jacken
 *
 */
public class ResultMessage
{
    /**
     * 返回状态，0表示失败，1表示成功
     */
    private int status;

    /**
     * 异常信息，返回状态为成功时为空
     */
    private String msg;

    /**
     * 返回标识，标记从哪个接口返回
     */
    private String site;

    /**
     * 登录返回信息
     */
    private String data;

    /**
     * @return the status
     */
    public int getStatus()
    {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status)
    {
        this.status = status;
    }

    /**
     * @return the msg
     */
    public String getMsg()
    {
        return msg;
    }

    /**
     * @param msg
     *            the msg to set
     */
    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    /**
     * @return the site
     */
    public String getSite()
    {
        return site;
    }

    /**
     * @param site
     *            the site to set
     */
    public void setSite(String site)
    {
        this.site = site;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }
}
