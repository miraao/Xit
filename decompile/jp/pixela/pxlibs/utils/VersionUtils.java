// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.utils;

import android.content.Context;
import android.os.Build;
import jp.pixela.pxlibs.utils.android.SystemPropertiesProxy;
import jp.pixela.pxlibs.utils.android.log.Logger;

public class VersionUtils
{

    public VersionUtils()
    {
    }

    public static String getSystemVer(Context context)
    {
        try
        {
            context = SystemPropertiesProxy.get(context, "jp.pixela.system.version.system");
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(LOG_HEAD);
            stringbuilder.append("getSystemVer error:");
            stringbuilder.append(context.getMessage());
            Logger.e(stringbuilder.toString(), new Object[0]);
            context = "";
        }
        return context;
    }

    public static String getVendorVer(Context context)
    {
        try
        {
            context = SystemPropertiesProxy.get(context, "jp.pixela.system.version.vendor");
        }
        catch(Exception exception)
        {
            context = new StringBuilder();
            context.append(LOG_HEAD);
            context.append("getVendorVer error:");
            context.append(exception.getMessage());
            Logger.e(context.toString(), new Object[0]);
            context = "";
        }
        return context;
    }

    public static String getVersion(Context context)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getSystemVer(context));
        stringbuilder.append(".");
        stringbuilder.append(getVendorVer(context));
        stringbuilder.append(".");
        stringbuilder.append(Build.TIME / 1000L);
        return stringbuilder.toString();
    }

    private static final String LOG_HEAD;
    private static final String PROPERTY_KEY_VERSION_SYSTEM = "jp.pixela.system.version.system";
    private static final String PROPERTY_KEY_VERSION_VENDOR = "jp.pixela.system.version.vendor";

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/pxlibs/utils/VersionUtils.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }
}
