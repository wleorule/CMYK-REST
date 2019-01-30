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
 * The type FABComponent.
 */
public class FABComponent extends android.support.v7.widget.AppCompatImageView {

    /**
     * The Height of the FABComponent.
     */
    private int height;
    /**
     * The Width of the FABComponent.
     */
    private int width;

    /**
     * The Draw which represents the shape of the FABComponent.
     */
    private Drawable draw = ResourcesCompat.getDrawable(getResources(), R.drawable.fab_drawable, null);

    /**
     * The created variable is used to check whether the FABComponent has been created or not.
     */
    private boolean created;

    /**
     * The scaler serves to scale up or down the height of the component depending on the screen size
     */
    private float scalerH;

    /**
     * The scaler serves to scale up or down the width of the component depending on the screen size
     */
    private float scalerW;

    /**
     * Instantiates a new FABComponent.
     *
     * @param context the context
     */
    public FABComponent(Context context) {
        this(context, null);
    }

    /**
     * Instantiates a new FABComponent.
     *
     * @param context the context
     * @param attrs   the attribute set
     */
    public FABComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * Instantiates a new FABComponent.
     *
     * @param context      the context
     * @param attrs        the attribute set
     * @param defStyleAttr the def style attr
     */
    public FABComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    /**
     * Init initializes the different given values to the attributes, such as the color, height and width.
     * If no values are given, default values are used.
     *
     * @param context      the context
     * @param attributeSet the attribute set
     */
    private void init(Context context, AttributeSet attributeSet) {

        TypedArray attrs = context.obtainStyledAttributes(attributeSet, R.styleable.FloatingActionButton, 0, 0);
        setColor(attrs.getColor(R.styleable.FloatingActionButton_color, ContextCompat.getColor(context, android.R.color.holo_red_dark)));
        height = attrs.getInteger(R.styleable.FloatingActionButton_fab_height,100);
        scalerH = height * getResources().getDisplayMetrics().density;
        width = attrs.getInteger(R.styleable.FloatingActionButton_fab_width,100);
        scalerW = width * getResources().getDisplayMetrics().density;
        attrs.recycle();
    }


    /**
     * UpdateBG sets the background drawable for the component.
     */
    private void updateBG() {
        setBackground(draw);
    }

    /**
     * The build method calls the methods for creating a background drawable and sets that the component is created.
     */
    private void build() {
        created = true;
        updateBG();
        }

    /**
     * The onMeasure method sets the size of the component in regards to the constraints of its parent.
     * @param width the width of the component
     * @param height the height of the component
     */
    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);
        setSize();
    }

    /**
     * Sets the size of the FABComponent
     */
    private void setSize() {
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = Math.round(scalerH);
        params.width = Math.round(scalerW);
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

    /**
     * Function that is called when the view is attached to the window.
     * Its used to start building the component.
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!created) {
            build();
        }
    }

}
