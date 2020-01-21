// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.view;


public interface WebViewListener
{

    public abstract void onCommandFromWebView(String s);

    public abstract void onLoadFinished(String s);
}
