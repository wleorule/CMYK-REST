package com.cmykui.framework.cmykui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hr.foi.air.cmykui.base.OnClick;
import hr.foi.air.cmykui.component.ButtonComponent;
import hr.foi.air.cmykui.component.FloatLabelText;

public class JerkoPlayground extends AppCompatActivity {

    ButtonComponent button;
    FloatLabelText floatLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jerko_playground);

        button = findViewById(R.id.button);
       floatLabel=findViewById(R.id.floatLabel);


        button.setButtonText("My button");
        button.setButtonSuccessText("Done!");
        button.setButtonErrorText("Error!");
        floatLabel.setHintAndLabel("Placeholder");

        button.TextLoading =false;
        button.isButtonLoader = true;
        button.setOnClickListener(new OnClick<Void>() {
            @Override
            public Void onClick() throws Exception {
                mojaMetoda(JerkoPlayground.this);
                return null;
            }
        });


    }

    public Void mojaMetoda(Context ctx){

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                button.setActionSuccess();

            }
        }, 5000);
        return null;
    }
}
