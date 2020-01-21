// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import jp.pixela.common.ApplicationUtility;
import jp.pixela.common.BmlOutputArea;
import jp.pixela.common.BuildUtilityWrapper;
import jp.pixela.common.FontManager;
import jp.pixela.common.HddFormatManager;
import jp.pixela.common.IntentHelper;
import jp.pixela.common.PlayerMessenger;
import jp.pixela.common.ProductInfo;
import jp.pixela.common.PxLog;
import jp.pixela.common.WakelockHelper;
import jp.pixela.pis_client.rest.rank.ViewingPointData;
import jp.pixela.pis_client.rest.thumbnail.ThumbnailData;
import jp.pixela.pis_client.user.RecommendApiClientUser;
import jp.pixela.pis_client.user.ThumbnailApiClientUser;
import jp.pixela.pis_client.user.ViewingPointApiClientUser;
import jp.pixela.player_service.browser.ContentShellManager;
import jp.pixela.player_service.message.AutoMessageInfo;
import jp.pixela.player_service.message.BmlSoundInfo;
import jp.pixela.player_service.message.BrowserStateInfo;
import jp.pixela.player_service.message.BrowserUrlInfo;
import jp.pixela.player_service.message.CognitiveSettingParam;
import jp.pixela.player_service.message.DeviceLogData;
import jp.pixela.player_service.message.KeyEventInfo;
import jp.pixela.player_service.message.OutputDeviceInfo;
import jp.pixela.player_service.message.ProgramData;
import jp.pixela.player_service.message.RecPlayData;
import jp.pixela.player_service.message.RecommendInfo;
import jp.pixela.player_service.message.ReservationData;
import jp.pixela.player_service.message.ThumbnailInfo;
import jp.pixela.player_service.message.ViewingPointInfo;
import jp.pixela.player_service.tunerservice.BmlSoundPlayer;
import jp.pixela.player_service.tunerservice.BroadcastTypeT;
import jp.pixela.player_service.tunerservice.BroadcastWave;
import jp.pixela.player_service.tunerservice.BrowserType;
import jp.pixela.player_service.tunerservice.ControlInterface;
import jp.pixela.player_service.tunerservice.LNBSettings;
import jp.pixela.player_service.tunerservice.LNBStateReceiverInternal;
import jp.pixela.player_service.tunerservice.TimeChangedReceiver;
import jp.pixela.player_service.tunerservice.TunerService;
import jp.pixela.player_service.tunerservice.UpdateEPGReceiver;
import jp.pixela.player_service.tunerservice.UsbDeviceObserver;
import jp.pixela.pxlibs.utils.android.log.Logger;
import jp.pixela.searchable_program.DatabaseUpdater;
import jp.pixela.searchable_program.DatabaseUpdaterHandler;
import jp.pixela.searchable_program.ExtraDataHelper;
import jp.pixela.searchable_program.ProgramInfoOpenHelper;
import jp.pixela.searchable_program.RecordInfoOpenHelper;
import jp.pixela.view.HddFormatActivity;
import jp.pixela.view.TunerTimeDiffService;
import jp.pixela.view.TvSettingsActivity;

// Referenced classes of package jp.pixela.player_service:
//            AutoMessageView, TunerServiceManager, DirectVideoView, DeviceDefines, 
//            SendLogToEventHub

