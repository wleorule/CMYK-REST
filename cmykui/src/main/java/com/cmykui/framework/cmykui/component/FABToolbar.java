package com.cmykui.framework.cmykui.component;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.cmykui.framework.cmykui.R;

public class FABToolbar extends FrameLayout {

    private LinearLayout FabToolbar;
    private FABComponent Fab;
    private boolean isExpanded;

    public FABToolbar(Context context) {
        super(context);
        init();
    }

    public FABToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        loadAttributes(context, attrs);
    }

    public FABToolbar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        loadAttributes(context, attrs);
    }

    private void init() {
        inflate(getContext(), R.layout.fab_toolbar, this);
        FabToolbar = (LinearLayout) findViewById(R.id.container);
    }

    private void loadAttributes(Context context, AttributeSet attributeSet) {
        TypedArray attrs = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.Toolbar, 0, 0);
        setColor(attrs.getColor(R.styleable.Toolbar_color_toolbar, ContextCompat.getColor(context, android.R.color.holo_red_dark)));
        attrs.recycle();
    }

    @Override public void addView(View child, ViewGroup.LayoutParams params) {
        if (viewToToolbar()) {
            FabToolbar.addView(child, params);
        } else {
            super.addView(child, params);
        }
    }
    private boolean viewToToolbar() {
        return FabToolbar != null;
    }


    public void setFab(FABComponent fabComponent) {
        Fab = fabComponent;
    }

    public void setColor(int color) {
        FabToolbar.setBackgroundColor(color);
    }

    public void expandFab() {

        TranslateAnimation toolAnim = new TranslateAnimation(0,0,500,0);
        toolAnim.setDuration(400);
        FabToolbar.startAnimation(toolAnim);
        FabToolbar.setVisibility(View.VISIBLE);

        TranslateAnimation fabAnim = new TranslateAnimation(0,500,0,0);
        fabAnim.setDuration(400);
        Fab.startAnimation(fabAnim);
        Fab.setVisibility(View.INVISIBLE);

        isExpanded = true;

    }

    public void contractFab() {
        if (isExpanded) {

            Fab.setVisibility(View.VISIBLE);
            TranslateAnimation anim = new TranslateAnimation(500, 0, 0, 0);
            anim.setDuration(400);
            Fab.startAnimation(anim);

            TranslateAnimation toolAnim = new TranslateAnimation(0,0,0,500);
            toolAnim.setDuration(400);
            FabToolbar.startAnimation(toolAnim);
            FabToolbar.setVisibility(View.INVISIBLE);

            isExpanded = false;
        }
    }
}
