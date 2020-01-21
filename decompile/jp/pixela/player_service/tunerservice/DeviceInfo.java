// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public class DeviceInfo
    implements Parcelable
{
    public static final class StorageState extends Enum
    {

        public static StorageState fromValue(int i)
        {
            StorageState storagestate = NOT_CONNECTED;
            StorageState astoragestate[] = values();
            int j = astoragestate.length;
            for(int k = 0; k < j; k++)
            {
                StorageState storagestate1 = astoragestate[k];
                if(storagestate1.toValue() == i)
                    storagestate = storagestate1;
            }

            return storagestate;
        }

        public static StorageState valueOf(String s)
        {
            return (StorageState)Enum.valueOf(jp/pixela/player_service/tunerservice/DeviceInfo$StorageState, s);
        }

        public static StorageState[] values()
        {
            return (StorageState[])$VALUES.clone();
        }

        public int toValue()
        {
            return mValue;
        }

        private static final StorageState $VALUES[];
        public static final StorageState NOT_CONNECTED;
        public static final StorageState NOT_INITIALIZED;
        public static final StorageState USEABLE;
        private int mValue;

        static 
        {
            NOT_CONNECTED = new StorageState("NOT_CONNECTED", 0, 0);
            NOT_INITIALIZED = new StorageState("NOT_INITIALIZED", 1, 1);
            USEABLE = new StorageState("USEABLE", 2, 2);
            $VALUES = (new StorageState[] {
                NOT_CONNECTED, NOT_INITIALIZED, USEABLE
            });
        }

        private StorageState(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }


    public DeviceInfo(Parcel parcel)
    {
        mMacAddress2GHz = new byte[6];
        mMacAddress5GHz = new byte[6];
        mMacAddressEther = new byte[6];
        mStorageState = StorageState.NOT_CONNECTED;
        mClientIpAddress = new byte[4];
        mClientMacAddress = new byte[6];
        mPairingKey = null;
        boolean flag = false;
        mDeviceType = 0;
        mAttenuator = false;
        mAntennaPower = false;
        mUrl = parcel.readString();
        mUDN = parcel.readString();
        mFriendlyName = parcel.readString();
        mModelName = parcel.readString();
        mModelNumber = parcel.readString();
        parcel.readByteArray(mMacAddress2GHz);
        parcel.readByteArray(mMacAddress5GHz);
        parcel.readByteArray(mMacAddressEther);
        mVersion = parcel.readString();
        mSerialNumber = parcel.readString();
        mStorageState = StorageState.fromValue(parcel.readInt());
        mStorageTotal = parcel.readInt();
        mStorageUsed = parcel.readInt();
        mStorageFree = parcel.readInt();
        parcel.readByteArray(mClientIpAddress);
        parcel.readByteArray(mClientMacAddress);
        int i = parcel.readInt();
        if(i > 0)
        {
            mPairingKey = new byte[i];
            parcel.readByteArray(mPairingKey);
        }
        mDeviceType = parcel.readInt();
        mStorageTotalByte = parcel.readLong();
        mStorageUsedByte = parcel.readLong();
        mStorageFreeByte = parcel.readLong();
        mUpnpPort = parcel.readInt();
        mStreamPort = parcel.readInt();
        mEventingPort = parcel.readInt();
        mMinEventingPort = parcel.readInt();
        mMaxEventingPort = parcel.readInt();
        boolean flag1;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        mAttenuator = flag1;
        flag1 = flag;
        if(parcel.readInt() == 1)
            flag1 = true;
        mAntennaPower = flag1;
        mStorageAssumedBitrate = parcel.readInt();
        mSourceType = parcel.readInt();
        mSegmentType = parcel.readInt();
    }

    public DeviceInfo(String s, String s1)
    {
        mMacAddress2GHz = new byte[6];
        mMacAddress5GHz = new byte[6];
        mMacAddressEther = new byte[6];
        mStorageState = StorageState.NOT_CONNECTED;
        mClientIpAddress = new byte[4];
        mClientMacAddress = new byte[6];
        mPairingKey = null;
        mDeviceType = 0;
        mAttenuator = false;
        mAntennaPower = false;
        mUrl = s;
        mUDN = s1;
        if(s1.equalsIgnoreCase("uuid:ffffffff-ffff-ffff-ffff-ffffffffffff"))
            mSourceType = ControlInterface.SourceTypeT.TUNER_IF_PROXY.toValue();
    }

    public ControlInterface.SegmentTypeT GetSegmentType()
    {
        ControlInterface.SegmentTypeT segmenttypet;
        if(mSegmentType == ControlInterface.SegmentTypeT.TUNER_FIXED_ONESEG.toValue())
            segmenttypet = ControlInterface.SegmentTypeT.TUNER_FIXED_ONESEG;
        else
        if(mSegmentType == ControlInterface.SegmentTypeT.TUNER_FIXED_FULLSEG.toValue())
            segmenttypet = ControlInterface.SegmentTypeT.TUNER_FIXED_FULLSEG;
        else
        if(mSegmentType == ControlInterface.SegmentTypeT.TUNER_AUTO_SEGMENT_CHANGE.toValue())
            segmenttypet = ControlInterface.SegmentTypeT.TUNER_AUTO_SEGMENT_CHANGE;
        else
            segmenttypet = null;
        return segmenttypet;
    }

    public ControlInterface.SourceTypeT GetSourceType()
    {
        ControlInterface.SourceTypeT sourcetypet;
        if(mSourceType == ControlInterface.SourceTypeT.AIR_TUNER.toValue())
            sourcetypet = ControlInterface.SourceTypeT.AIR_TUNER;
        else
        if(mSourceType == ControlInterface.SourceTypeT.LOCAL_TUNER.toValue())
            sourcetypet = ControlInterface.SourceTypeT.LOCAL_TUNER;
        else
        if(mSourceType == ControlInterface.SourceTypeT.SD.toValue())
            sourcetypet = ControlInterface.SourceTypeT.SD;
        else
        if(mSourceType == ControlInterface.SourceTypeT.FS.toValue())
            sourcetypet = ControlInterface.SourceTypeT.FS;
        else
        if(mSourceType == ControlInterface.SourceTypeT.NSM.toValue())
            sourcetypet = ControlInterface.SourceTypeT.NSM;
        else
        if(mSourceType == ControlInterface.SourceTypeT.TUNER_IF_PROXY.toValue())
            sourcetypet = ControlInterface.SourceTypeT.TUNER_IF_PROXY;
        else
            sourcetypet = null;
        return sourcetypet;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean getAntennaPower()
    {
        return mAntennaPower;
    }

    public boolean getAttenuator()
    {
        return mAttenuator;
    }

    public byte[] getClientIpAddress()
    {
        return mClientIpAddress;
    }

    public byte[] getClientMacAddress()
    {
        return mClientMacAddress;
    }

    public int getDeviceType()
    {
        return mDeviceType;
    }

    public int getEventingPort()
    {
        return mEventingPort;
    }

    public String getFriendlyName()
    {
        return mFriendlyName;
    }

    public byte[] getMacAddress2GHz()
    {
        return mMacAddress2GHz;
    }

    public byte[] getMacAddress5GHz()
    {
        return mMacAddress5GHz;
    }

    public byte[] getMacAddressEther()
    {
        return mMacAddressEther;
    }

    public int getMaxEventingPort()
    {
        return mMaxEventingPort;
    }

    public int getMinEventingPort()
    {
        return mMinEventingPort;
    }

    public String getModelName()
    {
        return mModelName;
    }

    public String getModelNumber()
    {
        return mModelNumber;
    }

    public byte[] getPairingKey()
    {
        return mPairingKey;
    }

    public long[] getPictLevelRange()
    {
        return mPictLevelRange;
    }

    public String getSerialNumber()
    {
        return mSerialNumber;
    }

    public int getStorageAssumedBitrate()
    {
        return mStorageAssumedBitrate;
    }

    public int getStorageFree()
    {
        return mStorageFree;
    }

    public long getStorageFreeByte()
    {
        return mStorageFreeByte;
    }

    public StorageState getStorageState()
    {
        return mStorageState;
    }

    public int getStorageTotal()
    {
        return mStorageTotal;
    }

    public long getStorageTotalByte()
    {
        return mStorageTotalByte;
    }

    public int getStorageUsed()
    {
        return mStorageUsed;
    }

    public long getStorageUsedByte()
    {
        return mStorageUsedByte;
    }

    public int getStreamPort()
    {
        return mStreamPort;
    }

    public String getUDN()
    {
        return mUDN;
    }

    public int getUpnpPort()
    {
        return mUpnpPort;
    }

    public String getUrl()
    {
        return mUrl;
    }

    public String getVersion()
    {
        return mVersion;
    }

    public void setAntennaPower(boolean flag)
    {
        mAntennaPower = flag;
    }

    public void setAttenuator(boolean flag)
    {
        mAttenuator = flag;
    }

    public void setClientIpAddress(byte abyte0[])
    {
        mClientIpAddress = abyte0;
    }

    public void setClientMacAddress(byte abyte0[])
    {
        mClientMacAddress = abyte0;
    }

    public void setDeviceType(int i)
    {
        mDeviceType = i;
    }

    public void setEventingPort(int i)
    {
        mEventingPort = i;
    }

    public void setFriendlyName(String s)
    {
        mFriendlyName = s;
    }

    public void setMacAddress2GHz(byte abyte0[])
    {
        mMacAddress2GHz = abyte0;
    }

    public void setMacAddress5GHz(byte abyte0[])
    {
        mMacAddress5GHz = abyte0;
    }

    public void setMacAddressEther(byte abyte0[])
    {
        mMacAddressEther = abyte0;
    }

    public void setMaxEventingPort(int i)
    {
        mMaxEventingPort = i;
    }

    public void setMinEventingPort(int i)
    {
        mMinEventingPort = i;
    }

    public void setModelName(String s)
    {
        mModelName = s;
    }

    public void setModelNumber(String s)
    {
        mModelNumber = s;
    }

    public void setPairingKey(byte abyte0[])
    {
        mPairingKey = abyte0;
    }

    public void setPictLevelRange(long al[])
    {
        mPictLevelRange = al;
    }

    public void setSegmentType(ControlInterface.SegmentTypeT segmenttypet)
    {
        mSegmentType = segmenttypet.toValue();
    }

    public void setSerialNumber(String s)
    {
        mSerialNumber = s;
    }

    public void setSourceType(ControlInterface.SourceTypeT sourcetypet)
    {
        mSourceType = sourcetypet.toValue();
    }

    public void setStorageAssumedBitrate(int i)
    {
        mStorageAssumedBitrate = i;
    }

    public void setStorageFree(int i)
    {
        mStorageFree = i;
    }

    public void setStorageFreeByte(long l)
    {
        mStorageFreeByte = l;
    }

    public void setStorageState(StorageState storagestate)
    {
        mStorageState = storagestate;
    }

    public void setStorageTotal(int i)
    {
        mStorageTotal = i;
    }

    public void setStorageTotalByte(long l)
    {
        mStorageTotalByte = l;
    }

    public void setStorageUsed(int i)
    {
        mStorageUsed = i;
    }

    public void setStorageUsedByte(long l)
    {
        mStorageUsedByte = l;
    }

    public void setStreamPort(int i)
    {
        mStreamPort = i;
    }

    public void setUpnpPort(int i)
    {
        mUpnpPort = i;
    }

    public void setVersion(String s)
    {
        mVersion = s;
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/player_service/tunerservice/DeviceInfo.getSimpleName());
        stringbuilder.append(" { ");
        stringbuilder.append("Url=");
        stringbuilder.append(mUrl);
        stringbuilder.append(" ");
        stringbuilder.append("UDN=");
        stringbuilder.append(mUDN);
        stringbuilder.append(" ");
        stringbuilder.append("PictLevelRange=");
        stringbuilder.append(Arrays.toString(getPictLevelRange()));
        stringbuilder.append(" ");
        stringbuilder.append("FriendlyName=");
        stringbuilder.append(getFriendlyName());
        stringbuilder.append(" ");
        stringbuilder.append("ModelName=");
        stringbuilder.append(getModelName());
        stringbuilder.append(" ");
        stringbuilder.append("ModelNumber=");
        stringbuilder.append(getModelNumber());
        stringbuilder.append(" ");
        stringbuilder.append("MacAddress2GHz=");
        stringbuilder.append(Arrays.toString(getMacAddress2GHz()));
        stringbuilder.append(" ");
        stringbuilder.append("MacAddress5GHz=");
        stringbuilder.append(Arrays.toString(getMacAddress5GHz()));
        stringbuilder.append(" ");
        stringbuilder.append("MacAddressEther=");
        stringbuilder.append(Arrays.toString(getMacAddressEther()));
        stringbuilder.append(" ");
        stringbuilder.append("Version=");
        stringbuilder.append(getVersion());
        stringbuilder.append(" ");
        stringbuilder.append("SerialNumber=");
        stringbuilder.append(getSerialNumber());
        stringbuilder.append(" ");
        stringbuilder.append("StorageState=");
        stringbuilder.append(getStorageState());
        stringbuilder.append(" ");
        stringbuilder.append("StorageTotal=");
        stringbuilder.append(getStorageTotal());
        stringbuilder.append(" ");
        stringbuilder.append("StorageUsed=");
        stringbuilder.append(getStorageUsed());
        stringbuilder.append(" ");
        stringbuilder.append("StorageFree=");
        stringbuilder.append(getStorageFree());
        stringbuilder.append(" ");
        stringbuilder.append("SourceType=");
        stringbuilder.append(GetSourceType());
        stringbuilder.append(" ");
        stringbuilder.append("SegmentType=");
        stringbuilder.append(GetSegmentType());
        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mUrl);
        parcel.writeString(mUDN);
        parcel.writeString(mFriendlyName);
        parcel.writeString(mModelName);
        parcel.writeString(mModelNumber);
        parcel.writeByteArray(mMacAddress2GHz);
        parcel.writeByteArray(mMacAddress5GHz);
        parcel.writeByteArray(mMacAddressEther);
        parcel.writeString(mVersion);
        parcel.writeString(mSerialNumber);
        parcel.writeInt(mStorageState.toValue());
        parcel.writeInt(mStorageTotal);
        parcel.writeInt(mStorageUsed);
        parcel.writeInt(mStorageFree);
        parcel.writeByteArray(mClientIpAddress);
        parcel.writeByteArray(mClientMacAddress);
        if(mPairingKey != null)
        {
            parcel.writeInt(mPairingKey.length);
            parcel.writeByteArray(mPairingKey);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(mDeviceType);
        parcel.writeLong(mStorageTotalByte);
        parcel.writeLong(mStorageUsedByte);
        parcel.writeLong(mStorageFreeByte);
        parcel.writeInt(mUpnpPort);
        parcel.writeInt(mStreamPort);
        parcel.writeInt(mEventingPort);
        parcel.writeInt(mMinEventingPort);
        parcel.writeInt(mMaxEventingPort);
        parcel.writeInt(mAttenuator);
        parcel.writeInt(mAntennaPower);
        parcel.writeInt(mStorageAssumedBitrate);
        parcel.writeInt(mSourceType);
        parcel.writeInt(mSegmentType);
    }

    public static final int BITRATE_DR = 24000;
    public static final int BITRATE_HIGH = 6000;
    public static final int BITRATE_LOW = 2000;
    public static final int BITRATE_STANDARD = 4000;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DeviceInfo createFromParcel(Parcel parcel)
        {
            return new DeviceInfo(parcel);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public DeviceInfo[] newArray(int i)
        {
            return new DeviceInfo[i];
        }

    }
;
    public static final int DEVICE_TYPE_DT195 = 6;
    public static final int DEVICE_TYPE_PCATS001 = 5;
    public static final int DEVICE_TYPE_PCATS002 = 7;
    public static final int DEVICE_TYPE_POE_MP4000 = 8;
    public static final int RECORD_BITRATE_DR = 24000;
    public static final int RECORD_BITRATE_HIGH = 6000;
    public static final int RECORD_BITRATE_LOW = 2000;
    public static final int RECORD_BITRATE_STANDARD = 4000;
    private boolean mAntennaPower;
    private boolean mAttenuator;
    private byte mClientIpAddress[];
    private byte mClientMacAddress[];
    private int mDeviceType;
    private int mEventingPort;
    private String mFriendlyName;
    private byte mMacAddress2GHz[];
    private byte mMacAddress5GHz[];
    private byte mMacAddressEther[];
    private int mMaxEventingPort;
    private int mMinEventingPort;
    private String mModelName;
    private String mModelNumber;
    private byte mPairingKey[];
    private long mPictLevelRange[];
    private int mSegmentType;
    private String mSerialNumber;
    private int mSourceType;
    private int mStorageAssumedBitrate;
    private int mStorageFree;
    private long mStorageFreeByte;
    private StorageState mStorageState;
    private int mStorageTotal;
    private long mStorageTotalByte;
    private int mStorageUsed;
    private long mStorageUsedByte;
    private int mStreamPort;
    private String mUDN;
    private int mUpnpPort;
    private String mUrl;
    private String mVersion;

}
