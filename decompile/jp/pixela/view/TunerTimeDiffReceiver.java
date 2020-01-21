// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.view;

import android.content.*;
import jp.pixela.common.PxLog;

public class TunerTimeDiffReceiver extends BroadcastReceiver
{
    public static interface OnTunerTimeDiffListener
    {

        public abstract void onReceiveGetTunerTimeDiff(TunerTimeDiffReceiver tunertimediffreceiver);
    }


    public TunerTimeDiffReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        PxLog.v(TAG, "in.");
        if(intent == null)
        {
            PxLog.d(TAG, "intent is null.");
            return;
        }
        context = intent.getAction();
        String s = TAG;
        intent = new StringBuilder();
        intent.append("action=");
        intent.append(context);
        PxLog.d(s, intent.toString());
        if("jp.pixela.stationtv.xit.action.GET_TUNER_TIME_DIFF".equals(context) && mListener != null)
            mListener.onReceiveGetTunerTimeDiff(this);
    }

    public void setOnTunerTimeDiffListener(OnTunerTimeDiffListener ontunertimedifflistener)
    {
        mListener = ontunertimedifflistener;
    }

    public static final String ACTION_GET_TUNER_TIME_DIFF = "jp.pixela.stationtv.xit.action.GET_TUNER_TIME_DIFF";
    private static final String TAG = "TunerTimeDiffReceiver";
    private OnTunerTimeDiffListener mListener;

}
