// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.common.json;

import jp.pixela.pis_client.rest.common.IRestResponse;
import org.json.JSONObject;

public class JsonRestResponse
    implements IRestResponse
{

    public JsonRestResponse(JSONObject jsonobject)
    {
        mResponse = null;
        mResponse = jsonobject;
    }

    public volatile Object getResponse()
    {
        return getResponse();
    }

    public JSONObject getResponse()
    {
        return mResponse;
    }

    private JSONObject mResponse;
}
