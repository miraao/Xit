// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.common;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package jp.pixela.common:
//            PxLog

public class ApplicationUtility
{

    public ApplicationUtility()
    {
    }

    public static boolean isAppRunning(Context context)
    {
        return isAppRunning(context, false);
    }

    private static boolean isAppRunning(Context context, boolean flag)
    {
        Object obj = context.getPackageName();
        Iterator iterator = ((ActivityManager)context.getSystemService("activity")).getRunningAppProcesses().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            context = (android.app.ActivityManager.RunningAppProcessInfo)iterator.next();
            if(!((String) (obj)).equals(((android.app.ActivityManager.RunningAppProcessInfo) (context)).processName))
                continue;
            obj = new StringBuilder();
            ((StringBuilder) (obj)).append(((android.app.ActivityManager.RunningAppProcessInfo) (context)).processName);
            ((StringBuilder) (obj)).append(" importance:");
            ((StringBuilder) (obj)).append(((android.app.ActivityManager.RunningAppProcessInfo) (context)).importance);
            PxLog.v("ApplicationUtility", ((StringBuilder) (obj)).toString());
            boolean flag1;
            if(flag ? ((android.app.ActivityManager.RunningAppProcessInfo) (context)).importance == 100 : ((android.app.ActivityManager.RunningAppProcessInfo) (context)).importance == 230 || ((android.app.ActivityManager.RunningAppProcessInfo) (context)).importance == 130)
                flag1 = true;
            else
                flag1 = false;
            if(flag1)
                return true;
            break;
        } while(true);
        return false;
    }

    public static boolean isAppRunningForeground(Context context)
    {
        return isAppRunning(context, true);
    }

    public static boolean isServiceRunning(Context context, String s)
    {
        for(context = ((ActivityManager)context.getSystemService("activity")).getRunningServices(0x7fffffff).iterator(); context.hasNext();)
            if(((android.app.ActivityManager.RunningServiceInfo)context.next()).service.getClassName().equals(s))
                return true;

        return false;
    }

    private static final String TAG = "ApplicationUtility";
}
