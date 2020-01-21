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

public class SbAlertDialogFragment extends DialogFragment
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
            Field field;
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
            obj2 = obj1.getClass().getMethod("apply", new Class[] {
                obj2
            });
            if(obj2 != null)
                break MISSING_BLOCK_LABEL_103;
            return super.create();
            ((Method) (obj2)).setAccessible(true);
            field = android/app/AlertDialog.getDeclaredField("mAlert");
            if(field != null)
                break MISSING_BLOCK_LABEL_127;
            return super.create();
            field.setAccessible(true);
            ((Method) (obj2)).invoke(obj1, new Object[] {
                field.get(obj)
            });
            flag = isCancelable();
            ((AlertDialog) (obj)).setCancelable(flag);
            ((AlertDialog) (obj)).setCanceledOnTouchOutside(flag);
            obj1 = getOnKeyListener();
            Logger.i("result.setOnKeyListener. general listener:%s", new Object[] {
                SbAlertDialogFragment.mFuncToGetCommonDialogKeyListner
            });
            obj2 = JVM INSTR new #11  <Class SbAlertDialogFragment$Builder$1>;
            obj. super();
            ((AlertDialog) (obj)).setOnKeyListener(SbAlertDialogFragment.getCommonDialogKeyListener(((android.content.DialogInterface.OnKeyListener) (obj1)), ((jp.pixela.pxlibs.utils.IDelegate.IFunc) (obj2))));
            return ((AlertDialog) (obj));
        }

        public int getButtonType()
        {
            return mButtonType;
        }

        public final jp.pixela.pxlibs.utils.IDelegate.IFunc getCreateDialogAction()
        {
            return mCreateDialogAction;
        }

        public final CharSequence getErrorCode()
        {
            return mErrorCode;
        }

        public final int getHeightRatio()
        {
            return mHeightRatio;
        }

        public int getLayoutType()
        {
            return mLayoutType;
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

        public final CharSequence getSubMessage()
        {
            return mSubMessage;
        }

        public final int getTextGravity()
        {
            return mTextGravity;
        }

        public final float getTextSize()
        {
            return mTextSize;
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

        public final boolean isVisibleIcon()
        {
            return mVisibleIcon;
        }

        public void setButtonType(int i)
        {
            mButtonType = i;
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

        public final android.app.AlertDialog.Builder setErrorCode(CharSequence charsequence)
        {
            mErrorCode = charsequence;
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

        public void setLayoutType(int i)
        {
            mLayoutType = i;
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

        public final void setSubMessage(CharSequence charsequence)
        {
            mSubMessage = charsequence;
        }

        public final android.app.AlertDialog.Builder setTextGravity(int i)
        {
            mTextGravity = i;
            return this;
        }

        public final android.app.AlertDialog.Builder setTextSize(float f)
        {
            mTextSize = f;
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

        public final android.app.AlertDialog.Builder setVisibleIcon(boolean flag)
        {
            mVisibleIcon = flag;
            return this;
        }

        public final android.app.AlertDialog.Builder setWidthRatio(int i)
        {
            mWidthRatio = i;
            return this;
        }

        private static final long serialVersionUID = 1L;
        private int mButtonType;
        private final transient Context mContext;
        private transient jp.pixela.pxlibs.utils.IDelegate.IFunc mCreateDialogAction;
        private transient CharSequence mErrorCode;
        private int mHeightRatio;
        private boolean mIsAutoDismiss;
        private boolean mIsCancelable;
        private boolean mIsCanceledOnTouchOutside;
        private boolean mIsShowKeyboard;
        private int mLayoutType;
        private transient CharSequence mMessage;
        private transient android.content.DialogInterface.OnClickListener mNegativeListener;
        private CharSequence mNegativeText;
        private transient android.content.DialogInterface.OnClickListener mNeutralListener;
        private CharSequence mNeutralText;
        private transient android.content.DialogInterface.OnCancelListener mOnCancelListener;
        private transient android.content.DialogInterface.OnDismissListener mOnDismissListener;
        private transient android.content.DialogInterface.OnKeyListener mOnKeyListener;
        private transient android.content.DialogInterface.OnShowListener mOnShowListener;
        private transient android.content.DialogInterface.OnClickListener mPositiveListener;
        private CharSequence mPositiveText;
        private transient CharSequence mSubMessage;
        private int mTextGravity;
        private float mTextSize;
        private transient CharSequence mTitle;
        private transient Typeface mTypeface;
        private transient View mView;
        private boolean mVisibleIcon;
        private int mWidthRatio;

        public Builder(Context context)
        {
            super(context);
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
            mErrorCode = null;
            mIsCancelable = true;
            mIsShowKeyboard = false;
            mIsAutoDismiss = true;
            mPositiveText = null;
            mNegativeText = null;
            mNeutralText = null;
            mButtonType = 0;
            mIsCanceledOnTouchOutside = false;
            mTypeface = null;
            mLayoutType = 0;
            mSubMessage = null;
            mVisibleIcon = true;
            mTextSize = 0.0F;
            mTextGravity = 0;
            mWidthRatio = 50;
            mHeightRatio = 50;
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
            mErrorCode = null;
            mIsCancelable = true;
            mIsShowKeyboard = false;
            mIsAutoDismiss = true;
            mPositiveText = null;
            mNegativeText = null;
            mNeutralText = null;
            mButtonType = 0;
            mIsCanceledOnTouchOutside = false;
            mTypeface = null;
            mLayoutType = 0;
            mSubMessage = null;
            mVisibleIcon = true;
            mTextSize = 0.0F;
            mTextGravity = 0;
            mWidthRatio = 50;
            mHeightRatio = 50;
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


    public SbAlertDialogFragment()
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
            obj = JVM INSTR new #102 <Class StringBuilder>;
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
                if(!SbAlertDialogFragment.mSkipFuncToGetCommandDialogKey && SbAlertDialogFragment.mFuncToGetCommonDialogKeyListner != null)
                {
                    android.content.DialogInterface.OnKeyListener onkeylistener1 = (android.content.DialogInterface.OnKeyListener)SbAlertDialogFragment.mFuncToGetCommonDialogKeyListner.invoke(getActivityFunc.invoke());
                    if(onkeylistener1 != null && onkeylistener1.onKey(dialoginterface, i, keyevent))
                        return true;
                }
                SbAlertDialogFragment.mSkipFuncToGetCommandDialogKey = false;
                if(originalKeyListener != null)
                {
                    SbAlertDialogFragment.mSkipFuncToGetCommandDialogKey = true;
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
        s = new SbAlertDialogFragment();
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

            final SbAlertDialogFragment val$newFragment;

            
            {
                newFragment = sbalertdialogfragment;
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
        catch(String s)
        {
            builder = new Throwable();
            activity = new StringBuilder();
            activity.append("catch IllegalStateException. e=");
            activity.append(s.getMessage());
            Logger.d(builder, activity.toString(), new Object[0]);
            return null;
        }
        ((SbAlertDialogFragment) (s)).mIsShowing.set(true);
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

            final SbAlertDialogFragment val$newFragment;

            
            {
                newFragment = sbalertdialogfragment;
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
            int i;
            if(builder.getLayoutType() == 0)
                i = jp.pixela.pxlibs.R.layout.view_sb_alert_dialog_fragment;
            else
                i = jp.pixela.pxlibs.R.layout.view_sb_alert_dialog_fragment2;
            view = getActivity().getLayoutInflater().inflate(i, null);
            builder.setView(view);
            builder.setCreateDialogAction(new jp.pixela.pxlibs.utils.IDelegate.IFunc() {

                public AlertDialog invoke()
                {
                    AlertDialog alertdialog = (new android.app.AlertDialog.Builder(builder.getContext(), jp.pixela.pxlibs.R.style.pxlibs_TransparentDialogStyle)).create();
                    if(alertdialog != null)
                    {
                        ArrayList arraylist = new ArrayList();
                        if(builder.getLayoutType() == 0)
                        {
                            Object obj2 = (ImageView)view.findViewById(jp.pixela.pxlibs.R.id.image_dialog_icon);
                            if(builder.isVisibleIcon())
                                ((ImageView) (obj2)).setVisibility(0);
                            else
                                ((ImageView) (obj2)).setVisibility(8);
                            ((ScrollView)view.findViewById(jp.pixela.pxlibs.R.id.scroll_dialog_message)).setFocusable(builder.isVisibleIcon() ^ true);
                            obj2 = (TextView)view.findViewById(jp.pixela.pxlibs.R.id.text_dialog_message);
                            ((TextView) (obj2)).setText(builder.getMessage());
                            if(builder.getTextSize() > 0.0F)
                                ((TextView) (obj2)).setTextSize(builder.getTextSize());
                            if(builder.getTextGravity() > 0)
                                ((TextView) (obj2)).setGravity(builder.getTextGravity());
                            obj2 = (TextView)view.findViewById(jp.pixela.pxlibs.R.id.text_dialog_error_code);
                            if(builder.getErrorCode() != null)
                            {
                                ((TextView) (obj2)).setText(String.format(getString(jp.pixela.pxlibs.R.string.sb_alert_dialog_error_code), new Object[] {
                                    builder.getErrorCode()
                                }));
                                ((TextView) (obj2)).setVisibility(0);
                                if(builder.getTextSize() > 0.0F)
                                    ((TextView) (obj2)).setTextSize(builder.getTextSize());
                                if(builder.getTextGravity() > 0)
                                    ((TextView) (obj2)).setGravity(builder.getTextGravity());
                            } else
                            if(!builder.isVisibleIcon())
                                ((TextView) (obj2)).setVisibility(8);
                            else
                                ((TextView) (obj2)).setVisibility(4);
                        } else
                        {
                            TextView textview = (TextView)view.findViewById(jp.pixela.pxlibs.R.id.text_dialog_message);
                            if(textview != null)
                            {
                                if(builder.getTypeface() != null)
                                    textview.setTypeface(builder.getTypeface());
                                textview.setText(builder.getMessage());
                            }
                            textview = (TextView)view.findViewById(jp.pixela.pxlibs.R.id.text_dialog_message2);
                            if(textview != null)
                            {
                                if(builder.getTypeface() != null)
                                    textview.setTypeface(builder.getTypeface());
                                textview.setText(builder.getSubMessage());
                            }
                        }
                        PxButton pxbutton1 = (PxButton)view.findViewById(jp.pixela.pxlibs.R.id.button_dialog_negative);
                        arraylist.add(new ViewButtonInfo(pxbutton1, -2, builder.getNegativeText(), builder.getNegativeListener()));
                        PxButton pxbutton = (PxButton)view.findViewById(jp.pixela.pxlibs.R.id.button_dialog_neutral);
                        arraylist.add(new ViewButtonInfo(pxbutton, -3, builder.getNeutralText(), builder.getNeutralListener()));
                        PxButton pxbutton2 = (PxButton)view.findViewById(jp.pixela.pxlibs.R.id.button_dialog_positive);
                        arraylist.add(new ViewButtonInfo(pxbutton2, -1, builder.getPositiveText(), builder.getPositiveListener()));
                        if(builder.getButtonType() == 1)
                        {
                            pxbutton2.setType(getContext(), 1);
                            pxbutton1.setType(getContext(), 1);
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

                final SbAlertDialogFragment this$0;
                final Builder val$builder;
                final View val$view;

            
            {
                this$0 = SbAlertDialogFragment.this;
                builder = builder1;
                view = view1;
                super();
            }
            }
);
            isCustomView = true;
        }
        View view1 = builder.getView();
        if(view1 != null)
        {
            view = (ViewGroup)view1.getParent();
            if(view != null)
                view.removeView(view1);
        }
        view = builder.create();
        if(!isCustomView)
        {
            view.setTitle(builder.getTitle());
            view.setMessage(builder.getMessage());
            Object obj = builder.getNegativeText();
            Object obj1 = builder.getNegativeListener();
            if(obj != null)
                view.setButton(-2, ((CharSequence) (obj)), ((android.content.DialogInterface.OnClickListener) (obj1)));
            obj1 = builder.getNeutralText();
            obj = builder.getNeutralListener();
            if(obj1 != null)
                view.setButton(-3, ((CharSequence) (obj1)), ((android.content.DialogInterface.OnClickListener) (obj)));
            obj1 = builder.getPositiveText();
            obj = builder.getPositiveListener();
            if(obj1 != null)
                view.setButton(-1, ((CharSequence) (obj1)), ((android.content.DialogInterface.OnClickListener) (obj)));
        }
        view.setCanceledOnTouchOutside(builder.isCanceledOnTouchOutside());
        view.setOnShowListener(new android.content.DialogInterface.OnShowListener() {

            public final void onShow(DialogInterface dialoginterface)
            {
                if(isCustomView)
                {
                    int j = (DisplayHelper.getSize(getContext()).x * builder.getWidthRatio()) / 100;
                    int l = (DisplayHelper.getSize(getContext()).y * builder.getHeightRatio()) / 100;
                    android.view.WindowManager.LayoutParams layoutparams = result.getWindow().getAttributes();
                    layoutparams.width = j;
                    result.getWindow().setAttributes(layoutparams);
                    View view2 = builder.getView();
                    if(view2 != null)
                    {
                        android.view.ViewGroup.LayoutParams layoutparams1 = view2.getLayoutParams();
                        if(layoutparams1 != null)
                        {
                            layoutparams1.width = j;
                            layoutparams1.height = l;
                            view2.setLayoutParams(layoutparams1);
                        }
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
                        final android.content.DialogInterface.OnClickListener listener;
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
                android.content.DialogInterface.OnShowListener onshowlistener = builder.getOnShowListener();
                if(onshowlistener != null)
                    onshowlistener.onShow(dialoginterface);
            }

            final SbAlertDialogFragment this$0;
            final Builder val$builder;
            final boolean val$isCustomView;
            final AlertDialog val$result;

            
            {
                this$0 = SbAlertDialogFragment.this;
                isCustomView = flag;
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
            boolean flag = false;
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
                        boolean flag1;
                        if(list != null)
                        {
                            pxbutton.setText(list);
                            pxbutton.setVisibility(0);
                            flag1 = flag;
                            if(!flag)
                            {
                                pxbutton.requestFocus();
                                flag1 = true;
                            }
                        } else
                        if(i == 0)
                        {
                            pxbutton.setVisibility(4);
                            flag1 = flag;
                        } else
                        {
                            pxbutton.setVisibility(8);
                            flag1 = flag;
                        }
                        flag = flag1;
                        if(list != null)
                        {
                            flag = flag1;
                            if(listener != null)
                            {
                                pxbutton.setOnClickListener(new android.view.View.OnClickListener() {

                                    public void onClick(View view)
                                    {
                                        listener.onClick(dialog, info.getId());
                                        if(builder.isAutoDismiss())
                                            dialog.dismiss();
                                    }

                                    final SbAlertDialogFragment this$0;
                                    final Builder val$builder;
                                    final AlertDialog val$dialog;
                                    final ViewButtonInfo val$info;
                                    final android.content.DialogInterface.OnClickListener val$listener;

            
            {
                this$0 = SbAlertDialogFragment.this;
                listener = onclicklistener;
                dialog = alertdialog;
                info = viewbuttoninfo;
                builder = builder1;
                super();
            }
                                }
);
                                flag = flag1;
                            }
                        }
                    }
                }
            } while(true);
            if(i == 1)
            {
                dialog = (LinearLayout)builder.getView().findViewById(jp.pixela.pxlibs.R.id.layout_dialog_button);
                if(dialog != null)
                    dialog.setWeightSum(2.0F);
            }
        }
    }

    private static final String SERIAL_KEY_BUILDER = "builder";
    private static SbAlertDialogFragment mDialogFragment;
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

    // Unreferenced inner class jp/pixela/pxlibs/android/views/dialogs/SbAlertDialogFragment$Builder$1

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
