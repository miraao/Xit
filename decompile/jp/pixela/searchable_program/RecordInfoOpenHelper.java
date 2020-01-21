// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.searchable_program;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.gson.Gson;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import jp.pixela.common.FilterAribGaiji;
import jp.pixela.common.PxLog;

// Referenced classes of package jp.pixela.searchable_program:
//            ContentInfoOpenHelper, SuggestContentDatabase

public class RecordInfoOpenHelper extends ContentInfoOpenHelper
{
    public static class RecordContentInfo
    {

        public String getObjectId()
        {
            return objectId_;
        }

        public long getProgramBroadcast()
        {
            return programBroadcast_;
        }

        public long getProgramDuration()
        {
            return programDuration_;
        }

        public String getProgramEventDesc()
        {
            return programEventDesc_;
        }

        public long getProgramServiceId()
        {
            return programServiceId_;
        }

        public long getProgramStart()
        {
            return programStart_;
        }

        public long getResumeOffset()
        {
            return resumeOffset_;
        }

        public String getTitle()
        {
            return title_;
        }

        public void setObjectId(String s)
        {
            objectId_ = s;
        }

        public void setProgramBroadcast(long l)
        {
            programBroadcast_ = l;
        }

        public void setProgramDuration(long l)
        {
            programDuration_ = l;
        }

        public void setProgramEventDesc(String s)
        {
            programEventDesc_ = s;
        }

        public void setProgramServiceId(long l)
        {
            programServiceId_ = l;
        }

        public void setProgramStart(long l)
        {
            programStart_ = l;
        }

        public void setResumeOffset(long l)
        {
            resumeOffset_ = l;
        }

        public void setTitle(String s)
        {
            title_ = s;
        }

        private String objectId_;
        private long programBroadcast_;
        private long programDuration_;
        private String programEventDesc_;
        private long programServiceId_;
        private long programStart_;
        private long resumeOffset_;
        private String title_;

        public RecordContentInfo()
        {
        }
    }


