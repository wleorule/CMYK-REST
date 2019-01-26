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

/**
 * The type Button component.
 */
public class ButtonComponent extends RelativeLayout implements ComponentInterface {

    /**
     * The isButtonLoader attribute defines whether we want our button having loading behaviour or just be a simple button.
     */
    public boolean isButtonLoader = true;
    /**
     * The Button is the main and the first button in class. It has one onClick functionality.
     */
    public Button ButtonButton;
    /**
     * ButtonTextView is one of the options that the user can choose if he decides for static text that will replace ButtonLoader.
     */
    public TextView ButtonTextView;
    /**
     * ButtonLoader is animated component that represents loading or waiting.
     */
    public LoadingComponent ButtonLoader;
    /**
     * ButtonError is the second button in this class and represents a mistake after loading.
     */
    public Button ButtonError;
    /**
     * ButtonError is the second button in this class and represents a success after loading.
     */
    public Button ButtonSuccess;
    /**
     * TextLoading attribute is in charge of the selection between dynamic components loading or textual static.
     */
    public boolean TextLoading;
    /**
     * The Fadeout.
     */
    Animation fadeout = new AlphaAnimation(1.0f, 0.0f);
    /**
     * The Fadein.
     */
    Animation fadein = new AlphaAnimation(0.0f, 1.0f);

    private OnClick<Void> metoda;
    private Boolean override = false;


    /**
     * Instantiates a new Button component.
     *
     * @param context the context
     */
    public ButtonComponent(Context context) {
        super(context);
        init(context);
    }

    /**
     * Instantiates a new Button component.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public ButtonComponent(Context context, AttributeSet attrs) {
        super(context, attrs);init(context);
    }

    /**
     * Instantiates a new Button component.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public ButtonComponent(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);init(context);
    }

    /**
     * Method init initializes every initial value of attributes, and then calls the method inflate.
     *
     * @param context the context
     */
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


    /**
     * Method startLoading is in charge of animating every component in this class. Using attribute TextLoading user can select which components will be animated and shown.
     */
    public void startLoading() {
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

    /**
     * The method setActionSuccess animates ButtonTextView or ButtonLoader, depending on the selection. After animations it shows ButtonSuccess button.
     */
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

    /**
     * The method setActionError animates ButtonTextView or ButtonLoader, depending on the selection. After animations it shows ButtonError button.
     */
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


    /**
     *Sets the text of attribute ButtonButton.
     *
     * @param tekst the tekst
     */
//Postavljanje tekstova gumbova
    public void setButtonText(String tekst){

        ButtonButton.setText(tekst);
    }

    /**
     * Sets the text of attribute ButtonSuccess.
     *
     * @param tekst the tekst
     */
    public void setButtonSuccessText(String tekst){

        ButtonSuccess.setText(tekst);
    }

    /**
     * Sets the text of attribute ButtonError.
     *
     * @param tekst the tekst
     */
    public void setButtonErrorText(String tekst){

        ButtonError.setText(tekst);
    }

    /**
     *Allows the user to define their own onClick event.
     *
     * @param context the context
     */
    public void onClick(Context context){

        if(override == true){
            try {
                metoda.onClick();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     *
     * Defines the method that the user transfers as their own onClick event.
     *
     * @param m the m
     */
    public void setOnClickListener(OnClick<Void> m){

        override = true;
        metoda = m;
    }

}
