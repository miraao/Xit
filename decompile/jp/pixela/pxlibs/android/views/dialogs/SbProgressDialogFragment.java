// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.android.views.dialogs;

import android.app.*;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import jp.pixela.pxlibs.android.views.buttons.PxButton;
import jp.pixela.pxlibs.android.views.dialogs.common.ViewButtonInfo;
import jp.pixela.pxlibs.utils.android.DisplayHelper;
import jp.pixela.pxlibs.utils.android.log.Logger;

// Referenced classes of package jp.pixela.pxlibs.android.views.dialogs:
//            SbAlertDialogFragment

public final class SbProgressDialogFragment extends SbAlertDialogFragment
{
    public static interface IDialog
        extends SbAlertDialogFragment.IDialog
    {

        public abstract CharSequence getMessage();

        public abstract void setMax(int i);

        public abstract void setMessage(CharSequence charsequence);

        public abstract void setProgress(int i);
    }


    public SbProgressDialogFragment()
    {
        mMessageTextView = null;
        mProgressBar = null;
        mProgressTextView = null;
        mProgressStyle = 0;
    }

    private final SbAlertDialogFragment.Builder getBuilder()
    {
        Object obj = getArguments();
        if(obj == null)
            return null;
        SbAlertDialogFragment.Builder builder;
        try
        {
            builder = (SbAlertDialogFragment.Builder)((Bundle) (obj)).getSerializable("progress_builder");
            obj = JVM INSTR new #81  <Class StringBuilder>;
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

    public static final IDialog show(Activity activity, SbAlertDialogFragment.Builder builder)
    {
        return show(activity, builder, false);
    }

    public static final IDialog show(Activity activity, SbAlertDialogFragment.Builder builder, boolean flag)
    {
        return show(activity, builder, flag, "dialog", 0);
    }

    public static final IDialog show(Activity activity, SbAlertDialogFragment.Builder builder, boolean flag, String s)
    {
        return show(activity, builder, flag, s, 0);
    }

    public static final IDialog show(Activity activity, SbAlertDialogFragment.Builder builder, boolean flag, String s, int i)
    {
        if(activity == null)
            throw new NullPointerException("Activity Object is null.");
        if(builder == null)
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
        ((StringBuilder) (obj)).append(builder);
        Logger.v(((StringBuilder) (obj)).toString(), new Object[0]);
        obj = new SbProgressDialogFragment();
        obj.mProgressStyle = i;
        setFuncToGetCommonDialogKeyListener(builder.getContext());
        builder.setOnKeyListener(getCommonDialogKeyListener(builder.getOnKeyListener(), new jp.pixela.pxlibs.utils.IDelegate.IFunc(((SbProgressDialogFragment) (obj))) {

            public Activity invoke()
            {
                return newFragment.getActivity();
            }

            public volatile Object invoke()
            {
                return invoke();
            }

            final SbProgressDialogFragment val$newFragment;

            
            {
                newFragment = sbprogressdialogfragment;
                super();
            }
        }
));
        s.putSerializable("progress_builder", builder);
        ((SbProgressDialogFragment) (obj)).setArguments(s);
        ((SbProgressDialogFragment) (obj)).setCancelable(builder.isCancelable());
        try
        {
            ((SbProgressDialogFragment) (obj)).show(fragmenttransaction, ((String) (activity)));
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            activity = new Throwable();
            builder = new StringBuilder();
            builder.append("catch IllegalStateException. e=");
            builder.append(s.getMessage());
            Logger.d(activity, builder.toString(), new Object[0]);
            return null;
        }
        ((SbProgressDialogFragment) (obj)).mIsShowing.set(true);
        return new IDialog(((SbProgressDialogFragment) (obj))) {

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

            public final CharSequence getMessage()
            {
                return newFragment.getMessage();
            }

            public final boolean isShowing()
            {
                return newFragment.mIsShowing.get();
            }

            public void setMax(int j)
            {
                if(newFragment.mProgressBar != null)
                {
                    newFragment.mProgressBar.setMax(j);
                    if(newFragment.mProgressTextView != null)
                    {
                        String s1 = String.format("%d/%d", new Object[] {
                            Integer.valueOf(newFragment.mProgressBar.getProgress()), Integer.valueOf(j)
                        });
                        newFragment.mProgressTextView.setText(s1);
                    }
                }
            }

            public final void setMessage(CharSequence charsequence)
            {
                newFragment.setMessage(charsequence);
            }

            public void setProgress(int j)
            {
                if(newFragment.mProgressBar != null)
                {
                    newFragment.mProgressBar.setProgress(j);
                    if(newFragment.mProgressTextView != null)
                    {
                        String s1 = String.format("%d/%d", new Object[] {
                            Integer.valueOf(j), Integer.valueOf(newFragment.mProgressBar.getMax())
                        });
                        newFragment.mProgressTextView.setText(s1);
                    }
                }
            }

            final SbProgressDialogFragment val$newFragment;

            
            {
                newFragment = sbprogressdialogfragment;
                super();
            }
        }
;
    }

    public final CharSequence getMessage()
    {
        if(mMessageTextView == null)
            return null;
        else
            return mMessageTextView.getText();
    }

    public final void onCancel(DialogInterface dialoginterface)
    {
        Object obj = getBuilder();
        if(obj != null)
            break MISSING_BLOCK_LABEL_33;
        Logger.v("builder == null", new Object[0]);
        super.onCancel(dialoginterface);
        mIsShowing.set(false);
        return;
        obj = ((SbAlertDialogFragment.Builder) (obj)).getOnCancelListener();
        if(obj == null)
        {
            super.onCancel(dialoginterface);
            mIsShowing.set(false);
            return;
        }
        ((android.content.DialogInterface.OnCancelListener) (obj)).onCancel(dialoginterface);
        super.onCancel(dialoginterface);
        mIsShowing.set(false);
        return;
        Exception exception;
        exception;
        super.onCancel(dialoginterface);
        mIsShowing.set(false);
        throw exception;
    }

    public final Dialog onCreateDialog(final Bundle view)
    {
        final SbAlertDialogFragment.Builder builder = getBuilder();
        if(builder == null)
        {
            Logger.v("builder == null", new Object[0]);
            return super.onCreateDialog(view);
        }
        int i;
        if(mProgressStyle == 0)
            i = jp.pixela.pxlibs.R.layout.view_sb_progress_dialog_fragment;
        else
            i = jp.pixela.pxlibs.R.layout.view_sb_progressbar_dialog_fragment;
        view = getActivity().getLayoutInflater().inflate(i, null);
        builder.setView(view);
        builder.setCreateDialogAction(new jp.pixela.pxlibs.utils.IDelegate.IFunc() {

            public AlertDialog invoke()
            {
                AlertDialog alertdialog = (new android.app.AlertDialog.Builder(builder.getContext(), jp.pixela.pxlibs.R.style.pxlibs_TransparentDialogStyle)).create();
                if(alertdialog != null)
                {
                    ArrayList arraylist = new ArrayList();
                    mMessageTextView = (TextView)view.findViewById(jp.pixela.pxlibs.R.id.text_dialog_message);
                    setMessage(builder.getMessage());
                    if(mProgressStyle == 1)
                    {
                        mProgressBar = (ProgressBar)view.findViewById(jp.pixela.pxlibs.R.id.progressBar);
                        mProgressTextView = (TextView)view.findViewById(jp.pixela.pxlibs.R.id.progress_text);
                    }
                    arraylist.add(new ViewButtonInfo((PxButton)view.findViewById(jp.pixela.pxlibs.R.id.button_dialog_negative), -2, builder.getNegativeText(), builder.getNegativeListener()));
                    arraylist.add(new ViewButtonInfo((PxButton)view.findViewById(jp.pixela.pxlibs.R.id.button_dialog_neutral), -3, builder.getNeutralText(), builder.getNeutralListener()));
                    arraylist.add(new ViewButtonInfo((PxButton)view.findViewById(jp.pixela.pxlibs.R.id.button_dialog_positive), -1, builder.getPositiveText(), builder.getPositiveListener()));
                    setCustomViewButton(alertdialog, builder, arraylist);
                }
                return alertdialog;
            }

            public volatile Object invoke()
            {
                return invoke();
            }

            final SbProgressDialogFragment this$0;
            final SbAlertDialogFragment.Builder val$builder;
            final View val$view;

            
            {
                this$0 = SbProgressDialogFragment.this;
                builder = builder1;
                view = view1;
                super();
            }
        }
);
        view = builder.getView();
        if(view != null)
        {
            ViewGroup viewgroup = (ViewGroup)view.getParent();
            if(viewgroup != null)
                viewgroup.removeView(view);
        }
        view = builder.create();
        view.setCanceledOnTouchOutside(builder.isCanceledOnTouchOutside());
        view.setOnShowListener(new android.content.DialogInterface.OnShowListener() {

            public final void onShow(DialogInterface dialoginterface)
            {
                int j = (DisplayHelper.getSize(getContext()).x * builder.getWidthRatio()) / 100;
                int l = (DisplayHelper.getSize(getContext()).y * builder.getHeightRatio()) / 100;
                final android.content.DialogInterface.OnClickListener listener = result.getWindow().getAttributes();
                listener.width = j;
                result.getWindow().setAttributes(listener);
                listener = builder.getView();
                if(listener != null)
                {
                    android.view.ViewGroup.LayoutParams layoutparams = listener.getLayoutParams();
                    if(layoutparams != null)
                    {
                        layoutparams.width = j;
                        layoutparams.height = l;
                        listener.setLayoutParams(layoutparams);
                    }
                }
                if(!builder.isAutoDismiss())
                {
                    int ai[] = new int[3];
                    int[] _tmp = ai;
                    ai[0] = -1;
                    ai[1] = -2;
                    ai[2] = -3;
                    for(int i1 = 0; i1 < ai.length; i1++)
                    {
                        int k = ai[i1];
                        switch(k)
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
                        Button button = result.getButton(k);
                        if(button != null)
                            button.setOnClickListener(k. new android.view.View.OnClickListener() {

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

            final SbProgressDialogFragment this$0;
            final SbAlertDialogFragment.Builder val$builder;
            final AlertDialog val$result;

            
            {
                this$0 = SbProgressDialogFragment.this;
                builder = builder1;
                result = alertdialog;
                super();
            }
        }
);
        if(builder.isShowKeyboard())
            view.getWindow().setSoftInputMode(5);
        return view;
    }

    public final void onDismiss(DialogInterface dialoginterface)
    {
        Object obj = getBuilder();
        if(obj != null)
            break MISSING_BLOCK_LABEL_33;
        Logger.v("builder == null", new Object[0]);
        super.onDismiss(dialoginterface);
        mIsShowing.set(false);
        return;
        obj = ((SbAlertDialogFragment.Builder) (obj)).getOnDismissListener();
        if(obj == null)
        {
            super.onDismiss(dialoginterface);
            mIsShowing.set(false);
            return;
        }
        ((android.content.DialogInterface.OnDismissListener) (obj)).onDismiss(dialoginterface);
        super.onDismiss(dialoginterface);
        mIsShowing.set(false);
        return;
        Exception exception;
        exception;
        super.onDismiss(dialoginterface);
        mIsShowing.set(false);
        throw exception;
    }

    public final void setMessage(CharSequence charsequence)
    {
        if(mMessageTextView != null)
            mMessageTextView.setText(charsequence);
    }

    private static final String SERIAL_KEY_BUILDER = "progress_builder";
    private final AtomicBoolean mIsShowing = new AtomicBoolean(false);
    private TextView mMessageTextView;
    private ProgressBar mProgressBar;
    private int mProgressStyle;
    private TextView mProgressTextView;




/*
    static ProgressBar access$102(SbProgressDialogFragment sbprogressdialogfragment, ProgressBar progressbar)
    {
        sbprogressdialogfragment.mProgressBar = progressbar;
        return progressbar;
    }

*/



/*
    static TextView access$202(SbProgressDialogFragment sbprogressdialogfragment, TextView textview)
    {
        sbprogressdialogfragment.mProgressTextView = textview;
        return textview;
    }

*/


/*
    static TextView access$302(SbProgressDialogFragment sbprogressdialogfragment, TextView textview)
    {
        sbprogressdialogfragment.mMessageTextView = textview;
        return textview;
    }

*/

}
