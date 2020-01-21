// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.atv_app;

import android.app.Application;

// Referenced classes of package jp.pixela.atv_app:
//            LifecycleHandler

public class App extends Application
{

    public App()
    {
        mLifecycleHandler = null;
    }

    public boolean isExistsActivity(String s)
    {
        return mLifecycleHandler.isExists(s);
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
