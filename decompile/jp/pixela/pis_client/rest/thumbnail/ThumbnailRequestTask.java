// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.thumbnail;

import android.content.Context;
import jp.pixela.pis_client.rest.common.*;

// Referenced classes of package jp.pixela.pis_client.rest.thumbnail:
//            ThumbnailApiParam, ThumbnailApiRestClient

public class ThumbnailRequestTask extends RestBaseTask
{

    public ThumbnailRequestTask(Context context, jp.pixela.pis_client.rest.common.RestBaseTask.RestTaskCallback resttaskcallback)
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
        ThumbnailApiParam thumbnailapiparam;
label0:
        {
            obj = null;
            if(airestitem != null && airestitem.length > 0)
            {
                airestitem = airestitem[0];
                if(airestitem != null && (airestitem instanceof ThumbnailApiParam))
                {
                    thumbnailapiparam = (ThumbnailApiParam)airestitem;
                    break label0;
                }
            }
            thumbnailapiparam = null;
        }
        airestitem = obj;
        if(thumbnailapiparam != null)
        {
            ThumbnailApiRestClient thumbnailapirestclient = new ThumbnailApiRestClient();
            airestitem = obj;
            if(thumbnailapirestclient != null)
                airestitem = thumbnailapirestclient.getThumbnailResult(mContext, thumbnailapiparam);
        }
        return airestitem;
    }

    private Context mContext;
}
