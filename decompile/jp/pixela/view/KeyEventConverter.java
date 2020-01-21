// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.view;

import android.view.KeyEvent;
import jp.pixela.common.PxLog;

class KeyEventConverter
{

    KeyEventConverter()
    {
    }

    KeyEvent convert(KeyEvent keyevent)
    {
        StringBuilder stringbuilder = null;
        if(keyevent == null)
        {
            PxLog.v(TAG, "convert() event == null");
            return null;
        }
        int i = keyevent.getKeyCode();
        KeyEvent keyevent1;
        if(i != 4)
        {
            keyevent1 = stringbuilder;
            if(i != 84)
                if(i != 135)
                {
                    keyevent1 = stringbuilder;
                    if(i != 164)
                    {
                        keyevent1 = stringbuilder;
                        if(i != 170)
                        {
                            keyevent1 = stringbuilder;
                            switch(i)
                            {
                            default:
                                switch(i)
                                {
                                default:
                                    switch(i)
                                    {
                                    default:
                                        keyevent1 = stringbuilder;
                                        break;

                                    case 167: 
                                        keyevent1 = new KeyEvent(keyevent.getDownTime(), keyevent.getEventTime(), keyevent.getAction(), 156, keyevent.getRepeatCount(), 0);
                                        break;

                                    case 166: 
                                        keyevent1 = new KeyEvent(keyevent.getDownTime(), keyevent.getEventTime(), keyevent.getAction(), 157, keyevent.getRepeatCount(), 0);
                                        break;
                                    }
                                    break;

                                case 142: 
                                    keyevent1 = new KeyEvent(keyevent.getDownTime(), keyevent.getEventTime(), keyevent.getAction(), 136, keyevent.getRepeatCount(), 0);
                                    break;

                                case 141: 
                                    keyevent1 = new KeyEvent(keyevent.getDownTime(), keyevent.getEventTime(), keyevent.getAction(), 137, keyevent.getRepeatCount(), 0);
                                    break;

                                case 140: 
                                    keyevent1 = new KeyEvent(keyevent.getDownTime(), keyevent.getEventTime(), keyevent.getAction(), 138, keyevent.getRepeatCount(), 0);
                                    break;

                                case 139: 
                                    keyevent1 = new KeyEvent(keyevent.getDownTime(), keyevent.getEventTime(), keyevent.getAction(), 135, keyevent.getRepeatCount(), 0);
                                    break;

                                case 138: 
                                    keyevent1 = new KeyEvent(keyevent.getDownTime(), keyevent.getEventTime(), keyevent.getAction(), 40, keyevent.getRepeatCount(), 0);
                                    break;
                                }
                                break;

                            case 19: // '\023'
                            case 20: // '\024'
                            case 21: // '\025'
                            case 22: // '\026'
                            case 23: // '\027'
                                keyevent1 = keyevent;
                                break;

                            case 24: // '\030'
                            case 25: // '\031'
                                break;
                            }
                        }
                    }
                } else
                {
                    keyevent1 = new KeyEvent(keyevent.getDownTime(), keyevent.getEventTime(), keyevent.getAction(), 142, keyevent.getRepeatCount(), 0);
                }
        } else
        {
            keyevent1 = new KeyEvent(keyevent.getDownTime(), keyevent.getEventTime(), keyevent.getAction(), 67, keyevent.getRepeatCount(), 0);
        }
        String s = TAG;
        stringbuilder = new StringBuilder();
        stringbuilder.append("convert() event=");
        stringbuilder.append(keyevent);
        stringbuilder.append(", newEvent=");
        stringbuilder.append(keyevent1);
        PxLog.v(s, stringbuilder.toString());
        return keyevent1;
    }

    boolean useAndroidStandard(KeyEvent keyevent)
    {
        boolean flag = false;
        if(keyevent == null)
        {
            PxLog.v(TAG, "useAndroidStandard() event == null");
            return false;
        }
        int i = keyevent.getKeyCode();
        boolean flag1 = flag;
        if(i != 4)
        {
            flag1 = flag;
            if(i != 84)
            {
                flag1 = flag;
                if(i != 135)
                {
                    flag1 = flag;
                    if(i != 164)
                    {
                        flag1 = flag;
                        if(i != 170)
                        {
                            flag1 = flag;
                            switch(i)
                            {
                            default:
                                flag1 = flag;
                                switch(i)
                                {
                                default:
                                    flag1 = flag;
                                    switch(i)
                                    {
                                    default:
                                        flag1 = true;
                                        break;

                                    case 166: 
                                    case 167: 
                                        break;
                                    }
                                    break;

                                case 138: 
                                case 139: 
                                case 140: 
                                case 141: 
                                case 142: 
                                    break;
                                }
                                break;

                            case 19: // '\023'
                            case 20: // '\024'
                            case 21: // '\025'
                            case 22: // '\026'
                            case 23: // '\027'
                            case 24: // '\030'
                            case 25: // '\031'
                                break;
                            }
                        }
                    }
                }
            }
        }
        String s = TAG;
        keyevent = new StringBuilder();
        keyevent.append("useAndroidStandard() ret=");
        keyevent.append(flag1);
        PxLog.v(s, keyevent.toString());
        return flag1;
    }

    private static final String TAG = "KeyEventConverter";

}
