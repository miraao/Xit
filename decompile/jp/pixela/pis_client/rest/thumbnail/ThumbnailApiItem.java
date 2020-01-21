// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.thumbnail;

import android.util.Pair;
import java.util.ArrayList;
import java.util.List;
import jp.pixela.pis_client.rest.common.IRestItem;
import jp.pixela.pis_client.rest.common.json.IJsonRestItem;
import jp.pixela.pxlibs.utils.android.log.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package jp.pixela.pis_client.rest.thumbnail:
//            ThumbnailData

public class ThumbnailApiItem
    implements IJsonRestItem
{

    ThumbnailApiItem(JSONObject jsonobject)
    {
        mJsonObj = jsonobject;
        initItem();
        fromJSONObject(jsonobject);
    }

    private Pair getValidImageObjectInfo(JSONObject jsonobject)
    {
        if(jsonobject == null)
        {
            jsonobject = new StringBuilder();
            jsonobject.append(LOG_HEAD);
            jsonobject.append("imageObject == null");
            Logger.v(jsonobject.toString(), new Object[0]);
            return null;
        }
        if(jsonobject.optInt("width", -1) != 320)
        {
            jsonobject = new StringBuilder();
            jsonobject.append(LOG_HEAD);
            jsonobject.append("imageWidth != RESIZED_IMAGE_WIDTH");
            Logger.v(jsonobject.toString(), new Object[0]);
            return null;
        }
        int i = jsonobject.optInt("height", -1);
        if(i == -1)
        {
            jsonobject = new StringBuilder();
            jsonobject.append(LOG_HEAD);
            jsonobject.append("imageHeight == OPT_INT_FALLBACK_VALUE");
            Logger.v(jsonobject.toString(), new Object[0]);
            return null;
        }
        jsonobject = jsonobject.optString("url");
        if(jsonobject.isEmpty())
        {
            jsonobject = new StringBuilder();
            jsonobject.append(LOG_HEAD);
            jsonobject.append("imageUrl.isEmpty()");
            Logger.v(jsonobject.toString(), new Object[0]);
            return null;
        } else
        {
            return new Pair(jsonobject, Integer.valueOf(i));
        }
    }

    public IRestItem fromJSONObject(JSONObject jsonobject)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(LOG_HEAD);
        stringbuilder.append("in");
        Logger.v(stringbuilder.toString(), new Object[0]);
        if(jsonobject == null)
        {
            jsonobject = new StringBuilder();
            jsonobject.append(LOG_HEAD);
            jsonobject.append("out. jsonObj == null");
            Logger.v(jsonobject.toString(), new Object[0]);
            return null;
        }
        if(!jsonobject.has("thumbnail_infos"))
        {
            jsonobject = new StringBuilder();
            jsonobject.append(LOG_HEAD);
            jsonobject.append("out. !jsonObj.has(ROOT_KEY)");
            Logger.v(jsonobject.toString(), new Object[0]);
            return null;
        }
        JSONArray jsonarray = jsonobject.optJSONArray("thumbnail_infos");
        if(jsonarray == null)
        {
            jsonobject = new StringBuilder();
            jsonobject.append(LOG_HEAD);
            jsonobject.append("out. array == null");
            Logger.v(jsonobject.toString(), new Object[0]);
            return null;
        }
        for(int i = 0; i < jsonarray.length(); i++)
        {
label0:
            {
                Object obj1;
                String s;
                int j;
                int k;
label1:
                {
                    obj1 = jsonarray.optJSONObject(i);
                    if(obj1 == null)
                    {
                        jsonobject = new StringBuilder();
                        jsonobject.append(LOG_HEAD);
                        jsonobject.append("serviceObj == null");
                        Logger.v(jsonobject.toString(), new Object[0]);
                        continue;
                    }
                    s = ((JSONObject) (obj1)).optString("broadcast_type");
                    j = ((JSONObject) (obj1)).optInt("service_id", -1);
                    k = ((JSONObject) (obj1)).optInt("event_id", -1);
                    if(s.isEmpty() || j == -1 || k == -1)
                        break label0;
                    jsonobject = ((JSONObject) (obj1)).optJSONArray("resized_images");
                    if(jsonobject != null)
                    {
                        int l = 0;
                        do
                        {
                            if(l >= jsonobject.length())
                                break;
                            Pair pair = getValidImageObjectInfo(jsonobject.optJSONObject(l));
                            if(pair != null)
                            {
                                jsonobject = new ThumbnailData(j, k, s, (String)pair.first, 320, ((Integer)pair.second).intValue());
                                break label1;
                            }
                            l++;
                        } while(true);
                    }
                    jsonobject = null;
                }
                Object obj = jsonobject;
                if(jsonobject == null)
                {
                    obj1 = getValidImageObjectInfo(((JSONObject) (obj1)).optJSONObject("image"));
                    obj = jsonobject;
                    if(obj1 != null)
                        obj = new ThumbnailData(j, k, s, (String)((Pair) (obj1)).first, 320, ((Integer)((Pair) (obj1)).second).intValue());
                }
                if(obj == null)
                {
                    jsonobject = new StringBuilder();
                    jsonobject.append(LOG_HEAD);
                    jsonobject.append("thumbnailData == null");
                    Logger.v(jsonobject.toString(), new Object[0]);
                } else
                {
                    mList.add(obj);
                    jsonobject = new StringBuilder();
                    jsonobject.append("thumbnailData=");
                    jsonobject.append(obj);
                    Logger.v(jsonobject.toString(), new Object[0]);
                }
                continue;
            }
            jsonobject = new StringBuilder();
            jsonobject.append(LOG_HEAD);
            jsonobject.append("invalid data (broadcastType, serviceId, eventId).");
            Logger.v(jsonobject.toString(), new Object[0]);
        }

        if(mList.size() <= 0)
        {
            jsonobject = new StringBuilder();
            jsonobject.append(LOG_HEAD);
            jsonobject.append("out. mList.size() <= 0");
            Logger.v(jsonobject.toString(), new Object[0]);
            return null;
        } else
        {
            jsonobject = new StringBuilder();
            jsonobject.append(LOG_HEAD);
            jsonobject.append("out");
            Logger.v(jsonobject.toString(), new Object[0]);
            return this;
        }
    }

    public List getList()
    {
        return mList;
    }

    public void initItem()
    {
        mList.clear();
    }

    public JSONObject toJSONObject()
    {
        return mJsonObj;
    }

    private static final String BROADCAST_TYPE_KEY = "broadcast_type";
    private static final String EVENT_ID_KEY = "event_id";
    private static final String IMAGE_HEIGHT_KEY = "height";
    private static final String IMAGE_KEY = "image";
    private static final String IMAGE_URL_KEY = "url";
    private static final String IMAGE_WIDTH_KEY = "width";
    private static final String LOG_HEAD;
    private static final int OPT_INT_FALLBACK_VALUE = -1;
    private static final String RESIZED_IMAGES_KEY = "resized_images";
    private static final int RESIZED_IMAGE_WIDTH = 320;
    public static final int RESULT_CODE_ERROR = -1;
    public static final int RESULT_CODE_SUCCESS = 0;
    public static final String ROOT_KEY = "thumbnail_infos";
    private static final String SERVICE_ID_KEY = "service_id";
    private final JSONObject mJsonObj;
    private final List mList = new ArrayList();

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/pis_client/rest/thumbnail/ThumbnailApiItem.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }
}
