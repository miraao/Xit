// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.common.json;

import jp.pixela.pis_client.rest.common.IRestItem;
import org.json.JSONObject;

public interface IJsonRestItem
    extends IRestItem
{

    public abstract IRestItem fromJSONObject(JSONObject jsonobject);

    public abstract void initItem();

    public abstract JSONObject toJSONObject();
}
