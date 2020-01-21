// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.common;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import java.io.*;

// Referenced classes of package jp.pixela.common:
//            PxLog

public class FontManager
{
    public static final class FontType extends Enum
    {

        public static FontType valueOf(String s)
        {
            return (FontType)Enum.valueOf(jp/pixela/common/FontManager$FontType, s);
        }

        public static FontType[] values()
        {
            return (FontType[])$VALUES.clone();
        }

        private static final FontType $VALUES[];
        public static final FontType KAKU_GOTHIC;
        public static final FontType MARU_GOTHIC;

        static 
        {
            MARU_GOTHIC = new FontType("MARU_GOTHIC", 0);
            KAKU_GOTHIC = new FontType("KAKU_GOTHIC", 1);
            $VALUES = (new FontType[] {
                MARU_GOTHIC, KAKU_GOTHIC
            });
        }

        private FontType(String s, int i)
        {
            super(s, i);
        }
    }


    public FontManager()
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
            PxLog.v("FontManager", inputstream.toString());
        }
        return flag;
        outputstream;
        break MISSING_BLOCK_LABEL_139;
        Exception exception;
        exception;
        outputstream = JVM INSTR new #58  <Class StringBuilder>;
        outputstream.StringBuilder();
        outputstream.append("CopyFile error : ");
        outputstream.append(exception.getMessage());
        PxLog.e("FontManager", outputstream.toString());
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
            PxLog.v("FontManager", outputstream.toString());
        }
        return false;
        try
        {
            inputstream.close();
        }
        catch(IOException ioexception)
        {
            inputstream = new StringBuilder();
            inputstream.append("CopyFile error : ");
            inputstream.append(ioexception.getMessage());
            PxLog.v("FontManager", inputstream.toString());
        }
        throw outputstream;
    }

    public static boolean CopyFont(Context context)
    {
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append(context.getFilesDir().getPath());
        ((StringBuilder) (obj)).append("/");
        ((StringBuilder) (obj)).append("common/data");
        obj = new File(((StringBuilder) (obj)).toString());
        if(!((File) (obj)).exists() && !((File) (obj)).mkdirs())
        {
            PxLog.e("FontManager", "CopyFont error : cannot mkdir.");
            return false;
        }
        obj = context.getResources().getAssets();
        String s = GetFontPath(context, FontType.KAKU_GOTHIC);
        IOException ioexception;
        InputStream inputstream1;
        FileOutputStream fileoutputstream1;
        boolean flag;
        boolean flag1;
        try
        {
            InputStream inputstream = ((AssetManager) (obj)).open("common/data/ui.dat");
            FileOutputStream fileoutputstream = JVM INSTR new #137 <Class FileOutputStream>;
            File file = JVM INSTR new #92  <Class File>;
            file.File(s);
            fileoutputstream.FileOutputStream(file);
            flag = CopyFile(inputstream, fileoutputstream);
        }
        catch(IOException ioexception1)
        {
            context = new StringBuilder();
            context.append("CopyFont error : ");
            context.append(ioexception1.getMessage());
            PxLog.e("FontManager", context.toString());
            return false;
        }
        if(!flag)
        {
            PxLog.e("FontManager", "CopyFont cannot copy KAKU_GOTHIC");
            return false;
        }
        context = GetFontPath(context, FontType.MARU_GOTHIC);
        inputstream1 = ((AssetManager) (obj)).open("common/data/ui2.dat");
        fileoutputstream1 = JVM INSTR new #137 <Class FileOutputStream>;
        obj = JVM INSTR new #92  <Class File>;
        ((File) (obj)).File(context);
        fileoutputstream1.FileOutputStream(((File) (obj)));
        flag1 = CopyFile(inputstream1, fileoutputstream1);
        flag = flag1;
        break MISSING_BLOCK_LABEL_221;
        ioexception;
        context = new StringBuilder();
        context.append("CopyFont error : ignore optional font ");
        context.append(ioexception.getMessage());
        PxLog.w("FontManager", context.toString());
        if(!flag)
        {
            PxLog.w("FontManager", "CopyFont cannot to copy MARU_GOTHIC_4K");
            return false;
        } else
        {
            return true;
        }
    }

    public static boolean CopyFontXml(Context context)
    {
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append(context.getFilesDir().getPath());
        ((StringBuilder) (obj)).append("/");
        ((StringBuilder) (obj)).append("common/data");
        Object obj1 = ((StringBuilder) (obj)).toString();
        obj = new StringBuilder();
        ((StringBuilder) (obj)).append(((String) (obj1)));
        ((StringBuilder) (obj)).append("/");
        ((StringBuilder) (obj)).append("fonts.xml");
        obj = ((StringBuilder) (obj)).toString();
        obj1 = new File(((String) (obj1)));
        if(!((File) (obj1)).exists() && !((File) (obj1)).mkdirs())
        {
            PxLog.e("FontManager", "CopyFont error : cannot mkdir.");
            return false;
        }
        obj1 = context.getResources().getAssets();
        Object obj2;
        StringBuilder stringbuilder1;
        Object obj3;
        boolean flag;
        StringBuilder stringbuilder2;
        try
        {
            obj1 = ((AssetManager) (obj1)).open("fonts.xml");
            FileOutputStream fileoutputstream = JVM INSTR new #137 <Class FileOutputStream>;
            File file = JVM INSTR new #92  <Class File>;
            file.File(((String) (obj)));
            fileoutputstream.FileOutputStream(file);
            flag = CopyFile(((InputStream) (obj1)), fileoutputstream);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("CopyFontXml error : ");
            stringbuilder.append(context.getMessage());
            PxLog.e("FontManager", stringbuilder.toString());
            return false;
        }
        if(!flag)
        {
            PxLog.e("FontManager", "CopyFont cannot copy fonts.xml");
            return false;
        }
        stringbuilder1 = null;
        obj1 = null;
        obj = obj1;
        obj3 = JVM INSTR new #58  <Class StringBuilder>;
        obj = obj1;
        ((StringBuilder) (obj3)).StringBuilder();
        obj = obj1;
        ((StringBuilder) (obj3)).append(context.getFilesDir().getPath());
        obj = obj1;
        ((StringBuilder) (obj3)).append("/../");
        obj = obj1;
        context = ((StringBuilder) (obj3)).toString();
        obj = obj1;
        obj3 = Runtime.getRuntime();
        obj = obj1;
        stringbuilder2 = JVM INSTR new #58  <Class StringBuilder>;
        obj = obj1;
        stringbuilder2.StringBuilder();
        obj = obj1;
        stringbuilder2.append("chmod o+rwx -R ");
        obj = obj1;
        stringbuilder2.append(context);
        obj = obj1;
        context = ((Runtime) (obj3)).exec(stringbuilder2.toString());
        context.waitFor();
        try
        {
            context.destroy();
        }
        // Misplaced declaration of an exception variable
        catch(Context context) { }
        return true;
        obj2;
        obj = context;
        context = ((Context) (obj2));
        break MISSING_BLOCK_LABEL_344;
        obj2;
        break MISSING_BLOCK_LABEL_295;
        context;
        break MISSING_BLOCK_LABEL_344;
        obj2;
        context = stringbuilder1;
        obj = context;
        stringbuilder1 = JVM INSTR new #58  <Class StringBuilder>;
        obj = context;
        stringbuilder1.StringBuilder();
        obj = context;
        stringbuilder1.append("setupFont exception :");
        obj = context;
        stringbuilder1.append(((Exception) (obj2)).toString());
        obj = context;
        PxLog.e("FontManager", stringbuilder1.toString());
        try
        {
            context.destroy();
        }
        // Misplaced declaration of an exception variable
        catch(Context context) { }
        return false;
        try
        {
            ((Process) (obj)).destroy();
        }
        catch(Exception exception) { }
        throw context;
    }

    public static String GetFontPath(Context context, FontType fonttype)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(context.getFilesDir().getPath());
        stringbuilder.append("/");
        stringbuilder.append("common/data");
        context = stringbuilder.toString();
        static class _cls1
        {

            static final int $SwitchMap$jp$pixela$common$FontManager$FontType[];

            static 
            {
                $SwitchMap$jp$pixela$common$FontManager$FontType = new int[FontType.values().length];
                try
                {
                    $SwitchMap$jp$pixela$common$FontManager$FontType[FontType.KAKU_GOTHIC.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                $SwitchMap$jp$pixela$common$FontManager$FontType[FontType.MARU_GOTHIC.ordinal()] = 2;
_L2:
                return;
                NoSuchFieldError nosuchfielderror1;
                nosuchfielderror1;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        switch(_cls1..SwitchMap.jp.pixela.common.FontManager.FontType[fonttype.ordinal()])
        {
        default:
            context = "";
            break;

        case 2: // '\002'
            fonttype = new StringBuilder();
            fonttype.append(context);
            fonttype.append("/");
            fonttype.append("ui2.dat");
            context = fonttype.toString();
            break;

        case 1: // '\001'
            fonttype = new StringBuilder();
            fonttype.append(context);
            fonttype.append("/");
            fonttype.append("ui.dat");
            context = fonttype.toString();
            break;
        }
        fonttype = new StringBuilder();
        fonttype.append("GetFontPath fontPath=");
        fonttype.append(context);
        PxLog.v("FontManager", fonttype.toString());
        return context;
    }

    private static Typeface createTypeface(Context context)
    {
        if(sTypeface != null)
            return sTypeface;
        try
        {
            String s = GetFontPath(context, FontType.MARU_GOTHIC);
            File file = JVM INSTR new #92  <Class File>;
            file.File(s);
            if(!file.exists())
                CopyFont(context);
            sTypeface = Typeface.createFromFile(s);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            sTypeface = Typeface.DEFAULT;
        }
        return sTypeface;
    }

    public static Typeface getTypeface(Context context)
    {
        return createTypeface(context);
    }

    private static boolean writeData(InputStream inputstream, OutputStream outputstream)
    {
        Object obj;
        Object obj1;
        Object obj2;
        obj = null;
        obj1 = null;
        obj2 = obj1;
        Object obj3 = JVM INSTR new #220 <Class BufferedOutputStream>;
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
                PxLog.v("FontManager", inputstream.toString());
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
        obj3 = JVM INSTR new #58  <Class StringBuilder>;
        obj2 = inputstream;
        ((StringBuilder) (obj3)).StringBuilder();
        obj2 = inputstream;
        ((StringBuilder) (obj3)).append("writeData error : ");
        obj2 = inputstream;
        ((StringBuilder) (obj3)).append(outputstream.getMessage());
        obj2 = inputstream;
        PxLog.e("FontManager", ((StringBuilder) (obj3)).toString());
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
                PxLog.v("FontManager", outputstream.toString());
            }
        return false;
        if(obj2 != null)
            try
            {
                ((BufferedOutputStream) (obj2)).close();
            }
            catch(IOException ioexception)
            {
                outputstream = new StringBuilder();
                outputstream.append("writeData error : ");
                outputstream.append(ioexception.getMessage());
                PxLog.v("FontManager", outputstream.toString());
            }
        throw inputstream;
    }

    private static final int BUFFER_SIZE = 1024;
    private static final String NAME_FONTDIR = "common/data";
    private static final String NAME_FONTS_XML_FILE = "fonts.xml";
    private static final String NAME_GOTHIC_FILE = "ui.dat";
    private static final String NAME_RGOTHIC_FILE = "ui2.dat";
    private static final String NAME_VENDOR_FONTDIR = "/vendor/pixela/font";
    private static final String TAG = "FontManager";
    private static Typeface sTypeface;

}
