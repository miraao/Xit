// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.searchable_program;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import java.text.Normalizer;
import java.util.*;
import jp.pixela.common.FilterAribGaiji;
import jp.pixela.common.PxLog;

// Referenced classes of package jp.pixela.searchable_program:
//            ContentInfoOpenHelper, SuggestContentDatabase, ChannelInfoOpenHelper

public class ProgramInfoOpenHelper extends ContentInfoOpenHelper
{
    public static class ProgramInfo
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public int getBroadcast()
        {
            return mBroadcast;
        }

        public int getCaAvailable()
        {
            return mCaAvailable;
        }

        public int getCaPayService()
        {
            return mCaPayService;
        }

        public String getDescription()
        {
            return mDescription;
        }

        public int getDuration()
        {
            return mDuration;
        }

        public int getEventId()
        {
            return mEventId;
        }

        public String getEventName()
        {
            return mEventName;
        }

        public int getGenre1()
        {
            return mGenre1;
        }

        public int getGenre2()
        {
            return mGenre2;
        }

        public int getGenre3()
        {
            return mGenre3;
        }

        public int getId()
        {
            return mId;
        }

        public int getRating()
        {
            return mRating;
        }

        public int getRefEventId()
        {
            return mRefEventId;
        }

        public int getRefServiceId()
        {
            return mRefServiceId;
        }

        public int getServiceId()
        {
            return mServiceId;
        }

        public int getStartTime()
        {
            return mStartTime;
        }

        public void setBroadcast(int i)
        {
            mBroadcast = i;
        }

        public void setCaAvailable(int i)
        {
            mCaAvailable = i;
        }

        public void setCaPayService(int i)
        {
            mCaPayService = i;
        }

        public void setDescription(String s)
        {
            mDescription = s;
        }

        public void setDuration(int i)
        {
            mDuration = i;
        }

        public void setEventId(int i)
        {
            mEventId = i;
        }

        public void setEventName(String s)
        {
            mEventName = s;
        }

        public void setGenre1(int i)
        {
            mGenre1 = i;
        }

        public void setGenre2(int i)
        {
            mGenre2 = i;
        }

        public void setGenre3(int i)
        {
            mGenre3 = i;
        }

        public void setId(int i)
        {
            mId = i;
        }

        public void setRating(int i)
        {
            mRating = i;
        }

        public void setRefEventId(int i)
        {
            mRefEventId = i;
        }

        public void setRefServiceId(int i)
        {
            mRefServiceId = i;
        }

        public void setServiceId(int i)
        {
            mServiceId = i;
        }

        public void setStartTime(int i)
        {
            mStartTime = i;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mId);
            parcel.writeInt(mBroadcast);
            parcel.writeInt(mServiceId);
            parcel.writeInt(mEventId);
            parcel.writeInt(mRefServiceId);
            parcel.writeInt(mRefEventId);
            parcel.writeString(mEventName);
            parcel.writeInt(mStartTime);
            parcel.writeInt(mDuration);
            parcel.writeString(mDescription);
            parcel.writeInt(mGenre1);
            parcel.writeInt(mGenre2);
            parcel.writeInt(mGenre3);
            parcel.writeInt(mRating);
            parcel.writeInt(mCaPayService);
            parcel.writeInt(mCaAvailable);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ProgramInfo createFromParcel(Parcel parcel)
            {
                return new ProgramInfo(parcel);
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

            public ProgramInfo[] newArray(int i)
            {
                return new ProgramInfo[i];
            }

        }
;
        private int mBroadcast;
        private int mCaAvailable;
        private int mCaPayService;
        private String mDescription;
        private int mDuration;
        private int mEventId;
        private String mEventName;
        private int mGenre1;
        private int mGenre2;
        private int mGenre3;
        private int mId;
        private int mRating;
        private int mRefEventId;
        private int mRefServiceId;
        private int mServiceId;
        private int mStartTime;


        public ProgramInfo()
        {
            mId = 0;
            mBroadcast = 1;
            mServiceId = 0;
            mEventId = 0;
            mRefServiceId = 0;
            mRefEventId = 0;
            mEventName = "";
            mStartTime = 0;
            mDuration = 0;
            mDescription = "";
            mGenre1 = 0;
            mGenre2 = 0;
            mGenre3 = 0;
            mRating = 0;
            mCaPayService = 0;
            mCaAvailable = 0;
        }

