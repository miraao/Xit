// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.common;

import android.app.PendingIntent;
import android.content.*;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Environment;
import android.os.Handler;
import java.io.File;
import java.util.*;

// Referenced classes of package jp.pixela.common:
//            PxLog, SCSICommandSender, FAT32Formatter

public class HddFormatManager
    implements FAT32Formatter.FormatProgressListener
{
    private static final class FormatState extends Enum
    {

        public static FormatState valueOf(String s)
        {
            return (FormatState)Enum.valueOf(jp/pixela/common/HddFormatManager$FormatState, s);
        }

        public static FormatState[] values()
        {
            return (FormatState[])$VALUES.clone();
        }

        private static final FormatState $VALUES[];
        public static final FormatState CENCELED;
        public static final FormatState COMPLETE;
        public static final FormatState FORMATTING;
        public static final FormatState IDLE;

        static 
        {
            IDLE = new FormatState("IDLE", 0);
            CENCELED = new FormatState("CENCELED", 1);
            FORMATTING = new FormatState("FORMATTING", 2);
            COMPLETE = new FormatState("COMPLETE", 3);
            $VALUES = (new FormatState[] {
                IDLE, CENCELED, FORMATTING, COMPLETE
            });
        }

        private FormatState(String s, int i)
        {
            super(s, i);
        }
    }

    public static class HddDeviceInfo
    {

        public final boolean equals(Object obj)
        {
            boolean flag = false;
            if(obj == null)
                return false;
            if(!(obj instanceof HddDeviceInfo))
                return false;
            obj = (HddDeviceInfo)obj;
            boolean flag1 = flag;
            if(((HddDeviceInfo) (obj)).mProductId == mProductId)
            {
                flag1 = flag;
                if(((HddDeviceInfo) (obj)).mVendorId == mVendorId)
                    flag1 = true;
            }
            return flag1;
        }

        public String getDeviceName()
        {
            return mDeviceName;
        }

        public final int hashCode()
        {
            return Arrays.hashCode(new int[] {
                mProductId, mVendorId
            });
        }

        private final String mDeviceName;
        private final int mProductId;
        private final int mVendorId;

        public HddDeviceInfo(String s, int i, int j)
        {
            mDeviceName = s;
            mVendorId = i;
            mProductId = j;
        }
    }

    public static interface HddFormatListener
    {

        public abstract boolean onProgressed(int i);

        public abstract void onResult(int i);
    }


    public HddFormatManager()
    {
        mContext = null;
        mPermission = null;
        mSupportedHddList = new ArrayList();
        mDeviceList = new ArrayList();
        mListener = null;
        mHandler = null;
        mThread = null;
        mState = FormatState.IDLE;
    }

    private void formatStorageAsFAT32()
    {
        mThread = new Thread(new Runnable() {

            public void run()
            {
                UsbDevice usbdevice;
label0:
                {
                    usbdevice = getSelectedDevice();
                    if(usbdevice == null)
                    {
                        if(mListener != null)
                            mListener.onResult(-1);
                        return;
                    }
                    synchronized(HddFormatManager.mLock)
                    {
                        if(mState != FormatState.FORMATTING)
                            break label0;
                        if(mListener != null)
                            mListener.onResult(-1);
                    }
                    return;
                }
                mState = FormatState.FORMATTING;
                obj1;
                JVM INSTR monitorexit ;
                int i = (new FAT32Formatter(mContext, usbdevice)).formatDevice(HddFormatManager.this);
                obj1 = new StringBuilder();
                ((StringBuilder) (obj1)).append("formatDevice result: ");
                ((StringBuilder) (obj1)).append(i);
                PxLog.d("FAT32Formatter", ((StringBuilder) (obj1)).toString());
                synchronized(HddFormatManager.mLock)
                {
                    mState = FormatState.COMPLETE;
                }
                if(i != 0 && i != -2)
                {
                    if(mListener != null)
                        mListener.onResult(i);
                } else
                if(i == 0 && mListener != null)
                    mListener.onResult(i);
                return;
                obj1;
                obj;
                JVM INSTR monitorexit ;
                throw obj1;
                exception;
                obj1;
                JVM INSTR monitorexit ;
                throw exception;
            }

            final HddFormatManager this$0;

            
            {
                this$0 = HddFormatManager.this;
                super();
            }
        }
);
        mThread.start();
    }

    private UsbDevice getSelectedDevice()
    {
        if(mDeviceList.size() > 0)
            return (UsbDevice)mDeviceList.get(0);
        else
            return null;
    }

    public static boolean isConnectedUsbDevice(Context context, ArrayList arraylist)
    {
        boolean flag = false;
        if(context != null && arraylist != null)
        {
            context = ((UsbManager)context.getSystemService("usb")).getDeviceList().values().iterator();
            boolean flag1;
            do
            {
                flag1 = flag;
                if(!context.hasNext())
                    break;
                UsbDevice usbdevice = (UsbDevice)context.next();
                if(!arraylist.contains(new HddDeviceInfo(null, usbdevice.getVendorId(), usbdevice.getProductId())))
                    continue;
                flag1 = true;
                break;
            } while(true);
            return flag1;
        } else
        {
            return false;
        }
    }

    public static boolean isFormatted(Context context)
    {
        File afile[] = context.getExternalFilesDirs(null);
        boolean flag = false;
        if(afile == null)
            return false;
        int i = 0;
        boolean flag1;
        do
        {
            flag1 = flag;
            if(i >= afile.length)
                break;
            if(afile[i] != null && Environment.isExternalStorageRemovable(afile[i]))
            {
                context = afile[i].getAbsolutePath();
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append("storage path : ");
                stringbuilder.append(context);
                PxLog.d("FAT32Formatter", stringbuilder.toString());
                if(context != null && !context.isEmpty())
                {
                    flag1 = true;
                    break;
                }
            }
            i++;
        } while(true);
        return flag1;
    }

    private boolean isSupportedDevice(UsbDevice usbdevice)
    {
        usbdevice = new HddDeviceInfo(null, usbdevice.getVendorId(), usbdevice.getProductId());
        return mSupportedHddList.contains(usbdevice);
    }

    public void addSupportedDevice(String s, int i, int j)
    {
        s = new HddDeviceInfo(s, i, j);
        mSupportedHddList.add(s);
    }

    public void cancelFormat()
    {
        synchronized(mLock)
        {
            if(mState == FormatState.FORMATTING)
                mState = FormatState.CENCELED;
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void initialize(Context context, String s)
    {
        mContext = context;
        mSupportedHddList.clear();
        mPermission = s;
        mHandler = new Handler();
        context = new IntentFilter(mPermission);
        mContext.registerReceiver(mUsbReceiver, context);
    }

    public boolean isConnectedDevice()
    {
        boolean flag;
        if(getSelectedDevice() != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean onProgressed(int i)
    {
label0:
        {
            synchronized(mLock)
            {
                if(mState != FormatState.CENCELED)
                    break label0;
            }
            return true;
        }
        obj;
        JVM INSTR monitorexit ;
        if(mListener != null)
            return mListener.onProgressed(i);
        else
            return false;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void registerHddFormatListener(HddFormatListener hddformatlistener)
    {
        mListener = hddformatlistener;
    }

    public void searchDevices()
    {
        if(mContext == null)
            return;
        mDeviceList.clear();
        Iterator iterator = ((UsbManager)mContext.getSystemService("usb")).getDeviceList().values().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            UsbDevice usbdevice = (UsbDevice)iterator.next();
            if(SCSICommandSender.getMassStorageInterfaceIndex(usbdevice) != -1 && isSupportedDevice(usbdevice))
                mDeviceList.add(usbdevice);
        } while(true);
    }

    public boolean startFormat()
    {
        if(mContext == null)
            return false;
        UsbDevice usbdevice = getSelectedDevice();
        if(usbdevice == null)
            return false;
        UsbManager usbmanager = (UsbManager)mContext.getSystemService("usb");
        if(!usbmanager.hasPermission(usbdevice))
        {
            usbmanager.requestPermission(usbdevice, PendingIntent.getBroadcast(mContext, 0, new Intent(mPermission), 0));
            return true;
        } else
        {
            formatStorageAsFAT32();
            return true;
        }
    }

    public void unInitialize()
    {
        cancelFormat();
        if(mThread != null)
        {
            try
            {
                mThread.join();
            }
            catch(InterruptedException interruptedexception) { }
            mThread = null;
        }
        mContext.unregisterReceiver(mUsbReceiver);
    }

    public void unregisterHddFormatListener()
    {
        mListener = null;
    }

    private static final String TAG = "FAT32Formatter";
    private static Object mLock = new Object();
    private Context mContext;
    private List mDeviceList;
    private Handler mHandler;
    private HddFormatListener mListener;
    private String mPermission;
    private FormatState mState;
    private List mSupportedHddList;
    private Thread mThread;
    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent)
        {
            context = intent.getAction();
            if(!mPermission.equals(context))
                break MISSING_BLOCK_LABEL_134;
            this;
            JVM INSTR monitorenter ;
            context = (UsbDevice)intent.getParcelableExtra("device");
            if(!intent.getBooleanExtra("permission", false))
                break MISSING_BLOCK_LABEL_71;
            if(context == null)
                break MISSING_BLOCK_LABEL_124;
            intent = mHandler;
            context = JVM INSTR new #8   <Class HddFormatManager$1$1>;
            context.this. _cls1();
            intent.post(context);
            break MISSING_BLOCK_LABEL_124;
            intent = JVM INSTR new #65  <Class StringBuilder>;
            intent.StringBuilder();
            intent.append("permission denied for device ");
            intent.append(context);
            PxLog.d("FAT32Formatter", intent.toString());
            if(mListener != null)
                mListener.onResult(-1);
            this;
            JVM INSTR monitorexit ;
            break MISSING_BLOCK_LABEL_134;
            context;
            this;
            JVM INSTR monitorexit ;
            throw context;
        }

        final HddFormatManager this$0;

            
            {
                this$0 = HddFormatManager.this;
                super();
            }
    }
;










/*
    static FormatState access$602(HddFormatManager hddformatmanager, FormatState formatstate)
    {
        hddformatmanager.mState = formatstate;
        return formatstate;
    }

*/


    // Unreferenced inner class jp/pixela/common/HddFormatManager$1$1

/* anonymous class */
    class _cls1
        implements Runnable
    {

        public void run()
        {
            formatStorageAsFAT32();
        }

        final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
    }

}
