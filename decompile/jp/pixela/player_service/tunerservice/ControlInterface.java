// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;

import android.content.Context;
import android.graphics.Rect;
import android.view.Surface;
import java.net.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Enumeration;
import jp.pixela.common.*;
import jp.pixela.pxlibs.utils.android.log.LoggerRTM;

// Referenced classes of package jp.pixela.player_service.tunerservice:
//            ReturnValue, AirTunerInfo, BroadcastTypeT, NetworkInterfaceModeT

public class ControlInterface
{
    public static final class SegmentTypeT extends Enum
    {

        public static SegmentTypeT fromValue(int i)
        {
            SegmentTypeT asegmenttypet[] = values();
            int j = asegmenttypet.length;
            for(int k = 0; k < j; k++)
            {
                SegmentTypeT segmenttypet = asegmenttypet[k];
                if(segmenttypet.toValue() == i)
                    return segmenttypet;
            }

            return TUNER_AUTO_SEGMENT_CHANGE;
        }

        public static SegmentTypeT valueOf(String s)
        {
            return (SegmentTypeT)Enum.valueOf(jp/pixela/player_service/tunerservice/ControlInterface$SegmentTypeT, s);
        }

        public static SegmentTypeT[] values()
        {
            return (SegmentTypeT[])$VALUES.clone();
        }

        public int toValue()
        {
            return mValue;
        }

        private static final SegmentTypeT $VALUES[];
        public static final SegmentTypeT FULL_SEG_FJDEC_TEST;
        public static final SegmentTypeT ONE_SEG_SOFTDEC_TEST;
        public static final SegmentTypeT TUNER_AUTO_SEGMENT_CHANGE;
        public static final SegmentTypeT TUNER_FIXED_FULLSEG;
        public static final SegmentTypeT TUNER_FIXED_ONESEG;
        private final int mValue;

        static 
        {
            TUNER_FIXED_ONESEG = new SegmentTypeT("TUNER_FIXED_ONESEG", 0, 0);
            TUNER_FIXED_FULLSEG = new SegmentTypeT("TUNER_FIXED_FULLSEG", 1, 1);
            TUNER_AUTO_SEGMENT_CHANGE = new SegmentTypeT("TUNER_AUTO_SEGMENT_CHANGE", 2, 2);
            ONE_SEG_SOFTDEC_TEST = new SegmentTypeT("ONE_SEG_SOFTDEC_TEST", 3, 3);
            FULL_SEG_FJDEC_TEST = new SegmentTypeT("FULL_SEG_FJDEC_TEST", 4, 4);
            $VALUES = (new SegmentTypeT[] {
                TUNER_FIXED_ONESEG, TUNER_FIXED_FULLSEG, TUNER_AUTO_SEGMENT_CHANGE, ONE_SEG_SOFTDEC_TEST, FULL_SEG_FJDEC_TEST
            });
        }

        private SegmentTypeT(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }

    public static final class SourceTypeT extends Enum
    {

        public static SourceTypeT fromValue(int i)
        {
            SourceTypeT asourcetypet[] = values();
            int j = asourcetypet.length;
            for(int k = 0; k < j; k++)
            {
                SourceTypeT sourcetypet = asourcetypet[k];
                if(sourcetypet.toValue() == i)
                    return sourcetypet;
            }

            return LOCAL_TUNER;
        }

        public static SourceTypeT valueOf(String s)
        {
            return (SourceTypeT)Enum.valueOf(jp/pixela/player_service/tunerservice/ControlInterface$SourceTypeT, s);
        }

        public static SourceTypeT[] values()
        {
            return (SourceTypeT[])$VALUES.clone();
        }

        public boolean isAirTunerType()
        {
            boolean flag;
            if(this != AIR_TUNER && this != TUNER_IF_PROXY)
                flag = false;
            else
                flag = true;
            return flag;
        }

        public int toValue()
        {
            return mValue;
        }

