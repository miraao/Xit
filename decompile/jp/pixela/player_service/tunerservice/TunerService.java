// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import jp.pixela.common.*;
import jp.pixela.pxlibs.utils.android.log.LoggerRTM;

// Referenced classes of package jp.pixela.player_service.tunerservice:
//            DeviceInfo, ControlInterface, BroadcastTypeT, AirTunerInfo, 
//            ReturnValue

public class TunerService
{
    public static final class StartMode extends Enum
    {

        public static StartMode valueOf(String s)
        {
            return (StartMode)Enum.valueOf(jp/pixela/player_service/tunerservice/TunerService$StartMode, s);
        }

        public static StartMode[] values()
        {
            return (StartMode[])$VALUES.clone();
        }

        public int getValue()
        {
            return mValue;
        }

        private static final StartMode $VALUES[];
        public static final StartMode CheckReservationState;
        public static final StartMode Default;
        public static final StartMode FormatExternalStorage;
        public static final StartMode OneTouchSelectChannel;
        public static final StartMode Reserve;
        public static final StartMode SelectBroadcastWave;
        public static final StartMode UpdateEPG;
        private final int mValue;

        static 
        {
            Default = new StartMode("Default", 0, 0);
            Reserve = new StartMode("Reserve", 1, 1);
            OneTouchSelectChannel = new StartMode("OneTouchSelectChannel", 2, 2);
            SelectBroadcastWave = new StartMode("SelectBroadcastWave", 3, 3);
            UpdateEPG = new StartMode("UpdateEPG", 4, 4);
            FormatExternalStorage = new StartMode("FormatExternalStorage", 5, 5);
            CheckReservationState = new StartMode("CheckReservationState", 6, 6);
            $VALUES = (new StartMode[] {
                Default, Reserve, OneTouchSelectChannel, SelectBroadcastWave, UpdateEPG, FormatExternalStorage, CheckReservationState
            });
        }

        private StartMode(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }


    public TunerService(Context context, ControlInterface controlinterface)
    {
        mTsInitialized = new AtomicBoolean(false);
        mAppConnect = false;
        mLockerObj = new Object();
        mWakeLock = null;
        mPairingKey = null;
        mContext = context;
        mControlInterface = controlinterface;
        mWakeLock = WakelockHelper.getInstance();
    }

    public static DeviceInfo CreateDefaultDeviceInfo()
    {
        PxLog.i(TAG, "getDeviceInfo");
        Object obj = TAG;
        Object obj1 = new StringBuilder();
        ((StringBuilder) (obj1)).append(">> url : ");
        ((StringBuilder) (obj1)).append("DeviceDesc.xml");
        PxLog.i(((String) (obj)), ((StringBuilder) (obj1)).toString());
        obj1 = TAG;
        obj = new StringBuilder();
        ((StringBuilder) (obj)).append(">> udn : ");
        ((StringBuilder) (obj)).append("uuid:ffffffff-ffff-ffff-ffff-ffffffffffff");
        PxLog.i(((String) (obj1)), ((StringBuilder) (obj)).toString());
        obj1 = TAG;
        obj = new StringBuilder();
        ((StringBuilder) (obj)).append(">> model name : ");
        ((StringBuilder) (obj)).append("PIX_DT195");
        PxLog.i(((String) (obj1)), ((StringBuilder) (obj)).toString());
        if("DeviceDesc.xml" != null && "uuid:ffffffff-ffff-ffff-ffff-ffffffffffff" != null)
        {
            DeviceInfo deviceinfo = new DeviceInfo("DeviceDesc.xml", "uuid:ffffffff-ffff-ffff-ffff-ffffffffffff");
            deviceinfo.setModelName("PIX_DT195");
            if("0000000000000000000000000000000000000000000000000000000000000000" != null)
            {
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                int j;
                int k;
                for(int i = 0; i < "0000000000000000000000000000000000000000000000000000000000000000".length(); bytearrayoutputstream.write((byte)(k * 16 + Integer.parseInt("0000000000000000000000000000000000000000000000000000000000000000".substring(j, i), 16))))
                {
                    j = i + 1;
                    k = Integer.parseInt("0000000000000000000000000000000000000000000000000000000000000000".substring(i, j), 16);
                    i += 2;
                }

                deviceinfo.setPairingKey(bytearrayoutputstream.toByteArray());
            }
            return deviceinfo;
        } else
        {
            return null;
        }
    }

    private void DeletePath(File file)
    {
        if(file == null)
            return;
        if(!file.exists())
            return;
        if(file.isDirectory())
        {
            File afile[] = file.listFiles();
            if(afile == null)
                return;
            for(int i = 0; i < afile.length; i++)
                DeletePath(afile[i]);

        }
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("DeletePath: path=");
        stringbuilder.append(file);
        PxLog.d(s, stringbuilder.toString());
        file.delete();
    }

