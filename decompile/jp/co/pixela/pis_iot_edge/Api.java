// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.co.pixela.pis_iot_edge;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import jp.co.pixela.pis_iot_edge.common.PxLog;
import org.json.JSONObject;

// Referenced classes of package jp.co.pixela.pis_iot_edge:
//            Token, Device

class Api
{
    public class DeviceFlowCode
    {

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("{deviceCode:");
            stringbuilder.append(deviceCode);
            stringbuilder.append(", userCode:");
            stringbuilder.append(userCode);
            stringbuilder.append("}");
            return stringbuilder.toString();
        }

        public final String deviceCode;
        final Api this$0;
        public final String userCode;

        public DeviceFlowCode(String s, String s1)
        {
            this$0 = Api.this;
            super();
            deviceCode = s;
            userCode = s1;
        }
    }

    public class JsonResponse
    {

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("{status:");
            stringbuilder.append(status);
            stringbuilder.append(", jsonObj:");
            stringbuilder.append(jsonObj);
            stringbuilder.append("}");
            return stringbuilder.toString();
        }

        public final JSONObject jsonObj;
        public final int status;
        final Api this$0;

        public JsonResponse(int i, JSONObject jsonobject)
        {
            this$0 = Api.this;
            super();
            status = i;
            jsonObj = jsonobject;
        }
    }

    public class TokenResult
    {

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("{status:");
            stringbuilder.append(status);
            stringbuilder.append(", token:");
            stringbuilder.append(token);
            stringbuilder.append("}");
            return stringbuilder.toString();
        }

        public final int status;
        final Api this$0;
        public final Token token;

        public TokenResult(int i, Token token1)
        {
            this$0 = Api.this;
            super();
            status = i;
            token = token1;
        }
    }


    public Api(Context context)
    {
        context_ = null;
        context_ = context;
    }

    private static byte[] getErrorContent(HttpURLConnection httpurlconnection)
        throws Exception
    {
        return getResponseContentFromStream(httpurlconnection.getErrorStream());
    }

    private static byte[] getResponseContent(HttpURLConnection httpurlconnection)
        throws Exception
    {
        return getResponseContentFromStream(httpurlconnection.getInputStream());
    }

    private static byte[] getResponseContentFromStream(InputStream inputstream)
        throws Exception
    {
        byte abyte0[];
        abyte0 = JVM INSTR new #51  <Class ByteArrayOutputStream>;
        abyte0.ByteArrayOutputStream();
_L1:
        byte abyte1[];
        int i;
        abyte1 = new byte[1024];
        i = inputstream.read(abyte1, 0, abyte1.length);
        if(i > 0)
            break MISSING_BLOCK_LABEL_38;
        abyte0 = abyte0.toByteArray();
        inputstream.close();
        return abyte0;
        abyte0.write(abyte1, 0, i);
          goto _L1
        Exception exception;
        exception;
        inputstream.close();
        throw exception;
    }

    private JsonResponse postForm(String s, HashMap hashmap)
        throws Exception
    {
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append("postForm  uri:");
        ((StringBuilder) (obj)).append(s);
        PxLog.d("PisIotEdgeService", ((StringBuilder) (obj)).toString());
        s = new URL(s);
        android.net.Uri.Builder builder = new android.net.Uri.Builder();
        String s1;
        for(obj = hashmap.keySet().iterator(); ((Iterator) (obj)).hasNext(); builder.appendQueryParameter(s1, (String)hashmap.get(s1)))
            s1 = (String)((Iterator) (obj)).next();

        hashmap = builder.build().getEncodedQuery();
        obj = new StringBuilder();
        ((StringBuilder) (obj)).append("postForm param:");
        ((StringBuilder) (obj)).append(hashmap);
        PxLog.d("PisIotEdgeService", ((StringBuilder) (obj)).toString());
        hashmap = hashmap.getBytes(StandardCharsets.UTF_8);
        s = (HttpURLConnection)s.openConnection();
        s.setRequestMethod("POST");
        s.setInstanceFollowRedirects(true);
        s.setFixedLengthStreamingMode(hashmap.length);
        s.setDoInput(true);
        s.setRequestProperty("Accept", "application/json");
        s.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        s.connect();
        setRequestContent(s, hashmap);
        int i = s.getResponseCode();
        if(i == 200)
            s = new String(getResponseContent(s), StandardCharsets.UTF_8);
        else
            s = new String(getErrorContent(s), StandardCharsets.UTF_8);
        hashmap = new StringBuilder();
        hashmap.append("postForm: status=");
        hashmap.append(i);
        hashmap.append(", content=");
        hashmap.append(s);
        PxLog.d("PisIotEdgeService", hashmap.toString());
        hashmap = new JSONObject();
        if(!s.isEmpty())
            hashmap = new JSONObject(s);
        return new JsonResponse(i, hashmap);
    }

    private static void setRequestContent(HttpURLConnection httpurlconnection, byte abyte0[])
        throws Exception
    {
        httpurlconnection = httpurlconnection.getOutputStream();
        httpurlconnection.write(abyte0, 0, abyte0.length);
        httpurlconnection.flush();
        httpurlconnection.close();
        return;
        abyte0;
        httpurlconnection.close();
        throw abyte0;
    }

    public Token acquireToken(String s)
        throws Exception
    {
        s = (HttpURLConnection)(new URL(getTokenUriForAuthorizationCode(s))).openConnection();
        s.setRequestMethod("GET");
        s.connect();
        int i = s.getResponseCode();
        if(i == 200)
        {
            s = new String(getResponseContent(s), StandardCharsets.UTF_8);
            PxLog.d("PisIotEdgeService", s);
            return new Token(new JSONObject(s));
        } else
        {
            s = new StringBuilder();
            s.append("acquireToken: HTTP status = ");
            s.append(i);
            PxLog.e("PisIotEdgeService", s.toString());
            return null;
        }
    }

    public Device createDevice(Device device, Token token)
        throws Exception
    {
        byte abyte0[] = device.toString().getBytes(StandardCharsets.UTF_8);
        device = (HttpURLConnection)(new URL("https://pixela-dtv-device.azurewebsites.net/api/v1/Devices")).openConnection();
        device.setDoOutput(true);
        device.setDoInput(true);
        device.setRequestMethod("POST");
        device.setFixedLengthStreamingMode(abyte0.length);
        device.setInstanceFollowRedirects(true);
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(token.getTokenType());
        stringbuilder.append(" ");
        stringbuilder.append(token.getAccessToken());
        device.setRequestProperty("Authorization", stringbuilder.toString());
        device.setRequestProperty("Accept", "application/json");
        device.setRequestProperty("Accept-Charset", "UTF-8");
        device.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        device.connect();
        setRequestContent(device, abyte0);
        int i = device.getResponseCode();
        if(i == 200)
        {
            return new Device(new JSONObject(new String(getResponseContent(device), StandardCharsets.UTF_8)));
        } else
        {
            device = new StringBuilder();
            device.append("createDevice: HTTP status = ");
            device.append(i);
            PxLog.e("PisIotEdgeService", device.toString());
            return null;
        }
    }

    public boolean deleteDevice(String s, Token token)
        throws Exception
    {
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append("https://pixela-dtv-device.azurewebsites.net/api/v1/Devices/");
        ((StringBuilder) (obj)).append(s);
        obj = (HttpURLConnection)(new URL(((StringBuilder) (obj)).toString())).openConnection();
        ((HttpURLConnection) (obj)).setRequestMethod("DELETE");
        ((HttpURLConnection) (obj)).setInstanceFollowRedirects(true);
        s = new StringBuilder();
        s.append(token.getTokenType());
        s.append(" ");
        s.append(token.getAccessToken());
        ((HttpURLConnection) (obj)).setRequestProperty("Authorization", s.toString());
        ((HttpURLConnection) (obj)).connect();
        int i = ((HttpURLConnection) (obj)).getResponseCode();
        if(i == 200)
        {
            return true;
        } else
        {
            s = new StringBuilder();
            s.append("deleteDevice: HTTP status = ");
            s.append(i);
            PxLog.e("PisIotEdgeService", s.toString());
            return false;
        }
    }

    public String getAuthorizeUri(String s)
        throws Exception
    {
        return (new android.net.Uri.Builder()).scheme("https").authority(context_.getString(R.string.PIXELA_PASSPORT_LOGIN_HOST)).appendEncodedPath("tfp").appendEncodedPath(context_.getString(R.string.PIXELA_PASSPORT_TENANT)).appendEncodedPath(context_.getString(R.string.PIXELA_PASSPORT_SIGN_IN_POLICY_TENANT)).appendEncodedPath("oauth2/v2.0/authorize").appendQueryParameter("scope", TextUtils.join(" ", getScopeValues())).appendQueryParameter("response_type", "code").appendQueryParameter("client_id", context_.getString(R.string.PIXELA_PASSPORT_CLIENT_ID)).appendQueryParameter("redirect_uri", getRedirectUri()).appendQueryParameter("response_mode", "query").appendQueryParameter("state", s).appendQueryParameter("prompt", "login").toString();
    }

    public String getAuthorizeUriWithDeviceFlow(String s)
        throws Exception
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(context_.getString(R.string.DEVICE_FLOW_DISPLAY_URI));
        stringbuilder.append("?code=");
        stringbuilder.append(s);
        return stringbuilder.toString();
    }

    public Device getDevice(String s, Token token)
        throws Exception
    {
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append("https://pixela-dtv-device.azurewebsites.net/api/v1/Devices/");
        ((StringBuilder) (obj)).append(s);
        obj = (HttpURLConnection)(new URL(((StringBuilder) (obj)).toString())).openConnection();
        ((HttpURLConnection) (obj)).setRequestMethod("GET");
        ((HttpURLConnection) (obj)).setInstanceFollowRedirects(true);
        s = new StringBuilder();
        s.append(token.getTokenType());
        s.append(" ");
        s.append(token.getAccessToken());
        ((HttpURLConnection) (obj)).setRequestProperty("Authorization", s.toString());
        ((HttpURLConnection) (obj)).setRequestProperty("Accept", "application/json");
        ((HttpURLConnection) (obj)).setRequestProperty("Accept-Charset", "UTF-8");
        ((HttpURLConnection) (obj)).connect();
        int i = ((HttpURLConnection) (obj)).getResponseCode();
        if(i == 200)
        {
            return new Device(new JSONObject(new String(getResponseContent(((HttpURLConnection) (obj))), StandardCharsets.UTF_8)));
        } else
        {
            s = new StringBuilder();
            s.append("getDevice: HTTP status = ");
            s.append(i);
            PxLog.e("PisIotEdgeService", s.toString());
            return null;
        }
    }

    public DeviceFlowCode getDeviceFlowCode()
        throws Exception
    {
        Object obj = new HashMap();
        ((HashMap) (obj)).put("client_id", context_.getString(R.string.DEVICE_FLOW_CLIENT_ID));
        obj = postForm(context_.getString(R.string.DEVICE_FLOW_AUTH_URI), ((HashMap) (obj)));
        int i = ((JsonResponse) (obj)).status;
        obj = ((JsonResponse) (obj)).jsonObj;
        if(i == 200)
        {
            return new DeviceFlowCode(((JSONObject) (obj)).getString("device_code"), ((JSONObject) (obj)).getString("user_code"));
        } else
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("getDeviceFlowCode: HTTP status = ");
            stringbuilder.append(i);
            PxLog.e("PisIotEdgeService", stringbuilder.toString());
            return null;
        }
    }

    public String getRedirectUri()
    {
        return context_.getString(R.string.PIXELA_PASSPORT_REDIRECT_URI);
    }

    public String[] getScopeValues()
    {
        Uri uri = (new android.net.Uri.Builder()).scheme("https").authority(context_.getString(R.string.PIXELA_PASSPORT_TENANT)).appendEncodedPath("dtvdeviceapi").build();
        return (new String[] {
            "offline_access", uri.buildUpon().appendEncodedPath("write").toString(), uri.buildUpon().appendEncodedPath("read").toString()
        });
    }

    public String getTokenUriForAuthorizationCode(String s)
        throws Exception
    {
        return (new android.net.Uri.Builder()).scheme("https").authority(context_.getString(R.string.PIXELA_PASSPORT_LOGIN_HOST)).appendEncodedPath("tfp").appendEncodedPath(context_.getString(R.string.PIXELA_PASSPORT_TENANT)).appendEncodedPath(context_.getString(R.string.PIXELA_PASSPORT_SIGN_IN_POLICY_TENANT)).appendEncodedPath("oauth2/v2.0/token").appendQueryParameter("scope", TextUtils.join(" ", getScopeValues())).appendQueryParameter("grant_type", "authorization_code").appendQueryParameter("client_id", context_.getString(R.string.PIXELA_PASSPORT_CLIENT_ID)).appendQueryParameter("redirect_uri", getRedirectUri()).appendQueryParameter("code", s).toString();
    }

    public String getTokenUriForRefreshToken(String s)
        throws Exception
    {
        return (new android.net.Uri.Builder()).scheme("https").authority(context_.getString(R.string.PIXELA_PASSPORT_LOGIN_HOST)).appendEncodedPath("tfp").appendEncodedPath(context_.getString(R.string.PIXELA_PASSPORT_TENANT)).appendEncodedPath(context_.getString(R.string.PIXELA_PASSPORT_SIGN_IN_POLICY_TENANT)).appendEncodedPath("oauth2/v2.0/token").appendQueryParameter("scope", TextUtils.join(" ", getScopeValues())).appendQueryParameter("grant_type", "refresh_token").appendQueryParameter("client_id", context_.getString(R.string.PIXELA_PASSPORT_CLIENT_ID)).appendQueryParameter("redirect_uri", getRedirectUri()).appendQueryParameter("refresh_token", s).toString();
    }

    public TokenResult getTokenWithDeviceFlow(String s)
        throws Exception
    {
        Object obj = new HashMap();
        ((HashMap) (obj)).put("client_id", context_.getString(R.string.DEVICE_FLOW_CLIENT_ID));
        ((HashMap) (obj)).put("grant_type", "urn:ietf:params:oauth:grant-type:device_code");
        ((HashMap) (obj)).put("device_code", s);
        s = postForm(context_.getString(R.string.DEVICE_FLOW_TOKEN_URI), ((HashMap) (obj)));
        int i = ((JsonResponse) (s)).status;
        JSONObject jsonobject = ((JsonResponse) (s)).jsonObj;
        s = jsonobject.optString("error", null);
        obj = jsonobject.optString("access_token", null);
        String s1 = jsonobject.optString("refresh_token", null);
        if(obj != null && obj != "null" && s1 != null && s1 != "null")
        {
            jsonobject.put("is_device_flow", true);
            Token token = new Token(jsonobject);
            s = new StringBuilder();
            s.append("new token:");
            s.append(token);
            PxLog.d("PisIotEdgeService", s.toString());
            return new TokenResult(i, token);
        }
        if(s.equals("authorization_pending"))
            return new TokenResult(200, null);
        else
            return new TokenResult(i, null);
    }

    public boolean isSupported(String s)
    {
        s = Uri.parse(s);
        Uri uri = (new android.net.Uri.Builder()).scheme("https").authority(context_.getString(R.string.PIXELA_PASSPORT_LOGIN_HOST)).build();
        if(s.getScheme().equals(uri.getScheme()) && s.getAuthority().equals(uri.getAuthority()))
        {
            Object obj = new StringBuilder();
            ((StringBuilder) (obj)).append(context_.getString(R.string.PIXELA_PASSPORT_API_PATH));
            ((StringBuilder) (obj)).append("/forgotPassword");
            obj = ((StringBuilder) (obj)).toString();
            if(s.getPath().endsWith(((String) (obj))))
                return false;
            obj = new StringBuilder();
            ((StringBuilder) (obj)).append(context_.getString(R.string.PIXELA_PASSPORT_API_PATH));
            ((StringBuilder) (obj)).append("/unified");
            obj = ((StringBuilder) (obj)).toString();
            if(s.getPath().endsWith(((String) (obj))))
            {
                s = s.getQueryParameter("social");
                return s != null && !s.isEmpty();
            }
        }
        return true;
    }

    public Token refreshToken(Token token)
        throws Exception
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("refreshToken: ");
        stringbuilder.append(token);
        PxLog.d("PisIotEdgeService", stringbuilder.toString());
        if(token.isDeviceFlow())
        {
            PxLog.d("PisIotEdgeService", "new token");
            return refreshTokenWithDeviceFlow(token);
        } else
        {
            PxLog.d("PisIotEdgeService", "old token");
            return refreshTokenFromPixelaPassport(token);
        }
    }

    public Token refreshTokenFromPixelaPassport(Token token)
        throws Exception
    {
        token = (HttpURLConnection)(new URL(getTokenUriForRefreshToken(token.getRefreshToken()))).openConnection();
        token.setRequestMethod("GET");
        token.connect();
        int i = token.getResponseCode();
        if(i == 200)
        {
            token = new String(getResponseContent(token), StandardCharsets.UTF_8);
            PxLog.d("PisIotEdgeService", token);
            return new Token(new JSONObject(token));
        } else
        {
            token = new StringBuilder();
            token.append("refreshToken: HTTP status = ");
            token.append(i);
            PxLog.e("PisIotEdgeService", token.toString());
            return null;
        }
    }

    public Token refreshTokenWithDeviceFlow(Token token)
        throws Exception
    {
        Object obj = new HashMap();
        ((HashMap) (obj)).put("client_id", context_.getString(R.string.DEVICE_FLOW_CLIENT_ID));
        ((HashMap) (obj)).put("grant_type", "refresh_token");
        ((HashMap) (obj)).put("refresh_token", token.getRefreshToken());
        token = postForm(context_.getString(R.string.DEVICE_FLOW_TOKEN_URI), ((HashMap) (obj)));
        int i = ((JsonResponse) (token)).status;
        obj = ((JsonResponse) (token)).jsonObj;
        token = ((JSONObject) (obj)).optString("access_token", null);
        String s = ((JSONObject) (obj)).optString("refresh_token", null);
        if(token != null && token != "null" && s != null && s != "null")
        {
            ((JSONObject) (obj)).put("is_device_flow", true);
            token = new Token(((JSONObject) (obj)));
            obj = new StringBuilder();
            ((StringBuilder) (obj)).append("refreshed token:");
            ((StringBuilder) (obj)).append(token);
            PxLog.d("PisIotEdgeService", ((StringBuilder) (obj)).toString());
            return token;
        } else
        {
            return null;
        }
    }

    public Device updateDevice(Device device, Token token)
        throws Exception
    {
        device = device.toString().getBytes(StandardCharsets.UTF_8);
        HttpURLConnection httpurlconnection = (HttpURLConnection)(new URL("https://pixela-dtv-device.azurewebsites.net/api/v1/Devices")).openConnection();
        httpurlconnection.setDoOutput(true);
        httpurlconnection.setDoInput(true);
        httpurlconnection.setRequestMethod("PUT");
        httpurlconnection.setFixedLengthStreamingMode(device.length);
        httpurlconnection.setInstanceFollowRedirects(true);
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(token.getTokenType());
        stringbuilder.append(" ");
        stringbuilder.append(token.getAccessToken());
        httpurlconnection.setRequestProperty("Authorization", stringbuilder.toString());
        httpurlconnection.setRequestProperty("Accept", "application/json");
        httpurlconnection.setRequestProperty("Accept-Charset", "UTF-8");
        httpurlconnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        httpurlconnection.connect();
        setRequestContent(httpurlconnection, device);
        int i = httpurlconnection.getResponseCode();
        if(i == 200)
        {
            return new Device(new JSONObject(new String(getResponseContent(httpurlconnection), StandardCharsets.UTF_8)));
        } else
        {
            device = new StringBuilder();
            device.append("updateDevice: HTTP status = ");
            device.append(i);
            PxLog.e("PisIotEdgeService", device.toString());
            return null;
        }
    }

    private static final String TAG = "PisIotEdgeService";
    private static final String URI = "https://pixela-dtv-device.azurewebsites.net/api/v1/Devices";
    Context context_;
}
