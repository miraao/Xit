// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.common;


// Referenced classes of package jp.pixela.pis_client.rest.common:
//            IRestResponse, IRestItem

public interface IRestClient
{

    public abstract int delete(String s);

    public abstract int get(String s);

    public abstract IRestResponse getResponse();

    public abstract int post(String s, IRestItem irestitem);

    public abstract int put(String s, IRestItem irestitem);
}
