// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.co.pixela.pis_iot_edge;

import android.app.*;
import android.content.*;
import android.os.*;
import android.preference.PreferenceManager;
import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.DeviceTwin.DeviceMethodCallback;
import com.microsoft.azure.sdk.iot.device.DeviceTwin.DeviceMethodData;
import com.microsoft.azure.sdk.iot.device.*;
import com.microsoft.azure.sdk.iot.device.transport.IotHubConnectionStatus;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import jp.co.pixela.pis_iot_edge.common.EncryptionUtility;
import jp.co.pixela.pis_iot_edge.common.IntentHelper;
import jp.co.pixela.pis_iot_edge.common.PxLog;
import org.json.*;

// Referenced classes of package jp.co.pixela.pis_iot_edge:
//            Device, Token, SymmetricKey, Api

public class IoTEdgeService extends Service
{
    static class ConnectionStatusChangeCallbackMqtt
        implements IotHubConnectionStatusChangeCallback
    {

        public void execute(IotHubConnectionStatus iothubconnectionstatus, IotHubConnectionStatusChangeReason iothubconnectionstatuschangereason, Throwable throwable, Object obj)
        {
            ((IoTEdgeService)obj).handleConnectionStatusChange(iothubconnectionstatus, iothubconnectionstatuschangereason, throwable);
        }

        ConnectionStatusChangeCallbackMqtt()
        {
        }
    }

    static class DeviceMethodCallbackMqtt
        implements DeviceMethodCallback
    {

        public DeviceMethodData call(String s, Object obj, Object obj1)
        {
            return ((IoTEdgeService)obj1).handleDirectMethod(s, obj);
        }

        DeviceMethodCallbackMqtt()
        {
        }
    }

    static class DeviceMethodStatusCallbackMqtt
        implements IotHubEventCallback
    {

        public void execute(IotHubStatusCode iothubstatuscode, Object obj)
        {
            ((IoTEdgeService)obj).handleDirectMethodStatus(iothubstatuscode);
        }

        DeviceMethodStatusCallbackMqtt()
        {
        }
    }

    static class MessageCallbackMqtt
        implements MessageCallback
    {

        public IotHubMessageResult execute(Message message, Object obj)
        {
            ((IoTEdgeService)obj).handleMessage(message.getMessageId(), message.getBytes());
            return IotHubMessageResult.COMPLETE;
        }

        MessageCallbackMqtt()
        {
        }
    }

    private final class PisIotEdgePastDataReceiver extends BroadcastReceiver
    {

        public void onReceive(Context context, Intent intent)
        {
            Object obj;
            obj = intent.getAction();
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("action:");
            stringbuilder.append(((String) (obj)));
            PxLog.v("PisIotEdgeService", stringbuilder.toString());
            StringBuilder stringbuilder1 = JVM INSTR new #29  <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            stringbuilder1.append(IoTEdgeService.PACKAGE_NAME_PREFIX);
            stringbuilder1.append("ACTION_PIS_IOT_EDGE_PAST_DATA");
            if(!stringbuilder1.toString().equals(obj))
                break MISSING_BLOCK_LABEL_394;
            if(mPisIotEdgePastDataTimer != null)
            {
                mPisIotEdgePastDataTimer.cancel();
                mPisIotEdgePastDataTimer = null;
            }
            obj = intent.getStringExtra("pis_iot_edge_device_name");
            context = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit();
            if(obj == null)
                break MISSING_BLOCK_LABEL_191;
            device_name_ = ((String) (obj));
            obj = JVM INSTR new #29  <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            ((StringBuilder) (obj)).append("PisIotEdgePastDataReceiver:device_name : ");
            ((StringBuilder) (obj)).append(device_name_);
            PxLog.v("PisIotEdgeService", ((StringBuilder) (obj)).toString());
            context.putString("pis_iot_edge_device_name", device_name_);
            context.putBoolean("pis_iot_edge_device_updated_data", true);
            context.apply();
            context = intent.getStringExtra("PisIotEdge_TokenJson");
            if(context == null)
                break MISSING_BLOCK_LABEL_394;
            if(context.isEmpty())
                break MISSING_BLOCK_LABEL_394;
            intent = new byte[context.length() / 2];
            int i = 0;
_L2:
            int j;
            if(i >= context.length())
                break; /* Loop/switch isn't completed */
            j = i / 2;
            int k = i + 2;
            intent[j] = (byte)Integer.parseInt(context.substring(i, k), 16);
            i = k;
            if(true) goto _L2; else goto _L1
_L1:
            try
            {
                intent = EncryptionUtility.decrypt(intent);
                context = JVM INSTR new #54  <Class String>;
                context.String(intent, "UTF8");
                Token token = JVM INSTR new #149 <Class Token>;
                intent = JVM INSTR new #151 <Class JSONObject>;
                intent.JSONObject(context);
                token.Token(intent);
                token.save(getApplicationContext());
                if(token.isValid())
                {
                    PxLog.v("PisIotEdgeService", "PisIotEdgePastDataReceiver : start");
                    is_enabled_ = true;
                    start(false, token);
                }
            }
            // Misplaced declaration of an exception variable
            catch(Intent intent)
            {
                context = new StringBuilder();
                context.append("e=");
                context.append(intent);
                PxLog.v("PisIotEdgeService", context.toString());
            }
        }

