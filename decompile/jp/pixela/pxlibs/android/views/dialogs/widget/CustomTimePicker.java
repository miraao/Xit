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
import android.widget.TimePicker;
import java.lang.reflect.Field;

public class CustomTimePicker extends TimePicker
    implements android.view.View.OnFocusChangeListener
{

    public CustomTimePicker(Context context)
    {
        super(context);
        mHourNumberPicker = null;
        mMinuteNumberPicker = null;
        mAmpmNumberPicker = null;
        mFocusIndex = -1;
        initialize();
    }

    public CustomTimePicker(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mHourNumberPicker = null;
        mMinuteNumberPicker = null;
        mAmpmNumberPicker = null;
        mFocusIndex = -1;
        initialize();
    }

    public CustomTimePicker(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mHourNumberPicker = null;
        mMinuteNumberPicker = null;
        mAmpmNumberPicker = null;
        mFocusIndex = -1;
        initialize();
    }

    public CustomTimePicker(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mHourNumberPicker = null;
        mMinuteNumberPicker = null;
        mAmpmNumberPicker = null;
        mFocusIndex = -1;
        initialize();
    }

    private void initialize()
    {
        Resources resources = Resources.getSystem();
        int i = resources.getIdentifier("hour", "id", "android");
        int j = resources.getIdentifier("minute", "id", "android");
        int k = resources.getIdentifier("amPm", "id", "android");
        mHourNumberPicker = (NumberPicker)findViewById(i);
        mMinuteNumberPicker = (NumberPicker)findViewById(j);
        mAmpmNumberPicker = (NumberPicker)findViewById(k);
        if(mHourNumberPicker != null)
            mHourNumberPicker.setOnFocusChangeListener(this);
        if(mMinuteNumberPicker != null)
            mMinuteNumberPicker.setOnFocusChangeListener(this);
        if(mAmpmNumberPicker != null)
            mAmpmNumberPicker.setOnFocusChangeListener(this);
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
            if(view == mAmpmNumberPicker)
                mFocusIndex = 0;
            else
            if(view == mHourNumberPicker)
                mFocusIndex = 1;
            if(view == mMinuteNumberPicker)
                mFocusIndex = 2;
        }
        if(view != null && (view instanceof NumberPicker))
            setDividerColor((NumberPicker)view, flag);
    }

    public boolean onInputEnter()
    {
        if(mFocusIndex == 0)
        {
            mHourNumberPicker.requestFocus();
            return false;
        }
        if(mFocusIndex == 1)
        {
            mMinuteNumberPicker.requestFocus();
            return false;
        }
        return mFocusIndex != 2 ? true : true;
    }

    private NumberPicker mAmpmNumberPicker;
    private int mFocusIndex;
    private NumberPicker mHourNumberPicker;
    private NumberPicker mMinuteNumberPicker;
}
