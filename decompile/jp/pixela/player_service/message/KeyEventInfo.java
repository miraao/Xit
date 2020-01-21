// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.message;


public class KeyEventInfo
{

    public KeyEventInfo()
    {
    }

    public int GetKeyCode()
    {
        return mCode;
    }

    public int GetKeyState()
    {
        return mState;
    }

    public void SetKeyCode(int i)
    {
        mCode = i;
    }

    public void SetKeyState(int i)
    {
        mState = i;
    }

    private int mCode;
    private int mState;
}
