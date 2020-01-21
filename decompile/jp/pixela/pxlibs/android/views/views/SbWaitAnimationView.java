// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.android.views.views;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SbWaitAnimationView extends ImageView
{

    public SbWaitAnimationView(Context context)
    {
        super(context);
        mAnimation = null;
        initialize(context);
    }

    public SbWaitAnimationView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mAnimation = null;
        initialize(context);
    }

    public SbWaitAnimationView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mAnimation = null;
        initialize(context);
    }

    private void initialize(Context context)
    {
        if(context != null)
        {
            setBackgroundResource(jp.pixela.pxlibs.R.drawable.animation_sb_wait_animation_view);
            if(getBackground() instanceof AnimationDrawable)
            {
                mAnimation = (AnimationDrawable)getBackground();
                mAnimation.start();
            }
        }
    }

    protected void onDetachedFromWindow()
    {
        if(mAnimation != null)
            mAnimation.stop();
        setBackground(null);
        super.onDetachedFromWindow();
    }

    private AnimationDrawable mAnimation;
}
