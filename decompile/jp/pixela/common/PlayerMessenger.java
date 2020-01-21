// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.common;

import android.os.Message;
import java.util.Observable;
import java.util.Observer;

// Referenced classes of package jp.pixela.common:
//            PxLog

public class PlayerMessenger extends Observable
{
    public static class MessageParam
    {

        public Object getNotifyParam()
        {
            return notifyParam_;
        }

        public long getTarget()
        {
            return target_;
        }

        private Object notifyParam_;
        private long target_;

        public MessageParam(Object obj, long l)
        {
            notifyParam_ = null;
            target_ = 0L;
            notifyParam_ = obj;
            target_ = l;
        }
    }

    public static class PlayerObserver
        implements Observer
    {

        public void update(Observable observable, Object obj)
        {
            observable = (Message)obj;
            obj = new StringBuilder();
            ((StringBuilder) (obj)).append("notify type=");
            ((StringBuilder) (obj)).append(((Message) (observable)).what);
            PxLog.d("PlayerObserver", ((StringBuilder) (obj)).toString());
            interface_.notify(observable);
        }

        private static final String OBSERVER_TAG = "PlayerObserver";
        PlayerObserverInterface interface_;

        public PlayerObserver(PlayerObserverInterface playerobserverinterface)
        {
            interface_ = playerobserverinterface;
        }
    }

    public static interface PlayerObserverInterface
    {

        public abstract void notify(Message message);
    }

    public static final class WindowType extends Enum
    {

        public static WindowType valueOf(String s)
        {
            return (WindowType)Enum.valueOf(jp/pixela/common/PlayerMessenger$WindowType, s);
        }

        public static WindowType[] values()
        {
            return (WindowType[])$VALUES.clone();
        }

        public int getValue()
        {
            return mValue;
        }

        private static final WindowType $VALUES[];
        public static final WindowType ChannelList;
        public static final WindowType ContentList;
        public static final WindowType DataPanel;
        public static final WindowType HddFormat;
        public static final WindowType Home;
        public static final WindowType InitialSetting;
        public static final WindowType PisSignIn;
        public static final WindowType Preview;
        public static final WindowType ProgramDetail;
        public static final WindowType ProgramList;
        public static final WindowType ProgramSearch;
        public static final WindowType Reserve1;
        public static final WindowType ReserveList;
        public static final WindowType Setting;
        private final int mValue;

        static 
        {
            Home = new WindowType("Home", 0, 1);
            Preview = new WindowType("Preview", 1, 2);
            DataPanel = new WindowType("DataPanel", 2, 3);
            ContentList = new WindowType("ContentList", 3, 4);
            ReserveList = new WindowType("ReserveList", 4, 5);
            ProgramList = new WindowType("ProgramList", 5, 6);
            Reserve1 = new WindowType("Reserve1", 6, 7);
            Setting = new WindowType("Setting", 7, 8);
            ProgramSearch = new WindowType("ProgramSearch", 8, 9);
            ChannelList = new WindowType("ChannelList", 9, 10);
            ProgramDetail = new WindowType("ProgramDetail", 10, 11);
            InitialSetting = new WindowType("InitialSetting", 11, 12);
            HddFormat = new WindowType("HddFormat", 12, 50);
            PisSignIn = new WindowType("PisSignIn", 13, 51);
            $VALUES = (new WindowType[] {
                Home, Preview, DataPanel, ContentList, ReserveList, ProgramList, Reserve1, Setting, ProgramSearch, ChannelList, 
                ProgramDetail, InitialSetting, HddFormat, PisSignIn
            });
        }

        private WindowType(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }


    private PlayerMessenger()
    {
    }

    public static void addPlayerObserver(PlayerObserver playerobserver)
    {
        PxLog.d("PlayerMessenger", "addPlayerObserver");
        PlayerMessenger playermessenger = getInstance();
        if(playermessenger != null)
            playermessenger.addObserver(playerobserver);
    }

    public static void deletePlayerObserver(PlayerObserver playerobserver)
    {
        PxLog.d("PlayerMessenger", "removePlayerObserver");
        PlayerMessenger playermessenger = getInstance();
        if(playermessenger != null)
            playermessenger.deleteObserver(playerobserver);
    }

    private static PlayerMessenger getInstance()
    {
        if(instance == null)
            instance = new PlayerMessenger();
        return instance;
    }

    private static void notify(int i, Object obj, long l)
    {
        PlayerMessenger playermessenger = getInstance();
        if(playermessenger != null)
        {
            Object obj1 = new StringBuilder();
            ((StringBuilder) (obj1)).append("notify type=");
            ((StringBuilder) (obj1)).append(i);
            PxLog.d("PlayerMessenger", ((StringBuilder) (obj1)).toString());
            obj1 = Message.obtain();
            obj1.what = i;
            obj1.obj = new MessageParam(obj, l);
            playermessenger.setChanged();
            playermessenger.notifyObservers(obj1);
        }
    }

    public static final int NOTIFY_BML_SET_OUTPUT_POSITION = 10;
    public static final int NOTIFY_BML_SOUND = 11;
    public static final int NOTIFY_BML_UPDATE_VIDEO_POSITION = 9;
    public static final int NOTIFY_BROADCAST_MAIL_RECEIVED = 25;
    public static final int NOTIFY_BROWSER_KEYEVENT = 200;
    public static final int NOTIFY_BROWSER_LOAD_URL = 203;
    public static final int NOTIFY_BROWSER_STATE = 201;
    public static final int NOTIFY_BROWSER_UPDATE_VIDEO_POSITION = 202;
    public static final int NOTIFY_DISPLAY_CHANGED = 23;
    public static final int NOTIFY_DUALMONO_CHANGED = 24;
    public static final int NOTIFY_GET_RECOMMEND = 16;
    public static final int NOTIFY_RESET_TUNER = 21;
    public static final int NOTIFY_SET_COGNITIVE_SETTING = 4;
    public static final int NOTIFY_UPDATE_AUTO_MESSAGE = 1;
    public static final int REQUEST_APP_FINISH = 100;
    public static final int REQUEST_COGNITIVE_SETTING = 5;
    public static final int REQUEST_DESTROY_SURFACE = 8;
    public static final int REQUEST_EXISTENCE_THUMBNAIL_CACHE = 13;
    public static final int REQUEST_FORMAT_EXTERNAL_STOREGE = 26;
    public static final int REQUEST_GET_LNB_SETTINGS = 19;
    public static final int REQUEST_GET_OUTPUT_DEVICE_INFO = 22;
    public static final int REQUEST_GET_RECOMMEND = 15;
    public static final int REQUEST_GET_THUMBNAIL = 12;
    public static final int REQUEST_GET_VIEWING_POINT = 6;
    public static final int REQUEST_RESET_SURFACE = 7;
    public static final int REQUEST_SEND_LOG = 14;
    public static final int REQUEST_SET_LNB_SETTINGS = 20;
    public static final int REQUEST_SHOW_WINDOW = 17;
    public static final int REQUEST_START_AIRTUNER_SERVICE = 2;
    public static final int REQUEST_STOP_AIRTUNER_SERVICE = 3;
    public static final int REQUEST_TOGGLE_WINDOW = 18;
    private static final String TAG = "PlayerMessenger";
    static PlayerMessenger instance;

}
