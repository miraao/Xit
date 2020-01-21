// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.utils;

import android.content.Context;
import android.os.Build;
import jp.pixela.pxlibs.utils.android.SystemPropertiesProxy;
import jp.pixela.pxlibs.utils.android.log.Logger;

public class BuildUtility
{

    private BuildUtility()
    {
        mIsRetail = false;
        mIsOem = false;
        initialize();
    }

    public static BuildUtility getInstance()
    {
        return mInstance;
    }

    private void initialize()
    {
        byte byte0;
label0:
        {
            String s = Build.MODEL;
            switch(s.hashCode())
            {
            default:
                break;

            case 1969745705: 
                if(s.equals("PIX-VP100"))
                {
                    byte0 = 1;
                    break label0;
                }
                break;

            case 844427065: 
                if(!s.equals("PIX-SMB410"))
                    break;
                byte0 = 2;
                break label0;

            case 844427034: 
                if(!s.equals("PIX-SMB400"))
                    break;
                byte0 = 0;
                break label0;

            case 380395092: 
                if(!s.equals("POE-SMB400-PS1"))
                    break;
                byte0 = 3;
                break label0;

            case 380385327: 
                if(!s.equals("POE-SMB400-FN1"))
                    break;
                byte0 = 4;
                break label0;
            }
            byte0 = -1;
        }
        switch(byte0)
        {
        case 3: // '\003'
        case 4: // '\004'
            mIsOem = true;
            break;

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
            mIsRetail = true;
            break;
        }
    }

    public static boolean isNetflixAvailable(Context context)
    {
        context = SystemPropertiesProxy.get(context, "ro.nrdp.modelgroup");
        return context != null && !context.isEmpty();
    }

    public static boolean isRecorder(Context context)
    {
        boolean flag;
        try
        {
            flag = SystemPropertiesProxy.getBoolean(context, "jp.pixela.system.recorder", false).booleanValue();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("exception occurred :");
            stringbuilder.append(context);
            Logger.e(stringbuilder.toString(), new Object[0]);
            flag = false;
        }
        return flag;
    }

    public static boolean isWifiAvailable(Context context)
    {
        boolean flag = false;
        int i;
        try
        {
            i = SystemPropertiesProxy.getInt(context, "jp.pixela.system.wifi_insmod", 0).intValue();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("convert to number err=");
            stringbuilder.append(context);
            Logger.e(stringbuilder.toString(), new Object[0]);
            i = 0;
        }
        if(i == 1)
            flag = true;
        return flag;
    }

    public boolean isOEM()
    {
        return mIsOem;
    }

    public boolean isPIXSMB400()
    {
        return Build.MODEL.equals("PIX-SMB400");
    }

    public boolean isPIXSMB410()
    {
        return Build.MODEL.equals("PIX-SMB410");
    }

    public boolean isPIXVP100()
    {
        return Build.MODEL.equals("PIX-VP100");
    }

    public boolean isPOESMB400FN1()
    {
        return Build.MODEL.equals("POE-SMB400-FN1");
    }

    public boolean isPOESMB400PS1()
    {
        return Build.MODEL.equals("POE-SMB400-PS1");
    }

    public boolean isRetail()
    {
        return mIsRetail;
    }

    public static final String MODEL_PIX_SMB400 = "PIX-SMB400";
    public static final String MODEL_PIX_SMB410 = "PIX-SMB410";
    public static final String MODEL_PIX_VP100 = "PIX-VP100";
    public static final String MODEL_POE_SMB400_FN1 = "POE-SMB400-FN1";
    public static final String MODEL_POE_SMB400_PS1 = "POE-SMB400-PS1";
    private static final String PROPERTY_KEY_RO_NRDP_MODELGROUP = "ro.nrdp.modelgroup";
    private static final String PROPERTY_KEY_SYSTEM_RECORDER = "jp.pixela.system.recorder";
    private static final String PROPERTY_KEY_SYSTEM_WIFI_INSMOD = "jp.pixela.system.wifi_insmod";
    private static BuildUtility mInstance = new BuildUtility();
    boolean mIsOem;
    boolean mIsRetail;

}
