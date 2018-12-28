package com.cmykui.framework.cmykui.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;

import com.cmykui.framework.cmykui.R;
import com.cmykui.framework.cmykui.base.FABInterface;

public class FABComponent extends android.support.v7.widget.AppCompatImageView implements FABInterface {

    int ncolor;
    int color_pressed;
    String title;
    int icon;
    int height;
    int width;


    public FABComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FABComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attributeSet) {
        TypedArray attrs = context.obtainStyledAttributes(attributeSet, R.styleable.FloatingActionButton, 0, 0);
        ncolor = attrs.getColor(R.styleable.FloatingActionButton_color, ContextCompat.getColor(context, android.R.color.holo_red_dark));
        color_pressed = attrs.getColor(R.styleable.FloatingActionButton_color, ContextCompat.getColor(context, android.R.color.holo_red_light));
        icon = attrs.getResourceId(R.styleable.FloatingActionButton_icon, 0);
        height = attrs.getInt(R.styleable.FloatingActionButton_h, 100);
        width = attrs.getInt(R.styleable.FloatingActionButton_w, 100);
        attrs.recycle();
        updateBG(ncolor);

    }

    public void updateBG(int color) {
        Drawable draw = ResourcesCompat.getDrawable(getResources(), R.drawable.fab_drawable, null);
        draw.setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));;
        setBackground(draw);
    }

    public void setColor(int color) {
        if (ncolor != color) {
            updateBG(color);
        }
    }
}
