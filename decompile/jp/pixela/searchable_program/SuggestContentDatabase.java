// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.searchable_program;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.*;
import android.net.Uri;
import java.text.SimpleDateFormat;
import java.util.*;
import jp.pixela.common.PxLog;

// Referenced classes of package jp.pixela.searchable_program:
//            ChannelInfoOpenHelper, ReservationInfoOpenHelper, ProgramInfoOpenHelper, RecordInfoOpenHelper

public class SuggestContentDatabase
{
    private static class SuggestContentDatabaseOpenHelper extends SQLiteOpenHelper
    {

        public void onCreate(SQLiteDatabase sqlitedatabase)
        {
            sqlitedatabase.execSQL("CREATE TABLE programs (_id INTEGER PRIMARY KEY,suggest_text_1 TEXT NOT NULL, suggest_text_2 TEXT, suggest_content_type TEXT NOT NULL, suggest_production_year INTEGER NOT NULL, suggest_duration INTEGER, suggest_video_height INTEGER DEFAULT 0, suggest_video_width INTEGER DEFAULT 0, suggest_result_card_image TEXT, suggest_is_live INTEGER DEFAULT 0, suggest_intent_extra_data TEXT, suggest_format TEXT  );");
        }

        public void onDowngrade(SQLiteDatabase sqlitedatabase, int i, int j)
        {
            onUpgrade(sqlitedatabase, i, j);
        }

        public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
        {
            sqlitedatabase.execSQL("DROP TABLE IF EXISTS programs");
            onCreate(sqlitedatabase);
        }

        private static final String DATABASE_NAME = "suggest_content.db";
        private static final int DATABASE_VERSION = 2;

        SuggestContentDatabaseOpenHelper(Context context)
        {
            super(context, "suggest_content.db", null, 2);
        }
    }


    SuggestContentDatabase(Context context)
    {
        mContext = context;
        mSuggestContentDatabaseOpenHelper = new SuggestContentDatabaseOpenHelper(context);
    }

    public static long addProgram(SQLiteDatabase sqlitedatabase, String s, String s1, String s2, int i, long l, int j, 
            int k, String s3, boolean flag, String s4, long l1)
    {
        PxLog.v(TAG, "addProgram in");
        if(sqlitedatabase == null)
        {
            PxLog.w(TAG, "addProgram (database == null)");
            return -1L;
        } else
        {
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("suggest_text_1", s);
            contentvalues.put("suggest_text_2", s1);
            contentvalues.put("suggest_content_type", s2);
            contentvalues.put("suggest_production_year", Integer.valueOf(i));
            contentvalues.put("suggest_duration", Long.valueOf(l));
            contentvalues.put("suggest_video_height", Integer.valueOf(j));
            contentvalues.put("suggest_video_width", Integer.valueOf(k));
            contentvalues.put("suggest_result_card_image", s3);
            contentvalues.put("suggest_is_live", Boolean.valueOf(flag));
            contentvalues.put("suggest_intent_extra_data", s4);
            contentvalues.put("suggest_format", Long.valueOf(l1));
            l = sqlitedatabase.insert("programs", null, contentvalues);
            sqlitedatabase = TAG;
            s = new StringBuilder();
            s.append("addProgram out. rowId=");
            s.append(l);
            PxLog.v(sqlitedatabase, s.toString());
            return l;
        }
    }

    private static Map buildColumnMap()
    {
        HashMap hashmap = new HashMap();
        hashmap.put("suggest_text_1", "suggest_text_1");
        hashmap.put("suggest_text_2", "suggest_text_2");
        hashmap.put("suggest_content_type", "suggest_content_type");
        hashmap.put("suggest_production_year", "suggest_production_year");
        hashmap.put("suggest_duration", "suggest_duration");
        hashmap.put("suggest_video_height", "suggest_video_height");
        hashmap.put("suggest_video_width", "suggest_video_width");
        hashmap.put("suggest_result_card_image", "suggest_result_card_image");
        hashmap.put("suggest_is_live", "suggest_is_live");
        hashmap.put("suggest_intent_extra_data", "suggest_intent_extra_data");
        hashmap.put("suggest_format", "suggest_format");
        hashmap.put("_id", "rowid AS _id");
        hashmap.put("suggest_intent_data_id", "rowid AS suggest_intent_data_id");
        hashmap.put("suggest_shortcut_id", "rowid AS suggest_shortcut_id");
        return hashmap;
    }

