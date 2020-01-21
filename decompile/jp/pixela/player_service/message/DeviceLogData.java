// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.message;


public class DeviceLogData
{

    public DeviceLogData()
    {
        mVersionString = "";
        mSerialNumber = "";
        mModelName = "";
    }

    public String getModelName()
    {
        return mModelName;
    }

    public String getSerialNumber()
    {
        return mSerialNumber;
    }

    public String getVersion()
    {
        return mVersionString;
    }

    public void setModelName(String s)
    {
        mModelName = s;
    }

    public void setSerialNumber(String s)
    {
        mSerialNumber = s;
    }

    public void setVersion(String s)
    {
        mVersionString = s;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("mVersionString:");
        stringbuilder.append(mVersionString);
        stringbuilder.append(", mSerialNumber:");
        stringbuilder.append(mSerialNumber);
        stringbuilder.append(", mModelName:");
        stringbuilder.append(mModelName);
        return stringbuilder.toString();
    }

    private String mModelName;
    private String mSerialNumber;
    private String mVersionString;
}
