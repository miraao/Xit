// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.android.views.buttons;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.Button;

public class PxButton extends Button
{

    public PxButton(Context context)
    {
        super(context);
        initialize(context);
    }

    public PxButton(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        initialize(context);
    }

    public PxButton(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        initialize(context);
    }

    private void initialize(Context context)
    {
        if(context != null)
        {
            setBackgroundResource(jp.pixela.pxlibs.R.drawable.selector_px_button);
            if(android.os.Build.VERSION.SDK_INT < 23)
                context = context.getResources().getColorStateList(jp.pixela.pxlibs.R.color.selector_px_button_text);
            else
                context = context.getColorStateList(jp.pixela.pxlibs.R.color.selector_px_button_text);
            setTextColor(context);
            setFocusable(true);
            setFocusableInTouchMode(true);
            setAllCaps(false);
            setStateListAnimator(null);
        }
    }

    protected void onDetachedFromWindow()
    {
        setBackground(null);
        super.onDetachedFromWindow();
    }

    public void setType(Context context, int i)
    {
        if(i != 1)
        {
            setBackgroundResource(jp.pixela.pxlibs.R.drawable.selector_px_button);
            if(android.os.Build.VERSION.SDK_INT < 23)
                context = context.getResources().getColorStateList(jp.pixela.pxlibs.R.color.selector_px_button_text);
            else
                context = context.getColorStateList(jp.pixela.pxlibs.R.color.selector_px_button_text);
            setTextColor(context);
        } else
        {
            setBackgroundResource(jp.pixela.pxlibs.R.drawable.selector_px_button_system);
            if(android.os.Build.VERSION.SDK_INT < 23)
                context = context.getResources().getColorStateList(jp.pixela.pxlibs.R.color.selector_px_button_system_text);
            else
                context = context.getColorStateList(jp.pixela.pxlibs.R.color.selector_px_button_system_text);
            setTextColor(context);
        }
    }

    public static final int BUTTON_TYPE_DEFAULT = 0;
    public static final int BUTTON_TYPE_SYSTEM = 1;
}
