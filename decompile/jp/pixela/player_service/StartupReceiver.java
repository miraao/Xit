// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service;

import android.content.*;
import jp.pixela.common.PxLog;

// Referenced classes of package jp.pixela.player_service:
//            TvTunerService

public class StartupReceiver extends BroadcastReceiver
{

    public StartupReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        intent = new StringBuilder();
        intent.append("onReceive call: this=");
        intent.append(this);
        PxLog.d("StartupReceiver", intent.toString());
        intent = new Intent(context, jp/pixela/player_service/TvTunerService);
        intent.setFlags(0x10000000);
        context.startActivity(intent);
    }

    private static final String TAG = "StartupReceiver";
}
