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

public class LoadingComponent extends LinearLayout implements ComponentInterface {

    public LoadingComponent(Context context) {
        super(context);init(context);
    }

    public LoadingComponent(Context context, AttributeSet attrs) {
        super(context, attrs);init(context);
    }

    public LoadingComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);init(context);
    }

    public LoadingComponent(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);init(context);
    }

    public void init(Context context){

        inflate(getContext(), R.layout.loading_component, this);
    onStart();
    }

    AnimatorSet set;


    public void onStart(){

        ImageView imgView=findViewById(R.id.imageview);

        set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(),R.animator.load_animation);
        set.setTarget(imgView);
        set.setInterpolator(new OvershootInterpolator(5));
        set.start();

    }
}
