package hr.foi.air.cmykui.component;

import android.app.AlertDialog;
import android.content.Context;





public class DialogComponent extends AlertDialog  {
    public DialogComponent(Context context) {
        super(context);
    }

    public DialogComponent(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public DialogComponent(Context context, int themeResId) {
        super(context, themeResId);
    }


}
