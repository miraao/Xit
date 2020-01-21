// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.thumbnail;

import android.content.Context;
import java.util.List;

public interface IThumbnailClient
{
    public static final class ClientResult extends Enum
    {

        public static ClientResult valueOf(String s)
        {
            return (ClientResult)Enum.valueOf(jp/pixela/pis_client/rest/thumbnail/IThumbnailClient$ClientResult, s);
        }

        public static ClientResult[] values()
        {
            return (ClientResult[])$VALUES.clone();
        }

        private static final ClientResult $VALUES[];
        public static final ClientResult CONTINUE;
        public static final ClientResult FAILED;
        public static final ClientResult SUCCESS;

        static 
        {
            SUCCESS = new ClientResult("SUCCESS", 0);
            FAILED = new ClientResult("FAILED", 1);
            CONTINUE = new ClientResult("CONTINUE", 2);
            $VALUES = (new ClientResult[] {
                SUCCESS, FAILED, CONTINUE
            });
        }

        private ClientResult(String s, int i)
        {
            super(s, i);
        }
    }

    public static interface ThumbnailClientCallback
    {

        public abstract void onCompleteThumbnail(int i, List list);
    }


    public abstract boolean request(Context context, long l, long l1, String s, Integer ainteger[]);
}
