// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.co.pixela.pis_iot_edge;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import org.json.JSONObject;

class Token
{

    Token()
    {
        is_device_flow_ = false;
    }

    Token(JSONObject jsonobject)
        throws Exception
    {
        is_device_flow_ = false;
        token_type_ = jsonobject.getString("token_type");
        access_token_ = jsonobject.getString("access_token");
        refresh_token_ = jsonobject.getString("refresh_token");
        is_device_flow_ = jsonobject.optBoolean("is_device_flow");
    }

    public static Token load(Context context)
    {
        String s = PreferenceManager.getDefaultSharedPreferences(context).getString("PisIotEdge_TokenJson", "");
        try
        {
            context = JVM INSTR new #37  <Class JSONObject>;
            context.JSONObject(s);
            context = new Token(context);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            context.printStackTrace();
            return null;
        }
        return context;
    }

    public static void remove(Context context)
    {
        context = PreferenceManager.getDefaultSharedPreferences(context).edit();
        context.remove("PisIotEdge_TokenJson");
        context.apply();
    }

    public String getAccessToken()
    {
        return access_token_;
    }

    public String getRefreshToken()
    {
        return refresh_token_;
    }

    public String getTokenType()
    {
        return token_type_;
    }

    public boolean isDeviceFlow()
    {
        return is_device_flow_;
    }

    public boolean isValid()
    {
        boolean flag;
        if(token_type_ != null && access_token_ != null && refresh_token_ != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void save(Context context)
    {
        context = PreferenceManager.getDefaultSharedPreferences(context).edit();
        context.putString("PisIotEdge_TokenJson", toString());
        context.apply();
    }

    JSONObject toJSONObject()
    {
        JSONObject jsonobject;
        try
        {
            jsonobject = JVM INSTR new #37  <Class JSONObject>;
            jsonobject.JSONObject();
            jsonobject.put("token_type", token_type_);
            jsonobject.put("access_token", access_token_);
            jsonobject.put("refresh_token", refresh_token_);
            jsonobject.put("is_device_flow", is_device_flow_);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
        return jsonobject;
    }

    public String toString()
    {
        Object obj;
        try
        {
            obj = toJSONObject();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
        if(obj == null)
            break MISSING_BLOCK_LABEL_17;
        obj = ((JSONObject) (obj)).toString();
        break MISSING_BLOCK_LABEL_19;
        obj = null;
        return ((String) (obj));
    }

    static final String KEY_NAME_ACCESS_TOKEN = "access_token";
    static final String KEY_NAME_DEVICE_FLOW = "is_device_flow";
    static final String KEY_NAME_REFRESH_TOKEN = "refresh_token";
    static final String KEY_NAME_TOKEN_TYPE = "token_type";
    private static final String KEY_TOKEN_JSON = "PisIotEdge_TokenJson";
    private String access_token_;
    private boolean is_device_flow_;
    private String refresh_token_;
    private String token_type_;
}