        private static final SourceTypeT $VALUES[];
        public static final SourceTypeT AIR_TUNER;
        public static final SourceTypeT FS;
        public static final SourceTypeT LOCAL_TUNER;
        public static final SourceTypeT NSM;
        public static final SourceTypeT SD;
        public static final SourceTypeT TUNER_IF_PROXY;
        private final int mValue;

        static 
        {
            AIR_TUNER = new SourceTypeT("AIR_TUNER", 0, 0);
            LOCAL_TUNER = new SourceTypeT("LOCAL_TUNER", 1, 1);
            SD = new SourceTypeT("SD", 2, 2);
            FS = new SourceTypeT("FS", 3, 3);
            NSM = new SourceTypeT("NSM", 4, 4);
            TUNER_IF_PROXY = new SourceTypeT("TUNER_IF_PROXY", 5, 5);
            $VALUES = (new SourceTypeT[] {
                AIR_TUNER, LOCAL_TUNER, SD, FS, NSM, TUNER_IF_PROXY
            });
        }

        private SourceTypeT(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }

    private static final class SurfaceType extends Enum
    {

        public static SurfaceType valueOf(String s)
        {
            return (SurfaceType)Enum.valueOf(jp/pixela/player_service/tunerservice/ControlInterface$SurfaceType, s);
        }

        public static SurfaceType[] values()
        {
            return (SurfaceType[])$VALUES.clone();
        }

        public int getValue()
        {
            return mValue;
        }

        private static final SurfaceType $VALUES[];
        public static final SurfaceType FULLSEG_BML;
        public static final SurfaceType FULLSEG_SUBTITLE;
        public static final SurfaceType FULLSEG_TEXTSUPER;
        public static final SurfaceType FULLSEG_VIDEO;
        public static final SurfaceType ONESEG_BML;
        public static final SurfaceType ONESEG_SUBTITLE;
        public static final SurfaceType ONESEG_VIDEO;
        public int mValue;

        static 
        {
            FULLSEG_VIDEO = new SurfaceType("FULLSEG_VIDEO", 0, 0);
            FULLSEG_BML = new SurfaceType("FULLSEG_BML", 1, 1);
            FULLSEG_SUBTITLE = new SurfaceType("FULLSEG_SUBTITLE", 2, 2);
            FULLSEG_TEXTSUPER = new SurfaceType("FULLSEG_TEXTSUPER", 3, 3);
            ONESEG_SUBTITLE = new SurfaceType("ONESEG_SUBTITLE", 4, 4);
            ONESEG_VIDEO = new SurfaceType("ONESEG_VIDEO", 5, 5);
            ONESEG_BML = new SurfaceType("ONESEG_BML", 6, 6);
            $VALUES = (new SurfaceType[] {
                FULLSEG_VIDEO, FULLSEG_BML, FULLSEG_SUBTITLE, FULLSEG_TEXTSUPER, ONESEG_SUBTITLE, ONESEG_VIDEO, ONESEG_BML
            });
        }

        private SurfaceType(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }


    public ControlInterface(Context context)
    {
        mAudioHwSyncForSession = 0;
        mContext = null;
        NtInstance_ = 0L;
        channelScanRunning_ = false;
        uuidSet_ = false;
        mContext = context;
        CustomAudioTrack.setContext(context);
        mAudioHwSyncForSession = CustomAudioTrack.getAudioHwSyncForSession();
    }

    private native void NotifyLNBShortOccurNt(long l);

    private native void ResponseBooleanNt(long l, int i, boolean flag);

    private native void ResponseLongNt(long l, int i, long l1);

    private native void ResponseObjectNt(long l, int i, Object obj);

    private native void ResponseStringNt(long l, int i, String s);

    private native int checkReservationRecordingStartAtTimeNt(long l, long l1, long l2, boolean aflag[]);

    private native int connectNt(long l, String s);

    private native int destroyNt(long l);

    private native int destroySurfaceNt(long l, int i);

