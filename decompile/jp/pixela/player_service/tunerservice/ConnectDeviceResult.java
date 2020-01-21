// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;


public final class ConnectDeviceResult extends Enum
{

    private ConnectDeviceResult(String s, int i, int j)
    {
        super(s, i);
        mValue = j;
    }

    public static ConnectDeviceResult fromValue(int i)
    {
        ConnectDeviceResult aconnectdeviceresult[] = values();
        for(int j = 0; j < aconnectdeviceresult.length; j++)
            if(aconnectdeviceresult[j].toValue() == i)
                return aconnectdeviceresult[j];

        return UNKNOWN;
    }

    public static ConnectDeviceResult valueOf(String s)
    {
        return (ConnectDeviceResult)Enum.valueOf(jp/pixela/player_service/tunerservice/ConnectDeviceResult, s);
    }

    public static ConnectDeviceResult[] values()
    {
        return (ConnectDeviceResult[])$VALUES.clone();
    }

    public int toValue()
    {
        return mValue;
    }

    private static final ConnectDeviceResult $VALUES[];
    public static final ConnectDeviceResult ACQUIRED;
    public static final ConnectDeviceResult ERROR_ALREADY_USED_CONFIRM;
    public static final ConnectDeviceResult ERROR_ALREADY_USED_STRONG;
    public static final ConnectDeviceResult ERROR_FAIL_REQUEST_RESOURCE;
    public static final ConnectDeviceResult ERROR_INIT;
    public static final ConnectDeviceResult ERROR_OTHER_FAIL;
    public static final ConnectDeviceResult ERROR_SERVICE_CONNECTED_FAIL;
    public static final ConnectDeviceResult ERROR_TUENR_RESTRICT;
    public static final ConnectDeviceResult NO_ERROR;
    public static final ConnectDeviceResult UNKNOWN;
    private int mValue;

    static 
    {
        NO_ERROR = new ConnectDeviceResult("NO_ERROR", 0, 0);
        ACQUIRED = new ConnectDeviceResult("ACQUIRED", 1, 1);
        ERROR_INIT = new ConnectDeviceResult("ERROR_INIT", 2, -1);
        ERROR_TUENR_RESTRICT = new ConnectDeviceResult("ERROR_TUENR_RESTRICT", 3, -2);
        ERROR_ALREADY_USED_CONFIRM = new ConnectDeviceResult("ERROR_ALREADY_USED_CONFIRM", 4, -3);
        ERROR_ALREADY_USED_STRONG = new ConnectDeviceResult("ERROR_ALREADY_USED_STRONG", 5, -4);
        ERROR_FAIL_REQUEST_RESOURCE = new ConnectDeviceResult("ERROR_FAIL_REQUEST_RESOURCE", 6, -5);
        ERROR_OTHER_FAIL = new ConnectDeviceResult("ERROR_OTHER_FAIL", 7, -999);
        ERROR_SERVICE_CONNECTED_FAIL = new ConnectDeviceResult("ERROR_SERVICE_CONNECTED_FAIL", 8, -998);
        UNKNOWN = new ConnectDeviceResult("UNKNOWN", 9, -9999);
        $VALUES = (new ConnectDeviceResult[] {
            NO_ERROR, ACQUIRED, ERROR_INIT, ERROR_TUENR_RESTRICT, ERROR_ALREADY_USED_CONFIRM, ERROR_ALREADY_USED_STRONG, ERROR_FAIL_REQUEST_RESOURCE, ERROR_OTHER_FAIL, ERROR_SERVICE_CONNECTED_FAIL, UNKNOWN
        });
    }
}
