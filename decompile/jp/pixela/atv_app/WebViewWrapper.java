// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.atv_app;

import android.app.Activity;
import android.view.KeyEvent;
import jp.pixela.atv_app.common.PxLog;
import org.xwalk.core.*;

class WebViewWrapper
{

    WebViewWrapper()
    {
    }

    boolean dispatchKeyEvent(Activity activity, KeyEvent keyevent)
    {
        activity = (XWalkView)activity.findViewById(R.id.web_view);
        if(activity == null)
        {
            PxLog.w(TAG, "dispatchKeyEvent() webView == null");
            return false;
        } else
        {
            boolean flag = activity.dispatchKeyEvent(keyevent);
            keyevent = TAG;
            activity = new StringBuilder();
            activity.append("dispatchKeyEvent() isConsumed=");
            activity.append(flag);
            PxLog.v(keyevent, activity.toString());
            return flag;
        }
    }

    void finalize(Activity activity)
    {
        activity = (XWalkView)activity.findViewById(R.id.web_view);
        if(activity == null)
        {
            PxLog.e(TAG, "finalize() webView == null");
            return;
        } else
        {
            activity.stopLoading();
            activity.onDestroy();
            PxLog.v(TAG, "finalize()");
            return;
        }
    }

    String getOriginalUrl(Activity activity)
    {
        activity = (XWalkView)activity.findViewById(R.id.web_view);
        if(activity == null)
        {
            PxLog.w(TAG, "getOriginalUrl() webView == null");
            return null;
        } else
        {
            activity = activity.getOriginalUrl();
            String s = TAG;
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("getOriginalUrl() url=");
            stringbuilder.append(activity);
            PxLog.v(s, stringbuilder.toString());
            return activity;
        }
    }

    String getUrl(Activity activity)
    {
        activity = (XWalkView)activity.findViewById(R.id.web_view);
        if(activity == null)
        {
            PxLog.w(TAG, "getUrl() webView == null");
            return null;
        } else
        {
            String s = activity.getUrl();
            activity = TAG;
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("getUrl() url=");
            stringbuilder.append(s);
            PxLog.v(activity, stringbuilder.toString());
            return s;
        }
    }

    void goBack(Activity activity)
    {
        activity = (XWalkView)activity.findViewById(R.id.web_view);
        if(activity == null)
        {
            PxLog.w(TAG, "goBack() webView == null");
            return;
        } else
        {
            activity.getNavigationHistory().navigate(org.xwalk.core.XWalkNavigationHistory.Direction.BACKWARD, 1);
            PxLog.v(TAG, "goBack()");
            return;
        }
    }

    void initialize(Activity activity)
    {
        activity = (XWalkView)activity.findViewById(R.id.web_view);
        if(activity == null)
        {
            PxLog.e(TAG, "initialize() webView == null");
            return;
        }
        activity.setBackgroundColor(0);
        if(11 <= android.os.Build.VERSION.SDK_INT)
            activity.setLayerType(1, null);
        activity.getSettings().setJavaScriptEnabled(true);
        activity.getSettings().setLoadWithOverviewMode(true);
        activity.getSettings().setUseWideViewPort(true);
        PxLog.v(TAG, "initialize()");
    }

    void loadUrl(Activity activity, String s)
    {
        activity = (XWalkView)activity.findViewById(R.id.web_view);
        if(activity == null)
        {
            PxLog.w(TAG, "loadUrl() webView == null");
            return;
        } else
        {
            activity.loadUrl(s);
            activity = TAG;
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("loadUrl() url=");
            stringbuilder.append(s);
            PxLog.v(activity, stringbuilder.toString());
            return;
        }
    }

    private static final String TAG = "WebViewWrapper";

}
