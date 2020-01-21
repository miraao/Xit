// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.webkit.ValueCallback;
import jp.pixela.common.PxLog;
import org.xwalk.core.*;

// Referenced classes of package jp.pixela.view:
//            WebViewListener

class WebViewWrapper
{
    private final class CustomXWalkResourceClient extends XWalkResourceClient
    {

        public void onLoadFinished(XWalkView xwalkview, String s)
        {
            super.onLoadFinished(xwalkview, s);
            if(mWebViewListener != null)
                mWebViewListener.onLoadFinished(s);
        }

        public boolean shouldOverrideUrlLoading(XWalkView xwalkview, String s)
        {
            if(executeCommand(s))
            {
                xwalkview = WebViewWrapper.TAG;
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append("cancel load url=");
                stringbuilder.append(s);
                PxLog.v(xwalkview, stringbuilder.toString());
                return true;
            } else
            {
                return super.shouldOverrideUrlLoading(xwalkview, s);
            }
        }

        final WebViewWrapper this$0;

        public CustomXWalkResourceClient(XWalkView xwalkview)
        {
            this$0 = WebViewWrapper.this;
            super(xwalkview);
        }
    }


    WebViewWrapper()
    {
        mPacakgeName = null;
        mWebViewListener = null;
    }

    private boolean executeCommand(String s)
    {
        if(s == null)
            return false;
        int i = s.lastIndexOf("app_command/");
        if(i != -1)
        {
            if(mWebViewListener != null)
            {
                s = s.substring(i + "app_command/".length(), s.length());
                mWebViewListener.onCommandFromWebView(s);
            }
            return true;
        } else
        {
            return false;
        }
    }

    private void loadJavascript(XWalkView xwalkview, String s)
    {
        xwalkview.evaluateJavascript(s, new ValueCallback() {

            public volatile void onReceiveValue(Object obj)
            {
                onReceiveValue((String)obj);
            }

            public void onReceiveValue(String s1)
            {
            }

            final WebViewWrapper this$0;

            
            {
                this$0 = WebViewWrapper.this;
                super();
            }
        }
);
    }

    boolean dispatchKeyEvent(Activity activity, KeyEvent keyevent)
    {
        activity = (XWalkView)activity.findViewById(0x7f0800e4);
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

    public void evaluateJavascript(Activity activity, String s)
    {
        activity = (XWalkView)activity.findViewById(0x7f0800e4);
        if(activity == null)
        {
            PxLog.w(TAG, "loadJavascript() webView == null");
            return;
        } else
        {
            loadJavascript(activity, s);
            return;
        }
    }

    void finalize(Activity activity)
    {
        activity = (XWalkView)activity.findViewById(0x7f0800e4);
        if(activity == null)
        {
            PxLog.e(TAG, "finalize() webView == null");
            return;
        } else
        {
            activity.stopLoading();
            PxLog.v(TAG, "finalize()");
            return;
        }
    }

    String getOriginalUrl(Activity activity)
    {
        activity = (XWalkView)activity.findViewById(0x7f0800e4);
        if(activity == null)
        {
            PxLog.w(TAG, "getOriginalUrl() webView == null");
            return null;
        } else
        {
            String s = activity.getOriginalUrl();
            activity = TAG;
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("getOriginalUrl() url=");
            stringbuilder.append(s);
            PxLog.v(activity, stringbuilder.toString());
            return s;
        }
    }

    String getUrl(Activity activity)
    {
        activity = (XWalkView)activity.findViewById(0x7f0800e4);
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

    void goBack(Activity activity)
    {
        activity = (XWalkView)activity.findViewById(0x7f0800e4);
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

    void initialize(Activity activity, WebViewListener webviewlistener)
    {
        XWalkView xwalkview = (XWalkView)activity.findViewById(0x7f0800e4);
        if(xwalkview == null)
        {
            PxLog.e(TAG, "initialize() webView == null");
            return;
        }
        xwalkview.setResourceClient(new CustomXWalkResourceClient(xwalkview));
        xwalkview.setBackgroundColor(0);
        if(11 <= android.os.Build.VERSION.SDK_INT)
            xwalkview.setLayerType(1, null);
        xwalkview.getSettings().setJavaScriptEnabled(true);
        xwalkview.getSettings().setLoadWithOverviewMode(true);
        xwalkview.getSettings().setUseWideViewPort(true);
        mPacakgeName = activity.getApplicationContext().getPackageName();
        mWebViewListener = webviewlistener;
        PxLog.v(TAG, "initialize()");
    }

    void loadUrl(Activity activity, String s)
    {
        activity = (XWalkView)activity.findViewById(0x7f0800e4);
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

    public void sendMessage(Activity activity, String s)
    {
        Object obj = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("sendMessage() message=");
        stringbuilder.append(s);
        PxLog.v(((String) (obj)), stringbuilder.toString());
        obj = new StringBuilder();
        ((StringBuilder) (obj)).append(mPacakgeName);
        ((StringBuilder) (obj)).append(".ACTION_EXECUTE_APP_COMMAND");
        obj = new Intent(((StringBuilder) (obj)).toString());
        ((Intent) (obj)).putExtra("app_command", s);
        activity.sendBroadcast(((Intent) (obj)));
    }

    private static final String ACTION_EXECUTE_APP_COMMAND = ".ACTION_EXECUTE_APP_COMMAND";
    private static final String EXECUTE_APP_COMMAND_KEY = "app_command";
    private static final String TAG = "WebViewWrapper";
    private String mPacakgeName;
    private WebViewListener mWebViewListener;




}
