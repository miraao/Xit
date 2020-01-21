// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.recommend;


public class RecommendData
{

    public RecommendData(int i, int j, String s)
    {
        mServiceId = -1;
        mEventId = -1;
        mBroadcastType = null;
        mRecommend = false;
        mPixRecommend = false;
        mServiceId = i;
        mEventId = j;
        mBroadcastType = s;
    }

    public String getBroadcastType()
    {
        return mBroadcastType;
    }

    public int getEventId()
    {
        return mEventId;
    }

    public boolean getPixRecommend()
    {
        return mPixRecommend;
    }

    public boolean getRecommend()
    {
        return mRecommend;
    }

    public int getServiceId()
    {
        return mServiceId;
    }

    public void setEventId(int i)
    {
        mEventId = i;
    }

    public void setPixRecommend(boolean flag)
    {
        mPixRecommend = flag;
    }

    public void setRecommend(boolean flag)
    {
        mRecommend = flag;
    }

    public void setServiceId(int i)
    {
        mServiceId = i;
    }

    private String mBroadcastType;
    private int mEventId;
    private boolean mPixRecommend;
    private boolean mRecommend;
    private int mServiceId;
}
