// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.searchable_program;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtraDataHelper
{

    public ExtraDataHelper()
    {
    }

    public static int getBroadcastType(String s)
    {
        s = Pattern.compile("broadcastType=(\\d+)").matcher(s);
        if(s.find())
            return Integer.parseInt(s.group(1));
        else
            return 0;
    }

    static String getExtraString(long l, long l1)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("broadcastType=");
        stringbuilder.append(l);
        stringbuilder.append("&serviceId=");
        stringbuilder.append(l1);
        return stringbuilder.toString();
    }

    public static int getServiceId(String s)
    {
        s = Pattern.compile("serviceId=(\\d+)").matcher(s);
        if(s.find())
            return Integer.parseInt(s.group(1));
        else
            return 0;
    }
}
