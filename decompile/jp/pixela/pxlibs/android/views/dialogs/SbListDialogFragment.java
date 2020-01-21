// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.android.views.dialogs;

import android.app.*;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.*;
import android.widget.ListView;
import android.widget.TextView;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import jp.pixela.pxlibs.android.views.dialogs.widget.SbListAdapter;
import jp.pixela.pxlibs.android.views.helper.KeyEventHelper;
import jp.pixela.pxlibs.utils.android.DisplayHelper;
import jp.pixela.pxlibs.utils.android.log.Logger;

public class SbListDialogFragment extends DialogFragment
{
    public static class Builder extends android.app.AlertDialog.Builder
        implements Serializable
    {

        public final AlertDialog create()
        {
            Object obj = getCreateDialogAction();
            if(obj == null)
                return super.create();
            obj = (AlertDialog)((jp.pixela.pxlibs.utils.IDelegate.IFunc) (obj)).invoke();
            if(obj == null)
                return super.create();
            Object obj1;
            Object obj2;
            Method method;
            boolean flag;
            try
            {
                obj1 = android/app/AlertDialog$Builder.getDeclaredField("P");
                ((Field) (obj1)).setAccessible(true);
                obj1 = ((Field) (obj1)).get(this);
            }
            catch(Exception exception)
            {
                return super.create();
            }
            if(obj1 != null)
                break MISSING_BLOCK_LABEL_61;
            return super.create();
            obj2 = Class.forName("com.android.internal.app.AlertController");
            if(obj2 != null)
                break MISSING_BLOCK_LABEL_76;
            return super.create();
            method = obj1.getClass().getMethod("apply", new Class[] {
                obj2
            });
            if(method != null)
                break MISSING_BLOCK_LABEL_105;
            return super.create();
            method.setAccessible(true);
            obj2 = android/app/AlertDialog.getDeclaredField("mAlert");
            if(obj2 != null)
                break MISSING_BLOCK_LABEL_128;
            return super.create();
            ((Field) (obj2)).setAccessible(true);
            method.invoke(obj1, new Object[] {
                ((Field) (obj2)).get(obj)
            });
            flag = isCancelable();
            ((AlertDialog) (obj)).setCancelable(flag);
            ((AlertDialog) (obj)).setCanceledOnTouchOutside(flag);
            obj2 = getOnKeyListener();
            Logger.i("result.setOnKeyListener. general listener:%s", new Object[] {
                SbListDialogFragment.mFuncToGetCommonDialogKeyListner
            });
            obj1 = JVM INSTR new #11  <Class SbListDialogFragment$Builder$1>;
            obj. super();
            ((AlertDialog) (obj)).setOnKeyListener(SbListDialogFragment.getCommonDialogKeyListener(((android.content.DialogInterface.OnKeyListener) (obj2)), ((jp.pixela.pxlibs.utils.IDelegate.IFunc) (obj1))));
            return ((AlertDialog) (obj));
        }

        public final jp.pixela.pxlibs.utils.IDelegate.IFunc getCreateDialogAction()
        {
            return mCreateDialogAction;
        }

        public final int getGravity()
        {
            return mGravity;
        }

        public final int getHeightRatio()
        {
            return mHeightRatio;
        }

        public String[] getItemNames()
        {
            return mItemNames;
        }

        public final CharSequence getMessage()
        {
            return mMessage;
        }

        public final android.content.DialogInterface.OnClickListener getNegativeListener()
        {
            return mNegativeListener;
        }

        public final CharSequence getNegativeText()
        {
            return mNegativeText;
        }

        public final android.content.DialogInterface.OnClickListener getNeutralListener()
        {
            return mNeutralListener;
        }

        public final CharSequence getNeutralText()
        {
            return mNeutralText;
        }

        public final android.content.DialogInterface.OnCancelListener getOnCancelListener()
        {
            return mOnCancelListener;
        }

        public final android.content.DialogInterface.OnDismissListener getOnDismissListener()
        {
            return mOnDismissListener;
        }

        public android.widget.AdapterView.OnItemClickListener getOnItemClickListener()
        {
            return mOnItemClickListener;
        }

        public final android.content.DialogInterface.OnKeyListener getOnKeyListener()
        {
            return mOnKeyListener;
        }

        public final android.content.DialogInterface.OnShowListener getOnShowListener()
        {
            return mOnShowListener;
        }

        public int getPosition()
        {
            return mPosition;
        }

        public final android.content.DialogInterface.OnClickListener getPositiveListener()
        {
            return mPositiveListener;
        }

        public final CharSequence getPositiveText()
        {
            return mPositiveText;
        }

        public final CharSequence getTitle()
        {
            return mTitle;
        }

        public Typeface getTypeface()
        {
            return mTypeface;
        }

        public final View getView()
        {
            return mView;
        }

        public final int getWidthRatio()
        {
            return mWidthRatio;
        }

        public final boolean isAutoDismiss()
        {
            return mIsAutoDismiss;
        }

        public final boolean isCancelable()
        {
            return mIsCancelable;
        }

        public final boolean isCanceledOnTouchOutside()
        {
            return mIsCanceledOnTouchOutside;
        }

        public final boolean isShowKeyboard()
        {
            return mIsShowKeyboard;
        }

        public final android.app.AlertDialog.Builder setCancelable(boolean flag)
        {
            mIsCancelable = flag;
            return super.setCancelable(flag);
        }

        public final Builder setCanceledOnTouchOutside(boolean flag)
        {
            mIsCanceledOnTouchOutside = flag;
            return this;
        }

        public final Builder setCreateDialogAction(jp.pixela.pxlibs.utils.IDelegate.IFunc ifunc)
        {
            mCreateDialogAction = ifunc;
            return this;
        }

        public final Builder setGravity(int i)
        {
            mGravity = i;
            return this;
        }

        public final android.app.AlertDialog.Builder setHeightRatio(int i)
        {
            mHeightRatio = i;
            return this;
        }

        public final Builder setIsAutoDismiss(boolean flag)
        {
            mIsAutoDismiss = flag;
            return this;
        }

        public final Builder setIsShowKeyboard(boolean flag)
        {
            mIsShowKeyboard = flag;
            return this;
        }

        public Builder setItemNames(String as[])
        {
            mItemNames = as;
            return this;
        }

        public final Builder setItems(int i, OnResourceItemClickListener onresourceitemclicklistener)
        {
label0:
            {
                if(onresourceitemclicklistener != null)
                {
                    final TypedArray items = mContext.getResources().obtainTypedArray(i);
                    if(items != null)
                    {
                        onresourceitemclicklistener = onresourceitemclicklistener. new android.content.DialogInterface.OnClickListener() {

                            public final void onClick(DialogInterface dialoginterface, int i)
                            {
                                i = items.getResourceId(i, 0);
                                listener.onClick(dialoginterface, i);
                            }

                            final Builder this$0;
                            final TypedArray val$items;
                            final Builder.OnResourceItemClickListener val$listener;

            
            {
                this$0 = final_builder;
                items = typedarray;
                listener = Builder.OnResourceItemClickListener.this;
                super();
            }
                        }
;
                        break label0;
                    }
                }
                onresourceitemclicklistener = null;
            }
            setItems(i, ((android.content.DialogInterface.OnClickListener) (onresourceitemclicklistener)));
            return this;
        }

        public final android.app.AlertDialog.Builder setMessage(int i)
        {
            return setMessage(((CharSequence) (mContext.getString(i))));
        }

        public final android.app.AlertDialog.Builder setMessage(CharSequence charsequence)
        {
            mMessage = charsequence;
            return this;
        }

        public final android.app.AlertDialog.Builder setNegativeButton(int i, android.content.DialogInterface.OnClickListener onclicklistener)
        {
            return setNegativeButton(((CharSequence) (mContext.getString(i))), onclicklistener);
        }

        public final android.app.AlertDialog.Builder setNegativeButton(CharSequence charsequence, android.content.DialogInterface.OnClickListener onclicklistener)
        {
            mNegativeText = charsequence;
            mNegativeListener = onclicklistener;
            return this;
        }

        public final android.app.AlertDialog.Builder setNeutralButton(int i, android.content.DialogInterface.OnClickListener onclicklistener)
        {
            return setNeutralButton(((CharSequence) (mContext.getString(i))), onclicklistener);
        }

        public final android.app.AlertDialog.Builder setNeutralButton(CharSequence charsequence, android.content.DialogInterface.OnClickListener onclicklistener)
        {
            mNeutralText = charsequence;
            mNeutralListener = onclicklistener;
            return this;
        }

        public final android.app.AlertDialog.Builder setOnCancelListener(android.content.DialogInterface.OnCancelListener oncancellistener)
        {
            mOnCancelListener = oncancellistener;
            return super.setOnCancelListener(null);
        }

        public volatile android.app.AlertDialog.Builder setOnDismissListener(android.content.DialogInterface.OnDismissListener ondismisslistener)
        {
            return setOnDismissListener(ondismisslistener);
        }

        public final Builder setOnDismissListener(android.content.DialogInterface.OnDismissListener ondismisslistener)
        {
            mOnDismissListener = ondismisslistener;
            return this;
        }

        public Builder setOnItemClickListener(android.widget.AdapterView.OnItemClickListener onitemclicklistener)
        {
            mOnItemClickListener = onitemclicklistener;
            return this;
        }

        public final android.app.AlertDialog.Builder setOnKeyListener(android.content.DialogInterface.OnKeyListener onkeylistener)
        {
            mOnKeyListener = onkeylistener;
            return super.setOnKeyListener(onkeylistener);
        }

        public final Builder setOnShowListener(android.content.DialogInterface.OnShowListener onshowlistener)
        {
            mOnShowListener = onshowlistener;
            return this;
        }

        public Builder setPosition(int i)
        {
            mPosition = i;
            return this;
        }

        public final android.app.AlertDialog.Builder setPositiveButton(int i, android.content.DialogInterface.OnClickListener onclicklistener)
        {
            return setPositiveButton(((CharSequence) (mContext.getString(i))), onclicklistener);
        }

        public final android.app.AlertDialog.Builder setPositiveButton(CharSequence charsequence, android.content.DialogInterface.OnClickListener onclicklistener)
        {
            mPositiveText = charsequence;
            mPositiveListener = onclicklistener;
            return this;
        }

        public final android.app.AlertDialog.Builder setTitle(int i)
        {
            return setTitle(((CharSequence) (mContext.getString(i))));
        }

        public final android.app.AlertDialog.Builder setTitle(CharSequence charsequence)
        {
            mTitle = charsequence;
            return this;
        }

        public void setTypeface(Typeface typeface)
        {
            mTypeface = typeface;
        }

        public final android.app.AlertDialog.Builder setView(View view)
        {
            mView = view;
            return super.setView(view);
        }

        public final android.app.AlertDialog.Builder setWidthRatio(int i)
        {
            mWidthRatio = i;
            return this;
        }

        private static final long serialVersionUID = 1L;
        private final transient Context mContext;
        private transient jp.pixela.pxlibs.utils.IDelegate.IFunc mCreateDialogAction;
        private int mGravity;
        private int mHeightRatio;
        private boolean mIsAutoDismiss;
        private boolean mIsCancelable;
        private boolean mIsCanceledOnTouchOutside;
        private boolean mIsShowKeyboard;
        private transient String mItemNames[];
        private transient CharSequence mMessage;
        private transient android.content.DialogInterface.OnClickListener mNegativeListener;
        private CharSequence mNegativeText;
        private transient android.content.DialogInterface.OnClickListener mNeutralListener;
        private CharSequence mNeutralText;
        private transient android.content.DialogInterface.OnCancelListener mOnCancelListener;
        private transient android.content.DialogInterface.OnDismissListener mOnDismissListener;
        private transient android.widget.AdapterView.OnItemClickListener mOnItemClickListener;
        private transient android.content.DialogInterface.OnKeyListener mOnKeyListener;
        private transient android.content.DialogInterface.OnShowListener mOnShowListener;
        private transient int mPosition;
        private transient android.content.DialogInterface.OnClickListener mPositiveListener;
        private CharSequence mPositiveText;
        private transient CharSequence mTitle;
        private transient Typeface mTypeface;
        private transient View mView;
        private int mWidthRatio;

        public Builder(Context context)
        {
            super(context);
            mPosition = 0;
            mTypeface = null;
            mView = null;
            mTitle = null;
            mMessage = null;
            mOnDismissListener = null;
            mOnCancelListener = null;
            mOnShowListener = null;
            mOnKeyListener = null;
            mPositiveListener = null;
            mNegativeListener = null;
            mNeutralListener = null;
            mCreateDialogAction = null;
            mIsCancelable = true;
            mIsShowKeyboard = false;
            mIsAutoDismiss = true;
            mPositiveText = null;
            mNegativeText = null;
            mNeutralText = null;
            mIsCanceledOnTouchOutside = false;
            mWidthRatio = 50;
            mHeightRatio = 50;
            mGravity = 17;
            if(context == null)
            {
                throw new NullPointerException("Context Object is null.");
            } else
            {
                mContext = context;
                return;
            }
        }

        public Builder(Context context, int i)
        {
            super(context, i);
            mPosition = 0;
            mTypeface = null;
            mView = null;
            mTitle = null;
            mMessage = null;
            mOnDismissListener = null;
            mOnCancelListener = null;
            mOnShowListener = null;
            mOnKeyListener = null;
            mPositiveListener = null;
            mNegativeListener = null;
            mNeutralListener = null;
            mCreateDialogAction = null;
            mIsCancelable = true;
            mIsShowKeyboard = false;
            mIsAutoDismiss = true;
            mPositiveText = null;
            mNegativeText = null;
            mNeutralText = null;
            mIsCanceledOnTouchOutside = false;
            mWidthRatio = 50;
            mHeightRatio = 50;
            mGravity = 17;
            if(context == null)
            {
                throw new NullPointerException("Context Object is null.");
            } else
            {
                mContext = context;
                return;
            }
        }
    }

