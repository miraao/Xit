// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.helper;

import android.content.Context;
import java.util.*;
import jp.pixela.pis_client.rest.thumbnail.ThumbnailData;
import jp.pixela.player_service.message.ProgramInfo;

public class GenreImageHelper
{

    public GenreImageHelper()
    {
    }

    public static void readGenreImage(Context context, List list, List list1)
    {
        if(list1 == null)
            return;
        if(list != null && !list.isEmpty())
        {
            context = list.iterator();
            do
            {
                if(!context.hasNext())
                    break;
                list = (ThumbnailData)context.next();
                if(list.getImage() == null || list.getImage().length <= 0)
                {
                    list.setImage(null);
                    list.setIsGenre(true);
                }
            } while(true);
        } else
        {
            context = list;
            if(list == null)
                context = new ArrayList();
            for(list = list1.iterator(); list.hasNext(); context.add(list1))
            {
                list1 = (ProgramInfo)list.next();
                String s = list1.getBroadcastTypeString();
                list1 = new ThumbnailData(list1.getServiceId(), list1.getEventId(), s, null, -1, -1);
                list1.setImage(null);
                list1.setIsGenre(true);
            }

        }
    }

    private static final String TAG = "GenreImageHelper";

}
