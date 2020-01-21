// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.searchable_program;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.LinkedList;
import java.util.List;
import jp.pixela.common.PxLog;

// Referenced classes of package jp.pixela.searchable_program:
//            ContentInfoOpenHelper

public class ReservationInfoOpenHelper extends ContentInfoOpenHelper
{
    public static class ReservationInfo
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

        public int getDuration()
        {
            return mDuration;
        }

        public int getEventId()
        {
            return mEventId;
        }

        public String getId()
        {
            return mId;
        }

        public int getScheduleState()
        {
            return mScheduleState;
        }

        public int getServiceId()
        {
            return mServiceId;
        }

        public int getStartTime()
        {
            return mStartTime;
        }

        public String getTitle()
        {
            return mTitle;
        }

        public int getType()
        {
            return mType;
        }

        public void setBroadcast(int i)
        {
            mBroadcast = i;
        }

        public void setDuration(int i)
        {
            mDuration = i;
        }

        public void setEventId(int i)
        {
            mEventId = i;
        }

        public void setId(String s)
        {
            mId = s;
        }

        public void setScheduleState(int i)
        {
            mScheduleState = i;
        }

        public void setServiceId(int i)
        {
            mServiceId = i;
        }

        public void setStartTime(int i)
        {
            mStartTime = i;
        }

        public void setTitle(String s)
        {
            mTitle = s;
        }

        public void setType(int i)
        {
            mType = i;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(mId);
            parcel.writeInt(mBroadcast);
            parcel.writeInt(mServiceId);
            parcel.writeString(mTitle);
            parcel.writeInt(mStartTime);
            parcel.writeInt(mDuration);
            parcel.writeInt(mEventId);
            parcel.writeInt(mScheduleState);
            parcel.writeInt(mType);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ReservationInfo createFromParcel(Parcel parcel)
            {
                return new ReservationInfo(parcel);
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

            public ReservationInfo[] newArray(int i)
            {
                return new ReservationInfo[i];
            }

        }
;
        private int mBroadcast;
        private int mDuration;
        private int mEventId;
        private String mId;
        private int mScheduleState;
        private int mServiceId;
        private int mStartTime;
        private String mTitle;
        private int mType;


        public ReservationInfo()
        {
            mId = null;
            mBroadcast = 1;
            mServiceId = 0;
            mTitle = null;
            mStartTime = 0;
            mDuration = 0;
            mEventId = 0;
            mScheduleState = 0;
            mType = 0;
        }

        public ReservationInfo(Parcel parcel)
        {
            mId = null;
            mBroadcast = 1;
            mServiceId = 0;
            mTitle = null;
            mStartTime = 0;
            mDuration = 0;
            mEventId = 0;
            mScheduleState = 0;
            mType = 0;
            mId = parcel.readString();
            mBroadcast = parcel.readInt();
            mServiceId = parcel.readInt();
            mTitle = parcel.readString();
            mStartTime = parcel.readInt();
            mDuration = parcel.readInt();
            mEventId = parcel.readInt();
            mScheduleState = parcel.readInt();
            mType = parcel.readInt();
        }
    }

    public static final class ReservationInfo.RecordType extends Enum
    {

        public static ReservationInfo.RecordType valueOf(String s)
        {
            return (ReservationInfo.RecordType)Enum.valueOf(jp/pixela/searchable_program/ReservationInfoOpenHelper$ReservationInfo$RecordType, s);
        }

        public static ReservationInfo.RecordType[] values()
        {
            return (ReservationInfo.RecordType[])$VALUES.clone();
        }

        public int getValue()
        {
            return mValue;
        }

        private static final ReservationInfo.RecordType $VALUES[];
        public static final ReservationInfo.RecordType DATE;
        public static final ReservationInfo.RecordType LIVE;
        public static final ReservationInfo.RecordType PROGRAM;
        public static final ReservationInfo.RecordType UNKNOWN;
        private int mValue;

        static 
        {
            UNKNOWN = new ReservationInfo.RecordType("UNKNOWN", 0, 0);
            LIVE = new ReservationInfo.RecordType("LIVE", 1, 1);
            PROGRAM = new ReservationInfo.RecordType("PROGRAM", 2, 2);
            DATE = new ReservationInfo.RecordType("DATE", 3, 3);
            $VALUES = (new ReservationInfo.RecordType[] {
                UNKNOWN, LIVE, PROGRAM, DATE
            });
        }

        private ReservationInfo.RecordType(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }

    public static final class ReservationInfo.ReservationState extends Enum
    {

        public static ReservationInfo.ReservationState valueOf(String s)
        {
            return (ReservationInfo.ReservationState)Enum.valueOf(jp/pixela/searchable_program/ReservationInfoOpenHelper$ReservationInfo$ReservationState, s);
        }

