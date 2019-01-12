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

public class FABComponent extends android.support.v7.widget.AppCompatImageView implements FABInterface {

    int ncolor;
    int color_pressed;

    Drawable draw = ResourcesCompat.getDrawable(getResources(), R.drawable.fab_drawable, null);

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
        setColor(attrs.getColor(R.styleable.FloatingActionButton_color, ContextCompat.getColor(context, android.R.color.holo_red_dark)));
        color_pressed = attrs.getColor(R.styleable.FloatingActionButton_color_pressed, ContextCompat.getColor(context, android.R.color.holo_red_light));
        attrs.recycle();
    }

    public void updateBG() {
        setBackground(draw);
    }

    private void build() {
        created = true;
        updateBG();
        setSize();
        }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);
        setSize();
    }

    private void setSize() {
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = 150;
        params.width = 150;
        this.setLayoutParams(params);
    }

    public void setColor(int color) {
        draw.setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!created) {
            build();
        }
    }

}
