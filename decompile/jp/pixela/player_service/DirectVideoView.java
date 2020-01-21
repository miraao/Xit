// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service;

import android.content.Context;
import android.graphics.Rect;
import android.view.*;
import jp.pixela.common.PxLog;
import jp.pixela.player_service.tunerservice.ControlInterface;

public class DirectVideoView extends SurfaceView
    implements android.view.SurfaceHolder.Callback
{
    public static class ViewInfo
    {

        public Rect GetInputCrop()
        {
            return mInputCrop;
        }

        public Rect GetOutputCrop()
        {
            return mOutputCrop;
        }

        public Rect GetRect()
        {
            return mRect;
        }

        public int GetRotation()
        {
            return mRotation;
        }

        private Rect mInputCrop;
        private Rect mOutputCrop;
        private Rect mRect;
        private int mRotation;

        public ViewInfo(Rect rect, Rect rect1, Rect rect2, int i)
        {
            mRect = rect;
            mInputCrop = rect1;
            mOutputCrop = rect2;
            mRotation = i;
        }
    }


    public DirectVideoView(Context context, int i, ControlInterface controlinterface, ViewInfo viewinfo)
    {
        super(context);
        mHolder = null;
        mVideoViewSurface = null;
        mSurfaceUsage = 0;
        mControlInterface = null;
        mSurfaceUsage = i;
        mControlInterface = controlinterface;
        mViewInfo = viewinfo;
        initView();
    }

    private native int DestroySurface(long l, int i);

    private native int SetSurfaceOutputPosition(long l, int i, Surface surface, int j, int k, int i1, 
            int j1, int k1, int l1, int i2, int j2);

    private void initView()
    {
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setFormat(-3);
    }

    public ViewInfo getmViewInfo()
    {
        return mViewInfo;
    }

    public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
    {
        surfaceholder = new StringBuilder();
        surfaceholder.append("surfaceChanged: id=");
        surfaceholder.append(mSurfaceUsage);
        surfaceholder.append(", hash=");
        surfaceholder.append(mVideoViewSurface.hashCode());
        surfaceholder.append(", width=");
        surfaceholder.append(j);
        surfaceholder.append(", height=");
        surfaceholder.append(k);
        PxLog.d("player_video_view", surfaceholder.toString());
    }

    public void surfaceCreated(SurfaceHolder surfaceholder)
    {
        mVideoViewSurface = surfaceholder.getSurface();
        Rect rect = mViewInfo.GetInputCrop();
        Rect rect1 = mViewInfo.GetRect();
        surfaceholder = mViewInfo.GetOutputCrop();
        int i = mViewInfo.GetRotation();
        if(mControlInterface != null)
            switch(mSurfaceUsage)
            {
            case 1: // '\001'
                mControlInterface.setVideoOutputPosition(1, mVideoViewSurface, rect, rect1, surfaceholder, i);
                mControlInterface.setVideoOutputPosition(2, mVideoViewSurface, rect, rect1, surfaceholder, i);
                mControlInterface.setVideoOutputPosition(3, mVideoViewSurface, rect, rect1, surfaceholder, i);
                break;

            case 0: // '\0'
                mControlInterface.setVideoOutputPosition(0, mVideoViewSurface, rect, rect1, surfaceholder, i);
                break;
            }
        surfaceholder = new StringBuilder();
        surfaceholder.append("surfaceCreated: id=");
        surfaceholder.append(mSurfaceUsage);
        surfaceholder.append(", hash=");
        surfaceholder.append(mVideoViewSurface.hashCode());
        PxLog.d("player_video_view", surfaceholder.toString());
    }

    public void surfaceDestroyed(SurfaceHolder surfaceholder)
    {
        surfaceholder = new StringBuilder();
        surfaceholder.append("surfaceDestroyed: id=");
        surfaceholder.append(mSurfaceUsage);
        surfaceholder.append(", hash=");
        surfaceholder.append(mVideoViewSurface.hashCode());
        PxLog.d("player_video_view", surfaceholder.toString());
        if(mControlInterface != null)
        {
            switch(mSurfaceUsage)
            {
            case 1: // '\001'
                mControlInterface.setVideoOutputPosition(1, null, null, null, null, 0);
                mControlInterface.setVideoOutputPosition(2, null, null, null, null, 0);
                mControlInterface.setVideoOutputPosition(3, null, null, null, null, 0);
                break;

            case 0: // '\0'
                mControlInterface.setVideoOutputPosition(0, null, null, null, null, 0);
                break;
            }
            mControlInterface = null;
        }
    }

    public void updateSurface(Rect rect, Rect rect1, Rect rect2, int i)
    {
        switch(mSurfaceUsage)
        {
        case 1: // '\001'
            mControlInterface.setVideoOutputPosition(1, mVideoViewSurface, rect, rect1, rect2, i);
            mControlInterface.setVideoOutputPosition(2, mVideoViewSurface, rect, rect1, rect2, i);
            mControlInterface.setVideoOutputPosition(3, mVideoViewSurface, rect, rect1, rect2, i);
            break;

        case 0: // '\0'
            mControlInterface.setVideoOutputPosition(0, mVideoViewSurface, rect, rect1, rect2, i);
            break;
        }
    }

    private static final String TAG = "player_video_view";
    ControlInterface mControlInterface;
    private SurfaceHolder mHolder;
    private int mSurfaceUsage;
    private Surface mVideoViewSurface;
    ViewInfo mViewInfo;
}
