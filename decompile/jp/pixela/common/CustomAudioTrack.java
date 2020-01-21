// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.common;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioTrack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.LinkedList;

// Referenced classes of package jp.pixela.common:
//            PxLog

public class CustomAudioTrack
{
    class QueueElement
    {

        ByteBuffer data;
        long pts;
        int size;
        final CustomAudioTrack this$0;

        QueueElement()
        {
            this$0 = CustomAudioTrack.this;
            super();
        }
    }


    public CustomAudioTrack()
    {
        mSampleRate = 0;
        mNumBytesQueued = 0;
        mQueue = new LinkedList();
        mStopped = false;
        PxLog.d(TAG, "CustomAudioTrack constructor call");
    }

    private long durationUsToFrames(long l, int i)
    {
        return (l * (long)i) / 0xf4240L;
    }

    public static int getAudioHwSyncForSession()
    {
        return sAudioHwSyncForSession;
    }

    public static void setContext(Context context)
    {
        PxLog.v(TAG, "setContext in");
        if(context == null)
        {
            PxLog.w(TAG, "setContext out. context == null");
            return;
        }
        mContext = context;
        mAudioManager = (AudioManager)mContext.getSystemService("audio");
        sAudioSessionId = mAudioManager.generateAudioSessionId();
        try
        {
            context = Class.forName("android.media.AudioSystem");
            sAudioHwSyncForSession = ((Integer)context.getMethod("getAudioHwSyncForSession", new Class[] {
                Integer.TYPE
            }).invoke(context, new Object[] {
                Integer.valueOf(sAudioSessionId)
            })).intValue();
        }
        catch(Exception exception)
        {
            context = TAG;
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("");
            stringbuilder.append(exception);
            PxLog.e(context, stringbuilder.toString());
        }
        String s = TAG;
        context = new StringBuilder();
        context.append("setContext out. audio session id = ");
        context.append(sAudioSessionId);
        context.append(", audio hw sync for session = ");
        context.append(sAudioHwSyncForSession);
        context.append(", ENABLE_TUNNEL_MODE=");
        context.append(true);
        PxLog.d(s, context.toString());
    }

    public void flush()
    {
        PxLog.v(TAG, "flush in");
        if(mAudioTrack == null)
        {
            PxLog.w(TAG, "flush out. mAudioTrack == null");
            return;
        }
        if(mAudioTrack.getState() != 1)
        {
            PxLog.i(TAG, "flush out. not initialized");
            return;
        }
        if(mAudioTrack.getPlayState() == 3)
        {
            PxLog.i(TAG, "flush out. playing");
            return;
        } else
        {
            mAudioTrack.flush();
            mQueue.clear();
            mNumBytesQueued = 0;
            mStopped = false;
            PxLog.v(TAG, "flush out");
            return;
        }
    }

    public long getAudioTimeUs()
    {
        if(mAudioTrack == null)
            return 0L;
        if(mAudioTrack.getState() != 1)
            return 0L;
        else
            return ((long)mAudioTrack.getPlaybackHeadPosition() * 0xf4240L) / (long)mSampleRate;
    }

    public int getNumBytesQueued()
    {
        return mNumBytesQueued;
    }

    public int getPlayState()
    {
        if(mAudioTrack == null)
            return 1;
        else
            return mAudioTrack.getPlayState();
    }

