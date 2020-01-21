// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.message;

import java.util.Arrays;

// Referenced classes of package jp.pixela.player_service.message:
//            ProgramInfo

public class RecommendInfo
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
            return (BroadcastTypeT)Enum.valueOf(jp/pixela/player_service/message/RecommendInfo$BroadcastTypeT, s);
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


    public RecommendInfo()
    {
    }

    public int getAreaCode()
    {
        return areaCode_;
    }

    public BroadcastTypeT getBroadcastType()
    {
        return broadcastType_;
    }

    public String getBroadcastTypeString()
    {
        static class _cls1
        {

            static final int $SwitchMap$jp$pixela$player_service$message$RecommendInfo$BroadcastTypeT[];

            static 
            {
                $SwitchMap$jp$pixela$player_service$message$RecommendInfo$BroadcastTypeT = new int[BroadcastTypeT.values().length];
                try
                {
                    $SwitchMap$jp$pixela$player_service$message$RecommendInfo$BroadcastTypeT[BroadcastTypeT.TR.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$jp$pixela$player_service$message$RecommendInfo$BroadcastTypeT[BroadcastTypeT.BS.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                $SwitchMap$jp$pixela$player_service$message$RecommendInfo$BroadcastTypeT[BroadcastTypeT.CS.ordinal()] = 3;
_L2:
                return;
                NoSuchFieldError nosuchfielderror2;
                nosuchfielderror2;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        String s;
        switch(_cls1..SwitchMap.jp.pixela.player_service.message.RecommendInfo.BroadcastTypeT[broadcastType_.ordinal()])
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

    public boolean getPixRecommend()
    {
        return pixRecommend_;
    }

    public ProgramInfo[] getProgramInfos()
    {
        return programInfos_;
    }

    public boolean getRecommend()
    {
        return recommend_;
    }

    public int getServiceId()
    {
        return serviceId_;
    }

    public int[] getServiceIds()
    {
        return serviceIds_;
    }

    public long getSince()
    {
        return since_;
    }

    public long getUntil()
    {
        return until_;
    }

    public String getUserId()
    {
        return userId_;
    }

    public void setAreaCode(int i)
    {
        areaCode_ = i;
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
            broadcastType_ = BroadcastTypeT.DEFAULT;
    }

    public void setBroadcastType(BroadcastTypeT broadcasttypet)
    {
        broadcastType_ = broadcasttypet;
    }

    public void setEventId(int i)
    {
        eventId_ = i;
    }

    public void setPixRecommend(boolean flag)
    {
        pixRecommend_ = flag;
    }

    public void setProgramInfos(ProgramInfo aprograminfo[])
    {
        programInfos_ = aprograminfo;
    }

    public void setRecommend(boolean flag)
    {
        recommend_ = flag;
    }

    public void setServiceId(int i)
    {
        serviceId_ = i;
    }

    public void setServiceIds(int ai[])
    {
        serviceIds_ = ai;
    }

    public void setSince(long l)
    {
        since_ = l;
    }

    public void setUntil(long l)
    {
        until_ = l;
    }

    public void setUserId(String s)
    {
        userId_ = s;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(super.toString());
        stringbuilder.append(" { since_=");
        stringbuilder.append(since_);
        stringbuilder.append(", until_=");
        stringbuilder.append(until_);
        stringbuilder.append(", broadcastType_=");
        stringbuilder.append(getBroadcastTypeString());
        stringbuilder.append(", serviceIds_=");
        stringbuilder.append(Arrays.toString(serviceIds_));
        stringbuilder.append(", programInfos_=");
        stringbuilder.append(Arrays.toString(programInfos_));
        stringbuilder.append(", serviceId_=");
        stringbuilder.append(serviceId_);
        stringbuilder.append(", eventId_=");
        stringbuilder.append(eventId_);
        stringbuilder.append(", recommend_=");
        stringbuilder.append(recommend_);
        stringbuilder.append(", pixRecommend_=");
        stringbuilder.append(pixRecommend_);
        stringbuilder.append(", areaCode_=");
        stringbuilder.append(areaCode_);
        stringbuilder.append(", userId_=");
        stringbuilder.append(userId_);
        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    private int areaCode_;
    private BroadcastTypeT broadcastType_;
    private int eventId_;
    private boolean pixRecommend_;
    private ProgramInfo programInfos_[];
    private boolean recommend_;
    private int serviceId_;
    private int serviceIds_[];
    private long since_;
    private long until_;
    private String userId_;
}
