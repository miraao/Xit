// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.recommend;

import java.util.*;
import jp.pixela.common.PxLog;
import org.json.JSONObject;

// Referenced classes of package jp.pixela.pis_client.rest.recommend:
//            RecommendApiRestCacheBase

public class RecommendApiRestCache
{

    public RecommendApiRestCache()
    {
        mMap = new HashMap();
    }

    public static RecommendApiRestCache getInstance()
    {
        return instance;
    }

    public JSONObject get(Integer ainteger[])
    {
        this;
        JVM INSTR monitorenter ;
        ainteger = Arrays.asList(ainteger);
        if(!mMap.containsKey(ainteger))
            break MISSING_BLOCK_LABEL_62;
        ainteger = (RecommendApiRestCacheBase)mMap.get(ainteger);
        if(!ainteger.isExpired())
            break MISSING_BLOCK_LABEL_53;
        PxLog.v(TAG, "expired");
        this;
        JVM INSTR monitorexit ;
        return null;
        ainteger = ainteger.getJsonObj();
        this;
        JVM INSTR monitorexit ;
        return ainteger;
        PxLog.v(TAG, "no cache");
        this;
        JVM INSTR monitorexit ;
        return null;
        ainteger;
        throw ainteger;
    }

    public void set(JSONObject jsonobject, Integer ainteger[])
    {
        this;
        JVM INSTR monitorenter ;
        if(ainteger == null)
            break MISSING_BLOCK_LABEL_85;
        if(ainteger.length == 0)
            break MISSING_BLOCK_LABEL_85;
        ainteger = Arrays.asList(ainteger);
        if(mMap.containsKey(ainteger))
            mMap.remove(ainteger);
        Map map = mMap;
        RecommendApiRestCacheBase recommendapirestcachebase = JVM INSTR new #48  <Class RecommendApiRestCacheBase>;
        recommendapirestcachebase.RecommendApiRestCacheBase(jsonobject);
        map.put(ainteger, recommendapirestcachebase);
        PxLog.v(TAG, "set cache");
        this;
        JVM INSTR monitorexit ;
        return;
        jsonobject;
        throw jsonobject;
        this;
        JVM INSTR monitorexit ;
    }

    private static final String TAG = "RecommendApiRestCache";
    private static final RecommendApiRestCache instance = new RecommendApiRestCache();
    private Map mMap;

}
