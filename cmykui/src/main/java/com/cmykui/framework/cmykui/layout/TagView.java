package com.cmykui.framework.cmykui.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.cmykui.framework.cmykui.R;

public class TagView extends View {
    public TagView(Context context) {
        super(context);
        init();
    }

    public TagView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TagView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TagView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init(){
        inflate(getContext(), R.layout.tagview, null);
    }
}
