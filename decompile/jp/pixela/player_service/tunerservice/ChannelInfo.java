// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;

import java.util.Arrays;
import jp.pixela.common.PxLog;

// Referenced classes of package jp.pixela.player_service.tunerservice:
//            BroadcastTypeT

public class ChannelInfo
{
    public static final class SelectChannelType_T extends Enum
    {

        public static SelectChannelType_T fromValue(int i)
        {
            SelectChannelType_T aselectchanneltype_t[] = values();
            int j = aselectchanneltype_t.length;
            for(int k = 0; k < j; k++)
            {
                SelectChannelType_T selectchanneltype_t = aselectchanneltype_t[k];
                if(selectchanneltype_t.toValue() == i)
                    return selectchanneltype_t;
            }

            return PhysicalChannel;
        }

        public static SelectChannelType_T valueOf(String s)
        {
            return (SelectChannelType_T)Enum.valueOf(jp/pixela/player_service/tunerservice/ChannelInfo$SelectChannelType_T, s);
        }

        public static SelectChannelType_T[] values()
        {
            return (SelectChannelType_T[])$VALUES.clone();
        }

        public int toValue()
        {
            return mValue;
        }

        private static final SelectChannelType_T $VALUES[];
        public static final SelectChannelType_T PhysicalChannel;
        public static final SelectChannelType_T PhysicalFrequency;
        private int mValue;

        static 
        {
            PhysicalChannel = new SelectChannelType_T("PhysicalChannel", 0, 1);
            PhysicalFrequency = new SelectChannelType_T("PhysicalFrequency", 1, 2);
            $VALUES = (new SelectChannelType_T[] {
                PhysicalChannel, PhysicalFrequency
            });
        }

        private SelectChannelType_T(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }

    public static final class serviceType_t extends Enum
    {

        public static serviceType_t valueOf(String s)
        {
            return (serviceType_t)Enum.valueOf(jp/pixela/player_service/tunerservice/ChannelInfo$serviceType_t, s);
        }

        public static serviceType_t[] values()
        {
            return (serviceType_t[])$VALUES.clone();
        }

        public int toValue()
        {
            return mValue;
        }

        private static final serviceType_t $VALUES[];
        public static final serviceType_t DATA_SERVICE;
        public static final serviceType_t DIGITAL_TV_SERVICE;
        private int mValue;

        static 
        {
            DIGITAL_TV_SERVICE = new serviceType_t("DIGITAL_TV_SERVICE", 0, 1);
            DATA_SERVICE = new serviceType_t("DATA_SERVICE", 1, 192);
            $VALUES = (new serviceType_t[] {
                DIGITAL_TV_SERVICE, DATA_SERVICE
            });
        }

        private serviceType_t(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }


    public ChannelInfo()
    {
        Id_ = null;
        PhysicalFrequency_ = 0;
        PhysicalChannelNumber_ = null;
        ServiceName_ = null;
        StationName_ = null;
        ChannelNumber_ = 0;
        ChannelLogoPicturePath_ = null;
        ChannelLogoPictureSize_ = 0;
        NetworkId_ = 0;
        TsId_ = 0;
        ServiceId_ = 0;
        ServiceIdSub_ = 0;
        isTunerModeOnesegOnly_ = false;
        ServiceType_ = 0;
        AffiliationId_ = new byte[0];
        Available_ = 0;
        ChannelScanFoundStatus_ = 0;
        UPnPdeviceURI_ = null;
        RemoteProhibit_ = 0;
    }

    public static boolean getIsOneseg(int i)
    {
        return (i & 0x180) >> 7 == 3;
    }

    public static boolean getIsOneseg(String s)
    {
        if(s == null)
            return false;
        s = s.split("\\.");
        if(s.length != 4)
            return false;
        return (Integer.parseInt(s[3], 16) & Integer.decode("0x180").intValue()) >> 7 == 3;
    }

