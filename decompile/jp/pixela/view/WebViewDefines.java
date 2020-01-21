// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.view;


public class WebViewDefines
{

    public WebViewDefines()
    {
    }

    public static final String COMPLETE_HDD_FORMAT_COMMAND = "hdd_format_complete";
    public static final String EXECUTE_HDD_FORMAT_COMMAND = "exec_hdd_format";
    public static final String FINISH_APP_COMMAND = "finish_app";
    public static final String HDD_FORMATTING_URL = "file:///android_asset/hddformat/index.html#/hdd-format-formatting";
    public static final String HDD_FORMAT_COMPLETE_URL = "file:///android_asset/hddformat/index.html#/hdd-format-complete";
    public static final String HDD_FORMAT_RESULT_PARAM = "result=%d&messageId=%d";
    public static final String HDD_FORMAT_START_URL = "file:///android_asset/hddformat/index.html#/hdd-format-start";
    public static final String HDD_SHOW_MODE_PARAM = "showMode=%d";
    public static final String REQUEST_STORAGE_PERMISSION_COMMAND = "request_storege_permission";
    public static final String REQUEST_STORAGE_PERMISSION_ERROR_URL = "file:///android_asset/hddformat/index.html#/storage_permission_error";
    public static final String REQUEST_STORAGE_PERMISSION_URL = "file:///android_asset/hddformat/index.html";
    public static final String SET_DEVICE_NAME = "setDeviceName('%s');";
    public static final String SET_FORMAT_PROGRESS = "setFormatProgress(%d);";
    public static final String SHOW_FORMAT_RESULT_MESSAGE = "showCompleteMessage(%d, %d);";
    public static final String SHOW_MESSAGE_DIALOG = "showMessageDialog();";
}
