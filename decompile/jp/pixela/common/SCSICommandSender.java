// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.common;

import android.content.Context;
import android.hardware.usb.*;

// Referenced classes of package jp.pixela.common:
//            PxLog

public class SCSICommandSender
{
    public static class SCSICommandParam
    {

        public byte cdb_[];
        public byte data_[];
        public boolean in_dir_;
        public byte lun_;
        public byte sense_data_[];
        public byte target_status_value_;
        public int timeout_;
        public int transfer_length_;

        public SCSICommandParam()
        {
        }
    }

    private class SCSICommandParamRaw extends SCSICommandParam
    {

        public byte csw_status_;
        final SCSICommandSender this$0;

        private SCSICommandParamRaw()
        {
            this$0 = SCSICommandSender.this;
            super();
        }

    }


    public SCSICommandSender(Context context, UsbDevice usbdevice)
    {
        if_index_ = getMassStorageInterfaceIndex(usbdevice);
        if(if_index_ < 0)
        {
            PxLog.e("SCSICommandSender", "Failed to get interface index");
            return;
        } else
        {
            device_ = usbdevice;
            ctx_ = context;
            return;
        }
    }

    private int bulkTransfer(UsbEndpoint usbendpoint, byte abyte0[], int i)
    {
        int j = usbendpoint.getMaxPacketSize();
        int l;
        for(int k = abyte0.length; k > 0; k -= l)
        {
            l = Math.min(j, k);
            l = conn_.bulkTransfer(usbendpoint, abyte0, abyte0.length - k, l, i);
            if(l < 0)
                return l;
        }

        return abyte0.length;
    }

    static int getMassStorageInterfaceIndex(UsbDevice usbdevice)
    {
        int i = usbdevice.getDeviceClass();
        boolean flag = isMassStorageDevice(i, usbdevice.getDeviceSubclass(), usbdevice.getDeviceProtocol());
        int k = 0;
        if(flag)
            return 0;
        if(i == 0)
        {
            for(int j = usbdevice.getInterfaceCount(); k < j; k++)
            {
                UsbInterface usbinterface = usbdevice.getInterface(k);
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append("    interfaceclass = ");
                stringbuilder.append(usbinterface.getInterfaceClass());
                PxLog.v("SCSICommandSender", stringbuilder.toString());
                if(isMassStorageDevice(usbinterface.getInterfaceClass(), usbinterface.getInterfaceSubclass(), usbinterface.getInterfaceProtocol()))
                    return k;
            }

        }
        return -1;
    }

    static boolean isMassStorageDevice(int i, int j, int k)
    {
        boolean flag;
        if(i == 8 && j == 6 && k == 80)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private int sendSCSICommandRaw(SCSICommandParamRaw scsicommandparamraw)
    {
        byte abyte0[] = new byte[31];
        setIntToByteArray(abyte0, 0, 0x43425355L);
        int i = cbw_tag_;
        cbw_tag_ = i + 1;
        long l = i;
        setIntToByteArray(abyte0, 4, l);
        setIntToByteArray(abyte0, 8, scsicommandparamraw.data_.length);
        if(scsicommandparamraw.in_dir_)
            i = 128;
        else
            i = 0;
        abyte0[12] = (byte)i;
        abyte0[13] = scsicommandparamraw.lun_;
        abyte0[14] = (byte)scsicommandparamraw.cdb_.length;
        System.arraycopy(scsicommandparamraw.cdb_, 0, abyte0, 15, scsicommandparamraw.cdb_.length);
        if(conn_.bulkTransfer(ep_out_, abyte0, abyte0.length, scsicommandparamraw.timeout_) != abyte0.length)
        {
            PxLog.e("SCSICommandSender", "Failed to send CBW");
            return -1;
        }
        if(scsicommandparamraw.data_ != null && scsicommandparamraw.data_.length > 0)
        {
            UsbEndpoint usbendpoint;
            if(scsicommandparamraw.in_dir_)
                usbendpoint = ep_in_;
            else
                usbendpoint = ep_out_;
            i = bulkTransfer(usbendpoint, scsicommandparamraw.data_, scsicommandparamraw.timeout_);
            if(i < 0)
            {
                PxLog.e("SCSICommandSender", "Failed to transfer data");
                return i;
            }
            scsicommandparamraw.transfer_length_ = i;
        }
        usbendpoint = new byte[13];
        i = bulkTransfer(ep_in_, usbendpoint, scsicommandparamraw.timeout_);
        if(i != usbendpoint.length)
        {
            scsicommandparamraw = new StringBuilder();
            scsicommandparamraw.append("Failed to receive CSW: ret=");
            scsicommandparamraw.append(i);
            PxLog.e("SCSICommandSender", scsicommandparamraw.toString());
            return -1;
        }
        long l1 = toIntFrom4Bytes(usbendpoint, 0);
        if(l1 != 0x53425355L && l1 != 0x43425355L)
        {
            scsicommandparamraw = new StringBuilder();
            scsicommandparamraw.append("CSW signature is invalid:");
            scsicommandparamraw.append(l1);
            PxLog.e("SCSICommandSender", scsicommandparamraw.toString());
            return -1;
        }
        if(toIntFrom4Bytes(usbendpoint, 4) != l)
        {
            scsicommandparamraw = new StringBuilder();
            scsicommandparamraw.append("CSW tag is invalid:");
            scsicommandparamraw.append(toIntFrom4Bytes(usbendpoint, 4));
            PxLog.e("SCSICommandSender", scsicommandparamraw.toString());
            return -1;
        } else
        {
            scsicommandparamraw.csw_status_ = usbendpoint[12];
            return 0;
        }
    }

    public static void setIntToByteArray(byte abyte0[], int i, long l)
    {
        abyte0[i + 0] = (byte)(int)(l >> 0);
        abyte0[i + 1] = (byte)(int)(l >> 8);
        abyte0[i + 2] = (byte)(int)(l >> 16);
        abyte0[i + 3] = (byte)(int)(l >> 24);
    }

    public static void setShortToByteArray(byte abyte0[], int i, int j)
    {
        abyte0[i + 0] = (byte)(j >> 0);
        abyte0[i + 1] = (byte)(j >> 8);
    }

    public static long toIntFrom4Bytes(byte abyte0[], int i)
    {
        long l = abyte0[i + 0];
        long l1 = abyte0[i + 1];
        long l2 = abyte0[i + 2];
        return (long)abyte0[i + 3] << 24 & 0xff000000L | (l << 0 & 255L | l1 << 8 & 65280L | l2 << 16 & 0xff0000L);
    }

    public boolean init()
    {
        if(if_index_ < 0)
            return false;
        iface_ = device_.getInterface(if_index_);
        for(int i = 0; i < iface_.getEndpointCount(); i++)
        {
            UsbEndpoint usbendpoint = iface_.getEndpoint(i);
            if(usbendpoint.getDirection() == 128)
                ep_in_ = usbendpoint;
            else
                ep_out_ = usbendpoint;
        }

        if(ep_in_ != null && ep_out_ != null)
        {
            conn_ = ((UsbManager)ctx_.getApplicationContext().getSystemService("usb")).openDevice(device_);
            if(conn_ == null)
            {
                PxLog.e("SCSICommandSender", "Failed to openDevice()");
                return false;
            } else
            {
                conn_.claimInterface(iface_, true);
                return true;
            }
        } else
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("UsbEndPoint is null. In=");
            stringbuilder.append(ep_in_);
            stringbuilder.append(", out=");
            stringbuilder.append(ep_out_);
            PxLog.e("SCSICommandSender", stringbuilder.toString());
            return false;
        }
    }

