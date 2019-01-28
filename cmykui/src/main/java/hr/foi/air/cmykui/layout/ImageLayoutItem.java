package hr.foi.air.cmykui.layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import hr.foi.air.cmykui.R;
import hr.foi.air.cmykui.base.OnClick;

/**
 * The type Image layout item.
 */
public class ImageLayoutItem extends RelativeLayout {

    /**
     * The Item image.
     */
    public ImageView itemImage;
    /**
     * The Item title.
     */
    public TextView itemTitle;
    /**
     * The Image zoom.
     */
    public Boolean ImageZoom = true;

    private OnClick<Void> metoda;
    private Boolean override = false;

    private Boolean blurred = false;
    private Bitmap  oldImage;
    private Bitmap  blurredImage;
    private  LinearLayout.LayoutParams blurredLayoutParams;
    private  LinearLayout.LayoutParams oldLayoutParams;


    /**
     * Instantiates a new Image layout item.
     *
     * @param context the context
     */
    public ImageLayoutItem(Context context) {
        super(context);
        init(context);
    }

    /**
     * Instantiates a new Image layout item.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public ImageLayoutItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * Instantiates a new Image layout item.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public ImageLayoutItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * Init.
     *
     * @param context the context
     */
    public void init(Context context){
        inflate(getContext(), R.layout.image_item, this);

        itemImage = findViewById(R.id.LayoutImageImage);
        itemTitle = findViewById(R.id.LayoutImageTitle);



        setOnClickListener(localOnClick);
    }

    /**
     * Set title.
     *
     * @param title the title
     */
    public void setTitle(String title){
        itemTitle.setText(title);
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() { return itemTitle.getText().toString(); }


    private OnClickListener localOnClick = new OnClickListener()
     {
        @Override
        public void onClick(View v) {



            //Bluranje
            if(oldImage == null){
                itemImage.buildDrawingCache();
                oldImage = itemImage.getDrawingCache();

                int w = (int) Math.round(oldImage.getWidth());
                int h = (int) Math.round(oldImage.getHeight());

                oldLayoutParams = new LinearLayout.LayoutParams(w, h);
            }

            if(blurred){
                if(ImageZoom) {
                    itemImage.setLayoutParams(oldLayoutParams);
                }
                itemImage.setImageBitmap(oldImage);
                blurred = false;
            }
            else {
                if(blurredImage == null) {

                    blurredImage = BlurBuilder.blur(v.getContext(),oldImage);

                    int w = (int) Math.round(oldImage.getWidth() * 1.2);
                    int h = (int) Math.round(oldImage.getHeight() * 1.2);

                    blurredLayoutParams = new LinearLayout.LayoutParams(w, h);

                }

                if(ImageZoom) {
                    itemImage.setLayoutParams(blurredLayoutParams);
                }
                itemImage.setImageBitmap(blurredImage);
                blurred = true;
            }

            //TODO: Pokaži iteme
            toogleAllChilds();


            ImageLayoutItem.this.onClick(v.getContext());
        }
    };


    /**
     * Toogle all childs.
     */
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

    /**
     * On click.
     *
     * @param context the context
     */
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

    /**
     * Set on click listener.
     *
     * @param m the m
     */
    public void setOnClickListener(OnClick<Void> m){
        override = true;
        metoda = m;
    }



}
