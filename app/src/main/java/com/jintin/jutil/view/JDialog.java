package com.jintin.jutil.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.IBinder;
import android.support.annotation.Keep;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;

import com.jintin.jutil.R;

import java.util.ArrayList;

/**
 * @author Jintin
 * @version 1.0
 */
@Keep
public class JDialog {

    private static String getText(Context context, Object msg) {
        if (msg == null) {
            return null;
        } else if (msg instanceof Integer) {
            return context.getString((Integer) msg);
        } else {
            return msg.toString();
        }
    }

    /**
     * One button dialog
     *
     * @param context
     * @param msg
     * @return
     */
    public static Dialog showMessage(Context context, Object msg) {
        return showMessage(context, null, msg, null);
    }

    public static Dialog showMessage(Context context, Object title, Object msg) {
        return showMessage(context, title, msg, null);
    }

    public static Dialog showMessage(Context context, Object msg, OnClickListener action) {
        return showMessage(context, null, msg, action);
    }

    public static Dialog showMessage(Context context, Object title, Object msg, OnClickListener action) {
        return createDialog(context, title, msg, action);
    }

    /**
     * Two button dialog
     *
     * @param context
     * @param msg
     * @param action
     * @return
     */
    public static Dialog showDialog(Context context, Object msg, OnClickListener... action) {
        return showDialog(context, null, msg, action);
    }

    public static Dialog showDialog(Context context, Object title, Object msg, OnClickListener... action) {
        if (action.length == 1) {
            return createDialog(context, title, msg, action[0], null);
        } else {
            return createDialog(context, title, msg, action);
        }
    }

    /**
     * Selectable list dialog
     *
     * @param context
     * @param title
     * @param list
     * @param listener
     * @return
     */
    public static Dialog showList(Context context, Object title, ArrayList<?> list, OnClickListener listener) {
        String[] items = new String[list.size()];
        for (int i = 0; i < items.length; i++) {
            items[i] = list.get(i).toString();
        }
        return showList(context, title, items, listener);
    }

    public static Dialog showList(Context context, Object title, String[] items, OnClickListener listener) {
        Builder builder = createBuilder(context, title, null);
        builder.setItems(items, listener);
        return builder.show();
    }

    public static Dialog showList(Context context, Object title, ListAdapter adapter, OnClickListener listener) {
        Builder builder = createBuilder(context, title, null);
        builder.setAdapter(adapter, listener);
        return builder.show();
    }

    /**
     * Custom view dialog
     *
     * @param context
     * @param title
     * @param view
     * @param action
     * @return
     */
    public static AlertDialog showView(Context context, Object title, View view, OnClickListener... action) {
        Builder builder = createBuilder(context, title, null);
        builder.setView(view);
        return show(context, builder, action);
    }

    public static Builder createBuilder(Context context, Object title, Object msg) {
        Builder builder = new Builder(context);
        builder.setTitle(title == null ? context.getString(R.string.dialog_title) : getText(context, title));
        if (msg != null)
            builder.setMessage(getText(context, msg));

        if (context instanceof Activity) {
            Activity act = (Activity) context;
            IBinder token = act.getWindow().getDecorView().getRootView().getWindowToken();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(token, 0);
        }

        return builder;
    }

    private static AlertDialog createDialog(Context context, Object title, Object msg, OnClickListener... action) {
        Builder builder = createBuilder(context, title, msg);
        return show(context, builder, action);
    }

    public static AlertDialog show(Context context, Builder builder, OnClickListener... action) {
        switch (action.length) {
            case 3:// TODO
                // builder.setNeutralButton(context.getString(R.string.dialog_neutral),
                // action.length > 2 ? action[2] : null);
            case 2:
                builder.setNegativeButton(context.getString(android.R.string.cancel), action.length > 1 ? action[1] : null);
            case 1:
            case 0:
                builder.setPositiveButton(context.getString(android.R.string.ok), action.length > 0 ? action[0] : null);
            default:
        }

        if (context == null)
            return null;
        if (!(context instanceof Activity) || ((Activity) context).isFinishing())
            return null;
        return builder.show();
    }

    public static void dismiss(DialogInterface dialog) {
        try {
            dialog.dismiss();
        } catch (final Exception ignore) {
        }
    }
}
