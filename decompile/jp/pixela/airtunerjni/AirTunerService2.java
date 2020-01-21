// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.airtunerjni;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.util.Log;

// Referenced classes of package jp.pixela.airtunerjni:
//            AirTunerServiceInterface, AirTunerJni

public class AirTunerService2 extends Service
    implements AirTunerServiceInterface
{

    public AirTunerService2()
    {
    }

    public Context getContext()
    {
        return this;
    }

    public void notifyStopAirTuner(final int retCode, boolean flag)
    {
        _handler.post(new Runnable() {

            public void run()
            {
                _atJni.stopNt();
                System.exit(retCode);
            }

            final AirTunerService2 this$0;
            final int val$retCode;

            
            {
                this$0 = AirTunerService2.this;
                retCode = i;
                super();
            }
        }
);
    }

    public IBinder onBind(Intent intent)
    {
        return new Binder() {

            protected boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            {
                switch(i)
                {
                default:
                    parcel = new StringBuilder();
                    parcel.append("Unknown code=");
                    parcel.append(i);
                    Log.e("AirTunerService2", parcel.toString());
                    break;

                case 1000: 
                    (new Thread(new Runnable() {

                        public void run()
                        {
                            startAirTuner();
                        }

                        final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
                    }
)).start();
                    break;

                case 1001: 
                    break;
                }
                return true;
            }

            final AirTunerService2 this$0;

            
            {
                this$0 = AirTunerService2.this;
                super();
            }
        }
;
    }

    public void onCreate()
    {
        super.onCreate();
        _handler = new Handler();
        _atJni = new AirTunerJni(this);
    }

    public void onDestroy()
    {
        Log.d("AirTunerService2", "AirTunerService2.onDestroy() is invoked.");
        super.onDestroy();
        notifyStopAirTuner(0, false);
    }

    public boolean onUnbind(Intent intent)
    {
        Log.d("AirTunerService2", "AirTunerService2.onUnbind() is invoked.");
        return false;
    }

    public void restartPxDMSDaemon()
    {
    }

    public int restartPxjfClient(String s, int i, String s1, String s2, String s3, int j, String s4, 
            String s5, String s6, int k, int l, int i1, int j1, String s7)
    {
        return 0;
    }

    public void sendCommandToPxjfClient(String s)
    {
    }

    public void startAirTuner()
    {
        Object obj = new Intent("jp.pixela.airtunerjni.AIRTUNER2_NOTIFY");
        Object obj1 = new StringBuilder();
        ((StringBuilder) (obj1)).append("--aservice --procnum 1 --extdir=");
        ((StringBuilder) (obj1)).append(getApplicationContext().getFilesDir());
        obj1 = ((StringBuilder) (obj1)).toString();
        int i = _atJni.startNt(((String) (obj1)));
        if(i != 0)
        {
            obj = new StringBuilder();
            ((StringBuilder) (obj)).append("AirTunerJni.startNt() failed:ret=");
            ((StringBuilder) (obj)).append(i);
            Log.e("AirTunerService2", ((StringBuilder) (obj)).toString());
            obj = null;
            notifyStopAirTuner(i, true);
        } else
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("AirTunerJni.startNt() succeeded:ret=");
            stringbuilder.append(i);
            Log.d("AirTunerService2", stringbuilder.toString());
            ((Intent) (obj)).putExtra("event", 0);
        }
        if(obj != null)
            sendBroadcast(((Intent) (obj)));
    }

    private static final String TAG = "AirTunerService2";
    private AirTunerJni _atJni;
    private Handler _handler;

}
