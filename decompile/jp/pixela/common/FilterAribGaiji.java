// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.common;


public class FilterAribGaiji
{
    static class AribGaijiRange
    {

        int end;
        int start;

        AribGaijiRange(int i, int j)
        {
            start = i;
            end = j;
        }
    }


    public FilterAribGaiji()
    {
    }

    public static String getFilterAribGaiji(String s)
    {
        if(s == null)
            return null;
        s = new StringBuffer(s);
        for(int i = s.length() - 1; i >= 0; i--)
            if(isAribGaiji(s.codePointAt(i)))
                s.deleteCharAt(i);

        return s.substring(0);
    }

    private static boolean isAribGaiji(int i)
    {
        for(int j = 0; j < arib_gaiji_range_.length; j++)
            if(arib_gaiji_range_[j].start <= i && arib_gaiji_range_[j].end >= i)
                return true;

        for(int k = 0; k < arib_gaiji_.length; k++)
            if(arib_gaiji_[k] == i)
                return true;

        return false;
    }

    public static String replaceAribGaijiWithWhiteSpace(String s)
    {
        if(s == null)
            return null;
        s = new StringBuffer(s);
        for(int i = s.length() - 1; i >= 0; i--)
            if(isAribGaiji(s.codePointAt(i)))
                s.setCharAt(i, ' ');

        return s.substring(0);
    }

    static int arib_gaiji_[] = {
        169, 174, 8252, 8467, 9654, 9664, 9824, 9827, 9836, 12342, 
        12857, 13169, 13258, 20221, 20223, 20378, 20425, 20636, 20766, 20924, 
        21255, 21345, 21356, 21581, 21654, 21660, 21673, 21774, 21834, 22130, 
        22244, 22618, 22656, 23012, 23075, 23125, 23532, 24236, 24372, 24389, 
        24503, 24599, 24880, 26148, 26312, 26329, 26897, 26939, 27205, 27281, 
        27355, 27633, 27872, 27950, 28023, 28095, 28106, 28152, 28186, 28510, 
        28665, 28772, 28999, 29121, 29184, 29599, 30069, 30081, 30578, 30920, 
        30944, 31047, 31150, 31194, 31262, 31615, 31793, 32139, 32673, 33048, 
        33082, 33454, 33883, 34012, 34028, 34137, 34254, 34645, 34796, 34827, 
        35061, 35282, 35574, 36302, 36795, 36854, 37085, 37159, 37165, 37298, 
        37427, 37512, 37665, 37704, 38290, 38622, 39171, 39232, 39641, 39894, 
        40407
    };
    static AribGaijiRange arib_gaiji_range_[] = {
        new AribGaijiRange(188, 190), new AribGaijiRange(8531, 8539), new AribGaijiRange(8544, 8555), new AribGaijiRange(9312, 9343), new AribGaijiRange(9352, 9360), new AribGaijiRange(9728, 9730), new AribGaijiRange(9829, 9830), new AribGaijiRange(10102, 10111), new AribGaijiRange(12310, 12311), new AribGaijiRange(12842, 12851), 
        new AribGaijiRange(12854, 12855), new AribGaijiRange(13179, 13182), new AribGaijiRange(13199, 13200), new AribGaijiRange(13213, 13214), new AribGaijiRange(13216, 13218), new AribGaijiRange(13220, 13221), new AribGaijiRange(22323, 22324), new AribGaijiRange(26362, 26363), new AribGaijiRange(40628, 40629), new AribGaijiRange(57472, 57486), 
        new AribGaijiRange(57488, 57599), new AribGaijiRange(57728, 57855), new AribGaijiRange(57984, 58111), new AribGaijiRange(58240, 58278)
    };

}
