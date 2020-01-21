// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.android.views.dialogs;

import android.app.*;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import jp.pixela.pxlibs.android.views.dialogs.widget.CustomNumberPicker;
import jp.pixela.pxlibs.android.views.helper.KeyEventHelper;
import jp.pixela.pxlibs.utils.android.log.Logger;

// Referenced classes of package jp.pixela.pxlibs.android.views.dialogs:
//            SbAlertDialogFragment

public class SbNumberPickerDialogFragment extends SbAlertDialogFragment
{
    public static class SbNumberPickerDialogBuilder extends SbAlertDialogFragment.Builder
    {

        public int getDescendantFocusability()
        {
            return mFocusability;
        }

        public String[] getDisplayedValues()
        {
            return mDisplayValues;
        }

        public int getMaxValue()
        {
            return mMaxValue.intValue();
        }

        public int getMinValue()
        {
            return mMinValue.intValue();
        }

        public CustomNumberPicker getNumberPicker()
        {
            return mNumberPicker;
        }

        public OnNumberInputListener getOnNumberInputListener()
        {
            return mOnNumberInputListener;
        }

        public int getValue()
        {
            return mValue;
        }

        public void setDescendantFocusability(int i)
        {
            mFocusability = i;
        }

        public void setDisplayedValues(String as[])
        {
            mDisplayValues = as;
        }

        public void setMaxValue(int i)
        {
            mMaxValue = Integer.valueOf(i);
        }

        public void setMinValue(int i)
        {
            mMinValue = Integer.valueOf(i);
        }

        public void setNumberPicker(CustomNumberPicker customnumberpicker)
        {
            mNumberPicker = customnumberpicker;
        }

        public void setOnNumberInputListener(OnNumberInputListener onnumberinputlistener)
        {
            mOnNumberInputListener = onnumberinputlistener;
        }

        public void setValue(int i)
        {
            mValue = i;
        }

        private String mDisplayValues[];
        private int mFocusability;
        private Integer mMaxValue;
        private Integer mMinValue;
        private transient CustomNumberPicker mNumberPicker;
        private transient OnNumberInputListener mOnNumberInputListener;
        private int mValue;

        public SbNumberPickerDialogBuilder(Context context)
        {
            super(context);
            mDisplayValues = null;
            mValue = 0;
            mMaxValue = null;
            mMinValue = null;
            mFocusability = 0x60000;
            mNumberPicker = null;
            mOnNumberInputListener = null;
        }

        public SbNumberPickerDialogBuilder(Context context, int i)
        {
            super(context, i);
            mDisplayValues = null;
            mValue = 0;
            mMaxValue = null;
            mMinValue = null;
            mFocusability = 0x60000;
            mNumberPicker = null;
            mOnNumberInputListener = null;
        }
    }

    public static interface SbNumberPickerDialogBuilder.OnNumberInputListener
    {

        public abstract void onClick(DialogInterface dialoginterface, int i);
    }


    public SbNumberPickerDialogFragment()
    {
    }

    private final SbAlertDialogFragment.Builder getBuilder()
    {
        Object obj = getArguments();
        if(obj == null)
            return null;
        SbAlertDialogFragment.Builder builder;
        try
        {
            builder = (SbAlertDialogFragment.Builder)((Bundle) (obj)).getSerializable("numberpicker_builder");
            obj = JVM INSTR new #58  <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            ((StringBuilder) (obj)).append("builder=");
            ((StringBuilder) (obj)).append(builder);
            Logger.v(((StringBuilder) (obj)).toString(), new Object[0]);
        }
        catch(Exception exception)
        {
            Logger.w(exception);
            return null;
        }
        return builder;
    }

    public static final SbAlertDialogFragment.IDialog show(Activity activity, SbNumberPickerDialogBuilder sbnumberpickerdialogbuilder)
    {
        return show(activity, sbnumberpickerdialogbuilder, false);
    }

    public static final SbAlertDialogFragment.IDialog show(Activity activity, SbNumberPickerDialogBuilder sbnumberpickerdialogbuilder, boolean flag)
    {
        return show(activity, sbnumberpickerdialogbuilder, flag, "dialog");
    }

