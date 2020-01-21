// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.airtunerjni;

import android.app.*;
import android.content.*;
import android.os.*;
import android.util.Log;
import java.io.File;

// Referenced classes of package jp.pixela.airtunerjni:
//            AirTunerService, AirTunerService2, PxDMSDaemonService

public class ProcessWatchingService extends Service
{

    public ProcessWatchingService()
    {
    }

    private void startAirTunerService()
    {
        synchronized(binderATLock_)
        {
            if(binderAT_ == null)
            {
                Intent intent = JVM INSTR new #174 <Class Intent>;
                intent.Intent(getApplicationContext(), jp/pixela/airtunerjni/AirTunerService);
                if(!bindService(intent, connAT_, 1))
                    Log.e("ProcessWatchingService", "AirTunerService bindService() failed!!");
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void startAirTunerService2()
    {
        synchronized(binderAT2Lock_)
        {
            if(binderAT2_ == null)
            {
                Intent intent = JVM INSTR new #174 <Class Intent>;
                intent.Intent(getApplicationContext(), jp/pixela/airtunerjni/AirTunerService2);
                if(!bindService(intent, connAT2_, 1))
                    Log.e("ProcessWatchingService", "AirTunerService2 bindService() failed!!");
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void startIoTEdgeService()
    {
        synchronized(binderIELock_)
        {
            if(binderIE_ == null)
            {
                Intent intent = JVM INSTR new #174 <Class Intent>;
                intent.Intent("jp.co.pixela.pis_iot_edge.intent.action.START");
                intent.setClassName(getApplicationContext(), "jp.co.pixela.pis_iot_edge.IoTEdgeService");
                if(!bindService(intent, connIE_, 1))
                    Log.e("ProcessWatchingService", "IoTEdgeService bindService() failed!!");
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void startPxDMSDaemonService()
    {
        synchronized(binderDMSLock_)
        {
            if(binderDMS_ == null)
            {
                Intent intent = JVM INSTR new #174 <Class Intent>;
                intent.Intent(getApplicationContext(), jp/pixela/airtunerjni/PxDMSDaemonService);
                if(!bindService(intent, connDMS_, 1))
                    Log.e("ProcessWatchingService", "PxDMSDaemonService bindService() failed!!");
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void stopAirTunerService()
    {
        stopAirTunerService2();
        Object obj = binderATLock_;
        obj;
        JVM INSTR monitorenter ;
        Parcel parcel;
        Parcel parcel1;
        if(binderAT_ == null)
            break MISSING_BLOCK_LABEL_59;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        try
        {
            binderAT_.transact(1001, parcel, parcel1, 0);
            break MISSING_BLOCK_LABEL_54;
        }
        catch(RemoteException remoteexception) { }
        Log.e("ProcessWatchingService", "AirTunerService.AIRTUNER_SERVICE_STOP throws exception.");
        binderAT_ = null;
        Exception exception;
        try
        {
            unbindService(connAT_);
        }
        catch(Exception exception1) { }
        obj;
        JVM INSTR monitorexit ;
        obj = new Intent("jp.pixela.airtunerjni.AIRTUNER_NOTIFY");
        ((Intent) (obj)).putExtra("serviceIndex", 0);
        ((Intent) (obj)).putExtra("event", 1);
        ((Intent) (obj)).putExtra("reason", 0);
        sendBroadcast(((Intent) (obj)));
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void stopAirTunerService2()
    {
        Object obj = binderAT2Lock_;
        obj;
        JVM INSTR monitorenter ;
        Parcel parcel;
        Parcel parcel1;
        if(binderAT2_ == null)
            break MISSING_BLOCK_LABEL_55;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        try
        {
            binderAT2_.transact(1001, parcel, parcel1, 0);
            break MISSING_BLOCK_LABEL_50;
        }
        catch(RemoteException remoteexception) { }
        Log.e("ProcessWatchingService", "AirTunerService.AIRTUNER_SERVICE_STOP throws exception.");
        binderAT2_ = null;
        Exception exception;
        try
        {
            unbindService(connAT2_);
        }
        catch(Exception exception1) { }
        obj;
        JVM INSTR monitorexit ;
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void stopIoTEdgeService()
    {
        Object obj = binderIELock_;
        obj;
        JVM INSTR monitorenter ;
        binderIE_ = null;
        Exception exception;
        try
        {
            unbindService(connIE_);
        }
        catch(Exception exception1) { }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void stopPxDMSDaemonService()
    {
        Object obj = binderDMSLock_;
        obj;
        JVM INSTR monitorenter ;
        Parcel parcel;
        Parcel parcel1;
        if(binderDMS_ == null)
            break MISSING_BLOCK_LABEL_55;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        try
        {
            binderDMS_.transact(2001, parcel, parcel1, 0);
            break MISSING_BLOCK_LABEL_50;
        }
        catch(RemoteException remoteexception) { }
        Log.e("ProcessWatchingService", "PxDMSDaemonService.PXDMSDAEMON_SERVICE_STOP throws exception.");
        binderDMS_ = null;
        Exception exception;
        try
        {
            unbindService(connDMS_);
        }
        catch(Exception exception1) { }
        obj;
        JVM INSTR monitorexit ;
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public IBinder onBind(Intent intent)
    {
        return new Binder() {

            protected boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            {
                boolean flag;
                flag = false;
                j = 0;
                i;
                JVM INSTR lookupswitch 6: default 64
            //                           1000: 177
            //                           1001: 167
            //                           2000: 157
            //                           2001: 147
            //                           5001: 107
            //                           5002: 67;
                   goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
                break; /* Loop/switch isn't completed */
_L7:
                parcel = ((Parcel) (binderDMSLock_));
                parcel;
                JVM INSTR monitorenter ;
                i = j;
                if(binderDMS_ != null)
                    i = 1;
                parcel1.writeInt(i);
                parcel;
                JVM INSTR monitorexit ;
                break; /* Loop/switch isn't completed */
                parcel1;
                parcel;
                JVM INSTR monitorexit ;
                throw parcel1;
_L6:
                parcel = ((Parcel) (binderATLock_));
                parcel;
                JVM INSTR monitorenter ;
                i = ((flag) ? 1 : 0);
                if(binderAT_ != null)
                    i = 1;
                parcel1.writeInt(i);
                parcel;
                JVM INSTR monitorexit ;
                break; /* Loop/switch isn't completed */
                parcel1;
                parcel;
                JVM INSTR monitorexit ;
                throw parcel1;
_L5:
                stopPxDMSDaemonService();
                break; /* Loop/switch isn't completed */
_L4:
                startPxDMSDaemonService();
                break; /* Loop/switch isn't completed */
_L3:
                stopAirTunerService();
                break; /* Loop/switch isn't completed */
_L2:
                startAirTunerService();
                return true;
            }

            final ProcessWatchingService this$0;

            
            {
                this$0 = ProcessWatchingService.this;
                super();
            }
        }
;
    }

    public void onCreate()
    {
        super.onCreate();
        registerReceiver(airTunerNotifyReceiver_, new IntentFilter("jp.pixela.airtunerjni.AIRTUNER_NOTIFY"));
        registerReceiver(airTuner2NotifyReceiver_, new IntentFilter("jp.pixela.airtunerjni.AIRTUNER2_NOTIFY"));
        PendingIntent pendingintent = PendingIntent.getService(this, 0, new Intent(this, jp/pixela/airtunerjni/ProcessWatchingService), 0);
        if(android.os.Build.VERSION.SDK_INT >= 26)
            ((NotificationManager)getSystemService("notification")).createNotificationChannel(new NotificationChannel("pws_ch_1", getApplicationContext().getString(R.string.notification_title_tunerservice), 3));
        Object obj;
        if(android.os.Build.VERSION.SDK_INT >= 26)
            obj = new android.app.Notification.Builder(getApplicationContext(), "pws_ch_1");
        else
            obj = new android.app.Notification.Builder(getApplicationContext());
        ((android.app.Notification.Builder) (obj)).setContentIntent(pendingintent);
        ((android.app.Notification.Builder) (obj)).setTicker("AirTuner Process Watching Ticker");
        ((android.app.Notification.Builder) (obj)).setContentTitle("AirTuner Process Watching Title");
        ((android.app.Notification.Builder) (obj)).setContentText("AirTuner Process Watching Text");
        ((android.app.Notification.Builder) (obj)).setSmallIcon(R.mipmap.ic_launcher);
        ((NotificationManager)getSystemService("notification")).notify(R.string.process_watching_service, ((android.app.Notification.Builder) (obj)).build());
        startForeground(R.string.process_watching_service, ((android.app.Notification.Builder) (obj)).build());
        obj = new StringBuilder();
        ((StringBuilder) (obj)).append(getFilesDir());
        ((StringBuilder) (obj)).append("/settings/init.dat");
        if((new File(((StringBuilder) (obj)).toString())).isFile())
        {
            Log.d("ProcessWatchingService", "required file exists, start AT service.");
            startAirTunerService();
        } else
        {
            Log.w("ProcessWatchingService", "required file does not exist, pend to start AT service.");
        }
        startIoTEdgeService();
    }

    public void onDestroy()
    {
        Log.d("ProcessWatchingService", "Enter ProcessWatchingService.onDestroy().");
        stopIoTEdgeService();
        Object obj = binderDMSLock_;
        obj;
        JVM INSTR monitorenter ;
        Parcel parcel;
        Parcel parcel1;
        if(binderDMS_ == null)
            break MISSING_BLOCK_LABEL_68;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        try
        {
            binderDMS_.transact(2001, parcel, parcel1, 0);
            break MISSING_BLOCK_LABEL_63;
        }
        catch(RemoteException remoteexception) { }
        Log.e("ProcessWatchingService", "PxDMSDaemonService.PXDMSDAEMON_SERVICE_STOP throws exception.");
        binderDMS_ = null;
        unbindService(connDMS_);
        obj;
        JVM INSTR monitorexit ;
        obj = binderAT2Lock_;
        obj;
        JVM INSTR monitorenter ;
        if(binderAT2_ == null)
            break MISSING_BLOCK_LABEL_133;
        parcel1 = Parcel.obtain();
        remoteexception = Parcel.obtain();
        try
        {
            binderAT2_.transact(1001, parcel1, remoteexception, 0);
            break MISSING_BLOCK_LABEL_128;
        }
        catch(RemoteException remoteexception1) { }
        Log.e("ProcessWatchingService", "AirTunerService.AIRTUNER_SERVICE_STOP throws exception.");
        binderAT2_ = null;
        unbindService(connAT2_);
        obj;
        JVM INSTR monitorexit ;
        obj = binderATLock_;
        obj;
        JVM INSTR monitorenter ;
        if(binderAT_ == null)
            break MISSING_BLOCK_LABEL_198;
        remoteexception1 = Parcel.obtain();
        parcel1 = Parcel.obtain();
        try
        {
            binderAT_.transact(1001, remoteexception1, parcel1, 0);
            break MISSING_BLOCK_LABEL_193;
        }
        catch(RemoteException remoteexception2) { }
        Log.e("ProcessWatchingService", "AirTunerService.AIRTUNER_SERVICE_STOP throws exception.");
        binderAT_ = null;
        unbindService(connAT_);
        obj;
        JVM INSTR monitorexit ;
        super.onDestroy();
        Log.d("ProcessWatchingService", "Leave ProcessWatchingService.onDestroy().");
        return;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    static final boolean $assertionsDisabled = false;
    public static final String ACTION_AIRTUNER2_NOTIFY = "jp.pixela.airtunerjni.AIRTUNER2_NOTIFY";
    public static final String ACTION_AIRTUNER2_NOTIFY_COMMAND = "event";
    public static final int ACTION_AIRTUNER2_NOTIFY_SERVICE_STARTED = 0;
    public static final int ACTION_AIRTUNER2_NOTIFY_SERVICE_STOPPED = 1;
    public static final String ACTION_AIRTUNER_NOTIFY = "jp.pixela.airtunerjni.AIRTUNER_NOTIFY";
    public static final String ACTION_AIRTUNER_NOTIFY_COMMAND = "event";
    public static final String ACTION_AIRTUNER_NOTIFY_REASON = "reason";
    public static final int ACTION_AIRTUNER_NOTIFY_SERVICE_FAILED = 2;
    public static final String ACTION_AIRTUNER_NOTIFY_SERVICE_INDEX = "serviceIndex";
    public static final int ACTION_AIRTUNER_NOTIFY_SERVICE_STARTED = 0;
    public static final int ACTION_AIRTUNER_NOTIFY_SERVICE_STOPPED = 1;
    public static final int ACTION_AIRTUNER_NOTIFY_SERVICE_STOP_REASON_API = 0;
    public static final int ACTION_AIRTUNER_NOTIFY_SERVICE_STOP_REASON_CRASH = 2;
    public static final int ACTION_AIRTUNER_NOTIFY_SERVICE_STOP_REASON_TUNER_DETACH = 1;
    public static final int ACTION_AIRTUNER_NOTIFY_SERVICE_WAIT_USB_ATTACH = 3;
    public static final String ACTION_PXDMSDAEMON_NOTIFY = "jp.pixela.airtunerjni.PXDMSDAEMON_NOTIFY";
    public static final String ACTION_PXDMSDAEMON_NOTIFY_COMMAND = "event";
    public static final int ACTION_PXDMSDAEMON_NOTIFY_RESTART_REQUESTED = 3;
    public static final int ACTION_PXDMSDAEMON_NOTIFY_SERVICE_FAILED = 2;
    public static final int ACTION_PXDMSDAEMON_NOTIFY_SERVICE_STARTED = 0;
    public static final int ACTION_PXDMSDAEMON_NOTIFY_SERVICE_STOPPED = 1;
    public static final String PIS_IOT_EDGE_SERVICE_ACTION = "jp.co.pixela.pis_iot_edge.intent.action.START";
    public static final String PIS_IOT_EDGE_SERVICE_NAME = "jp.co.pixela.pis_iot_edge.IoTEdgeService";
    public static final int PWS_COMMAND_IS_AIRTUNER_SERVICE_STARTED = 5001;
    public static final int PWS_COMMAND_IS_PXDMSDAEMON_SERVICE_STARTED = 5002;
    private static final String TAG = "ProcessWatchingService";
    private final BroadcastReceiver airTuner2NotifyReceiver_ = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent)
        {
            if("jp.pixela.airtunerjni.AIRTUNER2_NOTIFY".equals(intent.getAction()))
                switch(intent.getIntExtra("event", -1))
                {
                case 1: // '\001'
                    stopPxDMSDaemonService();
                    break;

                case 0: // '\0'
                    startPxDMSDaemonService();
                    break;
                }
        }

        final ProcessWatchingService this$0;

            
            {
                this$0 = ProcessWatchingService.this;
                super();
            }
    }
;
    private final BroadcastReceiver airTunerNotifyReceiver_ = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent)
        {
            if("jp.pixela.airtunerjni.AIRTUNER_NOTIFY".equals(intent.getAction()))
                switch(intent.getIntExtra("event", -1))
                {
                case 1: // '\001'
                    stopAirTunerService2();
                    break;

                case 0: // '\0'
                    startAirTunerService2();
                    break;
                }
        }

        final ProcessWatchingService this$0;

            
            {
                this$0 = ProcessWatchingService.this;
                super();
            }
    }
;
    private final Object binderAT2Lock_ = new Object();
    private IBinder binderAT2_;
    private final Object binderATLock_ = new Object();
    private IBinder binderAT_;
    private final Object binderDMSLock_ = new Object();
    private IBinder binderDMS_;
    private final Object binderIELock_ = new Object();
    private IBinder binderIE_;
    private final ServiceConnection connAT2_ = new ServiceConnection() {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            componentname = ((ComponentName) (binderAT2Lock_));
            componentname;
            JVM INSTR monitorenter ;
            Parcel parcel;
            Parcel parcel1;
            Log.d("ProcessWatchingService", "AirTunerService2 is connected.");
            binderAT2_ = ibinder;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            try
            {
                ibinder.transact(1000, parcel, parcel1, 0);
                break MISSING_BLOCK_LABEL_62;
            }
            // Misplaced declaration of an exception variable
            catch(IBinder ibinder) { }
            Log.e("ProcessWatchingService", "PxDMSDaemonService transact() throws RemoteException.");
            componentname;
            JVM INSTR monitorexit ;
            return;
            ibinder;
            componentname;
            JVM INSTR monitorexit ;
            throw ibinder;
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            Log.d("ProcessWatchingService", "AirTunerService2 is disconnected.");
            componentname = new Intent("jp.pixela.airtunerjni.AIRTUNER2_NOTIFY");
            componentname.putExtra("event", 1);
            sendBroadcast(componentname);
            synchronized(binderAT2Lock_)
            {
                binderAT2_ = null;
            }
            Log.d("ProcessWatchingService", "Leave connAT2_.onServiceDisconnected().");
            return;
            componentname;
            obj;
            JVM INSTR monitorexit ;
            throw componentname;
        }

        final ProcessWatchingService this$0;

            
            {
                this$0 = ProcessWatchingService.this;
                super();
            }
    }
;
    private final ServiceConnection connAT_ = new ServiceConnection() {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            componentname = ((ComponentName) (binderATLock_));
            componentname;
            JVM INSTR monitorenter ;
            Parcel parcel;
            Parcel parcel1;
            Log.d("ProcessWatchingService", "AirTunerService is connected.");
            binderAT_ = ibinder;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            try
            {
                ibinder.transact(1000, parcel, parcel1, 0);
                break MISSING_BLOCK_LABEL_62;
            }
            // Misplaced declaration of an exception variable
            catch(IBinder ibinder) { }
            Log.e("ProcessWatchingService", "PxDMSDaemonService transact() throws RemoteException.");
            componentname;
            JVM INSTR monitorexit ;
            return;
            ibinder;
            componentname;
            JVM INSTR monitorexit ;
            throw ibinder;
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            Log.d("ProcessWatchingService", "AirTunerService is disconnected.");
            stopAirTunerService2();
            componentname = new Intent("jp.pixela.airtunerjni.AIRTUNER_NOTIFY");
            componentname.putExtra("serviceIndex", 0);
            componentname.putExtra("event", 1);
            if(AirTunerService.findTargetDevice(getApplicationContext()) == null)
                componentname.putExtra("reason", 1);
            else
                componentname.putExtra("reason", 2);
            sendBroadcast(componentname);
            synchronized(binderATLock_)
            {
                binderAT_ = null;
            }
            Log.d("ProcessWatchingService", "Leave connAT_.onServiceDisconnected().");
            return;
            exception;
            componentname;
            JVM INSTR monitorexit ;
            throw exception;
        }

        final ProcessWatchingService this$0;

            
            {
                this$0 = ProcessWatchingService.this;
                super();
            }
    }
;
    private final ServiceConnection connDMS_ = new ServiceConnection() {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            componentname = ((ComponentName) (binderDMSLock_));
            componentname;
            JVM INSTR monitorenter ;
            Parcel parcel;
            Parcel parcel1;
            Log.d("ProcessWatchingService", "PxDMSDaemonService is connected.");
            binderDMS_ = ibinder;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            try
            {
                ibinder.transact(2000, parcel, parcel1, 0);
                break MISSING_BLOCK_LABEL_70;
            }
            // Misplaced declaration of an exception variable
            catch(IBinder ibinder) { }
            Log.e("ProcessWatchingService", "PxDMSDaemonService transact() throws RemoteException.");
            parcel1.writeInt(-3000);
            int i = parcel1.readInt();
            ibinder = JVM INSTR new #67  <Class StringBuilder>;
            ibinder.StringBuilder();
            ibinder.append("PxDMSDaemonService.PXDMSDAEMON_SERVICE_START: ret=");
            ibinder.append(i);
            Log.d("ProcessWatchingService", ibinder.toString());
            componentname;
            JVM INSTR monitorexit ;
            return;
            ibinder;
            componentname;
            JVM INSTR monitorexit ;
            throw ibinder;
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            Log.d("ProcessWatchingService", "PxDMSDaemonService is disconnected.");
            synchronized(binderDMSLock_)
            {
                binderDMS_ = null;
            }
            Log.d("ProcessWatchingService", "Leave connDMS_.onServiceDisconnected().");
            return;
            componentname;
            obj;
            JVM INSTR monitorexit ;
            throw componentname;
        }

        final ProcessWatchingService this$0;

            
            {
                this$0 = ProcessWatchingService.this;
                super();
            }
    }
;
    private final ServiceConnection connIE_ = new ServiceConnection() {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            synchronized(binderIELock_)
            {
                Log.d("ProcessWatchingService", "IoTEdgeService is connected.");
                binderIE_ = ibinder;
            }
            return;
            ibinder;
            componentname;
            JVM INSTR monitorexit ;
            throw ibinder;
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            Log.d("ProcessWatchingService", "IoTEdgeService is disconnected.");
            synchronized(binderIELock_)
            {
                binderIE_ = null;
            }
            return;
            exception;
            componentname;
            JVM INSTR monitorexit ;
            throw exception;
        }

        final ProcessWatchingService this$0;

            
            {
                this$0 = ProcessWatchingService.this;
                super();
            }
    }
;






/*
    static IBinder access$1102(ProcessWatchingService processwatchingservice, IBinder ibinder)
    {
        processwatchingservice.binderIE_ = ibinder;
        return ibinder;
    }

*/








/*
    static IBinder access$502(ProcessWatchingService processwatchingservice, IBinder ibinder)
    {
        processwatchingservice.binderAT_ = ibinder;
        return ibinder;
    }

*/



/*
    static IBinder access$702(ProcessWatchingService processwatchingservice, IBinder ibinder)
    {
        processwatchingservice.binderAT2_ = ibinder;
        return ibinder;
    }

*/




/*
    static IBinder access$902(ProcessWatchingService processwatchingservice, IBinder ibinder)
    {
        processwatchingservice.binderDMS_ = ibinder;
        return ibinder;
    }

*/
}
