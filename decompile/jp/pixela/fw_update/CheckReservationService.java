// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.fw_update;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import jp.pixela.common.ApplicationUtility;
import jp.pixela.common.PxLog;
import jp.pixela.player_service.tunerservice.ControlInterface;
import jp.pixela.player_service.tunerservice.TunerService;
import jp.pixela.pxlibs.utils.android.log.LoggerRTM;

public class CheckReservationService extends Service
{

    public CheckReservationService()
    {
    }

    private boolean checkReservation(long l, long l1, boolean aflag[])
    {
        PxLog.d(TAG, "checkReservation");
        try
        {
            Thread.sleep(2000L);
        }
        catch(Exception exception)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(LOG_HEAD);
            stringbuilder.append("e=");
            stringbuilder.append(exception);
            LoggerRTM.e(stringbuilder.toString(), new Object[0]);
        }
        if(controlInterface_ == null)
        {
            controlInterface_ = new ControlInterface(this);
            controlInterface_.loadLibrary();
        }
        Object obj = TunerService.CreateDefaultDeviceInfo();
        Bundle bundle = new Bundle();
        bundle.putParcelable("DeviceInfo", ((android.os.Parcelable) (obj)));
        bundle.putInt("BroadcastWave", -1);
        bundle.putInt("ServiceId", -1);
        bundle.putInt("StartMode", jp.pixela.player_service.tunerservice.TunerService.StartMode.CheckReservationState.getValue());
        obj = new Message();
        ((Message) (obj)).setData(bundle);
        if((new TunerService(this, controlInterface_)).Init(((Message) (obj))) != 0)
        {
            PxLog.e(TAG, "failed to tunerService.Init");
            return false;
        }
        if(controlInterface_.doUpdateCacheWithTarget(0) != 0)
        {
            PxLog.e(TAG, "failed to controlInterface_.doUpdateCacheWithTarget epg");
            return false;
        }
        long l2 = SystemClock.elapsedRealtime();
        for(long l3 = 0L; l3 - l2 < 2000L; l3 = SystemClock.elapsedRealtime())
        {
            try
            {
                Thread.sleep(100L);
            }
            catch(Exception exception1)
            {
                StringBuilder stringbuilder1 = new StringBuilder();
                stringbuilder1.append(LOG_HEAD);
                stringbuilder1.append("e=");
                stringbuilder1.append(exception1);
                LoggerRTM.e(stringbuilder1.toString(), new Object[0]);
            }
            if(ApplicationUtility.isAppRunningForeground(getApplicationContext()))
            {
                PxLog.d(TAG, "TV App running. Cancel process.");
                return false;
            }
        }

        int i = controlInterface_.checkReservationRecordingStartAtTime(l, l1, aflag);
        if(i != 0)
        {
            String s = TAG;
            aflag = new StringBuilder();
            aflag.append("failed to checkReservationStartAtTime:");
            aflag.append(i);
            PxLog.e(s, aflag.toString());
            return false;
        } else
        {
            return true;
        }
    }

    private void stopService()
    {
        PxLog.d(TAG, "stopService");
        this;
        JVM INSTR monitorenter ;
        if(controlInterface_ != null)
        {
            controlInterface_.destroy();
            controlInterface_ = null;
        }
        this;
        JVM INSTR monitorexit ;
        stopSelf();
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int j)
    {
        PxLog.d(TAG, "onStartCommand");
        (new Thread(new Runnable() {

            public void run()
            {
                boolean aflag[] = new boolean[1];
                aflag[0] = false;
                boolean flag = checkReservation(startTime, endTime, aflag);
                Object obj = new Intent("jp.pixela.system.px_setup.action.NOTIFY_RESERVATION_STATE");
                ((Intent) (obj)).putExtra("jp.pixela.system.px_setup.extra.RESULT", flag);
                ((Intent) (obj)).putExtra("jp.pixela.system.px_setup.extra.EMPTY", aflag[0]);
                ((Intent) (obj)).setPackage("jp.pixela.system.px_setup");
                ((Intent) (obj)).addFlags(0x1000000);
                sendBroadcast(((Intent) (obj)));
                String s = CheckReservationService.TAG;
                obj = new StringBuilder();
                ((StringBuilder) (obj)).append("result=");
                ((StringBuilder) (obj)).append(flag);
                ((StringBuilder) (obj)).append(":isEmpty=");
                ((StringBuilder) (obj)).append(aflag[0]);
                PxLog.d(s, ((StringBuilder) (obj)).toString());
                stopService();
            }

            final CheckReservationService this$0;
            final long val$endTime;
            final long val$startTime;

            
            {
                this$0 = CheckReservationService.this;
                startTime = l;
                endTime = l1;
                super();
            }
        }
)).start();
        return 2;
    }

    public static final String CHECK_RESERVATION_SERVICE_END_TIME = "jp.pixela.fw_update.extra.CheckReservationService.endtime";
    public static final String CHECK_RESERVATION_SERVICE_START_TIME = "jp.pixela.fw_update.extra.CheckReservationService.starttime";
    private static final String LOG_HEAD;
    static final String PACKAGE_NAME_PX_SETUP = "jp.pixela.system.px_setup";
    static final String TAG = "CheckReservationService";
    private static final long WaitMillisecAfterInitControllInterface = 2000L;
    private static final long WaitMillisecAfterTunerStarted = 2000L;
    private ControlInterface controlInterface_;

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/fw_update/CheckReservationService.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }


}
