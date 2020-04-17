/*
 * TokenBean
 *
 * 2016年9月6日
 *
 * All rights reserved. This material is confidential and proprietary to Jacken
 */
package com.game.web.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author Jacken
 *
 */
public class TokenBean
{
    @SerializedName("access_token")
    private String access_token;

    @SerializedName("expires_in")
    private String expires_in;

    @SerializedName("refresh_token")
    private String refresh_token;

    @SerializedName("openid")
    private String openid;

    @SerializedName("scope")
    private String scope;

    @SerializedName("unionid")
    private String unionid;

    /**
     * @return the access_token
     */
    public String getAccess_token()
    {
        return access_token;
    }

    /**
     * @param access_token the access_token to set
     */
    public void setAccess_token(String access_token)
    {
        this.access_token = access_token;
    }

    /**
     * @return the expires_in
     */
    public String getExpires_in()
    {
        return expires_in;
    }

    /**
     * @param expires_in the expires_in to set
     */
    public void setExpires_in(String expires_in)
    {
        this.expires_in = expires_in;
    }

    /**
     * @return the refresh_token
     */
    public String getRefresh_token()
    {
        return refresh_token;
    }

    /**
     * @param refresh_token the refresh_token to set
     */
    public void setRefresh_token(String refresh_token)
    {
        this.refresh_token = refresh_token;
    }

    /**
     * @return the openid
     */
    public String getOpenid()
    {
        return openid;
    }

    /**
     * @param openid the openid to set
     */
    public void setOpenid(String openid)
    {
        this.openid = openid;
    }

    /**
     * @return the scope
     */
    public String getScope()
    {
        return scope;
    }

    /**
     * @param scope the scope to set
     */
    public void setScope(String scope)
    {
        this.scope = scope;
    }

    /**
     * @return the unionid
     */
    public String getUnionid()
    {
        return unionid;
    }

    /**
     * @param unionid the unionid to set
     */
    public void setUnionid(String unionid)
    {
        this.unionid = unionid;
    }

}
