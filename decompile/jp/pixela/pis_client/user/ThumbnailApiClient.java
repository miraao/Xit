// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.user;

import android.content.Context;
import java.text.SimpleDateFormat;
import java.util.Date;
import jp.pixela.common.PxLog;
import jp.pixela.pis_client.rest.common.*;
import jp.pixela.pis_client.rest.thumbnail.*;

public class ThumbnailApiClient
    implements IThumbnailClient, jp.pixela.pis_client.rest.common.RestBaseTask.RestTaskCallback
{

    public ThumbnailApiClient(jp.pixela.pis_client.rest.thumbnail.IThumbnailClient.ThumbnailClientCallback thumbnailclientcallback)
    {
        mCallback = null;
        mCallback = thumbnailclientcallback;
    }

    private String getFormattedDateString(long l)
    {
        return (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")).format(new Date(l * 1000L));
    }

    private jp.pixela.pis_client.rest.thumbnail.IThumbnailClient.ClientResult requestThumbnail(Context context, String s, String s1, String s2, Integer ainteger[])
    {
        PxLog.d(TAG, "requestThumbnail in");
        jp.pixela.pis_client.rest.thumbnail.IThumbnailClient.ClientResult clientresult = jp.pixela.pis_client.rest.thumbnail.IThumbnailClient.ClientResult.FAILED;
        jp.pixela.pis_client.rest.thumbnail.IThumbnailClient.ClientResult clientresult1 = clientresult;
        if(context != null)
        {
            ThumbnailApiParam thumbnailapiparam = new ThumbnailApiParam();
            clientresult1 = clientresult;
            if(thumbnailapiparam != null)
            {
                thumbnailapiparam.setSince(s);
                thumbnailapiparam.setUntil(s1);
                thumbnailapiparam.setBroadcastType(s2);
                thumbnailapiparam.setServiceIdList(ainteger);
                (new ThumbnailRequestTask(context, this)).execute(new IRestItem[] {
                    thumbnailapiparam
                });
                clientresult1 = jp.pixela.pis_client.rest.thumbnail.IThumbnailClient.ClientResult.CONTINUE;
            }
        }
        return clientresult1;
    }

    public void onPostExecute(RestBaseTask restbasetask, RestResultParams restresultparams)
    {
        if(restresultparams != null && restresultparams.getResultCode() == 0)
        {
            restbasetask = restresultparams.getResponse();
            if(restbasetask != null && (restbasetask instanceof ThumbnailApiItem))
            {
                restbasetask = (ThumbnailApiItem)restbasetask;
                if(mCallback != null)
                    mCallback.onCompleteThumbnail(0, restbasetask.getList());
                return;
            }
        }
        if(mCallback != null)
            mCallback.onCompleteThumbnail(-1, null);
    }

    public void onPreExecute(RestBaseTask restbasetask)
    {
    }

    public boolean request(Context context, long l, long l1, String s, Integer ainteger[])
    {
        String s1 = TAG;
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append("request in. since=");
        ((StringBuilder) (obj)).append(l);
        ((StringBuilder) (obj)).append(", until=");
        ((StringBuilder) (obj)).append(l1);
        ((StringBuilder) (obj)).append(", broadcastType=");
        ((StringBuilder) (obj)).append(s);
        PxLog.v(s1, ((StringBuilder) (obj)).toString());
        if(l >= 0L)
            s1 = getFormattedDateString(l - 32400L);
        else
            s1 = null;
        if(l1 >= 0L)
            obj = getFormattedDateString(l1 - 32400L);
        else
            obj = null;
        boolean flag = false;
        context = requestThumbnail(context, s1, ((String) (obj)), s, ainteger);
        if(context == jp.pixela.pis_client.rest.thumbnail.IThumbnailClient.ClientResult.SUCCESS || context == jp.pixela.pis_client.rest.thumbnail.IThumbnailClient.ClientResult.CONTINUE)
            flag = true;
        if((context == jp.pixela.pis_client.rest.thumbnail.IThumbnailClient.ClientResult.SUCCESS || context == jp.pixela.pis_client.rest.thumbnail.IThumbnailClient.ClientResult.FAILED) && mCallback != null)
            mCallback.onCompleteThumbnail(-1, null);
        return flag;
    }

    private static final String TAG = "ThumbnailApiClient";
    private jp.pixela.pis_client.rest.thumbnail.IThumbnailClient.ThumbnailClientCallback mCallback;

}
