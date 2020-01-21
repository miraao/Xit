// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.message;


public class BrowserStateInfo
{

    public BrowserStateInfo()
    {
    }

    public int GetUsedKeyFlag()
    {
        return mUsedKeyFlag;
    }

    public boolean GetVisibleFlag()
    {
        return mVisibleFlag;
    }

    public void SetUsedKeyFlag(int i)
    {
        mUsedKeyFlag = i;
    }

    public void SetVisibleFlag(boolean flag)
    {
        mVisibleFlag = flag;
    }

    private int mUsedKeyFlag;
    private boolean mVisibleFlag;
}