        public ProgramInfo(Parcel parcel)
        {
            mId = 0;
            mBroadcast = 1;
            mServiceId = 0;
            mEventId = 0;
            mRefServiceId = 0;
            mRefEventId = 0;
            mEventName = "";
            mStartTime = 0;
            mDuration = 0;
            mDescription = "";
            mGenre1 = 0;
            mGenre2 = 0;
            mGenre3 = 0;
            mRating = 0;
            mCaPayService = 0;
            mCaAvailable = 0;
            mId = parcel.readInt();
            mBroadcast = parcel.readInt();
            mServiceId = parcel.readInt();
            mEventId = parcel.readInt();
            mRefServiceId = parcel.readInt();
            mRefEventId = parcel.readInt();
            mEventName = parcel.readString();
            mStartTime = parcel.readInt();
            mDuration = parcel.readInt();
            mDescription = parcel.readString();
            mGenre1 = parcel.readInt();
            mGenre2 = parcel.readInt();
            mGenre3 = parcel.readInt();
            mRating = parcel.readInt();
            mCaPayService = parcel.readInt();
            mCaAvailable = parcel.readInt();
        }
    }


    ProgramInfoOpenHelper(Context context)
    {
        super(context, "program_info.db", 2);
        PxLog.v(TAG, "constructor");
    }

    public static int getFutureGenreImageResource(int i)
    {
        switch(i >> 4)
        {
        default:
            return 0x7f0700a0;

        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
            return 0x7f0700a0;

        case 11: // '\013'
            return 0x7f0700a4;

        case 10: // '\n'
            return 0x7f07009c;

        case 9: // '\t'
            return 0x7f0700a2;

        case 8: // '\b'
            return 0x7f07009a;

        case 7: // '\007'
            return 0x7f070099;

        case 6: // '\006'
            return 0x7f07009d;

        case 5: // '\005'
            return 0x7f0700a3;

        case 4: // '\004'
            return 0x7f07009e;

        case 3: // '\003'
            return 0x7f07009b;

        case 2: // '\002'
            return 0x7f0700a5;

        case 1: // '\001'
            return 0x7f0700a1;

        case 0: // '\0'
            return 0x7f07009f;
        }
    }

    public static int getLiveGenreImageResource(int i)
    {
        switch(i >> 4)
        {
        default:
            return 0x7f0700cf;

        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
            return 0x7f0700df;

        case 11: // '\013'
            return 0x7f0700f9;

        case 10: // '\n'
            return 0x7f070087;

        case 9: // '\t'
            return 0x7f0700ec;

        case 8: // '\b'
            return 0x7f07007b;

        case 7: // '\007'
            return 0x7f07006d;

        case 6: // '\006'
            return 0x7f0700c6;

        case 5: // '\005'
            return 0x7f0700f5;

        case 4: // '\004'
            return 0x7f0700c9;

        case 3: // '\003'
            return 0x7f07007e;

        case 2: // '\002'
            return 0x7f0700fc;

        case 1: // '\001'
            return 0x7f0700e9;

        case 0: // '\0'
            return 0x7f0700cc;
        }
    }

    public static int getReserveGenreImageResource(int i)
    {
        switch(i >> 4)
        {
        default:
            return 0x7f0700d1;

        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
            return 0x7f0700e1;

        case 11: // '\013'
            return 0x7f0700fb;

        case 10: // '\n'
            return 0x7f070089;

        case 9: // '\t'
            return 0x7f0700ee;

        case 8: // '\b'
            return 0x7f07007d;

        case 7: // '\007'
            return 0x7f07006f;

        case 6: // '\006'
            return 0x7f0700c8;

        case 5: // '\005'
            return 0x7f0700f7;

        case 4: // '\004'
            return 0x7f0700cb;

        case 3: // '\003'
            return 0x7f070080;

        case 2: // '\002'
            return 0x7f0700fe;

        case 1: // '\001'
            return 0x7f0700eb;

        case 0: // '\0'
            return 0x7f0700ce;
        }
    }

    public static ProgramInfo readExtraData(String s)
    {
        String s1 = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("readExtraData ");
        stringbuilder.append(s);
        PxLog.v(s1, stringbuilder.toString());
        if(s == null)
            return null;
        try
        {
            Gson gson = JVM INSTR new #105 <Class Gson>;
            gson.Gson();
            s = (ProgramInfo)gson.fromJson(s, jp/pixela/searchable_program/ProgramInfoOpenHelper$ProgramInfo);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s = null;
        }
        return s;
    }

    public void addProgramInfoToSuggestContentDatabase(Context context, String s, List list, List list1, SQLiteDatabase sqlitedatabase)
    {
        SQLiteDatabase sqlitedatabase1 = getReadableDatabase();
        Object obj = new ArrayList();
        String s1 = context.getString(0x7f0e004f);
        Object obj1 = Normalizer.normalize(s, java.text.Normalizer.Form.NFKC);
        s = new StringBuilder();
        s.append("ProgramEventName LIKE ?");
        s.append(" OR ProgramEventDesc LIKE ?");
        s = s.toString();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("%");
        stringbuilder.append(((String) (obj1)));
        stringbuilder.append("%");
        ((List) (obj)).add(stringbuilder.toString());
        stringbuilder = new StringBuilder();
        stringbuilder.append("%");
        stringbuilder.append(((String) (obj1)));
        stringbuilder.append("%");
        ((List) (obj)).add(stringbuilder.toString());
        obj1 = s;
        if(list != null)
        {
            list = list.iterator();
            do
            {
                obj1 = s;
                if(!list.hasNext())
                    break;
                obj1 = (ChannelInfoOpenHelper.ChannelInfo)list.next();
                StringBuilder stringbuilder1 = new StringBuilder();
                stringbuilder1.append(s);
                stringbuilder1.append(" OR (ProgramBroadcast = ? AND ProgramServiceId = ?)");
                s = stringbuilder1.toString();
                ((List) (obj)).add(String.valueOf(convertBroadcastTypeToBroadcastWave(((ChannelInfoOpenHelper.ChannelInfo) (obj1)).getBroadcastType())));
                ((List) (obj)).add(String.valueOf(((ChannelInfoOpenHelper.ChannelInfo) (obj1)).getServiceId()));
            } while(true);
        }
        int i = 0;
        long l;
        int j;
        try
        {
            s = (String[])((List) (obj)).toArray(new String[0]);
            obj = sqlitedatabase1.query("TablePrograms", new String[] {
                "ProgramId", "ProgramBroadcast", "ProgramServiceId", "ProgramEventId", "ProgramRefServiceId", "ProgramRefEventId", "ProgramEventName", "ProgramStart", "ProgramDuration", "ProgramEventDesc", 
                "ProgramGenre1", "ProgramGenre2", "ProgramGenre3", "ProgramRating", "ProgramCAPayService", "ProgramCAAvailable"
            }, ((String) (obj1)), s, null, null, "ProgramStart ASC");
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            PxLog.w(TAG, context.getMessage());
            sqlitedatabase1.close();
            return;
        }
        if(obj == null)
        {
            PxLog.i(TAG, "cursor is null");
            sqlitedatabase1.close();
            return;
        }
        list = TAG;
        s = new StringBuilder();
        s.append("find count=");
        s.append(((Cursor) (obj)).getCount());
        PxLog.i(list, s.toString());
        l = System.currentTimeMillis();
        ((Cursor) (obj)).moveToFirst();
        j = 0;
label0:
        do
        {
            String s2;
            Object obj2;
            long l1;
            long l2;
            long l3;
            boolean flag;
            String s3;
            boolean flag1;
label1:
            {
label2:
                {
                    if(((Cursor) (obj)).isAfterLast())
                        break label0;
                    obj2 = new ProgramInfo();
                    l1 = ((Cursor) (obj)).getLong(7) * 1000L;
                    l2 = 1000L * ((Cursor) (obj)).getLong(8);
                    if(l >= l1 + l2)
                    {
                        ((Cursor) (obj)).moveToNext();
                        continue;
                    }
                    if(l1 <= l)
                    {
                        list = "live";
                        l3 = 0x3000000000000000L - ((Cursor) (obj)).getLong(7);
                        flag = true;
                    } else
                    {
                        if(j >= 50)
                        {
                            ((Cursor) (obj)).moveToNext();
                            continue;
                        }
                        j++;
                        list = "future";
                        l3 = ((Cursor) (obj)).getLong(7);
                        flag = i;
                        l3 = 0x1000000000000000L - l3;
                    }
                    s = ((Cursor) (obj)).getString(6);
                    if(s != null)
                        s = s.trim();
                    else
                        s = "";
                    if(s.isEmpty())
                    {
                        ((Cursor) (obj)).moveToNext();
                        continue;
                    }
                    s2 = ((Cursor) (obj)).getString(9);
                    if(s2 != null)
                        s2 = s2.trim();
                    else
                        s2 = "";
                    ((ProgramInfo) (obj2)).setId(((Cursor) (obj)).getInt(i));
                    ((ProgramInfo) (obj2)).setBroadcast(convertBroadcastWaveToBroadcastType(((Cursor) (obj)).getInt(1)));
                    ((ProgramInfo) (obj2)).setServiceId(((Cursor) (obj)).getInt(2));
                    ((ProgramInfo) (obj2)).setEventId(((Cursor) (obj)).getInt(3));
                    ((ProgramInfo) (obj2)).setRefServiceId(((Cursor) (obj)).getInt(4));
                    ((ProgramInfo) (obj2)).setRefEventId(((Cursor) (obj)).getInt(5));
                    ((ProgramInfo) (obj2)).setEventName(s);
                    ((ProgramInfo) (obj2)).setStartTime(((Cursor) (obj)).getInt(7));
                    ((ProgramInfo) (obj2)).setDuration(((Cursor) (obj)).getInt(8));
                    ((ProgramInfo) (obj2)).setDescription(s2);
                    ((ProgramInfo) (obj2)).setGenre1(((Cursor) (obj)).getInt(10));
                    ((ProgramInfo) (obj2)).setGenre2(((Cursor) (obj)).getInt(11));
                    ((ProgramInfo) (obj2)).setGenre3(((Cursor) (obj)).getInt(12));
                    ((ProgramInfo) (obj2)).setRating(((Cursor) (obj)).getInt(13));
                    ((ProgramInfo) (obj2)).setCaPayService(((Cursor) (obj)).getInt(14));
                    ((ProgramInfo) (obj2)).setCaAvailable(((Cursor) (obj)).getInt(15));
                    s3 = FilterAribGaiji.replaceAribGaijiWithWhiteSpace(s);
                    s = new StringBuilder();
                    s.append(SuggestContentDatabase.getFormattedDateTimeString(l1, l2));
                    s.append(" ");
                    s.append(ChannelInfoOpenHelper.getServiceName(context, ((ProgramInfo) (obj2)).getBroadcast(), ((ProgramInfo) (obj2)).getServiceId()));
                    s.append(" ");
                    s.append(FilterAribGaiji.replaceAribGaijiWithWhiteSpace(s2));
                    s2 = s.toString();
                    i = j;
                    if(list1 == null)
                        break label2;
                    s = list1.iterator();
                    ReservationInfoOpenHelper.ReservationInfo reservationinfo;
                    do
                    {
                        i = j;
                        if(!s.hasNext())
                            break label2;
                        reservationinfo = (ReservationInfoOpenHelper.ReservationInfo)s.next();
                        i = reservationinfo.getType();
                    } while(i != ReservationInfoOpenHelper.ReservationInfo.RecordType.PROGRAM.getValue() || reservationinfo.getEventId() != ((ProgramInfo) (obj2)).getEventId() || reservationinfo.getBroadcast() != ((ProgramInfo) (obj2)).getBroadcast() || reservationinfo.getServiceId() != ((ProgramInfo) (obj2)).getServiceId() || reservationinfo.getScheduleState() == ReservationInfoOpenHelper.ReservationInfo.ReservationState.RECORD_STOPED.getValue() || reservationinfo.getScheduleState() == ReservationInfoOpenHelper.ReservationInfo.ReservationState.RECORD_INTERRUPT.getValue());
                    flag1 = true;
                    break label1;
                }
                j = i;
                flag1 = false;
            }
            if(flag)
                s = SuggestContentDatabase.getUriString(context, getLiveGenreImageResource(((ProgramInfo) (obj2)).getGenre1()));
            else
            if(flag1)
                s = SuggestContentDatabase.getUriString(context, getReserveGenreImageResource(((ProgramInfo) (obj2)).getGenre1()));
            else
                s = SuggestContentDatabase.getUriString(context, getFutureGenreImageResource(((ProgramInfo) (obj2)).getGenre1()));
            int k = Integer.valueOf(SuggestContentDatabase.getYearString(l1)).intValue();
            Gson gson = new Gson();
            StringBuilder stringbuilder2 = new StringBuilder();
            stringbuilder2.append(gson.toJson(obj2));
            stringbuilder2.append("#");
            stringbuilder2.append(list);
            obj2 = stringbuilder2.toString();
            i = 0;
            SuggestContentDatabase.addProgram(sqlitedatabase, s3, s2, s1, k, l2, 180, 320, s, flag, ((String) (obj2)), l3);
            s2 = TAG;
            s = new StringBuilder();
            s.append("insertQueryMatched addProgram contentType=");
            s.append(list);
            s.append(", reserved=");
            s.append(flag1);
            PxLog.v(s2, s.toString());
            ((Cursor) (obj)).moveToNext();
        } while(true);
        ((Cursor) (obj)).close();
        sqlitedatabase1.close();
    }

    protected String getDatabaseNamePrefix()
    {
        return "program_info";
    }

    public volatile SQLiteDatabase getReadableDatabase()
    {
        return super.getReadableDatabase();
    }

    public volatile SQLiteDatabase getWritableDatabase()
    {
        return super.getWritableDatabase();
    }

    public volatile void onCreate(SQLiteDatabase sqlitedatabase)
    {
        super.onCreate(sqlitedatabase);
    }

    public volatile void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        super.onUpgrade(sqlitedatabase, i, j);
    }

    private static final String DATABASE_PREFIX = "program_info";
    private static final String MY_DATABASE_NAME = "program_info.db";
    private static final int MY_DATABASE_VERSION = 2;
    private static final String TAG = "ProgramInfoOpenHelper";

}