        final IoTEdgeService this$0;

        private PisIotEdgePastDataReceiver()
        {
            this$0 = IoTEdgeService.this;
            super();
        }
    }

    private class ServiceThread extends Thread
    {

        private Object retry(Callable callable)
            throws Exception
        {
            long l = 0L;
_L2:
            if(l > 6L)
                break; /* Loop/switch isn't completed */
            if(l > 0L && !running((long)Math.pow(2D, l), TimeUnit.SECONDS))
                return null;
            Object obj = callable.call();
            return obj;
            Exception exception;
            exception;
            exception.printStackTrace();
            l++;
            if(true) goto _L2; else goto _L1
_L1:
            return null;
        }

        private boolean running(long l, TimeUnit timeunit)
            throws Exception
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("running in ");
            stringbuilder.append(l);
            stringbuilder.append(" ");
            stringbuilder.append(timeunit.toString());
            PxLog.d("PisIotEdgeService", stringbuilder.toString());
            lock_.lock();
            boolean flag = running_;
            if(!flag)
            {
                lock_.unlock();
                return false;
            }
            condition_.await(l, timeunit);
            flag = running_;
            if(!flag)
            {
                lock_.unlock();
                return false;
            } else
            {
                lock_.unlock();
                return true;
            }
            timeunit;
            lock_.unlock();
            throw timeunit;
        }

        public void abort()
        {
            lock_.lock();
            running_ = false;
            condition_.signalAll();
            lock_.unlock();
        }

        public void reconnect()
        {
            lock_.lock();
            reconnecting_ = true;
            condition_.signalAll();
            lock_.unlock();
        }

