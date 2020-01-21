// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;

import android.content.*;
import android.preference.PreferenceManager;
import jp.pixela.common.ApplicationUtility;
import jp.pixela.common.PxLog;
import jp.pixela.player_service.App;
import jp.pixela.player_service.TvTunerService;

public class LNBStateReceiver extends BroadcastReceiver
{

    public LNBStateReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        if(intent != null && context != null)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("onReceive:avtion=");
            stringbuilder.append(intent.getAction());
            PxLog.d("LNBStateReceiver", stringbuilder.toString());
            if(TvTunerService.isInitialSettingsState(context))
            {
                PxLog.i("LNBStateReceiver", "isInitialSettingsState.");
                return;
            }
            intent = intent.getAction();
            if(intent == null)
            {
                PxLog.i("LNBStateReceiver", "null action.");
                return;
            }
            if(!intent.equals("jp.pixela.system.pxhwservice.LNB_SHORT_OCCUR"))
                return;
            if(ApplicationUtility.isAppRunningForeground(context))
            {
                intent = context.getApplicationContext();
                if((intent instanceof App) && ((App)intent).isRunning("jp.pixela.atv_app.MainActivity"))
                {
                    PxLog.i("LNBStateReceiver", "jp.pixela.atv_app.MainActivity is foreground.");
                    context.sendBroadcast(new Intent("jp.pixela.player_servic.LNB_SHORT_OCCUR_INTERNAL"));
                    return;
                }
            }
            PxLog.i("LNBStateReceiver", "save LNB short occur.");
            PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit().putBoolean("isLNBShortOccur", true).commit();
            return;
        } else
        {
            PxLog.w("LNBStateReceiver", "Intent or Context is null.");
            return;
        }
    }

    public static final String INTENT_NAME_LNB_SHORT_OCCUR_INTERNAL = "jp.pixela.player_servic.LNB_SHORT_OCCUR_INTERNAL";
    public static final String IS_LNB_SHORT_OCCUR = "isLNBShortOccur";
    private static final String TAG = "LNBStateReceiver";
}
