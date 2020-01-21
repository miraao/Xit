// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.thumbnail;

import android.content.Context;
import jp.pixela.pis_client.rest.common.IRestClient;
import jp.pixela.pis_client.rest.common.RestResultParams;
import jp.pixela.pis_client.rest.common.json.JsonRestResponse;
import jp.pixela.pis_client.rest.common_at.CommonAtRestClient;
import org.json.JSONObject;

// Referenced classes of package jp.pixela.pis_client.rest.thumbnail:
//            ThumbnailApiConfig, ThumbnailApiParam, ThumbnailApiItem

public class ThumbnailApiRestClient
{

    public ThumbnailApiRestClient()
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

    public RestResultParams getThumbnailResult(Context context, ThumbnailApiParam thumbnailapiparam)
    {
        CommonAtRestClient commonatrestclient = new CommonAtRestClient(mRestConfig, "thumbnail_infos");
        Object obj = null;
        int l;
        if(commonatrestclient != null)
        {
            context = "?";
            Object obj1 = thumbnailapiparam.getSince();
            if(obj1 != null)
            {
                context = new StringBuilder();
                context.append("?");
                context.append("since=");
                context.append(((String) (obj1)));
                context.append("&");
                context = context.toString();
            }
            String s = thumbnailapiparam.getUntil();
            obj1 = context;
            if(s != null)
            {
                obj1 = new StringBuilder();
                ((StringBuilder) (obj1)).append(context);
                ((StringBuilder) (obj1)).append("until=");
                ((StringBuilder) (obj1)).append(s);
                ((StringBuilder) (obj1)).append("&");
                obj1 = ((StringBuilder) (obj1)).toString();
            }
            s = thumbnailapiparam.getBroadcastType();
            context = ((Context) (obj1));
            if(s != null)
            {
                context = new StringBuilder();
                context.append(((String) (obj1)));
                context.append("broadcast_type=");
                context.append(s);
                context.append("&");
                context = context.toString();
            }
            Integer ainteger[] = thumbnailapiparam.getServiceIdList();
            thumbnailapiparam = context;
            if(ainteger != null)
            {
                thumbnailapiparam = context;
                if(ainteger.length > 0)
                {
                    int i = ainteger.length;
                    for(int k = 0; k < i; k++)
                    {
                        thumbnailapiparam = new StringBuilder();
                        thumbnailapiparam.append(context);
                        thumbnailapiparam.append("service_ids=");
                        thumbnailapiparam.append(ainteger[k]);
                        thumbnailapiparam.append("&");
                        context = thumbnailapiparam.toString();
                    }

                    thumbnailapiparam = context;
                }
            }
            context = new StringBuilder();
            context.append(thumbnailapiparam);
            context.append("uses_nhk_api_programs=true&");
            thumbnailapiparam = context.toString();
            context = thumbnailapiparam;
            if(thumbnailapiparam.endsWith("&"))
                context = thumbnailapiparam.substring(0, thumbnailapiparam.length() - 1);
            int j = commonatrestclient.get(context);
            context = obj;
            l = j;
            if(j == 0)
            {
                context = new ThumbnailApiItem(getResponse(commonatrestclient));
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
    private static final String USES_NHK_API_PROGRAMS_KEY = "uses_nhk_api_programs=";
    private static ThumbnailApiConfig mRestConfig = new ThumbnailApiConfig();

}
