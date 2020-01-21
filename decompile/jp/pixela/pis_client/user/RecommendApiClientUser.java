// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.user;

import android.content.Context;
import java.util.*;
import jp.pixela.common.PxLog;
import jp.pixela.pis_client.helper.NetworkUtility;
import jp.pixela.pis_client.helper.RecommendCacheHelper;
import jp.pixela.pis_client.rest.recommend.RecommendData;
import jp.pixela.player_service.message.ProgramInfo;
import jp.pixela.player_service.message.RecommendInfo;
import jp.pixela.player_service.tunerservice.ControlInterface;
import jp.pixela.pxlibs.utils.android.log.Logger;
import jp.pixela.pxlibs.utils.android.log.LoggerRTM;

// Referenced classes of package jp.pixela.pis_client.user:
//            RecommendApiClient

public class RecommendApiClientUser
{
    private class RequestRecommendThread extends Thread
        implements jp.pixela.pis_client.rest.recommend.IRecommendClient.RecommendClientCallback
    {

        private void completeRequest()
        {
            synchronized(mMutex)
            {
                mRequestQueue.remove(0);
            }
            mIsRequest = false;
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        private RequestParam getRequest()
        {
            RequestParam requestparam;
            synchronized(mMutex)
            {
                requestparam = (RequestParam)mRequestQueue.get(0);
            }
            return requestparam;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        private void intervalRequest()
        {
            try
            {
                Thread.sleep(100L);
            }
            catch(InterruptedException interruptedexception)
            {
                String s = RecommendApiClientUser.TAG;
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append("request recommend InterruptedException e=");
                stringbuilder.append(interruptedexception);
                PxLog.w(s, stringbuilder.toString());
            }
        }

        private void requestServer(RequestParam requestparam)
        {
            Integer ainteger[] = (Integer[])requestparam.mServiceIdList.toArray(new Integer[requestparam.mServiceIdList.size()]);
            (new RecommendApiClient(this)).request(mContext, requestparam.mSince, requestparam.mUntil, requestparam.mBroadcastType, ainteger, requestparam.mAreaCode, requestparam.mUserId);
        }

        public void cancel()
        {
            mThreadCancel = true;
        }

        public void onCompleteRecommend(int i, List list)
        {
            String s = RecommendApiClientUser.TAG;
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("onCompleteRecommend:resultCode=");
            stringbuilder.append(i);
            PxLog.v(s, stringbuilder.toString());
            if(i == 0 && list != null)
            {
                RequestParam requestparam = getRequest();
                if(requestparam == null)
                {
                    completeRequest();
                    return;
                }
                ArrayList arraylist = new ArrayList(list);
                addNonRecommendData(arraylist, requestparam.mProgramInfoList);
                RecommendCacheHelper.writeCache(mContext, arraylist, requestparam.mSince * 1000L);
                replaceRecommendData(list, requestparam.mProgramInfoList);
                if(list.size() > 0)
                {
                    RecommendInfo arecommendinfo[] = toRecommendInfoArray(list);
                    int j = arecommendinfo.length;
                    for(i = 0; i < j; i++)
                    {
                        RecommendInfo recommendinfo = arecommendinfo[i];
                        list = RecommendApiClientUser.TAG;
                        StringBuilder stringbuilder1 = new StringBuilder();
                        stringbuilder1.append("recommend: serviceId=");
                        stringbuilder1.append(recommendinfo.getServiceId());
                        PxLog.v(list, stringbuilder1.toString());
                    }

                    mRequestTime = Calendar.getInstance();
                    mControlInterface.ResponseObject(requestparam.mTarget, 16, arecommendinfo);
                }
                completeRequest();
                return;
            } else
            {
                completeRequest();
                return;
            }
        }

        public void request(long l, long l1, long l2, String s, 
                List list, List list1, int i, String s1)
        {
            RequestParam requestparam;
            requestparam = new RequestParam();
            requestparam.mTarget = l;
            requestparam.mSince = l1;
            requestparam.mUntil = l2;
            requestparam.mBroadcastType = s;
            requestparam.mServiceIdList = list;
            requestparam.mProgramInfoList = list1;
            requestparam.mAreaCode = i;
            requestparam.mUserId = s1;
            s = ((String) (mMutex));
            s;
            JVM INSTR monitorenter ;
            boolean flag = false;
            list = mRequestQueue.iterator();
_L2:
            i = ((flag) ? 1 : 0);
            if(!list.hasNext())
                break; /* Loop/switch isn't completed */
            if(!((RequestParam)list.next()).equals(requestparam))
                continue; /* Loop/switch isn't completed */
            i = 1;
            break; /* Loop/switch isn't completed */
            if(true) goto _L2; else goto _L1
_L1:
            if(i != 0)
                break MISSING_BLOCK_LABEL_143;
            mRequestQueue.add(requestparam);
            break MISSING_BLOCK_LABEL_206;
            list = RecommendApiClientUser.TAG;
            list1 = JVM INSTR new #81  <Class StringBuilder>;
            list1.StringBuilder();
            list1.append("duplicate request broadcastType=");
            list1.append(requestparam.mBroadcastType);
            list1.append(", serviceId=");
            list1.append(requestparam.mServiceIdList);
            PxLog.v(list, list1.toString());
            s;
            JVM INSTR monitorexit ;
            return;
            list;
            s;
            JVM INSTR monitorexit ;
            throw list;
        }

        public void run()
        {
            PxLog.v(RecommendApiClientUser.TAG, "recommend thread start");
_L2:
label0:
            {
                if(mThreadCancel)
                    PxLog.v(RecommendApiClientUser.TAG, "recommend thread cancel");
                else
                    synchronized(mMutex)
                    {
                        if(!mRequestQueue.isEmpty())
                            break label0;
                        PxLog.v(RecommendApiClientUser.TAG, "recommend thread complete");
                    }
                PxLog.v(RecommendApiClientUser.TAG, "recommend thread exit");
                return;
            }
            obj;
            JVM INSTR monitorexit ;
            if(mIsRequest)
            {
                intervalRequest();
            } else
            {
                mIsRequest = true;
                RequestParam requestparam = getRequest();
                if(requestparam == null)
                {
                    completeRequest();
                    return;
                }
                requestServer(requestparam);
            }
            if(true) goto _L2; else goto _L1
_L1:
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        private static final long INTERVAL_MILLIS = 100L;
        private Context mContext;
        private ControlInterface mControlInterface;
        private boolean mIsRequest;
        private final Object mMutex = new Object();
        private List mRequestQueue;
        private boolean mThreadCancel;
        final RecommendApiClientUser this$0;

        RequestRecommendThread(Context context, ControlInterface controlinterface)
        {
            this$0 = RecommendApiClientUser.this;
            super();
            mThreadCancel = false;
            mIsRequest = false;
            mRequestQueue = new ArrayList();
            mContext = context;
            mControlInterface = controlinterface;
        }
    }

    private class RequestRecommendThread.RequestParam
    {

        int mAreaCode;
        String mBroadcastType;
        List mProgramInfoList;
        List mServiceIdList;
        long mSince;
        long mTarget;
        long mUntil;
        String mUserId;
        final RequestRecommendThread this$1;

        private RequestRecommendThread.RequestParam()
        {
            this$1 = RequestRecommendThread.this;
            super();
        }

    }


    public RecommendApiClientUser(Context context, ControlInterface controlinterface)
    {
        mContext = context;
        mControlInterface = controlinterface;
    }

    private void addNonRecommendData(List list, List list1)
    {
        if(list1 == null)
            return;
        ArrayList arraylist = new ArrayList();
        list1 = list1.iterator();
label0:
        do
        {
            int i;
            int j;
            String s;
            boolean flag1;
label1:
            {
                if(!list1.hasNext())
                    break label0;
                Object obj = (ProgramInfo)list1.next();
                i = ((ProgramInfo) (obj)).getServiceId();
                j = ((ProgramInfo) (obj)).getEventId();
                int k = ((ProgramInfo) (obj)).getReferServiceId();
                int l = ((ProgramInfo) (obj)).getReferEventId();
                s = ((ProgramInfo) (obj)).getBroadcastTypeString();
                obj = list.iterator();
                do
                {
                    boolean flag = ((Iterator) (obj)).hasNext();
                    flag1 = true;
                    if(!flag)
                        break;
                    RecommendData recommenddata = (RecommendData)((Iterator) (obj)).next();
                    if(recommenddata.getBroadcastType().equals(s) && (recommenddata.getServiceId() == i && recommenddata.getEventId() == j || recommenddata.getServiceId() == k && recommenddata.getEventId() == l))
                        break label1;
                } while(true);
                flag1 = false;
            }
            if(!flag1)
                arraylist.add(new RecommendData(i, j, s));
        } while(true);
        if(arraylist.size() > 0)
        {
            list1 = list;
            if(list == null)
                list1 = new ArrayList();
            list1.addAll(arraylist);
        }
    }

    private void replaceRecommendData(List list, List list1)
    {
        if(list != null && list1 != null)
        {
            for(Iterator iterator = list.iterator(); iterator.hasNext();)
            {
                RecommendData recommenddata = (RecommendData)iterator.next();
                Iterator iterator1 = list1.iterator();
                while(iterator1.hasNext()) 
                {
                    list = (ProgramInfo)iterator1.next();
                    if(list.getBroadcastTypeString().equals(recommenddata.getBroadcastType()) && list.getReferServiceId() == recommenddata.getServiceId() && list.getReferEventId() == recommenddata.getEventId())
                    {
                        recommenddata.setServiceId(list.getServiceId());
                        recommenddata.setEventId(list.getEventId());
                    }
                }
            }

            return;
        } else
        {
            return;
        }
    }

    private RecommendInfo[] toRecommendInfoArray(List list)
    {
        if(list != null && list.size() != 0)
        {
            int i = list.size();
            RecommendInfo arecommendinfo[] = new RecommendInfo[i];
            for(int j = 0; j < i; j++)
            {
                RecommendData recommenddata = (RecommendData)list.get(j);
                arecommendinfo[j] = new RecommendInfo();
                arecommendinfo[j].setServiceId(recommenddata.getServiceId());
                arecommendinfo[j].setEventId(recommenddata.getEventId());
                arecommendinfo[j].setBroadcastType(recommenddata.getBroadcastType());
                arecommendinfo[j].setRecommend(recommenddata.getRecommend());
                arecommendinfo[j].setPixRecommend(recommenddata.getPixRecommend());
            }

            return arecommendinfo;
        } else
        {
            return null;
        }
    }

    private void updateRecommend(long l, long l1, long l2, String s, 
            List list, List list1, int i, String s1)
    {
        if(mRequestRecommendThread != null && mRequestRecommendThread.isAlive())
        {
            mRequestRecommendThread.request(l, l1, l2, s, list, list1, i, s1);
        } else
        {
            mRequestRecommendThread = new RequestRecommendThread(mContext, mControlInterface);
            mRequestRecommendThread.request(l, l1, l2, s, list, list1, i, s1);
            mRequestRecommendThread.start();
        }
    }

    private void updateRecommend(long l, boolean flag, long l1, long l2, 
            String s, List list, List list1, int i, String s1)
    {
        String s2 = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("updateRecommend in. target=");
        stringbuilder.append(l);
        stringbuilder.append(", force=");
        stringbuilder.append(flag);
        stringbuilder.append(", since=");
        stringbuilder.append(l1);
        stringbuilder.append(", until=");
        stringbuilder.append(l2);
        stringbuilder.append(", broadcastType=");
        stringbuilder.append(s);
        stringbuilder.append(", serviceIdList=");
        stringbuilder.append(list);
        PxLog.v(s2, stringbuilder.toString());
        if(list != null && list.size() > 0 && s == null)
        {
            PxLog.v(TAG, "updateRecommend out. serviceIdList.size() > 0. but broadcastType is null.");
            return;
        }
        if(flag)
        {
            updateRecommend(l, l1, l2, s, list, list1, i, s1);
        } else
        {
            Calendar calendar = Calendar.getInstance();
            calendar.add(12, -1);
            if(mRequestTime == null || mRequestTime.before(calendar))
                updateRecommend(l, l1, l2, s, list, list1, i, s1);
        }
    }

    public void finalize()
    {
        if(mRequestRecommendThread != null)
        {
            mRequestRecommendThread.cancel();
            while(mRequestRecommendThread.isAlive()) 
                try
                {
                    Thread.sleep(1L);
                }
                catch(InterruptedException interruptedexception)
                {
                    StringBuilder stringbuilder = new StringBuilder();
                    stringbuilder.append(LOG_HEAD);
                    stringbuilder.append("e=");
                    stringbuilder.append(interruptedexception);
                    LoggerRTM.e(stringbuilder.toString(), new Object[0]);
                }
            mRequestRecommendThread = null;
        }
        RecommendCacheHelper.deleteCache(mContext);
    }

    public RecommendInfo[] getRecommend(long l, boolean flag, long l1, long l2, 
            String s, List list, List list1, int i, String s1)
    {
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append(LOG_HEAD);
        ((StringBuilder) (obj)).append("in");
        Logger.v(((StringBuilder) (obj)).toString(), new Object[0]);
        obj = new ArrayList();
        if(RecommendCacheHelper.readCache(mContext, ((List) (obj)), list1, 1000L * l1) == 0)
        {
            list = toRecommendInfoArray(((List) (obj)));
            s = new StringBuilder();
            s.append(LOG_HEAD);
            s.append("out. result == 0 (cache). recommendInfoList=");
            s.append(Arrays.toString(list));
            Logger.v(s.toString(), new Object[0]);
            return list;
        }
        if(!NetworkUtility.isNetworkAvailable(mContext))
        {
            s = new StringBuilder();
            s.append(LOG_HEAD);
            s.append("out. !isNetworkAvailable");
            Logger.v(s.toString(), new Object[0]);
            return null;
        } else
        {
            updateRecommend(l, flag, l1, l2, s, list, list1, i, s1);
            s = new StringBuilder();
            s.append(LOG_HEAD);
            s.append("out. updateRecommend");
            Logger.v(s.toString(), new Object[0]);
            return null;
        }
    }

    private static final String LOG_HEAD;
    private static final int REQUEST_INTERVAL_MINUTES = 1;
    private static final String TAG = "RecommendApiClientUser";
    private final Context mContext;
    private final ControlInterface mControlInterface;
    private RequestRecommendThread mRequestRecommendThread;
    private Calendar mRequestTime;

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/pis_client/user/RecommendApiClientUser.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }






/*
    static Calendar access$402(RecommendApiClientUser recommendapiclientuser, Calendar calendar)
    {
        recommendapiclientuser.mRequestTime = calendar;
        return calendar;
    }

*/
}
