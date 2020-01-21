// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.recommend;

import android.content.Context;
import jp.pixela.pis_client.rest.common.IRestClient;
import jp.pixela.pis_client.rest.common.RestResultParams;
import jp.pixela.pis_client.rest.common.json.JsonRestResponse;
import jp.pixela.pis_client.rest.common_at.CommonAtRestClient;
import org.json.JSONObject;

// Referenced classes of package jp.pixela.pis_client.rest.recommend:
//            RecommendApiConfig, RecommendApiParam, RecommendApiRestCache, RecommendApiItem

public class RecommendApiRestClient
{

    public RecommendApiRestClient()
    {
    }

    private JSONObject getResponse(IRestClient irestclient)
    {
label0:
        {
            if(irestclient != null && (irestclient.getResponse() instanceof JsonRestResponse))
            {
                irestclient = (JsonRestResponse)irestclient.getResponse();
                if(irestclient != null)
                {
                    irestclient = irestclient.getResponse();
                    break label0;
                }
            }
            irestclient = null;
        }
        return irestclient;
    }

    public RestResultParams getRecommendResult(Context context, RecommendApiParam recommendapiparam)
    {
        String s = recommendapiparam.getBroadcastType();
        String s1 = recommendapiparam.getSince();
        String s2 = recommendapiparam.getUntil();
        Integer ainteger[] = recommendapiparam.getServiceIdList();
        RecommendApiRestCache recommendapirestcache = RecommendApiRestCache.getInstance();
        context = recommendapirestcache.get(ainteger);
        if(context != null)
            return new RestResultParams(0, new RecommendApiItem(context, s, s1, s2, ainteger));
        Object obj = null;
        CommonAtRestClient commonatrestclient = new CommonAtRestClient(mRestConfig, "recommend_infos");
        int l;
        if(commonatrestclient != null)
        {
            context = "?";
            recommendapiparam = recommendapiparam.getBroadcastType();
            if(recommendapiparam != null)
            {
                context = new StringBuilder();
                context.append("?");
                context.append("broadcast_type=");
                context.append(recommendapiparam);
                context.append("&");
                context = context.toString();
            }
            recommendapiparam = context;
            if(ainteger != null)
            {
                recommendapiparam = context;
                if(ainteger.length > 0)
                {
                    int i = ainteger.length;
                    for(int k = 0; k < i; k++)
                    {
                        recommendapiparam = new StringBuilder();
                        recommendapiparam.append(context);
                        recommendapiparam.append("service_ids=");
                        recommendapiparam.append(ainteger[k]);
                        recommendapiparam.append("&");
                        context = recommendapiparam.toString();
                    }

                    recommendapiparam = context;
                }
            }
            context = recommendapiparam;
            if(recommendapiparam.endsWith("&"))
                context = recommendapiparam.substring(0, recommendapiparam.length() - 1);
            int j = commonatrestclient.get(context);
            context = obj;
            l = j;
            if(j == 0)
            {
                context = getResponse(commonatrestclient);
                if(context != null)
                    recommendapirestcache.set(context, ainteger);
                context = new RecommendApiItem(context, s, s1, s2, ainteger);
                l = j;
            }
        } else
        {
            l = -1;
            context = obj;
        }
        return new RestResultParams(l, context);
    }

    private static final String BROADCAST_TYPE_KEY = "broadcast_type=";
    private static final String PARAM_DELIMITER = "&";
    private static final String SERVICE_IDS_KEY = "service_ids=";
    private static final String SINCE_KEY = "since=";
    private static final String UNTIL_KEY = "until=";
    private static final String URL_PARAM_DELIMITER = "?";
    private static RecommendApiConfig mRestConfig = new RecommendApiConfig();

}
