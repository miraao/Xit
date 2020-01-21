// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.helper;

import android.content.Context;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import jp.pixela.common.PxLog;
import jp.pixela.pis_client.rest.thumbnail.ThumbnailData;
import jp.pixela.player_service.message.ProgramInfo;

// Referenced classes of package jp.pixela.pis_client.helper:
//            CacheHelper

public class ThumbnailCacheHelper
{

    public ThumbnailCacheHelper()
    {
    }

    public static void deleteCache(Context context)
    {
        jp/pixela/pis_client/helper/ThumbnailCacheHelper;
        JVM INSTR monitorenter ;
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
        if(j >= i)
            break; /* Loop/switch isn't completed */
        context = afile[j];
        SimpleDateFormat simpledateformat = JVM INSTR new #65  <Class SimpleDateFormat>;
        simpledateformat.SimpleDateFormat("yyyyMMdd");
        if(simpledateformat.parse(context.getName()).getTime() < l - 0xa4cb800L)
            CacheHelper.deleteCache(context);
        j++;
        if(true) goto _L2; else goto _L1
        Exception exception;
        exception;
        String s1 = TAG;
        context = JVM INSTR new #88  <Class StringBuilder>;
        context.StringBuilder();
        context.append("err=");
        context.append(exception);
        PxLog.e(s1, context.toString());
_L1:
        jp/pixela/pis_client/helper/ThumbnailCacheHelper;
        JVM INSTR monitorexit ;
        return;
        context;
        throw context;
    }

