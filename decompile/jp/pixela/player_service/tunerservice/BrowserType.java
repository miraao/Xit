// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;

import android.view.KeyEvent;

public class BrowserType
{
    public static final class KeyCode extends Enum
    {

        public static KeyCode valueOf(String s)
        {
            return (KeyCode)Enum.valueOf(jp/pixela/player_service/tunerservice/BrowserType$KeyCode, s);
        }

        public static KeyCode[] values()
        {
            return (KeyCode[])$VALUES.clone();
        }

        public int getValue()
        {
            return mValue;
        }

        private static final KeyCode $VALUES[];
        public static final KeyCode KEY_CODE_0;
        public static final KeyCode KEY_CODE_1;
        public static final KeyCode KEY_CODE_10;
        public static final KeyCode KEY_CODE_11;
        public static final KeyCode KEY_CODE_12;
        public static final KeyCode KEY_CODE_2;
        public static final KeyCode KEY_CODE_3;
        public static final KeyCode KEY_CODE_4;
        public static final KeyCode KEY_CODE_5;
        public static final KeyCode KEY_CODE_6;
        public static final KeyCode KEY_CODE_7;
        public static final KeyCode KEY_CODE_8;
        public static final KeyCode KEY_CODE_9;
        public static final KeyCode KEY_CODE_BACK;
        public static final KeyCode KEY_CODE_BLUE;
        public static final KeyCode KEY_CODE_DATA;
        public static final KeyCode KEY_CODE_DOWN;
        public static final KeyCode KEY_CODE_ENTER;
        public static final KeyCode KEY_CODE_GREEN;
        public static final KeyCode KEY_CODE_LEFT;
        public static final KeyCode KEY_CODE_NONE_0;
        public static final KeyCode KEY_CODE_RED;
        public static final KeyCode KEY_CODE_RIGHT;
        public static final KeyCode KEY_CODE_UP;
        public static final KeyCode KEY_CODE_YELLOW;
        public int mValue;

        static 
        {
            KEY_CODE_NONE_0 = new KeyCode("KEY_CODE_NONE_0", 0, 0);
            KEY_CODE_UP = new KeyCode("KEY_CODE_UP", 1, 1);
            KEY_CODE_DOWN = new KeyCode("KEY_CODE_DOWN", 2, 2);
            KEY_CODE_LEFT = new KeyCode("KEY_CODE_LEFT", 3, 3);
            KEY_CODE_RIGHT = new KeyCode("KEY_CODE_RIGHT", 4, 4);
            KEY_CODE_0 = new KeyCode("KEY_CODE_0", 5, 5);
            KEY_CODE_1 = new KeyCode("KEY_CODE_1", 6, 6);
            KEY_CODE_2 = new KeyCode("KEY_CODE_2", 7, 7);
            KEY_CODE_3 = new KeyCode("KEY_CODE_3", 8, 8);
            KEY_CODE_4 = new KeyCode("KEY_CODE_4", 9, 9);
            KEY_CODE_5 = new KeyCode("KEY_CODE_5", 10, 10);
            KEY_CODE_6 = new KeyCode("KEY_CODE_6", 11, 11);
            KEY_CODE_7 = new KeyCode("KEY_CODE_7", 12, 12);
            KEY_CODE_8 = new KeyCode("KEY_CODE_8", 13, 13);
            KEY_CODE_9 = new KeyCode("KEY_CODE_9", 14, 14);
            KEY_CODE_10 = new KeyCode("KEY_CODE_10", 15, 15);
            KEY_CODE_11 = new KeyCode("KEY_CODE_11", 16, 16);
            KEY_CODE_12 = new KeyCode("KEY_CODE_12", 17, 17);
            KEY_CODE_ENTER = new KeyCode("KEY_CODE_ENTER", 18, 18);
            KEY_CODE_BACK = new KeyCode("KEY_CODE_BACK", 19, 19);
            KEY_CODE_DATA = new KeyCode("KEY_CODE_DATA", 20, 20);
            KEY_CODE_BLUE = new KeyCode("KEY_CODE_BLUE", 21, 21);
            KEY_CODE_RED = new KeyCode("KEY_CODE_RED", 22, 22);
            KEY_CODE_GREEN = new KeyCode("KEY_CODE_GREEN", 23, 23);
            KEY_CODE_YELLOW = new KeyCode("KEY_CODE_YELLOW", 24, 24);
            $VALUES = (new KeyCode[] {
                KEY_CODE_NONE_0, KEY_CODE_UP, KEY_CODE_DOWN, KEY_CODE_LEFT, KEY_CODE_RIGHT, KEY_CODE_0, KEY_CODE_1, KEY_CODE_2, KEY_CODE_3, KEY_CODE_4, 
                KEY_CODE_5, KEY_CODE_6, KEY_CODE_7, KEY_CODE_8, KEY_CODE_9, KEY_CODE_10, KEY_CODE_11, KEY_CODE_12, KEY_CODE_ENTER, KEY_CODE_BACK, 
                KEY_CODE_DATA, KEY_CODE_BLUE, KEY_CODE_RED, KEY_CODE_GREEN, KEY_CODE_YELLOW
            });
        }

        private KeyCode(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }

    public static final class KeyGroup extends Enum
    {

        public static KeyGroup valueOf(String s)
        {
            return (KeyGroup)Enum.valueOf(jp/pixela/player_service/tunerservice/BrowserType$KeyGroup, s);
        }

        public static KeyGroup[] values()
        {
            return (KeyGroup[])$VALUES.clone();
        }

        public int getValue()
        {
            return mValue;
        }

