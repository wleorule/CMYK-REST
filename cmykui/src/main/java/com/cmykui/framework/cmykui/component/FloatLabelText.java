package com.cmykui.framework.cmykui.component;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cmykui.framework.cmykui.R;
import com.cmykui.framework.cmykui.base.ComponentInterface;

/**
 * The type Float label text.
 */
public class FloatLabelText extends LinearLayout implements ComponentInterface {

    /**
     * The Placeholder.
     */
    public TextView placeholder;
    /**
     * The Label label.
     */
    public TextView LabelLabel;

    /**
     * Instantiates a new Float label text.
     *
     * @param context the context
     */
    public FloatLabelText(Context context) {
        super(context);
        init(context);
    }

    /**
     * Instantiates a new Float label text.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public FloatLabelText(Context context, AttributeSet attrs) {
        super(context, attrs);init(context);
    }

    /**
     * Instantiates a new Float label text.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public FloatLabelText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);init(context);
    }

    /**
     * Instantiates a new Float label text.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     * @param defStyleRes  the def style res
     */
    public FloatLabelText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);init(context);
    }


    /**
     *  Method init initializes every initial value of attributes, and then calls the method inflate.
     *
     * @param context the context
     */
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

    /**
     * Sets hint and label.
     *
     * @param hintString the hint string
     */
    public void setHintAndLabel(String hintString) {
        LabelLabel.setText(hintString);
        placeholder.setHint(hintString);
    }

    /**
     * Sets hint.
     *
     * @param hintString the hint string
     */
    public void setHint(String hintString) {
        placeholder.setHint(hintString);
    }

    /**
     * Sets label.
     *
     * @param hintString the hint string
     */
    public void setLabel(String hintString) {
        LabelLabel.setText(hintString);
    }
}
