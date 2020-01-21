// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.utils.android.log;

import android.util.Log;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.*;
import jp.pixela.pxlibs.utils.MethodHelper;
import jp.pixela.pxlibs.utils.StringUtility;

// Referenced classes of package jp.pixela.pxlibs.utils.android.log:
//            LogListenerBase, Logger

public final class LoggerRTM
{

    private LoggerRTM()
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
        StackTraceElement stacktraceelement;
        if(sIsLocation.get())
            stacktraceelement = MethodHelper.getStackTrace(1);
        else
            stacktraceelement = null;
        return println(stacktraceelement, 3, null, s, aobj);
    }

    public static final int d(Throwable throwable)
    {
        if(!isLoggable(3))
            return 0;
        StackTraceElement stacktraceelement;
        if(sIsLocation.get())
            stacktraceelement = MethodHelper.getStackTrace(1);
        else
            stacktraceelement = null;
        return println(stacktraceelement, 3, throwable, null, new Object[0]);
    }

    public static final transient int d(Throwable throwable, String s, Object aobj[])
    {
        if(!isLoggable(3))
            return 0;
        StackTraceElement stacktraceelement;
        if(sIsLocation.get())
            stacktraceelement = MethodHelper.getStackTrace(1);
        else
            stacktraceelement = null;
        return println(stacktraceelement, 3, throwable, s, aobj);
    }

    public static final transient int e(String s, Object aobj[])
    {
        if(!isLoggable(6))
            return 0;
        StackTraceElement stacktraceelement;
        if(sIsLocation.get())
            stacktraceelement = MethodHelper.getStackTrace(1);
        else
            stacktraceelement = null;
        return println(stacktraceelement, 6, null, s, aobj);
    }

    public static final int e(Throwable throwable)
    {
        if(!isLoggable(6))
            return 0;
        StackTraceElement stacktraceelement;
        if(sIsLocation.get())
            stacktraceelement = MethodHelper.getStackTrace(1);
        else
            stacktraceelement = null;
        return println(stacktraceelement, 6, throwable, null, new Object[0]);
    }

    public static final transient int e(Throwable throwable, String s, Object aobj[])
    {
        if(!isLoggable(6))
            return 0;
        StackTraceElement stacktraceelement;
        if(sIsLocation.get())
            stacktraceelement = MethodHelper.getStackTrace(1);
        else
            stacktraceelement = null;
        return println(stacktraceelement, 6, throwable, s, aobj);
    }

    public static final transient int i(String s, Object aobj[])
    {
        if(!isLoggable(4))
            return 0;
        StackTraceElement stacktraceelement;
        if(sIsLocation.get())
            stacktraceelement = MethodHelper.getStackTrace(1);
        else
            stacktraceelement = null;
        return println(stacktraceelement, 4, null, s, aobj);
    }

    public static final int i(Throwable throwable)
    {
        if(!isLoggable(4))
            return 0;
        StackTraceElement stacktraceelement;
        if(sIsLocation.get())
            stacktraceelement = MethodHelper.getStackTrace(1);
        else
            stacktraceelement = null;
        return println(stacktraceelement, 4, throwable, null, new Object[0]);
    }

    public static final transient int i(Throwable throwable, String s, Object aobj[])
    {
        if(!isLoggable(4))
            return 0;
        StackTraceElement stacktraceelement;
        if(sIsLocation.get())
            stacktraceelement = MethodHelper.getStackTrace(1);
        else
            stacktraceelement = null;
        return println(stacktraceelement, 4, throwable, s, aobj);
    }

    private static final boolean isLoggable(int j)
    {
        boolean flag = sIsDebuggable.get();
        int k = sLevel.get();
        if(flag && j >= k)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static final transient int println(StackTraceElement stacktraceelement, int j, Throwable throwable, String s, Object aobj[])
    {
        boolean flag = StringUtility.isNullOrWhiteSpace(s);
        int k = 0;
        if(flag && throwable == null)
            return 0;
        String s1 = (String)sTag.get();
        Object obj = (Locale)sLocale.get();
        if(j == 6)
        {
            try
            {
                throwable = Logger.getLogMessage(stacktraceelement, throwable, ((Locale) (obj)), s, aobj);
            }
            catch(Exception exception)
            {
                Log.w(s1, stacktraceelement);
                return 0;
            }
            break MISSING_BLOCK_LABEL_78;
        }
        throwable = Logger.getLogMessage(stacktraceelement, throwable, ((Locale) (obj)), true, s, aobj);
        if(sIsLog.get())
            k = Log.println(j, s1, throwable);
        if(sListeners.size() <= 0)
            return k;
        s = ((String) (sSyncRoot));
        s;
        JVM INSTR monitorenter ;
        aobj = sListeners.values().iterator();
_L1:
        do
        {
            if(!((Iterator) (aobj)).hasNext())
                break MISSING_BLOCK_LABEL_189;
            obj = (LogListenerBase)((Iterator) (aobj)).next();
        } while(obj == null);
        ((LogListenerBase) (obj)).write(stacktraceelement, j, s1, throwable);
        ((LogListenerBase) (obj)).flush();
          goto _L1
        exception;
        Log.w(s1, exception);
          goto _L1
        s;
        JVM INSTR monitorexit ;
        return k;
        stacktraceelement;
        s;
        JVM INSTR monitorexit ;
        throw stacktraceelement;
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

    public static final void setIsLocation(boolean flag)
    {
        sIsLocation.set(flag);
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
        StackTraceElement stacktraceelement;
        if(sIsLocation.get())
            stacktraceelement = MethodHelper.getStackTrace(1);
        else
            stacktraceelement = null;
        return println(stacktraceelement, 2, null, s, aobj);
    }

    public static final int v(Throwable throwable)
    {
        if(!isLoggable(2))
            return 0;
        StackTraceElement stacktraceelement;
        if(sIsLocation.get())
            stacktraceelement = MethodHelper.getStackTrace(1);
        else
            stacktraceelement = null;
        return println(stacktraceelement, 2, throwable, null, new Object[0]);
    }

    public static final transient int v(Throwable throwable, String s, Object aobj[])
    {
        if(!isLoggable(2))
            return 0;
        StackTraceElement stacktraceelement;
        if(sIsLocation.get())
            stacktraceelement = MethodHelper.getStackTrace(1);
        else
            stacktraceelement = null;
        return println(stacktraceelement, 2, throwable, s, aobj);
    }

    public static final transient int w(String s, Object aobj[])
    {
        if(!isLoggable(5))
            return 0;
        StackTraceElement stacktraceelement;
        if(sIsLocation.get())
            stacktraceelement = MethodHelper.getStackTrace(1);
        else
            stacktraceelement = null;
        return println(stacktraceelement, 5, null, s, aobj);
    }

    public static final int w(Throwable throwable)
    {
        if(!isLoggable(5))
            return 0;
        StackTraceElement stacktraceelement;
        if(sIsLocation.get())
            stacktraceelement = MethodHelper.getStackTrace(1);
        else
            stacktraceelement = null;
        return println(stacktraceelement, 5, throwable, null, new Object[0]);
    }

    public static final transient int w(Throwable throwable, String s, Object aobj[])
    {
        if(!isLoggable(5))
            return 0;
        StackTraceElement stacktraceelement;
        if(sIsLocation.get())
            stacktraceelement = MethodHelper.getStackTrace(1);
        else
            stacktraceelement = null;
        return println(stacktraceelement, 5, throwable, s, aobj);
    }

    private static final AtomicBoolean sIsDebuggable = new AtomicBoolean(true);
    private static final AtomicBoolean sIsLocation = new AtomicBoolean(true);
    private static final AtomicBoolean sIsLog = new AtomicBoolean(true);
    private static final AtomicInteger sLevel = new AtomicInteger(2);
    private static final ConcurrentHashMap sListeners = new ConcurrentHashMap();
    private static final AtomicReference sLocale = new AtomicReference(Locale.getDefault());
    private static final Object sSyncRoot = new Object();
    private static final AtomicReference sTag = new AtomicReference("StationTV_RTM");

}