    public byte[] getAffiliationId()
    {
        return AffiliationId_;
    }

    public String getAffiliationIdStr()
    {
        int i;
        if(AffiliationId_ != null)
            i = AffiliationId_.length;
        else
            i = 0;
        String s = "";
        for(int j = 0; j < i; j++)
            if(j % 6 != 0)
                s = s.concat(String.format("%02x", new Object[] {
                    Byte.valueOf(AffiliationId_[j])
                }));
            else
                s = s.concat(String.format(",%02x", new Object[] {
                    Byte.valueOf(AffiliationId_[j])
                }));

        return s;
    }

    public int getAvailable()
    {
        return Available_;
    }

    public BroadcastTypeT getBroadcastType()
    {
        return BroadcastType_;
    }

    public String getChannelLogoPicturePath()
    {
        return ChannelLogoPicturePath_;
    }

    public int getChannelLogoPictureSize()
    {
        return ChannelLogoPictureSize_;
    }

    public int getChannelNumber()
    {
        return ChannelNumber_;
    }

    public int getChannelScanFoundStatus()
    {
        return ChannelScanFoundStatus_;
    }

    public String getId()
    {
        return Id_;
    }

    public boolean getIsOneseg()
    {
        return getIsOneseg(ServiceId_);
    }

    public int getNetworkId()
    {
        return NetworkId_;
    }

    public String getPhysicalChannelNumber()
    {
        return PhysicalChannelNumber_;
    }

    public int getPhysicalFrequency()
    {
        return PhysicalFrequency_;
    }

    public int getRemoteProhibit()
    {
        return RemoteProhibit_;
    }

    public int getServiceId()
    {
        return ServiceId_;
    }

    public int getServiceIdSub()
    {
        return ServiceIdSub_;
    }

    public String getServiceName()
    {
        return ServiceName_;
    }

    public int getServiceType()
    {
        return ServiceType_;
    }

    public String getStationName()
    {
        return StationName_;
    }

    public int getTsId()
    {
        return TsId_;
    }

    public String getUPnPdeviceURI()
    {
        return UPnPdeviceURI_;
    }

