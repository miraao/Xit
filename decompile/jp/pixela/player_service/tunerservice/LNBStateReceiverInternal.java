// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;

import android.content.*;
import jp.pixela.common.PxLog;

public class LNBStateReceiverInternal extends BroadcastReceiver
{
    public static interface LNBStateReceiverInterface
    {

        public abstract void onLNBShortOccur();
    }


    public LNBStateReceiverInternal(LNBStateReceiverInterface lnbstatereceiverinterface)
    {
        mInterface = null;
        mInterface = lnbstatereceiverinterface;
    }

    public void onReceive(Context context, Intent intent)
    {
        if(intent != null && context != null)
        {
            context = intent.getAction();
            if(mInterface == null)
                return;
            if(context.equals("jp.pixela.player_servic.LNB_SHORT_OCCUR_INTERNAL"))
                mInterface.onLNBShortOccur();
            return;
        } else
        {
            PxLog.w("LNBStateReceiverInternal", "Intent or Context is null.");
            return;
        }
    }

    private static final String TAG = "LNBStateReceiverInternal";
    LNBStateReceiverInterface mInterface;
}
