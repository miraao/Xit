// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.common;

import android.os.AsyncTask;

// Referenced classes of package jp.pixela.pis_client.rest.common:
//            RestResultParams

public abstract class RestBaseTask extends AsyncTask
{
    public static interface RestTaskCallback
    {

        public abstract void onPostExecute(RestBaseTask restbasetask, RestResultParams restresultparams);

        public abstract void onPreExecute(RestBaseTask restbasetask);
    }


    protected RestBaseTask(RestTaskCallback resttaskcallback)
    {
        mCallback = null;
        mCallback = resttaskcallback;
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((RestResultParams)obj);
    }

    protected void onPostExecute(RestResultParams restresultparams)
    {
        if(mCallback != null)
            mCallback.onPostExecute(this, restresultparams);
        else
            super.onPostExecute(restresultparams);
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
