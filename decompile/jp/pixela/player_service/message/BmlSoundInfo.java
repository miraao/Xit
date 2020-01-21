// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.message;


public class BmlSoundInfo
{

    public BmlSoundInfo()
    {
    }

    public String GetFilePath()
    {
        return mFilePath;
    }

    public int GetSoundId()
    {
        return mId;
    }

    public int GetSoundType()
    {
        return mType;
    }

    public boolean IsLoop()
    {
        return mIsLoop;
    }

    public boolean IsStart()
    {
        return mIsStart;
    }

    public void SetFilePath(String s)
    {
        mFilePath = s;
    }

    public void SetISStart(boolean flag)
    {
        mIsStart = flag;
    }

    public void SetIsLoop(boolean flag)
    {
        mIsLoop = flag;
    }

    public void SetSoundId(int i)
    {
        mId = i;
    }

    public void SetSoundType(int i)
    {
        mType = i;
    }

    private String mFilePath;
    private int mId;
    private boolean mIsLoop;
    private boolean mIsStart;
    private int mType;
}
