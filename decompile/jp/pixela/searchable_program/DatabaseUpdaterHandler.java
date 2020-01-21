// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.searchable_program;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;
import java.util.Locale;
import jp.pixela.common.*;

// Referenced classes of package jp.pixela.searchable_program:
//            DatabaseUpdater

public class DatabaseUpdaterHandler
{

    public DatabaseUpdaterHandler()
    {
    }

    private static void cancelAlarm(Context context, PendingIntent pendingintent)
    {
        if(pendingintent == null)
        {
            return;
        } else
        {
            ((AlarmManager)context.getSystemService("alarm")).cancel(pendingintent);
            pendingintent.cancel();
            return;
        }
    }

    private static PendingIntent getPendingIntent(Context context, String s)
    {
        return PendingIntent.getBroadcast(context, 0, new Intent(s), 0x20000000);
    }

    public static void processIntent(Context context, Intent intent)
    {
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("processIntent in. intent=");
        stringbuilder.append(IntentHelper.dump(intent));
        PxLog.v(s, stringbuilder.toString());
        intent = intent.getAction();
        long l;
        if("jp.pixela.stationtv.xit.action.UPDATE_RECORD_DB".equals(intent))
        {
            if(!ApplicationUtility.isAppRunningForeground(context))
            {
                context.startService(new Intent(context, jp/pixela/searchable_program/DatabaseUpdater));
                PxLog.v(TAG, "processIntent startService");
            } else
            if(DatabaseUpdater.needsUpdateEpg())
                context.sendBroadcast(new Intent("ACTION_DB_UPDATER_UPDATE_EPG"));
            l = 0x1b7740L;
        } else
        if(!"android.intent.action.BOOT_COMPLETED".equals(intent) && !"android.intent.action.TIME_SET".equals(intent))
            l = 0L;
        else
            l = 0x493e0L;
        if(l != 0L)
            setNextAlarm(context, l);
        PxLog.v(TAG, "processIntent out");
    }

    private static void setAlarm(Context context, Calendar calendar, String s)
    {
        AlarmManager alarmmanager = (AlarmManager)context.getSystemService("alarm");
        long l = calendar.getTimeInMillis();
        calendar = new Intent(s);
        calendar.putExtra("alarmTime", l);
        calendar.addFlags(0x1000000);
        alarmmanager.setExact(0, l, PendingIntent.getBroadcast(context, 0, calendar, 0x10000000));
    }

    public static void setFirstAlarm(Context context)
    {
        setNextAlarm(context, 0x493e0L);
    }

    private static void setNextAlarm(Context context, long l)
    {
        cancelAlarm(context, getPendingIntent(context, "jp.pixela.stationtv.xit.action.UPDATE_RECORD_DB"));
        long l1 = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance(Locale.JAPAN);
        calendar.setTimeInMillis(l1 + l);
        setAlarm(context, calendar, "jp.pixela.stationtv.xit.action.UPDATE_RECORD_DB");
    }

    public static final String ACTION_UPDATE_RECORD_DB = "jp.pixela.stationtv.xit.action.UPDATE_RECORD_DB";
    private static final int ONE_MINUTE_MILLISEC = 60000;
    private static final int SHORT_UPDATE_INTERVAL_MILLISEC = 0x493e0;
    private static final String TAG = "DatabaseUpdaterHandler";
    private static final int UPDATE_INTERVAL_MILLISEC = 0x1b7740;

}
