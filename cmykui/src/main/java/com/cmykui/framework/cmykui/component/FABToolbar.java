package com.cmykui.framework.cmykui.component;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.cmykui.framework.cmykui.R;

public class FABToolbar extends FrameLayout {

    private LinearLayout FabToolbar;
    private FABComponent Fab;

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
        FabToolbar.setVisibility(View.VISIBLE);
        Fab.setVisibility(View.INVISIBLE);
    }

    public void contractFab() {
        Fab.setVisibility(View.VISIBLE);
        FabToolbar.setVisibility(View.INVISIBLE);
    }
}