    public void init(int i, int j, boolean flag)
    {
        boolean flag1;
        int k = j;
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("init in. sampleRate=");
        stringbuilder.append(i);
        stringbuilder.append(", channelCount=");
        stringbuilder.append(k);
        stringbuilder.append(", hwAvSync=");
        stringbuilder.append(flag);
        stringbuilder.append(", ENABLE_HW_AV_SYNC=");
        flag1 = true;
        stringbuilder.append(true);
        PxLog.d(s, stringbuilder.toString());
        if(k != 6)
        {
            if(k != 8 && k != 14)
                switch(k)
                {
                default:
                    PxLog.w(TAG, "init out. unsupported channelCount");
                    throw new IllegalArgumentException();

                case 2: // '\002'
                    j = 12;
                    break;

                case 1: // '\001'
                    j = 4;
                    break;
                }
            else
                j = 6396;
        } else
        {
            j = 252;
        }
        int l = k;
        if(k == 14)
            l = 8;
        int i1 = l * 2;
        int j1 = AudioTrack.getMinBufferSize(i, j, 2);
        k = j1 * 4;
        l = (int)durationUsToFrames(0x3d090L, i) * i1;
        i1 = (int)Math.max(j1, durationUsToFrames(0xb71b0L, i) * (long)i1);
        if(k < l)
            k = l;
        else
        if(k > i1)
            k = i1;
        if(!flag)
            mAudioTrack = new AudioTrack(3, i, j, 2, k, 1);
        else
            mAudioTrack = new AudioTrack((new android.media.AudioAttributes.Builder()).setLegacyStreamType(3).setFlags(16).build(), (new android.media.AudioFormat.Builder()).setChannelMask(j).setEncoding(2).setSampleRate(i).build(), k, 1, sAudioSessionId);
        if(mAudioTrack != null && mAudioTrack.getState() != 1)
        {
            PxLog.w(TAG, "init out. CustomAudioTrack.init() failed");
            throw new IllegalStateException("CustomAudioTrack.init() failed.");
        }
        mSampleRate = i;
        Process process1;
        Object obj;
        process1 = Runtime.getRuntime().exec("getprop pix.halaudio.nocompat");
        BufferedReader bufferedreader = JVM INSTR new #300 <Class BufferedReader>;
        obj = JVM INSTR new #302 <Class InputStreamReader>;
        ((InputStreamReader) (obj)).InputStreamReader(process1.getInputStream());
        bufferedreader.BufferedReader(((java.io.Reader) (obj)));
        obj = bufferedreader.readLine();
        if(obj == null || obj == "")
            break MISSING_BLOCK_LABEL_479;
        if(Integer.parseInt(((String) (obj))) == 1)
            flag = flag1;
        else
            flag = false;
        mNoCompat = flag;
        process1.destroy();
        break MISSING_BLOCK_LABEL_530;
        Exception exception;
        exception;
        String s1 = TAG;
        StringBuilder stringbuilder1 = new StringBuilder();
        stringbuilder1.append("ex=");
        stringbuilder1.append(exception);
        PxLog.v(s1, stringbuilder1.toString());
        PxLog.v(TAG, "init out");
        return;
    }

    public void pause()
    {
        PxLog.v(TAG, "pause in");
        if(mAudioTrack == null)
        {
            PxLog.w(TAG, "pause out. mAudioTrack == null");
            return;
        }
        if(mAudioTrack.getState() != 1)
        {
            PxLog.i(TAG, "pause out. not initialized");
            return;
        } else
        {
            mAudioTrack.pause();
            PxLog.v(TAG, "pause out");
            return;
        }
    }

    public void play()
    {
        PxLog.v(TAG, "play in");
        if(mAudioTrack == null)
        {
            PxLog.w(TAG, "play out. mAudioTrack == null");
            return;
        }
        if(mAudioTrack.getState() != 1)
        {
            PxLog.i(TAG, "play out. not initialized");
            return;
        }
        mStopped = false;
        if(mAudioTrack.getPlayState() != 1)
            mAudioTrack.play();
        PxLog.v(TAG, "play out");
    }

    public void process()
    {
        PxLog.v(TAG, "process in");
        if(mAudioTrack == null)
        {
            PxLog.w(TAG, "process out. mAudioTrack == null");
            return;
        }
        if(mAudioTrack.getState() != 1)
        {
            PxLog.i(TAG, "process out. not initialized");
            return;
        }
        do
        {
            if(mQueue.isEmpty())
                break;
            QueueElement queueelement = (QueueElement)mQueue.peekFirst();
            if(sAudioSessionId != 0 && queueelement.data.position() == 0 && mLastDataWritten != 0)
            {
                int i;
                if(mNoCompat)
                    i = 48;
                else
                    i = 16;
                if(mAvSyncHeaderBytesRemaining == 0)
                {
                    mAvSyncHeader = ByteBuffer.allocate(i);
                    mAvSyncHeader.order(ByteOrder.BIG_ENDIAN);
                    mAvSyncHeader.putInt(0x55550001);
                    mAvSyncHeader.putInt(queueelement.size);
                    mAvSyncHeader.putLong(queueelement.pts);
                    mAvSyncHeader.position(0);
                    mAvSyncHeaderBytesRemaining = i;
                }
                i = mAudioTrack.write(mAvSyncHeader, mAvSyncHeaderBytesRemaining, 1);
                if(i < 0)
                {
                    PxLog.w(TAG, "process out. headerWritten < 0");
                    throw new RuntimeException("AudioTrack.write() failed.");
                }
                mAvSyncHeaderBytesRemaining -= i;
                if(mAvSyncHeaderBytesRemaining > 0)
                    continue;
            }
            mLastDataWritten = mAudioTrack.write(queueelement.data, queueelement.size, 1);
            if(mLastDataWritten < 0)
            {
                PxLog.w(TAG, "process out. mLastDataWritten < 0");
                throw new RuntimeException("AudioTrack.write() failed.");
            }
            mNumBytesQueued = mNumBytesQueued - mLastDataWritten;
            queueelement.size = queueelement.size - mLastDataWritten;
            if(queueelement.size != 0)
                break;
            mQueue.removeFirst();
        } while(true);
        if(mStopped)
        {
            mAvSyncHeader = null;
            mAvSyncHeaderBytesRemaining = 0;
            mLastDataWritten = -1;
            mAudioTrack.stop();
            mNumBytesQueued = 0;
            mStopped = false;
        } else
        if(mAudioTrack.getPlayState() == 1)
            mAudioTrack.play();
        PxLog.v(TAG, "process out");
    }

