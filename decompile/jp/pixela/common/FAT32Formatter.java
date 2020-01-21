// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.common;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import jp.pixela.pxlibs.utils.android.log.LoggerRTM;

// Referenced classes of package jp.pixela.common:
//            SCSICommandSender, PxLog

public class FAT32Formatter
{
    private class CHS
    {

        int cyl_sec;
        byte head;
        final FAT32Formatter this$0;

        private CHS()
        {
            this$0 = FAT32Formatter.this;
            super();
        }

    }

    private class Capacity
    {

        long block_count;
        int block_size;
        final FAT32Formatter this$0;

        private Capacity()
        {
            this$0 = FAT32Formatter.this;
            super();
        }

    }

    public static interface FormatProgressListener
    {

        public abstract boolean onProgressed(int i);
    }


    public FAT32Formatter(Context context, UsbDevice usbdevice)
    {
        cmdSender_ = new SCSICommandSender(context, usbdevice);
    }

    private int formatDeviceRaw(FormatProgressListener formatprogresslistener)
    {
        Capacity capacity = readCapacity();
        if(capacity == null)
            return -1;
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("block_size=");
        stringbuilder.append(capacity.block_size);
        stringbuilder.append(", block_count=");
        stringbuilder.append(capacity.block_count);
        PxLog.d(s, stringbuilder.toString());
        long l = capacity.block_count - 8192L;
        int i = 32768 / capacity.block_size;
        int j = Math.max(i, 32);
        long l1 = i;
        long l3 = ((((((l / l1) * 4L + (long)capacity.block_size) - 1L) / (long)capacity.block_size + l1) - 1L) / l1) * l1;
        long l4 = 8192L + (long)j;
        long l5 = l4 + 2L * l3;
        l1 = l5 + 1L;
        int k = (int)(0xc8000L / l1);
        int i1 = (int)((l4 * 100L) / l1);
        long l6 = l4 + l3;
        int j1 = (int)((l6 * 100L) / l1);
        int k1 = (int)((100L * l5) / l1);
        if(safeInvoke(formatprogresslistener, 0))
            return -2;
        int i2 = initializeMBR(capacity.block_size, 8192L, l);
        if(i2 != 0)
            return i2;
        byte abyte0[] = new byte[capacity.block_size];
        for(long l2 = 1L; l2 < 8192L; l2++)
        {
            int j2 = writeSectors(capacity.block_size, l2, abyte0);
            if(j2 != 0)
                return j2;
            if(safeInvoke(formatprogresslistener, (int)(((long)k * l2) / 8192L)))
                return -2;
        }

        j = initializePBR(capacity.block_size, 8192L, l, i, j, l3, formatprogresslistener, k, i1);
        if(j != 0)
            return j;
        i1 = initializeFAT(capacity.block_size, i, l4, l3, formatprogresslistener, i1, j1);
        if(i1 != 0)
            return i1;
        k1 = initializeFAT(capacity.block_size, i, l6, l3, formatprogresslistener, j1, k1);
        if(k1 != 0)
            return k1;
        k1 = writeSectors(capacity.block_size, l5, abyte0);
        if(k1 != 0)
        {
            return k1;
        } else
        {
            safeInvoke(formatprogresslistener, 100);
            return 0;
        }
    }

    private int initializeFAT(int i, int j, long l, long l1, FormatProgressListener formatprogresslistener, 
            int k, int i1)
    {
        byte abyte0[] = new byte[i * j];
        SCSICommandSender.setIntToByteArray(abyte0, 0, 0xffffff8L);
        SCSICommandSender.setIntToByteArray(abyte0, 4, 0xfffffffL);
        SCSICommandSender.setIntToByteArray(abyte0, 8, 0xffffff8L);
        int j1 = (int)(l1 / (long)j);
        for(int k1 = 0; k1 < j1; k1++)
        {
            int i2 = writeSectors(i, l + (long)(k1 * j), abyte0);
            if(i2 != 0)
                return i2;
            if(safeInvoke(formatprogresslistener, k + ((i1 - k) * k1) / j1))
                return -2;
            if(k1 == 0)
            {
                SCSICommandSender.setIntToByteArray(abyte0, 0, 0L);
                SCSICommandSender.setIntToByteArray(abyte0, 4, 0L);
                SCSICommandSender.setIntToByteArray(abyte0, 8, 0L);
            }
        }

        return !safeInvoke(formatprogresslistener, i1) ? 0 : -2;
    }

    private int initializeMBR(int i, long l, long l1)
    {
        long l2 = l + l1;
        boolean flag;
        if((long)i * l2 >= 0x1f6080000L)
            flag = true;
        else
            flag = false;
        CHS chs = lbaToCHS(l);
        CHS chs1;
        if(flag)
        {
            chs1 = new CHS();
            chs1.head = (byte)-2;
            chs1.cyl_sec = 65535;
        } else
        {
            chs1 = lbaToCHS(l2 - 1L);
        }
        byte abyte0[] = new byte[i];
        abyte0[447] = chs.head;
        SCSICommandSender.setShortToByteArray(abyte0, 448, chs.cyl_sec);
        int j;
        if(flag)
            j = 12;
        else
            j = 11;
        abyte0[450] = (byte)j;
        abyte0[451] = chs1.head;
        SCSICommandSender.setShortToByteArray(abyte0, 452, chs1.cyl_sec);
        SCSICommandSender.setIntToByteArray(abyte0, 454, l);
        SCSICommandSender.setIntToByteArray(abyte0, 458, l1);
        abyte0[510] = (byte)85;
        abyte0[511] = (byte)-86;
        return writeSectors(i, 0L, abyte0);
    }

