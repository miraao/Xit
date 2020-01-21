// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service;

import android.app.ActivityManager;
import android.content.*;
import android.os.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import jp.pixela.airtunerjni.ProcessWatchingService;
import jp.pixela.common.ProductInfo;
import jp.pixela.common.PxLog;
import jp.pixela.player_service.tunerservice.AirTunerEventReceiver;
import jp.pixela.player_service.tunerservice.AssignType;
import jp.pixela.player_service.tunerservice.ControlInterface;
import jp.pixela.player_service.tunerservice.TunerService;
import jp.pixela.pxlibs.utils.android.log.LoggerRTM;

public class TunerServiceManager
    implements jp.pixela.player_service.tunerservice.AirTunerEventReceiver.AirTunerEventReceiverInterface
{
    public static final class InitializeResult extends Enum
    {

        public static InitializeResult valueOf(String s)
        {
            return (InitializeResult)Enum.valueOf(jp/pixela/player_service/TunerServiceManager$InitializeResult, s);
        }

        public static InitializeResult[] values()
        {
            return (InitializeResult[])$VALUES.clone();
        }

        private static final InitializeResult $VALUES[];
        public static final InitializeResult StartTunerService_Timeout;
        public static final InitializeResult Success;

        static 
        {
            Success = new InitializeResult("Success", 0);
            StartTunerService_Timeout = new InitializeResult("StartTunerService_Timeout", 1);
            $VALUES = (new InitializeResult[] {
                Success, StartTunerService_Timeout
            });
        }

        private InitializeResult(String s, int i)
        {
            super(s, i);
        }
    }

    public static interface TunerServiceInterface
    {

        public abstract void onInitialize(InitializeResult initializeresult, boolean flag);

        public abstract void onTerminate();

        public abstract void onTunerStopped();
    }


    private TunerServiceManager()
    {
        mContext = null;
        mControlInterface = null;
        mTunerService = null;
        mTunerServiceInitializeTimer = null;
        mProcessWatchingServiceBinder = null;
        mIsAirTunerStarted = false;
        mCallbackList = new ArrayList();
        mRegisterList = new ArrayList();
        mIsRegisteredAirTunerEventReceiver = false;
        mProductInfo = null;
        mConnection = new ServiceConnection() {

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                PxLog.d(TunerServiceManager.TAG, "ProcessWatchingService is connected.");
                TunerServiceManager.getInstance().onProcessWatchingServiceConnected(ibinder);
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                PxLog.d(TunerServiceManager.TAG, "ProcessWatchingService is disconnected.");
                TunerServiceManager.getInstance().onProcessWatchingServiceDisconnected();
            }

            final TunerServiceManager this$0;

            
            {
                this$0 = TunerServiceManager.this;
                super();
            }
        }
;
    }

    private void bindProcessWatchingService()
    {
        if(mProcessWatchingServiceBinder != null)
        {
            PxLog.d(TAG, "Already bind service");
            return;
        }
        Intent intent = new Intent(mContext, jp/pixela/airtunerjni/ProcessWatchingService);
        if(mContext.startService(intent) == null)
        {
            PxLog.e(TAG, "Failed to start ProcessWatchingService.");
            return;
        }
        if(!mContext.bindService(intent, mConnection, 0))
            PxLog.e(TAG, "ProcessWatchingService bindService() failed!!");
    }

    private void doNotifyInitialized(InitializeResult initializeresult, boolean flag)
    {
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("doNotifyInitialized. result=");
        stringbuilder.append(initializeresult);
        stringbuilder.append(",");
        stringbuilder.append(flag);
        PxLog.d(s, stringbuilder.toString());
        mInitializing.set(false);
        List list = mCallbackList;
        list;
        JVM INSTR monitorenter ;
        Iterator iterator = mCallbackList.iterator();
_L1:
        TunerServiceInterface tunerserviceinterface;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_117;
            tunerserviceinterface = (TunerServiceInterface)iterator.next();
        } while(tunerserviceinterface == null);
        tunerserviceinterface.onInitialize(initializeresult, flag);
          goto _L1
        list;
        JVM INSTR monitorexit ;
        return;
        initializeresult;
        list;
        JVM INSTR monitorexit ;
        throw initializeresult;
    }

    private void doNotifyTerminated()
    {
        List list = mCallbackList;
        list;
        JVM INSTR monitorenter ;
        Iterator iterator = mCallbackList.iterator();
_L1:
        TunerServiceInterface tunerserviceinterface;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_49;
            tunerserviceinterface = (TunerServiceInterface)iterator.next();
        } while(tunerserviceinterface == null);
        tunerserviceinterface.onTerminate();
          goto _L1
        list;
        JVM INSTR monitorexit ;
        mCallbackList.clear();
        mRegisterList.clear();
        return;
        Exception exception;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void doNotifyTunerStopped()
    {
        List list = mCallbackList;
        list;
        JVM INSTR monitorenter ;
        Iterator iterator = mCallbackList.iterator();
_L1:
        TunerServiceInterface tunerserviceinterface;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_49;
            tunerserviceinterface = (TunerServiceInterface)iterator.next();
        } while(tunerserviceinterface == null);
        tunerserviceinterface.onTunerStopped();
          goto _L1
        list;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static TunerServiceManager getInstance()
    {
        if(sInstance == null)
        {
            PxLog.v(TAG, "create instance");
            sInstance = new TunerServiceManager();
        }
        return sInstance;
    }

    private boolean isAirTunerServiceRunning()
    {
        if(mContext == null)
            return false;
        for(Iterator iterator = ((ActivityManager)mContext.getSystemService("activity")).getRunningServices(0x7fffffff).iterator(); iterator.hasNext();)
            if(((android.app.ActivityManager.RunningServiceInfo)iterator.next()).service.getClassName().equals("jp.pixela.airtunerjni.AirTunerService"))
            {
                PxLog.d(TAG, "AirTunerService is running.");
                return true;
            }

        PxLog.d(TAG, "AirTunerService is not running.");
        return false;
    }

    private void onProcessWatchingServiceConnected(IBinder ibinder)
    {
        mProcessWatchingServiceBinder = ibinder;
        startAirTunerService();
        if(!mIsAirTunerStarted)
            (new Handler()).postDelayed(new Runnable() {

                public void run()
                {
                    if(!mIsAirTunerStarted)
                        doNotifyInitialized(InitializeResult.StartTunerService_Timeout, false);
                }

                final TunerServiceManager this$0;

            
            {
                this$0 = TunerServiceManager.this;
                super();
            }
            }
, 15000L);
    }

    private void onProcessWatchingServiceDisconnected()
    {
        mProcessWatchingServiceBinder = null;
    }

    private boolean startTuner(Bundle bundle)
    {
        Object obj = mTunerService;
        boolean flag = false;
        if(obj == null)
        {
            PxLog.w(TAG, "startTuner() TunerService is null.");
            return false;
        }
        if(mTunerService.IsInit())
            return true;
        obj = bundle;
        if(bundle == null)
            obj = new Bundle();
        ((Bundle) (obj)).putParcelable("DeviceInfo", TunerService.CreateDefaultDeviceInfo());
        ((Bundle) (obj)).putParcelable("AssignType", AssignType.TV_PREVIEW);
        ((Bundle) (obj)).putParcelable("ProductInfo", mProductInfo);
        bundle = Message.obtain(null, 0);
        bundle.setData(((Bundle) (obj)));
        if(mTunerService.Init(bundle) == 0)
            flag = true;
        return flag;
    }

    private void unbindProcessWatchingService()
    {
        if(mProcessWatchingServiceBinder == null)
        {
            PxLog.d(TAG, "Already unbind service");
            return;
        } else
        {
            new Intent("jp.pixela.airtunerjni.ProcessWatchingService");
            mContext.unbindService(mConnection);
            return;
        }
    }

    public ControlInterface getControlInterface()
    {
        return mControlInterface;
    }

    public TunerService getTunerService()
    {
        return mTunerService;
    }

    public void initTuner(final Bundle bundle, long l, boolean flag, final jp.pixela.common.IDelegate.IFunc preInitTask, final jp.pixela.common.IDelegate.IAction2 resultTask)
    {
        if(isInit())
        {
            resultTask.invoke(Boolean.valueOf(true), Boolean.valueOf(true));
        } else
        {
            mIsInitializingTunerService.set(true);
            mIsPreparingTunerService.set(flag);
            mTunerServiceInitializeTimer = new Timer();
            mTunerServiceInitializeTimer.schedule(new TimerTask() {

                public void run()
                {
                    if(((Boolean)preInitTask.invoke()).booleanValue())
                    {
                        mIsInitializingTunerService.set(false);
                        mIsPreparingTunerService.set(false);
                        return;
                    } else
                    {
                        mStartingTunerService.set(true);
                        boolean flag1 = startTuner(bundle);
                        mStartingTunerService.set(false);
                        resultTask.invoke(Boolean.valueOf(flag1), Boolean.valueOf(false));
                        mIsInitializingTunerService.set(false);
                        mIsPreparingTunerService.set(false);
                        return;
                    }
                }

                final TunerServiceManager this$0;
                final Bundle val$bundle;
                final jp.pixela.common.IDelegate.IFunc val$preInitTask;
                final jp.pixela.common.IDelegate.IAction2 val$resultTask;

            
            {
                this$0 = TunerServiceManager.this;
                preInitTask = ifunc;
                bundle = bundle1;
                resultTask = iaction2;
                super();
            }
            }
, l);
        }
    }

    public void initialize(TunerServiceInterface tunerserviceinterface)
    {
        PxLog.d(TAG, "initialize in");
        if(mContext == null)
            return;
        if(!mRegisterList.contains(tunerserviceinterface))
            mRegisterList.add(tunerserviceinterface);
        if(!mCallbackList.contains(tunerserviceinterface))
            mCallbackList.add(tunerserviceinterface);
        if(!mIsRegisteredAirTunerEventReceiver)
        {
            tunerserviceinterface = new IntentFilter();
            tunerserviceinterface.addAction("jp.pixela.airtunerjni.AIRTUNER_NOTIFY");
            mContext.registerReceiver(mAirTunerEventReceiver, tunerserviceinterface);
            mIsRegisteredAirTunerEventReceiver = true;
        }
        if(isInit())
        {
            PxLog.d(TAG, "already Initialized TunerService");
            doNotifyInitialized(InitializeResult.Success, false);
            return;
        }
        if(mIsInitializingTunerService.get())
        {
            PxLog.d(TAG, "now initializing TunerService.");
            return;
        }
        if(mInitializing.get())
        {
            PxLog.d(TAG, "now initializing.");
            return;
        }
        mIsAirTunerStarted = isAirTunerServiceRunning();
        if(mProcessWatchingServiceBinder == null)
        {
            mInitializing.set(true);
            bindProcessWatchingService();
        } else
        if(!mIsAirTunerStarted)
        {
            mInitializing.set(true);
            startAirTunerService();
        }
    }

    public boolean isAirTunerStarted()
    {
        return mIsAirTunerStarted;
    }

    public boolean isInit()
    {
        return mTunerService != null && mTunerService.IsInit();
    }

    public boolean isInitializingTunerService()
    {
        return mIsInitializingTunerService.get();
    }

    public boolean isPreparingTunerService()
    {
        return mIsPreparingTunerService.get();
    }

    public boolean isTerminatingTunerService()
    {
        return mIsTerminatingTunerService.get();
    }

    public void onTunerStarted()
    {
        mIsAirTunerStarted = true;
        doNotifyInitialized(InitializeResult.Success, true);
    }

    public void onTunerStopped(int i)
    {
        mIsAirTunerStarted = false;
        doNotifyTunerStopped();
    }

    public void prepareTerminate()
    {
        if(mTunerServiceInitializeTimer != null)
        {
            mTunerServiceInitializeTimer.cancel();
            mTunerServiceInitializeTimer = null;
        }
        mIsTerminatingTunerService.set(true);
    }

    public void setContext(Context context)
    {
        PxLog.d(TAG, "setContext in");
        mProductInfo = new ProductInfo(context.getApplicationContext());
        mContext = context.getApplicationContext();
        if(mControlInterface == null)
            mControlInterface = new ControlInterface(mContext);
        if(mTunerService == null)
            mTunerService = new TunerService(mContext, mControlInterface);
        PxLog.d(TAG, "setContext out");
    }

    public void startAirTunerService()
    {
        PxLog.d(TAG, "startAirTunerService in");
        if(!mIsAirTunerStarted)
            mIsAirTunerStarted = isAirTunerServiceRunning();
        if(mIsAirTunerStarted)
        {
            PxLog.d(TAG, "AirTunerService already started.");
            onTunerStarted();
            return;
        }
        if(mProcessWatchingServiceBinder != null)
        {
            Parcel parcel = Parcel.obtain();
            Parcel parcel1 = Parcel.obtain();
            try
            {
                mProcessWatchingServiceBinder.transact(1000, parcel, parcel1, 0);
            }
            catch(RemoteException remoteexception) { }
            PxLog.d(TAG, "startAirTunerService out");
            return;
        } else
        {
            return;
        }
    }

    public void stopAirTunerService()
    {
        PxLog.d(TAG, "stopAirTunerService in");
        if(!mIsAirTunerStarted)
        {
            PxLog.d(TAG, "AirTunerService already stopped.");
            return;
        }
        if(mProcessWatchingServiceBinder != null)
        {
            Parcel parcel = Parcel.obtain();
            Parcel parcel1 = Parcel.obtain();
            try
            {
                mProcessWatchingServiceBinder.transact(1001, parcel, parcel1, 0);
            }
            catch(RemoteException remoteexception)
            {
                PxLog.e(TAG, "AirTunerService transact() throws RemoteException.");
            }
        }
        PxLog.d(TAG, "stopAirTunerService out");
    }

    public boolean terminate(TunerServiceInterface tunerserviceinterface, boolean flag)
    {
        mRegisterList.remove(tunerserviceinterface);
        int i = mRegisterList.size();
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("connect count=");
        stringbuilder.append(i);
        PxLog.d(s, stringbuilder.toString());
        if(i != 0)
        {
            mCallbackList.remove(tunerserviceinterface);
            return false;
        }
        if(mTunerServiceInitializeTimer != null)
        {
            mTunerServiceInitializeTimer.cancel();
            mTunerServiceInitializeTimer = null;
        }
        if(!mIsTerminatingTunerService.get())
        {
            mIsTerminatingTunerService.set(true);
            (new Thread(new Runnable() {

                public void run()
                {
                    PxLog.d(TunerServiceManager.TAG, "onStop() mTunerService.Term() thread in");
                    timer.start();
                    while(mStartingTunerService.get()) 
                        try
                        {
                            Thread.sleep(100L);
                        }
                        catch(InterruptedException interruptedexception)
                        {
                            StringBuilder stringbuilder1 = new StringBuilder();
                            stringbuilder1.append(TunerServiceManager.LOG_HEAD);
                            stringbuilder1.append("e=");
                            stringbuilder1.append(interruptedexception);
                            LoggerRTM.e(stringbuilder1.toString(), new Object[0]);
                        }
                    PxLog.d(TunerServiceManager.TAG, "start TunerService.Term()");
                    if(mTunerService != null)
                        mTunerService.Term();
                    PxLog.d(TunerServiceManager.TAG, "complete TunerService.Term()");
                    timer.cancel();
                    PxLog.d(TunerServiceManager.TAG, "onStop() mTunerService.Term() thread out");
                    mKillProcessHandler.post(mKillProcessRunnable);
                }

                final TunerServiceManager this$0;
                final CountDownTimer val$timer;

            
            {
                this$0 = TunerServiceManager.this;
                timer = countdowntimer;
                super();
            }
            }
)).start();
        }
        if(flag)
            stopAirTunerService();
        mIsAirTunerStarted = false;
        unbindProcessWatchingService();
        mProcessWatchingServiceBinder = null;
        if(mIsRegisteredAirTunerEventReceiver)
        {
            mContext.unregisterReceiver(mAirTunerEventReceiver);
            mIsRegisteredAirTunerEventReceiver = false;
        }
        return true;
    }

    public void terminateForce(final jp.pixela.common.IDelegate.IAction finishTask)
    {
        mCallbackList.clear();
        mRegisterList.clear();
        if(mTunerServiceInitializeTimer != null)
        {
            mTunerServiceInitializeTimer.cancel();
            mTunerServiceInitializeTimer = null;
        }
        mIsTerminatingTunerService.set(true);
        final CountDownTimer timer = new CountDownTimer(6000L, finishTask) {

            public void onFinish()
            {
                PxLog.d(TunerServiceManager.TAG, "onStop() mTunerService.Term() thread out by forced termination");
                finishTask.invoke();
            }

            public void onTick(long l)
            {
            }

            final TunerServiceManager this$0;
            final jp.pixela.common.IDelegate.IAction val$finishTask;

            
            {
                this$0 = TunerServiceManager.this;
                finishTask = iaction;
                super(final_l, l1);
            }
        }
;
        (new Thread(new Runnable() {

            public void run()
            {
                PxLog.d(TunerServiceManager.TAG, "onStop() mTunerService.Term() thread in");
                while(mStartingTunerService.get()) 
                    try
                    {
                        Thread.sleep(100L);
                    }
                    catch(InterruptedException interruptedexception)
                    {
                        StringBuilder stringbuilder = new StringBuilder();
                        stringbuilder.append(TunerServiceManager.LOG_HEAD);
                        stringbuilder.append("e=");
                        stringbuilder.append(interruptedexception);
                        LoggerRTM.e(stringbuilder.toString(), new Object[0]);
                    }
                PxLog.d(TunerServiceManager.TAG, "start TunerService.Term()");
                if(mTunerService != null)
                    mTunerService.Term();
                PxLog.d(TunerServiceManager.TAG, "complete TunerService.Term()");
                timer.cancel();
                finishTask.invoke();
            }

            final TunerServiceManager this$0;
            final jp.pixela.common.IDelegate.IAction val$finishTask;
            final CountDownTimer val$timer;

            
            {
                this$0 = TunerServiceManager.this;
                timer = countdowntimer;
                finishTask = iaction;
                super();
            }
        }
)).start();
        timer.start();
    }

    public static final int AIRTUNER_SERVICE_START = 1000;
    public static final int AIRTUNER_SERVICE_STOP = 1001;
    private static final String AirTunerServiceName = "jp.pixela.airtunerjni.AirTunerService";
    private static final String LOG_HEAD;
    private static final String ProcessWatchingServiceName = "jp.pixela.airtunerjni.ProcessWatchingService";
    private static final String TAG = "TunerServiceManager";
    private static final int WAIT_TUNER_TERM_TIME = 6000;
    private static final AtomicBoolean mIsTerminatingTunerService = new AtomicBoolean(false);
    private static TunerServiceManager sInstance;
    private final AirTunerEventReceiver mAirTunerEventReceiver = new AirTunerEventReceiver(this);
    private List mCallbackList;
    private ServiceConnection mConnection;
    private Context mContext;
    private ControlInterface mControlInterface;
    private final AtomicBoolean mInitializing = new AtomicBoolean(false);
    private boolean mIsAirTunerStarted;
    private final AtomicBoolean mIsInitializingTunerService = new AtomicBoolean(false);
    private final AtomicBoolean mIsPreparingTunerService = new AtomicBoolean(false);
    private boolean mIsRegisteredAirTunerEventReceiver;
    private final Handler mKillProcessHandler = new Handler(Looper.getMainLooper());
    private final Runnable mKillProcessRunnable = new Runnable() {

        public void run()
        {
            PxLog.d(TunerServiceManager.TAG, "mKillProcessRunnable.run() in");
            TunerServiceManager.mIsTerminatingTunerService.set(false);
            doNotifyTerminated();
            PxLog.d(TunerServiceManager.TAG, "mKillProcessRunnable.run() out");
        }

        final TunerServiceManager this$0;

            
            {
                this$0 = TunerServiceManager.this;
                super();
            }
    }
;
    private IBinder mProcessWatchingServiceBinder;
    ProductInfo mProductInfo;
    private List mRegisterList;
    private final AtomicBoolean mStartingTunerService = new AtomicBoolean(false);
    private TunerService mTunerService;
    private Timer mTunerServiceInitializeTimer;

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/player_service/TunerServiceManager.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }

















    // Unreferenced inner class jp/pixela/player_service/TunerServiceManager$3

/* anonymous class */
    class _cls3 extends CountDownTimer
    {

        public void onFinish()
        {
            PxLog.d(TunerServiceManager.TAG, "onStop() mTunerService.Term() thread out by forced termination");
            mContext.sendBroadcast(new Intent("jp.pixela.airtunerjni.AirTunerService.intent.action.STOP"));
            mKillProcessHandler.post(mKillProcessRunnable);
        }

        public void onTick(long l)
        {
        }

        final TunerServiceManager this$0;

            
            {
                this$0 = TunerServiceManager.this;
                super(l, l1);
            }
    }

}
