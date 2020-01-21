// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.common.www;

import jp.pixela.pis_client.rest.common.IRestItem;

public interface IWwwRestItem
    extends IRestItem
{

    public abstract IRestItem fromStringObject(String s);

    public abstract void initItem();

    public abstract String toStringObject();
}
