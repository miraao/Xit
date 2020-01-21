// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;


public class BmlType
{
    public static final class CharType extends Enum
    {

        public static CharType valueOf(String s)
        {
            return (CharType)Enum.valueOf(jp/pixela/player_service/tunerservice/BmlType$CharType, s);
        }

        public static CharType[] values()
        {
            return (CharType[])$VALUES.clone();
        }

        public int getValue()
        {
            return mValue;
        }

        private static final CharType $VALUES[];
        public static final CharType CHARACTER_TYPE_ALL;
        public static final CharType CHARACTER_TYPE_ALPHABET;
        public static final CharType CHARACTER_TYPE_HANKAKU;
        public static final CharType CHARACTER_TYPE_HIRAGANA;
        public static final CharType CHARACTER_TYPE_KATAKANA;
        public static final CharType CHARACTER_TYPE_NUMBER;
        public static final CharType CHARACTER_TYPE_ZENKAKU;
        public int mValue;

        static 
        {
            CHARACTER_TYPE_ALL = new CharType("CHARACTER_TYPE_ALL", 0, 0);
            CHARACTER_TYPE_NUMBER = new CharType("CHARACTER_TYPE_NUMBER", 1, 1);
            CHARACTER_TYPE_ALPHABET = new CharType("CHARACTER_TYPE_ALPHABET", 2, 2);
            CHARACTER_TYPE_HANKAKU = new CharType("CHARACTER_TYPE_HANKAKU", 3, 3);
            CHARACTER_TYPE_ZENKAKU = new CharType("CHARACTER_TYPE_ZENKAKU", 4, 4);
            CHARACTER_TYPE_KATAKANA = new CharType("CHARACTER_TYPE_KATAKANA", 5, 5);
            CHARACTER_TYPE_HIRAGANA = new CharType("CHARACTER_TYPE_HIRAGANA", 6, 6);
            $VALUES = (new CharType[] {
                CHARACTER_TYPE_ALL, CHARACTER_TYPE_NUMBER, CHARACTER_TYPE_ALPHABET, CHARACTER_TYPE_HANKAKU, CHARACTER_TYPE_ZENKAKU, CHARACTER_TYPE_KATAKANA, CHARACTER_TYPE_HIRAGANA
            });
        }

        private CharType(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }

    public static final class ConfirmItem extends Enum
    {

        public static ConfirmItem valueOf(String s)
        {
            return (ConfirmItem)Enum.valueOf(jp/pixela/player_service/tunerservice/BmlType$ConfirmItem, s);
        }

        public static ConfirmItem[] values()
        {
            return (ConfirmItem[])$VALUES.clone();
        }

        public int getValue()
        {
            return mValue;
        }

        private static final ConfirmItem $VALUES[];
        public static final ConfirmItem CONNECT;
        public static final ConfirmItem DIALUP;
        public static final ConfirmItem GPS;
        public static final ConfirmItem HTML_APP;
        public static final ConfirmItem IMEI;
        public static final ConfirmItem MAIL_APP;
        public static final ConfirmItem PHONE_APP;
        public static final ConfirmItem SSL;
        public static final ConfirmItem TRANSMIT;
        public int mValue;

        static 
        {
            CONNECT = new ConfirmItem("CONNECT", 0, 0);
            TRANSMIT = new ConfirmItem("TRANSMIT", 1, 1);
            SSL = new ConfirmItem("SSL", 2, 2);
            DIALUP = new ConfirmItem("DIALUP", 3, 3);
            MAIL_APP = new ConfirmItem("MAIL_APP", 4, 4);
            HTML_APP = new ConfirmItem("HTML_APP", 5, 5);
            PHONE_APP = new ConfirmItem("PHONE_APP", 6, 6);
            GPS = new ConfirmItem("GPS", 7, 7);
            IMEI = new ConfirmItem("IMEI", 8, 8);
            $VALUES = (new ConfirmItem[] {
                CONNECT, TRANSMIT, SSL, DIALUP, MAIL_APP, HTML_APP, PHONE_APP, GPS, IMEI
            });
        }

