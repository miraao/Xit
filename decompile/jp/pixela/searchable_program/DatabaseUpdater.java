// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.searchable_program;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import java.util.Calendar;
import java.util.TimeZone;
import jp.pixela.common.*;
import jp.pixela.player_service.tunerservice.ControlInterface;
import jp.pixela.player_service.tunerservice.TunerService;
import jp.pixela.pxlibs.utils.android.log.LoggerRTM;

public class DatabaseUpdater extends Service
{

    public DatabaseUpdater()
    {
    }

    public static boolean needsUpdateEpg()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+09:00"));
        int i = calendar.get(11) * 60 + calendar.get(12);
        if(277 < i && i <= 457)
            return true;
        return 997 < i && i <= 1177;
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

    private void updateDatabase()
    {
        PxLog.d(TAG, "updateDatabase in");
        try
        {
            Thread.sleep(2000L);
        }
        catch(Exception exception)
        {
            StringBuilder stringbuilder1 = new StringBuilder();
            stringbuilder1.append(LOG_HEAD);
            stringbuilder1.append("e=");
            stringbuilder1.append(exception);
            LoggerRTM.e(stringbuilder1.toString(), new Object[0]);
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
        bundle.putInt("StartMode", jp.pixela.player_service.tunerservice.TunerService.StartMode.UpdateEPG.getValue());
        obj = new Message();
        ((Message) (obj)).setData(bundle);
        if((new TunerService(this, controlInterface_)).Init(((Message) (obj))) != 0)
        {
            PxLog.e(TAG, "failed to tunerService.Init");
            return;
        }
        int i;
        if((new BuildUtilityWrapper(this)).isRecordable())
        {
            if(controlInterface_.doUpdateCacheWithTarget(5) != 0)
            {
                PxLog.e(TAG, "failed to controlInterface_.doUpdateCacheWithTarget recordcontent");
                i = 0;
            } else
            {
                i = (int)((long)0 + 2000L);
            }
            if(controlInterface_.doUpdateCacheWithTarget(6) != 0)
                PxLog.e(TAG, "failed to controlInterface_.doUpdateCacheWithTarget reservation");
            else
                i = (int)((long)i + 2000L);
        } else
        {
            i = 0;
        }
        int j = i;
        if(needsUpdateEpg())
            if(controlInterface_.doUpdateCacheWithTarget(0) != 0)
            {
                PxLog.e(TAG, "failed to controlInterface_.doUpdateCacheWithTarget epg");
                j = i;
            } else
            {
                j = (int)((long)i + 0x1d4c0L);
            }
        if(j != 0)
        {
            long l = SystemClock.elapsedRealtime();
            for(long l1 = 0L; l1 - l < (long)j; l1 = SystemClock.elapsedRealtime())
            {
                try
                {
                    Thread.sleep(100L);
                }
                catch(Exception exception1)
                {
                    StringBuilder stringbuilder = new StringBuilder();
                    stringbuilder.append(LOG_HEAD);
                    stringbuilder.append("e=");
                    stringbuilder.append(exception1);
                    LoggerRTM.e(stringbuilder.toString(), new Object[0]);
                }
                if(ApplicationUtility.isAppRunningForeground(getApplicationContext()))
                {
                    PxLog.d(TAG, "TV App running. Cancel update DB.");
                    return;
                }
            }

        }
        PxLog.d(TAG, "updateDatabase out");
    }

    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int j)
    {
        PxLog.d(TAG, "onStartCommand");
        if(!(new BuildUtilityWrapper(this)).isRecordable() && !needsUpdateEpg())
        {
            stopSelf(j);
            PxLog.d(TAG, "cancel updateDatabase. onStartCommand out.");
            return 2;
        } else
        {
            (new Thread(new Runnable() {

                public void run()
                {
                    updateDatabase();
                    mHandler.post(new Runnable() {

                        public void run()
                        {
                            stopService();
                        }

                        final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
                    }
);
                }

                final DatabaseUpdater this$0;

            
            {
                this$0 = DatabaseUpdater.this;
                super();
            }
            }
)).start();
            return 2;
        }
    }

    public static final String ACTION_DB_UPDATER_UPDATE_EPG = "ACTION_DB_UPDATER_UPDATE_EPG";
    private static final int DURATION_EPG_SCAN = 180;
    private static final String LOG_HEAD;
    private static final int START_EPG_SCAN_AM_TIME = 277;
    private static final int START_EPG_SCAN_PM_TIME = 997;
    private static final String TAG = "DatabaseUpdater";
    private static final long WaitMillisecAfterInitControllInterface = 2000L;
    private static final long WaitMillisecAfterTunerStarted = 2000L;
    private static final long WaitUpdateEpgCache = 0x1d4c0L;
    private ControlInterface controlInterface_;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/searchable_program/DatabaseUpdater.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }



}
