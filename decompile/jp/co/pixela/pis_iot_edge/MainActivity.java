// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.co.pixela.pis_iot_edge;

import android.app.Activity;
import android.content.*;
import android.net.Uri;
import android.os.*;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.*;
import java.util.concurrent.*;
import jp.co.pixela.pis_iot_edge.common.IntentHelper;
import jp.co.pixela.pis_iot_edge.common.PxLog;

// Referenced classes of package jp.co.pixela.pis_iot_edge:
//            WebViewManager, IoTEdgeService, Token, Api

public class MainActivity extends Activity
{

    public MainActivity()
    {
        intentOnResume = null;
        needsToClearHistory = false;
        deviceFlowCode_ = null;
    }

    private void acquireAuthorizationToken(final String url, final String expected_state, final String deviceName)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("url=");
        stringbuilder.append(url);
        PxLog.d("PisIotEdgeMain", stringbuilder.toString());
        url = new Runnable() {

            public void run()
            {
                byte byte0 = 2;
                Object obj;
                obj = Uri.parse(url);
                String s = ((Uri) (obj)).getQueryParameter("state");
                if(s.equals(expected_state))
                    break MISSING_BLOCK_LABEL_95;
                obj = JVM INSTR new #56  <Class StringBuilder>;
                ((StringBuilder) (obj)).StringBuilder();
                ((StringBuilder) (obj)).append("state not matched, ");
                ((StringBuilder) (obj)).append(expected_state);
                ((StringBuilder) (obj)).append(" / ");
                ((StringBuilder) (obj)).append(s);
                PxLog.e("PisIotEdgeMain", ((StringBuilder) (obj)).toString());
                handler_.post(2. new Runnable() {

                    public void run()
                    {
                        Object obj = new StringBuilder();
                        ((StringBuilder) (obj)).append(MainActivity.PACKAGE_NAME_PREFIX);
                        ((StringBuilder) (obj)).append("ACTION_PIS_IOT_EDGE_CONTROL_RESULT");
                        obj = new Intent(((StringBuilder) (obj)).toString());
                        ((Intent) (obj)).setPackage("jp.pixela.stationtv.xit");
                        ((Intent) (obj)).putExtra("EXTRA_COMMAND", 1);
                        ((Intent) (obj)).putExtra("EXTRA_RESULT", result2);
                        sendBroadcast(((Intent) (obj)));
                        StringBuilder stringbuilder = new StringBuilder();
                        stringbuilder.append("acquireAuthorizationToken sendBroadcast intent=");
                        stringbuilder.append(IntentHelper.dump(((Intent) (obj))));
                        PxLog.v("PisIotEdgeMain", stringbuilder.toString());
                    }

                    final _cls12 this$1;
                    final int val$result2;

            
            {
                this$1 = final__pcls12;
                result2 = I.this;
                super();
            }
                }
);
                return;
                obj = ((Uri) (obj)).getQueryParameter("code");
                Api api = JVM INSTR new #94  <Class Api>;
                api.Api(getApplicationContext());
                obj = api.acquireToken(((String) (obj)));
                byte byte1;
                byte1 = byte0;
                if(obj == null)
                    break MISSING_BLOCK_LABEL_179;
                boolean flag;
                Parcel parcel = Parcel.obtain();
                parcel.writeString(((Token) (obj)).toString());
                parcel.writeString(deviceName);
                flag = binder_.transact(1, parcel, null, 0);
                byte1 = byte0;
                if(flag)
                    byte1 = 1;
                Object obj1;
                obj1 = handler_;
                obj = byte1. new _cls1();
                break MISSING_BLOCK_LABEL_228;
                obj1;
                break MISSING_BLOCK_LABEL_235;
                obj1;
                ((Exception) (obj1)).printStackTrace();
                obj1 = handler_;
                obj = 2. new _cls1();
                ((Handler) (obj1)).post(((Runnable) (obj)));
                return;
                handler_.post(2. new _cls1());
                throw obj1;
            }

            final MainActivity this$0;
            final String val$deviceName;
            final String val$expected_state;
            final String val$url;

            
            {
                this$0 = MainActivity.this;
                url = s;
                expected_state = s1;
                deviceName = s2;
                super();
            }
        }
;
        if(binder_ != null)
            pool.submit(url);
        else
            pending_pool.add(url);
    }

    private void bindService()
    {
        Intent intent = new Intent(getApplicationContext(), jp/co/pixela/pis_iot_edge/IoTEdgeService);
        ComponentName componentname;
        if(android.os.Build.VERSION.SDK_INT < 26)
            componentname = startService(intent);
        else
            componentname = startForegroundService(intent);
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("start IoTEdgeService intent=");
        stringbuilder.append(IntentHelper.dump(intent));
        PxLog.v("PisIotEdgeMain", stringbuilder.toString());
        if(componentname == null)
            return;
        if(!bindService(intent, connection_, 0))
            PxLog.e("PisIotEdgeMain", "Failed to bind IoTEdgeService.");
    }

    private void checkTokenFailure()
    {
        handler_.post(new Runnable() {

            public void run()
            {
                timerExecutor.shutdown();
                hideWebView();
                sendResult("checkTokenTask", 1, 2);
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
);
    }

    private void checkTokenSuccess(final String deviceName, final Token token)
    {
        handler_.post(new Runnable() {

            public void run()
            {
                byte byte0 = 2;
                Parcel parcel;
                parcel = Parcel.obtain();
                parcel.writeString(token.toString());
                parcel.writeString(deviceName);
                if(binder_.transact(1, parcel, null, 0))
                    byte0 = 1;
                try
                {
                    timerExecutor.shutdown();
                    hideWebView();
                    sendResult("checkTokenTask", 1, byte0);
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                }
                return;
            }

            final MainActivity this$0;
            final String val$deviceName;
            final Token val$token;

            
            {
                this$0 = MainActivity.this;
                token = token1;
                deviceName = s;
                super();
            }
        }
);
    }

    private Runnable checkTokenTask(final String deviceName)
    {
        return new Runnable() {

            public void run()
            {
label0:
                {
                    PxLog.v("PisIotEdgeMain", "getTokenWithDeviceFlow...");
                    Object obj = JVM INSTR new #40  <Class Api>;
                    ((Api) (obj)).Api(getApplicationContext());
                    obj = ((Api) (obj)).getTokenWithDeviceFlow(deviceFlowCode_.deviceCode);
                    StringBuilder stringbuilder = JVM INSTR new #62  <Class StringBuilder>;
                    stringbuilder.StringBuilder();
                    stringbuilder.append("token:");
                    stringbuilder.append(obj);
                    PxLog.v("PisIotEdgeMain", stringbuilder.toString());
                    if(((Api.TokenResult) (obj)).status != 200)
                    {
                        checkTokenFailure();
                        break label0;
                    }
                    try
                    {
                        if(((Api.TokenResult) (obj)).token != null)
                            checkTokenSuccess(deviceName, ((Api.TokenResult) (obj)).token);
                    }
                    catch(Exception exception)
                    {
                        exception.printStackTrace();
                    }
                }
            }

            final MainActivity this$0;
            final String val$deviceName;

            
            {
                this$0 = MainActivity.this;
                deviceName = s;
                super();
            }
        }
;
    }

    private void clearAuthorizationToken()
    {
        Runnable runnable = new Runnable() {

            public void run()
            {
                byte byte0 = 2;
                boolean flag;
                Parcel parcel = Parcel.obtain();
                flag = binder_.transact(2, parcel, null, 0);
                Object obj;
                Handler handler;
                if(flag)
                    byte0 = 1;
                handler = handler_;
                obj = byte0. new Runnable() {

                    public void run()
                    {
                        Object obj = new StringBuilder();
                        ((StringBuilder) (obj)).append(MainActivity.PACKAGE_NAME_PREFIX);
                        ((StringBuilder) (obj)).append("ACTION_PIS_IOT_EDGE_CONTROL_RESULT");
                        obj = new Intent(((StringBuilder) (obj)).toString());
                        ((Intent) (obj)).setPackage("jp.pixela.stationtv.xit");
                        ((Intent) (obj)).putExtra("EXTRA_COMMAND", 2);
                        ((Intent) (obj)).putExtra("EXTRA_RESULT", result2);
                        sendBroadcast(((Intent) (obj)));
                        StringBuilder stringbuilder = new StringBuilder();
                        stringbuilder.append("clearAuthorizationToken sendBroadcast intent=");
                        stringbuilder.append(IntentHelper.dump(((Intent) (obj))));
                        PxLog.v("PisIotEdgeMain", stringbuilder.toString());
                    }

                    final _cls11 this$1;
                    final int val$result2;

            
            {
                this$1 = final__pcls11;
                result2 = I.this;
                super();
            }
                }
;
                break MISSING_BLOCK_LABEL_79;
                obj;
                break MISSING_BLOCK_LABEL_87;
                obj;
                ((Exception) (obj)).printStackTrace();
                handler = handler_;
                obj = 2. new _cls1();
                handler.post(((Runnable) (obj)));
                return;
                handler_.post(2. new _cls1());
                throw obj;
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
;
        if(binder_ != null)
            pool.submit(runnable);
        else
            pending_pool.add(runnable);
    }

    private Runnable fetchUserCodeTask(final Runnable completeTask)
    {
        return new Runnable() {

            public void run()
            {
label0:
                {
                    MainActivity mainactivity = MainActivity.this;
                    Api api = JVM INSTR new #30  <Class Api>;
                    api.Api(getApplicationContext());
                    mainactivity.deviceFlowCode_ = api.getDeviceFlowCode();
                    if(deviceFlowCode_ == null)
                    {
                        sendResult("fetchUserCodeTask", 1, 2);
                        break label0;
                    }
                    try
                    {
                        handler_.post(completeTask);
                    }
                    catch(Exception exception)
                    {
                        exception.printStackTrace();
                        sendResult("fetchUserCodeTask", 1, 2);
                    }
                }
            }

            final MainActivity this$0;
            final Runnable val$completeTask;

            
            {
                this$0 = MainActivity.this;
                completeTask = runnable;
                super();
            }
        }
;
    }

    private void getAuthorizationToken(final String deviceName)
    {
        deviceName = new Runnable() {

            public void run()
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                if(binder_.transact(0, parcel, parcel1, 0))
                {
                    boolean flag;
                    if(parcel1.readInt() != 0)
                        flag = true;
                    else
                        flag = false;
                    if(flag)
                    {
                        try
                        {
                            Object obj = Parcel.obtain();
                            ((Parcel) (obj)).writeString("");
                            ((Parcel) (obj)).writeString(deviceName);
                            binder_.transact(1, ((Parcel) (obj)), null, 0);
                            obj = JVM INSTR new #59  <Class Intent>;
                            StringBuilder stringbuilder = JVM INSTR new #61  <Class StringBuilder>;
                            stringbuilder.StringBuilder();
                            stringbuilder.append(MainActivity.PACKAGE_NAME_PREFIX);
                            stringbuilder.append("ACTION_PIS_IOT_EDGE_CONTROL_RESULT");
                            ((Intent) (obj)).Intent(stringbuilder.toString());
                            ((Intent) (obj)).setPackage("jp.pixela.stationtv.xit");
                            ((Intent) (obj)).putExtra("EXTRA_COMMAND", 1);
                            ((Intent) (obj)).putExtra("EXTRA_RESULT", 1);
                            sendBroadcast(((Intent) (obj)));
                            stringbuilder = JVM INSTR new #61  <Class StringBuilder>;
                            stringbuilder.StringBuilder();
                            stringbuilder.append("getAuthorizationToken sendBroadcast intent=");
                            stringbuilder.append(IntentHelper.dump(((Intent) (obj))));
                            PxLog.v("PisIotEdgeMain", stringbuilder.toString());
                            return;
                        }
                        catch(Exception exception)
                        {
                            exception.printStackTrace();
                        }
                        break MISSING_BLOCK_LABEL_235;
                    }
                }
                if(binder_ != null)
                {
                    pool.submit(fetch_user_code_task);
                    break MISSING_BLOCK_LABEL_235;
                }
                pending_pool.add(fetch_user_code_task);
            }

            final MainActivity this$0;
            final String val$deviceName;
            final Runnable val$fetch_user_code_task;

            
            {
                this$0 = MainActivity.this;
                deviceName = s;
                fetch_user_code_task = runnable;
                super();
            }
        }
;
        if(binder_ != null)
            pool.submit(deviceName);
        else
            pending_pool.add(deviceName);
    }

    private void hideWebView()
    {
        View view = findViewById(R.id.controller);
        View view1 = findViewById(R.id.web_view);
        webViewManager.loadUrl(this, "about:blank");
        view1.setVisibility(8);
        if(view.getVisibility() != 8)
            view.bringToFront();
    }

    private boolean processIntent(Intent intent)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("processIntent intent=");
        stringbuilder.append(IntentHelper.dump(intent));
        PxLog.v("PisIotEdgeMain", stringbuilder.toString());
        String s1 = intent.getAction();
        View view = findViewById(R.id.controller);
        stringbuilder = new StringBuilder();
        stringbuilder.append(PACKAGE_NAME_PREFIX);
        stringbuilder.append("ACTION_PIS_IOT_EDGE_CONTROL");
        if(!stringbuilder.toString().equals(s1))
        {
            view.setVisibility(0);
            return false;
        }
        view.setVisibility(8);
        int i = intent.getIntExtra("EXTRA_COMMAND", -1);
        if(i == -1)
            return false;
        switch(i)
        {
        default:
            return false;

        case 2: // '\002'
            clearAuthorizationToken();
            break;

        case 1: // '\001'
            String s = intent.getStringExtra("DEVICE_NAME");
            intent = s;
            if(s == null)
                intent = "";
            getAuthorizationToken(intent);
            break;

        case 0: // '\0'
            finish();
            break;
        }
        return true;
    }

    private void sendResult(String s, int i, int j)
    {
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append(PACKAGE_NAME_PREFIX);
        ((StringBuilder) (obj)).append("ACTION_PIS_IOT_EDGE_CONTROL_RESULT");
        obj = new Intent(((StringBuilder) (obj)).toString());
        ((Intent) (obj)).setPackage("jp.pixela.stationtv.xit");
        ((Intent) (obj)).putExtra("EXTRA_COMMAND", i);
        ((Intent) (obj)).putExtra("EXTRA_RESULT", j);
        sendBroadcast(((Intent) (obj)));
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(s);
        stringbuilder.append(" sendBroadcast intent=");
        stringbuilder.append(IntentHelper.dump(((Intent) (obj))));
        PxLog.v("PisIotEdgeMain", stringbuilder.toString());
    }

    private void showMessage(String s)
    {
        TextView textview = (TextView)findViewById(R.id.log);
        if(textview != null)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(textview.getText());
            stringbuilder.append("\n");
            stringbuilder.append(s);
            textview.setText(stringbuilder.toString());
        }
        PxLog.d("PisIotEdgeMain", s);
    }

    private void showMessageInHandler(final String msg)
    {
        handler_.post(new Runnable() {

            public void run()
            {
                showMessage(msg);
            }

            final MainActivity this$0;
            final String val$msg;

            
            {
                this$0 = MainActivity.this;
                msg = s;
                super();
            }
        }
);
    }

    private void showWebView()
    {
        View view = findViewById(R.id.web_view);
        view.setVisibility(0);
        view.bringToFront();
        view.requestFocus(130);
    }

    private void unbindService()
    {
        unbindService(connection_);
    }

    public void onBackPressed()
    {
        View view = findViewById(R.id.controller);
        if(findViewById(R.id.web_view).getVisibility() != 0)
        {
            super.onBackPressed();
            return;
        }
        if(webViewManager.canGoBack(this))
        {
            webViewManager.goBack(this);
            return;
        }
        hideWebView();
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append(PACKAGE_NAME_PREFIX);
        ((StringBuilder) (obj)).append("ACTION_PIS_IOT_EDGE_CONTROL_RESULT");
        obj = new Intent(((StringBuilder) (obj)).toString());
        ((Intent) (obj)).setPackage("jp.pixela.stationtv.xit");
        ((Intent) (obj)).putExtra("EXTRA_COMMAND", 1);
        ((Intent) (obj)).putExtra("EXTRA_RESULT", 4);
        sendBroadcast(((Intent) (obj)));
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("onBackPressed sendBroadcast intent=");
        stringbuilder.append(IntentHelper.dump(((Intent) (obj))));
        PxLog.v("PisIotEdgeMain", stringbuilder.toString());
        if(view.getVisibility() == 8)
            super.onBackPressed();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        bundle = new StringBuilder();
        bundle.append(getApplicationContext().getPackageName());
        bundle.append(".");
        PACKAGE_NAME_PREFIX = bundle.toString();
        bundle = new StringBuilder();
        bundle.append("onCreate() PACKAGE_NAME_PREFIX=");
        bundle.append(PACKAGE_NAME_PREFIX);
        PxLog.v("PisIotEdgeMain", bundle.toString());
        handler_ = new Handler();
        setContentView(R.layout.main_activity);
        ((Button)findViewById(R.id.get_auth_token)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                getAuthorizationToken("");
                PxLog.v("PisIotEdgeMain", "get_auth_token onClick");
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
);
        ((TextView)findViewById(R.id.log)).setMovementMethod(new ScrollingMovementMethod());
        bundle = new IntentFilter();
        bundle.addAction("jp.co.pixela.pis_iot_edge.BROADCAST");
        bundle.addAction("jp.co.pixela.pis_iot_edge.LOG");
        registerReceiver(receiver_, bundle);
        IntentFilter intentfilter = new IntentFilter();
        bundle = new StringBuilder();
        bundle.append(PACKAGE_NAME_PREFIX);
        bundle.append("ACTION_PIS_IOT_EDGE_CONTROL");
        intentfilter.addAction(bundle.toString());
        registerReceiver(pisIotEdgeControlBroadcastReceiver_, intentfilter);
        bindService();
        webViewManager.initializeWebView(this);
        intentOnResume = getIntent();
        bundle = new StringBuilder();
        bundle.append("onCreate() intentOnResume=");
        bundle.append(IntentHelper.dump(intentOnResume));
        PxLog.v("PisIotEdgeMain", bundle.toString());
    }

    protected void onDestroy()
    {
        PxLog.v("PisIotEdgeMain", "onDestroy() in");
        unregisterReceiver(pisIotEdgeControlBroadcastReceiver_);
        unregisterReceiver(receiver_);
        try
        {
            pool.shutdown();
            if(!pool.awaitTermination(60L, TimeUnit.SECONDS))
                pool.shutdownNow();
            timerExecutor.shutdown();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        unbindService();
        webViewManager.finalizeWebView(this);
        super.onDestroy();
        PxLog.v("PisIotEdgeMain", "onDestroy() out");
    }

    protected void onNewIntent(Intent intent)
    {
        PxLog.v("PisIotEdgeMain", "onNewIntent() in");
        super.onNewIntent(intent);
        intentOnResume = intent;
        intent = new StringBuilder();
        intent.append("onNewIntent() intentOnResume=");
        intent.append(IntentHelper.dump(intentOnResume));
        PxLog.v("PisIotEdgeMain", intent.toString());
        PxLog.v("PisIotEdgeMain", "onNewIntent() out");
    }

    protected void onResume()
    {
        PxLog.v("PisIotEdgeMain", "onResume() in");
        super.onResume();
        if(intentOnResume != null)
        {
            boolean flag = processIntent(intentOnResume);
            intentOnResume = null;
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("onResume() processIntent ret=");
            stringbuilder.append(flag);
            PxLog.v("PisIotEdgeMain", stringbuilder.toString());
        }
        PxLog.v("PisIotEdgeMain", "onResume() out");
    }

    static final boolean $assertionsDisabled = false;
    public static final String ACTION_PIS_IOT_EDGE_CONTROL = "ACTION_PIS_IOT_EDGE_CONTROL";
    public static final String ACTION_PIS_IOT_EDGE_CONTROL_RESULT = "ACTION_PIS_IOT_EDGE_CONTROL_RESULT";
    public static final String EXTRA_COMMAND = "EXTRA_COMMAND";
    public static final String EXTRA_DEVICE_NAME = "DEVICE_NAME";
    public static final int EXTRA_IOT_EDGE_COMMAND_DISABLE = 2;
    public static final int EXTRA_IOT_EDGE_COMMAND_ENABLE = 1;
    public static final int EXTRA_IOT_EDGE_COMMAND_FINISH_ACTIVITY = 0;
    public static final int EXTRA_IOT_EDGE_COMMAND_INVALID = -1;
    public static final int EXTRA_IOT_EDGE_RESULT_CANCEL = 4;
    public static final int EXTRA_IOT_EDGE_RESULT_FAILURE = 2;
    public static final int EXTRA_IOT_EDGE_RESULT_NOT_SUPPORTED_URL = 3;
    public static final int EXTRA_IOT_EDGE_RESULT_SUCCESS = 1;
    public static final String EXTRA_RESULT = "EXTRA_RESULT";
    private static String PACKAGE_NAME_PREFIX;
    private static final String TAG = "PisIotEdgeMain";
    private IBinder binder_;
    private final ServiceConnection connection_ = new ServiceConnection() {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            PxLog.d("PisIotEdgeMain", "IoTEdgeService is connected.");
            binder_ = ibinder;
            for(componentname = pending_pool.iterator(); componentname.hasNext(); pool.submit(ibinder))
                ibinder = (Runnable)componentname.next();

            pending_pool.clear();
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            PxLog.d("PisIotEdgeMain", "IoTEdgeService is disconnected.");
            binder_ = null;
        }

        final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
    }
;
    private Api.DeviceFlowCode deviceFlowCode_;
    private Handler handler_;
    private Intent intentOnResume;
    private boolean needsToClearHistory;
    private final List pending_pool = new ArrayList();
    private final BroadcastReceiver pisIotEdgeControlBroadcastReceiver_ = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent)
        {
            PxLog.v("PisIotEdgeMain", "onReceive() in");
            boolean flag = processIntent(intent);
            context = new StringBuilder();
            context.append("onReceive() processIntent ret=");
            context.append(flag);
            PxLog.v("PisIotEdgeMain", context.toString());
            PxLog.v("PisIotEdgeMain", "onReceive() out");
        }

        static final boolean $assertionsDisabled = false;
        final MainActivity this$0;


            
            {
                this$0 = MainActivity.this;
                super();
            }
    }