    private String GetCacheDir(boolean flag)
    {
        if(mCacheFile == null)
            mCacheFile = mContext.getCacheDir();
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append(mContext.getCacheDir().getAbsolutePath());
        ((StringBuilder) (obj)).append("/");
        obj = ((StringBuilder) (obj)).toString();
        if(flag)
        {
            DeletePath(mCacheFile);
            mCacheFile = null;
            obj = GetCacheDir(false);
        } else
        {
            String s = TAG;
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("GetCacheDir: path=");
            stringbuilder.append(((String) (obj)));
            PxLog.d(s, stringbuilder.toString());
        }
        return ((String) (obj));
    }

    private String GetUnderDataDir(String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(mContext.getDir(s, 32768).getAbsolutePath());
        stringbuilder.append("/");
        s = stringbuilder.toString();
        String s1 = TAG;
        stringbuilder = new StringBuilder();
        stringbuilder.append("GetUnderDataDir: path=");
        stringbuilder.append(s);
        PxLog.d(s1, stringbuilder.toString());
        return s;
    }

    public static boolean existsInitFile(Context context)
    {
        return (new File(getInitFilePath(context))).exists();
    }

    public static String getInitFilePath(Context context)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(context.getFilesDir().getPath());
        stringbuilder.append("/");
        stringbuilder.append("settings");
        stringbuilder.append("/");
        stringbuilder.append("init.dat");
        return stringbuilder.toString();
    }

    public int Init(Message message)
    {
        PxLog.v(TAG, "Init in.");
        Object obj = message.getData();
        DeviceInfo deviceinfo = (DeviceInfo)((Bundle) (obj)).getParcelable("DeviceInfo");
        message = (ProductInfo)((Bundle) (obj)).getParcelable("ProductInfo");
        String s1 = mContext.getFilesDir().getPath();
        Object obj1 = new StringBuilder();
        ((StringBuilder) (obj1)).append(s1);
        ((StringBuilder) (obj1)).append("/");
        ((StringBuilder) (obj1)).append("database");
        obj1 = ((StringBuilder) (obj1)).toString();
        Object obj2 = new StringBuilder();
        ((StringBuilder) (obj2)).append(s1);
        ((StringBuilder) (obj2)).append("/");
        ((StringBuilder) (obj2)).append("settings");
        obj2 = ((StringBuilder) (obj2)).toString();
        String s2 = GetCacheDir(true);
        String s3 = GetUnderDataDir("nvram");
        s1 = RomSoundManager.GetRomSoundDirectoryPath(mContext);
        int i = ((Bundle) (obj)).getInt("BroadcastWave", 0);
        int j = ((Bundle) (obj)).getInt("ServiceId", 0);
        int k = ((Bundle) (obj)).getInt("StartMode", 0);
        int l = ((Bundle) (obj)).getInt("RemoconNumber", 0);
        obj = ((Bundle) (obj)).getString("FirstPlayObjectId", "");
        mControlInterface.loadLibrary();
        if(!((String) (obj)).isEmpty())
        {
            try
            {
                l = Integer.parseInt(((String) (obj)).substring(2));
            }
            catch(Exception exception)
            {
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append(LOG_HEAD);
                stringbuilder.append("e=");
                stringbuilder.append(exception);
                LoggerRTM.e(stringbuilder.toString(), new Object[0]);
                l = -1;
            }
            l = mControlInterface.initForFirstPlay(deviceinfo.GetSourceType(), ControlInterface.SegmentTypeT.TUNER_FIXED_FULLSEG, true, message, ((String) (obj1)), ((String) (obj2)), FontManager.GetFontPath(mContext, jp.pixela.common.FontManager.FontType.KAKU_GOTHIC), FontManager.GetFontPath(mContext, jp.pixela.common.FontManager.FontType.MARU_GOTHIC), s2, s3, s1, RootCAManager.GetRootCAPath(mContext), l);
        } else
        {
            l = mControlInterface.init(deviceinfo.GetSourceType(), ControlInterface.SegmentTypeT.TUNER_FIXED_FULLSEG, true, message, ((String) (obj1)), ((String) (obj2)), FontManager.GetFontPath(mContext, jp.pixela.common.FontManager.FontType.KAKU_GOTHIC), FontManager.GetFontPath(mContext, jp.pixela.common.FontManager.FontType.MARU_GOTHIC), s2, s3, s1, RootCAManager.GetRootCAPath(mContext), i, j, l, k);
        }
        if(l != 0)
        {
            String s = TAG;
            message = new StringBuilder();
            message.append("init failed, ret:");
            message.append(l);
            PxLog.d(s, message.toString());
            mTsInitialized.set(false);
            return -1;
        }
        mTsInitialized.set(mControlInterface.isInit());
        if(mWakeLock != null)
        {
            mWakeLock.wakeLock();
            PxLog.d(TAG, "Wake Lock");
        }
        return 0;
    }

