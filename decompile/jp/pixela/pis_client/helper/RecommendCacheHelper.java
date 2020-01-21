// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.helper;

import android.content.Context;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import jp.pixela.common.PxLog;
import jp.pixela.pis_client.rest.recommend.RecommendData;
import jp.pixela.player_service.message.ProgramInfo;

// Referenced classes of package jp.pixela.pis_client.helper:
//            CacheHelper

public class RecommendCacheHelper
{

    public RecommendCacheHelper()
    {
    }

    public static void deleteCache(Context context)
    {
        long l;
        String s;
        l = System.currentTimeMillis();
        s = getFormattedCachePathString(context, l);
        File afile[];
        int i;
        context = JVM INSTR new #52  <Class File>;
        context.File(s);
        afile = context.getParentFile().listFiles();
        i = afile.length;
        int j = 0;
_L2:
        File file;
        if(j >= i)
            break; /* Loop/switch isn't completed */
        file = afile[j];
        context = JVM INSTR new #65  <Class SimpleDateFormat>;
        context.SimpleDateFormat("yyyyMMdd");
        if(context.parse(file.getName()).getTime() < l - 0xa4cb800L)
            CacheHelper.deleteCache(file);
        j++;
        if(true) goto _L2; else goto _L1
        context;
        String s1 = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("err=");
        stringbuilder.append(context);
        PxLog.e(s1, stringbuilder.toString());
_L1:
    }

    private static String getFormattedCacheFilePathString(Context context, long l, String s, int i, int j)
    {
        Object obj = (new SimpleDateFormat("yyyyMMdd")).format(new Date(l));
        context = String.format("%s/recommend/%s", new Object[] {
            context.getFilesDir(), obj
        });
        s = String.format("%s_%d_%d", new Object[] {
            s, Integer.valueOf(i), Integer.valueOf(j)
        });
        obj = new StringBuilder();
        ((StringBuilder) (obj)).append(context);
        ((StringBuilder) (obj)).append("/");
        ((StringBuilder) (obj)).append(s);
        return ((StringBuilder) (obj)).toString();
    }

    private static String getFormattedCachePathString(Context context, long l)
    {
        String s = (new SimpleDateFormat("yyyyMMdd")).format(new Date(l));
        return String.format("%s/recommend/%s", new Object[] {
            context.getFilesDir(), s
        });
    }

    private static String getReferenceCachePath(byte abyte0[])
    {
        if(abyte0 != null && abyte0.length != 0)
        {
            try
            {
                int i = "ref:".length();
                abyte0 = new String(abyte0, i, abyte0.length - i, "UTF-8");
            }
            catch(Exception exception)
            {
                abyte0 = TAG;
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append("err=");
                stringbuilder.append(exception);
                PxLog.e(abyte0, stringbuilder.toString());
                return null;
            }
            return abyte0;
        } else
        {
            return null;
        }
    }

    private static boolean hasReferenceKey(byte abyte0[])
    {
        boolean flag = false;
        if(abyte0 == null)
            return false;
        if(abyte0.length < "ref:".length())
            return false;
        byte abyte1[];
        int i;
        byte byte0;
        byte byte1;
        try
        {
            abyte1 = "ref:".getBytes("UTF-8");
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            String s = TAG;
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("err=");
            stringbuilder.append(abyte0);
            PxLog.e(s, stringbuilder.toString());
            return false;
        }
        if(abyte1 == null)
            break MISSING_BLOCK_LABEL_78;
        if(abyte1.length == 0)
            break MISSING_BLOCK_LABEL_78;
        i = 0;
label0:
        {
            while(i < abyte1.length) 
            {
                byte0 = abyte0[i];
                byte1 = abyte1[i];
                if(byte0 != byte1)
                    break label0;
                i++;
            }
            flag = true;
        }
        return flag;
        return false;
    }

    private static boolean isAvailableCache(String s)
    {
        s = new File(s);
        boolean flag = false;
        if(s == null)
            return false;
        if(!s.exists())
            return false;
        if(s.length() == 0L)
            return false;
        if(System.currentTimeMillis() - 0x36ee80L < s.lastModified())
            flag = true;
        return flag;
    }

