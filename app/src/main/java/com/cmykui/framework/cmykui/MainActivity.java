package com.cmykui.framework.cmykui;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.cmykui.framework.cmykui.base.OnClick;
import com.cmykui.framework.cmykui.component.ButtonComponent;
import com.cmykui.framework.cmykui.component.FloatLabelText;


public class MainActivity extends AppCompatActivity {

    ButtonComponent button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = findViewById(R.id.button);

        FloatLabelText text = new FloatLabelText(this);
        String pomocni="string";




        button.isButtonLoader = true;
        button.setOnClickListener(new OnClick<Void>() {
            @Override
            public Void onClick() throws Exception {
                mojaMetoda(MainActivity.this);
                return null;
            }
        });

    }

    public Void mojaMetoda(Context ctx){
       //

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               button.setActionSuccess();
            }
        }, 2000);
        return null;
    }
}
