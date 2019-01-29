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
 * The type Fam component.
 */
public class FAMComponent extends FrameLayout {


    private float animationDistance;


    /**
     * The constant menu_bottom.
     */
    public static final int MENU_BOTTOM = 0;
    /**
     * The constant menu_top.
     */
    public static final int MENU_TOP = 1;

    /**
     * The Child position.
     */
    public int childPosition;
    private OnMenuExpandedListener onMenuExpandedListener;

    private boolean created;
    private boolean expanded;
    private boolean animating = false;
    private ArrayList<View> views = new ArrayList<>();


    /**
     * Instantiates a new Fam component.
     *
     * @param context the context
     */
    public FAMComponent(Context context) {
        this(context, null);
    }

    /**
     * Instantiates a new Fam component.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public FAMComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * Instantiates a new Fam component.
     *
     * @param context  the context
     * @param attrs    the attrs
     * @param defStyle the def style
     */
    public FAMComponent(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {

        TypedArray attrs = context.obtainStyledAttributes(attributeSet, R.styleable.FloatingActionsMenu, 0, 0);
        childPosition = attrs.getInteger(R.styleable.FloatingActionsMenu_fab_position, MENU_BOTTOM);
        animationDistance = attrs.getFloat(R.styleable.FloatingActionsMenu_animation_distance, 200);
        attrs.recycle();
    }

    private void build() {
        created = true;
        if (getChildCount() == 0) {
            return;
        }

        numChildren();
        animation();
    }

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

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!created) {
            build();
        }
    }

    private void animation() {
        View numview = views.get(0);
        numview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (animating) {
                    return;
                }
                else if(childPosition == MENU_BOTTOM){
                    if (expanded) {
                        collapseUp();
                    } else {
                        expandUp();
                    }
                }
                if(childPosition == MENU_TOP) {
                    if (expanded) {
                        collapseDown();
                    } else {
                        expandDown();
                    }
                }
            }
        });
    }

    private void expandDown() {
        ArrayList<Animator> animators = new ArrayList<>();
        animating = true;
        for (int i = 1; i < views.size(); i++) {
            final View view = views.get(i);
            float animationSize = animationDistance;

            ObjectAnimator viewAnimator = ObjectAnimator.ofFloat(view, "translationY", 0f, i * animationSize);
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

    private void collapseDown() {
        ArrayList<Animator> animators = new ArrayList<>();
        animating = true;
        for (int i = views.size() - 1; i > 0; i--) {
            final View view = views.get(i);
            float animationSize = animationDistance;

            ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", i * animationSize, 0f);
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

    private void expandUp() {
        ArrayList<Animator> animators = new ArrayList<>();
        animating = true;
        for (int i = 1; i < views.size(); i++) {
            final View view = views.get(i);
            float animationSize = animationDistance;

            ObjectAnimator viewAnimator = ObjectAnimator.ofFloat(view, "translationY", 0f, -i * animationSize);
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

    private void collapseUp() {
        ArrayList<Animator> animators = new ArrayList<>();
        animating = true;
        for (int i = views.size() - 1; i > 0; i--) {
            final View view = views.get(i);
            float animationSize = animationDistance;

            ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", -i * animationSize, 0f);
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
