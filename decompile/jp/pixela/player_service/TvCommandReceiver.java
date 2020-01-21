// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service;

import android.content.*;
import android.view.KeyEvent;
import jp.pixela.common.ApplicationUtility;
import jp.pixela.common.PxLog;
import jp.pixela.player_service.tunerservice.BroadcastWave;

// Referenced classes of package jp.pixela.player_service:
//            TvTunerService, App

public class TvCommandReceiver extends BroadcastReceiver
{

    public TvCommandReceiver()
    {
    }

    private int convertToRemoconKeyNumber(int i)
    {
        switch(i)
        {
        default:
            switch(i)
            {
            default:
                return 0;

            case 228: 
                return 12;

            case 227: 
                return 11;
            }

        case 16: // '\020'
            return 9;

        case 15: // '\017'
            return 8;

        case 14: // '\016'
            return 7;

        case 13: // '\r'
            return 6;

        case 12: // '\f'
            return 5;

        case 11: // '\013'
            return 4;

        case 10: // '\n'
            return 3;

        case 9: // '\t'
            return 2;

        case 8: // '\b'
            return 1;

        case 7: // '\007'
            return 10;
        }
    }

    private int getBroadcastType(KeyEvent keyevent)
    {
        BroadcastWave broadcastwave = BroadcastWave.TR;
        switch(keyevent.getKeyCode())
        {
        case 237: 
        default:
            keyevent = broadcastwave;
            break;

        case 240: 
            keyevent = BroadcastWave.BSCS;
            break;

        case 239: 
            keyevent = BroadcastWave.CS;
            break;

        case 238: 
            if((keyevent.getMetaState() & 8) != 0)
            {
                keyevent = BroadcastWave.ADTV;
                break;
            }
            if((keyevent.getMetaState() & 2) != 0)
                keyevent = BroadcastWave.BSADTV;
            else
                keyevent = BroadcastWave.BS;
            break;

        case 236: 
            keyevent = BroadcastWave.TR;
            break;
        }
        return keyevent.getValue();
    }

