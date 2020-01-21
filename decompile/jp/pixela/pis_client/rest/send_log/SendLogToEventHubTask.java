// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.send_log;

import android.os.AsyncTask;
import android.util.Base64;
import java.io.*;
import java.net.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.*;
import jp.pixela.common.PxLog;
import jp.pixela.pis_client.rest.utility.EncryptionUtility;
import jp.pixela.pxlibs.utils.android.log.LoggerRTM;

public class SendLogToEventHubTask extends AsyncTask
{

    public SendLogToEventHubTask()
    {
    }

    private static String GetSASToken(String s, String s1, String s2)
    {
        String s3 = Long.toString(System.currentTimeMillis() / 1000L + (long)0x93a80);
        try
        {
            StringBuilder stringbuilder = JVM INSTR new #29  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            stringbuilder.append(URLEncoder.encode(s, "UTF-8"));
            stringbuilder.append("\n");
            stringbuilder.append(s3);
            s2 = getHMAC256(s2, stringbuilder.toString());
            stringbuilder = JVM INSTR new #29  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            stringbuilder.append("SharedAccessSignature sr=");
            stringbuilder.append(URLEncoder.encode(s, "UTF-8"));
            stringbuilder.append("&sig=");
            stringbuilder.append(URLEncoder.encode(s2, "UTF-8"));
            stringbuilder.append("&se=");
            stringbuilder.append(s3);
            stringbuilder.append("&skn=");
            stringbuilder.append(s1);
            s = stringbuilder.toString();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s1 = new StringBuilder();
            s1.append(LOG_HEAD);
            s1.append("e=");
            s1.append(s);
            LoggerRTM.e(s1.toString(), new Object[0]);
            s = null;
        }
        return s;
    }

    public static String getHMAC256(String s, String s1)
    {
label0:
        {
            try
            {
                Mac mac = Mac.getInstance("HmacSHA256");
                SecretKeySpec secretkeyspec = JVM INSTR new #122 <Class SecretKeySpec>;
                secretkeyspec.SecretKeySpec(s.getBytes("UTF-8"), "HmacSHA256");
                mac.init(secretkeyspec);
                s = Base64.encodeToString(mac.doFinal(s1.getBytes("UTF-8")), 0).replace("\n", "");
                break label0;
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                s1 = new StringBuilder();
                s1.append(LOG_HEAD);
                s1.append("e=");
                s1.append(s);
                LoggerRTM.e(s1.toString(), new Object[0]);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                s1 = new StringBuilder();
                s1.append(LOG_HEAD);
                s1.append("e=");
                s1.append(s);
                LoggerRTM.e(s1.toString(), new Object[0]);
            }
            // Misplaced declaration of an exception variable
            catch(String s1)
            {
                s = new StringBuilder();
                s.append(LOG_HEAD);
                s.append("e=");
                s.append(s1);
                LoggerRTM.e(s.toString(), new Object[0]);
            }
            // Misplaced declaration of an exception variable
            catch(String s1)
            {
                s = new StringBuilder();
                s.append(LOG_HEAD);
                s.append("e=");
                s.append(s1);
                LoggerRTM.e(s.toString(), new Object[0]);
            }
            s = null;
        }
        return s;
    }

