package com.cmykui.framework.cmykui.component;


import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmykui.framework.cmykui.R;
import com.cmykui.framework.cmykui.base.ComponentInterface;
import com.cmykui.framework.cmykui.base.OnClick;

public class ButtonComponent extends RelativeLayout implements ComponentInterface {

    public boolean isButtonLoader = false;
   public Button ButtonButton;
   public TextView ButtonTextView;
    public Button ButtonError;
    public Button ButtonSuccess;
    Animation fadeout = new AlphaAnimation(1.0f, 0.0f);
    Animation fadein = new AlphaAnimation(0.0f, 1.0f);

    private OnClick<Void> metoda;
    private Boolean override = false;


    public ButtonComponent(Context context) {
        super(context);
        init(context);
    }

    public ButtonComponent(Context context, AttributeSet attrs) {
        super(context, attrs);init(context);
    }

    public ButtonComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);init(context);
    }

    public void init(Context context){

        inflate(getContext(), R.layout.button_component, this);
        ButtonButton = this.findViewById(R.id.Component_button);
        ButtonTextView = this.findViewById(R.id.Component_textView);
        ButtonError = this.findViewById(R.id.Component_buttonError);
        ButtonSuccess = this.findViewById(R.id.Component_buttonSuccess);


        ButtonButton.setAlpha(1.0f);
        ButtonTextView.setAlpha(0.0f);
        ButtonError.setAlpha(0.0f);
        ButtonSuccess.setAlpha(0.0f);

        fadeout.setDuration(500);
        fadein.setDuration(500);

        ButtonButton.setOnClickListener(lokalOnClick);

    }

    private OnClickListener lokalOnClick= new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(isButtonLoader){
                startLoading();
            }
            ButtonComponent.this.onClick(v.getContext());
        }
    };


    private void startLoading() {

        ButtonButton.startAnimation(fadeout);
        ButtonButton.postDelayed(new Runnable() {
            @Override
            public void run() {
                ButtonButton.setVisibility(View.GONE);
              ButtonError.setVisibility(View.GONE);
              ButtonSuccess.setVisibility(GONE);
                ButtonTextView.setAlpha(1.f);
                ButtonTextView.startAnimation(fadein);
                ButtonTextView.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        ButtonTextView.setVisibility(View.VISIBLE);
                    }
                }, 500);
            }
        }, 500);








    }

    public void setActionSuccess(){


        ButtonTextView.startAnimation(fadeout);
        ButtonTextView.postDelayed(new Runnable() {
            @Override
            public void run() {
                ButtonTextView.setVisibility(View.GONE);
                ButtonSuccess.setAlpha(1.0f);
                ButtonSuccess.startAnimation(fadein);
                ButtonSuccess.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ButtonSuccess.setVisibility(View.VISIBLE);
                    }
                }, 00);

            }
        }, 500);



    }

    public void setActionError(){
        
        ButtonError.setVisibility(View.VISIBLE);
        ButtonTextView.setVisibility(View.GONE);
    }
    public void onClick(Context context){

        if(override == true){
            try {
                metoda.onClick();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context);
            dlgAlert.setMessage("Uspjesno si stisnuo!");
            dlgAlert.setTitle("Layout");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }
    }

    public void setOnClickListener(OnClick<Void> m){
        override = true;
        metoda = m;
    }

}
