// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.airtunerjni;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

// Referenced classes of package jp.pixela.airtunerjni:
//            ProcessWatchingService

public class DeviceAttachActivity extends Activity
{

    public DeviceAttachActivity()
    {
    }

    private void startProcessWatchingService()
    {
        if(startService(new Intent(getApplicationContext(), jp/pixela/airtunerjni/ProcessWatchingService)) == null)
            Log.e("DeviceAttachActivity", "Failed to start ProcessWatchingService.");
        else
            Log.e("DeviceAttachActivity", "Succeeded to start ProcessWatchingService.");
        finish();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        startProcessWatchingService();
    }

    private static final String TAG = "DeviceAttachActivity";
}
