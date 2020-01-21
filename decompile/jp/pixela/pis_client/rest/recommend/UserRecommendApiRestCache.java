// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.recommend;

import java.util.Date;
import jp.pixela.common.PxLog;
import org.json.JSONObject;

public class UserRecommendApiRestCache
{

    public UserRecommendApiRestCache()
    {
        mUserRecommendJson = null;
        mAreaCode = 0;
        mSetDate = null;
    }

    public static UserRecommendApiRestCache getInstance()
    {
        return instance;
    }

    public JSONObject get(int i)
    {
        this;
        JVM INSTR monitorenter ;
        if(mUserRecommendJson == null || mSetDate == null)
            break MISSING_BLOCK_LABEL_93;
        if(i == mAreaCode)
            break MISSING_BLOCK_LABEL_39;
        PxLog.v(TAG, "diff areacode");
        this;
        JVM INSTR monitorexit ;
        return null;
        Date date = JVM INSTR new #58  <Class Date>;
        date.Date();
        long l = mSetDate.getTime();
        if(date.getTime() - l <= (long)CACHE_LIMIT.intValue())
            break MISSING_BLOCK_LABEL_84;
        PxLog.v(TAG, "old cache");
        this;
        JVM INSTR monitorexit ;
        return null;
        JSONObject jsonobject = mUserRecommendJson;
        this;
        JVM INSTR monitorexit ;
        return jsonobject;
        PxLog.v(TAG, "no cache");
        this;
        JVM INSTR monitorexit ;
        return null;
        Exception exception;
        exception;
        throw exception;
    }

    public void set(JSONObject jsonobject, int i)
    {
        this;
        JVM INSTR monitorenter ;
        try
        {
            JSONObject jsonobject1 = JVM INSTR new #77  <Class JSONObject>;
            jsonobject1.JSONObject(jsonobject.toString());
            mUserRecommendJson = jsonobject1;
            PxLog.v(TAG, "set cache");
            break MISSING_BLOCK_LABEL_45;
        }
        // Misplaced declaration of an exception variable
        catch(JSONObject jsonobject) { }
        break MISSING_BLOCK_LABEL_35;
        jsonobject;
        break MISSING_BLOCK_LABEL_67;
        PxLog.v(TAG, jsonobject.toString());
        mAreaCode = i;
        jsonobject = JVM INSTR new #58  <Class Date>;
        jsonobject.Date();
        mSetDate = jsonobject;
        this;
        JVM INSTR monitorexit ;
        return;
        throw jsonobject;
    }

    private static final Integer CACHE_LIMIT = Integer.valueOf(0x36ee80);
    private static final String TAG = "UserRecommendApiRestCache";
    private static final UserRecommendApiRestCache instance = new UserRecommendApiRestCache();
    private int mAreaCode;
    private Date mSetDate;
    private JSONObject mUserRecommendJson;

}
