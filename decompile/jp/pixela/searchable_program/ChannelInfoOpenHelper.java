// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.searchable_program;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;
import java.text.Normalizer;
import java.util.*;
import jp.pixela.common.PxLog;

// Referenced classes of package jp.pixela.searchable_program:
//            ContentInfoOpenHelper

class ChannelInfoOpenHelper extends ContentInfoOpenHelper
{
    public static class ChannelInfo
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public int getAdBroadcasterId()
        {
            return mAdBroadcasterId;
        }

        public int getBranchNumber()
        {
            return mBranchNumber;
        }

        public int getBroadcastType()
        {
            return mBroadcastType;
        }

        public String getDstAddress()
        {
            return mDstAddress;
        }

        public int getFrequency()
        {
            return mFrequency;
        }

        public int getMediaType()
        {
            return mMediaType;
        }

        public int getNetworkId()
        {
            return mNetworkId;
        }

        public int getRemoconKeyId()
        {
            return mRemoconKeyId;
        }

        public int getSICID()
        {
            return mSICID;
        }

        public int getServiceCID()
        {
            return mServiceCID;
        }

        public int getServiceId()
        {
            return mServiceId;
        }

        public String getServiceName()
        {
            return mServiceName;
        }

        public int getServiceType()
        {
            return mServiceType;
        }

        public String getSrcAddress()
        {
            return mSrcAddress;
        }

        public int getStreamId()
        {
            return mStreamId;
        }

        public int getThreeDigitNumber()
        {
            return mThreeDigitNumber;
        }

        public String getUrl()
        {
            return mUrl;
        }

        public void setAdBroadcasterId(int i)
        {
            mAdBroadcasterId = i;
        }

        public void setBranchNumber(int i)
        {
            mBranchNumber = i;
        }

        public void setBroadcastType(int i)
        {
            mBroadcastType = i;
        }

        public void setDstAddress(String s)
        {
            mDstAddress = s;
        }

        public void setFrequency(int i)
        {
            mFrequency = i;
        }

        public void setMediaType(int i)
        {
            mMediaType = i;
        }

        public void setNetworkId(int i)
        {
            mNetworkId = i;
        }

        public void setRemoconKeyId(int i)
        {
            mRemoconKeyId = i;
        }

        public void setSICID(int i)
        {
            mSICID = i;
        }

        public void setServiceCID(int i)
        {
            mServiceCID = i;
        }

        public void setServiceId(int i)
        {
            mServiceId = i;
        }

        public void setServiceName(String s)
        {
            mServiceName = s;
        }

        public void setServiceType(int i)
        {
            mServiceType = i;
        }

        public void setSrcAddress(String s)
        {
            mSrcAddress = s;
        }

        public void setStreamId(int i)
        {
            mStreamId = i;
        }

        public void setThreeDigitNumber(int i)
        {
            mThreeDigitNumber = i;
        }

        public void setUrl(String s)
        {
            mUrl = s;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mBroadcastType);
            parcel.writeInt(mServiceId);
            parcel.writeInt(mNetworkId);
            parcel.writeInt(mStreamId);
            parcel.writeInt(mFrequency);
            parcel.writeInt(mServiceType);
            parcel.writeInt(mMediaType);
            parcel.writeInt(mAdBroadcasterId);
            parcel.writeInt(mRemoconKeyId);
            parcel.writeString(mServiceName);
            parcel.writeString(mSrcAddress);
            parcel.writeString(mDstAddress);
            parcel.writeInt(mSICID);
            parcel.writeInt(mServiceCID);
            parcel.writeInt(mCommonServiceCID);
            parcel.writeString(mUrl);
            parcel.writeInt(mThreeDigitNumber);
            parcel.writeInt(mBranchNumber);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ChannelInfo createFromParcel(Parcel parcel)
            {
                return new ChannelInfo(parcel);
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

            public ChannelInfo[] newArray(int i)
            {
                return new ChannelInfo[i];
            }

        }
;
        private int mAdBroadcasterId;
        private int mBranchNumber;
        private int mBroadcastType;
        private int mCommonServiceCID;
        private String mDstAddress;
        private int mFrequency;
        private int mMediaType;
        private int mNetworkId;
        private int mRemoconKeyId;
        private int mSICID;
        private int mServiceCID;
        private int mServiceId;
        private String mServiceName;
        private int mServiceType;
        private String mSrcAddress;
        private int mStreamId;
        private int mThreeDigitNumber;
        private String mUrl;


        public ChannelInfo()
        {
            mBroadcastType = 0;
            mServiceId = 0;
            mNetworkId = 0;
            mStreamId = 0;
            mFrequency = 0;
            mServiceType = 0;
            mMediaType = 0;
            mAdBroadcasterId = 0;
            mRemoconKeyId = 0;
            mServiceName = null;
            mSrcAddress = null;
            mDstAddress = null;
            mSICID = 0;
            mServiceCID = 0;
            mCommonServiceCID = 0;
            mUrl = null;
            mThreeDigitNumber = 0;
            mBranchNumber = 0;
        }

