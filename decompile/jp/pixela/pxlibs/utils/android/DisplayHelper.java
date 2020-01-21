// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.utils.android;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

// Referenced classes of package jp.pixela.pxlibs.utils.android:
//            ServiceLocator

public final class DisplayHelper
{

    private DisplayHelper()
    {
    }

    public static final Display getDefault(Context context)
    {
        if(context == null)
            return null;
        context = ServiceLocator.getWindowManager(context);
        if(context == null)
            return null;
        else
            return context.getDefaultDisplay();
    }

    public static final DisplayMetrics getMetrics(Context context)
    {
        if(context == null)
            return null;
        Display display = getDefault(context);
        if(display == null)
        {
            return null;
        } else
        {
            context = new DisplayMetrics();
            display.getMetrics(context);
            return context;
        }
    }

    public static final DisplayMetrics getRealMetrics(Context context)
    {
        if(context == null)
            return null;
        Display display = getDefault(context);
        if(display == null)
        {
            return null;
        } else
        {
            context = new DisplayMetrics();
            display.getRealMetrics(context);
            return context;
        }
    }

    public static final Rect getRealRect(Context context)
    {
        if(context == null)
            return null;
        context = getRealMetrics(context);
        if(context == null)
            return null;
        else
            return getRect(context);
    }

    public static final Point getRealSize(Context context)
    {
        if(context == null)
            return null;
        context = getRealMetrics(context);
        if(context == null)
            return null;
        else
            return getSize(context);
    }

    public static final Rect getRect(Context context)
    {
        if(context == null)
            return null;
        context = getMetrics(context);
        if(context == null)
            return null;
        else
            return getRect(((DisplayMetrics) (context)));
    }

    private static final Rect getRect(DisplayMetrics displaymetrics)
    {
        if(displaymetrics == null)
            return null;
        else
            return new Rect(0, 0, displaymetrics.widthPixels, displaymetrics.heightPixels);
    }

    public static final Point getSize(Context context)
    {
        if(context == null)
            return null;
        context = getMetrics(context);
        if(context == null)
            return null;
        else
            return getSize(((DisplayMetrics) (context)));
    }

    private static final Point getSize(DisplayMetrics displaymetrics)
    {
        if(displaymetrics == null)
            return null;
        else
            return new Point(displaymetrics.widthPixels, displaymetrics.heightPixels);
    }

    public static final boolean isLandscape(Context context)
    {
        boolean flag = false;
        if(context == null)
            return false;
        context = context.getApplicationContext();
        if(context == null)
            return false;
        context = context.getResources();
        if(context == null)
            return false;
        context = context.getConfiguration();
        if(context == null)
            return false;
        if(((Configuration) (context)).orientation == 2)
            flag = true;
        return flag;
    }

    public static final boolean isTablet(Context context, int i)
    {
        boolean flag = false;
        if(context == null)
            return false;
        context = context.getResources();
        if(context == null)
            return false;
        context = context.getConfiguration();
        if(context == null)
            return false;
        if((((Configuration) (context)).screenLayout & 0xf) >= i)
            flag = true;
        return flag;
    }

    public static final float toDp(Context context, float f)
    {
        if(context == null)
            return f;
        context = getMetrics(context);
        if(context == null)
            return f;
        float f1 = ((DisplayMetrics) (context)).density;
        if(f1 <= 0.0F)
            return f;
        else
            return f / f1;
    }

    public static final float toPx(Context context, float f)
    {
        if(context == null)
            return f;
        context = getMetrics(context);
        if(context == null)
            return f;
        float f1 = ((DisplayMetrics) (context)).density;
        if(f1 <= 0.0F)
            return f;
        else
            return f * f1;
    }
}
