package com.cmykui.framework.cmykui.component;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.animation.OvershootInterpolator;

import com.cmykui.framework.cmykui.R;
import com.cmykui.framework.cmykui.base.ComponentInterface;

/**
 * The type Loading component.
 */
public class LoadingComponent extends LinearLayout implements ComponentInterface {
    /**
     * AnimatorSet.
     */
    AnimatorSet set;

    /**
     * Instantiates a new Loading component.
     *
     * @param context the context
     */
    public LoadingComponent(Context context) {
        super(context);init(context);
    }

    /**
     * Instantiates a new Loading component.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public LoadingComponent(Context context, AttributeSet attrs) {
        super(context, attrs);init(context);
    }

    /**
     * Instantiates a new Loading component.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public LoadingComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);init(context);
    }

    /**
     * Instantiates a new Loading component.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     * @param defStyleRes  the def style res
     */
    public LoadingComponent(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);init(context);
    }

    /**
     * Method init() calls method inflate() and onStart().
     *
     * @param context the context
     */
    public void init(Context context){

        inflate(getContext(), R.layout.loading_component, this);
    onStart();
    }


    /**
     * Method onStart() defines and image which will be displayed in loading animation and then plays the animation.
     */
    public void onStart(){

        ImageView imgView=findViewById(R.id.imageview);

        set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(),R.animator.load_animation);
        set.setTarget(imgView);
        set.setInterpolator(new OvershootInterpolator(5));
        set.start();

    }
}
