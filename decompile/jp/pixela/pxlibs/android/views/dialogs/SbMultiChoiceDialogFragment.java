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

public class SbMultiChoiceDialogFragment extends SbAlertDialogFragment
{
    public static class SbMultiChoiceDialogFragmentBuilder extends SbAlertDialogFragment.Builder
    {

        public boolean[] getChoiceItems()
        {
            return mChoiceItems;
        }

        public int getHeight()
        {
            return mHeight;
        }

        public String[] getItemNames()
        {
            return mItemNames;
        }

        public IMultiChoiceDialogInterface getOnMultiChoiceClickListener()
        {
            return mOnCheckboxClickListener;
        }

        public int getWidth()
        {
            return mWidth;
        }

        public void setMultiChoiceItems(String as[], boolean aflag[], IMultiChoiceDialogInterface imultichoicedialoginterface)
        {
            mItemNames = as;
            mChoiceItems = aflag;
            mOnCheckboxClickListener = imultichoicedialoginterface;
        }

        public void setSize(int i, int j)
        {
            mWidth = i;
            mHeight = j;
        }

        private boolean mChoiceItems[];
        private int mHeight;
        private String mItemNames[];
        private transient IMultiChoiceDialogInterface mOnCheckboxClickListener;
        private int mWidth;

        public SbMultiChoiceDialogFragmentBuilder(Context context)
        {
            super(context);
            mWidth = 0;
            mHeight = 0;
            mChoiceItems = null;
            mItemNames = null;
            mOnCheckboxClickListener = null;
        }

        public SbMultiChoiceDialogFragmentBuilder(Context context, int i)
        {
            super(context, i);
            mWidth = 0;
            mHeight = 0;
            mChoiceItems = null;
            mItemNames = null;
            mOnCheckboxClickListener = null;
        }
    }

    public static interface SbMultiChoiceDialogFragmentBuilder.IMultiChoiceDialogInterface
    {

        public abstract boolean onClick(DialogInterface dialoginterface, int i, boolean flag);
    }


    public SbMultiChoiceDialogFragment()
    {
    }

    private void createListView(final DialogInterface dialog, ListView listview, String as[], boolean aflag[], final SbMultiChoiceDialogFragmentBuilder.IMultiChoiceDialogInterface listener)
    {
        if(listview == null)
            return;
        as = new ArrayAdapter(getActivity(), jp.pixela.pxlibs.R.layout.adapter_multi_choice_list, 0x1020014, as) {

            public View getView(int l, View view, ViewGroup viewgroup)
            {
                view = super.getView(l, view, viewgroup);
                if(view != null)
                {
                    boolean flag = false;
                    if(viewgroup instanceof ListView)
                        flag = ((ListView)viewgroup).isItemChecked(l);
                    viewgroup = (CheckedTextView)view.findViewById(0x1020014);
                    if(viewgroup != null)
                        viewgroup.setChecked(flag);
                }
                return view;
            }

            final SbMultiChoiceDialogFragment this$0;

            
            {
                this$0 = SbMultiChoiceDialogFragment.this;
                super(context, i, j, as);
            }
        }
;
        listview.setChoiceMode(2);
        listview.setAdapter(as);
        int i = 0;
        listview.setFocusableInTouchMode(false);
        listview.setItemsCanFocus(false);
        int j = aflag.length;
        int k = 0;
        for(; i < j; i++)
        {
            listview.setItemChecked(k, aflag[i]);
            k++;
        }

        listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int l, long l1)
            {
                if((adapterview instanceof ListView) && view != null)
                {
                    boolean flag = ((ListView)adapterview).isItemChecked(l);
                    adapterview = (CheckedTextView)view.findViewById(0x1020014);
                    if(listener != null)
                    {
                        flag = listener.onClick(dialog, l, flag);
                        if(adapterview != null)
                            adapterview.setChecked(flag);
                    } else
                    if(adapterview != null)
                        adapterview.setChecked(flag);
                }
            }

