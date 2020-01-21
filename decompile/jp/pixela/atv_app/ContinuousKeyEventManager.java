// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.atv_app;

import android.app.Activity;
import android.view.KeyEvent;
import java.io.*;
import jp.pixela.atv_app.common.PxLog;

class ContinuousKeyEventManager
{
    private static final class KeyControlState extends Enum
    {

        public static KeyControlState valueOf(String s)
        {
            return (KeyControlState)Enum.valueOf(jp/pixela/atv_app/ContinuousKeyEventManager$KeyControlState, s);
        }

        public static KeyControlState[] values()
        {
            return (KeyControlState[])$VALUES.clone();
        }

        private static final KeyControlState $VALUES[];
        public static final KeyControlState DROPPING;
        public static final KeyControlState NORMAL;
        public static final KeyControlState REPEATING;

        static 
        {
            NORMAL = new KeyControlState("NORMAL", 0);
            DROPPING = new KeyControlState("DROPPING", 1);
            REPEATING = new KeyControlState("REPEATING", 2);
            $VALUES = (new KeyControlState[] {
                NORMAL, DROPPING, REPEATING
            });
        }

        private KeyControlState(String s, int i)
        {
            super(s, i);
        }
    }


    ContinuousKeyEventManager()
    {
        lastUpKeyEventMillis = 0L;
        longPressFirstDownMillis = 0L;
        lastUpKeyCode = 0;
        keyControlState = KeyControlState.NORMAL;
        intervalDropKeyMillis = 200;
        intervalStartRepeatKeyMillis = 500;
        intervalEndRepeatKeyMillis = 300;
    }

    private void getDebugInfo(Activity activity)
    {
        Object obj1;
        Object obj2;
        Object obj;
label0:
        {
            obj = null;
            obj1 = null;
            try
            {
                obj2 = JVM INSTR new #63  <Class File>;
                StringBuilder stringbuilder3 = JVM INSTR new #65  <Class StringBuilder>;
                stringbuilder3.StringBuilder();
                stringbuilder3.append(activity.getFilesDir().getAbsolutePath());
                stringbuilder3.append("/");
                stringbuilder3.append("debug_key_settings.txt");
                ((File) (obj2)).File(stringbuilder3.toString());
                if(((File) (obj2)).exists())
                {
                    obj2 = JVM INSTR new #94  <Class BufferedReader>;
                    InputStreamReader inputstreamreader = JVM INSTR new #96  <Class InputStreamReader>;
                    inputstreamreader.InputStreamReader(activity.openFileInput("debug_key_settings.txt"));
                    ((BufferedReader) (obj2)).BufferedReader(inputstreamreader);
                    break label0;
                }
            }
            // Misplaced declaration of an exception variable
            catch(Activity activity)
            {
                obj2 = TAG;
                StringBuilder stringbuilder4 = new StringBuilder();
                stringbuilder4.append("e=");
                stringbuilder4.append(activity);
                PxLog.e(((String) (obj2)), stringbuilder4.toString());
            }
            obj2 = null;
        }
        activity = obj;
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_204;
        activity = ((BufferedReader) (obj2)).readLine();
        ((BufferedReader) (obj2)).close();
        break MISSING_BLOCK_LABEL_204;
        IOException ioexception;
        ioexception;
        break MISSING_BLOCK_LABEL_170;
        ioexception;
        activity = obj1;
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("e=");
        stringbuilder.append(ioexception);
        PxLog.e(s, stringbuilder.toString());
        if(activity != null)
        {
            String s1 = TAG;
            StringBuilder stringbuilder1 = new StringBuilder();
            stringbuilder1.append("debug_key_settings.txt => ");
            stringbuilder1.append(activity);
            PxLog.i(s1, stringbuilder1.toString());
            activity = activity.split(" ", 0);
            if(activity.length == 3)
            {
                intervalDropKeyMillis = tryParseInt(activity[0], intervalDropKeyMillis);
                intervalStartRepeatKeyMillis = tryParseInt(activity[1], intervalStartRepeatKeyMillis);
                intervalEndRepeatKeyMillis = tryParseInt(activity[2], intervalEndRepeatKeyMillis);
            }
        }
        activity = TAG;
        StringBuilder stringbuilder2 = new StringBuilder();
        stringbuilder2.append("KEY Handling option => ( ");
        stringbuilder2.append(intervalDropKeyMillis);
        stringbuilder2.append(", ");
        stringbuilder2.append(intervalStartRepeatKeyMillis);
        stringbuilder2.append(", ");
        stringbuilder2.append(intervalEndRepeatKeyMillis);
        stringbuilder2.append(" )");
        PxLog.i(activity, stringbuilder2.toString());
        return;
    }

    private int tryParseInt(String s, int i)
    {
        if(s == null)
            break MISSING_BLOCK_LABEL_52;
        int j = Integer.parseInt(s);
        i = j;
        break MISSING_BLOCK_LABEL_52;
        NumberFormatException numberformatexception;
        numberformatexception;
        String s1 = TAG;
        s = new StringBuilder();
        s.append("e=");
        s.append(numberformatexception);
        PxLog.v(s1, s.toString());
        return i;
    }

    boolean dispatchKeyEventForDrop(KeyEvent keyevent)
    {
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("dispatchKeyEventForDrop event=");
        stringbuilder.append(keyevent);
        PxLog.v(s, stringbuilder.toString());
        return false;
    }

    void initialize(Activity activity)
    {
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("initialize activity=");
        stringbuilder.append(activity);
        PxLog.v(s, stringbuilder.toString());
    }

    private static final String KEY_SETTINGS_FILE_NAME_FOR_DEBUG = "debug_key_settings.txt";
    private static final String TAG = "ContinuousKeyEventManager";
    private static final int sIntervalDropKeyMillis = 200;
    private static final int sIntervalEndRepeatKeyMillis = 300;
    private static final int sIntervalStartRepeatKeyMillis = 500;
    private int intervalDropKeyMillis;
    private int intervalEndRepeatKeyMillis;
    private int intervalStartRepeatKeyMillis;
    private KeyControlState keyControlState;
    private int lastUpKeyCode;
    private long lastUpKeyEventMillis;
    private long longPressFirstDownMillis;


    // Unreferenced inner class jp/pixela/atv_app/ContinuousKeyEventManager$1

/* anonymous class */
    static class _cls1
    {

        static final int $SwitchMap$jp$pixela$atv_app$ContinuousKeyEventManager$KeyControlState[];

        static 
        {
            $SwitchMap$jp$pixela$atv_app$ContinuousKeyEventManager$KeyControlState = new int[KeyControlState.values().length];
            try
            {
                $SwitchMap$jp$pixela$atv_app$ContinuousKeyEventManager$KeyControlState[KeyControlState.NORMAL.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            try
            {
                $SwitchMap$jp$pixela$atv_app$ContinuousKeyEventManager$KeyControlState[KeyControlState.DROPPING.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            $SwitchMap$jp$pixela$atv_app$ContinuousKeyEventManager$KeyControlState[KeyControlState.REPEATING.ordinal()] = 3;
_L2:
            return;
            NoSuchFieldError nosuchfielderror2;
            nosuchfielderror2;
            if(true) goto _L2; else goto _L1
_L1:
        }
    }

}
