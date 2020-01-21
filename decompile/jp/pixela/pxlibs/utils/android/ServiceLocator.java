// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.utils.android;

import android.accounts.AccountManager;
import android.app.*;
import android.app.admin.DevicePolicyManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.hardware.SensorManager;
import android.hardware.display.DisplayManager;
import android.hardware.input.InputManager;
import android.hardware.usb.UsbManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.net.ConnectivityManager;
import android.net.nsd.NsdManager;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.nfc.NfcManager;
import android.os.*;
import android.os.storage.StorageManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textservice.TextServicesManager;

public final class ServiceLocator
{

    private ServiceLocator()
    {
    }

    public static final AccessibilityManager getAccessibilityManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (AccessibilityManager)context.getSystemService("accessibility");
    }

    public static final AccountManager getAccountManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (AccountManager)context.getSystemService("account");
    }

    public static final ActivityManager getActivityManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (ActivityManager)context.getSystemService("activity");
    }

    public static final AlarmManager getAlarmManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (AlarmManager)context.getSystemService("alarm");
    }

    public static final AudioManager getAudioManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (AudioManager)context.getSystemService("audio");
    }

    public static final ClipboardManager getClipboardManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (ClipboardManager)context.getSystemService("clipboard");
    }

    public static final ConnectivityManager getConnectivityManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (ConnectivityManager)context.getSystemService("connectivity");
    }

    public static final DevicePolicyManager getDevicePolicyManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (DevicePolicyManager)context.getSystemService("device_policy");
    }

    public static final DisplayManager getDisplayManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (DisplayManager)context.getSystemService("display");
    }

    public static final DownloadManager getDownloadManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (DownloadManager)context.getSystemService("download");
    }

    public static final DropBoxManager getDropBoxManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (DropBoxManager)context.getSystemService("dropbox");
    }

    public static final InputManager getInputManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (InputManager)context.getSystemService("input");
    }

    public static final InputMethodManager getInputMethodManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (InputMethodManager)context.getSystemService("input_method");
    }

    public static final KeyguardManager getKeyguardManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (KeyguardManager)context.getSystemService("keyguard");
    }

    public static final LayoutInflater getLayoutInflater(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (LayoutInflater)context.getSystemService("layout_inflater");
    }

    public static final LocationManager getLocationManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (LocationManager)context.getSystemService("location");
    }

    public static final MediaRouter getMediaRouter(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (MediaRouter)context.getSystemService("media_router");
    }

    public static final NfcManager getNfcManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (NfcManager)context.getSystemService("nfc");
    }

    public static final NotificationManager getNotificationManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (NotificationManager)context.getSystemService("notification");
    }

    public static final NsdManager getNsdManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (NsdManager)context.getSystemService("servicediscovery");
    }

    public static final PowerManager getPowerManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (PowerManager)context.getSystemService("power");
    }

    public static final SearchManager getSearchManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (SearchManager)context.getSystemService("search");
    }

    public static final SensorManager getSensorManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (SensorManager)context.getSystemService("sensor");
    }

    public static final StorageManager getStorageManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (StorageManager)context.getSystemService("storage");
    }

    public static final TelephonyManager getTelephonyManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (TelephonyManager)context.getSystemService("phone");
    }

    public static final TextServicesManager getTextServicesManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (TextServicesManager)context.getSystemService("textservices");
    }

    public static final UiModeManager getUiModeManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (UiModeManager)context.getSystemService("uimode");
    }

    public static final UsbManager getUsbManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (UsbManager)context.getSystemService("usb");
    }

    public static final UserManager getUserManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (UserManager)context.getSystemService("user");
    }

    public static final Vibrator getVibrator(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (Vibrator)context.getSystemService("vibrator");
    }

    public static final WallpaperManager getWallpaperManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        context = ((Context) (context.getSystemService("wallpaper")));
        if(!(context instanceof WallpaperManager))
            throw new NullPointerException("Object is not instance of WallpaperManager.");
        else
            return (WallpaperManager)context;
    }

    public static final WifiManager getWifiManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (WifiManager)context.getSystemService("wifi");
    }

    public static final WifiP2pManager getWifiP2pManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (WifiP2pManager)context.getSystemService("wifip2p");
    }

    public static final WindowManager getWindowManager(Context context)
    {
        if(context == null)
            throw new NullPointerException("Context Object is null.");
        else
            return (WindowManager)context.getSystemService("window");
    }
}
