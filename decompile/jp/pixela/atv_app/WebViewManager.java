// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.atv_app;

import android.app.Activity;
import android.view.KeyEvent;
import jp.pixela.atv_app.common.PxLog;

// Referenced classes of package jp.pixela.atv_app:
//            ContinuousKeyEventManager, WebViewWrapper, KeyEventConverter

class WebViewManager
{

    WebViewManager(KeyEventConverter keyeventconverter)
    {
        keyEventConverter = keyeventconverter;
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
        keyevent = keyEventConverter.convert(keyevent);
        if(keyevent == null)
        {
            PxLog.v(TAG, "dispatchKeyEvent() out. keyEvent == null by keyEventConverter");
            return true;
        } else
        {
            boolean flag = webViewWrapper.dispatchKeyEvent(activity, keyevent);
            activity = TAG;
            keyevent = new StringBuilder();
            keyevent.append("dispatchKeyEvent() out. isConsumed=");
            keyevent.append(flag);
            PxLog.v(activity, keyevent.toString());
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

    void initializeWebView(Activity activity)
    {
        continuousKeyEventManager.initialize(activity);
        webViewWrapper.initialize(activity);
    }

    void loadUrl(Activity activity, String s)
    {
        webViewWrapper.loadUrl(activity, s);
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
        "file:///android_asset/license/externals/NOTICE.html", "file:///android_asset/license/externals/pixela_app_oss_licenses.html", "file:///android_asset/license/externals/smart_speaker_oss_licenses.html", "file:///android_asset/license/externals/webview_licenses.notice", "file:///android_asset/license/externals/nfbe_oss_license.html"
    };
    private static final String TAG = "WebViewManager";
    private final ContinuousKeyEventManager continuousKeyEventManager = new ContinuousKeyEventManager();
    private final KeyEventConverter keyEventConverter;
    private final WebViewWrapper webViewWrapper = new WebViewWrapper();

}
