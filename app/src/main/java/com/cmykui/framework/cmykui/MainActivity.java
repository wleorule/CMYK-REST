package com.cmykui.framework.cmykui;


import android.app.AlertDialog;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;


import com.cmykui.framework.cmykui.base.OnClick;
import com.cmykui.framework.cmykui.component.ButtonComponent;
import com.cmykui.framework.cmykui.layout.InboxViewItem;

import java.util.concurrent.Callable;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ConstraintLayout L = findViewById(R.id.layout);
        LayoutInflater layoutInflater = getLayoutInflater();
        ButtonComponent C = new ButtonComponent(this);



        //L.addView(C);


        //InboxLayout tagLayout = (InboxLayout) findViewById(R.id.inboxLayout);
      

        ConstraintLayout layout = findViewById(R.id.layout) ;

        InboxViewItem iv = new InboxViewItem(this);

        iv.itemTitle.setText("Leo Leo Leo");

        iv.setOnClickListener(new OnClick<Void>() {
            public Void onClick() {
                mojaMetoda(MainActivity.this);
                return null;
            }});

        layout.addView(iv);



/*
        View view1 = layoutInflater.inflate(R.layout.inbox_item, null, false );
        view1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        TextView tv = (TextView)view1.findViewById(R.id.InboxTitle);

        tv.setText("Test");
        tagLayout.addView(view1);*/

    }

    public Void mojaMetoda(Context ctx){
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ctx);
        dlgAlert.setMessage("JEBI SEEe!");
        dlgAlert.setTitle("Layout");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
        return null;
    }
}
