// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import jp.pixela.common.PxLog;

public class TvPlayerService extends Service
{
    private class TvPlayerHandler extends Handler
    {

        public void handleMessage(Message message)
        {
        }

        final TvPlayerService this$0;

        private TvPlayerHandler()
        {
            this$0 = TvPlayerService.this;
            super();
        }

    }


    public TvPlayerService()
    {
        mServiceMessenger = null;
    }

    public IBinder onBind(Intent intent)
    {
        PxLog.d("TvPlayerService", "onBind");
        if(mServiceMessenger == null)
        {
            PxLog.w("TvPlayerService", "Messenger is null.");
            return null;
        } else
        {
            return mServiceMessenger.getBinder();
        }
    }

    public void onCreate()
    {
        PxLog.d("TvPlayerService", "onCreate");
        super.onCreate();
        mServiceMessenger = new Messenger(new TvPlayerHandler());
    }

    public void onDestroy()
    {
        PxLog.d("TvPlayerService", "onDestroy");
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int j)
    {
        PxLog.d("TvPlayerService", "onStartCommand");
        return 2;
    }

    private static final String TAG = "TvPlayerService";
    private Messenger mServiceMessenger;
}
