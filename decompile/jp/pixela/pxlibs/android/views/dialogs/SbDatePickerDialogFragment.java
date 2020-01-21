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
import jp.pixela.pxlibs.android.views.dialogs.widget.CustomDatePicker;
import jp.pixela.pxlibs.android.views.helper.KeyEventHelper;
import jp.pixela.pxlibs.utils.android.log.Logger;

// Referenced classes of package jp.pixela.pxlibs.android.views.dialogs:
//            SbAlertDialogFragment

public class SbDatePickerDialogFragment extends SbAlertDialogFragment
{
    public static class DatePickerDialogBuilder extends SbAlertDialogFragment.Builder
    {

        public CustomDatePicker getDatePicker()
        {
            return mDatePicker;
        }

        public int getDay()
        {
            return mDay;
        }

        public long getMaxDate()
        {
            return mMaxDate;
        }

        public long getMinDate()
        {
            return mMinDate;
        }

        public int getMonth()
        {
            return mMonth;
        }

        public android.app.DatePickerDialog.OnDateSetListener getOnDateSetListener()
        {
            return mDateSetListener;
        }

        public int getYear()
        {
            return mYear;
        }

        public void setDatePicker(CustomDatePicker customdatepicker)
        {
            mDatePicker = customdatepicker;
        }

        public void setDay(int i)
        {
            mDay = i;
        }

        public void setMaxDate(long l)
        {
            mMaxDate = l;
        }

        public void setMinDate(long l)
        {
            mMinDate = l;
        }

        public void setMonth(int i)
        {
            mMonth = i;
        }

        public void setOnDateSetListener(android.app.DatePickerDialog.OnDateSetListener ondatesetlistener)
        {
            mDateSetListener = ondatesetlistener;
        }

        public void setYear(int i)
        {
            mYear = i;
        }

        private transient CustomDatePicker mDatePicker;
        private transient android.app.DatePickerDialog.OnDateSetListener mDateSetListener;
        private int mDay;
        private long mMaxDate;
        private long mMinDate;
        private int mMonth;
        private int mYear;

        public DatePickerDialogBuilder(Context context)
        {
            super(context);
            mYear = 0;
            mMonth = 0;
            mDay = 0;
            mMinDate = 0L;
            mMaxDate = 0L;
            mDatePicker = null;
            mDateSetListener = null;
        }

        public DatePickerDialogBuilder(Context context, int i)
        {
            super(context, i);
            mYear = 0;
            mMonth = 0;
            mDay = 0;
            mMinDate = 0L;
            mMaxDate = 0L;
            mDatePicker = null;
            mDateSetListener = null;
        }
    }


    public SbDatePickerDialogFragment()
    {
    }

    private final SbAlertDialogFragment.Builder getBuilder()
    {
        Object obj = getArguments();
        if(obj == null)
            return null;
        try
        {
            obj = (SbAlertDialogFragment.Builder)((Bundle) (obj)).getSerializable("datepicker_builder");
            StringBuilder stringbuilder = JVM INSTR new #55  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            stringbuilder.append("builder=");
            stringbuilder.append(obj);
            Logger.v(stringbuilder.toString(), new Object[0]);
        }
        catch(Exception exception)
        {
            Logger.w(exception);
            return null;
        }
        return ((SbAlertDialogFragment.Builder) (obj));
    }

    public static final SbAlertDialogFragment.IDialog show(Activity activity, DatePickerDialogBuilder datepickerdialogbuilder)
    {
        return show(activity, datepickerdialogbuilder, false);
    }

    public static final SbAlertDialogFragment.IDialog show(Activity activity, DatePickerDialogBuilder datepickerdialogbuilder, boolean flag)
    {
        return show(activity, datepickerdialogbuilder, flag, "dialog");
    }

