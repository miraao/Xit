// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.co.pixela.pis_iot_edge;

import org.json.JSONObject;

class SymmetricKey
{

    SymmetricKey(String s, String s1)
    {
        primaryKey_ = s;
        secondaryKey_ = s1;
    }

    SymmetricKey(JSONObject jsonobject)
    {
        if(jsonobject != null)
            try
            {
                primaryKey_ = jsonobject.getString(KEY_NAME_PRIMARY_KEY);
                secondaryKey_ = jsonobject.getString(KEY_NAME_SECONDARY_KEY);
            }
            // Misplaced declaration of an exception variable
            catch(JSONObject jsonobject)
            {
                jsonobject.printStackTrace();
            }
    }

    String getPrimaryKey()
    {
        return primaryKey_;
    }

    String getSecondaryKey()
    {
        return secondaryKey_;
    }

    public JSONObject toJSONObject()
    {
        JSONObject jsonobject;
        try
        {
            jsonobject = JVM INSTR new #31  <Class JSONObject>;
            jsonobject.JSONObject();
            jsonobject.put(KEY_NAME_PRIMARY_KEY, primaryKey_);
            jsonobject.put(KEY_NAME_SECONDARY_KEY, secondaryKey_);
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

    static String KEY_NAME_PRIMARY_KEY = "PrimaryKey";
    static String KEY_NAME_SECONDARY_KEY = "SecondaryKey";
    private String primaryKey_;
    private String secondaryKey_;

}
