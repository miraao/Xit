// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.airtunerjni;

import android.content.*;
import android.net.*;
import android.os.Build;
import android.os.Environment;
import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.List;
import jp.pixela.pxlibs.utils.IntentHelper;
import jp.pixela.pxlibs.utils.android.log.Logger;
import jp.pixela.pxlibs.utils.android.log.LoggerRTM;

// Referenced classes of package jp.pixela.airtunerjni:
//            AirTunerServiceInterface

public class AirTunerJni
{

    AirTunerJni(AirTunerServiceInterface airtunerserviceinterface)
    {
        synchronized(lockService_)
        {
            service_ = airtunerserviceinterface;
            airtunerserviceinterface = JVM INSTR new #62  <Class IntentFilter>;
            airtunerserviceinterface.IntentFilter("android.intent.action.MEDIA_MOUNTED");
            airtunerserviceinterface.addDataScheme("file");
            service_.getContext().getApplicationContext().registerReceiver(mediaMountReceiver_, airtunerserviceinterface, null, null);
        }
        return;
        airtunerserviceinterface;
        obj;
        JVM INSTR monitorexit ;
        throw airtunerserviceinterface;
    }

    public static String getDefaultGatewayAddress(String s)
    {
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append(LOG_HEAD);
        ((StringBuilder) (obj)).append("enter getDefaultGatewayAddress(");
        ((StringBuilder) (obj)).append(s);
        ((StringBuilder) (obj)).append(")");
        Logger.d(((StringBuilder) (obj)).toString(), new Object[0]);
        if(s == null)
        {
            s = new StringBuilder();
            s.append(LOG_HEAD);
            s.append("if_name is null.");
            LoggerRTM.e(s.toString(), new Object[0]);
            return null;
        }
        obj = (ConnectivityManager)service_.getContext().getApplicationContext().getSystemService("connectivity");
        if(obj == null)
        {
            s = new StringBuilder();
            s.append(LOG_HEAD);
            s.append("ConnectivityManager is null.");
            LoggerRTM.e(s.toString(), new Object[0]);
            return null;
        }
        android.net.Network anetwork[] = ((ConnectivityManager) (obj)).getAllNetworks();
        if(anetwork == null)
        {
            s = new StringBuilder();
            s.append(LOG_HEAD);
            s.append("No Network is available.");
            LoggerRTM.e(s.toString(), new Object[0]);
            return null;
        }
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(LOG_HEAD);
        stringbuilder.append(anetwork.length);
        stringbuilder.append(" networks exists.");
        LoggerRTM.d(stringbuilder.toString(), new Object[0]);
        int i = anetwork.length;
label0:
        for(int j = 0; j < i; j++)
        {
            Object obj1 = ((ConnectivityManager) (obj)).getLinkProperties(anetwork[j]);
            if(obj1 == null)
                continue;
            Object obj2 = new StringBuilder();
            ((StringBuilder) (obj2)).append(LOG_HEAD);
            ((StringBuilder) (obj2)).append("Network Interface ");
            ((StringBuilder) (obj2)).append(((LinkProperties) (obj1)).getInterfaceName());
            ((StringBuilder) (obj2)).append(" is found.");
            LoggerRTM.d(((StringBuilder) (obj2)).toString(), new Object[0]);
            if(!s.equals(((LinkProperties) (obj1)).getInterfaceName()))
                continue;
            obj2 = ((LinkProperties) (obj1)).getRoutes().iterator();
            do
            {
                if(!((Iterator) (obj2)).hasNext())
                    continue label0;
                obj1 = (RouteInfo)((Iterator) (obj2)).next();
            } while(!((RouteInfo) (obj1)).isDefaultRoute() || !(((RouteInfo) (obj1)).getGateway() instanceof Inet4Address));
            s = new StringBuilder();
            s.append(LOG_HEAD);
            s.append("Default gateway is found: ");
            s.append(((RouteInfo) (obj1)).getGateway().getHostAddress());
            LoggerRTM.d(s.toString(), new Object[0]);
            return ((RouteInfo) (obj1)).getGateway().getHostAddress();
        }

        s = new StringBuilder();
        s.append(LOG_HEAD);
        s.append("No default gateway address is found.");
        LoggerRTM.e(s.toString(), new Object[0]);
        return null;
    }

    public static String getDeviceUniqueIdentifier()
    {
        return Build.SERIAL;
    }

