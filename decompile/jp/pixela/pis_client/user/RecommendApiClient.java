// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.user;

import android.content.Context;
import java.text.SimpleDateFormat;
import java.util.*;
import jp.pixela.common.PxLog;
import jp.pixela.pis_client.rest.common.*;
import jp.pixela.pis_client.rest.recommend.*;
import jp.pixela.pxlibs.utils.android.log.Logger;

public class RecommendApiClient
    implements IRecommendClient, jp.pixela.pis_client.rest.common.RestTwoBaseTask.RestTaskCallback
{

    public RecommendApiClient(jp.pixela.pis_client.rest.recommend.IRecommendClient.RecommendClientCallback recommendclientcallback)
    {
        mCallback = null;
        mCallback = recommendclientcallback;
    }

    private boolean checkSameProgram(RecommendData recommenddata, RecommendData recommenddata1)
    {
        return recommenddata.getBroadcastType().equals(recommenddata1.getBroadcastType()) && recommenddata.getServiceId() == recommenddata1.getServiceId() && recommenddata.getEventId() == recommenddata1.getEventId();
    }

    private String getFormattedDateString(long l)
    {
        return (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")).format(new Date(l * 1000L));
    }

    private jp.pixela.pis_client.rest.recommend.IRecommendClient.ClientResult requestRecommend(Context context, String s, String s1, String s2, Integer ainteger[], int i, String s3)
    {
        PxLog.d(TAG, "requestRecommend in");
        jp.pixela.pis_client.rest.recommend.IRecommendClient.ClientResult clientresult = jp.pixela.pis_client.rest.recommend.IRecommendClient.ClientResult.FAILED;
        jp.pixela.pis_client.rest.recommend.IRecommendClient.ClientResult clientresult1 = clientresult;
        if(context != null)
        {
            RecommendApiParam recommendapiparam = new RecommendApiParam();
            clientresult1 = clientresult;
            if(recommendapiparam != null)
            {
                recommendapiparam.setSince(s);
                recommendapiparam.setUntil(s1);
                recommendapiparam.setBroadcastType(s2);
                recommendapiparam.setServiceIdList(ainteger);
                recommendapiparam.setAreaCode(Integer.valueOf(i));
                recommendapiparam.setUserId(s3);
                (new RecommendRequestTask(context, this)).execute(new IRestItem[] {
                    recommendapiparam
                });
                clientresult1 = jp.pixela.pis_client.rest.recommend.IRecommendClient.ClientResult.CONTINUE;
            }
        }
        return clientresult1;
    }

    public void onPostExecute(RestTwoBaseTask resttwobasetask, RestTwoResultParams resttworesultparams)
    {
label0:
        {
            if(resttworesultparams == null || resttworesultparams.getResultCode1() != 0 || resttworesultparams.getResultCode2() != 0)
                break label0;
            Object obj = resttworesultparams.getResponse1();
            resttwobasetask = resttworesultparams.getResponse2();
            if(obj == null || !(obj instanceof RecommendApiItem) || resttwobasetask == null || !(resttwobasetask instanceof RecommendApiItem))
                break label0;
            resttworesultparams = (RecommendApiItem)obj;
            resttwobasetask = (RecommendApiItem)resttwobasetask;
            Object obj1 = resttworesultparams.getList();
            obj = resttwobasetask.getList();
            resttworesultparams = new ArrayList();
            resttwobasetask = new ArrayList();
            Iterator iterator = ((List) (obj1)).iterator();
label1:
            do
            {
                if(!iterator.hasNext())
                    break;
                RecommendData recommenddata1 = (RecommendData)iterator.next();
                Iterator iterator2 = ((List) (obj)).iterator();
                do
                    if(!iterator2.hasNext())
                        continue label1;
                while(!checkSameProgram(recommenddata1, (RecommendData)iterator2.next()));
                recommenddata1.setRecommend(true);
                recommenddata1.setPixRecommend(true);
                resttworesultparams.add(recommenddata1);
                resttwobasetask.add(recommenddata1);
            } while(true);
            obj1 = ((List) (obj1)).iterator();
            do
            {
                boolean flag = ((Iterator) (obj1)).hasNext();
                boolean flag1 = false;
                if(!flag)
                    break;
                RecommendData recommenddata2 = (RecommendData)((Iterator) (obj1)).next();
                Iterator iterator1 = resttworesultparams.iterator();
                boolean flag2;
                do
                {
                    flag2 = flag1;
                    if(!iterator1.hasNext())
                        break;
                    if(!checkSameProgram(recommenddata2, (RecommendData)iterator1.next()))
                        continue;
                    flag2 = true;
                    break;
                } while(true);
                if(!flag2)
                {
                    recommenddata2.setRecommend(true);
                    resttwobasetask.add(recommenddata2);
                }
            } while(true);
            obj = ((List) (obj)).iterator();
label2:
            do
            {
                RecommendData recommenddata;
                boolean flag3;
label3:
                {
                    if(!((Iterator) (obj)).hasNext())
                        break label2;
                    recommenddata = (RecommendData)((Iterator) (obj)).next();
                    for(Iterator iterator3 = resttworesultparams.iterator(); iterator3.hasNext();)
                        if(checkSameProgram(recommenddata, (RecommendData)iterator3.next()))
                        {
                            flag3 = true;
                            break label3;
                        }

                    flag3 = false;
                }
                if(!flag3)
                {
                    recommenddata.setPixRecommend(true);
                    resttwobasetask.add(recommenddata);
                }
            } while(true);
            if(mCallback != null)
                mCallback.onCompleteRecommend(0, resttwobasetask);
            return;
        }
        if(mCallback != null)
            mCallback.onCompleteRecommend(-1, null);
    }

    public void onPreExecute(RestTwoBaseTask resttwobasetask)
    {
    }

    public boolean request(Context context, long l, long l1, String s, Integer ainteger[], 
            int i, String s1)
    {
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append(LOG_HEAD);
        ((StringBuilder) (obj)).append("in. since=");
        ((StringBuilder) (obj)).append(l);
        ((StringBuilder) (obj)).append(", until=");
        ((StringBuilder) (obj)).append(l1);
        ((StringBuilder) (obj)).append(", broadcastType=");
        ((StringBuilder) (obj)).append(s);
        Logger.v(((StringBuilder) (obj)).toString(), new Object[0]);
        if(l >= 0L)
            obj = getFormattedDateString(l - 32400L);
        else
            obj = null;
        String s2;
        if(l1 >= 0L)
            s2 = getFormattedDateString(l1 - 32400L);
        else
            s2 = null;
        context = requestRecommend(context, ((String) (obj)), s2, s, ainteger, i, s1);
        boolean flag;
        if(context != jp.pixela.pis_client.rest.recommend.IRecommendClient.ClientResult.SUCCESS && context != jp.pixela.pis_client.rest.recommend.IRecommendClient.ClientResult.CONTINUE)
            flag = false;
        else
            flag = true;
        if((context == jp.pixela.pis_client.rest.recommend.IRecommendClient.ClientResult.SUCCESS || context == jp.pixela.pis_client.rest.recommend.IRecommendClient.ClientResult.FAILED) && mCallback != null)
            mCallback.onCompleteRecommend(-1, null);
        context = new StringBuilder();
        context.append(LOG_HEAD);
        context.append("out. result=");
        context.append(flag);
        Logger.v(context.toString(), new Object[0]);
        return flag;
    }

    private static final String LOG_HEAD;
    private static final String TAG = "RecommendApiClient";
    private jp.pixela.pis_client.rest.recommend.IRecommendClient.RecommendClientCallback mCallback;

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/pis_client/user/RecommendApiClient.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }
}
