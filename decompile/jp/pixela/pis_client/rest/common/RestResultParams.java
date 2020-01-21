// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.common;


// Referenced classes of package jp.pixela.pis_client.rest.common:
//            IRestItem

public class RestResultParams
{

    public RestResultParams(int i, IRestItem irestitem)
    {
        mResultCode = 0;
        mResponse = null;
        mResultCode = i;
        mResponse = irestitem;
    }

    public IRestItem getResponse()
    {
        return mResponse;
    }

    public int getResultCode()
    {
        return mResultCode;
    }

    private IRestItem mResponse;
    private int mResultCode;
}