    public static String getUsbStoragePath()
    {
        File afile[];
        int i;
        int j;
        afile = service_.getContext().getApplicationContext().getExternalFilesDirs(null);
        i = afile.length;
        j = 0;
_L3:
        if(j >= i) goto _L2; else goto _L1
_L1:
        Object obj1;
        obj1 = afile[j];
        if(obj1 == null)
            continue; /* Loop/switch isn't completed */
        if(!((File) (obj1)).isDirectory())
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(LOG_HEAD);
            stringbuilder.append("!f.isDirectory() f=");
            stringbuilder.append(obj1);
            Logger.d(stringbuilder.toString(), new Object[0]);
            continue; /* Loop/switch isn't completed */
        }
        if(!Environment.isExternalStorageRemovable(((File) (obj1))))
        {
            StringBuilder stringbuilder1 = JVM INSTR new #20  <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            stringbuilder1.append(LOG_HEAD);
            stringbuilder1.append("!Environment.isExternalStorageRemovable(f) f=");
            stringbuilder1.append(obj1);
            Logger.d(stringbuilder1.toString(), new Object[0]);
            continue; /* Loop/switch isn't completed */
        } else
        {
            String s = ((File) (obj1)).getAbsolutePath();
            obj1 = new StringBuilder();
            ((StringBuilder) (obj1)).append(LOG_HEAD);
            ((StringBuilder) (obj1)).append("path=");
            ((StringBuilder) (obj1)).append(s);
            Logger.d(((StringBuilder) (obj1)).toString(), new Object[0]);
            return s;
        }
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        StringBuilder stringbuilder2 = new StringBuilder();
        stringbuilder2.append(LOG_HEAD);
        stringbuilder2.append("e=");
        stringbuilder2.append(illegalargumentexception);
        Logger.d(stringbuilder2.toString(), new Object[0]);
        j++;
          goto _L3
_L2:
label0:
        {
            Object obj2;
            synchronized(lockPath_)
            {
                if(broadcasted_path_ == null)
                    break label0;
                obj2 = JVM INSTR new #20  <Class StringBuilder>;
                ((StringBuilder) (obj2)).StringBuilder();
                ((StringBuilder) (obj2)).append(LOG_HEAD);
                ((StringBuilder) (obj2)).append("path=");
                ((StringBuilder) (obj2)).append(broadcasted_path_);
                Logger.d(((StringBuilder) (obj2)).toString(), new Object[0]);
                obj2 = broadcasted_path_;
            }
            return ((String) (obj2));
        }
        obj;
        JVM INSTR monitorexit ;
        obj = new StringBuilder();
        ((StringBuilder) (obj)).append(LOG_HEAD);
        ((StringBuilder) (obj)).append("path=null");
        Logger.d(((StringBuilder) (obj)).toString(), new Object[0]);
        return null;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static void notifyStopAirTuner(int i)
    {
        if(service_ != null)
            service_.notifyStopAirTuner(i, true);
    }

    public static void restartPxDMSDaemon()
    {
        if(service_ != null)
            service_.restartPxDMSDaemon();
    }

    public static int restartPxjfClient(String s, int i, String s1, String s2, String s3, int j, String s4, String s5, 
            String s6, int k, int l, int i1, int j1, String s7)
    {
        if(service_ != null)
        {
            return service_.restartPxjfClient(s, i, s1, s2, s3, j, s4, s5, s6, k, l, i1, j1, s7);
        } else
        {
            s = new StringBuilder();
            s.append(LOG_HEAD);
            s.append("AirTunerService is not attached!!");
            LoggerRTM.e(s.toString(), new Object[0]);
            return -1;
        }
    }

    public native int startNt(String s);

    public native int stopNt();

    private static final String LOG_HEAD;
    private static String broadcasted_path_;
    private static final Object lockPath_ = new Object();
    private static final Object lockService_ = new Object();
    private static final BroadcastReceiver mediaMountReceiver_ = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent)
        {
label0:
            {
                context = new StringBuilder();
                context.append(AirTunerJni.LOG_HEAD);
                context.append("in. intent=");
                context.append(IntentHelper.dump(intent));
                LoggerRTM.d(context.toString(), new Object[0]);
                if(intent == null)
                    return;
                if(!"android.intent.action.MEDIA_MOUNTED".equals(intent.getAction()))
                    return;
                intent = intent.getData();
                if(intent == null)
                    return;
                context = new File(intent.getPath());
                if(!context.isDirectory())
                {
                    intent = new StringBuilder();
                    intent.append(AirTunerJni.LOG_HEAD);
                    intent.append("!f.isDirectory() f=");
                    intent.append(context);
                    LoggerRTM.d(intent.toString(), new Object[0]);
                    return;
                }
                Object obj;
                try
                {
                    if(!Environment.isExternalStorageRemovable(context))
                    {
                        intent = JVM INSTR new #17  <Class StringBuilder>;
                        intent.StringBuilder();
                        intent.append(AirTunerJni.LOG_HEAD);
                        intent.append("!Environment.isExternalStorageRemovable(f) f=");
                        intent.append(context);
                        LoggerRTM.d(intent.toString(), new Object[0]);
                        return;
                    }
                }
                // Misplaced declaration of an exception variable
                catch(Context context)
                {
                    intent = new StringBuilder();
                    intent.append(AirTunerJni.LOG_HEAD);
                    intent.append("e=");
                    intent.append(context);
                    LoggerRTM.d(intent.toString(), new Object[0]);
                    return;
                }
                synchronized(AirTunerJni.lockPath_)
                {
                    if(AirTunerJni.broadcasted_path_ == null)
                        break label0;
                }
                return;
            }
            obj = JVM INSTR new #17  <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            ((StringBuilder) (obj)).append(intent.getPath());
            ((StringBuilder) (obj)).append("/Android/data/");
            ((StringBuilder) (obj)).append(AirTunerJni.service_.getContext().getApplicationContext().getPackageName());
            ((StringBuilder) (obj)).append("/files");
            intent = ((StringBuilder) (obj)).toString();
            obj = JVM INSTR new #64  <Class File>;
            ((File) (obj)).File(intent);
            if(((File) (obj)).exists())
                break MISSING_BLOCK_LABEL_271;
            context;
            JVM INSTR monitorexit ;
            return;
            AirTunerJni.broadcasted_path_ = intent;
            intent = JVM INSTR new #17  <Class StringBuilder>;
            intent.StringBuilder();
            intent.append(AirTunerJni.LOG_HEAD);
            intent.append("broadcasted_path_=");
            intent.append(AirTunerJni.broadcasted_path_);
            LoggerRTM.d(intent.toString(), new Object[0]);
            context;
            JVM INSTR monitorexit ;
            return;
            intent;
            context;
            JVM INSTR monitorexit ;
            throw intent;
        }

    }
;
    private static AirTunerServiceInterface service_;

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/airtunerjni/AirTunerJni.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
        System.loadLibrary("airtuner");
    }





/*
    static String access$202(String s)
    {
        broadcasted_path_ = s;
        return s;
    }

*/

}
