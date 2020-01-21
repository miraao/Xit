// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;

import android.os.Parcel;
import android.os.Parcelable;

public final class AssignType extends Enum
    implements Parcelable
{

    private AssignType(String s, int i, int j)
    {
        super(s, i);
        mValue = j;
    }

    public static AssignType fromValue(int i)
    {
        AssignType aassigntype[] = values();
        int j = aassigntype.length;
        for(int k = 0; k < j; k++)
        {
            AssignType assigntype = aassigntype[k];
            if(assigntype.toValue() == i)
                return assigntype;
        }

        return UNKNOWN;
    }

    public static AssignType valueOf(String s)
    {
        return (AssignType)Enum.valueOf(jp/pixela/player_service/tunerservice/AssignType, s);
    }

    public static AssignType[] values()
    {
        return (AssignType[])$VALUES.clone();
    }

    public int describeContents()
    {
        return 0;
    }

    public int toValue()
    {
        return mValue;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mValue);
    }

    private static final AssignType $VALUES[];
    public static final AssignType ACCESS_SDINFO;
    public static final AssignType CONTENT_PREVIEW;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AssignType createFromParcel(Parcel parcel)
        {
            return AssignType.fromValue(parcel.readInt());
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public AssignType[] newArray(int i)
        {
            return new AssignType[i];
        }

    }
;
    public static final AssignType RESERVATION_RECORD;
    public static final AssignType SH_SERVICE;
    public static final AssignType TV_PREVIEW;
    public static final AssignType UNKNOWN;
    private int mValue;

    static 
    {
        TV_PREVIEW = new AssignType("TV_PREVIEW", 0, 0);
        CONTENT_PREVIEW = new AssignType("CONTENT_PREVIEW", 1, 1);
        RESERVATION_RECORD = new AssignType("RESERVATION_RECORD", 2, 2);
        SH_SERVICE = new AssignType("SH_SERVICE", 3, 3);
        ACCESS_SDINFO = new AssignType("ACCESS_SDINFO", 4, 4);
        UNKNOWN = new AssignType("UNKNOWN", 5, 255);
        $VALUES = (new AssignType[] {
            TV_PREVIEW, CONTENT_PREVIEW, RESERVATION_RECORD, SH_SERVICE, ACCESS_SDINFO, UNKNOWN
        });
    }
}
