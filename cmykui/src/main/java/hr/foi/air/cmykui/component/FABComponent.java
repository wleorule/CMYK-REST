package hr.foi.air.cmykui.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.ViewGroup;

import hr.foi.air.cmykui.R;

public class FABComponent extends android.support.v7.widget.AppCompatImageView {

    private int height;
    private int width;

    private Drawable draw = ResourcesCompat.getDrawable(getResources(), R.drawable.fab_drawable, null);

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

    private void init(Context context, AttributeSet attributeSet) {

        TypedArray attrs = context.obtainStyledAttributes(attributeSet, R.styleable.FloatingActionButton, 0, 0);
        setColor(attrs.getColor(R.styleable.FloatingActionButton_color, ContextCompat.getColor(context, android.R.color.holo_red_dark)));
        height = attrs.getInteger(R.styleable.FloatingActionButton_fab_height,100);
        width = attrs.getInteger(R.styleable.FloatingActionButton_fab_width,100);
        attrs.recycle();
    }

    private void updateBG() {
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
        params.height = height;
        params.width = width;
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
