// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.utils;


public final class MethodHelper
{

    private MethodHelper()
    {
    }

    public static final String getLocation(StackTraceElement stacktraceelement)
    {
        return getLocation(stacktraceelement, false);
    }

    public static final String getLocation(StackTraceElement stacktraceelement, boolean flag)
    {
        if(stacktraceelement == null)
            return null;
        if(flag)
            return stacktraceelement.getMethodName();
        StringBuilder stringbuilder = new StringBuilder();
        String s = stacktraceelement.getClassName();
        int i = s.lastIndexOf('.');
        if(i >= 0)
            s = s.substring(i + 1);
        String s1 = stacktraceelement.getFileName();
        String s2 = stacktraceelement.getMethodName();
        i = stacktraceelement.getLineNumber();
        stringbuilder.append(s);
        stringbuilder.append("#");
        stringbuilder.append(s2);
        stringbuilder.append(" - ");
        stringbuilder.append(s1);
        stringbuilder.append(":");
        stringbuilder.append(String.valueOf(i));
        return stringbuilder.toString();
    }

    public static final StackTraceElement getStackTrace(int i)
    {
        if(i < 0)
            return null;
        StackTraceElement astacktraceelement[] = (new Throwable()).getStackTrace();
        if(astacktraceelement == null)
            return null;
        if(++i >= astacktraceelement.length)
            return null;
        else
            return astacktraceelement[i];
    }
}
