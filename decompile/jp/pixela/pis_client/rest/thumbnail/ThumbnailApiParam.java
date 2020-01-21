// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.thumbnail;

import jp.pixela.pis_client.rest.common.IRestItem;
import jp.pixela.pis_client.rest.common.json.IJsonRestItem;
import org.json.JSONObject;

public class ThumbnailApiParam
    implements IJsonRestItem
{

    public ThumbnailApiParam()
    {
        mSince = null;
        mUntil = null;
        mBroadcastType = null;
        mServiceIdList = null;
    }

    public IRestItem fromJSONObject(JSONObject jsonobject)
    {
        return null;
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

    public void initItem()
    {
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

    public JSONObject toJSONObject()
    {
        return null;
    }

    private String mBroadcastType;
    private Integer mServiceIdList[];
    private String mSince;
    private String mUntil;
}
