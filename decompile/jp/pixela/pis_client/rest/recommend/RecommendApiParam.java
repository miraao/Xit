// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.recommend;

import jp.pixela.pis_client.rest.common.IRestItem;
import jp.pixela.pis_client.rest.common.json.IJsonRestItem;
import org.json.JSONObject;

public class RecommendApiParam
    implements IJsonRestItem
{

    public RecommendApiParam()
    {
        mSince = null;
        mUntil = null;
        mBroadcastType = null;
        mServiceIdList = null;
        mAreaCode = Integer.valueOf(0);
        mUserId = null;
    }

    public IRestItem fromJSONObject(JSONObject jsonobject)
    {
        return null;
    }

    public Integer getAreaCode()
    {
        return mAreaCode;
    }

    public String getBroadcastType()
    {
        return mBroadcastType;
    }

    public Integer[] getServiceIdList()
    {
        return mServiceIdList;
    }

    public String getSince()
    {
        return mSince;
    }

    public String getUntil()
    {
        return mUntil;
    }

    public String getUserId()
    {
        return mUserId;
    }

    public void initItem()
    {
    }

    public void setAreaCode(Integer integer)
    {
        mAreaCode = integer;
    }

    public void setBroadcastType(String s)
    {
        mBroadcastType = s;
    }

    public void setServiceIdList(Integer ainteger[])
    {
        mServiceIdList = ainteger;
    }

    public void setSince(String s)
    {
        mSince = s;
    }

    public void setUntil(String s)
    {
        mUntil = s;
    }

    public void setUserId(String s)
    {
        mUserId = s;
    }

    public JSONObject toJSONObject()
    {
        return null;
    }

    private Integer mAreaCode;
    private String mBroadcastType;
    private Integer mServiceIdList[];
    private String mSince;
    private String mUntil;
    private String mUserId;
}
