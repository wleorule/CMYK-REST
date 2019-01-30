package hr.foi.air.cmykui.component;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import hr.foi.air.cmykui.R;

import java.util.ArrayList;


/**
 * The type FAMComponent.
 */
public class FAMComponent extends FrameLayout {

    /**
     * The distance which the buttons (FABComponents) travel when expanding and collapsing.
     */
    private float animationDistance;

    /**
     * The constant menu_bottom which indicates the position of the starting button (FABComponent).
     */
    private static final int MENU_BOTTOM = 0;

    /**
     * The constant menu_top which indicates the position of the starting button (FABComponent)..
     */
    private static final int MENU_TOP = 1;

    /**
     * The Child position which stores the value dictating where the starting button (FABComponent) is.
     */
    private int childPosition;

    /**
     * Variable created is used to check whether the FAMComponent is created or not.
     */
    private boolean created;

    /**
     * Variable expanded is used to check whether the children (buttons) in the FAMComponent are expanded or collapsed.
     */
    private boolean expanded;

    /**
     * Variable animating is used to check whether the buttons are currently in animation or not.
     */
    private boolean animating = false;

    /**
     * The array views stores the children of the FAMComponent
     */
    private ArrayList<View> views = new ArrayList<>();


    /**
     * Instantiates a new FAMComponent.
     *
     * @param context the context
     */
    public FAMComponent(Context context) {
        this(context, null);
    }

    /**
     * Instantiates a new FAMComponent.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public FAMComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * Instantiates a new FAMComponent.
     *
     * @param context  the context
     * @param attrs    the attrs
     * @param defStyle the def style
     */
    public FAMComponent(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    /**
     * Init initializes the values given to the attributes, such as the position of the starting button (which in turn decides the direction of the animation)
     * and the distance traveled during the animation. If no values are given, default values are selected.
     * @param context the context
     * @param attributeSet the attribute set
     */
    private void init(Context context, AttributeSet attributeSet) {

        TypedArray attrs = context.obtainStyledAttributes(attributeSet, R.styleable.FloatingActionsMenu, 0, 0);
        childPosition = attrs.getInteger(R.styleable.FloatingActionsMenu_fab_position, MENU_BOTTOM);
        animationDistance = attrs.getFloat(R.styleable.FloatingActionsMenu_animation_distance, 75);
        attrs.recycle();
    }

    /**
     * The build method sets that the FAMComponent is created, checks if it has any children and if the number is greater than zero, calls the methods
     * for to account for the children and their animations.
     */
    private void build() {
        created = true;
        if (getChildCount() == 0) {
            return;
        }

        numChildren();
        animation();
    }

    /**
     * The numChildren method accounts for all of the children, sets the first ones elevation above others, makes the others invisible,
     * sets their parameters as well as the starting position of the first child/button.
     * In the end it adds all of the children to the array of views.
     */
    private void numChildren(){
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if(i==0){
                child.setElevation(10);
            }
            if(i>0){
                child.setVisibility(GONE);
            }
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            if(childPosition == MENU_BOTTOM){
                params.gravity = Gravity.BOTTOM;
            }
            else if (childPosition == MENU_TOP){
                params.gravity = Gravity.TOP;
            }
            child.setLayoutParams(params);
            views.add(child);
        }
    }

    /**
     * Method onAttachedToWindow builds the component when the view is attached to the window.
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!created) {
            build();
        }
    }

    /**
     * The animation method sets the expansion and/or collapse to begin on click.
     * It checks if the children are extended. If they are, it collapses them, if not it expands.
     */
    private void animation() {
        View numview = views.get(0);
        numview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (animating) {
                    return;
                }
                    if (expanded) {
                        collapse();
                    } else {
                        expand();
                    }

            }
        });
    }

    /**
     * The expahd method creates the animation for expansion - the directions, starting point and ending point for each of the children/buttons.
     */
    private void expand() {
        ArrayList<Animator> animators = new ArrayList<>();
        animating = true;
        for (int i = 1; i < views.size(); i++) {
            final View view = views.get(i);
            float scaler = animationDistance * getResources().getDisplayMetrics().density;
            ObjectAnimator viewAnimator;
            if(childPosition == MENU_TOP) {
                viewAnimator = ObjectAnimator.ofFloat(view, "translationY", 0f, i * scaler);
            }
            else{
                viewAnimator = ObjectAnimator.ofFloat(view, "translationY", 0f, -i * scaler);
            }
            viewAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    view.setVisibility(VISIBLE);
                }
            });
            animators.add(viewAnimator);
        }

        AnimatorSet set = new AnimatorSet();
        set.playSequentially(animators);

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                animating = false;
                expanded = !expanded;
            }
        });

        set.start();
    }

    /**
     * The collapse method creates the animation for the collapse - the directions, starting point and ending point for each of the children/buttons.
     */
    private void collapse() {
        ArrayList<Animator> animators = new ArrayList<>();
        animating = true;
        for (int i = views.size() - 1; i > 0; i--) {
            final View view = views.get(i);
            float scaler = animationDistance * getResources().getDisplayMetrics().density;
            ObjectAnimator animator;
            if(childPosition == MENU_TOP) {
                animator = ObjectAnimator.ofFloat(view, "translationY", i * scaler, 0f);
            }
            else{
                animator = ObjectAnimator.ofFloat(view, "translationY", -i * scaler, 0f);
            }
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    view.setVisibility(GONE);
                }
            });
            animators.add(animator);
        }

        AnimatorSet set = new AnimatorSet();
        set.playSequentially(animators);

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                animating = false;
                expanded = !expanded;
            }
        });

        set.start();
    }
}
