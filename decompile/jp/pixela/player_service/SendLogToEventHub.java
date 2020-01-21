// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service;

import android.content.Context;
import android.content.pm.*;
import android.os.Build;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import jp.pixela.common.PxLog;
import jp.pixela.pis_client.helper.NetworkUtility;
import jp.pixela.pis_client.rest.send_log.SendLogToEventHubTask;
import jp.pixela.player_service.message.DeviceLogData;
import jp.pixela.player_service.message.ProgramData;
import jp.pixela.player_service.message.RecPlayData;
import jp.pixela.player_service.message.ReservationData;
import jp.pixela.player_service.message.TimerRecordData;
import jp.pixela.pxlibs.utils.android.log.Logger;
import jp.pixela.pxlibs.utils.android.log.LoggerRTM;
import org.json.*;

public class SendLogToEventHub
{
    public static final class Action extends Enum
    {

        public static Action valueOf(String s)
        {
            return (Action)Enum.valueOf(jp/pixela/player_service/SendLogToEventHub$Action, s);
        }

        public static Action[] values()
        {
            return (Action[])$VALUES.clone();
        }

        public int getValue()
        {
            return mValue;
        }

        private static final Action $VALUES[];
        public static final Action ACTIVATE;
        public static final Action DEACTIVATE;
        public static final Action RESERVATION_ADDED;
        public static final Action RESERVATION_DELETED;
        public static final Action RESERVATION_UPDATED;
        public static final Action START_LIVE;
        public static final Action START_REC_PLAY;
        public static final Action START_TIME_SHIFT;
        public static final Action STOP_LIVE;
        public static final Action STOP_REC_PLAY;
        public static final Action STOP_TIME_SHIFT;
        private final int mValue;

        static 
        {
            ACTIVATE = new Action("ACTIVATE", 0, 0);
            START_LIVE = new Action("START_LIVE", 1, 1);
            STOP_LIVE = new Action("STOP_LIVE", 2, 2);
            DEACTIVATE = new Action("DEACTIVATE", 3, 3);
            RESERVATION_ADDED = new Action("RESERVATION_ADDED", 4, 4);
            RESERVATION_UPDATED = new Action("RESERVATION_UPDATED", 5, 5);
            RESERVATION_DELETED = new Action("RESERVATION_DELETED", 6, 6);
            START_REC_PLAY = new Action("START_REC_PLAY", 7, 7);
            STOP_REC_PLAY = new Action("STOP_REC_PLAY", 8, 8);
            START_TIME_SHIFT = new Action("START_TIME_SHIFT", 9, 9);
            STOP_TIME_SHIFT = new Action("STOP_TIME_SHIFT", 10, 10);
            $VALUES = (new Action[] {
                ACTIVATE, START_LIVE, STOP_LIVE, DEACTIVATE, RESERVATION_ADDED, RESERVATION_UPDATED, RESERVATION_DELETED, START_REC_PLAY, STOP_REC_PLAY, START_TIME_SHIFT, 
                STOP_TIME_SHIFT
            });
        }

        private Action(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }


    public SendLogToEventHub()
    {
    }