    public RecordInfoOpenHelper(Context context)
    {
        super(context, "record_content.db", 1);
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

    public static int getRecordImageResource(int i)
    {
        switch(i >> 4)
        {
        default:
            return 0x7f0700d0;

        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
            return 0x7f0700e0;

        case 11: // '\013'
            return 0x7f0700fa;

        case 10: // '\n'
            return 0x7f070088;

        case 9: // '\t'
            return 0x7f0700ed;

        case 8: // '\b'
            return 0x7f07007c;

        case 7: // '\007'
            return 0x7f07006e;

        case 6: // '\006'
            return 0x7f0700c7;

        case 5: // '\005'
            return 0x7f0700f6;

        case 4: // '\004'
            return 0x7f0700ca;

        case 3: // '\003'
            return 0x7f07007f;

        case 2: // '\002'
            return 0x7f0700fd;

        case 1: // '\001'
            return 0x7f0700ea;

        case 0: // '\0'
            return 0x7f0700cd;
        }
    }

    public static RecordContentInfo readExtraData(String s)
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
            Gson gson = JVM INSTR new #90  <Class Gson>;
            gson.Gson();
            s = (RecordContentInfo)gson.fromJson(s, jp/pixela/searchable_program/RecordInfoOpenHelper$RecordContentInfo);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s = null;
        }
        return s;
    }

    public void addRecordContentToSuggestContentDatabase(Context context, String s, SQLiteDatabase sqlitedatabase)
    {
        ArrayList arraylist = new ArrayList();
        s = Normalizer.normalize(s, java.text.Normalizer.Form.NFKC);
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append("ColumnRecordDcTitle LIKE ?");
        ((StringBuilder) (obj)).append(" OR ColumnRecordProgramEventDescription LIKE ?");
        obj = ((StringBuilder) (obj)).toString();
        Object obj1 = new StringBuilder();
        ((StringBuilder) (obj1)).append(((String) (obj)));
        ((StringBuilder) (obj1)).append(" OR ColumnRecordServiceName LIKE ?");
        obj1 = ((StringBuilder) (obj1)).toString();
        obj = new StringBuilder();
        ((StringBuilder) (obj)).append("%");
        ((StringBuilder) (obj)).append(s);
        ((StringBuilder) (obj)).append("%");
        arraylist.add(((StringBuilder) (obj)).toString());
        obj = new StringBuilder();
        ((StringBuilder) (obj)).append("%");
        ((StringBuilder) (obj)).append(s);
        ((StringBuilder) (obj)).append("%");
        arraylist.add(((StringBuilder) (obj)).toString());
        obj = new StringBuilder();
        ((StringBuilder) (obj)).append("%");
        ((StringBuilder) (obj)).append(s);
        ((StringBuilder) (obj)).append("%");
        arraylist.add(((StringBuilder) (obj)).toString());
        obj = getReadableDatabase();
        boolean flag;
        try
        {
            s = (String[])arraylist.toArray(new String[0]);
            obj1 = ((SQLiteDatabase) (obj)).query("TableRecords", new String[] {
                "ColumnRecordDcTitle", "ColumnRecordProgramEventDescription", "ColumnRecordDcDate", "ColumnRecordResDuration", "ColumnRecordBroadcastWave", "ColumnRecordServiceID", "ColumnRecordObjectID", "ColumnRecordResumeOffset", "ColumnRecordServiceName", "ColumnRecordProgramGenre1"
            }, ((String) (obj1)), s, null, null, "ColumnRecordDcDate DESC");
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            PxLog.w(TAG, context.getMessage());
            ((SQLiteDatabase) (obj)).close();
            return;
        }
        if(obj1 == null)
        {
            PxLog.i(TAG, "cursor is null");
            ((SQLiteDatabase) (obj)).close();
            return;
        }
        flag = ((Cursor) (obj1)).moveToFirst();
        for(int i = 0; flag && i < 50; flag = ((Cursor) (obj1)).moveToNext())
        {
            i++;
            s = new RecordContentInfo();
            s.setTitle(((Cursor) (obj1)).getString(0).trim());
            s.setProgramEventDesc(((Cursor) (obj1)).getString(1).trim());
            s.setProgramStart(((Cursor) (obj1)).getLong(2));
            s.setProgramDuration(((Cursor) (obj1)).getLong(3));
            s.setProgramBroadcast(convertBroadcastWaveToBroadcastType((int)((Cursor) (obj1)).getLong(4)));
            s.setProgramServiceId(((Cursor) (obj1)).getLong(5));
            s.setObjectId(((Cursor) (obj1)).getString(6));
            s.setResumeOffset(((Cursor) (obj1)).getLong(7));
            String s2 = ((Cursor) (obj1)).getString(8);
            int j = ((Cursor) (obj1)).getInt(9);
            long l = s.getProgramStart() * 1000L;
            long l1 = 1000L * s.getProgramDuration();
            String s1 = FilterAribGaiji.replaceAribGaijiWithWhiteSpace(s.getTitle());
            Object obj2 = new StringBuilder();
            ((StringBuilder) (obj2)).append(SuggestContentDatabase.getFormattedDateTimeString(l, l1));
            ((StringBuilder) (obj2)).append(" ");
            ((StringBuilder) (obj2)).append(s2);
            ((StringBuilder) (obj2)).append(" ");
            ((StringBuilder) (obj2)).append(FilterAribGaiji.replaceAribGaijiWithWhiteSpace(s.getProgramEventDesc()));
            obj2 = ((StringBuilder) (obj2)).toString();
            String s3 = context.getString(0x7f0e004f);
            int k = Integer.valueOf(SuggestContentDatabase.getYearString(l)).intValue();
            String s4 = SuggestContentDatabase.getUriString(context, getRecordImageResource(j));
            s2 = (new Gson()).toJson(s);
            l = s.getProgramStart();
            l1 = SuggestContentDatabase.addProgram(sqlitedatabase, s1, ((String) (obj2)), s3, k, l1, thumbnail_height_, thumbnail_width_, s4, false, s2, 0x1000000000000000L + l);
            s2 = TAG;
            obj2 = new StringBuilder();
            ((StringBuilder) (obj2)).append("insertQueryMatched addProgram ");
            if(l1 >= 0L)
                s = "success";
            else
                s = "failure";
            ((StringBuilder) (obj2)).append(s);
            ((StringBuilder) (obj2)).append(". title=");
            ((StringBuilder) (obj2)).append(s1);
            PxLog.v(s2, ((StringBuilder) (obj2)).toString());
        }

        ((Cursor) (obj1)).close();
        ((SQLiteDatabase) (obj)).close();
    }

    protected String getDatabaseNamePrefix()
    {
        return "record_content";
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

    public void setThumbnailSize(int i, int j)
    {
        thumbnail_width_ = i;
        thumbnail_height_ = j;
    }

    private static final String DATABASE_PREFIX = "record_content";
    private static final String MY_DATABASE_NAME = "record_content.db";
    private static final int MY_DATABASE_VERSION = 1;
    private static final String TAG = "RecordInfoOpenHelper";
    private int thumbnail_height_;
    private int thumbnail_width_;

}