    public static final SbAlertDialogFragment.IDialog show(Activity activity, SbNumberPickerDialogBuilder sbnumberpickerdialogbuilder, boolean flag, String s)
    {
        if(activity == null)
            throw new NullPointerException("Activity Object is null.");
        if(sbnumberpickerdialogbuilder == null)
            throw new NullPointerException("Builder Object is null.");
        Object obj = activity.getFragmentManager();
        if(obj == null)
            throw new NullPointerException("FragmentManager Object is null.");
        FragmentTransaction fragmenttransaction = ((FragmentManager) (obj)).beginTransaction();
        if(fragmenttransaction == null)
            throw new NullPointerException("FragmentTransaction Object is null.");
        activity = s;
        if(s == null)
            activity = "dialog";
        s = ((FragmentManager) (obj)).findFragmentByTag(activity);
        if(s != null)
            fragmenttransaction.remove(s);
        if(flag)
            fragmenttransaction.addToBackStack(null);
        s = new Bundle();
        obj = new StringBuilder();
        ((StringBuilder) (obj)).append("builder=");
        ((StringBuilder) (obj)).append(sbnumberpickerdialogbuilder);
        Logger.v(((StringBuilder) (obj)).toString(), new Object[0]);
        obj = new SbNumberPickerDialogFragment();
        sbnumberpickerdialogbuilder.setOnKeyListener(new android.content.DialogInterface.OnKeyListener(sbnumberpickerdialogbuilder) {

            public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
            {
                KeyEventHelper.sendKeyEvent(builder.getContext(), keyevent);
                if(keyevent.getAction() == 0 && keyevent.getRepeatCount() == 0 && (i == 66 || i == 23))
                {
                    keyevent = builder.getNumberPicker();
                    if(keyevent != null)
                    {
                        if(builder.getOnNumberInputListener() != null)
                            builder.getOnNumberInputListener().onClick(dialoginterface, keyevent.getValue());
                        dialoginterface.dismiss();
                        return true;
                    }
                }
                return false;
            }

            final SbNumberPickerDialogBuilder val$builder;

            
            {
                builder = sbnumberpickerdialogbuilder;
                super();
            }
        }
);
        s.putSerializable("numberpicker_builder", sbnumberpickerdialogbuilder);
        ((SbNumberPickerDialogFragment) (obj)).setArguments(s);
        ((SbNumberPickerDialogFragment) (obj)).setCancelable(sbnumberpickerdialogbuilder.isCancelable());
        try
        {
            ((SbNumberPickerDialogFragment) (obj)).show(fragmenttransaction, ((String) (activity)));
        }
        // Misplaced declaration of an exception variable
        catch(Activity activity)
        {
            sbnumberpickerdialogbuilder = new Throwable();
            s = new StringBuilder();
            s.append("catch IllegalStateException. e=");
            s.append(activity.getMessage());
            Logger.d(sbnumberpickerdialogbuilder, s.toString(), new Object[0]);
            return null;
        }
        ((SbNumberPickerDialogFragment) (obj)).mIsShowing.set(true);
        return new SbAlertDialogFragment.IDialog(((SbNumberPickerDialogFragment) (obj))) {

            public final void cancel()
            {
                Dialog dialog = newFragment.getDialog();
                if(dialog == null)
                {
                    return;
                } else
                {
                    dialog.cancel();
                    return;
                }
            }

            public final void dismiss()
            {
                Dialog dialog = newFragment.getDialog();
                if(dialog == null)
                {
                    return;
                } else
                {
                    dialog.dismiss();
                    return;
                }
            }

            public final boolean isShowing()
            {
                return newFragment.mIsShowing.get();
            }

            final SbNumberPickerDialogFragment val$newFragment;

            
            {
                newFragment = sbnumberpickerdialogfragment;
                super();
            }
        }
;
    }

