// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.user;

import android.content.Context;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import jp.pixela.common.PxLog;
import jp.pixela.pis_client.helper.NetworkUtility;
import jp.pixela.pis_client.rest.rank.ViewingPointData;
import jp.pixela.pxlibs.utils.android.log.LoggerRTM;

// Referenced classes of package jp.pixela.pis_client.user:
//            ViewingPointApiClient

public class ViewingPointApiClientUser
{
    private class RequestParam
    {

        private String mBroadcast;
        private final List mServiceIdList;
        final ViewingPointApiClientUser this$0;



/*
        static String access$202(RequestParam requestparam, String s)
        {
            requestparam.mBroadcast = s;
            return s;
        }

*/


        private RequestParam()
        {
            this$0 = ViewingPointApiClientUser.this;
            super();
            mServiceIdList = new ArrayList();
            mBroadcast = null;
        }

    }

    private class RequestViewingPointThread extends Thread
        implements jp.pixela.pis_client.rest.rank.IViewingPointClient.ViewingPointClientCallback
    {

        private boolean requestViewingPoint()
        {
label0:
            {
                synchronized(mRequestQueueLock)
                {
                    if(!mRequestQueue.isEmpty())
                        break label0;
                    PxLog.v(ViewingPointApiClientUser.TAG, "requestViewingPoint() out. Request is empty.");
                }
                return false;
            }
            RequestParam requestparam;
            requestparam = (RequestParam)mRequestQueue.poll();
            if(!requestparam.mServiceIdList.isEmpty())
                break MISSING_BLOCK_LABEL_68;
            PxLog.v(ViewingPointApiClientUser.TAG, "requestViewingPoint() out. ServiceId List is empty.");
            ainteger;
            JVM INSTR monitorexit ;
            return false;
            ainteger;
            JVM INSTR monitorexit ;
            ainteger = (Integer[])requestparam.mServiceIdList.toArray(new Integer[requestparam.mServiceIdList.size()]);
            (new ViewingPointApiClient(this)).request(mContext, requestparam.mBroadcast, ainteger);
            return true;
            exception;
            ainteger;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void cancel()
        {
            mThreadCancel.set(true);
            synchronized(mRequestQueueLock)
            {
                mRequestQueueLock.notify();
            }
            synchronized(mUpdatingLock)
            {
                mUpdatingLock.notify();
            }
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
            exception1;
            obj;
            JVM INSTR monitorexit ;
            throw exception1;
        }

        public void onCompleteViewingPoint(int i, String s, List list)
        {
            String s1 = ViewingPointApiClientUser.TAG;
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("onCompleteViewingPoint:resultCode=");
            stringbuilder.append(i);
            PxLog.v(s1, stringbuilder.toString());
            if(s == null)
            {
                synchronized(mUpdatingLock)
                {
                    mUpdatingLock.notify();
                }
                return;
            }
            break MISSING_BLOCK_LABEL_65;
            s;
            list;
            JVM INSTR monitorexit ;
            throw s;
            if(i == 0 && list != null)
            {
                synchronized(mViewingPointDataLock)
                {
                    ((List)mViewingPointDataMap.get(s)).clear();
                    ((List)mViewingPointDataMap.get(s)).addAll(list);
                }
                synchronized(mUpdatingLock)
                {
                    mUpdatingLock.notify();
                }
                return;
            }
            break MISSING_BLOCK_LABEL_163;
            list;
            s;
            JVM INSTR monitorexit ;
            throw list;
            s;
            obj;
            JVM INSTR monitorexit ;
            throw s;
            synchronized(mViewingPointDataLock)
            {
                ((List)mViewingPointDataMap.get(s)).clear();
            }
            synchronized(mUpdatingLock)
            {
                mUpdatingLock.notify();
            }
            return;
            s;
            list;
            JVM INSTR monitorexit ;
            throw s;
            s;
            list;
            JVM INSTR monitorexit ;
            throw s;
        }

        public void request(String s, List list)
        {
            synchronized(mRequestQueueLock)
            {
                RequestParam requestparam = JVM INSTR new #75  <Class ViewingPointApiClientUser$RequestParam>;
                requestparam.ViewingPointApiClientUser.this. RequestParam();
                requestparam.mBroadcast = s;
                requestparam.mServiceIdList.addAll(list);
                mRequestQueue.add(requestparam);
                mRequestQueueLock.notify();
            }
            return;
            s;
            obj;
            JVM INSTR monitorexit ;
            throw s;
        }

        public void run()
        {
_L2:
label0:
            {
                synchronized(mRequestQueueLock)
                {
                    if(!mThreadCancel.get())
                        break label0;
                }
                break MISSING_BLOCK_LABEL_115;
            }
            boolean flag = mRequestQueue.isEmpty();
            if(!flag)
                break MISSING_BLOCK_LABEL_94;
            mRequestQueueLock.wait();
            break MISSING_BLOCK_LABEL_94;
            InterruptedException interruptedexception;
            interruptedexception;
            StringBuilder stringbuilder1 = JVM INSTR new #120 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            stringbuilder1.append(ViewingPointApiClientUser.LOG_HEAD);
            stringbuilder1.append("e=");
            stringbuilder1.append(interruptedexception);
            LoggerRTM.e(stringbuilder1.toString(), new Object[0]);
            obj;
            JVM INSTR monitorexit ;
            synchronized(mUpdatingLock)
            {
                if(!mThreadCancel.get())
                    break MISSING_BLOCK_LABEL_116;
            }
            return;
            flag = requestViewingPoint();
            if(!flag)
                break MISSING_BLOCK_LABEL_179;
            mUpdatingLock.wait();
            break MISSING_BLOCK_LABEL_179;
            InterruptedException interruptedexception1;
            interruptedexception1;
            StringBuilder stringbuilder = JVM INSTR new #120 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            stringbuilder.append(ViewingPointApiClientUser.LOG_HEAD);
            stringbuilder.append("e=");
            stringbuilder.append(interruptedexception1);
            LoggerRTM.e(stringbuilder.toString(), new Object[0]);
            obj;
            JVM INSTR monitorexit ;
            if(true) goto _L2; else goto _L1
_L1:
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
            exception1;
            obj;
            JVM INSTR monitorexit ;
            throw exception1;
        }

        private Context mContext;
        private final Queue mRequestQueue = new ArrayDeque();
        private final Object mRequestQueueLock = new Object();
        private AtomicBoolean mThreadCancel;
        private final Object mUpdatingLock = new Object();
        final ViewingPointApiClientUser this$0;

        RequestViewingPointThread(Context context)
        {
            this$0 = ViewingPointApiClientUser.this;
            super();
            mThreadCancel = new AtomicBoolean(false);
            mContext = context;
        }
    }


    public ViewingPointApiClientUser(Context context)
    {
        mRequestThread = null;
        mContext = context;
    }

    private List copyViewingPointData(String s)
    {
        Object obj = mViewingPointDataLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = (List)mViewingPointDataMap.get(s);
        s = JVM INSTR new #84  <Class ArrayList>;
        s.ArrayList(((List) (obj1)).size());
        ViewingPointData viewingpointdata1;
        for(obj1 = ((List) (obj1)).iterator(); ((Iterator) (obj1)).hasNext(); s.add(viewingpointdata1))
        {
            ViewingPointData viewingpointdata = (ViewingPointData)((Iterator) (obj1)).next();
            viewingpointdata1 = JVM INSTR new #107 <Class ViewingPointData>;
            viewingpointdata1.ViewingPointData(viewingpointdata);
        }

        obj;
        JVM INSTR monitorexit ;
        return s;
        s;
        obj;
        JVM INSTR monitorexit ;
        throw s;
    }

    private void updateViewingPoint(String s, List list)
    {
        if(list.size() <= 0)
        {
            PxLog.v(TAG, "updateViewingPoint() out. serviceIdList.size() <= 0");
            return;
        }
        if(mRequestThread == null || !mRequestThread.isAlive())
        {
            mRequestThread = new RequestViewingPointThread(mContext);
            mRequestThread.start();
        }
        mRequestThread.request(s, list);
    }

    public void finalize()
    {
        if(mRequestThread != null)
        {
            mRequestThread.cancel();
            while(mRequestThread.isAlive()) 
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
            mRequestThread = null;
        }
    }

    public List getViewingPoint(String s, List list)
    {
        if(!mViewingPointDataMap.containsKey(s))
            mViewingPointDataMap.put(s, new ArrayList());
        if(!NetworkUtility.isNetworkAvailable(mContext))
        {
            synchronized(mViewingPointDataLock)
            {
                ((List)mViewingPointDataMap.get(s)).clear();
                s = copyViewingPointData(s);
            }
            return s;
        } else
        {
            updateViewingPoint(s, list);
            return copyViewingPointData(s);
        }
        s;
        list;
        JVM INSTR monitorexit ;
        throw s;
    }

    private static final String LOG_HEAD;
    private static final String TAG = "ViewingPointApiClientUser";
    private final Context mContext;
    private RequestViewingPointThread mRequestThread;
    private final Object mViewingPointDataLock = new Object();
    private final Map mViewingPointDataMap = new HashMap();

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/pis_client/user/ViewingPointApiClientUser.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }




}
