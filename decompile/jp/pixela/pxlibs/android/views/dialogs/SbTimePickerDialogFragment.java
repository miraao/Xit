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
import jp.pixela.pxlibs.android.views.dialogs.widget.CustomTimePicker;
import jp.pixela.pxlibs.android.views.helper.KeyEventHelper;
import jp.pixela.pxlibs.utils.android.log.Logger;

// Referenced classes of package jp.pixela.pxlibs.android.views.dialogs:
//            SbAlertDialogFragment

public class SbTimePickerDialogFragment extends SbAlertDialogFragment
{
    public static class TimePickerDialogBuilder extends SbAlertDialogFragment.Builder
    {

        public int getHour()
        {
            return mHour;
        }

        public boolean getIs24HourView()
        {
            return mIs24HourView;
        }

        public int getMinute()
        {
            return mMinute;
        }

        public android.app.TimePickerDialog.OnTimeSetListener getOnTimeSetListener()
        {
            return mTimeSetListener;
        }

        public CustomTimePicker getTimePicker()
        {
            return mTimePicker;
        }

        public void setHour(int i)
        {
            mHour = i;
        }

        public void setIs24HourView(boolean flag)
        {
            mIs24HourView = flag;
        }

        public void setMinute(int i)
        {
            mMinute = i;
        }

        public void setOnTimeSetListener(android.app.TimePickerDialog.OnTimeSetListener ontimesetlistener)
        {
            mTimeSetListener = ontimesetlistener;
        }

        public void setTimePicker(CustomTimePicker customtimepicker)
        {
            mTimePicker = customtimepicker;
        }

        private int mHour;
        private boolean mIs24HourView;
        private int mMinute;
        private transient CustomTimePicker mTimePicker;
        private transient android.app.TimePickerDialog.OnTimeSetListener mTimeSetListener;

        public TimePickerDialogBuilder(Context context)
        {
            super(context);
            mIs24HourView = true;
            mHour = 0;
            mMinute = 0;
            mTimePicker = null;
            mTimeSetListener = null;
        }

        public TimePickerDialogBuilder(Context context, int i)
        {
            super(context, i);
            mIs24HourView = true;
            mHour = 0;
            mMinute = 0;
            mTimePicker = null;
            mTimeSetListener = null;
        }
    }


    public SbTimePickerDialogFragment()
    {
    }

    private final SbAlertDialogFragment.Builder getBuilder()
    {
        Object obj = getArguments();
        if(obj == null)
            return null;
        try
        {
            obj = (SbAlertDialogFragment.Builder)((Bundle) (obj)).getSerializable("timepicker_builder");
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

    public static final SbAlertDialogFragment.IDialog show(Activity activity, TimePickerDialogBuilder timepickerdialogbuilder)
    {
        return show(activity, timepickerdialogbuilder, false);
    }

    public static final SbAlertDialogFragment.IDialog show(Activity activity, TimePickerDialogBuilder timepickerdialogbuilder, boolean flag)
    {
        return show(activity, timepickerdialogbuilder, flag, "dialog");
    }

    public static final SbAlertDialogFragment.IDialog show(Activity activity, TimePickerDialogBuilder timepickerdialogbuilder, boolean flag, String s)
    {
        if(activity == null)
            throw new NullPointerException("Activity Object is null.");
        if(timepickerdialogbuilder == null)
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
        ((StringBuilder) (obj)).append(timepickerdialogbuilder);
        Logger.v(((StringBuilder) (obj)).toString(), new Object[0]);
        obj = new SbTimePickerDialogFragment();
        timepickerdialogbuilder.setOnKeyListener(new android.content.DialogInterface.OnKeyListener(timepickerdialogbuilder) {

            public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
            {
                KeyEventHelper.sendKeyEvent(builder.getContext(), keyevent);
                if(keyevent.getAction() == 0 && keyevent.getRepeatCount() == 0 && (i == 66 || i == 23))
                {
                    keyevent = builder.getTimePicker();
                    if(keyevent != null)
                    {
                        if(keyevent.onInputEnter() && builder.getOnTimeSetListener() != null)
                        {
                            builder.getOnTimeSetListener().onTimeSet(keyevent, keyevent.getHour(), keyevent.getMinute());
                            dialoginterface.dismiss();
                        }
                        return true;
                    }
                }
                return false;
            }

            final TimePickerDialogBuilder val$builder;

            
            {
                builder = timepickerdialogbuilder;
                super();
            }
        }
);
        s.putSerializable("timepicker_builder", timepickerdialogbuilder);
        ((SbTimePickerDialogFragment) (obj)).setArguments(s);
        ((SbTimePickerDialogFragment) (obj)).setCancelable(timepickerdialogbuilder.isCancelable());
        try
        {
            ((SbTimePickerDialogFragment) (obj)).show(fragmenttransaction, ((String) (activity)));
        }
        // Misplaced declaration of an exception variable
        catch(TimePickerDialogBuilder timepickerdialogbuilder)
        {
            activity = new Throwable();
            s = new StringBuilder();
            s.append("catch IllegalStateException. e=");
            s.append(timepickerdialogbuilder.getMessage());
            Logger.d(activity, s.toString(), new Object[0]);
            return null;
        }
        ((SbTimePickerDialogFragment) (obj)).mIsShowing.set(true);
        return new SbAlertDialogFragment.IDialog(((SbTimePickerDialogFragment) (obj))) {

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

            final SbTimePickerDialogFragment val$newFragment;

            
            {
                newFragment = sbtimepickerdialogfragment;
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
        view = getActivity().getLayoutInflater().inflate(jp.pixela.pxlibs.R.layout.view_sb_timepicker_dialog_fragment, null);
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

            final SbTimePickerDialogFragment this$0;
            final SbAlertDialogFragment.Builder val$builder;
            final View val$view;

            
            {
                this$0 = SbTimePickerDialogFragment.this;
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

            final SbTimePickerDialogFragment this$0;
            final SbAlertDialogFragment.Builder val$builder;
            final AlertDialog val$result;

            
            {
                this$0 = SbTimePickerDialogFragment.this;
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
            obj = (CustomTimePicker)((View) (obj)).findViewById(jp.pixela.pxlibs.R.id.time_picker);
            if(obj != null)
            {
                builder = (TimePickerDialogBuilder)builder;
                builder.setTimePicker(((CustomTimePicker) (obj)));
                ((CustomTimePicker) (obj)).setIs24HourView(Boolean.valueOf(builder.getIs24HourView()));
                ((CustomTimePicker) (obj)).setHour(builder.getHour());
                ((CustomTimePicker) (obj)).setMinute(builder.getMinute());
            }
        }
        return view;
    }

    private static final String SERIAL_KEY_BUILDER = "timepicker_builder";
    private final AtomicBoolean mIsShowing = new AtomicBoolean(false);

}
