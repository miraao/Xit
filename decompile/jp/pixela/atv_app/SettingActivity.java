// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.atv_app;

import android.content.*;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import jp.pixela.atv_app.common.IntentHelper;
import jp.pixela.atv_app.common.PxLog;

// Referenced classes of package jp.pixela.atv_app:
//            MainActivity, WebViewManager

public class SettingActivity extends MainActivity
{

    public SettingActivity()
    {
        mInitializingProgress = null;
        mInitializing = false;
        mIsBackKeyDown = false;
    }

    private void sendFinishSetting()
    {
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append(PACKAGE_NAME_PREFIX);
        ((StringBuilder) (obj)).append("ACTION_FINISH_SETTING");
        obj = new Intent(((StringBuilder) (obj)).toString());
        ((Intent) (obj)).setPackage("jp.pixela.stationtv.xit");
        sendBroadcast(((Intent) (obj)));
    }

    private void updateInitializingProgress()
    {
        if(mInitializingProgress != null)
        {
            View view = mInitializingProgress;
            byte byte0;
            if(mInitializing)
                byte0 = 0;
            else
                byte0 = 8;
            view.setVisibility(byte0);
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        if(mInitializing)
        {
            if(isBackKeyPressed(keyevent))
                sendFinishSetting();
            return true;
        } else
        {
            return super.dispatchKeyEvent(keyevent);
        }
    }

    protected void finishActivity()
    {
        finish();
    }

    public boolean isBackKeyPressed(KeyEvent keyevent)
    {
        if(keyevent.isCanceled())
            return false;
        if(keyevent.getKeyCode() != 4)
            return false;
        if(keyevent.getAction() == 0)
        {
            mIsBackKeyDown = true;
            return false;
        }
        if(keyevent.getAction() == 1 && mIsBackKeyDown)
        {
            mIsBackKeyDown = false;
            return true;
        } else
        {
            mIsBackKeyDown = false;
            return false;
        }
    }

    protected void onCreate(Bundle bundle)
    {
        PxLog.v(TAG, "onCreate() in");
        super.onCreate(bundle);
        mInitializingProgress = findViewById(R.id.progressBar);
        bundle = mLoadUrlReceiver;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(PACKAGE_NAME_PREFIX);
        stringbuilder.append("ACTION_WEB_VIEW_LOAD_URL_FOR_SETTING");
        registerReceiver(bundle, new IntentFilter(stringbuilder.toString()));
        bundle = getIntent();
        if(bundle != null)
            mInitializing = bundle.getBooleanExtra("Initializing", false);
        else
            mInitializing = false;
        updateInitializingProgress();
        PxLog.v(TAG, "onCreate() out");
    }

    protected void onNewIntent(Intent intent)
    {
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("onNewIntent() in. intent=");
        stringbuilder.append(IntentHelper.dump(intent));
        PxLog.v(s, stringbuilder.toString());
        super.onNewIntent(intent);
        if(mInitializing && intent != null && !intent.getBooleanExtra("Initializing", true))
        {
            mInitializing = false;
            updateInitializingProgress();
            intent = intent.getStringExtra("URL");
            if(intent != null)
                webVewManager.loadUrl(this, intent);
        }
        intentOnResume = null;
        PxLog.v(TAG, "onNewIntent() out");
    }

    protected void onReceiveFinishIntent(Context context, Intent intent)
    {
        context = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("onReceiveFinishIntent() in. intent=");
        stringbuilder.append(IntentHelper.dump(intent));
        PxLog.v(context, stringbuilder.toString());
        if(intent == null)
            return;
        context = intent.getAction();
        if(context == null)
            return;
        if(!context.equals("jp.pixela.stationtv.xit.ACTION_PLAYER_SERVICE_FINISHED"))
            return;
        context = intent.getStringExtra("FinishActivty");
        if(context != null && context.compareToIgnoreCase("SettingActivity") == 0)
            finishActivity();
        PxLog.v(TAG, "onReceiveFinishIntent() out");
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
                dispatchKeyEvent((KeyEvent)context);
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
            broadcastreceiver.setResultCode(-1);
            return;
        }
        intent = new StringBuilder();
        intent.append(PACKAGE_NAME_PREFIX);
        intent.append("ACTION_PREPARE_RESTART");
        if(context.equals(intent.toString()))
        {
            mFinishOnBinderDied = false;
            unBindTvPlayerService();
            broadcastreceiver.setResultCode(-1);
            return;
        } else
        {
            PxLog.v(TAG, "onReceiveIntentFromTV() out");
            return;
        }
    }

