// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.common;

import android.content.Context;

public class BuildUtilityWrapper
{

    public BuildUtilityWrapper(Context context)
    {
        mPoductType = ProductInfo.ProductTypeT.PRODUCT_UNKNOWN;
        initialize();
    }

    private boolean initialize()
    {
        mPoductType = ProductInfo.ProductTypeT.PRODUCT_PIX_SMB100;
        return true;
    }

    public ProductInfo.ProductTypeT getProductType()
    {
        return mPoductType;
    }

    public boolean isOEM()
    {
        return false;
    }

    public boolean isPOESMB400FN1()
    {
        return false;
    }

    public boolean isPOESMB400PS1()
    {
        return false;
    }

    public boolean isRecordable()
    {
        return true;
    }

    public boolean isRetail()
    {
        return true;
    }

    public boolean isSmartSpeakerPropControl()
    {
        return false;
    }

    public boolean isSupportDolbyDigital()
    {
        return false;
    }

    private final boolean mIsOem = false;
    private final boolean mIsRecordable = true;
    private final boolean mIsRetail = true;
    private ProductInfo.ProductTypeT mPoductType;
}
