// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.message;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jp.pixela.common.PxLog;

public class OutputDeviceInfo
{
    public static final class HdmiAudioOutputTypeT extends Enum
    {

        public static HdmiAudioOutputTypeT fromValue(int i)
        {
            HdmiAudioOutputTypeT ahdmiaudiooutputtypet[] = values();
            int j = ahdmiaudiooutputtypet.length;
            for(int k = 0; k < j; k++)
            {
                HdmiAudioOutputTypeT hdmiaudiooutputtypet = ahdmiaudiooutputtypet[k];
                if(hdmiaudiooutputtypet.toValue() == i)
                    return hdmiaudiooutputtypet;
            }

            return HDMI_AUDIO_OUTPUT_LPCM_2CH;
        }

        public static HdmiAudioOutputTypeT valueOf(String s)
        {
            return (HdmiAudioOutputTypeT)Enum.valueOf(jp/pixela/player_service/message/OutputDeviceInfo$HdmiAudioOutputTypeT, s);
        }

        public static HdmiAudioOutputTypeT[] values()
        {
            return (HdmiAudioOutputTypeT[])$VALUES.clone();
        }

        public int toValue()
        {
            return mValue;
        }

        private static final HdmiAudioOutputTypeT $VALUES[];
        public static final HdmiAudioOutputTypeT HDMI_AUDIO_OUTPUT_LPCM_2CH;
        public static final HdmiAudioOutputTypeT HDMI_AUDIO_OUTPUT_LPCM_6CH;
        public static final HdmiAudioOutputTypeT HDMI_AUDIO_OUTPUT_LPCM_8CH;
        private int mValue;

        static 
        {
            HDMI_AUDIO_OUTPUT_LPCM_2CH = new HdmiAudioOutputTypeT("HDMI_AUDIO_OUTPUT_LPCM_2CH", 0, 2);
            HDMI_AUDIO_OUTPUT_LPCM_6CH = new HdmiAudioOutputTypeT("HDMI_AUDIO_OUTPUT_LPCM_6CH", 1, 6);
            HDMI_AUDIO_OUTPUT_LPCM_8CH = new HdmiAudioOutputTypeT("HDMI_AUDIO_OUTPUT_LPCM_8CH", 2, 8);
            $VALUES = (new HdmiAudioOutputTypeT[] {
                HDMI_AUDIO_OUTPUT_LPCM_2CH, HDMI_AUDIO_OUTPUT_LPCM_6CH, HDMI_AUDIO_OUTPUT_LPCM_8CH
            });
        }

        private HdmiAudioOutputTypeT(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }


    public OutputDeviceInfo()
    {
        mHdmiAudioOutputType = HdmiAudioOutputTypeT.HDMI_AUDIO_OUTPUT_LPCM_2CH;
    }

    public HdmiAudioOutputTypeT getHdmiAudioOutputType()
    {
        Object obj;
        Process process;
        BufferedReader bufferedreader;
        int i;
        Object obj1;
        NumberFormatException numberformatexception;
        StringBuilder stringbuilder;
        boolean flag;
        int j;
        try
        {
            ProcessBuilder processbuilder = JVM INSTR new #31  <Class ProcessBuilder>;
            processbuilder.ProcessBuilder(new String[] {
                "cat", "/proc/msp/hdmi0_sink"
            });
            process = processbuilder.redirectErrorStream(true).start();
        }
        catch(IOException ioexception1)
        {
            return mHdmiAudioOutputType;
        }
        process.getInputStream();
        bufferedreader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        try
        {
            obj = Pattern.compile("^CodeType.*L-PCM.*MaxChannelNum.*:(.*)$");
        }
        catch(IOException ioexception)
        {
            return mHdmiAudioOutputType;
        }
        i = 0;
        obj1 = bufferedreader.readLine();
        if(obj1 == null)
            break; /* Loop/switch isn't completed */
        stringbuilder = JVM INSTR new #78  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        stringbuilder.append("OutputDeviceInfo: ");
        stringbuilder.append(((String) (obj1)));
        PxLog.v("OutputDeviceInfo", stringbuilder.toString());
        obj1 = ((Pattern) (obj)).matcher(((CharSequence) (obj1)));
        flag = ((Matcher) (obj1)).find();
        if(!flag)
            continue; /* Loop/switch isn't completed */
        j = Integer.parseInt(((Matcher) (obj1)).group(1).trim());
        break MISSING_BLOCK_LABEL_156;
        numberformatexception;
        j = 0;
        numberformatexception = JVM INSTR new #78  <Class StringBuilder>;
        numberformatexception.StringBuilder();
        numberformatexception.append("Detect MaxChannelNum=");
        numberformatexception.append(j);
        PxLog.d("OutputDeviceInfo", numberformatexception.toString());
        if(j > i)
            i = j;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_64;
_L1:
        process.destroy();
        if(i != 6)
        {
            if(i != 8)
                mHdmiAudioOutputType = HdmiAudioOutputTypeT.HDMI_AUDIO_OUTPUT_LPCM_2CH;
            else
                mHdmiAudioOutputType = HdmiAudioOutputTypeT.HDMI_AUDIO_OUTPUT_LPCM_8CH;
        } else
        {
            mHdmiAudioOutputType = HdmiAudioOutputTypeT.HDMI_AUDIO_OUTPUT_LPCM_6CH;
        }
        obj = new StringBuilder();
        ((StringBuilder) (obj)).append("detected HdmiAudioOutputType: ");
        ((StringBuilder) (obj)).append(mHdmiAudioOutputType);
        PxLog.d("OutputDeviceInfo", ((StringBuilder) (obj)).toString());
        return mHdmiAudioOutputType;
    }

    private static final String TAG = "OutputDeviceInfo";
    private HdmiAudioOutputTypeT mHdmiAudioOutputType;
}
