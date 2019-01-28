package hr.foi.air.cmykui.layout;

import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import hr.foi.air.cmykui.R;
import hr.foi.air.cmykui.base.OnClick;

/**
 * The type Inbox view item.
 */
public class InboxViewItem extends RelativeLayout {

    /**
     * The Item image.
     */
    public ImageView itemImage;
    /**
     * The Item title.
     */
    public TextView itemTitle;
    /**
     * The Item arrow.
     */
    public ImageView itemArrow;

    private OnClick<Void> metoda;
    private Boolean override = false;


    /**
     * Instantiates a new Inbox view item.
     *
     * @param context the context
     */
    public InboxViewItem(Context context) {
        super(context);
        init(context);
    }

    /**
     * Instantiates a new Inbox view item.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public InboxViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * Instantiates a new Inbox view item.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public InboxViewItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * Init.
     *
     * @param context the context
     */
    public void init(Context context){
        inflate(getContext(), R.layout.inbox_item, this);

        itemImage = findViewById(R.id.LayoutInboxImage);
        itemArrow = findViewById(R.id.LayoutInboxAction);
        itemTitle = findViewById(R.id.LayoutInboxTitle);

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


    private View.OnClickListener localOnClick = new View.OnClickListener()
     {
        @Override
        public void onClick(View v) {

            //TODO: sto se lokalno dogada!
            InboxViewItem.this.onClick(v.getContext());
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