    private native int disconnectNt(long l);

    private native int doUpdateCacheNt(long l);

    private native int doUpdateCacheWithTargetNt(long l, int i);

    private native int formatExternalStorageNt(long l);

    private native int getAirTunerAreaInfoNt(long l, boolean flag);

    private native AirTunerInfo getAirTunerInfoNt(long l, boolean flag);

    private native int getChannelListAndUpdateDBNt(long l, BroadcastTypeT broadcasttypet);

    private native ReturnValue getChannelListNt(long l);

    private int getNetworkInfo(byte abyte0[], byte abyte1[])
    {
        Enumeration enumeration = NetworkInterface.getNetworkInterfaces();
        if(enumeration == null)
            return -1;
_L2:
        NetworkInterface networkinterface;
        Enumeration enumeration1;
        if(!enumeration.hasMoreElements())
            break MISSING_BLOCK_LABEL_232;
        networkinterface = (NetworkInterface)enumeration.nextElement();
        enumeration1 = networkinterface.getInetAddresses();
_L3:
        if(!enumeration1.hasMoreElements()) goto _L2; else goto _L1
_L1:
        byte abyte2[] = ((InetAddress)enumeration1.nextElement()).getAddress();
        if(abyte2.length == 4 && ((abyte2[0] & 0xff) != 127 || (abyte2[1] & 0xff) != 0 || (abyte2[2] & 0xff) != 0 || (abyte2[3] & 0xff) != 1) && ((abyte2[0] & 0xff) != 0 || (abyte2[1] & 0xff) != 0 || (abyte2[2] & 0xff) != 0 || (abyte2[3] & 0xff) != 0)) goto _L4; else goto _L3
_L4:
        System.arraycopy(abyte2, 0, abyte0, 0, 4);
        System.arraycopy(networkinterface.getHardwareAddress(), 0, abyte1, 0, 6);
        return 0;
        abyte0;
        abyte1 = new StringBuilder();
        abyte1.append(LOG_HEAD);
        abyte1.append("e=");
        abyte1.append(abyte0);
        LoggerRTM.e(abyte1.toString(), new Object[0]);
        return -1;
    }

    private native ReturnValue getTunerTimeDiffNt(long l);

    private native ReturnValue getXmppAccountNt(long l);

    private native long initNt(SourceTypeT sourcetypet, SegmentTypeT segmenttypet, boolean flag, ProductInfo productinfo, int i);

    private native long initWithDirectoriesForFirstPlayNt(SourceTypeT sourcetypet, SegmentTypeT segmenttypet, boolean flag, ProductInfo productinfo, int i, String s, String s1, 
            String s2, String s3, String s4, String s5, String s6, String s7, int j);

    private native long initWithDirectoriesNt(SourceTypeT sourcetypet, SegmentTypeT segmenttypet, boolean flag, ProductInfo productinfo, int i, String s, String s1, 
            String s2, String s3, String s4, String s5, String s6, String s7, int j, 
            int k, int l, int i1);

    private native void notifyInitializeSettingCompleteNt(long l);

    private native int notifyRamAudioStoppedNt(long l);

    private native int notifyTunerStoppedNt(long l, int i);

    private native int notifyUpdateSystemTimeNt(long l);

    private native int requestPairingAuthNt(long l, byte abyte0[]);

    private native byte[] requestPairingKeyNt(long l);

    private native int selectChannelByDirectNumberNt(long l, BroadcastTypeT broadcasttypet, int i);

    private native int selectChannelNt(long l, BroadcastTypeT broadcasttypet, int ai[]);

    private native int selectLastChannelNt(long l);

    private native int setClientIDNt(long l, String s);

    private native int setCommunicationInfoNt(long l, byte abyte0[], int i, int j, int k, int i1, 
            int j1, int k1, byte abyte1[], String s, String s1, NetworkInterfaceModeT networkinterfacemodet);

