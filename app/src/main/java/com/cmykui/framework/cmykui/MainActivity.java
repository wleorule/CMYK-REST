package com.cmykui.framework.cmykui;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import com.cmykui.framework.cmykui.base.OnClick;
import com.cmykui.framework.cmykui.component.ButtonComponent;
import com.cmykui.framework.cmykui.component.FloatLabelText;
import com.cmykui.framework.cmykui.layout.ImageLayoutItem;
import com.cmykui.framework.cmykui.layout.ImageLayoutItemMenuItem;
import com.cmykui.framework.cmykui.layout.InboxViewItem;


public class MainActivity extends AppCompatActivity {


    ImageLayoutItem JerkoPlaygroundMenuItem;
    ImageLayoutItemMenuItem JerkoPrebaci;
    ImageLayoutItem MatijaPlaygroundMenuItem;
    ImageLayoutItemMenuItem MatijaPrebaci;
    ImageLayoutItem NikoPlaygroundMenuItem;
    ImageLayoutItemMenuItem NikoPrebaci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JerkoPlaygroundMenuItem = findViewById(R.id.JerkoPlayground);
        JerkoPrebaci = findViewById(R.id.PrebaciJerko);
        MatijaPlaygroundMenuItem = findViewById(R.id.MatijaPlayground);
        MatijaPrebaci = findViewById(R.id.PrebaciMatija);
        NikoPlaygroundMenuItem = findViewById(R.id.NikoPlayground);
        NikoPrebaci = findViewById(R.id.PrebaciNiko);

        // JerkoPlaygroundMenuItem
        JerkoPlaygroundMenuItem.setTitle("Jerko playgorund");
        JerkoPrebaci.setOnClickListener(new OnClick<Void>() {
            @Override
            public Void onClick() throws Exception {
                Intent transfer = new Intent(MainActivity.this, JerkoPlayground.class);
                startActivity(transfer);

                return null;
            }
        });


        // MatijaPlaygroundMenuItem
        MatijaPlaygroundMenuItem.setTitle("Matija playgorund");
        MatijaPrebaci.setOnClickListener(new OnClick<Void>() {
            @Override
            public Void onClick() throws Exception {
                Intent transfer = new Intent(MainActivity.this, MatijaPlayground.class);
                startActivity(transfer);

                return null;
            }
        });


        // NikoPlaygroundMenuItem
        NikoPlaygroundMenuItem.setTitle("Niko playgorund");
        NikoPrebaci.setOnClickListener(new OnClick<Void>() {
            @Override
            public Void onClick() throws Exception {
                Intent transfer = new Intent(MainActivity.this, NikoPlayground.class);
                startActivity(transfer);

                return null;
            }
        });

    }


}
