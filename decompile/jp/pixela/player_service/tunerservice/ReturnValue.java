// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;


public class ReturnValue
{
    public static final class ValueTypeT extends Enum
    {

        public static ValueTypeT valueOf(String s)
        {
            return (ValueTypeT)Enum.valueOf(jp/pixela/player_service/tunerservice/ReturnValue$ValueTypeT, s);
        }

        public static ValueTypeT[] values()
        {
            return (ValueTypeT[])$VALUES.clone();
        }

        private static final ValueTypeT $VALUES[];
        public static final ValueTypeT VT_NONE;
        public static final ValueTypeT VT_OBJECT;
        public static final ValueTypeT VT_OBJECTARRAY;

        static 
        {
            VT_NONE = new ValueTypeT("VT_NONE", 0);
            VT_OBJECT = new ValueTypeT("VT_OBJECT", 1);
            VT_OBJECTARRAY = new ValueTypeT("VT_OBJECTARRAY", 2);
            $VALUES = (new ValueTypeT[] {
                VT_NONE, VT_OBJECT, VT_OBJECTARRAY
            });
        }

        private ValueTypeT(String s, int i)
        {
            super(s, i);
        }
    }


    public ReturnValue()
    {
        Error_ = 0;
        ValueType_ = ValueTypeT.VT_NONE;
    }

    public int getError()
    {
        return Error_;
    }

    public Object getObject()
    {
        return Object_;
    }

    public Object[] getObjectArray()
    {
        return ObjectArray_;
    }

    public ValueTypeT getValueType()
    {
        return ValueType_;
    }

    public void setError(int i)
    {
        Error_ = i;
    }

    public void setObject(Object obj)
    {
        Object_ = obj;
    }

    public void setObjectArray(Object aobj[])
    {
        ObjectArray_ = aobj;
    }

    public void setValueType(ValueTypeT valuetypet)
    {
        ValueType_ = valuetypet;
    }

    private int Error_;
    private Object ObjectArray_[];
    private Object Object_;
    private ValueTypeT ValueType_;
}
