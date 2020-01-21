// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.rank;

import android.content.Context;
import jp.pixela.pis_client.rest.common.IRestClient;
import jp.pixela.pis_client.rest.common.RestResultParams;
import jp.pixela.pis_client.rest.common.json.JsonRestResponse;
import org.json.JSONObject;

// Referenced classes of package jp.pixela.pis_client.rest.rank:
//            ViewingPointApiConfig, ViewingPointRestClient, ViewingPointApiParam, ViewingPointApiItem

public class ViewingPointApiRestClient
{

    public ViewingPointApiRestClient()
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

    public RestResultParams getViewingPointResult(Context context, ViewingPointApiParam viewingpointapiparam)
    {
        ViewingPointRestClient viewingpointrestclient = new ViewingPointRestClient(mRestConfig);
        byte byte0 = -1;
        Object obj = null;
        int i = byte0;
        context = obj;
        if(viewingpointrestclient != null)
        {
            Integer ainteger[] = viewingpointapiparam.getServiceIdList();
            String s = viewingpointapiparam.getBroadcastTypeString();
            int j = byte0;
            if(s != null)
            {
                j = byte0;
                if(ainteger != null)
                {
                    j = byte0;
                    if(ainteger.length > 0)
                    {
                        i = ainteger.length;
                        context = new StringBuilder();
                        context.append("?broadcast_type=");
                        context.append(s);
                        context.append("&");
                        context = context.toString();
                        for(j = 0; j < i; j++)
                        {
                            viewingpointapiparam = new StringBuilder();
                            viewingpointapiparam.append(context);
                            viewingpointapiparam.append("service_ids=");
                            viewingpointapiparam.append(ainteger[j]);
                            viewingpointapiparam = viewingpointapiparam.toString();
                            context = viewingpointapiparam;
                            if(j < i - 1)
                            {
                                context = new StringBuilder();
                                context.append(viewingpointapiparam);
                                context.append("&");
                                context = context.toString();
                            }
                        }

                        j = viewingpointrestclient.get(context);
                    }
                }
            }
            i = j;
            context = obj;
            if(j == 0)
            {
                context = new ViewingPointApiItem(s, getResponse(viewingpointrestclient));
                i = j;
            }
        }
        return new RestResultParams(i, context);
    }

    private static final String BROADCAST_TYPE = "broadcast_type=";
    private static final String PARAM_DELIMITER = "&";
    private static final String SERVICE_IDS_KEY = "service_ids=";
    private static final String URL_PARAM_DELIMITER = "?";
    private static ViewingPointApiConfig mRestConfig = new ViewingPointApiConfig();

}
