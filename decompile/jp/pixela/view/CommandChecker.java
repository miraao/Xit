// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.view;

import android.view.KeyEvent;
import java.util.*;
import jp.pixela.common.PxLog;

class CommandChecker
{
    static final class COMMAND extends Enum
    {

        public static COMMAND valueOf(String s)
        {
            return (COMMAND)Enum.valueOf(jp/pixela/view/CommandChecker$COMMAND, s);
        }

        public static COMMAND[] values()
        {
            return (COMMAND[])$VALUES.clone();
        }

        private static final COMMAND $VALUES[];
        public static final COMMAND ANDROID_SETTING;
        public static final COMMAND CHECKER_MODE;
        public static final COMMAND FINISH;
        public static final COMMAND SPECIAL_KEY_F10_RECEIVED;

        static 
        {
            FINISH = new COMMAND("FINISH", 0);
            ANDROID_SETTING = new COMMAND("ANDROID_SETTING", 1);
            SPECIAL_KEY_F10_RECEIVED = new COMMAND("SPECIAL_KEY_F10_RECEIVED", 2);
            CHECKER_MODE = new COMMAND("CHECKER_MODE", 3);
            $VALUES = (new COMMAND[] {
                FINISH, ANDROID_SETTING, SPECIAL_KEY_F10_RECEIVED, CHECKER_MODE
            });
        }

        private COMMAND(String s, int i)
        {
            super(s, i);
        }
    }


    CommandChecker()
    {
    }

    private boolean isMatch(COMMAND command, KeyEvent keyevent)
    {
        int i;
        int j;
        if(keyevent == null)
        {
            PxLog.v(TAG, "isMatch() event == null");
            return false;
        }
        if(keyevent.getAction() != 1)
            return false;
        keyevent = (Integer[][])sCommandKeyCodeArraysMap.get(command);
        i = keyevent.length;
        j = 0;
_L7:
        Object obj;
        if(j >= i)
            break MISSING_BLOCK_LABEL_176;
        obj = keyevent[j];
        if(mKeyCodeList.size() >= obj.length) goto _L2; else goto _L1
_L2:
        int k = 0;
_L5:
        int l;
        int i1;
        if(k >= obj.length)
            break; /* Loop/switch isn't completed */
        l = mKeyCodeList.size();
        i1 = obj.length;
        if(((Integer)mKeyCodeList.get((l - i1) + k)).equals(obj[k])) goto _L3; else goto _L1
_L1:
        j++;
        continue; /* Loop/switch isn't completed */
_L3:
        k++;
        if(true) goto _L5; else goto _L4
_L4:
        keyevent = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("isMatch() matched. command=");
        stringbuilder.append(command);
        PxLog.v(keyevent, stringbuilder.toString());
        return true;
        return false;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private void updateKeyCodeList(KeyEvent keyevent)
    {
        if(keyevent == null)
        {
            PxLog.v(TAG, "updateKeyCodeList() event == null");
            return;
        }
        if(keyevent.getAction() != 1)
            return;
        mKeyCodeList.add(Integer.valueOf(keyevent.getKeyCode()));
        if(10 < mKeyCodeList.size())
            mKeyCodeList.remove(0);
    }

    void addRunnable(COMMAND command, Runnable runnable)
    {
        String s = TAG;
        runnable = new StringBuilder();
        runnable.append("addRunnable command=");
        runnable.append(command);
        PxLog.v(s, runnable.toString());
    }

    boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("dispatchKeyEvent event=");
        stringbuilder.append(keyevent);
        PxLog.v(s, stringbuilder.toString());
        return false;
    }

    private static final int COMMAND_MAX_LENGTH = 10;
    private static final String TAG = "CommandChecker";
    private static final Integer sAndroidSettingCommandKeyCodeArrays[][] = {
        {
            Integer.valueOf(206), Integer.valueOf(206), Integer.valueOf(206), Integer.valueOf(206), Integer.valueOf(206), Integer.valueOf(206), Integer.valueOf(206), Integer.valueOf(206), Integer.valueOf(206), Integer.valueOf(206)
        }
    };
    private static final Integer sCheckerModeCommandKeyCodeArrays[][] = {
        {
            Integer.valueOf(206), Integer.valueOf(171), Integer.valueOf(89)
        }
    };
    private static final Map sCommandKeyCodeArraysMap;
    private static final Integer sFinishCommandKeyCodeArrays[][] = {
        {
            Integer.valueOf(86), Integer.valueOf(86), Integer.valueOf(86), Integer.valueOf(86), Integer.valueOf(86), Integer.valueOf(86), Integer.valueOf(86), Integer.valueOf(86), Integer.valueOf(86), Integer.valueOf(86)
        }, {
            Integer.valueOf(47), Integer.valueOf(47), Integer.valueOf(47), Integer.valueOf(47), Integer.valueOf(47), Integer.valueOf(47), Integer.valueOf(47), Integer.valueOf(47), Integer.valueOf(47), Integer.valueOf(47)
        }
    };
    private static final Integer sSpecialKeyF10ReceivedCommandKeyCodeArrays[][] = {
        {
            Integer.valueOf(140)
        }
    };
    private final ArrayList mKeyCodeList = new ArrayList();
    private final Map mRunnableMap = new HashMap();

    static 
    {
        sCommandKeyCodeArraysMap = new HashMap();
        sCommandKeyCodeArraysMap.put(COMMAND.FINISH, sFinishCommandKeyCodeArrays);
        sCommandKeyCodeArraysMap.put(COMMAND.ANDROID_SETTING, sAndroidSettingCommandKeyCodeArrays);
        sCommandKeyCodeArraysMap.put(COMMAND.SPECIAL_KEY_F10_RECEIVED, sSpecialKeyF10ReceivedCommandKeyCodeArrays);
        sCommandKeyCodeArraysMap.put(COMMAND.CHECKER_MODE, sCheckerModeCommandKeyCodeArrays);
    }
}
