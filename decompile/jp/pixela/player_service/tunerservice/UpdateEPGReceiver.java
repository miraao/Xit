// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;

import android.content.*;

// Referenced classes of package jp.pixela.player_service.tunerservice:
//            ControlInterface

public class UpdateEPGReceiver extends BroadcastReceiver
{

    public UpdateEPGReceiver(ControlInterface controlinterface)
    {
        mControlInterface = null;
        mControlInterface = controlinterface;
    }

    public void onReceive(Context context, Intent intent)
    {
        if(intent.getAction() != "ACTION_DB_UPDATER_UPDATE_EPG")
            return;
        if(mControlInterface != null)
            mControlInterface.doUpdateCacheWithTarget(0);
    }

    private static final String TAG = "UpdateEPGReceiver";
    private ControlInterface mControlInterface;

}
