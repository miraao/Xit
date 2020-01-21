// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service;

import android.app.Activity;
import android.os.Bundle;
import jp.pixela.common.PxLog;

public class HomeLauncher extends Activity
{

    public HomeLauncher()
    {
    }

    public void onCreate(Bundle bundle)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("onCreate call: this=");
        stringbuilder.append(this);
        PxLog.d("HomeLauncher", stringbuilder.toString());
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(0x7f0a003b);
    }

    protected void onDestroy()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("onDestroy call: this=");
        stringbuilder.append(this);
        PxLog.d("HomeLauncher", stringbuilder.toString());
        super.onDestroy();
    }

    private static final String TAG = "HomeLauncher";
}
