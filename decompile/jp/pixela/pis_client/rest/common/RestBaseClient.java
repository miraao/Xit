// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.common;

import java.net.HttpURLConnection;
import java.util.*;
import jp.pixela.common.PxLog;

// Referenced classes of package jp.pixela.pis_client.rest.common:
//            IRestClient, IRestConfig

public abstract class RestBaseClient
    implements IRestClient
{

    public RestBaseClient(IRestConfig irestconfig)
    {
        mConfig = null;
        mConfig = irestconfig;
    }

    private void writeRestLog(String s)
    {
        if(s != null)
        {
            int i = 0;
            do
            {
                int j = i;
                if(j > s.length() / 2000)
                    break;
                int k = j + 1;
                int l = k * 2000;
                i = l;
                if(s.length() < l)
                    i = s.length();
                PxLog.i(TAG, s.substring(j * 2000, i));
                i = k;
            } while(true);
        }
    }

    protected IRestConfig getRestConfig()
    {
        return mConfig;
    }

    protected int toRestResultCode(int i)
    {
        if(i == 200)
            i = 0;
        else
        if(i == 400)
            i = -6;
        else
        if(i == 401)
            i = -7;
        else
        if(i == 403)
            i = -8;
        else
        if(i == 404)
            i = -9;
        else
        if(i == 503)
            i = -10;
        else
            i = -1;
        return i;
    }

    protected void writeResponseLog(int i, String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("HTTP RESPONSE responseCode=");
        stringbuilder.append(i);
        stringbuilder = new StringBuilder(stringbuilder.toString());
        stringbuilder.append("\nbody:");
        if(s != null)
            stringbuilder.append(s);
        writeRestLog(stringbuilder.toString());
    }

    protected void writeSendLog(String s, HttpURLConnection httpurlconnection, String s1)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("HTTP SEND url=");
        stringbuilder.append(s);
        s = new StringBuilder(stringbuilder.toString());
        if(httpurlconnection != null)
        {
            s.append("\n     properties=");
            for(httpurlconnection = httpurlconnection.getRequestProperties().entrySet().iterator(); httpurlconnection.hasNext();)
            {
                Object obj = (java.util.Map.Entry)httpurlconnection.next();
                StringBuilder stringbuilder1 = new StringBuilder();
                stringbuilder1.append("\n         ");
                stringbuilder1.append((String)((java.util.Map.Entry) (obj)).getKey());
                stringbuilder1.append("=");
                s.append(stringbuilder1.toString());
                obj = ((List)((java.util.Map.Entry) (obj)).getValue()).iterator();
                while(((Iterator) (obj)).hasNext()) 
                {
                    String s2 = (String)((Iterator) (obj)).next();
                    StringBuilder stringbuilder2 = new StringBuilder();
                    stringbuilder2.append(s2);
                    stringbuilder2.append(", ");
                    s.append(stringbuilder2.toString());
                }
            }

        }
        if(s1 != null)
        {
            s.append("\n     request=");
            httpurlconnection = new StringBuilder();
            httpurlconnection.append("\n");
            httpurlconnection.append(s1);
            s.append(httpurlconnection.toString());
        }
        writeRestLog(s.toString());
    }

    private static final String TAG = "RestBaseClient";
    private IRestConfig mConfig;

}
