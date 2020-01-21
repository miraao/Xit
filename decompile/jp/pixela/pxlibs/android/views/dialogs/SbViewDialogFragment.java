// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.android.views.dialogs;

import android.app.*;
import android.content.Context;
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

public class SbViewDialogFragment extends SbAlertDialogFragment
{
    public static interface IDialog
        extends SbAlertDialogFragment.IDialog
    {

        public abstract void showDialogButton(boolean flag);
    }

    public static class SbViewDialogFragmentBuilder extends SbAlertDialogFragment.Builder
    {

        public View getCustomView()
        {
            return mCustomView;
        }

        public void setCustomView(View view)
        {
            mCustomView = view;
        }

        public boolean shouldShowDialogButton()
        {
            return mShowDialogButton;
        }

        public void showDialogButton(boolean flag)
        {
            mShowDialogButton = flag;
        }

        private transient View mCustomView;
        private boolean mShowDialogButton;

        public SbViewDialogFragmentBuilder(Context context)
        {
            super(context);
            mShowDialogButton = true;
            mCustomView = null;
        }

        public SbViewDialogFragmentBuilder(Context context, int i)
        {
            super(context, i);
            mShowDialogButton = true;
            mCustomView = null;
        }
    }


    public SbViewDialogFragment()
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
            builder = (SbAlertDialogFragment.Builder)((Bundle) (obj)).getSerializable("view_builder");
            obj = JVM INSTR new #66  <Class StringBuilder>;
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

    public static final IDialog show(Activity activity, SbViewDialogFragmentBuilder sbviewdialogfragmentbuilder)
    {
        return show(activity, sbviewdialogfragmentbuilder, false);
    }

    public static final IDialog show(Activity activity, SbViewDialogFragmentBuilder sbviewdialogfragmentbuilder, boolean flag)
    {
        return show(activity, sbviewdialogfragmentbuilder, flag, "dialog");
    }