    private native int setCommunicationInfoNt(long l, byte abyte0[], byte abyte1[], String s, String s1);

    private native int setMuteNt(long l, boolean flag);

    private native int setVideoOutputPositionNt(long l, int i, Surface surface, Rect rect, Rect rect1, Rect rect2, 
            int j);

    private native int startPlayContentNt(long l, int i);

    private native int stopPreviewNt(long l);

    public int NotifyLNBShortOccur()
    {
        if(NtInstance_ == 0L)
        {
            return -1;
        } else
        {
            NotifyLNBShortOccurNt(NtInstance_);
            return 0;
        }
    }

    public int NotifyRamAudioStopped()
    {
        if(NtInstance_ == 0L)
            return -1;
        else
            return notifyRamAudioStoppedNt(NtInstance_);
    }

    public int NotifyTunerStopped(int i)
    {
        if(NtInstance_ == 0L)
            return -1;
        else
            return notifyTunerStoppedNt(NtInstance_, i);
    }

    public void ResponseBoolean(long l, int i, long l1)
    {
        ResponseLongNt(l, i, l1);
    }

    public void ResponseBoolean(long l, int i, boolean flag)
    {
        ResponseBooleanNt(l, i, flag);
    }

    public void ResponseObject(long l, int i, Object obj)
    {
        ResponseObjectNt(l, i, obj);
    }

    public void ResponseString(long l, int i, String s)
    {
        ResponseStringNt(l, i, s);
    }

    public int checkReservationRecordingStartAtTime(long l, long l1, boolean aflag[])
    {
        if(NtInstance_ == 0L)
            return -1;
        else
            return checkReservationRecordingStartAtTimeNt(NtInstance_, l, l1, aflag);
    }

    public int connect(String s)
    {
        String s1 = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("connect: deviceURI=");
        stringbuilder.append(s);
        PxLog.d(s1, stringbuilder.toString());
        if(NtInstance_ == 0L)
            return -1;
        if(!sourceType_.isAirTunerType())
            return -1;
        if(!uuidSet_)
            setServerUUID(new byte[16]);
        return connectNt(NtInstance_, s);
    }

    public void destroy()
    {
        PxLog.d(TAG, "destroy");
        if(NtInstance_ == 0L)
            return;
        SurfaceType asurfacetype[] = SurfaceType.values();
        int i = asurfacetype.length;
        for(int j = 0; j < i; j++)
        {
            SurfaceType surfacetype = asurfacetype[j];
            destroySurfaceNt(NtInstance_, surfacetype.getValue());
        }

        destroyNt(NtInstance_);
        NtInstance_ = 0L;
    }

    public int disconnect()
    {
        PxLog.d(TAG, "disconnect");
        if(NtInstance_ == 0L)
            return -1;
        if(!sourceType_.isAirTunerType())
            return -1;
        else
            return disconnectNt(NtInstance_);
    }

    public int doUpdateCache()
    {
        if(NtInstance_ == 0L)
            return -1;
        else
            return doUpdateCacheNt(NtInstance_);
    }

    public int doUpdateCacheWithTarget(int i)
    {
        if(NtInstance_ == 0L)
            return -1;
        else
            return doUpdateCacheWithTargetNt(NtInstance_, i);
    }

    public int formatExternalStorage()
    {
        if(NtInstance_ == 0L)
            return -1;
        else
            return formatExternalStorageNt(NtInstance_);
    }

    public AirTunerInfo getAirTunerInfo()
    {
        return getAirTunerInfo(false);
    }

    public AirTunerInfo getAirTunerInfo(boolean flag)
    {
        if(NtInstance_ == 0L)
            return null;
        if(!sourceType_.isAirTunerType())
            return null;
        else
            return getAirTunerInfoNt(NtInstance_, flag);
    }

    public int getAreaInfo(boolean flag)
    {
        if(NtInstance_ == 0L)
            return -1;
        else
            return getAirTunerAreaInfoNt(NtInstance_, flag);
    }