    public boolean isSameTo(ChannelInfo channelinfo)
    {
        if(channelinfo == null)
        {
            PxLog.d("ChannelInfo", "isSameTo(); arg is null");
            return false;
        }
        if(Id_ != null && !Id_.equals(channelinfo.getId()))
        {
            PxLog.d("ChannelInfo", "isSameTo(); Id_ is not same");
            return false;
        }
        boolean flag;
        if(Id_ == null)
            flag = true;
        else
            flag = false;
        boolean flag1;
        if(channelinfo.getId() == null)
            flag1 = true;
        else
            flag1 = false;
        if(flag != flag1)
        {
            PxLog.d("ChannelInfo", "isSameTo(); Id_ is not same, some is null");
            return false;
        }
        if(PhysicalFrequency_ != channelinfo.getPhysicalFrequency())
        {
            PxLog.d("ChannelInfo", "isSameTo(); PhysicalFrequency_ is not same");
            return false;
        }
        if(PhysicalChannelNumber_ != null && !PhysicalChannelNumber_.equals(channelinfo.getPhysicalChannelNumber()))
        {
            PxLog.d("ChannelInfo", "isSameTo(); PhysicalChannelNumber_ is not same");
            return false;
        }
        if(PhysicalChannelNumber_ == null)
            flag = true;
        else
            flag = false;
        if(channelinfo.getPhysicalChannelNumber() == null)
            flag1 = true;
        else
            flag1 = false;
        if(flag != flag1)
        {
            PxLog.d("ChannelInfo", "isSameTo(); PhysicalChannelNumber_ is not same, found null");
            return false;
        }
        if(BroadcastType_ != channelinfo.getBroadcastType())
        {
            PxLog.d("ChannelInfo", "isSameTo(); BroadcastType_ is not same");
            return false;
        }
        if(ServiceName_ != null && !ServiceName_.equals(channelinfo.getServiceName()))
        {
            PxLog.d("ChannelInfo", "isSameTo(); ServiceName_ is not same");
            return false;
        }
        if(ServiceName_ == null)
            flag = true;
        else
            flag = false;
        if(channelinfo.getServiceName() == null)
            flag1 = true;
        else
            flag1 = false;
        if(flag != flag1)
        {
            PxLog.d("ChannelInfo", "isSameTo(); ServiceName_ is not same, found null");
            return false;
        }
        if(StationName_ != null && !StationName_.equals(channelinfo.getStationName()))
        {
            PxLog.d("ChannelInfo", "isSameTo(); StationName_ is not same");
            return false;
        }
        if(StationName_ == null)
            flag = true;
        else
            flag = false;
        if(channelinfo.getStationName() == null)
            flag1 = true;
        else
            flag1 = false;
        if(flag != flag1)
        {
            PxLog.d("ChannelInfo", "isSameTo(); StationName_ is not same, found null");
            return false;
        }
        if(ChannelNumber_ != channelinfo.getChannelNumber())
        {
            PxLog.d("ChannelInfo", "isSameTo(); ChannelNumber_ is not same");
            return false;
        }
        if(ChannelLogoPicturePath_ != null && !ChannelLogoPicturePath_.equals(channelinfo.getChannelLogoPicturePath()))
        {
            PxLog.d("ChannelInfo", "isSameTo(); ChannelLogoPicturePath_ is not same");
            return false;
        }
        if(ChannelLogoPicturePath_ == null)
            flag = true;
        else
            flag = false;
        if(channelinfo.getChannelLogoPicturePath() == null)
            flag1 = true;
        else
            flag1 = false;
        if(flag != flag1)
        {
            PxLog.d("ChannelInfo", "isSameTo(); ChannelLogoPicturePath_ is not same, found null");
            return false;
        }
        if(ChannelLogoPictureSize_ != 0 && channelinfo.getChannelLogoPictureSize() != 0 && ChannelLogoPictureSize_ != channelinfo.getChannelLogoPictureSize())
        {
            PxLog.d("ChannelInfo", "isSameTo(); ChannelLogoPictureSize_ is not same");
            return false;
        }
        if(NetworkId_ != channelinfo.getNetworkId())
        {
            PxLog.d("ChannelInfo", "isSameTo(); NetworkId_ is not same");
            return false;
        }
        if(TsId_ != channelinfo.getTsId())
        {
            PxLog.d("ChannelInfo", "isSameTo(); TsId_ is not same");
            return false;
        }
        if(ServiceId_ != channelinfo.getServiceId())
        {
            PxLog.d("ChannelInfo", "isSameTo(); ServiceId_ is not same");
            return false;
        }
        if(ServiceIdSub_ != channelinfo.getServiceIdSub())
        {
            PxLog.d("ChannelInfo", "isSameTo(); ServiceIdSub_ is not same");
            return false;
        }
        if(ServiceType_ != channelinfo.getServiceType())
        {
            PxLog.d("ChannelInfo", "isSameTo(); ServiceType_ is not same");
            return false;
        }
        if(!Arrays.equals(AffiliationId_, channelinfo.getAffiliationId()))
        {
            PxLog.d("ChannelInfo", "isSameTo(); AffiliationId_ is not same");
            return false;
        }
        if(ChannelScanFoundStatus_ != -1 && channelinfo.getChannelScanFoundStatus() != -1 && ChannelScanFoundStatus_ != channelinfo.getChannelScanFoundStatus())
        {
            PxLog.d("ChannelInfo", "isSameTo(); ChannelScanFoundStatus_ is not same");
            return false;
        }
        if(UPnPdeviceURI_ != null && !UPnPdeviceURI_.equals(channelinfo.getUPnPdeviceURI()))
        {
            PxLog.d("ChannelInfo", "isSameTo(); UPnPdeviceURI_ is not same");
            return false;
        }
        if(UPnPdeviceURI_ == null)
            flag = true;
        else
            flag = false;
        if(channelinfo.getUPnPdeviceURI() == null)
            flag1 = true;
        else
            flag1 = false;
        if(flag != flag1)
        {
            PxLog.d("ChannelInfo", "isSameTo(); UPnPdeviceURI_ is not same");
            return false;
        } else
        {
            PxLog.d("ChannelInfo", "isSameTo(); all OK");
            return true;
        }
    }

