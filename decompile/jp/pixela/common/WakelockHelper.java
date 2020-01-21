// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.common;

import android.content.Context;
import android.os.PowerManager;

// Referenced classes of package jp.pixela.common:
//            PxLog

public class WakelockHelper
{

    private WakelockHelper()
    {
        mTag = "WakelockHelper";
        mWakeLock = null;
        mReferenceCount = 0;
        mContext = null;
        mlevelAndFlags = 0;
        mIsForceReleased = false;
    }

    public static WakelockHelper getInstance()
    {
        return sInstance;
    }

    public static android.os.PowerManager.WakeLock makeWakeLock(Context context, int i, String s)
    {
        if(context == null)
        {
            PxLog.e("WakelockHelper", "Context Is null");
            return null;
        }
        if(s == null)
        {
            PxLog.e("WakelockHelper", "tag Is null");
            return null;
        }
        context = (PowerManager)context.getSystemService("power");
        if(context == null)
        {
            PxLog.e("WakelockHelper", "[ERROR] PowerManager == null");
            return null;
        }
        context = context.newWakeLock(i, s);
        if(context == null)
        {
            PxLog.e("WakelockHelper", "[ERROR] WakeLock == null");
            return null;
        } else
        {
            return context;
        }
    }

    public static boolean releaseWakeLock(android.os.PowerManager.WakeLock wakelock)
    {
        if(wakelock == null)
        {
            PxLog.e("WakelockHelper", "wakeLock == NULL");
            return false;
        }
        if(!wakelock.isHeld())
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("releaseWakeLock: already all released. isHeld=");
            stringbuilder.append(wakelock.isHeld());
            PxLog.d("WakelockHelper", stringbuilder.toString());
            return false;
        }
        try
        {
            wakelock.release();
        }
        // Misplaced declaration of an exception variable
        catch(android.os.PowerManager.WakeLock wakelock)
        {
            PxLog.i("WakelockHelper", "failed to release");
            return false;
        }
        return true;
    }

    public void forceRelease()
    {
label0:
        {
            synchronized(sSyncLock)
            {
                if(!mIsForceReleased)
                    break label0;
                PxLog.i("WakelockHelper", "Already forceReleased not need to forceRelease.");
            }
            return;
        }
        if(mWakeLock != null)
            break MISSING_BLOCK_LABEL_40;
        PxLog.d("WakelockHelper", "sWakeLock == null, may be wakeLock() not called.");
        obj;
        JVM INSTR monitorexit ;
        return;
        if(mReferenceCount > 0)
            break MISSING_BLOCK_LABEL_86;
        StringBuilder stringbuilder = JVM INSTR new #91  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        stringbuilder.append("already all release. idHeid=");
        stringbuilder.append(mWakeLock.isHeld());
        PxLog.d("WakelockHelper", stringbuilder.toString());
        obj;
        JVM INSTR monitorexit ;
        return;
        int i = 0;
_L2:
        if(i >= mReferenceCount)
            break; /* Loop/switch isn't completed */
        releaseWakeLock(mWakeLock);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        mIsForceReleased = true;
        StringBuilder stringbuilder1 = JVM INSTR new #91  <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        stringbuilder1.append("forceRelease refCount=");
        stringbuilder1.append(mReferenceCount);
        stringbuilder1.append(" contextName:");
        stringbuilder1.append(mTag);
        stringbuilder1.append(" idHeid=");
        stringbuilder1.append(mWakeLock.isHeld());
        PxLog.i("WakelockHelper", stringbuilder1.toString());
        obj;
        JVM INSTR monitorexit ;
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void init(Context context, int i, String s)
    {
        if(mContext != null)
        {
            PxLog.e("WakelockHelper", "Duplicate init call");
            return;
        } else
        {
            mContext = context;
            mlevelAndFlags = i;
            mTag = s;
            mReferenceCount = 0;
            context = new StringBuilder();
            context.append("init refCount=");
            context.append(mReferenceCount);
            context.append(" context=");
            context.append(mContext);
            context.append(" levelAndFlags:");
            context.append(mlevelAndFlags);
            context.append(" Tag:");
            context.append(mTag);
            PxLog.i("WakelockHelper", context.toString());
            return;
        }
    }

    public void release()
    {
label0:
        {
            synchronized(sSyncLock)
            {
                if(mWakeLock != null)
                    break label0;
                PxLog.d("WakelockHelper", "sWakeLock == null, may be wakeLock() not called.");
            }
            return;
        }
        if(mReferenceCount > 0)
            break MISSING_BLOCK_LABEL_69;
        StringBuilder stringbuilder = JVM INSTR new #91  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        stringbuilder.append("already all release. idHeid=");
        stringbuilder.append(mWakeLock.isHeld());
        PxLog.d("WakelockHelper", stringbuilder.toString());
        obj;
        JVM INSTR monitorexit ;
        return;
        mReferenceCount = mReferenceCount - 1;
        if(mIsForceReleased)
            break MISSING_BLOCK_LABEL_97;
        releaseWakeLock(mWakeLock);
        StringBuilder stringbuilder1 = JVM INSTR new #91  <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        stringbuilder1.append("release refCount=");
        stringbuilder1.append(mReferenceCount);
        stringbuilder1.append(" contextName:");
        stringbuilder1.append(mTag);
        stringbuilder1.append(" idHeid=");
        stringbuilder1.append(mWakeLock.isHeld());
        PxLog.i("WakelockHelper", stringbuilder1.toString());
        obj;
        JVM INSTR monitorexit ;
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void resetReferenceCount()
    {
label0:
        {
            synchronized(sSyncLock)
            {
                if(mWakeLock != null)
                    break label0;
                PxLog.d("WakelockHelper", "sWakeLock == null, may be wakeLock() not called.");
            }
            return;
        }
        mReferenceCount = 0;
        StringBuilder stringbuilder = JVM INSTR new #91  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        stringbuilder.append("resetReferenceCount refCount=");
        stringbuilder.append(mReferenceCount);
        stringbuilder.append(" contextName:");
        stringbuilder.append(mTag);
        stringbuilder.append(" idHeid=");
        stringbuilder.append(mWakeLock.isHeld());
        PxLog.i("WakelockHelper", stringbuilder.toString());
        obj;
        JVM INSTR monitorexit ;
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void restoreWakelock()
    {
label0:
        {
            synchronized(sSyncLock)
            {
                if(mIsForceReleased)
                    break label0;
                PxLog.i("WakelockHelper", "Did not forceReleased, not to need to restoreWakelock.");
            }
            return;
        }
        if(mWakeLock != null)
            break MISSING_BLOCK_LABEL_40;
        PxLog.d("WakelockHelper", "sWakeLock == null, may be wakeLock() not called.");
        obj;
        JVM INSTR monitorexit ;
        return;
        int i = 0;
_L2:
        if(i >= mReferenceCount)
            break; /* Loop/switch isn't completed */
        mWakeLock.acquire();
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        mIsForceReleased = false;
        StringBuilder stringbuilder = JVM INSTR new #91  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        stringbuilder.append("restoreWakelock refCount=");
        stringbuilder.append(mReferenceCount);
        stringbuilder.append(" contextName:");
        stringbuilder.append(mTag);
        stringbuilder.append(" idHeid=");
        stringbuilder.append(mWakeLock.isHeld());
        PxLog.i("WakelockHelper", stringbuilder.toString());
        obj;
        JVM INSTR monitorexit ;
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void wakeLock()
    {
label0:
        {
            synchronized(sSyncLock)
            {
                if(mContext != null)
                    break label0;
                PxLog.e("WakelockHelper", "[ERROR] sContext == null, may be init() not called");
            }
            return;
        }
        if(mWakeLock != null)
            break MISSING_BLOCK_LABEL_66;
        mWakeLock = makeWakeLock(mContext, mlevelAndFlags, mTag);
        if(mWakeLock != null)
            break MISSING_BLOCK_LABEL_66;
        PxLog.e("WakelockHelper", "[ERROR] WakeLock == null");
        obj;
        JVM INSTR monitorexit ;
        return;
        if(mIsForceReleased)
            restoreWakelock();
        mWakeLock.acquire();
        mReferenceCount = mReferenceCount + 1;
        StringBuilder stringbuilder = JVM INSTR new #91  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        stringbuilder.append("wakeLock refCount=");
        stringbuilder.append(mReferenceCount);
        stringbuilder.append(" context=");
        stringbuilder.append(mContext);
        stringbuilder.append(" contextName:");
        stringbuilder.append(mTag);
        PxLog.i("WakelockHelper", stringbuilder.toString());
        obj;
        JVM INSTR monitorexit ;
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private static final String TAG = "WakelockHelper";
    private static final WakelockHelper sInstance = new WakelockHelper();
    private static final Object sSyncLock = new Object();
    private Context mContext;
    private boolean mIsForceReleased;
    private int mReferenceCount;
    private String mTag;
    private android.os.PowerManager.WakeLock mWakeLock;
    private int mlevelAndFlags;

}
