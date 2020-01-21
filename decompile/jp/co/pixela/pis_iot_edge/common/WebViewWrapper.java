// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.co.pixela.pis_iot_edge.common;

import android.app.Activity;
import android.net.Uri;
import android.view.KeyEvent;
import android.webkit.*;

// Referenced classes of package jp.co.pixela.pis_iot_edge.common:
//            PxLog

public class WebViewWrapper
{
    public static interface WebViewClientWrapper
    {

        public abstract void onPageFinished();

        public abstract void onReceivedError(String s, String s1);

        public abstract boolean shouldOverrideUrlLoading(String s);
    }


    public WebViewWrapper()
    {
    }

    public boolean canGoBack(Activity activity)
    {
        activity = (WebView)activity.findViewById(jp.co.pixela.pis_iot_edge.R.id.web_view);
        if(activity == null)
        {
            PxLog.w(TAG, "canGoBack() webView == null");
            return false;
        } else
        {
            boolean flag = activity.canGoBack();
            activity = TAG;
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("canGoBack() ret=");
            stringbuilder.append(flag);
            PxLog.v(activity, stringbuilder.toString());
            return flag;
        }
    }

    public void clearHistory(Activity activity)
    {
        activity = (WebView)activity.findViewById(jp.co.pixela.pis_iot_edge.R.id.web_view);
        if(activity == null)
        {
            PxLog.w(TAG, "clearHistory() webView == null");
            return;
        } else
        {
            activity.clearHistory();
            return;
        }
    }

    public boolean dispatchKeyEvent(Activity activity, KeyEvent keyevent)
    {
        activity = (WebView)activity.findViewById(jp.co.pixela.pis_iot_edge.R.id.web_view);
        if(activity == null)
        {
            PxLog.w(TAG, "dispatchKeyEvent() webView == null");
            return false;
        } else
        {
            boolean flag = activity.dispatchKeyEvent(keyevent);
            activity = TAG;
            keyevent = new StringBuilder();
            keyevent.append("dispatchKeyEvent() isConsumed=");
            keyevent.append(flag);
            PxLog.v(activity, keyevent.toString());
            return flag;
        }
    }

    public void finalize(Activity activity)
    {
        activity = (WebView)activity.findViewById(jp.co.pixela.pis_iot_edge.R.id.web_view);
        if(activity == null)
        {
            PxLog.e(TAG, "finalize() webView == null");
            return;
        } else
        {
            activity.stopLoading();
            activity.setWebChromeClient(null);
            activity.setWebViewClient(null);
            activity.destroy();
            PxLog.v(TAG, "finalize()");
            return;
        }
    }

    public String getOriginalUrl(Activity activity)
    {
        activity = (WebView)activity.findViewById(jp.co.pixela.pis_iot_edge.R.id.web_view);
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

    public String getUrl(Activity activity)
    {
        activity = (WebView)activity.findViewById(jp.co.pixela.pis_iot_edge.R.id.web_view);
        if(activity == null)
        {
            PxLog.w(TAG, "getUrl() webView == null");
            return null;
        } else
        {
            String s = activity.getUrl();
            String s1 = TAG;
            activity = new StringBuilder();
            activity.append("getUrl() url=");
            activity.append(s);
            PxLog.v(s1, activity.toString());
            return s;
        }
    }

    public void goBack(Activity activity)
    {
        activity = (WebView)activity.findViewById(jp.co.pixela.pis_iot_edge.R.id.web_view);
        if(activity == null)
        {
            PxLog.w(TAG, "goBack() webView == null");
            return;
        } else
        {
            activity.goBack();
            PxLog.v(TAG, "goBack()");
            return;
        }
    }

    public void initialize(Activity activity)
    {
        activity = (WebView)activity.findViewById(jp.co.pixela.pis_iot_edge.R.id.web_view);
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

    public void loadUrl(Activity activity, String s)
    {
        activity = (WebView)activity.findViewById(jp.co.pixela.pis_iot_edge.R.id.web_view);
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

    public void setWebViewClientWrapper(Activity activity, final WebViewClientWrapper clientWrapper)
    {
        activity = (WebView)activity.findViewById(jp.co.pixela.pis_iot_edge.R.id.web_view);
        if(activity == null)
        {
            PxLog.w(TAG, "setWebViewClientWrapper() webView == null");
            return;
        } else
        {
            activity.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView webview, String s)
                {
                    PxLog.v(WebViewWrapper.TAG, "onPageFinished() in");
                    clientWrapper.onPageFinished();
                    super.onPageFinished(webview, s);
                    PxLog.v(WebViewWrapper.TAG, "onPageFinished() out");
                }

                public void onReceivedError(WebView webview, WebResourceRequest webresourcerequest, WebResourceError webresourceerror)
                {
                    PxLog.v(WebViewWrapper.TAG, "onReceivedError() in");
                    clientWrapper.onReceivedError(webresourcerequest.getUrl().toString(), webresourceerror.getDescription().toString());
                    PxLog.v(WebViewWrapper.TAG, "onReceivedError() out");
                }

                public boolean shouldOverrideUrlLoading(WebView webview, WebResourceRequest webresourcerequest)
                {
                    PxLog.v(WebViewWrapper.TAG, "shouldOverrideUrlLoading() in");
                    boolean flag = clientWrapper.shouldOverrideUrlLoading(webresourcerequest.getUrl().toString());
                    webresourcerequest = WebViewWrapper.TAG;
                    webview = new StringBuilder();
                    webview.append("shouldOverrideUrlLoading() out. ret=");
                    webview.append(flag);
                    PxLog.v(webresourcerequest, webview.toString());
                    return flag;
                }

                final WebViewWrapper this$0;
                final WebViewClientWrapper val$clientWrapper;

            
            {
                this$0 = WebViewWrapper.this;
                clientWrapper = webviewclientwrapper;
                super();
            }
            }
);
            PxLog.v(TAG, "setWebViewClientWrapper()");
            return;
        }
    }

    private static final String TAG = "WebViewWrapper";


}