        public ChannelInfo(Parcel parcel)
        {
            mBroadcastType = 0;
            mServiceId = 0;
            mNetworkId = 0;
            mStreamId = 0;
            mFrequency = 0;
            mServiceType = 0;
            mMediaType = 0;
            mAdBroadcasterId = 0;
            mRemoconKeyId = 0;
            mServiceName = null;
            mSrcAddress = null;
            mDstAddress = null;
            mSICID = 0;
            mServiceCID = 0;
            mCommonServiceCID = 0;
            mUrl = null;
            mThreeDigitNumber = 0;
            mBranchNumber = 0;
            mBroadcastType = parcel.readInt();
            mServiceId = parcel.readInt();
            mNetworkId = parcel.readInt();
            mStreamId = parcel.readInt();
            mFrequency = parcel.readInt();
            mServiceType = parcel.readInt();
            mMediaType = parcel.readInt();
            mAdBroadcasterId = parcel.readInt();
            mRemoconKeyId = parcel.readInt();
            mServiceName = parcel.readString();
            mSrcAddress = parcel.readString();
            mDstAddress = parcel.readString();
            mSICID = parcel.readInt();
            mServiceCID = parcel.readInt();
            mCommonServiceCID = parcel.readInt();
            mUrl = parcel.readString();
            mThreeDigitNumber = parcel.readInt();
            mBranchNumber = parcel.readInt();
        }
    }


    ChannelInfoOpenHelper(Context context)
    {
        super(context, "channel_info.db", 2);
        PxLog.v(TAG, "constructor");
    }

    public static String getServiceName(Context context, int i, int j)
    {
        SQLiteDatabase sqlitedatabase = (new ChannelInfoOpenHelper(context)).getReadableDatabase();
        Object obj = new ArrayList();
        context = "";
        ((List) (obj)).add(String.valueOf(convertBroadcastTypeToBroadcastWave(i)));
        ((List) (obj)).add(String.valueOf(j));
        try
        {
            String as[] = (String[])((List) (obj)).toArray(new String[0]);
            as = sqlitedatabase.query("TableChannels", new String[] {
                "ColumnChannelBroadcastType", "ColumnChannelServiceID", "ColumnChannelServiceName"
            }, "ColumnChannelBroadcastType = ? AND ColumnChannelServiceID = ?", as, null, null, null);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            PxLog.w(TAG, context.getMessage());
            sqlitedatabase.close();
            return "";
        }
        if(as == null)
        {
            PxLog.i(TAG, "cursor is null");
            sqlitedatabase.close();
            return "";
        }
        if(as.moveToFirst())
            context = as.getString(2);
        as.close();
        sqlitedatabase.close();
        return context;
    }

    protected String getDatabaseNamePrefix()
    {
        return "channel_info";
    }

    public List getSuggestChannels(Context context, String s)
    {
        SQLiteDatabase sqlitedatabase = getReadableDatabase();
        Object obj = new ArrayList();
        context = new LinkedList();
        Object obj1 = Normalizer.normalize(s, java.text.Normalizer.Form.NFKC);
        s = new StringBuilder();
        s.append("%");
        s.append(((String) (obj1)));
        s.append("%");
        ((List) (obj)).add(s.toString());
        try
        {
            s = (String[])((List) (obj)).toArray(new String[0]);
            s = sqlitedatabase.query("TableChannels", new String[] {
                "ColumnChannelBroadcastType", "ColumnChannelServiceID", "ColumnChannelServiceName"
            }, "ColumnChannelServiceName LIKE ?", s, null, null, null);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            PxLog.w(TAG, s.getMessage());
            sqlitedatabase.close();
            return context;
        }
        if(s == null)
        {
            PxLog.i(TAG, "cursor is null");
            sqlitedatabase.close();
            return context;
        }
        obj = TAG;
        obj1 = new StringBuilder();
        ((StringBuilder) (obj1)).append("find count=");
        ((StringBuilder) (obj1)).append(s.getCount());
        PxLog.i(((String) (obj)), ((StringBuilder) (obj1)).toString());
        s.moveToFirst();
        for(; !s.isAfterLast(); s.moveToNext())
        {
            ChannelInfo channelinfo = new ChannelInfo();
            channelinfo.setBroadcastType(convertBroadcastWaveToBroadcastType(s.getInt(0)));
            channelinfo.setServiceId(s.getInt(1));
            channelinfo.setServiceName(s.getString(2));
            context.add(channelinfo);
        }

        s.close();
        sqlitedatabase.close();
        return context;
    }

    private static final String DATABASE_PREFIX = "channel_info";
    private static final String MY_DATABASE_NAME = "channel_info.db";
    private static final int MY_DATABASE_VERSION = 2;
    private static final String TAG = "ChannelInfoOpenHelper";

}