    private int initializePBR(int i, long l, long l1, int j, int k, 
            long l2, FormatProgressListener formatprogresslistener, int i1, int j1)
    {
        long l3 = l + l1;
        boolean flag;
        if((long)i * l3 >= 0x1f6080000L)
            flag = true;
        else
            flag = false;
        CHS chs;
        if(flag)
        {
            chs = new CHS();
            chs.head = (byte)-2;
            chs.cyl_sec = 65535;
        } else
        {
            chs = lbaToCHS(l3 - 1L);
        }
        byte abyte0[] = new byte[i * 3];
        abyte0[0] = (byte)-21;
        abyte0[1] = (byte)60;
        abyte0[2] = (byte)-112;
        char c;
        try
        {
            System.arraycopy("MSWIN4.1".getBytes("UTF-8"), 0, abyte0, 3, 8);
            SCSICommandSender.setShortToByteArray(abyte0, 11, i);
        }
        // Misplaced declaration of an exception variable
        catch(FormatProgressListener formatprogresslistener)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(LOG_HEAD);
            stringbuilder.append("e=");
            stringbuilder.append(formatprogresslistener);
            LoggerRTM.e(stringbuilder.toString(), new Object[0]);
            return -1;
        }
        abyte0[13] = (byte)64;
        SCSICommandSender.setShortToByteArray(abyte0, 14, k);
        abyte0[16] = (byte)2;
        abyte0[21] = (byte)-8;
        SCSICommandSender.setShortToByteArray(abyte0, 24, 63);
        if(flag)
        {
            c = '\377';
            break MISSING_BLOCK_LABEL_184;
        }
        c = chs.head;
        SCSICommandSender.setShortToByteArray(abyte0, 26, c);
        SCSICommandSender.setIntToByteArray(abyte0, 28, l);
        SCSICommandSender.setIntToByteArray(abyte0, 32, l1);
        SCSICommandSender.setIntToByteArray(abyte0, 36, l2);
        SCSICommandSender.setIntToByteArray(abyte0, 44, 2L);
        SCSICommandSender.setShortToByteArray(abyte0, 48, 1);
        SCSICommandSender.setShortToByteArray(abyte0, 50, 6);
        abyte0[64] = (byte)-128;
        abyte0[66] = (byte)41;
        SCSICommandSender.setIntToByteArray(abyte0, 67, (long)(Math.random() * 4294967295D));
        System.arraycopy("PIX-SMB100 ".getBytes("UTF-8"), 0, abyte0, 71, 11);
        System.arraycopy("FAT32   ".getBytes("UTF-8"), 0, abyte0, 82, 8);
        abyte0[510] = (byte)85;
        abyte0[511] = (byte)-86;
        l1 = (l1 - ((long)k + 2L * l2)) / (long)j;
        SCSICommandSender.setIntToByteArray(abyte0, 512, 0x41615252L);
        SCSICommandSender.setIntToByteArray(abyte0, 996, 0x61417272L);
        SCSICommandSender.setIntToByteArray(abyte0, 1000, l1 - 1L);
        SCSICommandSender.setIntToByteArray(abyte0, 1004, 3L);
        SCSICommandSender.setIntToByteArray(abyte0, 1020, 0xaa550000L);
        abyte0[1534] = (byte)85;
        abyte0[1535] = (byte)-86;
        if(safeInvoke(formatprogresslistener, i1))
            return -2;
        j = writeSectors(i, l, abyte0);
        if(j != 0)
            return j;
        if(safeInvoke(formatprogresslistener, i1 + (j1 - i1) / 2))
            return -2;
        i = writeSectors(i, l + 6L, abyte0);
        if(i != 0)
            return i;
        return !safeInvoke(formatprogresslistener, j1) ? 0 : -2;
    }

    private static int readBE16(byte abyte0[], int i)
    {
        byte byte0 = abyte0[i + 1];
        return abyte0[i + 0] << 8 & 0xff00 | byte0 << 0 & 0xff;
    }

    private static long readBE32(byte abyte0[], int i)
    {
        long l = abyte0[i + 3];
        long l1 = abyte0[i + 2];
        long l2 = abyte0[i + 1];
        return (long)abyte0[i + 0] << 24 & 0xff000000L | (l << 0 & 255L | l1 << 8 & 65280L | l2 << 16 & 0xff0000L);
    }

    private Capacity readCapacity()
    {
        SCSICommandSender.SCSICommandParam scsicommandparam = new SCSICommandSender.SCSICommandParam();
        scsicommandparam.cdb_ = new byte[10];
        scsicommandparam.in_dir_ = true;
        scsicommandparam.lun_ = (byte)0;
        scsicommandparam.data_ = new byte[8];
        scsicommandparam.timeout_ = 60000;
        scsicommandparam.cdb_[0] = (byte)37;
        int i = cmdSender_.sendSCSICommand(scsicommandparam);
        if(i == 0 && scsicommandparam.target_status_value_ == 0)
        {
            Capacity capacity = new Capacity();
            capacity.block_count = readBE32(scsicommandparam.data_, 0);
            if(capacity.block_count != -1L)
                capacity.block_count = capacity.block_count + 1L;
            capacity.block_size = (int)readBE32(scsicommandparam.data_, 4);
            return capacity;
        } else
        {
            String s = TAG;
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("READ CAPACITY failed: ret=");
            stringbuilder.append(i);
            stringbuilder.append(", TSV=");
            stringbuilder.append(scsicommandparam.target_status_value_);
            PxLog.e(s, stringbuilder.toString());
            return null;
        }
    }

    private boolean safeInvoke(FormatProgressListener formatprogresslistener, int i)
    {
        if(formatprogresslistener != null)
            return formatprogresslistener.onProgressed(i);
        else
            return false;
    }

    private static int senseDataToReturnCode(byte abyte0[])
    {
        byte byte0 = abyte0[2];
        byte byte1 = abyte0[12];
        return abyte0[13] << 0 | ((byte0 & 0xf) << 16 | 0x80000000 | byte1 << 4);
    }

    private static void writeBE16(byte abyte0[], int i, int j)
    {
        abyte0[i + 0] = (byte)(j >> 8);
        abyte0[i + 1] = (byte)(j >> 0);
    }

    private static void writeBE32(byte abyte0[], int i, long l)
    {
        abyte0[i + 0] = (byte)(int)(l >> 24);
        abyte0[i + 1] = (byte)(int)(l >> 16);
        abyte0[i + 2] = (byte)(int)(l >> 8);
        abyte0[i + 3] = (byte)(int)(l >> 0);
    }

    private int writeSectors(int i, long l, byte abyte0[])
    {
        Object obj = new SCSICommandSender.SCSICommandParam();
        obj.cdb_ = new byte[10];
        obj.in_dir_ = false;
        obj.lun_ = (byte)0;
        obj.data_ = abyte0;
        obj.timeout_ = 60000;
        ((SCSICommandSender.SCSICommandParam) (obj)).cdb_[0] = (byte)42;
        writeBE32(((SCSICommandSender.SCSICommandParam) (obj)).cdb_, 2, l);
        writeBE16(((SCSICommandSender.SCSICommandParam) (obj)).cdb_, 7, abyte0.length / i);
        i = cmdSender_.sendSCSICommand(((SCSICommandSender.SCSICommandParam) (obj)));
        if(i != 0)
        {
            abyte0 = TAG;
            obj = new StringBuilder();
            ((StringBuilder) (obj)).append("WRITE(10)(");
            ((StringBuilder) (obj)).append(l);
            ((StringBuilder) (obj)).append(") failed: ret=");
            ((StringBuilder) (obj)).append(i);
            PxLog.e(abyte0, ((StringBuilder) (obj)).toString());
            return i;
        }
        if(((SCSICommandSender.SCSICommandParam) (obj)).target_status_value_ != 0)
        {
            i = senseDataToReturnCode(((SCSICommandSender.SCSICommandParam) (obj)).sense_data_);
            obj = TAG;
            abyte0 = new StringBuilder();
            abyte0.append("WRITE(10)(");
            abyte0.append(l);
            abyte0.append(") failed: ret=");
            abyte0.append(i);
            PxLog.e(((String) (obj)), abyte0.toString());
            return i;
        } else
        {
            return 0;
        }
    }

    public int formatDevice(FormatProgressListener formatprogresslistener)
    {
        if(!cmdSender_.init())
        {
            return -1;
        } else
        {
            int i = formatDeviceRaw(formatprogresslistener);
            cmdSender_.uninit();
            return i;
        }
    }

    CHS lbaToCHS(long l)
    {
        CHS chs = new CHS();
        chs.head = (byte)(int)(l / 64512L);
        l %= 64512L;
        chs.cyl_sec = (int)(l / 63L << 6 | l % 63L);
        return chs;
    }

    private static final String LOG_HEAD;
    private static final int MAX_CYLINDERS_PER_HEAD = 1024;
    private static final byte MAX_SECTORS_PER_CLUSTER = 64;
    private static final int MAX_SECTORS_PER_CLUSTER_IN_BYTES = 32768;
    private static final int MAX_SECTORS_PER_CYLINDER = 63;
    private static final long PARTITION_START_LBA = 8192L;
    private static final int RESERVED_SECTOR_COUNT = 32;
    private static final long SYSTEM_ID_THRESHOLD = 0x1f6080000L;
    private static final String TAG = "FAT32Formatter";
    private SCSICommandSender cmdSender_;

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/common/FAT32Formatter.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }
}
