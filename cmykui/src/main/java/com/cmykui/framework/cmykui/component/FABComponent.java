package com.cmykui.framework.cmykui.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.cmykui.framework.cmykui.R;
import com.cmykui.framework.cmykui.base.FABInterface;

public class FABComponent extends android.support.v7.widget.AppCompatTextView implements FABInterface {

    int ncolor;
    int color_pressed;

    private boolean created;

    public FABComponent(Context context) {
        this(context, null);
    }

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
        attrs.recycle();
    }

    public void updateBG(int color) {
        Drawable draw = ResourcesCompat.getDrawable(getResources(), R.drawable.fab_drawable, null);
        draw.setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
        setBackground(draw);
    }

    private void build() {
        int color = ContextCompat.getColor(getContext(), android.R.color.holo_red_light);
        created = true;
        updateBG(color);
        setSize();
        }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);
        setSize();
    }

    private void setSize() {
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = 50;
        params.width = 50;
        this.setLayoutParams(params);
    }

    public void setColor(int color) {
        if (ncolor != color) {
            updateBG(color);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!created) {
            build();
        }
    }

}
