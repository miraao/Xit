// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.message;


public class ViewingPointInfo
{
    public static final class BroadcastTypeT extends Enum
    {

        public static BroadcastTypeT fromValue(int i)
        {
            BroadcastTypeT broadcasttypet = DEFAULT;
            BroadcastTypeT abroadcasttypet[] = values();
            int j = abroadcasttypet.length;
            int k = 0;
            BroadcastTypeT broadcasttypet1;
            do
            {
                broadcasttypet1 = broadcasttypet;
                if(k >= j)
                    break;
                broadcasttypet1 = abroadcasttypet[k];
                if(broadcasttypet1.toValue() == i)
                    break;
                k++;
            } while(true);
            return broadcasttypet1;
        }

        public static BroadcastTypeT valueOf(String s)
        {
            return (BroadcastTypeT)Enum.valueOf(jp/pixela/player_service/message/ViewingPointInfo$BroadcastTypeT, s);
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
        public static final BroadcastTypeT ADTV;
        public static final BroadcastTypeT BS;
        public static final BroadcastTypeT CS;
        public static final BroadcastTypeT DEFAULT;
        public static final BroadcastTypeT TR;
        private int mValue;

        static 
        {
            DEFAULT = new BroadcastTypeT("DEFAULT", 0, 0);
            TR = new BroadcastTypeT("TR", 1, 1);
            BS = new BroadcastTypeT("BS", 2, 2);
            CS = new BroadcastTypeT("CS", 3, 3);
            ADTV = new BroadcastTypeT("ADTV", 4, 4);
            $VALUES = (new BroadcastTypeT[] {
                DEFAULT, TR, BS, CS, ADTV
            });
        }

        private BroadcastTypeT(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }


    public ViewingPointInfo()
    {
    }

    public BroadcastTypeT getBroadcastType()
    {
        return broadcastType_;
    }

    public String getBroadcastTypeString()
    {
        static class _cls1
        {

            static final int $SwitchMap$jp$pixela$player_service$message$ViewingPointInfo$BroadcastTypeT[];

            static 
            {
                $SwitchMap$jp$pixela$player_service$message$ViewingPointInfo$BroadcastTypeT = new int[BroadcastTypeT.values().length];
                try
                {
                    $SwitchMap$jp$pixela$player_service$message$ViewingPointInfo$BroadcastTypeT[BroadcastTypeT.TR.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$jp$pixela$player_service$message$ViewingPointInfo$BroadcastTypeT[BroadcastTypeT.BS.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$jp$pixela$player_service$message$ViewingPointInfo$BroadcastTypeT[BroadcastTypeT.CS.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                $SwitchMap$jp$pixela$player_service$message$ViewingPointInfo$BroadcastTypeT[BroadcastTypeT.ADTV.ordinal()] = 4;
_L2:
                return;
                NoSuchFieldError nosuchfielderror3;
                nosuchfielderror3;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        String s;
        switch(_cls1..SwitchMap.jp.pixela.player_service.message.ViewingPointInfo.BroadcastTypeT[broadcastType_.ordinal()])
        {
        default:
            s = null;
            break;

        case 4: // '\004'
            s = "4k";
            break;

        case 3: // '\003'
            s = "cs";
            break;

        case 2: // '\002'
            s = "bs";
            break;

        case 1: // '\001'
            s = "tr";
            break;
        }
        return s;
    }

    public int[] getServiceIds()
    {
        return serviceIds_;
    }

    public void setBroadcastType(String s)
    {
        if(s.equals("tr"))
            broadcastType_ = BroadcastTypeT.TR;
        else
        if(s.equals("bs"))
            broadcastType_ = BroadcastTypeT.BS;
        else
        if(s.equals("cs"))
            broadcastType_ = BroadcastTypeT.CS;
        else
        if(s.equals("4k"))
            broadcastType_ = BroadcastTypeT.ADTV;
        else
            broadcastType_ = BroadcastTypeT.DEFAULT;
    }

    public void setBroadcastType(BroadcastTypeT broadcasttypet)
    {
        broadcastType_ = broadcasttypet;
    }

    public void setServiceIds(int ai[])
    {
        serviceIds_ = ai;
    }

    private BroadcastTypeT broadcastType_;
    private int serviceIds_[];
}
