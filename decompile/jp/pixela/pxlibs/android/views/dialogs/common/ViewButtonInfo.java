// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.android.views.dialogs.common;

import jp.pixela.pxlibs.android.views.buttons.PxButton;

public class ViewButtonInfo
{

    public ViewButtonInfo(PxButton pxbutton, int i, CharSequence charsequence, android.content.DialogInterface.OnClickListener onclicklistener)
    {
        mButton = pxbutton;
        mId = i;
        mText = charsequence;
        mListener = onclicklistener;
    }

    public PxButton getButton()
    {
        return mButton;
    }

    public android.content.DialogInterface.OnClickListener getClickListener()
    {
        return mListener;
    }

    public int getId()
    {
        return mId;
    }

    public CharSequence getText()
    {
        return mText;
    }

    private PxButton mButton;
    private int mId;
    private android.content.DialogInterface.OnClickListener mListener;
    private CharSequence mText;
}