    public void release()
    {
        PxLog.v(TAG, "release in");
        if(mAudioTrack == null)
        {
            PxLog.w(TAG, "release out. mAudioTrack == null");
            return;
        } else
        {
            mAvSyncHeader = null;
            mAvSyncHeaderBytesRemaining = 0;
            mLastDataWritten = -1;
            mQueue.clear();
            mNumBytesQueued = 0;
            mAudioTrack.release();
            mAudioTrack = null;
            mStopped = false;
            PxLog.v(TAG, "release out");
            return;
        }
    }

    public void setMute(boolean flag)
    {
    }

    public void stop()
    {
        PxLog.v(TAG, "stop in");
        if(mAudioTrack == null)
        {
            PxLog.w(TAG, "stop out. mAudioTrack == null");
            return;
        }
        if(mAudioTrack.getState() != 1)
        {
            PxLog.i(TAG, "stop out. not initialized");
            return;
        }
        if(mQueue.isEmpty())
        {
            mAudioTrack.stop();
            mNumBytesQueued = 0;
        } else
        {
            mStopped = true;
        }
        PxLog.v(TAG, "stop out");
    }

    public void term()
    {
        PxLog.v(TAG, "term in");
        pause();
        flush();
        release();
        PxLog.v(TAG, "term out");
    }

    public void write(byte abyte0[], int i, long l)
    {
        PxLog.v(TAG, "write in");
        if(mAudioTrack == null)
        {
            PxLog.w(TAG, "write out. mAudioTrack == null");
            return;
        }
        if(mAudioTrack.getState() != 1)
        {
            PxLog.i(TAG, "write out. not initialized");
            return;
        }
        ByteBuffer bytebuffer = ByteBuffer.allocate(i);
        bytebuffer.put((byte[])abyte0.clone());
        bytebuffer.position(0);
        abyte0 = new QueueElement();
        abyte0.data = bytebuffer;
        abyte0.size = i;
        abyte0.pts = l;
        mNumBytesQueued = mNumBytesQueued + i;
        mQueue.add(abyte0);
        if(mAudioTrack.getPlayState() == 1 && mQueue.size() < 20)
        {
            PxLog.v(TAG, "write out. buffering");
            return;
        } else
        {
            process();
            PxLog.v(TAG, "write out");
            return;
        }
    }

    private static final int AUDIO_STREAM_TYPE = 3;
    private static final int BUFFER_MULTIPLICATION_FACTOR = 4;
    private static final boolean ENABLE_HW_AV_SYNC = true;
    private static final boolean ENABLE_TUNNEL_MODE = true;
    private static final long MAX_BUFFER_DURATION_US = 0xb71b0L;
    private static final long MIN_BUFFER_DURATION_US = 0x3d090L;
    private static final String TAG = "CustomAudioTrack";
    private static AudioManager mAudioManager;
    private static ByteBuffer mAvSyncHeader;
    private static int mAvSyncHeaderBytesRemaining = 0;
    private static Context mContext;
    private static int mLastDataWritten = -1;
    private static boolean mNoCompat = false;
    private static int sAudioHwSyncForSession;
    private static int sAudioSessionId;
    private AudioTrack mAudioTrack;
    private int mNumBytesQueued;
    private LinkedList mQueue;
    private int mSampleRate;
    private boolean mStopped;

}