    private static int sendLog(String s)
    {
        jp/pixela/pis_client/rest/send_log/SendLogToEventHubTask;
        JVM INSTR monitorenter ;
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        Object obj9;
        Object obj10;
        Object obj11;
        HttpURLConnection httpurlconnection;
        Object obj12;
        Object obj13;
        Object obj14;
        Object obj15;
        Object obj16;
        Object obj17;
        Object obj18;
        Object obj19;
        Object obj20;
        int i;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        obj5 = null;
        obj6 = null;
        obj7 = null;
        obj8 = null;
        obj9 = null;
        obj10 = null;
        obj11 = null;
        httpurlconnection = null;
        obj12 = null;
        obj13 = null;
        obj14 = null;
        obj15 = null;
        obj16 = null;
        obj17 = null;
        obj18 = null;
        obj19 = null;
        obj20 = null;
        i = -1;
        Object obj21;
        obj21 = JVM INSTR new #173 <Class URL>;
        ((URL) (obj21)).URL("https://pixela-dtv.servicebus.windows.net/syslog/messages");
        obj21 = (HttpURLConnection)((URL) (obj21)).openConnection();
        Object obj22;
        int j;
        if(obj21 == null)
            break MISSING_BLOCK_LABEL_1663;
        obj22 = obj;
        obj15 = obj1;
        obj17 = obj2;
        obj16 = obj3;
        obj9 = obj4;
        obj18 = obj5;
        obj14 = obj6;
        obj13 = obj7;
        obj12 = obj8;
        obj11 = obj19;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        StringBuilder stringbuilder;
        int k;
        try
        {
            ((HttpURLConnection) (obj21)).setRequestMethod("POST");
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            obj10 = obj12;
            break MISSING_BLOCK_LABEL_3379;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            obj10 = obj13;
            break MISSING_BLOCK_LABEL_3175;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            obj10 = obj14;
            break MISSING_BLOCK_LABEL_2971;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            obj10 = obj18;
            break MISSING_BLOCK_LABEL_2767;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            obj10 = obj9;
            break MISSING_BLOCK_LABEL_2567;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            obj10 = obj16;
            break MISSING_BLOCK_LABEL_2363;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            obj10 = obj17;
            break MISSING_BLOCK_LABEL_2163;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            obj10 = obj15;
            break MISSING_BLOCK_LABEL_1963;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            obj10 = obj22;
            break MISSING_BLOCK_LABEL_1763;
        }
        obj22 = obj;
        obj15 = obj1;
        obj17 = obj2;
        obj16 = obj3;
        obj9 = obj4;
        obj18 = obj5;
        obj14 = obj6;
        obj13 = obj7;
        obj12 = obj8;
        obj11 = obj19;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((HttpURLConnection) (obj21)).setInstanceFollowRedirects(false);
        obj22 = obj;
        obj15 = obj1;
        obj17 = obj2;
        obj16 = obj3;
        obj9 = obj4;
        obj18 = obj5;
        obj14 = obj6;
        obj13 = obj7;
        obj12 = obj8;
        obj11 = obj19;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((HttpURLConnection) (obj21)).setDoOutput(true);
        obj22 = obj;
        obj15 = obj1;
        obj17 = obj2;
        obj16 = obj3;
        obj9 = obj4;
        obj18 = obj5;
        obj14 = obj6;
        obj13 = obj7;
        obj12 = obj8;
        obj11 = obj19;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((HttpURLConnection) (obj21)).setRequestProperty("Accept-Language", "jp");
        obj22 = obj;
        obj15 = obj1;
        obj17 = obj2;
        obj16 = obj3;
        obj9 = obj4;
        obj18 = obj5;
        obj14 = obj6;
        obj13 = obj7;
        obj12 = obj8;
        obj11 = obj19;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((HttpURLConnection) (obj21)).setRequestProperty("Content-Type", "application/json");
        obj15 = obj1;
        obj17 = obj2;
        obj16 = obj3;
        obj9 = obj4;
        obj18 = obj5;
        obj14 = obj6;
        obj13 = obj7;
        obj12 = obj8;
        obj11 = obj19;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        obj10 = Base64.encodeToString(EncryptionUtility.decrypt("hoEht/0XV3+R0pYQj93+vBp4OSDJjCtiW/6RwPKsM5YnEGGhh9573hhK+7Ufos4p"), 0).replace("\n", "");
        break MISSING_BLOCK_LABEL_744;
        obj10;
        obj22 = obj;
        obj15 = obj1;
        obj17 = obj2;
        obj16 = obj3;
        obj9 = obj4;
        obj18 = obj5;
        obj14 = obj6;
        obj13 = obj7;
        obj12 = obj8;
        obj11 = obj19;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        stringbuilder = JVM INSTR new #29  <Class StringBuilder>;
        obj22 = obj;
        obj15 = obj1;
        obj17 = obj2;
        obj16 = obj3;
        obj9 = obj4;
        obj18 = obj5;
        obj14 = obj6;
        obj13 = obj7;
        obj12 = obj8;
        obj11 = obj19;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        stringbuilder.StringBuilder();
        obj22 = obj;
        obj15 = obj1;
        obj17 = obj2;
        obj16 = obj3;
        obj9 = obj4;
        obj18 = obj5;
        obj14 = obj6;
        obj13 = obj7;
        obj12 = obj8;
        obj11 = obj19;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        stringbuilder.append(LOG_HEAD);
        obj22 = obj;
        obj15 = obj1;
        obj17 = obj2;
        obj16 = obj3;
        obj9 = obj4;
        obj18 = obj5;
        obj14 = obj6;
        obj13 = obj7;
        obj12 = obj8;
        obj11 = obj19;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        stringbuilder.append("e=");
        obj22 = obj;
        obj15 = obj1;
        obj17 = obj2;
        obj16 = obj3;
        obj9 = obj4;
        obj18 = obj5;
        obj14 = obj6;
        obj13 = obj7;
        obj12 = obj8;
        obj11 = obj19;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        stringbuilder.append(obj10);
        obj22 = obj;
        obj15 = obj1;
        obj17 = obj2;
        obj16 = obj3;
        obj9 = obj4;
        obj18 = obj5;
        obj14 = obj6;
        obj13 = obj7;
        obj12 = obj8;
        obj11 = obj19;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        LoggerRTM.e(stringbuilder.toString(), new Object[0]);
        obj10 = null;
        obj22 = obj;
        obj15 = obj1;
        obj17 = obj2;
        obj16 = obj3;
        obj9 = obj4;
        obj18 = obj5;
        obj14 = obj6;
        obj13 = obj7;
        obj12 = obj8;
        obj11 = obj19;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((HttpURLConnection) (obj21)).setRequestProperty("Authorization", GetSASToken("https://pixela-dtv.servicebus.windows.net/syslog/messages", "SendOnly", ((String) (obj10))));
        obj22 = obj;
        obj15 = obj1;
        obj17 = obj2;
        obj16 = obj3;
        obj9 = obj4;
        obj18 = obj5;
        obj14 = obj6;
        obj13 = obj7;
        obj12 = obj8;
        obj11 = obj19;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((HttpURLConnection) (obj21)).setConnectTimeout(5000);
        obj22 = obj;
        obj15 = obj1;
        obj17 = obj2;
        obj16 = obj3;
        obj9 = obj4;
        obj18 = obj5;
        obj14 = obj6;
        obj13 = obj7;
        obj12 = obj8;
        obj11 = obj19;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((HttpURLConnection) (obj21)).setReadTimeout(5000);
        obj22 = obj;
        obj15 = obj1;
        obj17 = obj2;
        obj16 = obj3;
        obj9 = obj4;
        obj18 = obj5;
        obj14 = obj6;
        obj13 = obj7;
        obj12 = obj8;
        obj11 = obj19;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        if(!(obj21 instanceof HttpsURLConnection))
            break MISSING_BLOCK_LABEL_1114;
        obj22 = obj;
        obj15 = obj1;
        obj17 = obj2;
        obj16 = obj3;
        obj9 = obj4;
        obj18 = obj5;
        obj14 = obj6;
        obj13 = obj7;
        obj12 = obj8;
        obj11 = obj19;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        obj10 = JVM INSTR new #7   <Class SendLogToEventHubTask$1>;
        obj22 = obj;
        obj15 = obj1;
        obj17 = obj2;
        obj16 = obj3;
        obj9 = obj4;
        obj18 = obj5;
        obj14 = obj6;
        obj13 = obj7;
        obj12 = obj8;
        obj11 = obj19;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((_cls1) (obj10))._cls1();
        obj22 = obj;
        obj15 = obj1;
        obj17 = obj2;
        obj16 = obj3;
        obj9 = obj4;
        obj18 = obj5;
        obj14 = obj6;
        obj13 = obj7;
        obj12 = obj8;
        obj11 = obj19;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        HttpsURLConnection.setDefaultHostnameVerifier(((HostnameVerifier) (obj10)));
        obj22 = obj;
        obj15 = obj1;
        obj17 = obj2;
        obj16 = obj3;
        obj9 = obj4;
        obj18 = obj5;
        obj14 = obj6;
        obj13 = obj7;
        obj12 = obj8;
        obj11 = obj19;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((HttpURLConnection) (obj21)).connect();
        obj10 = obj20;
        if(s == null)
            break MISSING_BLOCK_LABEL_1340;
        obj22 = obj;
        obj15 = obj1;
        obj17 = obj2;
        obj16 = obj3;
        obj9 = obj4;
        obj18 = obj5;
        obj14 = obj6;
        obj13 = obj7;
        obj12 = obj8;
        obj11 = obj19;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        obj10 = JVM INSTR new #234 <Class OutputStreamWriter>;
        obj22 = obj;
        obj15 = obj1;
        obj17 = obj2;
        obj16 = obj3;
        obj9 = obj4;
        obj18 = obj5;
        obj14 = obj6;
        obj13 = obj7;
        obj12 = obj8;
        obj11 = obj19;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((OutputStreamWriter) (obj10)).OutputStreamWriter(((HttpURLConnection) (obj21)).getOutputStream(), "UTF-8");
        try
        {
            ((OutputStreamWriter) (obj10)).write(s.toString());
            ((OutputStreamWriter) (obj10)).flush();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            break MISSING_BLOCK_LABEL_3379;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            break MISSING_BLOCK_LABEL_3175;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            break MISSING_BLOCK_LABEL_2971;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            break MISSING_BLOCK_LABEL_2767;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            break MISSING_BLOCK_LABEL_2567;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            break MISSING_BLOCK_LABEL_2363;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            break MISSING_BLOCK_LABEL_2163;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            break MISSING_BLOCK_LABEL_1963;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            break MISSING_BLOCK_LABEL_1763;
        }
        break MISSING_BLOCK_LABEL_1340;
        s;
        j = i;
        break MISSING_BLOCK_LABEL_3571;
        obj22 = obj10;
        obj15 = obj10;
        obj17 = obj10;
        obj16 = obj10;
        obj9 = obj10;
        obj18 = obj10;
        obj14 = obj10;
        obj13 = obj10;
        obj12 = obj10;
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        k = ((HttpURLConnection) (obj21)).getResponseCode();
        try
        {
            obj11 = TAG;
            s = JVM INSTR new #29  <Class StringBuilder>;
            s.StringBuilder();
            s.append("http communication : responseCode=");
            s.append(k);
            PxLog.i(((String) (obj11)), s.toString());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            i = k;
            break MISSING_BLOCK_LABEL_3379;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            i = k;
            break MISSING_BLOCK_LABEL_3175;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            i = k;
            break MISSING_BLOCK_LABEL_2971;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            i = k;
            break MISSING_BLOCK_LABEL_2767;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            i = k;
            break MISSING_BLOCK_LABEL_2567;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            i = k;
            break MISSING_BLOCK_LABEL_2363;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            i = k;
            break MISSING_BLOCK_LABEL_2163;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            i = k;
            break MISSING_BLOCK_LABEL_1963;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            i = k;
            break MISSING_BLOCK_LABEL_1763;
        }
        s = ((String) (obj10));
        j = k;
        if(k == 200)
            break MISSING_BLOCK_LABEL_1669;
        s = ((String) (obj10));
        j = k;
        if(k == 201)
            break MISSING_BLOCK_LABEL_1669;
        s = TAG;
        obj11 = JVM INSTR new #29  <Class StringBuilder>;
        ((StringBuilder) (obj11)).StringBuilder();
        ((StringBuilder) (obj11)).append("Error. Json http communication error: ");
        ((StringBuilder) (obj11)).append(k);
        PxLog.e(s, ((StringBuilder) (obj11)).toString());
        s = ((String) (obj10));
        j = k;
        break MISSING_BLOCK_LABEL_1669;
        s;
        j = k;
        break MISSING_BLOCK_LABEL_3571;
        j = -1;
        s = ((String) (obj9));
        if(s == null)
            break MISSING_BLOCK_LABEL_1728;
        try
        {
            s.close();
            break MISSING_BLOCK_LABEL_1728;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        obj10 = JVM INSTR new #29  <Class StringBuilder>;
        ((StringBuilder) (obj10)).StringBuilder();
        ((StringBuilder) (obj10)).append(LOG_HEAD);
        ((StringBuilder) (obj10)).append("e=");
        ((StringBuilder) (obj10)).append(s);
        LoggerRTM.e(((StringBuilder) (obj10)).toString(), new Object[0]);
        if(obj21 == null)
            break MISSING_BLOCK_LABEL_1738;
        ((HttpURLConnection) (obj21)).disconnect();
        jp/pixela/pis_client/rest/send_log/SendLogToEventHubTask;
        JVM INSTR monitorexit ;
        return j;
        s;
        obj21 = null;
        obj10 = obj18;
        j = i;
        break MISSING_BLOCK_LABEL_3571;
        s;
        obj21 = null;
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        obj12 = JVM INSTR new #29  <Class StringBuilder>;
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).StringBuilder();
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append(LOG_HEAD);
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append("e=");
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append(s);
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        LoggerRTM.e(((StringBuilder) (obj12)).toString(), new Object[0]);
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_1939;
        try
        {
            ((OutputStreamWriter) (obj10)).close();
            break MISSING_BLOCK_LABEL_1939;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj10) { }
        s = JVM INSTR new #29  <Class StringBuilder>;
        s.StringBuilder();
        s.append(LOG_HEAD);
        s.append("e=");
        s.append(obj10);
        LoggerRTM.e(s.toString(), new Object[0]);
        if(obj21 == null)
            break MISSING_BLOCK_LABEL_1949;
        ((HttpURLConnection) (obj21)).disconnect();
        jp/pixela/pis_client/rest/send_log/SendLogToEventHubTask;
        JVM INSTR monitorexit ;
        return i;
        s;
        obj21 = null;
        obj10 = obj11;
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        obj12 = JVM INSTR new #29  <Class StringBuilder>;
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).StringBuilder();
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append(LOG_HEAD);
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append("e=");
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append(s);
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        LoggerRTM.e(((StringBuilder) (obj12)).toString(), new Object[0]);
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_2139;
        try
        {
            ((OutputStreamWriter) (obj10)).close();
            break MISSING_BLOCK_LABEL_2139;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj10) { }
        s = JVM INSTR new #29  <Class StringBuilder>;
        s.StringBuilder();
        s.append(LOG_HEAD);
        s.append("e=");
        s.append(obj10);
        LoggerRTM.e(s.toString(), new Object[0]);
        if(obj21 == null)
            break MISSING_BLOCK_LABEL_2149;
        ((HttpURLConnection) (obj21)).disconnect();
        jp/pixela/pis_client/rest/send_log/SendLogToEventHubTask;
        JVM INSTR monitorexit ;
        return i;
        s;
        obj21 = null;
        obj10 = httpurlconnection;
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        obj12 = JVM INSTR new #29  <Class StringBuilder>;
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).StringBuilder();
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append(LOG_HEAD);
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append("e=");
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append(s);
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        LoggerRTM.e(((StringBuilder) (obj12)).toString(), new Object[0]);
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_2339;
        try
        {
            ((OutputStreamWriter) (obj10)).close();
            break MISSING_BLOCK_LABEL_2339;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj10) { }
        s = JVM INSTR new #29  <Class StringBuilder>;
        s.StringBuilder();
        s.append(LOG_HEAD);
        s.append("e=");
        s.append(obj10);
        LoggerRTM.e(s.toString(), new Object[0]);
        if(obj21 == null)
            break MISSING_BLOCK_LABEL_2349;
        ((HttpURLConnection) (obj21)).disconnect();
        jp/pixela/pis_client/rest/send_log/SendLogToEventHubTask;
        JVM INSTR monitorexit ;
        return i;
        s;
        obj21 = null;
        obj10 = obj12;
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        obj12 = JVM INSTR new #29  <Class StringBuilder>;
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).StringBuilder();
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append(LOG_HEAD);
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append("e=");
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append(s);
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        LoggerRTM.e(((StringBuilder) (obj12)).toString(), new Object[0]);
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_2543;
        try
        {
            ((OutputStreamWriter) (obj10)).close();
            break MISSING_BLOCK_LABEL_2543;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        obj10 = JVM INSTR new #29  <Class StringBuilder>;
        ((StringBuilder) (obj10)).StringBuilder();
        ((StringBuilder) (obj10)).append(LOG_HEAD);
        ((StringBuilder) (obj10)).append("e=");
        ((StringBuilder) (obj10)).append(s);
        LoggerRTM.e(((StringBuilder) (obj10)).toString(), new Object[0]);
        if(obj21 == null)
            break MISSING_BLOCK_LABEL_2553;
        ((HttpURLConnection) (obj21)).disconnect();
        jp/pixela/pis_client/rest/send_log/SendLogToEventHubTask;
        JVM INSTR monitorexit ;
        return i;
        s;
        obj21 = null;
        obj10 = obj13;
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        obj12 = JVM INSTR new #29  <Class StringBuilder>;
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).StringBuilder();
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append(LOG_HEAD);
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append("e=");
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append(s);
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        LoggerRTM.e(((StringBuilder) (obj12)).toString(), new Object[0]);
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_2743;
        try
        {
            ((OutputStreamWriter) (obj10)).close();
            break MISSING_BLOCK_LABEL_2743;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj10) { }
        s = JVM INSTR new #29  <Class StringBuilder>;
        s.StringBuilder();
        s.append(LOG_HEAD);
        s.append("e=");
        s.append(obj10);
        LoggerRTM.e(s.toString(), new Object[0]);
        if(obj21 == null)
            break MISSING_BLOCK_LABEL_2753;
        ((HttpURLConnection) (obj21)).disconnect();
        jp/pixela/pis_client/rest/send_log/SendLogToEventHubTask;
        JVM INSTR monitorexit ;
        return i;
        s;
        obj21 = null;
        obj10 = obj14;
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        obj12 = JVM INSTR new #29  <Class StringBuilder>;
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).StringBuilder();
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append(LOG_HEAD);
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append("e=");
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append(s);
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        LoggerRTM.e(((StringBuilder) (obj12)).toString(), new Object[0]);
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_2947;
        try
        {
            ((OutputStreamWriter) (obj10)).close();
            break MISSING_BLOCK_LABEL_2947;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        obj10 = JVM INSTR new #29  <Class StringBuilder>;
        ((StringBuilder) (obj10)).StringBuilder();
        ((StringBuilder) (obj10)).append(LOG_HEAD);
        ((StringBuilder) (obj10)).append("e=");
        ((StringBuilder) (obj10)).append(s);
        LoggerRTM.e(((StringBuilder) (obj10)).toString(), new Object[0]);
        if(obj21 == null)
            break MISSING_BLOCK_LABEL_2957;
        ((HttpURLConnection) (obj21)).disconnect();
        jp/pixela/pis_client/rest/send_log/SendLogToEventHubTask;
        JVM INSTR monitorexit ;
        return i;
        s;
        obj21 = null;
        obj10 = obj15;
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        obj12 = JVM INSTR new #29  <Class StringBuilder>;
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).StringBuilder();
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append(LOG_HEAD);
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append("e=");
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append(s);
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        LoggerRTM.e(((StringBuilder) (obj12)).toString(), new Object[0]);
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_3151;
        try
        {
            ((OutputStreamWriter) (obj10)).close();
            break MISSING_BLOCK_LABEL_3151;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        obj10 = JVM INSTR new #29  <Class StringBuilder>;
        ((StringBuilder) (obj10)).StringBuilder();
        ((StringBuilder) (obj10)).append(LOG_HEAD);
        ((StringBuilder) (obj10)).append("e=");
        ((StringBuilder) (obj10)).append(s);
        LoggerRTM.e(((StringBuilder) (obj10)).toString(), new Object[0]);
        if(obj21 == null)
            break MISSING_BLOCK_LABEL_3161;
        ((HttpURLConnection) (obj21)).disconnect();
        jp/pixela/pis_client/rest/send_log/SendLogToEventHubTask;
        JVM INSTR monitorexit ;
        return i;
        s;
        obj21 = null;
        obj10 = obj16;
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        obj12 = JVM INSTR new #29  <Class StringBuilder>;
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).StringBuilder();
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append(LOG_HEAD);
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append("e=");
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append(s);
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        LoggerRTM.e(((StringBuilder) (obj12)).toString(), new Object[0]);
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_3355;
        try
        {
            ((OutputStreamWriter) (obj10)).close();
            break MISSING_BLOCK_LABEL_3355;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        obj10 = JVM INSTR new #29  <Class StringBuilder>;
        ((StringBuilder) (obj10)).StringBuilder();
        ((StringBuilder) (obj10)).append(LOG_HEAD);
        ((StringBuilder) (obj10)).append("e=");
        ((StringBuilder) (obj10)).append(s);
        LoggerRTM.e(((StringBuilder) (obj10)).toString(), new Object[0]);
        if(obj21 == null)
            break MISSING_BLOCK_LABEL_3365;
        ((HttpURLConnection) (obj21)).disconnect();
        jp/pixela/pis_client/rest/send_log/SendLogToEventHubTask;
        JVM INSTR monitorexit ;
        return i;
        s;
        obj21 = null;
        obj10 = obj17;
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        obj12 = JVM INSTR new #29  <Class StringBuilder>;
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).StringBuilder();
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append(LOG_HEAD);
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append("e=");
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        ((StringBuilder) (obj12)).append(s);
        obj11 = obj10;
        j = i;
        httpurlconnection = ((HttpURLConnection) (obj21));
        LoggerRTM.e(((StringBuilder) (obj12)).toString(), new Object[0]);
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_3555;
        try
        {
            ((OutputStreamWriter) (obj10)).close();
            break MISSING_BLOCK_LABEL_3555;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj10) { }
        s = JVM INSTR new #29  <Class StringBuilder>;
        s.StringBuilder();
        s.append(LOG_HEAD);
        s.append("e=");
        s.append(obj10);
        LoggerRTM.e(s.toString(), new Object[0]);
        if(obj21 == null)
            break MISSING_BLOCK_LABEL_3565;
        ((HttpURLConnection) (obj21)).disconnect();
        jp/pixela/pis_client/rest/send_log/SendLogToEventHubTask;
        JVM INSTR monitorexit ;
        return i;
