// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.thumbnail;


public class ThumbnailData
{

    public ThumbnailData(int i, int j, String s, String s1, int k, int l)
    {
        mIsGenre = false;
        mImage = null;
        mServiceId = i;
        mEventId = j;
        mBroadcastType = s;
        mImageUrl = s1;
        mImageWidth = k;
        mImageHeight = l;
    }

    public String getBroadcastType()
    {
        return mBroadcastType;
    }

    public int getEventId()
    {
        return mEventId;
    }

    public byte[] getImage()
    {
        return mImage;
    }

    public int getImageHeight()
    {
        return mImageHeight;
    }

    public String getImageUrl()
    {
        return mImageUrl;
    }

    public int getImageWidth()
    {
        return mImageWidth;
    }

    public int getServiceId()
    {
        return mServiceId;
    }

    public boolean isGenre()
    {
        return mIsGenre;
    }

    public void setEventId(int i)
    {
        mEventId = i;
    }

    public void setImage(byte abyte0[])
    {
        mImage = abyte0;
    }

    public void setIsGenre(boolean flag)
    {
        mIsGenre = flag;
    }

    public void setServiceId(int i)
    {
        mServiceId = i;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("ThumbnailData { mServiceId=");
        stringbuilder.append(mServiceId);
        stringbuilder.append(", mEventId=");
        stringbuilder.append(mEventId);
        stringbuilder.append(", mBroadcastType=");
        stringbuilder.append(mBroadcastType);
        stringbuilder.append(", mImageUrl=");
        stringbuilder.append(mImageUrl);
        stringbuilder.append(", mImageWidth=");
        stringbuilder.append(mImageWidth);
        stringbuilder.append(", mImageHeight=");
        stringbuilder.append(mImageHeight);
        stringbuilder.append(", mIsGenre=");
        stringbuilder.append(mIsGenre);
        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    private String mBroadcastType;
    private int mEventId;
    private byte mImage[];
    private int mImageHeight;
    private String mImageUrl;
    private int mImageWidth;
    private boolean mIsGenre;
    private int mServiceId;
}
