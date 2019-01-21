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

    public boolean isButtonLoader = true;
   public Button ButtonButton;
   public TextView ButtonTextView;
   public LoadingComponent ButtonLoader;
    public Button ButtonError;
    public Button ButtonSuccess;
    public boolean TextLoading;
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
        ButtonLoader = this.findViewById(R.id.Component_loader);

        TextLoading =false;

        ButtonButton.setAlpha(1.0f);
        ButtonTextView.setAlpha(0.0f);
        ButtonError.setAlpha(0.0f);
        ButtonSuccess.setAlpha(0.0f);
        ButtonLoader.setAlpha(0.0f);

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
if (TextLoading) {
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
}else {
    ButtonButton.startAnimation(fadeout);
    ButtonButton.postDelayed(new Runnable() {
        @Override
        public void run() {
            ButtonButton.setVisibility(View.GONE);
            ButtonError.setVisibility(View.GONE);
            ButtonSuccess.setVisibility(GONE);
            ButtonTextView.setVisibility(GONE);
            ButtonLoader.setAlpha(1.f);
            ButtonLoader.startAnimation(fadein);
            ButtonLoader.postDelayed(new Runnable() {
                @Override
                public void run() {

                    ButtonLoader.setVisibility(View.VISIBLE);
                }
            }, 500);
        }
    }, 500);
}
    }

    public void setActionSuccess(){

        if (TextLoading) {
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
        }else {
            ButtonLoader.startAnimation(fadeout);
            ButtonLoader.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ButtonLoader.setVisibility(View.GONE);
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
    }

    public void setActionError(){

        if (TextLoading) {
            ButtonTextView.startAnimation(fadeout);
            ButtonTextView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ButtonTextView.setVisibility(View.GONE);
                    ButtonError.setAlpha(1.0f);
                    ButtonError.startAnimation(fadein);
                    ButtonError.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ButtonError.setVisibility(View.VISIBLE);
                        }
                    }, 00);

                }
            }, 500);
        }else{
            ButtonLoader.startAnimation(fadeout);
            ButtonLoader.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ButtonLoader.setVisibility(View.GONE);
                    ButtonError.setAlpha(1.0f);
                    ButtonError.startAnimation(fadein);
                    ButtonError.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ButtonError.setVisibility(View.VISIBLE);
                        }
                    }, 00);

                }
            }, 500);

        }

    }


//Postavljanje tekstova gumbova
    public void setButtonText(String tekst){

        ButtonButton.setText(tekst);
    }

    public void setButtonSuccessText(String tekst){

        ButtonSuccess.setText(tekst);
    }
    public void setButtonErrorText(String tekst){

        ButtonError.setText(tekst);
    }

    public void onClick(Context context){

        if(override == true){
            try {
                metoda.onClick();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void setOnClickListener(OnClick<Void> m){
        override = true;
        metoda = m;
    }

}
