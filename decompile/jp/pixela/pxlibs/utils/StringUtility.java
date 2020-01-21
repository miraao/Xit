// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.pxlibs.utils;


// Referenced classes of package jp.pixela.pxlibs.utils:
//            Utility

public final class StringUtility
{

    private StringUtility()
    {
    }

    public static final transient String concat(Object aobj[])
    {
        StringBuilder stringbuilder = new StringBuilder();
        for(int i = 0; i < aobj.length; i++)
            stringbuilder.append(aobj[i]);

        return stringbuilder.toString();
    }

    public static final String cutNull(String s)
    {
        if(!isNullOrEmpty(s))
        {
            int i = s.indexOf('\0');
            if(i != -1)
                return s.substring(0, i);
        }
        return s;
    }

    public static final transient boolean isContain(String s, boolean flag, String as[])
    {
        boolean flag1 = false;
        boolean flag2 = flag1;
        if(s != null)
        {
            flag2 = flag1;
            if(as != null)
            {
                flag2 = flag1;
                if(as.length > 0)
                {
                    int i = 0;
                    do
                    {
                        flag2 = flag1;
                        if(i >= as.length)
                            break;
                        String s1 = as[i];
                        if(!flag && s.equals(s1) || flag && s.equalsIgnoreCase(s1))
                        {
                            flag2 = true;
                            break;
                        }
                        i++;
                    } while(true);
                }
            }
        }
        return flag2;
    }

    public static final transient boolean isExistChar(char c, char ac[])
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(ac != null)
        {
            flag1 = flag;
            if(ac.length > 0)
            {
                int i = 0;
                do
                {
                    flag1 = flag;
                    if(i >= ac.length)
                        break;
                    if(c == ac[i])
                    {
                        flag1 = true;
                        break;
                    }
                    i++;
                } while(true);
            }
        }
        return flag1;
    }

    public static final boolean isNullOrEmpty(String s)
    {
        boolean flag;
        if(s != null && s.length() != 0)
            flag = false;
        else
            flag = true;
        return flag;
    }

    public static final boolean isNullOrWhiteSpace(String s)
    {
        boolean flag;
        if(s != null && trimUni(s).length() != 0)
            flag = false;
        else
            flag = true;
        return flag;
    }

    public static final boolean isNumeric(String s)
    {
        boolean flag1;
label0:
        {
            boolean flag = isNullOrWhiteSpace(s);
            flag1 = false;
            if(flag)
                return false;
            int i = 0;
            int j = i;
            for(; i < s.length(); i++)
            {
                char c = s.charAt(i);
                if(i == 0 && c == '-')
                    continue;
                if(c == '.')
                {
                    if(j != 1)
                    {
                        j = 1;
                        continue;
                    }
                    break label0;
                }
                if(!Character.isDigit(c))
                    break label0;
            }

            flag1 = true;
        }
        return flag1;
    }

    public static final transient String join(String s, String as[])
    {
        StringBuilder stringbuilder = new StringBuilder();
        for(int i = 0; i < as.length; i++)
        {
            String s1 = as[i];
            if(isNullOrEmpty(s1))
                continue;
            if(stringbuilder.length() > 0)
                stringbuilder.append(s);
            stringbuilder.append(s1);
        }

        return stringbuilder.toString();
    }

    public static final String left(String s, int i)
    {
        if(!isNullOrEmpty(s) && i <= s.length())
        {
            if(1 <= i && i <= s.length())
                return s.substring(0, i);
            else
                return "";
        } else
        {
            return s;
        }
    }

    public static final String mid(String s, int i)
    {
        if(isNullOrEmpty(s))
            return s;
        int j = s.length();
        if(1 <= i && i <= j)
            return s.substring(i - 1);
        else
            return "";
    }

    public static final String mid(String s, int i, int j)
    {
        if(isNullOrEmpty(s))
            return s;
        int k = s.length();
        if(1 <= i && i <= k)
        {
            j = (j + i) - 1;
            if(j <= k)
                return s.substring(i - 1, j);
            else
                return s.substring(i - 1);
        } else
        {
            return "";
        }
    }

    public static final transient int notIndexOf(String s, char ac[])
    {
label0:
        {
            int j;
label1:
            {
                if(isNullOrEmpty(s))
                    return -1;
                boolean flag = false;
                if(ac == null || ac.length == 0)
                    break label0;
                s = s.toCharArray();
                int i = 0;
                do
                {
                    j = ((flag) ? 1 : 0);
                    if(i >= s.length)
                        break label1;
                    if(!isExistChar(s[i], ac))
                        break;
                    i++;
                } while(true);
                j = i;
            }
            return j;
        }
        return 0;
    }

    public static final transient int notLastIndexOf(String s, char ac[])
    {
        int i;
label0:
        {
            int k;
label1:
            {
                if(isNullOrEmpty(s))
                    return -1;
                s = s.toCharArray();
                i = s.length - 1;
                if(ac == null || ac.length == 0)
                    break label0;
                int j = i;
                do
                {
                    k = i;
                    if(j < 0)
                        break label1;
                    if(!isExistChar(s[j], ac))
                        break;
                    j--;
                } while(true);
                k = j;
            }
            return k;
        }
        return i;
    }

    public static final String padLeft(String s, int i)
    {
        return padLeft(s, i, ' ');
    }

    public static final String padLeft(String s, int i, char c)
    {
        return padding(s, i, c, true);
    }

    public static final String padRight(String s, int i)
    {
        return padRight(s, i, ' ');
    }

    public static final String padRight(String s, int i, char c)
    {
        return padding(s, i, c, false);
    }

    private static final String padding(String s, int i, char c, boolean flag)
    {
        String s1 = (String)Utility.nvl(s, "");
        i -= s1.length();
        if(i <= 0)
            return s1;
        s = repeat(c, i);
        if(flag)
            s = s.concat(s1);
        else
            s = s1.concat(s);
        return s;
    }

    public static final String repeat(char c, int i)
    {
        if(i <= 0)
            return "";
        char ac[] = new char[i];
        for(i = 0; i < ac.length; i++)
            ac[i] = c;

        return String.valueOf(ac);
    }

    public static final String repeat(String s, int i)
    {
        if(i <= 0)
            return "";
        s = (String)Utility.nvl(s, "");
        int j = s.length();
        if(i != 1 && j != 0)
        {
            StringBuilder stringbuilder = new StringBuilder(j * i);
            for(int k = 0; k < i; k++)
                stringbuilder.append(s);

            return stringbuilder.toString();
        } else
        {
            return s;
        }
    }

    public static final String right(String s, int i)
    {
        if(!isNullOrEmpty(s) && i <= s.length())
        {
            if(1 <= i && i <= s.length())
                return s.substring(s.length() - i);
            else
                return "";
        } else
        {
            return s;
        }
    }

    public static final transient String trim(String s, char ac[])
    {
        if(isNullOrEmpty(s))
            return s;
        if(ac != null && ac.length != 0)
            return s.substring(notIndexOf(s, ac), notLastIndexOf(s, ac) + 1);
        else
            return s.trim();
    }

    public static final String trimNull(String s)
    {
        return trim(s, new char[] {
            '\0'
        });
    }

    public static final String trimUni(String s)
    {
        return trim(s, new char[] {
            ' ', '\u3000'
        });
    }

    public static final String EMPTY = "";
}
