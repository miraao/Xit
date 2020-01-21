// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.helper;

import android.content.Context;
import android.os.AsyncTask;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;

public class AsyncTaskHttpThumbnailRequest extends AsyncTask
{
    public static interface LoadThumbnailListener
    {

        public abstract void onLoadThumbnailFinished(ByteBuffer bytebuffer);
    }


    public AsyncTaskHttpThumbnailRequest(Context context, LoadThumbnailListener loadthumbnaillistener)
    {
        mContext = context;
        mListener = loadthumbnaillistener;
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((String[])aobj);
    }

    protected transient ByteBuffer doInBackground(String as[])
    {
        Object obj = null;
        URL url = JVM INSTR new #37  <Class URL>;
        url.URL(as[0]);
        as = (HttpURLConnection)url.openConnection();
        Object obj1;
        Object obj2;
        int i;
        byte abyte0[];
        try
        {
            as.setRequestMethod("GET");
            as.setReadTimeout(5000);
            as.setConnectTimeout(5000);
            as.connect();
            obj1 = as.getInputStream();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj1)
        {
            break MISSING_BLOCK_LABEL_249;
        }
        try
        {
            obj2 = JVM INSTR new #67  <Class ByteArrayOutputStream>;
            ((ByteArrayOutputStream) (obj2)).ByteArrayOutputStream();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj2)
        {
            obj2 = null;
            obj3 = obj1;
            obj1 = obj2;
            break MISSING_BLOCK_LABEL_254;
        }
        i = ((InputStream) (obj1)).read();
        if(i == -1)
            break MISSING_BLOCK_LABEL_82;
        try
        {
            ((ByteArrayOutputStream) (obj2)).write(i);
            break MISSING_BLOCK_LABEL_60;
        }
        catch(Exception exception6)
        {
            exception6 = ((Exception) (obj1));
            obj1 = obj2;
        }
          goto _L1
        abyte0 = ((ByteArrayOutputStream) (obj2)).toByteArray();
        Exception exception5;
        Object obj3;
        String as1[];
        Exception exception8;
        if(obj2 != null)
            try
            {
                ((ByteArrayOutputStream) (obj2)).close();
            }
            catch(Exception exception3) { }
        if(obj1 != null)
            try
            {
                ((InputStream) (obj1)).close();
            }
            catch(Exception exception) { }
        obj1 = abyte0;
        if(as != null)
        {
            as.disconnect();
            obj1 = abyte0;
        }
        break MISSING_BLOCK_LABEL_282;
        exception5;
        as1 = as;
        obj = obj1;
        as = exception5;
        obj1 = as1;
        obj3 = obj2;
        obj2 = obj;
        break MISSING_BLOCK_LABEL_216;
        exception8;
        obj3 = as;
        obj2 = obj1;
        as = exception8;
        obj1 = obj3;
        obj3 = obj;
        break MISSING_BLOCK_LABEL_216;
        obj1;
        obj2 = as;
        exception8 = null;
        as = ((String []) (obj1));
        obj1 = obj2;
        obj3 = obj;
        obj2 = exception8;
        break MISSING_BLOCK_LABEL_216;
        as;
        obj1 = null;
        obj2 = obj1;
        obj3 = obj;
        if(obj3 != null)
            try
            {
                ((ByteArrayOutputStream) (obj3)).close();
            }
            catch(Exception exception7) { }
        if(obj2 != null)
            try
            {
                ((InputStream) (obj2)).close();
            }
            catch(Exception exception4) { }
        if(obj1 != null)
            ((HttpURLConnection) (obj1)).disconnect();
        throw as;
        as;
        as = null;
        obj3 = null;
        obj1 = null;
_L1:
        if(obj1 != null)
            try
            {
                ((ByteArrayOutputStream) (obj1)).close();
            }
            catch(Exception exception1) { }
        if(obj3 != null)
            try
            {
                ((InputStream) (obj3)).close();
            }
            catch(Exception exception2) { }
        if(as != null)
            as.disconnect();
        obj1 = null;
        if(obj1 == null)
            return null;
        else
            return ByteBuffer.wrap(((byte []) (obj1)));
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((ByteBuffer)obj);
    }

    protected void onPostExecute(ByteBuffer bytebuffer)
    {
        if(mContext != null && mListener != null)
            mListener.onLoadThumbnailFinished(bytebuffer);
    }

    private static final int CONNECT_TIMEOUT_MSEC = 5000;
    private static final int READ_TIMEOUT_MSEC = 5000;
    private Context mContext;
    private LoadThumbnailListener mListener;
}