    public boolean IsInit()
    {
        return mTsInitialized.get();
    }

    public Message SelectChannel(BroadcastTypeT broadcasttypet, int ai[])
    {
        mControlInterface.selectChannel(broadcasttypet, ai);
        return null;
    }

    public Message SelectChannelByDirectNumber(BroadcastTypeT broadcasttypet, int i)
    {
        mControlInterface.selectChannelByDirectNumber(broadcasttypet, i);
        return null;
    }

    public Message SelectLastChannel()
    {
        mControlInterface.selectLastChannel();
        return null;
    }

    public void Term()
    {
        if(mWakeLock != null)
        {
            mWakeLock.release();
            PxLog.d(TAG, "Wake Lock release");
        }
        mControlInterface.destroy();
        mTsInitialized.set(false);
    }

    public int Update(Message message)
    {
        int i;
label0:
        {
            PxLog.d(TAG, "Update in.");
            message = message.getData();
            i = 0;
            int j = message.getInt("BroadcastWave", 0);
            int k = message.getInt("ServiceId", 0);
            int l = message.getInt("StartMode", StartMode.Default.getValue());
            message = message.getString("FirstPlayObjectId", "");
            if(!message.isEmpty())
            {
                try
                {
                    i = Integer.parseInt(message.substring(2));
                }
                // Misplaced declaration of an exception variable
                catch(Message message)
                {
                    StringBuilder stringbuilder = new StringBuilder();
                    stringbuilder.append(LOG_HEAD);
                    stringbuilder.append("e=");
                    stringbuilder.append(message);
                    LoggerRTM.e(stringbuilder.toString(), new Object[0]);
                    i = -1;
                }
                i = mControlInterface.startPlayContent(i);
                break label0;
            }
            if(l == StartMode.Default.getValue())
            {
                if(j > 0 && k > 0)
                {
                    switch(j)
                    {
                    default:
                        message = TAG;
                        StringBuilder stringbuilder1 = new StringBuilder();
                        stringbuilder1.append("unknown broadcastWave. broadcastWave=");
                        stringbuilder1.append(j);
                        PxLog.e(message, stringbuilder1.toString());
                        return -1;

                    case 4: // '\004'
                    case 5: // '\005'
                        message = BroadcastTypeT.BROADCAST_TYPE_4K;
                        break;

                    case 3: // '\003'
                        message = BroadcastTypeT.BROADCAST_TYPE_CS;
                        break;

                    case 2: // '\002'
                        message = BroadcastTypeT.BROADCAST_TYPE_BS;
                        break;

                    case 1: // '\001'
                        message = BroadcastTypeT.BROADCAST_TYPE_TR;
                        break;
                    }
                    i = mControlInterface.selectChannel(message, new int[] {
                        k
                    });
                    break label0;
                }
            } else
            if(l == StartMode.Reserve.getValue())
                break label0;
            i = -1;
        }
        String s = TAG;
        message = new StringBuilder();
        message.append("Update out. ret=");
        message.append(i);
        PxLog.d(s, message.toString());
        return i;
    }

    public int getAreaInfo(boolean flag)
    {
        return mControlInterface.getAreaInfo(flag);
    }

    public AirTunerInfo getTunerInfo(boolean flag)
    {
        return mControlInterface.getAirTunerInfo(flag);
    }

    public ReturnValue getTunerTimeDiff()
    {
        return mControlInterface.getTunerTimeDiff();
    }

    private static final String LOG_HEAD;
    public static final String NAME_DBDIR = "database";
    public static final String NAME_INIT_SETTINGS_FILE = "init.dat";
    public static final String NAME_NVRAM = "nvram";
    public static final String NAME_SCREEN_OFF_FILE = "screenoff.dat";
    public static final String NAME_SETTINGSDIR = "settings";
    private static final String TAG = "TunerService";
    private static File mCacheFile;
    private boolean mAppConnect;
    Context mContext;
    ControlInterface mControlInterface;
    private Object mLockerObj;
    private byte mPairingKey[];
    private AtomicBoolean mTsInitialized;
    private WakelockHelper mWakeLock;

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/player_service/tunerservice/TunerService.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }
}
