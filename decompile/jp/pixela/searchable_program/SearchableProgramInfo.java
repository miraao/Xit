// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.searchable_program;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import jp.pixela.common.IntentHelper;
import jp.pixela.common.PxLog;
import jp.pixela.player_service.TvTunerService;

public class SearchableProgramInfo extends Activity
{

    public SearchableProgramInfo()
    {
    }

    private void handleIntent(Intent intent)
    {
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("handleIntent intent=");
        stringbuilder.append(IntentHelper.dump(intent));
        PxLog.v(s, stringbuilder.toString());
        startActivity((new Intent(this, jp/pixela/player_service/TvTunerService)).putExtra("intent_extra_data_key", intent.getStringExtra("intent_extra_data_key")));
    }

    public void onCreate(Bundle bundle)
    {
        Intent intent = getIntent();
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("onCreate intent=");
        stringbuilder.append(intent);
        PxLog.v(s, stringbuilder.toString());
        super.onCreate(bundle);
        handleIntent(intent);
        finish();
    }

    protected void onNewIntent(Intent intent)
    {
        String s = TAG;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("onNewIntent intent=");
        stringbuilder.append(intent);
        PxLog.v(s, stringbuilder.toString());
        handleIntent(intent);
    }

    private static final String TAG = "SearchableProgramInfo";

}
