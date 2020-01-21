// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;

import android.content.*;
import java.util.concurrent.atomic.AtomicBoolean;
import jp.pixela.common.PxLog;
import jp.pixela.pxlibs.utils.android.log.LoggerRTM;

public class LNBSettings extends BroadcastReceiver
{

    public LNBSettings(Context context)
    {
        mContext = null;
        mResultRequestLNBEnabled = 0;
        mIsOnReceiveCalled = new AtomicBoolean(false);
        mContext = context;
    }

    public void onReceive(Context context, Intent intent)
    {
        context = new StringBuilder();
        context.append("onReceiver : ");
        context.append(intent.getAction());
        PxLog.d("LNBSettings", context.toString());
        mIsOnReceiveCalled.set(true);
        mResultRequestLNBEnabled = getResultCode();
    }

    public boolean requestLNBEnabled()
    {
        PxLog.d("LNBSettings", "requestLNBEnabled in");
        Intent intent = new Intent("jp.pixela.system.pxhwaccess.LNB_GET");
        AtomicBoolean atomicboolean = mIsOnReceiveCalled;
        boolean flag = false;
        atomicboolean.set(false);
        mContext.sendOrderedBroadcast(intent, null, this, null, 0, null, null);
        for(int i = 0; !mIsOnReceiveCalled.get() && i < 50;)
        {
            i++;
            try
            {
                Thread.sleep(10L);
                StringBuilder stringbuilder = JVM INSTR new #25  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                stringbuilder.append("requestLNBEnabled retryCount:");
                stringbuilder.append(i);
                PxLog.d("LNBSettings", stringbuilder.toString());
            }
            catch(InterruptedException interruptedexception)
            {
                StringBuilder stringbuilder1 = new StringBuilder();
                stringbuilder1.append(LOG_HEAD);
                stringbuilder1.append("e=");
                stringbuilder1.append(interruptedexception);
                LoggerRTM.e(stringbuilder1.toString(), new Object[0]);
            }
        }

        if(mResultRequestLNBEnabled != 0)
            flag = true;
        return flag;
    }

    public void sendEnabledLNB(boolean flag)
    {
        if(mContext == null)
        {
            return;
        } else
        {
            Intent intent = new Intent("jp.pixela.system.pxhwaccess.LNB_SET");
            intent.putExtra("value", flag);
            mContext.sendBroadcast(intent);
            return;
        }
    }

    private static final String ACTION_LNB_GET = "jp.pixela.system.pxhwaccess.LNB_GET";
    private static final String ACTION_LNB_SET = "jp.pixela.system.pxhwaccess.LNB_SET";
    private static final String LOG_HEAD;
    private static final String TAG = "LNBSettings";
    Context mContext;
    AtomicBoolean mIsOnReceiveCalled;
    int mResultRequestLNBEnabled;

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/player_service/tunerservice/LNBSettings.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }
}
