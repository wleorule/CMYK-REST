package com.cmykui.framework.cmykui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import hr.foi.air.cmykui.component.FABComponent;
import hr.foi.air.cmykui.component.FABToolbar;

public class NikoPlayground extends AppCompatActivity implements View.OnClickListener{

    FABComponent Fab,secondButton,thirdButton;
    FABToolbar FabToolbar;
    RelativeLayout base;
    TextView textView;
    ImageView settings,call,confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niko_playground);

        FabToolbar = (FABToolbar) findViewById(R.id.toolbar);
        Fab = (FABComponent) findViewById(R.id.toolbar_button);
        secondButton = (FABComponent) findViewById(R.id.menu_button2);
        thirdButton = (FABComponent) findViewById(R.id.menu_button3);

        base = (RelativeLayout) findViewById(R.id.base_layout);
        textView = (TextView) findViewById(R.id.ispis);

        settings = FabToolbar.firstButton;
        call = FabToolbar.secondButton;
        confirm = FabToolbar.thirdButton;

        base.setOnClickListener(this);
        Fab.setOnClickListener(this);
        secondButton.setOnClickListener(this);
        thirdButton.setOnClickListener(this);
        FabToolbar.setOnClickListener(this);
        call.setOnClickListener(this);
        settings.setOnClickListener(this);
        confirm.setOnClickListener(this);

        FabToolbar.setFab(Fab);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            case R.id.toolbar_button:
                FabToolbar.expandFab();
                break;

            case R.id.base_layout:
                FabToolbar.contractFab();
                break;

            case R.id.menu_button2:
                textView.setText("Kliknut je drugi gumb!");
                break;

            case R.id.menu_button3:
                textView.setText("Kliknut je treci gumb!");
                break;

            case R.id.first_button:
                textView.setText("Kliknut je gumb za poziv!");
                break;

            case R.id.second_button:
                textView.setText("Kliknut je gumb za postavke!");
                break;

            case R.id.third_button:
                textView.setText("Kliknut je gumb za potvrdu!");
                break;


        }
    }
}