// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.airtunerjni;

import android.content.*;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// Referenced classes of package jp.pixela.airtunerjni:
//            ProcessWatchingService

public class AirTunerActivity extends AppCompatActivity
{

    public AirTunerActivity()
    {
        airTunerNotifyReceiver_ = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent)
            {
                if("jp.pixela.airtunerjni.AIRTUNER_NOTIFY".equals(intent.getAction()))
                    switch(intent.getIntExtra("event", -1))
                    {
                    default:
                        break;

                    case 3: // '\003'
                        showMessageInHandler("AirTunerService is waiting until USB device is attached.");
                        break;

                    case 2: // '\002'
                        showMessageInHandler("AirTunerService is failed to start, reset device.");
                        break;

                    case 1: // '\001'
                        int i = intent.getIntExtra("reason", -1);
                        switch(i)
                        {
                        default:
                            context = new StringBuilder();
                            context.append("AirTunerService is stopped ");
                            context.append(" by unknown reason.(");
                            context.append(i);
                            context.append(")");
                            context = context.toString();
                            break;

                        case 2: // '\002'
                            context = new StringBuilder();
                            context.append("AirTunerService is stopped ");
                            context.append(" by server process crashed.");
                            context = context.toString();
                            break;

                        case 1: // '\001'
                            context = new StringBuilder();
                            context.append("AirTunerService is stopped ");
                            context.append(" by tuner detached.");
                            context = context.toString();
                            break;

                        case 0: // '\0'
                            context = new StringBuilder();
                            context.append("AirTunerService is stopped ");
                            context.append(" by app request.");
                            context = context.toString();
                            break;
                        }
                        showMessageInHandler(context);
                        break;

                    case 0: // '\0'
                        showMessageInHandler("AirTunerService is started.");
                        break;
                    }
            }

            final AirTunerActivity this$0;

            
            {
                this$0 = AirTunerActivity.this;
                super();
            }
        }
;
        dmsNotifyReceiver_ = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent)
            {
                if("jp.pixela.airtunerjni.PXDMSDAEMON_NOTIFY".equals(intent.getAction()))
                    switch(intent.getIntExtra("event", -1))
                    {
                    case 2: // '\002'
                        showMessageInHandler("PxDMSDaemonService start failed.");
                        break;

                    case 1: // '\001'
                        showMessageInHandler("PxDMSDaemonService is stoped.");
                        break;

                    case 0: // '\0'
                        showMessageInHandler("PxDMSDaemonService is started.");
                        break;
                    }
            }

            final AirTunerActivity this$0;

            
            {
                this$0 = AirTunerActivity.this;
                super();
            }
        }
;
        conn_ = new ServiceConnection() {

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                Log.d("AirTunerApk", "ProcessWatchingService is connected.");
                binder_ = ibinder;
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                Log.d("AirTunerApk", "ProcessWatchingService is disconnected.");
                binder_ = null;
            }

            final AirTunerActivity this$0;

            
            {
                this$0 = AirTunerActivity.this;
                super();
            }
        }
;
    }

    private void showMessage(String s)
    {
        TextView textview = (TextView)findViewById(R.id.hello_textview);
        if(textview != null)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(textview.getText());
            stringbuilder.append("\n");
            stringbuilder.append(s);
            textview.setText(stringbuilder.toString());
        }
        Log.d("AirTunerApk", s);
    }

    private void showMessageInHandler(final String msg)
    {
        handler_.post(new Runnable() {

            public void run()
            {
                showMessage(msg);
            }

            final AirTunerActivity this$0;
            final String val$msg;

            
            {
                this$0 = AirTunerActivity.this;
                msg = s;
                super();
            }
        }
);
    }

    private void startAirTunerService()
    {
        if(binder_ != null)
        {
            Parcel parcel = Parcel.obtain();
            Parcel parcel1 = Parcel.obtain();
            try
            {
                binder_.transact(1000, parcel, parcel1, 0);
            }
            catch(RemoteException remoteexception)
            {
                Log.e("AirTunerApk", "AirTunerService transact() throws RemoteException.");
            }
        }
    }

    private void startProcessWatchingService()
    {
        Intent intent = new Intent(getApplicationContext(), jp/pixela/airtunerjni/ProcessWatchingService);
        if(startService(intent) == null)
        {
            Log.e("AirTunerApk", "Failed to start ProcessWatchingService.");
            return;
        }
        if(!bindService(intent, conn_, 0))
            showMessage("ProcessWatchingService bindService() failed!!");
    }

    private void startPxDMSDaemonService()
    {
        if(binder_ != null)
        {
            Parcel parcel = Parcel.obtain();
            Parcel parcel1 = Parcel.obtain();
            try
            {
                binder_.transact(2000, parcel, parcel1, 0);
            }
            catch(RemoteException remoteexception)
            {
                Log.e("AirTunerApk", "PxDMSDaemonService transact() throws RemoteException.");
            }
        }
    }

    private void stopAirTunerService()
    {
        if(binder_ != null)
        {
            Parcel parcel = Parcel.obtain();
            Parcel parcel1 = Parcel.obtain();
            try
            {
                binder_.transact(1001, parcel, parcel1, 0);
            }
            catch(RemoteException remoteexception)
            {
                Log.e("AirTunerApk", "AirTunerService transact() throws RemoteException.");
            }
        }
    }

    private void stopPxDMSDaemonService()
    {
        if(binder_ != null)
        {
            Parcel parcel = Parcel.obtain();
            Parcel parcel1 = Parcel.obtain();
            try
            {
                binder_.transact(2001, parcel, parcel1, 0);
            }
            catch(RemoteException remoteexception)
            {
                Log.e("AirTunerApk", "PxDMSDaemonService transact() throws RemoteException.");
            }
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        handler_ = new Handler();
        setContentView(R.layout.activity_hello_jni);
        ((Button)findViewById(R.id.start_airtuner_service)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                startAirTunerService();
            }

            final AirTunerActivity this$0;

            
            {
                this$0 = AirTunerActivity.this;
                super();
            }
        }
);
        ((Button)findViewById(R.id.stop_airtuner_service)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                stopAirTunerService();
            }

            final AirTunerActivity this$0;

            
            {
                this$0 = AirTunerActivity.this;
                super();
            }
        }
);
        ((Button)findViewById(R.id.start_dms_service)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                startPxDMSDaemonService();
            }

            final AirTunerActivity this$0;

            
            {
                this$0 = AirTunerActivity.this;
                super();
            }
        }
);
        ((Button)findViewById(R.id.stop_dms_service)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                stopPxDMSDaemonService();
            }

            final AirTunerActivity this$0;

            
            {
                this$0 = AirTunerActivity.this;
                super();
            }
        }
);
        registerReceiver(airTunerNotifyReceiver_, new IntentFilter("jp.pixela.airtunerjni.AIRTUNER_NOTIFY"));
        registerReceiver(dmsNotifyReceiver_, new IntentFilter("jp.pixela.airtunerjni.PXDMSDAEMON_NOTIFY"));
        startProcessWatchingService();
    }

    protected void onDestroy()
    {
        super.onDestroy();
    }

    private static final String TAG = "AirTunerApk";
    private BroadcastReceiver airTunerNotifyReceiver_;
    private IBinder binder_;
    private ServiceConnection conn_;
    private BroadcastReceiver dmsNotifyReceiver_;
    private Handler handler_;



/*
    static IBinder access$102(AirTunerActivity airtuneractivity, IBinder ibinder)
    {
        airtuneractivity.binder_ = ibinder;
        return ibinder;
    }

*/





}