    public int sendSCSICommand(SCSICommandParam scsicommandparam)
    {
        SCSICommandParamRaw scsicommandparamraw;
        scsicommandparamraw = new SCSICommandParamRaw();
        scsicommandparamraw.cdb_ = scsicommandparam.cdb_;
        scsicommandparamraw.in_dir_ = scsicommandparam.in_dir_;
        scsicommandparamraw.lun_ = scsicommandparam.lun_;
        scsicommandparamraw.data_ = scsicommandparam.data_;
        scsicommandparamraw.timeout_ = scsicommandparam.timeout_;
        int i = sendSCSICommandRaw(scsicommandparamraw);
        if(i != 0)
            return i;
        scsicommandparamraw.csw_status_;
        JVM INSTR tableswitch 0 2: default 92
    //                   0 221
    //                   1 106
    //                   2 95;
           goto _L1 _L2 _L3 _L4
_L1:
        break; /* Loop/switch isn't completed */
_L4:
        PxLog.e("SCSICommandSender", "Phase error happened!!");
        break; /* Loop/switch isn't completed */
_L3:
        scsicommandparamraw = new SCSICommandParamRaw();
        scsicommandparamraw.cdb_ = new byte[6];
        scsicommandparamraw.in_dir_ = true;
        scsicommandparamraw.lun_ = scsicommandparam.lun_;
        scsicommandparamraw.data_ = new byte[252];
        scsicommandparamraw.timeout_ = scsicommandparam.timeout_;
        scsicommandparamraw.cdb_[0] = (byte)3;
        scsicommandparamraw.cdb_[4] = (byte)scsicommandparamraw.data_.length;
        if(sendSCSICommandRaw(scsicommandparamraw) >= 0 && scsicommandparamraw.csw_status_ == 0)
        {
            scsicommandparam.target_status_value_ = (byte)2;
            scsicommandparam.sense_data_ = scsicommandparamraw.data_;
        } else
        {
            PxLog.e("SCSICommandSender", "Failed REQUEST SENSE");
            return -1;
        }
          goto _L5
_L2:
        scsicommandparam.target_status_value_ = (byte)0;
        scsicommandparam.transfer_length_ = scsicommandparamraw.transfer_length_;
_L5:
        return 0;
        return -1;
    }

    public void uninit()
    {
        if(conn_ != null)
        {
            conn_.releaseInterface(iface_);
            conn_ = null;
        }
        iface_ = null;
        ep_in_ = null;
        ep_out_ = null;
    }

    private static final long CBW_SIGNATURE = 0x43425355L;
    private static final long CSW_SIGNATURE = 0x53425355L;
    private static final String TAG = "SCSICommandSender";
    private int cbw_tag_;
    private UsbDeviceConnection conn_;
    private Context ctx_;
    private UsbDevice device_;
    private UsbEndpoint ep_in_;
    private UsbEndpoint ep_out_;
    private final int if_index_;
    private UsbInterface iface_;
}
