// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.rank;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.*;
import jp.pixela.common.PxLog;
import jp.pixela.pis_client.rest.common.*;
import jp.pixela.pis_client.rest.common.json.IJsonRestItem;
import jp.pixela.pis_client.rest.common.json.JsonRestResponse;
import jp.pixela.pis_client.rest.common.www.IWwwRestItem;
import jp.pixela.pis_client.rest.utility.EncryptionUtility;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package jp.pixela.pis_client.rest.rank:
//            ViewingPointApiConfig

public class ViewingPointRestClient extends CommonRestClient
{

    public ViewingPointRestClient(IRestConfig irestconfig)
    {
        super(irestconfig);
    }

    protected int execute(String s, String s1, IRestItem irestitem)
    {
        Object obj;
        byte byte0;
        int i;
        Object obj1;
        Object obj2;
        Object obj3;
        String s2;
        String s3;
        String s4;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        Object obj9;
        Object obj10;
        obj = (ViewingPointApiConfig)getRestConfig();
        byte0 = -1;
        i = byte0;
        if(obj == null)
            break MISSING_BLOCK_LABEL_4674;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        s2 = null;
        s3 = null;
        s4 = null;
        obj4 = null;
        obj5 = null;
        obj6 = null;
        obj7 = null;
        obj8 = null;
        obj9 = null;
        if(irestitem != null && (irestitem instanceof IJsonRestItem))
        {
            obj10 = "application/json";
            irestitem = ((IJsonRestItem)irestitem).toJSONObject().toString();
            break MISSING_BLOCK_LABEL_122;
        }
        if(irestitem != null && (irestitem instanceof IWwwRestItem))
        {
            obj10 = "application/x-www-form-urlencoded";
            irestitem = ((IWwwRestItem)irestitem).toStringObject();
            break MISSING_BLOCK_LABEL_122;
        }
        obj10 = "text/plain";
        irestitem = null;
        jp/pixela/pis_client/rest/common/CommonRestClient;
        JVM INSTR monitorenter ;
        Object obj11;
        Object obj13;
        obj11 = JVM INSTR new #86  <Class URL>;
        obj13 = JVM INSTR new #88  <Class StringBuilder>;
        ((StringBuilder) (obj13)).StringBuilder();
        ((StringBuilder) (obj13)).append(((ViewingPointApiConfig) (obj)).getHostURL());
        ((StringBuilder) (obj13)).append(s);
        ((URL) (obj11)).URL(((StringBuilder) (obj13)).toString());
        obj13 = (HttpURLConnection)((URL) (obj11)).openConnection();
        if(obj13 == null)
            break MISSING_BLOCK_LABEL_2127;
        ((HttpURLConnection) (obj13)).setConnectTimeout(5000);
        ((HttpURLConnection) (obj13)).setReadTimeout(5000);
        ((HttpURLConnection) (obj13)).setRequestMethod(s1);
        if("GET".equals(s1))
        {
            ((HttpURLConnection) (obj13)).setDoInput(true);
            break MISSING_BLOCK_LABEL_247;
        }
        Object obj12;
        int j;
        int k;
        try
        {
            if("POST".equals(s1))
            {
                ((HttpURLConnection) (obj13)).setInstanceFollowRedirects(false);
                ((HttpURLConnection) (obj13)).setRequestProperty("Accept-Language", "jp");
            }
        }
        // Misplaced declaration of an exception variable
        catch(Object obj12)
        {
            obj8 = null;
            obj = obj8;
            obj1 = obj7;
            abyte0 = ((byte []) (obj13));
            break MISSING_BLOCK_LABEL_4353;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj12)
        {
            obj8 = null;
            obj = obj8;
            obj1 = obj6;
            abyte0 = ((byte []) (obj13));
            break MISSING_BLOCK_LABEL_4119;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj12)
        {
            obj8 = null;
            obj = obj8;
            obj1 = obj5;
            abyte0 = ((byte []) (obj13));
            break MISSING_BLOCK_LABEL_3885;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj12)
        {
            obj8 = null;
            obj = obj8;
            obj1 = obj4;
            abyte0 = ((byte []) (obj13));
            break MISSING_BLOCK_LABEL_3651;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj12)
        {
            obj8 = null;
            obj = obj8;
            obj1 = s4;
            abyte0 = ((byte []) (obj13));
            break MISSING_BLOCK_LABEL_3417;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj12)
        {
            obj8 = null;
            obj = obj8;
            obj1 = s3;
            abyte0 = ((byte []) (obj13));
            break MISSING_BLOCK_LABEL_3183;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj12)
        {
            obj8 = null;
            obj = obj8;
            obj1 = s2;
            abyte0 = ((byte []) (obj13));
            break MISSING_BLOCK_LABEL_2949;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj12)
        {
            obj8 = null;
            obj = obj8;
            obj1 = obj3;
            abyte0 = ((byte []) (obj13));
            break MISSING_BLOCK_LABEL_2715;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj12)
        {
            obj8 = null;
            obj = obj8;
            obj1 = obj2;
            abyte0 = ((byte []) (obj13));
            break MISSING_BLOCK_LABEL_2481;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj12)
        {
            abyte0 = null;
            obj8 = abyte0;
            obj = obj13;
            break MISSING_BLOCK_LABEL_2249;
        }
        if(irestitem == null)
            break MISSING_BLOCK_LABEL_269;
        ((HttpURLConnection) (obj13)).setDoOutput(true);
        ((HttpURLConnection) (obj13)).setRequestProperty("Content-Type", ((String) (obj10)));
        break MISSING_BLOCK_LABEL_302;
        if("PUT".equals(s1) || "POST".equals(s1))
        {
            ((HttpURLConnection) (obj13)).setDoOutput(true);
            ((HttpURLConnection) (obj13)).setRequestProperty("Content-Type", "text/plain");
        }
        byte abyte0[] = EncryptionUtility.decrypt("2PgHZfCvCJZOdQJElDHE2e58I8mCsMTZLGLHBhNGFzh0lgEYOpeDXzi7LI4cvPufpUKmpSwgvxl0 3FcYFcZlOScQYaGH3nveGEr7tR+izik=".replace(' ', '\n'));
        s1 = JVM INSTR new #121 <Class String>;
        s1.String(abyte0, StandardCharsets.UTF_8);
        ((HttpURLConnection) (obj13)).setRequestProperty("X-Api-Key", s1);
        ((HttpURLConnection) (obj13)).setRequestProperty("Accept", "application/json");
        ((HttpURLConnection) (obj13)).setConnectTimeout(((ViewingPointApiConfig) (obj)).getConnectTimeout());
        ((HttpURLConnection) (obj13)).setReadTimeout(((ViewingPointApiConfig) (obj)).getReadTimeout());
        writeSendLog(((URL) (obj11)).toString(), ((HttpURLConnection) (obj13)), irestitem);
        if(obj13 instanceof HttpsURLConnection)
        {
            s1 = JVM INSTR new #6   <Class ViewingPointRestClient$1>;
            s1.this. _cls1();
            HttpsURLConnection.setDefaultHostnameVerifier(s1);
        }
        abyte0 = TAG;
        s1 = JVM INSTR new #88  <Class StringBuilder>;
        s1.StringBuilder();
        s1.append("http communication : resourcePath=");
        s1.append(s);
        PxLog.i(abyte0, s1.toString());
        ((HttpURLConnection) (obj13)).connect();
        if(irestitem == null)
            break MISSING_BLOCK_LABEL_537;
        s = JVM INSTR new #205 <Class OutputStreamWriter>;
        s.OutputStreamWriter(((HttpURLConnection) (obj13)).getOutputStream(), "UTF-8");
        obj3 = s;
        obj1 = s;
        s1 = s;
        obj2 = s;
        s2 = s;
        abyte0 = s;
        obj11 = s;
        obj = s;
        s3 = s;
        s4 = s;
        obj8 = s;
        s.write(irestitem.toString());
        obj3 = s;
        obj1 = s;
        s1 = s;
        obj2 = s;
        s2 = s;
        abyte0 = s;
        obj11 = s;
        obj = s;
        s3 = s;
        s4 = s;
        obj8 = s;
        s.flush();
        break MISSING_BLOCK_LABEL_539;
        s = null;
        obj3 = s;
        obj1 = s;
        s1 = s;
        obj2 = s;
        s2 = s;
        abyte0 = s;
        obj11 = s;
        obj = s;
        s3 = s;
        s4 = s;
        obj8 = s;
        j = ((HttpURLConnection) (obj13)).getResponseCode();
        obj3 = s;
        obj1 = s;
        s1 = s;
        obj2 = s;
        s2 = s;
        abyte0 = s;
        obj11 = s;
        obj = s;
        s3 = s;
        s4 = s;
        obj8 = s;
        irestitem = TAG;
        obj3 = s;
        obj1 = s;
        s1 = s;
        obj2 = s;
        s2 = s;
        abyte0 = s;
        obj11 = s;
        obj = s;
        s3 = s;
        s4 = s;
        obj8 = s;
        obj4 = JVM INSTR new #88  <Class StringBuilder>;
        obj3 = s;
        obj1 = s;
        s1 = s;
        obj2 = s;
        s2 = s;
        abyte0 = s;
        obj11 = s;
        obj = s;
        s3 = s;
        s4 = s;
        obj8 = s;
        ((StringBuilder) (obj4)).StringBuilder();
        obj3 = s;
        obj1 = s;
        s1 = s;
        obj2 = s;
        s2 = s;
        abyte0 = s;
        obj11 = s;
        obj = s;
        s3 = s;
        s4 = s;
        obj8 = s;
        ((StringBuilder) (obj4)).append("http communication : responseCode=");
        obj3 = s;
        obj1 = s;
        s1 = s;
        obj2 = s;
        s2 = s;
        abyte0 = s;
        obj11 = s;
        obj = s;
        s3 = s;
        s4 = s;
        obj8 = s;
        ((StringBuilder) (obj4)).append(j);
        obj3 = s;
        obj1 = s;
        s1 = s;
        obj2 = s;
        s2 = s;
        abyte0 = s;
        obj11 = s;
        obj = s;
        s3 = s;
        s4 = s;
        obj8 = s;
        PxLog.i(irestitem, ((StringBuilder) (obj4)).toString());
        if(j == 200)
            break MISSING_BLOCK_LABEL_1045;
        obj3 = s;
        obj1 = s;
        s1 = s;
        obj2 = s;
        s2 = s;
        abyte0 = s;
        obj11 = s;
        obj = s;
        s3 = s;
        s4 = s;
        obj8 = s;
        obj4 = TAG;
        obj3 = s;
        obj1 = s;
        s1 = s;
        obj2 = s;
        s2 = s;
        abyte0 = s;
        obj11 = s;
        obj = s;
        s3 = s;
        s4 = s;
        obj8 = s;
        irestitem = JVM INSTR new #88  <Class StringBuilder>;
        obj3 = s;
        obj1 = s;
        s1 = s;
        obj2 = s;
        s2 = s;
        abyte0 = s;
        obj11 = s;
        obj = s;
        s3 = s;
        s4 = s;
        obj8 = s;
        irestitem.StringBuilder();
        obj3 = s;
        obj1 = s;
        s1 = s;
        obj2 = s;
        s2 = s;
        abyte0 = s;
        obj11 = s;
        obj = s;
        s3 = s;
        s4 = s;
        obj8 = s;
        irestitem.append("Error. Json http communication error: ");
        obj3 = s;
        obj1 = s;
        s1 = s;
        obj2 = s;
        s2 = s;
        abyte0 = s;
        obj11 = s;
        obj = s;
        s3 = s;
        s4 = s;
        obj8 = s;
        irestitem.append(j);
        obj3 = s;
        obj1 = s;
        s1 = s;
        obj2 = s;
        s2 = s;
        abyte0 = s;
        obj11 = s;
        obj = s;
        s3 = s;
        s4 = s;
        obj8 = s;
        PxLog.e(((String) (obj4)), irestitem.toString());
        obj3 = s;
        obj1 = s;
        s1 = s;
        obj2 = s;
        s2 = s;
        abyte0 = s;
        obj11 = s;
        obj = s;
        s3 = s;
        s4 = s;
        obj8 = s;
        k = toRestResultCode(j);
        if(k != 0)
            break MISSING_BLOCK_LABEL_1133;
        obj3 = s;
        obj1 = s;
        s1 = s;
        obj2 = s;
        s2 = s;
        abyte0 = s;
        obj11 = s;
        obj = s;
        s3 = s;
        s4 = s;
        obj8 = s;
        irestitem = ((HttpURLConnection) (obj13)).getInputStream();
        s1 = irestitem;
        break MISSING_BLOCK_LABEL_1173;
        obj3 = s;
        obj1 = s;
        s1 = s;
        obj2 = s;
        s2 = s;
        abyte0 = s;
        obj11 = s;
        obj = s;
        s3 = s;
        s4 = s;
        obj8 = s;
        irestitem = ((HttpURLConnection) (obj13)).getErrorStream();
        s1 = irestitem;
        abyte0 = s1;
        obj8 = obj9;
        irestitem = s;
        i = k;
        if(s1 == null)
            break MISSING_BLOCK_LABEL_2139;
        try
        {
            irestitem = JVM INSTR new #247 <Class BufferedReader>;
            abyte0 = JVM INSTR new #249 <Class InputStreamReader>;
            abyte0.InputStreamReader(s1, "UTF-8");
            irestitem.BufferedReader(abyte0);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            irestitem = null;
            obj8 = s1;
            s1 = abyte0;
            break MISSING_BLOCK_LABEL_1906;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            irestitem = null;
            obj8 = s1;
            s1 = abyte0;
            break MISSING_BLOCK_LABEL_1876;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            irestitem = null;
            obj8 = s1;
            s1 = abyte0;
            break MISSING_BLOCK_LABEL_1850;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            irestitem = null;
            obj8 = s1;
            s1 = abyte0;
            break MISSING_BLOCK_LABEL_1824;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            irestitem = null;
            obj8 = s1;
            s1 = abyte0;
            break MISSING_BLOCK_LABEL_1798;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            irestitem = null;
            obj8 = s1;
            s1 = abyte0;
            break MISSING_BLOCK_LABEL_1772;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            irestitem = null;
            obj8 = s1;
            s1 = abyte0;
            break MISSING_BLOCK_LABEL_1746;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            irestitem = null;
            obj8 = s1;
            s1 = abyte0;
            break MISSING_BLOCK_LABEL_1720;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            irestitem = null;
            obj8 = s1;
            s1 = abyte0;
            break MISSING_BLOCK_LABEL_1694;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj8)
        {
            irestitem = null;
            abyte0 = s1;
            s1 = ((String) (obj8));
            break MISSING_BLOCK_LABEL_1665;
        }
        obj8 = JVM INSTR new #88  <Class StringBuilder>;
        ((StringBuilder) (obj8)).StringBuilder();
_L1:
        abyte0 = irestitem.readLine();
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_1245;
        try
        {
            ((StringBuilder) (obj8)).append(abyte0);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            obj8 = s1;
            s1 = abyte0;
            break MISSING_BLOCK_LABEL_1906;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            obj8 = s1;
            s1 = abyte0;
            break MISSING_BLOCK_LABEL_1876;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            obj8 = s1;
            s1 = abyte0;
            break MISSING_BLOCK_LABEL_1850;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            obj8 = s1;
            s1 = abyte0;
            break MISSING_BLOCK_LABEL_1824;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            obj8 = s1;
            s1 = abyte0;
            break MISSING_BLOCK_LABEL_1798;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            obj8 = s1;
            s1 = abyte0;
            break MISSING_BLOCK_LABEL_1772;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            obj8 = s1;
            s1 = abyte0;
            break MISSING_BLOCK_LABEL_1746;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            obj8 = s1;
            s1 = abyte0;
            break MISSING_BLOCK_LABEL_1720;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            obj8 = s1;
            s1 = abyte0;
            break MISSING_BLOCK_LABEL_1694;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj8)
        {
            abyte0 = s1;
            s1 = ((String) (obj8));
            break MISSING_BLOCK_LABEL_1665;
        }
          goto _L1
        if(obj8 == null)
            break MISSING_BLOCK_LABEL_1319;
        if(!"".equals(((StringBuilder) (obj8)).toString()))
        {
            abyte0 = JVM INSTR new #72  <Class JSONObject>;
            obj = JVM INSTR new #88  <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            ((StringBuilder) (obj)).append("{\"service_ids\":");
            ((StringBuilder) (obj)).append(((StringBuilder) (obj8)).toString());
            ((StringBuilder) (obj)).append("}");
            abyte0.JSONObject(((StringBuilder) (obj)).toString());
            break MISSING_BLOCK_LABEL_1328;
        }
        abyte0 = new JSONObject();
        obj8 = JVM INSTR new #264 <Class JsonRestResponse>;
        ((JsonRestResponse) (obj8)).JsonRestResponse(abyte0);
        mResponse = ((JsonRestResponse) (obj8));
        writeResponseLog(j, abyte0);
        abyte0 = s1;
        obj8 = irestitem;
        irestitem = s;
        i = k;
        break MISSING_BLOCK_LABEL_2139;
        obj8;
        abyte0 = s1;
        s1 = ((String) (obj8));
        break MISSING_BLOCK_LABEL_1643;
        obj8;
        irestitem = null;
        abyte0 = s1;
        s1 = ((String) (obj8));
        break MISSING_BLOCK_LABEL_1643;
        s1;
        abyte0 = null;
        irestitem = abyte0;
        s = ((String) (obj3));
        obj8 = s;
        s = abyte0;
        abyte0 = s1;
        break MISSING_BLOCK_LABEL_4582;
        s1;
        abyte0 = null;
        irestitem = abyte0;
        s = ((String) (obj1));
        obj1 = s;
        obj8 = irestitem;
        obj = obj13;
        obj11 = s1;
        break MISSING_BLOCK_LABEL_2249;
        abyte0;
        obj8 = null;
        irestitem = ((IRestItem) (obj8));
        s = s1;
        s1 = abyte0;
        obj1 = s;
        obj = irestitem;
        abyte0 = ((byte []) (obj13));
        obj11 = s1;
        break MISSING_BLOCK_LABEL_2481;
        s1;
        obj8 = null;
        irestitem = ((IRestItem) (obj8));
        s = ((String) (obj2));
        obj1 = s;
        obj = irestitem;
        abyte0 = ((byte []) (obj13));
        obj11 = s1;
        break MISSING_BLOCK_LABEL_2715;
        s1;
        obj8 = null;
        irestitem = ((IRestItem) (obj8));
        s = s2;
        obj1 = s;
        obj = irestitem;
        abyte0 = ((byte []) (obj13));
        obj11 = s1;
        break MISSING_BLOCK_LABEL_2949;
        s1;
        obj8 = null;
        irestitem = ((IRestItem) (obj8));
        s = abyte0;
        obj1 = s;
        obj = irestitem;
        abyte0 = ((byte []) (obj13));
        obj11 = s1;
        break MISSING_BLOCK_LABEL_3183;
        s1;
        obj8 = null;
        irestitem = ((IRestItem) (obj8));
        s = ((String) (obj11));
        obj1 = s;
        obj = irestitem;
        abyte0 = ((byte []) (obj13));
        obj12 = s1;
        break MISSING_BLOCK_LABEL_3417;
        s1;
        obj8 = null;
        irestitem = ((IRestItem) (obj8));
        s = ((String) (obj));
        obj1 = s;
        obj = irestitem;
        abyte0 = ((byte []) (obj13));
        obj12 = s1;
        break MISSING_BLOCK_LABEL_3651;
        s1;
        obj8 = null;
        irestitem = ((IRestItem) (obj8));
        s = s3;
        obj1 = s;
        obj = irestitem;
        abyte0 = ((byte []) (obj13));
        obj12 = s1;
        break MISSING_BLOCK_LABEL_3885;
        s1;
        obj8 = null;
        irestitem = ((IRestItem) (obj8));
        s = s4;
        obj1 = s;
        obj = irestitem;
        abyte0 = ((byte []) (obj13));
        obj12 = s1;
        break MISSING_BLOCK_LABEL_4119;
        s1;
        abyte0 = null;
        irestitem = abyte0;
        s = ((String) (obj8));
        obj8 = abyte0;
        obj1 = s;
        obj = irestitem;
        abyte0 = ((byte []) (obj13));
        obj12 = s1;
        break MISSING_BLOCK_LABEL_4353;
        abyte0;
        s = null;
        irestitem = s;
        break MISSING_BLOCK_LABEL_4582;
        i = -1;
        irestitem = null;
        abyte0 = irestitem;
        obj8 = obj9;
        if(irestitem == null)
            break MISSING_BLOCK_LABEL_2154;
        irestitem.close();
        if(obj8 == null)
            break MISSING_BLOCK_LABEL_2164;
        ((BufferedReader) (obj8)).close();
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_2211;
        try
        {
            abyte0.close();
            break MISSING_BLOCK_LABEL_2211;
        }
        // Misplaced declaration of an exception variable
        catch(IRestItem irestitem) { }
        s1 = TAG;
        s = JVM INSTR new #88  <Class StringBuilder>;
        s.StringBuilder();
        s.append("e=");
        s.append(irestitem);
        PxLog.e(s1, s.toString());
        if(obj13 == null)
            break MISSING_BLOCK_LABEL_4571;
        ((HttpURLConnection) (obj13)).disconnect();
        break MISSING_BLOCK_LABEL_4571;
        abyte0;
        s = null;
        irestitem = s;
        obj13 = irestitem;
        break MISSING_BLOCK_LABEL_4582;
        obj12;
        abyte0 = null;
        obj8 = abyte0;
        obj = obj8;
        s = abyte0;
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj8));
        obj13 = obj;
        obj3 = TAG;
        s = abyte0;
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj8));
        obj13 = obj;
        obj2 = JVM INSTR new #88  <Class StringBuilder>;
        s = abyte0;
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj8));
        obj13 = obj;
        ((StringBuilder) (obj2)).StringBuilder();
        s = abyte0;
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj8));
        obj13 = obj;
        ((StringBuilder) (obj2)).append("e=");
        s = abyte0;
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj8));
        obj13 = obj;
        ((StringBuilder) (obj2)).append(obj12);
        s = abyte0;
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj8));
        obj13 = obj;
        PxLog.e(((String) (obj3)), ((StringBuilder) (obj2)).toString());
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_2386;
        ((OutputStreamWriter) (obj1)).close();
        if(obj8 == null)
            break MISSING_BLOCK_LABEL_2396;
        ((BufferedReader) (obj8)).close();
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_2443;
        try
        {
            abyte0.close();
            break MISSING_BLOCK_LABEL_2443;
        }
        // Misplaced declaration of an exception variable
        catch(IRestItem irestitem) { }
        s = TAG;
        s1 = JVM INSTR new #88  <Class StringBuilder>;
        s1.StringBuilder();
        s1.append("e=");
        s1.append(irestitem);
        PxLog.e(s, s1.toString());
        i = byte0;
        if(obj == null)
            break MISSING_BLOCK_LABEL_4571;
        ((HttpURLConnection) (obj)).disconnect();
        i = byte0;
        break MISSING_BLOCK_LABEL_4571;
        obj12;
        obj8 = null;
        obj = obj8;
        abyte0 = ((byte []) (obj));
        obj1 = obj2;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        obj2 = TAG;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        obj3 = JVM INSTR new #88  <Class StringBuilder>;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj3)).StringBuilder();
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj3)).append("e=");
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj3)).append(obj12);
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        PxLog.e(((String) (obj2)), ((StringBuilder) (obj3)).toString());
        byte0 = -5;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_2622;
        ((OutputStreamWriter) (obj1)).close();
        if(obj == null)
            break MISSING_BLOCK_LABEL_2632;
        ((BufferedReader) (obj)).close();
        if(obj8 == null)
            break MISSING_BLOCK_LABEL_2679;
        try
        {
            ((InputStream) (obj8)).close();
            break MISSING_BLOCK_LABEL_2679;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        s1 = TAG;
        irestitem = JVM INSTR new #88  <Class StringBuilder>;
        irestitem.StringBuilder();
        irestitem.append("e=");
        irestitem.append(s);
        PxLog.e(s1, irestitem.toString());
        i = byte0;
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_4571;
        s = abyte0;
        i = byte0;
        break MISSING_BLOCK_LABEL_4567;
        obj12;
        obj8 = null;
        obj = obj8;
        abyte0 = ((byte []) (obj));
        obj1 = obj3;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        obj3 = TAG;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        obj2 = JVM INSTR new #88  <Class StringBuilder>;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj2)).StringBuilder();
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj2)).append("e=");
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj2)).append(obj12);
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        PxLog.e(((String) (obj3)), ((StringBuilder) (obj2)).toString());
        byte0 = -15;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_2856;
        ((OutputStreamWriter) (obj1)).close();
        if(obj == null)
            break MISSING_BLOCK_LABEL_2866;
        ((BufferedReader) (obj)).close();
        if(obj8 == null)
            break MISSING_BLOCK_LABEL_2913;
        try
        {
            ((InputStream) (obj8)).close();
            break MISSING_BLOCK_LABEL_2913;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        irestitem = TAG;
        s1 = JVM INSTR new #88  <Class StringBuilder>;
        s1.StringBuilder();
        s1.append("e=");
        s1.append(s);
        PxLog.e(irestitem, s1.toString());
        i = byte0;
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_4571;
        s = abyte0;
        i = byte0;
        break MISSING_BLOCK_LABEL_4567;
        obj12;
        obj8 = null;
        obj = obj8;
        abyte0 = ((byte []) (obj));
        obj1 = s2;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        obj2 = TAG;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        obj3 = JVM INSTR new #88  <Class StringBuilder>;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj3)).StringBuilder();
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj3)).append("e=");
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj3)).append(obj12);
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        PxLog.e(((String) (obj2)), ((StringBuilder) (obj3)).toString());
        byte0 = -14;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_3090;
        ((OutputStreamWriter) (obj1)).close();
        if(obj == null)
            break MISSING_BLOCK_LABEL_3100;
        ((BufferedReader) (obj)).close();
        if(obj8 == null)
            break MISSING_BLOCK_LABEL_3147;
        try
        {
            ((InputStream) (obj8)).close();
            break MISSING_BLOCK_LABEL_3147;
        }
        // Misplaced declaration of an exception variable
        catch(IRestItem irestitem) { }
        s1 = TAG;
        s = JVM INSTR new #88  <Class StringBuilder>;
        s.StringBuilder();
        s.append("e=");
        s.append(irestitem);
        PxLog.e(s1, s.toString());
        i = byte0;
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_4571;
        s = abyte0;
        i = byte0;
        break MISSING_BLOCK_LABEL_4567;
        obj12;
        obj8 = null;
        obj = obj8;
        abyte0 = ((byte []) (obj));
        obj1 = s3;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        obj3 = TAG;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        obj2 = JVM INSTR new #88  <Class StringBuilder>;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj2)).StringBuilder();
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj2)).append("e=");
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj2)).append(obj12);
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        PxLog.e(((String) (obj3)), ((StringBuilder) (obj2)).toString());
        byte0 = -13;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_3324;
        ((OutputStreamWriter) (obj1)).close();
        if(obj == null)
            break MISSING_BLOCK_LABEL_3334;
        ((BufferedReader) (obj)).close();
        if(obj8 == null)
            break MISSING_BLOCK_LABEL_3381;
        try
        {
            ((InputStream) (obj8)).close();
            break MISSING_BLOCK_LABEL_3381;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        irestitem = TAG;
        s1 = JVM INSTR new #88  <Class StringBuilder>;
        s1.StringBuilder();
        s1.append("e=");
        s1.append(s);
        PxLog.e(irestitem, s1.toString());
        i = byte0;
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_4571;
        s = abyte0;
        i = byte0;
        break MISSING_BLOCK_LABEL_4567;
        obj12;
        obj8 = null;
        obj = obj8;
        abyte0 = ((byte []) (obj));
        obj1 = s4;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        obj2 = TAG;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        obj3 = JVM INSTR new #88  <Class StringBuilder>;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj3)).StringBuilder();
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj3)).append("e=");
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj3)).append(obj12);
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        PxLog.e(((String) (obj2)), ((StringBuilder) (obj3)).toString());
        byte0 = -12;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_3558;
        ((OutputStreamWriter) (obj1)).close();
        if(obj == null)
            break MISSING_BLOCK_LABEL_3568;
        ((BufferedReader) (obj)).close();
        if(obj8 == null)
            break MISSING_BLOCK_LABEL_3615;
        try
        {
            ((InputStream) (obj8)).close();
            break MISSING_BLOCK_LABEL_3615;
        }
        // Misplaced declaration of an exception variable
        catch(String s1) { }
        s = TAG;
        irestitem = JVM INSTR new #88  <Class StringBuilder>;
        irestitem.StringBuilder();
        irestitem.append("e=");
        irestitem.append(s1);
        PxLog.e(s, irestitem.toString());
        i = byte0;
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_4571;
        s = abyte0;
        i = byte0;
        break MISSING_BLOCK_LABEL_4567;
        obj12;
        obj8 = null;
        obj = obj8;
        abyte0 = ((byte []) (obj));
        obj1 = obj4;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        obj2 = TAG;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        obj3 = JVM INSTR new #88  <Class StringBuilder>;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj3)).StringBuilder();
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj3)).append("e=");
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj3)).append(obj12);
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        PxLog.e(((String) (obj2)), ((StringBuilder) (obj3)).toString());
        byte0 = -11;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_3792;
        ((OutputStreamWriter) (obj1)).close();
        if(obj == null)
            break MISSING_BLOCK_LABEL_3802;
        ((BufferedReader) (obj)).close();
        if(obj8 == null)
            break MISSING_BLOCK_LABEL_3849;
        try
        {
            ((InputStream) (obj8)).close();
            break MISSING_BLOCK_LABEL_3849;
        }
        // Misplaced declaration of an exception variable
        catch(String s1) { }
        s = TAG;
        irestitem = JVM INSTR new #88  <Class StringBuilder>;
        irestitem.StringBuilder();
        irestitem.append("e=");
        irestitem.append(s1);
        PxLog.e(s, irestitem.toString());
        i = byte0;
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_4571;
        s = abyte0;
        i = byte0;
        break MISSING_BLOCK_LABEL_4567;
        obj12;
        obj8 = null;
        obj = obj8;
        abyte0 = ((byte []) (obj));
        obj1 = obj5;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        obj2 = TAG;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        obj3 = JVM INSTR new #88  <Class StringBuilder>;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj3)).StringBuilder();
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj3)).append("e=");
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj3)).append(obj12);
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        PxLog.e(((String) (obj2)), ((StringBuilder) (obj3)).toString());
        byte0 = -4;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_4026;
        ((OutputStreamWriter) (obj1)).close();
        if(obj == null)
            break MISSING_BLOCK_LABEL_4036;
        ((BufferedReader) (obj)).close();
        if(obj8 == null)
            break MISSING_BLOCK_LABEL_4083;
        try
        {
            ((InputStream) (obj8)).close();
            break MISSING_BLOCK_LABEL_4083;
        }
        // Misplaced declaration of an exception variable
        catch(IRestItem irestitem) { }
        s = TAG;
        s1 = JVM INSTR new #88  <Class StringBuilder>;
        s1.StringBuilder();
        s1.append("e=");
        s1.append(irestitem);
        PxLog.e(s, s1.toString());
        i = byte0;
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_4571;
        s = abyte0;
        i = byte0;
        break MISSING_BLOCK_LABEL_4567;
        obj12;
        obj8 = null;
        obj = obj8;
        abyte0 = ((byte []) (obj));
        obj1 = obj6;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        obj2 = TAG;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        obj3 = JVM INSTR new #88  <Class StringBuilder>;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj3)).StringBuilder();
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj3)).append("e=");
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj3)).append(obj12);
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        PxLog.e(((String) (obj2)), ((StringBuilder) (obj3)).toString());
        byte0 = -3;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_4260;
        ((OutputStreamWriter) (obj1)).close();
        if(obj == null)
            break MISSING_BLOCK_LABEL_4270;
        ((BufferedReader) (obj)).close();
        if(obj8 == null)
            break MISSING_BLOCK_LABEL_4317;
        try
        {
            ((InputStream) (obj8)).close();
            break MISSING_BLOCK_LABEL_4317;
        }
        // Misplaced declaration of an exception variable
        catch(String s1) { }
        s = TAG;
        irestitem = JVM INSTR new #88  <Class StringBuilder>;
        irestitem.StringBuilder();
        irestitem.append("e=");
        irestitem.append(s1);
        PxLog.e(s, irestitem.toString());
        i = byte0;
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_4571;
        s = abyte0;
        i = byte0;
        break MISSING_BLOCK_LABEL_4567;
        obj12;
        obj8 = null;
        obj = obj8;
        abyte0 = ((byte []) (obj));
        obj1 = obj7;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        obj2 = TAG;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        obj3 = JVM INSTR new #88  <Class StringBuilder>;
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj3)).StringBuilder();
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj3)).append("e=");
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        ((StringBuilder) (obj3)).append(obj12);
        s = ((String) (obj8));
        s1 = ((String) (obj1));
        irestitem = ((IRestItem) (obj));
        obj13 = abyte0;
        PxLog.e(((String) (obj2)), ((StringBuilder) (obj3)).toString());
        byte0 = -2;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_4494;
        ((OutputStreamWriter) (obj1)).close();
        if(obj == null)
            break MISSING_BLOCK_LABEL_4504;
        ((BufferedReader) (obj)).close();
        if(obj8 == null)
            break MISSING_BLOCK_LABEL_4551;
        try
        {
            ((InputStream) (obj8)).close();
            break MISSING_BLOCK_LABEL_4551;
        }
        // Misplaced declaration of an exception variable
        catch(String s1) { }
        s = TAG;
        irestitem = JVM INSTR new #88  <Class StringBuilder>;
        irestitem.StringBuilder();
        irestitem.append("e=");
        irestitem.append(s1);
        PxLog.e(s, irestitem.toString());
        i = byte0;
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_4571;
        i = byte0;
        s = abyte0;
        s.disconnect();
        jp/pixela/pis_client/rest/common/CommonRestClient;
        JVM INSTR monitorexit ;
        break MISSING_BLOCK_LABEL_4674;
        abyte0;
        obj8 = s1;
        if(obj8 == null)
            break MISSING_BLOCK_LABEL_4603;
        ((OutputStreamWriter) (obj8)).close();
        break MISSING_BLOCK_LABEL_4603;
        s;
        break MISSING_BLOCK_LABEL_4671;
        if(irestitem == null)
            break MISSING_BLOCK_LABEL_4611;
        irestitem.close();
        if(s == null)
            break MISSING_BLOCK_LABEL_4656;
        try
        {
            s.close();
            break MISSING_BLOCK_LABEL_4656;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        s1 = TAG;
        irestitem = JVM INSTR new #88  <Class StringBuilder>;
        irestitem.StringBuilder();
        irestitem.append("e=");
        irestitem.append(s);
        PxLog.e(s1, irestitem.toString());
        if(obj13 == null)
            break MISSING_BLOCK_LABEL_4666;
        ((HttpURLConnection) (obj13)).disconnect();
        throw abyte0;
        throw s;
        s1 = TAG;
        s = new StringBuilder();
        s.append("EXIT: ret=");
        s.append(i);
        PxLog.d(s1, s.toString());
        return i;
    }

    private static final String API_KEY = "2PgHZfCvCJZOdQJElDHE2e58I8mCsMTZLGLHBhNGFzh0lgEYOpeDXzi7LI4cvPufpUKmpSwgvxl0 3FcYFcZlOScQYaGH3nveGEr7tR+izik=";
    private static final int CONNECT_TIMEOUT_MSEC = 5000;
    private static final String PREFIX = "{\"service_ids\":";
    private static final int READ_TIMEOUT_MSEC = 5000;
    private static final String SUFFIX = "}";
    private static final String TAG = "ViewingPointRestClient";



    // Unreferenced inner class jp/pixela/pis_client/rest/rank/ViewingPointRestClient$1

/* anonymous class */
    class _cls1
        implements HostnameVerifier
    {

        public boolean verify(String s, SSLSession sslsession)
        {
            String s1 = ViewingPointRestClient.TAG;
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("ENTER: hostname=");
            stringbuilder.append(s);
            PxLog.d(s1, stringbuilder.toString());
            boolean flag;
            if(sslsession != null && s.equals(sslsession.getPeerHost()))
                flag = true;
            else
                flag = false;
            sslsession = ViewingPointRestClient.TAG;
            s = new StringBuilder();
            s.append("EXIT: ret=");
            s.append(flag);
            PxLog.d(sslsession, s.toString());
            return flag;
        }

        final ViewingPointRestClient this$0;

            
            {
                this$0 = ViewingPointRestClient.this;
                super();
            }
    }

}
