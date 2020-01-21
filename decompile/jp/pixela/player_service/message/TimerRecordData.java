// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.message;


public class TimerRecordData
{

    public TimerRecordData()
    {
        mBroadcastType = 0;
        mServiceId = 0;
        mOriginalNetworkId = 0;
        mRecStartTime = 0L;
        mRecEndTime = 0L;
    }

    public int getBroadcastType()
    {
        return mBroadcastType;
    }

    public int getOriginalNetworkId()
    {
        return mOriginalNetworkId;
    }

    public long getRecEndTime()
    {
        return mRecEndTime;
    }

    public long getRecStartTime()
    {
        return mRecStartTime;
    }

    public int getServiceId()
    {
        return mServiceId;
    }

    public void setBroadcastType(int i)
    {
        mBroadcastType = i;
    }

    public void setOriginalNetworkId(int i)
    {
        mOriginalNetworkId = i;
    }

    public void setRecEndTime(long l)
    {
        mRecEndTime = l;
    }

    public void setRecStartTime(long l)
    {
        mRecStartTime = l;
    }

    public void setServiceId(int i)
    {
        mServiceId = i;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("TimerRecordData {, BroadcastType=");
        stringbuilder.append(mBroadcastType);
        stringbuilder.append(", ServiceId=");
        stringbuilder.append(mServiceId);
        stringbuilder.append(", OriginalNetworkId=");
        stringbuilder.append(mOriginalNetworkId);
        stringbuilder.append(", RecStartTime=");
        stringbuilder.append(mRecStartTime);
        stringbuilder.append(", RecEndTime=");
        stringbuilder.append(mRecEndTime);
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    private int mBroadcastType;
    private int mOriginalNetworkId;
    private long mRecEndTime;
    private long mRecStartTime;
    private int mServiceId;
}
