// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.atv_app.common;

import android.util.Base64;
import java.io.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

// Referenced classes of package jp.pixela.atv_app.common:
//            PxLog

public class EncryptionUtility
{

    public EncryptionUtility()
    {
    }

    public static boolean cryptDataByte(int i, File file, File file1)
    {
        Object obj1;
        byte abyte0[];
        String s1;
        boolean flag;
        PxLog.d(TAG, "cryptDataByte in");
        int j = getKeySize();
        int k = (int)file.length();
        if(k % j != 0)
            k = (k / j + 1) * j;
        String s = TAG;
        obj1 = new StringBuilder();
        ((StringBuilder) (obj1)).append("cryptDataByte bufferSize : ");
        ((StringBuilder) (obj1)).append(k);
        PxLog.d(s, ((StringBuilder) (obj1)).toString());
        abyte0 = new byte[k];
        obj1 = null;
        s1 = null;
        flag = false;
        Object obj;
        obj = JVM INSTR new #71  <Class FileInputStream>;
        ((FileInputStream) (obj)).FileInputStream(file);
        file = JVM INSTR new #76  <Class FileOutputStream>;
        file.FileOutputStream(file1);
        ((InputStream) (obj)).read(abyte0, 0, abyte0.length);
        if(i != 1)
            break MISSING_BLOCK_LABEL_140;
        file1 = encrypt(abyte0);
        break MISSING_BLOCK_LABEL_146;
        file1 = decrypt(abyte0);
        if(i != 1) goto _L2; else goto _L1
_L1:
        boolean flag1;
        try
        {
            file.write(file1);
            break MISSING_BLOCK_LABEL_198;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj1)
        {
            file1 = file;
        }
          goto _L3
_L2:
        i = file1.length - 1;
        do
        {
            if(i <= -1)
                break MISSING_BLOCK_LABEL_198;
            if(file1[i] != 0)
                break;
            i--;
        } while(true);
        if(file1[i] != 10)
            break MISSING_BLOCK_LABEL_198;
        file.write(file1, 0, i + 1);
        if(obj != null)
            try
            {
                ((InputStream) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch(File file1) { }
        if(file != null)
            try
            {
                file.close();
            }
            // Misplaced declaration of an exception variable
            catch(File file) { }
        flag1 = true;
          goto _L4
        file1;
        break MISSING_BLOCK_LABEL_236;
        file1;
        file = null;
        obj1 = obj;
        obj = file;
          goto _L5
        obj1;
        file1 = null;
_L3:
        file = ((File) (obj));
          goto _L6
        file1;
        obj = null;
          goto _L5
        obj1;
        file1 = null;
        file = s1;
_L6:
        s1 = TAG;
        obj = JVM INSTR new #55  <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        ((StringBuilder) (obj)).append("cryptDataByte error : ");
        ((StringBuilder) (obj)).append(((Exception) (obj1)).getMessage());
        PxLog.e(s1, ((StringBuilder) (obj)).toString());
        if(file != null)
            try
            {
                file.close();
            }
            // Misplaced declaration of an exception variable
            catch(File file) { }
        flag1 = flag;
        if(file1 == null) goto _L4; else goto _L7
_L7:
        file1.close();
        flag1 = flag;
_L4:
        return flag1;
        obj1;
        obj = file1;
        file1 = ((File) (obj1));
        obj1 = file;
_L5:
        if(obj1 != null)
            try
            {
                ((InputStream) (obj1)).close();
            }
            // Misplaced declaration of an exception variable
            catch(File file) { }
        if(obj != null)
            try
            {
                ((OutputStream) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch(File file) { }
        throw file1;
        file;
        flag1 = flag;
        if(true) goto _L4; else goto _L8
_L8:
    }

    public static byte[] decrypt(String s)
        throws Exception
    {
        return decrypt(Base64.decode(s, 0));
    }

    public static byte[] decrypt(byte abyte0[])
        throws Exception
    {
        SecretKeySpec secretkeyspec = new SecretKeySpec(KEY_BITES, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
        cipher.init(2, secretkeyspec);
        return cipher.doFinal(abyte0);
    }

    public static String encode(byte abyte0[])
    {
        return Base64.encodeToString(abyte0, 0);
    }

    public static byte[] encrypt(String s)
        throws Exception
    {
        return encrypt(Base64.decode(s, 0));
    }

    public static byte[] encrypt(byte abyte0[])
        throws Exception
    {
        SecretKeySpec secretkeyspec = new SecretKeySpec(KEY_BITES, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
        cipher.init(1, secretkeyspec);
        return cipher.doFinal(abyte0);
    }

    public static int getKeySize()
    {
        return KEY_BITES.length;
    }

    private static final int BUFFER_SIZE = 1024;
    private static final byte KEY_BITES[] = {
        7, 0, 5, 0, 0, 0, 0, 19, 0, 0, 
        1, 0, 0, 0, 16, 0
    };
    private static final String TAG = "EncryptionUtility";

}
