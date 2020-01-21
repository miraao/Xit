// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service;

import android.app.Application;

// Referenced classes of package jp.pixela.player_service:
//            LifecycleHandler

public class App extends Application
{

    public App()
    {
        mLifecycleHandler = null;
    }

    public boolean isRunning(String s)
    {
        return mLifecycleHandler.isRunning(s);
    }

    public void onCreate()
    {
        super.onCreate();
        mLifecycleHandler = new LifecycleHandler();
        registerActivityLifecycleCallbacks(mLifecycleHandler);
    }

    public void onTerminate()
    {
        if(mLifecycleHandler != null)
        {
            unregisterActivityLifecycleCallbacks(mLifecycleHandler);
            mLifecycleHandler = null;
        }
        super.onTerminate();
    }

    private LifecycleHandler mLifecycleHandler;
}