;
    private final ExecutorService pool = Executors.newSingleThreadExecutor();
    private final BroadcastReceiver receiver_ = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent)
        {
            context = new StringBuilder();
            context.append("onReceive intent=");
            context.append(IntentHelper.dump(intent));
            PxLog.v("PisIotEdgeMain", context.toString());
            context = intent.getAction();
            if("jp.co.pixela.pis_iot_edge.BROADCAST".equals(context))
            {
                int i = intent.getIntExtra("CONTROL", -1);
                if(i != 100)
                {
                    if(i != 102)
                    {
                        if(i != 200)
                        {
                            switch(i)
                            {
                            case 2: // '\002'
                                context = intent.getStringExtra("BROADCASTING");
                                int ai[] = intent.getIntArrayExtra("VALUES");
                                intent = MainActivity.this;
                                StringBuilder stringbuilder = new StringBuilder();
                                stringbuilder.append("Receive SelectChannel: Broadcasting=");
                                stringbuilder.append(context);
                                stringbuilder.append(", values=");
                                stringbuilder.append(Arrays.toString(ai));
                                intent.showMessageInHandler(stringbuilder.toString());
                                break;

                            case 1: // '\001'
                                context = intent.getStringExtra("BROADCASTING");
                                int j = intent.getIntExtra("VALUE", 0);
                                MainActivity mainactivity = MainActivity.this;
                                intent = new StringBuilder();
                                intent.append("Receive OneTouchSelectChannel: Broadcasting=");
                                intent.append(context);
                                intent.append(", value=");
                                intent.append(j);
                                mainactivity.showMessageInHandler(intent.toString());
                                break;

                            case 0: // '\0'
                                int k = intent.getIntExtra("VALUE", 0);
                                context = MainActivity.this;
                                intent = new StringBuilder();
                                intent.append("Receive UpDownSelectChannel: value=");
                                intent.append(k);
                                context.showMessageInHandler(intent.toString());
                                break;
                            }
                        } else
                        {
                            boolean flag = intent.getBooleanExtra("VALUE", false);
                            context = MainActivity.this;
                            intent = new StringBuilder();
                            intent.append("Receive Power: value=");
                            intent.append(flag);
                            context.showMessageInHandler(intent.toString());
                        }
                    } else
                    {
                        int l = intent.getIntExtra("VALUE", 0);
                        intent = MainActivity.this;
                        context = new StringBuilder();
                        context.append("Receive AbsoluteVolume: value=");
                        context.append(l);
                        intent.showMessageInHandler(context.toString());
                    }
                } else
                {
                    int i1 = intent.getIntExtra("VALUE", 0);
                    intent = MainActivity.this;
                    context = new StringBuilder();
                    context.append("Receive UpDownVolume: value=");
                    context.append(i1);
                    intent.showMessageInHandler(context.toString());
                }
            } else
            if("jp.co.pixela.pis_iot_edge.LOG".equals(context))
            {
                context = intent.getStringExtra("LOG_MESSAGE");
                showMessageInHandler(context);
            }
        }

        final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
    }