public class TvTunerService extends Activity
    implements jp.pixela.common.PlayerMessenger.PlayerObserverInterface, TunerServiceManager.TunerServiceInterface, jp.pixela.player_service.tunerservice.LNBStateReceiverInternal.LNBStateReceiverInterface, jp.pixela.player_service.tunerservice.UsbDeviceObserver.UsbAudioEventListener
{
    private final class IoTEdgeCommandReceiver extends BroadcastReceiver
    {

        public void onReceive(Context context, Intent intent)
        {
            String s = intent.getAction();
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("action:");
            stringbuilder.append(s);
            PxLog.v("tv_tuner_service", stringbuilder.toString());
            if(!"jp.co.pixela.pis_iot_edge.BROADCAST".equals(s))
                break MISSING_BLOCK_LABEL_1291;
            Object obj;
            int i;
            i = intent.getIntExtra("CONTROL", -1);
            obj = JVM INSTR new #40  <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            ((StringBuilder) (obj)).append("command=");
            ((StringBuilder) (obj)).append(i);
            PxLog.d("tv_tuner_service", ((StringBuilder) (obj)).toString());
            obj = JVM INSTR new #82  <Class BuildUtilityWrapper>;
            ((BuildUtilityWrapper) (obj)).BuildUtilityWrapper(getApplicationContext());
            int j;
            boolean flag;
            j = 1;
            flag = true;
            if(i == 200) goto _L2; else goto _L1
_L1:
            i;
            JVM INSTR tableswitch 0 2: default 152
        //                       0 1157
        //                       1 953
        //                       2 802;
               goto _L3 _L4 _L5 _L6
_L3:
            i;
            JVM INSTR tableswitch 100 102: default 180
        //                       100 527
        //                       101 377
        //                       102 183;
               goto _L7 _L8 _L9 _L10
_L7:
            break MISSING_BLOCK_LABEL_1291;
_L10:
            if(((BuildUtilityWrapper) (obj)).isSmartSpeakerPropControl())
                break MISSING_BLOCK_LABEL_1291;
            i = intent.getIntExtra("VALUE", 0);
            context = JVM INSTR new #40  <Class StringBuilder>;
            context.StringBuilder();
            context.append("value=");
            context.append(i);
            PxLog.d("tv_tuner_service", context.toString());
            context = (AudioManager)getSystemService("audio");
            double d = context.getStreamMaxVolume(3);
            double d1 = Math.floor(100D / d);
            j = (int)Math.round(Math.floor(((double)i + d1) * (d / 100D)));
            intent = JVM INSTR new #40  <Class StringBuilder>;
            intent.StringBuilder();
            intent.append("volume=");
            intent.append(j);
            PxLog.d("tv_tuner_service", intent.toString());
            if(j != 0) goto _L12; else goto _L11
_L11:
            context.setStreamVolume(3, 1, 0);
              goto _L13
_L12:
            context.setStreamVolume(3, j, 1);
            flag = false;
_L13:
            int k;
            boolean flag1;
            boolean flag2;
            int l;
            int i1;
            if(flag)
                try
                {
                    context = JVM INSTR new #129 <Class Thread>;
                    intent = JVM INSTR new #15  <Class TvTunerService$IoTEdgeCommandReceiver$4>;
                    i. super();
                    context.Thread(intent);
                    context.start();
                }
                // Misplaced declaration of an exception variable
                catch(Context context) { }
            break MISSING_BLOCK_LABEL_1291;
_L9:
            if(((BuildUtilityWrapper) (obj)).isSmartSpeakerPropControl())
                break MISSING_BLOCK_LABEL_1291;
            flag1 = intent.getBooleanExtra("VALUE", true);
            intent = JVM INSTR new #40  <Class StringBuilder>;
            intent.StringBuilder();
            intent.append("value=");
            intent.append(flag1);
            PxLog.d("tv_tuner_service", intent.toString());
            flag2 = ((AudioManager)context.getSystemService("audio")).isStreamMute(3);
            context = JVM INSTR new #40  <Class StringBuilder>;
            context.StringBuilder();
            context.append("IOT_EDGE_BROADCAST_MUTE_VOLUME currentMute =");
            context.append(flag2);
            PxLog.d("tv_tuner_service", context.toString());
            if(!flag1 && !flag2 || flag1 && flag2)
                break MISSING_BLOCK_LABEL_1291;
            intent = JVM INSTR new #129 <Class Thread>;
            context = JVM INSTR new #13  <Class TvTunerService$IoTEdgeCommandReceiver$3>;
            flag1. super();
            intent.Thread(context);
            intent.start();
            break MISSING_BLOCK_LABEL_1291;
_L8:
            if(((BuildUtilityWrapper) (obj)).isSmartSpeakerPropControl())
                break MISSING_BLOCK_LABEL_1291;
            l = intent.getIntExtra("VALUE", 0);
            context = JVM INSTR new #40  <Class StringBuilder>;
            context.StringBuilder();
            context.append("value=");
            context.append(l);
            PxLog.d("tv_tuner_service", context.toString());
            if(l == 0)
                break MISSING_BLOCK_LABEL_1291;
            context = (AudioManager)getSystemService("audio");
            k = context.getStreamVolume(3);
            intent = JVM INSTR new #40  <Class StringBuilder>;
            intent.StringBuilder();
            intent.append("currentVolume=");
            intent.append(k);
            PxLog.d("tv_tuner_service", intent.toString());
            i1 = context.getStreamMaxVolume(3);
            intent = JVM INSTR new #40  <Class StringBuilder>;
            intent.StringBuilder();
            intent.append("maxVolume=");
            intent.append(i1);
            PxLog.d("tv_tuner_service", intent.toString());
            i = k + l;
            k = i;
            if(i < 0)
                k = 0;
            i = k;
            if(k > i1)
                i = i1;
            intent = JVM INSTR new #40  <Class StringBuilder>;
            intent.StringBuilder();
            intent.append("volume=");
            intent.append(i);
            PxLog.d("tv_tuner_service", intent.toString());
            if(i != 0) goto _L15; else goto _L14
_L14:
            context.setStreamVolume(3, 1, 0);
            k = j;
              goto _L16
_L15:
            context.setStreamVolume(3, i, 1);
            k = 0;
_L16:
            if(k == 0)
                break MISSING_BLOCK_LABEL_1291;
            intent = JVM INSTR new #129 <Class Thread>;
            context = JVM INSTR new #11  <Class TvTunerService$IoTEdgeCommandReceiver$2>;
            l. super();
            intent.Thread(context);
            intent.start();
            break MISSING_BLOCK_LABEL_1291;
_L6:
            if(!TunerServiceManager.getInstance().isInit())
                break MISSING_BLOCK_LABEL_1291;
            context = TunerServiceManager.getInstance().getTunerService();
            if(context == null)
                break MISSING_BLOCK_LABEL_1291;
            obj = intent.getStringExtra("BROADCASTING");
            intent = intent.getIntArrayExtra("VALUES");
            if(obj != null) goto _L18; else goto _L17
_L17:
            context.SelectChannel(null, intent);
            break MISSING_BLOCK_LABEL_1291;
_L18:
            if(((String) (obj)).equals("TR"))
            {
                context.SelectChannel(BroadcastTypeT.BROADCAST_TYPE_TR, intent);
                break MISSING_BLOCK_LABEL_1291;
            }
            if(((String) (obj)).equals("BS"))
            {
                context.SelectChannel(BroadcastTypeT.BROADCAST_TYPE_BS, intent);
                break MISSING_BLOCK_LABEL_1291;
            }
            if(((String) (obj)).equals("CS"))
            {
                context.SelectChannel(BroadcastTypeT.BROADCAST_TYPE_CS, intent);
                break MISSING_BLOCK_LABEL_1291;
            }
            if(((String) (obj)).equals("4K"))
            {
                context.SelectChannel(BroadcastTypeT.BROADCAST_TYPE_4K, intent);
                break MISSING_BLOCK_LABEL_1291;
            }
            context.SelectChannel(null, intent);
            break MISSING_BLOCK_LABEL_1291;
_L5:
            if(!TunerServiceManager.getInstance().isInit())
                break MISSING_BLOCK_LABEL_1291;
            context = intent.getStringExtra("BROADCASTING");
            k = intent.getIntExtra("VALUE", 0);
            if(k >= 1 && k <= 999) goto _L20; else goto _L19
_L20:
            intent = TunerServiceManager.getInstance().getTunerService();
            if(intent == null)
                break MISSING_BLOCK_LABEL_1291;
            if(context != null) goto _L22; else goto _L21
_L21:
            intent.SelectChannelByDirectNumber(null, k);
            break MISSING_BLOCK_LABEL_1291;
_L22:
            if(context.equals("TR"))
            {
                intent.SelectChannelByDirectNumber(BroadcastTypeT.BROADCAST_TYPE_TR, k);
                break MISSING_BLOCK_LABEL_1291;
            }
            if(context.equals("BS"))
            {
                intent.SelectChannelByDirectNumber(BroadcastTypeT.BROADCAST_TYPE_BS, k);
                break MISSING_BLOCK_LABEL_1291;
            }
            if(context.equals("CS"))
            {
                intent.SelectChannelByDirectNumber(BroadcastTypeT.BROADCAST_TYPE_CS, k);
                break MISSING_BLOCK_LABEL_1291;
            }
            if(context.equals("4K"))
            {
                intent.SelectChannelByDirectNumber(BroadcastTypeT.BROADCAST_TYPE_4K, k);
                break MISSING_BLOCK_LABEL_1291;
            }
            intent.SelectChannelByDirectNumber(null, k);
            break MISSING_BLOCK_LABEL_1291;
_L19:
            context = JVM INSTR new #40  <Class StringBuilder>;
            context.StringBuilder();
            context.append("Invalid Channel Number: ");
            context.append(k);
            PxLog.e("tv_tuner_service", context.toString());
            break MISSING_BLOCK_LABEL_1291;
_L4:
            k = intent.getIntExtra("VALUE", 0);
            context = JVM INSTR new #40  <Class StringBuilder>;
            context.StringBuilder();
            context.append("value=");
            context.append(k);
            PxLog.d("tv_tuner_service", context.toString());
            intent = JVM INSTR new #129 <Class Thread>;
            context = JVM INSTR new #9   <Class TvTunerService$IoTEdgeCommandReceiver$1>;
            k. super();
            intent.Thread(context);
            intent.start();
            break MISSING_BLOCK_LABEL_1291;
_L2:
            flag2 = intent.getBooleanExtra("VALUE", true);
            context = JVM INSTR new #40  <Class StringBuilder>;
            context.StringBuilder();
            context.append("value=");
            context.append(flag2);
            PxLog.d("tv_tuner_service", context.toString());
            if(flag2)
                break MISSING_BLOCK_LABEL_1291;
            context = JVM INSTR new #231 <Class Message>;
            context.Message();
            context.what = 100;
            notify(context);
        }

        final TvTunerService this$0;

        private IoTEdgeCommandReceiver()
        {
            this$0 = TvTunerService.this;
            super();
        }

    }

    private final class PisIotEdgeControlResultReceiver extends BroadcastReceiver
    {

        public void onReceive(Context context, Intent intent)
        {
            context = new StringBuilder();
            context.append("PisIotEdgeControlResultReceiver onReceive in. intent=");
            context.append(IntentHelper.dump(intent));
            PxLog.v("tv_tuner_service", context.toString());
            mAbortFinishOnStop = false;
            if(!"jp.pixela.stationtv.xit.ACTION_PIS_IOT_EDGE_CONTROL_RESULT".equals(intent.getAction()))
            {
                PxLog.v("tv_tuner_service", "PisIotEdgeControlResultReceiver onReceive out. invalid action");
                return;
            }
            int i = intent.getIntExtra("EXTRA_COMMAND", -1);
            int j = intent.getIntExtra("EXTRA_RESULT", 2);
label0:
            switch(i)
            {
            default:
                break;

            case 2: // '\002'
                startPisIotEdgeMainActivity(0);
                break;

            case 1: // '\001'
                startPisIotEdgeMainActivity(0);
                if(j != 1)
                {
                    switch(j)
                    {
                    default:
                        if(TvTunerService.isInitialSettingsState(getApplicationContext()))
                            loadUrl("file:///android_asset/initial/index.html#/GoogleAssistantSettingFailure", true);
                        else
                            loadUrl("file:///android_asset/setting/index.html#/google-assistant-setting-failure", false);
                        break label0;

                    case 4: // '\004'
                        if(TvTunerService.isInitialSettingsState(getApplicationContext()))
                            loadUrl("file:///android_asset/initial/index.html#/GoogleAssistantSettingCancel", true);
                        else
                            loadUrl("file:///android_asset/setting/index.html#/google-assistant-setting-cancel", false);
                        break label0;

                    case 3: // '\003'
                        break;
                    }
                    if(TvTunerService.isInitialSettingsState(getApplicationContext()))
                        loadUrl("file:///android_asset/initial/index.html#/GoogleAssistantNotice3Wrapper", true);
                    else
                        loadUrl("file:///android_asset/setting/index.html#/google-assistant-notice-3", false);
                    break;
                }
                if(TvTunerService.isInitialSettingsState(getApplicationContext()))
                    loadUrl("file:///android_asset/initial/index.html#/GoogleAssistantSettingSuccess", true);
                else
                    loadUrl("file:///android_asset/setting/index.html#/google-assistant-setting-success", false);
                break;
            }
            PxLog.v("tv_tuner_service", "PisIotEdgeControlResultReceiver onReceive out.");
        }

        static final boolean $assertionsDisabled = false;
        final TvTunerService this$0;


        private PisIotEdgeControlResultReceiver()
        {
            this$0 = TvTunerService.this;
            super();
        }

    }


    public TvTunerService()
    {
        mState = 0;
        captionView = null;
        isFirst = true;
        mPreparingTime = 0;
        mCurrentPreparingTime = 0;
        mVideoViewLayout = null;
        mVideoViewLayoutMeasurable = false;
        autoMessageView = null;
        mSplashAnimView = null;
        mBmlSoundPlayer = null;
        settingsPath_ = null;
        mCurrentBrowserUsedKeyFlag = jp.pixela.player_service.tunerservice.BrowserType.KeyGroup.KeyGroup_DButton.getValue();
        mLastBrowserDownKeyCode = 0;
        isAppFinishingForRequest = false;
        intentOnInit = null;
        mPlayerObserver = null;
        mIsFormattedHdd = false;
        mAbortFinishOnStop = false;
        mPisIotEdgeDeviceName = "";
        mFirstUrl = null;
        mCancelStartSplash = false;
        mViewPointUser = null;
        mRecommendUser = null;
        mUserIdLock = new Object();
        mThumbnailUser = null;
        mPisClientCancelled = false;
        mFinishToastResourceId = 0;
        mResultCode = null;
        mTunerTimeDiffService = null;
        mShouldForegroundAtvApp = false;
        mFinishAppReceiver = null;
        mLoadUrl = null;
        mStartClient = false;
        mUsbDeviceObserver = new UsbDeviceObserver();
        mAllowUsbAudio = false;
        mAudioManager = null;
        mAudioFocusRequest = null;
        mStartPreview = false;
        mHDDRegistrationReceiver = null;
        mAudioFocusChangeListener = new android.media.AudioManager.OnAudioFocusChangeListener() {

            public void onAudioFocusChange(int i)
            {
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append("AudioManager.AUDIOFOCUS focusChange: ");
                stringbuilder.append(i);
                PxLog.d("tv_tuner_service", stringbuilder.toString());
                switch(i)
                {
                case 0: // '\0'
                case 2: // '\002'
                case 3: // '\003'
                default:
                    break;

                case 1: // '\001'
                    ControlInterface controlinterface = TunerServiceManager.getInstance().getControlInterface();
                    if(controlinterface != null)
                        controlinterface.setMute(false);
                    break;

                case -1: 
                    ControlInterface controlinterface1 = TunerServiceManager.getInstance().getControlInterface();
                    if(controlinterface1 != null)
                        controlinterface1.setMute(true);
                    break;

                case -2: 
                    ControlInterface controlinterface2 = TunerServiceManager.getInstance().getControlInterface();
                    if(controlinterface2 != null)
                        controlinterface2.setMute(true);
                    break;

                case -3: 
                    ControlInterface controlinterface3 = TunerServiceManager.getInstance().getControlInterface();
                    if(controlinterface3 != null)
                        controlinterface3.setMute(true);
                    break;
                }
            }

            final TvTunerService this$0;

            
            {
                this$0 = TvTunerService.this;
                super();
            }
        }
;
        mBmlOutputArea = new BmlOutputArea();
        mLNBStateReceiver = null;
        mIoTEdgeCommandReceiver = null;
        mPisIotEdgeControlResultReceiver = null;
        mTimeChangedReceiver = null;
        mUpdateEPGReceiver = null;
    }

    private void InitStreamLayer()
    {
        System.loadLibrary("stationtv_lt_stb_stream");
        System.loadLibrary("stationtv_lt_stb_control");
        System.loadLibrary("player_serviceAT");
        videoView = null;
        captionView = null;
        autoMessageView = new AutoMessageView(this, null);
        videoViewLayout = null;
    }

    private boolean IsInitialSettingsState()
    {
        return existsInitFile(settingsPath_) ^ true;
    }

    private void NotifyLNBShortIfNeeded()
    {
        PxLog.d("tv_tuner_service", "NotifyLNBShortIfNeeded in");
        Object obj = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean flag = ((SharedPreferences) (obj)).getBoolean("isLNBShortOccur", false);
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("isLNBShortOccur = ");
        stringbuilder.append(flag);
        PxLog.d("tv_tuner_service", stringbuilder.toString());
        if(flag)
        {
            ((SharedPreferences) (obj)).edit().remove("isLNBShortOccur").commit();
            obj = TunerServiceManager.getInstance().getControlInterface();
            if(obj != null)
                ((ControlInterface) (obj)).NotifyLNBShortOccur();
        }
    }

    private void abandonAudioFocus()
    {
        PxLog.v("tv_tuner_service", "abandonAudioFocus in");
        if(android.os.Build.VERSION.SDK_INT < 26)
        {
            PxLog.d("tv_tuner_service", "abandonAudioFocus out. not supported");
            return;
        }
        if(mAudioManager != null)
        {
            if(mAudioFocusRequest != null)
                mAudioManager.abandonAudioFocusRequest(mAudioFocusRequest);
            mAudioManager = null;
        }
        PxLog.v("tv_tuner_service", "abandonAudioFocus out");
    }

    private static ArrayList asList(int ai[])
    {
        ArrayList arraylist = new ArrayList();
        int i = ai.length;
        for(int j = 0; j < i; j++)
            arraylist.add(Integer.valueOf(ai[j]));

        return arraylist;
    }

    private void clearLayout()
    {
        if(mState != 0)
            return;
        if(videoViewLayout != null)
        {
            PxLog.d("tv_tuner_service", "clearLayout:remove all views");
            videoViewLayout.removeAllViews();
            videoViewLayout.invalidate();
            videoViewLayout = null;
            videoView = null;
            captionView = null;
            autoMessageView = null;
        }
    }

    private DirectVideoView.ViewInfo createViewInfo()
    {
        Rect rect = new Rect();
        mVideoViewLayout.getLocalVisibleRect(rect);
        int i = rect.width();
        int j = rect.height();
        float f = i;
        float f1 = j;
        if(f / f1 > 1.777778F)
        {
            float f2 = (16F * f1) / 9F;
            i = (int)((f - f2) / 2.0F);
            j = 0;
            f = f2;
        } else
        {
            float f3 = (9F * f) / 16F;
            j = (int)((f1 - f3) / 2.0F);
            f1 = f3;
            i = 0;
        }
        int k = (int)((double)f + 0.5D);
        int l = (int)((double)f1 + 0.5D);
        int i1 = k;
        if(k <= 0)
            i1 = 1;
        k = l;
        if(l <= 0)
            k = 1;
        rect = new Rect();
        rect.set(i, j, i1 + i, k + j);
        return new DirectVideoView.ViewInfo(rect, rect, rect, 0);
    }

    private void deleteLayout()
    {
        if(mState == 0)
            return;
        if(videoViewLayout != null)
        {
            PxLog.d("tv_tuner_service", "deleteLayout:remove all views");
            videoViewLayout.removeAllViews();
            videoViewLayout = null;
            videoView = null;
            captionView = null;
        }
    }

    public static void deleteScreenOffFile(Context context)
    {
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append(context.getFilesDir().getPath());
        ((StringBuilder) (obj)).append("/");
        ((StringBuilder) (obj)).append("settings");
        obj = ((StringBuilder) (obj)).toString();
        context = new StringBuilder();
        context.append(((String) (obj)));
        context.append("/");
        context.append("screenoff.dat");
        context = new File(context.toString());
        if(context.exists())
            context.delete();
    }

    private boolean enterUserSpecifiedUrl(KeyEvent keyevent)
    {
        if(keyevent.getKeyCode() == 32 && mContentShellManager != null && mContentShellManager.loadUserSpecifiedUrl())
        {
            mCurrentBrowserUsedKeyFlag = jp.pixela.player_service.tunerservice.BrowserType.KeyGroup.KeyGroup_Navigation.getValue() | jp.pixela.player_service.tunerservice.BrowserType.KeyGroup.KeyGroup_DButton.getValue() | jp.pixela.player_service.tunerservice.BrowserType.KeyGroup.KeyGroup_Red.getValue() | jp.pixela.player_service.tunerservice.BrowserType.KeyGroup.KeyGroup_Green.getValue() | jp.pixela.player_service.tunerservice.BrowserType.KeyGroup.KeyGroup_Yellow.getValue() | jp.pixela.player_service.tunerservice.BrowserType.KeyGroup.KeyGroup_Blue.getValue();
            return true;
        } else
        {
            return false;
        }
    }

    private static boolean existsInitFile(String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(s);
        stringbuilder.append("/");
        stringbuilder.append("init.dat");
        return (new File(stringbuilder.toString())).exists();
    }

    private static boolean existsScreenOffFile(String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(s);
        stringbuilder.append("/");
        stringbuilder.append("screenoff.dat");
        return (new File(stringbuilder.toString())).exists();
    }

    private void finishClient()
    {
        sendBroadcast(new Intent("jp.pixela.stationtv.xit.ACTION_PLAYER_SERVICE_FINISHED"));
        mStartClient = false;
    }

    private String getCognitiveUserId(Context context)
    {
        Object obj = mUserIdLock;
        obj;
        JVM INSTR monitorenter ;
        SharedPreferences sharedpreferences;
        String s;
        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context);
        s = sharedpreferences.getString("COGNITIVE_USER_ID", null);
        context = s;
        if(s != null)
            break MISSING_BLOCK_LABEL_57;
        context = UUID.randomUUID().toString();
        sharedpreferences.edit().putString("COGNITIVE_USER_ID", context).apply();
        obj;
        JVM INSTR monitorexit ;
        return context;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
    }

    public static boolean isInitialSettingsState(Context context)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(context.getFilesDir().getPath());
        stringbuilder.append("/");
        stringbuilder.append("settings");
        return existsInitFile(stringbuilder.toString()) ^ true;
    }

    private void loadUrl(String s, boolean flag)
    {
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append("jp.pixela.stationtv.xit.");
        ((StringBuilder) (obj)).append("ACTION_WEB_VIEW_LOAD_URL");
        obj = new Intent(((StringBuilder) (obj)).toString());
        ((Intent) (obj)).putExtra("EXTRA_URL", s);
        s = new StringBuilder();
        s.append("loadUrl sendBroadcast intent=");
        s.append(IntentHelper.dump(((Intent) (obj))));
        PxLog.v("tv_tuner_service", s.toString());
        sendBroadcast(((Intent) (obj)));
    }

    private boolean onVoiceSearchResult(Bundle bundle, boolean flag)
    {
        PxLog.d("tv_tuner_service", "onVoiceSearchResult in.");
        Bundle bundle1 = bundle;
        if(bundle == null)
        {
            bundle1 = bundle;
            if(intentOnInit != null)
            {
                bundle1 = new Bundle();
                bundle1.putBoolean("Preview", flag);
                processIntentOnInit(intentOnInit, bundle1);
                intentOnInit = null;
            }
        }
        boolean flag1 = false;
        flag = flag1;
        if(bundle1 != null)
        {
            flag = flag1;
            if(bundle1.getBoolean("VoiceSearch", false))
            {
                TunerService tunerservice = TunerServiceManager.getInstance().getTunerService();
                flag = flag1;
                if(tunerservice != null)
                {
                    bundle = Message.obtain(null, 0);
                    bundle.setData(bundle1);
                    flag = flag1;
                    if(tunerservice.Update(bundle) == 0)
                    {
                        flag = true;
                        (new Handler()).postDelayed(new Runnable() {

                            public void run()
                            {
                                startClient();
                            }

                            final TvTunerService this$0;

            
            {
                this$0 = TvTunerService.this;
                super();
            }
                        }
, 500L);
                    }
                }
            }
        }
        PxLog.d("tv_tuner_service", "onVoiceSearchResult out.");
        return flag;
    }

    private boolean processIntentOnInit(Intent intent, Bundle bundle)
    {
        String s;
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append("processIntentOnInit() in. intent=");
        ((StringBuilder) (obj)).append(IntentHelper.dump(intent));
        PxLog.v("tv_tuner_service", ((StringBuilder) (obj)).toString());
        if(intent == null)
        {
            PxLog.w("tv_tuner_service", "processIntentOnInit() out. intent == null");
            return false;
        }
        s = intent.getStringExtra("intent_extra_data_key");
        obj = RecordInfoOpenHelper.readExtraData(s);
        if(obj != null)
        {
            intent = new StringBuilder();
            intent.append("processIntentOnInit rec objectId:");
            intent.append(((jp.pixela.searchable_program.RecordInfoOpenHelper.RecordContentInfo) (obj)).getObjectId());
            PxLog.v("tv_tuner_service", intent.toString());
            bundle.putString("FirstPlayObjectId", ((jp.pixela.searchable_program.RecordInfoOpenHelper.RecordContentInfo) (obj)).getObjectId());
            bundle.putBoolean("VoiceSearch", true);
            mFirstUrl = "file:///android_asset/preview/index.html";
            mStartPreview = true;
            return true;
        }
        if(s == null) goto _L2; else goto _L1
_L1:
        bundle.putBoolean("VoiceSearch", true);
        intent = s.split("#");
        if(intent != null && intent.length >= 2)
        {
            if("live".equals(intent[1]))
            {
                intent = ProgramInfoOpenHelper.readExtraData(intent[0]);
                if(intent != null)
                {
                    bundle.putInt("BroadcastWave", intent.getBroadcast());
                    bundle.putInt("ServiceId", intent.getServiceId());
                    PxLog.v("tv_tuner_service", "processIntentOnInit() out");
                    mFirstUrl = "file:///android_asset/preview/index.html";
                    mStartPreview = true;
                    return true;
                }
            } else
            if("future".equals(intent[1]))
            {
                intent = ProgramInfoOpenHelper.readExtraData(intent[0]);
                if(intent != null)
                {
                    bundle.putInt("BroadcastWave", -1);
                    bundle.putInt("ServiceId", -1);
                    bundle.putInt("StartMode", jp.pixela.player_service.tunerservice.TunerService.StartMode.Reserve.getValue());
                    int i = intent.getId();
                    int j = intent.getBroadcast();
                    int k = intent.getServiceId();
                    int l = intent.getEventId();
                    intent = String.format("?ts=%d#/?program_id=%d&broadcast_type=%d&service_id=%d&event_id=%d", new Object[] {
                        Long.valueOf(System.currentTimeMillis()), Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k), Integer.valueOf(l)
                    });
                    StringBuilder stringbuilder = new StringBuilder();
                    stringbuilder.append("file:///android_asset/programList/index.html");
                    stringbuilder.append(intent);
                    mFirstUrl = stringbuilder.toString();
                    if(bundle.getBoolean("Preview", false))
                    {
                        bundle = new StringBuilder();
                        bundle.append(mFirstUrl);
                        bundle.append("&preview=1");
                        mFirstUrl = bundle.toString();
                    }
                    bundle = new StringBuilder();
                    bundle.append("processIntentOnInit() out. params=");
                    bundle.append(intent);
                    PxLog.v("tv_tuner_service", bundle.toString());
                    return true;
                }
            }
        } else
        {
            bundle.putInt("BroadcastWave", ExtraDataHelper.getBroadcastType(s));
            bundle.putInt("ServiceId", ExtraDataHelper.getServiceId(s));
            PxLog.v("tv_tuner_service", "processIntentOnInit() out");
            return true;
        }
          goto _L3