    private static JSONObject getAppJSONObject(Context context)
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("version", getAppVersionName(context));
            jsonobject.put("id", context.getPackageName());
            jsonobject.put("name", getAppName(context));
            context = JVM INSTR new #214 <Class JSONObject>;
            context.JSONObject();
            context.put("name", "AndroidTV");
            context.put("version", android.os.Build.VERSION.RELEASE);
            context.put("brand", Build.BRAND);
            context.put("hw.machine", Build.MODEL);
            jsonobject.put("platform", context);
        }
        catch(JSONException jsonexception)
        {
            context = new StringBuilder();
            context.append(LOG_HEAD);
            context.append("e=");
            context.append(jsonexception);
            LoggerRTM.e(context.toString(), new Object[0]);
        }
        return jsonobject;
    }

    private static String getAppName(Context context)
    {
        if(mAppName != null)
            return mAppName;
        context = context.getApplicationInfo().loadLabel(context.getPackageManager());
        if(context != null)
            context = context.toString();
        else
            context = "";
        mAppName = context;
        return mAppName;
    }

    private static String getAppVersionName(Context context)
    {
        if(mAppVersion != null)
            return mAppVersion;
        PackageManager packagemanager = context.getPackageManager();
        try
        {
            context = packagemanager.getPackageInfo(context.getPackageName(), 0).versionName;
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(LOG_HEAD);
            stringbuilder.append("e=");
            stringbuilder.append(context);
            LoggerRTM.e(stringbuilder.toString(), new Object[0]);
            context = null;
        }
        mAppVersion = context;
        return context;
    }

    private static JSONObject getCommonJSONObject(Context context)
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("time", getCurrentTimeString());
            jsonobject.put("application", getAppJSONObject(context));
            jsonobject.put("device", getDeviceJSONObject());
            jsonobject.put("user", getUserJSONObject());
        }
        catch(JSONException jsonexception)
        {
            context = new StringBuilder();
            context.append(LOG_HEAD);
            context.append("e=");
            context.append(jsonexception);
            LoggerRTM.e(context.toString(), new Object[0]);
        }
        return jsonobject;
    }

    public static JSONObject getContentJSONObject(ProgramData programdata)
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("service", getServiceJSONObject(programdata.getBroadcastType(), programdata.getServiceId(), programdata.getOriginalNetworkId()));
            jsonobject.put("program", getProgramJSONObject(programdata));
        }
        catch(JSONException jsonexception)
        {
            programdata = new StringBuilder();
            programdata.append(LOG_HEAD);
            programdata.append("e=");
            programdata.append(jsonexception);
            LoggerRTM.e(programdata.toString(), new Object[0]);
        }
        return jsonobject;
    }

    private static String getCurrentTimeString()
    {
        Date date = new Date();
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.mmm", Locale.JAPANESE)).format(date);
    }

    private static JSONObject getDeviceJSONObject()
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            JSONObject jsonobject1 = JVM INSTR new #214 <Class JSONObject>;
            jsonobject1.JSONObject();
            jsonobject1.put("serialNumber", mSerialNumber);
            jsonobject1.put("manufacturer", "PIXELA CORPORATION");
            jsonobject1.put("modelNumber", mProductName);
            jsonobject1.put("firmwareRevision", mVersion);
            jsonobject.put("name", mProductName);
            jsonobject.put("tunerInfo", jsonobject1);
        }
        catch(JSONException jsonexception)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(LOG_HEAD);
            stringbuilder.append("e=");
            stringbuilder.append(jsonexception);
            LoggerRTM.e(stringbuilder.toString(), new Object[0]);
        }
        return jsonobject;
    }

    private static String getDurationString(long l, long l1)
    {
        l = l1 - l;
        int i = (int)(l / 3600L);
        int j = (int)((l / 60L) % 60L);
        int k = (int)(l % 60L);
        return String.format(Locale.US, "%02d:%02d:%02d", new Object[] {
            Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k)
        });
    }

    private static int getGenreMain(int i)
    {
        i >>= 4;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(LOG_HEAD);
        stringbuilder.append("genreTop4bit=");
        stringbuilder.append(i);
        Logger.v(stringbuilder.toString(), new Object[0]);
        return i;
    }

    private static int getGenreSub(int i)
    {
        i &= 0xf;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(LOG_HEAD);
        stringbuilder.append("genreBottom4bit=");
        stringbuilder.append(i);
        Logger.v(stringbuilder.toString(), new Object[0]);
        return i;
    }

    public static JSONObject getProgramJSONObject(ProgramData programdata)
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            JSONObject jsonobject1 = JVM INSTR new #214 <Class JSONObject>;
            jsonobject1.JSONObject();
            jsonobject1.put("content_nibble_level_1", getGenreMain(programdata.getGenre1()));
            jsonobject1.put("content_nibble_level_2", getGenreSub(programdata.getGenre1()));
            JSONObject jsonobject2 = JVM INSTR new #214 <Class JSONObject>;
            jsonobject2.JSONObject();
            jsonobject2.put("content_nibble_level_1", getGenreMain(programdata.getGenre2()));
            jsonobject2.put("content_nibble_level_2", getGenreSub(programdata.getGenre2()));
            JSONObject jsonobject3 = JVM INSTR new #214 <Class JSONObject>;
            jsonobject3.JSONObject();
            jsonobject3.put("content_nibble_level_1", getGenreMain(programdata.getGenre3()));
            jsonobject3.put("content_nibble_level_2", getGenreSub(programdata.getGenre3()));
            JSONArray jsonarray = JVM INSTR new #408 <Class JSONArray>;
            jsonarray.JSONArray();
            jsonarray.put(jsonobject1);
            jsonarray.put(jsonobject2);
            jsonarray.put(jsonobject3);
            jsonobject.put("event_id", programdata.getEventId());
            jsonobject.put("duration", getDurationString(programdata.getScheduledStartTime(), programdata.getScheduledEndTime()));
            jsonobject.put("content", jsonarray);
            jsonobject.put("name", programdata.getTitle());
            jsonobject.put("start_time", getTimeString(programdata.getScheduledStartTime()));
            jsonobject.put("text", programdata.getDescription());
            jsonobject.put("free_CA_mode", programdata.getCaProgram());
            jsonobject.put("parental_rating", programdata.getRating());
        }
        // Misplaced declaration of an exception variable
        catch(ProgramData programdata)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(LOG_HEAD);
            stringbuilder.append("e=");
            stringbuilder.append(programdata);
            LoggerRTM.e(stringbuilder.toString(), new Object[0]);
        }
        return jsonobject;
    }

    public static JSONObject getProgramReservationJSONObject(ReservationData reservationdata)
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            ProgramData programdata = reservationdata.getProgramData();
            jsonobject.put("id", reservationdata.getReservationId());
            jsonobject.put("service", getServiceJSONObject(programdata.getBroadcastType(), programdata.getServiceId(), programdata.getOriginalNetworkId()));
            jsonobject.put("program", getProgramJSONObject(programdata));
        }
        // Misplaced declaration of an exception variable
        catch(ReservationData reservationdata)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(LOG_HEAD);
            stringbuilder.append("e=");
            stringbuilder.append(reservationdata);
            LoggerRTM.e(stringbuilder.toString(), new Object[0]);
        }
        return jsonobject;
    }

    public static JSONObject getRecPlayContentJSONObject(RecPlayData recplaydata)
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            ProgramData programdata = recplaydata.getProgramData();
            jsonobject.put("id", recplaydata.getContentId());
            jsonobject.put("service", getServiceJSONObject(programdata.getBroadcastType(), programdata.getServiceId(), programdata.getOriginalNetworkId()));
            jsonobject.put("program", getProgramJSONObject(programdata));
        }
        // Misplaced declaration of an exception variable
        catch(RecPlayData recplaydata)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(LOG_HEAD);
            stringbuilder.append("e=");
            stringbuilder.append(recplaydata);
            LoggerRTM.e(stringbuilder.toString(), new Object[0]);
        }
        return jsonobject;
    }

    public static JSONObject getReservationDeletedJSONObject(ReservationData reservationdata)
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("id", reservationdata.getReservationId());
        }
        // Misplaced declaration of an exception variable
        catch(ReservationData reservationdata)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(LOG_HEAD);
            stringbuilder.append("e=");
            stringbuilder.append(reservationdata);
            LoggerRTM.e(stringbuilder.toString(), new Object[0]);
        }
        return jsonobject;
    }

    public static JSONObject getReservationJSONObject(ReservationData reservationdata)
    {
        if(reservationdata.getReservationType() == jp.pixela.player_service.message.ReservationData.ReservationType.PROGRAM.getValue())
            return getProgramReservationJSONObject(reservationdata);
        if(reservationdata.getReservationType() == jp.pixela.player_service.message.ReservationData.ReservationType.TIMER.getValue())
            return getTimerRecReservationJSONObject(reservationdata);
        else
            return null;
    }

    public static JSONObject getServiceJSONObject(int i, int j, int k)
    {
        JSONObject jsonobject = new JSONObject();
        if(i == 7) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch 2 5: default 44
    //                   2 65
    //                   3 57
    //                   4 73
    //                   5 73;
           goto _L3 _L4 _L5 _L2 _L2
_L3:
        Object obj;
        obj = "tr";
        break; /* Loop/switch isn't completed */
_L5:
        obj = "cs";
        break; /* Loop/switch isn't completed */
_L4:
        obj = "bs";
        break; /* Loop/switch isn't completed */
_L2:
        obj = "4k";
        try
        {
            jsonobject.put("broadcasting", obj);
            jsonobject.put("original_network_id", k);
            jsonobject.put("service_id", j);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(LOG_HEAD);
            stringbuilder.append("e=");
            stringbuilder.append(obj);
            LoggerRTM.e(stringbuilder.toString(), new Object[0]);
        }
        return jsonobject;
    }

    private static double getSpeedString(int i)
    {
        double d;
        if(i == 1)
            d = 1.0D;
        else
            d = (double)i / 2D;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(LOG_HEAD);
        stringbuilder.append("val=");
        stringbuilder.append(d);
        Logger.v(stringbuilder.toString(), new Object[0]);
        return d;
    }

    public static JSONObject getStreamJSONObject(RecPlayData recplaydata)
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("time", getTimeString(recplaydata.getStreamTime()));
            jsonobject.put("speed", getSpeedString(recplaydata.getStreamSpeed()));
        }
        // Misplaced declaration of an exception variable
        catch(RecPlayData recplaydata)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(LOG_HEAD);
            stringbuilder.append("e=");
            stringbuilder.append(recplaydata);
            LoggerRTM.e(stringbuilder.toString(), new Object[0]);
        }
        return jsonobject;
    }

    private static String getTimeString(long l)
    {
        Date date = new Date();
        date.setTime(l * 1000L);
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE)).format(date);
    }

    public static JSONObject getTimerRecReservationJSONObject(ReservationData reservationdata)
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            TimerRecordData timerrecorddata = reservationdata.getTimerRecordData();
            jsonobject.put("id", reservationdata.getReservationId());
            jsonobject.put("service", getServiceJSONObject(timerrecorddata.getBroadcastType(), timerrecorddata.getServiceId(), timerrecorddata.getOriginalNetworkId()));
            jsonobject.put("start_time", getTimeString(timerrecorddata.getRecStartTime()));
            jsonobject.put("duration", getDurationString(timerrecorddata.getRecStartTime(), timerrecorddata.getRecEndTime()));
        }
        catch(JSONException jsonexception)
        {
            reservationdata = new StringBuilder();
            reservationdata.append(LOG_HEAD);
            reservationdata.append("e=");
            reservationdata.append(jsonexception);
            LoggerRTM.e(reservationdata.toString(), new Object[0]);
        }
        return jsonobject;
    }

    public static JSONObject getUserJSONObject()
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("id", mSerialNumber);
            jsonobject.put("area_code", Integer.toString(mAreaCode));
        }
        catch(JSONException jsonexception)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(LOG_HEAD);
            stringbuilder.append("e=");
            stringbuilder.append(jsonexception);
            LoggerRTM.e(stringbuilder.toString(), new Object[0]);
        }
        return jsonobject;
    }

    public static boolean saveCommonParam(DeviceLogData devicelogdata, int i)
    {
        if(devicelogdata == null)
        {
            PxLog.w(TAG, "devInfo is null.");
            return false;
        } else
        {
            mVersion = devicelogdata.getVersion();
            mSerialNumber = devicelogdata.getSerialNumber();
            mProductName = devicelogdata.getModelName();
            mAreaCode = i;
            return true;
        }
    }

    private static void send(Context context, String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(LOG_HEAD);
        stringbuilder.append("in. jsonString=");
        stringbuilder.append(s);
        Logger.v(stringbuilder.toString(), new Object[0]);
        if(!NetworkUtility.isNetworkAvailable(context))
        {
            context = new StringBuilder();
            context.append(LOG_HEAD);
            context.append("out. !isNetworkAvailable");
            Logger.v(context.toString(), new Object[0]);
            return;
        } else
        {
            (new SendLogToEventHubTask()).execute(new String[] {
                s
            });
            context = new StringBuilder();
            context.append(LOG_HEAD);
            context.append("out");
            Logger.v(context.toString(), new Object[0]);
            return;
        }
    }

    public static void sendActivate(Context context, DeviceLogData devicelogdata, int i)
    {
        if(!saveCommonParam(devicelogdata, i))
            return;
        JSONObject jsonobject = getCommonJSONObject(context);
        try
        {
            jsonobject.put("type", "activate");
        }
        // Misplaced declaration of an exception variable
        catch(DeviceLogData devicelogdata)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(LOG_HEAD);
            stringbuilder.append("e=");
            stringbuilder.append(devicelogdata);
            LoggerRTM.e(stringbuilder.toString(), new Object[0]);
        }
        send(context, jsonobject.toString());
    }

    public static void sendDeactivate(Context context, DeviceLogData devicelogdata, int i)
    {
        if(!saveCommonParam(devicelogdata, i))
            return;
        JSONObject jsonobject = getCommonJSONObject(context);
        try
        {
            jsonobject.put("type", "deactivate");
        }
        // Misplaced declaration of an exception variable
        catch(DeviceLogData devicelogdata)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(LOG_HEAD);
            stringbuilder.append("e=");
            stringbuilder.append(devicelogdata);
            LoggerRTM.e(stringbuilder.toString(), new Object[0]);
        }
        send(context, jsonobject.toString());
    }

    public static void sendFilePlay(Context context, DeviceLogData devicelogdata, RecPlayData recplaydata, int i)
    {
        mIsAlreadySendPlayStop = true;
        mIsAlreadySendFilePlayStop = false;
        if(recplaydata == null)
        {
            PxLog.w(TAG, "recPlay is null.");
            return;
        }
        if(!saveCommonParam(devicelogdata, i))
            return;
        devicelogdata = getCommonJSONObject(context);
        try
        {
            devicelogdata.put("type", "file_play");
            devicelogdata.put("stream", getStreamJSONObject(recplaydata));
            devicelogdata.put("content", getRecPlayContentJSONObject(recplaydata));
        }
        catch(JSONException jsonexception)
        {
            recplaydata = new StringBuilder();
            recplaydata.append(LOG_HEAD);
            recplaydata.append("e=");
            recplaydata.append(jsonexception);
            LoggerRTM.e(recplaydata.toString(), new Object[0]);
        }
        send(context, devicelogdata.toString());
    }

    public static void sendFileStop(Context context, DeviceLogData devicelogdata, int i)
    {
        if(mIsAlreadySendFilePlayStop)
            return;
        mIsAlreadySendFilePlayStop = true;
        if(!saveCommonParam(devicelogdata, i))
            return;
        devicelogdata = getCommonJSONObject(context);
        try
        {
            devicelogdata.put("type", "file_stop");
        }
        catch(JSONException jsonexception)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(LOG_HEAD);
            stringbuilder.append("e=");
            stringbuilder.append(jsonexception);
            LoggerRTM.e(stringbuilder.toString(), new Object[0]);
        }
        send(context, devicelogdata.toString());
    }

    public static void sendLivePlay(Context context, DeviceLogData devicelogdata, ProgramData programdata, int i)
    {
        mIsAlreadySendPlayStop = false;
        mIsAlreadySendFilePlayStop = true;
        if(programdata == null)
        {
            PxLog.w(TAG, "programData is null.");
            return;
        }
        if(!saveCommonParam(devicelogdata, i))
            return;
        if(programdata.getEventId() == mCurrentEventId && programdata.getScheduledStartTime() == mCurrentStartTime && programdata.getScheduledEndTime() == mCurrentEndTime && programdata.getServiceId() == mCurrentServiceId && programdata.getBroadcastType() == mCurrentBroadcastType)
        {
            devicelogdata = TAG;
            context = new StringBuilder();
            context.append("same program. event id=");
            context.append(mCurrentEventId);
            context.append(", service id=");
            context.append(mCurrentServiceId);
            PxLog.d(devicelogdata, context.toString());
            return;
        }
        mCurrentEventId = programdata.getEventId();
        mCurrentStartTime = programdata.getScheduledStartTime();
        mCurrentEndTime = programdata.getScheduledEndTime();
        mCurrentServiceId = programdata.getServiceId();
        mCurrentBroadcastType = programdata.getBroadcastType();
        String s = TAG;
        devicelogdata = new StringBuilder();
        devicelogdata.append("new program. event id=");
        devicelogdata.append(mCurrentEventId);
        devicelogdata.append(", service id=");
        devicelogdata.append(mCurrentServiceId);
        PxLog.d(s, devicelogdata.toString());
        devicelogdata = getCommonJSONObject(context);
        try
        {
            devicelogdata.put("type", "live_play");
            devicelogdata.put("content", getContentJSONObject(programdata));
        }
        // Misplaced declaration of an exception variable
        catch(ProgramData programdata)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(LOG_HEAD);
            stringbuilder.append("e=");
            stringbuilder.append(programdata);
            LoggerRTM.e(stringbuilder.toString(), new Object[0]);
        }
        send(context, devicelogdata.toString());
    }

    public static void sendLiveStop(Context context, DeviceLogData devicelogdata, int i)
    {
        if(mIsAlreadySendPlayStop)
            return;
        mCurrentEventId = 0;
        mCurrentStartTime = -1L;
        mCurrentEndTime = -1L;
        mCurrentServiceId = -1;
        mCurrentBroadcastType = 0;
        mIsAlreadySendPlayStop = true;
        if(!saveCommonParam(devicelogdata, i))
            return;
        devicelogdata = getCommonJSONObject(context);
        try
        {
            devicelogdata.put("type", "live_stop");
        }
        catch(JSONException jsonexception)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(LOG_HEAD);
            stringbuilder.append("e=");
            stringbuilder.append(jsonexception);
            LoggerRTM.e(stringbuilder.toString(), new Object[0]);
        }
        send(context, devicelogdata.toString());
    }

    public static void sendReservationAdded(Context context, DeviceLogData devicelogdata, ReservationData reservationdata, int i)
    {
        if(reservationdata == null)
        {
            PxLog.w(TAG, "programData is null.");
            return;
        }
        if(!saveCommonParam(devicelogdata, i))
            return;
        devicelogdata = getCommonJSONObject(context);
        try
        {
            devicelogdata.put("type", "reservation_added");
            devicelogdata.put("reservation", getReservationJSONObject(reservationdata));
        }
        // Misplaced declaration of an exception variable
        catch(ReservationData reservationdata)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(LOG_HEAD);
            stringbuilder.append("e=");
            stringbuilder.append(reservationdata);
            LoggerRTM.e(stringbuilder.toString(), new Object[0]);
        }
        send(context, devicelogdata.toString());
    }

    public static void sendReservationDeleted(Context context, DeviceLogData devicelogdata, ReservationData reservationdata, int i)
    {
        if(reservationdata == null)
        {
            PxLog.w(TAG, "programData is null.");
            return;
        }
        if(!saveCommonParam(devicelogdata, i))
            return;
        devicelogdata = getCommonJSONObject(context);
        try
        {
            devicelogdata.put("type", "reservation_deleted");
            devicelogdata.put("reservation", getReservationDeletedJSONObject(reservationdata));
        }
        // Misplaced declaration of an exception variable
        catch(ReservationData reservationdata)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(LOG_HEAD);
            stringbuilder.append("e=");
            stringbuilder.append(reservationdata);
            LoggerRTM.e(stringbuilder.toString(), new Object[0]);
        }
        send(context, devicelogdata.toString());
    }

    public static void sendReservationUpdated(Context context, DeviceLogData devicelogdata, ReservationData reservationdata, int i)
    {
        if(reservationdata == null)
        {
            PxLog.w(TAG, "programData is null.");
            return;
        }
        if(!saveCommonParam(devicelogdata, i))
            return;
        devicelogdata = getCommonJSONObject(context);
        try
        {
            devicelogdata.put("type", "reservation_updated");
            devicelogdata.put("reservation", getReservationJSONObject(reservationdata));
        }
        catch(JSONException jsonexception)
        {
            reservationdata = new StringBuilder();
            reservationdata.append(LOG_HEAD);
            reservationdata.append("e=");
            reservationdata.append(jsonexception);
            LoggerRTM.e(reservationdata.toString(), new Object[0]);
        }
        send(context, devicelogdata.toString());
    }

    public static void sendTimeShiftPlay(Context context, DeviceLogData devicelogdata, RecPlayData recplaydata, int i)
    {
        mIsAlreadySendPlayStop = true;
        mIsAlreadySendFilePlayStop = false;
        if(recplaydata == null)
        {
            PxLog.w(TAG, "recPlay is null.");
            return;
        }
        if(!saveCommonParam(devicelogdata, i))
            return;
        devicelogdata = getCommonJSONObject(context);
        try
        {
            devicelogdata.put("type", "timeshift_play");
            devicelogdata.put("stream", getStreamJSONObject(recplaydata));
            devicelogdata.put("content", getRecPlayContentJSONObject(recplaydata));
        }
        catch(JSONException jsonexception)
        {
            recplaydata = new StringBuilder();
            recplaydata.append(LOG_HEAD);
            recplaydata.append("e=");
            recplaydata.append(jsonexception);
            LoggerRTM.e(recplaydata.toString(), new Object[0]);
        }
        send(context, devicelogdata.toString());
    }

    public static void sendTimeShiftStop(Context context, DeviceLogData devicelogdata, int i)
    {
        if(mIsAlreadySendFilePlayStop)
            return;
        mIsAlreadySendFilePlayStop = true;
        if(!saveCommonParam(devicelogdata, i))
            return;
        JSONObject jsonobject = getCommonJSONObject(context);
        try
        {
            jsonobject.put("type", "timeshift_stop");
        }
        catch(JSONException jsonexception)
        {
            devicelogdata = new StringBuilder();
            devicelogdata.append(LOG_HEAD);
            devicelogdata.append("e=");
            devicelogdata.append(jsonexception);
            LoggerRTM.e(devicelogdata.toString(), new Object[0]);
        }
        send(context, jsonobject.toString());
    }

    private static final String KEY_APPLICATION = "application";
    private static final String KEY_APP_ID = "id";
    private static final String KEY_APP_NAME = "name";
    private static final String KEY_APP_PLATFORM = "platform";
    private static final String KEY_APP_VERSION = "version";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_CONTENT_NIBBLE_LEVEL_1 = "content_nibble_level_1";
    private static final String KEY_CONTENT_NIBBLE_LEVEL_2 = "content_nibble_level_2";
    private static final String KEY_CONTENT_PROGRAM = "program";
    private static final String KEY_CONTENT_SERVICE = "service";
    private static final String KEY_DEVICE = "device";
    private static final String KEY_DEVICE_NAME = "name";
    private static final String KEY_DEVICE_TUNER_INFO = "tunerInfo";
    private static final String KEY_PLATFORM_BRAND = "brand";
    private static final String KEY_PLATFORM_HW_MACHINE = "hw.machine";
    private static final String KEY_PLATFORM_NAME = "name";
    private static final String KEY_PLATFORM_VERSION = "version";
    private static final String KEY_PROGRAM_CONTENT = "content";
    private static final String KEY_PROGRAM_DURATION = "duration";
    private static final String KEY_PROGRAM_EVENT_ID = "event_id";
    private static final String KEY_PROGRAM_FREE_CA_MODE = "free_CA_mode";
    private static final String KEY_PROGRAM_NAME = "name";
    private static final String KEY_PROGRAM_PARENTAL_RATING = "parental_rating";
    private static final String KEY_PROGRAM_START_TIME = "start_time";
    private static final String KEY_PROGRAM_TEXT = "text";
    private static final String KEY_REC_PLAY_ID = "id";
    private static final String KEY_RESERVATION = "reservation";
    private static final String KEY_RESERVATION_DURATION_TIME = "duration";
    private static final String KEY_RESERVATION_ID = "id";
    private static final String KEY_RESERVATION_START_TIME = "start_time";
    private static final String KEY_SERVICE_BROADCASTING = "broadcasting";
    private static final String KEY_SERVICE_ORIGINAL_NETWORK_ID = "original_network_id";
    private static final String KEY_SERVICE_SERVICE_ID = "service_id";
    private static final String KEY_STREAM = "stream";
    private static final String KEY_STREAM_SPEED = "speed";
    private static final String KEY_STREAM_TIME = "time";
    private static final String KEY_TIME = "time";
    private static final String KEY_TUNER_FIRMWARE_REVISION = "firmwareRevision";
    private static final String KEY_TUNER_MANUFACTURER = "manufacturer";
    private static final String KEY_TUNER_MODEL_NUMBER = "modelNumber";
    private static final String KEY_TUNER_SERIAL_NUMBER = "serialNumber";
    private static final String KEY_TYPE = "type";
    private static final String KEY_USER = "user";
    private static final String KEY_USER_AREA_CODE = "area_code";
    private static final String KEY_USER_ID = "id";
    private static final String LOG_HEAD;
    private static final String TAG = "SendLogToEventHub";
    private static final String TUNER_MANUFACTURER = "PIXELA CORPORATION";
    private static final String TYPE_ACTIVATE = "activate";
    private static final String TYPE_DEACTIVATE = "deactivate";
    private static final String TYPE_FILE_PLAY = "file_play";
    private static final String TYPE_FILE_STOP = "file_stop";
    private static final String TYPE_LIVE_PLAY = "live_play";
    private static final String TYPE_LIVE_STOP = "live_stop";
    private static final String TYPE_RESERVATION_ADDED = "reservation_added";
    private static final String TYPE_RESERVATION_DELETED = "reservation_deleted";
    private static final String TYPE_RESERVATION_UPDATED = "reservation_updated";
    private static final String TYPE_TIMESHIFT_PLAY = "timeshift_play";
    private static final String TYPE_TIMESHIFT_STOP = "timeshift_stop";
    private static String mAppName;
    private static String mAppVersion;
    private static int mAreaCode = 0;
    private static int mCurrentBroadcastType = 0;
    private static long mCurrentEndTime = -1L;
    private static int mCurrentEventId = 0;
    private static int mCurrentServiceId = -1;
    private static long mCurrentStartTime = -1L;
    private static boolean mIsAlreadySendFilePlayStop = true;
    private static boolean mIsAlreadySendPlayStop = true;
    private static String mProductName;
    private static String mSerialNumber;
    private static String mVersion;

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/player_service/SendLogToEventHub.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }
}
