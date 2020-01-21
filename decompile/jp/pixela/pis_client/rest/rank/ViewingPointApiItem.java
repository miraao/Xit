// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.rank;

import java.util.ArrayList;
import java.util.List;
import jp.pixela.pis_client.rest.common.IRestItem;
import jp.pixela.pis_client.rest.common.json.IJsonRestItem;
import jp.pixela.pxlibs.utils.android.log.LoggerRTM;
import org.json.*;

// Referenced classes of package jp.pixela.pis_client.rest.rank:
//            ViewingPointData

public class ViewingPointApiItem
    implements IJsonRestItem
{

    public ViewingPointApiItem(String s, JSONObject jsonobject)
    {
        mBroadcastType = s;
        mJsonObj = jsonobject;
        mResultCode = -1;
        initItem();
        fromJSONObject(jsonobject);
    }

    public IRestItem fromJSONObject(JSONObject jsonobject)
    {
        Object obj;
        Object obj1;
        obj = null;
        obj1 = null;
        if(jsonobject == null)
            break MISSING_BLOCK_LABEL_252;
        if(!jsonobject.has("service_ids")) goto _L2; else goto _L1
_L1:
        jsonobject = jsonobject.getJSONArray("service_ids");
        if(jsonobject == null) goto _L2; else goto _L3
_L3:
        int i = 0;
_L4:
        int j;
label0:
        {
            if(i >= jsonobject.length())
                break; /* Loop/switch isn't completed */
            obj = jsonobject.getJSONObject(i);
            if(((JSONObject) (obj)).has("rank"))
            {
                j = ((JSONObject) (obj)).getInt("rank");
                break label0;
            }
            j = -1;
        }
        int k;
label1:
        {
            if(((JSONObject) (obj)).has("count"))
            {
                k = ((JSONObject) (obj)).getInt("count");
                break label1;
            }
            k = -1;
        }
        if(!((JSONObject) (obj)).has("program"))
            break MISSING_BLOCK_LABEL_133;
        obj = ((JSONObject) (obj)).getJSONObject("program");
        if(obj == null)
            break MISSING_BLOCK_LABEL_133;
        int l;
        if(((JSONObject) (obj)).has("service_id"))
        {
            l = ((JSONObject) (obj)).getInt("service_id");
            break MISSING_BLOCK_LABEL_136;
        }
        l = -1;
        if(l == -1 || j == -1 || k == -1)
            break MISSING_BLOCK_LABEL_184;
        obj = mList;
        ViewingPointData viewingpointdata = JVM INSTR new #108 <Class ViewingPointData>;
        viewingpointdata.ViewingPointData(l, j, k);
        ((List) (obj)).add(viewingpointdata);
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
            break MISSING_BLOCK_LABEL_209;
        }
        obj = this;
        break MISSING_BLOCK_LABEL_252;
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

    public String getBroadcastType()
    {
        return mBroadcastType;
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

    private static final String COUNT_KEY = "count";
    private static final String LOG_HEAD;
    private static final String PROGRAM_KEY = "program";
    private static final String RANK_KEY = "rank";
    public static final int RESULT_CODE_ERROR = -1;
    public static final int RESULT_CODE_SUCCESS = 0;
    public static final String ROOT_KEY = "service_ids";
    private static final String SERVICE_ID_KEY = "service_id";
    private String mBroadcastType;
    private JSONObject mJsonObj;
    private List mList;
    protected int mResultCode;

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/pis_client/rest/rank/ViewingPointApiItem.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }
}
