package com.cmykui.framework.cmykui;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cmykui.framework.cmykui.component.ButtonComponent;
import com.cmykui.framework.cmykui.layout.TagLayout;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout L = findViewById(R.id.layout);
        LayoutInflater layoutInflater = getLayoutInflater();
        ButtonComponent C = new ButtonComponent(this);
        L.addView(C);
    }
}
