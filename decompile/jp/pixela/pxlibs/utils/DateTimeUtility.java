// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.utils;

import java.text.SimpleDateFormat;
import java.util.*;

public final class DateTimeUtility
{

    private DateTimeUtility()
    {
    }

    public static final Calendar getNearest(Calendar calendar, int i)
    {
        return getNearest(calendar, i, true);
    }

    public static final Calendar getNearest(Calendar calendar, int i, boolean flag)
    {
        if(calendar == null)
            throw new NullPointerException("Calendar Object is null.");
        if(i >= 1 && 7 >= i)
        {
            Calendar calendar1 = GregorianCalendar.getInstance();
            calendar1.setTimeInMillis(calendar.getTimeInMillis());
            i--;
            int j = calendar.get(7) - 1;
            if(flag && j == i)
            {
                return calendar1;
            } else
            {
                calendar1.add(5, ((6 - j) + i) % 7 + 1);
                return calendar1;
            }
        } else
        {
            throw new IllegalArgumentException("Day of week is out of range.");
        }
    }

    public static String toFormatDateTime(long l)
    {
        Date date = new Date(l);
        return (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())).format(date);
    }

    public static final long truncate(long l, int i)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(l);
        return truncate(calendar, i).getTimeInMillis();
    }

    public static final Calendar truncate(Calendar calendar, int i)
    {
        if(calendar == null)
            throw new NullPointerException("Calendar Object is null.");
        calendar = (Calendar)calendar.clone();
        if(i <= 0)
            calendar.set(1, 1);
        if(i <= 1)
            calendar.set(2, 0);
        if(i <= 2)
            calendar.set(5, 1);
        if(i <= 5)
            calendar.set(11, 0);
        if(i <= 11 || i <= 10)
            calendar.set(12, 0);
        if(i <= 12)
            calendar.set(13, 0);
        if(i <= 13)
            calendar.set(14, 0);
        return calendar;
    }

    public static final Date truncate(Date date, int i)
    {
        if(date == null)
        {
            throw new NullPointerException("Date Object is null.");
        } else
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return truncate(calendar, i).getTime();
        }
    }

    public static final String NORMAL_DATETIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
}
