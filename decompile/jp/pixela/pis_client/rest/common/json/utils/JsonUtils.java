// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.common.json.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import jp.pixela.pxlibs.utils.android.log.LoggerRTM;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils
{

    public JsonUtils()
    {
    }

    public static JSONObject bytesToJson(byte abyte0[])
    {
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_124;
        Object obj;
        obj = JVM INSTR new #40  <Class String>;
        ((String) (obj)).String(abyte0, "UTF-8");
        abyte0 = ((byte []) (obj));
        break MISSING_BLOCK_LABEL_64;
        IOException ioexception;
        ioexception;
        abyte0 = new StringBuilder();
        abyte0.append(LOG_HEAD);
        abyte0.append("e=");
        abyte0.append(ioexception);
        LoggerRTM.e(abyte0.toString(), new Object[0]);
        abyte0 = null;
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_124;
        ioexception = JVM INSTR new #58  <Class JSONObject>;
        ioexception.JSONObject(abyte0);
        abyte0 = ioexception;
        break MISSING_BLOCK_LABEL_126;
        abyte0;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(LOG_HEAD);
        stringbuilder.append("e=");
        stringbuilder.append(abyte0);
        LoggerRTM.e(stringbuilder.toString(), new Object[0]);
        abyte0 = null;
        return abyte0;
    }

    public static byte[] jsonToBytes(JSONObject jsonobject)
    {
        if(jsonobject != null)
            jsonobject = jsonobject.toString().getBytes(Charset.defaultCharset());
        else
            jsonobject = null;
        return jsonobject;
    }

    private static final String LOG_HEAD;

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/pis_client/rest/common/json/utils/JsonUtils.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }
}
