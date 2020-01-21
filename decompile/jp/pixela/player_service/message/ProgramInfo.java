// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.message;

import java.util.Arrays;

public class ProgramInfo
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
            return (BroadcastTypeT)Enum.valueOf(jp/pixela/player_service/message/ProgramInfo$BroadcastTypeT, s);
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
            $VALUES = (new BroadcastTypeT[] {
                DEFAULT, TR, BS, CS
            });
        }

        private BroadcastTypeT(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }


    public ProgramInfo()
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

            static final int $SwitchMap$jp$pixela$player_service$message$ProgramInfo$BroadcastTypeT[];

            static 
            {
                $SwitchMap$jp$pixela$player_service$message$ProgramInfo$BroadcastTypeT = new int[BroadcastTypeT.values().length];
                try
                {
                    $SwitchMap$jp$pixela$player_service$message$ProgramInfo$BroadcastTypeT[BroadcastTypeT.TR.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$jp$pixela$player_service$message$ProgramInfo$BroadcastTypeT[BroadcastTypeT.BS.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                $SwitchMap$jp$pixela$player_service$message$ProgramInfo$BroadcastTypeT[BroadcastTypeT.CS.ordinal()] = 3;
_L2:
                return;
                NoSuchFieldError nosuchfielderror2;
                nosuchfielderror2;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        String s;
        switch(_cls1..SwitchMap.jp.pixela.player_service.message.ProgramInfo.BroadcastTypeT[broadcastType_.ordinal()])
        {
        default:
            s = null;
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

    public int getEventId()
    {
        return eventId_;
    }

    public int[] getGenres()
    {
        return genres_;
    }

    public int getReferEventId()
    {
        return referEventId_;
    }

    public int getReferServiceId()
    {
        return referServiceId_;
    }

    public int getServiceId()
    {
        return serviceId_;
    }

    public void setBroadcastType(BroadcastTypeT broadcasttypet)
    {
        broadcastType_ = broadcasttypet;
    }

    public void setEventId(int i)
    {
        eventId_ = i;
    }

    public void setGenres(int ai[])
    {
        genres_ = ai;
    }

    public void setReferEventId(int i)
    {
        referEventId_ = i;
    }

    public void setReferServiceId(int i)
    {
        referServiceId_ = i;
    }

    public void setServiceId(int i)
    {
        serviceId_ = i;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(super.toString());
        stringbuilder.append(" { eventId_=");
        stringbuilder.append(eventId_);
        stringbuilder.append(", serviceId_=");
        stringbuilder.append(serviceId_);
        stringbuilder.append(", broadcastType_=");
        stringbuilder.append(getBroadcastTypeString());
        stringbuilder.append(", genres_=");
        stringbuilder.append(Arrays.toString(genres_));
        stringbuilder.append(", referServiceId_=");
        stringbuilder.append(referServiceId_);
        stringbuilder.append(", referEventId_=");
        stringbuilder.append(referEventId_);
        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    private BroadcastTypeT broadcastType_;
    private int eventId_;
    int genres_[];
    private int referEventId_;
    private int referServiceId_;
    private int serviceId_;
}
