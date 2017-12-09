package com.jintin.jutil.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Keep;
import android.util.TypedValue;

import java.util.List;

/**
 * @author Jintin
 * @version 1.0
 */
@Keep
public class JUtils {

    public static int dpToPx(int dp, Context ctx) {
        Resources r = ctx.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }

    public static String getNameById(Context context, int id) {
        return context.getResources().getResourceEntryName(id);
    }

    public static int getIdByName(Context context, String type, String name) {
        return context.getResources().getIdentifier(name, type, context.getPackageName());
    }

    public static <E> void switchData(List<E> list, int a, int b) {
        E aE = list.get(a);
        list.set(a, list.get(b));
        list.set(b, aE);
    }

    public static <E> void switchData(E[] list, int a, int b) {
        E aE = list[a];
        list[a] = list[b];
        list[b] = aE;
    }

}
