// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.utils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.TimeUnit;

public final class Utility
{

    private Utility()
    {
    }

    public static final transient Object[] ary(Object aobj[])
    {
        return aobj;
    }

    public static final transient List asList(Object aobj[])
    {
        if(aobj != null)
            aobj = new ArrayList(Arrays.asList(aobj));
        else
            aobj = null;
        return ((List) (aobj));
    }

    public static final transient boolean containsAll(Collection collection, Object aobj[])
    {
        if(collection != null && aobj != null && aobj.length > 0)
            return collection.containsAll(Arrays.asList(aobj));
        else
            return false;
    }

    public static final transient boolean containsAny(Collection collection, Object aobj[])
    {
        if(collection != null && aobj != null && aobj.length > 0)
        {
            int i = aobj.length;
            for(int j = 0; j < i; j++)
            {
                Object obj = aobj[j];
                if(obj != null && collection.contains(obj))
                    return true;
            }

            return false;
        } else
        {
            return false;
        }
    }

    public static final transient Object[] copyArray(Class class1, Object aobj[])
    {
        if(class1 != null && aobj != null)
        {
            class1 = ((Class) ((Object[])Array.newInstance(class1, aobj.length)));
            System.arraycopy(((Object) (aobj)), 0, class1, 0, aobj.length);
        } else
        {
            class1 = null;
        }
        return class1;
    }

    public static final List copyList(Collection collection)
    {
        if(collection != null)
            collection = new ArrayList(collection);
        else
            collection = null;
        return collection;
    }

    public static final String getGuid()
    {
        return UUID.randomUUID().toString();
    }

    public static final boolean hasFlag(int i, int j)
    {
        boolean flag;
        if((i & j) == j)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static final transient boolean isContain(Object obj, Object aobj[])
    {
        if(obj != null && aobj != null && aobj.length > 0)
        {
            int i = aobj.length;
            for(int j = 0; j < i; j++)
                if(obj.equals(aobj[j]))
                    return true;

            return false;
        } else
        {
            return false;
        }
    }

    public static final Object nvl(Object obj, Object obj1)
    {
        if(obj == null)
            obj = obj1;
        return obj;
    }

    public static final boolean sleep(long l)
    {
        return sleep(l, TimeUnit.MILLISECONDS);
    }

    public static final boolean sleep(long l, TimeUnit timeunit)
    {
        if(timeunit == null)
            throw new NullPointerException("TimeUnit Object is null.");
        boolean flag = true;
        try
        {
            timeunit.sleep(l);
        }
        // Misplaced declaration of an exception variable
        catch(TimeUnit timeunit)
        {
            flag = false;
        }
        return flag;
    }

    public static final Object[] toArray(Class class1, Collection collection)
    {
        if(class1 == null && collection == null)
        {
            class1 = null;
        } else
        {
            class1 = ((Class) ((Object[])Array.newInstance(class1, collection.size())));
            collection.toArray(class1);
        }
        return class1;
    }
}
