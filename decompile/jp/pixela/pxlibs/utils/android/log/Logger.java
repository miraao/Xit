// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.utils.android.log;

import android.util.Log;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.*;
import jp.pixela.pxlibs.utils.*;

// Referenced classes of package jp.pixela.pxlibs.utils.android.log:
//            BuildMode, LogListenerBase

public final class Logger
{

    private Logger()
    {
    }

    public static final boolean addListener(LogListenerBase loglistenerbase)
    {
        boolean flag = false;
        if(loglistenerbase == null)
            return false;
        if((LogListenerBase)sListeners.putIfAbsent(loglistenerbase, loglistenerbase) == null)
            flag = true;
        return flag;
    }

    public static final transient int d(String s, Object aobj[])
    {
        if(!isLoggable(3))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 3, null, s, aobj);
    }

    public static final int d(Throwable throwable)
    {
        if(!isLoggable(3))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 3, throwable, null, new Object[0]);
    }

    public static final int d(Throwable throwable, int j, int k)
    {
        if(!isLoggable(3))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 3, throwable, j, k, null, new Object[0]);
    }

    public static final transient int d(Throwable throwable, int j, int k, String s, Object aobj[])
    {
        if(!isLoggable(3))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 3, throwable, j, k, s, aobj);
    }

    public static final transient int d(Throwable throwable, String s, Object aobj[])
    {
        if(!isLoggable(3))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 3, throwable, s, aobj);
    }

    public static final transient int e(String s, Object aobj[])
    {
        if(!isLoggable(6))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 6, null, s, aobj);
    }

    public static final int e(Throwable throwable)
    {
        if(!isLoggable(6))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 6, throwable, null, new Object[0]);
    }

    public static final int e(Throwable throwable, int j, int k)
    {
        if(!isLoggable(6))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 6, throwable, j, k, null, new Object[0]);
    }

    public static final transient int e(Throwable throwable, int j, int k, String s, Object aobj[])
    {
        if(!isLoggable(6))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 6, throwable, j, k, s, aobj);
    }

    public static final transient int e(Throwable throwable, String s, Object aobj[])
    {
        if(!isLoggable(6))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 6, throwable, s, aobj);
    }

    static final transient String getLogMessage(StackTraceElement stacktraceelement, Throwable throwable, int j, int k, Locale locale, String s, Object aobj[])
    {
        return getLogMessage(stacktraceelement, throwable, j, k, locale, false, s, aobj);
    }

    static final transient String getLogMessage(StackTraceElement stacktraceelement, Throwable throwable, int j, int k, Locale locale, boolean flag, String s, Object aobj[])
    {
        StringBuilder stringbuilder = new StringBuilder();
        Locale locale1 = (Locale)Utility.nvl(locale, Locale.getDefault());
        s = (String)Utility.nvl(s, "");
        locale = s;
        if(aobj != null)
        {
            locale = s;
            if(aobj.length > 0)
                locale = String.format(locale1, s, aobj);
        }
        stringbuilder.append(locale);
        stacktraceelement = MethodHelper.getLocation(stacktraceelement, flag);
        if(!StringUtility.isNullOrWhiteSpace(stacktraceelement))
        {
            if(stringbuilder.length() > 0)
                stringbuilder.append("  ");
            stringbuilder.append("(");
            stringbuilder.append(stacktraceelement);
            stringbuilder.append(")");
        }
        stacktraceelement = Log.getStackTraceString(throwable);
        if(!StringUtility.isNullOrWhiteSpace(stacktraceelement))
            if(j <= 0 && k <= 0)
            {
                stringbuilder.append('\n');
                stringbuilder.append(stacktraceelement);
            } else
            {
                throwable = stacktraceelement.split("\\n", -1);
                int l;
                if(j <= 0)
                    l = 0;
                else
                    l = j - 1;
                j = l;
                if(throwable.length <= l)
                    j = throwable.length - 1;
                l = throwable.length;
                l = k;
                if(k <= 0)
                    l = throwable.length;
                int i1 = j;
                k = l;
                if(throwable.length < l)
                {
                    k = throwable.length;
                    i1 = j;
                }
                for(; i1 < k; i1++)
                {
                    stacktraceelement = throwable[i1];
                    stringbuilder.append('\n');
                    stringbuilder.append(stacktraceelement);
                }

            }
        return stringbuilder.toString();
    }

    static final transient String getLogMessage(StackTraceElement stacktraceelement, Throwable throwable, Locale locale, String s, Object aobj[])
    {
        return getLogMessage(stacktraceelement, throwable, 0, 0, locale, false, s, aobj);
    }

    static final transient String getLogMessage(StackTraceElement stacktraceelement, Throwable throwable, Locale locale, boolean flag, String s, Object aobj[])
    {
        return getLogMessage(stacktraceelement, throwable, 0, 0, locale, flag, s, aobj);
    }

    public static final transient int i(String s, Object aobj[])
    {
        if(!isLoggable(4))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 4, null, s, aobj);
    }

    public static final int i(Throwable throwable)
    {
        if(!isLoggable(4))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 4, throwable, null, new Object[0]);
    }

    public static final int i(Throwable throwable, int j, int k)
    {
        if(!isLoggable(4))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 4, throwable, j, k, null, new Object[0]);
    }

    public static final transient int i(Throwable throwable, int j, int k, String s, Object aobj[])
    {
        if(!isLoggable(4))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 4, throwable, j, k, s, aobj);
    }

    public static final transient int i(Throwable throwable, String s, Object aobj[])
    {
        if(!isLoggable(4))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 4, throwable, s, aobj);
    }

    private static final boolean isLoggable(int j)
    {
        int k = sLevel.get();
        boolean flag;
        if(sIsDebugBuild && j >= k)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static final transient int println(StackTraceElement stacktraceelement, int j, Throwable throwable, int k, int l, String s, Object aobj[])
    {
        boolean flag = StringUtility.isNullOrWhiteSpace(s);
        boolean flag1 = false;
        if(flag && throwable == null)
            return 0;
        String s1 = (String)sTag.get();
        Object obj = (Locale)sLocale.get();
        Exception exception;
        try
        {
            aobj = getLogMessage(stacktraceelement, throwable, k, l, ((Locale) (obj)), s, aobj);
        }
        // Misplaced declaration of an exception variable
        catch(StackTraceElement stacktraceelement)
        {
            Log.w(s1, stacktraceelement);
            return 0;
        }
        k = ((flag1) ? 1 : 0);
        if(sIsLog.get())
            k = Log.println(j, s1, ((String) (aobj)));
        if(sListeners.size() <= 0)
            return k;
        throwable = ((Throwable) (sSyncRoot));
        throwable;
        JVM INSTR monitorenter ;
        s = sListeners.values().iterator();
_L1:
        do
        {
            if(!s.hasNext())
                break MISSING_BLOCK_LABEL_173;
            obj = (LogListenerBase)s.next();
        } while(obj == null);
        ((LogListenerBase) (obj)).write(stacktraceelement, j, s1, ((String) (aobj)));
        ((LogListenerBase) (obj)).flush();
          goto _L1
        exception;
        Log.w(s1, exception);
          goto _L1
        throwable;
        JVM INSTR monitorexit ;
        return k;
        stacktraceelement;
        throwable;
        JVM INSTR monitorexit ;
        throw stacktraceelement;
    }

    private static final transient int println(StackTraceElement stacktraceelement, int j, Throwable throwable, String s, Object aobj[])
    {
        return println(stacktraceelement, j, throwable, 0, 0, s, aobj);
    }

    public static final void release()
    {
        if(sListeners.isEmpty())
            return;
        Object obj = sSyncRoot;
        obj;
        JVM INSTR monitorenter ;
        Iterator iterator = sListeners.values().iterator();
_L1:
        LogListenerBase loglistenerbase;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_85;
            loglistenerbase = (LogListenerBase)iterator.next();
            iterator.remove();
        } while(loglistenerbase == null);
        loglistenerbase.release();
          goto _L1
        Exception exception1;
        exception1;
        Log.w((String)sTag.get(), exception1);
          goto _L1
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static final boolean removeListener(LogListenerBase loglistenerbase)
    {
        if(loglistenerbase == null)
            return false;
        else
            return sListeners.remove(loglistenerbase, loglistenerbase);
    }

    public static final void setIsDebuggable(boolean flag)
    {
        sIsDebuggable.set(flag);
    }

    public static final void setIsLog(boolean flag)
    {
        sIsLog.set(flag);
    }

    public static final void setLevel(int j)
    {
        sLevel.set(j);
    }

    public static final void setLocale(Locale locale)
    {
        sLocale.set(locale);
    }

    public static final void setTag(String s)
    {
        sTag.set(s);
    }

    public static final transient int v(String s, Object aobj[])
    {
        if(!isLoggable(2))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 2, null, s, aobj);
    }

    public static final int v(Throwable throwable)
    {
        if(!isLoggable(2))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 2, throwable, null, new Object[0]);
    }

    public static final int v(Throwable throwable, int j, int k)
    {
        if(!isLoggable(2))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 2, throwable, j, k, null, new Object[0]);
    }

    public static final transient int v(Throwable throwable, int j, int k, String s, Object aobj[])
    {
        if(!isLoggable(2))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 2, throwable, j, k, s, aobj);
    }

    public static final transient int v(Throwable throwable, String s, Object aobj[])
    {
        if(!isLoggable(2))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 2, throwable, s, aobj);
    }

    public static final transient int w(String s, Object aobj[])
    {
        if(!isLoggable(5))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 5, null, s, aobj);
    }

    public static final int w(Throwable throwable)
    {
        if(!isLoggable(5))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 5, throwable, null, new Object[0]);
    }

    public static final int w(Throwable throwable, int j, int k)
    {
        if(!isLoggable(5))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 5, throwable, j, k, null, new Object[0]);
    }

    public static final transient int w(Throwable throwable, int j, int k, String s, Object aobj[])
    {
        if(!isLoggable(5))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 5, throwable, j, k, s, aobj);
    }

    public static final transient int w(Throwable throwable, String s, Object aobj[])
    {
        if(!isLoggable(5))
            return 0;
        else
            return println(MethodHelper.getStackTrace(1), 5, throwable, s, aobj);
    }

    private static final boolean sIsDebugBuild = BuildMode.isDebugBuild();
    private static final AtomicBoolean sIsDebuggable = new AtomicBoolean(false);
    private static final AtomicBoolean sIsLog = new AtomicBoolean(true);
    private static final AtomicInteger sLevel = new AtomicInteger(2);
    private static final ConcurrentHashMap sListeners = new ConcurrentHashMap();
    private static final AtomicReference sLocale = new AtomicReference(Locale.getDefault());
    private static final Object sSyncRoot = new Object();
    private static final AtomicReference sTag = new AtomicReference("StationTV");

}
