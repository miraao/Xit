// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.recommend;

import jp.pixela.pis_client.rest.common.IRestConfig;

public class UserRecommendApiConfig
    implements IRestConfig
{

    public UserRecommendApiConfig()
    {
    }

    public int getConnectTimeout()
    {
        return 30000;
    }

    public String getHostURL()
    {
        return "https://pixeladtvapi.azurewebsites.net/api/v1/userrecommendedprograms";
    }

    public int getReadTimeout()
    {
        return 30000;
    }

    private static final int CONNECT_TIMEOUT_MSEC = 30000;
    private static final int READ_TIMEOUT_MSEC = 30000;
    private static final String SERVER_URL = "https://pixeladtvapi.azurewebsites.net/api/v1/userrecommendedprograms";
}
