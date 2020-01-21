// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;


public final class BroadcastTypeT extends Enum
{

    private BroadcastTypeT(String s, int i, int j)
    {
        super(s, i);
        mValue = j;
    }

    public static BroadcastTypeT fromValue(int i)
    {
        BroadcastTypeT broadcasttypet = BROADCAST_TYPE_TR;
        BroadcastTypeT abroadcasttypet[] = values();
        int j = abroadcasttypet.length;
        for(int k = 0; k < j; k++)
        {
            BroadcastTypeT broadcasttypet1 = abroadcasttypet[k];
            if(broadcasttypet1.toValue() == i)
                broadcasttypet = broadcasttypet1;
        }

        return broadcasttypet;
    }

    public static BroadcastTypeT valueOf(String s)
    {
        return (BroadcastTypeT)Enum.valueOf(jp/pixela/player_service/tunerservice/BroadcastTypeT, s);
    }

    public static BroadcastTypeT[] values()
    {
        return (BroadcastTypeT[])$VALUES.clone();
    }

    public int toValue()
    {
        return mValue;
    }

    private static final BroadcastTypeT $VALUES[];
    public static final BroadcastTypeT BROADCAST_TYPE_4K;
    public static final BroadcastTypeT BROADCAST_TYPE_BS;
    public static final BroadcastTypeT BROADCAST_TYPE_CONFIG;
    public static final BroadcastTypeT BROADCAST_TYPE_CS;
    public static final BroadcastTypeT BROADCAST_TYPE_TR;
    private int mValue;

    static 
    {
        BROADCAST_TYPE_TR = new BroadcastTypeT("BROADCAST_TYPE_TR", 0, 1);
        BROADCAST_TYPE_BS = new BroadcastTypeT("BROADCAST_TYPE_BS", 1, 2);
        BROADCAST_TYPE_CS = new BroadcastTypeT("BROADCAST_TYPE_CS", 2, 3);
        BROADCAST_TYPE_4K = new BroadcastTypeT("BROADCAST_TYPE_4K", 3, 10);
        BROADCAST_TYPE_CONFIG = new BroadcastTypeT("BROADCAST_TYPE_CONFIG", 4, 4);
        $VALUES = (new BroadcastTypeT[] {
            BROADCAST_TYPE_TR, BROADCAST_TYPE_BS, BROADCAST_TYPE_CS, BROADCAST_TYPE_4K, BROADCAST_TYPE_CONFIG
        });
    }
}
