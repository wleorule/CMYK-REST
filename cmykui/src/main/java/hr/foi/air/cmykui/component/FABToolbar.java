package hr.foi.air.cmykui.component;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import hr.foi.air.cmykui.R;
import hr.foi.air.cmykui.component.FABComponent;

/**
 * The type Fab toolbar.
 */
public class FABToolbar extends FrameLayout {

    private LinearLayout FabToolbar;
    private FABComponent Fab;
    private boolean isExpanded;

    /**
     * The First button.
     */
    public ImageView firstButton, /**
     * The Second button.
     */
    secondButton, /**
     * The Third button.
     */
    thirdButton;
    /**
     * The First icon.
     */
    private Drawable firstIcon, /**
     * The Second icon.
     */
    secondIcon, /**
     * The Third icon.
     */
    thirdIcon;

    /**
     * Instantiates a new Fab toolbar.
     *
     * @param context the context
     */
    public FABToolbar(Context context) {
        super(context);
        init();
    }

    /**
     * Instantiates a new Fab toolbar.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public FABToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        loadAttributes(context, attrs);
    }

    /**
     * Instantiates a new Fab toolbar.
     *
     * @param context  the context
     * @param attrs    the attrs
     * @param defStyle the def style
     */
    public FABToolbar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        loadAttributes(context, attrs);
    }

    private void init() {
        inflate(getContext(), R.layout.fab_toolbar, this);
        FabToolbar = (LinearLayout) findViewById(R.id.container);

        firstButton = (ImageView) findViewById(R.id.first_button);
        secondButton = (ImageView) findViewById(R.id.second_button);
        thirdButton = (ImageView) findViewById(R.id.third_button);
    }

    private void loadAttributes(Context context, AttributeSet attributeSet) {
        TypedArray attrs = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.Toolbar, 0, 0);
        firstIcon = attrs.getDrawable(R.styleable.Toolbar_firstIcon);
        secondIcon = attrs.getDrawable(R.styleable.Toolbar_secondIcon);
        thirdIcon = attrs.getDrawable(R.styleable.Toolbar_thirdIcon);
        setColor(attrs.getColor(R.styleable.Toolbar_color_toolbar, ContextCompat.getColor(context, android.R.color.holo_red_dark)));

        firstButton.setImageDrawable(firstIcon);
        secondButton.setImageDrawable(secondIcon);
        thirdButton.setImageDrawable(thirdIcon);

        attrs.recycle();
    }


    /**
     * Sets fab.
     *
     * @param fabComponent the fab component
     */
    public void setFab(FABComponent fabComponent) {
        Fab = fabComponent;
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(int color) {
        FabToolbar.setBackgroundColor(color);
    }


    /**
     * Expand fab.
     */
    public void expandFab(int fromX, int toX, int fromY, int toY) {


        TranslateAnimation toolAnim = new TranslateAnimation(0,0,500,0);
        toolAnim.setDuration(400);
        FabToolbar.startAnimation(toolAnim);
        FabToolbar.setVisibility(View.VISIBLE);

        TranslateAnimation fabAnim = new TranslateAnimation(fromX,toX,fromY,toY);
        fabAnim.setDuration(400);
        Fab.startAnimation(fabAnim);
        Fab.setVisibility(View.INVISIBLE);

        isExpanded = true;

    }


    /**
     * Contract fab.
     */
    public void contractFab(int fromX, int toX, int fromY, int toY) {

        if (isExpanded) {

            Fab.setVisibility(View.VISIBLE);
            TranslateAnimation anim = new TranslateAnimation(fromX,toX,fromY,toY);
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
