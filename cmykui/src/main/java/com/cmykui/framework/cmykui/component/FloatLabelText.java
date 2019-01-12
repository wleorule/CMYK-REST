package com.cmykui.framework.cmykui.component;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import android.widget.TextView;

import com.cmykui.framework.cmykui.R;
import com.cmykui.framework.cmykui.base.ComponentInterface;
import com.cmykui.framework.cmykui.base.OnClick;

import static android.provider.Settings.System.getString;

public class FloatLabelText extends LinearLayout implements ComponentInterface {




    public TextView placeholder;

    public TextView LabelLabel;

    public FloatLabelText(Context context) {
        super(context);
        init(context);
    }

    public FloatLabelText(Context context, AttributeSet attrs) {
        super(context, attrs);init(context);
    }

    public FloatLabelText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);init(context);
    }

    public FloatLabelText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);init(context);
    }


    public void init(Context context){

        inflate(getContext(), R.layout.floatlabel_component, this);
        LabelLabel=this.findViewById(R.id.textView);
        placeholder=this.findViewById(R.id.editText);
        LabelLabel.animate().alpha(0.0f);




        EditText searchTo = (EditText)findViewById(R.id.editText);
        searchTo.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LabelLabel.animate().alpha(1.0f);

               if (placeholder.getText().toString().matches("")){
                   LabelLabel.animate().alpha(0.0f);
               }
            }
        });

    }

    public void setHintAndLabel(String hintString) {
        LabelLabel.setText(hintString);
        placeholder.setHint(hintString);
    }
    public void setHint(String hintString) {
        placeholder.setHint(hintString);
    }
    public void setLabel(String hintString) {
        LabelLabel.setText(hintString);
    }





}
