// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.message;


// Referenced classes of package jp.pixela.player_service.message:
//            ProgramData, TimerRecordData

public class ReservationData
{
    public static final class ReservationType extends Enum
    {

        public static ReservationType valueOf(String s)
        {
            return (ReservationType)Enum.valueOf(jp/pixela/player_service/message/ReservationData$ReservationType, s);
        }

        public static ReservationType[] values()
        {
            return (ReservationType[])$VALUES.clone();
        }

        public int getValue()
        {
            return mValue;
        }

        private static final ReservationType $VALUES[];
        public static final ReservationType DELETE;
        public static final ReservationType PROGRAM;
        public static final ReservationType TIMER;
        private final int mValue;

        static 
        {
            PROGRAM = new ReservationType("PROGRAM", 0, 0);
            TIMER = new ReservationType("TIMER", 1, 1);
            DELETE = new ReservationType("DELETE", 2, 2);
            $VALUES = (new ReservationType[] {
                PROGRAM, TIMER, DELETE
            });
        }

        private ReservationType(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }


    public ReservationData()
    {
        mReservationId = "";
    }

    public ProgramData getProgramData()
    {
        return mProgramData;
    }

    public String getReservationId()
    {
        return mReservationId;
    }

    public int getReservationType()
    {
        return mReservationType;
    }

    public TimerRecordData getTimerRecordData()
    {
        return mTimerRecData;
    }

    public void setProgramData(ProgramData programdata)
    {
        mProgramData = programdata;
    }

    public void setReservationId(String s)
    {
        mReservationId = s;
    }

    public void setReservationType(int i)
    {
        mReservationType = i;
    }

    public void setTimerRecordData(TimerRecordData timerrecorddata)
    {
        mTimerRecData = timerrecorddata;
    }

    public String toString()
    {
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append("ReservationData {ReservationId=");
        ((StringBuilder) (obj)).append(mReservationId);
        ((StringBuilder) (obj)).append(", ReservationType=");
        ((StringBuilder) (obj)).append(mReservationType);
        ((StringBuilder) (obj)).append(", ");
        ((StringBuilder) (obj)).append(mProgramData);
        if(((StringBuilder) (obj)).toString() != null)
        {
            obj = mProgramData.toString();
        } else
        {
            obj = new StringBuilder();
            ((StringBuilder) (obj)).append("no program data, ");
            ((StringBuilder) (obj)).append(mTimerRecData);
            if(((StringBuilder) (obj)).toString() != null)
                obj = mTimerRecData.toString();
            else
                obj = "no timer data}";
        }
        return ((String) (obj));
    }

    private ProgramData mProgramData;
    private String mReservationId;
    private int mReservationType;
    private TimerRecordData mTimerRecData;
}
