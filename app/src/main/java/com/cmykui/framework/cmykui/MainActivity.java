package com.cmykui.framework.cmykui;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.cmykui.framework.cmykui.base.OnClick;
import com.cmykui.framework.cmykui.component.ButtonComponent;
import com.cmykui.framework.cmykui.component.FloatLabelText;
import com.cmykui.framework.cmykui.layout.InboxViewItem;


public class MainActivity extends AppCompatActivity {


    InboxViewItem JerkoPlaygroundMenuItem;
    InboxViewItem MatijaPlaygroundMenuItem;
    InboxViewItem NikoPlaygroundMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JerkoPlaygroundMenuItem = findViewById(R.id.JerkoPlayground);
        MatijaPlaygroundMenuItem = findViewById(R.id.MatijaPlayground);
        NikoPlaygroundMenuItem = findViewById(R.id.NikoPlayground);

        // JerkoPlaygroundMenuItem
        JerkoPlaygroundMenuItem.setTitle("Jerko playgorund");
        JerkoPlaygroundMenuItem.setOnClickListener(new OnClick<Void>() {
            @Override
            public Void onClick() throws Exception {
                Intent transfer = new Intent(MainActivity.this, JerkoPlayground.class);
                startActivity(transfer);

                return null;
            }
        });


        // MatijaPlaygroundMenuItem
        MatijaPlaygroundMenuItem.setTitle("Matija playgorund");
        MatijaPlaygroundMenuItem.setOnClickListener(new OnClick<Void>() {
            @Override
            public Void onClick() throws Exception {
                Intent transfer = new Intent(MainActivity.this, MatijaPlayground.class);
                startActivity(transfer);

                return null;
            }
        });


        // NikoPlaygroundMenuItem
        NikoPlaygroundMenuItem.setTitle("Matija playgorund");
        NikoPlaygroundMenuItem.setOnClickListener(new OnClick<Void>() {
            @Override
            public Void onClick() throws Exception {
                Intent transfer = new Intent(MainActivity.this, NikoPlayground.class);
                startActivity(transfer);

                return null;
            }
        });

    }


}
