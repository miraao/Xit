// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.airtunerjni;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.*;
import android.util.Log;
import java.io.*;

public class PxjfClientService extends Service
{

    public PxjfClientService()
    {
    }

    private boolean ensureExistsCaCertFile(String s)
    {
        Object obj;
        Object obj1;
        obj = getApplicationContext().getResources().getAssets();
        if(obj == null)
        {
            Log.e("PxjfClientService", "Failed to get AssetManager.");
            return false;
        }
        obj1 = new StringBuilder();
        ((StringBuilder) (obj1)).append(s);
        ((StringBuilder) (obj1)).append("/cacert.pem");
        obj1 = ((StringBuilder) (obj1)).toString();
        s = JVM INSTR new #87  <Class File>;
        s.File(((String) (obj1)));
        if(s.exists())
            s.delete();
        if(s.createNewFile())
            break MISSING_BLOCK_LABEL_89;
        Log.e("PxjfClientService", "Failed to create cacert.pem");
        return false;
        obj1 = ((AssetManager) (obj)).open("cacert.pem");
        obj = JVM INSTR new #110 <Class FileOutputStream>;
        ((FileOutputStream) (obj)).FileOutputStream(s);
        s = new byte[1024];
_L1:
        int i = ((InputStream) (obj1)).read(s);
        if(i <= 0)
        {
            try
            {
                ((FileOutputStream) (obj)).close();
                ((InputStream) (obj1)).close();
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                s.printStackTrace();
                Log.e("PxjfClientService", "Failed to copy cacert.pem");
                return false;
            }
            return true;
        }
        ((FileOutputStream) (obj)).write(s, 0, i);
          goto _L1
    }

    private static native int restart(String s, int i, String s1, String s2, String s3, int j, String s4, String s5, 
            String s6, int k, int l, int i1, int j1, String s7);

    private static native void send_cmd(String s);

    private static native void set_app_dir(String s);

    private static native void start_stdio_forwarder();

    public IBinder onBind(Intent intent)
    {
        intent = intent.getExtras();
        set_app_dir((String)intent.get("app_dir"));
        ensureExistsCaCertFile((String)intent.get("app_dir"));
        return new Binder() {

            public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            {
                if(!PxjfClientService.existLib_)
                {
                    Log.e("PxjfClientService", "PxjfClientService.so is not loaded.");
                    return false;
                }
                switch(i)
                {
                default:
                    return false;

                case 1: // '\001'
                    PxjfClientService.send_cmd(parcel.readString());
                    break;

                case 0: // '\0'
                    parcel = parcel.readBundle();
                    i = PxjfClientService.restart((String)parcel.get("server"), parcel.getInt("port"), (String)parcel.get("xmpp_bare_jid"), (String)parcel.get("xmpp_passwd"), (String)parcel.get("id"), parcel.getInt("type"), (String)parcel.get("gateway"), (String)parcel.get("device_identification"), (String)parcel.get("login_marker_file"), parcel.getInt("keep_alive"), parcel.getInt("enable_debug_log"), parcel.getInt("upnp_duration"), parcel.getInt("mode"), (String)parcel.get("port_mapping"));
                    parcel = new StringBuilder();
                    parcel.append("restart_pxjfclient():ret=");
                    parcel.append(i);
                    Log.d("PxjfClientService", parcel.toString());
                    parcel1.writeInt(i);
                    break;
                }
                return true;
            }

            final PxjfClientService this$0;

            
            {
                this$0 = PxjfClientService.this;
                super();
            }
        }
;
    }

    public void onCreate()
    {
        start_stdio_forwarder();
    }

    public void onDestroy()
    {
    }

    public static final int RESTART = 0;
    public static final int SEND_CMD = 1;
    private static final String TAG = "PxjfClientService";
    private static boolean existLib_;

    static 
    {
        try
        {
            System.loadLibrary("PxjfClientService");
            existLib_ = true;
        }
        catch(Exception exception)
        {
            existLib_ = false;
        }
    }



}
