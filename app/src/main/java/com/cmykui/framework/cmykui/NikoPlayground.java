package com.cmykui.framework.cmykui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.cmykui.framework.cmykui.component.FABComponent;
import com.cmykui.framework.cmykui.component.FABToolbar;

public class NikoPlayground extends AppCompatActivity implements View.OnClickListener{
    private FABComponent Fab;
    private FABToolbar FabToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niko_playground);
        FabToolbar = (FABToolbar) findViewById(R.id.toolbar);
        Fab = (FABComponent) findViewById(R.id.toolbar_button);
        Fab.setOnClickListener(this);
        FabToolbar.setOnClickListener(this);
        FabToolbar.setFab(Fab);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.toolbar_button:
                FabToolbar.expandFab();
                break;
            case R.id.toolbar:
                FabToolbar.contractFab();
        }
    }
}