        private ConfirmItem(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }

    public static final class KeyCode extends Enum
    {

        public static KeyCode valueOf(String s)
        {
            return (KeyCode)Enum.valueOf(jp/pixela/player_service/tunerservice/BmlType$KeyCode, s);
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
        public static final KeyCode KEYCODE_RETURN_START_UP;
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
        public static final KeyCode KEY_CODE_ARIB_RESERVED_END;
        public static final KeyCode KEY_CODE_ARIB_RESERVED_START;
        public static final KeyCode KEY_CODE_ASTERISK;
        public static final KeyCode KEY_CODE_BACK;
        public static final KeyCode KEY_CODE_BLUE;
        public static final KeyCode KEY_CODE_BOOKMARK;
        public static final KeyCode KEY_CODE_DATA;
        public static final KeyCode KEY_CODE_DATA1;
        public static final KeyCode KEY_CODE_DATA2;
        public static final KeyCode KEY_CODE_DATA3;
        public static final KeyCode KEY_CODE_DATA4;
        public static final KeyCode KEY_CODE_DOWN;
        public static final KeyCode KEY_CODE_ENTER;
        public static final KeyCode KEY_CODE_GREEN;
        public static final KeyCode KEY_CODE_LEFT;
        public static final KeyCode KEY_CODE_NONE_0;
        public static final KeyCode KEY_CODE_NUMBER_SIGN;
        public static final KeyCode KEY_CODE_RED;
        public static final KeyCode KEY_CODE_RESERVED_END;
        public static final KeyCode KEY_CODE_RESERVED_START;
        public static final KeyCode KEY_CODE_RIGHT;
        public static final KeyCode KEY_CODE_UP;
        public static final KeyCode KEY_CODE_VENDOE_RESERVED_START;
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
            KEY_CODE_DATA1 = new KeyCode("KEY_CODE_DATA1", 25, 25);
            KEY_CODE_DATA2 = new KeyCode("KEY_CODE_DATA2", 26, 26);
            KEY_CODE_DATA3 = new KeyCode("KEY_CODE_DATA3", 27, 27);
            KEY_CODE_DATA4 = new KeyCode("KEY_CODE_DATA4", 28, 28);
            KEY_CODE_ARIB_RESERVED_START = new KeyCode("KEY_CODE_ARIB_RESERVED_START", 29, 29);
            KEY_CODE_ARIB_RESERVED_END = new KeyCode("KEY_CODE_ARIB_RESERVED_END", 30, 99);
            KEY_CODE_BOOKMARK = new KeyCode("KEY_CODE_BOOKMARK", 31, 100);
            KEY_CODE_ASTERISK = new KeyCode("KEY_CODE_ASTERISK", 32, 101);
            KEY_CODE_NUMBER_SIGN = new KeyCode("KEY_CODE_NUMBER_SIGN", 33, 102);
            KEY_CODE_RESERVED_START = new KeyCode("KEY_CODE_RESERVED_START", 34, 101);
            KEY_CODE_RESERVED_END = new KeyCode("KEY_CODE_RESERVED_END", 35, 149);
            KEY_CODE_VENDOE_RESERVED_START = new KeyCode("KEY_CODE_VENDOE_RESERVED_START", 36, 150);
            KEYCODE_RETURN_START_UP = new KeyCode("KEYCODE_RETURN_START_UP", 37, 254);
            $VALUES = (new KeyCode[] {
                KEY_CODE_NONE_0, KEY_CODE_UP, KEY_CODE_DOWN, KEY_CODE_LEFT, KEY_CODE_RIGHT, KEY_CODE_0, KEY_CODE_1, KEY_CODE_2, KEY_CODE_3, KEY_CODE_4, 
                KEY_CODE_5, KEY_CODE_6, KEY_CODE_7, KEY_CODE_8, KEY_CODE_9, KEY_CODE_10, KEY_CODE_11, KEY_CODE_12, KEY_CODE_ENTER, KEY_CODE_BACK, 
                KEY_CODE_DATA, KEY_CODE_BLUE, KEY_CODE_RED, KEY_CODE_GREEN, KEY_CODE_YELLOW, KEY_CODE_DATA1, KEY_CODE_DATA2, KEY_CODE_DATA3, KEY_CODE_DATA4, KEY_CODE_ARIB_RESERVED_START, 
                KEY_CODE_ARIB_RESERVED_END, KEY_CODE_BOOKMARK, KEY_CODE_ASTERISK, KEY_CODE_NUMBER_SIGN, KEY_CODE_RESERVED_START, KEY_CODE_RESERVED_END, KEY_CODE_VENDOE_RESERVED_START, KEYCODE_RETURN_START_UP
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
            return (KeyGroup)Enum.valueOf(jp/pixela/player_service/tunerservice/BmlType$KeyGroup, s);
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
        public static final KeyGroup KeyGroup_Basic;
        public static final KeyGroup KeyGroup_DataButton;
        public static final KeyGroup KeyGroup_NumericTuning;
        public static final KeyGroup KeyGroup_OtherTuning;
        public static final KeyGroup KeyGroup_Special1;
        public static final KeyGroup KeyGroup_Special2;
        public int mValue;

        static 
        {
            KeyGroup_Basic = new KeyGroup("KeyGroup_Basic", 0, 1);
            KeyGroup_DataButton = new KeyGroup("KeyGroup_DataButton", 1, 2);
            KeyGroup_NumericTuning = new KeyGroup("KeyGroup_NumericTuning", 2, 4);
            KeyGroup_OtherTuning = new KeyGroup("KeyGroup_OtherTuning", 3, 8);
            KeyGroup_Special1 = new KeyGroup("KeyGroup_Special1", 4, 16);
            KeyGroup_Special2 = new KeyGroup("KeyGroup_Special2", 5, 32);
            $VALUES = (new KeyGroup[] {
                KeyGroup_Basic, KeyGroup_DataButton, KeyGroup_NumericTuning, KeyGroup_OtherTuning, KeyGroup_Special1, KeyGroup_Special2
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
            return (KeyState)Enum.valueOf(jp/pixela/player_service/tunerservice/BmlType$KeyState, s);
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

    public static final class Note extends Enum
    {

        public static Note valueOf(String s)
        {
            return (Note)Enum.valueOf(jp/pixela/player_service/tunerservice/BmlType$Note, s);
        }

        public static Note[] values()
        {
            return (Note[])$VALUES.clone();
        }

        public int getValue()
        {
            return mValue;
        }

        private static final Note $VALUES[];
        public static final Note ERROR_ARIB_E300;
        public static final Note ERROR_ARIB_E400;
        public static final Note ERROR_ARIB_E401;
        public static final Note ERROR_ARIB_E402;
        public static final Note ERROR_NVRAM_FULL_AFFILIATION00;
        public static final Note ERROR_NVRAM_FULL_AFFILIATION01;
        public static final Note ERROR_NVRAM_FULL_AFFILIATION02;
        public static final Note ERROR_NVRAM_FULL_AFFILIATION03;
        public static final Note ERROR_NVRAM_FULL_AFFILIATION04;
        public static final Note ERROR_NVRAM_FULL_AFFILIATION05;
        public static final Note ERROR_NVRAM_FULL_AFFILIATION06;
        public static final Note ERROR_NVRAM_FULL_AFFILIATION07;
        public static final Note ERROR_NVRAM_FULL_AFFILIATION08;
        public static final Note ERROR_NVRAM_FULL_AFFILIATION09;
        public static final Note ERROR_NVRAM_FULL_AFFILIATION10;
        public static final Note ERROR_NVRAM_FULL_AFFILIATION11;
        public static final Note ERROR_TLS_CERTIFICATE_EXPIRED;
        public static final Note ERROR_TLS_CERTIFICATE_NOTHING;
        public static final Note ERROR_TLS_CERTIFICATE_REVOKE;
        public static final Note ERROR_TLS_CHAIN;
        public static final Note ERROR_TLS_CRYPT;
        public static final Note ERROR_TLS_FALSIFICATE;
        public static final Note ERROR_TLS_SERVER_EXPIRED;
        public static final Note ERROR_TLS_SIGNATURE_FAILURE;
        public static final Note ERROR_TLS_TIMEOUT;
        public static final Note NOTE_DATA_RECEIVING;
        public static final Note NOTE_STATE_UNLINK;
        public static final Note Note_DataLoading;
        public static final Note Note_HttpLoading;
        public static final Note Note_QuitBrowser;
        public int mValue;

        static 
        {
            NOTE_DATA_RECEIVING = new Note("NOTE_DATA_RECEIVING", 0, 1);
            NOTE_STATE_UNLINK = new Note("NOTE_STATE_UNLINK", 1, 2);
            ERROR_ARIB_E300 = new Note("ERROR_ARIB_E300", 2, 3);
            ERROR_ARIB_E400 = new Note("ERROR_ARIB_E400", 3, 4);
            ERROR_ARIB_E401 = new Note("ERROR_ARIB_E401", 4, 5);
            ERROR_ARIB_E402 = new Note("ERROR_ARIB_E402", 5, 6);
            Note_QuitBrowser = new Note("Note_QuitBrowser", 6, 7);
            Note_DataLoading = new Note("Note_DataLoading", 7, 10);
            Note_HttpLoading = new Note("Note_HttpLoading", 8, 11);
            ERROR_TLS_CERTIFICATE_NOTHING = new Note("ERROR_TLS_CERTIFICATE_NOTHING", 9, 101);
            ERROR_TLS_CRYPT = new Note("ERROR_TLS_CRYPT", 10, 102);
            ERROR_TLS_CERTIFICATE_EXPIRED = new Note("ERROR_TLS_CERTIFICATE_EXPIRED", 11, 103);
            ERROR_TLS_TIMEOUT = new Note("ERROR_TLS_TIMEOUT", 12, 104);
            ERROR_TLS_SERVER_EXPIRED = new Note("ERROR_TLS_SERVER_EXPIRED", 13, 105);
            ERROR_TLS_SIGNATURE_FAILURE = new Note("ERROR_TLS_SIGNATURE_FAILURE", 14, 106);
            ERROR_TLS_CERTIFICATE_REVOKE = new Note("ERROR_TLS_CERTIFICATE_REVOKE", 15, 107);
            ERROR_TLS_FALSIFICATE = new Note("ERROR_TLS_FALSIFICATE", 16, 108);
            ERROR_TLS_CHAIN = new Note("ERROR_TLS_CHAIN", 17, 109);
            ERROR_NVRAM_FULL_AFFILIATION00 = new Note("ERROR_NVRAM_FULL_AFFILIATION00", 18, 200);
            ERROR_NVRAM_FULL_AFFILIATION01 = new Note("ERROR_NVRAM_FULL_AFFILIATION01", 19, 201);
            ERROR_NVRAM_FULL_AFFILIATION02 = new Note("ERROR_NVRAM_FULL_AFFILIATION02", 20, 202);
            ERROR_NVRAM_FULL_AFFILIATION03 = new Note("ERROR_NVRAM_FULL_AFFILIATION03", 21, 203);
            ERROR_NVRAM_FULL_AFFILIATION04 = new Note("ERROR_NVRAM_FULL_AFFILIATION04", 22, 204);
            ERROR_NVRAM_FULL_AFFILIATION05 = new Note("ERROR_NVRAM_FULL_AFFILIATION05", 23, 205);
            ERROR_NVRAM_FULL_AFFILIATION06 = new Note("ERROR_NVRAM_FULL_AFFILIATION06", 24, 206);
            ERROR_NVRAM_FULL_AFFILIATION07 = new Note("ERROR_NVRAM_FULL_AFFILIATION07", 25, 207);
            ERROR_NVRAM_FULL_AFFILIATION08 = new Note("ERROR_NVRAM_FULL_AFFILIATION08", 26, 208);
            ERROR_NVRAM_FULL_AFFILIATION09 = new Note("ERROR_NVRAM_FULL_AFFILIATION09", 27, 209);
            ERROR_NVRAM_FULL_AFFILIATION10 = new Note("ERROR_NVRAM_FULL_AFFILIATION10", 28, 210);
            ERROR_NVRAM_FULL_AFFILIATION11 = new Note("ERROR_NVRAM_FULL_AFFILIATION11", 29, 211);
            $VALUES = (new Note[] {
                NOTE_DATA_RECEIVING, NOTE_STATE_UNLINK, ERROR_ARIB_E300, ERROR_ARIB_E400, ERROR_ARIB_E401, ERROR_ARIB_E402, Note_QuitBrowser, Note_DataLoading, Note_HttpLoading, ERROR_TLS_CERTIFICATE_NOTHING, 
                ERROR_TLS_CRYPT, ERROR_TLS_CERTIFICATE_EXPIRED, ERROR_TLS_TIMEOUT, ERROR_TLS_SERVER_EXPIRED, ERROR_TLS_SIGNATURE_FAILURE, ERROR_TLS_CERTIFICATE_REVOKE, ERROR_TLS_FALSIFICATE, ERROR_TLS_CHAIN, ERROR_NVRAM_FULL_AFFILIATION00, ERROR_NVRAM_FULL_AFFILIATION01, 
                ERROR_NVRAM_FULL_AFFILIATION02, ERROR_NVRAM_FULL_AFFILIATION03, ERROR_NVRAM_FULL_AFFILIATION04, ERROR_NVRAM_FULL_AFFILIATION05, ERROR_NVRAM_FULL_AFFILIATION06, ERROR_NVRAM_FULL_AFFILIATION07, ERROR_NVRAM_FULL_AFFILIATION08, ERROR_NVRAM_FULL_AFFILIATION09, ERROR_NVRAM_FULL_AFFILIATION10, ERROR_NVRAM_FULL_AFFILIATION11
            });
        }

        private Note(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }

    public static final class Rotation extends Enum
    {

        public static Rotation valueOf(String s)
        {
            return (Rotation)Enum.valueOf(jp/pixela/player_service/tunerservice/BmlType$Rotation, s);
        }

        public static Rotation[] values()
        {
            return (Rotation[])$VALUES.clone();
        }

        public int getValue()
        {
            return mValue;
        }

        private static final Rotation $VALUES[];
        public static final Rotation Rotation_0;
        public static final Rotation Rotation_180;
        public static final Rotation Rotation_270;
        public static final Rotation Rotation_90;
        public int mValue;

        static 
        {
            Rotation_0 = new Rotation("Rotation_0", 0, 1);
            Rotation_90 = new Rotation("Rotation_90", 1, 2);
            Rotation_180 = new Rotation("Rotation_180", 2, 4);
            Rotation_270 = new Rotation("Rotation_270", 3, 8);
            $VALUES = (new Rotation[] {
                Rotation_0, Rotation_90, Rotation_180, Rotation_270
            });
        }

        private Rotation(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }

    public static final class SetType extends Enum
    {

        public static SetType valueOf(String s)
        {
            return (SetType)Enum.valueOf(jp/pixela/player_service/tunerservice/BmlType$SetType, s);
        }

        public static SetType[] values()
        {
            return (SetType[])$VALUES.clone();
        }

        public int getValue()
        {
            return mValue;
        }

        private static final SetType $VALUES[];
        public static final SetType OFF;
        public static final SetType ON;
        public int mValue;

        static 
        {
            OFF = new SetType("OFF", 0, 0);
            ON = new SetType("ON", 1, 1);
            $VALUES = (new SetType[] {
                OFF, ON
            });
        }

        private SetType(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }

    public static final class SupportMedia extends Enum
    {

        public static SupportMedia valueOf(String s)
        {
            return (SupportMedia)Enum.valueOf(jp/pixela/player_service/tunerservice/BmlType$SupportMedia, s);
        }

        public static SupportMedia[] values()
        {
            return (SupportMedia[])$VALUES.clone();
        }

        public int getValue()
        {
            return mValue;
        }

        private static final SupportMedia $VALUES[];
        public static final SupportMedia SUPPORT_MEDIA_ALL;
        public static final SupportMedia SUPPORT_MEDIA_CS_CCW;
        public static final SupportMedia SUPPORT_MEDIA_CS_CW;
        public static final SupportMedia SUPPORT_MEDIA_INVALID;
        public static final SupportMedia SUPPORT_MEDIA_MEDIA_BS;
        public static final SupportMedia SUPPORT_MEDIA_TR_AUDIO;
        public static final SupportMedia SUPPORT_MEDIA_TR_TV;
        public int mValue;

        static 
        {
            SUPPORT_MEDIA_INVALID = new SupportMedia("SUPPORT_MEDIA_INVALID", 0, 0);
            SUPPORT_MEDIA_MEDIA_BS = new SupportMedia("SUPPORT_MEDIA_MEDIA_BS", 1, 1);
            SUPPORT_MEDIA_CS_CW = new SupportMedia("SUPPORT_MEDIA_CS_CW", 2, 2);
            SUPPORT_MEDIA_CS_CCW = new SupportMedia("SUPPORT_MEDIA_CS_CCW", 3, 4);
            SUPPORT_MEDIA_TR_TV = new SupportMedia("SUPPORT_MEDIA_TR_TV", 4, 8);
            SUPPORT_MEDIA_TR_AUDIO = new SupportMedia("SUPPORT_MEDIA_TR_AUDIO", 5, 16);
            SUPPORT_MEDIA_ALL = new SupportMedia("SUPPORT_MEDIA_ALL", 6, 31);
            $VALUES = (new SupportMedia[] {
                SUPPORT_MEDIA_INVALID, SUPPORT_MEDIA_MEDIA_BS, SUPPORT_MEDIA_CS_CW, SUPPORT_MEDIA_CS_CCW, SUPPORT_MEDIA_TR_TV, SUPPORT_MEDIA_TR_AUDIO, SUPPORT_MEDIA_ALL
            });
        }

        private SupportMedia(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }

    public static final class TouchState extends Enum
    {

        public static TouchState valueOf(String s)
        {
            return (TouchState)Enum.valueOf(jp/pixela/player_service/tunerservice/BmlType$TouchState, s);
        }

        public static TouchState[] values()
        {
            return (TouchState[])$VALUES.clone();
        }

        public int getValue()
        {
            return mValue;
        }

        private static final TouchState $VALUES[];
        public static final TouchState TOUCH_STATE_DOWN;
        public static final TouchState TOUCH_STATE_UP;
        public int mValue;

        static 
        {
            TOUCH_STATE_UP = new TouchState("TOUCH_STATE_UP", 0, 0);
            TOUCH_STATE_DOWN = new TouchState("TOUCH_STATE_DOWN", 1, 1);
            $VALUES = (new TouchState[] {
                TOUCH_STATE_UP, TOUCH_STATE_DOWN
            });
        }

        private TouchState(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }

    public static final class TvLinkType extends Enum
    {

        public static TvLinkType valueOf(String s)
        {
            return (TvLinkType)Enum.valueOf(jp/pixela/player_service/tunerservice/BmlType$TvLinkType, s);
        }

        public static TvLinkType[] values()
        {
            return (TvLinkType[])$VALUES.clone();
        }

        public int getValue()
        {
            return mValue;
        }

        private static final TvLinkType $VALUES[];
        public static final TvLinkType CPROLINK;
        public static final TvLinkType CPROUNLINK;
        public static final TvLinkType HTML;
        public static final TvLinkType MEMO;
        public static final TvLinkType SPECIAL;
        public int mValue;

        static 
        {
            MEMO = new TvLinkType("MEMO", 0, 0);
            CPROLINK = new TvLinkType("CPROLINK", 1, 1);
            CPROUNLINK = new TvLinkType("CPROUNLINK", 2, 2);
            HTML = new TvLinkType("HTML", 3, 3);
            SPECIAL = new TvLinkType("SPECIAL", 4, 4);
            $VALUES = (new TvLinkType[] {
                MEMO, CPROLINK, CPROUNLINK, HTML, SPECIAL
            });
        }

        private TvLinkType(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }


    public BmlType()
    {
    }
}
