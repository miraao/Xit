// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.searchable_program;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.*;
import jp.pixela.common.PxLog;

abstract class ContentInfoOpenHelper extends SQLiteOpenHelper
{

    ContentInfoOpenHelper(Context context, String s, int i)
    {
        super(context, s, null, i);
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getDatabaseNamePrefix());
        stringbuilder.append(".last_modified.txt");
        MY_DATABASE_LAST_MODIFIED_PATH = stringbuilder.toString();
        mContext = context;
        mMyDatabaseFile = context.getDatabasePath(s);
        s = new StringBuilder();
        s.append(context.getFilesDir());
        s.append(File.separator);
        s.append("database");
        s.append(File.separator);
        s.append(getDatabaseNamePrefix());
        s.append(".sqlite3");
        mMmtControlDatabaseFile = new File(s.toString());
    }

    static int convertBroadcastTypeToBroadcastWave(int i)
    {
        switch(i)
        {
        default:
            return 0;

        case 5: // '\005'
            return 16;

        case 4: // '\004'
            return 8;

        case 3: // '\003'
            return 4;

        case 2: // '\002'
            return 2;

        case 1: // '\001'
            return 1;
        }
    }

    static int convertBroadcastWaveToBroadcastType(int i)
    {
        if(i != 4)
        {
            if(i != 8)
            {
                if(i != 16)
                    switch(i)
                    {
                    default:
                        return 0;

                    case 2: // '\002'
                        return 2;

                    case 1: // '\001'
                        return 1;
                    }
                else
                    return 5;
            } else
            {
                return 4;
            }
        } else
        {
            return 3;
        }
    }

    private boolean copyFromMmtControlDatabaseToMyDatabase()
    {
        PxLog.v(TAG, "copyFromMmtControlDatabaseToMyDatabase in");
        FileInputStream fileinputstream;
        FileOutputStream fileoutputstream;
        byte abyte0[];
        fileinputstream = JVM INSTR new #94  <Class FileInputStream>;
        fileinputstream.FileInputStream(mMmtControlDatabaseFile);
        fileoutputstream = JVM INSTR new #99  <Class FileOutputStream>;
        fileoutputstream.FileOutputStream(mMyDatabaseFile);
        abyte0 = new byte[1024];
_L1:
        int i = fileinputstream.read(abyte0);
label0:
        {
            if(i <= 0)
                break label0;
            try
            {
                fileoutputstream.write(abyte0, 0, i);
            }
            catch(IOException ioexception)
            {
                String s = TAG;
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append("copyFromMmtControlDatabaseToMyDatabase out. e=");
                stringbuilder.append(ioexception);
                PxLog.v(s, stringbuilder.toString());
                return false;
            }
        }
          goto _L1
        fileoutputstream.flush();
        fileoutputstream.close();
        fileinputstream.close();
        PxLog.v(TAG, "copyFromMmtControlDatabaseToMyDatabase out");
        return true;
    }

    private boolean createEmptyDatabase(SQLiteDatabase sqlitedatabase)
    {
        PxLog.v(TAG, "createEmptyDatabase in");
        if(sqlitedatabase == null)
        {
            PxLog.e(TAG, "createEmptyDatabase out. return false. (database == null)");
            return false;
        } else
        {
            sqlitedatabase.close();
            PxLog.v(TAG, "createEmptyDatabase out. return true.");
            return true;
        }
    }

    private long getMmtControlDatabaseLastModified()
    {
        if(!mMmtControlDatabaseFile.exists())
            return 0L;
        else
            return mMmtControlDatabaseFile.lastModified();
    }

    private long getMyDatabaseLastModified()
    {
        Object obj;
        PxLog.v(TAG, "getMyDatabaseLastModified in");
        obj = null;
        BufferedReader bufferedreader;
        Object obj1;
        bufferedreader = JVM INSTR new #152 <Class BufferedReader>;
        obj1 = JVM INSTR new #154 <Class InputStreamReader>;
        ((InputStreamReader) (obj1)).InputStreamReader(mContext.openFileInput(MY_DATABASE_LAST_MODIFIED_PATH));
        bufferedreader.BufferedReader(((java.io.Reader) (obj1)));
        obj1 = bufferedreader.readLine();
        bufferedreader.close();
        break MISSING_BLOCK_LABEL_94;
        IOException ioexception;
        ioexception;
        break MISSING_BLOCK_LABEL_57;
        ioexception;
        obj1 = obj;
        String s = TAG;
        StringBuilder stringbuilder1 = new StringBuilder();
        stringbuilder1.append("getMyDatabaseLastModified e=");
        stringbuilder1.append(ioexception);
        PxLog.w(s, stringbuilder1.toString());
        if(obj1 == null)
        {
            PxLog.v(TAG, "getMyDatabaseLastModified out. return 0 (line == null)");
            return 0L;
        }
        long l;
        try
        {
            l = Long.parseLong(((String) (obj1)));
            String s2 = TAG;
            obj1 = JVM INSTR new #27  <Class StringBuilder>;
            ((StringBuilder) (obj1)).StringBuilder();
            ((StringBuilder) (obj1)).append("getMyDatabaseLastModified out. lastModified=");
            ((StringBuilder) (obj1)).append(l);
            PxLog.v(s2, ((StringBuilder) (obj1)).toString());
        }
        catch(NumberFormatException numberformatexception)
        {
            String s1 = TAG;
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("getMyDatabaseLastModified out. return 0 (e=");
            stringbuilder.append(numberformatexception);
            stringbuilder.append(")");
            PxLog.v(s1, stringbuilder.toString());
            return 0L;
        }
        return l;
    }

    private boolean isNeedUpdateMyDatabase()
    {
        PxLog.v(TAG, "isNeedUpdateMyDatabase in");
        if(!mMmtControlDatabaseFile.exists())
        {
            PxLog.e(TAG, "isNeedUpdateMyDatabase out. return false. (!mMmtControlDatabaseFile.exists())");
            return false;
        }
        if(!mMyDatabaseFile.exists())
        {
            PxLog.v(TAG, "isNeedUpdateMyDatabase out. return true. (!mMyDatabaseFile.exists())");
            return true;
        }
        long l = getMyDatabaseLastModified();
        long l1 = getMmtControlDatabaseLastModified();
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("isNeedUpdateMyDatabase lastModified=");
        stringbuilder.append(l);
        stringbuilder.append(", mmtControlDatabaseLastModified=");
        stringbuilder.append(l1);
        PxLog.v(s, stringbuilder.toString());
        if(l == l1)
        {
            PxLog.v(TAG, "isNeedUpdateMyDatabase out. return false. (lastModified == mmtControlDatabaseLastModified)");
            return false;
        } else
        {
            PxLog.v(TAG, "isNeedUpdateMyDatabase out. return true. (lastModified != mmtControlDatabaseLastModified)");
            return true;
        }
    }

    private boolean setMyDatabaseLastModified()
    {
        PxLog.v(TAG, "setMyDatabaseLastModified in");
        long l = getMmtControlDatabaseLastModified();
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("setMyDatabaseLastModified originalLastModified=");
        stringbuilder.append(l);
        PxLog.v(s, stringbuilder.toString());
        try
        {
            FileOutputStream fileoutputstream = mContext.openFileOutput(MY_DATABASE_LAST_MODIFIED_PATH, 0);
            fileoutputstream.write(String.valueOf(l).getBytes());
            fileoutputstream.close();
        }
        catch(IOException ioexception)
        {
            String s1 = TAG;
            StringBuilder stringbuilder1 = new StringBuilder();
            stringbuilder1.append("setMyDatabaseLastModified e=");
            stringbuilder1.append(ioexception);
            PxLog.w(s1, stringbuilder1.toString());
            PxLog.w(TAG, "setMyDatabaseLastModified out. return false");
            return false;
        }
        PxLog.w(TAG, "setMyDatabaseLastModified out");
        return true;
    }

    private void updateMyDatabase()
    {
        PxLog.v(TAG, "updateMyDatabase in");
        boolean flag = mMyDatabaseFile.delete();
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("updateMyDatabase delete ret=");
        stringbuilder.append(flag);
        PxLog.v(s, stringbuilder.toString());
        flag = createEmptyDatabase(super.getReadableDatabase());
        s = TAG;
        stringbuilder = new StringBuilder();
        stringbuilder.append("updateMyDatabase createEmptyDatabase ret=");
        stringbuilder.append(flag);
        PxLog.v(s, stringbuilder.toString());
        flag = copyFromMmtControlDatabaseToMyDatabase();
        s = TAG;
        stringbuilder = new StringBuilder();
        stringbuilder.append("updateMyDatabase copyFromMmtControlDatabaseToMyDatabase ret=");
        stringbuilder.append(flag);
        PxLog.v(s, stringbuilder.toString());
        flag = setMyDatabaseLastModified();
        s = TAG;
        stringbuilder = new StringBuilder();
        stringbuilder.append("updateMyDatabase setMyDatabaseLastModified ret=");
        stringbuilder.append(flag);
        PxLog.v(s, stringbuilder.toString());
        PxLog.v(TAG, "updateMyDatabase out");
    }

    private void updateMyDatabaseIfNeed()
    {
        PxLog.v(TAG, "updateMyDatabaseIfNeed in");
        if(isNeedUpdateMyDatabase())
            updateMyDatabase();
        PxLog.v(TAG, "updateMyDatabaseIfNeed out");
    }

    protected abstract String getDatabaseNamePrefix();

    public SQLiteDatabase getReadableDatabase()
    {
        PxLog.v(TAG, "getReadableDatabase in");
        updateMyDatabaseIfNeed();
        SQLiteDatabase sqlitedatabase = super.getReadableDatabase();
        PxLog.v(TAG, "getReadableDatabase out");
        return sqlitedatabase;
    }

    public SQLiteDatabase getWritableDatabase()
    {
        PxLog.v(TAG, "getWritableDatabase in");
        updateMyDatabaseIfNeed();
        SQLiteDatabase sqlitedatabase = super.getWritableDatabase();
        PxLog.v(TAG, "getWritableDatabase out");
        return sqlitedatabase;
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        PxLog.v(TAG, "onCreate");
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        PxLog.v(TAG, "onUpgrade");
    }

    private static final int BUFFER_SIZE = 1024;
    private static final String TAG = "ContentInfoOpenHelper";
    private final String MY_DATABASE_LAST_MODIFIED_PATH;
    private final Context mContext;
    private final File mMmtControlDatabaseFile;
    private final File mMyDatabaseFile;

}
