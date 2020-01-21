// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.rank;

import jp.pixela.pis_client.rest.common.IRestItem;
import jp.pixela.pis_client.rest.common.json.IJsonRestItem;
import org.json.JSONObject;

public class ViewingPointApiParam
    implements IJsonRestItem
{

    public ViewingPointApiParam()
    {
        mServiceIdList = null;
        mBroadcastType = null;
    }

    public IRestItem fromJSONObject(JSONObject jsonobject)
    {
        return null;
    }

    public String getBroadcastTypeString()
    {
        return mBroadcastType;
    }

    public Integer[] getServiceIdList()
    {
        return mServiceIdList;
    }

    public void initItem()
    {
    }

    public void setBroadcastTypeString(String s)
    {
        mBroadcastType = s;
    }

    public void setServiceIdList(Integer ainteger[])
    {
        mServiceIdList = ainteger;
    }

    public JSONObject toJSONObject()
    {
        return null;
    }

    private String mBroadcastType;
    private Integer mServiceIdList[];
}
