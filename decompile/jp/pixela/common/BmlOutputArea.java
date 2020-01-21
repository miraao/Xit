// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.common;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.WindowManager;

public class BmlOutputArea
{

    public BmlOutputArea()
    {
        mLeftPercent = 0.0F;
        mTopPercent = 0.0F;
        mWidthPercent = 100F;
        mHeightPercent = 100F;
    }

    public Rect CalcBmlOutputRect(Activity activity)
    {
        Display display = activity.getWindowManager().getDefaultDisplay();
        activity = new Point(0, 0);
        display.getRealSize(activity);
        int i = (int)(((float)((Point) (activity)).x * mLeftPercent) / 100F);
        int j = (int)(((float)((Point) (activity)).y * mTopPercent) / 100F);
        int k = (int)(((float)((Point) (activity)).x * mWidthPercent) / 100F);
        int l = (int)(((float)((Point) (activity)).y * mHeightPercent) / 100F);
        float f = k;
        float f1 = l;
        float f2;
        float f5;
        if(f / f1 > 1.777778F)
        {
            f2 = (16F * f1) / 9F;
            float f3 = f1;
            f5 = f3;
        } else
        {
            f5 = (9F * f) / 16F;
            float f4 = f;
            f2 = f4;
        }
        l = i + (int)((f - f2) / 2.0F);
        j += (int)((f1 - f5) / 2.0F);
        return new Rect(l, j, (int)((float)l + f2), (int)((float)j + f5));
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("mLeftPercent:");
        stringbuilder.append(mLeftPercent);
        stringbuilder.append("% mTopPercent:");
        stringbuilder.append(mTopPercent);
        stringbuilder.append("% mWidthPercent:");
        stringbuilder.append(mWidthPercent);
        stringbuilder.append("% mHeightPercent:");
        stringbuilder.append(mHeightPercent);
        stringbuilder.append("%");
        return stringbuilder.toString();
    }

    public float mHeightPercent;
    public float mLeftPercent;
    public float mTopPercent;
    public float mWidthPercent;
}
