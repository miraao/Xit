// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.co.pixela.pis_iot_edge;

import android.content.*;
import jp.co.pixela.pis_iot_edge.common.IntentHelper;
import jp.co.pixela.pis_iot_edge.common.PxLog;

// Referenced classes of package jp.co.pixela.pis_iot_edge:
//            IoTEdgeService

public class StartupReceiver extends BroadcastReceiver
{

    public StartupReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("onReceive intent=");
        stringbuilder.append(IntentHelper.dump(intent));
        PxLog.d(s, stringbuilder.toString());
        intent = intent.getAction();
        if(!intent.equals("android.intent.action.BOOT_COMPLETED") && !intent.equals("android.intent.action.MY_PACKAGE_REPLACED") && !intent.equals("jp.pixela.system.pxhwaccess.SCREEN_ON"))
        {
            PxLog.w(TAG, "Not matched.");
            return;
        }
        intent = new Intent(context, jp/co/pixela/pis_iot_edge/IoTEdgeService);
        if(android.os.Build.VERSION.SDK_INT < 26)
            context.startService(intent);
        else
            context.startForegroundService(intent);
        s = TAG;
        context = new StringBuilder();
        context.append("start IoTEdgeService intent=");
        context.append(IntentHelper.dump(intent));
        PxLog.v(s, context.toString());
    }

    static final boolean $assertionsDisabled = false;
    private static final String ACTION_HWSERVICE_SCREEN_ON = "jp.pixela.system.pxhwaccess.SCREEN_ON";
    private static final String TAG = "StartupReceiver";

}