    public static interface Builder.OnResourceItemClickListener
    {

        public abstract void onClick(DialogInterface dialoginterface, int i);
    }

    public static interface IDialog
        extends DialogInterface
    {

        public abstract boolean isShowing();
    }


    public SbListDialogFragment()
    {
    }

    private void createListView(Activity activity, ListView listview, String as[], android.widget.AdapterView.OnItemClickListener onitemclicklistener, int i, Typeface typeface)
    {
        as = new ArrayList(Arrays.asList(as));
        if(listview != null)
        {
            listview.setOnItemClickListener(onitemclicklistener);
            listview.setAdapter(new SbListAdapter(activity, jp.pixela.pxlibs.R.layout.adapter_sb_list, as, typeface));
            listview.setFocusableInTouchMode(false);
            listview.setItemsCanFocus(true);
            listview.setSelection(i);
        }
    }

    public static boolean finishDialog()
    {
        if(mDialogFragment != null && mDialogFragment.getDialog() != null)
        {
            mDialogFragment.getDialog().dismiss();
            return true;
        } else
        {
            return false;
        }
    }

    private final Builder getBuilder()
    {
        Object obj = getArguments();
        if(obj == null)
            return null;
        try
        {
            obj = (Builder)((Bundle) (obj)).getSerializable("builder");
            StringBuilder stringbuilder = JVM INSTR new #146 <Class StringBuilder>;
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
        return ((Builder) (obj));
    }

    protected static android.content.DialogInterface.OnKeyListener getCommonDialogKeyListener(android.content.DialogInterface.OnKeyListener onkeylistener, jp.pixela.pxlibs.utils.IDelegate.IFunc ifunc)
    {
        return new android.content.DialogInterface.OnKeyListener(ifunc, onkeylistener) {

            public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
            {
                Logger.i("alertdialog onkey", new Object[0]);
                if(!SbListDialogFragment.mSkipFuncToGetCommandDialogKey && SbListDialogFragment.mFuncToGetCommonDialogKeyListner != null)
                {
                    android.content.DialogInterface.OnKeyListener onkeylistener1 = (android.content.DialogInterface.OnKeyListener)SbListDialogFragment.mFuncToGetCommonDialogKeyListner.invoke(getActivityFunc.invoke());
                    if(onkeylistener1 != null && onkeylistener1.onKey(dialoginterface, i, keyevent))
                        return true;
                }
                SbListDialogFragment.mSkipFuncToGetCommandDialogKey = false;
                if(originalKeyListener != null)
                {
                    SbListDialogFragment.mSkipFuncToGetCommandDialogKey = true;
                    return originalKeyListener.onKey(dialoginterface, i, keyevent);
                } else
                {
                    return false;
                }
            }

            final jp.pixela.pxlibs.utils.IDelegate.IFunc val$getActivityFunc;
            final android.content.DialogInterface.OnKeyListener val$originalKeyListener;

            
            {
                getActivityFunc = ifunc;
                originalKeyListener = onkeylistener;
                super();
            }
        }
;
    }

    protected static void setFuncToGetCommonDialogKeyListener(Context context)
    {
        setFuncToGetCommonDialogKeyListener(new jp.pixela.pxlibs.utils.IDelegate.IFunc1(context) {

            public android.content.DialogInterface.OnKeyListener invoke(Activity activity)
            {
                return new android.content.DialogInterface.OnKeyListener() {

                    public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
                    {
                        KeyEventHelper.sendKeyEvent(context, keyevent);
                        return false;
                    }

                    final _cls6 this$0;

            
            {
                this$0 = _cls6.this;
                super();
            }
                }
;
            }

            public volatile Object invoke(Object obj)
            {
                return invoke((Activity)obj);
            }

            final Context val$context;

            
            {
                context = context1;
                super();
            }
        }
);
    }

    public static void setFuncToGetCommonDialogKeyListener(jp.pixela.pxlibs.utils.IDelegate.IFunc1 ifunc1)
    {
        Logger.i("set func to GetCommonDialogKeyListner. func:%s", new Object[] {
            ifunc1
        });
        mFuncToGetCommonDialogKeyListner = ifunc1;
    }

    public static IDialog show(Activity activity, Builder builder)
    {
        return show(activity, builder, false);
    }

    public static IDialog show(Activity activity, Builder builder, boolean flag)
    {
        return show(activity, builder, flag, "dialog");
    }

    public static IDialog show(Activity activity, Builder builder, boolean flag, String s)
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
        obj = new SbListDialogFragment();
        setFuncToGetCommonDialogKeyListener(builder.getContext());
        builder.setOnKeyListener(getCommonDialogKeyListener(builder.getOnKeyListener(), new jp.pixela.pxlibs.utils.IDelegate.IFunc(((SbListDialogFragment) (obj))) {

            public Activity invoke()
            {
                return newFragment.getActivity();
            }

            public volatile Object invoke()
            {
                return invoke();
            }

            final SbListDialogFragment val$newFragment;

            
            {
                newFragment = sblistdialogfragment;
                super();
            }
        }
));
        s.putSerializable("builder", builder);
        ((SbListDialogFragment) (obj)).setArguments(s);
        ((SbListDialogFragment) (obj)).setCancelable(builder.isCancelable());
        try
        {
            ((SbListDialogFragment) (obj)).show(fragmenttransaction, ((String) (activity)));
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
        ((SbListDialogFragment) (obj)).mIsShowing.set(true);
        activity = new IDialog(((SbListDialogFragment) (obj))) {

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

            final SbListDialogFragment val$newFragment;

            
            {
                newFragment = sblistdialogfragment;
                super();
            }
        }
;
        mDialogFragment = ((SbListDialogFragment) (obj));
        return activity;
    }

    public void onCancel(DialogInterface dialoginterface)
    {
        Object obj = getBuilder();
        if(obj != null)
            break MISSING_BLOCK_LABEL_34;
        Logger.v("builder == null", new Object[0]);
        super.onCancel(dialoginterface);
        mIsShowing.set(false);
        return;
        obj = ((Builder) (obj)).getOnCancelListener();
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

    public Dialog onCreateDialog(final Bundle view)
    {
        final Builder builder = getBuilder();
        final boolean isCustomView = false;
        if(builder == null)
        {
            Logger.v("builder == null", new Object[0]);
            return super.onCreateDialog(view);
        }
        if(builder.getView() == null)
        {
            isCustomView = true;
            view = getActivity().getLayoutInflater().inflate(jp.pixela.pxlibs.R.layout.view_sb_list_dialog_fragment, null);
            builder.setView(view);
            builder.setCreateDialogAction(new jp.pixela.pxlibs.utils.IDelegate.IFunc() {

                public AlertDialog invoke()
                {
                    AlertDialog alertdialog = (new android.app.AlertDialog.Builder(builder.getContext(), jp.pixela.pxlibs.R.style.pxlibs_TransparentDialogStyle)).create();
                    if(alertdialog != null)
                    {
                        TextView textview = (TextView)view.findViewById(jp.pixela.pxlibs.R.id.text_dialog_message);
                        if(builder.getMessage() != null)
                        {
                            if(builder.getTypeface() != null)
                                textview.setTypeface(builder.getTypeface());
                            textview.setText(builder.getMessage());
                        } else
                        {
                            textview.setVisibility(8);
                            view.findViewById(jp.pixela.pxlibs.R.id.image_dialog_separator).setVisibility(8);
                        }
                        createListView(getActivity(), (ListView)view.findViewById(jp.pixela.pxlibs.R.id.view_dialog_list), builder.getItemNames(), builder.getOnItemClickListener(), builder.getPosition(), builder.getTypeface());
                    }
                    return alertdialog;
                }

                public volatile Object invoke()
                {
                    return invoke();
                }

                final SbListDialogFragment this$0;
                final Builder val$builder;
                final View val$view;

            
            {
                this$0 = SbListDialogFragment.this;
                builder = builder1;
                view = view1;
                super();
            }
            }
);
        }
        view = builder.create();
        if(!isCustomView)
        {
            view.setTitle(builder.getTitle());
            view.setMessage(builder.getMessage());
        }
        view.setCanceledOnTouchOutside(builder.isCanceledOnTouchOutside());
        view.setOnShowListener(new android.content.DialogInterface.OnShowListener() {

            public final void onShow(DialogInterface dialoginterface)
            {
                if(isCustomView)
                {
                    int i = (DisplayHelper.getSize(getContext()).x * builder.getWidthRatio()) / 100;
                    int j = (DisplayHelper.getSize(getContext()).y * builder.getHeightRatio()) / 100;
                    Object obj = result.getWindow().getAttributes();
                    obj.width = i;
                    obj.gravity = builder.getGravity();
                    result.getWindow().setAttributes(((android.view.WindowManager.LayoutParams) (obj)));
                    obj = builder.getView();
                    if(obj != null)
                    {
                        android.view.ViewGroup.LayoutParams layoutparams = ((View) (obj)).getLayoutParams();
                        if(layoutparams != null)
                        {
                            layoutparams.width = i;
                            ((View) (obj)).setLayoutParams(layoutparams);
                        }
                    }
                }
                android.content.DialogInterface.OnShowListener onshowlistener = builder.getOnShowListener();
                if(onshowlistener != null)
                    onshowlistener.onShow(dialoginterface);
            }

            final SbListDialogFragment this$0;
            final Builder val$builder;
            final boolean val$isCustomView;
            final AlertDialog val$result;

            
            {
                this$0 = SbListDialogFragment.this;
                isCustomView = flag;
                builder = builder1;
                result = alertdialog;
                super();
            }
        }
);
        return view;
    }

    public void onDismiss(DialogInterface dialoginterface)
    {
        Object obj = getBuilder();
        if(obj != null)
            break MISSING_BLOCK_LABEL_34;
        Logger.v("builder == null", new Object[0]);
        super.onDismiss(dialoginterface);
        mIsShowing.set(false);
        return;
        obj = ((Builder) (obj)).getOnDismissListener();
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

    private static final String SERIAL_KEY_BUILDER = "builder";
    private static transient SbListDialogFragment mDialogFragment;
    private static jp.pixela.pxlibs.utils.IDelegate.IFunc1 mFuncToGetCommonDialogKeyListner;
    private static boolean mSkipFuncToGetCommandDialogKey = false;
    private final AtomicBoolean mIsShowing = new AtomicBoolean(false);







/*
    static boolean access$302(boolean flag)
    {
        mSkipFuncToGetCommandDialogKey = flag;
        return flag;
    }

*/

    // Unreferenced inner class jp/pixela/pxlibs/android/views/dialogs/SbListDialogFragment$Builder$1

/* anonymous class */
    class Builder._cls1
        implements jp.pixela.pxlibs.utils.IDelegate.IFunc
    {

        public Activity invoke()
        {
            return result.getOwnerActivity();
        }

        public volatile Object invoke()
        {
            return invoke();
        }

        final Builder this$0;
        final AlertDialog val$result;

            
            {
                this$0 = final_builder;
                result = AlertDialog.this;
                super();
            }
    }

}
