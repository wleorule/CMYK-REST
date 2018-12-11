package com.cmykui.framework.cmykui.layout;

import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cmykui.framework.cmykui.R;
import com.cmykui.framework.cmykui.base.LayoutInterface;

public class InboxView extends RelativeLayout {

    public InboxView(Context context) {
        super(context);
        init(context);
    }

    public InboxView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public InboxView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public InboxView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context){
        inflate(getContext(), R.layout.inbox_item, this);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("LEODEB", "leoeooeoeoeoeo");
            }
        });
    }


}
