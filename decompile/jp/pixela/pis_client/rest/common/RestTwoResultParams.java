// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.common;


// Referenced classes of package jp.pixela.pis_client.rest.common:
//            RestResultParams, IRestItem

public class RestTwoResultParams
{

    public RestTwoResultParams(RestResultParams restresultparams, RestResultParams restresultparams1)
    {
        mResultCode1 = 0;
        mResponse1 = null;
        mResultCode2 = 0;
        mResponse2 = null;
        if(restresultparams != null)
        {
            mResultCode1 = restresultparams.getResultCode();
            mResponse1 = restresultparams.getResponse();
        }
        if(restresultparams1 != null)
        {
            mResultCode2 = restresultparams1.getResultCode();
            mResponse2 = restresultparams1.getResponse();
        }
    }

    public IRestItem getResponse1()
    {
        return mResponse1;
    }

    public IRestItem getResponse2()
    {
        return mResponse2;
    }

    public int getResultCode1()
    {
        return mResultCode1;
    }

    public int getResultCode2()
    {
        return mResultCode2;
    }

    private IRestItem mResponse1;
    private IRestItem mResponse2;
    private int mResultCode1;
    private int mResultCode2;
}