    public void onReceive(Context context, Intent intent)
    {
        if(intent != null && context != null) goto _L2; else goto _L1
_L2:
        Object obj;
        Object obj1;
        if(TvTunerService.isInitialSettingsState(context))
        {
            PxLog.i("TvCommandReceiver", "isInitialSettingsState.");
            return;
        }
        obj = intent.getAction();
        if(obj == null)
        {
            PxLog.w("TvCommandReceiver", "action is null.");
            return;
        }
        obj1 = new StringBuilder();
        ((StringBuilder) (obj1)).append(context.getPackageName());
        ((StringBuilder) (obj1)).append(".ACTION_REQUEST_FOREGROUND");
        if(((String) (obj)).equals(((StringBuilder) (obj1)).toString()))
        {
            obj = new Intent();
            ((Intent) (obj)).putExtra("FromKeyEvent", true);
            ((Intent) (obj)).putExtra("FromKeyEventLoadURL", intent.getStringExtra("FromKeyEventLoadURL"));
            ((Intent) (obj)).setClass(context, jp/pixela/player_service/TvTunerService);
            context.startActivity(((Intent) (obj)));
            break MISSING_BLOCK_LABEL_649;
        }
        obj1 = new StringBuilder();
        ((StringBuilder) (obj1)).append(context.getPackageName());
        ((StringBuilder) (obj1)).append(".ACTION_REQUEST_KEY_EVENT");
        if(!((String) (obj)).equals(((StringBuilder) (obj1)).toString()))
            break MISSING_BLOCK_LABEL_649;
        if(ApplicationUtility.isAppRunningForeground(context))
        {
            obj = context.getApplicationContext();
            if((obj instanceof App) && ((App)obj).isRunning("jp.pixela.atv_app.MainActivity"))
            {
                PxLog.i("TvCommandReceiver", "jp.pixela.atv_app.MainActivity is foreground.");
                return;
            }
        }
        obj = new Intent();
        obj1 = intent.getParcelableExtra("key_event");
        String s = intent.getStringExtra("action");
        intent = new StringBuilder();
        intent.append("tvAction:");
        intent.append(s);
        PxLog.d("TvCommandReceiver", intent.toString());
        if(s != null && s.equals("switch TV"))
        {
            ((Intent) (obj)).putExtra("FromKeyEvent", true);
            ((Intent) (obj)).putExtra("switch TV", true);
            ((Intent) (obj)).setClass(context, jp/pixela/player_service/TvTunerService);
            context.startActivity(((Intent) (obj)));
            return;
        }
        if(obj1 != null && (obj1 instanceof KeyEvent)) goto _L4; else goto _L3
_L4:
        int i;
        obj1 = (KeyEvent)obj1;
        intent = new StringBuilder();
        intent.append("KeyCode:");
        intent.append(((KeyEvent) (obj1)).getKeyCode());
        intent.append(", Action:");
        intent.append(((KeyEvent) (obj1)).getAction());
        PxLog.d("TvCommandReceiver", intent.toString());
        i = ((KeyEvent) (obj1)).getKeyCode();
        if(i == 28 || i == 257) goto _L6; else goto _L5
_L5:
        if(((KeyEvent) (obj1)).getAction() != 1)
            return;
        i = ((KeyEvent) (obj1)).getKeyCode();
        if(i == 172) goto _L8; else goto _L7
_L7:
        if(i == 236) goto _L10; else goto _L9
_L9:
        i;
        JVM INSTR tableswitch 7 16: default 492
    //                   7 547
    //                   8 547
    //                   9 547
    //                   10 547
    //                   11 547
    //                   12 547
    //                   13 547
    //                   14 547
    //                   15 547
    //                   16 547;
           goto _L11 _L12 _L12 _L12 _L12 _L12 _L12 _L12 _L12 _L12 _L12
_L11:
        i;
        JVM INSTR tableswitch 227 228: default 516
    //                   227 547
    //                   228 547;
           goto _L13 _L12 _L12
_L13:
        switch(i)
        {
        default:
            break; /* Loop/switch isn't completed */

        case 238: 
        case 239: 
        case 240: 
            break;
        }
          goto _L10
_L12:
        ((Intent) (obj)).putExtra("KeyCode", ((KeyEvent) (obj1)).getKeyCode());
        ((Intent) (obj)).putExtra("RemoconNumber", convertToRemoconKeyNumber(((KeyEvent) (obj1)).getKeyCode()));
        break; /* Loop/switch isn't completed */
_L10:
        ((Intent) (obj)).putExtra("KeyCode", ((KeyEvent) (obj1)).getKeyCode());
        ((Intent) (obj)).putExtra("BroadcastType", getBroadcastType(((KeyEvent) (obj1))));
        break; /* Loop/switch isn't completed */
_L8:
        ((Intent) (obj)).putExtra("KeyCode", 172);
        ((Intent) (obj)).putExtra("FromKeyEvent", true);
        ((Intent) (obj)).setClass(context, jp/pixela/player_service/TvTunerService);
        context.startActivity(((Intent) (obj)));
        break MISSING_BLOCK_LABEL_649;
_L6:
        return;
_L3:
        PxLog.w("TvCommandReceiver", "key_event is null.");
        return;
        return;
_L1:
        PxLog.w("TvCommandReceiver", "Intent or Context is null.");
        return;
    }

    public static final String ACTION_REQUEST_FOREGROUND_SUFFIX = ".ACTION_REQUEST_FOREGROUND";
    public static final String BROADCAST_TYPE = "BroadcastType";
    public static final String FROM_KEY_EVENT = "FromKeyEvent";
    private static final String KEY_ACTION = "action";
    public static final String KEY_CODE = "KeyCode";
    private static final String KEY_EVENT = "key_event";
    public static final String KEY_EVENT_URL = "FromKeyEventLoadURL";
    public static final String REMOCON_NUMBER = "RemoconNumber";
    public static final String SWITCH_TV = "switch TV";
    private static final String TAG = "TvCommandReceiver";
    private static final String TV_COMMAND_ACTION_SUFFIX = ".ACTION_REQUEST_KEY_EVENT";
}
