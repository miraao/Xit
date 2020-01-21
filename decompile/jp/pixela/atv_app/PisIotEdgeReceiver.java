// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.atv_app;

import android.content.*;
import android.preference.PreferenceManager;
import jp.pixela.atv_app.common.EncryptionUtility;
import jp.pixela.atv_app.common.PxLog;

public class PisIotEdgeReceiver extends BroadcastReceiver
{

    public PisIotEdgeReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        String s;
        s = intent.getAction();
        String s1 = TAG;
        intent = new StringBuilder();
        intent.append("action:");
        intent.append(s);
        PxLog.v(s1, intent.toString());
        Object obj;
        if(!"jp.pixela.pis_iot_edge.ACTION_PIS_IOT_EDGE_PAST_DATA".equals(s))
            break MISSING_BLOCK_LABEL_252;
        intent = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        s = intent.getString("pis_iot_edge_device_name", "");
        obj = intent.getString("PisIotEdge_TokenJson", "");
        intent = ((Intent) (obj));
        int i;
        if(((String) (obj)).isEmpty())
            break MISSING_BLOCK_LABEL_163;
        intent = EncryptionUtility.encrypt(((String) (obj)).getBytes("UTF8"));
        obj = JVM INSTR new #36  <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        i = intent.length;
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        ((StringBuilder) (obj)).append(String.format("%02X", new Object[] {
            Byte.valueOf(intent[j])
        }));
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        intent = ((StringBuilder) (obj)).toString();
        Intent intent1 = JVM INSTR new #28  <Class Intent>;
        intent1.Intent();
        intent1.setPackage("jp.pixela.pis_iot_edge");
        intent1.setAction("jp.pixela.pis_iot_edge.ACTION_PIS_IOT_EDGE_PAST_DATA");
        intent1.putExtra("pis_iot_edge_device_name", s);
        intent1.putExtra("PisIotEdge_TokenJson", intent);
        context.sendBroadcast(intent1);
        break MISSING_BLOCK_LABEL_252;
        Exception exception;
        exception;
        context = TAG;
        intent = new StringBuilder();
        intent.append("e=");
        intent.append(exception);
        PxLog.v(context, intent.toString());
    }

    private static final String ACTION_PIS_IOT_EDGE_PAST_DATA = "ACTION_PIS_IOT_EDGE_PAST_DATA";
    private static final String KEY_NAME_DEVICE_NAME = "pis_iot_edge_device_name";
    private static final String KEY_TOKEN_JSON = "PisIotEdge_TokenJson";
    private static final String TAG = "PisIotEdgeReceiver";

}
