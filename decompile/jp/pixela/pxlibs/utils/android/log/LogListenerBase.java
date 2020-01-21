// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.utils.android.log;

import android.os.Process;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import jp.pixela.pxlibs.utils.*;

public abstract class LogListenerBase
{

    public LogListenerBase()
    {
        mLevel = 2;
    }

    protected String createMessage(StackTraceElement stacktraceelement, int i, String s, String s1)
    {
        String s2 = mDateFormatter.format(new Date());
        String s3 = s;
        if(StringUtility.isNullOrWhiteSpace(s))
            s3 = "No Tag";
        s = getLevelText(i);
        i = Process.myPid();
        long l = Thread.currentThread().getId();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append((String)Utility.nvl(s1, ""));
        stacktraceelement = MethodHelper.getLocation(stacktraceelement);
        if(!StringUtility.isNullOrWhiteSpace(stacktraceelement))
        {
            if(stringbuilder.length() > 0)
                stringbuilder.append("  ");
            stringbuilder.append("(");
            stringbuilder.append(stacktraceelement);
            stringbuilder.append(")");
        }
        return String.format("%1$s    %2$s    %3$-7s    PID=%4$-5d    TID=%5$-4d    %6$s", new Object[] {
            s2, s3, s, Integer.valueOf(i), Long.valueOf(l), stringbuilder.toString()
        });
    }

    void flush()
        throws Exception
    {
        flushMain();
    }

    protected void flushMain()
        throws Exception
    {
    }

    protected final String getLevelText(int i)
    {
        String s;
        switch(i)
        {
        default:
            s = "Other";
            break;

        case 6: // '\006'
            s = "Error";
            break;

        case 5: // '\005'
            s = "Warn";
            break;

        case 4: // '\004'
            s = "Info";
            break;

        case 3: // '\003'
            s = "Debug";
            break;

        case 2: // '\002'
            s = "Verbose";
            break;
        }
        return s;
    }

    protected final String getTag()
    {
        return mTag;
    }

    final void release()
    {
        try
        {
            releaseMain();
        }
        catch(Exception exception)
        {
            Log.w(getTag(), exception);
        }
    }

    protected void releaseMain()
        throws Exception
    {
    }

    public final void setLevel(int i)
    {
        mLevel = i;
    }

    final boolean write(StackTraceElement stacktraceelement, int i, String s, String s1)
        throws Exception
    {
        if(i < mLevel)
            return false;
        else
            return writeMain(createMessage(stacktraceelement, i, s, s1));
    }

    protected abstract boolean writeMain(String s)
        throws Exception;

    private final SimpleDateFormat mDateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS", Locale.getDefault());
    private int mLevel;
    private final String mTag = getClass().getSimpleName();
}