    public boolean isTunerModeOnesegOnly()
    {
        return isTunerModeOnesegOnly_;
    }

    public void setAffiliationId(byte abyte0[])
    {
        AffiliationId_ = abyte0;
    }

    public void setAffiliationIdStr(String s)
    {
        int i = 0;
        if(s == null)
        {
            AffiliationId_ = new byte[0];
            return;
        }
        s = s.replaceAll(",", "");
        int j = s.length();
        AffiliationId_ = new byte[j / 2];
        int k;
        for(; i < j; i = k)
        {
            k = i + 2;
            String s1 = s.substring(i, k);
            AffiliationId_[i / 2] = Byte.parseByte(s1, 16);
        }

    }

    public void setAvailable(int i)
    {
        Available_ = i;
    }

    public void setBroadcastType(BroadcastTypeT broadcasttypet)
    {
        BroadcastType_ = broadcasttypet;
    }

    public void setChannelLogoPicturePath(String s)
    {
        ChannelLogoPicturePath_ = s;
    }

    public void setChannelLogoPictureSize(int i)
    {
        ChannelLogoPictureSize_ = i;
    }

    public void setChannelNumber(int i)
    {
        ChannelNumber_ = i;
    }

    public void setChannelScanFoundStatus(int i)
    {
        ChannelScanFoundStatus_ = i;
    }

    public void setId(String s)
    {
        Id_ = s;
    }

    public void setNetworkId(int i)
    {
        NetworkId_ = i;
    }

    public void setPhysicalChannelNumber(String s)
    {
        PhysicalChannelNumber_ = s;
    }

    public void setPhysicalFrequency(int i)
    {
        PhysicalFrequency_ = i;
    }

    public void setRemoteProhibit(int i)
    {
        RemoteProhibit_ = i;
    }

    public void setServiceId(int i)
    {
        ServiceId_ = i;
    }

    public void setServiceIdSub(int i)
    {
        ServiceIdSub_ = i;
    }

    public void setServiceName(String s)
    {
        ServiceName_ = s;
    }

    public void setServiceType(int i)
    {
        ServiceType_ = i;
    }

    public void setStationName(String s)
    {
        StationName_ = s;
    }

    public void setTsId(int i)
    {
        TsId_ = i;
    }

    public void setTunerModeOnesegOnly(boolean flag)
    {
        isTunerModeOnesegOnly_ = flag;
    }

    public void setUPnPdeviceURI(String s)
    {
        UPnPdeviceURI_ = s;
    }

    private static final String TAG = "ChannelInfo";
    private byte AffiliationId_[];
    private int Available_;
    private BroadcastTypeT BroadcastType_;
    private String ChannelLogoPicturePath_;
    private int ChannelLogoPictureSize_;
    private int ChannelNumber_;
    private int ChannelScanFoundStatus_;
    private String Id_;
    private int NetworkId_;
    private String PhysicalChannelNumber_;
    private int PhysicalFrequency_;
    private int RemoteProhibit_;
    private int ServiceIdSub_;
    private int ServiceId_;
    private String ServiceName_;
    private int ServiceType_;
    private String StationName_;
    private int TsId_;
    private String UPnPdeviceURI_;
    private boolean isTunerModeOnesegOnly_;
}
