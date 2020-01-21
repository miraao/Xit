// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.common;

import android.os.Parcel;
import android.os.Parcelable;

public class AutoMessageInfo
    implements Parcelable
{
    public static final class CharacterSize extends Enum
    {

        public static CharacterSize fromValue(int i)
        {
            CharacterSize charactersize = SIZE1;
            CharacterSize acharactersize[] = values();
            int j = acharactersize.length;
            for(int k = 0; k < j; k++)
            {
                CharacterSize charactersize1 = acharactersize[k];
                if(charactersize1.toValue() == i)
                    charactersize = charactersize1;
            }

            return charactersize;
        }

        public static CharacterSize valueOf(String s)
        {
            return (CharacterSize)Enum.valueOf(jp/pixela/common/AutoMessageInfo$CharacterSize, s);
        }

        public static CharacterSize[] values()
        {
            return (CharacterSize[])$VALUES.clone();
        }

        public int toValue()
        {
            return mValue;
        }

        private static final CharacterSize $VALUES[];
        public static final CharacterSize RESERVED;
        public static final CharacterSize SIZE1;
        public static final CharacterSize SIZE2;
        public static final CharacterSize SIZE3;
        private int mValue;

        static 
        {
            SIZE1 = new CharacterSize("SIZE1", 0, 0);
            SIZE2 = new CharacterSize("SIZE2", 1, 1);
            SIZE3 = new CharacterSize("SIZE3", 2, 2);
            RESERVED = new CharacterSize("RESERVED", 3, 3);
            $VALUES = (new CharacterSize[] {
                SIZE1, SIZE2, SIZE3, RESERVED
            });
        }

        private CharacterSize(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }

    public static final class DisplayType extends Enum
    {

        public static DisplayType fromValue(int i)
        {
            DisplayType displaytype = ERASABLE_DISPLEY;
            DisplayType adisplaytype[] = values();
            int j = adisplaytype.length;
            for(int k = 0; k < j; k++)
            {
                DisplayType displaytype1 = adisplaytype[k];
                if(displaytype1.toValue() == i)
                    displaytype = displaytype1;
            }

            return displaytype;
        }

        public static DisplayType valueOf(String s)
        {
            return (DisplayType)Enum.valueOf(jp/pixela/common/AutoMessageInfo$DisplayType, s);
        }

        public static DisplayType[] values()
        {
            return (DisplayType[])$VALUES.clone();
        }

        public int toValue()
        {
            return mValue;
        }

        private static final DisplayType $VALUES[];
        public static final DisplayType ERASABLE_DISPLEY;
        public static final DisplayType ERASE_DIRECTION;
        public static final DisplayType FORCED_DISPLAY;
        private int mValue;

        static 
        {
            ERASABLE_DISPLEY = new DisplayType("ERASABLE_DISPLEY", 0, 0);
            FORCED_DISPLAY = new DisplayType("FORCED_DISPLAY", 1, 1);
            ERASE_DIRECTION = new DisplayType("ERASE_DIRECTION", 2, 2);
            $VALUES = (new DisplayType[] {
                ERASABLE_DISPLEY, FORCED_DISPLAY, ERASE_DIRECTION
            });
        }

        private DisplayType(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }

    public static final class HorizontalPosition extends Enum
    {

        public static HorizontalPosition fromValue(int i)
        {
            HorizontalPosition horizontalposition = LEFT;
            HorizontalPosition ahorizontalposition[] = values();
            int j = ahorizontalposition.length;
            for(int k = 0; k < j; k++)
            {
                HorizontalPosition horizontalposition1 = ahorizontalposition[k];
                if(horizontalposition1.toValue() == i)
                    horizontalposition = horizontalposition1;
            }

            return horizontalposition;
        }

        public static HorizontalPosition valueOf(String s)
        {
            return (HorizontalPosition)Enum.valueOf(jp/pixela/common/AutoMessageInfo$HorizontalPosition, s);
        }

        public static HorizontalPosition[] values()
        {
            return (HorizontalPosition[])$VALUES.clone();
        }

        public int toValue()
        {
            return mValue;
        }

        private static final HorizontalPosition $VALUES[];
        public static final HorizontalPosition CENTER;
        public static final HorizontalPosition LEFT;
        public static final HorizontalPosition NO_DIRECTION;
        public static final HorizontalPosition RIGHT;
        private int mValue;

        static 
        {
            LEFT = new HorizontalPosition("LEFT", 0, 0);
            CENTER = new HorizontalPosition("CENTER", 1, 1);
            RIGHT = new HorizontalPosition("RIGHT", 2, 2);
            NO_DIRECTION = new HorizontalPosition("NO_DIRECTION", 3, 3);
            $VALUES = (new HorizontalPosition[] {
                LEFT, CENTER, RIGHT, NO_DIRECTION
            });
        }

        private HorizontalPosition(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }

    public static final class VerticalPosition extends Enum
    {

        public static VerticalPosition fromValue(int i)
        {
            VerticalPosition verticalposition = TOP;
            VerticalPosition averticalposition[] = values();
            int j = averticalposition.length;
            for(int k = 0; k < j; k++)
            {
                VerticalPosition verticalposition1 = averticalposition[k];
                if(verticalposition1.toValue() == i)
                    verticalposition = verticalposition1;
            }

            return verticalposition;
        }

        public static VerticalPosition valueOf(String s)
        {
            return (VerticalPosition)Enum.valueOf(jp/pixela/common/AutoMessageInfo$VerticalPosition, s);
        }

        public static VerticalPosition[] values()
        {
            return (VerticalPosition[])$VALUES.clone();
        }

        public int toValue()
        {
            return mValue;
        }

        private static final VerticalPosition $VALUES[];
        public static final VerticalPosition BOTTOM;
        public static final VerticalPosition CENTER;
        public static final VerticalPosition NO_DIRECTION;
        public static final VerticalPosition TOP;
        private int mValue;

        static 
        {
            TOP = new VerticalPosition("TOP", 0, 0);
            CENTER = new VerticalPosition("CENTER", 1, 1);
            BOTTOM = new VerticalPosition("BOTTOM", 2, 2);
            NO_DIRECTION = new VerticalPosition("NO_DIRECTION", 3, 3);
            $VALUES = (new VerticalPosition[] {
                TOP, CENTER, BOTTOM, NO_DIRECTION
            });
        }

        private VerticalPosition(String s, int i, int j)
        {
            super(s, i);
            mValue = j;
        }
    }


    public AutoMessageInfo()
    {
    }

    public AutoMessageInfo(Parcel parcel)
    {
        mText = parcel.readString();
        mDisplayType = DisplayType.fromValue(parcel.readInt());
        mHorizontalPosition = HorizontalPosition.fromValue(parcel.readInt());
        mVerticalPosition = VerticalPosition.fromValue(parcel.readInt());
        mCharacterSize = CharacterSize.fromValue(parcel.readInt());
    }

    public CharacterSize GetCharacterSize()
    {
        return mCharacterSize;
    }

    public DisplayType GetDisplayType()
    {
        return mDisplayType;
    }

    public HorizontalPosition GetHorizontalPosition()
    {
        return mHorizontalPosition;
    }

    public String GetText()
    {
        return mText;
    }

    public VerticalPosition GetVerticalPosition()
    {
        return mVerticalPosition;
    }

    public void SetCharacterSize(CharacterSize charactersize)
    {
        mCharacterSize = charactersize;
    }

    public void SetDisplayType(DisplayType displaytype)
    {
        mDisplayType = displaytype;
    }

    public void SetHorizontalPosition(HorizontalPosition horizontalposition)
    {
        mHorizontalPosition = horizontalposition;
    }

    public void SetText(String s)
    {
        mText = s;
    }

    public void SetVerticalPosition(VerticalPosition verticalposition)
    {
        mVerticalPosition = verticalposition;
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mText);
        parcel.writeInt(mDisplayType.toValue());
        parcel.writeInt(mHorizontalPosition.toValue());
        parcel.writeInt(mVerticalPosition.toValue());
        parcel.writeInt(mCharacterSize.toValue());
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AutoMessageInfo createFromParcel(Parcel parcel)
        {
            return new AutoMessageInfo(parcel);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public AutoMessageInfo[] newArray(int i)
        {
            return new AutoMessageInfo[i];
        }

    }
;
    private CharacterSize mCharacterSize;
    private DisplayType mDisplayType;
    private HorizontalPosition mHorizontalPosition;
    private String mText;
    private VerticalPosition mVerticalPosition;

}
