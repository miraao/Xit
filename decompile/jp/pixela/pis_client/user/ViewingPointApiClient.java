// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.user;

import android.content.Context;
import jp.pixela.common.PxLog;
import jp.pixela.pis_client.rest.common.*;
import jp.pixela.pis_client.rest.rank.*;

public class ViewingPointApiClient
    implements IViewingPointClient, jp.pixela.pis_client.rest.common.RestBaseTask.RestTaskCallback
{

    public ViewingPointApiClient(jp.pixela.pis_client.rest.rank.IViewingPointClient.ViewingPointClientCallback viewingpointclientcallback)
    {
        mCallback = null;
        mCallback = viewingpointclientcallback;
    }

    private jp.pixela.pis_client.rest.rank.IViewingPointClient.ClientResult requestViewingPoint(Context context, String s, Integer ainteger[])
    {
        PxLog.d(TAG, "requestViewingPoint in");
        jp.pixela.pis_client.rest.rank.IViewingPointClient.ClientResult clientresult = jp.pixela.pis_client.rest.rank.IViewingPointClient.ClientResult.FAILED;
        jp.pixela.pis_client.rest.rank.IViewingPointClient.ClientResult clientresult1 = clientresult;
        if(context != null)
        {
            ViewingPointApiParam viewingpointapiparam = new ViewingPointApiParam();
            clientresult1 = clientresult;
            if(viewingpointapiparam != null)
            {
                viewingpointapiparam.setServiceIdList(ainteger);
                viewingpointapiparam.setBroadcastTypeString(s);
                (new ViewingPointRequestTask(context, this)).execute(new IRestItem[] {
                    viewingpointapiparam
                });
                clientresult1 = jp.pixela.pis_client.rest.rank.IViewingPointClient.ClientResult.CONTINUE;
            }
        }
        return clientresult1;
    }

    public void onPostExecute(RestBaseTask restbasetask, RestResultParams restresultparams)
    {
        if(restresultparams != null && restresultparams.getResultCode() == 0)
        {
            restbasetask = restresultparams.getResponse();
            if(restbasetask != null && (restbasetask instanceof ViewingPointApiItem))
            {
                restbasetask = (ViewingPointApiItem)restbasetask;
                if(mCallback != null)
                    mCallback.onCompleteViewingPoint(0, restbasetask.getBroadcastType(), restbasetask.getList());
                return;
            }
        }
        if(mCallback != null)
            mCallback.onCompleteViewingPoint(-1, null, null);
    }

    public void onPreExecute(RestBaseTask restbasetask)
    {
    }

    public boolean request(Context context, String s, Integer ainteger[])
    {
        context = requestViewingPoint(context, s, ainteger);
        boolean flag;
        if(context != jp.pixela.pis_client.rest.rank.IViewingPointClient.ClientResult.SUCCESS && context != jp.pixela.pis_client.rest.rank.IViewingPointClient.ClientResult.CONTINUE)
            flag = false;
        else
            flag = true;
        if((context == jp.pixela.pis_client.rest.rank.IViewingPointClient.ClientResult.SUCCESS || context == jp.pixela.pis_client.rest.rank.IViewingPointClient.ClientResult.FAILED) && mCallback != null)
            mCallback.onCompleteViewingPoint(-1, null, null);
        return flag;
    }

    private static final String TAG = "ViewingPointApiClient";
    private jp.pixela.pis_client.rest.rank.IViewingPointClient.ViewingPointClientCallback mCallback;

}
