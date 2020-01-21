// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;

import android.content.*;
import android.hardware.usb.*;
import java.util.*;
import jp.pixela.common.PxLog;

public class UsbDeviceObserver extends BroadcastReceiver
{
    public static interface UsbAudioEventListener
    {

        public abstract void onUsbAudioConnectionChanged(boolean flag);
    }


    public UsbDeviceObserver()
    {
        mUsbAudioEventListener = null;
        mRegistered = false;
    }

    public static boolean isConnectedUsbAudio(Context context)
    {
        context = (UsbManager)context.getSystemService("usb");
        if(context == null)
        {
            PxLog.w("UsbDeviceObserver", "UsbManager is null");
            return false;
        }
        context = context.getDeviceList().values().iterator();
        do
        {
            if(!context.hasNext())
                break;
            Object obj = context.next();
            if(obj instanceof UsbDevice)
            {
                obj = (UsbDevice)obj;
                int i = ((UsbDevice) (obj)).getDeviceClass();
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append("UsbDevice DeviceClass:");
                stringbuilder.append(i);
                PxLog.d("UsbDeviceObserver", stringbuilder.toString());
                if(i == 1)
                    return true;
                if(i == 0)
                {
                    int k = ((UsbDevice) (obj)).getInterfaceCount();
                    int j = 0;
                    while(j < k) 
                    {
                        Object obj1 = ((UsbDevice) (obj)).getInterface(j);
                        if(obj1 == null)
                        {
                            PxLog.i("UsbDeviceObserver", "UsbInterface is null");
                        } else
                        {
                            int l = ((UsbInterface) (obj1)).getInterfaceClass();
                            obj1 = new StringBuilder();
                            ((StringBuilder) (obj1)).append("UsbInterface DeviceClass:");
                            ((StringBuilder) (obj1)).append(l);
                            PxLog.d("UsbDeviceObserver", ((StringBuilder) (obj1)).toString());
                            if(l == 1)
                                return true;
                        }
                        j++;
                    }
                }
            }
        } while(true);
        return false;
    }

    public void onReceive(Context context, Intent intent)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("onReceive call: this=");
        stringbuilder.append(this);
        PxLog.d("UsbDeviceObserver", stringbuilder.toString());
        intent = intent.getAction();
        if(("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(intent) || "android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(intent)) && mUsbAudioEventListener != null)
        {
            boolean flag = isConnectedUsbAudio(context);
            context = new StringBuilder();
            context.append(intent);
            context.append(":");
            context.append(flag);
            PxLog.d("UsbDeviceObserver", context.toString());
            mUsbAudioEventListener.onUsbAudioConnectionChanged(flag);
        }
    }

    public void registerReceiver(Context context)
    {
        if(!mRegistered)
        {
            IntentFilter intentfilter = new IntentFilter();
            intentfilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
            intentfilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
            mRegistered = true;
            context.registerReceiver(this, intentfilter);
        }
    }

    public void setUsbAudioEventListener(UsbAudioEventListener usbaudioeventlistener)
    {
        mUsbAudioEventListener = usbaudioeventlistener;
    }

    public void unregisterReceiver(Context context)
    {
        if(mRegistered)
        {
            mRegistered = false;
            context.unregisterReceiver(this);
        }
    }

    private static final String TAG = "UsbDeviceObserver";
    private boolean mRegistered;
    private UsbAudioEventListener mUsbAudioEventListener;
}