    public Dialog onCreateDialog(final Bundle view)
    {
        final SbAlertDialogFragment.Builder builder = getBuilder();
        if(builder == null)
        {
            Logger.v("builder == null", new Object[0]);
            return super.onCreateDialog(view);
        }
        view = getActivity().getLayoutInflater().inflate(jp.pixela.pxlibs.R.layout.view_sb_numberpicker_dialog_fragment, null);
        builder.setView(view);
        builder.setCreateDialogAction(new jp.pixela.pxlibs.utils.IDelegate.IFunc() {

            public AlertDialog invoke()
            {
                AlertDialog alertdialog = (new android.app.AlertDialog.Builder(builder.getContext(), jp.pixela.pxlibs.R.style.pxlibs_TransparentDialogStyle)).create();
                if(alertdialog != null)
                {
                    ArrayList arraylist = new ArrayList();
                    TextView textview = (TextView)view.findViewById(jp.pixela.pxlibs.R.id.text_dialog_title);
                    if(textview != null)
                        textview.setText(builder.getTitle());
                    setCustomViewButton(alertdialog, builder, arraylist);
                }
                return alertdialog;
            }

            public volatile Object invoke()
            {
                return invoke();
            }

            final SbNumberPickerDialogFragment this$0;
            final SbAlertDialogFragment.Builder val$builder;
            final View val$view;

            
            {
                this$0 = SbNumberPickerDialogFragment.this;
                builder = builder1;
                view = view1;
                super();
            }
        }
);
        Object obj = builder.getView();
        if(obj != null)
        {
            view = (ViewGroup)((View) (obj)).getParent();
            if(view != null)
                view.removeView(((View) (obj)));
        }
        view = builder.create();
        view.setCanceledOnTouchOutside(builder.isCanceledOnTouchOutside());
        view.setOnShowListener(new android.content.DialogInterface.OnShowListener() {

            public final void onShow(DialogInterface dialoginterface)
            {
                final android.content.DialogInterface.OnClickListener listener = ((WindowManager)builder.getContext().getSystemService("window")).getDefaultDisplay();
                android.view.WindowManager.LayoutParams layoutparams = result.getWindow().getAttributes();
                layoutparams.width = (int)((double)listener.getWidth() * 0.5D);
                result.getWindow().setAttributes(layoutparams);
                if(!builder.isAutoDismiss())
                {
                    int ai[] = new int[3];
                    int[] _tmp = ai;
                    ai[0] = -1;
                    ai[1] = -2;
                    ai[2] = -3;
                    for(int i = 0; i < ai.length; i++)
                    {
                        int j = ai[i];
                        switch(j)
                        {
                        default:
                            listener = null;
                            break;

                        case -1: 
                            listener = builder.getPositiveListener();
                            break;

                        case -2: 
                            listener = builder.getNegativeListener();
                            break;

                        case -3: 
                            listener = builder.getNeutralListener();
                            break;
                        }
                        if(listener == null)
                            continue;
                        Button button = result.getButton(j);
                        if(button != null)
                            button.setOnClickListener(j. new android.view.View.OnClickListener() {

                                public final void onClick(View view)
                                {
                                    if(listener != null)
                                        listener.onClick(result, buttonId);
                                }

                                final _cls4 this$1;
                                final int val$buttonId;
                                final android.content.DialogInterface.OnClickListener val$listener;

            
            {
                this$1 = final__pcls4;
                listener = onclicklistener;
                buttonId = I.this;
                super();
            }
                            }
);
                    }

                }
                listener = builder.getOnShowListener();
                if(listener != null)
                    listener.onShow(dialoginterface);
            }

            final SbNumberPickerDialogFragment this$0;
            final SbAlertDialogFragment.Builder val$builder;
            final AlertDialog val$result;

            
            {
                this$0 = SbNumberPickerDialogFragment.this;
                builder = builder1;
                result = alertdialog;
                super();
            }
        }
);
        if(builder.isShowKeyboard())
            view.getWindow().setSoftInputMode(5);
        if(obj != null)
        {
            obj = (CustomNumberPicker)((View) (obj)).findViewById(jp.pixela.pxlibs.R.id.number_picker);
            if(obj != null)
            {
                builder = (SbNumberPickerDialogBuilder)builder;
                builder.setNumberPicker(((CustomNumberPicker) (obj)));
                Integer integer = Integer.valueOf(builder.getMinValue());
                if(integer != null)
                    ((CustomNumberPicker) (obj)).setMinValue(integer.intValue());
                integer = Integer.valueOf(builder.getMaxValue());
                if(integer != null)
                    ((CustomNumberPicker) (obj)).setMaxValue(integer.intValue());
                ((CustomNumberPicker) (obj)).setValue(builder.getValue());
                ((CustomNumberPicker) (obj)).setDisplayedValues(builder.getDisplayedValues());
                ((CustomNumberPicker) (obj)).setDescendantFocusability(builder.getDescendantFocusability());
            }
        }
        return view;
    }

    private static final String SERIAL_KEY_BUILDER = "numberpicker_builder";
    private final AtomicBoolean mIsShowing = new AtomicBoolean(false);

}
