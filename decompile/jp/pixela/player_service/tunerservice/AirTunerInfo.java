// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;


// Referenced classes of package jp.pixela.player_service.tunerservice:
//            VersionClass

public class AirTunerInfo
{
    public static final class strageState_t extends Enum
    {

        public static strageState_t valueOf(String s)
        {
            return (strageState_t)Enum.valueOf(jp/pixela/player_service/tunerservice/AirTunerInfo$strageState_t, s);
        }

        public static strageState_t[] values()
        {
            return (strageState_t[])$VALUES.clone();
        }

        private static final strageState_t $VALUES[];
        public static final strageState_t NOT_CONNECTED;
        public static final strageState_t NOT_INITIALIZED;
        public static final strageState_t USEABLE;

        static 
        {
            NOT_CONNECTED = new strageState_t("NOT_CONNECTED", 0);
            NOT_INITIALIZED = new strageState_t("NOT_INITIALIZED", 1);
            USEABLE = new strageState_t("USEABLE", 2);
            $VALUES = (new strageState_t[] {
                NOT_CONNECTED, NOT_INITIALIZED, USEABLE
            });
        }

        private strageState_t(String s, int i)
        {
            super(s, i);
        }
    }


    public AirTunerInfo()
    {
    }

    public boolean getAntennaPower()
    {
        return antennaPower_;
    }

    public boolean getAttenuator()
    {
        return attenuator_;
    }

    public String getFriendlyName()
    {
        return friendlyName_;
    }

    public byte[] getMacAddress2GHz()
    {
        return macAddress2GHz_;
    }

    public byte[] getMacAddress5GHz()
    {
        return macAddress5GHz_;
    }

    public byte[] getMacAddressEther()
    {
        return macAddressEther_;
    }

    public String getModelName()
    {
        return modelName_;
    }

    public String getModelNumber()
    {
        return modelNumber_;
    }

    public String getSerialNumber()
    {
        return serialNumber_;
    }

    public int getStorageAssumedBitrate()
    {
        return storageAssumedBitrate_;
    }

    public int getStorageFree()
    {
        return storageFree_;
    }

    public long getStorageFreeByte()
    {
        return storageFreeByte_;
    }

    public strageState_t getStorageState()
    {
        return storageState_;
    }

    public int getStorageTotal()
    {
        return storageTotal_;
    }

    public long getStorageTotalByte()
    {
        return storageTotalByte_;
    }

    public int getStorageUsed()
    {
        return storageUsed_;
    }

    public long getStorageUsedByte()
    {
        return storageUsedByte_;
    }

    public String getUniqueDeviceName()
    {
        return uniqueDeviceName_;
    }

    public VersionClass getVersion()
    {
        return version_;
    }

    public void setAntennaPower(boolean flag)
    {
        antennaPower_ = flag;
    }

    public void setAttenuator(boolean flag)
    {
        attenuator_ = flag;
    }

    public void setFriendlyName(String s)
    {
        friendlyName_ = s;
    }

    public void setMacAddress2GHz(byte abyte0[])
    {
        macAddress2GHz_ = abyte0;
    }

    public void setMacAddress5GHz(byte abyte0[])
    {
        macAddress5GHz_ = abyte0;
    }

    public void setMacAddressEther(byte abyte0[])
    {
        macAddressEther_ = abyte0;
    }

    public void setModelName(String s)
    {
        modelName_ = s;
    }

    public void setModelNumber(String s)
    {
        modelNumber_ = s;
    }

    public void setSerialNumber(String s)
    {
        serialNumber_ = s;
    }

    public void setStorageAssumedBitrate(int i)
    {
        storageAssumedBitrate_ = i;
    }

    public void setStorageFree(int i)
    {
        storageFree_ = i;
    }

    public void setStorageFreeByte(long l)
    {
        storageFreeByte_ = l;
    }

    public void setStorageState(strageState_t stragestate_t)
    {
        storageState_ = stragestate_t;
    }

    public void setStorageTotal(int i)
    {
        storageTotal_ = i;
    }

    public void setStorageTotalByte(long l)
    {
        storageTotalByte_ = l;
    }

    public void setStorageUsed(int i)
    {
        storageUsed_ = i;
    }

    public void setStorageUsedByte(long l)
    {
        storageUsedByte_ = l;
    }

    public void setUniqueDeviceName(String s)
    {
        uniqueDeviceName_ = s;
    }

    public void setVersion(VersionClass versionclass)
    {
        version_ = versionclass;
    }

    private boolean antennaPower_;
    private boolean attenuator_;
    private String friendlyName_;
    private byte macAddress2GHz_[];
    private byte macAddress5GHz_[];
    private byte macAddressEther_[];
    private String modelName_;
    private String modelNumber_;
    private String serialNumber_;
    private int storageAssumedBitrate_;
    private long storageFreeByte_;
    private int storageFree_;
    private strageState_t storageState_;
    private long storageTotalByte_;
    private int storageTotal_;
    private long storageUsedByte_;
    private int storageUsed_;
    private String uniqueDeviceName_;
    private VersionClass version_;
}
