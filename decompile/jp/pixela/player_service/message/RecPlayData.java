// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.message;


// Referenced classes of package jp.pixela.player_service.message:
//            ProgramData

public class RecPlayData
{

    public RecPlayData()
    {
        mContentId = "";
        mStreamTime = 0L;
        mStreamSpeed = 0;
    }

    public String getContentId()
    {
        return mContentId;
    }

    public ProgramData getProgramData()
    {
        return mProgramData;
    }

    public int getStreamSpeed()
    {
        return mStreamSpeed;
    }

    public long getStreamTime()
    {
        return mStreamTime;
    }

    public void setContentId(String s)
    {
        mContentId = s;
    }

    public void setProgramData(ProgramData programdata)
    {
        mProgramData = programdata;
    }

    public void setStreamSpeed(int i)
    {
        mStreamSpeed = i;
    }

    public void setStreamTime(long l)
    {
        mStreamTime = l;
    }

    private String mContentId;
    private ProgramData mProgramData;
    private int mStreamSpeed;
    private long mStreamTime;
}