        private static final KeyGroup $VALUES[];
        public static final KeyGroup KeyGroup_Blue;
        public static final KeyGroup KeyGroup_DButton;
        public static final KeyGroup KeyGroup_Green;
        public static final KeyGroup KeyGroup_Navigation;
        public static final KeyGroup KeyGroup_None;
        public static final KeyGroup KeyGroup_Numeric;
        public static final KeyGroup KeyGroup_Red;
        public static final KeyGroup KeyGroup_Subtitle;
        public static final KeyGroup KeyGroup_Vcr;
        public static final KeyGroup KeyGroup_Yellow;
        public int mValue;

        static 
        {
            KeyGroup_None = new KeyGroup("KeyGroup_None", 0, 0);
            KeyGroup_Red = new KeyGroup("KeyGroup_Red", 1, 1);
            KeyGroup_Green = new KeyGroup("KeyGroup_Green", 2, 2);
            KeyGroup_Yellow = new KeyGroup("KeyGroup_Yellow", 3, 4);
            KeyGroup_Blue = new KeyGroup("KeyGroup_Blue", 4, 8);
            KeyGroup_Navigation = new KeyGroup("KeyGroup_Navigation", 5, 16);
            KeyGroup_Numeric = new KeyGroup("KeyGroup_Numeric", 6, 32);
            KeyGroup_Vcr = new KeyGroup("KeyGroup_Vcr", 7, 64);
            KeyGroup_DButton = new KeyGroup("KeyGroup_DButton", 8, 128);
            KeyGroup_Subtitle = new KeyGroup("KeyGroup_Subtitle", 9, 256);
            $VALUES = (new KeyGroup[] {
                KeyGroup_None, KeyGroup_Red, KeyGroup_Green, KeyGroup_Yellow, KeyGroup_Blue, KeyGroup_Navigation, KeyGroup_Numeric, KeyGroup_Vcr, KeyGroup_DButton, KeyGroup_Subtitle
            });
        }

        private KeyGroup(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }

    public static final class KeyState extends Enum
    {

        public static KeyState valueOf(String s)
        {
            return (KeyState)Enum.valueOf(jp/pixela/player_service/tunerservice/BrowserType$KeyState, s);
        }

        public static KeyState[] values()
        {
            return (KeyState[])$VALUES.clone();
        }

        public int getValue()
        {
            return mValue;
        }

        private static final KeyState $VALUES[];
        public static final KeyState KEY_STATE_DOWN;
        public static final KeyState KEY_STATE_UP;
        public int mValue;

        static 
        {
            KEY_STATE_UP = new KeyState("KEY_STATE_UP", 0, 0);
            KEY_STATE_DOWN = new KeyState("KEY_STATE_DOWN", 1, 1);
            $VALUES = (new KeyState[] {
                KEY_STATE_UP, KEY_STATE_DOWN
            });
        }

        private KeyState(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }


    public BrowserType()
    {
    }

    public static KeyEvent convert(int i, int j)
    {
        boolean flag;
label0:
        {
            char c = '\007';
            flag = false;
            switch(j)
            {
            default:
                j = 0;
                break label0;

            case 5: // '\005'
            case 15: // '\017'
                break;

            case 24: // '\030'
                c = '\212';
                break;

            case 23: // '\027'
                c = '\211';
                break;

            case 22: // '\026'
                c = '\210';
                break;

            case 21: // '\025'
                c = '\207';
                break;

            case 20: // '\024'
                c = ' ';
                break;

            case 19: // '\023'
                c = 'C';
                break;

            case 18: // '\022'
                c = '\027';
                break;

            case 17: // '\021'
                c = '\344';
                break;

            case 16: // '\020'
                c = '\343';
                break;

            case 14: // '\016'
                c = '\020';
                break;

            case 13: // '\r'
                c = '\017';
                break;

            case 12: // '\f'
                c = '\016';
                break;

            case 11: // '\013'
                c = '\r';
                break;

            case 10: // '\n'
                c = '\f';
                break;

            case 9: // '\t'
                c = '\013';
                break;

            case 8: // '\b'
                c = '\n';
                break;

            case 7: // '\007'
                c = '\t';
                break;

            case 6: // '\006'
                c = '\b';
                break;

            case 4: // '\004'
                c = '\026';
                break;

            case 3: // '\003'
                c = '\025';
                break;

            case 2: // '\002'
                c = '\024';
                break;

            case 1: // '\001'
                c = '\023';
                break;
            }
            j = c;
        }
        if(j != 0)
        {
            int k = ((flag) ? 1 : 0);
            if(KeyState.KEY_STATE_UP.getValue() == i)
                k = 1;
            return new KeyEvent(0L, 0L, k, j, 0, 0);
        } else
        {
            return null;
        }
    }

    public static KeyGroup getKeyGroup(int i)
    {
        switch(i)
        {
        default:
            return KeyGroup.KeyGroup_None;

        case 24: // '\030'
            return KeyGroup.KeyGroup_Yellow;

        case 23: // '\027'
            return KeyGroup.KeyGroup_Green;

        case 22: // '\026'
            return KeyGroup.KeyGroup_Red;

        case 21: // '\025'
            return KeyGroup.KeyGroup_Blue;

        case 20: // '\024'
            return KeyGroup.KeyGroup_DButton;

        case 18: // '\022'
        case 19: // '\023'
            return KeyGroup.KeyGroup_Navigation;

        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        case 16: // '\020'
        case 17: // '\021'
            return KeyGroup.KeyGroup_Numeric;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
            return KeyGroup.KeyGroup_Navigation;
        }
    }
}