    public static final IDialog show(Activity activity, SbViewDialogFragmentBuilder sbviewdialogfragmentbuilder, boolean flag, String s)
    {
        if(activity == null)
            throw new NullPointerException("Activity Object is null.");
        if(sbviewdialogfragmentbuilder == null)
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
        ((StringBuilder) (obj)).append(sbviewdialogfragmentbuilder);
        Logger.v(((StringBuilder) (obj)).toString(), new Object[0]);
        obj = new SbViewDialogFragment();
        setFuncToGetCommonDialogKeyListener(sbviewdialogfragmentbuilder.getContext());
        sbviewdialogfragmentbuilder.setOnKeyListener(getCommonDialogKeyListener(sbviewdialogfragmentbuilder.getOnKeyListener(), new jp.pixela.pxlibs.utils.IDelegate.IFunc(((SbViewDialogFragment) (obj))) {

            public Activity invoke()
            {
                return newFragment.getActivity();
            }

            public volatile Object invoke()
            {
                return invoke();
            }

            final SbViewDialogFragment val$newFragment;

            
            {
                newFragment = sbviewdialogfragment;
                super();
            }
        }
));
        s.putSerializable("view_builder", sbviewdialogfragmentbuilder);
        ((SbViewDialogFragment) (obj)).setArguments(s);
        ((SbViewDialogFragment) (obj)).setCancelable(sbviewdialogfragmentbuilder.isCancelable());
        try
        {
            ((SbViewDialogFragment) (obj)).show(fragmenttransaction, ((String) (activity)));
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            sbviewdialogfragmentbuilder = new Throwable();
            activity = new StringBuilder();
            activity.append("catch IllegalStateException. e=");
            activity.append(s.getMessage());
            Logger.d(sbviewdialogfragmentbuilder, activity.toString(), new Object[0]);
            return null;
        }
        return new IDialog(((SbViewDialogFragment) (obj))) {

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

            public final void showDialogButton(boolean flag1)
            {
                if(newFragment.mButtonBaseView != null)
                {
                    View view = newFragment.mButtonBaseView;
                    byte byte0;
                    if(flag1)
                        byte0 = 0;
                    else
                        byte0 = 8;
                    view.setVisibility(byte0);
                }
            }

            final SbViewDialogFragment val$newFragment;

            
            {
                newFragment = sbviewdialogfragment;
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
        view = getActivity().getLayoutInflater().inflate(jp.pixela.pxlibs.R.layout.view_sb_view_dialog_fragment, null);
        builder.setView(view);
        builder.setCreateDialogAction(new jp.pixela.pxlibs.utils.IDelegate.IFunc() {

            public AlertDialog invoke()
            {
                AlertDialog alertdialog = (new android.app.AlertDialog.Builder(builder.getContext(), jp.pixela.pxlibs.R.style.pxlibs_TransparentDialogStyle)).create();
                if(alertdialog != null)
                {
                    ArrayList arraylist = new ArrayList();
                    Object obj = builder.getTitle();
                    Object obj1 = (TextView)view.findViewById(jp.pixela.pxlibs.R.id.text_dialog_title);
                    if(obj1 != null)
                        if(obj == null)
                            ((TextView) (obj1)).setVisibility(8);
                        else
                            ((TextView) (obj1)).setText(((CharSequence) (obj)));
                    obj1 = view.findViewById(jp.pixela.pxlibs.R.id.image_dialog_separator);
                    if(obj1 != null && obj == null)
                        ((View) (obj1)).setVisibility(4);
                    obj = ((SbViewDialogFragmentBuilder)builder).getCustomView();
                    if(obj != null)
                    {
                        obj1 = (LinearLayout)view.findViewById(jp.pixela.pxlibs.R.id.dialog_content_view);
                        if(obj1 != null)
                            ((LinearLayout) (obj1)).addView(((View) (obj)));
                    }
                    mButtonBaseView = view.findViewById(jp.pixela.pxlibs.R.id.layout_dialog_button);
                    if(mButtonBaseView != null && !((SbViewDialogFragmentBuilder)builder).shouldShowDialogButton())
                        mButtonBaseView.setVisibility(8);
                    obj1 = (PxButton)view.findViewById(jp.pixela.pxlibs.R.id.button_dialog_negative);
                    arraylist.add(new ViewButtonInfo(((PxButton) (obj1)), -2, builder.getNegativeText(), builder.getNegativeListener()));
                    PxButton pxbutton = (PxButton)view.findViewById(jp.pixela.pxlibs.R.id.button_dialog_neutral);
                    arraylist.add(new ViewButtonInfo(pxbutton, -3, builder.getNeutralText(), builder.getNeutralListener()));
                    obj = (PxButton)view.findViewById(jp.pixela.pxlibs.R.id.button_dialog_positive);
                    arraylist.add(new ViewButtonInfo(((PxButton) (obj)), -1, builder.getPositiveText(), builder.getPositiveListener()));
                    if(builder.getButtonType() == 1)
                    {
                        ((PxButton) (obj)).setType(getContext(), 1);
                        ((PxButton) (obj1)).setType(getContext(), 1);
                        pxbutton.setType(getContext(), 1);
                    }
                    setCustomViewButton(alertdialog, builder, arraylist);
                }
                return alertdialog;
            }

            public volatile Object invoke()
            {
                return invoke();
            }

            final SbViewDialogFragment this$0;
            final SbAlertDialogFragment.Builder val$builder;
            final View val$view;

            
            {
                this$0 = SbViewDialogFragment.this;
                builder = builder1;
                view = view1;
                super();
            }
        }
);
        View view1 = builder.getView();
        if(view1 != null)
        {
            view = (ViewGroup)view1.getParent();
            if(view != null)
                view.removeView(view1);
        }
        view = builder.create();
        view.setCanceledOnTouchOutside(builder.isCanceledOnTouchOutside());
        view.setOnShowListener(new android.content.DialogInterface.OnShowListener() {

            public final void onShow(DialogInterface dialoginterface)
            {
                int i = (DisplayHelper.getSize(getContext()).x * builder.getWidthRatio()) / 100;
                int k = (DisplayHelper.getSize(getContext()).y * builder.getHeightRatio()) / 100;
                final android.content.DialogInterface.OnClickListener listener = result.getWindow().getAttributes();
                listener.width = i;
                result.getWindow().setAttributes(listener);
                listener = builder.getView();
                if(listener != null)
                {
                    android.view.ViewGroup.LayoutParams layoutparams = listener.getLayoutParams();
                    if(layoutparams != null)
                    {
                        layoutparams.width = i;
                        layoutparams.height = k;
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
                    for(int j = 0; j < ai.length; j++)
                    {
                        int l = ai[j];
                        switch(l)
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
                        Button button = result.getButton(l);
                        if(button != null)
                            button.setOnClickListener(l. new android.view.View.OnClickListener() {

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

            final SbViewDialogFragment this$0;
            final SbAlertDialogFragment.Builder val$builder;
            final AlertDialog val$result;

            
            {
                this$0 = SbViewDialogFragment.this;
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

    private static final String SERIAL_KEY_BUILDER = "view_builder";
    private View mButtonBaseView;
    private final AtomicBoolean mIsShowing = new AtomicBoolean(false);




/*
    static View access$102(SbViewDialogFragment sbviewdialogfragment, View view)
    {
        sbviewdialogfragment.mButtonBaseView = view;
        return view;
    }

*/
}
