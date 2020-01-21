// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.common_at;

import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import jp.pixela.pis_client.rest.common.CommonRestClient;
import jp.pixela.pis_client.rest.common.IRestConfig;
import jp.pixela.pis_client.rest.utility.EncryptionUtility;
import jp.pixela.pxlibs.utils.android.log.Logger;
import jp.pixela.pxlibs.utils.android.log.LoggerRTM;

public class CommonAtRestClient extends CommonRestClient
{

    public CommonAtRestClient(IRestConfig irestconfig, String s)
    {
        super(irestconfig);
        mPrefixKey = s;
    }

    protected String getCustomResponseString(String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(LOG_HEAD);
        stringbuilder.append("in");
        Logger.v(stringbuilder.toString(), new Object[0]);
        if(s == null)
        {
            s = new StringBuilder();
            s.append(LOG_HEAD);
            s.append("out. responseString == null");
            Logger.v(s.toString(), new Object[0]);
            return null;
        }
        if(s.isEmpty())
        {
            s = new StringBuilder();
            s.append(LOG_HEAD);
            s.append("out. responseString.isEmpty()");
            Logger.v(s.toString(), new Object[0]);
            return null;
        } else
        {
            String s1 = String.format("{\"%s\":", new Object[] {
                mPrefixKey
            });
            Object obj = new StringBuilder();
            ((StringBuilder) (obj)).append(s1);
            ((StringBuilder) (obj)).append(s);
            ((StringBuilder) (obj)).append("}");
            obj = ((StringBuilder) (obj)).toString();
            s = new StringBuilder();
            s.append(LOG_HEAD);
            s.append("out. ret=");
            s.append(((String) (obj)));
            Logger.v(s.toString(), new Object[0]);
            return ((String) (obj));
        }
    }

    protected void setCustomRequestProperty(HttpURLConnection httpurlconnection, IRestConfig irestconfig)
    {
        irestconfig = new StringBuilder();
        irestconfig.append(LOG_HEAD);
        irestconfig.append("in");
        Logger.v(irestconfig.toString(), new Object[0]);
        try
        {
            byte abyte0[] = EncryptionUtility.decrypt("2PgHZfCvCJZOdQJElDHE2e58I8mCsMTZLGLHBhNGFzh0lgEYOpeDXzi7LI4cvPufpUKmpSwgvxl0 3FcYFcZlOScQYaGH3nveGEr7tR+izik=".replace(' ', '\n'));
            irestconfig = JVM INSTR new #63  <Class String>;
            irestconfig.String(abyte0, StandardCharsets.UTF_8);
        }
        catch(Exception exception)
        {
            irestconfig = new StringBuilder();
            irestconfig.append(LOG_HEAD);
            irestconfig.append("e=");
            irestconfig.append(exception);
            LoggerRTM.e(irestconfig.toString(), new Object[0]);
            irestconfig = null;
        }
        httpurlconnection.setRequestProperty("X-Api-Key", irestconfig);
        httpurlconnection = new StringBuilder();
        httpurlconnection.append(LOG_HEAD);
        httpurlconnection.append("out");
        Logger.v(httpurlconnection.toString(), new Object[0]);
    }

    private static final String API_KEY = "2PgHZfCvCJZOdQJElDHE2e58I8mCsMTZLGLHBhNGFzh0lgEYOpeDXzi7LI4cvPufpUKmpSwgvxl0 3FcYFcZlOScQYaGH3nveGEr7tR+izik=";
    private static final String LOG_HEAD;
    private static final String PREFIX = "{\"%s\":";
    private static final String SUFFIX = "}";
    private String mPrefixKey;

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/pis_client/rest/common_at/CommonAtRestClient.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }
}
