package com.cmykui.framework.cmykui.layout;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmykui.framework.cmykui.R;
import com.cmykui.framework.cmykui.base.OnClick;

public class ImageLayoutItem extends RelativeLayout {

    public ImageView itemImage;
    public TextView itemTitle;
    public ImageView itemArrow;

    private OnClick<Void> metoda;
    private Boolean override = false;

    private Boolean blurred = false;
    private Bitmap  oldImage;
    private Bitmap  blurredImage;


    public ImageLayoutItem(Context context) {
        super(context);
        init(context);
    }

    public ImageLayoutItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ImageLayoutItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        inflate(getContext(), R.layout.image_item, this);

        itemImage = findViewById(R.id.LayoutImageImage);
        itemTitle = findViewById(R.id.LayoutImageTitle);



        setOnClickListener(localOnClick);
    }

    public void setTitle(String title){
        itemTitle.setText(title);
    }
    public String getTitle() { return itemTitle.getText().toString(); }


    private OnClickListener localOnClick = new OnClickListener()
     {
        @Override
        public void onClick(View v) {

            //Bluranje
            if(oldImage == null){
                itemImage.buildDrawingCache();
                oldImage = itemImage.getDrawingCache();
            }

            if(blurred){
                itemImage.setImageBitmap(oldImage);
                blurred = false;
            }
            else {
                if(blurredImage == null) {
                    blurredImage = BlurBuilder.blur(v.getContext(), oldImage);
                }
                itemImage.setImageBitmap(blurredImage);
                blurred = true;
            }

            //TODO: Pokaži iteme
            toogleAllChilds();


            ImageLayoutItem.this.onClick(v.getContext());
        }
    };



    public void toogleAllChilds() {

        final int count = getChildCount();


        for (int i = 0; i < count; i++) {
            View temp = getChildAt(i);

            if (temp instanceof ImageLayoutItemMenuItem) {
                ImageLayoutItemMenuItem child = (ImageLayoutItemMenuItem) getChildAt(i);

                if (blurred) {
                    child.setVisibility(View.VISIBLE);
                } else {
                    child.setVisibility(View.GONE);
                }
            }
        }

    }

    public void onClick(Context context){

        if(override == true){
            try {
                metoda.onClick();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {

        }
    }

    public void setOnClickListener(OnClick<Void> m){
        override = true;
        metoda = m;
    }



}