    protected void onReceiveLoadUrlIntent(Context context, Intent intent)
    {
label0:
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
            stringbuilder.append("ACTION_WEB_VIEW_LOAD_URL_FOR_SETTING");
            if(!context.equals(stringbuilder.toString()))
            {
                intent = TAG;
                StringBuilder stringbuilder1 = new StringBuilder();
                stringbuilder1.append("onReceiveLoadUrlIntent() out. !action.equals(");
                stringbuilder1.append(PACKAGE_NAME_PREFIX);
                stringbuilder1.append("ACTION_WEB_VIEW_LOAD_URL_FOR_SETTING");
                stringbuilder1.append(") action=");
                stringbuilder1.append(context);
                PxLog.w(intent, stringbuilder1.toString());
                return;
            }
            context = intent.getStringExtra("EXTRA_URL");
            if(context != null)
            {
                intent = context;
                if(context.length() != 0)
                    break label0;
            }
            intent = "file:///android_asset/setting/index.html";
        }
        Object obj = webVewManager.getUrl(this);
        context = TAG;
        StringBuilder stringbuilder2 = new StringBuilder();
        stringbuilder2.append("currentURL:");
        stringbuilder2.append(((String) (obj)));
        stringbuilder2.append(", url:");
        stringbuilder2.append(intent);
        PxLog.d(context, stringbuilder2.toString());
        context = intent;
        if(obj != null)
        {
            context = intent;
            if(((String) (obj)).startsWith("file:///android_asset/initial/index.html"))
                if(intent.equals("file:///android_asset/setting/index.html#/google-assistant-setting-success"))
                {
                    obj = ((String) (obj)).split("#", 0);
                    context = intent;
                    if(obj.length > 0)
                    {
                        context = new StringBuilder();
                        context.append(obj[0]);
                        context.append("#/GoogleAssistantSettingSuccess");
                        context = context.toString();
                    }
                } else
                if(intent.equals("file:///android_asset/setting/index.html#/google-assistant-setting-failure"))
                {
                    obj = ((String) (obj)).split("#", 0);
                    context = intent;
                    if(obj.length > 0)
                    {
                        context = new StringBuilder();
                        context.append(obj[0]);
                        context.append("#/GoogleAssistantSettingFailure");
                        context = context.toString();
                    }
                } else
                if(intent.equals("file:///android_asset/setting/index.html#/google-assistant-setting-cancel"))
                {
                    obj = ((String) (obj)).split("#", 0);
                    context = intent;
                    if(obj.length > 0)
                    {
                        context = new StringBuilder();
                        context.append(obj[0]);
                        context.append("#/GoogleAssistantSettingCancel");
                        context = context.toString();
                    }
                } else
                {
                    context = intent;
                    if(intent.equals("file:///android_asset/setting/index.html#/google-assistant-notice-3"))
                    {
                        String as[] = ((String) (obj)).split("#", 0);
                        context = intent;
                        if(as.length > 0)
                        {
                            context = new StringBuilder();
                            context.append(as[0]);
                            context.append("#/GoogleAssistantNotice3Wrapper");
                            context = context.toString();
                        }
                    }
                }
        }
        webVewManager.loadUrl(this, context);
        PxLog.v(TAG, "onReceiveLoadUrlIntent() out");
    }

    protected void registerAtvAppReceiver()
    {
        IntentFilter intentfilter = new IntentFilter();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(PACKAGE_NAME_PREFIX);
        stringbuilder.append("CHECK_ATV_APP_ACTION");
        intentfilter.addAction(stringbuilder.toString());
        stringbuilder = new StringBuilder();
        stringbuilder.append(PACKAGE_NAME_PREFIX);
        stringbuilder.append("ACTION_PREPARE_RESTART");
        intentfilter.addAction(stringbuilder.toString());
        registerReceiver(mAtvAppReceiver, intentfilter);
    }

    protected void setLayout()
    {
        setContentView(R.layout.activity_setting);
    }

    private static final String ACTION_FINISH_SETTING = "ACTION_FINISH_SETTING";
    private static final String ACTION_PREPARE_RESTART = "ACTION_PREPARE_RESTART";
    private static final String ACTION_WEB_VIEW_LOAD_URL_FOR_SETTING = "ACTION_WEB_VIEW_LOAD_URL_FOR_SETTING";
    private static final String DEFAULT_URL_FOR_SETTING = "file:///android_asset/setting/index.html";
    private static final String TAG = "SettingActivity";
    private boolean mInitializing;
    private View mInitializingProgress;
    private boolean mIsBackKeyDown;

}
