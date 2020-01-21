// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.android.views.activities;

import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.os.IBinder;
import android.view.KeyEvent;
import jp.pixela.pxlibs.android.views.helper.KeyEventHelper;

public class SbActivity extends Activity
{

    public SbActivity()
    {
    }

    private boolean bindService()
    {
        boolean flag;
        synchronized(mMutex)
        {
            Object obj1 = JVM INSTR new #6   <Class SbActivity$1>;
            ((_cls1) (obj1)).this. _cls1();
            mConnection = ((ServiceConnection) (obj1));
            obj1 = JVM INSTR new #31  <Class Intent>;
            StringBuilder stringbuilder = JVM INSTR new #33  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            stringbuilder.append("jp.pixela.pxeventmanagerservice");
            stringbuilder.append(".service.IPxEventManagerService");
            ((Intent) (obj1)).Intent(stringbuilder.toString());
            ((Intent) (obj1)).setPackage("jp.pixela.pxeventmanagerservice");
            flag = bindService(((Intent) (obj1)), mConnection, 1);
        }
        return flag;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void unbindService()
    {
        synchronized(mMutex)
        {
            if(mConnection != null)
            {
                unbindService(mConnection);
                mConnection = null;
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        pxDispatchKeyEvent(keyevent, true);
        return pxDispatchKeyEvent(keyevent, false);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        bindService();
    }

    protected void onDestroy()
    {
        unbindService();
        super.onDestroy();
    }

    protected void onPause()
    {
        super.onPause();
    }

    protected void onResume()
    {
        super.onResume();
    }

    protected void onStart()
    {
        super.onStart();
    }

    protected void onStop()
    {
        super.onStop();
    }

    protected boolean pxDispatchKeyEvent(KeyEvent keyevent, boolean flag)
    {
        if(flag)
        {
            KeyEventHelper.sendKeyEvent(getApplicationContext(), keyevent);
            return true;
        } else
        {
            return super.dispatchKeyEvent(keyevent);
        }
    }

    private ServiceConnection mConnection;
    private final Object mMutex = new Object();


    // Unreferenced inner class jp/pixela/pxlibs/android/views/activities/SbActivity$1

/* anonymous class */
    class _cls1
        implements ServiceConnection
    {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            synchronized(mMutex) { }
            return;
            componentname;
            ibinder;
            JVM INSTR monitorexit ;
            throw componentname;
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            synchronized(mMutex) { }
            return;
            exception;
            componentname;
            JVM INSTR monitorexit ;
            throw exception;
        }

        final SbActivity this$0;

            
            {
                this$0 = SbActivity.this;
                super();
            }
    }

}
