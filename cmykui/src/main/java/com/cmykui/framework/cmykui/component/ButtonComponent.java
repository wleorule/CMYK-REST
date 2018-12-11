package com.cmykui.framework.cmykui.component;


import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmykui.framework.cmykui.R;
import com.cmykui.framework.cmykui.base.ComponentInterface;

public class ButtonComponent extends RelativeLayout implements ComponentInterface {



    public ButtonComponent(Context context) {
        super(context);
        init(context);
    }

    public ButtonComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ButtonComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(Context context){

        inflate(getContext(), R.layout.button_component, this);
        Button componentButton=findViewById(R.id.Component_button);
        TextView probniTextView=findViewById(R.id.Component_textView);

        componentButton= new Button(context);
        probniTextView = new TextView(context);

        probniTextView.setVisibility(View.INVISIBLE);
        componentButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.INVISIBLE);
                TextView probniTextView=findViewById(R.id.Component_textView);
                probniTextView.setVisibility(View.VISIBLE);

            }
        });
    }


}
