// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service;

import java.util.ArrayList;

public class DeviceDefines
{

    public DeviceDefines()
    {
    }

    public static final int DT195W_TUNER_PRODUCT_ID = 4160;
    public static final int DT295W_TUNER_PRODUCT_ID = 4156;
    public static final int JMICRON_PRODUCT_ID = 1400;
    public static final int JMICRON_VENDOR_ID = 5421;
    public static final int PIXELA_TUNER_VENDOR_ID = 1720;
    public static final String PIX_SMB100_NAME = "PIX-SMB100";
    public static final int PIX_SMB100_PRODUCT_ID = 4164;
    public static final int PIX_SMB100_TUNER_PRODUCT_ID = 4161;
    public static final int PIX_SMB100_VENDOR_ID = 1720;
    public static final ArrayList sSupportTunerList = new ArrayList() {

            
            {
                add(new jp.pixela.common.HddFormatManager.HddDeviceInfo("PIX-SMB100", 1720, 4161));
            }
    }
;

}
