// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.common;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package jp.pixela.common:
//            BuildUtilityWrapper

public class ProductInfo
    implements Parcelable
{
    public static final class EmumProductType extends Enum
    {

        public static EmumProductType valueOf(String s)
        {
            return (EmumProductType)Enum.valueOf(jp/pixela/common/ProductInfo$EmumProductType, s);
        }

        public static EmumProductType[] values()
        {
            return (EmumProductType[])$VALUES.clone();
        }

        public int toValue()
        {
            return mValue;
        }

        private static final EmumProductType $VALUES[];
        public static final EmumProductType STB;
        public static final EmumProductType TV;
        public static final EmumProductType UNDEF;
        private final int mValue;

        static 
        {
            UNDEF = new EmumProductType("UNDEF", 0, 0);
            STB = new EmumProductType("STB", 1, 1);
            TV = new EmumProductType("TV", 2, 2);
            $VALUES = (new EmumProductType[] {
                UNDEF, STB, TV
            });
        }

        private EmumProductType(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }

    public static final class EmumSTBModelType extends Enum
    {

        public static EmumSTBModelType valueOf(String s)
        {
            return (EmumSTBModelType)Enum.valueOf(jp/pixela/common/ProductInfo$EmumSTBModelType, s);
        }

        public static EmumSTBModelType[] values()
        {
            return (EmumSTBModelType[])$VALUES.clone();
        }

        public int toValue()
        {
            return mValue;
        }

        private static final EmumSTBModelType $VALUES[];
        public static final EmumSTBModelType PIX_SMB100;
        public static final EmumSTBModelType PIX_SMB400;
        public static final EmumSTBModelType PIX_SMB410;
        public static final EmumSTBModelType POE_SMB400_FN1;
        public static final EmumSTBModelType POE_SMB400_PS1;
        public static final EmumSTBModelType UNDEF;
        private final int mValue;

        static 
        {
            UNDEF = new EmumSTBModelType("UNDEF", 0, 0);
            PIX_SMB100 = new EmumSTBModelType("PIX_SMB100", 1, 1);
            PIX_SMB400 = new EmumSTBModelType("PIX_SMB400", 2, 2);
            POE_SMB400_PS1 = new EmumSTBModelType("POE_SMB400_PS1", 3, 3);
            POE_SMB400_FN1 = new EmumSTBModelType("POE_SMB400_FN1", 4, 4);
            PIX_SMB410 = new EmumSTBModelType("PIX_SMB410", 5, 5);
            $VALUES = (new EmumSTBModelType[] {
                UNDEF, PIX_SMB100, PIX_SMB400, POE_SMB400_PS1, POE_SMB400_FN1, PIX_SMB410
            });
        }

        private EmumSTBModelType(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }

    public static final class EmumTVModelType extends Enum
    {

        public static EmumTVModelType valueOf(String s)
        {
            return (EmumTVModelType)Enum.valueOf(jp/pixela/common/ProductInfo$EmumTVModelType, s);
        }

        public static EmumTVModelType[] values()
        {
            return (EmumTVModelType[])$VALUES.clone();
        }

        public int toValue()
        {
            return mValue;
        }

        private static final EmumTVModelType $VALUES[];
        public static final EmumTVModelType PIX_VP100;
        public static final EmumTVModelType UNDEF;
        private final int mValue;

        static 
        {
            UNDEF = new EmumTVModelType("UNDEF", 0, 0);
            PIX_VP100 = new EmumTVModelType("PIX_VP100", 1, 1);
            $VALUES = (new EmumTVModelType[] {
                UNDEF, PIX_VP100
            });
        }

        private EmumTVModelType(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }

    public static final class ProductTypeT extends Enum
    {

        public static ProductTypeT fromValue(int i)
        {
            ProductTypeT aproducttypet[] = values();
            int j = aproducttypet.length;
            for(int k = 0; k < j; k++)
            {
                ProductTypeT producttypet = aproducttypet[k];
                if(producttypet.toValue() == i)
                    return producttypet;
            }

            return PRODUCT_DEFAULT;
        }

        public static ProductTypeT valueOf(String s)
        {
            return (ProductTypeT)Enum.valueOf(jp/pixela/common/ProductInfo$ProductTypeT, s);
        }

        public static ProductTypeT[] values()
        {
            return (ProductTypeT[])$VALUES.clone();
        }

        public int toValue()
        {
            return mValue;
        }

        private static final ProductTypeT $VALUES[];
        public static final ProductTypeT PRODUCT_DEFAULT;
        public static final ProductTypeT PRODUCT_DT300;
        public static final ProductTypeT PRODUCT_DT360;
        public static final ProductTypeT PRODUCT_PIX_SMB100;
        public static final ProductTypeT PRODUCT_PIX_SMB400;
        public static final ProductTypeT PRODUCT_PIX_SMB410;
        public static final ProductTypeT PRODUCT_PIX_VP100;
        public static final ProductTypeT PRODUCT_POE_SMB400_FN1;
        public static final ProductTypeT PRODUCT_POE_SMB400_PS1;
        public static final ProductTypeT PRODUCT_UNKNOWN;
        private final int mValue;

        static 
        {
            PRODUCT_DEFAULT = new ProductTypeT("PRODUCT_DEFAULT", 0, 0);
            PRODUCT_DT300 = new ProductTypeT("PRODUCT_DT300", 1, 1);
            PRODUCT_DT360 = new ProductTypeT("PRODUCT_DT360", 2, 2);
            PRODUCT_PIX_SMB100 = new ProductTypeT("PRODUCT_PIX_SMB100", 3, 3);
            PRODUCT_PIX_SMB400 = new ProductTypeT("PRODUCT_PIX_SMB400", 4, 4);
            PRODUCT_POE_SMB400_PS1 = new ProductTypeT("PRODUCT_POE_SMB400_PS1", 5, 5);
            PRODUCT_POE_SMB400_FN1 = new ProductTypeT("PRODUCT_POE_SMB400_FN1", 6, 6);
            PRODUCT_PIX_VP100 = new ProductTypeT("PRODUCT_PIX_VP100", 7, 7);
            PRODUCT_PIX_SMB410 = new ProductTypeT("PRODUCT_PIX_SMB410", 8, 8);
            PRODUCT_UNKNOWN = new ProductTypeT("PRODUCT_UNKNOWN", 9, 255);
            $VALUES = (new ProductTypeT[] {
                PRODUCT_DEFAULT, PRODUCT_DT300, PRODUCT_DT360, PRODUCT_PIX_SMB100, PRODUCT_PIX_SMB400, PRODUCT_POE_SMB400_PS1, PRODUCT_POE_SMB400_FN1, PRODUCT_PIX_VP100, PRODUCT_PIX_SMB410, PRODUCT_UNKNOWN
            });
        }

        private ProductTypeT(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }


    public ProductInfo(Context context)
    {
        mProductType = ProductTypeT.PRODUCT_UNKNOWN;
        mIsRetail = false;
        mIsOem = false;
        mIsRecordable = false;
        mSerial = "";
        mModelName = "SmartTuner";
        mBuildUtilityWrapper = new BuildUtilityWrapper(context);
        mProductType = mBuildUtilityWrapper.getProductType();
        mIsRetail = mBuildUtilityWrapper.isRetail();
        mIsOem = mBuildUtilityWrapper.isOEM();
        mIsRecordable = mBuildUtilityWrapper.isRecordable();
    }

    public ProductInfo(Parcel parcel)
    {
        mProductType = ProductTypeT.PRODUCT_UNKNOWN;
        boolean flag = false;
        mIsRetail = false;
        mIsOem = false;
        mIsRecordable = false;
        mSerial = "";
        mModelName = "SmartTuner";
        mProductType = ProductTypeT.fromValue(parcel.readInt());
        boolean flag1;
        if(parcel.readInt() == 0)
            flag1 = false;
        else
            flag1 = true;
        mIsRetail = flag1;
        if(parcel.readInt() == 0)
            flag1 = false;
        else
            flag1 = true;
        mIsOem = flag1;
        if(parcel.readInt() == 0)
            flag1 = flag;
        else
            flag1 = true;
        mIsRecordable = flag1;
        mModelName = parcel.readString();
        mSerial = parcel.readString();
    }

    public static int getEmumModelTypeValue(ProductTypeT producttypet)
    {
        int i = getEmumProductTypeValue(producttypet);
        static class _cls2
        {

            static final int $SwitchMap$jp$pixela$common$ProductInfo$ProductTypeT[];

            static 
            {
                $SwitchMap$jp$pixela$common$ProductInfo$ProductTypeT = new int[ProductTypeT.values().length];
                try
                {
                    $SwitchMap$jp$pixela$common$ProductInfo$ProductTypeT[ProductTypeT.PRODUCT_PIX_SMB100.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$jp$pixela$common$ProductInfo$ProductTypeT[ProductTypeT.PRODUCT_PIX_SMB400.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$jp$pixela$common$ProductInfo$ProductTypeT[ProductTypeT.PRODUCT_POE_SMB400_PS1.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$jp$pixela$common$ProductInfo$ProductTypeT[ProductTypeT.PRODUCT_POE_SMB400_FN1.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$jp$pixela$common$ProductInfo$ProductTypeT[ProductTypeT.PRODUCT_PIX_SMB410.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                $SwitchMap$jp$pixela$common$ProductInfo$ProductTypeT[ProductTypeT.PRODUCT_PIX_VP100.ordinal()] = 6;
_L2:
                return;
                NoSuchFieldError nosuchfielderror5;
                nosuchfielderror5;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        if(i == EmumProductType.STB.toValue())
            switch(_cls2..SwitchMap.jp.pixela.common.ProductInfo.ProductTypeT[producttypet.ordinal()])
            {
            default:
                return EmumSTBModelType.UNDEF.toValue();

            case 5: // '\005'
                return EmumSTBModelType.PIX_SMB410.toValue();

            case 4: // '\004'
                return EmumSTBModelType.POE_SMB400_FN1.toValue();

            case 3: // '\003'
                return EmumSTBModelType.POE_SMB400_PS1.toValue();

            case 2: // '\002'
                return EmumSTBModelType.PIX_SMB400.toValue();

            case 1: // '\001'
                return EmumSTBModelType.PIX_SMB100.toValue();
            }
        if(i == EmumProductType.TV.toValue())
        {
            if(_cls2..SwitchMap.jp.pixela.common.ProductInfo.ProductTypeT[producttypet.ordinal()] != 6)
                return EmumTVModelType.UNDEF.toValue();
            else
                return EmumTVModelType.PIX_VP100.toValue();
        } else
        {
            return EmumSTBModelType.UNDEF.toValue();
        }
    }

    public static int getEmumProductTypeValue(ProductTypeT producttypet)
    {
        switch(_cls2..SwitchMap.jp.pixela.common.ProductInfo.ProductTypeT[producttypet.ordinal()])
        {
        default:
            return EmumProductType.UNDEF.toValue();

        case 6: // '\006'
            return EmumProductType.TV.toValue();

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
            return EmumProductType.STB.toValue();
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public String getModelName()
    {
        return mModelName;
    }

    public ProductTypeT getProductType()
    {
        return mProductType;
    }

    public String getSerial()
    {
        return mSerial;
    }

    public boolean isOEM()
    {
        return mIsOem;
    }

    public boolean isRecordable()
    {
        return mIsRecordable;
    }

    public boolean isRetail()
    {
        return mIsRetail;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mProductType.toValue());
        parcel.writeInt(mIsRetail);
        parcel.writeInt(mIsOem);
        parcel.writeInt(mIsRecordable);
        parcel.writeString(mModelName);
        parcel.writeString(mSerial);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ProductInfo createFromParcel(Parcel parcel)
        {
            return new ProductInfo(parcel);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public ProductInfo[] newArray(int i)
        {
            return new ProductInfo[i];
        }

    }
;
    private BuildUtilityWrapper mBuildUtilityWrapper;
    private boolean mIsOem;
    private boolean mIsRecordable;
    private boolean mIsRetail;
    private String mModelName;
    private ProductTypeT mProductType;
    private String mSerial;

}
