// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.common;


public interface IRestConfig
{

    public abstract int getConnectTimeout();

    public abstract String getHostURL();

    public abstract int getReadTimeout();
}