            final SbMultiChoiceDialogFragment this$0;
            final DialogInterface val$dialog;
            final SbMultiChoiceDialogFragmentBuilder.IMultiChoiceDialogInterface val$listener;

            
            {
                this$0 = SbMultiChoiceDialogFragment.this;
                listener = imultichoicedialoginterface;
                dialog = dialoginterface;
                super();
            }
        }
);
    }

    private final SbAlertDialogFragment.Builder getBuilder()
    {
        Object obj = getArguments();
        if(obj == null)
            return null;
        SbAlertDialogFragment.Builder builder;
        try
        {
            builder = (SbAlertDialogFragment.Builder)((Bundle) (obj)).getSerializable("multi_choice_list_builder");
            obj = JVM INSTR new #109 <Class StringBuilder>;
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

    public static final SbAlertDialogFragment.IDialog show(Activity activity, SbMultiChoiceDialogFragmentBuilder sbmultichoicedialogfragmentbuilder)
    {
        return show(activity, sbmultichoicedialogfragmentbuilder, false);
    }

    public static final SbAlertDialogFragment.IDialog show(Activity activity, SbMultiChoiceDialogFragmentBuilder sbmultichoicedialogfragmentbuilder, boolean flag)
    {
        return show(activity, sbmultichoicedialogfragmentbuilder, flag, "dialog");
    }

    public static final SbAlertDialogFragment.IDialog show(Activity activity, SbMultiChoiceDialogFragmentBuilder sbmultichoicedialogfragmentbuilder, boolean flag, String s)
    {
        if(activity == null)
            throw new NullPointerException("Activity Object is null.");
        if(sbmultichoicedialogfragmentbuilder == null)
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
        ((StringBuilder) (obj)).append(sbmultichoicedialogfragmentbuilder);
        Logger.v(((StringBuilder) (obj)).toString(), new Object[0]);
        obj = new SbMultiChoiceDialogFragment();
        setFuncToGetCommonDialogKeyListener(sbmultichoicedialogfragmentbuilder.getContext());
        sbmultichoicedialogfragmentbuilder.setOnKeyListener(getCommonDialogKeyListener(sbmultichoicedialogfragmentbuilder.getOnKeyListener(), new jp.pixela.pxlibs.utils.IDelegate.IFunc(((SbMultiChoiceDialogFragment) (obj))) {

            public Activity invoke()
            {
                return newFragment.getActivity();
            }

            public volatile Object invoke()
            {
                return invoke();
            }

            final SbMultiChoiceDialogFragment val$newFragment;

            
            {
                newFragment = sbmultichoicedialogfragment;
                super();
            }
        }
));
        s.putSerializable("multi_choice_list_builder", sbmultichoicedialogfragmentbuilder);
        ((SbMultiChoiceDialogFragment) (obj)).setArguments(s);
        ((SbMultiChoiceDialogFragment) (obj)).setCancelable(sbmultichoicedialogfragmentbuilder.isCancelable());
        try
        {
            ((SbMultiChoiceDialogFragment) (obj)).show(fragmenttransaction, ((String) (activity)));
        }
        // Misplaced declaration of an exception variable
        catch(Activity activity)
        {
            s = new Throwable();
            sbmultichoicedialogfragmentbuilder = new StringBuilder();
            sbmultichoicedialogfragmentbuilder.append("catch IllegalStateException. e=");
            sbmultichoicedialogfragmentbuilder.append(activity.getMessage());
            Logger.d(s, sbmultichoicedialogfragmentbuilder.toString(), new Object[0]);
            return null;
        }
        return new SbAlertDialogFragment.IDialog(((SbMultiChoiceDialogFragment) (obj))) {

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

            final SbMultiChoiceDialogFragment val$newFragment;

            
            {
                newFragment = sbmultichoicedialogfragment;
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
        view = getActivity().getLayoutInflater().inflate(jp.pixela.pxlibs.R.layout.view_sb_multi_choice_dialog_fragment, null);
        builder.setView(view);
        builder.setCreateDialogAction(new jp.pixela.pxlibs.utils.IDelegate.IFunc() {

            public AlertDialog invoke()
            {
                AlertDialog alertdialog = (new android.app.AlertDialog.Builder(builder.getContext(), jp.pixela.pxlibs.R.style.pxlibs_TransparentDialogStyle)).create();
                if(alertdialog != null)
                {
                    ArrayList arraylist = new ArrayList();
                    Object obj = (TextView)view.findViewById(jp.pixela.pxlibs.R.id.text_dialog_title);
                    if(obj != null)
                        ((TextView) (obj)).setText(builder.getTitle());
                    SbMultiChoiceDialogFragmentBuilder sbmultichoicedialogfragmentbuilder = (SbMultiChoiceDialogFragmentBuilder)builder;
                    obj = (ListView)view.findViewById(jp.pixela.pxlibs.R.id.view_dialog_list);
                    createListView(alertdialog, ((ListView) (obj)), sbmultichoicedialogfragmentbuilder.getItemNames(), sbmultichoicedialogfragmentbuilder.getChoiceItems(), sbmultichoicedialogfragmentbuilder.getOnMultiChoiceClickListener());
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

            final SbMultiChoiceDialogFragment this$0;
            final SbAlertDialogFragment.Builder val$builder;
            final View val$view;

            
            {
                this$0 = SbMultiChoiceDialogFragment.this;
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
                View view2 = builder.getView();
                if(view2 != null)
                {
                    listener = view2.getLayoutParams();
                    if(listener != null)
                    {
                        listener.width = i;
                        view2.setLayoutParams(listener);
                    }
                }
                if(!builder.isAutoDismiss())
                {
                    int ai[] = new int[3];
                    int[] _tmp = ai;
                    ai[0] = -1;
                    ai[1] = -2;
                    ai[2] = -3;
                    for(int l = 0; l < ai.length; l++)
                    {
                        int j = ai[l];
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

            final SbMultiChoiceDialogFragment this$0;
            final SbAlertDialogFragment.Builder val$builder;
            final AlertDialog val$result;

            
            {
                this$0 = SbMultiChoiceDialogFragment.this;
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

    private static final String SERIAL_KEY_BUILDER = "multi_choice_list_builder";
    private final AtomicBoolean mIsShowing = new AtomicBoolean(false);


}
