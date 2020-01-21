// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.co.pixela.pis_iot_edge;

import android.app.Activity;
import android.view.KeyEvent;
import jp.co.pixela.pis_iot_edge.common.PxLog;
import jp.co.pixela.pis_iot_edge.common.WebViewWrapper;

class WebViewManager
{

    WebViewManager()
    {
    }

    private boolean goBackIfNeed(Activity activity, KeyEvent keyevent)
    {
        int k;
label0:
        {
            String s = webViewWrapper.getUrl(activity);
            if(s == null)
            {
                PxLog.e(TAG, "dispatchKeyEvent() out. url == null");
                return false;
            }
            String s1 = webViewWrapper.getOriginalUrl(activity);
            String s2 = TAG;
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("dispatchKeyEvent() url=");
            stringbuilder.append(s);
            stringbuilder.append(", originalUrl=");
            stringbuilder.append(s1);
            PxLog.v(s2, stringbuilder.toString());
            String as[] = SPECIAL_URL_LIST;
            int i = as.length;
            k = 0;
            do
            {
                if(k >= i)
                    break;
                if(s.startsWith(as[k]))
                {
                    k = 1;
                    break label0;
                }
                k++;
            } while(true);
            k = 0;
        }
        int j = keyevent.getKeyCode();
        if(j != 4 && j != 67 && j != 111)
            j = 0;
        else
            j = 1;
        if(k != 0 && j && keyevent.getAction() == 0)
        {
            webViewWrapper.goBack(activity);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean canGoBack(Activity activity)
    {
        return webViewWrapper.canGoBack(activity);
    }

    public void clearHistory(Activity activity)
    {
        webViewWrapper.clearHistory(activity);
    }

    boolean dispatchKeyEvent(Activity activity, KeyEvent keyevent)
    {
        PxLog.v(TAG, "dispatchKeyEvent() in.");
        if(keyevent == null)
        {
            PxLog.e(TAG, "dispatchKeyEvent() out. event == null");
            return false;
        }
        boolean flag = goBackIfNeed(activity, keyevent);
        if(flag)
        {
            PxLog.v(TAG, "dispatchKeyEvent() out. by goBackIfNeed");
            return true;
        } else
        {
            keyevent = TAG;
            activity = new StringBuilder();
            activity.append("dispatchKeyEvent() out. isConsumed=");
            activity.append(flag);
            PxLog.v(keyevent, activity.toString());
            return flag;
        }
    }

    void finalizeWebView(Activity activity)
    {
        webViewWrapper.finalize(activity);
    }

    String getUrl(Activity activity)
    {
        return webViewWrapper.getUrl(activity);
    }

    public void goBack(Activity activity)
    {
        webViewWrapper.goBack(activity);
    }

    void initializeWebView(Activity activity)
    {
        webViewWrapper.initialize(activity);
    }

    void loadUrl(Activity activity, String s)
    {
        webViewWrapper.loadUrl(activity, s);
    }

    public void setWebViewClientWrapper(Activity activity, jp.co.pixela.pis_iot_edge.common.WebViewWrapper.WebViewClientWrapper webviewclientwrapper)
    {
        webViewWrapper.setWebViewClientWrapper(activity, webviewclientwrapper);
    }

    void startCheckerMode(Activity activity)
    {
        KeyEvent akeyevent[] = new KeyEvent[2];
        int i = 0;
        akeyevent[0] = new KeyEvent(0, 51);
        akeyevent[1] = new KeyEvent(1, 51);
        for(int j = akeyevent.length; i < j; i++)
        {
            KeyEvent keyevent = akeyevent[i];
            webViewWrapper.dispatchKeyEvent(activity, keyevent);
        }

    }

    private static final String SPECIAL_URL_LIST[] = {
        "file:///android_asset/license/externals/NOTICE.html", "file:///android_asset/license/externals/pixela_app_oss_licenses.html", "file:///android_asset/license/externals/webview_licenses.notice", "file:///android_asset/license/externals/nfbe_oss_license.html"
    };
    private static final String TAG = "WebViewManager";
    private final WebViewWrapper webViewWrapper = new WebViewWrapper();

}