        public void run()
        {
            Object obj;
            obj = JVM INSTR new #9   <Class IoTEdgeService$ServiceThread$1>;
            ((_cls1) (obj)).this. _cls1();
            obj = (Token)retry(((Callable) (obj)));
            if(obj == null)
                return;
            long l;
            token_ = ((Token) (obj));
            token_.save(getApplicationContext());
            l = SystemClock.elapsedRealtime();
            obj = JVM INSTR new #101 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            ((StringBuilder) (obj)).append("refresh token at ");
            ((StringBuilder) (obj)).append(l);
            PxLog.d("PisIotEdgeService", ((StringBuilder) (obj)).toString());
            if(deleting_)
            {
                obj = JVM INSTR new #11  <Class IoTEdgeService$ServiceThread$2>;
                ((_cls2) (obj)).this. _cls2();
                retry(((Callable) (obj)));
                PxLog.d("PisIotEdgeService", "delete device");
                Token.remove(getApplicationContext());
                PxLog.d("PisIotEdgeService", "remove token");
                return;
            }
            obj = JVM INSTR new #101 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            ((StringBuilder) (obj)).append("device=");
            ((StringBuilder) (obj)).append(device_.toString());
            PxLog.d("PisIotEdgeService", ((StringBuilder) (obj)).toString());
            obj = JVM INSTR new #13  <Class IoTEdgeService$ServiceThread$3>;
            ((_cls3) (obj)).this. _cls3();
            obj = (Device)retry(((Callable) (obj)));
            if(obj == null)
                return;
            Object obj1;
            PxLog.d("PisIotEdgeService", "create/update device");
            obj1 = JVM INSTR new #101 <Class StringBuilder>;
            ((StringBuilder) (obj1)).StringBuilder();
            ((StringBuilder) (obj1)).append("HostName=");
            ((StringBuilder) (obj1)).append(((Device) (obj)).getHost());
            ((StringBuilder) (obj1)).append(";DeviceId=");
            ((StringBuilder) (obj1)).append(((Device) (obj)).getRegisteredDeviceId());
            ((StringBuilder) (obj1)).append(";SharedAccessKey=");
            ((StringBuilder) (obj1)).append(((Device) (obj)).getSymmetricKey().getPrimaryKey());
            obj1 = ((StringBuilder) (obj1)).toString();
            obj = JVM INSTR new #101 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            ((StringBuilder) (obj)).append("ConnectionString=");
            ((StringBuilder) (obj)).append(((String) (obj1)));
            PxLog.d("PisIotEdgeService", ((StringBuilder) (obj)).toString());
_L4:
            lock_.lock();
            reconnecting_ = false;
            lock_.unlock();
            obj = JVM INSTR new #211 <Class DeviceClient>;
            ((DeviceClient) (obj)).DeviceClient(((String) (obj1)), IotHubClientProtocol.MQTT);
            ((DeviceClient) (obj)).setOperationTimeout(0x7fffffffffffffffL);
            Object obj2 = JVM INSTR new #228 <Class IoTEdgeService$MessageCallbackMqtt>;
            ((MessageCallbackMqtt) (obj2)).MessageCallbackMqtt();
            ((DeviceClient) (obj)).setMessageCallback(((MessageCallback) (obj2)), IoTEdgeService.this);
            obj2 = JVM INSTR new #235 <Class IoTEdgeService$ConnectionStatusChangeCallbackMqtt>;
            ((ConnectionStatusChangeCallbackMqtt) (obj2)).ConnectionStatusChangeCallbackMqtt();
            ((DeviceClient) (obj)).registerConnectionStatusChangeCallback(((IotHubConnectionStatusChangeCallback) (obj2)), IoTEdgeService.this);
            obj2 = JVM INSTR new #15  <Class IoTEdgeService$ServiceThread$4>;
            obj. super();
            retry(((Callable) (obj2)));
            PxLog.d("PisIotEdgeService", "connect device");
            DeviceMethodCallbackMqtt devicemethodcallbackmqtt = JVM INSTR new #247 <Class IoTEdgeService$DeviceMethodCallbackMqtt>;
            devicemethodcallbackmqtt.DeviceMethodCallbackMqtt();
            IoTEdgeService iotedgeservice = IoTEdgeService.this;
            DeviceMethodStatusCallbackMqtt devicemethodstatuscallbackmqtt = JVM INSTR new #250 <Class IoTEdgeService$DeviceMethodStatusCallbackMqtt>;
            devicemethodstatuscallbackmqtt.DeviceMethodStatusCallbackMqtt();
            ((DeviceClient) (obj)).subscribeToDeviceMethod(devicemethodcallbackmqtt, iotedgeservice, devicemethodstatuscallbackmqtt, IoTEdgeService.this);
            PxLog.d("PisIotEdgeService", "subscribe");
_L1:
            boolean flag = running(Math.max(1L, TimeUnit.DAYS.toMillis(30L) - (SystemClock.elapsedRealtime() - l)), TimeUnit.MILLISECONDS);
            if(!flag)
            {
                Exception exception;
                Exception exception2;
                Object obj3;
                try
                {
                    ((DeviceClient) (obj)).closeNow();
                    PxLog.d("PisIotEdgeService", "disconnect device");
                    return;
                }
                catch(Exception exception1)
                {
                    exception1.printStackTrace();
                }
                break MISSING_BLOCK_LABEL_698;
            }
            lock_.lock();
            if(!reconnecting_)
                break MISSING_BLOCK_LABEL_549;
            PxLog.d("PisIotEdgeService", "reconnecting");
            lock_.unlock();
            ((DeviceClient) (obj)).closeNow();
            PxLog.d("PisIotEdgeService", "disconnect device");
            continue; /* Loop/switch isn't completed */
            lock_.unlock();
            obj3 = JVM INSTR new #17  <Class IoTEdgeService$ServiceThread$5>;
            ((_cls5) (obj3)).this. _cls5();
            obj3 = (Token)retry(((Callable) (obj3)));
            if(obj3 != null) goto _L2; else goto _L1
_L2:
            token_ = ((Token) (obj3));
            token_.save(getApplicationContext());
            l = SystemClock.elapsedRealtime();
            obj3 = JVM INSTR new #101 <Class StringBuilder>;
            ((StringBuilder) (obj3)).StringBuilder();
            ((StringBuilder) (obj3)).append("refresh token at ");
            ((StringBuilder) (obj3)).append(l);
            PxLog.d("PisIotEdgeService", ((StringBuilder) (obj3)).toString());
              goto _L1
            exception2;
            lock_.unlock();
            throw exception2;
            exception2;
            ((DeviceClient) (obj)).closeNow();
            PxLog.d("PisIotEdgeService", "disconnect device");
            throw exception2;
            exception;
            lock_.unlock();
            throw exception;
            return;
            if(true) goto _L4; else goto _L3
_L3:
        }

