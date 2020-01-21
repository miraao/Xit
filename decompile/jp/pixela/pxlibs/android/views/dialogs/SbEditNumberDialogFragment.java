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
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.*;
import android.widget.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import jp.pixela.pxlibs.android.views.buttons.PxButton;
import jp.pixela.pxlibs.android.views.dialogs.common.ViewButtonInfo;
import jp.pixela.pxlibs.android.views.helper.KeyEventHelper;
import jp.pixela.pxlibs.utils.android.DisplayHelper;
import jp.pixela.pxlibs.utils.android.log.Logger;

public class SbEditNumberDialogFragment extends DialogFragment
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
            obj1 = getOnKeyListener();
            Logger.i("result.setOnKeyListener. general listener:%s", new Object[] {
                SbEditNumberDialogFragment.mFuncToGetCommonDialogKeyListner
            });
            obj2 = JVM INSTR new #11  <Class SbEditNumberDialogFragment$Builder$1>;
            obj. super();
            ((AlertDialog) (obj)).setOnKeyListener(SbEditNumberDialogFragment.getCommonDialogKeyListener(((android.content.DialogInterface.OnKeyListener) (obj1)), ((jp.pixela.pxlibs.utils.IDelegate.IFunc) (obj2))));
            return ((AlertDialog) (obj));
        }

        public android.view.View.OnClickListener getCancelClickListener()
        {
            return mCancelListener;
        }

        public String getCancelLabel()
        {
            return mCancelLabel;
        }

        public final jp.pixela.pxlibs.utils.IDelegate.IFunc getCreateDialogAction()
        {
            return mCreateDialogAction;
        }

        public EditText getEditText()
        {
            return mEditText;
        }

        public final int getGravity()
        {
            return mGravity;
        }

        public final int getHeightRatio()
        {
            return mHeightRatio;
        }

        public int getInputType()
        {
            return mInputType;
        }

        public int getLength()
        {
            return mLength;
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

        public android.view.View.OnClickListener getOkClickListener()
        {
            return mOkListener;
        }

        public String getOkLabel()
        {
            return mOkLabel;
        }

        public final android.content.DialogInterface.OnCancelListener getOnCancelListener()
        {
            return mOnCancelListener;
        }

        public final android.content.DialogInterface.OnDismissListener getOnDismissListener()
        {
            return mOnDismissListener;
        }

        public final android.content.DialogInterface.OnKeyListener getOnKeyListener()
        {
            return mOnKeyListener;
        }

        public final android.content.DialogInterface.OnShowListener getOnShowListener()
        {
            return mOnShowListener;
        }

        public final android.content.DialogInterface.OnClickListener getPositiveListener()
        {
            return mPositiveListener;
        }

        public final CharSequence getPositiveText()
        {
            return mPositiveText;
        }

        public String getPrompt()
        {
            return mPrompt;
        }

        public TextWatcher getTextWatcher()
        {
            return mTextWatcher;
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

        public void setCancelClickListener(android.view.View.OnClickListener onclicklistener)
        {
            mCancelListener = onclicklistener;
        }

        public void setCancelLabel(String s)
        {
            mCancelLabel = s;
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

        public void setEditText(EditText edittext)
        {
            mEditText = edittext;
        }

        public final Builder setGravity(int i)
        {
            mGravity = i;
            return this;
        }

        public final Builder setHeightRatio(int i)
        {
            mHeightRatio = i;
            return this;
        }

        public void setInputType(int i)
        {
            mInputType = i;
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

        public Builder setLength(int i)
        {
            mLength = i;
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

        public void setOkClickListener(android.view.View.OnClickListener onclicklistener)
        {
            mOkListener = onclicklistener;
        }

        public void setOkLabel(String s)
        {
            mOkLabel = s;
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

        public Builder setPrompt(String s)
        {
            mPrompt = s;
            return this;
        }

        public void setTextWatcher(TextWatcher textwatcher)
        {
            mTextWatcher = textwatcher;
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

        public final Builder setWidthRatio(int i)
        {
            mWidthRatio = i;
            return this;
        }

        private static final long serialVersionUID = 1L;
        private transient String mCancelLabel;
        private transient android.view.View.OnClickListener mCancelListener;
        private final transient Context mContext;
        private transient jp.pixela.pxlibs.utils.IDelegate.IFunc mCreateDialogAction;
        private transient EditText mEditText;
        private int mGravity;
        private int mHeightRatio;
        private transient int mInputType;
        private transient boolean mIsAutoDismiss;
        private transient boolean mIsCancelable;
        private transient boolean mIsCanceledOnTouchOutside;
        private transient boolean mIsShowKeyboard;
        private transient int mLength;
        private transient CharSequence mMessage;
        private transient android.content.DialogInterface.OnClickListener mNegativeListener;
        private transient CharSequence mNegativeText;
        private transient android.content.DialogInterface.OnClickListener mNeutralListener;
        private transient CharSequence mNeutralText;
        private transient String mOkLabel;
        private transient android.view.View.OnClickListener mOkListener;
        private transient android.content.DialogInterface.OnCancelListener mOnCancelListener;
        private transient android.content.DialogInterface.OnDismissListener mOnDismissListener;
        private transient android.content.DialogInterface.OnKeyListener mOnKeyListener;
        private transient android.content.DialogInterface.OnShowListener mOnShowListener;
        private transient android.content.DialogInterface.OnClickListener mPositiveListener;
        private transient CharSequence mPositiveText;
        private transient String mPrompt;
        private transient TextWatcher mTextWatcher;
        private transient CharSequence mTitle;
        private transient Typeface mTypeface;
        private transient View mView;
        private int mWidthRatio;

        public Builder(Context context)
        {
            super(context);
            mEditText = null;
            mInputType = 1;
            mPrompt = null;
            mLength = 0;
            mTextWatcher = null;
            mOkLabel = "OK";
            mOkListener = null;
            mCancelLabel = "Cancel";
            mCancelListener = null;
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
            mEditText = null;
            mInputType = 1;
            mPrompt = null;
            mLength = 0;
            mTextWatcher = null;
            mOkLabel = "OK";
            mOkListener = null;
            mCancelLabel = "Cancel";
            mCancelListener = null;
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


    public SbEditNumberDialogFragment()
    {
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
        Builder builder;
        try
        {
            builder = (Builder)((Bundle) (obj)).getSerializable("builder");
            obj = JVM INSTR new #100 <Class StringBuilder>;
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

    protected static android.content.DialogInterface.OnKeyListener getCommonDialogKeyListener(android.content.DialogInterface.OnKeyListener onkeylistener, jp.pixela.pxlibs.utils.IDelegate.IFunc ifunc)
    {
        return new android.content.DialogInterface.OnKeyListener(ifunc, onkeylistener) {

            public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
            {
                Logger.i("alertdialog onkey", new Object[0]);
                if(!SbEditNumberDialogFragment.mSkipFuncToGetCommandDialogKey && SbEditNumberDialogFragment.mFuncToGetCommonDialogKeyListner != null)
                {
                    android.content.DialogInterface.OnKeyListener onkeylistener1 = (android.content.DialogInterface.OnKeyListener)SbEditNumberDialogFragment.mFuncToGetCommonDialogKeyListner.invoke(getActivityFunc.invoke());
                    if(onkeylistener1 != null && onkeylistener1.onKey(dialoginterface, i, keyevent))
                        return true;
                }
                SbEditNumberDialogFragment.mSkipFuncToGetCommandDialogKey = false;
                if(originalKeyListener != null)
                {
                    SbEditNumberDialogFragment.mSkipFuncToGetCommandDialogKey = true;
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

                    final _cls7 this$0;

            
            {
                this$0 = _cls7.this;
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
        obj = new Bundle();
        s = new StringBuilder();
        s.append("builder=");
        s.append(builder);
        Logger.v(s.toString(), new Object[0]);
        s = new SbEditNumberDialogFragment();
        setFuncToGetCommonDialogKeyListener(builder.getContext());
        builder.setOnKeyListener(getCommonDialogKeyListener(builder.getOnKeyListener(), new jp.pixela.pxlibs.utils.IDelegate.IFunc(s) {

            public Activity invoke()
            {
                return newFragment.getActivity();
            }

            public volatile Object invoke()
            {
                return invoke();
            }

            final SbEditNumberDialogFragment val$newFragment;

            
            {
                newFragment = sbeditnumberdialogfragment;
                super();
            }
        }
));
        ((Bundle) (obj)).putSerializable("builder", builder);
        s.setArguments(((Bundle) (obj)));
        s.setCancelable(builder.isCancelable());
        try
        {
            s.show(fragmenttransaction, ((String) (activity)));
        }
        // Misplaced declaration of an exception variable
        catch(Builder builder)
        {
            s = new Throwable();
            activity = new StringBuilder();
            activity.append("catch IllegalStateException. e=");
            activity.append(builder.getMessage());
            Logger.d(s, activity.toString(), new Object[0]);
            return null;
        }
        ((SbEditNumberDialogFragment) (s)).mIsShowing.set(true);
        activity = new IDialog(s) {

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

            final SbEditNumberDialogFragment val$newFragment;

            
            {
                newFragment = sbeditnumberdialogfragment;
                super();
            }
        }
;
        mDialogFragment = s;
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
            view = getActivity().getLayoutInflater().inflate(jp.pixela.pxlibs.R.layout.view_sb_edit_dialog_fragment, null);
            builder.setView(view);
            builder.setCreateDialogAction(new jp.pixela.pxlibs.utils.IDelegate.IFunc() {

                public AlertDialog invoke()
                {
                    AlertDialog alertdialog = (new android.app.AlertDialog.Builder(builder.getContext(), jp.pixela.pxlibs.R.style.pxlibs_TransparentDialogStyle)).create();
                    if(alertdialog != null)
                    {
                        TextView textview = (TextView)view.findViewById(jp.pixela.pxlibs.R.id.text_dialog_message);
                        textview.setText(builder.getMessage());
                        Object obj = (EditText)view.findViewById(jp.pixela.pxlibs.R.id.edit_box);
                        ((EditText) (obj)).requestFocus();
                        ((EditText) (obj)).setImeOptions(6);
                        builder.setEditText(((EditText) (obj)));
                        if(builder.getLength() > 0)
                            ((EditText) (obj)).setFilters(new InputFilter[] {
                                new android.text.InputFilter.LengthFilter(builder.getLength())
                            });
                        ((EditText) (obj)).setInputType(builder.getInputType());
                        ((EditText) (obj)).addTextChangedListener(builder.getTextWatcher());
                        if(builder.getPrompt() != null)
                        {
                            ((EditText) (obj)).setText(builder.getPrompt());
                            ((EditText) (obj)).setSelection(builder.getPrompt().length());
                        }
                        if(builder.getTypeface() != null)
                        {
                            textview.setTypeface(builder.getTypeface());
                            ((EditText) (obj)).setTypeface(builder.getTypeface());
                        }
                        obj = new ArrayList();
                        ((List) (obj)).add(new ViewButtonInfo((PxButton)view.findViewById(jp.pixela.pxlibs.R.id.button_dialog_negative), -2, builder.getNegativeText(), builder.getNegativeListener()));
                        ((List) (obj)).add(new ViewButtonInfo((PxButton)view.findViewById(jp.pixela.pxlibs.R.id.button_dialog_neutral), -3, builder.getNeutralText(), builder.getNeutralListener()));
                        ((List) (obj)).add(new ViewButtonInfo((PxButton)view.findViewById(jp.pixela.pxlibs.R.id.button_dialog_positive), -1, builder.getPositiveText(), builder.getPositiveListener()));
                        setCustomViewButton(alertdialog, builder, ((List) (obj)));
                    }
                    return alertdialog;
                }

                public volatile Object invoke()
                {
                    return invoke();
                }

                final SbEditNumberDialogFragment this$0;
                final Builder val$builder;
                final View val$view;

            
            {
                this$0 = SbEditNumberDialogFragment.this;
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
                            layoutparams.height = j;
                            ((View) (obj)).setLayoutParams(layoutparams);
                        }
                    }
                }
                android.content.DialogInterface.OnShowListener onshowlistener = builder.getOnShowListener();
                if(onshowlistener != null)
                    onshowlistener.onShow(dialoginterface);
            }

            final SbEditNumberDialogFragment this$0;
            final Builder val$builder;
            final boolean val$isCustomView;
            final AlertDialog val$result;

            
            {
                this$0 = SbEditNumberDialogFragment.this;
                isCustomView = flag;
                builder = builder1;
                result = alertdialog;
                super();
            }
        }
);
        view.getWindow().setSoftInputMode(5);
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

    protected final void setCustomViewButton(final AlertDialog dialog, final Builder builder, List list)
    {
        if(dialog == null)
            throw new NullPointerException("AlertDialog Object is null.");
        if(builder == null)
            throw new NullPointerException("Builder Object is null.");
        if(list == null)
            throw new NullPointerException("ViewButtonInfo List Object is null.");
        if(list != null)
        {
            Iterator iterator = list.iterator();
            int i = 0;
            do
            {
                if(!iterator.hasNext())
                    break;
                ViewButtonInfo viewbuttoninfo = (ViewButtonInfo)iterator.next();
                if(viewbuttoninfo != null && viewbuttoninfo.getButton() != null && viewbuttoninfo.getText() != null)
                    i++;
            } while(true);
            iterator = list.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                final ViewButtonInfo info = (ViewButtonInfo)iterator.next();
                if(info != null)
                {
                    PxButton pxbutton = info.getButton();
                    list = info.getText();
                    final android.content.DialogInterface.OnClickListener listener = info.getClickListener();
                    if(pxbutton != null)
                    {
                        if(list != null)
                        {
                            pxbutton.setText(list);
                            pxbutton.setVisibility(0);
                        } else
                        if(i == 0)
                            pxbutton.setVisibility(4);
                        else
                            pxbutton.setVisibility(8);
                        if(list != null && listener != null)
                            pxbutton.setOnClickListener(new android.view.View.OnClickListener() {

                                public void onClick(View view)
                                {
                                    listener.onClick(dialog, info.getId());
                                    if(builder.isAutoDismiss())
                                        dialog.dismiss();
                                }

                                final SbEditNumberDialogFragment this$0;
                                final Builder val$builder;
                                final AlertDialog val$dialog;
                                final ViewButtonInfo val$info;
                                final android.content.DialogInterface.OnClickListener val$listener;

            
            {
                this$0 = SbEditNumberDialogFragment.this;
                listener = onclicklistener;
                dialog = alertdialog;
                info = viewbuttoninfo;
                builder = builder1;
                super();
            }
                            }
);
                    }
                }
            } while(true);
            if(i == 0)
            {
                dialog = (LinearLayout)builder.getView().findViewById(jp.pixela.pxlibs.R.id.layout_dialog_button);
                if(dialog != null)
                    dialog.setVisibility(8);
            } else
            if(i == 1)
            {
                dialog = (LinearLayout)builder.getView().findViewById(jp.pixela.pxlibs.R.id.layout_dialog_button);
                if(dialog != null)
                    dialog.setWeightSum(2.0F);
            }
        }
    }

    private static final String SERIAL_KEY_BUILDER = "builder";
    private static transient SbEditNumberDialogFragment mDialogFragment;
    private static jp.pixela.pxlibs.utils.IDelegate.IFunc1 mFuncToGetCommonDialogKeyListner;
    private static boolean mSkipFuncToGetCommandDialogKey = false;
    private final AtomicBoolean mIsShowing = new AtomicBoolean(false);






/*
    static boolean access$202(boolean flag)
    {
        mSkipFuncToGetCommandDialogKey = flag;
        return flag;
    }

*/

    // Unreferenced inner class jp/pixela/pxlibs/android/views/dialogs/SbEditNumberDialogFragment$Builder$1

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
