package com.jintin.jutil.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Keep;
import android.widget.Toast;

/**
 * @author Jintin
 * @version 1.0
 */
@Keep
public class JToast {

    public static void makeText(Context context, int resId) {
        makeText(context, context.getString(resId), Toast.LENGTH_LONG);
    }

    public static void makeText(Context context, String text) {
        makeText(context, text, Toast.LENGTH_LONG);
    }

    public static void makeText(Context context, int resId, int duration) {
        makeText(context, context.getString(resId), duration);
    }

    public static void makeText(Context context, String text, int duration) {
        if (!(context instanceof Activity) || ((Activity) context).isFinishing())
            return;
        try {
            Toast.makeText(context, text, duration).show();
        } catch (Exception ignore) {

        }
    }
}