;
    private final ScheduledExecutorService timerExecutor = Executors.newSingleThreadScheduledExecutor();
    private final WebViewManager webViewManager = new WebViewManager();




/*
    static IBinder access$002(MainActivity mainactivity, IBinder ibinder)
    {
        mainactivity.binder_ = ibinder;
        return ibinder;
    }

*/









/*
    static boolean access$1502(MainActivity mainactivity, boolean flag)
    {
        mainactivity.needsToClearHistory = flag;
        return flag;
    }

*/











/*
    static Api.DeviceFlowCode access$602(MainActivity mainactivity, Api.DeviceFlowCode deviceflowcode)
    {
        mainactivity.deviceFlowCode_ = deviceflowcode;
        return deviceflowcode;
    }

*/




    // Unreferenced inner class jp/co/pixela/pis_iot_edge/MainActivity$9

/* anonymous class */
    class _cls9
        implements Runnable
    {

        public void run()
        {
            try
            {
                long l = (long)(Math.random() * 4294967296D);
                Object obj = JVM INSTR new #40  <Class Api>;
                ((Api) (obj)).Api(getApplicationContext());
                obj = ((Api) (obj)).getRedirectUri();
                Object obj1 = JVM INSTR new #40  <Class Api>;
                ((Api) (obj1)).Api(getApplicationContext());
                obj1 = ((Api) (obj1)).getAuthorizeUriWithDeviceFlow(deviceFlowCode_.userCode);
                Object obj2 = JVM INSTR new #66  <Class StringBuilder>;
                ((StringBuilder) (obj2)).StringBuilder();
                ((StringBuilder) (obj2)).append("authorize_uri=");
                ((StringBuilder) (obj2)).append(((String) (obj1)));
                PxLog.d("PisIotEdgeMain", ((StringBuilder) (obj2)).toString());
                obj2 = webViewManager;
                MainActivity mainactivity = MainActivity.this;
                jp.co.pixela.pis_iot_edge.common.WebViewWrapper.WebViewClientWrapper webviewclientwrapper = JVM INSTR new #13  <Class MainActivity$9$1>;
                ((_cls1) (obj)).String.valueOf(l). _cls1();
                ((WebViewManager) (obj2)).setWebViewClientWrapper(mainactivity, webviewclientwrapper);
                webViewManager.loadUrl(MainActivity.this, ((String) (obj1)));
                needsToClearHistory = true;
                showWebView();
                timerExecutor.scheduleWithFixedDelay(checkTokenTask(deviceName), 10L, 10L, TimeUnit.SECONDS);
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
        }

        final MainActivity this$0;
        final String val$deviceName;

            
            {
                this$0 = MainActivity.this;
                deviceName = s;
                super();
            }
    }


    // Unreferenced inner class jp/co/pixela/pis_iot_edge/MainActivity$9$1

/* anonymous class */
    class _cls1
        implements jp.co.pixela.pis_iot_edge.common.WebViewWrapper.WebViewClientWrapper
    {

        public void onPageFinished()
        {
            if(needsToClearHistory)
            {
                needsToClearHistory = false;
                webViewManager.clearHistory(_fld0);
            }
        }

        public void onReceivedError(String s, String s1)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("onReceiveError: ");
            stringbuilder.append(s);
            stringbuilder.append(", error=");
            stringbuilder.append(s1);
            PxLog.e("PisIotEdgeMain", stringbuilder.toString());
            hideWebView();
            s = new StringBuilder();
            s.append(MainActivity.PACKAGE_NAME_PREFIX);
            s.append("ACTION_PIS_IOT_EDGE_CONTROL_RESULT");
            s1 = new Intent(s.toString());
            s1.setPackage("jp.pixela.stationtv.xit");
            s1.putExtra("EXTRA_COMMAND", 1);
            s1.putExtra("EXTRA_RESULT", 2);
            sendBroadcast(s1);
            s = new StringBuilder();
            s.append("onReceivedError sendBroadcast intent=");
            s.append(IntentHelper.dump(s1));
            PxLog.v("PisIotEdgeMain", s.toString());
        }

        public boolean shouldOverrideUrlLoading(String s)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("shouldOverrideUrlLoading in. url=");
            stringbuilder.append(s);
            PxLog.v("PisIotEdgeMain", stringbuilder.toString());
            if(s.startsWith(redirectUri))
            {
                hideWebView();
                acquireAuthorizationToken(s, state, deviceName);
                return true;
            }
            if((new Api(getApplicationContext())).isSupported(s))
            {
                return false;
            } else
            {
                hideWebView();
                s = new StringBuilder();
                s.append(MainActivity.PACKAGE_NAME_PREFIX);
                s.append("ACTION_PIS_IOT_EDGE_CONTROL_RESULT");
                Intent intent = new Intent(s.toString());
                intent.setPackage("jp.pixela.stationtv.xit");
                intent.putExtra("EXTRA_COMMAND", 1);
                intent.putExtra("EXTRA_RESULT", 3);
                sendBroadcast(intent);
                s = new StringBuilder();
                s.append("shouldOverrideUrlLoading notSupportedURL sendBroadcast intent=");
                s.append(IntentHelper.dump(intent));
                PxLog.v("PisIotEdgeMain", s.toString());
                return true;
            }
        }

        final _cls9 this$1;
        final String val$redirectUri;
        final String val$state;

            
            {
                this$1 = final__pcls9;
                redirectUri = s;
                state = String.this;
                super();
            }
    }

}
