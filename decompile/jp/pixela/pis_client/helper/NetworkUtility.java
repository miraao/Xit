// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import jp.pixela.pxlibs.utils.android.log.Logger;

public class NetworkUtility
{

    private NetworkUtility()
    {
    }

    public static boolean isNetworkAvailable(Context context)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(LOG_HEAD);
        stringbuilder.append("in");
        Logger.v(stringbuilder.toString(), new Object[0]);
        context = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
        if(context == null)
        {
            context = new StringBuilder();
            context.append(LOG_HEAD);
            context.append("out. ret=false. activeNetworkInfo == null");
            Logger.v(context.toString(), new Object[0]);
            return false;
        }
        if(!context.isConnected())
        {
            context = new StringBuilder();
            context.append(LOG_HEAD);
            context.append("out. ret=false. !activeNetworkInfo.isConnected()");
            Logger.v(context.toString(), new Object[0]);
            return false;
        }
        if(!context.isAvailable())
        {
            context = new StringBuilder();
            context.append(LOG_HEAD);
            context.append("out. ret=false. !activeNetworkInfo.isAvailable()");
            Logger.v(context.toString(), new Object[0]);
            return false;
        } else
        {
            context = new StringBuilder();
            context.append(LOG_HEAD);
            context.append("out. ret=true");
            Logger.v(context.toString(), new Object[0]);
            return true;
        }
    }

    static final boolean $assertionsDisabled = false;
    private static final String LOG_HEAD;

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/pis_client/helper/NetworkUtility.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }
}
