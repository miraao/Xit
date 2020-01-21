// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.recommend;

import java.text.SimpleDateFormat;
import java.util.*;
import jp.pixela.pis_client.rest.common.IRestItem;
import jp.pixela.pis_client.rest.common.json.IJsonRestItem;
import jp.pixela.pxlibs.utils.android.log.LoggerRTM;
import org.json.*;

// Referenced classes of package jp.pixela.pis_client.rest.recommend:
//            RecommendData

public class RecommendApiItem
    implements IJsonRestItem
{

    public RecommendApiItem(JSONObject jsonobject)
    {
        mTargetBroadCast = null;
        mTargetSince = null;
        mTargetUntil = null;
        mTargetServiceIds = null;
        mJsonObj = jsonobject;
        mResultCode = -1;
        initItem();
        fromJSONObject(jsonobject);
    }

    public RecommendApiItem(JSONObject jsonobject, String s, String s1, String s2, Integer ainteger[])
    {
        mTargetBroadCast = null;
        mTargetSince = null;
        mTargetUntil = null;
        mTargetServiceIds = null;
        mJsonObj = jsonobject;
        mResultCode = -1;
        mTargetBroadCast = s;
        mTargetSince = s1;
        mTargetUntil = s2;
        if(ainteger != null)
            mTargetServiceIds = (Integer[])ainteger.clone();
        else
            mTargetServiceIds = null;
        initItem();
        fromJSONObject(jsonobject);
    }

    private boolean isValidItem(String s, String s1, int i)
    {
        if(mTargetBroadCast != null && mTargetSince != null && mTargetUntil != null)
        {
            if(!s.equals(mTargetBroadCast))
                return false;
            if(mTargetServiceIds != null && mTargetServiceIds.length > 0 && !Arrays.asList(mTargetServiceIds).contains(Integer.valueOf(i)))
                return false;
            long l;
            long l1;
            long l2;
            try
            {
                SimpleDateFormat simpledateformat = JVM INSTR new #120 <Class SimpleDateFormat>;
                simpledateformat.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                s = JVM INSTR new #120 <Class SimpleDateFormat>;
                s.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                l = simpledateformat.parse(s1).getTime() - 0x1ee6280L;
                l1 = s.parse(mTargetSince).getTime();
                l2 = s.parse(mTargetUntil).getTime();
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                return false;
            }
            return l1 <= l && l <= l2;
        } else
        {
            return true;
        }
    }

    public IRestItem fromJSONObject(JSONObject jsonobject)
    {
        Object obj;
        Object obj1;
        obj = null;
        obj1 = null;
        if(jsonobject == null)
            break MISSING_BLOCK_LABEL_266;
        if(!jsonobject.has("recommend_infos")) goto _L2; else goto _L1
_L1:
        JSONArray jsonarray = jsonobject.getJSONArray("recommend_infos");
        if(jsonarray == null) goto _L2; else goto _L3
_L3:
        int i = 0;
_L4:
        int j;
label0:
        {
            if(i >= jsonarray.length())
                break; /* Loop/switch isn't completed */
            obj = jsonarray.getJSONObject(i);
            if(((JSONObject) (obj)).has("service_id"))
            {
                j = ((JSONObject) (obj)).getInt("service_id");
                break label0;
            }
            j = -1;
        }
        int k;
label1:
        {
            if(((JSONObject) (obj)).has("event_id"))
            {
                k = ((JSONObject) (obj)).getInt("event_id");
                break label1;
            }
            k = -1;
        }
label2:
        {
            if(((JSONObject) (obj)).has("broadcast_type"))
            {
                jsonobject = ((JSONObject) (obj)).getString("broadcast_type");
                break label2;
            }
            jsonobject = null;
        }
label3:
        {
            if(((JSONObject) (obj)).has("start_time"))
            {
                obj = ((JSONObject) (obj)).getString("start_time");
                break label3;
            }
            obj = null;
        }
        if(jsonobject == null || j == -1 || k == -1 || obj == null)
            break MISSING_BLOCK_LABEL_198;
        if(isValidItem(jsonobject, ((String) (obj)), j))
        {
            List list = mList;
            obj = JVM INSTR new #175 <Class RecommendData>;
            ((RecommendData) (obj)).RecommendData(j, k, jsonobject);
            list.add(obj);
        }
        i++;
        if(true) goto _L4; else goto _L2
_L2:
        try
        {
            mResultCode = 0;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            jsonobject = this;
            break MISSING_BLOCK_LABEL_223;
        }
        obj = this;
        break MISSING_BLOCK_LABEL_266;
        obj;
        jsonobject = obj1;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(LOG_HEAD);
        stringbuilder.append("e=");
        stringbuilder.append(obj);
        LoggerRTM.e(stringbuilder.toString(), new Object[0]);
        obj = jsonobject;
        return ((IRestItem) (obj));
    }

    public List getList()
    {
        return mList;
    }

    public void initItem()
    {
        if(mList == null)
            mList = new ArrayList();
    }

    public JSONObject toJSONObject()
    {
        return mJsonObj;
    }

    private static final String BROADCAST_TYPE_KEY = "broadcast_type";
    private static final String EVENT_ID_KEY = "event_id";
    private static final String LOG_HEAD;
    public static final int RESULT_CODE_ERROR = -1;
    public static final int RESULT_CODE_SUCCESS = 0;
    public static final String ROOT_KEY = "recommend_infos";
    private static final String SERVICE_ID_KEY = "service_id";
    private static final String START_TIME_KEY = "start_time";
    private JSONObject mJsonObj;
    private List mList;
    protected int mResultCode;
    private String mTargetBroadCast;
    private Integer mTargetServiceIds[];
    private String mTargetSince;
    private String mTargetUntil;

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/pis_client/rest/recommend/RecommendApiItem.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }
}
