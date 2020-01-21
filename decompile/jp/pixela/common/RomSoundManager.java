// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.common;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import java.io.*;
import jp.pixela.pxlibs.utils.android.log.LoggerRTM;

// Referenced classes of package jp.pixela.common:
//            PxLog

public class RomSoundManager
{

    public RomSoundManager()
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
            inputstream = TAG;
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("CopyFile error : ");
            stringbuilder.append(outputstream.getMessage());
            PxLog.v(inputstream, stringbuilder.toString());
        }
        return flag;
        outputstream;
        break MISSING_BLOCK_LABEL_150;
        Exception exception;
        exception;
        String s = TAG;
        outputstream = JVM INSTR new #21  <Class StringBuilder>;
        outputstream.StringBuilder();
        outputstream.append("CopyFile error : ");
        outputstream.append(exception.getMessage());
        PxLog.e(s, outputstream.toString());
        try
        {
            inputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch(OutputStream outputstream)
        {
            String s1 = TAG;
            inputstream = new StringBuilder();
            inputstream.append("CopyFile error : ");
            inputstream.append(outputstream.getMessage());
            PxLog.v(s1, inputstream.toString());
        }
        return false;
        try
        {
            inputstream.close();
        }
        catch(IOException ioexception)
        {
            inputstream = TAG;
            StringBuilder stringbuilder1 = new StringBuilder();
            stringbuilder1.append("CopyFile error : ");
            stringbuilder1.append(ioexception.getMessage());
            PxLog.v(inputstream, stringbuilder1.toString());
        }
        throw outputstream;
    }

    public static boolean CopyRomSound(Context context)
    {
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append(context.getFilesDir().getPath());
        ((StringBuilder) (obj)).append("/");
        ((StringBuilder) (obj)).append("common/raw");
        String s = ((StringBuilder) (obj)).toString();
        obj = new File(s);
        if(!((File) (obj)).exists() && !((File) (obj)).mkdirs())
        {
            PxLog.e(TAG, "CopyFont error : cannot mkdir.");
            return false;
        } else
        {
            copyRomSound(context.getResources().getAssets(), s);
            return false;
        }
    }

    public static String GetRomSoundDirectoryPath(Context context)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(context.getFilesDir().getPath());
        stringbuilder.append("/");
        stringbuilder.append("common/raw");
        stringbuilder.append("/");
        return stringbuilder.toString();
    }

    private static void copyRomSound(AssetManager assetmanager, String s)
    {
        for(int i = 0; i < 14 && copyRomSound(assetmanager, s, i); i++);
    }

    private static boolean copyRomSound(AssetManager assetmanager, String s, int i)
    {
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append("rom");
        ((StringBuilder) (obj)).append(String.format("%02d", new Object[] {
            Integer.valueOf(i)
        }));
        ((StringBuilder) (obj)).append(".ogg");
        obj = ((StringBuilder) (obj)).toString();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(s);
        stringbuilder.append("/");
        stringbuilder.append(((String) (obj)));
        s = stringbuilder.toString();
        boolean flag;
        try
        {
            Object obj1 = JVM INSTR new #21  <Class StringBuilder>;
            ((StringBuilder) (obj1)).StringBuilder();
            ((StringBuilder) (obj1)).append("raw/");
            ((StringBuilder) (obj1)).append(((String) (obj)));
            assetmanager = assetmanager.open(((StringBuilder) (obj1)).toString());
            obj1 = JVM INSTR new #151 <Class FileOutputStream>;
            obj = JVM INSTR new #87  <Class File>;
            ((File) (obj)).File(s);
            ((FileOutputStream) (obj1)).FileOutputStream(((File) (obj)));
            flag = CopyFile(assetmanager, ((OutputStream) (obj1)));
        }
        // Misplaced declaration of an exception variable
        catch(AssetManager assetmanager)
        {
            s = new StringBuilder();
            s.append(LOG_HEAD);
            s.append("e=");
            s.append(assetmanager);
            LoggerRTM.e(s.toString(), new Object[0]);
            return false;
        }
        return flag;
    }

    private static boolean writeData(InputStream inputstream, OutputStream outputstream)
    {
        StringBuilder stringbuilder;
        Object obj;
        Object obj1;
        stringbuilder = null;
        obj = null;
        obj1 = obj;
        Object obj2 = JVM INSTR new #168 <Class BufferedOutputStream>;
        obj1 = obj;
        ((BufferedOutputStream) (obj2)).BufferedOutputStream(outputstream);
        outputstream = new byte[1024];
_L1:
        int i = inputstream.read(outputstream, 0, outputstream.length);
        if(i <= 0)
            break MISSING_BLOCK_LABEL_54;
        try
        {
            ((BufferedOutputStream) (obj2)).write(outputstream, 0, i);
        }
        // Misplaced declaration of an exception variable
        catch(OutputStream outputstream)
        {
            inputstream = ((InputStream) (obj2));
            break MISSING_BLOCK_LABEL_137;
        }
          goto _L1
        ((BufferedOutputStream) (obj2)).flush();
        if(obj2 != null)
            try
            {
                ((BufferedOutputStream) (obj2)).close();
            }
            // Misplaced declaration of an exception variable
            catch(OutputStream outputstream)
            {
                inputstream = TAG;
                obj1 = new StringBuilder();
                ((StringBuilder) (obj1)).append("writeData error : ");
                ((StringBuilder) (obj1)).append(outputstream.getMessage());
                PxLog.v(inputstream, ((StringBuilder) (obj1)).toString());
            }
        return true;
        inputstream;
        obj1 = obj2;
        break MISSING_BLOCK_LABEL_247;
        inputstream;
        break MISSING_BLOCK_LABEL_247;
        outputstream;
        inputstream = stringbuilder;
        obj1 = inputstream;
        obj2 = TAG;
        obj1 = inputstream;
        stringbuilder = JVM INSTR new #21  <Class StringBuilder>;
        obj1 = inputstream;
        stringbuilder.StringBuilder();
        obj1 = inputstream;
        stringbuilder.append("writeData error : ");
        obj1 = inputstream;
        stringbuilder.append(outputstream.getMessage());
        obj1 = inputstream;
        PxLog.e(((String) (obj2)), stringbuilder.toString());
        if(inputstream != null)
            try
            {
                inputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch(OutputStream outputstream)
            {
                inputstream = TAG;
                obj1 = new StringBuilder();
                ((StringBuilder) (obj1)).append("writeData error : ");
                ((StringBuilder) (obj1)).append(outputstream.getMessage());
                PxLog.v(inputstream, ((StringBuilder) (obj1)).toString());
            }
        return false;
        if(obj1 != null)
            try
            {
                ((BufferedOutputStream) (obj1)).close();
            }
            // Misplaced declaration of an exception variable
            catch(OutputStream outputstream)
            {
                String s = TAG;
                StringBuilder stringbuilder1 = new StringBuilder();
                stringbuilder1.append("writeData error : ");
                stringbuilder1.append(outputstream.getMessage());
                PxLog.v(s, stringbuilder1.toString());
            }
        throw inputstream;
    }

    private static final int BUFFER_SIZE = 1024;
    private static final String LOG_HEAD;
    private static final String NAME_ROWDATA_DIR = "common/raw";
    private static final int ROW_SOUNDS_COUNT = 14;
    private static final String TAG = "RomSoundManager";

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/common/RomSoundManager.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }
}
