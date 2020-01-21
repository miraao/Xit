// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.rank;


public class ViewingPointData
{

    public ViewingPointData(int i, int j, int k)
    {
        mServiceId = -1;
        mRank = -1;
        mPoint = -1;
        mServiceId = i;
        mRank = j;
        mPoint = k;
    }

    public ViewingPointData(ViewingPointData viewingpointdata)
    {
        mServiceId = -1;
        mRank = -1;
        mPoint = -1;
        mServiceId = viewingpointdata.mServiceId;
        mRank = viewingpointdata.mRank;
        mPoint = viewingpointdata.mPoint;
    }

    public int getPoint()
    {
        return mPoint;
    }

    public int getRank()
    {
        return mRank;
    }

    public int getServiceId()
    {
        return mServiceId;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("serviceId=");
        stringbuilder.append(mServiceId);
        stringbuilder.append(",rank=");
        stringbuilder.append(mRank);
        stringbuilder.append(",point=");
        stringbuilder.append(mPoint);
        return new String(stringbuilder.toString());
    }

    private int mPoint;
    private int mRank;
    private int mServiceId;
}
