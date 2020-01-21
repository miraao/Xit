// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.airtunerjni;

import android.content.Context;
import android.hardware.usb.*;
import android.util.Log;

// Referenced classes of package jp.pixela.airtunerjni:
//            AirTunerService

public class AccessUSB
{

    public AccessUSB()
    {
    }

    public static int init(UsbDevice usbdevice)
    {
        if(mManager == null)
        {
            Log.e("AccessUSB", "init(): setContext() is not called!!");
            return native_init(-1, -1, null);
        }
        mDevice = usbdevice;
        if(mDevice == null)
        {
            mInterface = null;
            mEndpoints = null;
            mCtrlConnection = null;
            return native_init(-1, -1, null);
        }
        if(mDevice.getInterfaceCount() < 1)
        {
            Log.e("AccessUSB", "onTargetDeviceFound(): no interface is found!!");
            return native_init(-1, -1, null);
        }
        mInterface = mDevice.getInterface(0);
        if(mInterface == null)
        {
            Log.e("AccessUSB", "onTargetDeviceFound(): mInterface is null");
            mDevice = null;
            return native_init(-1, -1, null);
        }
        mEndpoints = new UsbEndpoint[mInterface.getEndpointCount()];
        for(int i = 0; i < mEndpoints.length; i++)
            mEndpoints[i] = mInterface.getEndpoint(i);

        mCtrlConnection = mManager.openDevice(mDevice);
        if(mCtrlConnection == null)
        {
            Log.e("AccessUSB", "onTargetDeviceFound(): mCtrlConnection\u3000is null");
            mEndpoints = null;
            mInterface = null;
            return native_init(-1, -1, null);
        } else
        {
            mCtrlConnection.claimInterface(mInterface, true);
            return native_init(mCtrlConnection.getFileDescriptor(), 0, mEndpoints);
        }
    }

    private static native int native_init(int i, int j, UsbEndpoint ausbendpoint[]);

    private static native void native_notify_device_attach();

    private static native void native_notify_device_detach();

    private static native int native_prepare();

    private static native void native_term(int i);

    public static void notifyDeviceAttach()
    {
        native_notify_device_attach();
    }

    public static void notifyDeviceDetach()
    {
        native_notify_device_detach();
    }

    public static void requestOpenUSBDevice()
    {
        mService.requestOpenUSBDevice();
    }

    public static void setContext(AirTunerService airtunerservice)
    {
        mService = airtunerservice;
        mManager = (UsbManager)mService.getApplicationContext().getSystemService("usb");
        native_prepare();
    }

    public static void term(boolean flag)
    {
        native_term(flag);
        if(mCtrlConnection != null)
        {
            if(mInterface != null)
                mCtrlConnection.releaseInterface(mInterface);
            mCtrlConnection.close();
            mCtrlConnection = null;
        }
        if(mInterface != null)
            mInterface = null;
        if(mDevice != null)
            mDevice = null;
    }

    private static final String TAG = "AccessUSB";
    private static UsbDeviceConnection mCtrlConnection;
    private static UsbDevice mDevice;
    private static UsbEndpoint mEndpoints[];
    private static UsbInterface mInterface;
    private static UsbManager mManager;
    private static AirTunerService mService;

    static 
    {
        System.loadLibrary("airtuner");
    }
}
