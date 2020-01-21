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
//            UserRecommendApiConfig, RecommendApiParam, UserRecommendApiRestCache, RecommendApiItem

public class UserRecommendApiRestClient
{

    public UserRecommendApiRestClient()
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
        Integer integer = recommendapiparam.getAreaCode();
        Integer ainteger[] = recommendapiparam.getServiceIdList();
        UserRecommendApiRestCache userrecommendapirestcache = UserRecommendApiRestCache.getInstance();
        context = userrecommendapirestcache.get(integer.intValue());
        if(context != null)
            return new RestResultParams(0, new RecommendApiItem(context, s, s1, s2, ainteger));
        Object obj = null;
        CommonAtRestClient commonatrestclient = new CommonAtRestClient(mRestConfig, "recommend_infos");
        int j;
        if(commonatrestclient != null)
        {
            context = "?";
            if(integer != null)
            {
                context = new StringBuilder();
                context.append("?");
                context.append("area_code=");
                context.append(integer);
                context.append("&");
                context = context.toString();
            }
            Object obj1 = new StringBuilder();
            ((StringBuilder) (obj1)).append(context);
            ((StringBuilder) (obj1)).append("limit=");
            ((StringBuilder) (obj1)).append(LIMIT_VALUE);
            ((StringBuilder) (obj1)).append("&");
            obj1 = ((StringBuilder) (obj1)).toString();
            recommendapiparam = recommendapiparam.getUserId();
            context = ((Context) (obj1));
            if(recommendapiparam != null)
            {
                context = new StringBuilder();
                context.append(((String) (obj1)));
                context.append("user_id=");
                context.append(recommendapiparam);
                context.append("&");
                context = context.toString();
            }
            recommendapiparam = context;
            if(context.endsWith("&"))
                recommendapiparam = context.substring(0, context.length() - 1);
            int i = commonatrestclient.get(recommendapiparam);
            context = obj;
            j = i;
            if(i == 0)
            {
                context = getResponse(commonatrestclient);
                if(context != null)
                    userrecommendapirestcache.set(context, integer.intValue());
                context = new RecommendApiItem(context, s, s1, s2, ainteger);
                j = i;
            }
        } else
        {
            j = -1;
            context = obj;
        }
        return new RestResultParams(j, context);
    }

    private static final String AREA_KEY = "area_code=";
    private static final String LIMIT_KEY = "limit=";
    private static final Integer LIMIT_VALUE = Integer.valueOf(100);
    private static final String PARAM_DELIMITER = "&";
    private static final String URL_PARAM_DELIMITER = "?";
    private static final String USER_KEY = "user_id=";
    private static UserRecommendApiConfig mRestConfig = new UserRecommendApiConfig();

}
