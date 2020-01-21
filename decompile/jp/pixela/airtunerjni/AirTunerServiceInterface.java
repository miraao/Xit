// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.airtunerjni;

import android.content.Context;

public interface AirTunerServiceInterface
{

    public abstract Context getContext();

    public abstract void notifyStopAirTuner(int i, boolean flag);

    public abstract void restartPxDMSDaemon();

    public abstract int restartPxjfClient(String s, int i, String s1, String s2, String s3, int j, String s4, 
            String s5, String s6, int k, int l, int i1, int j1, String s7);

    public abstract void sendCommandToPxjfClient(String s);
}