    public ReturnValue getChannelList()
    {
        if(NtInstance_ == 0L)
        {
            ReturnValue returnvalue = new ReturnValue();
            returnvalue.setError(-1);
            return returnvalue;
        } else
        {
            return getChannelListNt(NtInstance_);
        }
    }

    public ReturnValue getTunerTimeDiff()
    {
        if(NtInstance_ == 0L)
            return null;
        else
            return getTunerTimeDiffNt(NtInstance_);
    }

    public ReturnValue getXmppAccount()
    {
        if(NtInstance_ == 0L)
            return null;
        else
            return getXmppAccountNt(NtInstance_);
    }

    public int init()
    {
        return init(SourceTypeT.TUNER_IF_PROXY, SegmentTypeT.TUNER_FIXED_FULLSEG, false, null, "", "", "", "", "", "", "", "", 0, 0, 0, 0);
    }

    public int init(SourceTypeT sourcetypet, SegmentTypeT segmenttypet, boolean flag, ProductInfo productinfo, String s, String s1, String s2, 
            String s3, String s4, String s5, String s6, String s7, int i, int j, 
            int k, int l)
    {
        String s8 = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("init: sourceType=");
        stringbuilder.append(sourcetypet);
        stringbuilder.append(", segmentType=");
        stringbuilder.append(segmenttypet);
        stringbuilder.append(", isFullsegBmlEnabled=");
        stringbuilder.append(flag);
        PxLog.d(s8, stringbuilder.toString());
        if(productinfo != null)
        {
            String s9 = TAG;
            StringBuilder stringbuilder1 = new StringBuilder();
            stringbuilder1.append("init: producttype=");
            stringbuilder1.append(productinfo.getProductType());
            stringbuilder1.append(", recordable=");
            stringbuilder1.append(productinfo.isRecordable());
            PxLog.d(s9, stringbuilder1.toString());
        }
        if(NtInstance_ == 0L)
            NtInstance_ = initWithDirectoriesNt(sourcetypet, segmenttypet, flag, productinfo, mAudioHwSyncForSession, s, s1, s2, s3, s4, s5, s6, s7, i, j, k, l);
        segmenttypet = this;
        productinfo = String.format("NtInstance_=0x%08x", new Object[] {
            Long.valueOf(((ControlInterface) (segmenttypet)).NtInstance_)
        });
        PxLog.d(TAG, productinfo);
        if(((ControlInterface) (segmenttypet)).NtInstance_ != 0L)
        {
            segmenttypet.sourceType_ = sourcetypet;
            return 0;
        } else
        {
            return -1;
        }
    }

    public int initForFirstPlay(SourceTypeT sourcetypet, SegmentTypeT segmenttypet, boolean flag, ProductInfo productinfo, String s, String s1, String s2, 
            String s3, String s4, String s5, String s6, String s7, int i)
    {
        String s8 = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("initForFirstPlay: sourceType=");
        stringbuilder.append(sourcetypet);
        stringbuilder.append(", segmentType=");
        stringbuilder.append(segmenttypet);
        stringbuilder.append(", isFullsegBmlEnabled=");
        stringbuilder.append(flag);
        PxLog.d(s8, stringbuilder.toString());
        if(productinfo != null)
        {
            String s9 = TAG;
            StringBuilder stringbuilder1 = new StringBuilder();
            stringbuilder1.append("init: producttype=");
            stringbuilder1.append(productinfo.getProductType());
            stringbuilder1.append(", recordable=");
            stringbuilder1.append(productinfo.isRecordable());
            PxLog.d(s9, stringbuilder1.toString());
        }
        if(NtInstance_ == 0L)
            NtInstance_ = initWithDirectoriesForFirstPlayNt(sourcetypet, segmenttypet, flag, productinfo, mAudioHwSyncForSession, s, s1, s2, s3, s4, s5, s6, s7, i);
        segmenttypet = String.format("NtInstance_=0x%08x", new Object[] {
            Long.valueOf(NtInstance_)
        });
        PxLog.d(TAG, segmenttypet);
        if(NtInstance_ != 0L)
        {
            sourceType_ = sourcetypet;
            return 0;
        } else
        {
            return -1;
        }
    }

