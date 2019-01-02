package com.cmykui.framework.cmykui.component;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cmykui.framework.cmykui.R;
import com.cmykui.framework.cmykui.base.ComponentInterface;

public class WaveLoadingComponent extends LinearLayout implements ComponentInterface {

    public WaveLoadingComponent(Context context) {
        super(context);init(context);
    }

    public WaveLoadingComponent(Context context, AttributeSet attrs) {
        super(context, attrs);init(context);
    }

    public WaveLoadingComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);init(context);
    }

    public WaveLoadingComponent(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);init(context);
    }

    public void init(Context context){

        inflate(getContext(), R.layout.wave_loading_component, this);
    onStart();
    }

    AnimatorSet set;
    ImageView imgView;



    public void onStart(){

        ImageView imgView=(ImageView)findViewById(R.id.imageview);
        set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(),R.animator.wave);
        set.setTarget(imgView);
        set.start();

    }
}
