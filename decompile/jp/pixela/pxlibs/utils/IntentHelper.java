// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.utils;

import android.content.Intent;
import android.os.Bundle;
import java.util.Iterator;
import java.util.Set;

public final class IntentHelper
{

    private IntentHelper()
    {
    }

    public static String dump(Intent intent)
    {
        if(intent == null)
            return "intent == null";
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(intent);
        Bundle bundle = intent.getExtras();
        if(bundle != null)
        {
            stringbuilder.append(" extras={ ");
            for(Iterator iterator = bundle.keySet().iterator(); iterator.hasNext();)
            {
                intent = (String)iterator.next();
                Object obj = bundle.get(intent);
                if(obj == null)
                    stringbuilder.append(String.format("%s=null ", new Object[] {
                        intent
                    }));
                else
                    stringbuilder.append(String.format("%s=%s (%s) ", new Object[] {
                        intent, obj.toString(), obj.getClass().getName()
                    }));
            }

            stringbuilder.append("}");
        }
        return stringbuilder.toString();
    }
}
