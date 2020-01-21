// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.airtunerjni;

import android.app.PendingIntent;
import android.app.Service;
import android.content.*;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.*;
import android.util.Log;
import java.io.File;
import java.util.*;

// Referenced classes of package jp.pixela.airtunerjni:
//            AirTunerServiceInterface, AccessUSB, PxjfClientService, AirTunerJni

public class AirTunerService extends Service
    implements AirTunerServiceInterface
{
    private static class ProductProperty
    {

        public boolean hasAssociatedHdd;
        public int productId;

        public ProductProperty(int i, boolean flag)
        {
            productId = i;
            hasAssociatedHdd = flag;
        }
    }


    public AirTunerService()
    {
        binderPxJFLock_ = new Object();
        connPxJF_ = new ServiceConnection() {

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                Log.d("AirTunerService", "PxjfClientService is connected.");
                synchronized(binderPxJFLock_)
                {
                    binderPxJF_ = ibinder;
                }
                return;
                ibinder;
                componentname;
                JVM INSTR monitorexit ;
                throw ibinder;
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                Log.d("AirTunerService", "PxjfClientService is disconnected.");
                synchronized(binderPxJFLock_)
                {
                    binderPxJF_ = null;
                }
                return;
                componentname;
                obj;
                JVM INSTR monitorexit ;
                throw componentname;
            }

            final AirTunerService this$0;

            
            {
                this$0 = AirTunerService.this;
                super();
            }
        }
;
    }

    public static UsbDevice findTargetDevice(Context context)
    {
        for(Iterator iterator = ((UsbManager)context.getSystemService("usb")).getDeviceList().values().iterator(); iterator.hasNext();)
        {
            context = (UsbDevice)iterator.next();
            if(isTargetDevice(context.getVendorId(), context.getProductId()))
            {
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append("Target device is found: vendor=");
                stringbuilder.append(context.getVendorId());
                stringbuilder.append(", product=");
                stringbuilder.append(context.getProductId());
                stringbuilder.append(", id=");
                stringbuilder.append(context.getDeviceId());
                Log.d("AirTunerService", stringbuilder.toString());
                return context;
            }
        }

        return null;
    }

    private static boolean hasAssociatedHdd(int i)
    {
        for(int j = 0; j < TARGET_PRODUCT_IDs.length; j++)
            if(i == TARGET_PRODUCT_IDs[j].productId)
                return TARGET_PRODUCT_IDs[j].hasAssociatedHdd;

        return false;
    }

    private static boolean isTargetDevice(int i, int j)
    {
        if(i == 1720)
            for(i = 0; i < TARGET_PRODUCT_IDs.length; i++)
                if(j == TARGET_PRODUCT_IDs[i].productId)
                    return true;

        return false;
    }

    private void onTargetDeviceFound(UsbDevice usbdevice)
    {
        Object obj = lockTarget_;
        obj;
        JVM INSTR monitorenter ;
        int i;
        target_ = usbdevice;
        i = AccessUSB.init(usbdevice);
        if(i == 0)
            break MISSING_BLOCK_LABEL_60;
        usbdevice = JVM INSTR new #178 <Class StringBuilder>;
        usbdevice.StringBuilder();
        usbdevice.append("AccessUSB.init() failed:ret=");
        usbdevice.append(i);
        Log.e("AirTunerService", usbdevice.toString());
        target_ = null;
        obj;
        JVM INSTR monitorexit ;
        return;
        obj;
        JVM INSTR monitorexit ;
        return;
        usbdevice;
        obj;
        JVM INSTR monitorexit ;
        throw usbdevice;
    }

    private void searchTunerDevice()
    {
        UsbDevice usbdevice = findTargetDevice(getApplicationContext());
        if(usbdevice == null)
        {
            Log.e("AirTunerService", "The target device is not found!!");
            onTargetDeviceFound(null);
            return;
        }
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append("Found USB device: id = ");
        ((StringBuilder) (obj)).append(usbdevice.getDeviceId());
        Log.d("AirTunerService", ((StringBuilder) (obj)).toString());
        obj = PendingIntent.getBroadcast(this, 0, new Intent("jp.pixela.airtunerjni.USB_PERMISSION"), 0);
        UsbManager usbmanager = (UsbManager)getApplicationContext().getSystemService("usb");
        if(!usbmanager.hasPermission(usbdevice))
        {
            Log.d("AirTunerService", "UsbManager.requestPermission() enter.");
            usbmanager.requestPermission(usbdevice, ((PendingIntent) (obj)));
            Log.d("AirTunerService", "UsbManager.requestPermission() leave.");
        } else
        {
            Log.d("AirTunerService", "USB Device permission is already granted.");
            onTargetDeviceFound(usbdevice);
        }
    }

    private void startPxjfClientService()
    {
        synchronized(binderPxJFLock_)
        {
            if(binderPxJF_ == null)
            {
                Intent intent = JVM INSTR new #237 <Class Intent>;
                intent.Intent(getApplicationContext(), jp/pixela/airtunerjni/PxjfClientService);
                intent.putExtra("app_dir", getApplicationContext().getFilesDir().getAbsolutePath());
                bindService(intent, connPxJF_, 1);
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void stopPxjfClientService()
    {
        synchronized(binderPxJFLock_)
        {
            if(binderPxJF_ != null)
            {
                sendCommandToPxjfClient("q0");
                unbindService(connPxJF_);
                binderPxJF_ = null;
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public Context getContext()
    {
        return this;
    }

    public void notifyStopAirTuner(final int retCode, final boolean forceResetDevice)
    {
        _handler.post(new Runnable() {

            public void run()
            {
                stopPxjfClientService();
                _atJni.stopNt();
                AccessUSB.term(forceResetDevice);
                System.exit(retCode);
            }

            final AirTunerService this$0;
            final boolean val$forceResetDevice;
            final int val$retCode;

            
            {
                this$0 = AirTunerService.this;
                forceResetDevice = flag;
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
                    Log.e("AirTunerService", parcel.toString());
                    break;

                case 1000: 
                    (new Thread(new Runnable() {

                        public void run()
                        {
                            startAirTuner();
                        }

                        final _cls6 this$1;

            
            {
                this$1 = _cls6.this;
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

            final AirTunerService this$0;

            
            {
                this$0 = AirTunerService.this;
                super();
            }
        }
;
    }

    public void onCreate()
    {
        super.onCreate();
        AccessUSB.setContext(this);
        registerReceiver(usbPermissionReceiver_, new IntentFilter("jp.pixela.airtunerjni.USB_PERMISSION"), null, null);
        registerReceiver(usbAttachReceiver_, new IntentFilter("android.hardware.usb.action.USB_DEVICE_ATTACHED"), null, null);
        registerReceiver(usbDetachReceiver_, new IntentFilter("android.hardware.usb.action.USB_DEVICE_DETACHED"), null, null);
        registerReceiver(actionReceiver_, new IntentFilter("jp.pixela.airtunerjni.AirTunerService.intent.action.STOP"), null, null);
        _handler = new Handler();
        _atJni = new AirTunerJni(this);
    }

    public void onDestroy()
    {
        Log.d("AirTunerService", "AirTunerService.onDestroy() is invoked.");
        super.onDestroy();
        notifyStopAirTuner(0, false);
    }

    public boolean onUnbind(Intent intent)
    {
        Log.d("AirTunerService", "AirTunerService.onUnbind() is invoked.");
        return false;
    }

    public void requestOpenUSBDevice()
    {
        searchTunerDevice();
    }

    public void restartPxDMSDaemon()
    {
        Intent intent = new Intent("jp.pixela.airtunerjni.PXDMSDAEMON_NOTIFY");
        intent.putExtra("event", 3);
        sendBroadcast(intent);
    }

    public int restartPxjfClient(String s, int i, String s1, String s2, String s3, int j, String s4, 
            String s5, String s6, int k, int l, int i1, int j1, String s7)
    {
        Object obj = binderPxJFLock_;
        obj;
        JVM INSTR monitorenter ;
        if(binderPxJF_ == null)
            break MISSING_BLOCK_LABEL_236;
        Bundle bundle = JVM INSTR new #365 <Class Bundle>;
        bundle.Bundle();
        bundle.putString("server", s);
        bundle.putInt("port", i);
        bundle.putString("xmpp_bare_jid", s1);
        bundle.putString("xmpp_passwd", s2);
        bundle.putString("id", s3);
        bundle.putInt("type", j);
        bundle.putString("gateway", s4);
        bundle.putString("device_identification", s5);
        bundle.putString("login_marker_file", s6);
        bundle.putInt("keep_alive", k);
        bundle.putInt("enable_debug_log", l);
        bundle.putInt("upnp_duration", i1);
        bundle.putInt("mode", j1);
        bundle.putString("port_mapping", s7);
        s1 = Parcel.obtain();
        s = Parcel.obtain();
        s1.writeBundle(bundle);
        if(binderPxJF_.transact(0, s1, s, 0))
            break MISSING_BLOCK_LABEL_207;
        Log.e("AirTunerService", "PxjfClientService.transact() failed.");
        return -1;
        i = s.readInt();
        obj;
        JVM INSTR monitorexit ;
        return i;
        s;
        Log.e("AirTunerService", "PxjfClientService.transact(RESTART) throws RemoteException.");
        s.printStackTrace();
        obj;
        JVM INSTR monitorexit ;
        return -1;
        Log.e("AirTunerService", "PxjfClientService is not yet started.");
        obj;
        JVM INSTR monitorexit ;
        return -1;
        s;
        obj;
        JVM INSTR monitorexit ;
        throw s;
    }

    public void sendCommandToPxjfClient(String s)
    {
        Object obj = binderPxJFLock_;
        obj;
        JVM INSTR monitorenter ;
        Parcel parcel;
        Parcel parcel1;
        if(binderPxJF_ == null)
            break MISSING_BLOCK_LABEL_60;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeString(s);
        try
        {
            binderPxJF_.transact(1, parcel, parcel1, 0);
            break MISSING_BLOCK_LABEL_60;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        Log.e("AirTunerService", "PxjfClientService.transact(SEND_CMD) throws RemoteException.");
        s.printStackTrace();
        obj;
        JVM INSTR monitorexit ;
        return;
        s;
        obj;
        JVM INSTR monitorexit ;
        throw s;
    }

    public void startAirTuner()
    {
        startPxjfClientService();
        Object obj = new Intent("jp.pixela.airtunerjni.AIRTUNER_NOTIFY");
        ((Intent) (obj)).putExtra("serviceIndex", 0);
        if(findTargetDevice(getApplicationContext()) != null)
        {
            Log.d("AirTunerService", "Start AirTuner service process..");
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("--aservice --procnum 0 --extdir=");
            stringbuilder.append(getApplicationContext().getFilesDir());
            String s = stringbuilder.toString();
            AirTunerJni airtunerjni = _atJni;
            stringbuilder = new StringBuilder();
            stringbuilder.append(s);
            stringbuilder.append(" --ifname=eth0");
            int i = airtunerjni.startNt(stringbuilder.toString());
            if(i != 0)
            {
                obj = new StringBuilder();
                ((StringBuilder) (obj)).append("AirTunerJni.startNt() failed:ret=");
                ((StringBuilder) (obj)).append(i);
                Log.e("AirTunerService", ((StringBuilder) (obj)).toString());
                obj = null;
                notifyStopAirTuner(i, true);
            } else
            {
                StringBuilder stringbuilder1 = new StringBuilder();
                stringbuilder1.append("AirTunerJni.startNt() succeeded:ret=");
                stringbuilder1.append(i);
                Log.d("AirTunerService", stringbuilder1.toString());
                ((Intent) (obj)).putExtra("event", 0);
            }
        } else
        {
            Log.w("AirTunerService", "Target device is not found. wait USB attach event.");
            ((Intent) (obj)).putExtra("event", 3);
        }
        if(obj != null)
            sendBroadcast(((Intent) (obj)));
    }

    private static final String ACTION_STOP_SERVICE = "jp.pixela.airtunerjni.AirTunerService.intent.action.STOP";
    private static final String ACTION_USB_PERMISSION = "jp.pixela.airtunerjni.USB_PERMISSION";
    public static final int AIRTUNER_SERVICE_START = 1000;
    public static final int AIRTUNER_SERVICE_STOP = 1001;
    private static final String TAG = "AirTunerService";
    private static final ProductProperty TARGET_PRODUCT_IDs[] = {
        new ProductProperty(4156, false), new ProductProperty(4160, false), new ProductProperty(4161, true)
    };
    private static final int TARGET_VENDOR_ID = 1720;
    private AirTunerJni _atJni;
    private Handler _handler;
    private final BroadcastReceiver actionReceiver_ = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent)
        {
            if("jp.pixela.airtunerjni.AirTunerService.intent.action.STOP".equals(intent.getAction()))
            {
                Log.d("AirTunerService", "Stop intent is received.");
                notifyStopAirTuner(0, false);
            }
        }

        final AirTunerService this$0;

            
            {
                this$0 = AirTunerService.this;
                super();
            }
    }
;
    private Object binderPxJFLock_;
    private IBinder binderPxJF_;
    private ServiceConnection connPxJF_;
    private final Object lockTarget_ = new Object();
    private UsbDevice target_;
    private final BroadcastReceiver usbAttachReceiver_ = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent)
        {
            if("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(intent.getAction()))
            {
                context = (UsbDevice)intent.getParcelableExtra("device");
                if(context != null && AirTunerService.isTargetDevice(context.getVendorId(), context.getProductId()))
                {
                    Log.d("AirTunerService", "Tuner device is attached.");
                    AccessUSB.notifyDeviceAttach();
                    startAirTuner();
                }
            }
        }

        final AirTunerService this$0;

            
            {
                this$0 = AirTunerService.this;
                super();
            }
    }
;
    private final BroadcastReceiver usbDetachReceiver_ = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent)
        {
label0:
            {
                synchronized(lockTarget_)
                {
                    if(target_ != null)
                        break label0;
                }
                return;
            }
            if(!"android.hardware.usb.action.USB_DEVICE_DETACHED".equals(intent.getAction()))
                break MISSING_BLOCK_LABEL_95;
            intent = (UsbDevice)intent.getParcelableExtra("device");
            if(intent == null)
                break MISSING_BLOCK_LABEL_95;
            if(intent.getDeviceId() == target_.getDeviceId())
            {
                Log.d("AirTunerService", "Tuner device is detached.");
                AccessUSB.notifyDeviceDetach();
                target_ = null;
                notifyStopAirTuner(-1, false);
            }
            context;
            JVM INSTR monitorexit ;
            return;
            intent;
            context;
            JVM INSTR monitorexit ;
            throw intent;
        }

        final AirTunerService this$0;

            
            {
                this$0 = AirTunerService.this;
                super();
            }
    }
;
    private final BroadcastReceiver usbPermissionReceiver_ = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent)
        {
            if(!"jp.pixela.airtunerjni.USB_PERMISSION".equals(intent.getAction()))
                break MISSING_BLOCK_LABEL_176;
            this;
            JVM INSTR monitorenter ;
            context = (UsbDevice)intent.getParcelableExtra("device");
            if(!intent.getBooleanExtra("permission", false))
                break MISSING_BLOCK_LABEL_150;
            if(context == null)
                break MISSING_BLOCK_LABEL_131;
            intent = JVM INSTR new #48  <Class StringBuilder>;
            intent.StringBuilder();
            intent.append("USB Device permission is granted: vendor = ");
            intent.append(context.getVendorId());
            intent.append(", product = ");
            intent.append(context.getProductId());
            intent.append(", deviceClass = ");
            intent.append(context.getDeviceClass());
            intent.append(", id = ");
            intent.append(context.getDeviceId());
            Log.d("AirTunerService", intent.toString());
            onTargetDeviceFound(context);
            break MISSING_BLOCK_LABEL_166;
            Log.e("AirTunerService", "USB Device is not specified.");
            onTargetDeviceFound(null);
            break MISSING_BLOCK_LABEL_166;
            Log.e("AirTunerService", "USB Device permission is not granted.");
            onTargetDeviceFound(null);
            this;
            JVM INSTR monitorexit ;
            break MISSING_BLOCK_LABEL_176;
            context;
            this;
            JVM INSTR monitorexit ;
            throw context;
        }

        final AirTunerService this$0;

            
            {
                this$0 = AirTunerService.this;
                super();
            }
    }
;







/*
    static UsbDevice access$302(AirTunerService airtunerservice, UsbDevice usbdevice)
    {
        airtunerservice.target_ = usbdevice;
        return usbdevice;
    }

*/



/*
    static IBinder access$502(AirTunerService airtunerservice, IBinder ibinder)
    {
        airtunerservice.binderPxJF_ = ibinder;
        return ibinder;
    }

*/


}
