// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.common;

import java.io.*;
import java.net.*;
import javax.net.ssl.*;
import jp.pixela.common.PxLog;
import jp.pixela.pis_client.rest.common.json.IJsonRestItem;
import jp.pixela.pis_client.rest.common.json.JsonRestResponse;
import jp.pixela.pis_client.rest.common.www.IWwwRestItem;
import jp.pixela.pxlibs.utils.android.log.Logger;
import jp.pixela.pxlibs.utils.android.log.LoggerRTM;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.pixela.pis_client.rest.common:
//            RestBaseClient, IRestConfig, IRestItem, IRestResponse

public class CommonRestClient extends RestBaseClient
{

    public CommonRestClient(IRestConfig irestconfig)
    {
        super(irestconfig);
        mResponse = null;
    }

    public int delete(String s)
    {
        return execute(s, "DELETE", null);
    }

    protected int execute(String s, String s1, IRestItem irestitem)
    {
        Object obj;
        IRestConfig irestconfig;
        int i;
        int j;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        String s2;
        String s3;
        String s4;
        String s5;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj9;
        Object obj10;
        obj = new StringBuilder();
        ((StringBuilder) (obj)).append(LOG_HEAD);
        ((StringBuilder) (obj)).append("in");
        Logger.v(((StringBuilder) (obj)).toString(), new Object[0]);
        irestconfig = getRestConfig();
        i = -1;
        j = i;
        if(irestconfig == null)
            break MISSING_BLOCK_LABEL_5547;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        s2 = null;
        s3 = null;
        s4 = null;
        s5 = null;
        obj5 = null;
        obj6 = null;
        obj7 = null;
        obj9 = null;
        if(irestitem != null && (irestitem instanceof IJsonRestItem))
        {
            obj10 = "application/json";
            obj = ((IJsonRestItem)irestitem).toJSONObject().toString();
            break MISSING_BLOCK_LABEL_161;
        }
        if(irestitem != null && (irestitem instanceof IWwwRestItem))
        {
            obj10 = "application/x-www-form-urlencoded";
            obj = ((IWwwRestItem)irestitem).toStringObject();
            break MISSING_BLOCK_LABEL_161;
        }
        obj10 = "text/plain";
        obj = null;
        jp/pixela/pis_client/rest/common/CommonRestClient;
        JVM INSTR monitorenter ;
        URL url;
        url = JVM INSTR new #110 <Class URL>;
        irestitem = JVM INSTR new #17  <Class StringBuilder>;
        irestitem.StringBuilder();
        irestitem.append(irestconfig.getHostURL());
        irestitem.append(s);
        url.URL(irestitem.toString());
        irestitem = (HttpURLConnection)url.openConnection();
        if(irestitem == null)
            break MISSING_BLOCK_LABEL_2137;
        irestitem.setRequestMethod(s1);
        if("GET".equals(s1))
        {
            irestitem.setDoInput(true);
            break MISSING_BLOCK_LABEL_261;
        }
        int k;
        int l;
        try
        {
            if("POST".equals(s1))
            {
                irestitem.setInstanceFollowRedirects(false);
                irestitem.setRequestProperty("Accept-Language", "jp");
            }
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            obj10 = null;
            obj1 = obj10;
            obj3 = obj6;
            obj2 = irestitem;
            break MISSING_BLOCK_LABEL_5081;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            obj10 = null;
            obj1 = obj10;
            obj2 = obj5;
            obj3 = irestitem;
            break MISSING_BLOCK_LABEL_4765;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            obj10 = null;
            obj1 = obj10;
            obj2 = s5;
            obj3 = irestitem;
            break MISSING_BLOCK_LABEL_4449;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            obj10 = null;
            obj1 = obj10;
            obj2 = s4;
            obj3 = irestitem;
            break MISSING_BLOCK_LABEL_4133;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            obj10 = null;
            obj1 = obj10;
            obj2 = s3;
            obj3 = irestitem;
            break MISSING_BLOCK_LABEL_3817;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            obj10 = null;
            obj1 = obj10;
            obj3 = s2;
            obj2 = irestitem;
            break MISSING_BLOCK_LABEL_3501;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            obj10 = null;
            obj1 = obj10;
            obj3 = obj4;
            obj2 = irestitem;
            break MISSING_BLOCK_LABEL_3185;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            obj10 = null;
            obj1 = obj10;
            obj2 = irestitem;
            break MISSING_BLOCK_LABEL_2869;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            obj10 = null;
            obj1 = obj10;
            obj3 = irestitem;
            break MISSING_BLOCK_LABEL_2557;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            j = -1;
            s = null;
            s1 = s;
            obj10 = obj1;
            break MISSING_BLOCK_LABEL_2332;
        }
        if(obj == null)
            break MISSING_BLOCK_LABEL_282;
        irestitem.setDoOutput(true);
        irestitem.setRequestProperty("Content-Type", ((String) (obj10)));
        break MISSING_BLOCK_LABEL_313;
        if("PUT".equals(s1) || "POST".equals(s1))
        {
            irestitem.setDoOutput(true);
            irestitem.setRequestProperty("Content-Type", "text/plain");
        }
        setCustomRequestProperty(irestitem, irestconfig);
        irestitem.setRequestProperty("Accept", "application/json");
        irestitem.setConnectTimeout(irestconfig.getConnectTimeout());
        irestitem.setReadTimeout(irestconfig.getReadTimeout());
        writeSendLog(url.toString(), irestitem, ((String) (obj)));
        if(irestitem instanceof HttpsURLConnection)
        {
            s1 = JVM INSTR new #6   <Class CommonRestClient$1>;
            s1.this. _cls1();
            HttpsURLConnection.setDefaultHostnameVerifier(s1);
        }
        s1 = TAG;
        obj10 = JVM INSTR new #17  <Class StringBuilder>;
        ((StringBuilder) (obj10)).StringBuilder();
        ((StringBuilder) (obj10)).append("http communication : resourcePath=");
        ((StringBuilder) (obj10)).append(s);
        PxLog.i(s1, ((StringBuilder) (obj10)).toString());
        irestitem.connect();
        if(obj == null)
            break MISSING_BLOCK_LABEL_520;
        s = JVM INSTR new #205 <Class OutputStreamWriter>;
        s.OutputStreamWriter(irestitem.getOutputStream(), "UTF-8");
        s4 = s;
        s1 = s;
        s2 = s;
        obj2 = s;
        obj3 = s;
        obj1 = s;
        obj7 = s;
        obj4 = s;
        obj10 = s;
        s5 = s;
        s3 = s;
        s.write(((String) (obj)));
        s4 = s;
        s1 = s;
        s2 = s;
        obj2 = s;
        obj3 = s;
        obj1 = s;
        obj7 = s;
        obj4 = s;
        obj10 = s;
        s5 = s;
        s3 = s;
        s.flush();
        break MISSING_BLOCK_LABEL_522;
        s = null;
        s4 = s;
        s1 = s;
        s2 = s;
        obj2 = s;
        obj3 = s;
        obj1 = s;
        obj7 = s;
        obj4 = s;
        obj10 = s;
        s5 = s;
        s3 = s;
        k = irestitem.getResponseCode();
        s4 = s;
        s1 = s;
        s2 = s;
        obj2 = s;
        obj3 = s;
        obj1 = s;
        obj7 = s;
        obj4 = s;
        obj10 = s;
        s5 = s;
        s3 = s;
        obj = TAG;
        s4 = s;
        s1 = s;
        s2 = s;
        obj2 = s;
        obj3 = s;
        obj1 = s;
        obj7 = s;
        obj4 = s;
        obj10 = s;
        s5 = s;
        s3 = s;
        obj5 = JVM INSTR new #17  <Class StringBuilder>;
        s4 = s;
        s1 = s;
        s2 = s;
        obj2 = s;
        obj3 = s;
        obj1 = s;
        obj7 = s;
        obj4 = s;
        obj10 = s;
        s5 = s;
        s3 = s;
        ((StringBuilder) (obj5)).StringBuilder();
        s4 = s;
        s1 = s;
        s2 = s;
        obj2 = s;
        obj3 = s;
        obj1 = s;
        obj7 = s;
        obj4 = s;
        obj10 = s;
        s5 = s;
        s3 = s;
        ((StringBuilder) (obj5)).append("http communication : responseCode=");
        s4 = s;
        s1 = s;
        s2 = s;
        obj2 = s;
        obj3 = s;
        obj1 = s;
        obj7 = s;
        obj4 = s;
        obj10 = s;
        s5 = s;
        s3 = s;
        ((StringBuilder) (obj5)).append(k);
        s4 = s;
        s1 = s;
        s2 = s;
        obj2 = s;
        obj3 = s;
        obj1 = s;
        obj7 = s;
        obj4 = s;
        obj10 = s;
        s5 = s;
        s3 = s;
        PxLog.i(((String) (obj)), ((StringBuilder) (obj5)).toString());
        if(k == 200)
            break MISSING_BLOCK_LABEL_1034;
        s4 = s;
        s1 = s;
        s2 = s;
        obj2 = s;
        obj3 = s;
        obj1 = s;
        obj7 = s;
        obj4 = s;
        obj10 = s;
        s5 = s;
        s3 = s;
        obj5 = TAG;
        s4 = s;
        s1 = s;
        s2 = s;
        obj2 = s;
        obj3 = s;
        obj1 = s;
        obj7 = s;
        obj4 = s;
        obj10 = s;
        s5 = s;
        s3 = s;
        obj = JVM INSTR new #17  <Class StringBuilder>;
        s4 = s;
        s1 = s;
        s2 = s;
        obj2 = s;
        obj3 = s;
        obj1 = s;
        obj7 = s;
        obj4 = s;
        obj10 = s;
        s5 = s;
        s3 = s;
        ((StringBuilder) (obj)).StringBuilder();
        s4 = s;
        s1 = s;
        s2 = s;
        obj2 = s;
        obj3 = s;
        obj1 = s;
        obj7 = s;
        obj4 = s;
        obj10 = s;
        s5 = s;
        s3 = s;
        ((StringBuilder) (obj)).append("Error. Json http communication error: ");
        s4 = s;
        s1 = s;
        s2 = s;
        obj2 = s;
        obj3 = s;
        obj1 = s;
        obj7 = s;
        obj4 = s;
        obj10 = s;
        s5 = s;
        s3 = s;
        ((StringBuilder) (obj)).append(k);
        s4 = s;
        s1 = s;
        s2 = s;
        obj2 = s;
        obj3 = s;
        obj1 = s;
        obj7 = s;
        obj4 = s;
        obj10 = s;
        s5 = s;
        s3 = s;
        PxLog.e(((String) (obj5)), ((StringBuilder) (obj)).toString());
        s4 = s;
        s1 = s;
        s2 = s;
        obj2 = s;
        obj3 = s;
        obj1 = s;
        obj7 = s;
        obj4 = s;
        obj10 = s;
        s5 = s;
        s3 = s;
        j = toRestResultCode(k);
        if(j != 0)
            break MISSING_BLOCK_LABEL_1182;
        s1 = irestitem.getInputStream();
        break MISSING_BLOCK_LABEL_1187;
        obj10;
        i = j;
        break MISSING_BLOCK_LABEL_1685;
        obj;
        break MISSING_BLOCK_LABEL_1719;
        obj7;
        i = j;
        break MISSING_BLOCK_LABEL_1743;
        obj7;
        i = j;
        break MISSING_BLOCK_LABEL_1765;
        obj7;
        i = j;
        break MISSING_BLOCK_LABEL_1787;
        obj7;
        i = j;
        break MISSING_BLOCK_LABEL_1809;
        obj7;
        i = j;
        break MISSING_BLOCK_LABEL_1833;
        obj7;
        i = j;
        break MISSING_BLOCK_LABEL_1855;
        obj7;
        i = j;
        break MISSING_BLOCK_LABEL_1877;
        obj7;
        i = j;
        break MISSING_BLOCK_LABEL_1899;
        obj7;
        i = j;
        break MISSING_BLOCK_LABEL_1921;
        s1 = irestitem.getErrorStream();
        obj = s1;
        obj1 = obj9;
        obj10 = s;
        l = j;
        if(s1 == null)
            break MISSING_BLOCK_LABEL_2151;
        try
        {
            obj = JVM INSTR new #246 <Class BufferedReader>;
            obj10 = JVM INSTR new #248 <Class InputStreamReader>;
            ((InputStreamReader) (obj10)).InputStreamReader(s1, "UTF-8");
            ((BufferedReader) (obj)).BufferedReader(((java.io.Reader) (obj10)));
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            i = j;
            break MISSING_BLOCK_LABEL_1923;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            i = j;
            break MISSING_BLOCK_LABEL_1901;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            i = j;
            break MISSING_BLOCK_LABEL_1879;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            i = j;
            break MISSING_BLOCK_LABEL_1857;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            i = j;
            break MISSING_BLOCK_LABEL_1835;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            i = j;
            break MISSING_BLOCK_LABEL_1811;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            i = j;
            break MISSING_BLOCK_LABEL_1789;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            i = j;
            break MISSING_BLOCK_LABEL_1767;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            i = j;
            break MISSING_BLOCK_LABEL_1745;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            obj1 = null;
            obj10 = s1;
            s1 = ((String) (obj1));
            break MISSING_BLOCK_LABEL_1725;
        }
        obj1 = JVM INSTR new #17  <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
_L1:
        obj10 = ((BufferedReader) (obj)).readLine();
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_1263;
        try
        {
            ((StringBuilder) (obj1)).append(((String) (obj10)));
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            obj10 = s1;
            i = j;
            obj3 = s;
            obj1 = obj;
            obj2 = irestitem;
            break MISSING_BLOCK_LABEL_5081;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            obj10 = s1;
            i = j;
            obj2 = s;
            obj1 = obj;
            obj3 = irestitem;
            break MISSING_BLOCK_LABEL_4765;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            obj10 = s1;
            i = j;
            obj2 = s;
            obj1 = obj;
            obj3 = irestitem;
            break MISSING_BLOCK_LABEL_4449;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            obj10 = s1;
            i = j;
            obj2 = s;
            obj1 = obj;
            obj3 = irestitem;
            break MISSING_BLOCK_LABEL_4133;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            obj10 = s1;
            i = j;
            obj2 = s;
            obj1 = obj;
            obj3 = irestitem;
            break MISSING_BLOCK_LABEL_3817;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            obj10 = s1;
            i = j;
            obj3 = s;
            obj1 = obj;
            obj2 = irestitem;
            break MISSING_BLOCK_LABEL_3501;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            obj10 = s1;
            i = j;
            obj3 = s;
            obj1 = obj;
            obj2 = irestitem;
            break MISSING_BLOCK_LABEL_3185;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            obj10 = s1;
            i = j;
            obj3 = s;
            obj1 = obj;
            obj2 = irestitem;
            break MISSING_BLOCK_LABEL_2869;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            obj10 = s1;
            i = j;
            obj2 = s;
            obj1 = obj;
            obj3 = irestitem;
            break MISSING_BLOCK_LABEL_2557;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj1)
        {
            obj10 = s1;
            s1 = ((String) (obj));
            obj = obj1;
            break MISSING_BLOCK_LABEL_1725;
        }
          goto _L1
        obj1 = getCustomResponseString(((StringBuilder) (obj1)).toString());
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_1302;
        if(!((String) (obj1)).isEmpty())
        {
            obj10 = JVM INSTR new #98  <Class JSONObject>;
            ((JSONObject) (obj10)).JSONObject(((String) (obj1)));
            break MISSING_BLOCK_LABEL_1311;
        }
        obj10 = new JSONObject();
        obj1 = JVM INSTR new #269 <Class JsonRestResponse>;
        ((JsonRestResponse) (obj1)).JsonRestResponse(((JSONObject) (obj10)));
        mResponse = ((JsonRestResponse) (obj1));
        writeResponseLog(k, ((JSONObject) (obj10)));
        obj1 = obj;
        obj = s1;
        obj10 = s;
        l = j;
        break MISSING_BLOCK_LABEL_2151;
        obj3;
        obj10 = s;
        s = s1;
        s1 = ((String) (obj10));
        break MISSING_BLOCK_LABEL_2534;
        obj10;
        break MISSING_BLOCK_LABEL_1691;
        obj10;
        s = s4;
        s1 = null;
        j = i;
        obj1 = null;
        obj = s;
        s = s1;
        s1 = ((String) (obj));
        obj = obj1;
        obj1 = irestitem;
        break MISSING_BLOCK_LABEL_5396;
        obj;
        j = -1;
        s = s1;
        obj10 = null;
        s1 = ((String) (obj10));
        obj1 = s;
        s = ((String) (obj10));
        obj10 = obj1;
        break MISSING_BLOCK_LABEL_2332;
        obj7;
        s = s2;
        s1 = null;
        obj1 = null;
        obj10 = s1;
        obj2 = s;
        obj3 = irestitem;
        break MISSING_BLOCK_LABEL_2557;
        obj7;
        s = ((String) (obj2));
        s1 = null;
        obj1 = null;
        obj10 = s1;
        obj3 = s;
        obj2 = irestitem;
        break MISSING_BLOCK_LABEL_2869;
        obj7;
        s = ((String) (obj3));
        s1 = null;
        obj1 = null;
        obj10 = s1;
        obj3 = s;
        obj2 = irestitem;
        break MISSING_BLOCK_LABEL_3185;
        obj7;
        s = ((String) (obj1));
        s1 = null;
        obj1 = null;
        obj10 = s1;
        obj3 = s;
        obj2 = irestitem;
        break MISSING_BLOCK_LABEL_3501;
        s1;
        s = ((String) (obj7));
        obj7 = s1;
        s1 = null;
        obj1 = null;
        obj10 = s1;
        obj2 = s;
        obj3 = irestitem;
        break MISSING_BLOCK_LABEL_3817;
        obj7;
        s = ((String) (obj4));
        s1 = null;
        obj1 = null;
        obj10 = s1;
        obj2 = s;
        obj3 = irestitem;
        break MISSING_BLOCK_LABEL_4133;
        obj7;
        s = ((String) (obj10));
        s1 = null;
        obj1 = null;
        obj10 = s1;
        obj2 = s;
        obj3 = irestitem;
        break MISSING_BLOCK_LABEL_4449;
        obj7;
        s = s5;
        s1 = null;
        obj1 = null;
        obj10 = s1;
        obj2 = s;
        obj3 = irestitem;
        break MISSING_BLOCK_LABEL_4765;
        obj7;
        s = s3;
        s1 = null;
        obj1 = null;
        obj10 = s1;
        obj3 = s;
        obj2 = irestitem;
        break MISSING_BLOCK_LABEL_5081;
        obj10;
        s = null;
        obj = s;
        j = i;
        s1 = ((String) (obj7));
        obj1 = irestitem;
        break MISSING_BLOCK_LABEL_5396;
        l = -1;
        obj10 = null;
        obj = obj10;
        obj1 = obj9;
        if(l == 0)
            break MISSING_BLOCK_LABEL_2199;
        s = JVM INSTR new #17  <Class StringBuilder>;
        s.StringBuilder();
        s.append(LOG_HEAD);
        s.append("http connection error : result=");
        s.append(l);
        LoggerRTM.e(s.toString(), new Object[0]);
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_2216;
        ((OutputStreamWriter) (obj10)).close();
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_2226;
        ((BufferedReader) (obj1)).close();
        if(obj == null)
            break MISSING_BLOCK_LABEL_2281;
        try
        {
            ((InputStream) (obj)).close();
            break MISSING_BLOCK_LABEL_2281;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        s1 = JVM INSTR new #17  <Class StringBuilder>;
        s1.StringBuilder();
        s1.append(LOG_HEAD);
        s1.append("http connection close error. e=");
        s1.append(s);
        LoggerRTM.e(s1.toString(), new Object[0]);
        if(irestitem == null)
            break MISSING_BLOCK_LABEL_2289;
        irestitem.disconnect();
        j = l;
        break MISSING_BLOCK_LABEL_5381;
        obj10;
        s = null;
        obj = s;
        obj1 = obj;
        j = i;
        s1 = ((String) (obj7));
        break MISSING_BLOCK_LABEL_5396;
        obj;
        j = -1;
        s = null;
        s1 = s;
        irestitem = s1;
        obj10 = obj1;
        obj1 = JVM INSTR new #17  <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        ((StringBuilder) (obj1)).append(LOG_HEAD);
        ((StringBuilder) (obj1)).append("e=");
        ((StringBuilder) (obj1)).append(obj);
        LoggerRTM.e(((StringBuilder) (obj1)).toString(), new Object[0]);
        obj = JVM INSTR new #17  <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        ((StringBuilder) (obj)).append(LOG_HEAD);
        ((StringBuilder) (obj)).append("http connection error : result=");
        ((StringBuilder) (obj)).append(-1);
        LoggerRTM.e(((StringBuilder) (obj)).toString(), new Object[0]);
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_2446;
        ((OutputStreamWriter) (obj10)).close();
        if(s1 == null)
            break MISSING_BLOCK_LABEL_2454;
        s1.close();
        if(s == null)
            break MISSING_BLOCK_LABEL_2507;
        try
        {
            s.close();
            break MISSING_BLOCK_LABEL_2507;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        s1 = JVM INSTR new #17  <Class StringBuilder>;
        s1.StringBuilder();
        s1.append(LOG_HEAD);
        s1.append("http connection close error. e=");
        s1.append(s);
        LoggerRTM.e(s1.toString(), new Object[0]);
        j = i;
        if(irestitem == null)
            break MISSING_BLOCK_LABEL_5381;
        irestitem.disconnect();
        j = i;
        break MISSING_BLOCK_LABEL_5381;
        obj3;
        obj = s1;
        s1 = ((String) (obj10));
        obj1 = irestitem;
        obj10 = obj3;
        break MISSING_BLOCK_LABEL_5396;
        Object obj8;
        obj8;
        obj10 = null;
        obj1 = obj10;
        obj3 = obj1;
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        obj4 = JVM INSTR new #17  <Class StringBuilder>;
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        ((StringBuilder) (obj4)).StringBuilder();
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        ((StringBuilder) (obj4)).append(LOG_HEAD);
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        ((StringBuilder) (obj4)).append("e=");
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        ((StringBuilder) (obj4)).append(obj8);
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        LoggerRTM.e(((StringBuilder) (obj4)).toString(), new Object[0]);
        i = -5;
        s = JVM INSTR new #17  <Class StringBuilder>;
        s.StringBuilder();
        s.append(LOG_HEAD);
        s.append("http connection error : result=");
        s.append(-5);
        LoggerRTM.e(s.toString(), new Object[0]);
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_2772;
        ((OutputStreamWriter) (obj2)).close();
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_2782;
        ((BufferedReader) (obj1)).close();
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_2837;
        try
        {
            ((InputStream) (obj10)).close();
            break MISSING_BLOCK_LABEL_2837;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        s1 = JVM INSTR new #17  <Class StringBuilder>;
        s1.StringBuilder();
        s1.append(LOG_HEAD);
        s1.append("http connection close error. e=");
        s1.append(s);
        LoggerRTM.e(s1.toString(), new Object[0]);
        j = i;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_5381;
        s = ((String) (obj3));
        j = i;
        break MISSING_BLOCK_LABEL_5377;
        obj8;
        obj10 = null;
        obj1 = obj10;
        obj2 = obj1;
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        obj4 = JVM INSTR new #17  <Class StringBuilder>;
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        ((StringBuilder) (obj4)).StringBuilder();
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        ((StringBuilder) (obj4)).append(LOG_HEAD);
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        ((StringBuilder) (obj4)).append("e=");
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        ((StringBuilder) (obj4)).append(obj8);
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        LoggerRTM.e(((StringBuilder) (obj4)).toString(), new Object[0]);
        i = -15;
        s = JVM INSTR new #17  <Class StringBuilder>;
        s.StringBuilder();
        s.append(LOG_HEAD);
        s.append("http connection error : result=");
        s.append(-15);
        LoggerRTM.e(s.toString(), new Object[0]);
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_3084;
        ((OutputStreamWriter) (obj3)).close();
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_3094;
        ((BufferedReader) (obj1)).close();
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_3149;
        try
        {
            ((InputStream) (obj10)).close();
            break MISSING_BLOCK_LABEL_3149;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        s1 = JVM INSTR new #17  <Class StringBuilder>;
        s1.StringBuilder();
        s1.append(LOG_HEAD);
        s1.append("http connection close error. e=");
        s1.append(s);
        LoggerRTM.e(s1.toString(), new Object[0]);
        j = i;
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_5381;
        s = ((String) (obj2));
        j = i;
        break MISSING_BLOCK_LABEL_5377;
        obj8;
        obj10 = null;
        obj1 = obj10;
        obj2 = obj1;
        obj3 = obj4;
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        obj4 = JVM INSTR new #17  <Class StringBuilder>;
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        ((StringBuilder) (obj4)).StringBuilder();
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        ((StringBuilder) (obj4)).append(LOG_HEAD);
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        ((StringBuilder) (obj4)).append("e=");
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        ((StringBuilder) (obj4)).append(obj8);
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        LoggerRTM.e(((StringBuilder) (obj4)).toString(), new Object[0]);
        i = -14;
        s = JVM INSTR new #17  <Class StringBuilder>;
        s.StringBuilder();
        s.append(LOG_HEAD);
        s.append("http connection error : result=");
        s.append(-14);
        LoggerRTM.e(s.toString(), new Object[0]);
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_3400;
        ((OutputStreamWriter) (obj3)).close();
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_3410;
        ((BufferedReader) (obj1)).close();
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_3465;
        try
        {
            ((InputStream) (obj10)).close();
            break MISSING_BLOCK_LABEL_3465;
        }
        // Misplaced declaration of an exception variable
        catch(String s1) { }
        s = JVM INSTR new #17  <Class StringBuilder>;
        s.StringBuilder();
        s.append(LOG_HEAD);
        s.append("http connection close error. e=");
        s.append(s1);
        LoggerRTM.e(s.toString(), new Object[0]);
        j = i;
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_5381;
        s = ((String) (obj2));
        j = i;
        break MISSING_BLOCK_LABEL_5377;
        obj8;
        obj10 = null;
        obj1 = obj10;
        obj2 = obj1;
        obj3 = s2;
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        obj4 = JVM INSTR new #17  <Class StringBuilder>;
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        ((StringBuilder) (obj4)).StringBuilder();
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        ((StringBuilder) (obj4)).append(LOG_HEAD);
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        ((StringBuilder) (obj4)).append("e=");
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        ((StringBuilder) (obj4)).append(obj8);
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        LoggerRTM.e(((StringBuilder) (obj4)).toString(), new Object[0]);
        i = -13;
        s = JVM INSTR new #17  <Class StringBuilder>;
        s.StringBuilder();
        s.append(LOG_HEAD);
        s.append("http connection error : result=");
        s.append(-13);
        LoggerRTM.e(s.toString(), new Object[0]);
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_3716;
        ((OutputStreamWriter) (obj3)).close();
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_3726;
        ((BufferedReader) (obj1)).close();
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_3781;
        try
        {
            ((InputStream) (obj10)).close();
            break MISSING_BLOCK_LABEL_3781;
        }
        // Misplaced declaration of an exception variable
        catch(String s1) { }
        s = JVM INSTR new #17  <Class StringBuilder>;
        s.StringBuilder();
        s.append(LOG_HEAD);
        s.append("http connection close error. e=");
        s.append(s1);
        LoggerRTM.e(s.toString(), new Object[0]);
        j = i;
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_5381;
        s = ((String) (obj2));
        j = i;
        break MISSING_BLOCK_LABEL_5377;
        obj8;
        obj10 = null;
        obj1 = obj10;
        obj3 = obj1;
        obj2 = s3;
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        obj4 = JVM INSTR new #17  <Class StringBuilder>;
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        ((StringBuilder) (obj4)).StringBuilder();
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        ((StringBuilder) (obj4)).append(LOG_HEAD);
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        ((StringBuilder) (obj4)).append("e=");
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        ((StringBuilder) (obj4)).append(obj8);
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        LoggerRTM.e(((StringBuilder) (obj4)).toString(), new Object[0]);
        i = -12;
        s = JVM INSTR new #17  <Class StringBuilder>;
        s.StringBuilder();
        s.append(LOG_HEAD);
        s.append("http connection error : result=");
        s.append(-12);
        LoggerRTM.e(s.toString(), new Object[0]);
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_4032;
        ((OutputStreamWriter) (obj2)).close();
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_4042;
        ((BufferedReader) (obj1)).close();
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_4097;
        try
        {
            ((InputStream) (obj10)).close();
            break MISSING_BLOCK_LABEL_4097;
        }
        // Misplaced declaration of an exception variable
        catch(String s1) { }
        s = JVM INSTR new #17  <Class StringBuilder>;
        s.StringBuilder();
        s.append(LOG_HEAD);
        s.append("http connection close error. e=");
        s.append(s1);
        LoggerRTM.e(s.toString(), new Object[0]);
        j = i;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_5381;
        s = ((String) (obj3));
        j = i;
        break MISSING_BLOCK_LABEL_5377;
        obj8;
        obj10 = null;
        obj1 = obj10;
        obj3 = obj1;
        obj2 = s4;
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        obj4 = JVM INSTR new #17  <Class StringBuilder>;
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        ((StringBuilder) (obj4)).StringBuilder();
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        ((StringBuilder) (obj4)).append(LOG_HEAD);
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        ((StringBuilder) (obj4)).append("e=");
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        ((StringBuilder) (obj4)).append(obj8);
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        LoggerRTM.e(((StringBuilder) (obj4)).toString(), new Object[0]);
        i = -11;
        s = JVM INSTR new #17  <Class StringBuilder>;
        s.StringBuilder();
        s.append(LOG_HEAD);
        s.append("http connection error : result=");
        s.append(-11);
        LoggerRTM.e(s.toString(), new Object[0]);
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_4348;
        ((OutputStreamWriter) (obj2)).close();
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_4358;
        ((BufferedReader) (obj1)).close();
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_4413;
        try
        {
            ((InputStream) (obj10)).close();
            break MISSING_BLOCK_LABEL_4413;
        }
        // Misplaced declaration of an exception variable
        catch(String s1) { }
        s = JVM INSTR new #17  <Class StringBuilder>;
        s.StringBuilder();
        s.append(LOG_HEAD);
        s.append("http connection close error. e=");
        s.append(s1);
        LoggerRTM.e(s.toString(), new Object[0]);
        j = i;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_5381;
        s = ((String) (obj3));
        j = i;
        break MISSING_BLOCK_LABEL_5377;
        obj8;
        obj10 = null;
        obj1 = obj10;
        obj3 = obj1;
        obj2 = s5;
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        obj4 = JVM INSTR new #17  <Class StringBuilder>;
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        ((StringBuilder) (obj4)).StringBuilder();
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        ((StringBuilder) (obj4)).append(LOG_HEAD);
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        ((StringBuilder) (obj4)).append("e=");
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        ((StringBuilder) (obj4)).append(obj8);
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        LoggerRTM.e(((StringBuilder) (obj4)).toString(), new Object[0]);
        i = -4;
        s = JVM INSTR new #17  <Class StringBuilder>;
        s.StringBuilder();
        s.append(LOG_HEAD);
        s.append("http connection error : result=");
        s.append(-4);
        LoggerRTM.e(s.toString(), new Object[0]);
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_4664;
        ((OutputStreamWriter) (obj2)).close();
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_4674;
        ((BufferedReader) (obj1)).close();
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_4729;
        try
        {
            ((InputStream) (obj10)).close();
            break MISSING_BLOCK_LABEL_4729;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        s1 = JVM INSTR new #17  <Class StringBuilder>;
        s1.StringBuilder();
        s1.append(LOG_HEAD);
        s1.append("http connection close error. e=");
        s1.append(s);
        LoggerRTM.e(s1.toString(), new Object[0]);
        j = i;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_5381;
        s = ((String) (obj3));
        j = i;
        break MISSING_BLOCK_LABEL_5377;
        obj8;
        obj10 = null;
        obj1 = obj10;
        obj3 = obj1;
        obj2 = obj5;
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        obj4 = JVM INSTR new #17  <Class StringBuilder>;
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        ((StringBuilder) (obj4)).StringBuilder();
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        ((StringBuilder) (obj4)).append(LOG_HEAD);
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        ((StringBuilder) (obj4)).append("e=");
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        ((StringBuilder) (obj4)).append(obj8);
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj2));
        irestitem = ((IRestItem) (obj1));
        obj = obj3;
        LoggerRTM.e(((StringBuilder) (obj4)).toString(), new Object[0]);
        i = -3;
        s = JVM INSTR new #17  <Class StringBuilder>;
        s.StringBuilder();
        s.append(LOG_HEAD);
        s.append("http connection error : result=");
        s.append(-3);
        LoggerRTM.e(s.toString(), new Object[0]);
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_4980;
        ((OutputStreamWriter) (obj2)).close();
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_4990;
        ((BufferedReader) (obj1)).close();
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_5045;
        try
        {
            ((InputStream) (obj10)).close();
            break MISSING_BLOCK_LABEL_5045;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        s1 = JVM INSTR new #17  <Class StringBuilder>;
        s1.StringBuilder();
        s1.append(LOG_HEAD);
        s1.append("http connection close error. e=");
        s1.append(s);
        LoggerRTM.e(s1.toString(), new Object[0]);
        j = i;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_5381;
        s = ((String) (obj3));
        j = i;
        break MISSING_BLOCK_LABEL_5377;
        obj8;
        obj10 = null;
        obj1 = obj10;
        obj2 = obj1;
        obj3 = obj6;
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        obj4 = JVM INSTR new #17  <Class StringBuilder>;
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        ((StringBuilder) (obj4)).StringBuilder();
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        ((StringBuilder) (obj4)).append(LOG_HEAD);
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        ((StringBuilder) (obj4)).append("e=");
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        ((StringBuilder) (obj4)).append(obj8);
        s = ((String) (obj10));
        j = i;
        s1 = ((String) (obj3));
        irestitem = ((IRestItem) (obj1));
        obj = obj2;
        LoggerRTM.e(((StringBuilder) (obj4)).toString(), new Object[0]);
        i = -2;
        s = JVM INSTR new #17  <Class StringBuilder>;
        s.StringBuilder();
        s.append(LOG_HEAD);
        s.append("http connection error : result=");
        s.append(-2);
        LoggerRTM.e(s.toString(), new Object[0]);
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_5296;
        ((OutputStreamWriter) (obj3)).close();
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_5306;
        ((BufferedReader) (obj1)).close();
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_5361;
        try
        {
            ((InputStream) (obj10)).close();
            break MISSING_BLOCK_LABEL_5361;
        }
        // Misplaced declaration of an exception variable
        catch(String s1) { }
        s = JVM INSTR new #17  <Class StringBuilder>;
        s.StringBuilder();
        s.append(LOG_HEAD);
        s.append("http connection close error. e=");
        s.append(s1);
        LoggerRTM.e(s.toString(), new Object[0]);
        j = i;
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_5381;
        j = i;
        s = ((String) (obj2));
        s.disconnect();
        jp/pixela/pis_client/rest/common/CommonRestClient;
        JVM INSTR monitorexit ;
        break MISSING_BLOCK_LABEL_5547;
        obj10;
        obj1 = obj;
        obj = irestitem;
        if(j == 0)
            break MISSING_BLOCK_LABEL_5451;
        irestitem = JVM INSTR new #17  <Class StringBuilder>;
        irestitem.StringBuilder();
        irestitem.append(LOG_HEAD);
        irestitem.append("http connection error : result=");
        irestitem.append(j);
        LoggerRTM.e(irestitem.toString(), new Object[0]);
        break MISSING_BLOCK_LABEL_5451;
        s;
        break MISSING_BLOCK_LABEL_5544;
        if(s1 == null)
            break MISSING_BLOCK_LABEL_5466;
        s1.close();
        if(obj == null)
            break MISSING_BLOCK_LABEL_5476;
        ((BufferedReader) (obj)).close();
        if(s == null)
            break MISSING_BLOCK_LABEL_5529;
        try
        {
            s.close();
            break MISSING_BLOCK_LABEL_5529;
        }
        // Misplaced declaration of an exception variable
        catch(String s1) { }
        s = JVM INSTR new #17  <Class StringBuilder>;
        s.StringBuilder();
        s.append(LOG_HEAD);
        s.append("http connection close error. e=");
        s.append(s1);
        LoggerRTM.e(s.toString(), new Object[0]);
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_5539;
        ((HttpURLConnection) (obj1)).disconnect();
        throw obj10;
        throw s;
        s = new StringBuilder();
        s.append(LOG_HEAD);
        s.append("out. result=");
        s.append(j);
        Logger.v(s.toString(), new Object[0]);
        return j;
    }

    public int get(String s)
    {
        return execute(s, "GET", null);
    }

    protected String getCustomResponseString(String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(LOG_HEAD);
        stringbuilder.append("in. responseString=");
        stringbuilder.append(s);
        Logger.v(stringbuilder.toString(), new Object[0]);
        return s;
    }

    public IRestResponse getResponse()
    {
        return mResponse;
    }

    public int post(String s, IRestItem irestitem)
    {
        return execute(s, "POST", irestitem);
    }

    public int put(String s, IRestItem irestitem)
    {
        return execute(s, "PUT", irestitem);
    }

    protected void setCustomRequestProperty(HttpURLConnection httpurlconnection, IRestConfig irestconfig)
    {
        httpurlconnection = new StringBuilder();
        httpurlconnection.append(LOG_HEAD);
        httpurlconnection.append("in");
        Logger.v(httpurlconnection.toString(), new Object[0]);
    }

    protected void writeResponseLog(int i, JSONObject jsonobject)
    {
label0:
        {
            if(jsonobject != null)
                try
                {
                    jsonobject = jsonobject.toString(4);
                    break label0;
                }
                catch(JSONException jsonexception)
                {
                    jsonobject = new StringBuilder();
                    jsonobject.append(LOG_HEAD);
                    jsonobject.append("e=");
                    jsonobject.append(jsonexception);
                    LoggerRTM.e(jsonobject.toString(), new Object[0]);
                }
            jsonobject = null;
        }
        writeResponseLog(i, ((String) (jsonobject)));
    }

    protected void writeSendLog(String s, HttpURLConnection httpurlconnection, JSONObject jsonobject)
    {
label0:
        {
            if(jsonobject != null)
                try
                {
                    jsonobject = jsonobject.toString(4);
                    break label0;
                }
                catch(JSONException jsonexception)
                {
                    jsonobject = new StringBuilder();
                    jsonobject.append(LOG_HEAD);
                    jsonobject.append("e=");
                    jsonobject.append(jsonexception);
                    LoggerRTM.e(jsonobject.toString(), new Object[0]);
                }
            jsonobject = null;
        }
        writeSendLog(s, httpurlconnection, ((String) (jsonobject)));
    }

    private static final String LOG_HEAD;
    private static final String TAG = "CommonRestClient";
    protected JsonRestResponse mResponse;

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/pis_client/rest/common/CommonRestClient.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }


    // Unreferenced inner class jp/pixela/pis_client/rest/common/CommonRestClient$1

/* anonymous class */
    class _cls1
        implements HostnameVerifier
    {

        public boolean verify(String s, SSLSession sslsession)
        {
            String s1 = CommonRestClient.TAG;
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("ENTER: hostname=");
            stringbuilder.append(s);
            PxLog.d(s1, stringbuilder.toString());
            boolean flag;
            if(sslsession != null && s.equals(sslsession.getPeerHost()))
                flag = true;
            else
                flag = false;
            sslsession = CommonRestClient.TAG;
            s = new StringBuilder();
            s.append("EXIT: ret=");
            s.append(flag);
            PxLog.d(sslsession, s.toString());
            return flag;
        }

        final CommonRestClient this$0;

            
            {
                this$0 = CommonRestClient.this;
                super();
            }
    }

}