        public static ReservationInfo.ReservationState[] values()
        {
            return (ReservationInfo.ReservationState[])$VALUES.clone();
        }

        public int getValue()
        {
            return mValue;
        }

        private static final ReservationInfo.ReservationState $VALUES[];
        public static final ReservationInfo.ReservationState COMPLETE;
        public static final ReservationInfo.ReservationState PREPARING;
        public static final ReservationInfo.ReservationState PRE_RUNNING;
        public static final ReservationInfo.ReservationState RECORD_INTERRUPT;
        public static final ReservationInfo.ReservationState RECORD_STOPED;
        public static final ReservationInfo.ReservationState RUNNING;
        public static final ReservationInfo.ReservationState UNKNOWN;
        public static final ReservationInfo.ReservationState UPDATE_WAITING;
        public static final ReservationInfo.ReservationState WAITING;
        private int mValue;

        static 
        {
            UNKNOWN = new ReservationInfo.ReservationState("UNKNOWN", 0, 0);
            WAITING = new ReservationInfo.ReservationState("WAITING", 1, 1);
            PREPARING = new ReservationInfo.ReservationState("PREPARING", 2, 2);
            PRE_RUNNING = new ReservationInfo.ReservationState("PRE_RUNNING", 3, 3);
            RUNNING = new ReservationInfo.ReservationState("RUNNING", 4, 4);
            COMPLETE = new ReservationInfo.ReservationState("COMPLETE", 5, 5);
            UPDATE_WAITING = new ReservationInfo.ReservationState("UPDATE_WAITING", 6, 6);
            RECORD_STOPED = new ReservationInfo.ReservationState("RECORD_STOPED", 7, 7);
            RECORD_INTERRUPT = new ReservationInfo.ReservationState("RECORD_INTERRUPT", 8, 8);
            $VALUES = (new ReservationInfo.ReservationState[] {
                UNKNOWN, WAITING, PREPARING, PRE_RUNNING, RUNNING, COMPLETE, UPDATE_WAITING, RECORD_STOPED, RECORD_INTERRUPT
            });
        }

        private ReservationInfo.ReservationState(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }


    ReservationInfoOpenHelper(Context context)
    {
        super(context, "reservation_info.db", 1);
    }

    protected String getDatabaseNamePrefix()
    {
        return "reservation_info";
    }

    public volatile SQLiteDatabase getReadableDatabase()
    {
        return super.getReadableDatabase();
    }

    public List getReservationInfoList(Context context)
    {
        SQLiteDatabase sqlitedatabase = getReadableDatabase();
        context = new LinkedList();
        Cursor cursor;
        String s;
        StringBuilder stringbuilder;
        try
        {
            cursor = sqlitedatabase.query("TableReservation", new String[] {
                "ColumnReservationID", "ColumnReservationBroascastWave", "ColumnReservationServiceID", "ColumnReservationTitle", "ColumnReservationScheduleStartDate", "ColumnReservationScheduleDuration", "ColumnReservationEventID", "ColumnReservationScheduleState", "ColumnReservationReservationType"
            }, null, null, null, null, null);
        }
        catch(Exception exception)
        {
            PxLog.w(TAG, exception.getMessage());
            sqlitedatabase.close();
            return context;
        }
        if(cursor == null)
        {
            PxLog.i(TAG, "cursor is null");
            sqlitedatabase.close();
            return context;
        }
        s = TAG;
        stringbuilder = new StringBuilder();
        stringbuilder.append("find count=");
        stringbuilder.append(cursor.getCount());
        PxLog.i(s, stringbuilder.toString());
        cursor.moveToFirst();
        for(; !cursor.isAfterLast(); cursor.moveToNext())
        {
            ReservationInfo reservationinfo = new ReservationInfo();
            reservationinfo.setId(cursor.getString(0));
            reservationinfo.setBroadcast(convertBroadcastWaveToBroadcastType(cursor.getInt(1)));
            reservationinfo.setServiceId(cursor.getInt(2));
            reservationinfo.setTitle(cursor.getString(3));
            reservationinfo.setStartTime(cursor.getInt(4));
            reservationinfo.setDuration(cursor.getInt(5));
            reservationinfo.setEventId(cursor.getInt(6));
            reservationinfo.setScheduleState(cursor.getInt(7));
            reservationinfo.setType(cursor.getInt(8));
            context.add(reservationinfo);
        }

        cursor.close();
        sqlitedatabase.close();
        return context;
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

    private static final String DATABASE_PREFIX = "reservation_info";
    private static final String MY_DATABASE_NAME = "reservation_info.db";
    private static final int MY_DATABASE_VERSION = 1;
    private static final String TAG = "ReservationInfoOpenHelper";

}
