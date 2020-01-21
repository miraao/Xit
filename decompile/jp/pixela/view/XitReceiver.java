// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.view;

import android.content.*;
import android.content.pm.PackageManager;
import android.widget.Toast;
import java.io.File;
import jp.pixela.common.*;
import jp.pixela.fw_update.CheckReservationService;
import jp.pixela.player_service.TvTunerService;
import jp.pixela.searchable_program.DatabaseUpdaterHandler;

public class XitReceiver extends BroadcastReceiver
{

    public XitReceiver()
    {
    }

    private boolean isScreenOff()
    {
        return (new File("/data/vendor/pixela/state/standby")).exists();
    }

    private void launchTV(Context context, boolean flag)
    {
        Intent intent = new Intent();
        intent.setFlags(0x14000000);
        intent.setAction("android.intent.action.MAIN");
        intent.setClass(context, jp/pixela/player_service/TvTunerService);
        intent.putExtra("REMOVE_SCREEN_OFF_FILE", true);
        try
        {
            context.startActivity(intent);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            PxLog.w(TAG, "startActivity failed.");
        }
    }

    private void sendBroadcastRestoreStereoSetting(Context context)
    {
        PxLog.d(TAG, "sendBroadcastRestoreStereoSetting.");
        if(!(new BuildUtilityWrapper(context.getApplicationContext())).isSupportDolbyDigital())
        {
            return;
        } else
        {
            Intent intent = new Intent("com.android.tv.settings.action.PX_FORCED_STEREO_SETTING_CANCELLATION");
            intent.addFlags(0x1000000);
            context.sendBroadcast(intent);
            return;
        }
    }

    private void setActivityAlias(Context context)
    {
        if(context == null)
        {
            PxLog.e(TAG, "context is null.");
            return;
        }
        PackageManager packagemanager = context.getPackageManager();
        if(packagemanager == null)
        {
            PxLog.e(TAG, "PackageManager is null.");
            return;
        }
        Object obj = context.getApplicationContext().getPackageName();
        context = new BuildUtilityWrapper(context.getApplicationContext());
        ComponentName componentname = new ComponentName(((String) (obj)), "jp.pixela.player_service.TvTunerServicePx");
        boolean flag = context.isRetail();
        byte byte0 = 2;
        byte byte1;
        if(flag)
            byte1 = 1;
        else
            byte1 = 2;
        packagemanager.setComponentEnabledSetting(componentname, byte1, 1);
        componentname = new ComponentName(((String) (obj)), "jp.pixela.player_service.TvTunerServicePS");
        if(context.isPOESMB400PS1())
            byte1 = 1;
        else
            byte1 = 2;
        packagemanager.setComponentEnabledSetting(componentname, byte1, 1);
        obj = new ComponentName(((String) (obj)), "jp.pixela.player_service.TvTunerServiceFN");
        byte1 = byte0;
        if(context.isPOESMB400FN1())
            byte1 = 1;
        packagemanager.setComponentEnabledSetting(((ComponentName) (obj)), byte1, 1);
    }

    private boolean shouldLaunchTV(Context context)
    {
        boolean flag;
        int i;
label0:
        {
            flag = false;
            if(context == null)
            {
                PxLog.e(TAG, "context is null.");
                return false;
            }
            context = context.getContentResolver();
            if(context != null)
                try
                {
                    i = android.provider.Settings.Global.getInt(context, "tvsettings_cancel_androidtv_at_boot", 0);
                    break label0;
                }
                catch(Exception exception)
                {
                    String s = TAG;
                    context = new StringBuilder();
                    context.append("Settings.Global.getInt failed. ");
                    context.append(exception.getMessage());
                    PxLog.w(s, context.toString());
                }
            i = 0;
        }
        if(i == 0)
            flag = true;
        return flag;
    }

