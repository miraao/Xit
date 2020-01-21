// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.utils.android;

import android.content.Context;
import java.lang.reflect.Method;

public class SystemPropertiesProxy
{

    private SystemPropertiesProxy()
    {
    }

    public static String get(Context context, String s)
        throws IllegalArgumentException
    {
        try
        {
            context = context.getClassLoader().loadClass("android.os.SystemProperties");
            Method method = context.getMethod("get", new Class[] {
                java/lang/String
            });
            String s1 = JVM INSTR new #34  <Class String>;
            s1.String(s);
            context = (String)method.invoke(context, new Object[] {
                s1
            });
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw context;
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            context = "";
        }
        return context;
    }

    public static String get(Context context, String s, String s1)
        throws IllegalArgumentException
    {
        Class class1 = context.getClassLoader().loadClass("android.os.SystemProperties");
        Method method = class1.getMethod("get", new Class[] {
            java/lang/String, java/lang/String
        });
        context = JVM INSTR new #34  <Class String>;
        context.String(s);
        s = JVM INSTR new #34  <Class String>;
        s.String(s1);
        context = (String)method.invoke(class1, new Object[] {
            context, s
        });
        s1 = context;
        break MISSING_BLOCK_LABEL_78;
        context;
        return s1;
        context;
        throw context;
    }

    public static Boolean getBoolean(Context context, String s, boolean flag)
        throws IllegalArgumentException
    {
        try
        {
            context = context.getClassLoader().loadClass("android.os.SystemProperties");
            Method method = context.getMethod("getBoolean", new Class[] {
                java/lang/String, Boolean.TYPE
            });
            String s1 = JVM INSTR new #34  <Class String>;
            s1.String(s);
            s = JVM INSTR new #56  <Class Boolean>;
            s.Boolean(flag);
            context = (Boolean)method.invoke(context, new Object[] {
                s1, s
            });
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw context;
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            context = Boolean.valueOf(flag);
        }
        return context;
    }

    public static Integer getInt(Context context, String s, int i)
        throws IllegalArgumentException
    {
        try
        {
            Class class1 = context.getClassLoader().loadClass("android.os.SystemProperties");
            Method method = class1.getMethod("getInt", new Class[] {
                java/lang/String, Integer.TYPE
            });
            context = JVM INSTR new #34  <Class String>;
            context.String(s);
            s = JVM INSTR new #72  <Class Integer>;
            s.Integer(i);
            context = (Integer)method.invoke(class1, new Object[] {
                context, s
            });
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw context;
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            context = Integer.valueOf(i);
        }
        return context;
    }

    public static Long getLong(Context context, String s, long l)
        throws IllegalArgumentException
    {
        try
        {
            Class class1 = context.getClassLoader().loadClass("android.os.SystemProperties");
            Method method = class1.getMethod("getLong", new Class[] {
                java/lang/String, Long.TYPE
            });
            context = JVM INSTR new #34  <Class String>;
            context.String(s);
            s = JVM INSTR new #84  <Class Long>;
            s.Long(l);
            context = (Long)method.invoke(class1, new Object[] {
                context, s
            });
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw context;
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            context = Long.valueOf(l);
        }
        return context;
    }

    public static void set(Context context, String s, String s1)
        throws IllegalArgumentException
    {
        Class class1 = context.getClassLoader().loadClass("android.os.SystemProperties");
        context = class1.getMethod("set", new Class[] {
            java/lang/String, java/lang/String
        });
        String s2 = JVM INSTR new #34  <Class String>;
        s2.String(s);
        s = JVM INSTR new #34  <Class String>;
        s.String(s1);
        context.invoke(class1, new Object[] {
            s2, s
        });
_L2:
        return;
        context;
        throw context;
        context;
        if(true) goto _L2; else goto _L1
_L1:
    }
}