    public static final SbAlertDialogFragment.IDialog show(Activity activity, DatePickerDialogBuilder datepickerdialogbuilder, boolean flag, String s)
    {
        if(activity == null)
            throw new NullPointerException("Activity Object is null.");
        if(datepickerdialogbuilder == null)
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
        ((StringBuilder) (obj)).append(datepickerdialogbuilder);
        Logger.v(((StringBuilder) (obj)).toString(), new Object[0]);
        obj = new SbDatePickerDialogFragment();
        datepickerdialogbuilder.setOnKeyListener(new android.content.DialogInterface.OnKeyListener(datepickerdialogbuilder) {

            public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
            {
                KeyEventHelper.sendKeyEvent(builder.getContext(), keyevent);
                if(keyevent.getAction() == 0 && keyevent.getRepeatCount() == 0 && (i == 66 || i == 23))
                {
                    keyevent = builder.getDatePicker();
                    if(keyevent != null)
                    {
                        if(keyevent.onInputEnter() && builder.getOnDateSetListener() != null)
                        {
                            builder.getOnDateSetListener().onDateSet(keyevent, keyevent.getYear(), keyevent.getMonth(), keyevent.getDayOfMonth());
                            dialoginterface.dismiss();
                        }
                        return true;
                    }
                }
                return false;
            }

            final DatePickerDialogBuilder val$builder;

            
            {
                builder = datepickerdialogbuilder;
                super();
            }
        }
);
        s.putSerializable("datepicker_builder", datepickerdialogbuilder);
        ((SbDatePickerDialogFragment) (obj)).setArguments(s);
        ((SbDatePickerDialogFragment) (obj)).setCancelable(datepickerdialogbuilder.isCancelable());
        try
        {
            ((SbDatePickerDialogFragment) (obj)).show(fragmenttransaction, ((String) (activity)));
        }
        // Misplaced declaration of an exception variable
        catch(Activity activity)
        {
            datepickerdialogbuilder = new Throwable();
            s = new StringBuilder();
            s.append("catch IllegalStateException. e=");
            s.append(activity.getMessage());
            Logger.d(datepickerdialogbuilder, s.toString(), new Object[0]);
            return null;
        }
        ((SbDatePickerDialogFragment) (obj)).mIsShowing.set(true);
        return new SbAlertDialogFragment.IDialog(((SbDatePickerDialogFragment) (obj))) {

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

            final SbDatePickerDialogFragment val$newFragment;

            
            {
                newFragment = sbdatepickerdialogfragment;
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
        view = getActivity().getLayoutInflater().inflate(jp.pixela.pxlibs.R.layout.view_sb_datepicker_dialog_fragment, null);
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

            final SbDatePickerDialogFragment this$0;
            final SbAlertDialogFragment.Builder val$builder;
            final View val$view;

            
            {
                this$0 = SbDatePickerDialogFragment.this;
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

            final SbDatePickerDialogFragment this$0;
            final SbAlertDialogFragment.Builder val$builder;
            final AlertDialog val$result;

            
            {
                this$0 = SbDatePickerDialogFragment.this;
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
            obj = (CustomDatePicker)((View) (obj)).findViewById(jp.pixela.pxlibs.R.id.date_picker);
            if(obj != null)
            {
                builder = (DatePickerDialogBuilder)builder;
                builder.setDatePicker(((CustomDatePicker) (obj)));
                long l = builder.getMinDate();
                if(l != 0L)
                    try
                    {
                        ((CustomDatePicker) (obj)).setMinDate(l);
                    }
                    catch(Exception exception1) { }
                l = builder.getMaxDate();
                if(l != 0L)
                    try
                    {
                        ((CustomDatePicker) (obj)).setMaxDate(l);
                    }
                    catch(Exception exception2) { }
                try
                {
                    ((CustomDatePicker) (obj)).updateDate(builder.getYear(), builder.getMonth(), builder.getDay());
                }
                catch(Exception exception) { }
            }
        }
        return view;
    }

    private static final String SERIAL_KEY_BUILDER = "datepicker_builder";
    private final AtomicBoolean mIsShowing = new AtomicBoolean(false);

}