_L2:
        if(!intent.getBooleanExtra("FromKeyEvent", false)) goto _L3; else goto _L4
_L4:
        if(!intent.hasExtra("KeyCode")) goto _L6; else goto _L5
_L5:
        int i1 = intent.getIntExtra("KeyCode", 0);
        if(i1 == 172) goto _L8; else goto _L7
_L7:
        if(i1 == 236) goto _L10; else goto _L9
_L9:
        i1;
        JVM INSTR tableswitch 7 16: default 604
    //                   7 659
    //                   8 659
    //                   9 659
    //                   10 659
    //                   11 659
    //                   12 659
    //                   13 659
    //                   14 659
    //                   15 659
    //                   16 659;
           goto _L11 _L12 _L12 _L12 _L12 _L12 _L12 _L12 _L12 _L12 _L12
_L11:
        i1;
        JVM INSTR tableswitch 227 228: default 628
    //                   227 659
    //                   228 659;
           goto _L13 _L12 _L12
_L13:
        switch(i1)
        {
        default:
            break; /* Loop/switch isn't completed */

        case 238: 
        case 239: 
        case 240: 
            break;
        }
          goto _L10
_L12:
        bundle.putInt("BroadcastWave", -1);
        bundle.putInt("ServiceId", -1);
        bundle.putInt("RemoconNumber", intent.getIntExtra("RemoconNumber", 0));
        bundle.putInt("StartMode", jp.pixela.player_service.tunerservice.TunerService.StartMode.OneTouchSelectChannel.getValue());
        mStartPreview = true;
        break; /* Loop/switch isn't completed */
_L10:
        bundle.putInt("BroadcastWave", intent.getIntExtra("BroadcastType", BroadcastWave.TR.getValue()));
        bundle.putInt("ServiceId", 0);
        bundle.putInt("StartMode", jp.pixela.player_service.tunerservice.TunerService.StartMode.SelectBroadcastWave.getValue());
        mFirstUrl = "file:///android_asset/preview/index.html";
        mStartPreview = true;
        break; /* Loop/switch isn't completed */
_L8:
        bundle.putInt("BroadcastWave", -1);
        bundle.putInt("ServiceId", -1);
        bundle.putInt("StartMode", jp.pixela.player_service.tunerservice.TunerService.StartMode.Default.getValue());
        intent = String.format("#/?action=%s", new Object[] {
            "select_ch"
        });
        bundle = new StringBuilder();
        bundle.append("file:///android_asset/programList/index.html");
        bundle.append(intent);
        mFirstUrl = bundle.toString();
        mStartPreview = true;
        break; /* Loop/switch isn't completed */
_L6:
        if(intent.getBooleanExtra("switch TV", false))
        {
            bundle = String.format("?sm=%s", new Object[] {
                "true"
            });
            intent = new StringBuilder();
            intent.append("file:///android_asset/preview/index.html");
            intent.append(bundle);
            mFirstUrl = intent.toString();
            mStartPreview = true;
        }
