// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.view;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import jp.pixela.common.PxLog;
import jp.pixela.player_service.tunerservice.*;

// Referenced classes of package jp.pixela.view:
//            TunerTimeDiffReceiver

public class TunerTimeDiffService
    implements TunerTimeDiffReceiver.OnTunerTimeDiffListener
{

    public TunerTimeDiffService(Context context)
    {
        mContext = context;
    }

    private void stopSelf()
    {
        if(mReceiver != null)
        {
            mContext.unregisterReceiver(mReceiver);
            mReceiver = null;
        }
    }

    public void onReceiveGetTunerTimeDiff(TunerTimeDiffReceiver tunertimediffreceiver)
    {
        PxLog.v(TAG, "in.");
        if(tunertimediffreceiver == null)
        {
            PxLog.d(TAG, "receiver is null.");
            return;
        }
        byte byte0 = -1;
        long l = 0L;
        byte byte1;
        long l1;
        if(mTunerService != null)
        {
            Object obj = mTunerService.getTunerTimeDiff();
            byte1 = byte0;
            l1 = l;
            if(obj != null)
            {
                obj = (TunerTimeDiffInfo)((ReturnValue) (obj)).getObject();
                byte1 = byte0;
                l1 = l;
                if(obj != null)
                {
                    l1 = ((TunerTimeDiffInfo) (obj)).getTunerTimeDiff();
                    byte1 = 0;
                }
            }
        } else
        {
            PxLog.d(TAG, "tuner service is null.");
            l1 = l;
            byte1 = byte0;
        }
        tunertimediffreceiver.setResultCode(tunertimediffreceiver.getResultCode());
        Bundle bundle = tunertimediffreceiver.getResultExtras(true);
        bundle.putInt("result", byte1);
        bundle.putLong("time_diff", l1);
        tunertimediffreceiver.setResultExtras(bundle);
    }

    public void start(TunerService tunerservice)
    {
        PxLog.v(TAG, "in.");
        stopSelf();
        mTunerService = tunerservice;
        mReceiver = new TunerTimeDiffReceiver();
        mReceiver.setOnTunerTimeDiffListener(this);
        tunerservice = new IntentFilter();
        tunerservice.addAction("jp.pixela.stationtv.xit.action.GET_TUNER_TIME_DIFF");
        mContext.registerReceiver(mReceiver, tunerservice);
    }

    public void stop()
    {
        PxLog.v(TAG, "in.");
        stopSelf();
    }

    private static final String TAG = "TunerTimeDiffService";
    private Context mContext;
    private TunerTimeDiffReceiver mReceiver;
    private TunerService mTunerService;

}
