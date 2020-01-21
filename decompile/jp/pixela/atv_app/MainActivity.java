// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.atv_app;

import android.app.Activity;
import android.content.*;
import android.content.res.Resources;
import android.os.*;
import android.preference.PreferenceManager;
import android.view.*;
import jp.pixela.atv_app.common.IntentHelper;
import jp.pixela.atv_app.common.PxLog;
import jp.pixela.pxlibs.android.views.dialogs.SbAlertDialogFragment;
import jp.pixela.pxlibs.utils.android.log.Logger;

// Referenced classes of package jp.pixela.atv_app:
//            KeyEventConverter, WebViewManager, CommandChecker, App, 
//            SettingActivity

public class MainActivity extends Activity
{
    private class TvPlayerServiceConnection
        implements ServiceConnection
    {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            PxLog.d(MainActivity.TAG, "onServiceConnected");
            mBinder = ibinder;
            try
            {
                mBinder.linkToDeath(mDeathRecipient, 0);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                componentname.printStackTrace();
            }
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            PxLog.d(MainActivity.TAG, "onServiceDisconnected");
        }

        private IBinder mBinder;
        private final android.os.IBinder.DeathRecipient mDeathRecipient;
        final MainActivity this$0;


        private TvPlayerServiceConnection()
        {
            this$0 = MainActivity.this;
            super();
            mBinder = null;
            mDeathRecipient = new _cls1();
        }

    }


    public MainActivity()
    {
        webVewManager = new WebViewManager(keyEventConverter);
        mLoadUrl = null;
        mIsForeground = false;
        mConnection = null;
        mFinishOnBinderDied = true;
        mIsSupportDolbyDigital = false;
        intentOnResume = null;
        mLoadUrlReceiver = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent)
            {
                onReceiveLoadUrlIntent(context, intent);
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
;
        mPlayerServiceFinishedReceiver = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent)
            {
                onReceiveFinishIntent(context, intent);
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
;
        mTvCommandReceiver = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent)
            {
                onReceiveGlobalKeyEvent(context, intent);
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
;
        mAtvAppReceiver = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent)
            {
                onReceiveIntentFromTV(this, context, intent);
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
;
    }

    private void initializeCommandChecker()
    {
        PxLog.v(TAG, "initializeCommandChecker() in");
        commandChecker.addRunnable(CommandChecker.COMMAND.FINISH, new Runnable() {

            public void run()
            {
                finishActivity();
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
);
        commandChecker.addRunnable(CommandChecker.COMMAND.ANDROID_SETTING, new Runnable() {

            public void run()
            {
                startAndroidSettings();
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
);
        commandChecker.addRunnable(CommandChecker.COMMAND.SPECIAL_KEY_F10_RECEIVED, new Runnable() {

            public void run()
            {
                sendSpecialKeyF10Received();
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
);
        commandChecker.addRunnable(CommandChecker.COMMAND.CHECKER_MODE, new Runnable() {

            public void run()
            {
                webVewManager.startCheckerMode(MainActivity.this);
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
);
        PxLog.v(TAG, "initializeCommandChecker() out");
    }

    private boolean isShowRecordingSupportedDialog()
    {
        return PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("IS_SHOW_RECORDING_SUPPORTED_DIALOG", false);
    }

    private boolean processIntent(Intent intent)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(LOG_HEAD);
        stringbuilder.append("in. intent=");
        stringbuilder.append(IntentHelper.dump(intent));
        Logger.v(stringbuilder.toString(), new Object[0]);
        if(intent == null)
        {
            intent = new StringBuilder();
            intent.append(LOG_HEAD);
            intent.append("out. intent == null");
            Logger.w(intent.toString(), new Object[0]);
            return false;
        }
        mIsSupportDolbyDigital = intent.getBooleanExtra("DolbyDigital", false);
        stringbuilder = new StringBuilder();
        stringbuilder.append(PACKAGE_NAME_PREFIX);
        stringbuilder.append("ACTION_WEB_VIEW_LOAD_URL");
        if(stringbuilder.toString().equals(intent.getAction()))
        {
            mLoadUrlReceiver.onReceive(this, intent);
            intent = new StringBuilder();
            intent.append(LOG_HEAD);
            intent.append("out. ACTION_WEB_VIEW_LOAD_URL");
            Logger.v(intent.toString(), new Object[0]);
            return true;
        }
        if(intent.getBooleanExtra("Reload", false))
        {
            mLoadUrl = webVewManager.getUrl(this);
            intent = new StringBuilder();
            intent.append(LOG_HEAD);
            intent.append("out. RELOAD");
            Logger.v(intent.toString(), new Object[0]);
            return true;
        } else
        {
            mLoadUrl = intent.getStringExtra("URL");
            intent = new StringBuilder();
            intent.append(LOG_HEAD);
            intent.append("out. not matched.");
            Logger.w(intent.toString(), new Object[0]);
            return false;
        }
    }

    private void sendBroadcastRestoreStereoSetting()
    {
        if(!mIsSupportDolbyDigital)
        {
            return;
        } else
        {
            PxLog.d(TAG, "sendBroadcastRestoreStereoSetting.");
            Intent intent = new Intent("com.android.tv.settings.action.PX_FORCED_STEREO_SETTING_CANCELLATION");
            intent.addFlags(0x1000000);
            sendBroadcast(intent);
            return;
        }
    }

    private void sendFinishApp()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(PACKAGE_NAME_PREFIX);
        stringbuilder.append("ACTION_FINISH_PLAYER_SERVICE");
        sendBroadcast(new Intent(stringbuilder.toString()));
    }

    private void sendSpecialKeyF10Received()
    {
        sendBroadcast(new Intent("jp.pixela.atv_app.SPECIAL_KEY_F10_RECEIVED"));
    }

    private static void setNavigationBarVisibility(Activity activity, boolean flag)
    {
        activity = activity.getWindow().getDecorView();
        if(flag)
        {
            activity.setSystemUiVisibility(0);
            return;
        }
        if(android.os.Build.VERSION.SDK_INT > 18)
            activity.setSystemUiVisibility(4102);
        else
            activity.setSystemUiVisibility(2);
    }

    private void setShowRecordingSupportedDialog()
    {
        android.content.SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
        editor.putBoolean("IS_SHOW_RECORDING_SUPPORTED_DIALOG", true);
        editor.apply();
    }

    private void showRecordingSupportedDialog()
    {
        String s = getResources().getString(R.string.message_recording_supported);
        jp.pixela.pxlibs.android.views.dialogs.SbAlertDialogFragment.Builder builder = new jp.pixela.pxlibs.android.views.dialogs.SbAlertDialogFragment.Builder(this);
        builder.setVisibleIcon(false);
        builder.setMessage(s);
        builder.setTextSize(18F);
        builder.setHeightRatio(50);
        builder.setCancelable(true);
        builder.setNegativeButton(R.string.label_general_close, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
);
        builder.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

            public void onCancel(DialogInterface dialoginterface)
            {
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
);
        SbAlertDialogFragment.show(this, builder, false, "RecordingSupportedDialog");
    }

    private void startAndBindTvPlayerService()
    {
        if(mConnection != null)
        {
            PxLog.i(TAG, "already connected.");
            return;
        }
        mFinishOnBinderDied = true;
        mConnection = new TvPlayerServiceConnection();
        Object obj = new Intent();
        ((Intent) (obj)).setComponent(new ComponentName("jp.pixela.stationtv.xit", "jp.pixela.player_service.TvPlayerService"));
        startService(((Intent) (obj)));
        boolean flag = bindService(((Intent) (obj)), mConnection, 1);
        if(!flag)
            mConnection = null;
        String s = TAG;
        obj = new StringBuilder();
        ((StringBuilder) (obj)).append("bind result=");
        ((StringBuilder) (obj)).append(flag);
        PxLog.i(s, ((StringBuilder) (obj)).toString());
    }

    private void startAndroidSettings()
    {
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("dispatchKeyEvent() in. event=");
        stringbuilder.append(keyevent);
        PxLog.v(s, stringbuilder.toString());
        if(!keyEventConverter.isDispatchToApp(keyevent))
        {
            PxLog.v(TAG, "dispatchKeyEvent() out. by !isDispatchToApp");
            return false;
        }
        if(commandChecker.dispatchKeyEvent(keyevent))
        {
            PxLog.v(TAG, "dispatchKeyEvent() out. by commandChecker");
            return true;
        }
        if(keyEventConverter.isDispatchToWebView(keyevent) && webVewManager.dispatchKeyEvent(this, keyevent))
        {
            PxLog.v(TAG, "dispatchKeyEvent() out. by webVewManager");
            return true;
        } else
        {
            boolean flag = super.dispatchKeyEvent(keyevent);
            keyevent = TAG;
            StringBuilder stringbuilder1 = new StringBuilder();
            stringbuilder1.append("dispatchKeyEvent() out. isConsumed=");
            stringbuilder1.append(flag);
            PxLog.v(keyevent, stringbuilder1.toString());
            return flag;
        }
    }

    public void finish()
    {
        super.finish();
    }

    protected void finishActivity()
    {
        finish();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(PACKAGE_NAME_PREFIX);
        stringbuilder.append("ACTION_WEB_VIEW_FINISHED");
        sendBroadcast(new Intent(stringbuilder.toString()));
    }

    protected void inputKeyEvent(int i)
    {
        long l = SystemClock.uptimeMillis();
        dispatchKeyEvent(new KeyEvent(l, l, 0, i, 0, 0, -1, 0, 0, 257));
        dispatchKeyEvent(new KeyEvent(l, l, 1, i, 0, 0, -1, 0, 0, 257));
    }

    protected void onCreate(Bundle bundle)
    {
        PxLog.v(TAG, "onCreate() in");
        super.onCreate(bundle);
        mIsSupportDolbyDigital = false;
        bundle = new StringBuilder();
        bundle.append(getApplicationContext().getPackageName());
        bundle.append(".");
        PACKAGE_NAME_PREFIX = bundle.toString();
        bundle = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("onCreate() PACKAGE_NAME_PREFIX=");
        stringbuilder.append(PACKAGE_NAME_PREFIX);
        PxLog.v(bundle, stringbuilder.toString());
        requestWindowFeature(1);
        setLayout();
        setNavigationBarVisibility(this, false);
        webVewManager.initializeWebView(this);
        bundle = mLoadUrlReceiver;
        stringbuilder = new StringBuilder();
        stringbuilder.append(PACKAGE_NAME_PREFIX);
        stringbuilder.append("ACTION_WEB_VIEW_LOAD_URL");
        registerReceiver(bundle, new IntentFilter(stringbuilder.toString()));
        registerReceiver(mPlayerServiceFinishedReceiver, new IntentFilter("jp.pixela.stationtv.xit.ACTION_PLAYER_SERVICE_FINISHED"));
        registerReceiver(mTvCommandReceiver, new IntentFilter("jp.pixela.stationtv.xit.ACTION_REQUEST_KEY_EVENT"));
        registerAtvAppReceiver();
        intentOnResume = getIntent();
        bundle = TAG;
        stringbuilder = new StringBuilder();
        stringbuilder.append("onCreate() intentOnResume=");
        stringbuilder.append(IntentHelper.dump(intentOnResume));
        PxLog.v(bundle, stringbuilder.toString());
        initializeCommandChecker();
        PxLog.v(TAG, "onCreate() out");
    }

    protected void onDestroy()
    {
        PxLog.v(TAG, "onDestroy() in");
        unregisterReceiver(mLoadUrlReceiver);
        unregisterReceiver(mPlayerServiceFinishedReceiver);
        unregisterReceiver(mTvCommandReceiver);
        unregisterReceiver(mAtvAppReceiver);
        webVewManager.finalizeWebView(this);
        super.onDestroy();
        PxLog.v(TAG, "onDestroy() out");
    }

    protected void onNewIntent(Intent intent)
    {
        String s = TAG;
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append("onNewIntent() in. intent=");
        ((StringBuilder) (obj)).append(IntentHelper.dump(intent));
        PxLog.v(s, ((StringBuilder) (obj)).toString());
        super.onNewIntent(intent);
        intentOnResume = intent;
        obj = TAG;
        intent = new StringBuilder();
        intent.append("onNewIntent() intentOnResume=");
        intent.append(IntentHelper.dump(intentOnResume));
        PxLog.v(((String) (obj)), intent.toString());
        PxLog.v(TAG, "onNewIntent() out");
    }

    protected void onPause()
    {
        PxLog.v(TAG, "onPause() in");
        super.onPause();
        mIsForeground = false;
        PxLog.v(TAG, "onPause() out");
    }

    protected void onReceiveFinishIntent(Context context, Intent intent)
    {
        context = new StringBuilder();
        context.append(LOG_HEAD);
        context.append("in. intent=");
        context.append(IntentHelper.dump(intent));
        Logger.v(context.toString(), new Object[0]);
        if(intent == null)
        {
            context = new StringBuilder();
            context.append(LOG_HEAD);
            context.append("out. intent == null");
            Logger.w(context.toString(), new Object[0]);
            return;
        }
        context = intent.getAction();
        if(context == null)
        {
            context = new StringBuilder();
            context.append(LOG_HEAD);
            context.append("out. action == null");
            Logger.w(context.toString(), new Object[0]);
            return;
        }
        if(!context.equals("jp.pixela.stationtv.xit.ACTION_PLAYER_SERVICE_FINISHED"))
        {
            context = new StringBuilder();
            context.append(LOG_HEAD);
            context.append("out. !ACTION_PLAYER_SERVICE_FINISHED");
            Logger.v(context.toString(), new Object[0]);
            return;
        }
        context = intent.getStringExtra("FinishActivty");
        if(context != null && context.compareToIgnoreCase("SettingActivity") == 0)
        {
            context = new StringBuilder();
            context.append(LOG_HEAD);
            context.append("out. SettingActivity");
            Logger.v(context.toString(), new Object[0]);
            return;
        } else
        {
            finishActivity();
            context = new StringBuilder();
            context.append(LOG_HEAD);
            context.append("out");
            Logger.v(context.toString(), new Object[0]);
            return;
        }
    }

    protected void onReceiveGlobalKeyEvent(Context context, Intent intent)
    {
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("onReceiveGlobalKeyEvent() in. intent=");
        stringbuilder.append(IntentHelper.dump(intent));
        PxLog.v(s, stringbuilder.toString());
        if(intent != null && context != null)
        {
            if(!"jp.pixela.stationtv.xit.ACTION_REQUEST_KEY_EVENT".equals(intent.getAction()))
                return;
            context = intent.getStringExtra("action");
            if(context != null && context.equals("switch TV"))
            {
                inputKeyEvent(256);
                return;
            }
            context = intent.getParcelableExtra("key_event");
            if(context != null && (context instanceof KeyEvent))
            {
                context = (KeyEvent)context;
                intent = getApplication();
                if((intent instanceof App) && ((App)intent).isExistsActivity(jp/pixela/atv_app/SettingActivity.getName()))
                {
                    context = TAG;
                    intent = new StringBuilder();
                    intent.append("cancel dispatchKeyEvent.");
                    intent.append(jp/pixela/atv_app/SettingActivity.getName());
                    PxLog.d(context, intent.toString());
                    return;
                }
                dispatchKeyEvent(context);
                if(!mIsForeground && context.getAction() == 1)
                {
                    context = new Intent("jp.pixela.stationtv.xit.ACTION_REQUEST_FOREGROUND");
                    context.addFlags(0x1000000);
                    sendBroadcast(context);
                }
            }
            PxLog.v(TAG, "onReceiveGlobalKeyEvent() out");
            return;
        } else
        {
            return;
        }
    }

    protected void onReceiveIntentFromTV(BroadcastReceiver broadcastreceiver, Context context, Intent intent)
    {
        context = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("onReceiveIntentFromTV() in. intent=");
        stringbuilder.append(IntentHelper.dump(intent));
        PxLog.v(context, stringbuilder.toString());
        if(intent == null)
        {
            PxLog.w(TAG, "onReceiveIntentFromTV() out. intent == null");
            return;
        }
        context = intent.getAction();
        if(context == null)
        {
            PxLog.w(TAG, "onReceiveIntentFromTV() out. action == null");
            return;
        }
        intent = new StringBuilder();
        intent.append(PACKAGE_NAME_PREFIX);
        intent.append("CHECK_ATV_APP_ACTION");
        if(context.equals(intent.toString()))
        {
            context = getApplication();
            if((context instanceof App) && ((App)context).isExistsActivity(jp/pixela/atv_app/SettingActivity.getName()))
            {
                broadcastreceiver.setResultCode(1);
                return;
            } else
            {
                broadcastreceiver.setResultCode(-1);
                return;
            }
        } else
        {
            PxLog.v(TAG, "onReceiveIntentFromTV() out");
            return;
        }
    }

    protected void onReceiveLoadUrlIntent(Context context, Intent intent)
    {
        context = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("onReceiveLoadUrlIntent() in. intent=");
        stringbuilder.append(IntentHelper.dump(intent));
        PxLog.v(context, stringbuilder.toString());
        if(intent == null)
        {
            PxLog.w(TAG, "onReceiveLoadUrlIntent() out. intent == null");
            return;
        }
        context = intent.getAction();
        if(context == null)
        {
            PxLog.w(TAG, "onReceiveLoadUrlIntent() out. action == null");
            return;
        }
        stringbuilder = new StringBuilder();
        stringbuilder.append(PACKAGE_NAME_PREFIX);
        stringbuilder.append("ACTION_WEB_VIEW_LOAD_URL");
        if(!context.equals(stringbuilder.toString()))
        {
            intent = TAG;
            StringBuilder stringbuilder1 = new StringBuilder();
            stringbuilder1.append("onReceiveLoadUrlIntent() out. !action.equals(");
            stringbuilder1.append(PACKAGE_NAME_PREFIX);
            stringbuilder1.append("ACTION_WEB_VIEW_LOAD_URL");
            stringbuilder1.append(") action=");
            stringbuilder1.append(context);
            PxLog.w(intent, stringbuilder1.toString());
            return;
        }
        intent = intent.getStringExtra("EXTRA_URL");
        if(intent != null && intent.length() != 0)
        {
            context = intent;
            if(intent.equals("Reload"))
                context = webVewManager.getUrl(this);
        } else
        {
            context = "file:///android_asset/initial/index.html";
        }
        webVewManager.loadUrl(this, context);
        PxLog.v(TAG, "onReceiveLoadUrlIntent() out");
    }

    protected void onResume()
    {
        Object obj = webVewManager.getUrl(this);
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("onResume() in. url=");
        stringbuilder.append(((String) (obj)));
        PxLog.v(s, stringbuilder.toString());
        super.onResume();
        mIsForeground = true;
        if(intentOnResume != null && (intentOnResume.getFlags() & 0x100000) != 0)
        {
            obj = new Intent("android.intent.action.VIEW");
            ((Intent) (obj)).setComponent(new ComponentName("jp.pixela.stationtv.xit", "jp.pixela.player_service.TvTunerService"));
            startActivity(((Intent) (obj)));
            PxLog.v(TAG, "onResume() out. FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY");
            return;
        }
        if(intentOnResume == null)
        {
            PxLog.v(TAG, "onResume() out. intentOnResume == null");
            return;
        }
        boolean flag = processIntent(intentOnResume);
        intentOnResume = null;
        if(mLoadUrl != null)
        {
            webVewManager.loadUrl(this, mLoadUrl);
            mLoadUrl = null;
        } else
        if(!flag && obj == null)
            webVewManager.loadUrl(this, "file:///android_asset/initial/index.html");
        PxLog.v(TAG, "onResume() out");
    }

    protected void registerAtvAppReceiver()
    {
        BroadcastReceiver broadcastreceiver = mAtvAppReceiver;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(PACKAGE_NAME_PREFIX);
        stringbuilder.append("CHECK_ATV_APP_ACTION");
        registerReceiver(broadcastreceiver, new IntentFilter(stringbuilder.toString()));
    }

    protected void setLayout()
    {
        setContentView(R.layout.activity_main);
    }

    protected void unBindTvPlayerService()
    {
        if(mConnection == null)
        {
            PxLog.i(TAG, "already disconnect.");
            return;
        } else
        {
            unbindService(mConnection);
            mConnection = null;
            return;
        }
    }

    private static final String ACTION_FINISH_PLAYER_SERVICE = "ACTION_FINISH_PLAYER_SERVICE";
    protected static final String ACTION_PLAYER_SERVICE_FINISHED = "ACTION_PLAYER_SERVICE_FINISHED";
    protected static final String ACTION_REQUEST_FOREGROUND_SUFFIX = ".ACTION_REQUEST_FOREGROUND";
    private static final String ACTION_WEB_VIEW_FINISHED = "ACTION_WEB_VIEW_FINISHED";
    private static final String ACTION_WEB_VIEW_LOAD_URL = "ACTION_WEB_VIEW_LOAD_URL";
    protected static final String CHECK_ATV_APP_ACTION = "CHECK_ATV_APP_ACTION";
    private static final String DEFAULT_URL = "file:///android_asset/initial/index.html";
    private static final String DOLBY_DIGITAL_EXTRA = "DolbyDigital";
    protected static final String EXTRA_URL = "EXTRA_URL";
    private static final String IS_SHOW_RECORDING_SUPPORTED_DIALOG = "IS_SHOW_RECORDING_SUPPORTED_DIALOG";
    protected static final String KEY_EVENT = "key_event";
    protected static final String KEY_TV_ACTION = "action";
    protected static final String LOAD_URL_EXTRA = "URL";
    private static final String LOG_HEAD;
    protected static String PACKAGE_NAME_PREFIX;
    private static final String PLAYER_ACTIVITY_NAME = "jp.pixela.player_service.TvTunerService";
    protected static final String PLAYER_SERVICE_PACKAGE = "jp.pixela.stationtv.xit";
    private static final String PREVIEW_URL_SUFFIX = "preview/index.html";
    private static final String PX_FORCED_STEREO_SETTING_CANCELLATION = "com.android.tv.settings.action.PX_FORCED_STEREO_SETTING_CANCELLATION";
    private static final String RELOAD_CURRENT_URL = "Reload";
    private static final String RELOAD_EXTRA = "Reload";
    private static final String SETTINGS_ACTIVITY_NAME = "jp.pixela.view.TvSettingsActivity";
    private static final String SPECIAL_KEY_F10_RECEIVED = "jp.pixela.atv_app.SPECIAL_KEY_F10_RECEIVED";
    protected static final String SWITCH_TV = "switch TV";
    private static final String TAG = "MainActivity";
    protected static final String TV_COMMAND_ACTION_SUFFIX = ".ACTION_REQUEST_KEY_EVENT";
    private static final String TV_PLAYER_SERVICE_NAME = "jp.pixela.player_service.TvPlayerService";
    private final CommandChecker commandChecker = new CommandChecker();
    protected Intent intentOnResume;
    private final KeyEventConverter keyEventConverter = new KeyEventConverter();
    protected BroadcastReceiver mAtvAppReceiver;
    private TvPlayerServiceConnection mConnection;
    protected boolean mFinishOnBinderDied;
    protected boolean mIsForeground;
    private boolean mIsSupportDolbyDigital;
    private String mLoadUrl;
    protected BroadcastReceiver mLoadUrlReceiver;
    private BroadcastReceiver mPlayerServiceFinishedReceiver;
    private BroadcastReceiver mTvCommandReceiver;
    protected final WebViewManager webVewManager;

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/atv_app/MainActivity.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }





    // Unreferenced inner class jp/pixela/atv_app/MainActivity$TvPlayerServiceConnection$1

/* anonymous class */
    class TvPlayerServiceConnection._cls1
        implements android.os.IBinder.DeathRecipient
    {

        public void binderDied()
        {
            if(mBinder != null)
                mBinder.unlinkToDeath(this, 0);
            if(mFinishOnBinderDied)
            {
                sendBroadcastRestoreStereoSetting();
                finishActivity();
            }
        }

        final TvPlayerServiceConnection this$1;

            
            {
                this$1 = TvPlayerServiceConnection.this;
                super();
            }
    }

}
