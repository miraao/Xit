// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.atv_app;

import android.app.Activity;
import android.os.Bundle;
import java.util.HashMap;

public class LifecycleHandler
    implements android.app.Application.ActivityLifecycleCallbacks
{
    private static final class State extends Enum
    {

        public static State valueOf(String s)
        {
            return (State)Enum.valueOf(jp/pixela/atv_app/LifecycleHandler$State, s);
        }

        public static State[] values()
        {
            return (State[])$VALUES.clone();
        }

        private static final State $VALUES[];
        public static final State CREATED;
        public static final State PAUSED;
        public static final State RESUMED;
        public static final State STARTED;
        public static final State STOPPED;

        static 
        {
            CREATED = new State("CREATED", 0);
            STARTED = new State("STARTED", 1);
            RESUMED = new State("RESUMED", 2);
            PAUSED = new State("PAUSED", 3);
            STOPPED = new State("STOPPED", 4);
            $VALUES = (new State[] {
                CREATED, STARTED, RESUMED, PAUSED, STOPPED
            });
        }

        private State(String s, int i)
        {
            super(s, i);
        }
    }


    public LifecycleHandler()
    {
        mLifecycleList = new HashMap();
    }

    public boolean isExists(String s)
    {
        return mLifecycleList.containsKey(s);
    }

    public boolean isRunning(String s)
    {
        boolean flag = mLifecycleList.containsKey(s);
        boolean flag1 = false;
        if(!flag)
            return false;
        if((State)mLifecycleList.get(s) == State.RESUMED)
            flag1 = true;
        return flag1;
    }

    public void onActivityCreated(Activity activity, Bundle bundle)
    {
        activity = activity.getClass().getName();
        mLifecycleList.put(activity, State.CREATED);
    }

    public void onActivityDestroyed(Activity activity)
    {
        activity = activity.getClass().getName();
        mLifecycleList.remove(activity);
    }

    public void onActivityPaused(Activity activity)
    {
        activity = activity.getClass().getName();
        mLifecycleList.put(activity, State.PAUSED);
    }

    public void onActivityResumed(Activity activity)
    {
        activity = activity.getClass().getName();
        mLifecycleList.put(activity, State.RESUMED);
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle)
    {
    }

    public void onActivityStarted(Activity activity)
    {
        activity = activity.getClass().getName();
        mLifecycleList.put(activity, State.STARTED);
    }

    public void onActivityStopped(Activity activity)
    {
        activity = activity.getClass().getName();
        mLifecycleList.put(activity, State.STOPPED);
    }

    private HashMap mLifecycleList;
}
