// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.common;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class RuntimePermissionUtils
{

    public RuntimePermissionUtils()
    {
    }

    public static boolean checkPermission(Context context, String s)
    {
        if(android.os.Build.VERSION.SDK_INT < 23)
            return true;
        return ContextCompat.checkSelfPermission(context, s) == 0;
    }

    public static void requestPermission(Activity activity, String s, int i)
    {
        ActivityCompat.requestPermissions(activity, new String[] {
            s
        }, i);
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String s)
    {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, s);
    }
}
