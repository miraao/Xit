// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.searchable_program;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import java.util.Arrays;
import jp.pixela.common.PxLog;

// Referenced classes of package jp.pixela.searchable_program:
//            SuggestContentDatabase

public class ProgramInfoProvider extends ContentProvider
{

    public ProgramInfoProvider()
    {
    }

    public int delete(Uri uri, String s, String as[])
    {
        throw new UnsupportedOperationException();
    }

    public String getType(Uri uri)
    {
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("getType uri=");
        stringbuilder.append(uri);
        PxLog.v(s, stringbuilder.toString());
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentvalues)
    {
        throw new UnsupportedOperationException();
    }

    public boolean onCreate()
    {
        PxLog.v(TAG, "onCreate in");
        mSuggestContentDatabase = new SuggestContentDatabase(getContext());
        PxLog.v(TAG, "onCreate out");
        return true;
    }

    public Cursor query(Uri uri, String as[], String s, String as1[], String s1)
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
        uri = mSuggestContentDatabase.query(uri, as, s, as1, s1);
        PxLog.v(TAG, "query out");
        return uri;
    }

    public int update(Uri uri, ContentValues contentvalues, String s, String as[])
    {
        throw new UnsupportedOperationException();
    }

    static final String TAG = "ProgramInfoProvider";
    private SuggestContentDatabase mSuggestContentDatabase;

}
