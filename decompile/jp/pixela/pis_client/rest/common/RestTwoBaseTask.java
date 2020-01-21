// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.common;

import android.os.AsyncTask;

// Referenced classes of package jp.pixela.pis_client.rest.common:
//            RestTwoResultParams

public abstract class RestTwoBaseTask extends AsyncTask
{
    public static interface RestTaskCallback
    {

        public abstract void onPostExecute(RestTwoBaseTask resttwobasetask, RestTwoResultParams resttworesultparams);

        public abstract void onPreExecute(RestTwoBaseTask resttwobasetask);
    }


    protected RestTwoBaseTask(RestTaskCallback resttaskcallback)
    {
        mCallback = null;
        mCallback = resttaskcallback;
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((RestTwoResultParams)obj);
    }

    protected void onPostExecute(RestTwoResultParams resttworesultparams)
    {
        if(mCallback != null)
            mCallback.onPostExecute(this, resttworesultparams);
        else
            super.onPostExecute(resttworesultparams);
    }

    protected void onPreExecute()
    {
        if(mCallback != null)
            mCallback.onPreExecute(this);
        else
            super.onPreExecute();
    }

    private RestTaskCallback mCallback;
}
