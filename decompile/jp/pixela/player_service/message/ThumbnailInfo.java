// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.message;


// Referenced classes of package jp.pixela.player_service.message:
//            ProgramInfo

public class ThumbnailInfo
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
            return (BroadcastTypeT)Enum.valueOf(jp/pixela/player_service/message/ThumbnailInfo$BroadcastTypeT, s);
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

    public static final class ThumbnailTypeT extends Enum
    {

        public static ThumbnailTypeT fromValue(int i)
        {
            ThumbnailTypeT thumbnailtypet = UNKNOWN;
            ThumbnailTypeT athumbnailtypet[] = values();
            int j = athumbnailtypet.length;
            int k = 0;
            ThumbnailTypeT thumbnailtypet1;
            do
            {
                thumbnailtypet1 = thumbnailtypet;
                if(k >= j)
                    break;
                thumbnailtypet1 = athumbnailtypet[k];
                if(thumbnailtypet1.toValue() == i)
                    break;
                k++;
            } while(true);
            return thumbnailtypet1;
        }

        public static ThumbnailTypeT valueOf(String s)
        {
            return (ThumbnailTypeT)Enum.valueOf(jp/pixela/player_service/message/ThumbnailInfo$ThumbnailTypeT, s);
        }

        public static ThumbnailTypeT[] values()
        {
            return (ThumbnailTypeT[])$VALUES.clone();
        }

        public int toValue()
        {
            return mValue;
        }

        private static final ThumbnailTypeT $VALUES[];
        public static final ThumbnailTypeT DEFAULT;
        public static final ThumbnailTypeT GENRE;
        public static final ThumbnailTypeT PIS;
        public static final ThumbnailTypeT UNKNOWN;
        private int mValue;

        static 
        {
            UNKNOWN = new ThumbnailTypeT("UNKNOWN", 0, 0);
            DEFAULT = new ThumbnailTypeT("DEFAULT", 1, 1);
            GENRE = new ThumbnailTypeT("GENRE", 2, 2);
            PIS = new ThumbnailTypeT("PIS", 3, 3);
            $VALUES = (new ThumbnailTypeT[] {
                UNKNOWN, DEFAULT, GENRE, PIS
            });
        }

        private ThumbnailTypeT(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }


    public ThumbnailInfo()
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

            static final int $SwitchMap$jp$pixela$player_service$message$ThumbnailInfo$BroadcastTypeT[];

            static 
            {
                $SwitchMap$jp$pixela$player_service$message$ThumbnailInfo$BroadcastTypeT = new int[BroadcastTypeT.values().length];
                try
                {
                    $SwitchMap$jp$pixela$player_service$message$ThumbnailInfo$BroadcastTypeT[BroadcastTypeT.TR.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$jp$pixela$player_service$message$ThumbnailInfo$BroadcastTypeT[BroadcastTypeT.BS.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                $SwitchMap$jp$pixela$player_service$message$ThumbnailInfo$BroadcastTypeT[BroadcastTypeT.CS.ordinal()] = 3;
_L2:
                return;
                NoSuchFieldError nosuchfielderror2;
                nosuchfielderror2;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        String s;
        switch(_cls1..SwitchMap.jp.pixela.player_service.message.ThumbnailInfo.BroadcastTypeT[broadcastType_.ordinal()])
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

    public byte[] getImage()
    {
        return image_;
    }

    public int getImageHeight()
    {
        return imageHeight_;
    }

    public int getImageWidth()
    {
        return imageWidth_;
    }

    public boolean getNeedGenreImage()
    {
        return needGenreImage_;
    }

    public ProgramInfo[] getProgramInfos()
    {
        return programInfos_;
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

    public ThumbnailTypeT getThumbnailType()
    {
        return thumbnailType_;
    }

    public long getUntil()
    {
        return until_;
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

    public void setImage(byte abyte0[])
    {
        image_ = abyte0;
    }

    public void setImageHeight(int i)
    {
        imageHeight_ = i;
    }

    public void setImageWidth(int i)
    {
        imageWidth_ = i;
    }

    public void setNeedGenreImage(boolean flag)
    {
        needGenreImage_ = flag;
    }

    public void setProgramInfos(ProgramInfo aprograminfo[])
    {
        programInfos_ = aprograminfo;
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

    public void setThumbnailType(ThumbnailTypeT thumbnailtypet)
    {
        thumbnailType_ = thumbnailtypet;
    }

    public void setUntil(long l)
    {
        until_ = l;
    }

    private BroadcastTypeT broadcastType_;
    private int imageHeight_;
    private int imageWidth_;
    private byte image_[];
    private boolean needGenreImage_;
    private ProgramInfo programInfos_[];
    private int serviceId_;
    private int serviceIds_[];
    private long since_;
    private ThumbnailTypeT thumbnailType_;
    private long until_;
}
