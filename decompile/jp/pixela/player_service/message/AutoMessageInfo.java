// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.message;


public class AutoMessageInfo
{
    public static final class CharacterSize extends Enum
    {

        public static CharacterSize valueOf(String s)
        {
            return (CharacterSize)Enum.valueOf(jp/pixela/player_service/message/AutoMessageInfo$CharacterSize, s);
        }

        public static CharacterSize[] values()
        {
            return (CharacterSize[])$VALUES.clone();
        }

        private static final CharacterSize $VALUES[];
        public static final CharacterSize C_NONE;
        public static final CharacterSize C_SIZE1;
        public static final CharacterSize C_SIZE2;
        public static final CharacterSize C_SIZE3;

        static 
        {
            C_SIZE1 = new CharacterSize("C_SIZE1", 0);
            C_SIZE2 = new CharacterSize("C_SIZE2", 1);
            C_SIZE3 = new CharacterSize("C_SIZE3", 2);
            C_NONE = new CharacterSize("C_NONE", 3);
            $VALUES = (new CharacterSize[] {
                C_SIZE1, C_SIZE2, C_SIZE3, C_NONE
            });
        }

        private CharacterSize(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class DisplayAction extends Enum
    {

        public static DisplayAction valueOf(String s)
        {
            return (DisplayAction)Enum.valueOf(jp/pixela/player_service/message/AutoMessageInfo$DisplayAction, s);
        }

        public static DisplayAction[] values()
        {
            return (DisplayAction[])$VALUES.clone();
        }

        private static final DisplayAction $VALUES[];
        public static final DisplayAction ERASABLE_DISPLAY;
        public static final DisplayAction ERASE;
        public static final DisplayAction FORCED_DISPLAY;

        static 
        {
            ERASABLE_DISPLAY = new DisplayAction("ERASABLE_DISPLAY", 0);
            FORCED_DISPLAY = new DisplayAction("FORCED_DISPLAY", 1);
            ERASE = new DisplayAction("ERASE", 2);
            $VALUES = (new DisplayAction[] {
                ERASABLE_DISPLAY, FORCED_DISPLAY, ERASE
            });
        }

        private DisplayAction(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class HorizontalPosition extends Enum
    {

        public static HorizontalPosition valueOf(String s)
        {
            return (HorizontalPosition)Enum.valueOf(jp/pixela/player_service/message/AutoMessageInfo$HorizontalPosition, s);
        }

        public static HorizontalPosition[] values()
        {
            return (HorizontalPosition[])$VALUES.clone();
        }

        private static final HorizontalPosition $VALUES[];
        public static final HorizontalPosition H_CENTER;
        public static final HorizontalPosition H_LEFT;
        public static final HorizontalPosition H_NONE;
        public static final HorizontalPosition H_RIGHT;

        static 
        {
            H_LEFT = new HorizontalPosition("H_LEFT", 0);
            H_CENTER = new HorizontalPosition("H_CENTER", 1);
            H_RIGHT = new HorizontalPosition("H_RIGHT", 2);
            H_NONE = new HorizontalPosition("H_NONE", 3);
            $VALUES = (new HorizontalPosition[] {
                H_LEFT, H_CENTER, H_RIGHT, H_NONE
            });
        }

        private HorizontalPosition(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class VerticalPosition extends Enum
    {

        public static VerticalPosition valueOf(String s)
        {
            return (VerticalPosition)Enum.valueOf(jp/pixela/player_service/message/AutoMessageInfo$VerticalPosition, s);
        }

        public static VerticalPosition[] values()
        {
            return (VerticalPosition[])$VALUES.clone();
        }

        private static final VerticalPosition $VALUES[];
        public static final VerticalPosition V_BOTTOM;
        public static final VerticalPosition V_CENTER;
        public static final VerticalPosition V_NONE;
        public static final VerticalPosition V_TOP;

        static 
        {
            V_TOP = new VerticalPosition("V_TOP", 0);
            V_CENTER = new VerticalPosition("V_CENTER", 1);
            V_BOTTOM = new VerticalPosition("V_BOTTOM", 2);
            V_NONE = new VerticalPosition("V_NONE", 3);
            $VALUES = (new VerticalPosition[] {
                V_TOP, V_CENTER, V_BOTTOM, V_NONE
            });
        }

        private VerticalPosition(String s, int i)
        {
            super(s, i);
        }
    }


    public AutoMessageInfo()
    {
    }

    public CharacterSize getCharacterSize()
    {
        return characterSize_;
    }

    public DisplayAction getDisplayAction()
    {
        return displayAction_;
    }

    public HorizontalPosition getHorizontalPosition()
    {
        return horizontalPosition_;
    }

    public String getMessage()
    {
        return message_;
    }

    public VerticalPosition getVerticalPosition()
    {
        return verticalPosition_;
    }

    public void setCharacterSize(CharacterSize charactersize)
    {
        characterSize_ = charactersize;
    }

    public void setDisplayAction(DisplayAction displayaction)
    {
        displayAction_ = displayaction;
    }

    public void setHorizontalPosition(HorizontalPosition horizontalposition)
    {
        horizontalPosition_ = horizontalposition;
    }

    public void setMessage(String s)
    {
        message_ = s;
    }

    public void setVerticalPosition(VerticalPosition verticalposition)
    {
        verticalPosition_ = verticalposition;
    }

    private CharacterSize characterSize_;
    private DisplayAction displayAction_;
    private HorizontalPosition horizontalPosition_;
    private String message_;
    private VerticalPosition verticalPosition_;
}
