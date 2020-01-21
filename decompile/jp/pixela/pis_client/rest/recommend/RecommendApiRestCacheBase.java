// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.recommend;

import java.util.Date;
import jp.pixela.common.PxLog;
import org.json.JSONObject;

public class RecommendApiRestCacheBase
{

    public RecommendApiRestCacheBase(JSONObject jsonobject)
    {
        mSetDate = null;
        mJsonObj = null;
        setJsonObj(jsonobject);
    }

    public JSONObject getJsonObj()
    {
        return mJsonObj;
    }

    public boolean isExpired()
    {
        Date date = new Date();
        long l = mSetDate.getTime();
        return date.getTime() - l > (long)CACHE_LIMIT.intValue();
    }

    public void setJsonObj(JSONObject jsonobject)
    {
        try
        {
            JSONObject jsonobject1 = JVM INSTR new #56  <Class JSONObject>;
            jsonobject1.JSONObject(jsonobject.toString());
            mJsonObj = jsonobject1;
            PxLog.v(TAG, "set cache");
        }
        // Misplaced declaration of an exception variable
        catch(JSONObject jsonobject)
        {
            PxLog.v(TAG, jsonobject.toString());
            return;
        }
        mSetDate = new Date();
    }

    private static final String TAG = "RecommendApiRestCacheBase";
    private final Integer CACHE_LIMIT = Integer.valueOf(0x36ee80);
    JSONObject mJsonObj;
    private Date mSetDate;

}
