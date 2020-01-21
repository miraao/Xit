// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.airtunerjni;

import android.content.*;
import android.util.Log;

// Referenced classes of package jp.pixela.airtunerjni:
//            ProcessWatchingService

public class StartupReceiver extends BroadcastReceiver
{

    public StartupReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        Log.d("AirTunerStartupReceiver", "onReceive");
        if(context.startService(new Intent(context, jp/pixela/airtunerjni/ProcessWatchingService)) == null)
            Log.e("AirTunerStartupReceiver", "Failed to start ProcessWatchingService.");
        else
            Log.e("AirTunerStartupReceiver", "Succeeded to start ProcessWatchingService.");
    }

    private static final String TAG = "AirTunerStartupReceiver";
}
