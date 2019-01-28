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

/**
 * The type Fab component.
 */
public class FABComponent extends android.support.v7.widget.AppCompatImageView {

    /**
     * The Height.
     */
    int height;
    /**
     * The Width.
     */
    int width;
    /**
     * The Color pressed.
     */
    int color_pressed;

    /**
     * The Draw.
     */
    Drawable draw = ResourcesCompat.getDrawable(getResources(), R.drawable.fab_drawable, null);

    private boolean created;

    /**
     * Instantiates a new Fab component.
     *
     * @param context the context
     */
    public FABComponent(Context context) {
        this(context, null);
    }

    /**
     * Instantiates a new Fab component.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public FABComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * Instantiates a new Fab component.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public FABComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * Init.
     *
     * @param context      the context
     * @param attributeSet the attribute set
     */
    public void init(Context context, AttributeSet attributeSet) {

        TypedArray attrs = context.obtainStyledAttributes(attributeSet, R.styleable.FloatingActionButton, 0, 0);
        setColor(attrs.getColor(R.styleable.FloatingActionButton_color, ContextCompat.getColor(context, android.R.color.holo_red_dark)));
        color_pressed = attrs.getColor(R.styleable.FloatingActionButton_color_pressed, ContextCompat.getColor(context, android.R.color.holo_red_light));
        height = attrs.getInteger(R.styleable.FloatingActionButton_fab_height,100);
        width = attrs.getInteger(R.styleable.FloatingActionButton_fab_width,100);
        attrs.recycle();
    }

    /**
     * Update bg.
     */
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
        params.height = height;
        params.width = width;
        this.setLayoutParams(params);
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
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