_L3:
        PxLog.w("tv_tuner_service", "processIntentOnInit() out. not matched.");
        return false;
    }

    private void removePreparingAnimation()
    {
        View view = findViewById(0x7f080087);
        if(view != null)
            view.setVisibility(4);
    }

    private void requestAudioFocus()
    {
        PxLog.v("tv_tuner_service", "requestAudioFocus in");
        if(android.os.Build.VERSION.SDK_INT < 26)
        {
            PxLog.d("tv_tuner_service", "requestAudioFocus out. not supported");
            return;
        }
        mAudioManager = (AudioManager)getSystemService("audio");
        if(mAudioManager == null)
        {
            PxLog.e("tv_tuner_service", "requestAudioFocus out. AudioManager is null");
            return;
        }
        android.media.AudioAttributes audioattributes = (new android.media.AudioAttributes.Builder()).setUsage(1).setContentType(2).build();
        mAudioFocusRequest = (new android.media.AudioFocusRequest.Builder(1)).setOnAudioFocusChangeListener(mAudioFocusChangeListener).setAcceptsDelayedFocusGain(true).setWillPauseWhenDucked(true).setAudioAttributes(audioattributes).build();
        int i = mAudioManager.requestAudioFocus(mAudioFocusRequest);
        if(i != 1)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("requestAudioFocus not granted: ");
            stringbuilder.append(i);
            PxLog.e("tv_tuner_service", stringbuilder.toString());
        }
        PxLog.v("tv_tuner_service", "requestAudioFocus out");
    }

    private void resetVideoLayout()
    {
        if(videoView == null)
            return;
        android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)videoView.getLayoutParams();
        Rect rect = videoView.getmViewInfo().GetRect();
        videoView.setX(rect.left);
        videoView.setY(rect.top);
        layoutparams.width = rect.right - rect.left;
        layoutparams.height = rect.bottom - rect.top;
        if(layoutparams instanceof android.view.ViewGroup.MarginLayoutParams)
            layoutparams.setMargins(0, 0, 0, 0);
        videoView.setLayoutParams(layoutparams);
    }

    private void sendBroadcastUpdateStereoSetting(boolean flag)
    {
    }

    private void sendKeyDownUpSync(int i)
    {
        Instrumentation instrumentation = new Instrumentation();
        instrumentation.sendKeySync(new KeyEvent(0, i));
        try
        {
            Thread.sleep(10L);
        }
        catch(Exception exception) { }
        instrumentation.sendKeySync(new KeyEvent(1, i));
    }

    private void sendRegisterRecordingHdd()
    {
    }

    private void setInitialSettingsState()
    {
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append(settingsPath_);
        ((StringBuilder) (obj)).append("/");
        ((StringBuilder) (obj)).append("init.dat");
        obj = new File(((StringBuilder) (obj)).toString());
        if(((File) (obj)).exists())
            ((File) (obj)).delete();
    }

    private void setLayout()
    {
        PxLog.v("tv_tuner_service", "setLayout() in");
        if(mState == 0)
            return;
        ControlInterface controlinterface = TunerServiceManager.getInstance().getControlInterface();
        DirectVideoView.ViewInfo viewinfo = createViewInfo();
        if(videoView == null)
            videoView = new DirectVideoView(this, 0, controlinterface, viewinfo);
        if(captionView == null)
            captionView = new DirectVideoView(this, 1, controlinterface, viewinfo);
        if(videoViewLayout == null)
        {
            videoViewLayout = (FrameLayout)findViewById(0x7f0800e1);
            if(videoViewLayout != null)
            {
                videoViewLayout.removeAllViews();
                videoViewLayout.addView(videoView, 0);
                if(captionView != null)
                {
                    videoViewLayout.addView(captionView, 1);
                    captionView.setZOrderMediaOverlay(true);
                }
                if(autoMessageView != null)
                    videoViewLayout.addView(autoMessageView, 2);
            }
        }
        resetVideoLayout();
        PxLog.v("tv_tuner_service", "setLayout() out");
    }

    private void setLayoutIfEnabled()
    {
        if(mVideoViewLayoutMeasurable && TunerServiceManager.getInstance().isInit())
            setLayout();
    }

    private void startClient()
    {
        if(!IsInitialSettingsState())
        {
            if(mFirstUrl == null)
            {
                startClient("file:///android_asset/preview/index.html");
            } else
            {
                startClient(mFirstUrl);
                mFirstUrl = null;
            }
        } else
        {
            String s = mFirstUrl;
            Object obj = s;
            if(s == null)
            {
                obj = (new BuildUtilityWrapper(this)).getProductType();
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append("file:///android_asset/initial/index.html");
                stringbuilder.append(String.format("#/?from=%d&product=%d&model=%d", new Object[] {
                    Integer.valueOf(0), Integer.valueOf(ProductInfo.getEmumProductTypeValue(((jp.pixela.common.ProductInfo.ProductTypeT) (obj)))), Integer.valueOf(ProductInfo.getEmumModelTypeValue(((jp.pixela.common.ProductInfo.ProductTypeT) (obj))))
                }));
                obj = stringbuilder.toString();
            }
            startClient(((String) (obj)));
            mFirstUrl = null;
        }
    }

    private void startClient(String s)
    {
        Intent intent;
        intent = JVM INSTR new #784 <Class Intent>;
        intent.Intent();
        ComponentName componentname = JVM INSTR new #1221 <Class ComponentName>;
        componentname.ComponentName("jp.pixela.stationtv.xit", "jp.pixela.atv_app.MainActivity");
        intent.setComponent(componentname);
        if(s == null)
            break MISSING_BLOCK_LABEL_41;
        intent.putExtra("URL", s);
        intent.putExtra("DolbyDigital", false);
        s = JVM INSTR new #486 <Class StringBuilder>;
        s.StringBuilder();
        s.append("startClient startActivity intent=");
        s.append(IntentHelper.dump(intent));
        PxLog.v("tv_tuner_service", s.toString());
        startActivity(intent);
        mStartClient = true;
        break MISSING_BLOCK_LABEL_106;
        s;
        PxLog.e("tv_tuner_service", "failed to startActivity=jp.pixela.atv_app.MainActivity");
    }

    private void startDelayedInitialize(long l, boolean flag)
    {
        if(!mIsFormattedHdd)
        {
            stopSplash();
            mCancelStartSplash = true;
            startHddFormatActivity("requestHddFormat", 0);
        } else
        if(!mInitializedTunerService.get() && !TunerServiceManager.getInstance().isInitializingTunerService())
        {
            if(mPlayerObserver == null)
            {
                mPlayerObserver = new jp.pixela.common.PlayerMessenger.PlayerObserver(this);
                PlayerMessenger.addPlayerObserver(mPlayerObserver);
            }
            Bundle bundle = new Bundle();
            if(intentOnInit != null)
            {
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append("onResume() intentOnResume=");
                stringbuilder.append(intentOnInit);
                PxLog.v("tv_tuner_service", stringbuilder.toString());
                processIntentOnInit(intentOnInit, bundle);
                intentOnInit = null;
            }
            if(mBmlSoundPlayer == null)
            {
                ControlInterface controlinterface = TunerServiceManager.getInstance().getControlInterface();
                if(controlinterface != null)
                    mBmlSoundPlayer = new BmlSoundPlayer(getApplicationContext(), controlinterface);
            }
            if(flag)
                startPreparingTimer();
            TunerServiceManager.getInstance().initTuner(bundle, l, flag, new jp.pixela.common.IDelegate.IFunc() {

                public Boolean invoke()
                {
                    if(!isFinishing() && !isAppFinishingForRequest)
                    {
                        for(int i = 50; ApplicationUtility.isServiceRunning(getApplicationContext(), jp/pixela/searchable_program/DatabaseUpdater.getName()) && i > 0;)
                        {
                            i--;
                            try
                            {
                                Thread.sleep(100L);
                            }
                            catch(Exception exception) { }
                        }

                        return Boolean.valueOf(false);
                    } else
                    {
                        return Boolean.valueOf(true);
                    }
                }

                public volatile Object invoke()
                {
                    return invoke();
                }

                final TvTunerService this$0;

            
            {
                this$0 = TvTunerService.this;
                super();
            }
            }
, new jp.pixela.common.IDelegate.IAction2() {

                public void invoke(Boolean boolean1, Boolean boolean2)
                {
                    final boolean initResult = boolean1.booleanValue();
                    boolean flag1 = boolean2.booleanValue();
                    boolean1 = new StringBuilder();
                    boolean1.append("initTuner result=");
                    boolean1.append(initResult);
                    boolean1.append(", alreadyInitialized=");
                    boolean1.append(flag1);
                    PxLog.v("tv_tuner_service", boolean1.toString());
                    mInitializedTunerService.set(true);
                    runOnUiThread(flag1. new Runnable() {

                        public void run()
                        {
                            stopSplash();
                            stopPreparingTimer();
                            removePreparingAnimation();
                            if(initResult)
                            {
                                PxLog.v("tv_tuner_service", "TunerService is initialized!");
                                requestAudioFocus();
                                if(!launchTV)
                                    if(alreadyInitialized && bundle != null && bundle.getBoolean("VoiceSearch", false))
                                        onVoiceSearchResult(bundle, false);
                                    else
                                        startClient();
                                restartPlayer();
                                setLayoutIfEnabled();
                                NotifyLNBShortIfNeeded();
                            } else
                            {
                                PxLog.v("tv_tuner_service", "TunerService failed to initialize...");
                                (new Handler()).postDelayed(new Runnable() {

                                    public void run()
                                    {
                                        if(launchTV)
                                        {
                                            isAppFinishingForRequest = true;
                                            finishClient();
                                            finish();
                                        } else
                                        {
                                            finish();
                                            isAppFinishingForRequest = false;
                                        }
                                        Logger.v("tv_tuner_service terminate", new Object[0]);
                                        TunerServiceManager.getInstance().terminate(_fld0, true);
                                    }

                                    final _cls1 this$2;

            
            {
                this$2 = _cls1.this;
                super();
            }
                                }
, 5000L);
                                Toast.makeText(getApplicationContext(), 0x7f0e0061, 1).show();
                            }
                        }

                        final _cls30 this$1;
                        final boolean val$alreadyInitialized;
                        final boolean val$initResult;

            
            {
                this$1 = final__pcls30;
                initResult = flag;
                alreadyInitialized = Z.this;
                super();
            }
                    }
);
                }

                public volatile void invoke(Object obj, Object obj1)
                {
                    invoke((Boolean)obj, (Boolean)obj1);
                }

                final TvTunerService this$0;
                final Bundle val$bundle;
                final boolean val$launchTV;

            
            {
                this$0 = TvTunerService.this;
                launchTV = flag;
                bundle = bundle1;
                super();
            }
            }
);
        }
    }

    private void startHddFormatActivity(String s, int i)
    {
        mAbortFinishOnStop = true;
        Intent intent = new Intent();
        intent.setClass(this, jp/pixela/view/HddFormatActivity);
        intent.putExtra("ShowMode", i);
        intent.setAction(s);
        startActivityForResult(intent, 100);
    }

    private void startPisIotEdgeMainActivity(int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("startPisIotEdgeMainActivity command=");
        stringbuilder.append(i);
        PxLog.v("tv_tuner_service", stringbuilder.toString());
        i;
        JVM INSTR tableswitch 0 2: default 60
    //                   0 156
    //                   1 63
    //                   2 63;
           goto _L1 _L2 _L3 _L3
_L1:
        break; /* Loop/switch isn't completed */
_L3:
        Intent intent;
        intent = JVM INSTR new #784 <Class Intent>;
        intent.Intent();
        intent.setClassName("jp.pixela.stationtv.xit", "jp.co.pixela.pis_iot_edge.MainActivity");
        intent.setAction("jp.pixela.stationtv.xit.ACTION_PIS_IOT_EDGE_CONTROL");
        intent.putExtra("EXTRA_COMMAND", i);
        if(i != 1) goto _L5; else goto _L4
_L4:
        intent.putExtra("DEVICE_NAME", mPisIotEdgeDeviceName);
_L5:
        startActivity(intent);
        StringBuilder stringbuilder1 = JVM INSTR new #486 <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        stringbuilder1.append("startPisIotEdgeMainActivity startActivity intent=");
        stringbuilder1.append(IntentHelper.dump(intent));
        PxLog.v("tv_tuner_service", stringbuilder1.toString());
        break; /* Loop/switch isn't completed */
_L2:
        StringBuilder stringbuilder3;
        try
        {
            Intent intent1 = JVM INSTR new #784 <Class Intent>;
            intent1.Intent();
            intent1.setAction("jp.pixela.stationtv.xit.ACTION_PIS_IOT_EDGE_CONTROL");
            intent1.putExtra("EXTRA_COMMAND", i);
            sendBroadcast(intent1);
            StringBuilder stringbuilder2 = JVM INSTR new #486 <Class StringBuilder>;
            stringbuilder2.StringBuilder();
            stringbuilder2.append("startPisIotEdgeMainActivity sendBroadcast intent=");
            stringbuilder2.append(IntentHelper.dump(intent1));
            PxLog.v("tv_tuner_service", stringbuilder2.toString());
            break; /* Loop/switch isn't completed */
        }
        catch(Exception exception)
        {
            stringbuilder3 = new StringBuilder();
        }
        stringbuilder3.append("startPisIotEdgeMainActivity error=");
        stringbuilder3.append(i);
        PxLog.e("tv_tuner_service", stringbuilder3.toString());
    }

    private void startPreparingAnimation()
    {
        mPreparingProgressBar = (ProgressBar)findViewById(0x7f080068);
        View view = findViewById(0x7f080087);
        if(view != null)
            view.setVisibility(0);
    }

    private void startPreparingTimer()
    {
        int i = mPreparingTime + 5;
        mCurrentPreparingTime = 0;
        if(mPreparingProgressBar != null)
        {
            mPreparingProgressBar.setProgress(0);
            mPreparingProgressBar.setMax(i);
        }
        mPreparingTimer = new CountDownTimer(i * 1000, 1000L) ;
        mPreparingTimer.start();
    }

    private void startSplash()
    {
        PxLog.v("tv_tuner_service", "startSplash() in");
        mSplashAnimView = new ImageView(this);
        if(mVideoViewLayout == null)
            mVideoViewLayout = (FrameLayout)findViewById(0x7f0800e1);
        mVideoViewLayout.addView(mSplashAnimView, 0);
        Object obj = new LayoutParams(-1, -1);
        mSplashAnimView.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
        mSplashAnimView.setImageResource(0x7f070053);
        mSplashAnimView.setScaleType(android.widget.ImageView.ScaleType.CENTER);
        mSplashAnimView.setBackgroundColor(0xff000000);
        mSplashAnimView.setVisibility(0);
        obj = (AnimationDrawable)mSplashAnimView.getDrawable();
        if(obj != null)
            ((AnimationDrawable) (obj)).start();
        PxLog.v("tv_tuner_service", "startSplash() out");
    }

    private void startTvSettingsActivity()
    {
        Intent intent = new Intent();
        intent.setClass(this, jp/pixela/view/TvSettingsActivity);
        intent.putExtra("LaunchFrom", 0);
        try
        {
            startActivityForResult(intent, 101);
        }
        catch(Exception exception)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("failed to startActivity=");
            stringbuilder.append(jp/pixela/view/TvSettingsActivity);
            PxLog.e("tv_tuner_service", stringbuilder.toString());
        }
    }

    private void stopPreparingTimer()
    {
        if(mPreparingTimer != null)
        {
            mPreparingTimer.cancel();
            mPreparingTimer = null;
        }
    }

    private void stopSplash()
    {
        PxLog.v("tv_tuner_service", "stopSplash() in");
        if(mSplashAnimView == null)
        {
            PxLog.i("tv_tuner_service", "stopSplash() out. mSplashAnimView == null");
            return;
        }
        if(mVideoViewLayout == null)
            mVideoViewLayout = (FrameLayout)findViewById(0x7f0800e1);
        mVideoViewLayout.removeView(mSplashAnimView);
        mSplashAnimView = null;
        PxLog.v("tv_tuner_service", "stopSplash() out");
    }

    private void terminateApp(boolean flag)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("tv_tuner_service in. killProcess=");
        stringbuilder.append(flag);
        Logger.v(stringbuilder.toString(), new Object[0]);
        if(mBmlSoundPlayer != null)
        {
            mBmlSoundPlayer.Finalize();
            mBmlSoundPlayer = null;
        }
        if(mPlayerObserver != null)
        {
            PlayerMessenger.deletePlayerObserver(mPlayerObserver);
            mPlayerObserver = null;
        }
        finishClient();
        finish();
        if(flag)
        {
            PxLog.d("tv_tuner_service", "mKillProcessRunnable.run() out. killProcess");
            Process.killProcess(Process.myPid());
        }
        Logger.v("tv_tuner_service out", new Object[0]);
    }

    private void updateCaptionViewArea()
    {
        if(captionView == null)
            return;
        Rect rect = mBmlOutputArea.CalcBmlOutputRect(this);
        Object obj = new Rect(rect.left, rect.top, rect.right, rect.bottom);
        captionView.updateSurface(((Rect) (obj)), ((Rect) (obj)), ((Rect) (obj)), 0);
        obj = captionView.getLayoutParams();
        if(obj instanceof android.view.ViewGroup.MarginLayoutParams)
        {
            android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)obj;
            obj.width = rect.width();
            obj.height = rect.height();
            marginlayoutparams.setMargins(rect.left, rect.top, 0, 0);
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("updateOutputVideoViewArea captionView area left:");
            stringbuilder.append(rect.left);
            stringbuilder.append(" top:");
            stringbuilder.append(rect.top);
            stringbuilder.append(" width:");
            stringbuilder.append(((android.view.ViewGroup.LayoutParams) (obj)).width);
            stringbuilder.append(" height:");
            stringbuilder.append(((android.view.ViewGroup.LayoutParams) (obj)).height);
            PxLog.d("tv_tuner_service", stringbuilder.toString());
            captionView.setLayoutParams(marginlayoutparams);
            autoMessageView.setLayoutParams(marginlayoutparams);
        }
    }

    public static boolean wasRunning(Context context)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(context.getFilesDir().getPath());
        stringbuilder.append("/");
        stringbuilder.append("settings");
        return existsScreenOffFile(stringbuilder.toString());
    }

    public void clearKeepScreenOn()
    {
        getWindow().clearFlags(128);
    }

    public void finish()
    {
        if(mResultCode != null)
        {
            setResult(mResultCode.intValue());
            mResultCode = null;
        }
        finish();
    }

    public void keepScreenOn()
    {
        getWindow().addFlags(128);
    }

    public void notify(final Message stateInfo)
    {
        final Object devInfo;
        int i;
        devInfo = (jp.pixela.common.PlayerMessenger.MessageParam)stateInfo.obj;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("notify msg.what=");
        stringbuilder.append(stateInfo.what);
        PxLog.d("tv_tuner_service", stringbuilder.toString());
        i = stateInfo.what;
        if(i == 24) goto _L2; else goto _L1
_L1:
        if(i == 26) goto _L4; else goto _L3
_L3:
        boolean flag = true;
        if(i == 100) goto _L6; else goto _L5
_L5:
        boolean flag1;
        int j;
        int k;
        flag1 = false;
        j = 0;
        k = 0;
        i;
        JVM INSTR tableswitch 1 15: default 156
    //                   1 2974
    //                   2 2963
    //                   3 2952
    //                   4 2877
    //                   5 2866
    //                   6 2569
    //                   7 2554
    //                   8 2539
    //                   9 2509
    //                   10 2446
    //                   11 2401
    //                   12 1901
    //                   13 1779
    //                   14 1191
    //                   15 869;
           goto _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22
_L7:
        switch(i)
        {
        default:
            switch(i)
            {
            case 203: 
                if(mContentShellManager != null)
                {
                    stateInfo = (BrowserUrlInfo)((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getNotifyParam();
                    if(stateInfo == null)
                    {
                        PxLog.d("tv_tuner_service", "BrowserUrlInfo is null.");
                        return;
                    }
                    mCurrentBrowserUsedKeyFlag = jp.pixela.player_service.tunerservice.BrowserType.KeyGroup.KeyGroup_DButton.getValue();
                    mContentShellManager.loadUrl(stateInfo.GetUrl());
                }
                break;

            case 202: 
                PxLog.d("tv_tuner_service", "NOTIFY_BROWSER_UPDATE_VIDEO_POSITION");
                runOnUiThread(new Runnable() {

                    public void run()
                    {
                        if(videoView != null && rect.right - rect.left > 0 && rect.bottom - rect.top > 0)
                        {
                            android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)videoView.getLayoutParams();
                            layoutparams.width = rect.right - rect.left;
                            layoutparams.height = rect.bottom - rect.top;
                            videoView.setLayoutParams(layoutparams);
                            videoView.setX(rect.left);
                            videoView.setY(rect.top);
                        }
                    }

                    final TvTunerService this$0;
                    final Rect val$rect;

            
            {
                this$0 = TvTunerService.this;
                rect = rect1;
                super();
            }
                }
);
                break;

            case 201: 
                stateInfo = (BrowserStateInfo)((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getNotifyParam();
                runOnUiThread(new Runnable() {

                    public void run()
                    {
                        if(!stateInfo.GetVisibleFlag() && videoView != null)
                        {
                            Rect rect = videoView.getmViewInfo().GetRect();
                            android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)videoView.getLayoutParams();
                            layoutparams.width = rect.right - rect.left;
                            layoutparams.height = rect.bottom - rect.top;
                            videoView.setLayoutParams(layoutparams);
                            videoView.setX(rect.left);
                            videoView.setY(rect.top);
                        }
                    }

                    final TvTunerService this$0;
                    final BrowserStateInfo val$stateInfo;

            
            {
                this$0 = TvTunerService.this;
                stateInfo = browserstateinfo;
                super();
            }
                }
);
                mCurrentBrowserUsedKeyFlag = stateInfo.GetUsedKeyFlag();
                mLastBrowserDownKeyCode = 0;
                break;

            case 200: 
                KeyEventInfo keyeventinfo = (KeyEventInfo)((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getNotifyParam();
                stateInfo = TunerServiceManager.getInstance().getControlInterface();
                if((mCurrentBrowserUsedKeyFlag & BrowserType.getKeyGroup(keyeventinfo.GetKeyCode()).getValue()) != 0)
                {
                    if(stateInfo != null)
                        stateInfo.ResponseBoolean(((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getTarget(), 200, Boolean.valueOf(true).booleanValue());
                    stateInfo = BrowserType.convert(keyeventinfo.GetKeyState(), keyeventinfo.GetKeyCode());
                    if(stateInfo != null)
                        runOnUiThread(new Runnable() {

                            public void run()
                            {
                                StringBuilder stringbuilder5 = new StringBuilder();
                                stringbuilder5.append("SendKeyEvent >>> Browser : KeyAction=");
                                String s;
                                if(event.getAction() == 0)
                                    s = "ACTION_DOWN";
                                else
                                    s = "ACTION_UP";
                                stringbuilder5.append(s);
                                stringbuilder5.append(", KeyCode=");
                                stringbuilder5.append(event.getKeyCode());
                                PxLog.d("tv_tuner_service", stringbuilder5.toString());
                                if(event.getAction() == 1 && mLastBrowserDownKeyCode != event.getKeyCode())
                                {
                                    StringBuilder stringbuilder6 = new StringBuilder();
                                    stringbuilder6.append("Drop KeyEvent : KeyCode(UP)=");
                                    stringbuilder6.append(event.getKeyCode());
                                    stringbuilder6.append(" does not match prev KeyCode(DOWN)=");
                                    stringbuilder6.append(mLastBrowserDownKeyCode);
                                    PxLog.d("tv_tuner_service", stringbuilder6.toString());
                                    return;
                                }
                                if(enterUserSpecifiedUrl(event))
                                    return;
                                dispatchKeyEvent(event);
                                if(event.getAction() == 0)
                                    mLastBrowserDownKeyCode = event.getKeyCode();
                            }

                            final TvTunerService this$0;
                            final KeyEvent val$event;

            
            {
                this$0 = TvTunerService.this;
                event = keyevent;
                super();
            }
                        }
);
                } else
                {
                    mLastBrowserDownKeyCode = 0;
                    boolean flag2 = flag;
                    if(TunerServiceManager.getInstance().isInit())
                    {
                        if(jp.pixela.player_service.tunerservice.BrowserType.KeyState.KEY_STATE_DOWN.getValue() == keyeventinfo.GetKeyState())
                            flag2 = true;
                        else
                            flag2 = false;
                        k = keyeventinfo.GetKeyCode();
                        StringBuilder stringbuilder1 = new StringBuilder();
                        stringbuilder1.append("SendKeyEvent >>> TunerService: KeyCode=");
                        stringbuilder1.append(k);
                        stringbuilder1.append(", Pressed=");
                        stringbuilder1.append(flag2);
                        PxLog.d("tv_tuner_service", stringbuilder1.toString());
                        if(enterUserSpecifiedUrl(BrowserType.convert(keyeventinfo.GetKeyState(), keyeventinfo.GetKeyCode())))
                            flag2 = flag;
                        else
                            flag2 = false;
                    }
                    if(stateInfo != null)
                    {
                        StringBuilder stringbuilder2 = new StringBuilder();
                        stringbuilder2.append("SendKeyEvent >>> TunerService: handled=");
                        stringbuilder2.append(flag2);
                        PxLog.d("tv_tuner_service", stringbuilder2.toString());
                        stateInfo.ResponseBoolean(((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getTarget(), 200, Boolean.valueOf(flag2).booleanValue());
                    }
                }
                break;
            }
            break;

        case 22: // '\026'
            ControlInterface controlinterface = TunerServiceManager.getInstance().getControlInterface();
            if(controlinterface != null)
            {
                stateInfo = new OutputDeviceInfo();
                controlinterface.ResponseObject(((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getTarget(), 22, stateInfo);
            }
            break;

        case 21: // '\025'
            runOnUiThread(new Runnable() {

                public void run()
                {
                    finishClient();
                }

                final TvTunerService this$0;

            
            {
                this$0 = TvTunerService.this;
                super();
            }
            }
);
            break;

        case 20: // '\024'
            boolean flag3 = ((Boolean)((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getNotifyParam()).booleanValue();
            mLNBSettings.sendEnabledLNB(flag3);
            break;

        case 19: // '\023'
            stateInfo = TunerServiceManager.getInstance().getControlInterface();
            if(stateInfo != null)
            {
                boolean flag4 = mLNBSettings.requestLNBEnabled();
                stateInfo.ResponseBoolean(((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getTarget(), 20, Boolean.valueOf(flag4).booleanValue());
            }
            break;

        case 18: // '\022'
            if(((Integer)((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getNotifyParam()).intValue() == jp.pixela.common.PlayerMessenger.WindowType.InitialSetting.getValue())
                runOnUiThread(new Runnable() {

                    public void run()
                    {
                        mResultCode = Integer.valueOf(-1);
                        isAppFinishingForRequest = true;
                        finishClient();
                    }

                    final TvTunerService this$0;

            
            {
                this$0 = TvTunerService.this;
                super();
            }
                }
);
            break;

        case 17: // '\021'
            stateInfo = (Integer)((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getNotifyParam();
            if(stateInfo.intValue() == jp.pixela.common.PlayerMessenger.WindowType.HddFormat.getValue())
                runOnUiThread(new Runnable() {

                    public void run()
                    {
                        startHddFormatActivity("requestHddFormat", 1);
                    }

                    final TvTunerService this$0;

            
            {
                this$0 = TvTunerService.this;
                super();
            }
                }
);
            else
            if(stateInfo.intValue() == jp.pixela.common.PlayerMessenger.WindowType.PisSignIn.getValue())
                runOnUiThread(new Runnable() {

                    public void run()
                    {
                        startPisIotEdgeMainActivity(1);
                    }

                    final TvTunerService this$0;

            
            {
                this$0 = TvTunerService.this;
                super();
            }
                }
);
            else
            if(stateInfo.intValue() == jp.pixela.common.PlayerMessenger.WindowType.Setting.getValue())
                runOnUiThread(new Runnable() {

                    public void run()
                    {
                        startTvSettingsActivity();
                    }

                    final TvTunerService this$0;

            
            {
                this$0 = TvTunerService.this;
                super();
            }
                }
);
            break;
        }
        break; /* Loop/switch isn't completed */
_L22:
        final Integer areaCode;
        Object obj1;
        long l;
        long l1;
        Object obj4;
        Object obj5;
        PxLog.d("tv_tuner_service", "notify REQUEST_GET_RECOMMEND");
        stateInfo = TunerServiceManager.getInstance().getControlInterface();
        if(stateInfo == null)
            break; /* Loop/switch isn't completed */
        Object obj2 = (RecommendInfo)((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getNotifyParam();
        l = ((RecommendInfo) (obj2)).getSince();
        l1 = ((RecommendInfo) (obj2)).getUntil();
        obj1 = ((RecommendInfo) (obj2)).getBroadcastTypeString();
        areaCode = asList(((RecommendInfo) (obj2)).getServiceIds());
        obj4 = new ArrayList();
        if(((RecommendInfo) (obj2)).getProgramInfos() != null)
            for(; k < ((RecommendInfo) (obj2)).getProgramInfos().length; k++)
                ((List) (obj4)).add(((RecommendInfo) (obj2)).getProgramInfos()[k]);

        if((new BuildUtilityWrapper(this)).getProductType() != jp.pixela.common.ProductInfo.ProductTypeT.PRODUCT_PIX_SMB100)
            ((RecommendInfo) (obj2)).setUserId(getCognitiveUserId(getApplicationContext()));
        k = ((RecommendInfo) (obj2)).getAreaCode();
        obj5 = ((RecommendInfo) (obj2)).getUserId();
        obj2 = new StringBuilder();
        ((StringBuilder) (obj2)).append("since=");
        ((StringBuilder) (obj2)).append(l);
        ((StringBuilder) (obj2)).append(", until=");
        ((StringBuilder) (obj2)).append(l1);
        ((StringBuilder) (obj2)).append(", broadcastType=");
        ((StringBuilder) (obj2)).append(((String) (obj1)));
        PxLog.d("tv_tuner_service", ((StringBuilder) (obj2)).toString());
        Object obj3 = mPisClientUserLock;
        obj3;
        JVM INSTR monitorenter ;
        if(mPisClientCancelled)
            return;
        if(mRecommendUser == null)
        {
            RecommendApiClientUser recommendapiclientuser = JVM INSTR new #1663 <Class RecommendApiClientUser>;
            recommendapiclientuser.RecommendApiClientUser(getApplicationContext(), stateInfo);
            mRecommendUser = recommendapiclientuser;
        }
        RecommendInfo arecommendinfo[] = mRecommendUser.getRecommend(((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getTarget(), true, l, l1, ((String) (obj1)), areaCode, ((List) (obj4)), k, ((String) (obj5)));
        stateInfo.ResponseObject(((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getTarget(), 15, arecommendinfo);
        break; /* Loop/switch isn't completed */
        stateInfo;
        obj3;
        JVM INSTR monitorexit ;
        throw stateInfo;
_L21:
        Object aobj1[] = (Object[])((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getNotifyParam();
        if(aobj1 != null && aobj1.length != 0 && (aobj1[0] instanceof Integer))
        {
            obj1 = (Integer)aobj1[0];
            arecommendinfo = (Integer)aobj1[1];
            devInfo = (DeviceLogData)aobj1[2];
            if((new BuildUtilityWrapper(this)).getProductType() != jp.pixela.common.ProductInfo.ProductTypeT.PRODUCT_PIX_SMB100)
            {
                ((DeviceLogData) (devInfo)).setSerialNumber(getCognitiveUserId(getApplicationContext()));
                ((DeviceLogData) (devInfo)).setModelName(Build.MODEL);
                stateInfo = getApplicationContext().getPackageManager();
                try
                {
                    stateInfo = stateInfo.getPackageInfo(getApplicationContext().getPackageName(), 0).versionName;
                }
                // Misplaced declaration of an exception variable
                catch(final Message stateInfo)
                {
                    stateInfo = "0";
                }
                ((DeviceLogData) (devInfo)).setVersion(stateInfo);
            }
            stateInfo = new StringBuilder();
            stateInfo.append("notify REQUEST_SEND_LOG action=");
            stateInfo.append(obj1);
            stateInfo.append(" areaCode=");
            stateInfo.append(arecommendinfo);
            stateInfo.append(" devInfo={");
            stateInfo.append(((DeviceLogData) (devInfo)).toString());
            stateInfo.append("}");
            PxLog.d("tv_tuner_service", stateInfo.toString());
            if(((Integer) (obj1)).intValue() == SendLogToEventHub.Action.ACTIVATE.getValue())
                runOnUiThread(new Runnable() {

                    public void run()
                    {
                        SendLogToEventHub.sendActivate(TvTunerService.this, devInfo, areaCode.intValue());
                    }

                    final TvTunerService this$0;
                    final Integer val$areaCode;
                    final DeviceLogData val$devInfo;

            
            {
                this$0 = TvTunerService.this;
                devInfo = devicelogdata;
                areaCode = integer;
                super();
            }
                }
);
            else
            if(((Integer) (obj1)).intValue() == SendLogToEventHub.Action.DEACTIVATE.getValue())
                runOnUiThread(new Runnable() {

                    public void run()
                    {
                        SendLogToEventHub.sendDeactivate(TvTunerService.this, devInfo, areaCode.intValue());
                    }

                    final TvTunerService this$0;
                    final Integer val$areaCode;
                    final DeviceLogData val$devInfo;

            
            {
                this$0 = TvTunerService.this;
                devInfo = devicelogdata;
                areaCode = integer;
                super();
            }
                }
);
            else
            if(((Integer) (obj1)).intValue() == SendLogToEventHub.Action.START_LIVE.getValue())
                runOnUiThread(new Runnable() {

                    public void run()
                    {
                        SendLogToEventHub.sendLivePlay(TvTunerService.this, devInfo, programData, areaCode.intValue());
                    }

                    final TvTunerService this$0;
                    final Integer val$areaCode;
                    final DeviceLogData val$devInfo;
                    final ProgramData val$programData;

            
            {
                this$0 = TvTunerService.this;
                devInfo = devicelogdata;
                programData = programdata;
                areaCode = integer;
                super();
            }
                }
);
            else
            if(((Integer) (obj1)).intValue() == SendLogToEventHub.Action.STOP_LIVE.getValue())
                runOnUiThread(new Runnable() {

                    public void run()
                    {
                        SendLogToEventHub.sendLiveStop(TvTunerService.this, devInfo, areaCode.intValue());
                    }

                    final TvTunerService this$0;
                    final Integer val$areaCode;
                    final DeviceLogData val$devInfo;

            
            {
                this$0 = TvTunerService.this;
                devInfo = devicelogdata;
                areaCode = integer;
                super();
            }
                }
);
            else
            if(((Integer) (obj1)).intValue() == SendLogToEventHub.Action.RESERVATION_ADDED.getValue())
                runOnUiThread(new Runnable() {

                    public void run()
                    {
                        SendLogToEventHub.sendReservationAdded(TvTunerService.this, devInfo, reservationData, areaCode.intValue());
                    }

                    final TvTunerService this$0;
                    final Integer val$areaCode;
                    final DeviceLogData val$devInfo;
                    final ReservationData val$reservationData;

            
            {
                this$0 = TvTunerService.this;
                devInfo = devicelogdata;
                reservationData = reservationdata;
                areaCode = integer;
                super();
            }
                }
);
            else
            if(((Integer) (obj1)).intValue() == SendLogToEventHub.Action.RESERVATION_UPDATED.getValue())
                runOnUiThread(new Runnable() {

                    public void run()
                    {
                        SendLogToEventHub.sendReservationUpdated(TvTunerService.this, devInfo, reservationData, areaCode.intValue());
                    }

                    final TvTunerService this$0;
                    final Integer val$areaCode;
                    final DeviceLogData val$devInfo;
                    final ReservationData val$reservationData;

            
            {
                this$0 = TvTunerService.this;
                devInfo = devicelogdata;
                reservationData = reservationdata;
                areaCode = integer;
                super();
            }
                }
);
            else
            if(((Integer) (obj1)).intValue() == SendLogToEventHub.Action.RESERVATION_DELETED.getValue())
                runOnUiThread(new Runnable() {

                    public void run()
                    {
                        SendLogToEventHub.sendReservationDeleted(TvTunerService.this, devInfo, reservationData, areaCode.intValue());
                    }

                    final TvTunerService this$0;
                    final Integer val$areaCode;
                    final DeviceLogData val$devInfo;
                    final ReservationData val$reservationData;

            
            {
                this$0 = TvTunerService.this;
                devInfo = devicelogdata;
                reservationData = reservationdata;
                areaCode = integer;
                super();
            }
                }
);
            else
            if(((Integer) (obj1)).intValue() == SendLogToEventHub.Action.START_REC_PLAY.getValue())
                runOnUiThread(new Runnable() {

                    public void run()
                    {
                        SendLogToEventHub.sendFilePlay(TvTunerService.this, devInfo, recPlayData, areaCode.intValue());
                    }

                    final TvTunerService this$0;
                    final Integer val$areaCode;
                    final DeviceLogData val$devInfo;
                    final RecPlayData val$recPlayData;

            
            {
                this$0 = TvTunerService.this;
                devInfo = devicelogdata;
                recPlayData = recplaydata;
                areaCode = integer;
                super();
            }
                }
);
            else
            if(((Integer) (obj1)).intValue() == SendLogToEventHub.Action.START_TIME_SHIFT.getValue())
                runOnUiThread(new Runnable() {

                    public void run()
                    {
                        SendLogToEventHub.sendTimeShiftPlay(TvTunerService.this, devInfo, recPlayData, areaCode.intValue());
                    }

                    final TvTunerService this$0;
                    final Integer val$areaCode;
                    final DeviceLogData val$devInfo;
                    final RecPlayData val$recPlayData;

            
            {
                this$0 = TvTunerService.this;
                devInfo = devicelogdata;
                recPlayData = recplaydata;
                areaCode = integer;
                super();
            }
                }
);
            else
            if(((Integer) (obj1)).intValue() == SendLogToEventHub.Action.STOP_REC_PLAY.getValue())
                runOnUiThread(new Runnable() {

                    public void run()
                    {
                        SendLogToEventHub.sendFileStop(TvTunerService.this, devInfo, areaCode.intValue());
                    }

                    final TvTunerService this$0;
                    final Integer val$areaCode;
                    final DeviceLogData val$devInfo;

            
            {
                this$0 = TvTunerService.this;
                devInfo = devicelogdata;
                areaCode = integer;
                super();
            }
                }
);
            else
            if(((Integer) (obj1)).intValue() == SendLogToEventHub.Action.STOP_TIME_SHIFT.getValue())
                runOnUiThread(new Runnable() {

                    public void run()
                    {
                        SendLogToEventHub.sendTimeShiftStop(TvTunerService.this, devInfo, areaCode.intValue());
                    }

                    final TvTunerService this$0;
                    final Integer val$areaCode;
                    final DeviceLogData val$devInfo;

            
            {
                this$0 = TvTunerService.this;
                devInfo = devicelogdata;
                areaCode = integer;
                super();
            }
                }
);
        }
        break; /* Loop/switch isn't completed */
_L20:
        PxLog.d("tv_tuner_service", "notify REQUEST_EXISTENCE_THUMBNAIL_CACHE");
        obj1 = TunerServiceManager.getInstance().getControlInterface();
        if(obj1 != null)
        {
            arecommendinfo = (ThumbnailInfo)((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getNotifyParam();
            l1 = arecommendinfo.getSince();
            stateInfo = new ArrayList();
            if(arecommendinfo.getProgramInfos() != null)
                for(k = ((flag1) ? 1 : 0); k < arecommendinfo.getProgramInfos().length; k++)
                    stateInfo.add(arecommendinfo.getProgramInfos()[k]);

            boolean flag5 = (new ThumbnailApiClientUser(getApplicationContext())).isCacheExists(l1, stateInfo);
            ((ControlInterface) (obj1)).ResponseBoolean(((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getTarget(), 13, flag5);
        }
        break; /* Loop/switch isn't completed */
_L19:
        boolean flag6;
        PxLog.d("tv_tuner_service", "notify REQUEST_GET_THUMBNAIL");
        obj1 = TunerServiceManager.getInstance().getControlInterface();
        if(obj1 == null)
            break; /* Loop/switch isn't completed */
        aobj1 = (ThumbnailInfo)((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getNotifyParam();
        l1 = ((ThumbnailInfo) (aobj1)).getSince();
        l = ((ThumbnailInfo) (aobj1)).getUntil();
        stateInfo = ((ThumbnailInfo) (aobj1)).getBroadcastTypeString();
        obj3 = asList(((ThumbnailInfo) (aobj1)).getServiceIds());
        arecommendinfo = new ArrayList();
        if(((ThumbnailInfo) (aobj1)).getProgramInfos() != null)
            for(k = 0; k < ((ThumbnailInfo) (aobj1)).getProgramInfos().length; k++)
                arecommendinfo.add(((ThumbnailInfo) (aobj1)).getProgramInfos()[k]);

        flag6 = ((ThumbnailInfo) (aobj1)).getNeedGenreImage();
        aobj1 = new StringBuilder();
        ((StringBuilder) (aobj1)).append("since=");
        ((StringBuilder) (aobj1)).append(l1);
        ((StringBuilder) (aobj1)).append(", until=");
        ((StringBuilder) (aobj1)).append(l);
        ((StringBuilder) (aobj1)).append(", broadcastType=");
        ((StringBuilder) (aobj1)).append(stateInfo);
        PxLog.d("tv_tuner_service", ((StringBuilder) (aobj1)).toString());
        aobj1 = ((Object []) (mPisClientUserLock));
        aobj1;
        JVM INSTR monitorenter ;
        if(mPisClientCancelled)
            return;
        if(mThumbnailUser == null)
        {
            obj5 = JVM INSTR new #1785 <Class ThumbnailApiClientUser>;
            ((ThumbnailApiClientUser) (obj5)).ThumbnailApiClientUser(getApplicationContext());
            mThumbnailUser = ((ThumbnailApiClientUser) (obj5));
        }
        aobj1 = mThumbnailUser;
        aobj1;
        JVM INSTR monitorenter ;
        obj3 = mThumbnailUser.getThumbnail(true, l1, l, stateInfo, ((List) (obj3)), arecommendinfo, flag6);
        Object obj = null;
        stateInfo = obj;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_2371;
        stateInfo = obj;
        Object aobj[];
        if(((List) (obj3)).size() <= 0)
            break MISSING_BLOCK_LABEL_2371;
        aobj = new ThumbnailInfo[((List) (obj3)).size()];
        k = j;
_L27:
        if(k >= aobj.length)
            break; /* Loop/switch isn't completed */
        obj5 = (ThumbnailData)((List) (obj3)).get(k);
        stateInfo = JVM INSTR new #1781 <Class ThumbnailInfo>;
        stateInfo.ThumbnailInfo();
        aobj[k] = stateInfo;
        aobj[k].setServiceId(((ThumbnailData) (obj5)).getServiceId());
        aobj[k].setBroadcastType(((ThumbnailData) (obj5)).getBroadcastType());
        Object obj6 = aobj[k];
        if(!((ThumbnailData) (obj5)).isGenre()) goto _L24; else goto _L23
_L23:
        stateInfo = jp.pixela.player_service.message.ThumbnailInfo.ThumbnailTypeT.GENRE;
          goto _L25
_L24:
        stateInfo = jp.pixela.player_service.message.ThumbnailInfo.ThumbnailTypeT.PIS;
_L25:
        ((ThumbnailInfo) (obj6)).setThumbnailType(stateInfo);
        aobj[k].setImage(((ThumbnailData) (obj5)).getImage());
        aobj[k].setImageWidth(((ThumbnailData) (obj5)).getImageWidth());
        aobj[k].setImageHeight(((ThumbnailData) (obj5)).getImageHeight());
        k++;
        if(true) goto _L27; else goto _L26
_L26:
        stateInfo = JVM INSTR new #486 <Class StringBuilder>;
        stateInfo.StringBuilder();
        stateInfo.append("receive thumbnail array size=");
        stateInfo.append(aobj.length);
        PxLog.d("tv_tuner_service", stateInfo.toString());
        stateInfo = ((Message) (aobj));
        ((ControlInterface) (obj1)).ResponseObject(((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getTarget(), 12, stateInfo);
        aobj1;
        JVM INSTR monitorexit ;
        break; /* Loop/switch isn't completed */
        stateInfo;
        aobj1;
        JVM INSTR monitorexit ;
        throw stateInfo;
        stateInfo;
        aobj1;
        JVM INSTR monitorexit ;
        throw stateInfo;
_L18:
        stateInfo = (BmlSoundInfo)((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getNotifyParam();
        if(mBmlSoundPlayer != null)
            mBmlSoundPlayer.setPlay(stateInfo.GetSoundType(), stateInfo.GetSoundId(), stateInfo.IsStart(), stateInfo.IsLoop(), stateInfo.GetFilePath());
        break; /* Loop/switch isn't completed */
_L17:
        mBmlOutputArea = (BmlOutputArea)((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getNotifyParam();
        stateInfo = new StringBuilder();
        stateInfo.append("NOTIFY_BML_SET_OUTPUT_POSITION:");
        stateInfo.append(mBmlOutputArea.toString());
        PxLog.d("tv_tuner_service", stateInfo.toString());
        runOnUiThread(new Runnable() {

            public void run()
            {
                updateCaptionViewArea();
            }

            final TvTunerService this$0;

            
            {
                this$0 = TvTunerService.this;
                super();
            }
        }
);
        break; /* Loop/switch isn't completed */
_L16:
        PxLog.d("tv_tuner_service", "NOTIFY_BML_UPDATE_VIDEO_POSITION");
        runOnUiThread(new Runnable() {

            public void run()
            {
                if(videoView != null)
                {
                    Rect rect1 = mBmlOutputArea.CalcBmlOutputRect(TvTunerService.this);
                    StringBuilder stringbuilder5 = new StringBuilder();
                    stringbuilder5.append("NOTIFY_BML_UPDATE_VIDEO_POSITION:set layout:");
                    stringbuilder5.append(rect.toString());
                    PxLog.d("tv_tuner_service", stringbuilder5.toString());
                    if(rect.right - rect.left != 0 && rect.bottom - rect.top != 0)
                    {
                        android.view.ViewGroup.LayoutParams layoutparams = videoView.getLayoutParams();
                        if(layoutparams instanceof android.view.ViewGroup.MarginLayoutParams)
                        {
                            android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)layoutparams;
                            int i1 = rect.width();
                            int j1 = rect.height();
                            float f = i1;
                            float f1 = j1;
                            float f2;
                            float f3;
                            if(f / f1 > 1.777778F)
                            {
                                f2 = (16F * f1) / 9F;
                                f3 = f1;
                            } else
                            {
                                f3 = (9F * f) / 16F;
                                f2 = f;
                            }
                            layoutparams.width = (int)((double)f2 + 0.5D);
                            layoutparams.height = (int)((double)f3 + 0.5D);
                            StringBuilder stringbuilder6 = new StringBuilder();
                            stringbuilder6.append("NOTIFY_BML_UPDATE_VIDEO_POSITION:setMargins left:");
                            j1 = rect.left;
                            i1 = (int)((f - f2) / 2.0F);
                            stringbuilder6.append(j1 + i1 + rect1.left);
                            stringbuilder6.append(" top:");
                            int k1 = rect.top;
                            j1 = (int)((f1 - f3) / 2.0F);
                            stringbuilder6.append(k1 + j1 + rect1.top);
                            PxLog.d("tv_tuner_service", stringbuilder6.toString());
                            stringbuilder6 = new StringBuilder();
                            stringbuilder6.append("NOTIFY_BML_UPDATE_VIDEO_POSITION:setLayoutParam layoutParams.width:");
                            stringbuilder6.append(layoutparams.width);
                            stringbuilder6.append(" layoutParams.height:");
                            stringbuilder6.append(layoutparams.height);
                            PxLog.d("tv_tuner_service", stringbuilder6.toString());
                            videoView.setLayoutParams(marginlayoutparams);
                            videoView.setX(rect.left + i1 + rect1.left);
                            videoView.setY(rect.top + j1 + rect1.top);
                        }
                    } else
                    {
                        resetVideoLayout();
                    }
                }
            }

            final TvTunerService this$0;
            final Rect val$rect;

            
            {
                this$0 = TvTunerService.this;
                rect = rect1;
                super();
            }
        }
);
        break; /* Loop/switch isn't completed */
_L15:
        runOnUiThread(new Runnable() {

            public void run()
            {
                deleteLayout();
            }

            final TvTunerService this$0;

            
            {
                this$0 = TvTunerService.this;
                super();
            }
        }
);
        break; /* Loop/switch isn't completed */
_L14:
        runOnUiThread(new Runnable() {

            public void run()
            {
                setLayout();
            }

            final TvTunerService this$0;

            
            {
                this$0 = TvTunerService.this;
                super();
            }
        }
);
        break; /* Loop/switch isn't completed */
_L13:
        stateInfo = TunerServiceManager.getInstance().getControlInterface();
        if(stateInfo == null)
            break; /* Loop/switch isn't completed */
        obj1 = (ViewingPointInfo)((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getNotifyParam();
        aobj = ((ViewingPointInfo) (obj1)).getServiceIds();
        obj1 = ((ViewingPointInfo) (obj1)).getBroadcastTypeString();
        int ai[] = ((int []) (mPisClientUserLock));
        ai;
        JVM INSTR monitorenter ;
        if(mPisClientCancelled)
            return;
        if(mViewPointUser == null)
        {
            obj3 = JVM INSTR new #1894 <Class ViewingPointApiClientUser>;
            ((ViewingPointApiClientUser) (obj3)).ViewingPointApiClientUser(getApplicationContext());
            mViewPointUser = ((ViewingPointApiClientUser) (obj3));
        }
        ai = new int[aobj.length];
        obj6 = mViewPointUser.getViewingPoint(((String) (obj1)), asList(((int []) (aobj))));
        k = 0;
_L35:
        if(k >= aobj.length) goto _L29; else goto _L28
_L28:
        obj5 = ((List) (obj6)).iterator();
_L33:
        if(!((Iterator) (obj5)).hasNext()) goto _L31; else goto _L30
_L30:
        obj3 = (ViewingPointData)((Iterator) (obj5)).next();
        if(((ViewingPointData) (obj3)).getServiceId() != aobj[k]) goto _L33; else goto _L32
_L32:
        j = ((ViewingPointData) (obj3)).getPoint();
          goto _L34
_L31:
        j = 0;
_L34:
        ai[k] = j;
        k++;
          goto _L35
_L29:
        StringBuilder stringbuilder4 = new StringBuilder();
        stringbuilder4.append("broadcast=");
        stringbuilder4.append(((String) (obj1)));
        stringbuilder4.append(", serviceIds=");
        stringbuilder4.append(Arrays.toString(((int []) (aobj))));
        PxLog.d("tv_tuner_service", stringbuilder4.toString());
        StringBuilder stringbuilder3 = new StringBuilder();
        stringbuilder3.append("points=");
        stringbuilder3.append(Arrays.toString(ai));
        PxLog.d("tv_tuner_service", stringbuilder3.toString());
        stateInfo.ResponseObject(((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getTarget(), 6, ai);
        break; /* Loop/switch isn't completed */
        stateInfo;
        ai;
        JVM INSTR monitorexit ;
        throw stateInfo;
_L12:
        PxLog.d("tv_tuner_service", "REQUEST_COGNITIVE_SETTING");
        break; /* Loop/switch isn't completed */
_L11:
        PxLog.d("tv_tuner_service", "NOTIFY_SET_COGNITIVE_SETTING");
        stateInfo = (CognitiveSettingParam)((jp.pixela.common.PlayerMessenger.MessageParam) (devInfo)).getNotifyParam();
        stateInfo.isSendBehaviorHistory();
        boolean flag7 = stateInfo.isUseGoogleAssistant();
        stateInfo = stateInfo.getDeviceName();
        if(stateInfo != null && !stateInfo.isEmpty())
            mPisIotEdgeDeviceName = stateInfo;
        if(!IsInitialSettingsState() && !flag7)
            runOnUiThread(new Runnable() {

                public void run()
                {
                    startPisIotEdgeMainActivity(2);
                }

                final TvTunerService this$0;

            
            {
                this$0 = TvTunerService.this;
                super();
            }
            }
);
        break; /* Loop/switch isn't completed */
_L10:
        PxLog.d("tv_tuner_service", "REQUEST_STOP_AIRTUNER_SERVICE");
        break; /* Loop/switch isn't completed */
_L9:
        PxLog.d("tv_tuner_service", "REQUEST_START_AIRTUNER_SERVICE");
        break; /* Loop/switch isn't completed */
_L8:
        PxLog.d("tv_tuner_service", "notify NOTIFY_UPDATE_AUTO_MESSAGE");
        runOnUiThread(new Runnable() {

            public void run()
            {
                if(autoMessageInfo != null)
                {
                    jp.pixela.common.AutoMessageInfo automessageinfo = new jp.pixela.common.AutoMessageInfo();
                    if(autoMessageInfo.getDisplayAction() == jp.pixela.player_service.message.AutoMessageInfo.DisplayAction.ERASABLE_DISPLAY)
                        automessageinfo.SetDisplayType(jp.pixela.common.AutoMessageInfo.DisplayType.ERASABLE_DISPLEY);
                    else
                    if(autoMessageInfo.getDisplayAction() == jp.pixela.player_service.message.AutoMessageInfo.DisplayAction.ERASE)
                        automessageinfo.SetDisplayType(jp.pixela.common.AutoMessageInfo.DisplayType.ERASE_DIRECTION);
                    else
                    if(autoMessageInfo.getDisplayAction() == jp.pixela.player_service.message.AutoMessageInfo.DisplayAction.FORCED_DISPLAY)
                        automessageinfo.SetDisplayType(jp.pixela.common.AutoMessageInfo.DisplayType.FORCED_DISPLAY);
                    if(autoMessageInfo.getHorizontalPosition() == jp.pixela.player_service.message.AutoMessageInfo.HorizontalPosition.H_CENTER)
                        automessageinfo.SetHorizontalPosition(jp.pixela.common.AutoMessageInfo.HorizontalPosition.CENTER);
                    else
                    if(autoMessageInfo.getHorizontalPosition() == jp.pixela.player_service.message.AutoMessageInfo.HorizontalPosition.H_LEFT)
                        automessageinfo.SetHorizontalPosition(jp.pixela.common.AutoMessageInfo.HorizontalPosition.LEFT);
                    else
                    if(autoMessageInfo.getHorizontalPosition() == jp.pixela.player_service.message.AutoMessageInfo.HorizontalPosition.H_RIGHT)
                        automessageinfo.SetHorizontalPosition(jp.pixela.common.AutoMessageInfo.HorizontalPosition.RIGHT);
                    else
                    if(autoMessageInfo.getHorizontalPosition() == jp.pixela.player_service.message.AutoMessageInfo.HorizontalPosition.H_NONE)
                        automessageinfo.SetHorizontalPosition(jp.pixela.common.AutoMessageInfo.HorizontalPosition.NO_DIRECTION);
                    if(autoMessageInfo.getVerticalPosition() == jp.pixela.player_service.message.AutoMessageInfo.VerticalPosition.V_BOTTOM)
                        automessageinfo.SetVerticalPosition(jp.pixela.common.AutoMessageInfo.VerticalPosition.BOTTOM);
                    else
                    if(autoMessageInfo.getVerticalPosition() == jp.pixela.player_service.message.AutoMessageInfo.VerticalPosition.V_CENTER)
                        automessageinfo.SetVerticalPosition(jp.pixela.common.AutoMessageInfo.VerticalPosition.CENTER);
                    else
                    if(autoMessageInfo.getVerticalPosition() == jp.pixela.player_service.message.AutoMessageInfo.VerticalPosition.V_TOP)
                        automessageinfo.SetVerticalPosition(jp.pixela.common.AutoMessageInfo.VerticalPosition.TOP);
                    else
                    if(autoMessageInfo.getVerticalPosition() == jp.pixela.player_service.message.AutoMessageInfo.VerticalPosition.V_NONE)
                        automessageinfo.SetVerticalPosition(jp.pixela.common.AutoMessageInfo.VerticalPosition.NO_DIRECTION);
                    if(autoMessageInfo.getCharacterSize() == jp.pixela.player_service.message.AutoMessageInfo.CharacterSize.C_SIZE1)
                        automessageinfo.SetCharacterSize(jp.pixela.common.AutoMessageInfo.CharacterSize.SIZE1);
                    else
                    if(autoMessageInfo.getCharacterSize() == jp.pixela.player_service.message.AutoMessageInfo.CharacterSize.C_SIZE2)
                        automessageinfo.SetCharacterSize(jp.pixela.common.AutoMessageInfo.CharacterSize.SIZE2);
                    else
                    if(autoMessageInfo.getCharacterSize() == jp.pixela.player_service.message.AutoMessageInfo.CharacterSize.C_SIZE3)
                        automessageinfo.SetCharacterSize(jp.pixela.common.AutoMessageInfo.CharacterSize.SIZE3);
                    else
                    if(autoMessageInfo.getCharacterSize() == jp.pixela.player_service.message.AutoMessageInfo.CharacterSize.C_NONE)
                        automessageinfo.SetCharacterSize(jp.pixela.common.AutoMessageInfo.CharacterSize.RESERVED);
                    automessageinfo.SetText(autoMessageInfo.getMessage());
                    if(autoMessageView != null)
                        autoMessageView.AutoMessageChaned(automessageinfo);
                }
            }

            final TvTunerService this$0;
            final AutoMessageInfo val$autoMessageInfo;

            
            {
                this$0 = TvTunerService.this;
                autoMessageInfo = automessageinfo;
                super();
            }
        }
);
        break; /* Loop/switch isn't completed */
_L6:
        PxLog.d("tv_tuner_service", "REQUEST_APP_FINISH");
        isAppFinishingForRequest = true;
        finishClient();
        break; /* Loop/switch isn't completed */
_L4:
        runOnUiThread(new Runnable() {

            public void run()
            {
                sendRegisterRecordingHdd();
            }

            final TvTunerService this$0;

            
            {
                this$0 = TvTunerService.this;
                super();
            }
        }
);
        break; /* Loop/switch isn't completed */
_L2:
        runOnUiThread(new Runnable() {

            public void run()
            {
                sendBroadcastUpdateStereoSetting(dualMono);
            }

            final TvTunerService this$0;
            final boolean val$dualMono;

            
            {
                this$0 = TvTunerService.this;
                dualMono = flag;
                super();
            }
        }
);
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("onActivityResult requestCode=");
        stringbuilder.append(i);
        stringbuilder.append(", resultCode=");
        stringbuilder.append(j);
        stringbuilder.append(", intent=");
        stringbuilder.append(IntentHelper.dump(intent));
        PxLog.v("tv_tuner_service", stringbuilder.toString());
        super.onActivityResult(i, j, intent);
        switch(i)
        {
        default:
            if(mContentShellManager != null)
                mContentShellManager.onActivityResult(i, j, intent);
            break;

        case 101: // 'e'
            if(100 == j)
                break;
            Intent intent1 = new Intent();
            intent1.setComponent(new ComponentName("jp.pixela.stationtv.xit", "jp.pixela.atv_app.MainActivity"));
            boolean flag;
            if(intent != null)
                flag = intent.getBooleanExtra("switch TV", false);
            else
                flag = false;
            if(flag)
            {
                intent = String.format("?sm=%s", new Object[] {
                    "true"
                });
                StringBuilder stringbuilder1 = new StringBuilder();
                stringbuilder1.append("file:///android_asset/preview/index.html");
                stringbuilder1.append(intent);
                intent1.putExtra("URL", stringbuilder1.toString());
            } else
            {
                intent1.putExtra("Reload", true);
            }
            startActivity(intent1);
            break;

        case 100: // 'd'
            if(j == jp.pixela.view.HddFormatActivity.FormatResultCode.RESTART.getValue())
            {
                isAppFinishingForRequest = true;
                mAbortFinishOnStop = false;
                break;
            }
            if(intent != null)
                i = intent.getIntExtra("ShowMode", 0);
            else
                i = 0;
            mIsFormattedHdd = true;
            mAbortFinishOnStop = false;
            if(i == 1)
                startClient("file:///android_asset/setting/index.html#/record-setting");
            else
                startDelayedInitialize(0L, false);
            break;
        }
    }

    public void onCreate(Bundle bundle)
    {
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append("onCreate call: this=");
        ((StringBuilder) (obj)).append(this);
        PxLog.d("tv_tuner_service", ((StringBuilder) (obj)).toString());
        super.onCreate(bundle);
        bundle = new StringBuilder();
        bundle.append(getApplicationContext().getPackageName());
        bundle.append(".");
        PACKAGE_NAME_PREFIX = bundle.toString();
        requestWindowFeature(1);
        setContentView(0x7f0a003b);
        mVideoViewLayout = (FrameLayout)findViewById(0x7f0800e1);
        bundle = new StringBuilder();
        bundle.append(getFilesDir().getPath());
        bundle.append("/");
        bundle.append("settings");
        settingsPath_ = bundle.toString();
        if(TunerServiceManager.getInstance().isTerminatingTunerService())
        {
            PxLog.d("tv_tuner_service", "onCreate() out. terminating");
            Toast.makeText(getApplicationContext(), 0x7f0e00a2, 1).show();
            finish();
            return;
        }
        mAbortFinishOnStop = false;
        mShouldForegroundAtvApp = false;
        mStartClient = false;
        if(!HddFormatManager.isConnectedUsbDevice(getApplicationContext(), DeviceDefines.sSupportTunerList))
        {
            isAppFinishingForRequest = true;
            mFinishToastResourceId = 0x7f0e0062;
            Toast.makeText(this, 0x7f0e0062, 1).show();
            return;
        }
        mIsFormattedHdd = HddFormatManager.isFormatted(this);
        mAbortFinishOnStop = false;
        intentOnInit = getIntent();
        bundle = new StringBuilder();
        bundle.append("onCreate() intentOnInit=");
        bundle.append(IntentHelper.dump(intentOnInit));
        PxLog.v("tv_tuner_service", bundle.toString());
        mAllowUsbAudio = false;
        if(!mAllowUsbAudio && UsbDeviceObserver.isConnectedUsbAudio(this))
        {
            isAppFinishingForRequest = true;
            mFinishToastResourceId = 0x7f0e0063;
            Toast.makeText(this, 0x7f0e0063, 1).show();
            return;
        }
        mFinishToastResourceId = 0;
        bundle = new StringBuilder();
        bundle.append(getFilesDir().getPath());
        bundle.append("/");
        bundle.append("database");
        bundle = bundle.toString();
        obj = new File(bundle);
        if(!((File) (obj)).exists())
            if(((File) (obj)).mkdir())
            {
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append(bundle);
                stringbuilder.append("is created!");
                PxLog.d("tv_tuner_service", stringbuilder.toString());
            } else
            {
                StringBuilder stringbuilder1 = new StringBuilder();
                stringbuilder1.append("onCreate() Failed to create directory:");
                stringbuilder1.append(bundle);
                PxLog.d("tv_tuner_service", stringbuilder1.toString());
            }
        bundle = new File(settingsPath_);
        if(!bundle.exists())
            if(bundle.mkdir())
            {
                bundle = new StringBuilder();
                bundle.append(settingsPath_);
                bundle.append("is created!");
                PxLog.d("tv_tuner_service", bundle.toString());
            } else
            {
                bundle = new StringBuilder();
                bundle.append("onCreate() Failed to create directory:");
                bundle.append(settingsPath_);
                PxLog.d("tv_tuner_service", bundle.toString());
            }
        WakelockHelper.getInstance().init(getApplicationContext(), 1, "TunerService");
        if(!FontManager.CopyFont(this))
            PxLog.v("tv_tuner_service", "onCreate() CopyFont failure.");
        mInitializedTunerService.set(false);
        TunerServiceManager.getInstance().setContext(this);
        mLNBSettings = new LNBSettings(this);
        mTunerTimeDiffService = new TunerTimeDiffService(getApplicationContext());
        mTunerTimeDiffService.start(TunerServiceManager.getInstance().getTunerService());
        mIoTEdgeCommandReceiver = new IoTEdgeCommandReceiver();
        bundle = new IntentFilter();
        bundle.addAction("jp.co.pixela.pis_iot_edge.BROADCAST");
        registerReceiver(mIoTEdgeCommandReceiver, bundle);
        mPisIotEdgeControlResultReceiver = new PisIotEdgeControlResultReceiver();
        bundle = new IntentFilter();
        bundle.addAction("jp.pixela.stationtv.xit.ACTION_PIS_IOT_EDGE_CONTROL_RESULT");
        registerReceiver(mPisIotEdgeControlResultReceiver, bundle);
        mUpdateEPGReceiver = new UpdateEPGReceiver(TunerServiceManager.getInstance().getControlInterface());
        bundle = new IntentFilter();
        bundle.addAction("ACTION_DB_UPDATER_UPDATE_EPG");
        registerReceiver(mUpdateEPGReceiver, bundle);
        mPreparingTime = 0;
        if(intentOnInit != null && !IsInitialSettingsState())
        {
            mPreparingTime = intentOnInit.getIntExtra("preparing_time", 0);
            if(mPreparingTime != 0)
                startPreparingAnimation();
        }
        DatabaseUpdaterHandler.setFirstAlarm(this);
        mFinishAppReceiver = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent)
            {
                context = new StringBuilder();
                context.append("tv_tuner_service in. intent=");
                context.append(IntentHelper.dump(intent));
                Logger.v(context.toString(), new Object[0]);
                if(intent == null)
                {
                    Logger.w("tv_tuner_service out. intent == null", new Object[0]);
                    return;
                }
                if(!"jp.pixela.stationtv.xit.ACTION_FINISH_PLAYER_SERVICE".equals(intent.getAction()))
                {
                    Logger.v("tv_tuner_service out. !ACTION_FINISH_PLAYER_SERVICE", new Object[0]);
                    return;
                } else
                {
                    PxLog.d("tv_tuner_service", "Receive ACTION_FINISH_PLAYER_SERVICE.");
                    isAppFinishingForRequest = true;
                    finishClient();
                    Logger.v("tv_tuner_service out", new Object[0]);
                    return;
                }
            }

            final TvTunerService this$0;

            
            {
                this$0 = TvTunerService.this;
                super();
            }
        }
;
        bundle = new IntentFilter();
        bundle.addAction("jp.pixela.stationtv.xit.ACTION_FINISH_PLAYER_SERVICE");
        registerReceiver(mFinishAppReceiver, bundle);
        mVideoViewLayoutMeasurable = false;
        bundle = mVideoViewLayout.getViewTreeObserver();
        if(bundle != null)
            bundle.addOnGlobalLayoutListener(new android.view.ViewTreeObserver.OnGlobalLayoutListener() {

                public void onGlobalLayout()
                {
                    PxLog.v("tv_tuner_service", "onGlobalLayout() in");
                    mVideoViewLayoutMeasurable = true;
                    setLayoutIfEnabled();
                    if(android.os.Build.VERSION.SDK_INT < 16)
                        mVideoViewLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    else
                        mVideoViewLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

                final TvTunerService this$0;

            
            {
                this$0 = TvTunerService.this;
                super();
            }
            }
);
        mUsbDeviceObserver.setUsbAudioEventListener(this);
        mUsbDeviceObserver.registerReceiver(this);
    }

    protected void onDestroy()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("onDestroy call: this=");
        stringbuilder.append(this);
        PxLog.d("tv_tuner_service", stringbuilder.toString());
        if(mFinishAppReceiver != null)
        {
            unregisterReceiver(mFinishAppReceiver);
            mFinishAppReceiver = null;
        }
        if(mIoTEdgeCommandReceiver != null)
        {
            unregisterReceiver(mIoTEdgeCommandReceiver);
            mIoTEdgeCommandReceiver = null;
        }
        if(mPisIotEdgeControlResultReceiver != null)
        {
            unregisterReceiver(mPisIotEdgeControlResultReceiver);
            mPisIotEdgeControlResultReceiver = null;
        }
        if(mUpdateEPGReceiver != null)
        {
            unregisterReceiver(mUpdateEPGReceiver);
            mUpdateEPGReceiver = null;
        }
        mUsbDeviceObserver.unregisterReceiver(this);
        abandonAudioFocus();
        sendBroadcastUpdateStereoSetting(false);
        stopPreparingTimer();
        removePreparingAnimation();
        super.onDestroy();
    }

    public void onInitialize(TunerServiceManager.InitializeResult initializeresult, boolean flag)
    {
        PxLog.d("tv_tuner_service", "onInitialize.");
        TunerServiceManager.InitializeResult initializeresult1 = TunerServiceManager.InitializeResult.Success;
        boolean flag1 = true;
        if(initializeresult == initializeresult1)
        {
            if(mInitializedTunerService.get())
                return;
            long l;
            if(flag)
                l = 3000L;
            else
                l = mPreparingTime * 1000;
            if(mPreparingTime != 0)
                flag = flag1;
            else
                flag = false;
            startDelayedInitialize(l, flag);
        } else
        if(initializeresult == TunerServiceManager.InitializeResult.StartTunerService_Timeout && !isFinishing() && !isAppFinishingForRequest)
        {
            (new Handler()).postDelayed(new Runnable() {

                public void run()
                {
                    finish();
                    isAppFinishingForRequest = false;
                    Logger.v("tv_tuner_service terminate", new Object[0]);
                    TunerServiceManager.getInstance().terminate(TvTunerService.this, true);
                }

                final TvTunerService this$0;

            
            {
                this$0 = TvTunerService.this;
                super();
            }
            }
, 5000L);
            Toast.makeText(getApplicationContext(), 0x7f0e0061, 1).show();
        }
    }

    public void onLNBShortOccur()
    {
        PxLog.v("tv_tuner_service", "onLNBShortOccur");
        ControlInterface controlinterface = TunerServiceManager.getInstance().getControlInterface();
        if(controlinterface != null)
            controlinterface.NotifyLNBShortOccur();
    }

    protected void onNewIntent(Intent intent)
    {
        PxLog.v("tv_tuner_service", "onNewIntent() in");
        super.onNewIntent(intent);
        intentOnInit = intent;
        intent = new StringBuilder();
        intent.append("onNewIntent() intentOnInit=");
        intent.append(IntentHelper.dump(intentOnInit));
        PxLog.v("tv_tuner_service", intent.toString());
        PxLog.v("tv_tuner_service", "onNewIntent() out");
    }

    public void onPause()
    {
        boolean flag = isFinishing();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("onPause call: this=");
        stringbuilder.append(this);
        stringbuilder.append(", isFinishing=");
        stringbuilder.append(flag);
        PxLog.d("tv_tuner_service", stringbuilder.toString());
        super.onPause();
    }

    public void onResume()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("onResume call: this=");
        stringbuilder.append(this);
        PxLog.d("tv_tuner_service", stringbuilder.toString());
        super.onResume();
        if(intentOnInit != null)
        {
            Bundle bundle = intentOnInit.getExtras();
            if(bundle != null)
                bundle.getBoolean("REMOVE_SCREEN_OFF_FILE", false);
        }
        if(IsInitialSettingsState() && !mCancelStartSplash)
            startSplash();
        if(TunerServiceManager.getInstance().isTerminatingTunerService())
        {
            mShouldForegroundAtvApp = false;
            PxLog.d("tv_tuner_service", "onResume() out. terminating");
            Toast.makeText(getApplicationContext(), 0x7f0e00a2, 1).show();
            finish();
            return;
        }
        if(isAppFinishingForRequest)
        {
            mShouldForegroundAtvApp = false;
            if(mFinishToastResourceId != 0)
            {
                (new Handler()).postDelayed(new Runnable() {

                    public void run()
                    {
                        finish();
                        isAppFinishingForRequest = false;
                    }

                    final TvTunerService this$0;

            
            {
                this$0 = TvTunerService.this;
                super();
            }
                }
, 5000L);
            } else
            {
                finish();
                isAppFinishingForRequest = false;
            }
            return;
        }
        if(!mInitializedTunerService.get())
        {
            startClient("about:blank");
            TunerServiceManager.getInstance().initialize(this);
        } else
        {
            onVoiceSearchResult(null, mStartPreview);
        }
        keepScreenOn();
    }

    public void onStart()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("onStart call: this=");
        stringbuilder.append(this);
        PxLog.d("tv_tuner_service", stringbuilder.toString());
        super.onStart();
        synchronized(mPisClientUserLock)
        {
            mPisClientCancelled = false;
        }
        if(TunerServiceManager.getInstance().isTerminatingTunerService())
        {
            PxLog.d("tv_tuner_service", "onStart() out. terminating");
            Toast.makeText(getApplicationContext(), 0x7f0e00a2, 1).show();
            finish();
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void onStop()
    {
        boolean flag = isFinishing();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("onStop call: this=");
        stringbuilder.append(this);
        stringbuilder.append(", isFinishing=");
        stringbuilder.append(flag);
        PxLog.d("tv_tuner_service", stringbuilder.toString());
        super.onStop();
        if(mAbortFinishOnStop)
            return;
        if(mTunerTimeDiffService != null)
        {
            mTunerTimeDiffService.stop();
            mTunerTimeDiffService = null;
        }
        synchronized(mPisClientUserLock)
        {
            if(mThumbnailUser != null)
                mThumbnailUser.finalize();
            if(mRecommendUser != null)
                mRecommendUser.finalize();
            if(mViewPointUser != null)
                mViewPointUser.finalize();
            mPisClientCancelled = true;
        }
        stopPlayer(false);
        clearKeepScreenOn();
        if(mContentShellManager != null)
        {
            mContentShellManager.onStop();
            mContentShellManager.onDestroy();
            mContentShellManager = null;
        }
        flag = IsInitialSettingsState();
        if(!TunerServiceManager.getInstance().terminate(this, flag))
            terminateApp(false);
        isFirst = true;
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void onTerminate()
    {
        PxLog.d("tv_tuner_service", "onTerminate");
        terminateApp(false);
    }

    public void onTunerStopped()
    {
        PxLog.d("tv_tuner_service", "onTunerStopped");
        (new ThumbnailApiClientUser(getApplicationContext())).deleteThumbnailCache();
        isAppFinishingForRequest = true;
        finishClient();
        Toast.makeText(getApplicationContext(), 0x7f0e0062, 1).show();
    }

    public void onUsbAudioConnectionChanged(boolean flag)
    {
        if(flag && !mAllowUsbAudio)
        {
            (new Handler()).postDelayed(new Runnable() {

                public void run()
                {
                    isAppFinishingForRequest = true;
                    finishClient();
                    finish();
                }

                final TvTunerService this$0;

            
            {
                this$0 = TvTunerService.this;
                super();
            }
            }
, 5000L);
            Toast.makeText(getApplicationContext(), 0x7f0e0063, 1).show();
        }
    }

    void restartPlayer()
    {
        if(mState == 0 && isFirst)
        {
            InitStreamLayer();
            startPlayer();
            isFirst = false;
        }
    }

    void startPlayer()
    {
        if(mState == 0)
            mState = 1;
    }

    void stopPlayer(boolean flag)
    {
        if(mState == 1)
        {
            mState = 0;
            clearLayout();
        }
    }

    static final boolean $assertionsDisabled = false;
    public static final String ACTION_PIS_IOT_EDGE_CONTROL = "ACTION_PIS_IOT_EDGE_CONTROL";
    public static final String ACTION_PIS_IOT_EDGE_CONTROL_RESULT = "ACTION_PIS_IOT_EDGE_CONTROL_RESULT";
    private static final String ACTION_WEB_VIEW_LOAD_URL = "ACTION_WEB_VIEW_LOAD_URL";
    private static final String ACTION_WEB_VIEW_LOAD_URL_FOR_SETTING = "ACTION_WEB_VIEW_LOAD_URL_FOR_SETTING";
    private static final String ATV_APP_URL_INITIAL_PIS_CANCEL = "file:///android_asset/initial/index.html#/GoogleAssistantSettingCancel";
    private static final String ATV_APP_URL_INITIAL_PIS_FAILURE = "file:///android_asset/initial/index.html#/GoogleAssistantSettingFailure";
    private static final String ATV_APP_URL_INITIAL_PIS_NOTICE = "file:///android_asset/initial/index.html#/GoogleAssistantNotice3Wrapper";
    private static final String ATV_APP_URL_INITIAL_PIS_SUCCESS = "file:///android_asset/initial/index.html#/GoogleAssistantSettingSuccess";
    private static final String ATV_APP_URL_SETTING_PIS_CANCEL = "file:///android_asset/setting/index.html#/google-assistant-setting-cancel";
    private static final String ATV_APP_URL_SETTING_PIS_FAILURE = "file:///android_asset/setting/index.html#/google-assistant-setting-failure";
    private static final String ATV_APP_URL_SETTING_PIS_NOTICE = "file:///android_asset/setting/index.html#/google-assistant-notice-3";
    private static final String ATV_APP_URL_SETTING_PIS_SUCCESS = "file:///android_asset/setting/index.html#/google-assistant-setting-success";
    public static final String EXTRA_COMMAND = "EXTRA_COMMAND";
    public static final String EXTRA_DEVICE_NAME = "DEVICE_NAME";
    public static final int EXTRA_IOT_EDGE_COMMAND_DISABLE = 2;
    public static final int EXTRA_IOT_EDGE_COMMAND_ENABLE = 1;
    public static final int EXTRA_IOT_EDGE_COMMAND_FINISH_ACTIVITY = 0;
    public static final int EXTRA_IOT_EDGE_COMMAND_INVALID = -1;
    public static final int EXTRA_IOT_EDGE_RESULT_CANCEL = 4;
    public static final int EXTRA_IOT_EDGE_RESULT_FAILURE = 2;
    public static final int EXTRA_IOT_EDGE_RESULT_NOT_SUPPORTED_URL = 3;
    public static final int EXTRA_IOT_EDGE_RESULT_SUCCESS = 1;
    public static final String EXTRA_RESULT = "EXTRA_RESULT";
    private static final String EXTRA_URL = "EXTRA_URL";
    static final int IOT_EDGE_BROADCAST_ABSOLUTE_VOLUME = 102;
    static final String IOT_EDGE_BROADCAST_BROADCASTING = "BROADCASTING";
    static final String IOT_EDGE_BROADCAST_CONTROL = "CONTROL";
    static final int IOT_EDGE_BROADCAST_MUTE_VOLUME = 101;
    static final String IOT_EDGE_BROADCAST_NAME = "jp.co.pixela.pis_iot_edge.BROADCAST";
    static final int IOT_EDGE_BROADCAST_ONE_TOUCH_SELECT_CHANNEL = 1;
    static final int IOT_EDGE_BROADCAST_POWER = 200;
    static final int IOT_EDGE_BROADCAST_SELECT_CHANNEL_BY_SERVICE_ID = 2;
    static final int IOT_EDGE_BROADCAST_UP_DOWN_SELECT_CHANNEL = 0;
    static final int IOT_EDGE_BROADCAST_UP_DOWN_VOLUME = 100;
    static final String IOT_EDGE_BROADCAST_VALUE = "VALUE";
    static final String IOT_EDGE_BROADCAST_VALUES = "VALUES";
    public static final String KEY_PREPARING_TIME = "preparing_time";
    private static final String LOG_HEAD = "tv_tuner_service ";
    private static String PACKAGE_NAME_PREFIX;
    public static final String PREF_KEY_COGNITIVE_USER_ID = "COGNITIVE_USER_ID";
    public static final int PREPARING_TIME = 45;
    public static final String PX_FORCED_STEREO_SETTING = "com.android.tv.settings.action.PX_FORCED_STEREO_SETTING";
    public static final String PX_FORCED_STEREO_SETTING_CANCELLATION = "com.android.tv.settings.action.PX_FORCED_STEREO_SETTING_CANCELLATION";
    private static final int REQUEST_CODE_HDD_FORMAT = 100;
    private static final int REQUEST_CODE_PIS_IOT_EDGE_COMMAND_GET_AUTH_TOKEN = 200;
    private static final int REQUEST_CODE_TV_SETTING = 101;
    static final int STATE_IDLE = 0;
    static final int STATE_PLAYING = 1;
    private static final String TAG = "tv_tuner_service";
    AutoMessageView autoMessageView;
    private DirectVideoView captionView;
    private Intent intentOnInit;
    private boolean isAppFinishingForRequest;
    private boolean isFirst;
    private boolean mAbortFinishOnStop;
    private boolean mAllowUsbAudio;
    private android.media.AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener;
    private AudioFocusRequest mAudioFocusRequest;
    private AudioManager mAudioManager;
    BmlOutputArea mBmlOutputArea;
    BmlSoundPlayer mBmlSoundPlayer;
    private boolean mCancelStartSplash;
    private ContentShellManager mContentShellManager;
    private int mCurrentBrowserUsedKeyFlag;
    private int mCurrentPreparingTime;
    private BroadcastReceiver mFinishAppReceiver;
    private int mFinishToastResourceId;
    private String mFirstUrl;
    private BroadcastReceiver mHDDRegistrationReceiver;
    private final AtomicBoolean mInitializedTunerService = new AtomicBoolean(false);
    private IoTEdgeCommandReceiver mIoTEdgeCommandReceiver;
    private boolean mIsFormattedHdd;
    LNBSettings mLNBSettings;
    private LNBStateReceiverInternal mLNBStateReceiver;
    private int mLastBrowserDownKeyCode;
    private String mLoadUrl;
    private boolean mPisClientCancelled;
    private final Object mPisClientUserLock = new Object();
    private PisIotEdgeControlResultReceiver mPisIotEdgeControlResultReceiver;
    private String mPisIotEdgeDeviceName;
    jp.pixela.common.PlayerMessenger.PlayerObserver mPlayerObserver;
    private ProgressBar mPreparingProgressBar;
    private int mPreparingTime;
    private CountDownTimer mPreparingTimer;
    private RecommendApiClientUser mRecommendUser;
    private Integer mResultCode;
    private boolean mShouldForegroundAtvApp;
    ImageView mSplashAnimView;
    private boolean mStartClient;
    private boolean mStartPreview;
    int mState;
    private ThumbnailApiClientUser mThumbnailUser;
    private TimeChangedReceiver mTimeChangedReceiver;
    private TunerTimeDiffService mTunerTimeDiffService;
    private UpdateEPGReceiver mUpdateEPGReceiver;
    private UsbDeviceObserver mUsbDeviceObserver;
    private Object mUserIdLock;
    private FrameLayout mVideoViewLayout;
    boolean mVideoViewLayoutMeasurable;
    private ViewingPointApiClientUser mViewPointUser;
    String settingsPath_;
    private DirectVideoView videoView;
    private FrameLayout videoViewLayout;







/*
    static int access$1102(TvTunerService tvtunerservice, int i)
    {
        tvtunerservice.mLastBrowserDownKeyCode = i;
        return i;
    }

*/

















/*
    static boolean access$2802(TvTunerService tvtunerservice, boolean flag)
    {
        tvtunerservice.mAbortFinishOnStop = flag;
        return flag;
    }

*/





/*
    static int access$3008(TvTunerService tvtunerservice)
    {
        int i = tvtunerservice.mCurrentPreparingTime;
        tvtunerservice.mCurrentPreparingTime = i + 1;
        return i;
    }

*/







/*
    static Integer access$802(TvTunerService tvtunerservice, Integer integer)
    {
        tvtunerservice.mResultCode = integer;
        return integer;
    }

*/



/*
    static boolean access$902(TvTunerService tvtunerservice, boolean flag)
    {
        tvtunerservice.isAppFinishingForRequest = flag;
        return flag;
    }

*/

    // Unreferenced inner class jp/pixela/player_service/TvTunerService$IoTEdgeCommandReceiver$1

/* anonymous class */
    class IoTEdgeCommandReceiver._cls1
        implements Runnable
    {

        public void run()
        {
            Object obj = _fld0;
            char c;
            if(value > 0)
                c = '\246';
            else
                c = '\247';
            ((TvTunerService) (obj)).sendKeyDownUpSync(c);
            obj = new StringBuilder();
            ((StringBuilder) (obj)).append("sendKeyDownUpSync(");
            ((StringBuilder) (obj)).append(value);
            ((StringBuilder) (obj)).append(") is invoked");
            PxLog.d("tv_tuner_service", ((StringBuilder) (obj)).toString());
        }

        final IoTEdgeCommandReceiver this$1;
        final int val$value;

            
            {
                this$1 = final_iotedgecommandreceiver;
                value = I.this;
                super();
            }
    }


    // Unreferenced inner class jp/pixela/player_service/TvTunerService$IoTEdgeCommandReceiver$2

/* anonymous class */
    class IoTEdgeCommandReceiver._cls2
        implements Runnable
    {

        public void run()
        {
            sendKeyDownUpSync(25);
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("sendKeyDownUpSync(");
            stringbuilder.append(value);
            stringbuilder.append(") is invoked");
            PxLog.d("tv_tuner_service", stringbuilder.toString());
        }

        final IoTEdgeCommandReceiver this$1;
        final int val$value;

            
            {
                this$1 = final_iotedgecommandreceiver;
                value = I.this;
                super();
            }
    }


    // Unreferenced inner class jp/pixela/player_service/TvTunerService$IoTEdgeCommandReceiver$3

/* anonymous class */
    class IoTEdgeCommandReceiver._cls3
        implements Runnable
    {

        public void run()
        {
            sendKeyDownUpSync(164);
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("sendKeyDownUpSync(");
            stringbuilder.append(value);
            stringbuilder.append(") is invoked");
            PxLog.d("tv_tuner_service", stringbuilder.toString());
        }

        final IoTEdgeCommandReceiver this$1;
        final boolean val$value;

            
            {
                this$1 = final_iotedgecommandreceiver;
                value = Z.this;
                super();
            }
    }


    // Unreferenced inner class jp/pixela/player_service/TvTunerService$IoTEdgeCommandReceiver$4

/* anonymous class */
    class IoTEdgeCommandReceiver._cls4
        implements Runnable
    {

        public void run()
        {
            sendKeyDownUpSync(25);
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("sendKeyDownUpSync(");
            stringbuilder.append(value);
            stringbuilder.append(") is invoked");
            PxLog.d("tv_tuner_service", stringbuilder.toString());
        }

        final IoTEdgeCommandReceiver this$1;
        final int val$value;

            
            {
                this$1 = final_iotedgecommandreceiver;
                value = I.this;
                super();
            }
    }

}
