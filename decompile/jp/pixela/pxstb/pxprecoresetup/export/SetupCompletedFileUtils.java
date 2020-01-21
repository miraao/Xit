// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxstb.pxprecoresetup.export;

import android.util.Xml;
import java.io.*;
import java.nio.charset.StandardCharsets;
import org.xmlpull.v1.XmlPullParser;

public class SetupCompletedFileUtils
{

    public SetupCompletedFileUtils()
    {
    }

    public static boolean isSetupCompleted()
    {
        String s;
        boolean flag;
        s = null;
        flag = false;
        Object obj;
        obj = JVM INSTR new #24  <Class FileInputStream>;
        File file = JVM INSTR new #26  <Class File>;
        file.File("/data/pixela/setupCompleted.xml");
        ((FileInputStream) (obj)).FileInputStream(file);
        Object obj2;
        byte abyte0[] = new byte[((FileInputStream) (obj)).available()];
        ((FileInputStream) (obj)).read(abyte0);
        obj2 = JVM INSTR new #42  <Class String>;
        ((String) (obj2)).String(abyte0, StandardCharsets.UTF_8);
        boolean flag1;
        flag1 = true;
        s = ((String) (obj2));
          goto _L1
        obj;
        obj = null;
_L4:
        flag1 = false;
_L1:
        boolean flag2;
        flag2 = flag1;
        if(obj == null)
            break MISSING_BLOCK_LABEL_88;
        ((FileInputStream) (obj)).close();
        flag2 = flag1;
        break MISSING_BLOCK_LABEL_88;
        Object obj1;
        obj1;
        flag2 = false;
        if(!flag2)
            return false;
        if(s == null)
            return false;
        int i;
        obj2 = Xml.newPullParser();
        StringReader stringreader = JVM INSTR new #62  <Class StringReader>;
        stringreader.StringReader(s);
        ((XmlPullParser) (obj2)).setInput(stringreader);
        i = ((XmlPullParser) (obj2)).getEventType();
        int j = 0;
_L3:
        int k;
        flag1 = flag2;
        k = j;
        if(i == 1)
            break; /* Loop/switch isn't completed */
        k = j;
        if(i != 2)
            break MISSING_BLOCK_LABEL_214;
        k = j;
        i = j;
        if(!((XmlPullParser) (obj2)).getName().equals("setupCompleted"))
            break MISSING_BLOCK_LABEL_214;
        i = j;
        ((XmlPullParser) (obj2)).next();
        k = j;
        i = j;
        if(((XmlPullParser) (obj2)).getText().equals("true"))
            k = 1;
        i = k;
        j = ((XmlPullParser) (obj2)).next();
        i = j;
        j = k;
        if(true) goto _L3; else goto _L2
        stringreader;
        i = 0;
_L5:
        flag1 = false;
        k = i;
_L2:
        boolean flag3;
        if(k == 0)
            flag3 = flag;
        else
            flag3 = flag1;
        return flag3;
        Exception exception;
        exception;
          goto _L4
        stringreader;
          goto _L5
    }

    private static final String SETUP_COMPLETED_FILE_PATH = "/data/pixela/setupCompleted.xml";
    private static final String SETUP_COMPLETED_TAG_NAME = "setupCompleted";
}
