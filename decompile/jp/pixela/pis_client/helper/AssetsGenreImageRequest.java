// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.helper;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import jp.pixela.common.PxLog;

public class AssetsGenreImageRequest
{

    public AssetsGenreImageRequest()
    {
    }

    public static ByteBuffer readGenreImage(Context context, String s)
    {
        Object obj;
        String s1 = TAG;
        obj = new StringBuilder();
        ((StringBuilder) (obj)).append("in imagePath=");
        ((StringBuilder) (obj)).append(s);
        PxLog.v(s1, ((StringBuilder) (obj)).toString());
        obj = null;
        context = context.getResources().getAssets().open(s);
        int i;
        try
        {
            s = JVM INSTR new #59  <Class ByteArrayOutputStream>;
            s.ByteArrayOutputStream();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s = null;
            obj = context;
            context = s;
            break MISSING_BLOCK_LABEL_160;
        }
        i = context.read();
        if(i == -1)
            break MISSING_BLOCK_LABEL_76;
        try
        {
            s.write(i);
            break MISSING_BLOCK_LABEL_55;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            obj = context;
            context = s;
        }
          goto _L1
        obj = s.toByteArray();
        Context context1;
        Exception exception;
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        s = ((String) (obj));
        if(context == null)
            break MISSING_BLOCK_LABEL_178;
        try
        {
            context.close();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            s = ((String) (obj));
            if(false)
                ;
            else
                break MISSING_BLOCK_LABEL_178;
        }
        s = ((String) (obj));
        break MISSING_BLOCK_LABEL_178;
        obj;
        context1 = context;
        context = ((Context) (obj));
        obj = s;
        s = context1;
        break MISSING_BLOCK_LABEL_137;
        exception;
        s = context;
        context = exception;
        break MISSING_BLOCK_LABEL_137;
        context;
        s = null;
        if(obj != null)
            try
            {
                ((ByteArrayOutputStream) (obj)).close();
            }
            catch(Exception exception1) { }
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        throw context;
        context;
        obj = null;
        context = ((Context) (obj));
_L1:
        if(context != null)
            try
            {
                context.close();
            }
            // Misplaced declaration of an exception variable
            catch(Context context) { }
        if(obj != null)
            try
            {
                ((InputStream) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Context context) { }
        s = null;
        if(s == null)
            return null;
        else
            return ByteBuffer.wrap(s);
    }

    private static final String TAG = "AssetsGenreImageRequest";

}
