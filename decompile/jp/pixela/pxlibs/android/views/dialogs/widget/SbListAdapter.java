// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.android.views.dialogs.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class SbListAdapter extends ArrayAdapter
{

    public SbListAdapter(Context context, int i, List list, Typeface typeface)
    {
        super(context, i, list);
        mTypeface = null;
        mAreaName = null;
        mAreaName = (ArrayList)list;
        layoutInflater_ = (LayoutInflater)context.getSystemService("layout_inflater");
        mTypeface = typeface;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        viewgroup = view;
        if(view == null)
            viewgroup = layoutInflater_.inflate(jp.pixela.pxlibs.R.layout.adapter_sb_list, null);
        view = (TextView)viewgroup.findViewById(0x1020014);
        view.setText((CharSequence)mAreaName.get(i));
        if(mTypeface != null)
            view.setTypeface(mTypeface);
        return viewgroup;
    }

    private LayoutInflater layoutInflater_;
    ArrayList mAreaName;
    private Typeface mTypeface;
}
