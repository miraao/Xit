// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.common;


public interface IDelegate
{
    public static interface IAction
    {

        public abstract void invoke();
    }

    public static interface IAction1
    {

        public abstract void invoke(Object obj);
    }

    public static interface IAction2
    {

        public abstract void invoke(Object obj, Object obj1);
    }

    public static interface IAction3
    {

        public abstract void invoke(Object obj, Object obj1, Object obj2);
    }

    public static interface IAction4
    {

        public abstract void invoke(Object obj, Object obj1, Object obj2, Object obj3);
    }

    public static interface IAction5
    {

        public abstract void invoke(Object obj, Object obj1, Object obj2, Object obj3, Object obj4);
    }

    public static interface IFunc
    {

        public abstract Object invoke();
    }

    public static interface IFunc1
    {

        public abstract Object invoke(Object obj);
    }

    public static interface IFunc2
    {

        public abstract Object invoke(Object obj, Object obj1);
    }

    public static interface IFunc3
    {

        public abstract Object invoke(Object obj, Object obj1, Object obj2);
    }

    public static interface IFunc4
    {

        public abstract Object invoke(Object obj, Object obj1, Object obj2, Object obj3);
    }

    public static interface IFunc5
    {

        public abstract Object invoke(Object obj, Object obj1, Object obj2, Object obj3, Object obj4);
    }

}
