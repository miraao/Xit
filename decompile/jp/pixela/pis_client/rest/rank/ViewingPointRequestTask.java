// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.rank;

import android.content.Context;
import jp.pixela.pis_client.rest.common.*;

// Referenced classes of package jp.pixela.pis_client.rest.rank:
//            ViewingPointApiParam, ViewingPointApiRestClient

public class ViewingPointRequestTask extends RestBaseTask
{

    public ViewingPointRequestTask(Context context, jp.pixela.pis_client.rest.common.RestBaseTask.RestTaskCallback resttaskcallback)
    {
        super(resttaskcallback);
        mContext = context;
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((IRestItem[])aobj);
    }

    protected transient RestResultParams doInBackground(IRestItem airestitem[])
    {
        Object obj;
        ViewingPointApiParam viewingpointapiparam;
label0:
        {
            obj = null;
            if(airestitem != null && airestitem.length > 0)
            {
                airestitem = airestitem[0];
                if(airestitem != null && (airestitem instanceof ViewingPointApiParam))
                {
                    viewingpointapiparam = (ViewingPointApiParam)airestitem;
                    break label0;
                }
            }
            viewingpointapiparam = null;
        }
        airestitem = obj;
        if(viewingpointapiparam != null)
        {
            ViewingPointApiRestClient viewingpointapirestclient = new ViewingPointApiRestClient();
            airestitem = obj;
            if(viewingpointapirestclient != null)
                airestitem = viewingpointapirestclient.getViewingPointResult(mContext, viewingpointapiparam);
        }
        return airestitem;
    }

    private Context mContext;
}