    private static String getFormattedCacheFilePathString(Context context, long l, String s, int i, int j)
    {
        Object obj = (new SimpleDateFormat("yyyyMMdd")).format(new Date(l));
        context = String.format("%s/thumbnail/%s", new Object[] {
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
        return String.format("%s/thumbnail/%s", new Object[] {
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
        if(s.length() >= 0L)
            return true;
        if(System.currentTimeMillis() - 0x2932e00L < s.lastModified())
            flag = true;
        return flag;
    }

    public static boolean isCacheExists(Context context, List list, long l)
    {
        jp/pixela/pis_client/helper/ThumbnailCacheHelper;
        JVM INSTR monitorenter ;
        long l1;
        if(list == null)
            return false;
        l1 = l;
        if(l >= 0L)
            break MISSING_BLOCK_LABEL_33;
        l1 = System.currentTimeMillis();
        break MISSING_BLOCK_LABEL_33;
        context;
        break MISSING_BLOCK_LABEL_119;
        new ArrayList();
        list = list.iterator();
        boolean flag = true;
_L2:
        ProgramInfo programinfo;
        if(!list.hasNext())
            break; /* Loop/switch isn't completed */
        programinfo = (ProgramInfo)list.next();
        if(programinfo == null)
            continue; /* Loop/switch isn't completed */
        boolean flag1 = isAvailableCache(getFormattedCacheFilePathString(context, l1, programinfo.getBroadcastTypeString(), programinfo.getServiceId(), programinfo.getEventId()));
        if(!flag1)
            flag = false;
        if(true) goto _L2; else goto _L1
_L1:
        return flag;
        throw context;
    }

    public static int readCache(Context context, List list, List list1, long l)
    {
        jp/pixela/pis_client/helper/ThumbnailCacheHelper;
        JVM INSTR monitorenter ;
        if(list == null || list1 == null)
            break MISSING_BLOCK_LABEL_353;
        if(l >= 0L)
            break MISSING_BLOCK_LABEL_31;
        l = System.currentTimeMillis();
        break MISSING_BLOCK_LABEL_31;
        context;
        break MISSING_BLOCK_LABEL_348;
        ArrayList arraylist;
        Iterator iterator;
        arraylist = JVM INSTR new #164 <Class ArrayList>;
        arraylist.ArrayList();
        iterator = list1.iterator();
_L2:
        boolean flag = iterator.hasNext();
        boolean flag1;
        flag1 = false;
        if(!flag)
            break MISSING_BLOCK_LABEL_307;
        Object obj = (ProgramInfo)iterator.next();
        if(obj == null) goto _L2; else goto _L1
_L1:
        String s;
        int i;
        int j;
        String s1;
        s = ((ProgramInfo) (obj)).getBroadcastTypeString();
        i = ((ProgramInfo) (obj)).getServiceId();
        j = ((ProgramInfo) (obj)).getEventId();
        s1 = getFormattedCacheFilePathString(context, l, s, i, j);
        list1 = CacheHelper.readCache(s1);
        if(list1 != null)
            break MISSING_BLOCK_LABEL_207;
        if(((ProgramInfo) (obj)).getReferServiceId() == 0)
            break MISSING_BLOCK_LABEL_207;
        obj = getFormattedCacheFilePathString(context, l, s, ((ProgramInfo) (obj)).getReferServiceId(), ((ProgramInfo) (obj)).getReferEventId());
        list1 = CacheHelper.readCache(((String) (obj)));
        if(list1 == null)
            break MISSING_BLOCK_LABEL_233;
        StringBuilder stringbuilder = JVM INSTR new #88  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        stringbuilder.append("ref:");
        stringbuilder.append(((String) (obj)));
        CacheHelper.writeCacheString(s1, stringbuilder.toString(), false);
        if(hasReferenceKey(list1))
        {
            s1 = getReferenceCachePath(list1);
            list1 = CacheHelper.readCache(s1);
        }
        if(list1 != null)
            break MISSING_BLOCK_LABEL_239;
_L3:
        i = 0;
        break MISSING_BLOCK_LABEL_310;
label0:
        {
            if(list1.length != 0 || isAvailableCache(s1))
                break label0;
            context = JVM INSTR new #52  <Class File>;
            context.File(s1);
            CacheHelper.deleteCache(context);
        }
          goto _L3
        ThumbnailData thumbnaildata = JVM INSTR new #217 <Class ThumbnailData>;
        thumbnaildata.ThumbnailData(i, j, s, null, -1, -1);
        thumbnaildata.setImage(list1);
        arraylist.add(thumbnaildata);
          goto _L2
        i = 1;
        if(i == 0)
            break MISSING_BLOCK_LABEL_337;
        list.clear();
        list.addAll(arraylist);
        i = ((flag1) ? 1 : 0);
        break MISSING_BLOCK_LABEL_340;
        i = -1;
        return i;
        throw context;
        return -1;
    }

    public static void writeCache(Context context, List list, long l)
    {
        jp/pixela/pis_client/helper/ThumbnailCacheHelper;
        JVM INSTR monitorenter ;
        PxLog.d(TAG, "writeCache");
        if(list != null)
            break MISSING_BLOCK_LABEL_27;
        PxLog.d(TAG, "thumbnailDataList==null");
        jp/pixela/pis_client/helper/ThumbnailCacheHelper;
        JVM INSTR monitorexit ;
        return;
        long l1;
        l1 = l;
        if(l >= 0L)
            break MISSING_BLOCK_LABEL_41;
        l1 = System.currentTimeMillis();
        String s = TAG;
        StringBuilder stringbuilder = JVM INSTR new #88  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        stringbuilder.append("writeCache thumbnailDataList.size():");
        stringbuilder.append(list.size());
        PxLog.d(s, stringbuilder.toString());
        list = list.iterator();
_L2:
        String s1;
        do
        {
            if(!list.hasNext())
                break MISSING_BLOCK_LABEL_225;
            ThumbnailData thumbnaildata = (ThumbnailData)list.next();
            s1 = thumbnaildata.getBroadcastType();
            int i = thumbnaildata.getServiceId();
            int j = thumbnaildata.getEventId();
            String s2 = TAG;
            StringBuilder stringbuilder1 = JVM INSTR new #88  <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            stringbuilder1.append("writeCache thumbnailData isGenre:");
            stringbuilder1.append(thumbnaildata.isGenre());
            PxLog.d(s2, stringbuilder1.toString());
            s1 = getFormattedCacheFilePathString(context, l1, s1, i, j);
            if(thumbnaildata.isGenre())
                break MISSING_BLOCK_LABEL_215;
            CacheHelper.writeCache(s1, thumbnaildata.getImage(), true);
        } while(true);
        CacheHelper.writeCache(s1, null, false);
        if(true) goto _L2; else goto _L1
_L1:
        jp/pixela/pis_client/helper/ThumbnailCacheHelper;
        JVM INSTR monitorexit ;
        return;
        context;
        throw context;
    }

    private static final long CACHE_AVAILABLE_TIME_MILLIS = 0x2932e00L;
    private static final String CACHE_DATE_FORMAT = "yyyyMMdd";
    private static final String CACHE_FILE_NAME_FORMAT = "%s_%d_%d";
    private static final String CACHE_FILE_PATH_FORMAT = "%s/thumbnail/%s";
    private static final long CACHE_HOLD_TIME_MILLIS = 0xa4cb800L;
    private static final String CACHE_REFERENCE_KEY = "ref:";
    private static final String DELIMITER = "/";
    private static final String TAG = "ThumbnailCacheHelper";

}
