package com.cmykui.framework.cmykui.component;


import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
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
    public TextView ButtonTextError;
    public TextView ButtonTextSuccess;

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
        ButtonTextError = this.findViewById(R.id.Component_textError);
        ButtonTextSuccess = this.findViewById(R.id.Component_textSuccess);

        ButtonTextView.setVisibility(View.GONE);
        ButtonTextError.setVisibility(View.GONE);
        ButtonTextSuccess.setVisibility(View.GONE);

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
        ButtonTextView.setVisibility(View.VISIBLE);
        ButtonButton.setVisibility(View.GONE);
    }

    public void setActionSuccess(){
        ButtonTextSuccess.setVisibility(View.VISIBLE);
        ButtonTextView.setVisibility(View.GONE);
    }

    public void setActionError(){
        
        ButtonTextError.setVisibility(View.VISIBLE);
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
