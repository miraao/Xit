// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.android.views.dialogs.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.NumberPicker;
import java.lang.reflect.Field;

public class CustomNumberPicker extends NumberPicker
    implements android.view.View.OnFocusChangeListener
{

    public CustomNumberPicker(Context context)
    {
        super(context);
        initialize();
    }

    public CustomNumberPicker(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        initialize();
    }

    public CustomNumberPicker(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        initialize();
    }

    public CustomNumberPicker(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        initialize();
    }

    private void initialize()
    {
        setFocusable(true);
        setFocusableInTouchMode(true);
        setOnFocusChangeListener(this);
    }

    private void setDividerColor(NumberPicker numberpicker, boolean flag)
    {
        if(numberpicker == null)
            return;
        int i;
        if(flag)
            i = jp.pixela.pxlibs.R.color.divider_focus;
        else
            i = jp.pixela.pxlibs.R.color.divider_nml;
        Field afield[] = android/widget/NumberPicker.getDeclaredFields();
        int j = afield.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            Field field = afield[k];
            if(field.getName().equals("mSelectionDivider"))
            {
                field.setAccessible(true);
                try
                {
                    ColorDrawable colordrawable = JVM INSTR new #79  <Class ColorDrawable>;
                    colordrawable.ColorDrawable(numberpicker.getResources().getColor(i));
                    field.set(numberpicker, colordrawable);
                    numberpicker.invalidate();
                }
                // Misplaced declaration of an exception variable
                catch(NumberPicker numberpicker)
                {
                    numberpicker.printStackTrace();
                }
                // Misplaced declaration of an exception variable
                catch(NumberPicker numberpicker)
                {
                    numberpicker.printStackTrace();
                }
                // Misplaced declaration of an exception variable
                catch(NumberPicker numberpicker)
                {
                    numberpicker.printStackTrace();
                }
                // Misplaced declaration of an exception variable
                catch(NumberPicker numberpicker)
                {
                    numberpicker.printStackTrace();
                }
                break;
            }
            k++;
        } while(true);
    }

    public void onFocusChange(View view, boolean flag)
    {
        if(view != null && (view instanceof NumberPicker))
            setDividerColor((NumberPicker)view, flag);
    }
}
