// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.user;

import android.content.Context;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import jp.pixela.common.PxLog;
import jp.pixela.pis_client.helper.*;
import jp.pixela.pis_client.rest.thumbnail.ThumbnailData;
import jp.pixela.player_service.message.ProgramInfo;
import jp.pixela.pxlibs.utils.android.log.Logger;

// Referenced classes of package jp.pixela.pis_client.user:
//            ThumbnailApiClient

public class ThumbnailApiClientUser
    implements jp.pixela.pis_client.rest.thumbnail.IThumbnailClient.ThumbnailClientCallback
{

    public ThumbnailApiClientUser(Context context)
    {
        mSince = -1L;
        mUntil = -1L;
        mBroadcastType = null;
        mIsCancelled = new AtomicBoolean(false);
        mIsUpdating = false;
        mIsRequesting = false;
        mThumbnailImageByteArray = null;
        mContext = context;
    }

    private void deleteBlankThumbnailData(List list)
    {
        if(list == null)
            return;
        ArrayList arraylist = new ArrayList();
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ThumbnailData thumbnaildata = (ThumbnailData)iterator.next();
            byte abyte0[] = thumbnaildata.getImage();
            if(thumbnaildata.isGenre() || abyte0 != null && abyte0.length > 0)
                arraylist.add(thumbnaildata);
        } while(true);
        list.clear();
        list.addAll(arraylist);
    }

    private void downloadThumbnail(List list)
    {
        if(list != null)
        {
            Iterator iterator = list.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                list = (ThumbnailData)iterator.next();
                if(mIsCancelled.get())
                {
                    PxLog.v(TAG, "downloadThumbnail:cancelled.");
                    break;
                }
                mIsRequesting = true;
                downloadThumbnailImpl(list.getImageUrl());
                waitRequestThumbnail();
                if(mIsCancelled.get())
                {
                    PxLog.v(TAG, "downloadThumbnail:cancelled.");
                    break;
                }
                list.setImage(mThumbnailImageByteArray);
            } while(true);
        }
        mIsRequesting = false;
    }

    private void downloadThumbnailImpl(String s)
    {
        (new AsyncTaskHttpThumbnailRequest(mContext, new jp.pixela.pis_client.helper.AsyncTaskHttpThumbnailRequest.LoadThumbnailListener() {

            public void onLoadThumbnailFinished(ByteBuffer bytebuffer)
            {
                if(mIsCancelled.get())
                {
                    mIsRequesting = false;
                    return;
                }
                mThumbnailImageByteArray = null;
                if(bytebuffer != null)
                {
                    mThumbnailImageByteArray = bytebuffer.array();
                    bytebuffer.clear();
                } else
                {
                    PxLog.v(ThumbnailApiClientUser.TAG, "onLoadThumbnailFinished:bitmap=null.");
                }
                mIsRequesting = false;
            }

            final ThumbnailApiClientUser this$0;

            
            {
                this$0 = ThumbnailApiClientUser.this;
                super();
            }
        }
)).execute(new String[] {
            s
        });
    }

    private void replaceThumbnailData(List list, List list1)
    {
        if(list != null && list1 != null)
        {
            for(Iterator iterator = list.iterator(); iterator.hasNext();)
            {
                ThumbnailData thumbnaildata = (ThumbnailData)iterator.next();
                list = list1.iterator();
                while(list.hasNext()) 
                {
                    ProgramInfo programinfo = (ProgramInfo)list.next();
                    if(programinfo.getBroadcastTypeString().equals(thumbnaildata.getBroadcastType()) && programinfo.getReferServiceId() == thumbnaildata.getServiceId() && programinfo.getReferEventId() == thumbnaildata.getEventId())
                    {
                        thumbnaildata.setServiceId(programinfo.getServiceId());
                        thumbnaildata.setEventId(programinfo.getEventId());
                    }
                }
            }

            return;
        } else
        {
            return;
        }
    }

    private void requestThumbnail()
    {
        Integer ainteger[] = (Integer[])mServiceIdList.toArray(new Integer[mServiceIdList.size()]);
        (new ThumbnailApiClient(this)).request(mContext, mSince, mUntil, mBroadcastType, ainteger);
    }

    private void updateThumbnail(boolean flag, long l, long l1, String s, List list)
    {
        String s1 = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("updateThumbnail in. force=");
        stringbuilder.append(flag);
        stringbuilder.append(", since=");
        stringbuilder.append(l);
        stringbuilder.append(", until=");
        stringbuilder.append(l1);
        stringbuilder.append(", broadcastType=");
        stringbuilder.append(s);
        stringbuilder.append(", serviceIdList=");
        stringbuilder.append(list);
        PxLog.v(s1, stringbuilder.toString());
        if(list != null && list.size() > 0 && s == null)
        {
            PxLog.v(TAG, "updateThumbnail out. serviceIdList.size() > 0. but broadcastType is null.");
            return;
        }
        mSince = l;
        mUntil = l1;
        mBroadcastType = s;
        mServiceIdList.clear();
        if(list != null)
            mServiceIdList.addAll(list);
        if(flag)
        {
            mIsUpdating = true;
            mIsRequesting = true;
            requestThumbnail();
        } else
        {
            s = Calendar.getInstance();
            s.add(12, -1);
            if(mRequestTime == null || mRequestTime.before(s))
            {
                mIsUpdating = true;
                mIsRequesting = true;
                requestThumbnail();
            }
        }
    }

    private void waitCompleteThumbnail()
    {
        while(mIsUpdating) 
            try
            {
                Thread.sleep(100L);
            }
            catch(InterruptedException interruptedexception)
            {
                String s = TAG;
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append("getThumbnail() InterruptedException e=");
                stringbuilder.append(interruptedexception);
                PxLog.w(s, stringbuilder.toString());
            }
    }

    private void waitRequestThumbnail()
    {
        while(mIsRequesting && !mIsCancelled.get()) 
            try
            {
                Thread.sleep(100L);
            }
            catch(InterruptedException interruptedexception)
            {
                String s = TAG;
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append("requestThumbnail() InterruptedException e=");
                stringbuilder.append(interruptedexception);
                PxLog.w(s, stringbuilder.toString());
            }
    }

    public void deleteThumbnailCache()
    {
        ThumbnailCacheHelper.deleteCache(mContext);
    }

    public void finalize()
    {
        PxLog.d(TAG, "Thumbnail request is cancelled!");
        mIsCancelled.set(true);
    }

    public List getThumbnail(boolean flag, long l, long l1, String s, List list, 
            List list1, boolean flag1)
    {
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append(LOG_HEAD);
        ((StringBuilder) (obj)).append("in");
        Logger.v(((StringBuilder) (obj)).toString(), new Object[0]);
        waitCompleteThumbnail();
        mThumbnailDataList.clear();
        if(mIsCancelled.get())
        {
            mIsUpdating = false;
            s = new StringBuilder();
            s.append(LOG_HEAD);
            s.append("out. mIsCancelled.get()");
            Logger.v(s.toString(), new Object[0]);
            return null;
        }
        Context context = mContext;
        obj = mThumbnailDataList;
        long l2 = 1000L * l;
        if(ThumbnailCacheHelper.readCache(context, ((List) (obj)), list1, l2) != 0)
            if(NetworkUtility.isNetworkAvailable(mContext))
            {
                updateThumbnail(flag, l, l1, s, list);
                waitRequestThumbnail();
                downloadThumbnail(mThumbnailDataList);
            } else
            {
                s = new StringBuilder();
                s.append(LOG_HEAD);
                s.append("!isNetworkAvailable");
                Logger.v(s.toString(), new Object[0]);
            }
        if(mIsCancelled.get())
        {
            mIsUpdating = false;
            s = new StringBuilder();
            s.append(LOG_HEAD);
            s.append("out. mIsCancelled.get()");
            Logger.v(s.toString(), new Object[0]);
            return null;
        }
        if(flag1)
            GenreImageHelper.readGenreImage(mContext, mThumbnailDataList, list1);
        ThumbnailCacheHelper.writeCache(mContext, mThumbnailDataList, l2);
        replaceThumbnailData(mThumbnailDataList, list1);
        deleteBlankThumbnailData(mThumbnailDataList);
        mIsUpdating = false;
        waitCompleteThumbnail();
        s = new StringBuilder();
        s.append(LOG_HEAD);
        s.append("out");
        Logger.v(s.toString(), new Object[0]);
        return mThumbnailDataList;
    }

    public boolean isCacheExists(long l, List list)
    {
        waitCompleteThumbnail();
        return ThumbnailCacheHelper.isCacheExists(mContext, list, l * 1000L);
    }

    public void onCompleteThumbnail(int i, List list)
    {
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("onCompleteThumbnail:resultCode=");
        stringbuilder.append(i);
        stringbuilder.append(", listSize=");
        int j;
        if(list == null)
            j = 0;
        else
            j = list.size();
        stringbuilder.append(j);
        PxLog.v(s, stringbuilder.toString());
        if(i == 0 && list != null)
        {
            mRequestTime = Calendar.getInstance();
            mThumbnailDataList.clear();
            mThumbnailDataList.addAll(list);
            mIsRequesting = false;
            return;
        } else
        {
            mThumbnailDataList.clear();
            mIsRequesting = false;
            return;
        }
    }

    private static final String LOG_HEAD;
    private static final int REQUEST_INTERVAL_MINUTES = 1;
    private static final String TAG = "ThumbnailApiClientUser";
    private static final long WAIT_MILLIS = 100L;
    private String mBroadcastType;
    private final Context mContext;
    private AtomicBoolean mIsCancelled;
    private boolean mIsRequesting;
    private boolean mIsUpdating;
    private Calendar mRequestTime;
    private final List mServiceIdList = new ArrayList();
    private long mSince;
    private final List mThumbnailDataList = new ArrayList();
    private byte mThumbnailImageByteArray[];
    private long mUntil;

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/pis_client/user/ThumbnailApiClientUser.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }



/*
    static boolean access$102(ThumbnailApiClientUser thumbnailapiclientuser, boolean flag)
    {
        thumbnailapiclientuser.mIsRequesting = flag;
        return flag;
    }

*/


/*
    static byte[] access$202(ThumbnailApiClientUser thumbnailapiclientuser, byte abyte0[])
    {
        thumbnailapiclientuser.mThumbnailImageByteArray = abyte0;
        return abyte0;
    }

*/

}
