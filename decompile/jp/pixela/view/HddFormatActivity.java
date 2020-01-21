// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.view;

import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import java.util.Arrays;
import jp.pixela.common.*;
import jp.pixela.pxlibs.utils.android.log.LoggerRTM;

// Referenced classes of package jp.pixela.view:
//            WebViewListener, WebViewManager, CommandChecker

public class HddFormatActivity extends Activity
    implements WebViewListener, jp.pixela.common.HddFormatManager.HddFormatListener
{
    public static final class FormatResultCode extends Enum
    {

        public static FormatResultCode valueOf(String s)
        {
            return (FormatResultCode)Enum.valueOf(jp/pixela/view/HddFormatActivity$FormatResultCode, s);
        }

        public static FormatResultCode[] values()
        {
            return (FormatResultCode[])$VALUES.clone();
        }

        public int getValue()
        {
            return mResultCode;
        }

        private static final FormatResultCode $VALUES[];
        public static final FormatResultCode FAILED;
        public static final FormatResultCode OK;
        public static final FormatResultCode RESTART;
        private final int mResultCode;

        static 
        {
            OK = new FormatResultCode("OK", 0, -1);
            FAILED = new FormatResultCode("FAILED", 1, 0);
            RESTART = new FormatResultCode("RESTART", 2, 100);
            $VALUES = (new FormatResultCode[] {
                OK, FAILED, RESTART
            });
        }

        private FormatResultCode(String s, int i, int j)
        {
            super(s, i);
            mResultCode = j;
        }
    }


    public HddFormatActivity()
    {
        mHddFormatManager = new HddFormatManager();
        mWaitingForMounted = false;
        mMediaMounted = false;
        mIsActivate = false;
        mWaitMountHandler = null;
        mReceiver = null;
        mShowMode = 0;
    }

    private void finishApp(FormatResultCode formatresultcode)
    {
        if(formatresultcode == FormatResultCode.RESTART)
            startPowerActivity();
        Intent intent = new Intent();
        intent.putExtra("ShowMode", mShowMode);
        setResult(formatresultcode.getValue(), intent);
        finish();
    }

    private void onComplete()
    {
        String s = String.format("showCompleteMessage(%d, %d);", new Object[] {
            Integer.valueOf(0), Integer.valueOf(1)
        });
        webViewManager.evaluateJavascript(this, s);
        (new Handler()).postDelayed(new Runnable() {

            public void run()
            {
                finishApp(FormatResultCode.RESTART);
            }

            final HddFormatActivity this$0;

            
            {
                this$0 = HddFormatActivity.this;
                super();
            }
        }
, 5000L);
    }

    private void onFailure(int i)
    {
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append("file:///android_asset/hddformat/index.html#/hdd-format-complete?");
        ((StringBuilder) (obj)).append(String.format("result=%d&messageId=%d", new Object[] {
            Integer.valueOf(i), Integer.valueOf(0)
        }));
        obj = ((StringBuilder) (obj)).toString();
        webViewManager.loadUrl(this, ((String) (obj)));
        (new Handler()).postDelayed(new Runnable() {

            public void run()
            {
                finishApp(FormatResultCode.FAILED);
            }

            final HddFormatActivity this$0;

            
            {
                this$0 = HddFormatActivity.this;
                super();
            }
        }
, 5000L);
    }

    private void onSuccess()
    {
        mWaitingForMounted = true;
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append("file:///android_asset/hddformat/index.html#/hdd-format-complete?");
        ((StringBuilder) (obj)).append(String.format("result=%d&messageId=%d", new Object[] {
            Integer.valueOf(0), Integer.valueOf(0)
        }));
        obj = ((StringBuilder) (obj)).toString();
        webViewManager.loadUrl(this, ((String) (obj)));
        mWaitMountHandler = new Handler();
        mWaitMountHandler.postDelayed(new Runnable() {

            public void run()
            {
                if(mWaitingForMounted && !mMediaMounted)
                    if(mIsActivate)
                    {
                        mWaitingForMounted = false;
                        onComplete();
                    } else
                    {
                        mMediaMounted = true;
                    }
            }

            final HddFormatActivity this$0;

            
            {
                this$0 = HddFormatActivity.this;
                super();
            }
        }
, 60000L);
    }

    private void startPowerActivity()
    {
        Intent intent = new Intent();
        intent.setClassName("com.android.tv.settings", "com.android.tv.settings.system.PowerKeyActivity");
        try
        {
            startActivity(intent);
        }
        catch(Exception exception)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(LOG_HEAD);
            stringbuilder.append("e=");
            stringbuilder.append(exception);
            LoggerRTM.e(stringbuilder.toString(), new Object[0]);
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("dispatchKeyEvent() in. event=");
        stringbuilder.append(keyevent);
        PxLog.v(s, stringbuilder.toString());
        if(commandChecker.dispatchKeyEvent(keyevent))
        {
            PxLog.v(TAG, "dispatchKeyEvent() out. by commandChecker");
            return true;
        }
        if(webViewManager.dispatchKeyEvent(this, keyevent))
        {
            PxLog.v(TAG, "dispatchKeyEvent() out. by webViewManager");
            return true;
        } else
        {
            boolean flag = super.dispatchKeyEvent(keyevent);
            String s1 = TAG;
            keyevent = new StringBuilder();
            keyevent.append("dispatchKeyEvent() out. isConsumed=");
            keyevent.append(flag);
            PxLog.v(s1, keyevent.toString());
            return flag;
        }
    }

    public void onCommandFromWebView(String s)
    {
        if(s == null)
            return;
        if("request_storege_permission".equals(s))
            RuntimePermissionUtils.requestPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE", 1000);
        else
        if("finish_app".equals(s))
            finishApp(FormatResultCode.FAILED);
        else
        if("exec_hdd_format".equals(s))
            if(mShowMode == 0 && HddFormatManager.isFormatted(this))
            {
                webViewManager.evaluateJavascript(this, "showMessageDialog();");
            } else
            {
                mWaitingForMounted = false;
                mMediaMounted = false;
                webViewManager.loadUrl(this, "file:///android_asset/hddformat/index.html#/hdd-format-formatting");
                mHddFormatManager.startFormat();
            }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f0a001b);
        Object obj = getIntent();
        if(obj == null)
        {
            finishApp(FormatResultCode.FAILED);
            return;
        }
        bundle = ((Intent) (obj)).getAction();
        if(bundle != null && bundle.equals("requestHddFormat"))
        {
            mShowMode = ((Intent) (obj)).getIntExtra("ShowMode", 0);
            mWaitingForMounted = false;
            mIsActivate = false;
            mMediaMounted = false;
            bundle = mHddFormatManager;
            obj = new StringBuilder();
            ((StringBuilder) (obj)).append(getPackageName());
            ((StringBuilder) (obj)).append(".USB_PERMISSION");
            bundle.initialize(this, ((StringBuilder) (obj)).toString());
            mHddFormatManager.registerHddFormatListener(this);
            mHddFormatManager.addSupportedDevice("PIX-SMB100", 1720, 4164);
            mHddFormatManager.addSupportedDevice("PIX-SMB100", 5421, 1400);
            mHddFormatManager.searchDevices();
            if(!mHddFormatManager.isConnectedDevice())
            {
                finishApp(FormatResultCode.FAILED);
                return;
            } else
            {
                webViewManager.initializeWebView(this, this);
                bundle = new StringBuilder();
                bundle.append("file:///android_asset/hddformat/index.html#/hdd-format-start?");
                bundle.append(String.format("showMode=%d", new Object[] {
                    Integer.valueOf(mShowMode)
                }));
                bundle = bundle.toString();
                webViewManager.loadUrl(this, bundle);
                mReceiver = new BroadcastReceiver() {

                    public void onReceive(Context context, Intent intent)
                    {
                        if(mWaitingForMounted)
                            mMediaMounted = true;
                    }

                    final HddFormatActivity this$0;

            
            {
                this$0 = HddFormatActivity.this;
                super();
            }
                }
;
                bundle = new IntentFilter();
                bundle.addAction("android.intent.action.MEDIA_MOUNTED");
                bundle.addDataScheme("file");
                registerReceiver(mReceiver, bundle);
                return;
            }
        } else
        {
            finishApp(FormatResultCode.FAILED);
            return;
        }
    }

    protected void onDestroy()
    {
        webViewManager.finalizeWebView(this);
        super.onDestroy();
        if(mReceiver != null)
        {
            unregisterReceiver(mReceiver);
            mReceiver = null;
        }
    }

    public void onLoadFinished(String s)
    {
        if(s == null)
            return;
        if(s.startsWith("file:///android_asset/hddformat/index.html#/hdd-format-start"))
        {
            s = String.format("setDeviceName('%s');", new Object[] {
                "PIX-SMB100"
            });
            webViewManager.evaluateJavascript(this, s);
        }
    }

    protected void onPause()
    {
        super.onPause();
        mIsActivate = false;
    }

    public boolean onProgressed(final int current)
    {
        runOnUiThread(new Runnable() {

            public void run()
            {
                String s = String.format("setFormatProgress(%d);", new Object[] {
                    Integer.valueOf(current)
                });
                webViewManager.evaluateJavascript(HddFormatActivity.this, s);
            }

            final HddFormatActivity this$0;
            final int val$current;

            
            {
                this$0 = HddFormatActivity.this;
                current = i;
                super();
            }
        }
);
        return false;
    }

    public void onRequestPermissionsResult(int i, String as[], int ai[])
    {
        String s = TAG;
        as = new StringBuilder();
        as.append("onRequestPermissionsResult ");
        as.append(i);
        as.append(", ");
        as.append(Arrays.toString(ai));
        PxLog.d(s, as.toString());
        if(i == 1000)
            if(ai.length > 0 && ai[0] == 0)
            {
                setResult(-1);
                finish();
            } else
            {
                webViewManager.loadUrl(this, "file:///android_asset/hddformat/index.html#/storage_permission_error");
            }
    }

    public void onResult(final int result)
    {
        runOnUiThread(new Runnable() {

            public void run()
            {
                if(result == 0)
                    onSuccess();
                else
                    onFailure(result);
            }

            final HddFormatActivity this$0;
            final int val$result;

            
            {
                this$0 = HddFormatActivity.this;
                result = i;
                super();
            }
        }
);
    }

    protected void onResume()
    {
        super.onResume();
        mIsActivate = true;
        if(mWaitingForMounted && mMediaMounted)
        {
            mWaitingForMounted = false;
            mMediaMounted = false;
            onComplete();
        }
    }

    public static final int INITIAL_MODE = 0;
    private static final String LOG_HEAD;
    private static final int MOUNT_TIMEOUT = 60000;
    private static final String POWER_ACTIVITY_CLASS_NAME = "com.android.tv.settings.system.PowerKeyActivity";
    private static final String POWER_ACTIVITY_PACKAGE_NAME = "com.android.tv.settings";
    public static final String REQUEST_HDD_FORMAT = "requestHddFormat";
    public static final String REQUEST_PERMISSION_ACTION = "requestPermission";
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION = 1000;
    public static final int SETTING_MODE = 1;
    private static final int SHOW_MESSAGE_TIMEOUT = 5000;
    public static final String SHOW_MODE_KEY = "ShowMode";
    public static final String TAG = "HddFormatActivity";
    private final CommandChecker commandChecker = new CommandChecker();
    private HddFormatManager mHddFormatManager;
    private boolean mIsActivate;
    private boolean mMediaMounted;
    private BroadcastReceiver mReceiver;
    private int mShowMode;
    private Handler mWaitMountHandler;
    private boolean mWaitingForMounted;
    private final WebViewManager webViewManager = new WebViewManager();

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/view/HddFormatActivity.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }



/*
    static boolean access$002(HddFormatActivity hddformatactivity, boolean flag)
    {
        hddformatactivity.mWaitingForMounted = flag;
        return flag;
    }

*/



/*
    static boolean access$102(HddFormatActivity hddformatactivity, boolean flag)
    {
        hddformatactivity.mMediaMounted = flag;
        return flag;
    }

*/






}
