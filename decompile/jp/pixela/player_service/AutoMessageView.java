// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import jp.pixela.common.*;

public class AutoMessageView extends View
{

    public AutoMessageView(Context context)
    {
        super(context);
        automessageText_ = new ArrayList();
        display_type_ = jp.pixela.common.AutoMessageInfo.DisplayType.FORCED_DISPLAY;
        horizontal_position_ = jp.pixela.common.AutoMessageInfo.HorizontalPosition.LEFT;
        vertical_position_ = jp.pixela.common.AutoMessageInfo.VerticalPosition.BOTTOM;
        character_size_ = jp.pixela.common.AutoMessageInfo.CharacterSize.SIZE1;
        text_paint_ = new Paint(1);
        background_paint_ = new Paint(1);
        text_rect_ = new Rect();
        background_rect_ = new RectF();
        context_ = context;
    }

    public AutoMessageView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        automessageText_ = new ArrayList();
        display_type_ = jp.pixela.common.AutoMessageInfo.DisplayType.FORCED_DISPLAY;
        horizontal_position_ = jp.pixela.common.AutoMessageInfo.HorizontalPosition.LEFT;
        vertical_position_ = jp.pixela.common.AutoMessageInfo.VerticalPosition.BOTTOM;
        character_size_ = jp.pixela.common.AutoMessageInfo.CharacterSize.SIZE1;
        text_paint_ = new Paint(1);
        background_paint_ = new Paint(1);
        text_rect_ = new Rect();
        background_rect_ = new RectF();
        context_ = context;
    }

    public AutoMessageView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        automessageText_ = new ArrayList();
        display_type_ = jp.pixela.common.AutoMessageInfo.DisplayType.FORCED_DISPLAY;
        horizontal_position_ = jp.pixela.common.AutoMessageInfo.HorizontalPosition.LEFT;
        vertical_position_ = jp.pixela.common.AutoMessageInfo.VerticalPosition.BOTTOM;
        character_size_ = jp.pixela.common.AutoMessageInfo.CharacterSize.SIZE1;
        text_paint_ = new Paint(1);
        background_paint_ = new Paint(1);
        text_rect_ = new Rect();
        background_rect_ = new RectF();
        context_ = context;
    }

    private void caleAutoMessageRect()
    {
        text_rect_.setEmpty();
        if(automessageText_.size() == 0)
            return;
        android.graphics.Paint.FontMetrics fontmetrics = text_paint_.getFontMetrics();
        float f = fontmetrics.descent;
        float f1 = fontmetrics.ascent;
        float f2 = 0.0F;
        for(int i = 0; i < automessageText_.size();)
        {
            float f3 = text_paint_.measureText((String)automessageText_.get(i), 0, ((String)automessageText_.get(i)).length());
            float f4 = f2;
            if(f2 < f3)
                f4 = f3;
            i++;
            f2 = f4;
        }

        text_rect_.set(0, 0, (int)f2, (int)((float)automessageText_.size() * (f - f1)));
        int j = getWidth();
        int k = getHeight();
        if(j != 0 && k != 0)
        {
            if(horizontal_position_ == jp.pixela.common.AutoMessageInfo.HorizontalPosition.LEFT)
                text_rect_.offset((int)((double)j * 0.050000000000000003D), 0);
            else
            if(horizontal_position_ == jp.pixela.common.AutoMessageInfo.HorizontalPosition.RIGHT)
                text_rect_.offset((int)((double)(j - text_rect_.width()) - (double)j * 0.050000000000000003D), 0);
            else
                text_rect_.offset((j - text_rect_.width()) / 2, 0);
            if(vertical_position_ == jp.pixela.common.AutoMessageInfo.VerticalPosition.TOP)
                text_rect_.offset(0, (int)((double)k * 0.050000000000000003D));
            else
            if(vertical_position_ == jp.pixela.common.AutoMessageInfo.VerticalPosition.BOTTOM)
                text_rect_.offset(0, (int)((double)(k - text_rect_.height()) - (double)k * 0.050000000000000003D));
            else
                text_rect_.offset(0, (k - text_rect_.height()) / 2);
            return;
        } else
        {
            return;
        }
    }

    private void spliteAutoessage(String s)
    {
        automessageText_ = new ArrayList();
        if(s == null)
            return;
        String s1 = s;
        if(s.length() == 0)
            return;
        do
        {
            int l;
label0:
            {
label1:
                {
label2:
                    {
                        if(s1.length() == 0)
                            break label1;
                        if(s1.indexOf("\r") == 0)
                        {
                            s1 = s1.substring(1);
                            continue;
                        }
                        int i = s1.indexOf("\n");
                        if(i == 0)
                        {
                            s1 = s1.substring(1);
                            continue;
                        }
                        int j = i;
                        if(i < 0)
                            j = s1.length();
                        i = 0;
                        int k = i;
                        do
                        {
                            l = j;
                            if(i >= j)
                                break label2;
                            l = s1.codePointAt(i);
                            if(l == 13 || l == 10)
                                break;
                            if(l >= 128 && (65376 >= l || l >= 65440))
                                k += 2;
                            else
                                k++;
                            if(k > 36)
                                break;
                            i++;
                        } while(true);
                        l = i;
                    }
                    s = s1.substring(0, l);
                    automessageText_.add(s);
                    if(automessageText_.size() < 9)
                        break label0;
                }
                return;
            }
            s1 = s1.substring(l);
        } while(true);
    }

    public void AutoMessageChaned(AutoMessageInfo automessageinfo)
    {
        if(automessageinfo == null)
        {
            display_type_ = jp.pixela.common.AutoMessageInfo.DisplayType.ERASABLE_DISPLEY;
            automessageText_ = new ArrayList();
            invalidate();
            PxLog.d("AutoMessageView", "AutoMessageChaned: null");
            return;
        } else
        {
            display_type_ = automessageinfo.GetDisplayType();
            horizontal_position_ = automessageinfo.GetHorizontalPosition();
            vertical_position_ = automessageinfo.GetVerticalPosition();
            character_size_ = automessageinfo.GetCharacterSize();
            automessageinfo = automessageinfo.GetText();
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("AutoMessageChaned: display_type_ = ");
            stringbuilder.append(display_type_);
            PxLog.d("AutoMessageView", stringbuilder.toString());
            spliteAutoessage(automessageinfo);
            invalidate();
            return;
        }
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if(display_type_ == jp.pixela.common.AutoMessageInfo.DisplayType.ERASE_DIRECTION)
            return;
        if(automessageText_.size() == 0)
            return;
        int i = getWidth();
        int j = getHeight();
        if(i != 0 && j != 0)
        {
            if(character_size_ == jp.pixela.common.AutoMessageInfo.CharacterSize.SIZE2)
                j /= 20;
            else
                j /= 30;
            text_paint_.setTextSize(j);
            text_paint_.setColor(-1);
            Object obj = FontManager.getTypeface(context_);
            if(obj != null)
                text_paint_.setTypeface(((android.graphics.Typeface) (obj)));
            text_rect_.setEmpty();
            caleAutoMessageRect();
            obj = text_paint_.getFontMetrics();
            float f = ((android.graphics.Paint.FontMetrics) (obj)).descent;
            float f1 = ((android.graphics.Paint.FontMetrics) (obj)).ascent;
            float f2 = j / 2;
            background_rect_.left = (float)text_rect_.left - f2;
            background_rect_.right = (float)text_rect_.right + f2;
            background_rect_.top = (float)text_rect_.top - f2;
            background_rect_.bottom = (float)text_rect_.bottom + f2;
            background_paint_.setColor(0x80000000);
            canvas.drawRoundRect(background_rect_, 10F, 10F, background_paint_);
            for(int k = 0; k < automessageText_.size(); k++)
                canvas.drawText((String)automessageText_.get(k), text_rect_.left, ((float)text_rect_.top - ((android.graphics.Paint.FontMetrics) (obj)).ascent) + (float)k * (f - f1), text_paint_);

            return;
        } else
        {
            return;
        }
    }

    static final String TAG = "AutoMessageView";
    List automessageText_;
    Paint background_paint_;
    RectF background_rect_;
    jp.pixela.common.AutoMessageInfo.CharacterSize character_size_;
    Context context_;
    jp.pixela.common.AutoMessageInfo.DisplayType display_type_;
    jp.pixela.common.AutoMessageInfo.HorizontalPosition horizontal_position_;
    Paint text_paint_;
    Rect text_rect_;
    jp.pixela.common.AutoMessageInfo.VerticalPosition vertical_position_;
}
