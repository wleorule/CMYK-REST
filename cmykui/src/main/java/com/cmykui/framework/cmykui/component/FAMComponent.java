package com.cmykui.framework.cmykui.component;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.cmykui.framework.cmykui.R;
import com.cmykui.framework.cmykui.base.FAMInterface;

import java.util.ArrayList;


public class FAMComponent extends FrameLayout implements FAMInterface {

    public int color;
    private OnMenuExpandedListener onMenuExpandedListener;
    private boolean created;
    private boolean expanded;
    private boolean animating = false;
    private ArrayList<View> views = new ArrayList<>();


    public FAMComponent(Context context) {
        this(context, null);
    }

    public FAMComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FAMComponent(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {

        TypedArray attr = context.obtainStyledAttributes(attributeSet, R.styleable.FloatingActionsMenu, 0, 0);
        color = attr.getColor(R.styleable.FloatingActionsMenu_color_menu, ContextCompat.getColor(context, android.R.color.holo_red_dark));
        attr.recycle();
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
            child.setLayoutParams(generateDefaultLayoutParams());
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

                if (expanded) {
                    collapse();
                } else {
                    expand();
                }
            }
        });
    }

    private void expand() {
        ArrayList<Animator> animators = new ArrayList<>();
        animating = true;
        for (int i = 1; i < views.size(); i++) {
            final View view = views.get(i);
            float animationSize = 200f;

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
                if (onMenuExpandedListener != null) {
                    onMenuExpandedListener.onMenuExpanded();
                }
            }
        });

        set.start();
    }

    private void collapse() {
        ArrayList<Animator> animators = new ArrayList<>();
        animating = true;
        for (int i = views.size() - 1; i > 0; i--) {
            final View view = views.get(i);
            float animationSize = 200f;

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
                if (onMenuExpandedListener != null) {
                    onMenuExpandedListener.onMenuCollapsed();
                }
            }
        });

        set.start();
    }


    public interface OnMenuExpandedListener {
        void onMenuExpanded();

        void onMenuCollapsed();
    }

}
