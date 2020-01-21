// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.recommend;

import android.content.Context;
import jp.pixela.pis_client.rest.common.*;

// Referenced classes of package jp.pixela.pis_client.rest.recommend:
//            RecommendApiParam, RecommendApiRestClient, UserRecommendApiRestClient

public class RecommendRequestTask extends RestTwoBaseTask
{

    public RecommendRequestTask(Context context, jp.pixela.pis_client.rest.common.RestTwoBaseTask.RestTaskCallback resttaskcallback)
    {
        super(resttaskcallback);
        mContext = context;
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((IRestItem[])aobj);
    }

    protected transient RestTwoResultParams doInBackground(IRestItem airestitem[])
    {
        jp.pixela.pis_client.rest.common.RestResultParams restresultparams;
        RecommendApiParam recommendapiparam;
label0:
        {
            restresultparams = null;
            if(airestitem != null && airestitem.length > 0)
            {
                airestitem = airestitem[0];
                if(airestitem != null && (airestitem instanceof RecommendApiParam))
                {
                    recommendapiparam = (RecommendApiParam)airestitem;
                    break label0;
                }
            }
            recommendapiparam = null;
        }
        jp.pixela.pis_client.rest.common.RestResultParams restresultparams1;
        if(recommendapiparam != null)
        {
            airestitem = new RecommendApiRestClient();
            if(airestitem != null)
                airestitem = airestitem.getRecommendResult(mContext, recommendapiparam);
            else
                airestitem = null;
            UserRecommendApiRestClient userrecommendapirestclient = new UserRecommendApiRestClient();
            restresultparams1 = airestitem;
            if(userrecommendapirestclient != null)
            {
                restresultparams = userrecommendapirestclient.getRecommendResult(mContext, recommendapiparam);
                restresultparams1 = airestitem;
            }
        } else
        {
            restresultparams1 = null;
        }
        return new RestTwoResultParams(restresultparams1, restresultparams);
    }

    private Context mContext;
}
