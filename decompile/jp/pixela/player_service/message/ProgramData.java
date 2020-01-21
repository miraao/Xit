// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.message;


public class ProgramData
{

    public ProgramData()
    {
        mEventId = 0;
        mTitle = "";
        mScheduledStartTime = 0L;
        mScheduledEndTime = 0L;
        mDescription = "";
        mGenre1 = 0;
        mGenre2 = 0;
        mGenre3 = 0;
        mRating = 0;
        mCaProgram = false;
        mBroadcastType = 0;
        mServiceId = 0;
        mOriginalNetworkId = 0;
        mServiceName = "";
    }

    public int getBroadcastType()
    {
        return mBroadcastType;
    }

    public boolean getCaProgram()
    {
        return mCaProgram;
    }

    public String getDescription()
    {
        return mDescription;
    }

    public int getEventId()
    {
        return mEventId;
    }

    public int getGenre1()
    {
        return mGenre1;
    }

    public int getGenre2()
    {
        return mGenre2;
    }

    public int getGenre3()
    {
        return mGenre3;
    }

    public int getOriginalNetworkId()
    {
        return mOriginalNetworkId;
    }

    public int getRating()
    {
        return mRating;
    }

    public long getScheduledEndTime()
    {
        return mScheduledEndTime;
    }

    public long getScheduledStartTime()
    {
        return mScheduledStartTime;
    }

    public int getServiceId()
    {
        return mServiceId;
    }

    public String getServiceName()
    {
        return mServiceName;
    }

    public String getTitle()
    {
        return mTitle;
    }

    public void setBroadcastType(int i)
    {
        mBroadcastType = i;
    }

    public void setCaProgram(boolean flag)
    {
        mCaProgram = flag;
    }

    public void setDescription(String s)
    {
        mDescription = s;
    }

    public void setEventId(int i)
    {
        mEventId = i;
    }

    public void setGenre1(int i)
    {
        mGenre1 = i;
    }

    public void setGenre2(int i)
    {
        mGenre2 = i;
    }

    public void setGenre3(int i)
    {
        mGenre3 = i;
    }

    public void setOriginalNetworkId(int i)
    {
        mOriginalNetworkId = i;
    }

    public void setRating(int i)
    {
        mRating = i;
    }

    public void setScheduledEndTime(long l)
    {
        mScheduledEndTime = l;
    }

    public void setScheduledStartTime(long l)
    {
        mScheduledStartTime = l;
    }

    public void setServiceId(int i)
    {
        mServiceId = i;
    }

    public void setServiceName(String s)
    {
        mServiceName = s;
    }

    public void setTitle(String s)
    {
        mTitle = s;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("ProgramData {EventId=");
        stringbuilder.append(mEventId);
        stringbuilder.append(", Title=");
        stringbuilder.append(mTitle);
        stringbuilder.append(", ScheduledStartTime=");
        stringbuilder.append(mScheduledStartTime);
        stringbuilder.append(", ScheduledEndTime=");
        stringbuilder.append(mScheduledEndTime);
        stringbuilder.append(", Description=");
        stringbuilder.append(mDescription);
        stringbuilder.append(", Genre1=");
        stringbuilder.append(mGenre1);
        stringbuilder.append(", Genre2=");
        stringbuilder.append(mGenre2);
        stringbuilder.append(", Genre3=");
        stringbuilder.append(mGenre3);
        stringbuilder.append(", Rating=");
        stringbuilder.append(mRating);
        stringbuilder.append(", CaProgram=");
        stringbuilder.append(mCaProgram);
        stringbuilder.append(", BroadcastType=");
        stringbuilder.append(mBroadcastType);
        stringbuilder.append(", ServiceId=");
        stringbuilder.append(mServiceId);
        stringbuilder.append(", OriginalNetworkId=");
        stringbuilder.append(mOriginalNetworkId);
        stringbuilder.append(", ServiceName=");
        stringbuilder.append(mServiceName);
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    private int mBroadcastType;
    private boolean mCaProgram;
    private String mDescription;
    private int mEventId;
    private int mGenre1;
    private int mGenre2;
    private int mGenre3;
    private int mOriginalNetworkId;
    private int mRating;
    private long mScheduledEndTime;
    private long mScheduledStartTime;
    private int mServiceId;
    private String mServiceName;
    private String mTitle;
}
