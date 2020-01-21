// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.airtunerjni;

import android.app.Service;
import android.content.*;
import android.os.*;
import android.util.Log;

// Referenced classes of package jp.pixela.airtunerjni:
//            PxDMSDaemonJni

public class PxDMSDaemonService extends Service
{

    public PxDMSDaemonService()
    {
        _dms = new PxDMSDaemonJni();
        _lockDms = new Object();
        notifyReceiver_ = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent)
            {
                if("jp.pixela.airtunerjni.PXDMSDAEMON_NOTIFY".equals(intent.getAction()) && intent.getIntExtra("event", -1) == 3)
                    synchronized(_lockDms)
                    {
                        if(_dms != null)
                        {
                            _dms.stopPxDMSNt();
                            System.exit(0);
                        }
                    }
                break MISSING_BLOCK_LABEL_71;
                context;
                intent;
                JVM INSTR monitorexit ;
                throw context;
            }

            final PxDMSDaemonService this$0;

            
            {
                this$0 = PxDMSDaemonService.this;
                super();
            }
        }
;
    }

    public IBinder onBind(Intent intent)
    {
        return new Binder() {

            protected boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            {
label0:
                {
                    synchronized(_lockDms)
                    {
                        if(_dms != null)
                            break label0;
                        Log.e("PxDMSDaemonService", "PxDMSService transact() is called with no service instance.");
                        parcel1.writeInt(-1000);
                    }
                    return true;
                }
                i;
                JVM INSTR tableswitch 2000 2001: default 64
            //                           2000 152
            //                           2001 67;
                   goto _L1 _L2 _L3
_L3:
                i = _dms.stopPxDMSNt();
                if(i == 0) goto _L5; else goto _L4
_L4:
                StringBuilder stringbuilder = JVM INSTR new #53  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                stringbuilder.append("PxDMSDaemonJni.stopPxDMSNt() failed:ret=");
                stringbuilder.append(i);
                Log.e("PxDMSDaemonService", stringbuilder.toString());
_L5:
                parcel1.writeInt(i);
                parcel1 = JVM INSTR new #69  <Class Intent>;
                parcel1.Intent("jp.pixela.airtunerjni.PXDMSDAEMON_NOTIFY");
                parcel1.putExtra("event", 1);
                sendBroadcast(parcel1);
                break MISSING_BLOCK_LABEL_338;
_L2:
                PxDMSDaemonJni pxdmsdaemonjni = _dms;
                StringBuilder stringbuilder3 = JVM INSTR new #53  <Class StringBuilder>;
                stringbuilder3.StringBuilder();
                stringbuilder3.append("--aservice --atprocnum 1 --extdir=");
                stringbuilder3.append(getApplicationContext().getFilesDir());
                i = pxdmsdaemonjni.startPxDMSNt(stringbuilder3.toString());
                if(i == 0) goto _L7; else goto _L6
_L6:
                StringBuilder stringbuilder1 = JVM INSTR new #53  <Class StringBuilder>;
                stringbuilder1.StringBuilder();
                stringbuilder1.append("PxDMSDaemonJni.startPxDMSNt() failed:ret=");
                stringbuilder1.append(i);
                Log.e("PxDMSDaemonService", stringbuilder1.toString());
_L7:
                parcel1.writeInt(i);
                parcel1 = JVM INSTR new #69  <Class Intent>;
                parcel1.Intent("jp.pixela.airtunerjni.PXDMSDAEMON_NOTIFY");
                if(i != 0) goto _L9; else goto _L8
_L8:
                parcel1.putExtra("event", 0);
                  goto _L10
_L9:
                parcel1.putExtra("event", 2);
_L10:
                sendBroadcast(parcel1);
                break MISSING_BLOCK_LABEL_338;
_L1:
                StringBuilder stringbuilder2 = JVM INSTR new #53  <Class StringBuilder>;
                stringbuilder2.StringBuilder();
                stringbuilder2.append("Unknown code=");
                stringbuilder2.append(i);
                Log.e("PxDMSDaemonService", stringbuilder2.toString());
                parcel1.writeInt(-2000);
                parcel;
                JVM INSTR monitorexit ;
                return true;
                parcel1;
                parcel;
                JVM INSTR monitorexit ;
                throw parcel1;
            }

            final PxDMSDaemonService this$0;

            
            {
                this$0 = PxDMSDaemonService.this;
                super();
            }
        }
;
    }

    public void onCreate()
    {
        super.onCreate();
        synchronized(_lockDms)
        {
            if(_dms != null)
                _dms.stopPxDMSNt();
            PxDMSDaemonJni pxdmsdaemonjni = JVM INSTR new #39  <Class PxDMSDaemonJni>;
            pxdmsdaemonjni.PxDMSDaemonJni();
            _dms = pxdmsdaemonjni;
        }
        registerReceiver(notifyReceiver_, new IntentFilter("jp.pixela.airtunerjni.PXDMSDAEMON_NOTIFY"));
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void onDestroy()
    {
        super.onDestroy();
        synchronized(_lockDms)
        {
            _dms = null;
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static final int PXDMSDAEMON_SERVICE_START = 2000;
    public static final int PXDMSDAEMON_SERVICE_STOP = 2001;
    public static final String TAG = "PxDMSDaemonService";
    private PxDMSDaemonJni _dms;
    private Object _lockDms;
    private BroadcastReceiver notifyReceiver_;

    static 
    {
        System.loadLibrary("PxDMSDaemon");
    }


}