    public boolean isInit()
    {
        boolean flag;
        if(NtInstance_ != 0L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public int loadLibrary()
    {
        System.loadLibrary("stationtv_lt_stb_stream");
        System.loadLibrary("stationtv_lt_stb_control");
        System.loadLibrary("player_serviceAT");
        return 0;
    }

    public int notifyInitializeSettingComplete()
    {
        PxLog.v(TAG, "notifyResetTunerComplete.");
        if(NtInstance_ == 0L)
        {
            return -1;
        } else
        {
            notifyInitializeSettingCompleteNt(NtInstance_);
            return 0;
        }
    }

    public int notifyUpdateSystemTime()
    {
        if(NtInstance_ == 0L)
            return -1;
        else
            return notifyUpdateSystemTimeNt(NtInstance_);
    }

    public int requestPairingAuth(byte abyte0[])
    {
        PxLog.d(TAG, "requestPairingAuth");
        if(NtInstance_ == 0L)
            return -1;
        if(!sourceType_.isAirTunerType())
            return -1;
        else
            return requestPairingAuthNt(NtInstance_, abyte0);
    }

    public byte[] requestPairingKey()
    {
        PxLog.d(TAG, "requestPairingKey");
        if(NtInstance_ == 0L)
            return null;
        if(!sourceType_.isAirTunerType())
            return null;
        else
            return requestPairingKeyNt(NtInstance_);
    }

    public int selectChannel(BroadcastTypeT broadcasttypet, int ai[])
    {
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("selectChannel:broadcastType=");
        stringbuilder.append(broadcasttypet);
        stringbuilder.append(", serviceIds=");
        stringbuilder.append(Arrays.toString(ai));
        PxLog.d(s, stringbuilder.toString());
        if(NtInstance_ == 0L)
            return -1;
        else
            return selectChannelNt(NtInstance_, broadcasttypet, ai);
    }

    public int selectChannelByDirectNumber(BroadcastTypeT broadcasttypet, int i)
    {
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("selectChannelByDirectNumber:broadcastType=");
        stringbuilder.append(broadcasttypet);
        stringbuilder.append(", chNumber=");
        stringbuilder.append(i);
        PxLog.d(s, stringbuilder.toString());
        if(NtInstance_ == 0L)
            return -1;
        else
            return selectChannelByDirectNumberNt(NtInstance_, broadcasttypet, i);
    }

    public int selectLastChannel()
    {
        PxLog.d(TAG, "selectLastChannel");
        if(NtInstance_ == 0L)
            return -1;
        else
            return selectLastChannelNt(NtInstance_);
    }

    public int setClientID(String s)
    {
        if(NtInstance_ == 0L)
            return 0;
        else
            return setClientIDNt(NtInstance_, s);
    }

    public int setMute(boolean flag)
    {
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("setMute mute=");
        stringbuilder.append(flag);
        PxLog.v(s, stringbuilder.toString());
        if(NtInstance_ == 0L)
            return -1;
        else
            return setMuteNt(NtInstance_, flag);
    }

    public int setServerUUID(int i, int j, int k, int l, int i1, int j1, byte abyte0[], 
            byte abyte1[], byte abyte2[], NetworkInterfaceModeT networkinterfacemodet)
    {
        if(NtInstance_ == 0L)
            return -1;
        if(!sourceType_.isAirTunerType())
            return -1;
        uuidSet_ = true;
        abyte0 = new String(abyte0);
        if(abyte1 != null && abyte2 != null)
        {
            String s = String.format("%02x%02x%02x%02x%02x%02x", new Object[] {
                Byte.valueOf(abyte2[0]), Byte.valueOf(abyte2[1]), Byte.valueOf(abyte2[2]), Byte.valueOf(abyte2[3]), Byte.valueOf(abyte2[4]), Byte.valueOf(abyte2[5])
            });
            return setCommunicationInfoNt(NtInstance_, abyte1, i, j, k, l, i1, j1, abyte2, abyte0, s, networkinterfacemodet);
        }
        byte abyte3[] = new byte[4];
        abyte2 = new byte[16];
        if(getNetworkInfo(abyte3, abyte2) != 0)
        {
            return -1;
        } else
        {
            abyte1 = String.format("%02x%02x%02x%02x%02x%02x", new Object[] {
                Byte.valueOf(abyte2[0]), Byte.valueOf(abyte2[1]), Byte.valueOf(abyte2[2]), Byte.valueOf(abyte2[3]), Byte.valueOf(abyte2[4]), Byte.valueOf(abyte2[5])
            });
            return setCommunicationInfoNt(NtInstance_, abyte3, i, j, k, l, i1, j1, abyte2, abyte0, abyte1, networkinterfacemodet);
        }
    }

    public int setServerUUID(byte abyte0[])
    {
        PxLog.d(TAG, "setServerUUID");
        if(NtInstance_ == 0L)
            return -1;
        if(!sourceType_.isAirTunerType())
            return -1;
        uuidSet_ = true;
        byte abyte1[] = new byte[4];
        byte abyte2[] = new byte[16];
        abyte0 = new String(abyte0, Charset.defaultCharset());
        if(getNetworkInfo(abyte1, abyte2) != 0)
            return -1;
        else
            return setCommunicationInfoNt(NtInstance_, abyte1, abyte2, abyte0, "android_id");
    }

    public int setVideoOutputPosition(int i, Surface surface, Rect rect, Rect rect1, Rect rect2, int j)
    {
        Object obj = TAG;
        Object obj1 = new StringBuilder();
        ((StringBuilder) (obj1)).append("setVideoOutputPosition: angle=");
        ((StringBuilder) (obj1)).append(j);
        PxLog.d(((String) (obj)), ((StringBuilder) (obj1)).toString());
        if(NtInstance_ == 0L)
            return -1;
        obj1 = TAG;
        obj = new StringBuilder();
        ((StringBuilder) (obj)).append("setVideoOutputPosition: surface=");
        ((StringBuilder) (obj)).append(surface);
        PxLog.d(((String) (obj1)), ((StringBuilder) (obj)).toString());
        if(surface != null && rect1 != null && rect2 != null && rect != null)
            return setVideoOutputPositionNt(NtInstance_, i, surface, rect, rect1, rect2, j);
        else
            return destroySurfaceNt(NtInstance_, i);
    }

    public int startPlayContent(int i)
    {
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("startPlayContent.");
        stringbuilder.append(i);
        PxLog.v(s, stringbuilder.toString());
        if(NtInstance_ == 0L)
            return -1;
        else
            return startPlayContentNt(NtInstance_, i);
    }

    public int stopPreview()
    {
        PxLog.d(TAG, "stopPreview");
        if(NtInstance_ == 0L)
            return -1;
        else
            return stopPreviewNt(NtInstance_);
    }

    public int updateChannelsDB(BroadcastTypeT broadcasttypet)
    {
        if(NtInstance_ == 0L)
            return -1;
        else
            return getChannelListAndUpdateDBNt(NtInstance_, broadcasttypet);
    }

    private static final String LOG_HEAD;
    private static final String TAG = "ControlInterface";
    public static final int UpdateTargetEpg = 0;
    public static final int UpdateTargetRecordContent = 5;
    public static final int UpdateTargetReservation = 6;
    private long NtInstance_;
    private boolean channelScanRunning_;
    private int mAudioHwSyncForSession;
    private Context mContext;
    private SourceTypeT sourceType_;
    private boolean uuidSet_;

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/player_service/tunerservice/ControlInterface.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }
}
