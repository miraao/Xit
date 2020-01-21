// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;

import android.content.*;
import jp.pixela.common.PxLog;

public class AirTunerEventReceiver extends BroadcastReceiver
{
    public static interface AirTunerEventReceiverInterface
    {

        public abstract void onTunerStarted();

        public abstract void onTunerStopped(int i);
    }


    public AirTunerEventReceiver(AirTunerEventReceiverInterface airtunereventreceiverinterface)
    {
        mInterface = null;
        mInterface = airtunereventreceiverinterface;
    }

    public void onReceive(Context context, Intent intent)
    {
        int i = intent.getIntExtra("event", -1);
        context = new StringBuilder();
        context.append("onReceive command:");
        context.append(i);
        PxLog.d("AirTunerEventReceiver", context.toString());
        switch(i)
        {
        default:
            break;

        case 1: // '\001'
            int j = intent.getIntExtra("reason", -1);
            context = new StringBuilder();
            context.append("onReceive ACTION_AIRTUNER_NOTIFY_SERVICE_STOPPED reason:");
            context.append(i);
            PxLog.d("AirTunerEventReceiver", context.toString());
            if(mInterface != null)
                mInterface.onTunerStopped(j);
            break;

        case 0: // '\0'
            PxLog.d("AirTunerEventReceiver", "onReceive ACTION_AIRTUNER_NOTIFY_SERVICE_STARTED");
            if(mInterface != null)
                mInterface.onTunerStarted();
            break;
        }
    }

    public static final String ACTION_AIRTUNER_NOTIFY = "jp.pixela.airtunerjni.AIRTUNER_NOTIFY";
    private static final String ACTION_AIRTUNER_NOTIFY_COMMAND = "event";
    private static final String ACTION_AIRTUNER_NOTIFY_REASON = "reason";
    private static final int ACTION_AIRTUNER_NOTIFY_SERVICE_FAILED = 2;
    private static final int ACTION_AIRTUNER_NOTIFY_SERVICE_STARTED = 0;
    private static final int ACTION_AIRTUNER_NOTIFY_SERVICE_STOPPED = 1;
    public static final int ACTION_AIRTUNER_NOTIFY_SERVICE_STOP_REASON_API = 0;
    public static final int ACTION_AIRTUNER_NOTIFY_SERVICE_STOP_REASON_CRASH = 2;
    public static final int ACTION_AIRTUNER_NOTIFY_SERVICE_STOP_REASON_TUNER_DETACH = 1;
    private static final String TAG = "AirTunerEventReceiver";
    AirTunerEventReceiverInterface mInterface;
}
