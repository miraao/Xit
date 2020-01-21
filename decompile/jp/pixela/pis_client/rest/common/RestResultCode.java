// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pis_client.rest.common;


public class RestResultCode
{

    public RestResultCode()
    {
    }

    public static final int FAILED = -1;
    public static final int HTTP_BAD_REQUEST = -6;
    public static final int HTTP_FORBIDDEN = -8;
    public static final int HTTP_NOT_FOUND = -9;
    public static final int HTTP_SERVICE_UNAVAILABLE = -10;
    public static final int HTTP_UNAUTHORIZED = -7;
    public static final int IO_ERROR = -5;
    public static final int JSON_ERROR = -2;
    public static final int SSL_ERROR = -15;
    public static final int SSL_HANDSHAKE_ERROR = -11;
    public static final int SSL_KEY_ERROR = -12;
    public static final int SSL_PEER_UNVERIFIED_ERROR = -13;
    public static final int SSL_PROTOCOL_ERROR = -14;
    public static final int SUCCESS = 0;
    public static final int TIMEOUT = -3;
    public static final int UNKNOWN_HOST = -4;
}