    private int deleteAll(SQLiteDatabase sqlitedatabase)
    {
        PxLog.v(TAG, "deleteAll in");
        if(sqlitedatabase == null)
        {
            PxLog.w(TAG, "deleteAll (database == null)");
            return 0;
        } else
        {
            int i = sqlitedatabase.delete("programs", null, null);
            String s = TAG;
            sqlitedatabase = new StringBuilder();
            sqlitedatabase.append("deleteAll out. numOfRowsDeleted=");
            sqlitedatabase.append(i);
            PxLog.v(s, sqlitedatabase.toString());
            return i;
        }
    }

    public static String getFormattedDateString(long l)
    {
        return (new SimpleDateFormat("yyyy/MM/dd (E)", Locale.JAPAN)).format(new Date(l));
    }

    public static String getFormattedDateTimeString(long l, long l1)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getFormattedDateString(l));
        stringbuilder.append(" ");
        stringbuilder.append(getFormattedTimeString(l, l1 + l));
        return stringbuilder.toString();
    }

    public static String getFormattedTimeString(long l, long l1)
    {
        String s = getRoundedTime(l);
        String s1 = getRoundedTime(l1);
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(s);
        stringbuilder.append("-");
        stringbuilder.append(s1);
        return stringbuilder.toString();
    }

    public static String getRoundedTime(long l)
    {
        if(l == 0L)
            return "";
        else
            return (new SimpleDateFormat("HH:mm", Locale.JAPAN)).format(new Date(Math.round((double)l / 60000D) * 60000L));
    }

    public static String getServiceName(Context context, long l, long l1)
    {
        SQLiteDatabase sqlitedatabase = (new ChannelInfoOpenHelper(context)).getReadableDatabase();
        Cursor cursor = sqlitedatabase.query("TableChannels", new String[] {
            "ColumnChannelBroadcastType", "ColumnChannelServiceID", "ColumnChannelServiceName"
        }, null, null, null, null, null);
        String s = "";
        boolean flag = cursor.moveToFirst();
        do
        {
            context = s;
            if(!flag)
                break;
            long l2 = cursor.getLong(0);
            long l3 = cursor.getLong(1);
            context = cursor.getString(2);
            if(l2 == l && l3 == l1)
                break;
            flag = cursor.moveToNext();
        } while(true);
        cursor.close();
        sqlitedatabase.close();
        return context;
    }

    public static String getUriString(Context context, int i)
    {
        context = context.getResources();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("android.resource://");
        stringbuilder.append(context.getResourcePackageName(i));
        stringbuilder.append("/");
        stringbuilder.append(context.getResourceTypeName(i));
        stringbuilder.append("/");
        stringbuilder.append(context.getResourceEntryName(i));
        return stringbuilder.toString();
    }

    public static String getYearString(long l)
    {
        return (new SimpleDateFormat("yyyy", Locale.JAPAN)).format(new Date(l));
    }

    private void insertQueryMatched(Context context, String s)
    {
        PxLog.v(TAG, "insertQueryMatched in");
        if(context == null)
        {
            PxLog.e(TAG, "insertQueryMatched out. (context == null)");
            return;
        } else
        {
            Object obj = TAG;
            Object obj1 = new StringBuilder();
            ((StringBuilder) (obj1)).append("insertQueryMatched keyword=");
            ((StringBuilder) (obj1)).append(s);
            PxLog.v(((String) (obj)), ((StringBuilder) (obj1)).toString());
            obj = (new SuggestContentDatabaseOpenHelper(context)).getWritableDatabase();
            deleteAll(((SQLiteDatabase) (obj)));
            java.util.List list = (new ChannelInfoOpenHelper(context)).getSuggestChannels(context, s);
            obj1 = (new ReservationInfoOpenHelper(context)).getReservationInfoList(context);
            (new ProgramInfoOpenHelper(context)).addProgramInfoToSuggestContentDatabase(context, s, list, ((java.util.List) (obj1)), ((SQLiteDatabase) (obj)));
            obj1 = new RecordInfoOpenHelper(context);
            ((RecordInfoOpenHelper) (obj1)).setThumbnailSize(320, 180);
            ((RecordInfoOpenHelper) (obj1)).addRecordContentToSuggestContentDatabase(context, s, ((SQLiteDatabase) (obj)));
            ((SQLiteDatabase) (obj)).close();
            PxLog.v(TAG, "insertQueryMatched out");
            return;
        }
    }

    Cursor query(Uri uri, String as[], String s, String as1[], String s1)
    {
        String s2 = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("query in. uri=");
        stringbuilder.append(uri);
        stringbuilder.append(", projection=");
        stringbuilder.append(Arrays.toString(as));
        stringbuilder.append(", selection=");
        stringbuilder.append(s);
        stringbuilder.append(", selectionArgs=");
        stringbuilder.append(Arrays.toString(as1));
        stringbuilder.append(", sortOrder=");
        stringbuilder.append(s1);
        PxLog.v(s2, stringbuilder.toString());
        uri = as1[0];
        insertQueryMatched(mContext, uri);
        uri = new SQLiteQueryBuilder();
        uri.setTables("programs");
        uri.setProjectionMap(mColumnMap);
        uri = uri.query(mSuggestContentDatabaseOpenHelper.getReadableDatabase(), sVideosContainingQueryColumns, null, null, null, null, "suggest_format DESC");
        if(uri == null)
        {
            PxLog.v(TAG, "query out. (cursor == null)");
            return null;
        }
        if(!uri.moveToFirst())
        {
            uri.close();
            PxLog.v(TAG, "query out. (!cursor.moveToFirst())");
            return null;
        } else
        {
            PxLog.v(TAG, "query out");
            return uri;
        }
    }

    public static final String CONTENT_TYPE_FUTURE = "future";
    public static final String CONTENT_TYPE_LIVE = "live";
    public static final String CONTENT_TYPE_RECORD = "record";
    private static final String KEY_COLUMN_DURATION = "suggest_duration";
    private static final String KEY_COLUMN_FORMAT = "suggest_format";
    private static final String KEY_CONTENT_DESCRIPTION = "suggest_text_2";
    private static final String KEY_CONTENT_NAME = "suggest_text_1";
    private static final String KEY_CONTENT_TYPE = "suggest_content_type";
    private static final String KEY_ICON = "suggest_result_card_image";
    private static final String KEY_INTENT_EXTRA_DATA = "suggest_intent_extra_data";
    private static final String KEY_IS_LIVE = "suggest_is_live";
    private static final String KEY_PRODUCTION_YEAR = "suggest_production_year";
    private static final String KEY_VIDEO_HEIGHT = "suggest_video_height";
    private static final String KEY_VIDEO_WIDTH = "suggest_video_width";
    public static final int LIMIT_FUTURE = 50;
    public static final int LIMIT_RECORD = 50;
    public static final String SEPARATOR = "#";
    public static final long SORT_LIVE = 0x3000000000000000L;
    public static final long SORT_RECORD = 0x1000000000000000L;
    private static final String TABLE_NAME = "programs";
    private static final String TAG = "SuggestContentDatabase";
    public static final int THUMBNAIL_HEIGHT = 180;
    public static final int THUMBNAIL_WIDTH = 320;
    private static final Map mColumnMap = buildColumnMap();
    private static final String sVideosContainingQueryColumns[] = {
        "_id", "suggest_text_1", "suggest_text_2", "suggest_content_type", "suggest_production_year", "suggest_duration", "suggest_video_height", "suggest_video_width", "suggest_result_card_image", "suggest_is_live", 
        "suggest_intent_extra_data", "suggest_format", "suggest_intent_data_id"
    };
    private final Context mContext;
    private final SuggestContentDatabaseOpenHelper mSuggestContentDatabaseOpenHelper;

}
