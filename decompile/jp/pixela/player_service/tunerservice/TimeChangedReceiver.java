// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;

import android.content.*;
import jp.pixela.common.PxLog;

// Referenced classes of package jp.pixela.player_service.tunerservice:
//            ControlInterface

public class TimeChangedReceiver extends BroadcastReceiver
{

    public TimeChangedReceiver(ControlInterface controlinterface)
    {
        mControlInterface = null;
        mControlInterface = controlinterface;
    }

    public void onReceive(Context context, Intent intent)
    {
        if(intent != null && context != null)
        {
            if(mControlInterface == null)
                return;
            if(intent.getAction().equals("android.intent.action.TIME_SET"))
                mControlInterface.notifyUpdateSystemTime();
            return;
        } else
        {
            PxLog.w(TAG, "Intent or Context is null.");
            return;
        }
    }

    private static String TAG = "TimeChangedReceiver";
    private ControlInterface mControlInterface;

}
