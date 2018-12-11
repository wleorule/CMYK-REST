package com.cmykui.framework.cmykui;


import android.app.ActionBar;
import android.app.AlertDialog;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.cmykui.framework.cmykui.component.ButtonComponent;

import com.cmykui.framework.cmykui.layout.InboxLayout;
import com.cmykui.framework.cmykui.layout.InboxView;

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


        //InboxLayout tagLayout = (InboxLayout) findViewById(R.id.inboxLayout);
      

        ConstraintLayout layout = findViewById(R.id.layout) ;

        InboxView iv = new InboxView(this);
        layout.addView(iv);

/*
        View view1 = layoutInflater.inflate(R.layout.inbox_item, null, false );
        view1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        TextView tv = (TextView)view1.findViewById(R.id.InboxTitle);

        tv.setText("Test");
        tagLayout.addView(view1);*/

    }
}
