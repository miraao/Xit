// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.message;


public class CognitiveSettingParam
{

    public CognitiveSettingParam()
    {
        mIsSendBehaviorHistory = false;
        mIsUseGoogleAssistant = false;
    }

    public String getDeviceName()
    {
        return mDeviceName;
    }

    public boolean isSendBehaviorHistory()
    {
        return mIsSendBehaviorHistory;
    }

    public boolean isUseGoogleAssistant()
    {
        return mIsUseGoogleAssistant;
    }

    public void setDeviceName(String s)
    {
        mDeviceName = s;
    }

    public void setSendBehaviorHistory(boolean flag)
    {
        mIsSendBehaviorHistory = flag;
    }

    public void setUseGoogleAssistant(boolean flag)
    {
        mIsUseGoogleAssistant = flag;
    }

    private String mDeviceName;
    private boolean mIsSendBehaviorHistory;
    private boolean mIsUseGoogleAssistant;
}