    public void onReceive(Context context, Intent intent)
    {
        String s;
        boolean flag1;
        boolean flag3;
        s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("onReceive in. intent=");
        stringbuilder.append(IntentHelper.dump(intent));
        PxLog.v(s, stringbuilder.toString());
        s = intent.getAction();
        String s1 = TAG;
        stringbuilder = new StringBuilder();
        stringbuilder.append("action:");
        stringbuilder.append(s);
        PxLog.v(s1, stringbuilder.toString());
        "android.intent.action.BOOT_COMPLETED".equals(s);
        "jp.pixela.system.pxhwaccess.BOOT_COMPLETED".equals(s);
        if("jp.pixela.system.pxhwaccess.SCREEN_ON_ORDERED".equals(s))
        {
            boolean flag = TvTunerService.wasRunning(context);
            if(flag)
                TvTunerService.deleteScreenOffFile(context);
            context = TAG;
            intent = new StringBuilder();
            intent.append("onReceive out. wasRunning=");
            intent.append(flag);
            PxLog.v(context, intent.toString());
            return;
        }
        flag1 = "jp.pixela.stationtv.xit.action.UPDATE_RECORD_DB".equals(s);
        flag3 = true;
        if(!flag1 && !"android.intent.action.BOOT_COMPLETED".equals(s) && !"android.intent.action.TIME_SET".equals(s)) goto _L2; else goto _L1
_L2:
        if(!"jp.co.pixela.pis_iot_edge.BROADCAST".equals(s))
            break MISSING_BLOCK_LABEL_1045;
        Object obj;
        int i;
        i = intent.getIntExtra("CONTROL", -1);
        String s2 = TAG;
        obj = JVM INSTR new #209 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        ((StringBuilder) (obj)).append("command=");
        ((StringBuilder) (obj)).append(i);
        PxLog.d(s2, ((StringBuilder) (obj)).toString());
        obj = JVM INSTR new #133 <Class BuildUtilityWrapper>;
        ((BuildUtilityWrapper) (obj)).BuildUtilityWrapper(context);
        if(i == 200) goto _L4; else goto _L3
_L3:
        i;
        JVM INSTR tableswitch 100 102: default 304
    //                   100 640
    //                   101 466
    //                   102 307;
           goto _L5 _L6 _L7 _L8
_L5:
        break MISSING_BLOCK_LABEL_1045;
_L8:
        Object obj1;
        boolean flag2;
        int k;
        Object obj2;
        long l;
        long l1;
        try
        {
            int j = intent.getIntExtra("VALUE", 0);
            String s4 = TAG;
            StringBuilder stringbuilder1 = JVM INSTR new #209 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            stringbuilder1.append("value=");
            stringbuilder1.append(j);
            PxLog.d(s4, stringbuilder1.toString());
            if(((BuildUtilityWrapper) (obj)).isSmartSpeakerPropControl())
            {
                obj = JVM INSTR new #209 <Class StringBuilder>;
                ((StringBuilder) (obj)).StringBuilder();
                ((StringBuilder) (obj)).append("AbsoluteVolume,");
                ((StringBuilder) (obj)).append(String.valueOf(j));
                String s3 = ((StringBuilder) (obj)).toString();
                String s5 = TAG;
                obj = JVM INSTR new #209 <Class StringBuilder>;
                ((StringBuilder) (obj)).StringBuilder();
                ((StringBuilder) (obj)).append("SystemPropertiesProxy absolutevolume(");
                ((StringBuilder) (obj)).append(s3);
                ((StringBuilder) (obj)).append(") ");
                PxLog.d(s5, ((StringBuilder) (obj)).toString());
                SystemPropertiesProxyWrapper.smartSpeakerPropertiesSet(context, "jp.pixela.system.smartspeaker_request", s3);
            }
        }
        catch(Exception exception) { }
        break MISSING_BLOCK_LABEL_1045;
_L7:
        flag2 = intent.getBooleanExtra("VALUE", true);
        obj1 = TAG;
        obj2 = JVM INSTR new #209 <Class StringBuilder>;
        ((StringBuilder) (obj2)).StringBuilder();
        ((StringBuilder) (obj2)).append("value=");
        ((StringBuilder) (obj2)).append(flag2);
        PxLog.d(((String) (obj1)), ((StringBuilder) (obj2)).toString());
        if(!((BuildUtilityWrapper) (obj)).isSmartSpeakerPropControl())
            break MISSING_BLOCK_LABEL_1045;
        obj1 = JVM INSTR new #209 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        ((StringBuilder) (obj1)).append("Mute,");
        if(flag2)
            obj = "on";
        else
            obj = "off";
        ((StringBuilder) (obj1)).append(((String) (obj)));
        obj1 = ((StringBuilder) (obj1)).toString();
        obj = TAG;
        obj2 = JVM INSTR new #209 <Class StringBuilder>;
        ((StringBuilder) (obj2)).StringBuilder();
        ((StringBuilder) (obj2)).append("SystemPropertiesProxy mute(");
        ((StringBuilder) (obj2)).append(((String) (obj1)));
        ((StringBuilder) (obj2)).append(") ");
        PxLog.d(((String) (obj)), ((StringBuilder) (obj2)).toString());
        SystemPropertiesProxyWrapper.smartSpeakerPropertiesSet(context, "jp.pixela.system.smartspeaker_request", ((String) (obj1)));
        break MISSING_BLOCK_LABEL_1045;
_L6:
        k = intent.getIntExtra("PERCENT_VALUE", 0);
        obj2 = TAG;
        obj1 = JVM INSTR new #209 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        ((StringBuilder) (obj1)).append("value=");
        ((StringBuilder) (obj1)).append(k);
        PxLog.d(((String) (obj2)), ((StringBuilder) (obj1)).toString());
        if(k == 0)
            break MISSING_BLOCK_LABEL_1045;
        if(((BuildUtilityWrapper) (obj)).isSmartSpeakerPropControl())
        {
            obj = JVM INSTR new #209 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            ((StringBuilder) (obj)).append("UpDownVolume,");
            ((StringBuilder) (obj)).append(String.valueOf(k));
            obj2 = ((StringBuilder) (obj)).toString();
            obj = TAG;
            obj1 = JVM INSTR new #209 <Class StringBuilder>;
            ((StringBuilder) (obj1)).StringBuilder();
            ((StringBuilder) (obj1)).append("SystemPropertiesProxy updownvolume(");
            ((StringBuilder) (obj1)).append(((String) (obj2)));
            ((StringBuilder) (obj1)).append(") ");
            PxLog.d(((String) (obj)), ((StringBuilder) (obj1)).toString());
            SystemPropertiesProxyWrapper.smartSpeakerPropertiesSet(context, "jp.pixela.system.smartspeaker_request", ((String) (obj2)));
        }
        break MISSING_BLOCK_LABEL_1045;
_L4:
        flag2 = intent.getBooleanExtra("VALUE", true);
        obj2 = TAG;
        obj1 = JVM INSTR new #209 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        ((StringBuilder) (obj1)).append("value=");
        ((StringBuilder) (obj1)).append(flag2);
        PxLog.d(((String) (obj2)), ((StringBuilder) (obj1)).toString());
        if(!((BuildUtilityWrapper) (obj)).isSmartSpeakerPropControl()) goto _L10; else goto _L9
_L9:
        obj1 = JVM INSTR new #209 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        ((StringBuilder) (obj1)).append("Power,");
        if(flag2)
            obj = "on";
        else
            obj = "off";
        ((StringBuilder) (obj1)).append(((String) (obj)));
        obj = ((StringBuilder) (obj1)).toString();
        obj1 = TAG;
        obj2 = JVM INSTR new #209 <Class StringBuilder>;
        ((StringBuilder) (obj2)).StringBuilder();
        ((StringBuilder) (obj2)).append("SystemPropertiesProxy Power(");
        ((StringBuilder) (obj2)).append(((String) (obj)));
        ((StringBuilder) (obj2)).append(") ");
        PxLog.d(((String) (obj1)), ((StringBuilder) (obj2)).toString());
        SystemPropertiesProxyWrapper.smartSpeakerPropertiesSet(context, "jp.pixela.system.smartspeaker_request", ((String) (obj)));
_L10:
        if(!flag2)
            break MISSING_BLOCK_LABEL_1045;
        if(!ApplicationUtility.isAppRunningForeground(context))
        {
            Toast.makeText(context, 0x7f0e0091, 1).show();
            obj = JVM INSTR new #84  <Class Intent>;
            ((Intent) (obj)).Intent(context, jp/pixela/player_service/TvTunerService);
            ((Intent) (obj)).setAction("android.intent.action.MAIN");
            ((Intent) (obj)).addCategory("android.intent.category.DEFAULT");
            context.startActivity(((Intent) (obj)));
        }
        break MISSING_BLOCK_LABEL_1045;
_L1:
        DatabaseUpdaterHandler.processIntent(context, intent);
        if("jp.pixela.system.px_setup.action.REQUEST_RESERVATION_STATE".equals(s))
        {
            obj = new BuildUtilityWrapper(context);
            l = intent.getLongExtra("jp.pixela.system.px_setup.extra.START_TIME", 0L);
            l1 = intent.getLongExtra("jp.pixela.system.px_setup.extra.END_TIME", 0L);
            if(l == 0L || l1 == 0L || ApplicationUtility.isAppRunning(context.getApplicationContext()) || !((BuildUtilityWrapper) (obj)).isRecordable())
                flag3 = false;
            if(flag3)
            {
                intent = new Intent(context, jp/pixela/fw_update/CheckReservationService);
                intent.putExtra("jp.pixela.fw_update.extra.CheckReservationService.starttime", l);
                intent.putExtra("jp.pixela.fw_update.extra.CheckReservationService.endtime", l1);
                context.startService(intent);
            } else
            {
                intent = new Intent("jp.pixela.system.px_setup.action.NOTIFY_RESERVATION_STATE");
                intent.setPackage("jp.pixela.system.px_setup");
                intent.putExtra("jp.pixela.system.px_setup.extra.RESULT", false);
                intent.addFlags(0x1000000);
                context.sendBroadcast(intent);
            }
        }
        PxLog.v(TAG, "onReceive out");
        return;
    }

    private static final String ACTION_HWSERVICE_BOOT_COMPLETED = "jp.pixela.system.pxhwaccess.BOOT_COMPLETED";
    private static final String ACTION_HWSERVICE_SCREEN_ON_ORDERED = "jp.pixela.system.pxhwaccess.SCREEN_ON_ORDERED";
    private static final String ACTION_TV_SETTINGS_REQUEST_CHECK_RECORDING_HDD = "com.android.tv.settings.action.PX_REQUEST_CHECK_RECORDING_HDD";
    private static final String ACTION_TV_SETTINGS_REQUEST_REGISTER_RECORDING_HDD = "com.android.tv.settings.action.PX_REQUEST_REGISTER_RECORDING_HDD";
    static final int APP_LAUNCH_CANCELED = 1;
    static final int APP_LAUNCH_FAILED = -1;
    static final int APP_LAUNCH_SUCCEEDED = 0;
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
    static final String IOT_EDGE_PERCENT_VALUE = "PERCENT_VALUE";
    static final String PACKAGE_NAME_PX_SETUP = "jp.pixela.system.px_setup";
    private static final String TAG = "XitReceiver";

}