        static final int MAX_RETRY_COUNT = 6;
        static final int TOKEN_REFRESH_INTERVAL_DAYS = 30;
        final Condition condition_;
        boolean deleting_;
        final Lock lock_ = new ReentrantLock();
        boolean reconnecting_;
        boolean running_;
        final IoTEdgeService this$0;
        Token token_;

        public ServiceThread(boolean flag, Token token)
        {
            this$0 = IoTEdgeService.this;
            super();
            condition_ = lock_.newCondition();
            running_ = true;
            deleting_ = false;
            reconnecting_ = false;
            token_ = null;
            deleting_ = flag;
            token_ = token;
        }
    }


    public IoTEdgeService()
    {
        thread_ = null;
        device_ = new Device(Build.SERIAL, "", Build.MODEL);
        is_enabled_ = false;
        mPisIotEdgePastDataTimer = null;
        mPisIotEdgePastDataReceiver = null;
    }

    private void abort()
    {
        if(thread_ != null)
        {
            thread_.abort();
            try
            {
                thread_.join();
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
            thread_ = null;
        }
    }

    private void handleConnectionStatusChange(IotHubConnectionStatus iothubconnectionstatus, IotHubConnectionStatusChangeReason iothubconnectionstatuschangereason, Throwable throwable)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("Connection status=");
        stringbuilder.append(iothubconnectionstatus);
        stringbuilder.append(", reason=");
        stringbuilder.append(iothubconnectionstatuschangereason);
        PxLog.d("PisIotEdgeService", stringbuilder.toString());
        if(throwable != null)
            PxLog.e("PisIotEdgeService", throwable.getMessage());
        static class _cls6
        {

            static final int $SwitchMap$com$microsoft$azure$sdk$iot$device$IotHubStatusCode[];
            static final int $SwitchMap$com$microsoft$azure$sdk$iot$device$transport$IotHubConnectionStatus[];

            static 
            {
                $SwitchMap$com$microsoft$azure$sdk$iot$device$transport$IotHubConnectionStatus = new int[IotHubConnectionStatus.values().length];
                try
                {
                    $SwitchMap$com$microsoft$azure$sdk$iot$device$transport$IotHubConnectionStatus[IotHubConnectionStatus.DISCONNECTED.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                $SwitchMap$com$microsoft$azure$sdk$iot$device$IotHubStatusCode = new int[IotHubStatusCode.values().length];
                try
                {
                    $SwitchMap$com$microsoft$azure$sdk$iot$device$IotHubStatusCode[IotHubStatusCode.OK.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                $SwitchMap$com$microsoft$azure$sdk$iot$device$IotHubStatusCode[IotHubStatusCode.OK_EMPTY.ordinal()] = 2;
_L2:
                return;
                NoSuchFieldError nosuchfielderror2;
                nosuchfielderror2;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        if(_cls6..SwitchMap.com.microsoft.azure.sdk.iot.device.transport.IotHubConnectionStatus[iothubconnectionstatus.ordinal()] == 1)
            pool.submit(new Runnable() {

                public void run()
                {
                    reconnect();
                }

                final IoTEdgeService this$0;

            
            {
                this$0 = IoTEdgeService.this;
                super();
            }
            }
);
    }

    private DeviceMethodData handleDirectMethod(String s, Object obj)
    {
        Object obj2;
        int ai[];
        int i;
        int j;
        boolean flag;
        boolean flag1;
        try
        {
            Object obj1 = JVM INSTR new #209 <Class StringBuilder>;
            ((StringBuilder) (obj1)).StringBuilder();
            ((StringBuilder) (obj1)).append("methodName=");
            ((StringBuilder) (obj1)).append(s);
            PxLog.d("PisIotEdgeService", ((StringBuilder) (obj1)).toString());
            obj1 = JVM INSTR new #262 <Class String>;
            ((String) (obj1)).String((byte[])obj, StandardCharsets.UTF_8);
            obj = JVM INSTR new #209 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            ((StringBuilder) (obj)).append("json=");
            ((StringBuilder) (obj)).append(((String) (obj1)));
            PxLog.d("PisIotEdgeService", ((StringBuilder) (obj)).toString());
            ai = JVM INSTR new #277 <Class JSONObject>;
            ai.JSONObject(((String) (obj1)));
            obj = JVM INSTR new #282 <Class Intent>;
            ((Intent) (obj)).Intent("jp.co.pixela.pis_iot_edge.BROADCAST");
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s.printStackTrace();
            return null;
        }
        i = -1;
        j = s.hashCode();
        flag = false;
        j;
        JVM INSTR lookupswitch 7: default 180
    //                   -2033599417: 280
    //                   -573025942: 264
    //                   2410041: 248
    //                   77306085: 231
    //                   127891345: 215
    //                   686032974: 199
    //                   2095148919: 183;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        break; /* Loop/switch isn't completed */
_L8:
        if(s.equals("UpDownVolume"))
            i = 3;
        break; /* Loop/switch isn't completed */
_L7:
        if(s.equals("OneTouchSelectChannel"))
            i = 1;
        break; /* Loop/switch isn't completed */
_L6:
        if(s.equals("AbsoluteVolume"))
            i = 5;
        break; /* Loop/switch isn't completed */
_L5:
        if(s.equals("Power"))
            i = 6;
        break; /* Loop/switch isn't completed */
_L4:
        if(s.equals("Mute"))
            i = 4;
        break; /* Loop/switch isn't completed */
_L3:
        if(s.equals("UpDownSelectChannel"))
            i = 0;
        break; /* Loop/switch isn't completed */
_L2:
        if(s.equals("SelectChannel"))
            i = 2;
        j = 200;
        i;
        JVM INSTR tableswitch 0 6: default 344
    //                   0 708
    //                   1 652
    //                   2 555
    //                   3 481
    //                   4 450
    //                   5 419
    //                   6 347;
           goto _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16
_L16:
        ((Intent) (obj)).putExtra("CONTROL", 200);
        flag1 = ai.getBoolean("Value");
        ((Intent) (obj)).putExtra("VALUE", flag1);
        i = j;
        if(!flag1)
            break MISSING_BLOCK_LABEL_774;
        obj2 = ((PowerManager)getSystemService("power")).newWakeLock(0x30000006, "jp.co.pixela.pis_iot_edge.BROADCAST");
        ((android.os.PowerManager.WakeLock) (obj2)).acquire();
        ((android.os.PowerManager.WakeLock) (obj2)).release();
        i = j;
        break MISSING_BLOCK_LABEL_774;
_L15:
        ((Intent) (obj)).putExtra("CONTROL", 102);
        ((Intent) (obj)).putExtra("VALUE", ai.getInt("Value"));
        i = j;
        break MISSING_BLOCK_LABEL_774;
_L14:
        ((Intent) (obj)).putExtra("CONTROL", 101);
        ((Intent) (obj)).putExtra("VALUE", ai.getBoolean("Value"));
        i = j;
        break MISSING_BLOCK_LABEL_774;
_L13:
label0:
        {
            ((Intent) (obj)).putExtra("CONTROL", 100);
            i = ai.getInt("Value");
            ((Intent) (obj)).putExtra("VALUE", i);
            if(!ai.isNull("PercentValue"))
            {
                i = ai.getInt("PercentValue");
                break label0;
            }
            i *= 5;
        }
        ((Intent) (obj)).putExtra("PERCENT_VALUE", i);
        i = j;
        break MISSING_BLOCK_LABEL_774;
_L12:
        ((Intent) (obj)).putExtra("CONTROL", 2);
        if(!ai.isNull("Broadcasting"))
            ((Intent) (obj)).putExtra("BROADCASTING", ai.getString("Broadcasting"));
        obj2 = ai.getJSONArray("Values");
        ai = new int[((JSONArray) (obj2)).length()];
        i = ((flag) ? 1 : 0);
_L18:
        if(i >= ((JSONArray) (obj2)).length())
            break; /* Loop/switch isn't completed */
        ai[i] = ((JSONArray) (obj2)).getInt(i);
        i++;
        if(true) goto _L18; else goto _L17
_L17:
        ((Intent) (obj)).putExtra("VALUES", ai);
        i = j;
        break MISSING_BLOCK_LABEL_774;
_L11:
        ((Intent) (obj)).putExtra("CONTROL", 1);
        if(!ai.isNull("Broadcasting"))
            ((Intent) (obj)).putExtra("BROADCASTING", ai.getString("Broadcasting"));
        ((Intent) (obj)).putExtra("VALUE", ai.getInt("Value"));
        i = j;
        break MISSING_BLOCK_LABEL_774;
_L10:
        ((Intent) (obj)).putExtra("CONTROL", 0);
        ((Intent) (obj)).putExtra("VALUE", ai.getInt("Value"));
        i = j;
        break MISSING_BLOCK_LABEL_774;
_L9:
        obj2 = JVM INSTR new #209 <Class StringBuilder>;
        ((StringBuilder) (obj2)).StringBuilder();
        ((StringBuilder) (obj2)).append("Unknown method: ");
        ((StringBuilder) (obj2)).append(s);
        PxLog.e("PisIotEdgeService", ((StringBuilder) (obj2)).toString());
        i = 405;
        ((Intent) (obj)).setPackage("jp.pixela.stationtv.xit");
        sendBroadcast(((Intent) (obj)));
        obj2 = JVM INSTR new #209 <Class StringBuilder>;
        ((StringBuilder) (obj2)).StringBuilder();
        ((StringBuilder) (obj2)).append("sendBroadcast() is invoked successfully. intent=");
        ((StringBuilder) (obj2)).append(IntentHelper.dump(((Intent) (obj))));
        PxLog.d("PisIotEdgeService", ((StringBuilder) (obj2)).toString());
        break MISSING_BLOCK_LABEL_865;
        obj;
        ((JSONException) (obj)).printStackTrace();
        obj = JVM INSTR new #209 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        ((StringBuilder) (obj)).append("Invalid method argument: ");
        ((StringBuilder) (obj)).append(s);
        PxLog.e("PisIotEdgeService", ((StringBuilder) (obj)).toString());
        i = 400;
        s = new DeviceMethodData(i, "");
        return s;
    }

    private void handleDirectMethodStatus(IotHubStatusCode iothubstatuscode)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("DirectMethod status=");
        stringbuilder.append(iothubstatuscode);
        PxLog.d("PisIotEdgeService", stringbuilder.toString());
        switch(_cls6..SwitchMap.com.microsoft.azure.sdk.iot.device.IotHubStatusCode[iothubstatuscode.ordinal()])
        {
        default:
            pool.submit(new Runnable() {

                public void run()
                {
                    reconnect();
                }

                final IoTEdgeService this$0;

            
            {
                this$0 = IoTEdgeService.this;
                super();
            }
            }
);
            // fall through

        case 1: // '\001'
        case 2: // '\002'
            return;
        }
    }

    private void handleMessage(String s, byte abyte0[])
    {
        try
        {
            String s1 = JVM INSTR new #262 <Class String>;
            s1.String(abyte0, StandardCharsets.UTF_8);
            abyte0 = JVM INSTR new #209 <Class StringBuilder>;
            abyte0.StringBuilder();
            abyte0.append("Received request_id: ");
            abyte0.append(s);
            abyte0.append(", message: ");
            abyte0.append(s1);
            sendLogMessage(abyte0.toString());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s.printStackTrace();
        }
    }

    private void reconnect()
    {
        if(thread_ != null)
            thread_.reconnect();
    }

    private void sendLogMessage(String s)
    {
        Intent intent = new Intent("jp.co.pixela.pis_iot_edge.LOG");
        intent.putExtra("LOG_MESSAGE", s);
        sendBroadcast(intent);
        s = new StringBuilder();
        s.append("sendLogMessage sendBroadcast intent=");
        s.append(IntentHelper.dump(intent));
        PxLog.d("PisIotEdgeService", s.toString());
    }

    private void start(boolean flag, Token token)
    {
        abort();
        if(thread_ == null)
        {
            device_.setName(device_name_);
            thread_ = new ServiceThread(flag, token);
            thread_.start();
        }
    }

    public IBinder onBind(Intent intent)
    {
        return new Binder() {

            protected boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            {
                switch(i)
                {
                default:
                    break;

                case 2: // '\002'
                    is_enabled_ = false;
                    pool.submit(new Runnable() {

                        public void run()
                        {
                            Token token = Token.load(getApplicationContext());
                            if(token != null)
                                try
                                {
                                    if(token.isValid())
                                        start(true, token);
                                }
                                catch(Exception exception)
                                {
                                    exception.printStackTrace();
                                }
                            return;
                        }

                        final _cls2 this$1;

            
            {
                this$1 = _cls2.this;
                super();
            }
                    }
);
                    break;

                case 1: // '\001'
                    is_enabled_ = true;
                    parcel1 = parcel.readString();
                    parcel = parcel.readString();
                    if(parcel != null && !parcel.isEmpty())
                    {
                        device_name_ = parcel;
                        parcel = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
                        parcel.putString("pis_iot_edge_device_name", device_name_);
                        parcel.apply();
                    }
                    pool.submit(parcel1. new Runnable() {

                        public void run()
                        {
                            Token token;
                            if(json == null || json.isEmpty())
                                break MISSING_BLOCK_LABEL_64;
                            token = JVM INSTR new #36  <Class Token>;
                            JSONObject jsonobject = JVM INSTR new #38  <Class JSONObject>;
                            jsonobject.JSONObject(json);
                            token.Token(jsonobject);
                            if(token != null)
                                try
                                {
                                    if(token.isValid())
                                        start(false, token);
                                }
                                catch(Exception exception)
                                {
                                    exception.printStackTrace();
                                }
                            break MISSING_BLOCK_LABEL_109;
                            token = Token.load(getApplicationContext());
                            if(token == null)
                                break MISSING_BLOCK_LABEL_109;
                            if(token.isValid())
                                start(false, token);
                        }

                        final _cls2 this$1;
                        final String val$json;

            
            {
                this$1 = final__pcls2;
                json = String.this;
                super();
            }
                    }
);
                    break;

                case 0: // '\0'
                    parcel1.writeInt(is_enabled_);
                    break;
                }
                return true;
            }

            final IoTEdgeService this$0;

            
            {
                this$0 = IoTEdgeService.this;
                super();
            }
        }
;
    }

    public void onCreate()
    {
        super.onCreate();
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append(getApplicationContext().getPackageName());
        ((StringBuilder) (obj)).append(".");
        PACKAGE_NAME_PREFIX = ((StringBuilder) (obj)).toString();
        obj = new StringBuilder();
        ((StringBuilder) (obj)).append("onCreate() PACKAGE_NAME_PREFIX=");
        ((StringBuilder) (obj)).append(PACKAGE_NAME_PREFIX);
        PxLog.v("PisIotEdgeService", ((StringBuilder) (obj)).toString());
        PendingIntent pendingintent = PendingIntent.getService(this, 0, new Intent(this, jp/co/pixela/pis_iot_edge/IoTEdgeService), 0);
        obj = (NotificationManager)getSystemService("notification");
        if(android.os.Build.VERSION.SDK_INT >= 26)
        {
            NotificationChannel notificationchannel = new NotificationChannel(getString(R.string.NOTIFICATION_CHANNEL_ID), getString(R.string.NOTIFICATION_CHANNEL_NAME), 3);
            notificationchannel.setDescription(getString(R.string.NOTIFICATION_CHANNEL_DESCRIPTION));
            ((NotificationManager) (obj)).createNotificationChannel(notificationchannel);
        }
        android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder(getApplicationContext(), getString(R.string.NOTIFICATION_CHANNEL_ID));
        builder.setContentIntent(pendingintent);
        builder.setTicker("Pixela Information Service IoT Edge Ticker");
        builder.setContentTitle("Pixela Information Service IoT Edge Title");
        builder.setContentText("Pixela Information Service IoT Edge Text");
        builder.setSmallIcon(0x1080093);
        ((NotificationManager) (obj)).notify(R.string.iot_edge_service, builder.build());
        startForeground(R.string.iot_edge_service, builder.build());
        device_name_ = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("pis_iot_edge_device_name", "");
        if(device_name_.isEmpty())
            device_name_ = getString(R.string.DEFAULT_DEVICE_NAME);
        obj = Token.load(getApplicationContext());
        if(obj != null && ((Token) (obj)).isValid())
        {
            is_enabled_ = true;
            start(false, ((Token) (obj)));
        } else
        {
            is_enabled_ = false;
        }
        PxLog.d("PisIotEdgeService", "onCreate()");
    }

    public void onDestroy()
    {
        PxLog.d("PisIotEdgeService", "onDestroy() in");
        try
        {
            pool.shutdown();
            if(!pool.awaitTermination(60L, TimeUnit.SECONDS))
                pool.shutdownNow();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        abort();
        if(mPisIotEdgePastDataTimer != null)
        {
            mPisIotEdgePastDataTimer.cancel();
            mPisIotEdgePastDataTimer = null;
        }
        if(mPisIotEdgePastDataReceiver != null)
        {
            unregisterReceiver(mPisIotEdgePastDataReceiver);
            mPisIotEdgePastDataReceiver = null;
        }
        super.onDestroy();
        PxLog.d("PisIotEdgeService", "onDestroy() out");
    }

    static final String ACTION_PIS_IOT_EDGE_PAST_DATA = "ACTION_PIS_IOT_EDGE_PAST_DATA";
    public static final int IOT_EDGE_BROADCAST_ABSOLUTE_VOLUME = 102;
    public static final String IOT_EDGE_BROADCAST_BROADCASTING = "BROADCASTING";
    public static final String IOT_EDGE_BROADCAST_CONTROL = "CONTROL";
    public static final int IOT_EDGE_BROADCAST_MUTE_VOLUME = 101;
    public static final String IOT_EDGE_BROADCAST_NAME = "jp.co.pixela.pis_iot_edge.BROADCAST";
    public static final int IOT_EDGE_BROADCAST_ONE_TOUCH_SELECT_CHANNEL = 1;
    public static final int IOT_EDGE_BROADCAST_POWER = 200;
    public static final int IOT_EDGE_BROADCAST_SELECT_CHANNEL_BY_SERVICE_ID = 2;
    public static final int IOT_EDGE_BROADCAST_UP_DOWN_SELECT_CHANNEL = 0;
    public static final int IOT_EDGE_BROADCAST_UP_DOWN_VOLUME = 100;
    public static final String IOT_EDGE_BROADCAST_VALUE = "VALUE";
    public static final String IOT_EDGE_BROADCAST_VALUES = "VALUES";
    public static final int IOT_EDGE_COMMAND_DISABLE = 2;
    public static final int IOT_EDGE_COMMAND_ENABLE = 1;
    public static final int IOT_EDGE_COMMAND_IS_ENABLED = 0;
    public static final String IOT_EDGE_LOG_MESSAGE = "LOG_MESSAGE";
    public static final String IOT_EDGE_LOG_NAME = "jp.co.pixela.pis_iot_edge.LOG";
    public static final String IOT_EDGE_PERCENT_VALUE = "PERCENT_VALUE";
    static final String KEY_NAME_DEVICE_NAME = "pis_iot_edge_device_name";
    static final String KEY_NAME_UPDATED_DATA = "pis_iot_edge_device_updated_data";
    static final String KEY_TOKEN_JSON = "PisIotEdge_TokenJson";
    private static String PACKAGE_NAME_PREFIX;
    private static final String TAG = "PisIotEdgeService";
    final Device device_;
    String device_name_;
    boolean is_enabled_;
    private PisIotEdgePastDataReceiver mPisIotEdgePastDataReceiver;
    private Timer mPisIotEdgePastDataTimer;
    final ExecutorService pool = Executors.newSingleThreadExecutor();
    ServiceThread thread_;




/*
    static Timer access$102(IoTEdgeService iotedgeservice, Timer timer)
    {
        iotedgeservice.mPisIotEdgePastDataTimer = timer;
        return timer;
    }

*/







    // Unreferenced inner class jp/co/pixela/pis_iot_edge/IoTEdgeService$ServiceThread$1

/* anonymous class */
    class ServiceThread._cls1
        implements Callable
    {

        public volatile Object call()
            throws Exception
        {
            return call();
        }

        public Token call()
            throws Exception
        {
            return (new Api(getApplicationContext())).refreshToken(token_);
        }

        final ServiceThread this$1;

            
            {
                this$1 = ServiceThread.this;
                super();
            }
    }


    // Unreferenced inner class jp/co/pixela/pis_iot_edge/IoTEdgeService$ServiceThread$2

/* anonymous class */
    class ServiceThread._cls2
        implements Callable
    {

        public Object call()
            throws Exception
        {
            if(!(new Api(getApplicationContext())).deleteDevice(device_.getDeviceId(), token_))
                throw new Exception();
            else
                return null;
        }

        final ServiceThread this$1;

            
            {
                this$1 = ServiceThread.this;
                super();
            }
    }


    // Unreferenced inner class jp/co/pixela/pis_iot_edge/IoTEdgeService$ServiceThread$3

/* anonymous class */
    class ServiceThread._cls3
        implements Callable
    {

        public volatile Object call()
            throws Exception
        {
            return call();
        }

        public Device call()
            throws Exception
        {
            Device device = (new Api(getApplicationContext())).createDevice(device_, token_);
            Device device2 = device;
            if(device == null)
            {
                Device device1 = (new Api(getApplicationContext())).updateDevice(device_, token_);
                device2 = device1;
                if(device1 == null)
                    throw new Exception();
            }
            return device2;
        }

        final ServiceThread this$1;

            
            {
                this$1 = ServiceThread.this;
                super();
            }
    }


    // Unreferenced inner class jp/co/pixela/pis_iot_edge/IoTEdgeService$ServiceThread$4

/* anonymous class */
    class ServiceThread._cls4
        implements Callable
    {

        public Object call()
            throws Exception
        {
            client.open();
            return null;
        }

        final ServiceThread this$1;
        final DeviceClient val$client;

            
            {
                this$1 = final_servicethread;
                client = DeviceClient.this;
                super();
            }
    }


    // Unreferenced inner class jp/co/pixela/pis_iot_edge/IoTEdgeService$ServiceThread$5

/* anonymous class */
    class ServiceThread._cls5
        implements Callable
    {

        public volatile Object call()
            throws Exception
        {
            return call();
        }

        public Token call()
            throws Exception
        {
            return (new Api(getApplicationContext())).refreshToken(token_);
        }

        final ServiceThread this$1;

            
            {
                this$1 = ServiceThread.this;
                super();
            }
    }

}
