// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;


public final class BroadcastWave extends Enum
{

    private BroadcastWave(String s, int i, int j)
    {
        super(s, i);
        mValue = j;
    }

    public static BroadcastWave valueOf(String s)
    {
        return (BroadcastWave)Enum.valueOf(jp/pixela/player_service/tunerservice/BroadcastWave, s);
    }

    public static BroadcastWave[] values()
    {
        return (BroadcastWave[])$VALUES.clone();
    }

    public int getValue()
    {
        return mValue;
    }

    private static final BroadcastWave $VALUES[];
    public static final BroadcastWave ADBS;
    public static final BroadcastWave ADCS;
    public static final BroadcastWave ADTV;
    public static final BroadcastWave ALL;
    public static final BroadcastWave BS;
    public static final BroadcastWave BSADTV;
    public static final BroadcastWave BSCS;
    public static final BroadcastWave CS;
    public static final BroadcastWave TR;
    public static final BroadcastWave TRBS;
    public static final BroadcastWave TRBSCS;
    public static final BroadcastWave TRCS;
    private int mValue;

    static 
    {
        TR = new BroadcastWave("TR", 0, 1);
        BS = new BroadcastWave("BS", 1, 2);
        CS = new BroadcastWave("CS", 2, 4);
        ADBS = new BroadcastWave("ADBS", 3, 8);
        ADCS = new BroadcastWave("ADCS", 4, 16);
        TRBS = new BroadcastWave("TRBS", 5, 3);
        BSCS = new BroadcastWave("BSCS", 6, 6);
        TRCS = new BroadcastWave("TRCS", 7, 5);
        TRBSCS = new BroadcastWave("TRBSCS", 8, 7);
        ADTV = new BroadcastWave("ADTV", 9, 24);
        BSADTV = new BroadcastWave("BSADTV", 10, 26);
        ALL = new BroadcastWave("ALL", 11, 31);
        $VALUES = (new BroadcastWave[] {
            TR, BS, CS, ADBS, ADCS, TRBS, BSCS, TRCS, TRBSCS, ADTV, 
            BSADTV, ALL
        });
    }
}
