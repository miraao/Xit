// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.android.views.dialogs.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import java.lang.reflect.Field;

public class CustomDatePicker extends DatePicker
    implements android.view.View.OnFocusChangeListener
{

    public CustomDatePicker(Context context)
    {
        super(context);
        mYearNumberPicker = null;
        mMonthNumberPicker = null;
        mDayNumberPicker = null;
        mFocusIndex = -1;
        initialize();
    }

    public CustomDatePicker(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mYearNumberPicker = null;
        mMonthNumberPicker = null;
        mDayNumberPicker = null;
        mFocusIndex = -1;
        initialize();
    }

    public CustomDatePicker(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mYearNumberPicker = null;
        mMonthNumberPicker = null;
        mDayNumberPicker = null;
        mFocusIndex = -1;
        initialize();
    }

    public CustomDatePicker(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mYearNumberPicker = null;
        mMonthNumberPicker = null;
        mDayNumberPicker = null;
        mFocusIndex = -1;
        initialize();
    }

    private void initialize()
    {
        Resources resources = Resources.getSystem();
        int i = resources.getIdentifier("year", "id", "android");
        int j = resources.getIdentifier("month", "id", "android");
        int k = resources.getIdentifier("day", "id", "android");
        mYearNumberPicker = (NumberPicker)findViewById(i);
        mMonthNumberPicker = (NumberPicker)findViewById(j);
        mDayNumberPicker = (NumberPicker)findViewById(k);
        if(mYearNumberPicker != null)
            mYearNumberPicker.setOnFocusChangeListener(this);
        if(mMonthNumberPicker != null)
            mMonthNumberPicker.setOnFocusChangeListener(this);
        if(mDayNumberPicker != null)
            mDayNumberPicker.setOnFocusChangeListener(this);
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
                    ColorDrawable colordrawable = JVM INSTR new #112 <Class ColorDrawable>;
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
        if(flag)
        {
            if(view == mYearNumberPicker)
                mFocusIndex = 0;
            else
            if(view == mMonthNumberPicker)
                mFocusIndex = 1;
            if(view == mDayNumberPicker)
                mFocusIndex = 2;
        }
        if(view != null && view != null && (view instanceof NumberPicker))
            setDividerColor((NumberPicker)view, flag);
    }

    public boolean onInputEnter()
    {
        if(mFocusIndex == 0)
        {
            mMonthNumberPicker.requestFocus();
            return false;
        }
        if(mFocusIndex == 1)
        {
            mDayNumberPicker.requestFocus();
            return false;
        }
        return mFocusIndex != 2 ? true : true;
    }

    private NumberPicker mDayNumberPicker;
    private int mFocusIndex;
    private NumberPicker mMonthNumberPicker;
    private NumberPicker mYearNumberPicker;
}
