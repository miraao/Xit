// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.co.pixela.pis_iot_edge;

import org.json.JSONObject;

// Referenced classes of package jp.co.pixela.pis_iot_edge:
//            SymmetricKey

class Device
{

    Device(String s, String s1, String s2)
    {
        deviceId_ = s;
        name_ = s1;
        text_ = s2;
        symmetricKey_ = new SymmetricKey("", "");
    }

    Device(JSONObject jsonobject)
        throws Exception
    {
        deviceId_ = jsonobject.getString("DeviceID");
        host_ = jsonobject.getString("Host");
        registeredDeviceId_ = jsonobject.getString("RegisteredDeviceId");
        symmetricKey_ = new SymmetricKey(jsonobject.getJSONObject("SymmetricKey"));
        name_ = jsonobject.getString("Name");
        text_ = jsonobject.getString("Text");
    }

    String getDeviceId()
    {
        return deviceId_;
    }

    String getHost()
    {
        return host_;
    }

    String getName()
    {
        return name_;
    }

    String getRegisteredDeviceId()
    {
        return registeredDeviceId_;
    }

    SymmetricKey getSymmetricKey()
    {
        return symmetricKey_;
    }

    String getText()
    {
        return text_;
    }

    void setName(String s)
    {
        name_ = s;
    }

    JSONObject toJSONObject()
    {
        JSONObject jsonobject;
        try
        {
            jsonobject = JVM INSTR new #56  <Class JSONObject>;
            jsonobject.JSONObject();
            jsonobject.put("DeviceID", deviceId_);
            jsonobject.put("Host", host_);
            jsonobject.put("RegisteredDeviceId", registeredDeviceId_);
            jsonobject.put("SymmetricKey", symmetricKey_.toJSONObject());
            jsonobject.put("Name", name_);
            jsonobject.put("Text", text_);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
        return jsonobject;
    }

    public String toString()
    {
        Object obj;
        try
        {
            obj = toJSONObject();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
        if(obj == null)
            break MISSING_BLOCK_LABEL_17;
        obj = ((JSONObject) (obj)).toString();
        break MISSING_BLOCK_LABEL_19;
        obj = null;
        return ((String) (obj));
    }

    static final String KEY_NAME_DEVICE_ID = "DeviceID";
    static final String KEY_NAME_HOST = "Host";
    static final String KEY_NAME_NAME = "Name";
    static final String KEY_NAME_REGISTERED_DEVICE_ID = "RegisteredDeviceId";
    static final String KEY_NAME_SYMMETRIC_KEY = "SymmetricKey";
    static final String KEY_NAME_TEXT = "Text";
    private String deviceId_;
    private String host_;
    private String name_;
    private String registeredDeviceId_;
    private SymmetricKey symmetricKey_;
    private String text_;
}
