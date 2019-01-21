package com.cmykui.framework.cmykui.layout;

import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmykui.framework.cmykui.R;
import com.cmykui.framework.cmykui.base.OnClick;

public class InboxViewItem extends RelativeLayout {

    public ImageView itemImage;
    public TextView itemTitle;
    public ImageView itemArrow;

    private OnClick<Void> metoda;
    private Boolean override = false;


    public InboxViewItem(Context context) {
        super(context);
        init(context);
    }

    public InboxViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public InboxViewItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        inflate(getContext(), R.layout.inbox_item, this);

        itemImage = findViewById(R.id.LayoutInboxImage);
        itemArrow = findViewById(R.id.LayoutInboxAction);
        itemTitle = findViewById(R.id.LayoutInboxTitle);

        setOnClickListener(localOnClick);
    }

    public void setTitle(String title){
        itemTitle.setText(title);
    }
    public String getTitle() { return itemTitle.getText().toString(); }


    private View.OnClickListener localOnClick = new View.OnClickListener()
     {
        @Override
        public void onClick(View v) {

            //TODO: sto se lokalno dogada!
            InboxViewItem.this.onClick(v.getContext());
        }
    };

    public void onClick(Context context){

        if(override == true){
            try {
                metoda.onClick();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context);
            dlgAlert.setMessage("Uspjesno si stisnuo!");
            dlgAlert.setTitle("Layout");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }
    }

    public void setOnClickListener(OnClick<Void> m){
        override = true;
        metoda = m;
    }



}
