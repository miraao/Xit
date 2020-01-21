// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import java.io.File;
import java.io.IOException;
import jp.pixela.common.PxLog;
import jp.pixela.pxlibs.utils.android.log.LoggerRTM;

// Referenced classes of package jp.pixela.player_service.tunerservice:
//            ControlInterface

public class BmlSoundPlayer
    implements android.media.MediaPlayer.OnCompletionListener
{

    public BmlSoundPlayer(Context context, ControlInterface controlinterface)
    {
        TAG = jp/pixela/player_service/tunerservice/BmlSoundPlayer.getSimpleName();
        mSoundPool = null;
        mSoundIDs = null;
        mSoundIDIsLoaded = null;
        mSoundIDIsLoop = null;
        mStreamIDs = null;
        mMediaPlayer = null;
        mMediaPlayerSync = new Object();
        mPlayingFile = null;
        mFullsegSounds = 14;
        mFullsegSoundOffset = 100;
        loadCompleteListener = new android.media.SoundPool.OnLoadCompleteListener() {

            public void onLoadComplete(SoundPool soundpool, int i, int j)
            {
                PxLog.d(TAG, "LOCK START mSoundPool");
                soundpool = mSoundPool;
                soundpool;
                JVM INSTR monitorenter ;
                boolean flag;
                flag = false;
                j = 0;
_L2:
                if(j >= mSoundIDs.length)
                    break; /* Loop/switch isn't completed */
                if(!mSoundIDIsLoaded[j] || i != mSoundIDs[j])
                    break MISSING_BLOCK_LABEL_117;
                i = ((flag) ? 1 : 0);
                if(mSoundIDIsLoop[j])
                    i = -1;
                mStreamIDs[j] = mSoundPool.play(mSoundIDs[j], 1.0F, 1.0F, 0, i, 1.0F);
                break; /* Loop/switch isn't completed */
                j++;
                if(true) goto _L2; else goto _L1
_L1:
                soundpool;
                JVM INSTR monitorexit ;
                PxLog.d(TAG, "LOCK END mSoundPool");
                return;
                Exception exception;
                exception;
                soundpool;
                JVM INSTR monitorexit ;
                throw exception;
            }

            final BmlSoundPlayer this$0;

            
            {
                this$0 = BmlSoundPlayer.this;
                super();
            }
        }
;
        mContext = context;
        mControlInterface = controlinterface;
        mSoundIDs = new int[14];
        mSoundIDIsLoaded = new boolean[mSoundIDs.length];
        mSoundIDIsLoop = new boolean[mSoundIDs.length];
        mStreamIDs = new int[mSoundIDs.length];
        context = new android.media.AudioAttributes.Builder();
        context.setContentType(4);
        context.setUsage(1);
        context = context.build();
        controlinterface = new android.media.SoundPool.Builder();
        controlinterface.setMaxStreams(mSoundIDs.length);
        controlinterface.setAudioAttributes(context);
        mSoundPool = controlinterface.build();
        mSoundPool.setOnLoadCompleteListener(loadCompleteListener);
        synchronized(mMediaPlayerSync)
        {
            controlinterface = JVM INSTR new #128 <Class MediaPlayer>;
            controlinterface.MediaPlayer();
            mMediaPlayer = controlinterface;
            if(mMediaPlayer != null)
                mMediaPlayer.setOnCompletionListener(this);
        }
        return;
        controlinterface;
        context;
        JVM INSTR monitorexit ;
        throw controlinterface;
    }

    private void loadRomSound(int i)
    {
        Object obj;
        if(i >= mSoundIDs.length)
            break MISSING_BLOCK_LABEL_144;
        obj = JVM INSTR new #38  <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        ((StringBuilder) (obj)).append("raw/rom");
        ((StringBuilder) (obj)).append(String.format("%02d", new Object[] {
            Integer.valueOf(i)
        }));
        ((StringBuilder) (obj)).append(".ogg");
        obj = ((StringBuilder) (obj)).toString();
        obj = mContext.getAssets().openFd(((String) (obj)));
        if(obj != null)
            try
            {
                mSoundIDs[i] = mSoundPool.load(((AssetFileDescriptor) (obj)), 1);
                mSoundIDIsLoaded[i] = true;
                ((AssetFileDescriptor) (obj)).close();
            }
            catch(IOException ioexception)
            {
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append(LOG_HEAD);
                stringbuilder.append("e=");
                stringbuilder.append(ioexception);
                LoggerRTM.e(stringbuilder.toString(), new Object[0]);
            }
    }

    private void stopRomSound()
    {
        PxLog.d(TAG, "LOCK START mSoundPool");
        SoundPool soundpool = mSoundPool;
        soundpool;
        JVM INSTR monitorenter ;
        int i = 0;
_L2:
        if(i >= mSoundIDs.length)
            break; /* Loop/switch isn't completed */
        if(mStreamIDs[i] != 0)
        {
            mSoundIDIsLoop[i] = false;
            mSoundPool.stop(mStreamIDs[i]);
            mStreamIDs[i] = 0;
        }
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        soundpool;
        JVM INSTR monitorexit ;
        PxLog.d(TAG, "LOCK END mSoundPool");
        return;
        Exception exception;
        exception;
        soundpool;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void Finalize()
    {
        PxLog.d(TAG, "LOCK START mSoundPool");
        synchronized(mSoundPool)
        {
            mSoundPool.release();
        }
        PxLog.d(TAG, "LOCK END mSoundPool");
        PxLog.d(TAG, "LOCK START mMediaPlayer");
        synchronized(mMediaPlayerSync)
        {
            if(mMediaPlayer != null)
                mMediaPlayer.release();
            if(mPlayingFile != null)
            {
                mPlayingFile.delete();
                mPlayingFile = null;
            }
        }
        PxLog.d(TAG, "LOCK END mMediaPlayer");
        return;
        obj;
        obj1;
        JVM INSTR monitorexit ;
        throw obj;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void onCompletion(MediaPlayer mediaplayer)
    {
        PxLog.d(TAG, "LOCK START mMediaPlayer");
        synchronized(mMediaPlayerSync)
        {
            if(mMediaPlayer != null)
                mMediaPlayer.reset();
            if(mPlayingFile != null)
            {
                mPlayingFile.delete();
                mPlayingFile = null;
                mControlInterface.NotifyRamAudioStopped();
            }
        }
        PxLog.d(TAG, "LOCK END mMediaPlayer");
        return;
        mediaplayer;
        obj;
        JVM INSTR monitorexit ;
        throw mediaplayer;
    }

    public void setPlay(int i, int j, boolean flag, boolean flag1, String s)
    {
        boolean flag2;
        String s1 = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("notifySoundPlayCallback type=");
        stringbuilder.append(i);
        stringbuilder.append(", id=");
        stringbuilder.append(j);
        stringbuilder.append(", isStart=");
        stringbuilder.append(flag);
        stringbuilder.append(", loop=");
        stringbuilder.append(flag1);
        stringbuilder.append(", filePath=");
        stringbuilder.append(s);
        PxLog.d(s1, stringbuilder.toString());
        if(!flag || i != 0)
        {
            PxLog.d(TAG, "LOCK START mMediaPlayer");
            synchronized(mMediaPlayerSync)
            {
                if(mMediaPlayer != null)
                    mMediaPlayer.reset();
                if(mPlayingFile != null)
                {
                    mPlayingFile.delete();
                    mPlayingFile = null;
                }
            }
            PxLog.d(TAG, "LOCK END mMediaPlayer");
            if(i == 0)
                stopRomSound();
        }
        if(!flag)
            break MISSING_BLOCK_LABEL_483;
        flag2 = false;
        if(i == 0)
            break MISSING_BLOCK_LABEL_326;
        synchronized(mMediaPlayerSync)
        {
            if(mMediaPlayer != null)
            {
                mMediaPlayer.setDataSource(s);
                mMediaPlayer.prepare();
                mMediaPlayer.setLooping(flag1);
                mMediaPlayer.start();
            }
            File file = JVM INSTR new #220 <Class File>;
            file.File(s);
            mPlayingFile = file;
        }
        break MISSING_BLOCK_LABEL_483;
        s;
        obj;
        JVM INSTR monitorexit ;
        try
        {
            throw s;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            obj = new StringBuilder();
        }
        ((StringBuilder) (obj)).append(LOG_HEAD);
        ((StringBuilder) (obj)).append("e=");
        ((StringBuilder) (obj)).append(s);
        LoggerRTM.e(((StringBuilder) (obj)).toString(), new Object[0]);
        break MISSING_BLOCK_LABEL_483;
        i = j;
        if(j >= 100)
            i = j - 100;
        if(i < 0 || i >= mSoundIDs.length)
            break MISSING_BLOCK_LABEL_483;
        PxLog.d(TAG, "LOCK START mSoundPool");
        s = mSoundPool;
        s;
        JVM INSTR monitorenter ;
        if(mStreamIDs[i] != 0)
            mSoundPool.stop(mStreamIDs[i]);
        if(!flag1)
            break MISSING_BLOCK_LABEL_405;
        mSoundIDIsLoop[i] = flag1;
        if(!mSoundIDIsLoaded[i])
        {
            loadRomSound(i);
            break MISSING_BLOCK_LABEL_460;
        }
        j = ((flag2) ? 1 : 0);
        if(mSoundIDIsLoop[i])
            j = -1;
        mStreamIDs[i] = mSoundPool.play(mSoundIDs[i], 1.0F, 1.0F, 0, j, 1.0F);
        s;
        JVM INSTR monitorexit ;
        PxLog.d(TAG, "LOCK END mSoundPool");
        break MISSING_BLOCK_LABEL_483;
        obj;
        s;
        JVM INSTR monitorexit ;
        throw obj;
        return;
        s;
        obj;
        JVM INSTR monitorexit ;
        throw s;
    }

    private static final String LOG_HEAD;
    private final String TAG;
    private android.media.SoundPool.OnLoadCompleteListener loadCompleteListener;
    private Context mContext;
    ControlInterface mControlInterface;
    private final int mFullsegSoundOffset;
    private final int mFullsegSounds;
    private MediaPlayer mMediaPlayer;
    private Object mMediaPlayerSync;
    private File mPlayingFile;
    private boolean mSoundIDIsLoaded[];
    private boolean mSoundIDIsLoop[];
    private int mSoundIDs[];
    private SoundPool mSoundPool;
    private int mStreamIDs[];

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(jp/pixela/player_service/tunerservice/BmlSoundPlayer.getSimpleName());
        stringbuilder.append(" ");
        LOG_HEAD = stringbuilder.toString();
    }






}
