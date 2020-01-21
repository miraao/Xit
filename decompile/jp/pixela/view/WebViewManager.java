// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.view;

import android.app.Activity;
import android.view.KeyEvent;
import jp.pixela.common.PxLog;

// Referenced classes of package jp.pixela.view:
//            KeyEventConverter, ContinuousKeyEventManager, WebViewWrapper, WebViewListener

class WebViewManager
{

    WebViewManager()
    {
    }

    private boolean goBackIfNeed(Activity activity, KeyEvent keyevent)
    {
        int j;
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
            j = 0;
            do
            {
                if(j >= i)
                    break;
                if(s.startsWith(as[j]))
                {
                    j = 1;
                    break label0;
                }
                j++;
            } while(true);
            j = 0;
        }
        if(j != 0 && keyevent.getKeyCode() == 4 && keyevent.getAction() == 0)
        {
            webViewWrapper.goBack(activity);
            return true;
        } else
        {
            return false;
        }
    }

    boolean dispatchKeyEvent(Activity activity, KeyEvent keyevent)
    {
        PxLog.v(TAG, "dispatchKeyEvent() in.");
        if(keyevent == null)
        {
            PxLog.e(TAG, "dispatchKeyEvent() out. event == null");
            return false;
        }
        if(continuousKeyEventManager.dispatchKeyEventForDrop(keyevent))
        {
            PxLog.v(TAG, "dispatchKeyEvent() out. by continuousKeyEventManager");
            return true;
        }
        if(goBackIfNeed(activity, keyevent))
        {
            PxLog.v(TAG, "dispatchKeyEvent() out. by goBackIfNeed");
            return true;
        }
        if(keyEventConverter.useAndroidStandard(keyevent))
        {
            PxLog.v(TAG, "dispatchKeyEvent() out. useAndroidStandard");
            return false;
        }
        keyevent = keyEventConverter.convert(keyevent);
        if(keyevent == null)
        {
            PxLog.v(TAG, "dispatchKeyEvent() out. keyEvent == null by keyEventConverter");
            return true;
        } else
        {
            webViewWrapper.dispatchKeyEvent(activity, keyevent);
            PxLog.v(TAG, "dispatchKeyEvent() out.");
            return true;
        }
    }

    public void evaluateJavascript(Activity activity, String s)
    {
        webViewWrapper.evaluateJavascript(activity, s);
    }

    void finalizeWebView(Activity activity)
    {
        webViewWrapper.finalize(activity);
    }

    String getUrl(Activity activity)
    {
        return webViewWrapper.getUrl(activity);
    }

    void initializeWebView(Activity activity, WebViewListener webviewlistener)
    {
        continuousKeyEventManager.initialize(activity);
        webViewWrapper.initialize(activity, webviewlistener);
    }

    void loadUrl(Activity activity, String s)
    {
        webViewWrapper.loadUrl(activity, s);
    }

    void sendMessage(Activity activity, String s)
    {
        webViewWrapper.sendMessage(activity, s);
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
        "file:///android_asset/license/externals/NOTICE.html", "file:///android_asset/license/externals/pixela_app_oss_licenses.html", "file:///android_asset/license/externals/webview_licenses.notice"
    };
    private static final String TAG = "WebViewManager";
    private final ContinuousKeyEventManager continuousKeyEventManager = new ContinuousKeyEventManager();
    private final KeyEventConverter keyEventConverter = new KeyEventConverter();
    private final WebViewWrapper webViewWrapper = new WebViewWrapper();

}
