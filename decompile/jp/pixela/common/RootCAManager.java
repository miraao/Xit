// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.common;

import android.content.Context;
import android.content.res.Resources;
import java.io.*;

// Referenced classes of package jp.pixela.common:
//            PxLog

public class RootCAManager
{

    public RootCAManager()
    {
    }

    private static boolean CopyFile(InputStream inputstream, OutputStream outputstream)
    {
        inputstream = new BufferedInputStream(inputstream);
        boolean flag = writeData(inputstream, outputstream);
        try
        {
            inputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch(OutputStream outputstream)
        {
            inputstream = new StringBuilder();
            inputstream.append("CopyFile error : ");
            inputstream.append(outputstream.getMessage());
            PxLog.v("RootCAManager", inputstream.toString());
        }
        return flag;
        outputstream;
        break MISSING_BLOCK_LABEL_139;
        Exception exception;
        exception;
        outputstream = JVM INSTR new #53  <Class StringBuilder>;
        outputstream.StringBuilder();
        outputstream.append("CopyFile error : ");
        outputstream.append(exception.getMessage());
        PxLog.e("RootCAManager", outputstream.toString());
        try
        {
            inputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch(InputStream inputstream)
        {
            outputstream = new StringBuilder();
            outputstream.append("CopyFile error : ");
            outputstream.append(inputstream.getMessage());
            PxLog.v("RootCAManager", outputstream.toString());
        }
        return false;
        try
        {
            inputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch(InputStream inputstream)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("CopyFile error : ");
            stringbuilder.append(inputstream.getMessage());
            PxLog.v("RootCAManager", stringbuilder.toString());
        }
        throw outputstream;
    }

    public static boolean CopyRootCAFile(Context context)
    {
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append(context.getFilesDir().getPath());
        ((StringBuilder) (obj)).append("/");
        ((StringBuilder) (obj)).append("common/ca");
        obj = new File(((StringBuilder) (obj)).toString());
        if(!((File) (obj)).exists() && !((File) (obj)).mkdirs())
        {
            PxLog.e("RootCAManager", "CopyRootCAFile error : cannot mkdir.");
            return false;
        }
        String s = GetRootCAPath(context);
        int i = 0;
        int j = i;
        do
        {
            int k = ROOT_CA_FILE_IDS.length;
            boolean flag = true;
            if(i >= k)
                break;
            if(i == 0)
                flag = false;
            try
            {
                InputStream inputstream = context.getResources().openRawResource(ROOT_CA_FILE_IDS[i]);
                FileOutputStream fileoutputstream = JVM INSTR new #120 <Class FileOutputStream>;
                fileoutputstream.FileOutputStream(s, flag);
                j = CopyFile(inputstream, fileoutputstream);
            }
            catch(IOException ioexception)
            {
                context = new StringBuilder();
                context.append("CopyRootCAFile error : ");
                context.append(ioexception.getMessage());
                PxLog.e("RootCAManager", context.toString());
                return false;
            }
            i++;
        } while(true);
        if(j == 0)
        {
            PxLog.e("RootCAManager", "CopyRootCAFile cannot copy root ca files");
            return false;
        } else
        {
            return true;
        }
    }

    public static String GetRootCAPath(Context context)
    {
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append(context.getFilesDir().getPath());
        ((StringBuilder) (obj)).append("/");
        ((StringBuilder) (obj)).append("common/ca");
        ((StringBuilder) (obj)).append("/");
        ((StringBuilder) (obj)).append("root_ca_comp");
        obj = ((StringBuilder) (obj)).toString();
        context = new StringBuilder();
        context.append("GetRootCAPath rootCAPath=");
        context.append(((String) (obj)));
        PxLog.v("RootCAManager", context.toString());
        return ((String) (obj));
    }

    private static boolean writeData(InputStream inputstream, OutputStream outputstream)
    {
        Object obj;
        Object obj1;
        Object obj2;
        obj = null;
        obj1 = null;
        obj2 = obj1;
        Object obj3 = JVM INSTR new #133 <Class BufferedOutputStream>;
        obj2 = obj1;
        ((BufferedOutputStream) (obj3)).BufferedOutputStream(outputstream);
        outputstream = new byte[1024];
_L1:
        int i = inputstream.read(outputstream, 0, outputstream.length);
        if(i <= 0)
            break MISSING_BLOCK_LABEL_54;
        try
        {
            ((BufferedOutputStream) (obj3)).write(outputstream, 0, i);
        }
        // Misplaced declaration of an exception variable
        catch(OutputStream outputstream)
        {
            inputstream = ((InputStream) (obj3));
            break MISSING_BLOCK_LABEL_130;
        }
          goto _L1
        ((BufferedOutputStream) (obj3)).flush();
        if(obj3 != null)
            try
            {
                ((BufferedOutputStream) (obj3)).close();
            }
            // Misplaced declaration of an exception variable
            catch(OutputStream outputstream)
            {
                inputstream = new StringBuilder();
                inputstream.append("writeData error : ");
                inputstream.append(outputstream.getMessage());
                PxLog.v("RootCAManager", inputstream.toString());
            }
        return true;
        inputstream;
        obj2 = obj3;
        break MISSING_BLOCK_LABEL_230;
        inputstream;
        break MISSING_BLOCK_LABEL_230;
        outputstream;
        inputstream = obj;
        obj2 = inputstream;
        obj3 = JVM INSTR new #53  <Class StringBuilder>;
        obj2 = inputstream;
        ((StringBuilder) (obj3)).StringBuilder();
        obj2 = inputstream;
        ((StringBuilder) (obj3)).append("writeData error : ");
        obj2 = inputstream;
        ((StringBuilder) (obj3)).append(outputstream.getMessage());
        obj2 = inputstream;
        PxLog.e("RootCAManager", ((StringBuilder) (obj3)).toString());
        if(inputstream != null)
            try
            {
                inputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch(InputStream inputstream)
            {
                outputstream = new StringBuilder();
                outputstream.append("writeData error : ");
                outputstream.append(inputstream.getMessage());
                PxLog.v("RootCAManager", outputstream.toString());
            }
        return false;
        if(obj2 != null)
            try
            {
                ((BufferedOutputStream) (obj2)).close();
            }
            // Misplaced declaration of an exception variable
            catch(OutputStream outputstream)
            {
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append("writeData error : ");
                stringbuilder.append(outputstream.getMessage());
                PxLog.v("RootCAManager", stringbuilder.toString());
            }
        throw inputstream;
    }

    private static final int BUFFER_SIZE = 1024;
    private static final String NAME_ROOT_CA_DIR = "common/ca";
    private static final String NAME_ROOT_CA_FILE = "root_ca_comp";
    private static final int ROOT_CA_FILE_IDS[] = {
        0x7f0d0009, 0x7f0d0001, 0x7f0d0002, 0x7f0d0007, 0x7f0d0008, 0x7f0d0000, 0x7f0d0006
    };
    private static final String TAG = "RootCAManager";

}