    public static int readCache(Context context, List list, List list1, long l)
    {
        ArrayList arraylist;
        Iterator iterator;
        if(list == null || list1 == null)
            break MISSING_BLOCK_LABEL_462;
        if(l < 0L)
            l = System.currentTimeMillis();
        arraylist = new ArrayList();
        iterator = list1.iterator();
_L7:
        int i;
        boolean flag2;
        boolean flag3;
        ProgramInfo programinfo;
        String s;
        int j;
        String s1;
        boolean flag = iterator.hasNext();
        i = 1;
        flag2 = false;
        flag3 = false;
        if(!flag)
            break; /* Loop/switch isn't completed */
        programinfo = (ProgramInfo)iterator.next();
        if(programinfo == null)
            continue; /* Loop/switch isn't completed */
        s = programinfo.getBroadcastTypeString();
        j = programinfo.getServiceId();
        i = programinfo.getEventId();
        s1 = getFormattedCacheFilePathString(context, l, s, j, i);
        list1 = CacheHelper.readCache(s1);
        if(list1 != null || programinfo.getReferServiceId() == 0) goto _L2; else goto _L1
_L1:
        String s4;
        s4 = getFormattedCacheFilePathString(context, l, s, programinfo.getReferServiceId(), programinfo.getReferEventId());
        list1 = CacheHelper.readCache(s4);
        if(list1 != null) goto _L4; else goto _L3
_L3:
        i = 0;
        break; /* Loop/switch isn't completed */
_L4:
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("ref:");
        stringbuilder.append(s4);
        CacheHelper.writeCacheString(s1, stringbuilder.toString(), false);
_L2:
        if(hasReferenceKey(list1))
        {
            s1 = getReferenceCachePath(list1);
            list1 = CacheHelper.readCache(s1);
        }
        if(list1 == null)
            continue; /* Loop/switch isn't completed */
        if(isAvailableCache(s1))
            break; /* Loop/switch isn't completed */
        CacheHelper.deleteCache(new File(s1));
        if(true) goto _L3; else goto _L5
_L5:
        String s2 = JVM INSTR new #123 <Class String>;
        s2.String(list1, "UTF-8");
        list1 = s2.split(",", 0);
        if(list1.length != 1)
            break MISSING_BLOCK_LABEL_304;
        flag3 = Boolean.valueOf(list1[0]).booleanValue();
        boolean flag1;
        flag1 = false;
        break MISSING_BLOCK_LABEL_391;
        if(list1.length != 2)
            break MISSING_BLOCK_LABEL_339;
        flag3 = Boolean.valueOf(list1[0]).booleanValue();
        try
        {
            flag1 = Boolean.valueOf(list1[1]).booleanValue();
            break MISSING_BLOCK_LABEL_391;
        }
        // Misplaced declaration of an exception variable
        catch(List list1) { }
        break MISSING_BLOCK_LABEL_349;
        flag1 = false;
        break MISSING_BLOCK_LABEL_391;
        list1;
        flag3 = false;
        String s3 = TAG;
        StringBuilder stringbuilder1 = new StringBuilder();
        stringbuilder1.append("err=");
        stringbuilder1.append(list1);
        PxLog.e(s3, stringbuilder1.toString());
        flag1 = false;
        list1 = new RecommendData(j, i, s);
        list1.setRecommend(flag3);
        list1.setPixRecommend(flag1);
        arraylist.add(list1);
        if(true) goto _L7; else goto _L6
_L6:
        if(i != 0)
        {
            list.clear();
            list.addAll(arraylist);
            i = ((flag2) ? 1 : 0);
        } else
        {
            i = -1;
        }
        return i;
        return -1;
    }

    public static void writeCache(Context context, List list, long l)
    {
        if(list == null)
            return;
        long l1 = l;
        if(l < 0L)
            l1 = System.currentTimeMillis();
        for(list = list.iterator(); list.hasNext();)
        {
            Object obj = (RecommendData)list.next();
            String s = getFormattedCacheFilePathString(context, l1, ((RecommendData) (obj)).getBroadcastType(), ((RecommendData) (obj)).getServiceId(), ((RecommendData) (obj)).getEventId());
            try
            {
                boolean flag = ((RecommendData) (obj)).getRecommend();
                boolean flag1 = ((RecommendData) (obj)).getPixRecommend();
                obj = JVM INSTR new #88  <Class StringBuilder>;
                ((StringBuilder) (obj)).StringBuilder();
                ((StringBuilder) (obj)).append(String.valueOf(flag));
                ((StringBuilder) (obj)).append(",");
                ((StringBuilder) (obj)).append(String.valueOf(flag1));
                CacheHelper.writeCacheString(s, ((StringBuilder) (obj)).toString(), false);
            }
            catch(Exception exception)
            {
                String s1 = TAG;
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append("err=");
                stringbuilder.append(exception);
                PxLog.e(s1, stringbuilder.toString());
            }
        }

    }

    private static final long CACHE_AVAILABLE_TIME_MILLIS = 0x36ee80L;
    private static final String CACHE_DATE_FORMAT = "yyyyMMdd";
    private static final String CACHE_FILE_NAME_FORMAT = "%s_%d_%d";
    private static final String CACHE_FILE_PATH_FORMAT = "%s/recommend/%s";
    private static final long CACHE_HOLD_TIME_MILLIS = 0xa4cb800L;
    private static final String CACHE_REFERENCE_KEY = "ref:";
    private static final String DELIMITER = "/";
    private static final String TAG = "RecommendCacheHelper";

}
