// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;


public class VersionClass
{

    public VersionClass()
    {
    }

    public static String toString(VersionClass versionclass)
    {
        if(versionclass == null)
            return "";
        else
            return String.format("%d.%d.%d", new Object[] {
                Integer.valueOf(versionclass.getMajorVersion()), Integer.valueOf(versionclass.getMinorVersion()), Integer.valueOf(versionclass.getSubMinorVersion())
            });
    }

    public int getMajorVersion()
    {
        return major_;
    }

    public int getMinorVersion()
    {
        return minor_;
    }

    public int getSubMinorVersion()
    {
        return sub_minor_;
    }

    public void setMajorVersion(int i)
    {
        major_ = i;
    }

    public void setMinorVersion(int i)
    {
        minor_ = i;
    }

    public void setSubMinorVersion(int i)
    {
        sub_minor_ = i;
    }

    private int major_;
    private int minor_;
    private int sub_minor_;
}
