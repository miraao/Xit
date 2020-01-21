// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.helper;

import java.io.*;
import jp.pixela.common.PxLog;

public class CacheHelper
{

    public CacheHelper()
    {
    }

    public static void deleteCache(File file)
    {
        if(file == null)
            return;
        if(!file.exists())
            return;
        if(file.isDirectory())
        {
            File afile[] = file.listFiles();
            int i = afile.length;
            for(int j = 0; j < i; j++)
                deleteCache(afile[j]);

        }
        file.delete();
    }

    public static byte[] readCache(String s)
    {
        String s1;
        Object obj;
        Object obj2;
        s1 = null;
        obj = null;
        obj2 = obj;
        Object obj3 = JVM INSTR new #40  <Class FileInputStream>;
        obj2 = obj;
        Object obj4 = JVM INSTR new #18  <Class File>;
        obj2 = obj;
        ((File) (obj4)).File(s);
        obj2 = obj;
        ((FileInputStream) (obj3)).FileInputStream(((File) (obj4)));
        s = new byte[((FileInputStream) (obj3)).available()];
        try
        {
            ((FileInputStream) (obj3)).read(s);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj2)
        {
            break MISSING_BLOCK_LABEL_122;
        }
        obj2 = s;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_249;
        try
        {
            ((FileInputStream) (obj3)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj2)
        {
            obj3 = TAG;
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("err=");
            stringbuilder.append(obj2);
            PxLog.e(((String) (obj3)), stringbuilder.toString());
            obj2 = s;
            break MISSING_BLOCK_LABEL_249;
        }
        obj2 = s;
        break MISSING_BLOCK_LABEL_249;
        s;
        obj2 = obj3;
        break MISSING_BLOCK_LABEL_251;
        obj2;
        s = null;
        Object obj1;
        obj1 = obj2;
        break MISSING_BLOCK_LABEL_137;
        s;
        break MISSING_BLOCK_LABEL_251;
        obj1;
        s = null;
        obj3 = s1;
        obj2 = obj3;
        s1 = TAG;
        obj2 = obj3;
        obj4 = JVM INSTR new #60  <Class StringBuilder>;
        obj2 = obj3;
        ((StringBuilder) (obj4)).StringBuilder();
        obj2 = obj3;
        ((StringBuilder) (obj4)).append("read cache err=");
        obj2 = obj3;
        ((StringBuilder) (obj4)).append(((Exception) (obj1)).getMessage());
        obj2 = obj3;
        PxLog.v(s1, ((StringBuilder) (obj4)).toString());
        if(obj3 != null)
            try
            {
                ((FileInputStream) (obj3)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj2)
            {
                String s2 = TAG;
                StringBuilder stringbuilder2 = new StringBuilder();
                stringbuilder2.append("err=");
                stringbuilder2.append(obj2);
                PxLog.e(s2, stringbuilder2.toString());
            }
        obj2 = s;
        return ((byte []) (obj2));
        if(obj2 != null)
            try
            {
                ((FileInputStream) (obj2)).close();
            }
            catch(Exception exception)
            {
                String s3 = TAG;
                StringBuilder stringbuilder1 = new StringBuilder();
                stringbuilder1.append("err=");
                stringbuilder1.append(exception);
                PxLog.e(s3, stringbuilder1.toString());
            }
        throw s;
    }

    public static void writeCache(String s, byte abyte0[], boolean flag)
    {
        String s1;
        StringBuilder stringbuilder1;
        String s2;
        s1 = null;
        stringbuilder1 = null;
        s2 = stringbuilder1;
        File file = JVM INSTR new #18  <Class File>;
        s2 = stringbuilder1;
        file.File(s);
        s2 = stringbuilder1;
        if(file.exists() && !flag)
            return;
        s2 = stringbuilder1;
        s = file.getParentFile();
        s2 = stringbuilder1;
        if(s.exists())
            break MISSING_BLOCK_LABEL_71;
        s2 = stringbuilder1;
        s.mkdirs();
        s2 = stringbuilder1;
        s = JVM INSTR new #99  <Class FileOutputStream>;
        s2 = stringbuilder1;
        s.FileOutputStream(file);
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_119;
        try
        {
            if(abyte0.length > 0)
                s.write(abyte0);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            break MISSING_BLOCK_LABEL_154;
        }
        break MISSING_BLOCK_LABEL_119;
        abyte0;
        s2 = s;
        s = abyte0;
        break MISSING_BLOCK_LABEL_258;
        if(s == null)
            break MISSING_BLOCK_LABEL_257;
        try
        {
            s.close();
            break MISSING_BLOCK_LABEL_257;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s2 = TAG;
            abyte0 = new StringBuilder();
        }
        break MISSING_BLOCK_LABEL_235;
        s;
        break MISSING_BLOCK_LABEL_258;
        abyte0;
        s = s1;
        s2 = s;
        s1 = TAG;
        s2 = s;
        stringbuilder1 = JVM INSTR new #60  <Class StringBuilder>;
        s2 = s;
        stringbuilder1.StringBuilder();
        s2 = s;
        stringbuilder1.append("err=");
        s2 = s;
        stringbuilder1.append(abyte0);
        s2 = s;
        PxLog.e(s1, stringbuilder1.toString());
        if(s == null)
            break MISSING_BLOCK_LABEL_257;
        try
        {
            s.close();
            break MISSING_BLOCK_LABEL_257;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s2 = TAG;
            abyte0 = new StringBuilder();
        }
        abyte0.append("err=");
        abyte0.append(s);
        PxLog.e(s2, abyte0.toString());
        return;
        if(s2 != null)
            try
            {
                s2.close();
            }
            // Misplaced declaration of an exception variable
            catch(byte abyte0[])
            {
                String s3 = TAG;
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append("err=");
                stringbuilder.append(abyte0);
                PxLog.e(s3, stringbuilder.toString());
            }
        throw s;
    }

    public static void writeCacheString(String s, String s1, boolean flag)
    {
        try
        {
            writeCache(s, s1.getBytes("UTF-8"), flag);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s1 = TAG;
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("err=");
            stringbuilder.append(s);
            PxLog.e(s1, stringbuilder.toString());
        }
    }

    private static final String TAG = "CacheHelper";

}
