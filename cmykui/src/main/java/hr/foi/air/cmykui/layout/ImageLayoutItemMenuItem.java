package hr.foi.air.cmykui.layout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import hr.foi.air.cmykui.R;
import hr.foi.air.cmykui.base.OnClick;

/**
 * The type Image layout item menu item.
 */
public class ImageLayoutItemMenuItem extends RelativeLayout {

    /**
     * The Item image.
     */
    public ImageView itemImage;
    /**
     * The Item title.
     */
    public TextView itemTitle;

    private OnClick<Void> metoda;
    private Boolean override = false;


    /**
     * Instantiates a new Image layout item menu item.
     *
     * @param context the context
     */
    public ImageLayoutItemMenuItem(Context context) {
        super(context);
        init(context, "Menu item");
    }

    /**
     * Instantiates a new Image layout item menu item.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public ImageLayoutItemMenuItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray stilovi = context.obtainStyledAttributes(attrs, R.styleable.ImageLayoutItemMenuItem);
        String text = stilovi.getString(R.styleable.ImageLayoutItemMenuItem_text);

        init(context, text);
    }

    /**
     * Instantiates a new Image layout item menu item.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public ImageLayoutItemMenuItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray stilovi = context.obtainStyledAttributes(attrs, R.styleable.ImageLayoutItemMenuItem);
        String text = stilovi.getString(R.styleable.ImageLayoutItemMenuItem_text);

        init(context, text);
    }

    /**
     * Init.
     *
     * @param context the context
     * @param text    the text
     */
    public void init(Context context, String text){
        inflate(getContext(), R.layout.image_layout_item_menu_item, this);

        itemImage = findViewById(R.id.LayoutImageMenuImage);
        itemTitle = findViewById(R.id.LayoutImageMenuText);

        itemTitle.setText(text);

        setOnClickListener(localOnClick);
    }

    /**
     * Set title.
     *
     * @param title the title
     */
    public void setTitle(String title){
        itemTitle.  setText(title);
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
            //TODO: Hendlaj
            ImageLayoutItemMenuItem.this.onClick(v.getContext());
        }
    };


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
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context);
            dlgAlert.setMessage("Uspjesno si stisnuo!");
            dlgAlert.setTitle("Layout");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
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