_L2:
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_3636;
        try
        {
            ((OutputStreamWriter) (obj10)).close();
            break MISSING_BLOCK_LABEL_3636;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        break MISSING_BLOCK_LABEL_3589;
        s;
        break MISSING_BLOCK_LABEL_3652;
        obj10 = JVM INSTR new #29  <Class StringBuilder>;
        ((StringBuilder) (obj10)).StringBuilder();
        ((StringBuilder) (obj10)).append(LOG_HEAD);
        ((StringBuilder) (obj10)).append("e=");
        ((StringBuilder) (obj10)).append(s);
        LoggerRTM.e(((StringBuilder) (obj10)).toString(), new Object[0]);
        if(obj21 == null)
            break MISSING_BLOCK_LABEL_3646;
        ((HttpURLConnection) (obj21)).disconnect();
        jp/pixela/pis_client/rest/send_log/SendLogToEventHubTask;
        JVM INSTR monitorexit ;
        return j;
        jp/pixela/pis_client/rest/send_log/SendLogToEventHubTask;
        JVM INSTR monitorexit ;
        throw s;
        s;
        obj10 = obj11;
        obj21 = httpurlconnection;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static void writeResponseLog(int i, String s)
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

    private static void writeRestLog(String s)
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
                PxLog.d(TAG, s.substring(j * 2000, i));
                i = k;
            } while(true);
        }
    }

    private static void writeSendLog(String s, HttpURLConnection httpurlconnection, String s1)
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
                java.util.Map.Entry entry = (java.util.Map.Entry)httpurlconnection.next();
                StringBuilder stringbuilder2 = new StringBuilder();
                stringbuilder2.append("\n         ");
                stringbuilder2.append((String)entry.getKey());
                stringbuilder2.append("=");
                s.append(stringbuilder2.toString());
                Iterator iterator = ((List)entry.getValue()).iterator();
                while(iterator.hasNext()) 
                {
                    String s2 = (String)iterator.next();
                    StringBuilder stringbuilder1 = new StringBuilder();
                    stringbuilder1.append(s2);
                    stringbuilder1.append(", ");
                    s.append(stringbuilder1.toString());
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

    protected transient Integer doInBackground(String as[])
    {
        return Integer.valueOf(sendLog(as[0]));
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((String[])aobj);
    }

    private static final int CONNECT_TIMEOUT = 5000;
    private static final String KEY = "hoEht/0XV3+R0pYQj93+vBp4OSDJjCtiW/6RwPKsM5YnEGGhh9573hhK+7Ufos4p";
    private static final String KEY_NAME = "SendOnly";
    private static final String LOG_HEAD;
    private static final int READ_TIMEOUT = 5000;
    private static final String SERVICE_URL = "https://pixela-dtv.servicebus.windows.net/syslog/messages";
    private static final String TAG = "SendLogToEventHubTask";

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/pis_client/rest/send_log/SendLogToEventHubTask.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }


    // Unreferenced inner class jp/pixela/pis_client/rest/send_log/SendLogToEventHubTask$1

/* anonymous class */
    static final class _cls1
        implements HostnameVerifier
    {

        public boolean verify(String s, SSLSession sslsession)
        {
            String s1 = SendLogToEventHubTask.TAG;
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("ENTER: hostname=");
            stringbuilder.append(s);
            PxLog.d(s1, stringbuilder.toString());
            boolean flag;
            if(sslsession != null && s.equals(sslsession.getPeerHost()))
                flag = true;
            else
                flag = false;
            sslsession = SendLogToEventHubTask.TAG;
            s = new StringBuilder();
            s.append("EXIT: ret=");
            s.append(flag);
            PxLog.d(sslsession, s.toString());
            return flag;
        }

    }

}
