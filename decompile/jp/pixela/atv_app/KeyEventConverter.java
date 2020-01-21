// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.atv_app;

import android.view.KeyEvent;
import jp.pixela.atv_app.common.PxLog;

class KeyEventConverter
{

    KeyEventConverter()
    {
    }

    private boolean isSuppressLongPress(KeyEvent keyevent)
    {
        boolean flag = false;
        if(keyevent == null)
        {
            PxLog.v(TAG, "isSuppressLongPress() event == null");
            return false;
        }
        if(keyevent.getRepeatCount() <= 0)
        {
            PxLog.v(TAG, "isSuppressLongPress() event.getRepeatCount() <= 0");
            return false;
        }
        int i = keyevent.getKeyCode();
        if(i == 4 || i == 23 || i == 135 || i == 138 || i == 170)
            flag = true;
        keyevent = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("isSuppressLongPress() ret=");
        stringbuilder.append(flag);
        PxLog.v(keyevent, stringbuilder.toString());
        return flag;
    }

    KeyEvent convert(KeyEvent keyevent)
    {
        KeyEvent keyevent1;
label0:
        {
            if(isSuppressLongPress(keyevent))
                return null;
            if(keyevent == null)
            {
                PxLog.v(TAG, "convert() event == null");
                return null;
            }
            int i = keyevent.getKeyCode();
            switch(i)
            {
            default:
                switch(i)
                {
                default:
                    switch(i)
                    {
                    default:
                        switch(i)
                        {
                        case 84: // 'T'
                        case 219: 
                        default:
                            keyevent1 = null;
                            break label0;

                        case 170: 
                            keyevent1 = new KeyEvent(keyevent.getDownTime(), keyevent.getEventTime(), keyevent.getAction(), 32, keyevent.getRepeatCount(), 0);
                            break label0;

                        case 135: 
                            keyevent1 = new KeyEvent(keyevent.getDownTime(), keyevent.getEventTime(), keyevent.getAction(), 142, keyevent.getRepeatCount(), 0);
                            break label0;

                        case 4: // '\004'
                            keyevent1 = new KeyEvent(keyevent.getDownTime(), keyevent.getEventTime(), keyevent.getAction(), 67, keyevent.getRepeatCount(), 0);
                            break label0;

                        case 67: // 'C'
                        case 111: // 'o'
                        case 164: 
                            break;
                        }
                        break;

                    case 167: 
                        keyevent1 = new KeyEvent(keyevent.getDownTime(), keyevent.getEventTime(), keyevent.getAction(), 156, keyevent.getRepeatCount(), 0);
                        break label0;

                    case 166: 
                        keyevent1 = new KeyEvent(keyevent.getDownTime(), keyevent.getEventTime(), keyevent.getAction(), 157, keyevent.getRepeatCount(), 0);
                        break label0;
                    }
                    break;

                case 142: 
                    keyevent1 = new KeyEvent(keyevent.getDownTime(), keyevent.getEventTime(), keyevent.getAction(), 136, keyevent.getRepeatCount(), 0);
                    break label0;

                case 141: 
                    keyevent1 = new KeyEvent(keyevent.getDownTime(), keyevent.getEventTime(), keyevent.getAction(), 137, keyevent.getRepeatCount(), 0);
                    break label0;

                case 140: 
                    keyevent1 = new KeyEvent(keyevent.getDownTime(), keyevent.getEventTime(), keyevent.getAction(), 138, keyevent.getRepeatCount(), 0);
                    break label0;

                case 139: 
                    keyevent1 = new KeyEvent(keyevent.getDownTime(), keyevent.getEventTime(), keyevent.getAction(), 135, keyevent.getRepeatCount(), 0);
                    break label0;

                case 138: 
                    keyevent1 = new KeyEvent(keyevent.getDownTime(), keyevent.getEventTime(), keyevent.getAction(), 40, keyevent.getRepeatCount(), 0);
                    break label0;
                }
                // fall through

            case 19: // '\023'
            case 20: // '\024'
            case 21: // '\025'
            case 22: // '\026'
            case 23: // '\027'
            case 24: // '\030'
            case 25: // '\031'
                keyevent1 = keyevent;
                break;
            }
        }
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("convert() event=");
        stringbuilder.append(keyevent);
        stringbuilder.append(", newEvent=");
        stringbuilder.append(keyevent1);
        PxLog.v(s, stringbuilder.toString());
        return keyevent1;
    }

    boolean isDispatchToApp(KeyEvent keyevent)
    {
        if(keyevent == null)
        {
            PxLog.v(TAG, "isDispatchToApp() event == null");
            return false;
        }
        PxLog.v(TAG, "isDispatchToApp() IS_DISPATCH_KEYCODE_SEARCH_TO_APP=true");
        int i = keyevent.getKeyCode();
        return i == 84 || i == 219 ? true : true;
    }

    boolean isDispatchToWebView(KeyEvent keyevent)
    {
        boolean flag;
        int i;
        flag = false;
        if(keyevent == null)
        {
            PxLog.v(TAG, "isDispatchToWebView() event == null");
            return false;
        }
        i = keyevent.getKeyCode();
        i;
        JVM INSTR tableswitch 19 25: default 64
    //                   19 203
    //                   20 203
    //                   21 203
    //                   22 203
    //                   23 203
    //                   24 203
    //                   25 203;
           goto _L1 _L2 _L2 _L2 _L2 _L2 _L2 _L2
_L1:
        i;
        JVM INSTR tableswitch 138 142: default 100
    //                   138 203
    //                   139 203
    //                   140 203
    //                   141 203
    //                   142 203;
           goto _L3 _L2 _L2 _L2 _L2 _L2
_L3:
        i;
        JVM INSTR tableswitch 166 167: default 124
    //                   166 203
    //                   167 203;
           goto _L4 _L2 _L2
_L4:
        i;
        JVM INSTR lookupswitch 8: default 200
    //                   4: 203
    //                   67: 203
    //                   84: 203
    //                   111: 203
    //                   135: 203
    //                   164: 203
    //                   170: 203
    //                   219: 203;
           goto _L5 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2
_L2:
        flag = true;
_L5:
        keyevent = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("isDispatchToWebView() ret=");
        stringbuilder.append(flag);
        PxLog.v(keyevent, stringbuilder.toString());
        return flag;
    }

    private static final boolean IS_DISPATCH_KEYCODE_SEARCH_TO_APP = true;
    private static final String TAG = "KeyEventConverter";

}
