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

/**
 * Type of the FABToolbar.
 */
public class FABToolbar extends FrameLayout {

    private LinearLayout FabToolbar;
    private FABComponent Fab;
    private boolean isExpanded;

    /**
     * The First button of the toolbar.
     */
    public ImageView firstButton,

    /**
     * The Second button of the toolbar.
     */
    secondButton,

    /**
     * The Third button of the toolbar.
     */
    thirdButton;

    /**
     * The First icon of the toolbar.
     */
    private Drawable firstIcon,

    /**
     * The Second icon of the toolbar.
     */
    secondIcon,

    /**
     * The Third icon of the toolbar.
     */
    thirdIcon;

    /**
     * Instantiates a new FABToolbar.
     *
     * @param context the context
     */
    public FABToolbar(Context context) {
        super(context);
        init();
    }

    /**
     * Instantiates a new FABToolbar.
     *
     * @param context the context
     * @param attrs   the attribute set
     */
    public FABToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        loadAttributes(context, attrs);
    }

    /**
     * Instantiates a new FABToolbar.
     *
     * @param context  the context
     * @param attrs    the attribute set
     * @param defStyle the def style
     */
    public FABToolbar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        loadAttributes(context, attrs);
    }

    /**
     * Init inflates the toolbar and connects the variables and their styleable counterparts.
     */
    private void init() {
        inflate(getContext(), R.layout.fab_toolbar, this);
        FabToolbar = findViewById(R.id.container);

        firstButton = findViewById(R.id.first_button);
        secondButton =  findViewById(R.id.second_button);
        thirdButton =  findViewById(R.id.third_button);
    }

    /**
     * The loadAttributes method initializes the different values that are given, such as the icons of the buttons and color of the toolbar.
     * If no values are given, default values are used.
     * @param context the context
     * @param attributeSet the set of attributes
     */
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
     * Sets the FAB which controls the expanding and collapsing of the toolbar.
     *
     * @param fabComponent the FABComponent
     */
    public void setFab(FABComponent fabComponent) {
        Fab = fabComponent;
    }

    /**
     * Sets color of the toolbar.
     *
     * @param color the color
     */
    public void setColor(int color) {
        FabToolbar.setBackgroundColor(color);
    }

    /**
     * Expands the toolbar and conceals the FABComponent tied to it by animating its movement in a given direction.
     * Meant to be used in a way that the button leaves the screen.
     * @param fromX animate from some position to the original position using the x axis.
     * @param toX animate to some position from the originalk position using the x axis.
     * @param fromY animate from some position to the original position using the y axis.
     * @param toY animate to some position from the originalk position using the y axis.
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
     * Conceals the toolbar and shows the FABComponent by animating its movement.
     * Meant to be used in a way that the FABComponent reappears by entering the screen from outside.
     * @param fromX animate from some position to the original position using the x axis.
     * @param toX animate to some position from the originalk position using the x axis.
     * @param fromY animate from some position to the original position using the y axis.
     * @param toY animate to some position from the originalk position using the y axis.
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
